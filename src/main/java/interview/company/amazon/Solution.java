package interview.company.amazon;

/**
 * 题目描述：
 * 在 Amazon 的物流中心，团队需要对物品进行包装。给定一个数组 `item_weights`，其中包含 `n` 个物品的重量。
 * 他们需要删除 **n/3** 个物品，确保剩余物品的顺序不变，并计算新的数组 `new_arr` 的 `sum_arr` 值：
 * `sum_arr = 前半部分的总和 - 后半部分的总和`
 *
 * 任务：
 * 计算能够获得的最大 `sum_arr` 值。
 *
 * 示例：
 * 输入：
 *   n = 6
 *   item_weights = [1, 3, 4, 7, 5, 2]
 *
 * 可能的 `new_arr`：
 * - 删除 {1, 3} -> new_arr = [4, 7, 5, 2] -> sum_arr = (4+7) - (5+2) = 4
 *
 * 输出：
 *   4
 */

/**
 * 解题思路：
 * 1. **定义两个部分：**
 *    - 我们需要移除 **n/3** 个元素，剩余数组长度为 `2k`（其中 `k = n/3`）。
 *    - `new_arr` 被分为 **前半部分 k 个元素** 和 **后半部分 k 个元素**。
 *
 * 2. **使用优先队列计算最大 `k` 元素和 (leftSums)**
 *    - 遍历 `item_weights`，维护一个 **小顶堆**，保证前 `i` 个元素中最大的 `k` 个元素的总和。
 *    - `leftSums[i]` 存储 **从 `0` 到 `i-1` 选择 `k` 个最大元素的总和**。
 *
 * 3. **使用优先队列计算最小 `k` 元素和 (rightSums)**
 *    - 反向遍历 `item_weights`，维护一个 **大顶堆**，保证后 `i` 个元素中最小的 `k` 个元素的总和。
 *    - `rightSums[i]` 存储 **从 `i` 到 `n-1` 选择 `k` 个最小元素的总和**。
 *
 * 4. **找到 `leftSums[i] - rightSums[i]` 的最大值**
 *    - 遍历 `k ≤ i ≤ 2k`，确保 `new_arr` 的两部分长度相等，计算 `sum_arr` 最大值。
 *
 * **示例解释：**
 * ```
 *   item_weights = [1, 3, 4, 7, 5, 2], k = 2
 *   可能的 `new_arr` 组合：
 *   - 删除 [1, 3] => new_arr = [4, 7, 5, 2] => sum_arr = (4+7) - (5+2) = 4 (最大)
 *   - 删除 [3, 4] => new_arr = [1, 7, 5, 2] => sum_arr = (1+7) - (5+2) = 1
 * ```

 * 时间 & 空间复杂度：
 * - **时间复杂度：** O(n log k) = O(n log (n/3)) ≈ **O(n log n)**
 *   - 遍历数组 O(n)
 *   - 堆操作 O(n log k)
 *   - 计算最大值 O(n)
 * - **空间复杂度：** O(n)
 *   - `leftSums` 和 `rightSums` 需要 O(n)
 *   - 额外的堆存储 O(k)
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    public int getMaxSumArr(int[] item_weights) {
        int n = item_weights.length;
        int k = n / 3;  // 要移除的元素数量

        // 1) 计算 leftSums：前 i 个元素里，选 k 个最大元素的和
        long[] leftSums = new long[n + 1];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 小顶堆
        long currentSum = 0;

        for (int i = 0; i < n; i++) {
            minHeap.offer(item_weights[i]);
            currentSum += item_weights[i];

            if (minHeap.size() > k) {
                currentSum -= minHeap.poll();  // 移除最小元素
            }
            leftSums[i + 1] = (minHeap.size() == k) ? currentSum : Long.MIN_VALUE;
        }

        // 2) 计算 rightSums：后 i 个元素里，选 k 个最小元素的和
        long[] rightSums = new long[n + 1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 大顶堆
        currentSum = 0;

        for (int i = n - 1; i >= 0; i--) {
            maxHeap.offer(item_weights[i]);
            currentSum += item_weights[i];

            if (maxHeap.size() > k) {
                currentSum -= maxHeap.poll();  // 移除最大元素
            }
            rightSums[i] = (maxHeap.size() == k) ? currentSum : Long.MAX_VALUE;
        }

        // 3) 遍历 k ≤ i ≤ 2k，求最大 (leftSums[i] - rightSums[i])
        long answer = Long.MIN_VALUE;
        for (int i = k; i <= 2 * k; i++) {
            if (leftSums[i] != Long.MIN_VALUE && rightSums[i] != Long.MAX_VALUE) {
                answer = Math.max(answer, leftSums[i] - rightSums[i]);
            }
        }

        return (int) answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] test1 = {1, 3, 4, 7, 5, 2};
        System.out.println("最大 sum_arr: " + sol.getMaxSumArr(test1)); // 预期输出: 4

        int[] test2 = {3, 2, 1};
        System.out.println("最大 sum_arr: " + sol.getMaxSumArr(test2)); // 2
    }
}

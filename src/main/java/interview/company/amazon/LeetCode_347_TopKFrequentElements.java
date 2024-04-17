package interview.company.amazon;

import java.util.*;

/**
 *@Question:  347. Top K Frequent Elements
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 75.33%
 *@Time  Complexity: O(N) for quickselect, O(NlogK) for heap
 *@Space Complexity: O(N) for quickselect, O(N + K) for heap
 */

/**
 * **解题思路：**
 *
 * 题目要求找出给定数组中出现频率最高的前 K 个元素。两种主要的解法是使用堆（优先队列）和快速选择。
 *
 * **解法 1: 堆（优先队列）**
 *
 * 1. 首先，遍历数组，使用哈希表记录每个元素的出现频率。
 * 2. 利用优先队列（最小堆）来保持数组中前 K 个高频元素，每次添加元素时，若队列大小超过 K，则移除堆顶元素，以保持堆的大小为 K。
 * 3. 最终，从堆中取出前 K 个高频元素，即为结果。
 *
 * **解法 2: 快速选择**
 *
 * 1. 遍历数组，使用哈希表记录每个元素的出现频率。
 * 2. 将所有不同的元素放入一个数组中。
 * 3. 使用快速选择算法（快速排序的一部分）找到数组中第 (n - k) 个元素的位置，其中 n 为数组中不同元素的个数。
 * 4. 返回数组中第 (n - k) 个元素到最后一个元素的子数组，即为前 K 个高频元素。
 *
 * **时间复杂度：**
 *
 * - 解法 1（堆）：由于使用优先队列，插入和删除的时间复杂度为 O(logK)，总体时间复杂度为 O(N logK)，其中 N 为数组长度。
 * - 解法 2（快速选择）：构建哈希表的时间复杂度为 O(N)，快速选择的平均时间复杂度为 O(N)，总体时间复杂度为 O(N)。
 *
 * **空间复杂度：**
 *
 * - 解法 1（堆）：哈希表用于记录元素频率，优先队列用于维护前 K 个高频元素，空间复杂度为 O(N + K)。
 * - 解法 2（快速选择）：哈希表用于记录元素频率，数组用于存储不同元素，空间复杂度为 O(N)。
 */

public class LeetCode_347_TopKFrequentElements {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法 1: 堆
        public int[] topKFrequent(int[] nums, int k) {
            // O(1) 时间
            if (k == nums.length) {
                return nums;
            }

            // 1. 构建哈希映射：数字及其出现频率
            // O(N) 时间
            Map<Integer, Integer> count = new HashMap();
            for (int n : nums) {
                count.put(n, count.getOrDefault(n, 0) + 1);
            }

            // 初始化堆，按照出现频率从小到大排列
            Queue<Integer> heap = new PriorityQueue<>(
                    (n1, n2) -> count.get(n1) - count.get(n2));

            // 2. 保持堆中仅包含前 k 个高频元素
            // O(N log k) < O(N log N) 时间
            for (int n : count.keySet()) {
                heap.add(n);
                if (heap.size() > k) heap.poll();
            }

            // 3. 构建输出数组
            // O(k log k) 时间
            int[] top = new int[k];
            for (int i = k - 1; i >= 0; --i) {
                top[i] = heap.poll();
            }
            return top;
        }

        // 解法 2: 快速选择
        int[] unique;
        Map<Integer, Integer> count;

        public void swap(int a, int b) {
            int tmp = unique[a];
            unique[a] = unique[b];
            unique[b] = tmp;
        }

        public int partition(int left, int right, int pivot_index) {
            int pivot_frequency = count.get(unique[pivot_index]);
            // 1. 将基准元素移到末尾
            swap(pivot_index, right);
            int store_index = left;

            // 2. 将所有频率较低的元素移到左侧
            for (int i = left; i <= right; i++) {
                if (count.get(unique[i]) < pivot_frequency) {
                    swap(store_index, i);
                    store_index++;
                }
            }

            // 3. 将基准元素移到最终位置
            swap(store_index, right);

            return store_index;
        }

        public void quickselect(int left, int right, int k_smallest) {
            /*
             * 将数组部分排序，直到第 k 个较低频的元素就位。
             */

            // 基准情况：数组仅包含一个元素
            if (left == right) return;

            // 选择一个随机的基准元素
            Random random_num = new Random();
            int pivot_index = left + random_num.nextInt(right - left);

            // 在有序数组中找到基准元素的位置
            pivot_index = partition(left, right, pivot_index);

            // 如果基准元素已经位于最终位置
            if (k_smallest == pivot_index) {
                return;
            } else if (k_smallest < pivot_index) {
                // 向左移动
                quickselect(left, pivot_index - 1, k_smallest);
            } else {
                // 向右移动
                quickselect(pivot_index + 1, right, k_smallest);
            }
        }

        public int[] topKFrequent2(int[] nums, int k) {
            // 构建哈希映射：数字及其出现频率
            count = new HashMap();
            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            // 数组中的唯一元素
            int n = count.size();
            unique = new int[n];
            int i = 0;
            for (int num : count.keySet()) {
                unique[i] = num;
                i++;
            }

            // 第 k 个高频元素即为 (n - k)th 低频元素。
            // 进行部分排序：从低频到高频，直到第 (n - k)th 低频元素就位于排序后的数组中。
            // 左侧的所有元素频率较低。
            // 右侧的所有元素频率较高。
            quickselect(0, n - 1, n - k);
            // 返回前 k 个高频元素
            return Arrays.copyOfRange(unique, n - k, n);
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试代码
        leetcode.question.heap.LeetCode_347_TopKFrequentElements.Solution solution = new leetcode.question.heap.LeetCode_347_TopKFrequentElements().new Solution();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = solution.topKFrequent(nums, k);
        System.out.println("前 " + k + " 个高频元素: " + Arrays.toString(result));
        // 输出：前 2 个高频元素: [1, 2]
    }
}

/**
 Given an integer array nums and an integer k, return the k most frequent
 elements. You may return the answer in any order.


 Example 1:
 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]

 Example 2:
 Input: nums = [1], k = 1
 Output: [1]


 Constraints:


 1 <= nums.length <= 10⁵
 -10⁴ <= nums[i] <= 10⁴
 k is in the range [1, the number of unique elements in the array].
 It is guaranteed that the answer is unique.



 Follow up: Your algorithm's time complexity must be better than O(n log n),
 where n is the array's size.

 Related Topics Array Hash Table Divide and Conquer Sorting Heap (Priority
 Queue) Bucket Sort Counting Quickselect 👍 16557 👎 610

 */
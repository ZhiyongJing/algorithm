package leetcode.question.monotonic_stack_queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 *@Question:  239. Sliding Window Maximum
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(n)
 *@Space Complexity: O(k)
 */

/**
 * ### 解题思路详细讲解
 *
 * **问题描述：**
 *
 * 给定一个整数数组 `nums` 和一个大小为 `k` 的滑动窗口，请找出每个滑动窗口中的最大值。
 *
 * **解题思路：**
 *
 * 1. **初始化数据结构：**
 *    - 创建一个双端队列（`Deque`） `dq` 用来存储数组元素的索引。`dq` 中存储的索引对应的数组元素将会是递减的
 *    （即 `dq` 中的第一个元素总是当前窗口中的最大值）。
 *    - 创建一个列表 `res` 用来存储每个窗口的最大值。
 *
 * 2. **处理前 `k` 个元素：**
 *    - 遍历数组的前 `k` 个元素，对于每个元素：
 *      - 如果当前元素大于等于 `dq` 队尾索引对应的元素，则从 `dq` 队尾移除这些索引，确保 `dq` 队列中的元素保持递减。
 *      - 将当前元素的索引添加到 `dq` 队尾。
 *    - 处理完前 `k` 个元素后，将 `dq` 队首索引对应的元素（即最大值）添加到结果列表 `res` 中。
 *
 * 3. **处理剩余的元素：**
 *    - 从第 `k` 个元素开始遍历数组，对于每个元素：
 *      - 检查 `dq` 队首索引是否在当前窗口外（即索引小于 `i - k`），如果是则移除它。
 *      - 同样地，确保 `dq` 队列中的元素保持递减，对于当前元素，如果大于等于 `dq` 队尾索引对应的元素，则从 `dq` 队尾移除这些索引。
 *      - 将当前元素的索引添加到 `dq` 队尾。
 *      - 将 `dq` 队首索引对应的元素（即最大值）添加到结果列表 `res` 中。
 *
 * 4. **返回结果：**
 *    - 最后将结果列表 `res` 转换为数组并返回。
 *
 * ### 时间复杂度和空间复杂度
 *
 * **时间复杂度：**
 *
 * - 每个元素最多被插入和删除 `dq` 一次，因此时间复杂度为 `O(n)`，其中 `n` 是数组 `nums` 的长度。
 *
 * **空间复杂度：**
 *
 * - 双端队列 `dq` 最多存储 `k` 个元素的索引，因此空间复杂度为 `O(k)`。
 *
 * 综上，解决滑动窗口最大值问题的高效算法可以在 `O(n)` 时间复杂度和 `O(k)` 空间复杂度下完成。这使得该算法在处理大规模数据时表现良好。
 */

public class LeetCode_239_SlidingWindowMaximum{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            // 使用双端队列来存储窗口中的元素索引
            Deque<Integer> dq = new ArrayDeque<>();
            // 用一个列表来存储结果
            List<Integer> res = new ArrayList<>();

            // 初始化双端队列，处理前k个元素
            for (int i = 0; i < k; i++) {
                // 保持双端队列中的元素是递减的
                while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                    dq.pollLast(); // 移除队列末尾的元素
                }
                dq.offerLast(i); // 将当前元素的索引添加到队列末尾
            }
            res.add(nums[dq.peekFirst()]); // 将第一个窗口的最大值添加到结果中

            // 遍历剩余的元素
            for (int i = k; i < nums.length; i++) {
                // 如果队列头部的索引不在当前窗口中，移除它
                if (dq.peekFirst() == i - k) {
                    dq.pollFirst();
                }
                // 保持双端队列中的元素是递减的
                while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                    dq.pollLast(); // 移除队列末尾的元素
                }

                dq.offerLast(i); // 将当前元素的索引添加到队列末尾
                res.add(nums[dq.peekFirst()]); // 将当前窗口的最大值添加到结果中
            }
            // 将结果列表转换为数组并返回
            return res.stream().mapToInt(i -> i).toArray();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_239_SlidingWindowMaximum().new Solution();
        // 测试样例
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        // 打印结果: [3, 3, 5, 5, 6, 7]
        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, k)));
    }
}

/**
 You are given an array of integers nums, there is a sliding window of size k
 which is moving from the very left of the array to the very right. You can only
 see the k numbers in the window. Each time the sliding window moves right by one
 position.

 Return the max sliding window.


 Example 1:


 Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 Output: [3,3,5,5,6,7]
 Explanation:
 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7


 Example 2:


 Input: nums = [1], k = 1
 Output: [1]



 Constraints:


 1 <= nums.length <= 10⁵
 -10⁴ <= nums[i] <= 10⁴
 1 <= k <= nums.length


 Related Topics Array Queue Sliding Window Heap (Priority Queue) Monotonic
 Queue 👍 18043 👎 670

 */
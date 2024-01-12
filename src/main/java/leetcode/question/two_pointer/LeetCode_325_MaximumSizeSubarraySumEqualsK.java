package leetcode.question.two_pointer;

import java.util.HashMap;

/**
 * @Question:  325. Maximum Size Subarray Sum Equals k
 * @Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 36.32%
 * @Time  Complexity: O(N) N as the length of nums
 * @Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题的目标是找到数组中和为 k 的最大子数组的长度。为了解决这个问题，我们使用了一个前缀和的思想，同时使用 HashMap 来记录前缀和及其对应的索引。
 *
 * 1. **前缀和：** 我们遍历数组，计算每个位置的前缀和，并将其保存在 `prefixSum` 变量中。`prefixSum` 表示从数组起始位置到当前位置的和。
 *
 * 2. **检查当前位置：** 在遍历的过程中，我们不仅计算前缀和，还检查当前位置前的子数组和是否等于目标值 k。
 * 如果等于，说明当前位置就是一个满足条件的子数组，此时更新 `longestSubarray` 的值。
 *
 * 3. **更新最大子数组长度：** 如果在 HashMap 中找到了前缀和为 `prefixSum - k` 的索引，说明在这两个位置之间的子数组和为 k。
 * 因此，我们更新 `longestSubarray` 的值，取当前位置索引减去之前相同前缀和的索引。
 *
 * 4. **更新 HashMap：** 将当前前缀和及其对应的索引加入 HashMap 中，以便后续的检查。
 *
 * 5. **返回结果：** 遍历完成后，`longestSubarray` 就是最大子数组的长度。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度：** O(N)，其中 N 是数组的长度。我们只对数组进行一次遍历。
 *
 * - **空间复杂度：** O(N)，使用了 HashMap 存储前缀和及其索引，最坏情况下，HashMap 的大小会达到数组的长度。
 */

public class LeetCode_325_MaximumSizeSubarraySumEqualsK {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算最大子数组长度
         *
         * @param nums 数组
         * @param k 目标和
         * @return 最大子数组长度
         */
        public int maxSubArrayLen(int[] nums, int k) {
            int prefixSum = 0; // 当前位置前缀和
            int longestSubarray = 0; // 最大子数组长度
            HashMap<Integer, Integer> indices = new HashMap<>(); // 保存前缀和及其对应的索引
            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];

                // 检查是否当前位置之前的所有数字的和等于 k。
                if (prefixSum == k) {
                    longestSubarray = i + 1; // 更新最大子数组长度
                }

                // 如果之前的某个子数组的和等于 k，更新最大子数组长度。
                if (indices.containsKey(prefixSum - k)) {
                    longestSubarray = Math.max(longestSubarray, i - indices.get(prefixSum - k));
                }

                // 如果前缀和不在 map 中，将当前前缀和和索引加入 map。
                if (!indices.containsKey(prefixSum)) {
                    indices.put(prefixSum, i);
                }
            }

            return longestSubarray;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_325_MaximumSizeSubarraySumEqualsK().new Solution();
        // 测试代码
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        System.out.println(solution.maxSubArrayLen(nums, k));  // 应该输出 4
    }
}

/**
Given an integer array nums and an integer k, return the maximum length of a 
subarray that sums to k. If there is not one, return 0 instead. 

 
 Example 1: 

 
Input: nums = [1,-1,5,-2,3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 

 Example 2: 

 
Input: nums = [-2,-1,2,1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 

 
 Constraints: 

 
 1 <= nums.length <= 2 * 10⁵ 
 -10⁴ <= nums[i] <= 10⁴ 
 -10⁹ <= k <= 10⁹ 
 

 Related Topics Array Hash Table Prefix Sum 👍 2001 👎 62

*/

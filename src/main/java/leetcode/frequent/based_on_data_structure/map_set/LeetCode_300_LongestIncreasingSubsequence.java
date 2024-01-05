package leetcode.frequent.based_on_data_structure.map_set;

import java.util.ArrayList;
import java.util.Arrays;

/**
  *@Question:  300. Longest Increasing Subsequence     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 75.54%      
  *@Time  Complexity: O(NlogN) for binary search , O(N^2) for dp
  *@Space Complexity: O(N) for binary search and db
 */
/**
 * 这道题的解题思路使用了两种方法：二分查找和动态规划。
 *
 * ### 1. 二分查找方法
 *
 * #### 解题思路：
 *
 * - 维护一个辅助数组 `sub`，用于存储当前最长递增子序列。
 * - 从头遍历给定数组 `nums`。
 * - 如果当前数字大于 `sub` 中的最后一个数字，直接添加到 `sub` 中，因为可以构成更长的递增子序列。
 * - 如果当前数字不大于 `sub` 中的最后一个数字，使用二分查找找到 `sub` 中第一个不小于当前数字的位置，
 * 将其替换为当前数字。
 *
 * #### 复杂度分析：
 *
 * - 时间复杂度：O(NlogN)，其中 N 是数组的长度。在二分查找中，每个元素最多被比较一次，而数组的遍历复杂度为 O(N)。
 * - 空间复杂度：O(N)，因为需要额外的空间来存储辅助数组 `sub`。
 *
 * ### 2. 动态规划方法
 *
 * #### 解题思路：
 *
 * - 维护一个长度为 `nums.length` 的数组 `dp`，其中 `dp[i]` 表示以 `nums[i]` 结尾的最长递增子序列的长度。
 * - 初始化 `dp` 数组，将每个元素初始化为 1。
 * - 从数组的第二个元素开始，对于每个元素 `nums[i]`，遍历其之前的所有元素 `nums[j]`。
 *   - 如果 `nums[i] > nums[j]`，则更新 `dp[i] = max(dp[i], dp[j] + 1)`，表示可以将 `nums[i]`
 *   加入到以 `nums[j]` 结尾的子序列中，构成更长的子序列。
 * - 最终，返回 `dp` 数组中的最大值，即最长递增子序列的长度。
 *
 * #### 复杂度分析：
 *
 * - 时间复杂度：O(N^2)，其中 N 是数组的长度。需要双层循环遍历数组。
 * - 空间复杂度：O(N)，因为需要额外的空间存储 `dp` 数组。
 *
 * 综上所述，二分查找方法的时间复杂度较低，为 O(NlogN)，而动态规划方法的时间复杂度为 O(N^2)。
 * 但是在实际应用中，通过使用动态规划的优化版本或其他算法，可以将动态规划的时间复杂度降低到 O(NlogN)。
 */

public class LeetCode_300_LongestIncreasingSubsequence {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // Solution 1: 二分查找
        public int lengthOfLIS(int[] nums) {
            ArrayList<Integer> sub = new ArrayList<>();
            sub.add(nums[0]);

            for (int i = 1; i < nums.length; i++) {
                int num = nums[i];
                if (num > sub.get(sub.size() - 1)) {
                    sub.add(num);
                } else {
                    int j = binarySearch(sub, num);
                    sub.set(j, num);
                }
            }

            return sub.size();
        }

        private int binarySearch(ArrayList<Integer> sub, int num) {
            int left = 0;
            int right = sub.size() - 1;
            int mid;

            while (left < right) {
                mid = (left + right) / 2;
//                if (sub.get(mid) == num) {
//                    return mid;
//                }

                if (sub.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

        // Solution 2: 动态规划(容易思考)
        public int lengthOfLIS2(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);

            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            int longest = 0;
            for (int c : dp) {
                longest = Math.max(longest, c);
            }

            return longest;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_300_LongestIncreasingSubsequence.Solution solution =
                new LeetCode_300_LongestIncreasingSubsequence().new Solution();

        // 测试用例1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Test Case 1: " + solution.lengthOfLIS(nums1)); // 预期输出: 4

        // 测试用例2
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("Test Case 2: " + solution.lengthOfLIS(nums2)); // 预期输出: 4

        // 测试用例3
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("Test Case 3: " + solution.lengthOfLIS(nums3)); // 预期输出: 1
    }
}
/**
Given an integer array nums, return the length of the longest strictly 
increasing subsequence. 

 
 Example 1: 

 
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the 
length is 4.
 

 Example 2: 

 
Input: nums = [0,1,0,3,2,3]
Output: 4
 

 Example 3: 

 
Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

 
 Constraints: 

 
 1 <= nums.length <= 2500 
 -10⁴ <= nums[i] <= 10⁴ 
 

 
 Follow up: Can you come up with an algorithm that runs in O(n log(n)) time 
complexity? 

 Related Topics Array Binary Search Dynamic Programming 👍 19344 👎 366

*/
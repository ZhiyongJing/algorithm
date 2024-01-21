package leetcode.question.dp;

/**
  *@Question:  213. House Robber II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 57.4%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

/**
 * 这是一个有关动态规划（Dynamic Programming）的问题，问题是在一排房屋中选择一些进行偷窃，
 * 但有一个限制条件：不能同时偷窃相邻的两个房屋。题目中给定的是一个环形的街道，
 * 即第一个房屋和最后一个房屋是相邻的，因此我们需要分两种情况进行考虑：
 *
 * 1. **不偷最后一个房屋：**
 *    在这种情况下，我们可以将问题转化为一个简单的打家劫舍问题（House Robber I），
 *    即在一排房屋中选择一些进行偷窃，但不能偷窃相邻的两个房屋。我们使用动态规划来解决这个简化问题。
 *    定义一个数组 `dp`，其中 `dp[i]` 表示前 `i` 个房屋能够偷窃到的最高金额。
 *    状态转移方程为：`dp[i] = max(dp[i-1], dp[i-2] + nums[i])`。
 *    这样，最终 `dp[nums.length-2]` 就是不偷最后一个房屋的最高偷窃金额。
 *
 * 2. **不偷第一个房屋：**
 *    同样地，我们可以将问题转化为一个简单的打家劫舍问题，不偷第一个房屋。
 *    使用相同的动态规划方法，最终 `dp[nums.length-1]` 就是不偷第一个房屋的最高偷窃金额。
 *
 * 最后，取这两种情况的最大值即可。
 *
 * 下面是完整的解题思路：
 *
 * ```plaintext
 * 1. 如果没有房屋可偷，返回0。
 * 2. 如果只有一个房屋，直接返回该房屋的金额。
 * 3. 分两种情况计算最大值：
 *    a. 不偷最后一个房屋，转化为简单的打家劫舍问题，使用动态规划计算偷窃最高金额。
 *    b. 不偷第一个房屋，同样转化为简单的打家劫舍问题，使用动态规划计算偷窃最高金额。
 * 4. 返回两种情况的最大值。
 * ```
 *
 * **时间复杂度：** 动态规划过程中需要遍历房屋数组，因此时间复杂度为 O(n)，其中 n 是房屋的数量。
 *
 * **空间复杂度：** 动态规划过程中只使用了常数级别的额外空间，因此空间复杂度为 O(1)。
 */


public class LeetCode_213_HouseRobberIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 打家劫舍 II
         *
         * @param nums 包含每个房屋可获得的金额的数组
         * @return 可以在不触动报警装置的情况下偷窃到的最高金额
         */
        public int rob(int[] nums) {
            // 如果没有房屋可偷，返回0
            if (nums.length == 0)
                return 0;

            // 如果只有一个房屋，直接返回该房屋的金额
            if (nums.length == 1)
                return nums[0];

            // 分两种情况计算最大值，一种是不偷最后一个房屋，一种是不偷第一个房屋
            int max1 = rob_simple(nums, 0, nums.length - 2);
            int max2 = rob_simple(nums, 1, nums.length - 1);

            // 返回两种情况的最大值
            return Math.max(max1, max2);
        }

        /**
         * 简化的打家劫舍问题
         *
         * @param nums 包含每个房屋可获得的金额的数组
         * @param start 计算的起始位置
         * @param end 计算的结束位置
         * @return 可以在不触动报警装置的情况下偷窃到的最高金额
         */
        public int rob_simple(int[] nums, int start, int end) {
            int t1 = 0;
            int t2 = 0;

            // 遍历计算从start到end范围内的最高偷窃金额
            for (int i = start; i <= end; i++) {
                int temp = t1;
                int current = nums[i];
                // 更新两个变量，分别表示上一次和上上次的最高偷窃金额
                t1 = Math.max(current + t2, t1);
                t2 = temp;
            }

            // 返回最终的最高偷窃金额
            return t1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_213_HouseRobberIi lcr = new LeetCode_213_HouseRobberIi();
        Solution solution = lcr.new Solution();

        // 测试代码
        int[] nums = {2, 3, 2};
        System.out.println(solution.rob(nums)); // 预期输出: 3
    }
}

/**
You are a professional robber planning to rob houses along a street. Each house 
has a certain amount of money stashed. All houses at this place are arranged in 
a circle. That means the first house is the neighbor of the last one. Meanwhile,
 adjacent houses have a security system connected, and it will automatically 
contact the police if two adjacent houses were broken into on the same night. 

 Given an integer array nums representing the amount of money of each house, 
return the maximum amount of money you can rob tonight without alerting the police.
 

 
 Example 1: 

 
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2)
, because they are adjacent houses.
 

 Example 2: 

 
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
 

 Example 3: 

 
Input: nums = [1,2,3]
Output: 3
 

 
 Constraints: 

 
 1 <= nums.length <= 100 
 0 <= nums[i] <= 1000 
 

 Related Topics Array Dynamic Programming 👍 9454 👎 137

*/
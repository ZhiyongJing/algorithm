package leetcode.question.dp;

import java.util.Arrays;
/**
  *@Question:  198. House Robber     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 70.07%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N) for recursion, O(1) for dp
 */

/**
 * ### 解题思路
 *
 * #### 自顶向下的递归方式（Solution 1）
 *
 * 1. **初始化缓存数组：** 创建一个长度为 100 的缓存数组 `memo`，用于存储已计算的递归结果，初始值为 -1。
 *
 * 2. **递归函数 `rob`：** 创建递归函数 `robFrom`，表示从第 `i` 个房子开始抢劫到最后一个房子所能获得的最大金额。
 *
 * 3. **递归终止条件：** 如果当前房子索引 `i` 大于等于数组长度 `nums.length`，说明没有更多的房子可抢，返回 0。
 *
 * 4. **递归缓存：** 在递归函数内部，首先判断缓存数组 `memo[i]` 是否已经计算过，如果是则直接返回缓存的结果。
 *
 * 5. **递归关系：** 递归关系根据两种选择：抢劫当前房子或者不抢劫。递归调用 `robFrom(i + 1, nums)` 表示不抢劫当前房子，
 * 递归调用 `robFrom(i + 2, nums) + nums[i]` 表示抢劫当前房子，并获得 `nums[i]` 的金额。
 *
 * 6. **更新缓存：** 将递归计算的结果存入缓存数组 `memo[i]` 中，并返回结果。
 *
 * #### 自底向上的动态规划方式（Solution 2）
 *
 * 1. **初始化两个变量：** 创建两个变量 `robNext` 和 `robNextPlusOne`，分别表示抢劫下一个房子和下下个房子时的最大金额。
 *
 * 2. **基本情况初始化：** `robNextPlusOne` 初始化为 0，`robNext` 初始化为最后一个房子的金额 `nums[N - 1]`。
 *
 * 3. **动态规划计算：** 从倒数第二个房子开始向前遍历，对于每个房子，计算抢劫和不抢劫两种选择中的最大值，并更新两个变量的值。
 *
 * 4. **返回结果：** 最终返回变量 `robNext`，表示从第一个房子到最后一个房子所能获得的最大金额。
 *
 * ### 时间复杂度
 *
 * - 对于自顶向下的递归方式，每个房子的状态只计算一次，所以时间复杂度是 O(N)，其中 N 为房子的数量。
 *
 * - 对于自底向上的动态规划方式，同样需要遍历每个房子一次，时间复杂度也是 O(N)。
 *
 * ### 空间复杂度
 *
 * - 自顶向下的递归方式使用了缓存数组 `memo`，占用了 O(N) 的额外空间，其中 N 为房子的数量。
 *
 * - 自底向上的动态规划方式只使用了常数级别的额外空间，所以空间复杂度是 O(1)。
 */

public class LeetCode_198_HouseRobber {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // Solution 1: 自顶向下的递归方式

        private int[] memo;

        public int rob1(int[] nums) {
            this.memo = new int[100];
            // 用表示尚未计算的递归的哨兵值填充数组。
            Arrays.fill(this.memo, -1);
            return this.robFrom(0, nums);
        }

        private int robFrom(int i, int[] nums) {
            // 没有更多的房子要检查。
            if (i >= nums.length) {
                return 0;
            }
            // 返回缓存的值。
            if (this.memo[i] > -1) {
                return this.memo[i];
            }
            // 通过递归关系评估以获得最佳答案。
            int ans = Math.max(this.robFrom(i + 1, nums), this.robFrom(i + 2, nums) + nums[i]);
            // 缓存供将来使用。
            this.memo[i] = ans;
            return ans;
        }

        //Solution 2:
        //dp[n] = Math.max(dp[n-2] + nums[n-1], dp[n-1])
        public int rob(int[] nums){
            int n = nums.length;
            int[] dp = new int[n + 1];
            dp[1] = nums[0];
            dp[0] = 0;
//            dp[2] = Math.max(nums[0], nums[1]);
            for(int i = 2; i<= n; i++){
                dp[i] = Math.max(dp[i-2]+ nums[i-1], dp[i - 1]);
            }
            return dp[n];
        }


        // Solution 3: 基于 Solution2 的空间优化
        public int rob3(int[] nums) {
            int N = nums.length;
            // 对于空数组的特殊处理。
            if (N == 0) {
                return 0;
            }
            int robNext, robNextPlusOne;
            // 基本情况初始化。
            robNextPlusOne = 0;
            robNext = nums[N - 1];
            // DP 表计算。注意：我们这里不使用任何表来存储值。只使用两个变量就足够了。
            for (int i = N - 2; i >= 0; --i) {
                // 与递归解法相同。
                int current = Math.max(robNext, robNextPlusOne + nums[i]);
                // 更新变量
                robNextPlusOne = robNext;
                robNext = current;
            }
            return robNext;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_198_HouseRobber().new Solution();

        // 测试用例
        int[] nums = {2, 7, 9, 3, 1};
        int result = solution.rob1(nums);
        System.out.println(result);  // 输出：12

        // 或者使用 Solution 2 进行测试
        int result2 = solution.rob3(nums);
        System.out.println(result2);  // 输出：12
    }
}

/**
You are a professional robber planning to rob houses along a street. Each house 
has a certain amount of money stashed, the only constraint stopping you from 
robbing each of them is that adjacent houses have security systems connected and 
it will automatically contact the police if two adjacent houses were broken into 
on the same night. 

 Given an integer array nums representing the amount of money of each house, 
return the maximum amount of money you can rob tonight without alerting the police.
 

 
 Example 1: 

 
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
 

 Example 2: 

 
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (
money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 

 
 Constraints: 

 
 1 <= nums.length <= 100 
 0 <= nums[i] <= 400 
 

 Related Topics Array Dynamic Programming 👍 19908 👎 384

*/
package leetcode.question.dp;

import java.util.Arrays;
/**
 *@Question:  322. Coin Change
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 75.86%
 *@Time  Complexity: O(S*N)
 *@Space Complexity: O(S)
 */

/**
 * ### 解题思路
 *
 * 这道题目是经典的零钱找零问题，要求找出组成给定金额所需的最少硬币数量。两种常见的动态规划方法被用来解决这个问题：自顶向下（Top-Down）的递归和自底向上（Bottom-Up）的迭代。
 *
 * #### 自顶向下的递归动态规划（Solution 1）
 *
 * - **定义状态：** 定义一个状态函数 `coinChange(coins, rem, count)` 表示在给定硬币面额 `coins` 的情况下，
 * 组成金额 `rem` 所需的最小硬币数量。`count` 数组用于记忆中间结果，避免重复计算。
 *
 * - **状态转移方程：** 利用递归，对于金额 `rem`，尝试减去每个硬币的面额，得到子问题的解，然后选择其中最小的解加1作为当前问题的解。
 * 即 `coinChange(coins, rem) = 1 + min{coinChange(coins, rem - coin)}`，其中 `coin` 为硬币面额。
 *
 * - **初始化：** 对于递归终止条件，如果 `rem < 0`，说明无解，返回 `-1`；如果 `rem == 0`，说明已经达到目标金额，返回 `0`。
 *
 * - **递归计算：** 使用递归进行计算，利用 `count` 数组保存中间结果，避免重复计算。
 *
 * - **时间复杂度：** 由于存在重叠子问题，递归的时间复杂度较高，为 O(S*N)，其中 `S` 为金额，`N` 为硬币种类。
 *
 * - **空间复杂度：** 递归调用栈的深度，最坏情况下为金额 `S`，因此空间复杂度为 O(S)。
 *
 * #### 自底向上的迭代动态规划（Solution 2）
 *
 * - **定义状态：** 定义一个状态数组 `dp`，其中 `dp[i]` 表示组成金额 `i` 所需的最小硬币数量。
 *
 * - **状态转移方程：** 对于每个金额 `i`，遍历硬币面额 `coins[j]`，如果 `coins[j] <= i`，
 * 则更新状态 `dp[i] = min(dp[i], dp[i - coins[j]] + 1)`。
 *
 * - **初始化：** 初始条件为 `dp[0] = 0`，即组成金额为0时需要0个硬币。其他金额初始化为一个较大的值，
 * 例如 `max + 1`，表示初始状态下无解。
 *
 * - **递推计算：** 从小金额递推到目标金额，更新状态数组。
 *
 * - **时间复杂度：** 双重循环，时间复杂度为 O(S*N)，其中 `S` 为金额，`N` 为硬币种类。
 *
 * - **空间复杂度：** 状态数组 `dp` 的长度为金额 `S + 1`，因此空间复杂度为 O(S)。
 *
 * ### 总结
 *
 * 自底向上的迭代动态规划相较于自顶向下的递归动态规划更为高效，因为它避免了递归调用的重复计算，将问题拆解成更小的子问题，
 * 通过迭代求解，得到最终的解。在实际应用中，可以根据具体问题的特点选择合适的动态规划方法。
 */

public class LeetCode_322_CoinChange{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        // Solution 1: 自顶向下的动态规划
        public int coinChange1(int[] coins, int amount) {
            if (amount < 1) return 0;
            return coinChange(coins, amount, new int[amount]);
        }

        // 递归函数，用于计算组成金额rem所需的最小硬币数
        private int coinChange(int[] coins, int rem, int[] count) {
            if (rem < 0) return -1;  // 无解的情况
            if (rem == 0) return 0;  // 达到目标金额的情况
            if (count[rem - 1] != 0) return count[rem - 1];  // 如果已经计算过，直接返回
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int res = coinChange(coins, rem - coin, count);
                if (res >= 0 && res < min){
                    min = 1 + res;  // 更新最小硬币数
                }
            }
            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;  // 将结果存储在数组中
            return count[rem - 1];
        }

        // Solution 2: 自底向上的动态规划
        public int coinChange2(int[] coins, int amount) {
            int max = amount + 1;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, max);
            dp[0] = 0;  // 初始条件，金额为0时需要0个硬币
            for (int i = 1; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];  // 返回最终结果，如果大于amount说明无解
        }

        // Solution 2: 自底向上的动态规划
        //dp[i][a] = min(dp[i-1][a] + 1, dp[i-1][a-coins[i]]) i is the coin, a is ammount
        public int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] dp = new int[n + 1][amount + 1];
            int maxs = amount + 1;
            dp[0][0] = 0;  // 初始条件，金额为0时需要0个硬币
            for( int i = 0; i < n + 1; i++){
                dp[i][0] = 0;
            }
            for( int j = 0; j < amount + 1; j++){
                dp[0][j] =  maxs;
            }

            for (int i = 1; i <= coins.length; i++) {
                for (int a = 1; a <= amount; a++) {
                    if (coins[i-1]  > a) {
                        dp[i][a] = dp[i-1][a];
                    } else {
                        dp[i][a] = Math.min(dp[i-1][a], dp[i][a-coins[i-1]] + 1);
                    }
                }
            }
//            printMatrix(dp);
            return dp[n][amount] >= maxs ? -1 : dp[n][amount];
        }
        private  void printMatrix(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)



    public static void main(String[] args) {
        Solution solution = new LeetCode_322_CoinChange().new Solution();
        // 测试代码
        int[] coins = {1, 2, 5};
        int amount = 11;
//        System.out.println(solution.coinChange1(coins, amount));  // 测试 Solution 1
        System.out.println(solution.coinChange(coins, amount));    // 测试 Solution 2
    }
}

/**
You are given an integer array coins representing coins of different 
denominations and an integer amount representing a total amount of money. 

 Return the fewest number of coins that you need to make up that amount. If 
that amount of money cannot be made up by any combination of the coins, return -1. 

 You may assume that you have an infinite number of each kind of coin. 

 
 Example 1: 

 
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
 

 Example 2: 

 
Input: coins = [2], amount = 3
Output: -1
 

 Example 3: 

 
Input: coins = [1], amount = 0
Output: 0
 

 
 Constraints: 

 
 1 <= coins.length <= 12 
 1 <= coins[i] <= 2³¹ - 1 
 0 <= amount <= 10⁴ 
 

 Related Topics Array Dynamic Programming Breadth-First Search 👍 18272 👎 426

*/
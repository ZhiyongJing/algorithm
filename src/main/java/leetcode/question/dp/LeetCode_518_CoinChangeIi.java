package leetcode.question.dp;

import java.util.Arrays;

/**
  *@Question:  518. Coin Change II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 59.13%      
  *@Time  Complexity: O(N*amount) for solution 1, solution 2 and solution 3
  *@Space Complexity: O(N*amount) for solution 1 and solution 2, O(amount) for solution 3
 */

public class LeetCode_518_CoinChangeIi{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] memo;
    int[] coins;

    //Solution 1: Top-down DP
    public int numberOfWays(int i, int amount) {
        if (amount == 0) {
            return 1;
        }
        if (i == coins.length) {
            return 0;
        }
        if (memo[i][amount] != -1) {
            return memo[i][amount];
        }

        if (coins[i] > amount) {
            return memo[i][amount] = numberOfWays(i + 1, amount);
        }

        memo[i][amount] = numberOfWays(i, amount - coins[i]) + numberOfWays(i + 1, amount);
        return memo[i][amount];
    }

    public int change1(int amount, int[] coins) {
        this.coins = coins;
        memo = new int[coins.length][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return numberOfWays(0, amount);
    }

    //Solution 2: bottom up DP
    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= amount; i++) {
            dp[0][i] = 0;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i] > j) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - coins[i]];
                }
            }
        }

        return dp[0][amount];
    }

    //Solution 3: bottom up DP + space optimiztion
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[amount];
    }




}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_518_CoinChangeIi().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given an integer array coins representing coins of different 
denominations and an integer amount representing a total amount of money. 

 Return the number of combinations that make up that amount. If that amount of 
money cannot be made up by any combination of the coins, return 0. 

 You may assume that you have an infinite number of each kind of coin. 

 The answer is guaranteed to fit into a signed 32-bit integer. 

 
 Example 1: 

 
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 

 Example 2: 

 
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
 

 Example 3: 

 
Input: amount = 10, coins = [10]
Output: 1
 

 
 Constraints: 

 
 1 <= coins.length <= 300 
 1 <= coins[i] <= 5000 
 All the values of coins are unique. 
 0 <= amount <= 5000 
 

 Related Topics Array Dynamic Programming 👍 9041 👎 159

*/
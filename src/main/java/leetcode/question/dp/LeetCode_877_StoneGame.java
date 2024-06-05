package leetcode.question.dp;

/**
  *@Question:  877. Stone Game     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 24.86%      
  *@Time  Complexity: O(N^2)
  *@Space Complexity: O(N^2)
 */

/**
 * 这道题是经典的动态规划问题，主要思路是使用动态规划来解决。在动态规划的过程中，我们需要定义一个二维数组 `dp`，其中 `dp[i][j]` 表示当前游戏状态 `[i, ..., j]` 的价值。具体的动态规划过程如下：
 *
 * 1. 首先，我们遍历石堆数组 `piles`，对于每一堆石头，我们考虑它是先手还是后手拿取，这样就可以确定游戏的价值。
 * 2. 接着，我们使用动态规划的方式来填充 `dp` 数组。对于游戏状态 `[i, ..., j]`，我们分别计算先手和后手的价值，然后根据当前玩家的身份确定应该取最大值还是最小值。
 * 3. 最后，判断游戏的最终价值 `dp[1][N]` 是否大于0，如果大于0，则表示先手能够赢得游戏，返回 `true`，否则返回 `false`。
 *
 * 时间复杂度：在计算 `dp` 数组的过程中，我们需要填充一个二维数组，所以时间复杂度为 O(N^2)，其中 N 是石堆的数量。
 *
 * 空间复杂度：我们使用了一个二维数组 `dp` 来存储游戏状态的价值，因此空间复杂度也是 O(N^2)。
 */

public class LeetCode_877_StoneGame{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean stoneGame(int[] piles) {
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }

        return dp[1][N] > 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_877_StoneGame().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Alice and Bob play a game with piles of stones. There are an even number of 
piles arranged in a row, and each pile has a positive integer number of stones 
piles[i]. 

 The objective of the game is to end with the most stones. The total number of 
stones across all the piles is odd, so there are no ties. 

 Alice and Bob take turns, with Alice starting first. Each turn, a player takes 
the entire pile of stones either from the beginning or from the end of the row. 
This continues until there are no more piles left, at which point the person 
with the most stones wins. 

 Assuming Alice and Bob play optimally, return true if Alice wins the game, or 
false if Bob wins. 

 
 Example 1: 

 
Input: piles = [5,3,4,5]
Output: true
Explanation: 
Alice starts first, and can only take the first 5 or the last 5.
Say she takes the first 5, so that the row becomes [3, 4, 5].
If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 
points.
If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win 
with 9 points.
This demonstrated that taking the first 5 was a winning move for Alice, so we 
return true.
 

 Example 2: 

 
Input: piles = [3,7,2,3]
Output: true
 

 
 Constraints: 

 
 2 <= piles.length <= 500 
 piles.length is even. 
 1 <= piles[i] <= 500 
 sum(piles[i]) is odd. 
 

 Related Topics Array Math Dynamic Programming Game Theory 👍 3200 👎 2872

*/
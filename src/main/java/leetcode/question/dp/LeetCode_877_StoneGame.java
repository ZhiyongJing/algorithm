package leetcode.question.dp;

/**
 *@Question:  877. Stone Game
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 24.86%
 *@Time  Complexity: O(N^2)
 *@Space Complexity: O(N^2)
 */

/**
 * ### 解题思路
 *
 * 题目要求判断在两个玩家轮流从石堆中取石头的游戏中，先手玩家是否能够赢得游戏。我们可以使用动态规划（DP）来解决这个问题。具体思路如下：
 *
 * #### 1. 状态表示
 *
 * - 定义一个二维数组 `dp`，其中 `dp[i][j]` 表示当前游戏状态 `[piles[i], ..., piles[j]]` 的价值。
 * - `dp[i][j]` 表示在当前状态下，先手玩家相对于后手玩家能够获得的最大分数差值。如果 `dp[i][j]` > 0，说明先手玩家能够赢得游戏。
 *
 * #### 2. 初始化
 *
 * - 初始化二维数组 `dp`，大小为 `(N+2) x (N+2)`，其中 `N` 是石堆的数量。
 * - `dp[i][j]` 的初始值为 0，因为在没有石头的情况下，先手玩家和后手玩家的分数差值为 0。
 *
 * #### 3. 状态转移
 *
 * - 我们需要遍历所有可能的子问题大小 `size`，然后遍历每个子问题的起始位置 `i` 和结束位置 `j`，计算 `dp[i][j]` 的值。
 * - 对于游戏状态 `[i, ..., j]`，我们分别计算先手和后手的价值，然后根据当前玩家的身份确定应该取最大值还是最小值。
 *   - `parity = (j + i + N) % 2` 用来判断当前玩家是先手还是后手。
 *   - 如果是先手：`dp[i][j] = max(piles[i] + dp[i+1][j], piles[j] + dp[i][j-1])`。
 *   - 如果是后手：`dp[i][j] = min(-piles[i] + dp[i+1][j], -piles[j] + dp[i][j-1])`。
 *
 * #### 4. 最终结果
 *
 * - 返回 `dp[1][N] > 0`，判断先手玩家相对于后手玩家的最大分数差值是否大于 0。如果大于 0，则表示先手玩家能够赢得游戏，返回 `true`，否则返回 `false`。
 *
 * ### 时间和空间复杂度分析
 *
 * - **时间复杂度**：
 *   - `O(N^2)`，因为我们需要填充一个二维数组 `dp`，其中 `N` 是石堆的数量。对于每个 `size`，我们遍历所有可能的起始位置 `i` 和结束位置 `j`，计算 `dp[i][j]` 的值。
 *   - 内部的状态转移操作是常数时间的，所以整体时间复杂度为 `O(N^2)`。
 *
 * - **空间复杂度**：
 *   - `O(N^2)`，我们使用了一个二维数组 `dp` 来存储游戏状态的价值。因此，空间复杂度为 `O(N^2)`。
 *
 * ### 总结
 *
 * 通过使用动态规划，我们可以高效地计算出在石头游戏中，先手玩家是否能够赢得游戏。这个方法利用状态表示和状态转移来逐步构建解决方案，最终得到先手玩家相对于后手玩家的最大分数差值。虽然时间和空间复杂度较高，但对于长度适中的石堆数量，仍然是可行的。
 */

public class LeetCode_877_StoneGame{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean stoneGame(int[] piles) {
            int N = piles.length; // 获取石堆的数量

            // dp[i+1][j+1] = 当前游戏状态 [piles[i], ..., piles[j]] 的价值。
            int[][] dp = new int[N+2][N+2]; // 定义并初始化 dp 数组
            for (int size = 1; size <= N; ++size) // 遍历所有可能的子问题大小
                for (int i = 0; i + size <= N; ++i) { // 遍历所有子问题的起始位置
                    int j = i + size - 1; // 计算子问题的结束位置
                    int parity = (j + i + N) % 2;  // 计算当前玩家是先手还是后手
                    if (parity == 1) // 如果是先手
                        dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                    else // 如果是后手
                        dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
                }

            return dp[1][N] > 0; // 返回最终结果，判断先手是否能赢
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_877_StoneGame().new Solution();
        // 测试样例1
        int[] piles1 = {5, 3, 4, 5};
        System.out.println(solution.stoneGame(piles1)); // 输出：true

        // 测试样例2
        int[] piles2 = {3, 7, 2, 3};
        System.out.println(solution.stoneGame(piles2)); // 输出：true

        // 测试样例3
        int[] piles3 = {1, 2, 3, 4, 5, 6};
        System.out.println(solution.stoneGame(piles3)); // 输出：true
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
package leetcode.question.dp;
/**
 *@Question:  1140. Stone Game II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N^3)
 *@Space Complexity: O(N^2)
 */

/**
 * 题目要求解决的问题是石子游戏II，其中有两个玩家轮流取石子，每次可以取1到2*M堆石子，M随着游戏的进行而变化，以确保拿到尽可能多的石子。我们需要设计一个算法，计算出先手玩家可以获取的最大石子数。
 *
 * **解题思路**：
 *
 * 1. **动态规划定义**：
 *    - 使用 `dp[p][i][m]` 表示在当前玩家为 `p`，从第 `i` 堆开始取石子，且当前 `M` 值为 `m` 的情况下，当前玩家可以获得的最大石子数。
 *
 * 2. **状态转移**：
 *    - 如果当前是先手玩家（即 `p = 0`），那么先手玩家会尝试选择最优策略，即在可选的石子堆范围内选择可以获得最多石子的一种策略。
 *    - 如果当前是后手玩家（即 `p = 1`），那么后手玩家会尝试选择对先手玩家不利的策略，即在可选的石子堆范围内选择可以使得先手玩家获得最少石子的一种策略。
 *
 * 3. **递归计算**：
 *    - 使用递归函数 `f(piles, dp, 0, 0, 1)` 来计算先手玩家从第0堆开始，当前M值为1时的最大石子数。在递归中，通过尝试不同的取石子策略，更新 `dp[p][i][m]`，并返回适当的最优解。
 *
 * 4. **初始化和边界条件**：
 *    - 初始化 `dp` 数组为 `-1` 表示未计算过。递归函数的边界条件为当 `i` 超过石子堆数时，返回0。
 *
 * 5. **时间复杂度**：
 *    - 动态规划的解法涉及三重循环，时间复杂度为 O(N^3)，其中 N 是石子堆的数量。
 *
 * 6. **空间复杂度**：
 *    - 使用了三维的 `dp` 数组来存储中间状态，空间复杂度为 O(N^2)，其中 N 是石子堆的数量。
 *
 * 这种方法通过动态规划有效地解决了石子游戏的问题，通过存储中间状态避免了重复计算，提高了算法的效率。
 */
public class LeetCode_1140_StoneGameIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 计算在当前玩家为p、当前在第i堆石子时，最多能获取的最小或最大的分数
         * @param piles 石子堆数组
         * @param dp 动态规划数组，dp[p][i][m]表示当前玩家为p、当前在第i堆、M为m时的最优分数
         * @param p 当前玩家，0表示先手，1表示后手
         * @param i 当前在第i堆石子
         * @param m 当前的M值，决定可以连续拿的最大堆数
         * @return 当前状态下，当前玩家能获取的最优分数
         */
        private int f(int[] piles, int[][][] dp, int p, int i, int m) {
            // 达到末尾，返回0
            if (i == piles.length) {
                return 0;
            }
            // 如果已经计算过当前状态，则直接返回已计算的结果
            if (dp[p][i][m] != -1) {
                return dp[p][i][m];
            }

            int res = p == 1 ? 1000000 : -1; // 初始设定一个极大值或极小值，用于后续比较
            int s = 0; // 记录当前累积的石子数量

            // 遍历可以连续拿的堆数，x的范围是1到最大可以拿的堆数，但不超过剩余未处理的堆数
            for (int x = 1; x <= Math.min(2 * m, piles.length - i); x++) {
                s += piles[i + x - 1]; // 累积当前可以拿的堆的石子数

                // 根据当前玩家决定更新最优分数
                if (p == 0) {
                    res = Math.max(res, s + f(piles, dp, 1, i + x, Math.max(m, x)));
                } else {
                    res = Math.min(res, f(piles, dp, 0, i + x, Math.max(m, x)));
                }
            }

            // 将计算结果保存到dp数组中，并返回当前状态下的最优分数
            return dp[p][i][m] = res;
        }

        /**
         * 计算玩家一在先手情况下能获取的最大分数
         * @param piles 石子堆数组
         * @return 玩家一在先手情况下能获取的最大分数
         */
        public int stoneGameII(int[] piles) {
            int[][][] dp = new int[2][piles.length + 1][piles.length + 1];

            // 初始化dp数组为-1，表示未计算过
            for (int p = 0; p < 2; p++) {
                for (int i = 0; i <= piles.length; i++) {
                    for (int m = 0; m <= piles.length; m++) {
                        dp[p][i][m] = -1;
                    }
                }
            }

            // 调用递归函数计算玩家一在先手情况下的最大分数
            return f(piles, dp, 0, 0, 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1140_StoneGameIi().new Solution();
        // 测试样例
        int[] piles = {2, 7, 9, 4, 4}; // 示例输入石子堆数组
        int maxPoints = solution.stoneGameII(piles); // 计算玩家一的最大分数
        System.out.println("玩家一在先手情况下能获取的最大分数：" + maxPoints);
    }
}

/**
Alice and Bob continue their games with piles of stones. There are a number of 
piles arranged in a row, and each pile has a positive integer number of stones 
piles[i]. The objective of the game is to end with the most stones. 

 Alice and Bob take turns, with Alice starting first. Initially, M = 1. 

 On each player's turn, that player can take all the stones in the first X 
remaining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). 

 The game continues until all the stones have been taken. 

 Assuming Alice and Bob play optimally, return the maximum number of stones 
Alice can get. 

 
 Example 1: 

 
Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, 
then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If 
Alice takes two piles at the beginning, then Bob can take all three piles left. In 
this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's 
larger. 
 

 Example 2: 

 
Input: piles = [1,2,3,4,5,100]
Output: 104
 

 
 Constraints: 

 
 1 <= piles.length <= 100 
 1 <= piles[i] <= 10⁴ 
 

 Related Topics Array Math Dynamic Programming Prefix Sum Game Theory 👍 2823 👎
 622

*/
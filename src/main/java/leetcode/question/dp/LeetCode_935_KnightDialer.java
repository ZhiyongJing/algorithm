package leetcode.question.dp;

import java.util.Arrays;

/**
 *@Question:  935. Knight Dialer
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 46.7%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N) for solution 1 and solution 2, O(1) for solution 3
 */

/**
 * ### 解题思路详细讲解
 *
 * **问题描述**：
 * 你需要计算从给定步数 `n` 开始，骑士在数字键盘上能跳跃形成的所有可能的有效电话号码的数量。骑士在键盘上的跳跃规则如下：
 *
 * ```
 * 1 -> 6, 8
 * 2 -> 7, 9
 * 3 -> 4, 8
 * 4 -> 3, 9, 0
 * 5 -> 无
 * 6 -> 1, 7, 0
 * 7 -> 2, 6
 * 8 -> 1, 3
 * 9 -> 2, 4
 * 0 -> 4, 6
 * ```
 *
 * **解决方案**：
 * 1. **解法1 (Top-Down DP)**：
 *    - **递归**：使用递归和记忆化搜索来解决问题。对于每个键，计算从该键出发跳跃 `n` 步能到达的所有可能的有效电话号码的数量。
 *    - **记忆化**：用一个二维数组 `memo` 存储每个状态的结果，以避免重复计算。
 *    - **状态定义**：`dp(remain, square)` 表示从 `square` 键开始，剩余 `remain` 步的有效电话号码数量。
 *
 * 2. **解法2 (Bottom-Up DP)**：
 *    - **动态规划**：使用自底向上的动态规划方法。通过构建一个 DP 表格来逐步计算每一步的状态。
 *    - **状态转移**：`dp[remain][square]` 表示从 `square` 键开始，剩余 `remain` 步的有效电话号码数量。通过更新 DP 表格的方式来计算每个状态的值。
 *    - **初始状态**：每个键在第 `0` 步时只有一种方式，即自己。
 *
 * 3. **解法3 (空间优化)**：
 *    - **空间优化**：在解法2的基础上，优化空间复杂度，将 DP 表的空间复杂度从 `O(N * 10)` 降低到 `O(10)`。使用两个长度为 `10` 的数组来存储当前步数和前一步的状态。
 *    - **状态更新**：通过不断更新当前和前一步的状态数组，最终得到从所有键开始的有效电话号码的数量。
 *
 * ### 时间复杂度
 * - **解法1 和 解法2**：
 *   - 时间复杂度为 `O(N)`，其中 `N` 是跳跃的步数。每个状态的计算都需要遍历其可能的跳跃目标，这一过程的时间复杂度是常数级别的。因此，总体复杂度是 `O(N)`。
 *
 * - **解法3**：
 *   - 时间复杂度也为 `O(N)`，但是空间复杂度得到了优化。因为只使用了两个长度为 `10` 的数组，所以空间复杂度为 `O(1)`。
 *
 * ### 空间复杂度
 * - **解法1** 和 **解法2**：
 *   - 空间复杂度为 `O(N)`，其中 `N` 是跳跃的步数。解法1的空间复杂度包括递归栈的深度和 DP 表的存储，解法2的空间复杂度是 DP 表的存储。
 *
 * - **解法3**：
 *   - 空间复杂度为 `O(1)`，通过优化只使用了两个长度为 `10` 的数组来存储当前步数和前一步的状态。
 */

public class LeetCode_935_KnightDialer {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] memo; // 记忆化数组，用于存储子问题的解
        int n; // 持续跳跃的步数
        int MOD = (int) 1e9 + 7; // 结果取模的常数
        int[][] jumps = { // 从每个键跳跃到的键
                {4, 6},
                {6, 8},
                {7, 9},
                {4, 8},
                {3, 9, 0},
                {},
                {1, 7, 0},
                {2, 6},
                {1, 3},
                {2, 4}
        };

        // 递归 + 记忆化搜索 (Top-Down Approach)
        public int dp(int remain, int square) {
            if (remain == 0) {
                return 1; // 没有剩余步数时，返回1（一个有效的路径）
            }

            if (memo[remain][square] != 0) {
                return memo[remain][square]; // 如果已计算过，直接返回结果
            }

            int ans = 0;
            for (int nextSquare : jumps[square]) { // 遍历所有可以跳到的键
                ans = (ans + dp(remain - 1, nextSquare)) % MOD; // 递归计算并加到答案中
            }

            memo[remain][square] = ans; // 存储计算结果
            return ans;
        }

        // Solution1: 自顶向下的解法
        public int knightDialer(int n) {
            this.n = n; // 保存步数
            memo = new int[n + 1][10]; // 初始化记忆化数组
            int ans = 0;
            for (int square = 0; square < 10; square++) { // 从每个键开始计算
                ans = (ans + dp(n - 1, square)) % MOD;
            }

            return ans;
        }

        // Solution2: 自底向上的动态规划解法 (Bottom-Up DP)
        public int knightDialer(int n) {
            int[][] jumps = {
                    {4, 6},
                    {6, 8},
                    {7, 9},
                    {4, 8},
                    {3, 9, 0},
                    {},
                    {1, 7, 0},
                    {2, 6},
                    {1, 3},
                    {2, 4}
            };

            int MOD = (int) 1e9 + 7;
            int[][] dp = new int[n][10]; // 初始化 DP 数组
            for (int square = 0; square < 10; square++) {
                dp[0][square] = 1; // 第一步时每个键都只有1种方式
            }

            for (int remain = 1; remain < n; remain++) { // 从第2步开始计算
                for (int square = 0; square < 10; square++) {
                    int ans = 0;
                    for (int nextSquare : jumps[square]) {
                        ans = (ans + dp[remain - 1][nextSquare]) % MOD; // 更新 DP 数组
                    }

                    dp[remain][square] = ans;
                }
            }

            int ans = 0;
            for (int square = 0; square < 10; square++) {
                ans = (ans + dp[n - 1][square]) % MOD; // 计算最终结果
            }

            return ans;
        }

        // Solution3: Solution2 空间优化
        public int knightDialer(int n) {
            int[][] jumps = {
                    {4, 6},
                    {6, 8},
                    {7, 9},
                    {4, 8},
                    {3, 9, 0},
                    {},
                    {1, 7, 0},
                    {2, 6},
                    {1, 3},
                    {2, 4}
            };

            int MOD = (int) 1e9 + 7;
            int[] dp = new int[10]; // 当前状态的 DP 数组
            int[] prevDp = new int[10]; // 上一步状态的 DP 数组
            Arrays.fill(prevDp, 1); // 初始化，步数为0时每个键都有1种方式

            for (int remain = 1; remain < n; remain++) {
                dp = new int[10]; // 更新 DP 数组
                for (int square = 0; square < 10; square++) {
                    int ans = 0;
                    for (int nextSquare : jumps[square]) {
                        ans = (ans + prevDp[nextSquare]) % MOD; // 更新当前状态
                    }

                    dp[square] = ans;
                }

                prevDp = dp; // 移动到下一步
            }

            int ans = 0;
            for (int square = 0; square < 10; square++) {
                ans = (ans + prevDp[square]) % MOD; // 计算最终结果
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_935_KnightDialer().new Solution();

        // 测试样例
        int n = 3; // 持续跳跃步数
        System.out.println("Number of distinct phone numbers: " + solution.knightDialer(n));
    }
}

/**
The chess knight has a unique movement, it may move two squares vertically and 
one square horizontally, or two squares horizontally and one square vertically (
with both forming the shape of an L). The possible movements of chess knight are 
shown in this diagram: 

 A chess knight can move as indicated in the chess diagram below: 
 
 We have a chess knight and a phone pad as shown below, the knight can only 
stand on a numeric cell (i.e. blue cell). 
 
 Given an integer n, return how many distinct phone numbers of length n we can 
dial. 

 You are allowed to place the knight on any numeric cell initially and then you 
should perform n - 1 jumps to dial a number of length n. All jumps should be 
valid knight jumps. 

 As the answer may be very large, return the answer modulo 10⁹ + 7. 

 
 Example 1: 

 
Input: n = 1
Output: 10
Explanation: We need to dial a number of length 1, so placing the knight over 
any numeric cell of the 10 cells is sufficient.
 

 Example 2: 

 
Input: n = 2
Output: 20
Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 3
8, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
 

 Example 3: 

 
Input: n = 3131
Output: 136006598
Explanation: Please take care of the mod.
 

 
 Constraints: 

 
 1 <= n <= 5000 
 

 Related Topics Dynamic Programming 👍 3027 👎 444

*/
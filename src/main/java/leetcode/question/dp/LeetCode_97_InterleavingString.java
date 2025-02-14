package leetcode.question.dp;

/**
 *@Question:  97. Interleaving String
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 54.26%
 *@Time  Complexity: O(m * n), m, n is length of s1 and s2
 *@Space Complexity: O(m * n) for solution 1 and 2, O(N) for solution3
 */

/**
 * 题目描述：
 * --------------------------
 * LeetCode 97 - Interleaving String（交错字符串）
 *
 * 给定三个字符串 s1、s2 和 s3，判断 s3 是否是由 s1 和 s2 交错组成的。
 * 交错的定义如下：
 *  - s1 和 s2 的字符顺序必须保持不变，但它们可以交替出现。
 *  - 不能有任何额外的字符。
 *
 * 示例：
 *  - 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 *    输出: true
 *    解释: "aadbbcbcac" 是 "aabcc" 和 "dbbca" 交错形成的。
 *
 *  - 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 *    输出: false
 *    解释: "aadbbbaccc" 不能由 "aabcc" 和 "dbbca" 交错形成。
 *
 *
 * 解题思路：
 * --------------------------
 * 该问题可以用 **递归 + 记忆化搜索（Memoization）** 和 **动态规划（DP）** 两种方法解决。
 *
 * 方法1：递归 + 记忆化搜索
 * ---------------------------------
 * 1. 递归定义：
 *    - 设 `dp[i][j]` 表示 `s1` 的前 i 个字符和 `s2` 的前 j 个字符是否能交错形成 `s3` 的前 (i + j) 个字符。
 *    - 递归检查 `s3` 的当前字符 `s3[i + j]` 是否等于 `s1[i]` 或 `s2[j]`。
 *    - 如果匹配，则分别递归检查 `s1[i+1]` 或 `s2[j+1]`，并继续验证。
 *
 * 2. 递归终止条件：
 *    - 如果 `i == s1.length()`，那么 `s2[j:]` 必须匹配 `s3[k:]` 才返回 `true`。
 *    - 如果 `j == s2.length()`，那么 `s1[i:]` 必须匹配 `s3[k:]` 才返回 `true`。
 *    - 如果 `memo[i][j]` 之前计算过，直接返回缓存值。
 *
 * 3. 递归调用：
 *    - 如果 `s3[k] == s1[i]`，则尝试 `dp(i+1, j)` 。
 *    - 如果 `s3[k] == s2[j]`，则尝试 `dp(i, j+1)` 。
 *    - 只要有一个递归调用返回 `true`，则 `dp[i][j]` 设为 `true`，否则设为 `false`。
 *
 * 4. 举例：
 *    - s1 = "abc"，s2 = "def"，s3 = "adbcef"
 *    - 递归展开：
 *      1. s3[0] = 'a'，匹配 s1[0]，递归 (1, 0)
 *      2. s3[1] = 'd'，匹配 s2[0]，递归 (1, 1)
 *      3. s3[2] = 'b'，匹配 s1[1]，递归 (2, 1)
 *      4. s3[3] = 'c'，匹配 s1[2]，递归 (3, 1)
 *      5. s3[4] = 'e'，匹配 s2[1]，递归 (3, 2)
 *      6. s3[5] = 'f'，匹配 s2[2]，递归 (3, 3)，返回 true
 *
 * 方法2：动态规划（DP）
 * ---------------------------------
 * 1. 定义状态：
 *    - 设 `dp[i][j]` 表示 `s1[0:i]` 和 `s2[0:j]` 能否交错形成 `s3[0:i+j]`。
 *    - `dp[0][0] = true`，表示两个空串可以组成空串。
 *
 * 2. 转移方程：
 *    - 如果 `s1[i-1] == s3[i+j-1]` 且 `dp[i-1][j]` 为 `true`，则 `dp[i][j] = true`。
 *    - 如果 `s2[j-1] == s3[i+j-1]` 且 `dp[i][j-1]` 为 `true`，则 `dp[i][j] = true`。
 *    - 否则 `dp[i][j] = false`。
 *
 * 3. 举例：
 *    - s1 = "aab"，s2 = "axy"，s3 = "aaxaby"
 *    - 构建 dp 数组：
 *      ```
 *        ""  a  a  b
 *     ""  T  T  T  F
 *      a  T  F  F  F
 *      x  T  T  F  F
 *      y  T  T  T  T
 *      ```
 *      - dp[0][0] = true
 *      - dp[1][0] = true，因为 "a" 可以匹配 "a"
 *      - dp[2][0] = true，因为 "aa" 可以匹配 "aa"
 *      - dp[2][1] = true，因为 "aax" 可以匹配 "aax"
 *      - dp[3][3] = true，返回 true。
 *
 * 4. 优化：
 *    - 由于 DP 只依赖于前一行，可以将 `dp[i][j]` 简化为一维数组 `dp[j]`，从后向前更新。
 *    - 这样空间复杂度从 `O(m*n)` 降低到 `O(n)`。
 *
 *
 * 时间和空间复杂度分析：
 * ---------------------------------
 * 方法1（递归 + 记忆化搜索）：
 * - **时间复杂度：** O(m * n)，每个状态 (i, j) 只计算一次。
 * - **空间复杂度：** O(m * n)，用于存储 `memo[i][j]` 结果。
 *
 * 方法2（动态规划 DP）：
 * - **时间复杂度：** O(m * n)，需要填充 `dp` 数组的每个格子。
 * - **空间复杂度：**
 *   - 使用 `O(m * n)` 的 `dp` 数组时，空间复杂度为 `O(m * n)`。
 *   - 使用 `O(n)` 的一维 `dp[j]` 优化后，空间复杂度降低为 `O(n)`。
 */



public class LeetCode_97_InterleavingString {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * 递归 + 记忆化搜索方法：检查 s3 是否是由 s1 和 s2 交错形成的
         */
        public boolean is_Interleave(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
            // 如果 s1 遍历完毕，检查 s2 剩余部分是否与 s3 匹配
            if (i == s1.length()) {
                return s2.substring(j).equals(s3.substring(k));
            }
            // 如果 s2 遍历完毕，检查 s1 剩余部分是否与 s3 匹配
            if (j == s2.length()) {
                return s1.substring(i).equals(s3.substring(k));
            }
            // 如果之前已经计算过当前情况，直接返回存储的结果
            if (memo[i][j] >= 0) {
                return memo[i][j] == 1;
            }
            boolean ans = false;
            // 如果 s3 的当前字符匹配 s1 的当前字符，则递归检查后续情况
            if (s3.charAt(k) == s1.charAt(i) && is_Interleave(s1, i + 1, s2, j, s3, k + 1, memo)
                    // 或者 s3 的当前字符匹配 s2 的当前字符，则递归检查后续情况
                    || s3.charAt(k) == s2.charAt(j) && is_Interleave(s1, i, s2, j + 1, s3, k + 1, memo)) {
                ans = true;
            }
            // 记录计算结果到 memo 数组，避免重复计算
            memo[i][j] = ans ? 1 : 0;
            return ans;
        }

        /**
         * 递归 + 记忆化搜索：检查 s3 是否由 s1 和 s2 交错形成
         */
        public boolean isInterleave1(String s1, String s2, String s3) {
            // 如果 s1 和 s2 的长度之和不等于 s3 的长度，直接返回 false
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            // 初始化记忆数组 memo
            int memo[][] = new int[s1.length()][s2.length()];
            for (int i = 0; i < s1.length(); i++) {
                for (int j = 0; j < s2.length(); j++) {
                    memo[i][j] = -1;
                }
            }
            // 调用递归函数进行检查
            return is_Interleave(s1, 0, s2, 0, s3, 0, memo);
        }

        /**
         * 动态规划（DP）方法：检查 s3 是否是由 s1 和 s2 交错形成的
         */
        public boolean isInterleave(String s1, String s2, String s3) {
            // 如果 s3 的长度不等于 s1 和 s2 的长度之和，直接返回 false
            if (s3.length() != s1.length() + s2.length()) {
                return false;
            }
            // 定义二维 DP 数组，dp[i][j] 代表 s1 前 i 个字符和 s2 前 j 个字符能否交错形成 s3 的前 i+j 个字符
            boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
            dp[0][0] = true;
            for(int i = 1; i <= s1.length(); i++ ){
                dp[i][0] = dp[i - 1][0] &&
                        s1.charAt(i - 1) == s3.charAt(i + 0 - 1);
            }
            for(int j = 1; j <= s2.length(); j++ ){
                dp[0][j] = dp[0][j - 1] &&
                        s2.charAt(j - 1) == s3.charAt(0 + j - 1);
            }

            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = true; // 空字符串匹配空字符串
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1] &&
                                s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j] &&
                                s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    } else {
                        dp[i][j] = (dp[i - 1][j] &&
                                s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                                (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[s1.length()][s2.length()];
        }

        /**
         * 动态规划（DP）优化版本：使用1维数组
         */
        public boolean isInterleave3(String s1, String s2, String s3) {
            if (s3.length() != s1.length() + s2.length()) {
                return false;
            }
            // 采用一维数组 dp[j] 代替 dp[i][j]，用于存储上一行计算结果
            boolean dp[] = new boolean[s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 && j == 0) {
                        dp[j] = true;
                    } else if (i == 0) {
                        dp[j] = dp[j - 1] &&
                                s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    } else if (j == 0) {
                        dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    } else {
                        dp[j] = (dp[j] &&
                                s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                                (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[s2.length()];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_97_InterleavingString.Solution solution = new LeetCode_97_InterleavingString().new Solution();
        // 测试用例
        System.out.println(solution.isInterleave1("aabcc", "dbbca", "aadbbcbcac")); // 应返回 true
        System.out.println(solution.isInterleave1("aabcc", "dbbca", "aadbbbaccc")); // 应返回 false
        System.out.println(solution.isInterleave1("", "", "")); // 应返回 true

        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbcbcac")); // 应返回 true
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbbaccc")); // 应返回 false
        System.out.println(solution.isInterleave("", "", "")); // 应返回 true

        System.out.println(solution.isInterleave3("aabcc", "dbbca", "aadbbcbcac")); // 应返回 true
        System.out.println(solution.isInterleave3("aabcc", "dbbca", "aadbbbaccc")); // 应返回 false
        System.out.println(solution.isInterleave3("", "", "")); // 应返回 true
    }
}


/**
 Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1
 and s2.

 An interleaving of two strings s and t is a configuration where s and t are
 divided into n and m substrings respectively, such that:


 s = s1 + s2 + ... + sn
 t = t1 + t2 + ... + tm
 |n - m| <= 1
 The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3
 + s3 + ...


 Note: a + b is the concatenation of strings a and b.


 Example 1:


 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 Output: true
 Explanation: One way to obtain s3 is:
 Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
 Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" =
 "aadbbcbcac".
 Since s3 can be obtained by interleaving s1 and s2, we return true.


 Example 2:


 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 Output: false
 Explanation: Notice how it is impossible to interleave s2 with any other string
 to obtain s3.


 Example 3:


 Input: s1 = "", s2 = "", s3 = ""
 Output: true



 Constraints:


 0 <= s1.length, s2.length <= 100
 0 <= s3.length <= 200
 s1, s2, and s3 consist of lowercase English letters.



 Follow up: Could you solve it using only O(s2.length) additional memory space?


 Related Topics String Dynamic Programming 👍 7906 👎 453

 */
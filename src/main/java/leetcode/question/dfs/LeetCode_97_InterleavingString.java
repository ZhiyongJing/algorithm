package leetcode.question.dfs;

/**
 *@Question:  97. Interleaving String
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 54.26%
 *@Time  Complexity: O(m * n)
 *@Space Complexity: O(m * n)
 */

/**
 * **算法思路：**
 *
 * 这是一个经典的动态规划问题。给定字符串 `s1`、`s2` 和 `s3`，要判断 `s3` 是否由 `s1` 和 `s2` 交错组成。为了简化问题，
 * 定义一个递归函数 `is_Interleave` 来判断。
 *
 * 1. 递归函数 `is_Interleave` 的参数包括 `s1`、`i`（`s1` 的当前索引）、`s2`、`j`（`s2` 的当前索引）、`s3`、`k`（`s3` 的当前索引）、
 * `memo`（用于存储已计算的结果）。
 * 2. 递归函数的基本情况：
 *    - 如果 `s1` 已经遍历完，那么只需检查 `s2` 剩余部分是否与 `s3` 匹配。
 *    - 如果 `s2` 已经遍历完，那么只需检查 `s1` 剩余部分是否与 `s3` 匹配。
 *    - 如果之前已经计算过当前情况，直接返回存储的结果。
 * 3. 对于当前位置 `(i, j)`，判断 `s3` 的当前字符是否与 `s1` 的当前字符匹配，如果匹配，则递归调用 `is_Interleave` 并移动相应的指针。
 * 4. 如果不匹配，再判断 `s3` 的当前字符是否与 `s2` 的当前字符匹配，如果匹配，则同样递归调用 `is_Interleave` 并移动相应的指针。
 * 5. 将结果存储到 `memo` 数组中，以避免重复计算。
 * 6. 最终返回 `ans`，表示 `s3` 是否是由 `s1` 和 `s2` 交错组成。
 *
 * **时间复杂度：**
 *
 * - 由于存在递归调用和记忆化搜索，时间复杂度主要取决于函数调用的次数。最坏情况下，函数可能需要遍历整个 `s1` 和 `s2` 的所有可能组合，
 * 因此时间复杂度为 O(n^2)，其中 n 是 `s1` 和 `s2` 的长度。
 *
 * **空间复杂度：**
 *
 * - 使用了 `memo` 数组来存储已计算的结果，其大小为 `s1.length() * s2.length()`。因此，空间复杂度为 O(n^2)。
 */
public class LeetCode_97_InterleavingString {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * 检查 s3 是否是由 s1 和 s2 交错形成的
         *
         * @param s1    第一个字符串
         * @param i     s1 的当前索引
         * @param s2    第二个字符串
         * @param j     s2 的当前索引
         * @param s3    目标字符串
         * @param k     s3 的当前索引
         * @param memo  记忆数组，用于存储已计算的结果
         * @return      如果 s3 是由 s1 和 s2 交错形成的，则返回 true，否则返回 false
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
                return memo[i][j] == 1 ? true : false;
            }
            boolean ans = false;
            // 检查 s3 的当前字符是否与 s1 的当前字符匹配，并递归检查剩余部分
            if (s3.charAt(k) == s1.charAt(i) && is_Interleave(s1, i + 1, s2, j, s3, k + 1, memo)
                    // 或者检查 s3 的当前字符是否与 s2 的当前字符匹配，并递归检查剩余部分
                    || s3.charAt(k) == s2.charAt(j) && is_Interleave(s1, i, s2, j + 1, s3, k + 1, memo)) {
                ans = true;
            }
            // 将结果存储到 memo 数组中
            memo[i][j] = ans ? 1 : 0;
            return ans;
        }

        /**
         * 检查 s3 是否是由 s1 和 s2 交错形成的
         *
         * @param s1 第一个字符串
         * @param s2 第二个字符串
         * @param s3 目标字符串
         * @return 如果 s3 是由 s1 和 s2 交错形成的，则返回 true，否则返回 false
         */
        public boolean isInterleave(String s1, String s2, String s3) {
            // 如果 s1 和 s2 的长度之和不等于 s3 的长度，直接返回 false
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            // 初始化记忆数组
            int memo[][] = new int[s1.length()][s2.length()];
            for (int i = 0; i < s1.length(); i++) {
                for (int j = 0; j < s2.length(); j++) {
                    memo[i][j] = -1;
                }
            }
            // 调用递归函数进行检查
            return is_Interleave(s1, 0, s2, 0, s3, 0, memo);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_97_InterleavingString.Solution solution = new LeetCode_97_InterleavingString().new Solution();
        // 测试用例
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbcbcac")); // 应返回 true
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbbaccc")); // 应返回 false
        System.out.println(solution.isInterleave("", "", "")); // 应返回 true
    }
}




//bottom up dp solution

/**
 * **算法思路：**
 *
 * 这个问题可以使用动态规划来解决。我们定义一个二维数组`dp`，其中`dp[i][j]`表示s1的前i个字符和s2的前j个字符是否可以交错组成s3的前i+j个字符。
 * 数组的大小为`(s1.length() + 1) * (s2.length() + 1)`。
 *
 * 我们可以按照以下规则填充数组`dp`：
 *
 * 1. 如果`i == 0`且`j == 0`，说明s1和s2都为空，此时`dp[i][j]`为true。
 * 2. 如果`i == 0`，说明s1为空，此时`dp[i][j]`等于`s2`的前`j-1`个字符能否与`s3`的前`i+j-1`个字符交错组成，
 * 且`s2`的第`j`个字符等于`s3`的第`i+j-1`个字符。
 * 3. 如果`j == 0`，说明s2为空，此时`dp[i][j]`等于`s1`的前`i-1`个字符能否与`s3`的前`i+j-1`个字符交错组成，
 * 且`s1`的第`i`个字符等于`s3`的第`i+j-1`个字符。
 * 4. 如果`i > 0`且`j > 0`，此时`dp[i][j]`等于以下两种情况的逻辑或：
 *    - `dp[i-1][j]`（s1的前`i-1`个字符与s2的前`j`个字符是否可以交错组成s3的前`i+j-1`个字符）且`s1`的第`i`个字符等于`s3`的第`i+j-1`个字符。
 *    - `dp[i][j-1]`（s1的前`i`个字符与s2的前`j-1`个字符是否可以交错组成s3的前`i+j-1`个字符）且`s2`的第`j`个字符等于`s3`的第`i+j-1`个字符。
 *
 * 最终，`dp[s1.length()][s2.length()]`就是问题的解，表示s1和s2是否可以交错组成s3。
 *
 * **时间复杂度：** O(m * n)，其中m为s1的长度，n为s2的长度。
 *
 * **空间复杂度：** O(m * n)，用于存储动态规划数组。
 */
//
//public class LeetCode_97_InterleavingString {
//
//    //leetcode submit region begin(Prohibit modification and deletion)
//    public class Solution {
//        public boolean isInterleave(String s1, String s2, String s3) {
//            if (s3.length() != s1.length() + s2.length()) {
//                return false;
//            }
//            // 创建一个二维数组 dp，其中 dp[i][j] 表示 s1 的前 i 个字符和 s2 的前 j 个字符是否可以交错组成 s3 的前 i+j 个字符
//            boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
//
//            for (int i = 0; i <= s1.length(); i++) {
//                for (int j = 0; j <= s2.length(); j++) {
//                    if (i == 0 && j == 0) {
//                        dp[i][j] = true;
//                    } else if (i == 0) {
//                        dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
//                    } else if (j == 0) {
//                        dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
//                    } else {
//                        dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
//                                || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
//                    }
//                }
//            }
//            return dp[s1.length()][s2.length()];
//        }
//    }
////leetcode submit region end(Prohibit modification and deletion)
//
//
//    public static void main(String[] args) {
//        Solution solution = new LeetCode_97_InterleavingString().new Solution();
//        // 测试
//        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbcbcac")); // 应返回 true
//        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbbaccc")); // 应返回 false
//        System.out.println(solution.isInterleave("", "", "")); // 应返回 true
//    }
//}

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
package leetcode.question.dp;
/**
 *@Question:  10. Regular Expression Matching
 *@Difficulty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 66.53%
 *@Time  Complexity: O(T * P)  T,P be the lengths of the text and the pattern respective
 *@Space Complexity: O(T * P)
 */

/**
 * 问题描述：
 *
 * 给定一个字符串 `text` 和一个模式串 `pattern`，实现一个函数来判断 `text` 是否与 `pattern` 匹配。模式串中可以包含以下特殊字符：
 *
 * 1. `.` 可以匹配任何单个字符。
 * 2. `*` 表示前一个字符可以出现任意次（包括 0 次）。
 *
 * 解题思路：
 *
 * 1. **动态规划解法（自顶向下）**：
 *    - 使用记忆化递归（memoization）来优化递归过程，避免重复计算子问题。
 *    - 定义 `dp(i, j)` 表示 `text[i:]` 和 `pattern[j:]` 是否匹配。
 *    - 初始化 `dp(text.length(), pattern.length()) = true`，即空串与空模式匹配为真。
 *    - 如果 `pattern[j]` 是 `*`，可以有两种情况：
 *      - 忽略 `*` 和它前面的字符，即 `dp(i, j + 2)`。
 *      - 匹配 `*` 前面的字符，即 `first_match && dp(i + 1, j)`，其中 `first_match` 表示当前字符匹配。
 *    - 如果 `pattern[j]` 是 `.` 或者普通字符，则直接判断当前字符是否匹配，并继续向后匹配。
 *    - 时间复杂度为 `O(T * P)`，其中 `T` 是 `text` 的长度，`P` 是 `pattern` 的长度。空间复杂度也是 `O(T * P)`，主要由记忆数组 `memo` 占用空间。
 *
 * 2. **动态规划解法（自底向上）**：
 *    - 使用二维动态规划数组 `dp`，其中 `dp[i][j]` 表示 `text[0:i]` 和 `pattern[0:j]` 是否匹配。
 *    - 初始化 `dp[text.length()][pattern.length()] = true`。
 *    - 从后向前填充 `dp` 数组，计算每个 `dp[i][j]` 的值：
 *      - 如果 `pattern[j]` 是 `*`，可以有两种情况：
 *        - 忽略 `*` 和它前面的字符，即 `dp[i][j + 2]`。
 *        - 匹配 `*` 前面的字符，即 `first_match && dp[i + 1][j]`。
 *      - 如果 `pattern[j]` 是 `.` 或者普通字符，则直接判断当前字符是否匹配，并继续向后匹配。
 *    - 最终返回 `dp[0][0]`，表示整个 `text` 和 `pattern` 是否匹配。
 *    - 时间复杂度为 `O(T * P)`，空间复杂度为 `O(T * P)`。
 *
 * 总结：
 *
 * - 此问题通过动态规划的方式进行解决，主要思路是通过填表格的方式来逐步解决子问题，最终求解整个问题的答案。
 * - 自顶向下的记忆化递归和自底向上的动态规划都是常见的解决动态规划问题的方式，它们可以有效地处理重叠子问题，提高计算效率。
 * - 在实际应用中，可以根据具体问题选择合适的动态规划方法来解决。
 */

public class LeetCode_10_RegularExpressionMatching {

    //leetcode submit region begin(Prohibit modification and deletion)
    // 枚举类型，表示匹配结果
    enum Result {
        TRUE,   // 匹配成功
        FALSE,  // 匹配失败
    }

    class Solution {
        Result[][] memo; // 记忆数组

        // Solution1: 自顶向下的动态规划解法
        public boolean isMatch1(String text, String pattern) {
            memo = new Result[text.length() + 1][pattern.length() + 1]; // 初始化记忆数组
            return dp(0, 0, text, pattern); // 调用递归函数进行匹配
        }

        // 递归函数，用于判断text从位置i开始和pattern从位置j开始是否匹配
        public boolean dp(int i, int j, String text, String pattern) {
            if (memo[i][j] != null) { // 如果记忆数组中已经计算过当前情况，则直接返回结果
                return memo[i][j] == Result.TRUE;
            }
            boolean ans;
            if (j == pattern.length()) { // 如果pattern已经匹配完，则text也必须匹配完才算成功
                ans = i == text.length();
            } else {
                boolean first_match =
                        (i < text.length() && // 确保text的当前位置i在范围内
                                (pattern.charAt(j) == text.charAt(i) || // 当前字符匹配
                                        pattern.charAt(j) == '.')); // 当前字符是通配符.

                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    // 如果下一个字符是'*'，则可以选择忽略'*'和它前面的字符，或者重复匹配当前字符
                    ans = (dp(i, j + 2, text, pattern) || (first_match && dp(i + 1, j, text, pattern)));
                } else {
                    // 否则，必须确保当前字符匹配，并继续向后匹配
                    ans = first_match && dp(i + 1, j + 1, text, pattern);
                }
            }
            memo[i][j] = ans ? Result.TRUE : Result.FALSE; // 将计算结果存入记忆数组
            return ans;
        }

        // Solution2: 自底向上的动态规划解法
        public boolean isMatch(String text, String pattern) {
            boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1]; // 定义动态规划数组
            dp[text.length()][pattern.length()] = true; // 初始化：空文本与空模式匹配为真

            // 从后向前遍历，填充动态规划数组
            for (int i = text.length(); i >= 0; i--) {
                for (int j = pattern.length() - 1; j >= 0; j--) {
                    boolean first_match =
                            (i < text.length() && // 确保text的当前位置i在范围内
                                    (pattern.charAt(j) == text.charAt(i) || // 当前字符匹配
                                            pattern.charAt(j) == '.')); // 当前字符是通配符

                    if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                        // 如果下一个字符是'*'，则可以选择忽略'*'和它前面的字符，或者重复匹配当前字符
                        dp[i][j] = dp[i][j + 2] || (first_match && dp[i + 1][j]);
                    } else {
                        // 否则，必须确保当前字符匹配，并继续向后匹配
                        dp[i][j] = first_match && dp[i + 1][j + 1];
                    }
                }
            }
            return dp[0][0]; // 返回动态规划数组的起始位置，即整个匹配结果
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_10_RegularExpressionMatching().new Solution();
        // 测试样例
        System.out.println(solution.isMatch("aa", "a")); // false
        System.out.println(solution.isMatch("aa", "a*")); // true
    }
}

/**
Given an input string s and a pattern p, implement regular expression matching 
with support for '.' and '*' where: 

 
 '.' Matches any single character. 
 '*' Matches zero or more of the preceding element. 
 

 The matching should cover the entire input string (not partial). 

 
 Example 1: 

 
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
 

 Example 2: 

 
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, 
by repeating 'a' once, it becomes "aa".
 

 Example 3: 

 
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
 

 
 Constraints: 

 
 1 <= s.length <= 20 
 1 <= p.length <= 20 
 s contains only lowercase English letters. 
 p contains only lowercase English letters, '.', and '*'. 
 It is guaranteed for each appearance of the character '*', there will be a 
previous valid character to match. 
 

 Related Topics String Dynamic Programming Recursion 👍 12083 👎 2150

*/
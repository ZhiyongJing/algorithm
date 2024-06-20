package leetcode.question.dp;
/**
 *@Question:  44. Wildcard Matching
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 55.75%
 *@Time  Complexity: O(S * P) for solution1, O(min(S, P)) for solution2, S and P are lengths of the input string and the pattern respectively.
 *@Space Complexity: O(S * P) for solution1, O(1) for solution2
 */

/**
 * 问题描述：
 * 给定一个字符串 `s` 和一个模式串 `p`，其中模式串中可能包含通配符 `*` 和 `?`。问字符串 `s` 是否可以和模式串 `p` 匹配，其中 `?` 可以匹配任意一个字符，`*` 可以匹配零个或多个任意字符。
 *
 * 解题思路：
 * 1. **动态规划（DP）方法**：
 *    - 使用一个二维布尔数组 `d`，其中 `d[pIdx][sIdx]` 表示模式串 `p` 的前 `pIdx` 个字符和字符串 `s` 的前 `sIdx` 个字符是否匹配。
 *    - 初始化：`d[0][0] = true`，即空模式串和空字符串匹配。
 *    - 如果 `p` 的前一个字符是 `*`，则可以考虑两种情况：
 *      - `*` 匹配零个字符：`d[pIdx][sIdx] = d[pIdx-1][sIdx]`
 *      - `*` 匹配一个或多个字符：`d[pIdx][sIdx] = d[pIdx][sIdx-1]`
 *    - 如果 `p` 的前一个字符是 `?`，则直接看前一个字符的匹配情况：`d[pIdx][sIdx] = d[pIdx-1][sIdx-1]`
 *    - 如果 `p` 的前一个字符是普通字符，则直接看前一个字符的匹配情况：`d[pIdx][sIdx] = d[pIdx-1][sIdx-1] && p.charAt(pIdx-1) == s.charAt(sIdx-1)`
 *    - 最终结果是 `d[pLen][sLen]`，表示整个模式串 `p` 是否和整个字符串 `s` 匹配。
 *
 * 2. **时间复杂度**：
 *    - 如果使用二维数组 `d`，动态规划的时间复杂度为 `O(pLen * sLen)`，其中 `pLen` 是模式串的长度，`sLen` 是字符串的长度。这是因为需要填充一个二维数组。
 *    - 空间复杂度也是 `O(pLen * sLen)`，因为需要存储所有可能的子问题的解。
 *
 * 3. **优化空间的动态规划**：
 *    - 可以通过滚动数组或者只使用一维数组进行空间优化，将空间复杂度优化到 `O(sLen)` 或者 `O(pLen)`。
 *
 * 4. **贪心算法**：
 *    - 除了动态规划方法外，还可以考虑使用贪心算法或者双指针的方法解决此类问题，具体方法因情况而定，但动态规划方法通常是最直观和常用的解决方案之一。
 *
 * 总结：
 * 通配符匹配问题可以通过动态规划有效解决，利用二维数组存储子问题的解，然后逐步构建到整个问题的解。空间优化可以进一步减少空间复杂度，但相应地会增加代码的复杂性。
 */

public class LeetCode_44_WildcardMatching{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //Solution1: bottom up dp
        /**
         * 使用动态规划（Bottom-Up）解决通配符匹配问题。
         *
         * @param s 字符串s
         * @param p 模式串p
         * @return 返回布尔值，表示s是否与p匹配
         */
        public boolean isMatch(String s, String p) {
            int sLen = s.length(), pLen = p.length();

            // base cases
            if (p.equals(s)) {
                return true;
            }

            if (pLen > 0 && p.chars().allMatch(c -> c == '*')) {
                return true;
            }

            if (p.isEmpty() || s.isEmpty()) {
                return false;
            }

            // 初始化一个二维数组d，d[pIdx][sIdx]表示p的前pIdx个字符与s的前sIdx个字符是否匹配
            boolean[][] d = new boolean[pLen + 1][sLen + 1];
            d[0][0] = true;

            // DP 计算
            for (int pIdx = 1; pIdx < pLen + 1; pIdx++) {
                // 当前模式串的字符是 '*'
                if (p.charAt(pIdx - 1) == '*') {
                    int sIdx = 1;

                    // d[pIdx - 1][sIdx - 1] 表示上一步的匹配情况
                    // 寻找第一个在字符串中匹配的索引
                    while ((!d[pIdx - 1][sIdx - 1]) && (sIdx < sLen + 1)) {
                        sIdx++;
                    }

                    // 如果字符串(s)与模式(p)匹配，
                    // 则(s)与(p)*也匹配
                    d[pIdx][sIdx - 1] = d[pIdx - 1][sIdx - 1];

                    // 如果(s)与模式(p)匹配，
                    // 则(s)(任意字符)与(p)*也匹配
                    while (sIdx < sLen + 1) {
                        d[pIdx][sIdx++] = true;
                    }
                    // 当前模式串的字符是 '?'
                } else if (p.charAt(pIdx - 1) == '?') {
                    for (int sIdx = 1; sIdx < sLen + 1; sIdx++) {
                        d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1];
                    }
                    // 当前模式串的字符不是 '*' 或 '?'
                } else {
                    for (int sIdx = 1; sIdx < sLen + 1; sIdx++) {
                        // 如果存在前一步的匹配，并且当前字符也相同，则匹配
                        d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1] &&
                                (p.charAt(pIdx - 1) == s.charAt(sIdx - 1));
                    }
                }
            }

            return d[pLen][sLen];
        }

        //Solution2: bottom up + 空间优化
        /**
         * 使用空间优化的动态规划（Bottom-Up）解决通配符匹配问题。
         *
         * @param s 字符串s
         * @param p 模式串p
         * @return 返回布尔值，表示s是否与p匹配
         */
        public boolean isMatch2(String s, String p) {
            int sLen = s.length(), pLen = p.length();
            int sIdx = 0, pIdx = 0;
            int starIdx = -1, sTmpIdx = -1;

            while (sIdx < sLen) {
                // 如果模式串的字符 = 字符串的字符
                // 或者模式串的字符 = '?'
                if (pIdx < pLen &&
                        (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                    ++sIdx;
                    ++pIdx;
                    // 如果模式串的字符 = '*'
                } else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                    // 检查 '*' 匹配没有字符的情况
                    starIdx = pIdx;
                    sTmpIdx = sIdx;
                    ++pIdx;
                    // 如果模式串的字符 != 字符串的字符
                    // 或者模式串用完了
                    // 并且之前没有 '*' 字符在模式串中
                } else if (starIdx == -1) {
                    return false;
                    // 如果模式串的字符 != 字符串的字符
                    // 或者模式串用完了
                    // 并且之前有 '*' 字符在模式串中
                } else {
                    // 回溯：检查 '*' 匹配多一个字符的情况
                    pIdx = starIdx + 1;
                    sIdx = sTmpIdx + 1;
                    sTmpIdx = sIdx;
                }
            }

            // 模式串中剩余的字符应该都是 '*' 字符
            for (int i = pIdx; i < pLen; i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_44_WildcardMatching().new Solution();
        // 测试用例
        System.out.println(solution.isMatch("aa", "a")); // false
        System.out.println(solution.isMatch("aa", "*")); // true
        System.out.println(solution.isMatch("cb", "?a")); // false
        System.out.println(solution.isMatch("adceb", "*a*b")); // true
        System.out.println(solution.isMatch("acdcb", "a*c?b")); // false
        System.out.println(solution.isMatch("", "*")); // true
    }
}


这段代码使用了两种不同的动态规划方法解决通配符匹配问题，并提供了测试用例来验证功能。
/**
Given an input string (s) and a pattern (p), implement wildcard pattern 
matching with support for '?' and '*' where: 

 
 '?' Matches any single character. 
 '*' Matches any sequence of characters (including the empty sequence). 
 

 The matching should cover the entire input string (not partial). 

 
 Example 1: 

 
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
 

 Example 2: 

 
Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
 

 Example 3: 

 
Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not 
match 'b'.
 

 
 Constraints: 

 
 0 <= s.length, p.length <= 2000 
 s contains only lowercase English letters. 
 p contains only lowercase English letters, '?' or '*'. 
 

 Related Topics String Dynamic Programming Greedy Recursion 👍 8180 👎 346

*/
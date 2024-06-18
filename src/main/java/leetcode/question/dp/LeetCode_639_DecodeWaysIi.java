package leetcode.question.dp;
/**
 *@Question:  639. Decode Ways II
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N) where N is the length of the input string s
 *@Space Complexity: O(N) for solution1 and solution2, O(1) for solution3
 */

/**
 * 题目是解码方式问题，输入字符串包含数字和特殊字符 `*`，要求计算将字符串解码成字母的所有可能方式数目。解法涉及使用递归（带记忆化搜索）、动态规划以及动态规划的空间优化三种方法。
 *
 * **解题思路：**
 *
 * 1. **递归方法（带记忆化搜索）：**
 *    - 递归函数 `ways(s, i, memo)` 表示从字符串 `s` 的第 `i` 个字符到末尾的解码方式数目。
 *    - 如果 `s.charAt(i)` 是 `*`，则有 9 种可能性（对应 1 到 9），并考虑前一个字符对应的解码情况。
 *    - 如果 `s.charAt(i)` 不是 `*`，且不为 `0`，则可以独立解码成一个字母。
 *    - 如果前一个字符与当前字符组成的两位数在 10 到 26 之间，则可以组合成一个字母。
 *    - 使用记忆化搜索避免重复计算，时间复杂度为 \(O(N)\)，空间复杂度为 \(O(N)\)，其中 \(N\) 是字符串长度。
 *
 * 2. **动态规划方法：**
 *    - 创建一个数组 `dp`，其中 `dp[i]` 表示从字符串的开头到第 `i` 个字符的解码方式数目。
 *    - 初始化 `dp[0]` 为 1，表示空字符串有一种解码方式。
 *    - 如果当前字符是 `*`，则考虑 9 种可能性，并根据前一个字符来确定组合方式。
 *    - 如果当前字符不是 `*`，且不为 `0`，则可以独立解码成一个字母。
 *    - 如果前一个字符与当前字符组成的两位数在 10 到 26 之间，则可以组合成一个字母。
 *    - 时间复杂度为 \(O(N)\)，空间复杂度为 \(O(N)\)。
 *
 * 3. **动态规划的空间优化方法：**
 *    - 在动态规划方法的基础上，优化空间复杂度，只使用两个变量 `first` 和 `second` 来保存中间状态。
 *    - 时间复杂度为 \(O(N)\)，空间复杂度为 \(O(1)\)。
 *
 * **总结：**
 *
 * - 递归方法是自顶向下的解决方案，通过记忆化搜索优化了重复计算，但是空间复杂度较高。
 * - 动态规划方法是自底向上的解决方案，通过填表格的方式，将问题分解为子问题，并且可以通过空间优化进一步减少空间复杂度。
 * - 所有方法的时间复杂度均为 \(O(N)\)，其中 \(N\) 是输入字符串的长度，因为需要遍历整个字符串来计算解码方式数目。
 */
public class LeetCode_639_DecodeWaysIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        int M = 1000000007;
        //Solution1: top-down
        // 使用递归的方式求解，采用记忆化搜索优化
        public int numDecodings1(String s) {
            Long[] memo = new Long[s.length()];
            return (int) ways(s, s.length() - 1, memo);
        }
        public long ways(String s, int i, Long[] memo) {
            if (i < 0)
                return 1;
            if (memo[i] != null)
                return memo[i];
            if (s.charAt(i) == '*') {
                long res = 9 * ways(s, i - 1, memo) % M;
                if (i > 0 && s.charAt(i - 1) == '1')
                    res = (res + 9 * ways(s, i - 2, memo)) % M;
                else if (i > 0 && s.charAt(i - 1) == '2')
                    res = (res + 6 * ways(s, i - 2, memo)) % M;
                else if (i > 0 && s.charAt(i - 1) == '*')
                    res = (res + 15 * ways(s, i - 2, memo)) % M;
                memo[i] = res;
                return memo[i];
            }
            long res = s.charAt(i) != '0' ? ways(s, i - 1, memo) : 0;
            if (i > 0 && s.charAt(i - 1) == '1')
                res = (res + ways(s, i - 2, memo)) % M;
            else if (i > 0 && s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                res = (res + ways(s, i - 2, memo)) % M;
            else if (i > 0 && s.charAt(i - 1) == '*')
                res = (res + (s.charAt(i) <= '6' ? 2 : 1) * ways(s, i - 2, memo)) % M;
            memo[i] = res;
            return memo[i];
        }

        //Solution2: Bottom-up
        // 使用动态规划的方式求解，使用数组保存中间状态
        public int numDecodings(String s) {
            long[] dp = new long[s.length() + 1];
            dp[0] = 1;
            dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '*') {
                    dp[i + 1] = 9 * dp[i] % M;
                    if (s.charAt(i - 1) == '1')
                        dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '2')
                        dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '*')
                        dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
                } else {
                    dp[i + 1] = s.charAt(i) != '0' ? dp[i] : 0;
                    if (s.charAt(i - 1) == '1')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '*')
                        dp[i + 1] = (dp[i + 1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i - 1]) % M;
                }
            }
            return (int) dp[s.length()];
        }

        //Solution3: 基于solution2 的空间优化
        // 在solution2的基础上进一步优化空间复杂度，仅使用两个变量来保存中间状态
        public int numDecodings3(String s) {
            long first = 1, second = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
            for (int i = 1; i < s.length(); i++) {
                long temp = second;
                if (s.charAt(i) == '*') {
                    second = 9 * second % M;
                    if (s.charAt(i - 1) == '1')
                        second = (second + 9 * first) % M;
                    else if (s.charAt(i - 1) == '2')
                        second = (second + 6 * first) % M;
                    else if (s.charAt(i - 1) == '*')
                        second = (second + 15 * first) % M;
                } else {
                    second = s.charAt(i) != '0' ? second : 0;
                    if (s.charAt(i - 1) == '1')
                        second = (second + first) % M;
                    else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                        second = (second + first) % M;
                    else if (s.charAt(i - 1) == '*')
                        second = (second + (s.charAt(i) <= '6' ? 2 : 1) * first) % M;
                }
                first = temp;
            }
            return (int) second;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_639_DecodeWaysIi().new Solution();
        // TO TEST
        // Example 1
        String s1 = "1*";
        System.out.println(solution.numDecodings1(s1)); // Output: 18
        System.out.println(solution.numDecodings(s1));  // Output: 18
        System.out.println(solution.numDecodings3(s1)); // Output: 18

        // Example 2
        String s2 = "2*";
        System.out.println(solution.numDecodings1(s2)); // Output: 15
        System.out.println(solution.numDecodings(s2));  // Output: 15
        System.out.println(solution.numDecodings3(s2)); // Output: 15
    }
}

/**
A message containing letters from A-Z can be encoded into numbers using the 
following mapping: 

 
'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
 

 To decode an encoded message, all the digits must be grouped then mapped back 
into letters using the reverse of the mapping above (there may be multiple ways).
 For example, "11106" can be mapped into: 

 
 "AAJF" with the grouping (1 1 10 6) 
 "KJF" with the grouping (11 10 6) 
 

 Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 
'F' since "6" is different from "06". 

 In addition to the mapping above, an encoded message may contain the '*' 
character, which can represent any digit from '1' to '9' ('0' is excluded). For 
example, the encoded message "1*" may represent any of the encoded messages "11", "12
", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to 
decoding any of the encoded messages it can represent. 

 Given a string s consisting of digits and '*' characters, return the number of 
ways to decode it. 

 Since the answer may be very large, return it modulo 10⁹ + 7. 

 
 Example 1: 

 
Input: s = "*"
Output: 9
Explanation: The encoded message can represent any of the encoded messages "1", 
"2", "3", "4", "5", "6", "7", "8", or "9".
Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", 
"H", and "I" respectively.
Hence, there are a total of 9 ways to decode "*".
 

 Example 2: 

 
Input: s = "1*"
Output: 18
Explanation: The encoded message can represent any of the encoded messages "11",
 "12", "13", "14", "15", "16", "17", "18", or "19".
Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be 
decoded to "AA" or "K").
Hence, there are a total of 9 * 2 = 18 ways to decode "1*".
 

 Example 3: 

 
Input: s = "2*"
Output: 15
Explanation: The encoded message can represent any of the encoded messages "21",
 "22", "23", "24", "25", "26", "27", "28", or "29".
"21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", 
"28", and "29" only have 1 way.
Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".

 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s[i] is a digit or '*'. 
 

 Related Topics String Dynamic Programming 👍 1558 👎 816

*/
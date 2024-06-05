package leetcode.question.dp;

import java.util.HashMap;
import java.util.Map;
/**
 *@Question:  91. Decode Ways
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 66.12%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 题目要求解码给定的字符串，并返回有多少种不同的解码方式。每个数字（1到26）对应一个字母（A到Z）。
 *
 * 我们提供了两种解法：
 *
 * #### 解法1：递归加备忘录（自顶向下）
 *
 * 1. **递归函数**：
 *    - 从字符串的起始位置开始递归，每次递归尝试解码一个字符和两个字符。
 *    - 如果当前字符是'0'，返回0，因为'0'不能独立解码。
 *    - 如果到达字符串末尾，返回1，表示找到一种有效解码方式。
 *    - 使用备忘录保存每个位置的结果，避免重复计算。
 *
 * 2. **计算方式**：
 *    - 单字符解码：递归调用下一个位置。
 *    - 双字符解码：如果当前字符和下一个字符组成的两位数在10到26之间，递归调用后两个位置。
 *    - 递归的结果相加就是当前字符位置的解码方式数。
 *
 * #### 解法2：动态规划（自底向上）
 *
 * 1. **DP数组**：
 *    - 使用一个DP数组，`dp[i]`表示字符串前`i`个字符的解码方式数。
 *    - `dp[0]`初始化为1，表示空字符串有一种解码方式。
 *    - `dp[1]`根据第一个字符是否为'0'来初始化。
 *
 * 2. **状态转移**：
 *    - 遍历字符串，对于每个字符：
 *      - 如果当前字符不是'0'，`dp[i]`可以从`dp[i-1]`转移。
 *      - 如果当前字符和前一个字符组成的两位数在10到26之间，`dp[i]`可以从`dp[i-2]`转移。
 *    - 最终`dp[s.length()]`即为字符串的解码方式数。
 *
 * ### 时间复杂度
 * - **递归加备忘录**：O(N)
 *   - 每个子问题最多计算一次，因此总的计算量是字符串的长度N。
 *
 * - **动态规划**：O(N)
 *   - 只需遍历字符串一次，计算每个位置的状态，时间复杂度是字符串的长度N。
 *
 * ### 空间复杂度
 * - **递归加备忘录**：O(N)
 *   - 需要存储N个子问题的结果，备忘录的空间复杂度是O(N)。
 *   - 递归调用的栈空间在最坏情况下也是O(N)。
 *
 * - **动态规划**：O(N)
 *   - 需要一个大小为N+1的DP数组来存储每个位置的解码方式数。
 *
 * 这两种方法都能有效地解决这个问题，但在实际使用中，动态规划方法通常更受欢迎，因为它不涉及递归调用，避免了可能的栈溢出问题。
 */
public class LeetCode_91_DecodeWays {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 用于存储中间结果的备忘录
        Map<Integer, Integer> memo = new HashMap<>();

        // 解法1: 递归加备忘录（自顶向下）
        public int numDecodings2(String s) {
            return recursiveWithMemo(0, s);
        }

        private int recursiveWithMemo(int index, String str) {
            // 如果备忘录中已存在此子串的结果，直接返回
            if (memo.containsKey(index)) {
                return memo.get(index);
            }

            // 如果到达字符串末尾，返回1表示成功
            if (index == str.length()) {
                return 1;
            }

            // 如果字符串以零开头，无法解码
            if (str.charAt(index) == '0') {
                return 0;
            }

            // 如果只剩下一个字符，返回1
            if (index == str.length() - 1) {
                return 1;
            }

            // 计算单字符解码的结果
            int ans = recursiveWithMemo(index + 1, str);
            // 计算双字符解码的结果
            if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
                ans += recursiveWithMemo(index + 2, str);
            }

            // 将结果保存到备忘录
            memo.put(index, ans);

            return ans;
        }

        // 解法2: 动态规划（自底向上）
        public int numDecodings(String s) {
            // 用于存储子问题结果的DP数组
            int[] dp = new int[s.length() + 1];
            dp[0] = 1;

            // 对于长度为1的字符串，解码方法为1，除非字符串为‘0’
            dp[1] = s.charAt(0) == '0' ? 0 : 1;

            for (int i = 2; i < dp.length; i++) {
                // 检查单字符解码是否成功
                if (s.charAt(i - 1) != '0') {
                    dp[i] = dp[i - 1];
                }

                // 检查双字符解码是否成功
                int twoDigit = Integer.valueOf(s.substring(i - 2, i));
                if (twoDigit >= 10 && twoDigit <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

            return dp[s.length()];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_91_DecodeWays().new Solution();

        // 测试样例
        String test1 = "12";
        String test2 = "226";
        String test3 = "06";
        String test4 = "2101";

        System.out.println("Test Case 1: " + solution.numDecodings(test1)); // 2
        System.out.println("Test Case 2: " + solution.numDecodings(test2)); // 3
        System.out.println("Test Case 3: " + solution.numDecodings(test3)); // 0
        System.out.println("Test Case 4: " + solution.numDecodings(test4)); // 1

        // 使用解法2进行测试
        System.out.println("Test Case 1 (DP): " + solution.numDecodings2(test1)); // 2
        System.out.println("Test Case 2 (DP): " + solution.numDecodings2(test2)); // 3
        System.out.println("Test Case 3 (DP): " + solution.numDecodings2(test3)); // 0
        System.out.println("Test Case 4 (DP): " + solution.numDecodings2(test4)); // 1
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

 Given a string s containing only digits, return the number of ways to decode 
it. 

 The test cases are generated so that the answer fits in a 32-bit integer. 

 
 Example 1: 

 
Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 

 Example 2: 

 
Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6
).
 

 Example 3: 

 
Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is 
different from "06").
 

 
 Constraints: 

 
 1 <= s.length <= 100 
 s contains only digits and may contain leading zero(s). 
 

 Related Topics String Dynamic Programming 👍 11792 👎 4505

*/
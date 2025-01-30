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

/*
 * 题目描述：
 * LeetCode 91 - Decode Ways（解码方法）
 *
 * 给定一个仅包含数字 '0'-'9' 的字符串 s，要求计算**有多少种不同的解码方法**。
 * 规则：
 * - '1' - '26' 可以映射到字母 'A' - 'Z'，如：
 *   - "12" -> "AB"（1,2） 或 "L"（12），共 2 种解码方式。
 *   - "226" -> "BZ"（2,26），"VF"（22,6），"BBF"（2,2,6），共 3 种解码方式。
 * - '0' 不能单独解码，如 "06" 无法解码，返回 0。
 * - 计算所有可能的解码方式数量。
 *
 * **输入：**
 * - `s`：仅包含数字的字符串，长度范围 `1 ≤ s.length ≤ 100`。
 * **输出：**
 * - `int`：可能的解码方式总数。
 *
 * **示例：**
 * ```
 * 输入: "12"
 * 输出: 2
 * 解释: "AB"（1,2） 或 "L"（12）
 *
 * 输入: "226"
 * 输出: 3
 * 解释: "BZ"（2,26）, "VF"（22,6）, "BBF"（2,2,6）
 *
 * 输入: "06"
 * 输出: 0
 * 解释: "0" 不能单独解码。
 * ```
 */

/*
 * 解题思路：
 * 该问题具有 **最优子结构** 和 **重叠子问题**，适用于动态规划（DP）。
 *
 * **方法 1：递归 + 记忆化搜索（Top-Down DP）**
 * ------------------------------------------------------
 * 1️⃣ **递归拆解问题**
 *    - 设 `dp(i)` 表示从索引 `i` 开始解码的方式总数。
 *    - 递归公式：
 *      ```
 *      dp(i) = dp(i + 1) + (if valid two-digit) dp(i + 2)
 *      ```
 * 2️⃣ **递归终止条件**
 *    - `i == s.length()`：成功解码，返回 `1`。
 *    - `s[i] == '0'`：当前字符无效，返回 `0`。
 * 3️⃣ **使用 `memo` 记忆化优化**
 *    - 避免重复计算，提升效率。
 *
 * **示例**
 * ```
 * s = "226"
 * 递归调用：
 * dp(0) = dp(1) + dp(2)  // 2 or 22
 * dp(1) = dp(2) + dp(3)  // 2 or 26
 * dp(2) = dp(3)          // 6 (单独)
 * dp(3) = 1 (终止条件)
 * ```
 * **时间复杂度：O(n)**
 * **空间复杂度：O(n)**（递归深度 `n`）
 *
 * ------------------------------------------------------
 * **方法 2：动态规划（Bottom-Up DP）**
 * ------------------------------------------------------
 * 1️⃣ **定义 `dp[i]`**
 *    - `dp[i]` 代表前 `i` 个字符的解码方式数量。
 * 2️⃣ **状态转移方程**
 *    - 单字符解码（`s[i-1] != '0'`）：
 *      ```
 *      dp[i] += dp[i-1]
 *      ```
 *    - 双字符解码（`10 ≤ s[i-2, i-1] ≤ 26`）：
 *      ```
 *      dp[i] += dp[i-2]
 *      ```
 * 3️⃣ **初始化**
 *    - `dp[0] = 1`（空字符串的解码方式为 1）
 *    - `dp[1] = 1` 或 `0`（取决于 `s[0]` 是否是 '0'）
 *
 * **示例**
 * ```
 * s = "226"
 * 初始化 dp[] = [1, 1, 0, 0]
 * i = 2: dp[2] = dp[1] + dp[0] = 2
 * i = 3: dp[3] = dp[2] + dp[1] = 3
 * ```
 * **时间复杂度：O(n)**
 * **空间复杂度：O(n)**（可优化为 `O(1)`）
 *
 * ------------------------------------------------------
 * **方法 3：空间优化的动态规划**
 * ------------------------------------------------------
 * 1️⃣ **优化 DP 数组**
 *    - `dp[i]` 仅依赖 `dp[i-1]` 和 `dp[i-2]`，可以用 **两个变量代替数组**。
 * 2️⃣ **使用 `prev1` 和 `prev2`**
 *    - `prev1 = dp[i-1]`，`prev2 = dp[i-2]`
 * 3️⃣ **遍历更新**
 *    - `curr = 0`
 *    - `curr += prev1`（如果 `s[i-1] != '0'`）
 *    - `curr += prev2`（如果 `10 ≤ s[i-2, i-1] ≤ 26`）
 *    - `prev2 = prev1, prev1 = curr`
 *
 * **示例**
 * ```
 * s = "226"
 * prev1 = 1, prev2 = 1
 * i = 2: curr = prev1 + prev2 = 2, 更新 prev2 = 1, prev1 = 2
 * i = 3: curr = prev1 + prev2 = 3, 更新 prev2 = 2, prev1 = 3
 * ```
 * **时间复杂度：O(n)**
 * **空间复杂度：O(1)**（只需两个变量存储值）
 */

/*
 * 时间和空间复杂度分析：
 *
 * **方法 1（递归 + 记忆化搜索，Top-Down DP）**
 * - **时间复杂度：O(n)**（每个 `dp[i]` 只计算一次）
 * - **空间复杂度：O(n)**（递归深度最多为 `n`）
 *
 * **方法 2（自底向上 DP，Bottom-Up DP）**
 * - **时间复杂度：O(n)**（遍历 `s` 一次）
 * - **空间复杂度：O(n)**（使用 `dp[]` 存储所有子问题结果）
 *
 * **方法 3（优化 DP）**
 * - **时间复杂度：O(n)**（同样遍历 `s` 一次）
 * - **空间复杂度：O(1)**（只用 `prev1` 和 `prev2` 变量）
 *
 * **推荐选择**
 * | 方法 | 时间复杂度 | 空间复杂度 | 适用场景 |
 * |------|----------|----------|--------|
 * | **递归 + 记忆化搜索** | `O(n)` | `O(n)` | 适用于小 `n`，但递归可能导致栈溢出 |
 * | **自底向上 DP** | `O(n)` | `O(n)` | 适用于较大 `n`，避免递归问题 |
 * | **优化 DP（常数空间）** | `O(n)` | `O(1)` | **最佳解法**，节省空间 |
 */

public class LeetCode_91_DecodeWays {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 用于存储中间结果的备忘录（用于递归方法，避免重复计算）
        Map<Integer, Integer> memo = new HashMap<>();

        // 解法1: 递归加备忘录（自顶向下）
        public int numDecodings2(String s) {
            return recursiveWithMemo(0, s);
        }

        // 递归函数：从索引 index 开始解码字符串 str，并使用备忘录优化
        private int recursiveWithMemo(int index, String str) {
            // 如果备忘录中已存在此子串的结果，直接返回
            if (memo.containsKey(index)) {
                return memo.get(index);
            }

            // 如果到达字符串末尾，返回 1，表示找到一种可行解码方式
            if (index == str.length()) {
                return 1;
            }

            // 如果当前字符是 '0'，则无法解码，返回 0
            if (str.charAt(index) == '0') {
                return 0;
            }

            // 如果索引是倒数第二个字符，只能单独解码，返回 1
            if (index == str.length() - 1) {
                return 1;
            }

            // 计算单字符解码的方式数量
            int ans = recursiveWithMemo(index + 1, str);
            // 计算双字符解码的方式数量（如果当前字符和下一个字符组成的数字 <= 26）
            if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
                ans += recursiveWithMemo(index + 2, str);
            }

            // 将结果存入备忘录，避免重复计算
            memo.put(index, ans);

            return ans;
        }

        // 解法2: 动态规划（自底向上）
        public int numDecodings(String s) {
            // DP 数组，dp[i] 表示前 i 个字符的解码方式数量
            int[] dp = new int[s.length() + 1];
            dp[0] = 1; // 空字符串有 1 种解码方式（不解码）

            // 处理第一个字符的特殊情况
            dp[1] = s.charAt(0) == '0' ? 0 : 1;

            // 遍历字符串，填充 dp 数组
            for (int i = 2; i < dp.length; i++) {
                // 单字符解码（当前字符不为 '0' 时有效）
                if (s.charAt(i - 1) != '0') {
                    dp[i] = dp[i - 1];
                }

                // 双字符解码（前一个字符和当前字符组成的数字在 10~26 之间）
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
        String test1 = "12";   // 预期输出：2 ("AB" 或 "L")
        String test2 = "226";  // 预期输出：3 ("BZ", "VF", "BBF")
        String test3 = "06";   // 预期输出：0 (无法解码)
        String test4 = "2101"; // 预期输出：1 ("U01" 只包含 "U")

        // 测试动态规划解法
        System.out.println("Test Case 1 (DP): " + solution.numDecodings(test1)); // 2
        System.out.println("Test Case 2 (DP): " + solution.numDecodings(test2)); // 3
        System.out.println("Test Case 3 (DP): " + solution.numDecodings(test3)); // 0
        System.out.println("Test Case 4 (DP): " + solution.numDecodings(test4)); // 1

        // 测试递归加备忘录解法
        System.out.println("Test Case 1 (Recursion + Memo): " + solution.numDecodings2(test1)); // 2
        System.out.println("Test Case 2 (Recursion + Memo): " + solution.numDecodings2(test2)); // 3
        System.out.println("Test Case 3 (Recursion + Memo): " + solution.numDecodings2(test3)); // 0
        System.out.println("Test Case 4 (Recursion + Memo): " + solution.numDecodings2(test4)); // 1
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
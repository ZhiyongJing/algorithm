package leetcode.question.dfs;

import java.util.Arrays;
import java.util.List;

/**
 *@Question:  139. Word Break
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 82.76%
 *@Time  Complexity: O(n*m*k)  // n as the length of s, m as the length of wordDict, and k as the average length of the words in wordDict
 *@Space Complexity: O(n)   // 递归调用栈的深度最坏情况下为n
 */

/**
 * ==============================
 * 题目描述：LeetCode 139 - Word Break
 * ==============================
 * 给定一个字符串 `s` 和一个单词字典 `wordDict`，判断字符串是否可以**完全**由字典中的单词拼接而成。
 * - 字典中的单词可以重复使用。
 * - 输入字符串中没有空格，所有单词都必须连续拼接。
 *
 * **输入示例：**
 * - 输入：`s = "leetcode"`，`wordDict = ["leet", "code"]`
 * - 输出：`true`
 * - 解释：字符串可以拼接为 "leet" + "code"。
 *
 * **输入示例2：**
 * - 输入：`s = "applepenapple"`，`wordDict = ["apple", "pen"]`
 * - 输出：`true`
 * - 解释：字符串可以拼接为 "apple" + "pen" + "apple"。
 *
 * **输入示例3：**
 * - 输入：`s = "catsandog"`，`wordDict = ["cats", "dog", "sand", "and", "cat"]`
 * - 输出：`false`
 * - 解释：无法拼接成字典中的单词组合。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * **核心思想：动态规划（DP）**
 * - 本题的核心是判断字符串是否可以按照字典中的单词**完整分割**。
 * - 我们可以使用**动态规划**来解决，判断每个子字符串是否可以按字典单词分割成有效的单词组合。
 *
 * **两种动态规划解法：**
 * - **解法1：递归 + 记忆化搜索（Top-Down DP）**
 * - **解法2：迭代（Bottom-Up DP）**
 *
 * ------------------------------
 * **解法1：递归 + 记忆化搜索（Top-Down DP）**
 * - 我们从字符串的末尾开始递归向前分割，尝试匹配字典中的单词。
 * - 如果某个位置的子字符串能够分割为字典中的单词，并且前面的子串也可以分割，则认为当前子字符串是有效的。
 *
 * **具体步骤：**
 * 1. 从字符串的最后一个字符开始递归。
 * 2. 遍历字典中的单词，判断当前字符往前的子串是否等于字典中的某个单词。
 * 3. 如果匹配成功，则递归检查剩余的部分。
 * 4. 使用一个 `memo` 数组保存已计算过的结果，避免重复计算。
 *
 * **举例：**
 * 输入：`s = "leetcode"`，`wordDict = ["leet", "code"]`
 * - 第一次递归：检查子串 "leetcode"，发现 "code" 在字典中，继续递归检查 "leet"。
 * - 第二次递归：检查子串 "leet"，发现 "leet" 在字典中，递归结束。
 * - 结果：返回 `true`。

 * ------------------------------
 * **解法2：迭代（Bottom-Up DP）**
 * - 我们使用一个 `dp` 数组，其中 `dp[i]` 表示字符串的前 `i` 个字符是否可以按字典分割。
 * - 如果某个位置的 `dp[i]` 为 `true`，则表示从头到位置 `i` 的子字符串可以被分割。
 *
 * **具体步骤：**
 * 1. 初始化 `dp[0] = true`，表示空字符串可以分割。
 * 2. 遍历字符串的每个位置 `i`，检查从位置 `i` 往前的子串是否在字典中。
 * 3. 如果匹配成功，更新 `dp[i]` 为 `true`。
 *
 * **举例：**
 * 输入：`s = "applepenapple"`，`wordDict = ["apple", "pen"]`
 * - 初始化：`dp[0] = true`
 * - 遍历字符串：
 *   - `dp[5] = true`，因为前 5 个字符是 "apple"。
 *   - `dp[8] = true`，因为第 6 到第 8 个字符是 "pen"。
 *   - `dp[13] = true`，因为第 9 到第 13 个字符是 "apple"。
 * - 结果：返回 `true`。

 * ------------------------------
 * **特殊情况：**
 * 1. 如果字典为空，返回 `false`。
 * 2. 如果字符串长度为 0，返回 `true`。
 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：O(N * M)**
 * - N 是字符串的长度，M 是字典中最长单词的长度。
 * - 对每个字符的位置，我们最多检查字典中的所有单词。

 * **空间复杂度：O(N)**
 * - 记忆化数组 `memo` 或动态规划数组 `dp` 需要 O(N) 的空间。
 * - 递归的栈深度最多为 O(N)。
 */


public class LeetCode_139_WordBreak {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private String s; // 待分割的字符串
        private List<String> wordDict; // 单词字典列表
        private int[] memo; // 记忆化数组，用于存储已计算过的结果

        // 使用递归 + 记忆化搜索的动态规划方法判断字符串是否可以按字典中的单词分割
        private boolean dp(int i) {
            // 如果索引小于0，表示已经成功将字符串完全分割，返回 true
            if (i < 0) return true;

            // 如果当前位置已经计算过，直接返回记忆化结果
            if (memo[i] != -1) {
                return memo[i] == 1;
            }

            // 遍历字典中的每个单词
            for (String word : wordDict) {
                // 如果当前单词长度超过剩余字符串长度，跳过这个单词
                if (i - word.length() + 1 < 0) {
                    continue;
                }

                // 检查从当前索引往前的子串是否等于字典中的某个单词，并递归检查前面的子串是否也能分割
                if (s.substring(i - word.length() + 1, i + 1).equals(word) && dp(i - word.length())) {
                    memo[i] = 1; // 标记为可以分割
                    return true;
                }
            }

            // 如果所有单词都无法匹配，标记为不可以分割
            memo[i] = 0;
            return false;
        }

        // 解法1：递归 + 记忆化搜索（Top-Down 动态规划）
        public boolean wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            this.memo = new int[s.length()];
            Arrays.fill(this.memo, -1); // 初始化记忆化数组为 -1
            return dp(s.length() - 1); // 从字符串末尾开始递归检查
        }

        // 解法2：迭代（Bottom-Up 动态规划）
        public boolean wordBreak2(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length()]; // dp[i] 表示前 i+1 个字符是否可以按字典分割
            for (int i = 0; i < s.length(); i++) {
                for (String word : wordDict) {
                    // 处理越界情况，如果当前索引小于单词长度，跳过这个单词
                    if (i < word.length() - 1) {
                        continue;
                    }

                    // 检查当前单词是否匹配，并更新 dp 数组
                    if (i == word.length() - 1 || dp[i - word.length()]) {
                        if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                            dp[i] = true; // 标记当前索引为可分割
                            break; // 只要找到一个匹配的单词即可跳出内层循环
                        }
                    }
                }
            }

            // 返回最终结果，即整个字符串是否可以分割
            return dp[s.length() - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_139_WordBreak.Solution solution = new LeetCode_139_WordBreak().new Solution();

        // 测试样例1
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        System.out.println(solution.wordBreak(s1, wordDict1));  // 输出：true

        // 测试样例2
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        System.out.println(solution.wordBreak(s2, wordDict2));  // 输出：true

        // 测试样例3
        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(solution.wordBreak(s3, wordDict3));  // 输出：false

        // 测试样例4
        String s4 = "aaaaaaa";
        List<String> wordDict4 = Arrays.asList("aaa", "aaaa");
        System.out.println(solution.wordBreak(s4, wordDict4));  // 输出：true
    }
}

/**
 Given a string s and a dictionary of strings wordDict, return true if s can be
 segmented into a space-separated sequence of one or more dictionary words.

 Note that the same word in the dictionary may be reused multiple times in the
 segmentation.


 Example 1:


 Input: s = "leetcode", wordDict = ["leet","code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".


 Example 2:


 Input: s = "applepenapple", wordDict = ["apple","pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen
 apple".
 Note that you are allowed to reuse a dictionary word.


 Example 3:


 Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 Output: false



 Constraints:


 1 <= s.length <= 300
 1 <= wordDict.length <= 1000
 1 <= wordDict[i].length <= 20
 s and wordDict[i] consist of only lowercase English letters.
 All the strings of wordDict are unique.


 Related Topics Array Hash Table String Dynamic Programming Trie Memoization 👍
 16528 👎 724

 */
package leetcode.question.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Question:  139. Word Break
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 82.76%
 *@Time  Complexity: O(n*m*k) for solution 1, 2, // n as the length of s, m as the length of wordDict, and k as the average length of the words in wordDict
 * O(N^2 + M*K) for solution3 Building the trie involves iterating over all characters of all words. This costs O(m⋅k).
 * @Space Complexity: O(n)  for solution 1, 2 // 递归调用栈的深度最坏情况下为n, O(n + m*k) fro solution3
 */

/*
 * 题目描述：
 * 给定一个字符串 `s` 和一个单词字典 `wordDict`（包含若干不重复单词），
 * 判断 `s` 是否可以被拆分成 `wordDict` 中的一个或多个单词的组合。
 *
 * 要求：
 * - 单词可以重复使用。
 * - `wordDict` 中的单词长度可以不相同。
 *
 * 输入：
 * - `s`（字符串）：需要拆分的目标字符串。
 * - `wordDict`（列表）：包含若干可用单词的字典。
 *
 * 输出：
 * - `true`（如果 `s` 能够拆分成 `wordDict` 中的单词）。
 * - `false`（如果 `s` 不能拆分）。
 *
 * 示例：
 * 输入：
 *   s = "leetcode"
 *   wordDict = ["leet", "code"]
 * 输出：
 *   true
 * 解释：
 *   "leetcode" 可以拆分成 "leet" + "code"，两者均在字典中。
 */

/*
 * 解题思路：
 * 该问题可使用 **动态规划（DP）**、**记忆化搜索（Top-Down DP）** 或 **Trie 前缀树** 进行求解。
 *
 * **方法 1：递归 + 记忆化搜索（Top-Down 动态规划）**
 * --------------------------------------------------
 * 1️⃣ **定义状态 `dp(i)`**
 *    - `dp(i)` 代表 `s[0...i]` 是否可以被 `wordDict` 拆分。
 *    - 递归终止条件：`i < 0`，返回 `true`（表示字符串已经拆分完毕）。
 *
 * 2️⃣ **递归转移方程**
 *    - 对于 `wordDict` 中的每个 `word`：
 *      - 如果 `s[i-word.length+1...i] == word`，并且 `dp(i-word.length) == true`，则 `dp(i) = true`。
 *      - 否则继续尝试其他 `word`。
 *
 * 3️⃣ **优化**
 *    - 使用 `memo[i]` 记忆化搜索，避免重复计算，提高效率。
 *
 * **示例**
 * `s = "leetcode"`, `wordDict = ["leet", "code"]`
 * - `dp(7) = dp(3) && "code" == wordDict` ✅
 * - `dp(3) = dp(-1) && "leet" == wordDict` ✅
 * - **最终 `dp(7) = true`**
 *
 * --------------------------------------------------
 * **方法 2：迭代（Bottom-Up 动态规划）**
 * --------------------------------------------------
 * 1️⃣ **定义状态 `dp[i]`**
 *    - `dp[i]` 代表 `s[0...i]` 是否可以拆分。
 *
 * 2️⃣ **状态转移**
 *    对于 s[0:i]，遍历 wordDict 里的每个单词 word：
 *       1. 检查 word 是否可以匹配 s[i - word.length():i]（即 s 的最后 word.length() 个字符）。
 *       2. 同时检查 dp[i - word.length()] 是否为 true：
 *           若 dp[i - word.length()] = true，说明 s[0:i-word.length()] 可以拆分，且 word 也能匹配 s 末尾部分，则 dp[i] = true。
 *           否则继续尝试下一个单词 word。
 *
 *
 * 3️⃣ **优化**
 *    - 使用 `boolean[] dp` 数组，避免递归，提高执行效率。
 *
 * **示例**
 * ```
 *  s = "leetcode"
 *  wordDict = ["leet", "code"]
 *  dp[] 计算过程：
 *   i=3  "leet" -> dp[3] = true
 *   i=7  "code" -> dp[7] = true
 * ```
 * **最终 `dp[7] = true`**
 *
 * --------------------------------------------------
 * **方法 3：Trie 前缀树 + DP**
 * --------------------------------------------------
 * 1️⃣ **构建 Trie（前缀树）**
 *    - 将 `wordDict` 中的单词插入 `Trie`，用于快速查找。
 *
 * 2️⃣ **DP 进行匹配**
 *    - 遍历 `s`，对于 `dp[i] == true` 的位置，使用 Trie 进行匹配，更新 `dp[j]`。
 *
 * 3️⃣ **优化**
 *    - Trie 使得匹配效率更高，适用于 `wordDict` 规模较大时的优化。
 *
 * **示例**
 * ```
 * Trie 构建：
 * - "leet"  -> l -> e -> e -> t (终点)
 * - "code"  -> c -> o -> d -> e (终点)
 *
 * s = "leetcode"
 * dp 计算：
 * - i=3  匹配 "leet"，dp[3] = true
 * - i=7  匹配 "code"，dp[7] = true
 * ```
 * **最终 `dp[7] = true`**
 */

/*
 * 时间和空间复杂度分析：
 *
 * **方法 1（递归 + 记忆化搜索）**
 * - **时间复杂度：O(N * M)**，其中 `N` 是 `s` 长度，`M` 是 `wordDict` 中最长单词的长度。
 * - **空间复杂度：O(N)`**，存储 `memo[]` 数组，递归栈深度 `O(N)`。
 *
 * **方法 2（迭代 DP）**
 * - **时间复杂度：O(N * M)**，双层遍历 `s` 和 `wordDict` 。
 * - **空间复杂度：O(N)**，使用 `dp[]` 数组。
 *
 * **方法 3（Trie + DP）**
 * - **时间复杂度：O(N * M)**，Trie 构建 `O(M * W)`，DP 计算 `O(N * M)`。
 * - **空间复杂度：O(M * W + N)**，Trie 存储 `wordDict`，DP 数组 `O(N)`。
 *
 * **不同方法的对比**
 * | 方法 | 时间复杂度 | 空间复杂度 | 适用场景 |
 * |------|----------|----------|--------|
 * | **记忆化搜索 DP** | O(N * M) | O(N) | 适用于 `N` 适中，递归结构清晰的情况 |
 * | **迭代 DP** | O(N * M) | O(N) | 适用于 `N` 较大，递归栈溢出的情况 |
 * | **Trie + DP** | O(N * M) | O(M * W + N) | 适用于 `wordDict` 规模较大的情况 |
 */

public class LeetCode_139_WordBreak {

    //leetcode submit region begin(Prohibit modification and deletion)
    class TrieNode {
        boolean isWord; // 标记当前节点是否是一个完整单词的结尾
        Map<Character, TrieNode> children; // 存储子节点的映射关系

        TrieNode() {
            this.children = new HashMap<>(); // 初始化子节点映射
        }
    }

    class Solution {
        private String s; // 需要分割的目标字符串
        private List<String> wordDict; // 单词字典列表
        private int[] memo; // 记忆化数组，存储已经计算过的状态

        // 使用递归 + 记忆化搜索的方法判断字符串是否可以按字典中的单词拆分
        private boolean dp(int i) {
            // 如果索引小于0，表示整个字符串已经成功拆分，返回 true
            if (i < 0) return true;

            // 如果当前位置已经计算过，直接返回记忆化结果
            if (memo[i] != -1) {
                return memo[i] == 1;
            }

            // 遍历字典中的每个单词
            for (String word : wordDict) {
                // 如果当前单词长度超过剩余字符串的长度，跳过该单词
                if (i - word.length() + 1 < 0) {
                    continue;
                }

                // 检查当前索引前的子串是否等于字典中的某个单词，并递归检查剩余部分是否可以分割
                if (s.substring(i - word.length() + 1, i + 1).equals(word) && dp(i - word.length())) {
                    memo[i] = 1; // 标记当前索引可以被成功分割
                    return true;
                }
            }

            // 如果所有单词都无法匹配，标记为不可分割
            memo[i] = 0;
            return false;
        }

        // 解法1：递归 + 记忆化搜索（Top-Down DP）
        public boolean wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            this.memo = new int[s.length()]; // 初始化记忆化数组
            Arrays.fill(this.memo, -1); // 用 -1 表示尚未计算
            return dp(s.length() - 1); // 从字符串末尾开始递归检查
        }

        // 解法2：迭代（Bottom-Up 动态规划）
        public boolean wordBreak2(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length()]; // dp[i] 表示前 i+1 个字符是否可以按字典拆分
            for (int i = 0; i < s.length(); i++) {
                for (String word : wordDict) {
                    // 处理越界情况，如果当前索引小于单词长度，则跳过该单词
                    if (i < word.length() - 1) {
                        continue;
                    }

                    // 检查当前单词是否匹配，并更新 dp 数组
                    if (i == word.length() - 1 || dp[i - word.length()]) {
                        if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                            dp[i] = true; // 标记当前索引为可分割
                            break; // 找到一个匹配的单词后即可跳出内层循环
                        }
                    }
                }
            }

            // 返回最终结果，即整个字符串是否可以拆分
            return dp[s.length() - 1];
        }

        // 解法3：Trie + 动态规划
        public boolean wordBreak3(String s, List<String> wordDict) {
            TrieNode root = new TrieNode(); // 创建 Trie 树的根节点

            // 构建 Trie 树，将字典中的单词插入
            for (String word : wordDict) {
                TrieNode curr = root;
                for (char c : word.toCharArray()) {
                    if (!curr.children.containsKey(c)) {
                        curr.children.put(c, new TrieNode());
                    }
                    curr = curr.children.get(c);
                }
                curr.isWord = true; // 标记单词结尾
            }

            boolean[] dp = new boolean[s.length()]; // dp[i] 表示 s[0:i] 是否可被分割
            for (int i = 0; i < s.length(); i++) {
                // 如果 i == 0 或者前一个位置可拆分，则继续检查
                if (i == 0 || dp[i - 1]) {
                    TrieNode curr = root;
                    for (int j = i; j < s.length(); j++) {
                        char c = s.charAt(j);
                        if (!curr.children.containsKey(c)) {
                            // 不存在单词匹配，直接跳出循环
                            break;
                        }
                        curr = curr.children.get(c);
                        if (curr.isWord) {
                            dp[j] = true; // 记录可拆分状态
                        }
                    }
                }
            }

            return dp[s.length() - 1]; // 返回最终结果
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_139_WordBreak.Solution solution = new LeetCode_139_WordBreak().new Solution();

        // Define test cases
        List<String[]> testCases = Arrays.asList(
                new String[]{"leetcode", "leet", "code"},  // Expected: true
                new String[]{"applepenapple", "apple", "pen"},  // Expected: true
                new String[]{"catsandog", "cats", "dog", "sand", "and", "cat"},  // Expected: false
                new String[]{"aaaaaaa", "aaa", "aaaa"}  // Expected: true
        );

        // Iterate through all test cases
        for (int i = 0; i < testCases.size(); i++) {
            String s = testCases.get(i)[0]; // Extract the input string
            List<String> wordDict = Arrays.asList(Arrays.copyOfRange(testCases.get(i), 1, testCases.get(i).length)); // Extract dictionary words

            System.out.println("Test Case " + (i + 1) + ": s = \"" + s + "\", wordDict = " + wordDict);

            // Test Solution 1: Recursion + Memoization (Top-Down DP)
            System.out.println("  wordBreak  (Recursion + Memoization): " + solution.wordBreak(s, wordDict));

            // Test Solution 2: Iterative DP (Bottom-Up DP)
            System.out.println("  wordBreak2 (Iterative DP): " + solution.wordBreak2(s, wordDict));

            // Test Solution 3: Trie + DP
            System.out.println("  wordBreak3 (Trie + DP): " + solution.wordBreak3(s, wordDict));

            System.out.println();
        }
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
17678 👎 837

*/
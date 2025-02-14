package leetcode.question.dp;

import java.util.*;

/**
 *@Question:  140. Word Break II
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 66.6%
 *@Time  Complexity: O(N * 2^N)，N 是字符串 s 的长度。即使使用记忆化搜索，
 * 仍然需要解决 O(2^N) 个子问题，每个子问题需要 O(N) 的时间，因此总复杂度为 O(N * 2^N)。
 *@Space Complexity: O(N * 2^N)
 */

/**
 * 题目描述：
 * --------------------------
 * LeetCode 140 - Word Break II（单词拆分 II）
 *
 * 给定一个字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串 s 中添加空格，
 * 使其成为由字典中的单词组成的一个或多个有效句子，并返回所有可能的拆分方式。
 *
 * 约束：
 * - 字典中的所有单词都可以无限次使用。
 * - 返回的结果可以是任意顺序。
 *
 * 示例：
 *  - 输入: s = "catsanddog", wordDict = ["cat", "cats", "and", "sand", "dog"]
 *    输出: ["cats and dog", "cat sand dog"]
 *
 *  - 输入: s = "pineapplepenapple", wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 *    输出: ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]
 *
 *  - 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 *    输出: []
 *
 *
 * 解题思路：
 * --------------------------
 * 该问题可以用 **动态规划 + DFS 回溯** 和 **Trie + 记忆化搜索** 两种方法解决。
 *
 * 方法1：动态规划 + DFS 回溯
 * ---------------------------------
 * 1. 先使用 **动态规划（DP）** 确定字符串 s 是否可以被拆分：
 *    - 定义 `dp[i]`，表示字符串 `s[0:i]` 是否可以拆分为字典中的单词。
 *    - 迭代 s 的所有子串，检查 `dp[l]` 是否为 true，且 `s[l:r]` 是否在字典中。
 *
 * 2. **DFS 回溯** 构造所有可能的拆分方式：
 *    - 如果 `dp[len]` 为 true，则说明 s 可以拆分，继续 DFS 查找所有可能的路径。
 *    - 从 `s` 的结尾向前遍历，如果 `dp[i]` 为 true，且 `s[i:end]` 是字典中的单词，
 *      则继续递归 `dfs(s, i)`，并将 `s[i:end]` 添加到结果集中。
 *
 * 3. 举例：
 *    - s = "catsanddog", wordDict = ["cat", "cats", "and", "sand", "dog"]
 *    - DP 计算：
 *      ```
 *      dp[0] = true  (空字符串可被拆分)
 *      dp[3] = true  ("cat" 在字典中)
 *      dp[4] = true  ("cats" 在字典中)
 *      dp[7] = true  ("sand" 在字典中, 且 dp[3] = true)
 *      dp[10] = true ("dog" 在字典中, 且 dp[7] = true)
 *      ```
 *    - 结果： `["cats and dog", "cat sand dog"]`
 *
 * 方法2：Trie + 记忆化搜索
 * ---------------------------------
 * 1. **使用 Trie 树构建字典**：
 *    - 通过 Trie 结构存储 `wordDict`，使得单词查找的时间复杂度降低到 O(1)。
 *    - 插入单词时，逐个字符存入 Trie 节点，标记单词结尾。
 *
 * 2. **从后向前遍历 s，并存储子问题结果**：
 *    - 设 `dp[startIdx]` 存储 `s[startIdx:]` 可能的拆分方案。
 *    - 迭代 `s[startIdx:endIdx]`，在 Trie 中查找，如果匹配成功：
 *      - 如果 `endIdx` 到达字符串末尾，添加到结果。
 *      - 否则，递归调用 `dp[endIdx + 1]`，将所有可能的句子拼接。
 *
 * 3. 举例：
 *    - s = "pineapplepenapple", wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 *    - 以 Trie 存储字典：
 *      ```
 *          root
 *         /    \
 *        a      p
 *       / \      \
 *      p   p      i
 *     /     \       \
 *    p       l       n
 *   /         \       \
 *  l           e       e
 *  ```
 *    - dp 计算：
 *      ```
 *      dp[12] = ["apple"]
 *      dp[9] = ["pen apple"]
 *      dp[4] = ["apple pen apple", "applepen apple"]
 *      dp[0] = ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]
 *      ```
 *    - 结果：`["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]`
 *
 *
 * 时间和空间复杂度分析：
 * ---------------------------------
 * 方法1（动态规划 + DFS）：
 * - **时间复杂度：** O(N * 2^N)，N 是字符串 s 的长度。即使使用记忆化搜索，
 *   仍然需要解决 O(2^N) 个子问题，每个子问题需要 O(N) 的时间，因此总复杂度为 O(N * 2^N)。
 * - **空间复杂度：** O(N * 2^N)，用于存储所有可能的拆分结果。
 *
 * 方法2（Trie + 记忆化搜索）：
 * - **时间复杂度：** O(N * 2^N)，Trie 加速了单词查找，但整体仍然需要枚举所有可能的拆分方案。
 * - **空间复杂度：** O(N * 2^N)，Trie 需要 O(K) 额外空间（K 为字典总字符数），但结果集仍然是 O(N * 2^N)。
 */


public class LeetCode_140_WordBreakIi {

    //leetcode submit region begin(Prohibit modification and deletion)

    // Trie 树的节点类
    class TrieNode {
        boolean isEnd; // 标记是否是单词的结尾
        TrieNode[] children; // 存储子节点

        TrieNode() {
            isEnd = false;
            children = new TrieNode[26]; // 每个字母对应一个索引
        }
    }

    // Trie 树类
    class Trie {
        TrieNode root; // 根节点

        Trie() {
            root = new TrieNode();
        }

        // 插入单词到 Trie 树
        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true; // 标记单词结尾
        }
    }

    class Solution {
        // 方案1：动态规划 + DFS
        public List<String> wordBreak1(String s, List<String> wordDict) {
            int len = s.length();
            // dp[i] 表示 s[0:i] 能否被拆分成 wordDict 中的单词
            boolean[] dp = new boolean[len + 1];

            // 预处理，将 wordDict 转换为哈希集合，便于快速查找
            Set<String> wordSet = new HashSet<>(wordDict);

            // 初始状态，空字符串可以被拆分
            dp[0] = true;

            // 遍历所有可能的拆分点
            for (int r = 1; r <= len; r++) {
                for (int l = 0; l < r; l++) {
                    // 先判断 dp[l] 是否为 true，避免不必要的 substring 操作
                    if (dp[l] && wordSet.contains(s.substring(l, r))) {
                        dp[r] = true;
                        break; // 只要找到一个可行的拆分点，就无需继续遍历
                    }
                }
            }

            // 结果存储
            List<String> res = new ArrayList<>();
            if (dp[len]) {
                LinkedList<String> queue = new LinkedList<>();
                dfs(s, len, wordSet, res, queue, dp);
            }
            return res;
        }

        // 通过 DFS 回溯构建所有可能的拆分方式
        private void dfs(String s, int end, Set<String> wordSet, List<String> res, LinkedList<String> queue, boolean[] dp) {
            if (end == 0) {
                // 生成一个完整的句子
                StringBuilder stringBuilder = new StringBuilder();
                for (String word : queue) {
                    stringBuilder.append(word).append(" ");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1); // 移除最后一个空格
                res.add(stringBuilder.toString());
                return;
            }

            // 从前往后遍历，查找符合条件的单词
            for (int i = 0; i < end; i++) {
                if (dp[i]) { // 只有当 dp[i] 为 true，才可能从 i 开始找到合法的单词
                    String suffix = s.substring(i, end);
                    if (wordSet.contains(suffix)) {
                        queue.addFirst(suffix); // 将单词加入队列
                        dfs(s, i, wordSet, res, queue, dp); // 继续递归
                        queue.removeFirst(); // 回溯
                    }
                }
            }
        }

        // 方案2：Trie + 记忆化搜索
        public List<String> wordBreak(String s, List<String> wordDict) {
            // 构建 Trie 树
            Trie trie = new Trie();
            for (String word : wordDict) {
                trie.insert(word);
            }

            // 记忆化搜索，存储从某个索引开始的拆分结果
            Map<Integer, List<String>> dp = new HashMap<>();

            // 从后向前遍历字符串
            for (int startIdx = s.length(); startIdx >= 0; startIdx--) {
                // 存储以 startIdx 开头的所有合法句子
                List<String> validSentences = new ArrayList<>();

                // 当前 Trie 节点
                TrieNode currentNode = trie.root;

                // 遍历字符串 s[startIdx:]
                for (int endIdx = startIdx; endIdx < s.length(); endIdx++) {
                    char c = s.charAt(endIdx);
                    int index = c - 'a';

                    // 如果 Trie 树中不存在该字符，直接终止
                    if (currentNode.children[index] == null) {
                        break;
                    }

                    // 进入下一个 Trie 节点
                    currentNode = currentNode.children[index];

                    // 如果当前字符形成了一个完整的单词
                    if (currentNode.isEnd) {
                        String currentWord = s.substring(startIdx, endIdx + 1);

                        // 如果已经到达字符串末尾，直接加入结果
                        if (endIdx == s.length() - 1) {
                            validSentences.add(currentWord);
                        } else {
                            // 否则，将当前单词与后续子字符串拼接
                            List<String> sentencesFromNextIndex = dp.get(endIdx + 1);
                            for (String sentence : sentencesFromNextIndex) {
                                validSentences.add(currentWord + " " + sentence);
                            }
                        }
                    }
                }

                // 存储当前索引的拆分结果
                dp.put(startIdx, validSentences);
            }

            // 返回从索引 0 开始的所有合法拆分方式
            return dp.getOrDefault(0, new ArrayList<>());
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_140_WordBreakIi.Solution solution = new LeetCode_140_WordBreakIi().new Solution();

        // 测试用例
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(solution.wordBreak1("catsanddog", wordDict)); // 应返回 ["cats and dog", "cat sand dog"]

        wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(solution.wordBreak1("pineapplepenapple", wordDict)); // 应返回 ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]

        wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(solution.wordBreak1("catsandog", wordDict)); // 应返回 []
    }
}

/**
 Given a string s and a dictionary of strings wordDict, add spaces in s to
 construct a sentence where each word is a valid dictionary word. Return all such
 possible sentences in any order.

 Note that the same word in the dictionary may be reused multiple times in the
 segmentation.


 Example 1:


 Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 Output: ["cats and dog","cat sand dog"]


 Example 2:


 Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine",
 "pineapple"]
 Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 Explanation: Note that you are allowed to reuse a dictionary word.


 Example 3:


 Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 Output: []



 Constraints:


 1 <= s.length <= 20
 1 <= wordDict.length <= 1000
 1 <= wordDict[i].length <= 10
 s and wordDict[i] consist of only lowercase English letters.
 All the strings of wordDict are unique.
 Input is generated in a way that the length of the answer doesn't exceed 10⁵.


 Related Topics Array Hash Table String Dynamic Programming Backtracking Trie
 Memoization 👍 6695 👎 527

 */
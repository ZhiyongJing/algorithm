package leetcode.question.dfs;

import java.util.*;

/**
 * @Question:  472. Concatenated Words
 * @Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 26.04%
 * @Time  Complexity: O(n*m^3) N is words' length, and M is the length of the longest string in the array words
 * @Space Complexity: O(n*m)
 */

/**
 * **算法思路：**
 *
 * 1. 对于每个单词，使用深度优先搜索（DFS）进行拆分。在搜索的过程中，检查当前位置的子串是否在字典中存在。
 * 2. 使用 `visited` 数组来记录已经访问过的位置，以防止重复搜索。
 * 3. 对于给定的字符串数组，对每个单词调用 DFS 进行检查，如果可以拆分成两个或以上的单词，则加入结果列表。
 *
 * **时间复杂度：**
 *
 * - `n` 是单词的总数，`m` 是数组中最长的字符串的长度。
 * - 对于每个单词，DFS 的时间复杂度是 O(m^2)，因为在最坏的情况下，有 m 个可能的前缀，每个前缀的长度最大为 m。
 * - 总的时间复杂度为 O(n * m^3)。
 *
 * **空间复杂度：**
 *
 * - 字典的空间复杂度为 O(n * m)，因为存储了所有的单词。
 * - `visited` 数组的空间复杂度为 O(n * m)，因为对于每个单词都需要一个对应的数组。
 * - 总的空间复杂度为 O(n * m)。
 *
 * 这里的 `n` 表示单词的数量，`m` 表示数组中最长的字符串的长度。
 */

public class LeetCode_472_ConcatenatedWords {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 深度优先搜索，用 visited 数组记录已经访问过的位置，防止重复搜索
         *
         * @param word        当前单词
         * @param length      当前搜索的位置
         * @param visited     记录访问过的位置
         * @param dictionary  单词字典
         * @return 是否可以拆分成两个或以上的单词
         */
        private boolean dfs(final String word, int length, final boolean[] visited, final Set<String> dictionary) {
            if (length == word.length()) {
                return true;
            }
            if (visited[length]) {
                return false;
            }
            visited[length] = true;
            for (int i = word.length() - (length == 0 ? 1 : 0); i > length; --i) {
                if (dictionary.contains(word.substring(length, i))
                        && dfs(word, i, visited, dictionary)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 寻找所有可以由字典中的单词拼接而成的单词
         *
         * @param words 给定的字符串数组
         * @return 满足条件的单词列表
         */
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            final Set<String> dictionary = new HashSet<>(Arrays.asList(words));
            final List<String> answer = new ArrayList<>();
            for (final String word : words) {
                final int length = word.length();
                final boolean[] visited = new boolean[length];
                if (dfs(word, 0, visited, dictionary)) {
                    answer.add(word);
                }
            }
            return answer;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_472_ConcatenatedWords().new Solution();
        // 测试
        String[] words1 = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        System.out.println(solution.findAllConcatenatedWordsInADict(words1));
        // 应返回 ["catsdogcats","dogcatsdog","ratcatdogcat"]

        String[] words2 = {"cat", "dog", "catdog"};
        System.out.println(solution.findAllConcatenatedWordsInADict(words2));
        // 应返回 ["catdog"]
    }
}

/**
Given an array of strings words (without duplicates), return all the 
concatenated words in the given list of words. 

 A concatenated word is defined as a string that is comprised entirely of at 
least two shorter words (not necessarily distinct) in the given array. 

 
 Example 1: 

 
Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses",
"rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
"dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat". 

 Example 2: 

 
Input: words = ["cat","dog","catdog"]
Output: ["catdog"]
 

 
 Constraints: 

 
 1 <= words.length <= 10⁴ 
 1 <= words[i].length <= 30 
 words[i] consists of only lowercase English letters. 
 All the strings of words are unique. 
 1 <= sum(words[i].length) <= 10⁵ 
 

 Related Topics Array String Dynamic Programming Depth-First Search Trie 👍 3768
 👎 276

*/

package interview.company.amazon;

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
 这个问题需要找出可以由给定字符串数组中其他字符串拼接而成的字符串。为了解决这个问题，可以使用深度优先搜索（DFS）或动态规划（DP）来实现。

 1. **DFS 解法**：
 - 遍历给定的字符串数组 `words`，对于每个单词 `word`，尝试将其拆分成两个或更多其他单词。
 - 在拆分过程中，对于当前位置的每个可能的拆分点，检查拆分的子串是否在字典中存在。
 - 使用一个 `visited` 数组来记录已经访问过的位置，以防止重复搜索。

 2. **DP 解法**：
 - 使用动态规划来解决这个问题，可以在一定程度上优化时间复杂度。
 - 对于每个单词 `word`，初始化一个大小为 `length + 1` 的布尔型数组 `dp`，其中 `dp[i]` 表示长度为 `i` 的前缀子串是否可以由字典中的单词拼接而成。
 - 初始时，将 `dp[0]` 设为 `true`，表示空字符串可以由字典中的单词拼接而成。
 - 然后，遍历单词 `word` 的每个字符，并依次更新 `dp` 数组。对于每个位置 `i`，从位置 `0` 开始搜索可能的拆分点 `j`，如果 `dp[j]` 为 `true`，并且子串 `word.substring(j, i)` 在字典中存在，则将 `dp[i]` 设置为 `true`。
 - 最后，如果 `dp[length]` 为 `true`，则表示整个单词 `word` 可以由字典中的单词拼接而成，将其加入结果列表。

 **时间复杂度分析**：
 - 对于 DFS 解法，对于每个单词，DFS 的时间复杂度是 O(m^2)，其中 `m` 是单词长度。总的时间复杂度为 O(n * m^3)，其中 `n` 是单词数量。
 - 对于 DP 解法，遍历每个单词的每个字符，每次更新 `dp` 数组的时间复杂度是 O(m^2)，其中 `m` 是单词长度。总的时间复杂度为 O(n * m^3)，其中 `n` 是单词数量。

 **空间复杂度分析**：
 - 对于两种解法，空间复杂度都主要取决于存储单词字典和辅助数组的空间。单词字典的空间复杂度为 O(n * m)，其中 `n` 是单词数量，`m` 是单词长度。
 - 对于 DFS 解法，需要使用 `visited` 数组来记录已经访问过的位置，空间复杂度为 O(n * m)。
 - 对于 DP 解法，需要使用 `dp` 数组来记录前缀子串是否可以由字典中的单词拼接而成，空间复杂度为 O(n * m)。

 因此，DFS 解法和 DP 解法的时间复杂度都为 O(n * m^3)，空间复杂度都为 O(n * m)。
 */

public class LeetCode_472_ConcatenatedWords {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
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

        //Solution1: dfs
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

        //Solution2: dp
        public List<String> findAllConcatenatedWordsInADict2(String[] words) {
            final Set<String> dictionary = new HashSet<>(Arrays.asList(words));
            final List<String> answer = new ArrayList<>();
            for (final String word : words) {
                final int length = word.length();
                final boolean[] dp = new boolean[length + 1];
                dp[0] = true;
                for (int i = 1; i <= length; ++i) {
                    for (int j = (i == length ? 1 : 0); !dp[i] && j < i; ++j) {
                        dp[i] = dp[j] && dictionary.contains(word.substring(j, i));
                    }
                }
                if (dp[length]) {
                    answer.add(word);
                }
            }
            return answer;
        }

    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        leetcode.question.dfs.LeetCode_472_ConcatenatedWords.Solution solution = new leetcode.question.dfs.LeetCode_472_ConcatenatedWords().new Solution();
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

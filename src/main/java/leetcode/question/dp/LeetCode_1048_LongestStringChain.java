package leetcode.question.dp;

import java.util.*;

/**
 *@Question:  1048. Longest String Chain
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 34.49%
 *@Time  Complexity: O(N * L^2)  // N 是单词数量，L 是单词的最大长度
 *@Space Complexity: O(N * L)
 */

/**
 * 这道题目是要求找出最长的字符串链，其中每个字符串链中的字符串可以通过删除一个字符来得到另一个字符串。解决这个问题可以使用动态规划的思想。
 *
 * 1. **自顶向下动态规划：**
 *    - 使用深度优先搜索 (DFS) 和记忆化搜索 (Memoization) 来解决问题。
 *    - 对每个单词进行递归，尝试删除每个字符，检查新单词是否存在于单词集合中。
 *    - 用一个哈希表来存储以当前单词为起点的最长字符串链的长度，以避免重复计算。
 *
 * 2. **自底向上动态规划：**
 *    - 将单词按长度排序，然后遍历每个单词。
 *    - 对于每个单词，通过删除每个字符来找到所有可能的前驱单词，并更新一个哈希表中的最长链长度。
 *    - 这样可以保证在处理每个单词时，其前驱单词的最长链长度已经计算过了，从而减少重复计算。
 *
 * **时间复杂度：**
 * - 自顶向下动态规划的时间复杂度为 \(O(N \times L^2)\)，其中 \(N\) 是单词数量，\(L\) 是单词的最大长度。
 * - 自底向上动态规划的时间复杂度为 \(O(N \log N + N \times L^2)\)，其中 \(N\) 是单词数量，\(L\) 是单词的最大长度。
 *
 * **空间复杂度：**
 * - 自顶向下动态规划的空间复杂度为 \(O(N \times L)\)，主要是用于存储哈希表和递归栈。
 * - 自底向上动态规划的空间复杂度为 \(O(N \times L)\)，主要是用于存储哈希表。
 */
public class LeetCode_1048_LongestStringChain{

//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        private int dfs(Set<String> words, Map<String, Integer> memo, String currentWord) {
            // 如果当前单词已经在 memo 中，则直接返回它的值（使用记忆化搜索）
            if (memo.containsKey(currentWord)) {
                return memo.get(currentWord);
            }
            // 存储以当前单词开头的最长单词链的长度
            int maxLength = 1;
            StringBuilder sb = new StringBuilder(currentWord);

            // 通过删除当前单词的每个字符来创建所有可能的新单词
            for (int i = 0; i < currentWord.length(); i++) {
                sb.deleteCharAt(i);
                String newWord = sb.toString();
                // 如果新单词在单词集合中，则对新单词进行深度优先搜索
                if (words.contains(newWord)) {
                    int currentLength = 1 + dfs(words, memo, newWord);
                    maxLength = Math.max(maxLength, currentLength);
                }
                sb.insert(i, currentWord.charAt(i));
            }
            // 将当前单词的最长链长度存入 memo 中
            memo.put(currentWord, maxLength);

            return maxLength;
        }

        // 解决方案1：自顶向下
        public int longestStrChain(String[] words) {
            Map<String, Integer> memo = new HashMap<>();
            Set<String> wordsPresent = new HashSet<>();
            Collections.addAll(wordsPresent, words);
            int ans = 0;
            // 对每个单词进行深度优先搜索
            for (String word : words) {
                ans = Math.max(ans, dfs(wordsPresent, memo, word));
            }
            return ans;
        }

        // 解决方案2：自底向上动态规划
        public int longestStrChain2(String[] words) {
            Map<String, Integer> dp = new HashMap<>();

            // 按照单词长度进行排序
            Arrays.sort(words, (a, b) -> a.length() - b.length());

            int longestWordSequenceLength = 1;

            for (String word : words) {
                int presentLength = 1;
                // 通过删除当前单词的每个字符来找到所有可能的前驱单词
                for (int i = 0; i < word.length(); i++) {
                    StringBuilder temp = new StringBuilder(word);
                    temp.deleteCharAt(i);
                    String predecessor = temp.toString();
                    int previousLength = dp.getOrDefault(predecessor, 0);
                    presentLength = Math.max(presentLength, previousLength + 1);
                }
                dp.put(word, presentLength);
                longestWordSequenceLength = Math.max(longestWordSequenceLength, presentLength);
            }
            return longestWordSequenceLength;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1048_LongestStringChain().new Solution();
        // 测试样例
        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println(solution.longestStrChain(words));  // 输出：4
        System.out.println(solution.longestStrChain2(words)); // 输出：4
    }
}

/**
You are given an array of words where each word consists of lowercase English 
letters. 

 wordA is a predecessor of wordB if and only if we can insert exactly one 
letter anywhere in wordA without changing the order of the other characters to make 
it equal to wordB. 

 
 For example, "abc" is a predecessor of "abac", while "cba" is not a 
predecessor of "bcad". 
 

 A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, 
where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. 
A single word is trivially a word chain with k == 1. 

 Return the length of the longest possible word chain with words chosen from 
the given list of words. 

 
 Example 1: 

 
Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
 

 Example 2: 

 
Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", 
"pcxbc", "pcxbcf"].
 

 Example 3: 

 
Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters 
is changed.
 

 
 Constraints: 

 
 1 <= words.length <= 1000 
 1 <= words[i].length <= 16 
 words[i] only consists of lowercase English letters. 
 

 Related Topics Array Hash Table Two Pointers String Dynamic Programming 👍 7225
 👎 253

*/
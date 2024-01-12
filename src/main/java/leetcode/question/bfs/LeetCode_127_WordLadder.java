package leetcode.question.bfs;

import javafx.util.Pair;

import java.util.*;

/**
 * @Question: 127. Word Ladder
 * @Difficulty: 3 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 70.11%
 * @Time Complexity: O(N*M^2) M is the length of each word and N is the total number of words in the input word list.
 * @Space Complexity: O(N*M^2)
 */

/**
 * ### 算法思路：
 *
 * 这是一道基于广度优先搜索（BFS）的问题，要求从起始单词（beginWord）通过逐步变换一个字母，最终到达目标单词（endWord）。每一步只能变换一个字母，且所有变换后的单词必须在给定的单词列表（wordList）中。
 *
 * 为了解决这个问题，可以使用BFS来逐层遍历可能的变换序列。首先，将单词列表中的每个单词进行预处理，
 * 将其相邻的单词放在同一个“通配符”（使用'*'代替某个字母）的映射中。然后使用BFS遍历，从起始单词开始，逐层搜索可能的变换路径，直到找到目标单词。
 *
 * ### 详细步骤：
 *
 * 1. 预处理单词列表，建立通配符映射关系。将单词列表中每个单词的每个字母都替换为通配符'*'，并将这个通配符映射到该单词。
 * 例如，对于单词列表["hot","dot","dog","lot","log","cog"]，将其预处理为
 * `{
 *      "*ot":["hot","dot","lot"],
 *      "h*t":["hot"],
 *      "ho*":["hot"],
 *      "d*t":["dot","dog"],
 *      "do*":["dot","dog"],
 *      "d**":["dot","dog"],
 *      "l*t":["lot","log"],
 *      "lo*":["lot","log"],
 *      "l*g":["log"],
 *      "c*g":["cog"],
 *      "co*":["cog"],
 *      "c**":["cog"]
 *  }`。
 *
 * 2. 使用BFS遍历。创建一个队列，起始单词入队，同时记录步数为1。使用哈希表记录已经访问的单词，防止重复访问。
 * 每次从队列中取出一个单词，遍历其通配符映射的所有单词，如果该单词未被访问过，则加入队列，并记录步数。重复此步骤直到找到目标单词或队列为空。
 *
 * 3. 返回步数作为结果。如果队列为空，说明无法从起始单词变换到目标单词，返回0。
 *
 * ### 时间复杂度：
 *
 * - 对于每个单词，需要构建其通配符映射关系，时间复杂度为 O(N * M^2)，其中 N 为单词的数量，M 为单词的长度。
 * - BFS的过程中，每个单词会被访问一次，时间复杂度为 O(N * M)，其中 N 为单词的数量，M 为单词的长度。
 * - 总的时间复杂度为 O(N * M + N * M^2) = O(N * M^2)。
 *
 * ### 空间复杂度：
 *
 * - 预处理过程中，需要使用哈希表存储通配符映射，空间复杂度为 O(N * M^2)。
 * - BFS过程中，需要使用队列和哈希表，空间复杂度为 O(N * M)。
 * - 总的空间复杂度为 O(N * M^2)。
 */
public class LeetCode_127_WordLadder {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            // 由于所有单词的长度相同
            int L = beginWord.length();

            // 用于存储具有相同中间状态的单词的字典
            Map<String, List<String>> allComboDict = new HashMap<>();

            // 对每个单词构建中间状态的字典
            wordList.forEach(word -> {
                for (int i = 0; i < L; i++) {
                    // key是通用单词，value是具有相同中间通用单词的单词列表
                    String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                    List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                    transformations.add(word);
                    allComboDict.put(newWord, transformations);
                }
            });

            // BFS使用的队列
            Queue<Pair<String, Integer>> Q = new LinkedList<>();
            Q.add(new Pair<>(beginWord, 1));

            // 用于确保不重复处理相同的单词
            Map<String, Boolean> visited = new HashMap<>();
            visited.put(beginWord, true);

            while (!Q.isEmpty()) {
                Pair<String, Integer> node = Q.remove();
                String word = node.getKey();
                int level = node.getValue();
                for (int i = 0; i < L; i++) {

                    // 单词的中间状态
                    String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                    // 下一个状态是所有具有相同中间状态的单词
                    for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                        // 如果找到我们要的单词，直接返回答案
                        if (adjacentWord.equals(endWord)) {
                            return level + 1;
                        }
                        // 否则，将其添加到BFS队列，同时标记为已访问
                        if (!visited.containsKey(adjacentWord)) {
                            visited.put(adjacentWord, true);
                            Q.add(new Pair<>(adjacentWord, level + 1));
                        }
                    }
                }
            }

            return 0;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_127_WordLadder().new Solution();
        // 测试代码
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println("Result: " + result);
    }
}

/**
A transformation sequence from word beginWord to word endWord using a 
dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that: 

 
 Every adjacent pair of words differs by a single letter. 
 Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to 
be in wordList. 
 sk == endWord 
 

 Given two words, beginWord and endWord, and a dictionary wordList, return the 
number of words in the shortest transformation sequence from beginWord to 
endWord, or 0 if no such sequence exists. 

 
 Example 1: 

 
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
"log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> 
"dog" -> cog", which is 5 words long.
 

 Example 2: 

 
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
"log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid 
transformation sequence.
 

 
 Constraints: 

 
 1 <= beginWord.length <= 10 
 endWord.length == beginWord.length 
 1 <= wordList.length <= 5000 
 wordList[i].length == beginWord.length 
 beginWord, endWord, and wordList[i] consist of lowercase English letters. 
 beginWord != endWord 
 All the words in wordList are unique. 
 

 Related Topics Hash Table String Breadth-First Search 👍 11536 👎 1843

*/

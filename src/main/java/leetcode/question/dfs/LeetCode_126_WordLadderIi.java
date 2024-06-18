package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *@Question:  126. Word Ladder II
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 53.66%
 *@Time  Complexity: O(nk^2) N is the number of words in wordList, K is the maximum length of a word,
 *@Space Complexity: O(nk)
 */

/**
 * ### 解题思路
 *
 * 1. **构建邻接表 (BFS)**：
 *    - 使用广度优先搜索（BFS）从 `beginWord` 开始，逐层遍历可能的单词转换。对于每个单词，尝试将每个字符替换为其他所有可能的字符（从 'a' 到 'z'），如果新单词在 `wordList` 中，则将其作为当前单词的邻居。
 *    - 在这个过程中，构建一个邻接表（邻接列表表示法的有向图，DAG），记录每个单词可以转换到哪些单词。
 *    - 通过这个方法，我们可以确定所有从 `beginWord` 到 `endWord` 的最短路径层次结构。
 *
 * 2. **路径回溯 (DFS)**：
 *    - 使用深度优先搜索（DFS）从 `endWord` 开始，沿着邻接表向前遍历，找到所有可以到达 `beginWord` 的路径。
 *    - 在回溯过程中，将每条路径存储在结果列表中。
 *
 * ### 时间复杂度
 *
 * 1. **构建邻接表 (BFS)**：
 *    - 时间复杂度为 `O(N * K^2)`，其中 `N` 是 `wordList` 中单词的数量，`K` 是单词的最大长度。
 *      - 对于每个单词，尝试替换每个字符（`K` 次），每次替换需要遍历所有可能的字符（26 个），并检查新单词是否在 `wordList` 中（检查时间为 `O(K)`）。
 *      - 综上，对于每个单词，我们需要进行 `K * 26 * K = O(K^2)` 次操作。
 *
 * 2. **路径回溯 (DFS)**：
 *    - 时间复杂度取决于最短路径的数量和长度。假设最短路径的数量为 `P`，每条路径的平均长度为 `L`，那么时间复杂度为 `O(P * L)`。
 *
 * ### 空间复杂度
 *
 * 1. **构建邻接表 (BFS)**：
 *    - 空间复杂度为 `O(N * K)`，其中 `N` 是单词的数量，`K` 是单词的最大长度。
 *      - 邻接表需要存储每个单词的所有邻居，每个单词最多有 `K` 个字符，每个字符最多有 26 个可能的替换，但考虑到单词的有限数量，总空间复杂度为 `O(N * K)`。
 *
 * 2. **路径回溯 (DFS)**：
 *    - 空间复杂度取决于路径列表的大小和深度优先搜索的递归深度。假设最短路径的数量为 `P`，每条路径的平均长度为 `L`，那么路径列表的空间复杂度为 `O(P * L)`。
 *    - 递归深度最多为单词的长度，所需栈空间为 `O(L)`。
 *
 * 总结：
 * - **时间复杂度**：`O(N * K^2)` 用于构建邻接表，`O(P * L)` 用于路径回溯。
 * - **空间复杂度**：`O(N * K)` 用于存储邻接表，`O(P * L)` 用于存储所有路径。
 */

public class LeetCode_126_WordLadderIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 用来存储单词的邻接表
        Map<String, List<String>> adjList = new HashMap<String, List<String>>();
        // 用来存储当前路径
        List<String> currPath = new ArrayList<String>();
        // 用来存储最短路径
        List<List<String>> shortestPaths = new ArrayList<List<String>>();

        // 找到给定单词的所有邻居（只改变一个字符且存在于单词列表中的单词）
        private List<String> findNeighbors(String word, Set<String> wordList) {
            List<String> neighbors = new ArrayList<String>();
            char charList[] = word.toCharArray();

            for (int i = 0; i < word.length(); i++) {
                char oldChar = charList[i];

                // 将第i个字符替换为a到z之间的所有字符，除了原来的字符
                for (char c = 'a'; c <= 'z'; c++) {
                    charList[i] = c;

                    // 如果字符与原字符相同或者单词列表中不包含该单词则跳过
                    if (c == oldChar || !wordList.contains(String.valueOf(charList))) {
                        continue;
                    }
                    neighbors.add(String.valueOf(charList));
                }
                charList[i] = oldChar;
            }
            return neighbors;
        }

        // 使用回溯法找到从source到destination的所有路径
        private void backtrack(String source, String destination) {
            // 如果到达终点单词，存储路径
            if (source.equals(destination)) {
                List<String> tempPath = new ArrayList<String>(currPath);
                Collections.reverse(tempPath);
                shortestPaths.add(tempPath);
            }

            if (!adjList.containsKey(source)) {
                return;
            }

            for (int i = 0; i < adjList.get(source).size(); i++) {
                currPath.add(adjList.get(source).get(i));
                backtrack(adjList.get(source).get(i), destination);
                currPath.remove(currPath.size() - 1);
            }
        }

        // 使用广度优先搜索构建邻接表
        private void bfs(String beginWord, String endWord, Set<String> wordList) {
            Queue<String> q = new LinkedList<>();
            q.add(beginWord);

            // 如果单词列表包含起始单词，则删除它
            if (wordList.contains(beginWord)) {
                wordList.remove(beginWord);
            }

            Map<String, Integer> isEnqueued = new HashMap<String, Integer>();
            isEnqueued.put(beginWord, 1);

            while (q.size() > 0)  {
                // 访问当前层的所有单词
                List<String> visited = new ArrayList<String>();

                for (int i = q.size() - 1; i >= 0; i--) {
                    String currWord = q.peek();
                    q.remove();

                    // 找到当前单词的所有邻居
                    List<String> neighbors = findNeighbors(currWord, wordList);
                    for (String word : neighbors) {
                        visited.add(word);

                        if (!adjList.containsKey(word)) {
                            adjList.put(word, new ArrayList<String>());
                        }

                        // 添加从word到currWord的边
                        adjList.get(word).add(currWord);
                        if (!isEnqueued.containsKey(word)) {
                            q.add(word);
                            isEnqueued.put(word, 1);
                        }
                    }
                }
                // 删除上一层的单词
                for (int i = 0; i < visited.size(); i++) {
                    if (wordList.contains(visited.get(i))) {
                        wordList.remove(visited.get(i));
                    }
                }
            }
        }

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            // 将单词列表复制到集合中，以便在广度优先搜索中高效删除
            Set<String> copiedWordList = new HashSet<>(wordList);
            // 使用广度优先搜索构建邻接表
            bfs(beginWord, endWord, copiedWordList);

            // 每条路径从终点单词开始
            currPath.add(endWord);
            // 遍历邻接表找到从终点单词到起始单词的所有路径
            backtrack(endWord, beginWord);

            return shortestPaths;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_126_WordLadderIi().new Solution();

        // 测试样例
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        List<List<String>> results = solution.findLadders(beginWord, endWord, wordList);

        for (List<String> path : results) {
            System.out.println(path);
        }
    }
}

/**
A transformation sequence from word beginWord to word endWord using a 
dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that: 

 
 Every adjacent pair of words differs by a single letter. 
 Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to 
be in wordList. 
 sk == endWord 
 

 Given two words, beginWord and endWord, and a dictionary wordList, return all 
the shortest transformation sequences from beginWord to endWord, or an empty 
list if no such sequence exists. Each sequence should be returned as a list of the 
words [beginWord, s1, s2, ..., sk]. 

 
 Example 1: 

 
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
"log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
 

 Example 2: 

 
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
"log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid 
transformation sequence.
 

 
 Constraints: 

 
 1 <= beginWord.length <= 5 
 endWord.length == beginWord.length 
 1 <= wordList.length <= 500 
 wordList[i].length == beginWord.length 
 beginWord, endWord, and wordList[i] consist of lowercase English letters. 
 beginWord != endWord 
 All the words in wordList are unique. 
 The sum of all shortest transformation sequences does not exceed 10⁵. 
 

 Related Topics Hash Table String Backtracking Breadth-First Search 👍 5771 👎 7
13

*/

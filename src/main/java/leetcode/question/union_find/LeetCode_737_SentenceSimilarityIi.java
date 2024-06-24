package leetcode.question.union_find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *@Question:  737. Sentence Similarity II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N * K * M) for DFS and BFS. O( (N + K) * M)
 *
 * n is the number of words in sentence1 and sentence2 and k is the number of similar pairs.
 *  Let m be the average length of words in sentence1, sentence2 as well as in similarPairs.
 *@Space Complexity: O(K * M)
 */

/**
 * 问题：737. Sentence Similarity II
 *
 * ### 解题思路
 *
 * 这道题的主要目标是判断两个句子列表是否相似，其中相似性的定义通过给定的一组相似对列表来确定。题目提供了三种解决方法：DFS、BFS 和并查集（Union-Find）。
 *
 * 1. **DFS 解法**：
 *    - **构建图**：使用 HashMap 来表示每个单词与其相似单词之间的关系，即每个单词都可以映射到一个包含其相似单词的集合。
 *    - **深度优先搜索**：对于每一对待比较的句子中的单词，如果两个单词相等则跳过；如果两个单词不相等，则通过 DFS 判断是否存在从一个单词到另一个单词的路径，路径的存在意味着两个单词相似。
 *    - **时间复杂度**：对于每个单词，构建图和 DFS 都需要遍历相似对列表，因此时间复杂度为 O(N + P)，其中 N 是句子中的单词数，P 是相似对的数量。
 *    - **空间复杂度**：使用了 HashMap 来存储图的结构和 HashSet 用于记录访问状态，空间复杂度为 O(N + P)。
 *
 * 2. **BFS 解法**：
 *    - **构建图**：同样使用 HashMap 表示每个单词与其相似单词之间的关系。
 *    - **广度优先搜索**：对于每一对待比较的句子中的单词，通过 BFS 判断是否存在从一个单词到另一个单词的路径，路径的存在意味着两个单词相似。
 *    - **时间复杂度**：同样为 O(N + P)，因为构建图和 BFS 的操作都需要遍历相似对列表。
 *    - **空间复杂度**：使用了 HashMap 和 Queue 来存储图的结构和 BFS 过程中的状态，空间复杂度为 O(N + P)。
 *
 * 3. **并查集（Union-Find）解法**：
 *    - **数据结构**：使用并查集来管理相似关系，每个单词都作为一个节点，相似对则是连接两个节点的边。
 *    - **合并操作**：对于每对相似的单词，将它们合并到同一个集合中。
 *    - **查找操作**：通过并查集的根节点查找操作判断两个单词是否属于同一个集合。
 *    - **时间复杂度**：初始化并查集和判断操作都是近似常数时间，因此时间复杂度为 O(N + P)。
 *    - **空间复杂度**：使用了 HashMap 和并查集数据结构来存储相似关系和管理集合，空间复杂度为 O(N + P)。
 *
 * ### 总结
 *
 * 以上三种解法都能有效地判断两个句子列表是否相似，其中 DFS 和 BFS 更加直观和易于理解，而并查集虽然实现稍微复杂，但在大规模数据下可能会更高效。选择使用哪种方法可以根据具体的实际需求和输入数据的特点来决定。
 */
public class LeetCode_737_SentenceSimilarityIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class UnionFind {
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();

        // 添加单词到并查集中
        public void addWord(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                rank.put(x, 0);
            }
        }

        // 判断单词是否在并查集中存在
        public boolean isWordPresent(String x) {
            return parent.containsKey(x);
        }

        // 查找单词的根节点（路径压缩）
        public String find(String x) {
            if (parent.get(x) != x)
                parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        // 合并两个单词所在的集合（按秩合并）
        public void union(String x, String y) {
            String xset = find(x), yset = find(y);
            if (xset.equals(yset)) {
                return;
            } else if (rank.get(xset) < rank.get(yset)) {
                parent.put(xset, yset);
            } else if (rank.get(xset) > rank.get(yset)) {
                parent.put(yset, xset);
            } else {
                parent.put(yset, xset);
                rank.put(xset, rank.get(xset) + 1);
            }
        }
    }

    class Solution {

        // 使用深度优先搜索（DFS）判断从节点到目标节点是否存在路径
        boolean dfs(String node, Map<String, HashSet<String>> adj, Set<String> visit, String dest) {
            visit.add(node);
            if (node.equals(dest)) {
                return true;
            }
            if (!adj.containsKey(node)) {
                return false;
            }
            for (String neighbor : adj.get(node)) {
                if (!visit.contains(neighbor) && dfs(neighbor, adj, visit, dest)) {
                    return true;
                }
            }
            return false;
        }

        // 解决方案1: 使用DFS检查两个句子是否相似
        public boolean areSentencesSimilarTwo1(String[] sentence1, String[] sentence2,
                                               List<List<String>> similarPairs) {
            if (sentence1.length != sentence2.length) {
                return false;
            }
            // 构建相似关系的图
            Map<String, HashSet<String>> adj = new HashMap<>();
            for (List<String> pair : similarPairs) {
                adj.computeIfAbsent(pair.get(0), value -> new HashSet<String>()).add(pair.get(1));
                adj.computeIfAbsent(pair.get(1), value -> new HashSet<String>()).add(pair.get(0));
            }

            // 检查每对句子的相似性
            for (int i = 0; i < sentence1.length; i++) {
                if (sentence1[i].equals(sentence2[i])) {
                    continue;
                }
                Set<String> visit = new HashSet<>();
                if (adj.containsKey(sentence1[i]) && adj.containsKey(sentence2[i]) &&
                        dfs(sentence1[i], adj, visit, sentence2[i])) {
                    continue;
                }
                return false;
            }
            return true;
        }

        // 使用广度优先搜索（BFS）判断从源节点到目标节点是否存在路径
        boolean bfs(String source, Map<String, HashSet<String>> adj, String dest) {
            Set<String> visit = new HashSet<>();
            Queue<String> q = new LinkedList<>();
            q.offer(source);
            visit.add(source);

            while (!q.isEmpty()) {
                String node = q.poll();

                if (!adj.containsKey(node)) {
                    continue;
                }
                for (String neighbor : adj.get(node)) {
                    if (neighbor.equals(dest)) {
                        return true;
                    }
                    if (!visit.contains(neighbor)) {
                        visit.add(neighbor);
                        q.offer(neighbor);
                    }
                }
            }
            return false;
        }

        // 解决方案2: 使用BFS检查两个句子是否相似
        public boolean areSentencesSimilarTwo2(String[] sentence1, String[] sentence2,
                                               List<List<String>> similarPairs) {
            if (sentence1.length != sentence2.length) {
                return false;
            }
            // 构建相似关系的图
            Map<String, HashSet<String>> adj = new HashMap<>();
            for (List<String> pair : similarPairs) {
                adj.computeIfAbsent(pair.get(0), value -> new HashSet<String>()).add(pair.get(1));
                adj.computeIfAbsent(pair.get(1), value -> new HashSet<String>()).add(pair.get(0));
            }

            // 检查每对句子的相似性
            for (int i = 0; i < sentence1.length; i++) {
                if (sentence1[i].equals(sentence2[i])) {
                    continue;
                }
                if (adj.containsKey(sentence1[i]) && adj.containsKey(sentence2[i]) &&
                        bfs(sentence1[i], adj, sentence2[i])) {
                    continue;
                }
                return false;
            }
            return true;
        }

        // 解决方案3: 使用并查集检查两个句子是否相似
        public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2,
                                              List<List<String>> similarPairs) {
            if (sentence1.length != sentence2.length) {
                return false;
            }

            UnionFind dsu = new UnionFind();
            for (List<String> pair : similarPairs) {
                // 使用并查集管理相似关系
                dsu.addWord(pair.get(0));
                dsu.addWord(pair.get(1));
                dsu.union(pair.get(0), pair.get(1));
            }

            // 检查每对句子的相似性
            for (int i = 0; i < sentence1.length; i++) {
                if (sentence1[i].equals(sentence2[i])) {
                    continue;
                }
                if (dsu.isWordPresent(sentence1[i]) && dsu.isWordPresent(sentence2[i]) &&
                        dsu.find(sentence1[i]).equals(dsu.find(sentence2[i]))) {
                    continue;
                }
                return false;
            }
            return true;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_737_SentenceSimilarityIi().new Solution();

        // 测试样例
        String[] sentence1 = {"great", "acting", "skills"};
        String[] sentence2 = {"fine", "drama", "talent"};
        List<List<String>> similarPairs = new ArrayList<>();
        similarPairs.add(Arrays.asList("great", "fine"));
        similarPairs.add(Arrays.asList("fine", "good"));
        similarPairs.add(Arrays.asList("acting","drama"));
        similarPairs.add(Arrays.asList("skills","talent"));

        boolean result1 = solution.areSentencesSimilarTwo1(sentence1, sentence2, similarPairs);
        boolean result2 = solution.areSentencesSimilarTwo2(sentence1, sentence2, similarPairs);
        boolean result3 = solution.areSentencesSimilarTwo(sentence1, sentence2, similarPairs);

        System.out.println("Solution 1 result: " + result1);
        System.out.println("Solution 2 result: " + result2);
        System.out.println("Solution 3 result: " + result3);
    }
}

/**
We can represent a sentence as an array of words, for example, the sentence "I 
am happy with leetcode" can be represented as arr = ["I","am",happy","with",
"leetcode"]. 

 Given two sentences sentence1 and sentence2 each represented as a string array 
and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi]
 indicates that the two words xi and yi are similar. 

 Return true if sentence1 and sentence2 are similar, or false if they are not 
similar. 

 Two sentences are similar if: 

 
 They have the same length (i.e., the same number of words) 
 sentence1[i] and sentence2[i] are similar. 
 

 Notice that a word is always similar to itself, also notice that the 
similarity relation is transitive. For example, if the words a and b are similar, and the 
words b and c are similar, then a and c are similar. 

 
 Example 1: 

 
Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama",
"talent"], similarPairs = [["great","good"],["fine","good"],["drama","acting"],[
"skills","talent"]]
Output: true
Explanation: The two sentences have the same length and each word i of sentence1
 is also similar to the corresponding word in sentence2.
 

 Example 2: 

 
Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"],
 similarPairs = [["manga","onepiece"],["platform","anime"],["leetcode",
"platform"],["anime","manga"]]
Output: true
Explanation: "leetcode" --> "platform" --> "anime" --> "manga" --> "onepiece".
Since "leetcode is similar to "onepiece" and the first two words are the same, 
the two sentences are similar. 

 Example 3: 

 
Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"],
 similarPairs = [["manga","hunterXhunter"],["platform","anime"],["leetcode",
"platform"],["anime","manga"]]
Output: false
Explanation: "leetcode" is not similar to "onepiece".
 

 
 Constraints: 

 
 1 <= sentence1.length, sentence2.length <= 1000 
 1 <= sentence1[i].length, sentence2[i].length <= 20 
 sentence1[i] and sentence2[i] consist of lower-case and upper-case English 
letters. 
 0 <= similarPairs.length <= 2000 
 similarPairs[i].length == 2 
 1 <= xi.length, yi.length <= 20 
 xi and yi consist of English letters. 
 

 Related Topics Array Hash Table String Depth-First Search Breadth-First Search 
Union Find 👍 838 👎 43

*/
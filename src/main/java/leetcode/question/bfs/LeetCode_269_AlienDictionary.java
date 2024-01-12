package leetcode.question.bfs;

/**
  *@Question:  269. Alien Dictionary
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 59.56%
  *@Time  Complexity: O(C) C be the total length of all the words in the input list, added together.
  *@Space Complexity: O(1)
 */

import java.util.*;

/**
 * ### 算法思路
 *
 * #### 方法一：BFS（宽度优先搜索）
 *
 * 1. **构建图和计算入度：** 首先，遍历给定的单词数组 `words`，找出所有唯一的字母，构建邻接表 `adjList` 和记录每个字母的入度 `counts`。
 * 对于每一对相邻的单词 `word1` 和 `word2`，找到它们第一个不相同的字母，并在 `adjList` 中建立从 `word1.charAt(j)`
 * 到 `word2.charAt(j)` 的有向边，同时增加 `word2.charAt(j)` 的入度。
 *
 * 2. **BFS 构建拓扑序列：** 利用队列进行广度优先搜索。将所有入度为0的字母加入队列，然后从队列中依次取出字母，并删除以该字母为起点的边。
 * 接着，将新的入度为0的字母加入队列。重复这个过程直到队列为空。如果遍历过程中输出的拓扑序列长度小于字母的总数，
 * 说明存在循环依赖，返回空字符串；否则，返回拓扑序列。
 *
 * 3. **时间复杂度：** 构建图和计算入度的时间复杂度为 O(C)，其中 C 表示所有字符的总数；
 * BFS 的时间复杂度为 O(C + E)，其中 E 表示边的数量。总体时间复杂度为 O(C + E)。
 *
 * 4. **空间复杂度：** 存储图和入度的空间复杂度为 O(C + E)，使用了额外的队列和映射，总体空间复杂度为 O(C + E)。
 *
 * #### 方法二：DFS（深度优先搜索）
 *
 * 1. **构建反向邻接表：** 遍历给定的单词数组 `words`，找出所有唯一的字母，并构建反向邻接表 `reverseAdjList`。
 * 对于每一对相邻的单词 `word1` 和 `word2`，找到它们第一个不相同的字母，并在 `reverseAdjList` 中建立从 `word2.charAt(j)`
 * 到 `word1.charAt(j)` 的有向边。
 *
 * 2. **DFS 构建拓扑序列：** 通过深度优先搜索进行拓扑排序。对于每个节点，递归地访问其相邻节点，并在回溯过程中将节点标记为已访问。
 * 如果检测到循环依赖，返回空字符串；否则，返回拓扑序列。
 *
 * 3. **时间复杂度：** 构建反向邻接表的时间复杂度为 O(C + E)，其中 C 表示所有字符的总数，E 表示边的数量。DFS 的时间复杂度为 O(C + E)。
 *
 * 4. **空间复杂度：** 存储反向邻接表和访问状态的空间复杂度为 O(C + E)，递归调用的深度为 O(C)。总体空间复杂度为 O(C + E)。
 *
 * ### 总结
 *
 * 两种方法都基于拓扑排序的思想，通过构建图和计算入度，然后使用 BFS 或 DFS 进行拓扑排序。两者的时间复杂度和空间复杂度都为 O(C + E)，
 * 其中 C 表示所有字符的总数，E 表示边的数量。选择 BFS 还是 DFS 取决于个人偏好。
 */

public class LeetCode_269_AlienDictionary {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 方法一：BFS
        public String alienOrder(String[] words) {

            // Step 0: 创建数据结构并找到所有唯一的字母。
            Map<Character, List<Character>> adjList = new HashMap<>();
            Map<Character, Integer> counts = new HashMap<>();
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    counts.put(c, 0);
                    adjList.put(c, new ArrayList<>());
                }
            }
            //["wrt","wrf","er","ett","rftt"]
            System.out.println("adjList is: " + adjList);
            System.out.println("indegree is: " + counts);


            // Step 1: 找到所有的边。
            for (int i = 0; i < words.length - 1; i++) {
                String word1 = words[i];
                String word2 = words[i + 1];
                // 检查 word2 是否是 word1 的前缀。
                if (word1.length() > word2.length() && word1.startsWith(word2)) {
                    return "";
                }
                // 找到第一个不匹配的字符并插入相应的关系。
                for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                    if (word1.charAt(j) != word2.charAt(j)) {
                        adjList.get(word1.charAt(j)).add(word2.charAt(j));
                        counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                        break;
                    }
                }
            }
            System.out.println("adjList is: " + adjList);
            System.out.println("indegree is: " + counts);

            // Step 2: 广度优先搜索。
            StringBuilder sb = new StringBuilder();
            Queue<Character> queue = new LinkedList<>();
            for (Character c : counts.keySet()) {
                if (counts.get(c).equals(0)) {
                    queue.add(c);
                }
            }
            while (!queue.isEmpty()) {
                Character c = queue.remove();
                sb.append(c);
                for (Character next : adjList.get(c)) {
                    counts.put(next, counts.get(next) - 1);
                    if (counts.get(next).equals(0)) {
                        queue.add(next);
                    }
                }
            }

            if (sb.length() < counts.size()) {
                return "";
            }
            return sb.toString();
        }

        // 方法二：DFS
        private Map<Character, List<Character>> reverseAdjList = new HashMap<>();
        private Map<Character, Boolean> seen = new HashMap<>();
        private StringBuilder output = new StringBuilder();

        public String alienOrder2(String[] words) {

            // Step 0: 将所有唯一的字母放入 reverseAdjList 中作为键。
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    reverseAdjList.putIfAbsent(c, new ArrayList<>());
                }
            }

            // Step 1: 找到所有的边，并在 reverseAdjList 中添加反向边。
            for (int i = 0; i < words.length - 1; i++) {
                String word1 = words[i];
                String word2 = words[i + 1];
                // 检查 word2 是否是 word1 的前缀。
                if (word1.length() > word2.length() && word1.startsWith(word2)) {
                    return "";
                }
                // 找到第一个不匹配的字符并插入相应的关系。
                for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                    if (word1.charAt(j) != word2.charAt(j)) {
                        reverseAdjList.get(word2.charAt(j)).add(word1.charAt(j));
                        break;
                    }
                }
            }

            // Step 2: 使用深度优先搜索构建输出列表。
            for (Character c : reverseAdjList.keySet()) {
                boolean result = dfs(c);
                if (!result) return "";
            }

            return output.toString();
        }

        // 如果没有检测到循环，则返回 true。
        private boolean dfs(Character c) {
            if (seen.containsKey(c)) {
                return seen.get(c); // 如果该节点为灰色（false），则检测到了循环。
            }
            seen.put(c, false);
            for (Character next : reverseAdjList.get(c)) {
                boolean result = dfs(next);
                if (!result) return false;
            }
            seen.put(c, true);
            output.append(c);
            return true;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_269_AlienDictionary().new Solution();

        // 测试用例
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(solution.alienOrder(words));  // 应该返回 "wertf"

        String[] words2 = {"z", "x", "z"};
        System.out.println(solution.alienOrder(words2));  // 应该返回 ""，因为存在循环依赖
    }
}

/**
There is a new alien language that uses the English alphabet. However, the 
order of the letters is unknown to you. 

 You are given a list of strings words from the alien language's dictionary. 
Now it is claimed that the strings in words are sorted lexicographically by the 
rules of this new language. 

 If this claim is incorrect, and the given arrangement of string in words 
cannot correspond to any order of letters, return "". 

 Otherwise, return a string of the unique letters in the new alien language 
sorted in lexicographically increasing order by the new language's rules. If there 
are multiple solutions, return any of them. 

 
 Example 1: 

 
Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
 

 Example 2: 

 
Input: words = ["z","x"]
Output: "zx"
 

 Example 3: 

 
Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
 

 
 Constraints: 

 
 1 <= words.length <= 100 
 1 <= words[i].length <= 100 
 words[i] consists of only lowercase English letters. 
 

 Related Topics Array String Depth-First Search Breadth-First Search Graph 
Topological Sort 👍 4298 👎 962

*/

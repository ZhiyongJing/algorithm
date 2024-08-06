package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *@Question:  2246. Longest Path With Different Adjacent Characters
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 60.14000000000001%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 题目分析
 * **题目**: 2246. Longest Path With Different Adjacent Characters
 *
 * **题目描述**:
 * 给定一个树结构和一个字符串 `s`，其中每个节点都有一个字符。目标是找到一条最长的路径，使得路径上相邻的字符都不同。
 *
 * ### 解题思路
 *
 * #### 1. 深度优先搜索 (DFS)
 *
 * **思路**:
 * - **树的构建**: 从给定的 `parent` 数组构建树的结构，其中 `parent[i]` 表示节点 `i` 的父节点。
 * - **DFS 遍历**: 从根节点开始，使用深度优先搜索遍历树的所有节点。
 *   - 对每个节点，递归计算所有子节点的最长路径。
 *   - 在计算当前节点的路径时，必须确保相邻节点的字符不同。只有满足这一条件，才会更新当前节点的最长路径。
 *   - 维护两个变量 `longestChain` 和 `secondLongestChain`，分别表示从当前节点出发的最长路径和第二长路径。
 *
 * **步骤**:
 * 1. 从根节点开始遍历。
 * 2. 对每个子节点递归计算其最长路径。
 * 3. 更新当前节点的最长路径和第二长路径，并记录全局最长路径。
 * 4. 返回从当前节点出发的最长路径的长度。
 *
 * **时间复杂度**:
 * - **O(N)**: 每个节点和边都被访问一次，其中 `N` 是节点的总数。
 *
 * **空间复杂度**:
 * - **O(N)**: 主要是递归栈和存储树的结构，空间复杂度由树的深度和节点数量决定。
 *
 * #### 2. 广度优先搜索 (BFS)
 *
 * **思路**:
 * - **树的构建**: 与 DFS 方法相同，通过 `parent` 数组构建树的结构。
 * - **BFS 遍历**: 从所有叶子节点开始，向上进行广度优先搜索。
 *   - 维护每个节点的最长路径和第二长路径，更新父节点的路径信息。
 *   - 使用队列存储当前节点，并逐步向父节点传播更新的路径信息。
 *
 * **步骤**:
 * 1. 将所有叶子节点入队，初始时最长路径为1（叶子节点本身）。
 * 2. 从队列中取出节点，更新其父节点的路径信息。
 * 3. 如果父节点的所有子节点都处理完成，将父节点入队进行处理。
 * 4. 返回最长路径的长度。
 *
 * **时间复杂度**:
 * - **O(N)**: 每个节点和边都被处理一次，其中 `N` 是节点的总数。
 *
 * **空间复杂度**:
 * - **O(N)**: 主要是队列和存储每个节点的最长路径信息，空间复杂度由队列和节点数量决定。
 *
 * ### 总结
 * - **DFS** 和 **BFS** 都能有效解决此问题，复杂度在时间和空间上都是线性的。
 * - **DFS** 更适合递归深度较大的树结构，而 **BFS** 更适合广度较大的树结构。
 * - 根据树的具体形状和问题的要求，选择合适的搜索方法。
 */

public class LeetCode_2246_LongestPathWithDifferentAdjacentCharacters{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int longestPath = 1;  // 记录最长路径的长度

        // 深度优先搜索方法
        public int dfs(int currentNode, Map<Integer, List<Integer>> children, String s) {
            // 如果当前节点没有子节点，返回1（当前节点自身的路径长度）
            if (!children.containsKey(currentNode)) {
                return 1;
            }

            // 记录从当前节点出发的最长路径和第二长路径的长度
            int longestChain = 0, secondLongestChain = 0;
            for (int child : children.get(currentNode)) {
                // 计算从子节点出发的最长路径长度
                int longestChainStartingFromChild = dfs(child, children, s);
                // 如果当前子节点的字符与当前节点的字符相同，则跳过
                if (s.charAt(currentNode) == s.charAt(child)) {
                    continue;
                }
                // 更新最长路径和第二长路径的长度
                if (longestChainStartingFromChild > longestChain) {
                    secondLongestChain = longestChain;
                    longestChain = longestChainStartingFromChild;
                } else if (longestChainStartingFromChild > secondLongestChain) {
                    secondLongestChain = longestChainStartingFromChild;
                }
            }

            // 更新最长路径的长度
            longestPath = Math.max(longestPath, longestChain + secondLongestChain + 1);
            return longestChain + 1;  // 返回从当前节点出发的最长路径长度
        }

        // Solution1: 深度优先搜索 (DFS)
        public int longestPath(int[] parent, String s) {
            int n = parent.length;
            Map<Integer, List<Integer>> adj = new HashMap<>();
            // 从节点1开始，因为根节点0没有父节点
            for (int i = 1; i < n; i++) {
                adj.computeIfAbsent(parent[i], value -> new ArrayList<Integer>()).add(i);
            }
            System.out.println(adj);

            dfs(0, adj, s);  // 从根节点0开始进行DFS

            return longestPath;  // 返回最长路径的长度
        }

        // Solution2: 广度优先搜索 (BFS)
        public int longestPath2(int[] parent, String s) {
            int n = parent.length;
            int[] childrenCount = new int[n];
            // 从节点1开始，因为根节点没有父节点
            for (int node = 1; node < n; node++) {
                childrenCount[parent[node]]++;
            }

            Queue<Integer> q = new LinkedList<>();
            int longestPath = 1;
            int[][] longestChains = new int[n][2];  // 记录每个节点的最长路径和第二长路径

            for (int node = 1; node < n; node++) {
                // 将所有叶子节点入队
                if (childrenCount[node] == 0) {
                    longestChains[node][0] = 1;
                    q.offer(node);
                }
            }

            while (!q.isEmpty()) {
                int currentNode = q.poll();
                int par = parent[currentNode];
                // 获取当前节点的最长路径长度
                int longestChainStartingFromCurrNode = longestChains[currentNode][0];
                if (s.charAt(currentNode) != s.charAt(par)) {
                    // 更新当前节点的最长路径和第二长路径
                    if (longestChainStartingFromCurrNode > longestChains[par][0]) {
                        longestChains[par][1] = longestChains[par][0];
                        longestChains[par][0] = longestChainStartingFromCurrNode;
                    } else if (longestChainStartingFromCurrNode > longestChains[par][1]) {
                        longestChains[par][1] = longestChainStartingFromCurrNode;
                    }
                }

                longestPath = Math.max(longestPath, longestChains[par][0] + longestChains[par][1] + 1);
                childrenCount[par]--;

                // 如果当前节点的所有子节点都已处理，将当前节点入队
                if (childrenCount[par] == 0 && par != 0) {
                    longestChains[par][0]++;
                    q.offer(par);
                }
            }

            return longestPath;  // 返回最长路径的长度
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_2246_LongestPathWithDifferentAdjacentCharacters().new Solution();

        // 测试代码
        int[] parent = { -1, 0, 0, 1, 1, 2};  // 树的父节点数组
        String s = "abacbe";  // 节点的字符
        int result = solution.longestPath(parent, s);  // 使用DFS方法计算最长路径
        System.out.println("最长路径的长度是：" + result);  // 输出结果

        result = solution.longestPath2(parent, s);  // 使用BFS方法计算最长路径
        System.out.println("最长路径的长度是（BFS方法）：" + result);  // 输出结果
    }
}

/**
You are given a tree (i.e. a connected, undirected graph that has no cycles) 
rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is 
represented by a 0-indexed array parent of size n, where parent[i] is the parent of 
node i. Since node 0 is the root, parent[0] == -1. 

 You are also given a string s of length n, where s[i] is the character 
assigned to node i. 

 Return the length of the longest path in the tree such that no pair of 
adjacent nodes on the path have the same character assigned to them. 

 
 Example 1: 
 
 
Input: parent = [-1,0,0,1,1,2], s = "abacbe"
Output: 3
Explanation: The longest path where each two adjacent nodes have different 
characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 
is returned.
It can be proven that there is no longer path that satisfies the conditions. 
 

 Example 2: 
 
 
Input: parent = [-1,0,0,0], s = "aabc"
Output: 3
Explanation: The longest path where each two adjacent nodes have different 
characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 

 
 Constraints: 

 
 n == parent.length == s.length 
 1 <= n <= 10⁵ 
 0 <= parent[i] <= n - 1 for all i >= 1 
 parent[0] == -1 
 parent represents a valid tree. 
 s consists of only lowercase English letters. 
 

 Related Topics Array String Tree Depth-First Search Graph Topological Sort 👍 2
370 👎 61

*/
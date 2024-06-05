package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 *@Question:  797. All Paths From Source to Target
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 29.14%
 *@Time  Complexity: O(2^N * N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求找出从源节点到目标节点的所有路径。我们可以使用深度优先搜索（DFS）来解决这个问题。
 *
 * #### 步骤详解
 *
 * 1. **DFS遍历**：
 *    - 我们从源节点开始，使用深度优先搜索（DFS）遍历图。
 *    - 在遍历过程中，我们沿着图中的边前进，尝试所有可能的路径。
 *
 * 2. **回溯**：
 *    - 在遍历过程中，我们需要记录当前路径。
 *    - 每当遍历到目标节点时，我们将当前路径添加到结果集中。
 *    - 在回溯时，我们需要撤销当前节点的选择，以便尝试其他路径。
 *
 * #### 时间和空间复杂度分析
 *
 * #### 时间复杂度
 * - 对于每个节点，我们可能需要探索所有的出边，因此时间复杂度为 `O(2^N * N)`，其中 `N` 是图中的节点数。
 *
 * #### 空间复杂度
 * - 递归调用的深度取决于图的深度，因此空间复杂度为 `O(N)`。
 */
public class LeetCode_797_AllPathsFromSourceToTarget{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义全局变量：目标节点、图、结果集
        private int target;
        private int[][] graph;
        private List<List<Integer>> results;

        // 定义回溯方法
        protected void backtrack(int currNode, LinkedList<Integer> path) {
            // 如果当前节点等于目标节点，将路径添加到结果集中
            if (currNode == this.target) {
                this.results.add(new ArrayList<Integer>(path));
                return;
            }
            // 遍历当前节点的邻居节点
            for (int nextNode : this.graph[currNode]) {
                // 在回溯前将选择标记在路径中
                path.addLast(nextNode);
                // 递归调用回溯方法
                this.backtrack(nextNode, path);
                // 移除前一个选择，尝试下一个选择
                path.removeLast();
            }
        }

        // 定义求解方法
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // 初始化目标节点、图、结果集
            this.target = graph.length - 1;
            this.graph = graph;
            this.results = new ArrayList<List<Integer>>();
            // 从源节点（节点0）开始回溯
            LinkedList<Integer> path = new LinkedList<Integer>();
            path.addLast(0);
            this.backtrack(0, path);
            return this.results;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_797_AllPathsFromSourceToTarget().new Solution();
        // TO TEST
        // 添加测试样例
        int[][] graph = {{1,2}, {3}, {3}, {}};
        List<List<Integer>> paths = solution.allPathsSourceTarget(graph);
        System.out.println("All paths from source to target:");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }
}

/**
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find 
all possible paths from node 0 to node n - 1 and return them in any order. 

 The graph is given as follows: graph[i] is a list of all nodes you can visit 
from node i (i.e., there is a directed edge from node i to node graph[i][j]). 

 
 Example 1: 
 
 
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 

 Example 2: 
 
 
Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 

 
 Constraints: 

 
 n == graph.length 
 2 <= n <= 15 
 0 <= graph[i][j] < n 
 graph[i][j] != i (i.e., there will be no self-loops). 
 All the elements of graph[i] are unique. 
 The input graph is guaranteed to be a DAG. 
 

 Related Topics Backtracking Depth-First Search Breadth-First Search Graph 👍 71
70 👎 146

*/
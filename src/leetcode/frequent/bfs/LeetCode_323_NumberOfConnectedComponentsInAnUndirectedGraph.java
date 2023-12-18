package leetcode.frequent.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
  *@Question:  323. Number of Connected Components in an Undirected Graph     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 11.43%      
  *@Time  Complexity: O(E + V) E = Number of edges, V = Number of vertices.
  *@Space Complexity: O(E + V)
 */

/**
 * **算法思路：**
 *
 * 这题的目标是计算一个无向图中的连通分量数量。为了实现这个目标，使用深度优先搜索（DFS）来遍历图的节点，并标记已访问的节点。
 * 每次找到一个未访问的节点时，将其标记为已访问，并通过递归的方式继续深度优先搜索与之相邻的节点。遍历结束后，所有在同一连通分量中的节点都会被标记为已访问。
 *
 * **具体步骤：**
 *
 * 1. 初始化一个数组 `visited` 用于记录节点是否被访问，初始值都为 0。
 * 2. 遍历图的每个节点，如果节点未被访问，增加连通分量数量，并进行深度优先搜索。
 * 3. 在深度优先搜索中，将当前节点标记为已访问，然后递归地访问与之相邻的未访问节点。
 *
 * **时间复杂度：**
 *
 * - 遍历节点和边的过程中，每个节点和边最多被访问一次，因此时间复杂度为 O(E + V)，其中 E 为边的数量，V 为节点的数量。
 *
 * **空间复杂度：**
 *
 * - 使用了额外的数组 `visited` 来记录节点是否被访问，因此空间复杂度为 O(V)。
 *
 * 综上所述，该算法的时间复杂度为 O(E + V)，空间复杂度为 O(V)。
 */
public class LeetCode_323_NumberOfConnectedComponentsInAnUndirectedGraph {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 定义深度优先搜索函数
        private void dfs(List<Integer>[] adjList, int[] visited, int startNode) {
            // 标记当前节点为已访问
            visited[startNode] = 1;

            // 遍历邻接节点，如果邻接节点未被访问，则继续深度优先搜索
            for (int i = 0; i < adjList[startNode].size(); i++) {
                if (visited[adjList[startNode].get(i)] == 0) {
                    dfs(adjList, visited, adjList[startNode].get(i));
                }
            }
        }

        public int countComponents(int n, int[][] edges) {
            int components = 0;
            int[] visited = new int[n];

            // 创建邻接表，用于表示图的结构
            List<Integer>[] adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<Integer>();
            }

            // 构建邻接表，根据输入的边信息
            for (int i = 0; i < edges.length; i++) {
                adjList[edges[i][0]].add(edges[i][1]);
                adjList[edges[i][1]].add(edges[i][0]);
            }
            System.out.println(Arrays.toString(adjList));

            // 遍历所有节点，如果节点未被访问，则进行深度优先搜索，并增加连通分量数量
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0) {
                    components++;
                    dfs(adjList, visited, i);
                }
            }
            return components;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_323_NumberOfConnectedComponentsInAnUndirectedGraph().new Solution();
        // 测试代码
        int n1 = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println(solution.countComponents(n1, edges1));  // 输出 2

        int n2 = 5;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(solution.countComponents(n2, edges2));  // 输出 1
    }
}

/**
You have a graph of n nodes. You are given an integer n and an array edges 
where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the 
graph. 

 Return the number of connected components in the graph. 

 
 Example 1: 
 
 
Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2
 

 Example 2: 
 
 
Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1
 

 
 Constraints: 

 
 1 <= n <= 2000 
 1 <= edges.length <= 5000 
 edges[i].length == 2 
 0 <= ai <= bi < n 
 ai != bi 
 There are no repeated edges. 
 

 Related Topics Depth-First Search Breadth-First Search Union Find Graph 👍 2606
 👎 98

*/

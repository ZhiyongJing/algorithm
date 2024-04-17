package interview.company.amazon;

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
 *这段代码解决了LeetCode问题323：“无向图中连通分量的数目”。
 *
 * ### 解题思路：
 *
 * #### Solution 1: 使用深度优先搜索（DFS）解决
 *
 * 1. **构建邻接表：** 首先，根据给定的边信息，构建图的邻接表，用于表示图的结构。
 *
 * 2. **深度优先搜索：** 遍历图中的所有节点，对于每个未访问过的节点，进行深度优先搜索。在深度优先搜索的过程中，将访问过的节点标记为已访问，并继续深度优先搜索其邻接节点。每次深度优先搜索完成后，连通分量的数量加一。
 *
 * 3. **结果：** 最终，连通分量的数量即为解。
 *
 * #### Solution 2: 使用并查集解决
 *
 * 1. **初始化并查集：** 首先，初始化并查集，每个节点都是自己的代表，并且初始时每个集合的大小为1。
 *
 * 2. **遍历边并合并集合：** 遍历所有的边，对于每条边，找到边两端节点的代表，并判断是否属于同一个连通分量。如果不属于同一个连通分量，则将它们合并，并更新集合的大小。
 *
 * 3. **结果：** 最终，连通分量的数量即为节点的数量减去集合的数量。
 *
 * ### 时间复杂度：
 *
 * #### Solution 1：
 * - **构建邻接表：** 构建邻接表的时间复杂度为 O(E)，其中 E 是边的数量。
 * - **深度优先搜索：** 对于每个节点，最坏情况下需要遍历所有的边，因此深度优先搜索的时间复杂度为 O(V+E)，其中 V 是节点的数量。
 * 综合来看，总的时间复杂度为 O(V+E)。
 *
 * #### Solution 2：
 * - **初始化并查集：** 初始化并查集的时间复杂度为 O(V)，其中 V 是节点的数量。
 * - **遍历边并合并集合：** 遍历所有的边的时间复杂度为 O(E)，并查集的操作时间复杂度可以近似为 O(1)。
 * 综合来看，总的时间复杂度为 O(V+E)。
 *
 * ### 空间复杂度：
 *
 * #### Solution 1：
 * - **邻接表：** 使用了额外的空间存储邻接表，空间复杂度为 O(V+E)，其中 V 是节点的数量，E 是边的数量。
 *
 * #### Solution 2：
 * - **并查集：** 使用了额外的空间存储并查集的代表和集合大小，空间复杂度为 O(V)，其中 V 是节点的数量。
 *
 * 综合来看，这两种解法的空间复杂度都是与节点数量和边数量成正比的。
 *
 * 希望这些解释能帮助你理解解题思路和时间、空间复杂度的分析。
 */
public class LeetCode_323_NumberOfConnectedComponentsInAnUndirectedGraph {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
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
        //Solution 1
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

        //Solution 2: 使用并查集解决
        private int find(int[] representative, int vertex) {
            if (vertex == representative[vertex]) {
                return vertex;
            }

            return representative[vertex] = find(representative, representative[vertex]);
        }

        private int combine(int[] representative, int[] size, int vertex1, int vertex2) {
            vertex1 = find(representative, vertex1);
            vertex2 = find(representative, vertex2);

            if (vertex1 == vertex2) {
                return 0;
            } else {
                if (size[vertex1] > size[vertex2]) {
                    size[vertex1] += size[vertex2];
                    representative[vertex2] = vertex1;
                } else {
                    size[vertex2] += size[vertex1];
                    representative[vertex1] = vertex2;
                }
                return 1;
            }
        }

        public int countComponents2(int n, int[][] edges) {
            int[] representative = new int[n];
            int[] size = new int[n];

            // 初始化并查集
            for (int i = 0; i < n; i++) {
                representative[i] = i;
                size[i] = 1;
            }

            int components = n;
            // 遍历所有边，合并连通分量
            for (int i = 0; i < edges.length; i++) {
                components -= combine(representative, size, edges[i][0], edges[i][1]);
            }

            return components;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_323_NumberOfConnectedComponentsInAnUndirectedGraph.Solution solution = new LeetCode_323_NumberOfConnectedComponentsInAnUndirectedGraph().new Solution();
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

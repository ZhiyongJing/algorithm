package leetcode.question.union_find;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  323. Number of Connected Components in an Undirected Graph
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 25.009999999999998%
 *@Time  Complexity: O(E + V) for DFS, O(V + E * a(n)) for solution2
 *  E = Number of edges,  V = Number of vertices. α(n) is the inverse Ackermann function
 *@Space Complexity: O(E + V) for DFS, O(V) for solution2
 */

/**
 * 问题描述：
 * 给定一个无向图，表示为节点数量 n 和一组边 edges，每个边都是一对整数 [u, v]，表示节点 u 和节点 v 之间有一条边。我们需要计算这个无向图中连通分量的数量。
 *
 * 解题思路：
 *
 * 1. **深度优先搜索（DFS）解法**：
 *    - 使用邻接表表示图结构，并且初始化一个数组 `visited` 来记录每个节点是否被访问过。
 *    - 对于每个未被访问的节点，从该节点开始进行深度优先搜索（DFS），遍历所有与之相连的节点，并标记它们为已访问。
 *    - 每次启动新的 DFS 代表找到了一个新的连通分量，因此累加连通分量的计数器。
 *    - 时间复杂度为 O(V + E)，其中 V 是节点数，E 是边数。在遍历节点和边的过程中，每个节点和每条边都会被访问到一次。
 *    - 空间复杂度为 O(V + E)，主要用于存储邻接表和访问标记数组。
 *
 * 2. **并查集（Union Find）解法**：
 *    - 初始化并查集，每个节点自成一个集合，即每个节点的父节点指向自己。
 *    - 遍历每条边，将每对节点合并到同一个集合中。
 *    - 如果两个节点已经在同一个集合中，则合并操作失败，否则合并成功，并减少连通分量的计数器。
 *    - 时间复杂度为 O(E * α(n))，其中 α 是 Ackermann 函数的反函数，接近于常数。在实际应用中，对于大多数输入，α(n) 可视为一个非常小的值，因此可以认为接近于 O(E)。
 *    - 空间复杂度为 O(V)，用于存储并查集的父节点和大小信息。
 *
 * 总结：
 * - 深度优先搜索（DFS）适用于对图进行遍历和连通性检测的问题，实现简单，适合于图的基本操作。
 * - 并查集（Union Find）适用于动态连接和查询连通性的问题，特别是处理大规模图时，时间复杂度更优。
 */
public class LeetCode_323_NumberOfConnectedComponentsInAnUndirectedGraph{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 使用深度优先搜索来遍历并标记连接的节点
        private void dfs(List<Integer>[] adjList, int[] visited, int startNode) {
            visited[startNode] = 1; // 标记当前节点为已访问

            // 遍历当前节点的所有邻居节点
            for (int i = 0; i < adjList[startNode].size(); i++) {
                // 如果邻居节点未被访问过，则递归地进行深度优先搜索
                if (visited[adjList[startNode].get(i)] == 0) {
                    dfs(adjList, visited, adjList[startNode].get(i));
                }
            }
        }

        // Solution1: 深度优先搜索（DFS）
        public int countComponents1(int n, int[][] edges) {
            int components = 0; // 初始化连通分量的计数器
            int[] visited = new int[n]; // 用于标记节点是否被访问过的数组

            List<Integer>[] adjList = new ArrayList[n]; // 邻接表表示图
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<Integer>(); // 初始化邻接表的每一个节点
            }

            // 构建邻接表
            for (int i = 0; i < edges.length; i++) {
                adjList[edges[i][0]].add(edges[i][1]); // 在节点0和节点1之间添加双向边
                adjList[edges[i][1]].add(edges[i][0]);
            }

            // 遍历所有节点，进行深度优先搜索
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0) { // 如果当前节点未被访问过
                    components++; // 增加连通分量的计数器
                    dfs(adjList, visited, i); // 对该节点进行深度优先搜索
                }
            }
            return components; // 返回总的连通分量数目
        }

        // Solution2: 并查集（Union Find）
        private int find(int[] representative, int vertex) {
            if (vertex == representative[vertex]) { // 如果当前节点是其自身的代表节点
                return vertex; // 直接返回该节点
            }

            return representative[vertex] = find(representative, representative[vertex]); // 路径压缩
        }

        // 将两个节点所在的集合合并，并返回是否合并成功（1表示成功，0表示失败）
        private int combine(int[] representative, int[] size, int vertex1, int vertex2) {
            vertex1 = find(representative, vertex1); // 找到节点1的代表节点
            vertex2 = find(representative, vertex2); // 找到节点2的代表节点

            if (vertex1 == vertex2) { // 如果两个节点已经在同一个集合中
                return 0; // 返回合并失败
            } else {
                if (size[vertex1] > size[vertex2]) { // 将小集合并入大集合
                    size[vertex1] += size[vertex2];
                    representative[vertex2] = vertex1;
                } else {
                    size[vertex2] += size[vertex1];
                    representative[vertex1] = vertex2;
                }
                return 1; // 返回合并成功
            }
        }

        // 使用并查集计算连通分量的个数
        public int countComponents(int n, int[][] edges) {
            int[] representative = new int[n]; // 代表节点数组
            int[] size = new int[n]; // 各个集合的大小

            for (int i = 0; i < n; i++) {
                representative[i] = i; // 初始时每个节点是其自身的代表节点
                size[i] = 1; // 初始时每个集合的大小为1
            }

            int components = n; // 初始时连通分量的个数等于节点个数
            for (int i = 0; i < edges.length; i++) {
                components -= combine(representative, size, edges[i][0], edges[i][1]); // 尝试合并两个节点所在的集合
            }

            return components; // 返回最终的连通分量个数
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_323_NumberOfConnectedComponentsInAnUndirectedGraph().new Solution();
        // TO TEST
        int n = 5;
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println(solution.countComponents(n, edges)); // 输出：2
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
 

 Related Topics Depth-First Search Breadth-First Search Union Find Graph 👍 2660
 👎 101

*/
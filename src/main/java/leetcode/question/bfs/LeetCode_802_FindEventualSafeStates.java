package leetcode.question.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *@Question:  802. Find Eventual Safe States
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.62%
 *@Time Complexity: O(m + n), where n is the number of nodes and m is the number of edges.
 *@Space Complexity: O(m + n) for solution1 (BFS), O(n) for solution2 (DFS).
 */
/**
 * 题目描述：
 * 802. Find Eventual Safe States（找到最终安全状态的节点）
 *
 * 给定一个 **有向图**，其中 `graph[i]` 表示从节点 `i` 出发可以到达的所有节点的列表。
 * 如果一个节点 `i` 能够最终到达 **一个没有任何出边的终端节点**，我们称这个节点是**安全的**。
 *
 * **目标**：返回所有 **最终安全状态** 的节点列表，按照 **升序排列**。
 *
 * **示例 1**
 * ```plaintext
 * 输入: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * 输出: [2,4,5,6]
 * 解释:
 * 0 → 1 → 2 → 5
 *    ↘ 3 → 0 （存在环，不安全）
 * 4 → 5（安全）
 * 5、6（终端节点，安全）
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * 输出: [4]
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * **方法 1：BFS + 反向拓扑排序**
 * 1. **构造反向图**：
 *    - 将原图中的所有 **边反向**，即原图 `A -> B` 变为 `B -> A`。
 *    - 记录每个节点 `i` 的入度（反向图的出度）。
 * 2. **拓扑排序（类似 Kahn's 算法）**
 *    - 所有入度为 `0` 的节点（终端节点）加入队列 `queue`。
 *    - 从队列 `queue` 中不断取出节点 `i`，标记为安全。
 *    - 遍历 `i` 的所有前驱节点 `prev`，减少其入度 `indegree[prev]--`。
 *    - 若某个 `prev` 的入度变为 `0`，加入 `queue`。
 * 3. **最终所有 `safe[i] == true` 的节点即为安全节点，排序后返回**。
 *
 * **方法 2：DFS + 环检测**
 * 1. **使用 `visit[]` 记录访问状态**
 *    - `0` 表示未访问
 *    - `1` 表示正在访问（在递归栈中）
 *    - `2` 表示已确认安全
 * 2. **DFS 递归检测**
 *    - 若 `visit[node] == 1`（当前递归栈中），说明存在环，返回 `false`。
 *    - 若 `visit[node] == 2`（已确认安全），直接返回 `true`。
 *    - 递归访问 `graph[node]` 的所有邻居：
 *      - 若任意一个邻居处于环中，则 `node` 也是不安全的。
 * 3. **遍历所有节点，找出 `visit[i] == 2` 的所有安全节点，排序后返回**。
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `graph = [[1,2],[2,3],[5],[0],[5],[],[]]`
 *
 * **反向拓扑排序过程**
 * ```plaintext
 * 1. 终端节点：5, 6（无出度，入度 = 0），加入队列 `queue`。
 * 2. 处理 5：减少前驱节点 `2, 4` 的入度。
 * 3. 处理 6：无前驱，不影响。
 * 4. 处理 4：无出度，入度 = 0，加入 `queue`。
 * 5. 处理 2：无出度，入度 = 0，加入 `queue`。
 * 6. 处理 3、1、0：发现存在环，`0 → 1 → 3 → 0`，不安全。
 * 最终安全节点为 `[2,4,5,6]`。
 * ```
 *
 * **DFS 环检测过程**
 * ```plaintext
 * 遍历所有节点：
 * 1. 递归检测 0 → 1 → 2 → 5（安全），但 1 → 3 → 0 存在环（不安全）。
 * 2. 递归检测 4 → 5（安全）。
 * 3. 递归检测 5, 6（终端节点，安全）。
 * ```
 *
 * **最终安全节点**：`[2,4,5,6]`
 *
 * ---
 *
 * **时间复杂度分析**
 * - **BFS 反向拓扑排序**
 *   - **构造反向图** `O(m + n)`
 *   - **拓扑排序** `O(m + n)`
 *   - **最终排序** `O(n log n)`
 *   - **总时间复杂度：O(m + n + n log n) ≈ O(m + n)**（`n log n` 可忽略）
 *
 * - **DFS 环检测**
 *   - **遍历所有节点 `O(n)`，每条边最多遍历一次 `O(m)`**
 *   - **总时间复杂度：O(m + n)**
 *
 * **空间复杂度分析**
 * - **BFS 拓扑排序** 需要存储 **邻接表 `O(m + n)` + 队列 `O(n)` + 结果 `O(n)`**
 *   - **总空间复杂度：O(m + n)**
 * - **DFS 递归** 需要存储 **邻接表 `O(m + n)` + 递归栈 `O(n)`**
 *   - **总空间复杂度：O(n + m)**
 */


public class LeetCode_802_FindEventualSafeStates{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法 1: 拓扑排序 + 反向图 BFS
        public List<Integer> eventualSafeNodes(int[][] graph) {
            int n = graph.length;
            // 记录每个节点的入度（即反向图中的出度）
            int[] indegree = new int[n];
            // 反向邻接表存储每个节点的前驱节点
            List<List<Integer>> adj = new ArrayList<>();

            // 初始化邻接表
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            // 构造反向图，并计算每个节点的入度
            for (int i = 0; i < n; i++) {
                for (int node : graph[i]) {
                    adj.get(node).add(i); // 反向边
                    indegree[i]++; // 计算原图中的出度
                }
            }

            // 拓扑排序队列，存储所有入度为 0 的节点（终端节点）
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            // 记录所有安全节点
            boolean[] safe = new boolean[n];
            while (!q.isEmpty()) {
                int node = q.poll();
                safe[node] = true;

                // 遍历反向邻接表，减少前驱节点的出度
                for (int neighbor : adj.get(node)) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }

            // 筛选所有安全节点
            List<Integer> safeNodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (safe[i]) {
                    safeNodes.add(i);
                }
            }
            return safeNodes;
        }

        // 解法 2: 深度优先搜索（DFS）检测环
        public boolean dfs(
                int node,
                int[][] adj,
                boolean[] visit,
                boolean[] inStack
        ) {
            // 如果当前节点在递归栈中，说明存在环
            if (inStack[node]) {
                return true;
            }
            // 如果当前节点已经被访问过，则返回 false
            if (visit[node]) {
                return false;
            }

            // 标记当前节点为已访问，并加入递归栈
            visit[node] = true;
            inStack[node] = true;

            // 递归遍历所有邻居节点，检测是否存在环
            for (int neighbor : adj[node]) {
                if (dfs(neighbor, adj, visit, inStack)) {
                    return true; // 如果发现环，返回 true
                }
            }

            // 递归结束，移除递归栈标记
            inStack[node] = false;
            return false;
        }

        public List<Integer> eventualSafeNodes2(int[][] graph) {
            int n = graph.length;
            boolean[] visit = new boolean[n];
            boolean[] inStack = new boolean[n];

            // 遍历所有节点，进行 DFS 检测环
            for (int i = 0; i < n; i++) {
                dfs(i, graph, visit, inStack);
            }

            // 过滤出所有不在环中的安全节点
            List<Integer> safeNodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (!inStack[i]) {
                    safeNodes.add(i);
                }
            }
            return safeNodes;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_802_FindEventualSafeStates().new Solution();

        // 测试样例 1
        int[][] graph1 = {
                {1,2},{2,3},{5},{0},{5},{},{}
        };
        System.out.println(solution.eventualSafeNodes(graph1)); // 预期输出: [2,4,5,6]

        // 测试样例 2
        int[][] graph2 = {
                {1,2,3,4},{1,2},{3,4},{0,4},{}
        };
        System.out.println(solution.eventualSafeNodes(graph2)); // 预期输出: [4]

        // 测试样例 3
        int[][] graph3 = {
                {1},{2},{3},{4},{0}
        };
        System.out.println(solution.eventualSafeNodes(graph3)); // 预期输出: []

        // 测试样例 4
        int[][] graph4 = {
                {1,2},{2,3},{3,4},{4,5},{5,6},{6,7},{7,8},{8,9},{}
        };
        System.out.println(solution.eventualSafeNodes(graph4)); // 预期输出: [9]

        // 测试样例 5
        int[][] graph5 = {
                {1},{0},{3,4},{4},{2}
        };
        System.out.println(solution.eventualSafeNodes(graph5)); // 预期输出: []
    }
}

/**
There is a directed graph of n nodes with each node labeled from 0 to n - 1. 
The graph is represented by a 0-indexed 2D integer array graph where graph[i] is 
an integer array of nodes adjacent to node i, meaning there is an edge from node 
i to each node in graph[i]. 

 A node is a terminal node if there are no outgoing edges. A node is a safe 
node if every possible path starting from that node leads to a terminal node (or 
another safe node). 

 Return an array containing all the safe nodes of the graph. The answer should 
be sorted in ascending order. 

 
 Example 1: 
 
 
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of 
them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6. 

 Example 2: 

 
Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 
4.
 

 
 Constraints: 

 
 n == graph.length 
 1 <= n <= 10⁴ 
 0 <= graph[i].length <= n 
 0 <= graph[i][j] <= n - 1 
 graph[i] is sorted in a strictly increasing order. 
 The graph may contain self-loops. 
 The number of edges in the graph will be in the range [1, 4 * 10⁴]. 
 

 Related Topics Depth-First Search Breadth-First Search Graph Topological Sort ?
? 6436 👎 501

*/
package leetcode.question.dfs;
import java.util.*;

/**
 *@Question:  684. Redundant Connection
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 58.7%
 *@Time  Complexity: O(N)  for DFS solution, O(N * α(N)) for DSU solution (α(N) 为 Ackermann 逆函数，接近常数)
 *@Space Complexity: O(N)
 */
/**
 * 1. 题目描述:
 *    - 给定一个 **无向图**，其中包含 `n` 个节点，节点编号从 `1` 到 `n`。
 *    - 该图由 `n` 条边组成，意味着存在 **一条多余的边**。
 *    - 目标: **找到并删除这条冗余边，使得图仍然是一棵树**（即无环）。
 *    - **保证输入数据只有一条冗余边**。
 *
 *    **示例 1:**
 *      输入:
 *        [[1,2], [1,3], [2,3]]
 *      输出:
 *        [2,3]
 *
 *    **示例 2:**
 *      输入:
 *        [[1,2], [2,3], [3,4], [1,4], [1,5]]
 *      输出:
 *        [1,4]
 *
 *    **约束:**
 *      - `1 <= edges.length <= 1000`
 *      - `edges[i] = [u, v]`，1 <= u, v <= n
 *      - 输入数据保证 **每个输入图只有一个冗余边**。
 */

/**
 * 2. 解题思路:
 *    由于输入的图是连通的，并且 **只有一条冗余边**，意味着移除该边后，整个图应该成为一棵**树**（无环）。
 *    我们可以使用 **两种方法** 来找到这条冗余边：
 *
 *    **解法 1：DFS 检测环**
 *      - **思路:**
 *        1. 使用 **邻接表** 表示无向图。
 *        2. 遍历所有边，将每条边加入图中，并使用 **DFS** 检查是否形成环。
 *        3. 如果形成环，则说明该边是冗余边，返回该边。
 *      - **具体步骤:**
 *        - **构建图的邻接表** (`adjList`)。
 *        - **使用 DFS 进行环检测**:
 *          - 遍历每条边，使用 **深度优先搜索 (DFS)** 检查是否已经访问过。
 *          - 如果当前节点的相邻节点已经访问过，并且 **不是它的父节点**，说明发现了环。
 *          - 记录环的起始节点，并回溯找到环中的所有节点。
 *        - **遍历输入的边**，找到最后一条构成环的边，即为冗余边。
 *
 *    **示例解析:**
 *
 *    **初始状态（空图）**
 *      - 调用 `findRedundantConnection([[1,2], [1,3], [2,3]])`
 *
 *    **构建邻接表**
 *        1 -> [2, 3]
 *        2 -> [1, 3]
 *        3 -> [1, 2]
 *
 *    **DFS 发现环**
 *        - 从 `1` 开始 DFS，访问 `2`，然后访问 `3`。
 *        - 发现 `3` 已经访问过，并且 `3` 不是 `2` 的父节点，说明 `3` 在环中。
 *
 *    **找到冗余边**
 *        - `[2,3]` 是最后一条构成环的边，因此返回 `[2,3]`。
 *
 *
 *    **解法 2：并查集 (Disjoint Set Union, DSU)**
 *      - **思路:**
 *        1. 使用 **并查集**（DSU）维护连通性。
 *        2. 遍历所有边，每次尝试合并两个节点。
 *        3. 如果发现两个节点已经在同一连通分量中，则该边为冗余边。
 *      - **具体步骤:**
 *        - **初始化 DSU**: 每个节点的代表为自己，所有集合大小初始化为 `1`。
 *        - **遍历所有边**:
 *          - **尝试合并两个节点**。
 *          - **如果合并失败**，说明两个节点已经属于同一集合，则返回该边。
 *
 *    **示例解析**
 *      输入：
 *        [[1,2], [1,3], [2,3]]
 *
 *      并查集操作：
 *        - 合并 `[1,2]` -> 集合 `{1,2}`
 *        - 合并 `[1,3]` -> 集合 `{1,2,3}`
 *        - 处理 `[2,3]` 时，发现 `2` 和 `3` 已在同一集合中 -> 冗余边 `[2,3]`
 *
 *    **两种方法的比较**
 *      - **DFS 适用于小规模图**，时间复杂度 O(N)。
 *      - **并查集 (DSU) 具有更优的时间复杂度 O(N * α(N))**，适用于大规模数据。
 */

/**
 * 3. 时间和空间复杂度分析:
 *
 *    **DFS 解法**
 *      - **时间复杂度**: O(N) (构建邻接表 O(N)，DFS O(N))
 *      - **空间复杂度**: O(N) (存储图的邻接表 O(N)，DFS 递归栈 O(N))
 *
 *    **并查集 (DSU) 解法**
 *      - **时间复杂度**: O(N * α(N))  (其中 α(N) 是 Ackermann 逆函数，接近常数)
 *      - **空间复杂度**: O(N)  (存储 `parent` 和 `size` 数组)
 */

public class LeetCode_684_RedundantConnection{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int cycleStart = -1; // 用于存储环的起始节点

        // 通过 DFS 查找环的起始节点
        private void DFS(
                int src,
                boolean[] visited,
                List<Integer>[] adjList,
                int[] parent
        ) {
            visited[src] = true; // 标记当前节点已访问

            for (int adj : adjList[src]) { // 遍历所有相邻节点
                if (!visited[adj]) { // 如果未访问过该节点
                    parent[adj] = src; // 记录父节点
                    DFS(adj, visited, adjList, parent); // 递归搜索
                    // 如果发现一个已访问的节点且不是当前节点的父节点，则说明形成了环
                } else if (adj != parent[src] && cycleStart == -1) {
                    cycleStart = adj; // 记录环的起始节点
                    parent[adj] = src;
                }
            }
        }

        // **解法1: DFS 检测环**
        public int[] findRedundantConnection1(int[][] edges) {
            int N = edges.length; // 计算节点数量

            boolean[] visited = new boolean[N]; // 记录节点访问状态
            int[] parent = new int[N]; // 记录父节点
            Arrays.fill(parent, -1); // 初始化父节点数组

            List<Integer>[] adjList = new ArrayList[N]; // 用邻接表存储图
            for (int i = 0; i < N; i++) {
                adjList[i] = new ArrayList<>();
            }

            // 构建无向图
            for (int[] edge : edges) {
                adjList[edge[0] - 1].add(edge[1] - 1);
                adjList[edge[1] - 1].add(edge[0] - 1);
            }

            // 进行 DFS 查找环
            DFS(0, visited, adjList, parent);

            Map<Integer, Integer> cycleNodes = new HashMap<>();
            int node = cycleStart;
            // 回溯找到所有形成环的节点
            do {
                cycleNodes.put(node, 1);
                node = parent[node];
            } while (node != cycleStart);

            // 从后向前遍历 edges，找到最后一条构成环的边
            for (int i = edges.length - 1; i >= 0; i--) {
                if (
                        cycleNodes.containsKey(edges[i][0] - 1) &&
                                cycleNodes.containsKey(edges[i][1] - 1)
                ) {
                    return edges[i]; // 返回需要移除的边
                }
            }

            return new int[] {}; // 代码不会执行到这里
        }

        // **解法2: 并查集 DSU**
        class DSU {
            private int[] size;
            private int[] representative;

            // 初始化并查集
            public DSU(int N) {
                size = new int[N];
                representative = new int[N];

                for (int node = 0; node < N; node++) {
                    size[node] = 1;
                    representative[node] = node;
                }
            }

            // 查找并返回某个节点的祖先 (路径压缩优化)
            public int find(int node) {
                if (representative[node] == node) {
                    return node;
                }

                return representative[node] = find(representative[node]);
            }

            // 合并两个集合，并返回是否成功合并
            public boolean doUnion(int nodeOne, int nodeTwo) {
                nodeOne = find(nodeOne);
                nodeTwo = find(nodeTwo);

                if (nodeOne == nodeTwo) {
                    return false; // 如果两个节点已经在同一集合中，则返回 false
                } else {
                    // 按秩合并 (Union by size)
                    if (size[nodeOne] > size[nodeTwo]) {
                        representative[nodeTwo] = nodeOne;
                        size[nodeOne] += size[nodeTwo];
                    } else {
                        representative[nodeOne] = nodeTwo;
                        size[nodeTwo] += size[nodeOne];
                    }
                    return true;
                }
            }
        }

        public int[] findRedundantConnection(int[][] edges) {
            int N = edges.length;

            DSU dsu = new DSU(N);
            for (int[] edge : edges) {
                // 如果合并失败，说明出现环，则该边为冗余边
                if (!dsu.doUnion(edge[0] - 1, edge[1] - 1)) {
                    return edge;
                }
            }

            return new int[] {}; // 代码不会执行到这里
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_684_RedundantConnection().new Solution();

        // 测试样例1
        int[][] edges1 = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println("冗余边: " + Arrays.toString(solution.findRedundantConnection1(edges1)));
        System.out.println("冗余边 (DSU): " + Arrays.toString(solution.findRedundantConnection(edges1)));

        // 测试样例2
        int[][] edges2 = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        System.out.println("冗余边: " + Arrays.toString(solution.findRedundantConnection1(edges2)));
        System.out.println("冗余边 (DSU): " + Arrays.toString(solution.findRedundantConnection(edges2)));
    }
}

/**
In this problem, a tree is an undirected graph that is connected and has no 
cycles. 

 You are given a graph that started as a tree with n nodes labeled from 1 to n, 
with one additional edge added. The added edge has two different vertices 
chosen from 1 to n, and was not an edge that already existed. The graph is 
represented as an array edges of length n where edges[i] = [ai, bi] indicates that there 
is an edge between nodes ai and bi in the graph. 

 Return an edge that can be removed so that the resulting graph is a tree of n 
nodes. If there are multiple answers, return the answer that occurs last in the 
input. 

 
 Example 1: 
 
 
Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
 

 Example 2: 
 
 
Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]
 

 
 Constraints: 

 
 n == edges.length 
 3 <= n <= 1000 
 edges[i].length == 2 
 1 <= ai < bi <= edges.length 
 ai != bi 
 There are no repeated edges. 
 The given graph is connected. 
 

 Related Topics Depth-First Search Breadth-First Search Union Find Graph 👍 6797
 👎 430

*/
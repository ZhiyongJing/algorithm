package template;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;
/*
 * 题目描述：
 * Dijkstra 算法用于计算 **单源最短路径**，即从 **起点（source）** 到 **图中所有其他顶点的最短路径**。
 * 该算法适用于 **无负权边** 的图，并可使用 **邻接矩阵** 或 **邻接表 + 优先队列（最小堆）** 实现。
 *
 * 输入：
 * - `graph`：邻接矩阵或邻接表，表示图的所有边及其权重。
 * - `source`：起始顶点索引，从该顶点出发计算最短路径。
 *
 * 输出：
 * - `dist[]`：一个数组，其中 `dist[i]` 表示从 `source` 到 `i` 的最短路径长度。
 * - 过程日志：每一步的路径更新情况。
 *
 * 示例：
 * 输入：
 *   graph = graph = {
                {0, 12, 0, 0, 0, 16, 14},//a
                {12, 0, 10, 0, 0, 7, 0},//b
                {0, 10, 0, 3, 5, 6, 0},//c
                {0, 0, 3, 0, 4, 0, 0},//d
                {0, 0, 5, 4, 0, 2, 8},//e
                {16, 7, 6, 0, 2, 0, 9},//f
                {14, 0, 0, 0, 8, 9, 0}//g
        };
 *   source = 3
 * 输出：
 *   dist = [22, 13, 3, 0, 4, 6, 12]
 *   并打印路径更新日志：
 *   current step 0: a: INF ,b: INF ,c: 3 ,d: 0 ,e: 4 ,f: INF ,g: INF ,
 *   current step 1: a: INF ,b: 13 ,c: 3 ,d: 0 ,e: 4 ,f: INF ,g: INF ,
 *   ...
 */

/*
 * 解题思路：
 * Dijkstra 算法是一种 **贪心算法**，用于计算 **单源最短路径**。
 *
 * **方法 1：使用邻接矩阵（O(V²)）**
 * 1️⃣ **初始化**
 * - 创建 `dist[]` 数组，初始值为 `Integer.MAX_VALUE`，表示所有顶点与 `source` 不可达。
 * - `dist[source] = 0`，起点到自身的距离为 `0`。
 * - `visited[]` 数组，记录哪些顶点已经计算了最短路径，初始值均为 `false`。
 *
 * 2️⃣ **迭代 V-1 次，每次选择当前最短的未访问顶点**
 * - 通过 `minDistance()` 找到 `dist[]` 中 **未访问** 且 **最短路径最小** 的顶点 `u`。
 * - 标记 `u` 为已访问。
 * - 遍历 `u` 的所有邻接顶点 `v`：
 *   - 如果 `v` **未访问** 且 `u -> v` 存在边：
 *   - 计算 `source -> v` 的新路径 `dist[v] = min(dist[v], dist[u] + graph[u][v])`。
 *
 * 3️⃣ **终止**
 * - 经过 `V-1` 次迭代后，所有顶点的最短路径已确定，`dist[]` 结果即为最终解。
 * - 输出 `dist[]` 以及每一步的 `current step` 状态。
 *
 * 示例计算：
 * 以 `source = 3 (d)` 作为起点，初始 `dist[]`：
 * ```
 *  d: 0, a: INF, b: INF, c: 3, e: 4, f: INF, g: INF
 * ```
 * 第一步：
 * - 选择 `c (3)`，更新 `c` 的邻居：
 * ```
 *  d: 0, a: INF, b: 13, c: 3, e: 4, f: 9, g: INF
 * ```
 * 第二步：
 * - 选择 `e (4)`，更新 `e` 的邻居：
 * ```
 *  d: 0, a: INF, b: 13, c: 3, e: 4, f: 6, g: 12
 * ```
 * ...
 * 直到所有顶点的最短路径更新完毕，最终 `dist[]`：
 * ```
 *  d: 0, a: 22, b: 13, c: 3, e: 4, f: 6, g: 12
 * ```
 *
 * **方法 2：使用优先队列优化（O((V+E) log V)）**
 * 1️⃣ **初始化**
 * - 使用 `PriorityQueue`（最小堆）维护未访问顶点的最短路径。
 * - `dist[]` 记录 `source` 到各顶点的最短距离，初始值为 `Integer.MAX_VALUE`。
 * - `minHeap` 存储 `{顶点, 最短路径}`，初始时 `{source, 0}` 入队。
 *
 * 2️⃣ **主循环**
 * - 每次取出 `minHeap` 中 `dist` 最小的顶点 `u`，然后更新 `u` 的所有邻接顶点 `v`：
 *   - 计算 `newDist = dist[u] + weight[u][v]`
 *   - 若 `newDist < dist[v]`，则更新 `dist[v]` 并将 `{v, newDist}` 放入 `minHeap`。
 *
 * 3️⃣ **终止**
 * - 直到 `minHeap` 为空时，所有最短路径已确定。
 * - `dist[]` 记录最终结果。
 *
 * 示例：
 * - `minHeap` 维护 `{3, 0} -> {c, 3} -> {e, 4} -> {g, 6} -> {b, 13} -> {f, 12} -> {a, 22}`
 *
 */

/*
 * 时间和空间复杂度分析：
 *
 * **方法 1（邻接矩阵 + 线性查找 `minDistance()`）**
 * - **时间复杂度：** `O(V^2)`
 *   - 查找 `minDistance()` 需要 `O(V)` 遍历 `dist[]`，共执行 `V` 次。
 *   - 更新所有邻接顶点 `O(V)`，总共 `V` 次。
 *   - **总复杂度：O(V^2)**
 * - **空间复杂度：** `O(V^2)`（邻接矩阵存储所有边）。
 *
 * **方法 2（邻接表 + 优先队列 `PriorityQueue`）**
 * - **时间复杂度：** `O((V+E) log V)`
 *   - `PriorityQueue` 插入和删除操作 `O(log V)`。
 *   - `V` 个顶点被 `log V` 访问，每个 `E` 条边更新 `log V` 次。
 *   - **总复杂度：O((V+E) log V)**，适用于 **稀疏图**。
 * - **空间复杂度：** `O(V + E)`
 *   - 需要存储 `V` 个顶点的 `dist[]`，以及 `E` 条边的邻接表。
 *
 * **不同实现方式的时间复杂度对比**
 * |  实现方式   | 时间复杂度 | 适用场景 |
 * |------------|------------|----------|
 * | **邻接矩阵 + 线性查找 `minDistance()`** | `O(V^2)` | 适用于小规模图 |
 * | **邻接表 + 优先队列 `PriorityQueue`** | `O((V+E) log V)` | 适用于大规模稀疏图 |
 *
 * **算法适用场景**
 * - **适用于** 稠密图（`E ≈ V^2`）时 `O(V^2)` 的实现较快，适用于较小的 `V`。
 * - **适用于** 稀疏图（`E ≪ V^2`）时，建议使用 **优先队列优化** 以提升性能。
 */


public class Dijkstra {
    /**
     * 选择当前未访问的顶点中，最短路径值最小的顶点
     * @param dist 存储从源点到各顶点的最短路径长度
     * @param visited 记录哪些顶点已经被访问
     * @return 具有最小距离的未访问顶点索引
     */
    private static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE; // 记录当前最短距离，初始为无穷大
        int minIndex = -1; // 记录最短路径的顶点索引

        // 遍历所有顶点，找到未访问且距离最小的顶点
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i]; // 更新最小距离
                minIndex = i; // 记录该顶点索引
            }
        }
        return minIndex;
    }

    /**
     * Solution1: 使用邻接矩阵实现 Dijkstra 算法
     * 计算从源点到所有顶点的最短路径
     * @param graph 邻接矩阵表示的图，graph[i][j] 表示 i 到 j 的边权重
     * @param source 源点索引
     */
    private static void dijkstra(int[][] graph, int source) {
        int numVertices = graph.length; // 顶点个数
        int[] dist = new int[numVertices]; // 存储从源点到各顶点的最短路径值
        boolean[] visited = new boolean[numVertices]; // 记录哪些顶点已经被访问

        Arrays.fill(dist, Integer.MAX_VALUE); // 初始化所有顶点的最短路径为无穷大
        dist[source] = 0; // 源点到自身的距离为 0

        // 计算所有顶点的最短路径
        for (int count = 0; count < numVertices - 1; count++) {
            int u = minDistance(dist, visited); // 选择当前未访问顶点中最短路径值最小的顶点
            visited[u] = true; // 标记该顶点已访问

            // 更新所有邻接顶点的最短路径
            for (int v = 0; v < numVertices; v++) {
                // 只有满足以下条件才进行更新：
                // 1. 该顶点未访问
                // 2. u 到 v 之间存在边（即 graph[u][v] != 0）
                // 3. u 到源点的路径值不是无穷大
                // 4. 通过 u 到达 v 使路径变短
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v]; // 更新最短路径
                }
            }

            // 输出当前步骤的最短路径信息
            System.out.print("current step " + count + ": ");
            for (int i = 0; i < numVertices; i++) {
                System.out.print((char) (i + 'a') + ": " + dist[i] + " ,");
            }
            System.out.println();
        }
    }

    /**
     * Solution2: 使用最小堆（优先队列）优化 Dijkstra 算法
     * 计算从源点到所有顶点的最短路径
     * @param graph 邻接矩阵表示的图
     * @param start 源点索引
     * @return 返回从起点到所有顶点的最短路径数组
     */
    public static int[] dijkstra2(int[][] graph, int start) {
        int numVertices = graph.length; // 图的顶点数量
        int[] dist = new int[numVertices]; // 距离数组
        boolean[] visited = new boolean[numVertices]; // 访问标记数组
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>(
                (p1, p2) -> Integer.compare(p1.getValue(), p2.getValue())
        ); // 最小堆（存储顶点及其当前最短路径值）

        // 初始化距离数组
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0; // 起点到自身的距离为 0
        minHeap.add(new Pair<>(start, 0)); // 将起点加入优先队列

        while (!minHeap.isEmpty()) {
            Pair<Integer, Integer> currentNode = minHeap.poll(); // 取出当前最短路径的顶点
            int currentVertex = currentNode.getKey();

            // 如果当前节点已经访问过，则跳过
            if (visited[currentVertex]) {
                continue;
            }
            visited[currentVertex] = true; // 标记该节点为已访问

            // 遍历所有邻接顶点
            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (graph[currentVertex][neighbor] != 0 && !visited[neighbor]) {
                    int newDist = dist[currentVertex] + graph[currentVertex][neighbor];
                    // 如果新路径更短，则更新距离
                    if (newDist < dist[neighbor]) {
                        dist[neighbor] = newDist;
                        minHeap.add(new Pair<>(neighbor, newDist)); // 将更新的节点加入优先队列
                    }
                }
            }

            // 输出当前步骤的最短路径信息
            System.out.print("current step " + minHeap.size() + ": ");
            for (int i = 0; i < dist.length; i++) {
                System.out.print((char) (i + 'a') + ": " + dist[i] + " ,");
            }
            System.out.println();
        }

        return dist; // 返回从起点到各个节点的最短距离
    }

    public static void main(String[] args) {
        // 图的邻接矩阵表示，0 表示没有边
        int[][] graph = {
                {0, 12, 0, 0, 0, 16, 14},//a
                {12, 0, 10, 0, 0, 7, 0},//b
                {0, 10, 0, 3, 5, 6, 0},//c
                {0, 0, 3, 0, 4, 0, 0},//d
                {0, 0, 5, 4, 0, 2, 8},//e
                {16, 7, 6, 0, 2, 0, 9},//f
                {14, 0, 0, 0, 8, 9, 0}//g
        };

        int source = 3; // 以 'd' 作为源点（索引 3）

        // 测试 Solution 1：邻接矩阵实现 Dijkstra
        System.out.println("Dijkstra using adjacency matrix:");
        dijkstra(graph, source);

        // 测试 Solution 2：优先队列实现 Dijkstra
        System.out.println("\nDijkstra using priority queue:");
        dijkstra2(graph, source);
    }
}

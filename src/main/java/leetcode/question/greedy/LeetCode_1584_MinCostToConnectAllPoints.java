package leetcode.question.greedy;
/**
 *@Question:  1584. Min Cost to Connect All Points
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 42.87%
 *@Time  Complexity: O(N^2)  // Prim算法需要遍历所有点来选择最小的边，因此复杂度为 O(N^2)
 *@Space Complexity: O(N)  // 需要存储节点的最小距离数组 minDist 和 MST 状态数组 inMST，因此空间复杂度为 O(N)
 */
/**
 * 题目描述：
 * 给定一个二维平面上的一些点，要求通过**最小的总成本**将所有点连接起来。
 * 连接两个点的成本是它们之间的**曼哈顿距离**：
 *      |x1 - x2| + |y1 - y2|
 * 你可以使用任意顺序连接这些点，但最终必须保证所有点都连通，并且**总成本最小**。
 *
 * **示例 1**
 * 输入: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出: 20
 * 解释: 最优的连接方式如下：
 *      (0,0) --(2,2)
 *      (2,2) --(5,2)
 *      (5,2) --(7,0)
 *      (2,2) --(3,10)
 *      最小成本为 20。
 *
 * **示例 2**
 * 输入: points = [[3,12],[-2,5],[-4,1]]
 * 输出: 18
 */

/**
 * 解题思路：
 * 该问题是一个**最小生成树（Minimum Spanning Tree, MST）**问题，适用于 **Prim 算法** 或 **Kruskal 算法**。
 * 由于输入点较少（最多 1000 个），使用 **Prim 算法** 可以在 **O(N²)** 时间复杂度内解决该问题。
 *
 * **解法：Prim 算法**
 * 1. **初始化**：
 *    - 设 `n` 为点的总数。
 *    - 设 `minDist[i]` 记录 **当前 MST 到第 `i` 个点的最短距离**，初始时 `minDist[0] = 0`，其余点设为无穷大 `Integer.MAX_VALUE`。
 *    - 设 `inMST[i]` 记录点 `i` 是否已加入 MST，初始值均为 `false`。
 *    - 设 `mstCost = 0` 存储最小生成树的总权重。
 *
 * 2. **构建 MST**：
 *    - 进行 **n 次迭代**，每次从 `minDist` 中找出**最小的未加入 MST 的点**，加入 MST，并更新 `mstCost`。
 *    - 遍历所有未加入 MST 的点，计算它们到当前 MST 的最短距离，并更新 `minDist`。
 *
 * **示例步骤解析**
 * **输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]**
 *
 * - **Step 1:** 选取 `points[0] = (0,0)` 作为起始点，`mstCost = 0`。
 * - **Step 2:** 找到最近的点 `(2,2)`（距离 `4`），加入 MST，`mstCost = 4`。
 * - **Step 3:** 找到最近的点 `(5,2)`（距离 `3`），加入 MST，`mstCost = 7`。
 * - **Step 4:** 找到最近的点 `(7,0)`（距离 `5`），加入 MST，`mstCost = 12`。
 * - **Step 5:** 找到最近的点 `(3,10)`（距离 `8`），加入 MST，`mstCost = 20`。
 *
 * **最终 `mstCost = 20`**，即最小连接成本。
 */

/**
 * 时间和空间复杂度分析：
 * - **时间复杂度：O(N²)**：
 *   - `while` 循环运行 `N` 轮，每次选择 `minDist` 最小值的时间复杂度为 `O(N)`。
 *   - 更新 `minDist` 的时间复杂度为 `O(N)`。
 *   - 因此，总复杂度为 **O(N²)**，适用于 **N ≤ 1000** 的问题规模。
 *
 * - **空间复杂度：O(N)**：
 *   - 需要维护 `minDist`、`inMST` 等数组，空间复杂度为 **O(N)**。
 *   - 不需要额外的数据结构，如 `邻接表` 或 `并查集`，因此比 Kruskal 更适用于小规模问题。
 */

public class LeetCode_1584_MinCostToConnectAllPoints{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCostConnectPoints(int[][] points) {
            // 获取点的总数
            int n = points.length;
            // 记录最小生成树的总成本
            int mstCost = 0;
            // 记录当前已添加到最小生成树中的边数
            int edgesUsed = 0;

            // 记录某个节点是否已经被加入 MST（最小生成树）
            boolean[] inMST = new boolean[n];

            // 记录每个节点到当前 MST 的最小距离
            int[] minDist = new int[n];

            // 初始化第一个点的距离为 0，确保算法从这个点开始
            minDist[0] = 0;

            // 其他点的初始距离设为无穷大（Integer.MAX_VALUE），表示它们尚未连接
            for (int i = 1; i < n; ++i) {
                minDist[i] = Integer.MAX_VALUE;
            }

            // 运行 Prim 算法，直到所有节点都被加入 MST
            while (edgesUsed < n) {
                int currMinEdge = Integer.MAX_VALUE;
                int currNode = -1;

                // 在所有未加入 MST 的节点中，选择一个具有最小边权的节点
                for (int node = 0; node < n; ++node) {
                    if (!inMST[node] && currMinEdge > minDist[node]) {
                        currMinEdge = minDist[node];
                        currNode = node;
                    }
                }

                // 将该点加入 MST，并更新总成本
                mstCost += currMinEdge;
                edgesUsed++;
                inMST[currNode] = true;

                // 遍历所有未加入 MST 的节点，更新它们到当前 MST 的最小距离
                for (int nextNode = 0; nextNode < n; ++nextNode) {
                    // 计算当前节点与 nextNode 之间的曼哈顿距离（Manhattan Distance）
                    int weight = Math.abs(points[currNode][0] - points[nextNode][0]) +
                            Math.abs(points[currNode][1] - points[nextNode][1]);

                    // 如果 nextNode 还未加入 MST，并且新的边权小于之前的最小距离，则更新
                    if (!inMST[nextNode] && minDist[nextNode] > weight) {
                        minDist[nextNode] = weight;
                    }
                }
            }

            // 返回最小生成树的总权重，即最小连接所有点的成本
            return mstCost;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1584_MinCostToConnectAllPoints().new Solution();

        // 测试样例 1
        int[][] points1 = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println("Test Case 1: " + solution.minCostConnectPoints(points1)); // 预期输出: 20

        // 测试样例 2
        int[][] points2 = {{3,12},{-2,5},{-4,1}};
        System.out.println("Test Case 2: " + solution.minCostConnectPoints(points2)); // 预期输出: 18

        // 测试样例 3
        int[][] points3 = {{0,0},{1,1},{1,0},{-1,1}};
        System.out.println("Test Case 3: " + solution.minCostConnectPoints(points3)); // 预期输出: 4
    }
}

/**
You are given an array points representing integer coordinates of some points 
on a 2D-plane, where points[i] = [xi, yi]. 

 The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan 
distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value 
of val. 

 Return the minimum cost to make all points connected. All points are connected 
if there is exactly one simple path between any two points. 

 
 Example 1: 
 
 
Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
 

 Example 2: 

 
Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18
 

 
 Constraints: 

 
 1 <= points.length <= 1000 
 -10⁶ <= xi, yi <= 10⁶ 
 All pairs (xi, yi) are distinct. 
 

 Related Topics Array Union Find Graph Minimum Spanning Tree 👍 5210 👎 135

*/
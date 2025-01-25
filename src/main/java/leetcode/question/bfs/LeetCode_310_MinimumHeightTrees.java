package leetcode.question.bfs;

/**
 * @Question: 310. Minimum Height Trees
 * @Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 34.29%
 * @Time  Complexity: O(N)
 * @Space Complexity: O(N)
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 题目描述：
 * 在一棵 **无向连通树** 中，共有 `n` 个节点，节点编号从 `0` 到 `n-1`，并且有 `n-1` 条无向边。
 * 目标是找到 **最小高度树（MHT）** 的根节点，即能使树的 **最大深度最小化** 的根节点。
 *
 * 约束条件：
 * - `1 <= n <= 2 * 10^4`（保证树的规模不会太大）
 * - `edges.length == n - 1`（无环连通图）
 * - `edges[i] = [ai, bi]` 代表 `ai` 和 `bi` 之间有一条无向边
 *
 * 输入：
 * - `n`：整数，代表树的节点数。
 * - `edges`：`int[][]` 数组，代表树的边集合。
 *
 * 输出：
 * - `List<Integer>`：包含所有可以作为 MHT 根的节点编号（可能有多个）。
 *
 * 示例：
 * ```
 * 输入：
 * n = 4
 * edges = [[1,0], [1,2], [1,3]]
 * 输出：
 * [1]
 *
 * 输入：
 * n = 6
 * edges = [[3,0], [3,1], [3,2], [3,4], [5,4]]
 * 输出：
 * [3,4]
 * ```
 */

/*
 * 解题思路：
 * 1️⃣ **观察性质**
 *    - 树的中心节点（Centroid）是离 **最远叶子节点最近的节点**。
 *    - MHT 的根最多 **只有 1 个或 2 个**（如果树的高度为偶数，则有 2 个根）。
 *
 * 2️⃣ **构建邻接表（无向图）**
 *    - 由于输入的 `edges` 仅提供了边的关系，需要构建 **邻接表 `neighbors`** 来存储每个节点的邻居。
 *
 * 3️⃣ **找到初始叶子节点**
 *    - 叶子节点定义：**度数为 1 的节点**（即只有一个邻居的节点）。
 *    - 遍历所有节点，初始化 `leaves` 列表，将所有度数为 1 的节点存入 `leaves`。
 *
 * 4️⃣ **逐层修剪叶子节点**
 *    - 采用 **BFS（拓扑排序）**，每一轮删除当前所有叶子节点，并更新其邻居的度数：
 *      - 从 `leaves` 中移除当前层的叶子节点。
 *      - 递归删除所有叶子节点的邻居，使其邻居的度数减 1。
 *      - 如果某个邻居的度数变为 1，则它变成新的叶子节点，加入 `newLeaves`。
 *    - **不断修剪叶子，直到剩下最多 2 个节点**，它们即为最小高度树（MHT）的根。
 *
 * **示例**
 * ```
 * 输入：
 * n = 6
 * edges = [[3,0], [3,1], [3,2], [3,4], [5,4]]
 *
 * 1️⃣ 建立邻接表：
 * neighbors:
 *  0 -> {3}
 *  1 -> {3}
 *  2 -> {3}
 *  3 -> {0,1,2,4}
 *  4 -> {3,5}
 *  5 -> {4}
 *
 * 2️⃣ 识别初始叶子：
 * leaves = [0, 1, 2, 5] （度数为 1）
 *
 * 3️⃣ 逐层修剪：
 *  - 删除叶子 0,1,2,5，更新 neighbors：
 *    leaves = [3, 4]
 *  - 只剩下 3 和 4，返回 `[3,4]`
 *
 * 输出：
 * [3,4]
 * ```
 */

/*
 * 时间和空间复杂度分析：
 *
 * **步骤 1（构建邻接表）**
 * - **时间复杂度：O(N)**，遍历 `edges` 数组构建 `neighbors`。
 * - **空间复杂度：O(N)**，存储 `neighbors` 邻接表。
 *
 * **步骤 2（初始化叶子节点）**
 * - **时间复杂度：O(N)**，遍历所有 `n` 个节点，找到度数为 1 的叶子。
 * - **空间复杂度：O(N)**，存储 `leaves` 列表。
 *
 * **步骤 3（BFS 修剪叶子）**
 * - **时间复杂度：O(N)**，每个节点最多被访问 **1 次**，因此总访问次数是 `O(N)`。
 * - **空间复杂度：O(N)**，存储 `newLeaves` 和 `neighbors` 邻接表。
 *
 * **总复杂度**
 * - **时间复杂度：O(N)**（逐层修剪叶子，最多 `N` 轮）。
 * - **空间复杂度：O(N)**（存储邻接表和叶子列表）。
 */

public class LeetCode_310_MinimumHeightTrees {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {

            // 边缘情况处理：如果只有 1 个或 2 个节点，则它们本身就是 MHT（最小高度树）的根
            if (n < 2) {
                ArrayList<Integer> centroids = new ArrayList<>();
                for (int i = 0; i < n; i++) // 只有一个节点（n=1）或两个节点（n=2）时，所有节点都是根
                    centroids.add(i);
                return centroids;
            }

            // 使用邻接表构建无向图
            ArrayList<Set<Integer>> neighbors = new ArrayList<>();
            for (int i = 0; i < n; i++)
                neighbors.add(new HashSet<Integer>()); // 初始化邻接表，每个节点对应一个邻居集合

            // 遍历 edges 数组，填充邻接表
            for (int[] edge : edges) {
                Integer start = edge[0], end = edge[1];
                neighbors.get(start).add(end); // 记录 start 的邻居
                neighbors.get(end).add(start); // 记录 end 的邻居（无向图，双向连接）
            }

            // 初始化叶子节点列表（叶子节点：度为 1 的节点）
            ArrayList<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if (neighbors.get(i).size() == 1) // 度为 1 说明是叶子节点
                    leaves.add(i);

            // 逐步修剪叶子节点，直到剩下 1 或 2 个中心节点
            int remainingNodes = n;
            while (remainingNodes > 2) {
                remainingNodes -= leaves.size(); // 每一轮移除叶子节点，减少节点数
                ArrayList<Integer> newLeaves = new ArrayList<>();

                // 移除当前的叶子节点，并更新其邻居
                for (Integer leaf : leaves) {
                    Integer neighbor = neighbors.get(leaf).iterator().next(); // 获取叶子节点唯一的邻居
                    neighbors.get(neighbor).remove(leaf); // 删除叶子节点
                    if (neighbors.get(neighbor).size() == 1) // 如果邻居变成叶子节点，则加入下一轮处理
                        newLeaves.add(neighbor);
                }

                // 更新叶子节点列表，准备下一轮修剪
                leaves = newLeaves;
            }

            // 剩余的 1 或 2 个节点即为 MHT 的根
            return leaves;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_310_MinimumHeightTrees().new Solution();

        // 测试用例 1
        int n1 = 4;
        int[][] edges1 = {{1, 0}, {1, 2}, {1, 3}};
        System.out.println("Minimum Height Trees Roots: " + solution.findMinHeightTrees(n1, edges1)); // 预期输出：[1]

        // 测试用例 2
        int n2 = 6;
        int[][] edges2 = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println("Minimum Height Trees Roots: " + solution.findMinHeightTrees(n2, edges2)); // 预期输出：[3, 4]

        // 测试用例 3（特殊情况：只有一个节点）
        int n3 = 1;
        int[][] edges3 = {};
        System.out.println("Minimum Height Trees Roots: " + solution.findMinHeightTrees(n3, edges3)); // 预期输出：[0]

        // 测试用例 4（特殊情况：只有两个节点）
        int n4 = 2;
        int[][] edges4 = {{0, 1}};
        System.out.println("Minimum Height Trees Roots: " + solution.findMinHeightTrees(n4, edges4)); // 预期输出：[0, 1]
    }
}


/**
A tree is an undirected graph in which any two vertices are connected by 
exactly one path. In other words, any connected graph without simple cycles is a tree. 


 Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges 
where edges[i] = [ai, bi] indicates that there is an undirected edge between the 
two nodes ai and bi in the tree, you can choose any node of the tree as the 
root. When you select a node x as the root, the result tree has height h. Among all 
possible rooted trees, those with minimum height (i.e. min(h)) are called 
minimum height trees (MHTs). 

 Return a list of all MHTs' root labels. You can return the answer in any order.
 

 The height of a rooted tree is the number of edges on the longest downward 
path between the root and a leaf. 

 
 Example 1: 
 
 
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node 
with label 1 which is the only MHT.
 

 Example 2: 
 
 
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]
 

 
 Constraints: 

 
 1 <= n <= 2 * 10⁴ 
 edges.length == n - 1 
 0 <= ai, bi < n 
 ai != bi 
 All the pairs (ai, bi) are distinct. 
 The given input is guaranteed to be a tree and there will be no repeated edges.
 
 

 Related Topics Depth-First Search Breadth-First Search Graph Topological Sort ?
? 7305 👎 307

*/

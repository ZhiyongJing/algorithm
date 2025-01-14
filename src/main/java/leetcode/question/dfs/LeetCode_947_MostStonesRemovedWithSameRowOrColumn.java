package leetcode.question.dfs;
import java.util.*;

/**
 *@Question:  947. Most Stones Removed with Same Row or Column
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 71.67%
 *@Time  Complexity: O(N^2) for solution1, O(N) for solution2
 *@Space Complexity: O(N^2) for solution1, O(N) for solution2
 */
/**
 * 第一步：题目描述
 * 给定一个二维数组 `stones`，每个元素表示一个石头的坐标 `[x, y]`。两个石头如果在同一行或同一列，可以视为彼此相连。
 * 现在需要计算最多可以移除的石头数量，前提是至少留下一块石头在每个连通分量中。
 *
 * 示例：
 * 输入：[[0, 0], [0, 1], [1, 0], [1, 2], [2, 2], [2, 3]]
 * 输出：5
 * 解释：最多可以移除5块石头，例如保留 [2, 3]，移除其他石头。
 *
 * 输入：[[0, 0], [0, 2], [1, 1], [2, 0], [2, 2]]
 * 输出：3
 * 解释：最多可以移除3块石头，例如保留 [1, 1]，移除其他石头。
 *
 * 要求：
 * 1. 时间复杂度尽可能优化。
 * 2. 确保每个连通分量至少留下一块石头。
 */

/**
 * 第二步：解题思路
 * 1. 解法一：深度优先搜索 (DFS)
 *    - 我们将问题建模为一个图问题，其中每颗石头表示一个节点。
 *    - 如果两颗石头在同一行或同一列，则它们之间有一条边，表示它们属于同一个连通分量。
 *    - 使用邻接表存储图，之后用 DFS 遍历每个连通分量，统计总连通分量的数量。
 *    - 最多可以移除的石头数量为：总石头数 - 连通分量数。
 *    - 示例：
 *      输入：stones = [[0, 0], [0, 1], [1, 0], [1, 1], [2, 2]]
 *      构建的图：
 *      - 节点 0 (0,0) 和 1 (0,1) 相连
 *      - 节点 0 (0,0) 和 2 (1,0) 相连
 *      - 节点 1 (0,1) 和 3 (1,1) 相连
 *      连通分量数 = 2，最多可移除 5 - 2 = 3。
 *
 * 2. 解法二：并查集 (Union-Find)
 *    - 使用并查集对石头进行分组，如果两块石头在同一行或同一列，则它们属于同一个集合。
 *    - 每次合并操作会减少连通分量数量。
 *    - 特殊处理：为了避免同一行或同一列的坐标冲突，将列坐标偏移一个较大的常数（例如 10001）。
 *    - 最终的移除石头数量为：总石头数 - 并查集中的连通分量数。
 *    - 示例：
 *      输入：stones = [[0, 0], [0, 2], [1, 1], [2, 0], [2, 2]]
 *      合并步骤：
 *      - 合并 (0,0) 和 (0,2) -> 同一行
 *      - 合并 (1,1) -> 单独成一个分量
 *      - 合并 (2,0) 和 (2,2) -> 同一行
 *      连通分量数 = 3，最多可移除 5 - 3 = 2。
 */

/**
 * 第三步：时间和空间复杂度分析
 * 1. 深度优先搜索 (DFS)
 *    - 时间复杂度：O(N^2)，因为需要检查每对石头是否在同一行或同一列。
 *    - 空间复杂度：O(N^2)，用于存储邻接表。
 *
 * 2. 并查集 (Union-Find)
 *    - 时间复杂度：O(Nα(N))，其中 α(N) 是反阿克曼函数，接近常数。
 *    - 空间复杂度：O(N)，用于存储父节点数组和记录唯一节点。
 */


public class LeetCode_947_MostStonesRemovedWithSameRowOrColumn{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //Solution1: dfs
        public int removeStones(int[][] stones) {
            int n = stones.length;

            // 创建邻接表，用来存储石头之间的连接关系
            List<Integer>[] adjacencyList = new List[n];
            for (int i = 0; i < n; i++) {
                adjacencyList[i] = new ArrayList<>();
            }

            // 构建图：连接在同一行或同一列的石头
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    // 判断两颗石头是否在同一行或同一列
                    if (
                            stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]
                    ) {
                        adjacencyList[i].add(j);  // 将石头i和石头j连接
                        adjacencyList[j].add(i);  // 将石头j和石头i连接
                    }
                }
            }
            System.out.println(Arrays.toString(adjacencyList));

            int numOfConnectedComponents = 0; // 记录连通分量的数量
            boolean[] visited = new boolean[n]; // 记录每颗石头是否被访问过

            // 遍历所有的石头，使用DFS查找所有连通分量
            for (int i = 0; i < n; i++) {
                if (!visited[i]) { // 如果当前石头未被访问过
                    depthFirstSearch(adjacencyList, visited, i); // 进行深度优先搜索
                    numOfConnectedComponents++; // 连通分量数量加1
                }
            }

            // 最多可以移除的石头数量 = 总石头数 - 连通分量数
            return n - numOfConnectedComponents;
        }

        // 深度优先搜索，用于访问连通分量
        private void depthFirstSearch(
                List<Integer>[] adjacencyList,
                boolean[] visited,
                int stone
        ) {
            visited[stone] = true; // 将当前石头标记为已访问

            for (int neighbor : adjacencyList[stone]) {  // 遍历当前石头的所有邻居
                if (!visited[neighbor]) {  // 如果邻居未访问过
                    depthFirstSearch(adjacencyList, visited, neighbor);  // 递归访问邻居
                }
            }
        }

        //Solution2: union find
        public int removeStones2(int[][] stones) {
            int n = stones.length;
            UnionFind uf = new UnionFind(20002); // 初始化UnionFind数据结构，用于处理坐标，大小足够大以容纳所有的坐标

            // 合并在同一行或同一列的石头
            for (int i = 0; i < n; i++) {
                uf.union(stones[i][0], stones[i][1] + 10001); // 将y坐标加10001，以避免与x坐标冲突
            }

            // 返回总石头数减去连通分量数，即为可以移除的最大石头数
            return n - uf.componentCount;
        }

        // Union-Find 数据结构，用于跟踪连通分量
        class UnionFind {

            int[] parent; // 用来跟踪每个节点的父节点
            int componentCount; // 连通分量的数量
            Set<Integer> uniqueNodes; // 用来跟踪独特的节点

            UnionFind(int n) {
                parent = new int[n];
                Arrays.fill(parent, -1); // 初始化所有节点的父节点为-1，表示节点是自己的父节点
                componentCount = 0;
                uniqueNodes = new HashSet<>();
            }

            // 查找节点的根节点，带路径压缩
            int find(int node) {
                // 如果节点没有标记，增加连通分量的数量
                if (!uniqueNodes.contains(node)) {
                    componentCount++;
                    uniqueNodes.add(node);
                }

                // 路径压缩
                if (parent[node] == -1) {
                    return node;
                }
                return parent[node] = find(parent[node]);
            }

            // 合并两个节点，减少连通分量的数量
            void union(int node1, int node2) {
                int root1 = find(node1);
                int root2 = find(node2);

                if (root1 == root2) {
                    return;  // 如果两个节点已经在同一个连通分量中，直接返回
                }

                // 合并两个连通分量
                parent[root1] = root2;
                componentCount--; // 连通分量数量减1
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_947_MostStonesRemovedWithSameRowOrColumn().new Solution();

        // 创建测试样例
        int[][] stones = {
                {0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 2}
        };

        // 计算最多可以移除的石头数
        int result = solution.removeStones(stones);
        System.out.println("Maximum stones removed: " + result);  // 输出结果
    }
}

/**
On a 2D plane, we place n stones at some integer coordinate points. Each 
coordinate point may have at most one stone. 

 A stone can be removed if it shares either the same row or the same column as 
another stone that has not been removed. 

 Given an array stones of length n where stones[i] = [xi, yi] represents the 
location of the iᵗʰ stone, return the largest possible number of stones that can 
be removed. 

 
 Example 1: 

 
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another 
stone still on the plane.
 

 Example 2: 

 
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column 
with another stone still on the plane.
 

 Example 3: 

 
Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 

 
 Constraints: 

 
 1 <= stones.length <= 1000 
 0 <= xi, yi <= 10⁴ 
 No two stones are at the same coordinate point. 
 

 Related Topics Hash Table Depth-First Search Union Find Graph 👍 5972 👎 690

*/
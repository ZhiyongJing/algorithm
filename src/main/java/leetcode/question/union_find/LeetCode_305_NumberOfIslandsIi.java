package leetcode.question.union_find;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  305. Number of Islands II
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 33.64%
 *@Time  Complexity: O(M * N + L)  m and n are the number of rows and columns in the given grid, and l is the size of positions.
 *@Space Complexity: O(M * N)
 */

/**
 * 题目要求我们在一个二维网格上模拟陆地的连通情况，并随着陆地的添加动态更新岛屿的数量。我们可以使用并查集（Union-Find）这一数据结构来解决这个问题。
 *
 * ### 解题思路
 *
 * 1. **并查集数据结构选择**：
 *    - 我们使用并查集来管理岛屿的连通性。每个陆地单元格都可以看作并查集的一个节点，而它们的连接关系可以看作是并查集中的边。
 *
 * 2. **初始化**：
 *    - 首先创建一个大小为 `m * n` 的并查集，其中 `m` 是网格的行数，`n` 是网格的列数。
 *    - 创建一个计数器 `count` 用于记录当前岛屿的数量。
 *
 * 3. **处理每个新增的陆地**：
 *    - 对于每个新增的陆地单元格，将其加入并查集中，并将 `count` 岛屿数量加一。
 *    - 然后检查其四个方向（上、下、左、右）是否有相邻的陆地：
 *      - 如果有相邻的陆地，即相邻单元格也是陆地且与当前单元格连通，则将它们合并为一个集合。
 *
 * 4. **计算岛屿数量变化**：
 *    - 每次将新的陆地加入并查集后，记录当前的岛屿数量。
 *    - 每当发现新的相邻陆地合并时，更新并查集，同时岛屿数量减一。
 *
 * 5. **返回结果**：
 *    - 每次处理完一个新增的陆地后，将当前的岛屿数量存入结果列表中。
 *
 * ### 时间复杂度
 * - 每次处理新增的陆地时，最坏情况下需要检查其四个方向上的相邻单元格，因此对于每个新增的陆地，时间复杂度为 `O(1)`。总体时间复杂度取决于处理所有单元格的时间复杂度，即 `O(k)`，其中 `k` 是新增陆地的数量。
 *
 * ### 空间复杂度
 * - 空间复杂度主要取决于并查集的存储空间，以及额外的空间用于存储结果列表和一些临时变量。因此空间复杂度为 `O(m * n)`，其中 `m` 是网格的行数，`n` 是网格的列数。
 *
 * 这种方法通过动态地处理每个新增的陆地，并利用并查集高效地管理和更新岛屿的连通性，是一种高效的解决方案。
 */
public class LeetCode_305_NumberOfIslandsIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class UnionFind {
        int[] parent; // 用于存储每个节点的父节点
        int[] rank; // 用于存储每个集合的秩（树的高度）
        int count; // 记录当前集合的数量

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = -1; // 初始化每个节点的父节点为-1，表示各自独立成集合
            count = 0; // 初始时集合数量为0
        }

        public void addLand(int x) {
            if (parent[x] >= 0) // 如果节点已经有了父节点，说明已经是岛屿，直接返回
                return;
            parent[x] = x; // 将节点设为自己的父节点，表示成立新的岛屿
            count++; // 增加岛屿数量
        }

        public boolean isLand(int x) {
            if (parent[x] >= 0) { // 如果节点有父节点，则说明属于某个岛屿
                return true;
            } else {
                return false; // 否则不属于任何岛屿
            }
        }

        int numberOfIslands() {
            return count; // 返回当前岛屿的数量
        }

        public int find(int x) {
            if (parent[x] != x) // 如果节点的父节点不是自己，递归找到根节点
                parent[x] = find(parent[x]); // 路径压缩，将节点直接连接到根节点，加快查找速度
            return parent[x]; // 返回根节点
        }

        public void union(int x, int y) {
            int xset = find(x), yset = find(y); // 找到x和y的根节点
            if (xset == yset) { // 如果x和y已经在同一个集合中
                return; // 直接返回，无需合并
            } else if (rank[xset] < rank[yset]) { // 如果x的秩（树的高度）小于y的秩
                parent[xset] = yset; // 将x连接到y上
            } else if (rank[xset] > rank[yset]) { // 如果x的秩大于y的秩
                parent[yset] = xset; // 将y连接到x上
            } else { // 如果x和y的秩相等
                parent[yset] = xset; // 将y连接到x上
                rank[xset]++; // 此时x的高度需要增加
            }
            count--; // 合并后岛屿数量减少
        }
    }

    class Solution {
        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            int x[] = { -1, 1, 0, 0 }; // 方向数组，用于表示上、下、左、右四个方向
            int y[] = { 0, 0, -1, 1 };
            UnionFind dsu = new UnionFind(m * n); // 初始化并查集，大小为m*n
            List<Integer> answer = new ArrayList<>(); // 存储每次加入新节点后的岛屿数量

            for (int[] position : positions) { // 遍历每个加入的节点位置
                int landPosition = position[0] * n + position[1]; // 计算当前节点在并查集中的位置
                dsu.addLand(landPosition); // 将当前节点设为岛屿

                for (int i = 0; i < 4; i++) { // 遍历当前节点的四个相邻方向
                    int neighborX = position[0] + x[i]; // 计算相邻节点的行坐标
                    int neighborY = position[1] + y[i]; // 计算相邻节点的列坐标
                    int neighborPosition = neighborX * n + neighborY; // 计算相邻节点在并查集中的位置
                    // 如果相邻节点在有效范围内且为岛屿，则将当前节点与相邻节点合并
                    if (neighborX >= 0 && neighborX < m && neighborY >= 0 && neighborY < n &&
                            dsu.isLand(neighborPosition)) {
                        dsu.union(landPosition, neighborPosition); // 合并操作
                    }
                }
                answer.add(dsu.numberOfIslands()); // 将当前岛屿数量加入结果列表
            }
            return answer; // 返回结果列表
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_305_NumberOfIslandsIi().new Solution();
        // 测试样例
        int m = 3, n = 3;
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
        List<Integer> result = solution.numIslands2(m, n, positions);
        System.out.println(result); // 预期输出：[1, 1, 2, 3]
    }
}

/**
You are given an empty 2D binary grid grid of size m x n. The grid represents a 
map where 0's represent water and 1's represent land. Initially, all the cells 
of grid are water cells (i.e., all the cells are 0's). 

 We may perform an add land operation which turns the water at position into a 
land. You are given an array positions where positions[i] = [ri, ci] is the 
position (ri, ci) at which we should operate the iᵗʰ operation. 

 Return an array of integers answer where answer[i] is the number of islands 
after turning the cell (ri, ci) into a land. 

 An island is surrounded by water and is formed by connecting adjacent lands 
horizontally or vertically. You may assume all four edges of the grid are all 
surrounded by water. 

 
 Example 1: 
 
 
Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
Output: [1,1,2,3]
Explanation:
Initially, the 2d grid is filled with water.
- Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We 
have 1 island.
- Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We 
still have 1 island.
- Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We 
have 2 islands.
- Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We 
have 3 islands.
 

 Example 2: 

 
Input: m = 1, n = 1, positions = [[0,0]]
Output: [1]
 

 
 Constraints: 

 
 1 <= m, n, positions.length <= 10⁴ 
 1 <= m * n <= 10⁴ 
 positions[i].length == 2 
 0 <= ri < m 
 0 <= ci < n 
 

 
 Follow up: Could you solve it in time complexity O(k log(mn)), where k == 
positions.length? 

 Related Topics Array Hash Table Union Find 👍 1856 👎 72

*/
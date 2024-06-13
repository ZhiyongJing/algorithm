package leetcode.question.bfs;

import java.util.LinkedList;
import java.util.Queue;
/**
 *@Question:  317. Shortest Distance from All Buildings
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.08%
 *@Time  Complexity: O(N^2 * M^2) Let N and let M be the number of rows and columns in grid respectively.
 *@Space Complexity: O(N * M)
 */

/**
 * 这段代码的目标是找到从所有建筑物到达某一点的最短距离。让我们分析一下解题思路：
 *
 * 1. **最短距离计算**：对于每个空的单元格，我们使用广度优先搜索（BFS）来找到从该单元格到达所有房子的最短距离之和。这样我们就可以找到所有空单元格中的最小距离。
 *
 * 2. **BFS搜索**：我们从每个空单元格开始，依次向四个方向扩展。对于每个单元格，我们检查其四周是否有房子，如果有，则计算到达该房子的距离，并将其累加到总距离中。我们继续这个过程，直到到达所有房子或者无法到达。
 *
 * 3. **优化处理**：如果有空单元格无法到达所有房子，我们将这些无法到达的单元格标记为2，表示无法到达。这样下次我们搜索时就可以跳过这些单元格。
 *
 * 4. **最小总距离**：我们对所有空单元格计算出的距离进行比较，找到最小的总距离。如果无法从任何一个空单元格到达所有房子，则返回-1。
 *
 * 接下来，让我们分析一下时间复杂度和空间复杂度：
 *
 * - **时间复杂度**：假设网格大小为N x M，对于每个空单元格，我们执行BFS搜索。BFS的时间复杂度为O(N * M)，因此总时间复杂度为O(N^2 * M^2)。
 *
 * - **空间复杂度**：我们使用了一个队列和一个布尔型二维数组来存储访问过的单元格，它们的空间复杂度为O(N * M)。因此总空间复杂度为O(N * M)。
 *
 * 综上所述，该算法的时间复杂度为O(N^2 * M^2)，空间复杂度为O(N * M)。
 */

public class LeetCode_317_ShortestDistanceFromAllBuildings{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义一个内部类Solution，用于实现解决方案
        private int bfs(int[][] grid, int row, int col, int totalHouses) {
            // 定义四个方向
            int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            int rows = grid.length;
            int cols = grid[0].length;
            int distanceSum = 0;
            int housesReached = 0;

            // 使用队列进行广度优先搜索，从(row, col)开始
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{ row, col });

            // 记录已访问的单元格
            boolean[][] vis = new boolean[rows][cols];
            vis[row][col] = true;

            int steps = 0;
            while (!q.isEmpty() && housesReached != totalHouses) {
                for (int i = q.size(); i > 0; --i) {
                    int[] curr = q.poll();
                    row = curr[0];
                    col = curr[1];

                    // 如果这个单元格是房子，则添加从源到该单元格的距离，并继续遍历
                    if (grid[row][col] == 1) {
                        distanceSum += steps;
                        housesReached++;
                        continue;
                    }

                    // 这个单元格是空的，继续遍历下一个不是阻塞的单元格
                    for (int[] dir : dirs) {
                        int nextRow = row + dir[0];
                        int nextCol = col + dir[1];
                        if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                            if (!vis[nextRow][nextCol] && grid[nextRow][nextCol] != 2) {
                                vis[nextRow][nextCol] = true;
                                q.offer(new int[]{ nextRow, nextCol });
                            }
                        }
                    }
                }
                steps++;
            }

            // 如果没有到达所有房子，则返回最大值
            if (housesReached != totalHouses) {
                for (row = 0; row < rows; row++) {
                    for (col = 0; col < cols; col++) {
                        if (grid[row][col] == 0 && vis[row][col]) {
                            grid[row][col] = 2;
                        }
                    }
                }
                return Integer.MAX_VALUE;
            }

            // 如果到达了所有房子，则返回计算出的总距离
            return distanceSum;
        }

        // 计算最短距离
        public int shortestDistance(int[][] grid) {
            int minDistance = Integer.MAX_VALUE;
            int rows = grid.length;
            int cols = grid[0].length;
            int totalHouses = 0;

            // 计算房子的总数
            for (int row = 0; row < rows; ++row) {
                for (int col = 0; col < cols; ++col) {
                    if (grid[row][col] == 1) {
                        totalHouses++;
                    }
                }
            }

            // 对于每个空单元格，找到最小距离和
            for (int row = 0; row < rows; ++row) {
                for (int col = 0; col < cols; ++col) {
                    if (grid[row][col] == 0) {
                        minDistance = Math.min(minDistance, bfs(grid, row, col, totalHouses));
                    }
                }
            }

            // 如果无法从任何空单元格到达所有房子，则返回-1
            if (minDistance == Integer.MAX_VALUE) {
                return -1;
            }

            return minDistance;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_317_ShortestDistanceFromAllBuildings().new Solution();
        // 测试代码
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(solution.shortestDistance(grid)); // 应该输出 7
    }
}

/**
 You are given an m x n grid grid of values 0, 1, or 2, where:


 each 0 marks an empty land that you can pass by freely,
 each 1 marks a building that you cannot pass through, and
 each 2 marks an obstacle that you cannot pass through.


 You want to build a house on an empty land that reaches all buildings in the
 shortest total travel distance. You can only move up, down, left, and right.

 Return the shortest travel distance for such a house. If it is not possible to
 build such a house according to the above rules, return -1.

 The total travel distance is the sum of the distances between the houses of
 the friends and the meeting point.

 The distance is calculated using Manhattan Distance, where distance(p1, p2) = |
 p2.x - p1.x| + |p2.y - p1.y|.


 Example 1:


 Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 Output: 7
 Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0
 ,2).
 The point (1,2) is an ideal empty land to build a house, as the total travel
 distance of 3+3+1=7 is minimal.
 So return 7.


 Example 2:


 Input: grid = [[1,0]]
 Output: 1


 Example 3:


 Input: grid = [[1]]
 Output: -1



 Constraints:


 m == grid.length
 n == grid[i].length
 1 <= m, n <= 50
 grid[i][j] is either 0, 1, or 2.
 There will be at least one building in the grid.


 Related Topics Array Breadth-First Search Matrix 👍 1857 👎 291

 */
package leetcode.question.bfs;

/**
  *@Question:  1091. Shortest Path in Binary Matrix
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 61.43%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * **算法思路详解：**
 *
 * 题目要求在二进制矩阵中寻找从左上角到右下角的最短路径。矩阵中的 `0` 表示可通行的位置，`1` 表示障碍物，无法通行。
 * 路径必须经过可通行的位置，同时满足路径中相邻的两个单元格是八个方向（上、下、左、右以及对角线）相邻的。
 *
 * 这里使用广度优先搜索（BFS）算法来解决问题。BFS 可以在图或矩阵中找到最短路径。具体步骤如下：
 *
 * 1. 首先，检查起始和目标单元格是否为可通行的，如果不可通行则返回 -1。
 *
 * 2. 初始化 BFS 队列，将起始单元格加入队列，并标记为已访问（同时记录路径长度）。
 *
 * 3. 在队列不为空的情况下，循环执行以下步骤：
 *    - 弹出队列中的单元格，并获取其相邻的可通行单元格。
 *    - 将相邻的可通行单元格加入队列，同时更新它们的路径长度，并标记为已访问。
 *    - 如果弹出的单元格是目标单元格，则返回路径长度。
 *
 * 4. 如果队列为空且未找到目标单元格，说明目标单元格不可达，返回 -1。
 *
 * **时间复杂度：**
 *
 * BFS 的时间复杂度取决于矩阵的大小，最坏情况下需要遍历整个矩阵。设矩阵大小为 N，因此时间复杂度为 O(N)。
 *
 * **空间复杂度：**
 *
 * BFS 使用队列来存储待访问的单元格，因此空间复杂度取决于队列的大小。最坏情况下，所有可通行的单元格都会加入队列，空间复杂度为 O(N)。
 */
public class LeetCode_1091_ShortestPathInBinaryMatrix {

    // 定义八个方向的偏移量，用于获取相邻的单元格
    private final int[][] directions =
            new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    // Solution 类定义在 Solution 类内部，方便访问外部类的属性和方法
    class Solution {

        // 主函数，计算最短路径
        public int shortestPathBinaryMatrix(int[][] grid) {

            // 首先，检查起始和目标单元格是否为可通行的
            if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
                return -1;
            }

            // 设置 BFS 队列
            Queue<int[]> queue = new ArrayDeque<>();
            grid[0][0] = 1; // 表示该单元格已经被访问，同时用于记录路径长度
            queue.add(new int[]{0, 0});

            // 执行 BFS
            while (!queue.isEmpty()) {
                int[] cell = queue.remove();
                int row = cell[0];
                int col = cell[1];
                int distance = grid[row][col];

                // 到达目标单元格时返回路径长度
                if (row == grid.length - 1 && col == grid[0].length - 1) {
                    return distance;
                }

                // 获取相邻的单元格
                for (int[] neighbour : getNeighbours(row, col, grid)) {
                    int neighbourRow = neighbour[0];
                    int neighbourCol = neighbour[1];
                    queue.add(new int[]{neighbourRow, neighbourCol});
                    grid[neighbourRow][neighbourCol] = distance + 1; // 更新路径长度
                }
            }

            // 目标单元格不可达
            return -1;
        }

        // 获取相邻的单元格列表
        private List<int[]> getNeighbours(int row, int col, int[][] grid) {
            List<int[]> neighbours = new ArrayList<>();
            for (int i = 0; i < directions.length; i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                if (newRow < 0 || newCol < 0 || newRow >= grid.length
                        || newCol >= grid[0].length
                        || grid[newRow][newCol] != 0) {
                    continue;
                }
                neighbours.add(new int[]{newRow, newCol});
            }
            return neighbours;
        }
    }

    // 测试函数
    public static void main(String[] args) {
        LeetCode_1091_ShortestPathInBinaryMatrix outer = new LeetCode_1091_ShortestPathInBinaryMatrix();
        Solution solution = outer.new Solution();

        // 测试用例
        int[][] grid1 = {{0, 1}, {1, 0}};
        System.out.println(solution.shortestPathBinaryMatrix(grid1)); // 输出：2

        int[][] grid2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(solution.shortestPathBinaryMatrix(grid2)); // 输出：4

        int[][] grid3 = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(solution.shortestPathBinaryMatrix(grid3)); // 输出：-1
    }
}

/**
Given an n x n binary matrix grid, return the length of the shortest clear path 
in the matrix. If there is no clear path, return -1. 

 A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0))
 to the bottom-right cell (i.e., (n - 1, n - 1)) such that: 

 
 All the visited cells of the path are 0. 
 All the adjacent cells of the path are 8-directionally connected (i.e., they 
are different and they share an edge or a corner). 
 

 The length of a clear path is the number of visited cells of this path. 

 
 Example 1: 
 
 
Input: grid = [[0,1],[1,0]]
Output: 2
 

 Example 2: 
 
 
Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
 

 Example 3: 

 
Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
 

 
 Constraints: 

 
 n == grid.length 
 n == grid[i].length 
 1 <= n <= 100 
 grid[i][j] is 0 or 1 
 

 Related Topics Array Breadth-First Search Matrix 👍 6183 👎 216

*/

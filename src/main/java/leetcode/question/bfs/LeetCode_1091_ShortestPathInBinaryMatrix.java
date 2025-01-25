package leetcode.question.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
  *@Question:  1091. Shortest Path in Binary Matrix
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 76.99%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */
/*
    题目描述：
    给定一个二维网格 `grid`，其中每个单元格的值为 0 或 1。我们从左上角（0,0）开始，目标是到达右下角（n-1,m-1），
    并且只能穿越值为 0 的单元格。每一步，我们可以从当前单元格向八个方向中的任何一个相邻的单元格移动。
    求最短路径的长度。

    详细解题思路：

    1. **检查起始和终点是否可达：**
       - 首先需要检查起点（0,0）和终点（n-1,m-1）是否都可以访问（即值为0）。如果任何一个是1，则说明起点或终点不可达，直接返回 -1。
       - 例如，给定 `grid = {{1, 0}, {0, 0}}`，起点（0,0）是 1，表示不可达，返回 -1。

    2. **使用广度优先搜索（BFS）进行遍历：**
       - 从起点（0,0）开始，进行广度优先搜索，逐层遍历每个相邻的单元格。
       - BFS 是最常用于找到最短路径的算法，因为它会层层扩展，每次扩展都会是当前最短的路径。
       - BFS 的关键是队列，我们需要使用队列存储当前需要访问的单元格的位置。
       - 在 BFS 中，每次从队列中取出一个单元格，并遍历它的八个相邻单元格。如果相邻单元格值为 0，则表示可以访问，
       将其加入队列，并更新该单元格的路径长度（即当前单元格的路径长度加1）。
       - 如果访问到终点（n-1,m-1），立即返回当前路径长度。
       - 例如，给定 `grid = {{0, 1}, {1, 0}}`，从起点（0,0）出发，经过两步可到达终点（1,1），返回路径长度2。

    3. **获取相邻的可访问单元格：**
       - 对于当前单元格（行、列），检查它的八个方向的相邻单元格，确保这些相邻单元格在网格内且值为 0（可访问）。
       - 如果相邻单元格符合条件，添加到队列中以供下一步访问。
       - 例如，对于 `grid = {{0, 0}, {1, 0}}`，从起点（0,0）出发，检查它的相邻单元格（0,1）和（1,0）。只有（0,1）是可达的，添加到队列中。

    4. **目标不可达：**
       - 如果在 BFS 遍历结束后，队列为空且仍未到达终点，说明终点不可达，返回 -1。
       - 例如，给定 `grid = {{1, 1}, {1, 1}}`，起点和终点都不可达，返回 -1。

    时间复杂度：
    - 时间复杂度为 O(N)，其中 N 为网格中的单元格数量。每个单元格最多被访问一次，因此总时间复杂度为 O(N)。

    空间复杂度：
    - 空间复杂度为 O(N)，其中 N 为网格中的单元格数量。最坏情况下，队列可能会存储所有单元格，导致空间复杂度为 O(N)。
*/

public class LeetCode_1091_ShortestPathInBinaryMatrix{

//leetcode submit region begin(Prohibit modification and deletion)


    // Solution 类定义在 Solution 类内部，方便访问外部类的属性和方法
    class Solution {
        // 定义八个方向的偏移量，用于获取相邻的单元格
        private final int[][] directions =
                new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        // 主函数，计算最短路径
        public int shortestPathBinaryMatrix(int[][] grid) {

            // 首先，检查起始和目标单元格是否为可通行的
            if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
                return -1;
            }

            // 设置 BFS 队列
            Queue<int[]> queue = new LinkedList<>();
            grid[0][0] = 1; // 表示该单元格已经被访问，同时用于记录路径长度
            queue.add(new int[]{0, 0});

            // 执行 BFS
            while (!queue.isEmpty()) {
                int[] cell = queue.poll(); // 从队列中取出当前单元格
                int row = cell[0];           // 获取当前单元格的行坐标
                int col = cell[1];           // 获取当前单元格的列坐标
                int distance = grid[row][col]; // 获取当前单元格的路径长度（即访问深度）

                // 到达目标单元格时返回路径长度
                if (row == grid.length - 1 && col == grid[0].length - 1) {
                    return distance;
                }

                // 获取相邻的单元格
                for (int[] neighbour : getNeighbours(row, col, grid)) {
                    int neighbourRow = neighbour[0]; // 获取相邻单元格的行坐标
                    int neighbourCol = neighbour[1]; // 获取相邻单元格的列坐标
                    queue.offer(new int[]{neighbourRow, neighbourCol}); // 将相邻单元格加入队列
                    grid[neighbourRow][neighbourCol] = distance + 1; // 更新相邻单元格的路径长度
                }
            }

            // 目标单元格不可达
            return -1;
        }

        // 获取相邻的单元格列表
        private List<int[]> getNeighbours(int row, int col, int[][] grid) {
            List<int[]> neighbours = new ArrayList<>();
            for (int i = 0; i < directions.length; i++) { // 遍历八个方向
                int newRow = row + directions[i][0]; // 计算新的行坐标
                int newCol = col + directions[i][1]; // 计算新的列坐标
                // 如果新的坐标越界或相邻单元格不可通行（值不为 0），则跳过
                if (newRow < 0 || newCol < 0 || newRow >= grid.length
                        || newCol >= grid[0].length
                        || grid[newRow][newCol] != 0) {
                    continue;
                }
                neighbours.add(new int[]{newRow, newCol}); // 将相邻单元格加入列表
            }
            return neighbours; // 返回相邻单元格列表
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1091_ShortestPathInBinaryMatrix().new Solution();
        // TO TEST
        //solution.
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
 

 Related Topics Array Breadth-First Search Matrix 👍 6774 👎 253

*/
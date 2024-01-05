package leetcode.frequent.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
  *@Question:  200. Number of Islands     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 94.34%      
  *@Time  Complexity: O(M * N) where M is the number of rows and N is the number of columns -BFS
  *@Space Complexity: O(min(M, N)) - BFS
 */

/**
 * ### 算法思路：
 *
 * 这道题目是经典的岛屿数量问题，主要通过深度优先搜索（DFS）和广度优先搜索（BFS）两种方法来解决。
 *
 * #### 1. DFS 方法：
 *
 * - 使用深度优先搜索遍历二维网格。
 * - 对于每个为 '1' 的陆地，递归地将与之相邻的所有陆地标记为 '0'，表示已访问。
 * - 遍历整个网格，每次发现一个未访问的陆地（即为 '1'），就将岛屿数量加一。
 * - 最终返回岛屿数量。
 *
 * #### 2. BFS 方法：
 *
 * - 使用广度优先搜索遍历二维网格。
 * - 对于每个为 '1' 的陆地，将其标记为 '0'，表示已访问，并将其加入队列。
 * - 使用队列遍历当前陆地的相邻陆地，将相邻陆地标记为 '0'，并加入队列。
 * - 继续出队列，重复上述步骤，直到队列为空。
 * - 遍历整个网格，每次发现一个未访问的陆地（即为 '1'），就将岛屿数量加一。
 * - 最终返回岛屿数量。
 *
 * ### 复杂度分析：
 *
 * #### 1. DFS 方法：
 *
 * - 时间复杂度：O(M * N)，其中 M 是网格的行数，N 是网格的列数。每个节点至多访问一次。
 * - 空间复杂度：O(M* N)，递归调用的最大深度。
 *
 * #### 2. BFS 方法：
 *
 * - 时间复杂度：O(M * N)，每个节点至多被访问一次。
 * - 空间复杂度：O(min(M, N))，队列中最多存储 min(M, N) 个元素。
 */

public class LeetCode_200_NumberOfIslands {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * DFS 遍历岛屿，将遍历过的陆地标记为 '0'
         *
         * @param grid 二维网格
         * @param r    当前行
         * @param c    当前列
         */
        void dfs(char[][] grid, int r, int c) {
            int nr = grid.length;
            int nc = grid[0].length;

            if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
                return;
            }

            grid[r][c] = '0'; // 标记为已访问
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }

        /**
         * 计算岛屿数量，使用 DFS 遍历
         *
         * @param grid 二维网格
         * @return 岛屿数量
         */
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;
            int numIslands = 0;

            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        ++numIslands;
                        dfs(grid, r, c);
                    }
                }
            }

            return numIslands;
        }

        /**
         * 计算岛屿数量，使用 BFS 遍历
         *
         * @param grid 二维网格
         * @return 岛屿数量
         */
        public int numIslands2(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;
            int numIslands = 0;

            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        ++numIslands;
                        grid[r][c] = '0'; // 标记为已访问
                        Queue<Integer> neighbors = new LinkedList<>();
                        neighbors.add(r * nc + c);
                        while (!neighbors.isEmpty()) {
                            int id = neighbors.remove();
                            int row = id / nc;
                            int col = id % nc;
                            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                                neighbors.add((row - 1) * nc + col);
                                grid[row - 1][col] = '0';
                            }
                            if (row + 1 < nr && grid[row + 1][col] == '1') {
                                neighbors.add((row + 1) * nc + col);
                                grid[row + 1][col] = '0';
                            }
                            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                                neighbors.add(row * nc + col - 1);
                                grid[row][col - 1] = '0';
                            }
                            if (col + 1 < nc && grid[row][col + 1] == '1') {
                                neighbors.add(row * nc + col + 1);
                                grid[row][col + 1] = '0';
                            }
                        }
                    }
                }
            }

            return numIslands;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_200_NumberOfIslands().new Solution();

        // Test Case 1
        char[][] grid1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println("Test Case 1: " + solution.numIslands(grid1)); // Output: 1

        // Test Case 2
        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println("Test Case 2: " + solution.numIslands2(grid2)); // Output: 3
    }
}

/**
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0
's (water), return the number of islands. 

 An island is surrounded by water and is formed by connecting adjacent lands 
horizontally or vertically. You may assume all four edges of the grid are all 
surrounded by water. 

 
 Example 1: 

 
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
 

 Example 2: 

 
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 300 
 grid[i][j] is '0' or '1'. 
 

 Related Topics Array Depth-First Search Breadth-First Search Union Find Matrix 
👍 21651 👎 471

*/

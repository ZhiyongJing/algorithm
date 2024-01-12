package leetcode.question.dfs;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
  *@Question:  694. Number of Distinct Islands     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 45.08%      
  *@Time  Complexity: O(M*N) M is number of rows and N is number of columns
  *@Space Complexity: O(M*N)
 */

/**
 * **算法思路：**
 *
 * 1. 遍历整个二维矩阵，对于每个未访问的陆地格子，以该格子为起点进行深度优先搜索（DFS）。
 * 2. 在DFS中，记录每个岛屿中陆地格子的相对坐标（相对于岛屿的起点），将这些相对坐标存储为一个集合。
 * 3. 使用哈希集合存储所有不同的岛屿，每个集合代表一个岛屿的形状，不同形状的岛屿将存储为不同的集合。
 * 4. 最终，哈希集合的大小即为不同岛屿的数量。
 *
 * **时间复杂度：**
 *
 * - 遍历整个矩阵需要 O(m * n) 的时间，其中 m 和 n 分别为矩阵的行数和列数。
 * - 在DFS中，每个格子最多被访问一次，因此 DFS 的时间复杂度为 O(m * n)。
 *
 * 综合起来，总体时间复杂度为 O(m * n)。
 *
 * **空间复杂度：**
 *
 * - 使用了哈希集合来存储不同岛屿的形状，因此空间复杂度取决于不同岛屿的数量。
 * - 在最坏情况下，所有的陆地都是一个独立的岛屿，哈希集合的大小为 O(m * n)。
 * - 除哈希集合外，递归调用的栈深度为 O(min(m, n))，因为每个格子最多被访问一次。
 * - 综合起来，总体空间复杂度为 O(m * n)。
 */

public class LeetCode_694_NumberOfDistinctIslands {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int[][] grid;
        private boolean[][] seen;
        private Set<Pair<Integer, Integer>> currentIsland;
        private int currRowOrigin;
        private int currColOrigin;

        // 深度优先搜索遍历岛屿
        private void dfs(int row, int col) {
            // 边界检查
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
            // 如果当前位置是水域或已经访问过，直接返回
            if (grid[row][col] == 0 || seen[row][col]) return;
            seen[row][col] = true;
            // 将当前位置加入当前岛屿的相对坐标集合中
            currentIsland.add(new Pair<>(row - currRowOrigin, col - currColOrigin));
            // 继续遍历四个方向
            dfs(row + 1, col);
            dfs(row - 1, col);
            dfs(row, col + 1);
            dfs(row, col - 1);
        }

        // 计算不同岛屿的数量
        public int numDistinctIslands(int[][] grid) {
            this.grid = grid;
            this.seen = new boolean[grid.length][grid[0].length];
            Set<Set<Pair<Integer, Integer>>> islands = new HashSet<>();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    this.currentIsland = new HashSet<>();
                    this.currRowOrigin = row;
                    this.currColOrigin = col;
                    dfs(row, col);
                    // 如果当前岛屿非空，加入岛屿集合
                    if (!currentIsland.isEmpty()) islands.add(currentIsland);
                }
            }
            System.out.println(islands);
            // 返回岛屿集合的大小即不同岛屿的数量
            return islands.size();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_694_NumberOfDistinctIslands().new Solution();
        // 测试样例1
        int[][] grid1 = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        System.out.println(solution.numDistinctIslands(grid1)); // 预期输出: 1

        // 测试样例2
        int[][] grid2 = {{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}};
        System.out.println(solution.numDistinctIslands(grid2)); // 预期输出: 3
    }
}

/**
You are given an m x n binary matrix grid. An island is a group of 1's (
representing land) connected 4-directionally (horizontal or vertical.) You may assume 
all four edges of the grid are surrounded by water. 

 An island is considered to be the same as another if and only if one island 
can be translated (and not rotated or reflected) to equal the other. 

 Return the number of distinct islands. 

 
 Example 1: 
 
 
Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
Output: 1
 

 Example 2: 
 
 
Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
Output: 3
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 50 
 grid[i][j] is either 0 or 1. 
 

 Related Topics Hash Table Depth-First Search Breadth-First Search Union Find 
Hash Function 👍 2168 👎 135

*/

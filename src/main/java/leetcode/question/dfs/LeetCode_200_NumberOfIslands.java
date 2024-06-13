package leetcode.question.dfs;

/**
 *@Question:  200. Number of Islands
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 97.34%
 *@Time  Complexity: O(M*N)
 *@Space Complexity: O(M*N)
 */

public class LeetCode_200_NumberOfIslands{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        void dfs(char[][] grid, int r, int c) {
            int nr = grid.length;
            int nc = grid[0].length;

            if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
                return;
            }

            grid[r][c] = '0';
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;
            int num_islands = 0;
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        ++num_islands;
                        dfs(grid, r, c);
                    }
                }
            }

            return num_islands;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_200_NumberOfIslands.Solution solution = new LeetCode_200_NumberOfIslands().new Solution();
        // TO TEST
        //solution.
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
 👍 22571 👎 509

 */
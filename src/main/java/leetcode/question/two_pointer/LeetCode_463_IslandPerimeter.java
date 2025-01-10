package leetcode.question.two_pointer;

/**
 *@Question:  463. Island Perimeter
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.26%
 *@Time  Complexity: O(M * N)
 *@Space Complexity: O(1)
 */
/**
 * 题目描述：
 * 给定一个二维网格，其中值为 1 的格子表示陆地，值为 0 的格子表示水域。岛屿是由陆地组成的连通区域，
 * 该题目要求计算岛屿的周长。岛屿的周长定义为与水域接触的陆地的边界总长度。
 * 我们需要计算并返回该岛屿的周长。
 *
 * 解题思路：
 * 1. 遍历网格中的每个格子，若格子为陆地（值为1），初始认为该格子贡献了 4 条边的周长。
 * 2. 对于每个陆地格子，检查它的上方和左侧：
 *    - 如果上方的格子也是陆地，那么该上边界不算周长，应减少 2 单位的周长。
 *    - 如果左侧的格子也是陆地，那么该左边界不算周长，应减少 2 单位的周长。
 *    这些共享边界的部分不应重复计算。
 *
 * 例如：
 * - 对于输入 `grid = {{1}}`，只有一个陆地格子，初始周长为 `4`，没有共享边界，结果为 `4`。
 * - 对于输入 `grid = {{1, 1}, {1, 1}}`，形成一个 2x2 的岛屿，每个格子贡献 4 条边的周长，但每两格之间有共享边界，
 *   因此结果为 `8`。
 * - 对于输入 `grid = {{1, 0, 1}, {1, 1, 0}, {0, 1, 1}}`，最终的计算结果为 `12`。
 *
 * 时间复杂度：
 *  - 时间复杂度为 O(M * N)，其中 M 是网格的行数，N 是网格的列数。我们需要遍历整个网格一次，因此时间复杂度为 O(M * N)。
 *
 * 空间复杂度：
 *  - 空间复杂度为 O(1)，除了输入网格外，我们只使用了常数级别的额外空间来保存临时的变量。
 */

public class LeetCode_463_IslandPerimeter{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int islandPerimeter(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;

            int result = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 1) {
                        result += 4;

                        if (r > 0 && grid[r-1][c] == 1) {
                            result -= 2;
                        }

                        if (c > 0 && grid[r][c-1] == 1) {
                            result -= 2;
                        }
                    }
                }
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_463_IslandPerimeter().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given row x col grid representing a map where grid[i][j] = 1 represents 
land and grid[i][j] = 0 represents water. 

 Grid cells are connected horizontally/vertically (not diagonally). The grid is 
completely surrounded by water, and there is exactly one island (i.e., one or 
more connected land cells). 

 The island doesn't have "lakes", meaning the water inside isn't connected to 
the water around the island. One cell is a square with side length 1. The grid is 
rectangular, width and height don't exceed 100. Determine the perimeter of the 
island. 

 
 Example 1: 
 
 
Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
 

 Example 2: 

 
Input: grid = [[1]]
Output: 4
 

 Example 3: 

 
Input: grid = [[1,0]]
Output: 4
 

 
 Constraints: 

 
 row == grid.length 
 col == grid[i].length 
 1 <= row, col <= 100 
 grid[i][j] is 0 or 1. 
 There is exactly one island in grid. 
 

 Related Topics Array Depth-First Search Breadth-First Search Matrix 👍 6939 👎 
400

*/
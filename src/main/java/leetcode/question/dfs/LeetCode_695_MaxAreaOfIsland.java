package leetcode.question.dfs;
/**
 *@Question:  695. Max Area of Island
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 60.54999999999999%
 *@Time  Complexity: O(M * N)
 *@Space Complexity: O(M * N)
 */

/**
 * 当然可以。下面是详细的解题思路以及时间和空间复杂度分析。
 *
 * ### 解题思路
 *
 * 1. **问题理解**:
 *    - 题目要求我们计算一个二维网格中最大岛屿的面积。网格中值为 `1` 表示陆地，值为 `0` 表示水域。岛屿是由连通的陆地单元组成的区域，连通性是指四个方向（上下左右）的连通。
 *
 * 2. **思路概述**:
 *    - 我们需要遍历网格中的每一个位置，并从每一个未访问的陆地位置开始，计算以该位置为起点的岛屿的面积。
 *    - 计算岛屿面积的过程中，需要使用深度优先搜索（DFS）方法，递归地探索岛屿的每一个部分。
 *
 * 3. **具体步骤**:
 *    - **初始化**:
 *      - 创建一个 `seen` 二维数组，记录每一个位置是否已被访问过。
 *      - 变量 `maxArea` 用于记录当前找到的最大岛屿面积。
 *
 *    - **遍历网格**:
 *      - 对网格中的每一个位置进行遍历。
 *      - 如果当前位置是陆地且未被访问过，则调用 DFS 方法计算从该位置开始的岛屿面积。
 *      - 更新 `maxArea` 为当前岛屿的面积和已有最大面积之间的较大值。
 *
 *    - **DFS 递归计算岛屿面积**:
 *      - 从当前位置开始，递归地探索四个方向（上下左右）的陆地单元。
 *      - 每次探索一个方向时，标记该位置为已访问，避免重复计算。
 *
 * 4. **递归终止条件**:
 *    - 如果当前位置超出网格边界。
 *    - 如果当前位置已经被访问过。
 *    - 如果当前位置是水域（值为 `0`）。
 *
 * 5. **返回结果**:
 *    - 遍历结束后，`maxArea` 中保存的即为网格中最大岛屿的面积。
 *
 * ### 时间复杂度
 *
 * - **遍历网格**: 每个位置最多会被访问一次，因此遍历整个网格的时间复杂度是 `O(m * n)`，其中 `m` 是网格的行数，`n` 是列数。
 * - **DFS 搜索**: 对于每个陆地单元，DFS 会遍历其所有相连的陆地单元。由于每个单元格最多会被访问一次，DFS 的时间复杂度也是 `O(m * n)`。
 *
 * **综合时间复杂度**: `O(m * n)`
 *
 * ### 空间复杂度
 *
 * - **网格存储**: 存储原始网格需要 `O(m * n)` 的空间。
 * - **访问标记数组**: 存储每个位置是否已被访问，空间复杂度为 `O(m * n)`。
 * - **递归调用栈**: 在最坏情况下，递归深度可能达到 `O(m * n)`，但通常情况下会小于这个值。递归栈的空间复杂度为 `O(m * n)`。
 *
 * **综合空间复杂度**: `O(m * n)`
 *
 * 总的来说，该算法在时间和空间上的复杂度都是 `O(m * n)`，适用于大多数实际场景中的网格问题。
 */
public class LeetCode_695_MaxAreaOfIsland {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] grid; // 用于存储网格
        boolean[][] seen; // 用于标记网格中的位置是否已被访问

        // 计算从网格 (r, c) 开始的岛屿的面积
        public int area(int r, int c) {
            // 如果当前位置超出网格边界，或已被访问，或是水域 (值为0)，则返回0
            if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length ||
                    seen[r][c] || grid[r][c] == 0)
                return 0;

            // 标记当前位置已被访问
            seen[r][c] = true;

            // 递归计算四个方向的面积，并返回总面积
            return (1 + area(r+1, c) + area(r-1, c)
                    + area(r, c-1) + area(r, c+1));
        }

        // 计算网格中最大岛屿面积
        public int maxAreaOfIsland(int[][] grid) {
            this.grid = grid; // 初始化网格
            seen = new boolean[grid.length][grid[0].length]; // 初始化访问标记数组
            int ans = 0; // 存储最大岛屿面积

            // 遍历网格中的每一个位置
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    // 计算当前岛屿的面积，并更新最大面积
                    ans = Math.max(ans, area(r, c));
                }
            }
            return ans; // 返回最大岛屿面积
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_695_MaxAreaOfIsland().new Solution();

        // 测试样例
        int[][] grid1 = {
                {0, 1, 0, 0},
                {1, 1, 0, 1},
                {0, 0, 0, 0},
                {1, 1, 0, 1}
        };
        System.out.println("最大岛屿面积: " + solution.maxAreaOfIsland(grid1)); // 输出：最大岛屿面积: 4

        int[][] grid2 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        System.out.println("最大岛屿面积: " + solution.maxAreaOfIsland(grid2)); // 输出：最大岛屿面积: 0

        int[][] grid3 = {
                {1, 1, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };
        System.out.println("最大岛屿面积: " + solution.maxAreaOfIsland(grid3)); // 输出：最大岛屿面积: 9
    }
}

/**
You are given an m x n binary matrix grid. An island is a group of 1's (
representing land) connected 4-directionally (horizontal or vertical.) You may assume 
all four edges of the grid are surrounded by water. 

 The area of an island is the number of cells with a value 1 in the island. 

 Return the maximum area of an island in grid. If there is no island, return 0. 


 
 Example 1: 
 
 
Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,
1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,
0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-
directionally.
 

 Example 2: 

 
Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 50 
 grid[i][j] is either 0 or 1. 
 

 Related Topics Array Depth-First Search Breadth-First Search Union Find Matrix 
👍 9958 👎 203

*/
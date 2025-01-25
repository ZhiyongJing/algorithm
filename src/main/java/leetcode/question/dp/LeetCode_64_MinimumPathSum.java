package leetcode.question.dp;

/**
  *@Question:  64. Minimum Path Sum     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 53.47%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */
/*
 * 题目描述：
 * 给定一个包含非负整数的 `m x n` 网格 `grid`，从左上角 `grid[0][0]` 出发，
 * 只能向右或向下移动，直到到达右下角 `grid[m-1][n-1]`。
 * 计算一条从起点到终点的路径，使得路径上的数字总和最小，并返回该最小路径和。
 *
 * 输入：
 * - `grid`（m x n 的二维数组）：表示每个单元格的非负整数权重。
 *
 * 输出：
 * - `int`：从左上角到右下角的最小路径和。
 *
 * 示例：
 * 输入：
 *   grid = [
 *      [1, 3, 1],
 *      [1, 5, 1],
 *      [4, 2, 1]
 *   ]
 * 输出：
 *   7
 * 解释：
 *   最优路径为 `1 → 3 → 1 → 1 → 1`，路径和为 `7`。
 */

/*
 * 解题思路：
 * 该问题属于 **动态规划（Dynamic Programming）** 经典问题，可使用 **递归+记忆化搜索** 或 **迭代DP** 解决：
 *
 * 方法 1：递归 + 记忆化搜索（Top-Down）
 * ---------------------------------
 * 1. 递归求解 `minPathSum(i, j)`，表示从 `(0,0)` 到 `(i,j)` 的最小路径和。
 * 2. 递归转移方程：
 *    - `minPathSum(i, j) = min(minPathSum(i-1, j), minPathSum(i, j-1)) + grid[i][j]`
 *    - 即到达 `(i,j)` 的最小路径和等于 **其左侧或上方的最小路径和** 加上 `grid[i][j]` 本身的值。
 * 3. **初始化第一行和第一列**
 *    - `minPathSum(i,0) = minPathSum(i-1,0) + grid[i][0]`（只能向下）
 *    - `minPathSum(0,j) = minPathSum(0,j-1) + grid[0][j]`（只能向右）
 * 4. 使用 **记忆化数组** `memo[i][j]` 存储已计算的最小路径和，避免重复计算，提高效率。
 *
 * **示例计算：**
 * ```
 *  grid = [
 *      [1, 3, 1],
 *      [1, 5, 1],
 *      [4, 2, 1]
 *  ]
 * ```
 *  - `minPathSum(2,2) = min(minPathSum(1,2), minPathSum(2,1)) + grid[2][2]`
 *  - `minPathSum(2,2) = min(7, 5) + 1 = 6`
 *  - 依次递归计算，最终得到最优解。
 *
 * ---------------------------------
 * 方法 2：迭代 DP（Bottom-Up）
 * ---------------------------------
 * 1. 创建 `dp[i][j]` 数组，`dp[i][j]` 存储从 `(0,0)` 到 `(i,j)` 的最小路径和。
 * 2. **初始化边界**
 *    - `dp[0][0] = grid[0][0]`（起点）
 *    - `dp[i][0] = dp[i-1][0] + grid[i][0]`（只能从上方到达）
 *    - `dp[0][j] = dp[0][j-1] + grid[0][j]`（只能从左方到达）
 * 3. **状态转移**
 *    - `dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]`
 *    - 取左侧或上方的最小路径和，加上 `grid[i][j]`
 * 4. **返回 `dp[m-1][n-1]` 作为最终结果**
 *
 * **示例计算：**
 * ```
 * dp 数组计算过程：
 *  初始 grid：
 *   1   3   1
 *   1   5   1
 *   4   2   1
 *
 *  计算 dp 数组：
 *   1   4   5
 *   2   7   6
 *   6   8   7
 * ```
 * **最优路径：**
 * - `(0,0) → (0,1) → (0,2) → (1,2) → (2,2)`
 * - **路径和 = `7`**
 *
 * ---------------------------------
 * 方法 3：空间优化的 DP（滚动数组）
 * ---------------------------------
 * 1. 由于 `dp[i][j]` 只依赖于 **左侧和上方** 的值，因此可以 **用一维数组代替二维数组**，节省空间。
 * 2. `dp[j]` 存储当前行的计算结果，迭代更新 `dp` 数组，避免使用 `O(m*n)` 额外空间。
 * 3. **状态转移**
 *    - `dp[j] = min(dp[j], dp[j-1]) + grid[i][j]`
 *    - **`dp[j]` 代表的是上一行 `dp[i-1][j]`**
 * 4. **返回 `dp[n-1]` 作为最终答案**
 *
 * ---------------------------------
 * **路径回溯（仅适用于方法 2）**
 * ---------------------------------
 * 1. 记录从 `dp[m-1][n-1]` **回溯到 `dp[0][0]` 的路径**。
 * 2. 通过 **比较 `dp[i-1][j]` 和 `dp[i][j-1]`** 来决定从上方或左方移动。
 * 3. **示例**
 * ```
 *  1 → 3 → 1
 *        ↓
 *        1 → 1
 * ```
 * **路径输出：** `"(0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2)"`
 */

/*
 * 时间和空间复杂度分析：
 *
 * 1. **方法 1（递归 + 记忆化搜索）**
 *    - **时间复杂度：** `O(m * n)`（每个状态最多计算一次）
 *    - **空间复杂度：** `O(m * n)`（递归栈深度 `O(m+n)`，`memo` 额外存储 `O(m*n)`）
 *
 * 2. **方法 2（迭代 DP）**
 *    - **时间复杂度：** `O(m * n)`（遍历 `m x n` 个单元格）
 *    - **空间复杂度：** `O(m * n)`（使用 `dp[m][n]` 额外存储）
 *
 * 3. **方法 3（滚动数组优化 DP）**
 *    - **时间复杂度：** `O(m * n)`（遍历 `m x n` 个单元格）
 *    - **空间复杂度：** `O(n)`（只使用 `dp[n]` 一维数组存储结果）
 *
 * ---------------------------------
 * **算法选择建议**
 * - **数据规模较小时（`m, n ≤ 100`）：** **递归 + 记忆化搜索（方法 1）** 可行，但比迭代 DP 慢。
 * - **数据规模较大时（`m, n ≤ 1000`）：** **迭代 DP（方法 2）** 是最常见的方法，直观且高效。
 * - **如果进一步优化空间（`m, n ≤ 10^4`）：** **滚动数组 DP（方法 3）** 是最优解。
 */


public class LeetCode_64_MinimumPathSum{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // Solution 1: 递归 + 记忆化搜索 (Top-Down 动态规划)
        public int minPathSum1(int[][] grid) {
            if(grid == null) return 0; // 如果输入为空，则直接返回 0
            int m = grid.length, n = grid[0].length; // 获取网格的行数和列数
            int[][] memo = new int[m][n]; // 创建一个记忆化数组，用于存储最优子问题的解
            memo[0][0] = grid[0][0]; // 初始化起点的路径和

            // 预处理第一列，保证路径只能从上往下
            for(int i = 1; i < m; i++){
                memo[i][0] = memo[i-1][0] + grid[i][0];
            }

            // 预处理第一行，保证路径只能从左往右
            for(int j = 1; j < n; j++){
                memo[0][j] = memo[0][j-1] + grid[0][j];
            }

            // 递归求解最小路径和
            return dfs(memo, grid, m-1, n-1);
        }

        private int dfs(int[][] memo, int[][] grid, int i, int j){
            if( i == 0 && j ==0){ // 递归终止条件：到达起点
                return grid[0][0];
            }
            if(i < 0 || j < 0){ // 越界情况，返回无穷大（不选择这条路径）
                return Integer.MAX_VALUE;
            }
            if(memo[i][j] != 0){ // 如果当前状态已计算过，则直接返回
                return memo[i][j];
            }
            // 递归计算从上方或左方来的最小路径和，并更新 memo 记录
            int sum = Math.min(dfs(memo, grid, i -1, j), dfs(memo, grid, i, j-1)) + grid[i][j];
            memo[i][j] = sum;
            return sum;
        }

        // Solution 2: 迭代 + 动态规划 (Bottom-Up)
        // 状态转移方程：dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        public int minPathSum(int[][] grid) {
            if(grid == null){
                return 0; // 如果输入为空，直接返回 0
            }
            int m = grid.length, n = grid[0].length; // 获取网格的行数和列数
            int[][] dp = new int[m][n]; // 创建 DP 数组存储最小路径和
            dp[0][0] = grid[0][0]; // 初始化起点路径和

            // 预处理第一列
            for(int i = 1; i < m; i++){
                dp[i][0] = dp[i-1][0] + grid[i][0];
            }

            // 预处理第一行
            for(int j = 1; j < n; j++){
                dp[0][j] = dp[0][j-1] + grid[0][j];
            }

            // 计算剩余的 dp 值，依次填充 DP 表
            for(int i = 1; i< m; i++){
                for(int j = 1;j < n; j++){
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }

            // 计算路径
            StringBuilder path = new StringBuilder();
            int row = m - 1;
            int col = n - 1;

            // 记录从终点回溯到起点的路径
            path.append("(").append(row).append(", ").append(col).append(")");
            while (row > 0 || col > 0) {
                if (row == 0) {
                    col--; // 如果在第一行，只能向左移动
                } else if (col == 0) {
                    row--; // 如果在第一列，只能向上移动
                } else if (dp[row - 1][col] < dp[row][col - 1]) {
                    row--; // 如果上方的路径和较小，则向上移动
                } else {
                    col--; // 否则向左移动
                }
                path.insert(0, "(" + row + ", " + col + ") -> ");
            }

            // 打印最优路径
            System.out.println("Path: " + path);

            // 返回最小路径和
            return dp[m-1][n-1];
        }

        // Solution 3: 滚动数组优化空间复杂度 (仅使用一维数组)
        public int minPathSum3(int[][] grid) {
            if(grid == null) return 0; // 处理空输入
            int m = grid.length, n = grid[0].length; // 获取网格的行数和列数
            int[] dp = new int[n]; // 仅使用一维数组存储最优解
            dp[0] = grid[0][0]; // 初始化起点路径和

            // 预处理第一行
            for(int j = 1; j < n; j++){
                dp[j] = dp[j-1] + grid[0][j];
            }

            // 计算最优路径和，滚动更新 dp 数组
            for(int i = 1; i < m; i++){
                dp[0] = dp[0] + grid[i][0]; // 先更新第一列
                for(int j = 1; j < n; j++){
                    dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j]; // 只用一维数组更新状态
                }
            }
            return dp[n-1]; // 返回最小路径和
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_64_MinimumPathSum().new Solution();

        // 测试用例 1
        int[][] grid1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int minSum1 = solution.minPathSum(grid1);
        System.out.println("Minimum Path Sum (Test 1): " + minSum1);

        // 测试用例 2
        int[][] grid2 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int minSum2 = solution.minPathSum(grid2);
        System.out.println("Minimum Path Sum (Test 2): " + minSum2);

        // 测试用例 3
        int[][] grid3 = {
                {5}
        };
        int minSum3 = solution.minPathSum(grid3);
        System.out.println("Minimum Path Sum (Test 3): " + minSum3);
    }
}

/**
Given a m x n grid filled with non-negative numbers, find a path from top left 
to bottom right, which minimizes the sum of all numbers along its path. 

 Note: You can only move either down or right at any point in time. 

 
 Example 1: 
 
 
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 

 Example 2: 

 
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 200 
 0 <= grid[i][j] <= 200 
 

 Related Topics Array Dynamic Programming Matrix 👍 12062 👎 157

*/
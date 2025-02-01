package leetcode.question.dp;

/**
  *@Question:  63. Unique Paths II
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 52.98%
  *@Time  Complexity: O(M * N)
  *@Space Complexity: O(1)
 */
/*
 * 一、题目描述
 *    给定一个二维网格 obstacleGrid，其中部分格子可能为障碍（用 1 表示），
 *    其余格子都可以通行（用 0 表示）。要求计算从左上角 (0, 0) 到右下角
 *    (m-1, n-1) 的不同路径数，机器人只能向右或向下移动，如果遇到障碍物则无法通过。
 *
 * 二、解题思路（基于给出的代码，超级详细说明）
 *    1. 如果起始格子就是障碍物，那么根本无法移动，直接返回 0。
 *    2. 使用 obstacleGrid 数组自身作为动态规划存储，令 obstacleGrid[i][j] 表示从起点到达 (i, j) 的路径数：
 *       - 首先，将起始格子设为 1 表示有 1 种方法到达起点。
 *       - 填充第一列：如果当前格子不是障碍物且上方格子的路径数为 1，则它的路径数也为 1，否则为 0。
 *         举例：若第一列有连续的非障碍物，则它们沿着列向下都有 1 种走法；一旦碰到障碍物则后续都无法到达。
 *       - 填充第一行：同理，如果当前格子不是障碍物且左侧格子的路径数为 1，则它的路径数也为 1，否则为 0。
 *         举例：若第一行某个格子是障碍物，则右侧所有格子都无法从左上角到达。
 *       - 之后对于 (i, j) 从 (1,1) 开始遍历，如果不是障碍物，则路径数为上方格子加左侧格子的路径数之和；
 *         如果是障碍物则设为 0。举例：若 obstacleGrid[i][j] = 0，则
 *         obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1]。
 *       - 最终 obstacleGrid[m-1][n-1] 即为从起点到终点的路径总数。
 *
 * 三、时间和空间复杂度
 *    1. 时间复杂度：O(m * n)，因为需要遍历整个网格，对每个位置做一次更新。
 *    2. 空间复杂度：O(1)（如果允许原地修改 grid），因为直接在 obstacleGrid 数组中累加结果，
 *       不需要额外开辟新的二维数组；若不允许修改原始数据，则需要 O(m * n) 额外空间来存储 DP 状态。
 */


// 定义一个名为 LeetCode_63_UniquePathsIi 的公共类
public class LeetCode_63_UniquePathsIi{

    // leetcode 提交区域开始（不可修改）
//leetcode submit region begin(Prohibit modification and deletion)
// 定义一个内部类 Solution
    class Solution {
        // 定义方法 uniquePathsWithObstacles，用于计算机器人从起点到终点的路径数量
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            // 取得网格的行数
            int R = obstacleGrid.length;
            // 取得网格的列数
            int C = obstacleGrid[0].length;

            // 如果起始位置就是障碍物，则没有可行路径
            // If the starting cell has an obstacle, then simply return as there would be
            // no paths to the destination.
            if (obstacleGrid[0][0] == 1) {
                return 0;
            }

            // 将起始位置的路径数设为 1，表示有 1 种方法到达起点
            // Number of ways of reaching the starting cell = 1.
            obstacleGrid[0][0] = 1;

            // 填充第一列的数据
            // Filling the values for the first column
            for (int i = 1; i < R; i++) {
                // 如果当前格子本身不是障碍物，且上方格子的路径数为 1，则该格子路径数为 1，否则为 0
                obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 &&
                        obstacleGrid[i - 1][0] == 1)
                        ? 1
                        : 0;
            }

            // 填充第一行的数据
            // Filling the values for the first row
            for (int i = 1; i < C; i++) {
                // 如果当前格子本身不是障碍物，且左边格子的路径数为 1，则该格子路径数为 1，否则为 0
                obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 &&
                        obstacleGrid[0][i - 1] == 1)
                        ? 1
                        : 0;
            }

            // 从 (1,1) 开始填充数据
            // Starting from cell(1,1) fill up the values
            // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
            // i.e. From above and left.
            for (int i = 1; i < R; i++) {
                for (int j = 1; j < C; j++) {
                    // 如果当前格子不是障碍物，则路径数为上方和左方之和
                    if (obstacleGrid[i][j] == 0) {
                        obstacleGrid[i][j] = obstacleGrid[i - 1][j] +
                                obstacleGrid[i][j - 1];
                    } else {
                        // 如果是障碍物，则路径数设为 0
                        obstacleGrid[i][j] = 0;
                    }
                }
            }

            // 返回目标位置的路径数
            // Return value stored in rightmost bottommost cell. That is the destination.
            return obstacleGrid[R - 1][C - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    // main 方法用于测试
    public static void main(String[] args) {
        // 创建 Solution 实例
        Solution solution = new LeetCode_63_UniquePathsIi().new Solution();
        // 添加测试用例 1
        int[][] obstacleGrid1 = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        // 预期输出：2
        System.out.println("Test Case 1: " + solution.uniquePathsWithObstacles(obstacleGrid1));

        // 添加测试用例 2
        int[][] obstacleGrid2 = {
                {0,1},
                {0,0}
        };
        // 预期输出：1
        System.out.println("Test Case 2: " + solution.uniquePathsWithObstacles(obstacleGrid2));
    }
}

/**
You are given an m x n integer array grid. There is a robot initially located 
at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-
right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or 
right at any point in time. 

 An obstacle and space are marked as 1 or 0 respectively in grid. A path that 
the robot takes cannot include any square that is an obstacle. 

 Return the number of possible unique paths that the robot can take to reach 
the bottom-right corner. 

 The testcases are generated so that the answer will be less than or equal to 2 
* 10⁹. 

 
 Example 1: 
 
 
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
 

 Example 2: 
 
 
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
 

 
 Constraints: 

 
 m == obstacleGrid.length 
 n == obstacleGrid[i].length 
 1 <= m, n <= 100 
 obstacleGrid[i][j] is 0 or 1. 
 

 Related Topics Array Dynamic Programming Matrix 👍 9048 👎 525

*/
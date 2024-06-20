package leetcode.question.dp;
/**
 *@Question:  221. Maximal Square
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 57.440000000000005%
 *@Time  Complexity: O(M*N)
 *@Space Complexity: O(M*N) for solution1， O(N) for solution2
 */

/**
 * ### 解题思路
 * 这道题的目标是找到一个二维矩阵中全由 '1' 组成的最大正方形，并返回其面积。我们可以使用动态规划来解决这个问题，分为两种方法：一种是使用二维数组（Solution1），另一种是使用一维数组进行空间优化（Solution2）。
 *
 * #### Solution1: Bottom-Up 动态规划
 * 1. **初始化**：
 *    - 创建一个二维数组 `dp`，其中 `dp[i][j]` 表示以 `(i-1, j-1)` 为右下角的最大正方形的边长。
 *    - 初始化最大正方形边长 `maxsqlen` 为 0。
 *    - 为简化状态转移方程，`dp` 数组多加一行和一列，初始值都为 0。
 *
 * 2. **状态转移方程**：
 *    - 遍历矩阵中的每个元素 `matrix[i-1][j-1]`。
 *    - 如果当前元素为 '1'，则 `dp[i][j]` 的值等于 `dp[i-1][j]`、`dp[i][j-1]` 和 `dp[i-1][j-1]` 的最小值加 1。
 *    - 更新最大正方形边长 `maxsqlen`。
 *
 * 3. **返回结果**：
 *    - 返回最大正方形的面积，即 `maxsqlen * maxsqlen`。
 *
 * #### Solution2: 空间优化
 * 1. **初始化**：
 *    - 使用一维数组 `dp` 来代替二维数组，节省空间。`dp[j]` 表示当前行第 `j` 列的最大正方形边长。
 *    - 使用变量 `prev` 来记录左上角的值，以便在更新 `dp[j]` 时使用。
 *    - 初始化 `dp` 数组和 `prev` 变量。
 *
 * 2. **状态转移方程**：
 *    - 遍历矩阵中的每个元素。
 *    - 如果当前元素为 '1'，则 `dp[j]` 的值等于 `dp[j]`、`dp[j-1]` 和 `prev` 的最小值加 1。
 *    - 更新最大正方形边长 `maxsqlen`。
 *    - 更新 `prev` 变量。
 *
 * 3. **返回结果**：
 *    - 返回最大正方形的面积，即 `maxsqlen * maxsqlen`。
 *
 * ### 时间和空间复杂度
 *
 * #### Solution1: Bottom-Up 动态规划
 * - **时间复杂度**：`O(M * N)`，其中 `M` 是矩阵的行数，`N` 是矩阵的列数。我们需要遍历整个矩阵一次。
 * - **空间复杂度**：`O(M * N)`，需要一个二维数组来存储动态规划的中间结果。
 *
 * #### Solution2: 空间优化
 * - **时间复杂度**：`O(M * N)`，其中 `M` 是矩阵的行数，`N` 是矩阵的列数。我们需要遍历整个矩阵一次。
 * - **空间复杂度**：`O(N)`，仅使用一个一维数组来存储当前行的结果，并且用一个变量 `prev` 来记录左上角的值。
 */

public class LeetCode_221_MaximalSquare {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        // Solution1: bottom up DP
        public int maximalSquare(char[][] matrix) {
            int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
            int[][] dp = new int[rows + 1][cols + 1];
            int maxsqlen = 0;

            // 为了简化状态转移方程，增加了一行和一列全0的辅助数组
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    if (matrix[i - 1][j - 1] == '1') {
                        dp[i][j] = Math.min(
                                Math.min(dp[i][j - 1], dp[i - 1][j]), // 左边和上边的最小值
                                dp[i - 1][j - 1] // 左上角的值
                        ) + 1;
                        maxsqlen = Math.max(maxsqlen, dp[i][j]); // 更新最大边长
                    }
                }
            }
            return maxsqlen * maxsqlen; // 返回最大平方的面积
        }

        // Solution2: 基于 Solution1 的空间优化
        public int maximalSquare2(char[][] matrix) {
            int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
            int[] dp = new int[cols + 1]; // 仅使用一维数组来存储当前行的DP值
            int maxsqlen = 0, prev = 0; // prev 用于存储左上角的值
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    int temp = dp[j]; // 暂存 dp[j] 的值，用于下一次迭代中的 prev
                    if (matrix[i - 1][j - 1] == '1') {
                        dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                        maxsqlen = Math.max(maxsqlen, dp[j]); // 更新最大边长
                    } else {
                        dp[j] = 0; // 当前格子为 '0' 时，dp[j] 置为 0
                    }
                    prev = temp; // 更新 prev 为当前的 temp
                }
            }
            return maxsqlen * maxsqlen; // 返回最大平方的面积
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_221_MaximalSquare().new Solution();

        // 测试用例1
        char[][] matrix1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(solution.maximalSquare(matrix1)); // 输出: 4

        // 测试用例2
        char[][] matrix2 = {
                {'0', '1'},
                {'1', '0'}
        };
        System.out.println(solution.maximalSquare(matrix2)); // 输出: 1

        // 测试用例3
        char[][] matrix3 = {
                {'0'}
        };
        System.out.println(solution.maximalSquare(matrix3)); // 输出: 0
    }
}

/**
Given an m x n binary matrix filled with 0's and 1's, find the largest square 
containing only 1's and return its area. 

 
 Example 1: 
 
 
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1
"],["1","0","0","1","0"]]
Output: 4
 

 Example 2: 
 
 
Input: matrix = [["0","1"],["1","0"]]
Output: 1
 

 Example 3: 

 
Input: matrix = [["0"]]
Output: 0
 

 
 Constraints: 

 
 m == matrix.length 
 n == matrix[i].length 
 1 <= m, n <= 300 
 matrix[i][j] is '0' or '1'. 
 

 Related Topics Array Dynamic Programming Matrix 👍 10042 👎 223

*/
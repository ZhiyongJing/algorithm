package leetcode.frequent.prefix_sum;

/**
 * @Question: 304. Range Sum Query 2D - Immutable
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 52.49%
 * @Time Complexity: O()
 * @Space Complexity: O()
 */

/**
 * 这道题使用了二维前缀和的思想，通过在构造函数中计算二维前缀和数组，可以在之后的查询中快速获取指定区域的和。下面是解题思路的详细讲解：
 *
 * ### 解题思路
 *
 * 1. **构造函数 (`NumMatrix(int[][] matrix)`)：**
 *    - 首先检查输入矩阵是否为空，若为空则直接返回。
 *    - 创建一个新的二维数组 `dp`，其行数和列数均比原矩阵多一维。这个数组用来存储二维前缀和。
 *    - 使用两层嵌套循环遍历原矩阵，计算每个位置的二维前缀和，并存储在 `dp` 数组中。
 *
 * 2. **查询函数 (`sumRegion(int row1, int col1, int row2, int col2)`)：**
 *    - 利用二维前缀和的性质，可以通过以下公式快速计算指定区域的和：
 *      ```
 *      dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1]
 *      ```
 *      其中，`dp[row2 + 1][col2 + 1]` 表示指定区域右下角的前缀和，`dp[row1][col2 + 1]`
 *      和 `dp[row2 + 1][col1]` 表示两个相邻区域的前缀和，最后再减去一个重复计算的部分 `dp[row1][col1]`。
 *
 * ### 时间复杂度
 *
 * - **构造函数时间复杂度：**
 *   - 在构造函数中，使用两层嵌套循环遍历整个矩阵，计算二维前缀和。
 *   - 时间复杂度为 O(m * n)，其中 m 为矩阵的行数，n 为矩阵的列数。
 *
 * - **查询函数时间复杂度：**
 *   - 查询函数的计算过程是常数时间的操作。
 *   - 时间复杂度为 O(1)。
 *
 * ### 空间复杂度
 *
 * - **构造函数空间复杂度：**
 *   - 使用了一个额外的二维数组 `dp` 来存储二维前缀和。
 *   - 空间复杂度为 O(m * n)，其中 m 为矩阵的行数，n 为矩阵的列数。
 *
 * - **查询函数空间复杂度：**
 *   - 查询函数本身没有使用额外的空间，只是返回计算的结果。
 *
 * 总体上，构造函数的时间和空间复杂度主要受到矩阵的规模影响，而查询函数由于利用了前缀和，可以在常数时间内完成。
 */

public class LeetCode_304_RangeSumQuery2dImmutable {

    // leetcode submit region begin(Prohibit modification and deletion)
    class NumMatrix {
        private int[][] dp;

        /**
         * 构造函数，初始化二维前缀和数组 dp。
         * @param matrix 输入的二维矩阵
         */
        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;

            // 创建二维前缀和数组，行列长度均比原矩阵多一维
            dp = new int[matrix.length + 1][matrix[0].length + 1];

            // 计算二维前缀和
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
                }
            }
        }

        /**
         * 计算指定区域的和，利用二维前缀和数组。
         * @param row1 区域的起始行
         * @param col1 区域的起始列
         * @param row2 区域的结束行
         * @param col2 区域的结束列
         * @return 指定区域的和
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
        }
    }

    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */
    //leetcode submit region end(Prohibit modification and deletion)


        public static void main(String[] args) {
            // 创建一个二维矩阵
            int[][] matrix = {
                    {3, 0, 1, 4, 2},
                    {5, 6, 3, 2, 1},
                    {1, 2, 0, 1, 5},
                    {4, 1, 0, 1, 7},
                    {1, 0, 3, 0, 5}
            };

            // 创建 NumMatrix 对象
            NumMatrix solution = new LeetCode_304_RangeSumQuery2dImmutable().new NumMatrix(matrix);

            // 测试样例1
            int result1 = solution.sumRegion(2, 1, 4, 3);
            System.out.println("Result 1: " + result1); // 预期输出：8

            // 测试样例2
            int result2 = solution.sumRegion(1, 1, 2, 2);
            System.out.println("Result 2: " + result2); // 预期输出：11

            // 测试样例3
            int result3 = solution.sumRegion(1, 2, 2, 4);
            System.out.println("Result 3: " + result3); // 预期输出：12
        }
}



/**
Given a 2D matrix matrix, handle multiple queries of the following type: 

 
 Calculate the sum of the elements of matrix inside the rectangle defined by 
its upper left corner (row1, col1) and lower right corner (row2, col2). 
 

 Implement the NumMatrix class: 

 
 NumMatrix(int[][] matrix) Initializes the object with the integer matrix 
matrix. 
 int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the 
elements of matrix inside the rectangle defined by its upper left corner (row1, 
col1) and lower right corner (row2, col2). 
 

 You must design an algorithm where sumRegion works on O(1) time complexity. 

 
 Example 1: 
 
 
Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3,
 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]
 

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0,
 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)


 
 Constraints: 

 
 m == matrix.length 
 n == matrix[i].length 
 1 <= m, n <= 200 
 -10⁴ <= matrix[i][j] <= 10⁴ 
 0 <= row1 <= row2 < m 
 0 <= col1 <= col2 < n 
 At most 10⁴ calls will be made to sumRegion. 
 

 Related Topics Array Design Matrix Prefix Sum 👍 4798 👎 335

*/

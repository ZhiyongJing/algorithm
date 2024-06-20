package leetcode.question.dp;
/**
 *@Question:  1277. Count Square Submatrices with All Ones
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 28.49%
 *@Time  Complexity: O(M* N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 这道题的目标是计算一个给定的二进制矩阵中，包含所有1的正方形子矩阵的总数。我们可以使用动态规划来解决这个问题。
 *
 * #### 具体步骤：
 *
 * 1. **初始化和边界条件**：
 *    - 创建一个变量 `res` 来存储结果，即所有正方形子矩阵的总数。
 *    - 遍历矩阵的每个元素。
 *
 * 2. **动态规划转移方程**：
 *    - 如果当前元素 `A[i][j]` 为1，并且它不在第一行或第一列（即 `i > 0` 且 `j > 0`），那么：
 *      - `A[i][j]` 的值应该更新为它左边、上边和左上角三个值的最小值加1，即 `A[i][j] = min(A[i-1][j-1], min(A[i-1][j], A[i][j-1])) + 1`。
 *      - 这样做是因为，如果 `A[i][j]` 能构成一个正方形，那么它左边、上边和左上角的位置也必须能构成相应的正方形，这样才可以扩展成一个更大的正方形。
 *
 * 3. **累加结果**：
 *    - 对于每个位置 `A[i][j]`，将其值累加到 `res` 中。
 *
 * 4. **返回结果**：
 *    - 最终，`res` 就是所有正方形子矩阵的总数。
 *
 * ### 时间和空间复杂度分析
 *
 * #### 时间复杂度
 * - 遍历整个矩阵的每个元素，因此时间复杂度是 `O(M * N)`，其中 `M` 是矩阵的行数，`N` 是矩阵的列数。
 *
 * #### 空间复杂度
 * - 原地更新输入矩阵，因此额外的空间复杂度是 `O(1)`。不过，如果我们不允许修改输入矩阵，可以使用额外的矩阵来存储中间结果，这样空间复杂度就是 `O(M * N)`。考虑到在许多面试中，通常假设修改输入是允许的，空间复杂度通常会被认为是 `O(1)`。
 *
 * ### 结论
 * - 该算法有效地利用动态规划，避免了重复计算，并且在空间上进行了优化。通过直接在输入矩阵上进行修改，节省了额外的空间，同时保持了时间复杂度的最优性。
 */

public class LeetCode_1277_CountSquareSubmatricesWithAllOnes{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countSquares(int[][] A) {
            int res = 0;
            // 遍历二维数组的每一个元素
            for (int i = 0; i < A.length; ++i) {
                for (int j = 0; j < A[0].length; ++j) {
                    // 如果当前元素为1且不在第一行或第一列
                    if (A[i][j] > 0 && i > 0 && j > 0) {
                        // 计算当前元素所在位置的正方形的边长
                        A[i][j] = Math.min(A[i - 1][j - 1], Math.min(A[i - 1][j], A[i][j - 1])) + 1;
                    }
                    // 累加结果
                    res += A[i][j];
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1277_CountSquareSubmatricesWithAllOnes().new Solution();
        // 测试样例
        int[][] matrix1 = {
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };
        System.out.println(solution.countSquares(matrix1)); // 输出: 7

        int[][] matrix2 = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        System.out.println(solution.countSquares(matrix2)); // 输出: 15

        int[][] matrix3 = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        System.out.println(solution.countSquares(matrix3)); // 输出: 14
    }
}

/**
Given a m * n matrix of ones and zeros, return how many square submatrices have 
all ones. 

 
 Example 1: 

 
Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
 

 Example 2: 

 
Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 

 
 Constraints: 

 
 1 <= arr.length <= 300 
 1 <= arr[0].length <= 300 
 0 <= arr[i][j] <= 1 
 

 Related Topics Array Dynamic Programming Matrix 👍 4824 👎 80

*/
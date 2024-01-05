package leetcode.frequent.based_on_data_structure.map_set;

/**
  *@Question:  73. Set Matrix Zeroes     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 71.45%      
  *@Time  Complexity: O(M*N)
  *@Space Complexity: O(1)
 */
/**
 * **解题思路：**
 *
 * 这题的思路是使用矩阵的第一行和第一列来存储每行和每列是否需要置零的信息。首先，遍历整个矩阵，如果某个元素为零，将对应的行首和列首置零。
 * 接着，根据第一行和第一列的信息，将矩阵中的元素置零。最后，检查第一行和第一列是否需要置零，并分别置零。
 *
 * 步骤：
 *
 * 1. 创建一个布尔值 `isCol`，用于标记第一列是否需要置零。
 * 2. 获取矩阵的行数 `R` 和列数 `C`。
 * 3. 遍历矩阵，对于每个元素：
 *     - 如果元素为零，将该行的第一个元素和该列的第一个元素分别置零，作为标记。
 *     - 如果元素不为零，继续下一个元素的处理。
 * 4. 遍历矩阵，对于每个元素：
 *     - 如果该行的第一个元素或该列的第一个元素为零，将该元素置零。
 * 5. 检查第一行是否需要置零，如果需要，将整个第一行置零。
 * 6. 检查 `isCol`，如果为 true，将整个第一列置零。
 *
 * **时间复杂度：**
 *
 * 遍历整个矩阵的时间复杂度为 O(m * n)，其中 m 为矩阵的行数，n 为矩阵的列数。
 *
 * **空间复杂度：**
 *
 * 使用了常量级的额外空间，空间复杂度为 O(1)。
 */

public class LeetCode_73_SetMatrixZeroes {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void setZeroes(int[][] matrix) {
            Boolean isCol = false;
            int R = matrix.length;
            int C = matrix[0].length;

            // 遍历矩阵，将矩阵中的0的信息记录在第一行和第一列中
            for (int i = 0; i < R; i++) {
                if (matrix[i][0] == 0) {
                    isCol = true;
                }

                for (int j = 1; j < C; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }

            // 根据第一行和第一列的信息，将矩阵中的元素置零
            for (int i = 1; i < R; i++) {
                for (int j = 1; j < C; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            // 检查第一行是否需要置零
            if (matrix[0][0] == 0) {
                for (int j = 0; j < C; j++) {
                    matrix[0][j] = 0;
                }
            }

            // 检查第一列是否需要置零
            if (isCol) {
                for (int i = 0; i < R; i++) {
                    matrix[i][0] = 0;
                }
            }
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_73_SetMatrixZeroes().new Solution();

        // 测试用例
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };
        solution.setZeroes(matrix1);
        printMatrix(matrix1); // 输出置零后的矩阵

        int[][] matrix2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        solution.setZeroes(matrix2);
        printMatrix(matrix2); // 输出置零后的矩阵
    }

    // 打印矩阵
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

/**
Given an m x n integer matrix matrix, if an element is 0, set its entire row 
and column to 0's. 

 You must do it in place. 

 
 Example 1: 
 
 
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
 

 Example 2: 
 
 
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 

 
 Constraints: 

 
 m == matrix.length 
 n == matrix[0].length 
 1 <= m, n <= 200 
 -2³¹ <= matrix[i][j] <= 2³¹ - 1 
 

 
 Follow up: 

 
 A straightforward solution using O(mn) space is probably a bad idea. 
 A simple improvement uses O(m + n) space, but still not the best solution. 
 Could you devise a constant space solution? 
 

 Related Topics Array Hash Table Matrix 👍 13666 👎 684

*/
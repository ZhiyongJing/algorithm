package leetcode.question.map_set;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * @Question:  311. Sparse Matrix Multiplication
 * @Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 54.39000000000001%
 * @Time  Complexity: O(M * K * N)
 * @Space Complexity: O(M * K + K * N)
 */

/**
 * 这段代码实现了稀疏矩阵的乘法运算。下面是解题思路的详细说明：
 *
 * 1. **压缩矩阵：**
 *    - 首先定义了一个 `compressMatrix` 方法，用于将输入的矩阵进行压缩。对于每一行，遍历其所有列，如果发现非零元素，则将其存储为一个整数对 `(value, col)`，其中 `value` 是非零元素的值，`col` 是该非零元素所在的列号。将每一行的非零元素对存储在一个 `ArrayList` 中，并将所有行的 `ArrayList` 存储在另一个 `ArrayList` 中。
 *    - 时间复杂度：O(M * K)，其中 M 和 K 分别是输入矩阵的行数和列数。
 *    - 空间复杂度：O(M * K)，存储了所有非零元素对。
 *
 * 2. **矩阵相乘：**
 *    - 定义了 `multiply` 方法，用于实现两个稀疏矩阵的乘法运算。首先调用 `compressMatrix` 方法对两个输入矩阵进行压缩，得到压缩后的稀疏矩阵 A 和 B。
 *    - 然后遍历矩阵 A 的每一行，对于每一个非零元素 `(element1, col1)`，遍历矩阵 B 中该非零元素所在的列的所有非零元素 `(element2, col2)`，将它们相乘得到的结果累加到结果矩阵的 `(row, col2)` 位置上。
 *    - 时间复杂度：O(M * K * N)，其中 M、K 和 N 分别是输入矩阵的行数、列数和另一个矩阵的列数。
 *    - 空间复杂度：O(M * N)，结果矩阵的大小。
 */
public class LeetCode_311_SparseMatrixMultiplication{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 压缩矩阵，将非零元素存储在ArrayList中
        public ArrayList<ArrayList<Pair<Integer, Integer>>> compressMatrix(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            ArrayList<ArrayList<Pair<Integer, Integer>>> compressedMatrix = new ArrayList<>();

            for (int row = 0; row < rows; ++row) {
                ArrayList<Pair<Integer, Integer>> currRow = new ArrayList<>();
                for (int col = 0; col < cols; ++col) {
                    if (matrix[row][col] != 0) {
                        currRow.add(new Pair(matrix[row][col], col));
                    }
                }
                compressedMatrix.add(currRow);
            }
            return compressedMatrix;
        }

        // 矩阵相乘
        public int[][] multiply(int[][] mat1, int[][] mat2) {
            int m = mat1.length;
            int k = mat1[0].length;
            int n = mat2[0].length;

            // 存储每个矩阵的非零值
            ArrayList<ArrayList<Pair<Integer, Integer>>> A = compressMatrix(mat1);
            ArrayList<ArrayList<Pair<Integer, Integer>>> B = compressMatrix(mat2);

            int[][] ans = new int[m][n];

            for (int mat1Row = 0; mat1Row < m; ++mat1Row) {
                // 遍历mat1的当前行的所有非零元素
                for (Pair mat1Element : A.get(mat1Row)) {
                    int element1 = (int)mat1Element.getKey();
                    int mat1Col = (int)mat1Element.getValue();

                    // 将mat2的当前列等于mat1当前元素的列的非零元素相乘并相加
                    for (Pair mat2Element : B.get(mat1Col)) {
                        int element2 = (int)mat2Element.getKey();
                        int mat2Col = (int)mat2Element.getValue();
                        ans[mat1Row][mat2Col] += element1 * element2;
                    }
                }
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_311_SparseMatrixMultiplication().new Solution();
        // TO TEST
        //solution.
    }
}

/**
Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the 
result of mat1 x mat2. You may assume that multiplication is always possible. 

 
 Example 1: 
 
 
Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
Output: [[7,0,0],[-7,0,3]]
 

 Example 2: 

 
Input: mat1 = [[0]], mat2 = [[0]]
Output: [[0]]
 

 
 Constraints: 

 
 m == mat1.length 
 k == mat1[i].length == mat2.length 
 n == mat2[i].length 
 1 <= m, n, k <= 100 
 -100 <= mat1[i][j], mat2[i][j] <= 100 
 

 Related Topics Array Hash Table Matrix 👍 1056 👎 359

*/
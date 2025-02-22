package leetcode.question.string_list;

import java.util.Arrays;

/**
 *@Question:  48. Rotate Image
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 87.0%
 *@Time  Complexity: O(N^2)  // 由于每个元素需要被访问和交换
 *@Space Complexity: O(1)    // 只使用了常数级额外空间
 */
/**
 * 1. 题目描述:
 *    - 给定一个 `n x n` 的二维矩阵 `matrix`，表示一个图像。
 *    - 需要 **原地** 旋转矩阵 90°（顺时针方向）。
 *    - **不能使用额外的矩阵存储旋转结果**，必须在 **输入矩阵上直接修改**。
 *
 *  例如：
 *    输入:
 *    [
 *      [1, 2, 3],
 *      [4, 5, 6],
 *      [7, 8, 9]
 *    ]
 *
 *    输出:
 *    [
 *      [7, 4, 1],
 *      [8, 5, 2],
 *      [9, 6, 3]
 *    ]
 *
 *  约束:
 *    - `n == matrix.length == matrix[i].length`
 *    - `1 <= n <= 20`
 *    - `-1000 <= matrix[i][j] <= 1000`
 */

/**
 * 2. 解题思路:
 *    该问题的核心是如何在 **不使用额外存储** 的情况下 **原地旋转矩阵**。
 *    我们采用 **两步转换法**，先进行 **转置（Transpose）**，然后进行 **水平翻转（Reflect）**。
 *
 *    **第一步：转置矩阵（Transpose）**
 *    - 转置矩阵的定义是 **行变列，列变行**。
 *    - 具体方法：
 *      1. 遍历矩阵的**上三角部分**（主对角线右上方元素）。
 *      2. 交换 `matrix[i][j]` 和 `matrix[j][i]`，即 `matrix[i][j] = matrix[j][i]`。
 *
 *    **示例：**
 *      原始矩阵：
 *        1  2  3
 *        4  5  6
 *        7  8  9
 *
 *      转置后：
 *        1  4  7
 *        2  5  8
 *        3  6  9
 *
 *    **第二步：水平翻转（Reflect）**
 *    - 在转置完成后，我们再进行 **水平翻转**。
 *    - 具体方法：
 *      1. 遍历矩阵的 **每一行**。
 *      2. 交换 **对称列** 的元素，即 `matrix[i][j]` 和 `matrix[i][n-j-1]`。
 *
 *    **示例：**
 *      转置后的矩阵：
 *        1  4  7
 *        2  5  8
 *        3  6  9
 *
 *      进行水平翻转后：
 *        7  4  1
 *        8  5  2
 *        9  6  3
 *
 *    **完整流程总结**
 *    - 先 **转置**（行变列）
 *    - 再 **水平翻转**（每行对称列互换）
 *    - 这样就完成了 **90° 旋转**
 */

/**
 * 3. 时间和空间复杂度分析:
 *    - **时间复杂度：O(N²)**
 *      - 由于矩阵是 `n x n`，每个元素在 **转置** 和 **翻转** 时都需要被访问和交换。
 *      - 转置需要 **遍历一半的矩阵**，大约 O(N²/2)。
 *      - 翻转也需要 **遍历一半的矩阵**，大约 O(N²/2)。
 *      - 整体时间复杂度仍然是 O(N²)。
 *
 *    - **空间复杂度：O(1)**
 *      - 我们是 **原地操作矩阵**，没有使用额外的存储空间。
 *      - 所有交换操作都在 **输入矩阵内** 进行，没有额外数组存储。
 *      - 因此，空间复杂度为 O(1)。
 */


public class LeetCode_48_RotateImage{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[][] matrix) {
            // 先对矩阵进行转置
            transpose(matrix);
            // 再对矩阵进行水平翻转
            reflect(matrix);
        }

        public void transpose(int[][] matrix) {
            int n = matrix.length; // 获取矩阵的大小 n x n
            for (int i = 0; i < n; i++) { // 遍历矩阵的每一行
                for (int j = i + 1; j < n; j++) { // 只遍历对角线右上部分，避免重复交换
                    int tmp = matrix[j][i]; // 交换 matrix[j][i] 和 matrix[i][j]
                    matrix[j][i] = matrix[i][j];
                    matrix[i][j] = tmp;
                }
            }
        }

        public void reflect(int[][] matrix) {
            int n = matrix.length; // 获取矩阵的大小 n x n
            for (int i = 0; i < n; i++) { // 遍历矩阵的每一行
                for (int j = 0; j < n / 2; j++) { // 只遍历一半的列，对称交换
                    int tmp = matrix[i][j]; // 交换 matrix[i][j] 和 matrix[i][n - j - 1]
                    matrix[i][j] = matrix[i][n - j - 1];
                    matrix[i][n - j - 1] = tmp;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_48_RotateImage().new Solution();

        // 测试样例
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("原始矩阵:");
        printMatrix(matrix);

        // 调用旋转函数
        solution.rotate(matrix);

        System.out.println("旋转后的矩阵:");
        printMatrix(matrix);
    }

    // 打印矩阵的方法
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

/**
You are given an n x n 2D matrix representing an image, rotate the image by 90 
degrees (clockwise). 

 You have to rotate the image in-place, which means you have to modify the 
input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation. 

 
 Example 1: 
 
 
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
 

 Example 2: 
 
 
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 

 
 Constraints: 

 
 n == matrix.length == matrix[i].length 
 1 <= n <= 20 
 -1000 <= matrix[i][j] <= 1000 
 

 Related Topics Array Math Matrix 👍 18358 👎 876

*/
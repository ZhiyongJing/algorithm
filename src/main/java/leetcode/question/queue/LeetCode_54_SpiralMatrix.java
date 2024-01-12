package leetcode.question.queue;

/**
 * @Question: 54. Spiral Matrix
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 86.65%
 * @Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix
 * @Space Complexity: O(1)
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 这段代码解决的问题是按照螺旋顺序输出给定的二维矩阵。以下是解题思路和复杂度分析：
 *
 * **解题思路：**
 *
 * 1. 使用四个变量 `up`, `down`, `left`, 和 `right` 分别表示当前螺旋的上边界、下边界、左边界和右边界。
 *
 * 2. 使用一个循环，当结果集的大小小于矩阵中所有元素的个数时，不断循环遍历：
 *
 *    a. 从左到右遍历当前上边界，将元素添加到结果集中。
 *
 *    b. 从上到下遍历当前右边界，将元素添加到结果集中。
 *
 *    c. 检查是否有下边界，并从右到左遍历当前下边界，将元素添加到结果集中。
 *
 *    d. 检查是否有左边界，并从下到上遍历当前左边界，将元素添加到结果集中。
 *
 *    e. 更新上、下、左、右边界的值。
 *
 * 3. 返回最终的结果集。
 *
 * **时间复杂度：**
 *
 * - 由于我们需要访问矩阵中的每个元素一次，所以时间复杂度为 O(m * n)，其中 m 是矩阵的行数，n 是矩阵的列数。
 *
 * **空间复杂度：**
 *
 * - 空间复杂度为 O(1)，除了输出结果的 List<Integer> 之外，没有使用额外的空间。
 *
 * 这段代码采用了一种模拟螺旋遍历的方法，通过不断缩小边界，按照指定的顺序添加元素到结果集中，最终得到按螺旋顺序排列的矩阵元素。
 */

public class LeetCode_54_SpiralMatrix {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> result = new ArrayList<>();
            int rows = matrix.length;
            int columns = matrix[0].length;
            int up = 0;
            int left = 0;
            int right = columns - 1;
            int down = rows - 1;

            while (result.size() < rows * columns) {
                // 从左到右遍历
                for (int col = left; col <= right; col++) {
                    result.add(matrix[up][col]);
                }
                // 向下遍历
                for (int row = up + 1; row <= down; row++) {
                    result.add(matrix[row][right]);
                }
                // 确保当前行不与上一行相同
                if (up != down) {
                    // 从右到左遍历
                    for (int col = right - 1; col >= left; col--) {
                        result.add(matrix[down][col]);
                    }
                }
                // 确保当前列不与上一列相同
                if (left != right) {
                    // 向上遍历
                    for (int row = down - 1; row > up; row--) {
                        result.add(matrix[row][left]);
                    }
                }
                left++;
                right--;
                up++;
                down--;
            }

            return result;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试代码
        Solution solution = new LeetCode_54_SpiralMatrix().new Solution();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> result = solution.spiralOrder(matrix);
        System.out.println("Spiral Order: " + result);
        // 输出：Spiral Order: [1, 2, 3, 6, 9, 8, 7, 4, 5]
    }
}

/**
Given an m x n matrix, return all elements of the matrix in spiral order. 

 
 Example 1: 
 
 
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
 

 Example 2: 
 
 
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 

 
 Constraints: 

 
 m == matrix.length 
 n == matrix[i].length 
 1 <= m, n <= 10 
 -100 <= matrix[i][j] <= 100 
 

 Related Topics Array Matrix Simulation 👍 14035 👎 1223

*/
package leetcode.question.binary_search;

/**
  *@Question:  240. Search a 2D Matrix II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 61.61%      
  *@Time  Complexity: O(M +N)
  *@Space Complexity: O()
 */

/**
 * 这个算法使用了一种从左下角出发的搜索策略，逐步缩小搜索范围，直到找到目标值或者搜索范围越界。以下是算法的详细思路：
 *
 * ### 算法思路：
 *
 * 1. 从矩阵的左下角开始（也可以选择右上角），将行指针 `row` 设置为矩阵的最后一行，列指针 `col` 设置为矩阵的第一列。
 *
 * 2. 在每一次迭代中，比较当前元素 `matrix[row][col]` 与目标值 `target`：
 *    - 如果 `matrix[row][col] > target`，说明目标值可能在当前元素的上方，因此将 `row` 减小 1。
 *    - 如果 `matrix[row][col] < target`，说明目标值可能在当前元素的右侧，因此将 `col` 增加 1。
 *    - 如果 `matrix[row][col] == target`，说明找到了目标值，返回 `true`。
 *
 * 3. 重复步骤 2，直到行指针 `row` 越界（小于 0）或者列指针 `col` 越界（大于等于矩阵的列数）。
 *
 * 4. 如果搜索范围越界，说明目标值不在矩阵中，返回 `false`。
 *
 * ### 时间复杂度：
 *
 * - 在每一步中，要么将行指针减小 1，要么将列指针增加 1，因此每次迭代都会缩小搜索范围。在最坏情况下，算法的时间复杂度为 O(M + N)，
 * 其中 M 是矩阵的行数，N 是矩阵的列数。
 *
 * ### 空间复杂度：
 *
 * - 空间复杂度为 O(1)，因为只使用了常数级别的变量。
 */
public class LeetCode_240_SearchA2dMatrixIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 在二维矩阵中搜索目标值
         *
         * @param matrix 二维矩阵
         * @param target 目标值
         * @return 是否存在目标值
         */
        public boolean searchMatrix(int[][] matrix, int target) {
            // 从矩阵的左下角开始搜索
            int row = matrix.length - 1;
            int col = 0;

            // 在搜索过程中逐步缩小搜索范围
            while (row >= 0 && col < matrix[0].length) {
                if (matrix[row][col] > target) {
                    // 如果当前元素大于目标值，向上移动
                    row--;
                } else if (matrix[row][col] < target) {
                    // 如果当前元素小于目标值，向右移动
                    col++;
                } else {
                    // 如果当前元素等于目标值，找到目标值，返回true
                    return true;
                }
            }

            // 如果搜索范围越界，说明目标值不在矩阵中，返回false
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_240_SearchA2dMatrixIi().new Solution();

        // 测试代码
        int[][] matrix = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        // 测试目标值存在的情况
        // 预期输出: true
        System.out.println(solution.searchMatrix(matrix, 5));

        // 测试目标值不存在的情况
        // 预期输出: false
        System.out.println(solution.searchMatrix(matrix, 20));
    }
}

/**
Write an efficient algorithm that searches for a value target in an m x n 
integer matrix matrix. This matrix has the following properties: 

 
 Integers in each row are sorted in ascending from left to right. 
 Integers in each column are sorted in ascending from top to bottom. 
 

 
 Example 1: 
 
 
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,
21,23,26,30]], target = 5
Output: true
 

 Example 2: 
 
 
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,
21,23,26,30]], target = 20
Output: false
 

 
 Constraints: 

 
 m == matrix.length 
 n == matrix[i].length 
 1 <= n, m <= 300 
 -10⁹ <= matrix[i][j] <= 10⁹ 
 All the integers in each row are sorted in ascending order. 
 All the integers in each column are sorted in ascending order. 
 -10⁹ <= target <= 10⁹ 
 

 Related Topics Array Binary Search Divide and Conquer Matrix 👍 11454 👎 190

*/

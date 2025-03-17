package leetcode.question.string_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *@Question:  498. Diagonal Traverse
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 72.77%
 *@Time Complexity: O(M * N) // 需要遍历整个矩阵
 *@Space Complexity: O(min(M, N)) // 由于使用了中间列表存储对角线元素
 */
/**
 * 题目描述：
 * LeetCode 498. Diagonal Traverse
 *
 * 给定一个 `M x N` 的矩阵 `matrix`，按 **对角线遍历** 方式返回其所有元素。
 * **遍历规则**：
 * - 先从左上角 `(0,0)` 开始。
 * - 按 **从左到右**，**从上到下** 的方式 **依次遍历每条对角线**。
 * - **偶数编号对角线**（0, 2, 4, ...）从左下向右上遍历。
 * - **奇数编号对角线**（1, 3, 5, ...）从右上向左下遍历。
 *
 * **示例 1：**
 * ```
 * 输入:
 * matrix = [
 *   [1, 2, 3],
 *   [4, 5, 6],
 *   [7, 8, 9]
 * ]
 * 输出: [1,2,4,7,5,3,6,8,9]
 * ```
 *
 * ---
 *
 * **解题思路：**
 *
 * 由于矩阵元素是按 **对角线方向** 访问的，我们可以按照对角线编号（`d = 0, 1, 2, ...`）依次遍历：
 *
 * **1. 遍历对角线**
 * - 共有 `M + N - 1` 条对角线（从 `0` 开始编号）。
 * - **对角线的起点**：
 *   - `d < M` 时：起点在 **第一行** `(0, d)`
 *   - `d >= M` 时：起点在 **最后一列** `(d-M+1, M-1)`
 *
 * **2. 记录当前对角线元素**
 * - 使用 `ArrayList<Integer>` 存储当前对角线的元素。
 * - **向下遍历**：`r++`，`c--`（行增加，列减少）。
 *
 * **3. 反转偶数对角线**
 * - **偶数编号对角线** 从 **左下向右上遍历**（即 `reverse`）。
 * - **奇数编号对角线** 从 **右上向左下遍历**（无需 `reverse`）。
 * - 反转后，将当前对角线的元素依次存入 `result` 数组。
 *
 * ---
 * **示例解析**
 *
 * **输入：**
 * ```
 * matrix = [
 *   [1, 2, 3],
 *   [4, 5, 6],
 *   [7, 8, 9]
 * ]
 * ```
 *
 * **遍历过程**
 * ```
 * 对角线 0: [1]                     → [1]
 * 对角线 1: [2, 4]                  → [4, 2] (reverse)
 * 对角线 2: [3, 5, 7]               → [3, 5, 7]
 * 对角线 3: [6, 8]                  → [8, 6] (reverse)
 * 对角线 4: [9]                     → [9]
 * ```
 *
 * **最终输出**: `[1, 2, 4, 7, 5, 3, 6, 8, 9]`
 *
 * ---
 * **时间和空间复杂度分析**
 *
 * - **时间复杂度：O(M * N)**
 *   - 需要遍历整个矩阵，每个元素访问 **一次**，时间复杂度为 **O(M * N)**。
 *
 * - **空间复杂度：O(min(M, N))**
 *   - 额外的 `ArrayList<Integer>` **最多存储 `min(M, N)` 个元素**（单条对角线的长度）。
 */

public class LeetCode_498_DiagonalTraverse{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findDiagonalOrder(int[][] matrix) {

            // 检查矩阵是否为空
            if (matrix == null || matrix.length == 0) {
                return new int[0]; // 返回空数组
            }

            // 获取矩阵的行数 N 和列数 M
            int N = matrix.length;
            int M = matrix[0].length;

            // 结果数组，存储最终的对角线遍历结果
            int[] result = new int[N * M];
            int k = 0; // 用于存储结果数组的索引
            ArrayList<Integer> intermediate = new ArrayList<Integer>(); // 临时存储对角线元素

            // 遍历所有可能的对角线，共有 (N + M - 1) 条
            for (int d = 0; d < N + M - 1; d++) {

                // 清空临时数组，以存储新的对角线元素
                intermediate.clear();

                // 确定当前对角线的起点
                // 对角线的起点要么在第一行（r=0），要么在最后一列（c=M-1）
                int r = d < M ? 0 : d - M + 1; // 行索引
                int c = d < M ? d : M - 1;     // 列索引

                // 沿着对角线遍历，直到超出矩阵边界
                while (r < N && c > -1) {
                    intermediate.add(matrix[r][c]); // 添加元素到临时列表
                    ++r; // 向下移动
                    --c; // 向左移动
                }

                // 根据奇偶性决定是否反转当前对角线的元素顺序
                if (d % 2 == 0) {
                    Collections.reverse(intermediate); // 逆序处理偶数对角线
                }

                // 将当前对角线的元素添加到结果数组
                for (int i = 0; i < intermediate.size(); i++) {
                    result[k++] = intermediate.get(i);
                }
            }
            return result; // 返回最终遍历结果
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_498_DiagonalTraverse().new Solution();

        // 测试用例 1
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("对角线遍历结果: " + Arrays.toString(solution.findDiagonalOrder(matrix1)));
        // 预期输出: [1, 2, 4, 7, 5, 3, 6, 8, 9]

        // 测试用例 2
        int[][] matrix2 = {
                {1, 2},
                {3, 4}
        };
        System.out.println("对角线遍历结果: " + Arrays.toString(solution.findDiagonalOrder(matrix2)));
        // 预期输出: [1, 2, 3, 4]

        // 测试用例 3
        int[][] matrix3 = {
                {1}
        };
        System.out.println("对角线遍历结果: " + Arrays.toString(solution.findDiagonalOrder(matrix3)));
        // 预期输出: [1]
    }
}

/**
Given an m x n matrix mat, return an array of all the elements of the array in 
a diagonal order. 

 
 Example 1: 
 
 
Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
 

 Example 2: 

 
Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
 

 
 Constraints: 

 
 m == mat.length 
 n == mat[i].length 
 1 <= m, n <= 10⁴ 
 1 <= m * n <= 10⁴ 
 -10⁵ <= mat[i][j] <= 10⁵ 
 

 Related Topics Array Matrix Simulation 👍 3598 👎 719

*/
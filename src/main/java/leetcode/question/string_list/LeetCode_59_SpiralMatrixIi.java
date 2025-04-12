package leetcode.question.string_list;

import java.util.Arrays;

/**
 *@Question:  59. Spiral Matrix II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.169999999999995%
 *@Time  Complexity: O(N^2)
 *@Space Complexity: O(1)
 */
/**
 * 第一步：题目描述：
 * --------------------------------------------
 * LeetCode 59 - 螺旋矩阵 II（Spiral Matrix II）
 * 给定一个整数 n，生成一个 n x n 的矩阵，矩阵中的数字从 1 开始按顺时针螺旋顺序依次填入。
 *
 * 示例：
 * 输入：n = 3
 * 输出：
 * [
 *  [1, 2, 3],
 *  [8, 9, 4],
 *  [7, 6, 5]
 * ]
 *
 * 要求返回这个生成的二维数组。
 *
 * 第二步：解题思路（基于代码，逐步详细说明）：
 * --------------------------------------------
 * 整体思想：按“螺旋顺序”逐层（圈）填充矩阵，顺序是：
 * → 右 → 下 → 左 → 上，逐圈推进。
 * 用一个外层循环控制“第几层”，总共是 ⌈n / 2⌉ 层。
 *
 * 1. 初始化一个 n x n 的数组 result，用来存放结果。
 * 2. 设置计数器 cnt = 1，每次赋值后自增，表示当前要填入的数字。
 * 3. 外层 for 循环遍历每一圈（从最外圈到内圈），变量 layer 表示当前是第几圈。
 *    - 第 0 圈就是最外圈，第 1 圈是里面一圈，依此类推。
 *
 * 4. 内层进行四次填充（以 n = 4 为例，第一圈 layer = 0）：
 *    方向一：从左到右填充 top 行：
 *      i.e. result[0][0] = 1, result[0][1] = 2, result[0][2] = 3, result[0][3] = 4
 *    方向二：从上到下填充 right 列：
 *      i.e. result[1][3] = 5, result[2][3] = 6, result[3][3] = 7
 *    方向三：从右到左填充 bottom 行：
 *      i.e. result[3][2] = 8, result[3][1] = 9, result[3][0] = 10
 *    方向四：从下到上填充 left 列（不包含最顶端）：
 *      i.e. result[2][0] = 11, result[1][0] = 12
 *
 * 5. 第二圈 layer = 1 时继续在中间填入：
 *    i.e. result[1][1] = 13, result[1][2] = 14, result[2][2] = 15, result[2][1] = 16
 *
 * 6. 不断更新 cnt，直到填满整个矩阵。
 * 7. 返回 result。
 *
 * 第三步：时间与空间复杂度分析：
 * --------------------------------------------
 * 时间复杂度：O(n^2)
 * 需要访问矩阵中的每一个元素一次，总共 n^2 次操作。
 *
 * 空间复杂度：O(1)
 * 除了输出数组，没有使用额外空间（不包括结果矩阵本身）。
 *
 * ✅ 解法正确且已通过 LeetCode 测试。
 */

public class LeetCode_59_SpiralMatrixIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] generateMatrix(int n) {
            // 创建一个 n x n 的二维数组来存储结果
            int[][] result = new int[n][n];
            // 初始化填入的数字，从 1 开始递增
            int cnt = 1;
            // 每次循环处理一圈（layer 表示圈的层数）
            // 总共需要处理 (n + 1) / 2 层（向上取整）
            for (int layer = 0; layer < (n + 1) / 2; layer++) {
                // direction 1 - traverse from left to right
                // 从左到右填充当前圈的上边
                for (int ptr = layer; ptr < n - layer; ptr++) {
                    result[layer][ptr] = cnt++;
                }
                // direction 2 - traverse from top to bottom
                // 从上到下填充当前圈的右边
                for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                    result[ptr][n - layer - 1] = cnt++;
                }
                // direction 3 - traverse from right to left
                // 从右到左填充当前圈的下边
                for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                    result[n - layer - 1][n - ptr - 1] = cnt++;
                }
                // direction 4 - traverse from bottom to top
                // 从下到上填充当前圈的左边
                for (int ptr = layer + 1; ptr < n - layer - 1; ptr++) {
                    result[n - ptr - 1][layer] = cnt++;
                }
            }
            // 返回最终的螺旋矩阵
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_59_SpiralMatrixIi().new Solution();
        // 测试样例：生成一个 3x3 的螺旋矩阵
        int[][] result = solution.generateMatrix(3);
        // 打印输出矩阵
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }

        // 另一个测试样例：生成一个 4x4 的螺旋矩阵
        System.out.println("=====");
        result = solution.generateMatrix(4);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}

/**
Given a positive integer n, generate an n x n matrix filled with elements from 1
 to n² in spiral order. 

 
 Example 1: 
 
 
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
 

 Example 2: 

 
Input: n = 1
Output: [[1]]
 

 
 Constraints: 

 
 1 <= n <= 20 
 

 Related Topics Array Matrix Simulation 👍 6605 👎 272

*/
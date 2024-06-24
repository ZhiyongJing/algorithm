package leetcode.question.monotonic_stack_queue;

import java.util.Stack;

/**
 *@Question:  85. Maximal Rectangle
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 81.06%
 *@Time  Complexity: O(N*M)
 *@Space Complexity: O(M) M is length of row
 */

/**
 * 当解决 LeetCode 85 题 "Maximal Rectangle" 时，我们的主要思路是利用动态规划（DP）和柱状图中的最大矩形面积算法来解决。
 *
 * ### 解题思路详解：
 *
 * 1. **理解题目**：
 *    给定一个二维字符矩阵，矩阵中只包含 '0' 和 '1' 两种字符。我们需要找出矩阵中全为 '1' 的最大矩形面积。
 *
 * 2. **转化为柱状图问题**：
 *    将每一行看作是一个柱状图的底部，每个柱子的高度由当前行及其以上所有行的连续 '1' 的数量决定。例如，第 i 行的柱状图高度可以根据其上一行得出。
 *
 * 3. **使用动态规划**：
 *    - 维护一个数组 `heights`，其中 `heights[j]` 表示以当前行为底部，第 j 列的柱状图高度（即从当前行向上数连续 '1' 的数量）。
 *    - 对于每一行，根据上一行的 `heights` 数组更新当前行的 `heights` 数组。
 *
 * 4. **计算最大矩形面积**：
 *    - 对于每一行更新完 `heights` 数组后，将其视为柱状图，利用柱状图中最大矩形面积算法（84题）来计算当前行柱状图的最大矩形面积。
 *    - 每次更新最大面积时，取当前计算出的最大面积与之前计算的最大面积的较大值。
 *
 * 5. **复杂度分析**：
 *    - 时间复杂度：整体时间复杂度为 O(N*M)，其中 N 是矩阵的行数，M 是矩阵的列数。这是因为对于每个元素，我们都需要更新柱状图的高度和计算最大矩形面积，而这两个操作都是线性的。
 *    - 空间复杂度：空间复杂度为 O(M)，主要是由 `heights` 数组所使用的额外空间决定的，其中 M 是矩阵的列数。
 *
 * 通过以上步骤，我们可以高效地解决这个问题，并且在时间复杂度和空间复杂度上也能保持在合理的范围内。
 */
public class LeetCode_85_MaximalRectangle{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义一个内部方法，用于计算柱状图中的最大矩形面积
        public int leetcode84(int[] heights) {
            Stack<Integer> stack = new Stack<>(); // 使用栈来存储柱子的索引
            stack.push(-1); // 将-1作为栈底，方便计算宽度

            int maxarea = 0; // 初始化最大面积为0
            for (int i = 0; i < heights.length; ++i) {
                // 当栈不为空且当前柱子高度小于栈顶柱子高度时，计算面积
                while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                    int h = heights[stack.pop()]; // 弹出栈顶柱子的高度
                    int w = i - stack.peek() - 1; // 计算宽度，注意stack.peek()是左边界的索引
                    maxarea = Math.max(maxarea, h * w); // 更新最大面积
                }
                stack.push(i); // 将当前柱子索引压入栈中
            }
            // 处理栈中剩余的柱子
            while (stack.peek() != -1) {
                int h = heights[stack.pop()]; // 弹出栈顶柱子的高度
                int w = heights.length - stack.peek() - 1; // 计算宽度，注意这里使用heights.length作为右边界
                maxarea = Math.max(maxarea, h * w); // 更新最大面积
            }
            return maxarea; // 返回最大面积
        }

        // 主方法，用于计算给定二维字符矩阵的最大矩形面积
        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) return 0; // 空矩阵直接返回0
            int maxarea = 0; // 初始化最大面积为0
            int[] dp = new int[matrix[0].length]; // 用于存储当前行的柱状图高度信息

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    // 根据上一行的柱状图信息更新当前行的柱状图信息
                    dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
                }
                // 更新最大面积，调用leetcode84方法计算当前行柱状图的最大矩形面积
                maxarea = Math.max(maxarea, leetcode84(dp));
            }
            return maxarea; // 返回最大矩形面积
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_85_MaximalRectangle().new Solution();
        // TO TEST
         char[][] matrix = {
             {'1','0','1','0','0'},
             {'1','0','1','1','1'},
             {'1','1','1','1','1'},
             {'1','0','0','1','0'}
         };
         int maxArea = solution.maximalRectangle(matrix);
         System.out.println("最大矩形面积为：" + maxArea);
    }
}

/**
Given a rows x cols binary matrix filled with 0's and 1's, find the largest 
rectangle containing only 1's and return its area. 

 
 Example 1: 
 
 
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1
"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
 

 Example 2: 

 
Input: matrix = [["0"]]
Output: 0
 

 Example 3: 

 
Input: matrix = [["1"]]
Output: 1
 

 
 Constraints: 

 
 rows == matrix.length 
 cols == matrix[i].length 
 1 <= row, cols <= 200 
 matrix[i][j] is '0' or '1'. 
 

 Related Topics Array Dynamic Programming Stack Matrix Monotonic Stack 👍 10482 
👎 185

*/
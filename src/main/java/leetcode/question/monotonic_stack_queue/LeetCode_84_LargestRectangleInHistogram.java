package leetcode.question.monotonic_stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *@Question:  84. Largest Rectangle in Histogram
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 74.25%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * 当解决 LeetCode 84 题 "Largest Rectangle in Histogram" 时，我们使用了单调栈的算法来找到直方图中的最大矩形面积。
 *
 * ### 解题思路详解：
 *
 * 1. **问题理解**：
 *    给定一个直方图，每个柱子的高度由数组 `heights` 给出。我们需要找到直方图中的最大矩形，其面积是由柱子的高度和宽度决定的。
 *
 * 2. **单调栈的应用**：
 *    - 单调栈是一种特殊的栈结构，用于解决与数组或者列表相关的问题，其中维护的是一个递增或者递减的顺序。
 *    - 在该问题中，我们维护一个单调递增的栈，存储柱子的索引。栈顶元素始终表示当前正在考虑的矩形的最小高度的柱子。
 *
 * 3. **算法步骤**：
 *    - 创建一个空栈，并将 -1 压入栈底作为起始位置。
 *    - 遍历数组 `heights`，对于每个柱子高度，执行以下操作：
 *      - 如果当前柱子高度小于栈顶柱子的高度，说明可以计算以栈顶柱子为高度的矩形面积了。
 *      - 弹出栈顶元素，计算以该柱子为高度的矩形面积，宽度为当前柱子索引与新的栈顶元素索引之差减一。
 *      - 不断重复上述步骤，直到当前柱子高度大于等于栈顶柱子的高度，然后将当前柱子索引压入栈中。
 *    - 最后，处理栈中剩余的柱子，类似地计算以每个柱子为高度的矩形面积。
 *
 * 4. **计算最大矩形面积**：
 *    - 在遍历过程中，不断更新计算得到的最大矩形面积。
 *
 * 5. **复杂度分析**：
 *    - 时间复杂度：O(N)，其中 N 是数组 `heights` 的长度。每个柱子最多被压入和弹出栈一次，因此是线性的时间复杂度。
 *    - 空间复杂度：O(N)，主要取决于存储栈的空间，最坏情况下栈可能包含所有柱子的索引。
 *
 * 通过单调栈的算法，我们可以高效地解决该问题，保证了时间复杂度和空间复杂度的优秀性能。
 */
public class LeetCode_84_LargestRectangleInHistogram{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // 定义解题方法，计算直方图中的最大矩形面积
        public int largestRectangleArea(int[] heights) {
            Deque<Integer> stack = new ArrayDeque<>(); // 使用双端队列作为栈，存储柱子的索引
            stack.push(-1); // 将-1作为栈底，方便计算宽度

            int length = heights.length; // 获取直方图数组的长度
            int maxArea = 0; // 初始化最大面积为0

            for (int i = 0; i < length; i++) {
                // 当栈不为空且当前柱子高度小于栈顶柱子高度时，计算面积
                while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                    int currentHeight = heights[stack.pop()]; // 弹出栈顶柱子的高度
                    int currentWidth = i - stack.peek() - 1; // 计算宽度，注意stack.peek()是左边界的索引
                    maxArea = Math.max(maxArea, currentHeight * currentWidth); // 更新最大面积
                }
                stack.push(i); // 将当前柱子索引压入栈中
            }

            // 处理栈中剩余的柱子
            while (stack.peek() != -1) {
                int currentHeight = heights[stack.pop()]; // 弹出栈顶柱子的高度
                int currentWidth = length - stack.peek() - 1; // 计算宽度，注意这里使用length作为右边界
                maxArea = Math.max(maxArea, currentHeight * currentWidth); // 更新最大面积
            }

            return maxArea; // 返回最大矩形面积
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_84_LargestRectangleInHistogram().new Solution();
        // TO TEST
        // 这里可以添加测试用例进行验证，例如：
         int[] heights = {2, 1, 5, 6, 2, 3};
         int maxArea = solution.largestRectangleArea(heights);
         System.out.println("最大矩形面积为：" + maxArea);
    }
}

/**
Given an array of integers heights representing the histogram's bar height 
where the width of each bar is 1, return the area of the largest rectangle in the 
histogram. 

 
 Example 1: 
 
 
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
 

 Example 2: 
 
 
Input: heights = [2,4]
Output: 4
 

 
 Constraints: 

 
 1 <= heights.length <= 10⁵ 
 0 <= heights[i] <= 10⁴ 
 

 Related Topics Array Stack Monotonic Stack 👍 17104 👎 278

*/
package leetcode.question.stack;

import java.util.Stack;

/**
 *@Question:  155. Min Stack
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 74.0%
 *@Time  Complexity: O(1)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题的目标是设计一个支持 `push`、`pop`、`top` 和 `getMin` 操作的最小栈（Min Stack）。最小栈除了支持正常的栈操作外，还能在常数时间内返回栈中的最小元素。
 *
 * 为了实现这些功能，我们使用了两个栈：
 * 1. **主栈（stack）**：用于存储所有的元素。
 * 2. **辅助栈（minStack）**：用于存储每个元素进入栈时的最小值及其出现的频次。
 * 每个元素为一个二元数组 `[minValue, frequency]`，其中 `minValue` 是当前最小值，`frequency` 是该最小值的频次。
 *
 * #### 操作解释
 *
 * 1. **push(int x)**：
 *    - 将 `x` 推入主栈。
 *    - 如果辅助栈为空，或 `x` 小于当前辅助栈的栈顶元素，则将 `x` 和频次 `1` 推入辅助栈。
 *    - 如果 `x` 等于辅助栈的栈顶元素，则增加栈顶元素的频次。
 *
 * 2. **pop()**：
 *    - 从主栈弹出元素。
 *    - 如果弹出的元素等于辅助栈的栈顶元素，则减少栈顶元素的频次。如果频次变为 `0`，则从辅助栈弹出栈顶元素。
 *
 * 3. **top()**：
 *    - 返回主栈的栈顶元素。
 *
 * 4. **getMin()**：
 *    - 返回辅助栈的栈顶元素，即当前栈中的最小值。
 *
 * ### 时间复杂度
 *
 * - **push(x)**: O(1)
 * - **pop()**: O(1)
 * - **top()**: O(1)
 * - **getMin()**: O(1)
 *
 * 所有操作的时间复杂度均为 O(1)，因为它们都是对栈的操作，只涉及栈顶元素的添加、移除或访问。
 *
 * ### 空间复杂度
 *
 * - 空间复杂度主要取决于输入元素的数量。由于每个元素在主栈中出现一次，并且在辅助栈中可能出现一次（在最差情况下），
 * 空间复杂度为 O(N)。
 */
public class LeetCode_155_MinStack {

    // 定义一个类 MinStack，用于实现最小栈
    class MinStack {
        private Stack<Integer> stack = new Stack<>(); // 主栈，用于存储所有元素
        private Stack<int[]> minStack = new Stack<>(); // 辅助栈，用于存储当前最小值及其频次

        public MinStack() {}

        public void push(int x) {
            // 始终将元素压入主栈
            stack.push(x);

            // 如果 minStack 为空，或者 x 比当前 minStack 栈顶元素小，则将 x 及其频次 1 压入 minStack
            if (minStack.isEmpty() || x < minStack.peek()[0]) {
                minStack.push(new int[] { x, 1 });
            }
            // 如果 x 与 minStack 栈顶元素相同，则增加频次
            else if (x == minStack.peek()[0]) {
                minStack.peek()[1]++;
            }
        }

        public void pop() {
            // 如果主栈和 minStack 的栈顶元素相同，则减少 minStack 栈顶元素的频次
            if (stack.peek().equals(minStack.peek()[0])) {
                minStack.peek()[1]--;
            }

            // 如果 minStack 栈顶元素的频次为 0，则将其弹出
            if (minStack.peek()[1] == 0) {
                minStack.pop();
            }

            // 弹出主栈栈顶元素
            stack.pop();
        }

        public int top() {
            // 返回主栈的栈顶元素
            return stack.peek();
        }

        public int getMin() {
            // 返回 minStack 的栈顶元素，即当前栈中的最小值
            return minStack.peek()[0];
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        MinStack minStack = new LeetCode_155_MinStack().new MinStack();
        // 测试用例
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("getMin(): " + minStack.getMin()); // 输出: -3
        minStack.pop();
        System.out.println("top(): " + minStack.top()); // 输出: 0
        System.out.println("getMin(): " + minStack.getMin()); // 输出: -2
    }
}

/**
Design a stack that supports push, pop, top, and retrieving the minimum element 
in constant time. 

 Implement the MinStack class: 

 
 MinStack() initializes the stack object. 
 void push(int val) pushes the element val onto the stack. 
 void pop() removes the element on the top of the stack. 
 int top() gets the top element of the stack. 
 int getMin() retrieves the minimum element in the stack. 
 

 You must implement a solution with O(1) time complexity for each function. 

 
 Example 1: 

 
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
 

 
 Constraints: 

 
 -2³¹ <= val <= 2³¹ - 1 
 Methods pop, top and getMin operations will always be called on non-empty 
stacks. 
 At most 3 * 10⁴ calls will be made to push, pop, top, and getMin. 
 

 Related Topics Stack Design 👍 13493 👎 822

*/
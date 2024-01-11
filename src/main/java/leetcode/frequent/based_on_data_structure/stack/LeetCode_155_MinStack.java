package leetcode.frequent.based_on_data_structure.stack;

import java.util.Stack;

/**
  *@Question:  155. Min Stack     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 74.0%      
  *@Time  Complexity: O(1)
  *@Space Complexity: O(N)
 */

public class LeetCode_155_MinStack{
    
//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

    private Stack<int[]> stack = new Stack<>();

    public MinStack() { }


    public void push(int x) {

        /* If the stack is empty, then the min value
         * must just be the first value we add. */
        if (stack.isEmpty()) {
            stack.push(new int[]{x, x});
            return;
        }

        int currentMin = stack.peek()[1];
        stack.push(new int[]{x, Math.min(x, currentMin)});
    }


    public void pop() {
        stack.pop();
    }


    public int top() {
        return stack.peek()[0];
    }


    public int getMin() {
        return stack.peek()[1];
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
        MinStack solution = new LeetCode_155_MinStack().new MinStack();
        // TO TEST
        //solution.
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
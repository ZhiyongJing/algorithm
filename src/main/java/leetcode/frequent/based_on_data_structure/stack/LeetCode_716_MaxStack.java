package leetcode.frequent.based_on_data_structure.stack;

import java.util.*;

/**
  *@Question:  716. Max Stack     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 54.43%      
  *@Time  Complexity: O(logN) for push, pop, popMax, peekMax; O(1) for top
  *@Space Complexity: O(N)
 */
/**
 * **问题描述：**
 *
 * 设计一个支持 `push`, `pop`, `top`, `peekMax` 和 `popMax` 操作的栈，其中：
 *
 * - `push(x)` -- 将元素 `x` 推入栈中。
 * - `pop()` -- 移除栈顶元素并返回该元素。
 * - `top()` -- 返回栈顶元素。
 * - `peekMax()` -- 返回栈中最大元素。
 * - `popMax()` -- 返回栈中最大元素，并将其从栈中移除。如果有多个最大元素，则移除最近被推入栈的那个。
 *
 * **解题思路：**
 *
 * 为了实现 `popMax()` 操作，我们可以使用一个最大堆（PriorityQueue）来跟踪当前栈中的最大元素，堆顶即为当前最大元素。
 * 同时，我们还需要一个辅助的栈来保存所有的元素和它们的插入顺序。
 *
 * 具体步骤如下：
 *
 * 1. **初始化：** 使用一个辅助栈 `stack` 用于存储元素和它们的插入顺序，一个最大堆 `maxHeap` 用于存储当前栈中的最大元素。
 *
 * 2. **push操作：** 将元素 `x` 和插入顺序（通过计数器 `cnt` 实现）放入 `stack` 和 `maxHeap` 中。
 *
 * 3. **pop操作：** 弹出 `stack` 栈顶元素，并标记为已移除。如果该元素同时是 `maxHeap` 的堆顶元素，
 * 也从 `maxHeap` 中移除，保持最大堆的实时性。
 *
 * 4. **top操作：** 返回 `stack` 栈顶元素。
 *
 * 5. **peekMax操作：** 返回 `maxHeap` 的堆顶元素。
 *
 * 6. **popMax操作：** 弹出 `maxHeap` 的堆顶元素，并标记为已移除。然后在 `stack` 中找到并移除该元素，保持栈的一致性。
 *
 * **时间复杂度：**
 *
 * - `push` 操作的时间复杂度为 O(log N)，其中 N 是栈中元素的数量。
 * - `pop`、`top` 操作的时间复杂度为 O(1)。
 * - `peekMax` 和 `popMax` 操作的时间复杂度为 O(log N)。
 *
 * **空间复杂度：**
 *
 * - 使用了一个辅助栈 `stack` 和一个最大堆 `maxHeap`，它们的空间复杂度分别为 O(N) 和 O(N)，其中 N 是栈中元素的数量。
 */

public class LeetCode_716_MaxStack {

    //leetcode submit region begin(Prohibit modification and deletion)
    class MaxStack {
        // 存储元素的栈
        private Stack<int[]> stack;
        // 存储元素的最大堆
        private Queue<int[]> heap;
        // 记录已经被移除的元素
        private Set<Integer> removed;
        // 计数器，用于标记元素的插入顺序
        private int cnt;

        // 构造函数，初始化数据结构
        public MaxStack() {
            stack = new Stack<>();
            // 最大堆按照元素值和插入顺序降序排列
            heap = new PriorityQueue<>((a, b) -> b[0] - a[0] == 0 ? b[1] - a[1] : b[0] - a[0]);
            removed = new HashSet<>();
        }

        // 将元素推入栈中
        public void push(int x) {
            // 将元素和插入顺序加入栈和最大堆
            stack.add(new int[] { x, cnt });
            heap.add(new int[] { x, cnt });
            cnt++;
        }

        // 弹出栈顶元素
        public int pop() {
            // 移除已经被标记为移除的栈顶元素
            while (removed.contains(stack.peek()[1])) {
                stack.pop();
            }
            int[] top = stack.pop();
            removed.add(top[1]);
            return top[0];
        }

        // 获取栈顶元素
        public int top() {
            // 移除已经被标记为移除的栈顶元素
            while (removed.contains(stack.peek()[1])) {
                stack.pop();
            }
            return stack.peek()[0];
        }

        // 获取最大元素值
        public int peekMax() {
            // 移除已经被标记为移除的最大堆顶元素
            while (removed.contains(heap.peek()[1])) {
                heap.poll();
            }
            return heap.peek()[0];
        }

        // 移除并返回最大元素值
        public int popMax() {
            // 移除已经被标记为移除的最大堆顶元素
            while (removed.contains(heap.peek()[1])) {
                heap.poll();
            }
            int[] top = heap.poll();
            removed.add(top[1]);
            return top[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_716_MaxStack.MaxStack solution = new LeetCode_716_MaxStack().new MaxStack();

        // 测试代码
        solution.push(5);
        solution.push(1);
        solution.push(-5);
        int result1 = solution.top(); // 返回 5
        int result2 = solution.popMax(); // 返回 5
        int result3 = solution.top(); // 返回 1
        int result4 = solution.peekMax(); // 返回 1
        int result5 = solution.pop(); // 返回 1
        int result6 = solution.top(); // 返回 5
        System.out.println("栈顶元素: " + result1);
        System.out.println("移除最大元素: " + result2);
        System.out.println("栈顶元素: " + result3);
        System.out.println("最大元素: " + result4);
        System.out.println("弹出元素: " + result5);
        System.out.println("栈顶元素: " + result6);
    }
}

/**
Design a max stack data structure that supports the stack operations and 
supports finding the stack's maximum element. 

 Implement the MaxStack class: 

 
 MaxStack() Initializes the stack object. 
 void push(int x) Pushes element x onto the stack. 
 int pop() Removes the element on top of the stack and returns it. 
 int top() Gets the element on the top of the stack without removing it. 
 int peekMax() Retrieves the maximum element in the stack without removing it. 
 int popMax() Retrieves the maximum element in the stack and removes it. If 
there is more than one maximum element, only remove the top-most one. 
 

 You must come up with a solution that supports O(1) for each top call and O(
logn) for each other call. 

 
 Example 1: 

 
Input
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", 
"top"]
[[], [5], [1], [5], [], [], [], [], [], []]
Output
[null, null, null, null, 5, 5, 1, 5, 1, 5]

Explanation
MaxStack stk = new MaxStack();
stk.push(5);   // [5] the top of the stack and the maximum number is 5.
stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the 
maximum, because it is the top most one.
stk.top();     // return 5, [5, 1, 5] the stack did not change.
stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is 
different from the max.
stk.top();     // return 1, [5, 1] the stack did not change.
stk.peekMax(); // return 5, [5, 1] the stack did not change.
stk.pop();     // return 1, [5] the top of the stack and the max element is now 
5.
stk.top();     // return 5, [5] the stack did not change.
 

 
 Constraints: 

 
 -10⁷ <= x <= 10⁷ 
 At most 10⁵ calls will be made to push, pop, top, peekMax, and popMax. 
 There will be at least one element in the stack when pop, top, peekMax, or 
popMax is called. 
 

 Related Topics Linked List Stack Design Doubly-Linked List Ordered Set 👍 1876 
👎 504

*/
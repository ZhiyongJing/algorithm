package leetcode.question.stack;
import java.util.Stack;

/**
  *@Question:  232. Implement Queue using Stacks     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 35.74%      
  *@Time  Complexity: O(1) for all operations
  *@Space Complexity: O(n) for push, O(1) for others
 */

/**
 * **问题描述：**
 *
 * 设计一个使用栈作为底层数据结构的队列，实现队列的基本操作（push、pop、peek、empty）。
 *
 * **解题思路：**
 *
 * 1. 使用两个栈 s1 和 s2 分别作为主栈和辅助栈。s1 用于实现入队操作，s2 用于实现出队操作。
 *
 * 2. 入队操作（push）：将元素压入 s1 栈。
 *
 * 3. 出队操作（pop）：
 *    - 如果 s2 栈不为空，直接从 s2 出栈即可。
 *    - 如果 s2 栈为空，将 s1 栈中的所有元素逐个弹出并压入 s2 栈，然后从 s2 栈出栈。
 *
 * 4. 获取队头元素（peek）：
 *    - 如果 s2 栈不为空，直接返回 s2 栈顶元素。
 *    - 如果 s2 栈为空，将 s1 栈中的所有元素逐个弹出并压入 s2 栈，然后返回 s2 栈顶元素。
 *
 * 5. 判断队列是否为空（empty）：当 s1 和 s2 均为空时，队列为空。
 *
 * **时间复杂度：**
 *
 * - 入队操作（push）：O(1)，将元素压入 s1 栈。
 * - 出队操作（pop）：均摊时间复杂度为 O(1)，当 s2 不为空时直接出栈，当 s2 为空时需要将 s1 的元素逐个压入 s2。
 * - 获取队头元素（peek）：均摊时间复杂度为 O(1)，同样需要考虑 s1 和 s2 的状态。
 * - 判断队列是否为空（empty）：O(1)，直接判断 s1 和 s2 是否均为空。
 *
 * **空间复杂度：**
 *
 * - 需要额外使用两个栈 s1 和 s2，因此空间复杂度为 O(n)，其中 n 为队列中的元素数量。
 */


public class LeetCode_232_ImplementQueueUsingStacks {

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyQueue {
        // 使用两个栈来模拟队列的操作
        private Stack<Integer> s1 = new Stack<>();
        private Stack<Integer> s2 = new Stack<>();
        // 用于记录队头元素
        private int front;

        // 构造函数
        public MyQueue() {

        }

        // 元素入队操作
        public void push(int x) {
            // 如果s1为空，记录队头元素
            if (s1.empty())
                front = x;
            s1.push(x);
        }

        // 元素出队操作
        public int pop() {
            // 如果s2为空，将s1中的元素依次弹出并压入s2
            if (s2.isEmpty()) {
                while (!s1.isEmpty())
                    s2.push(s1.pop());
            }
            // 弹出s2的栈顶元素即为队头元素
            return s2.pop();
        }

        // 获取队头元素
        public int peek() {
            // 如果s2不为空，直接返回s2的栈顶元素
            if (!s2.isEmpty()) {
                return s2.peek();
            }
            // 否则返回事先记录的队头元素
            return front;
        }

        // 判断队列是否为空
        public boolean empty() {
            // 如果s1和s2均为空，队列为空
            return s1.isEmpty() && s2.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_232_ImplementQueueUsingStacks.MyQueue solution = new LeetCode_232_ImplementQueueUsingStacks().new MyQueue();

        // 测试代码
        solution.push(1);
        solution.push(2);
        int result1 = solution.peek(); // 返回 1
        int result2 = solution.pop(); // 返回 1
        boolean result3 = solution.empty(); // 返回 false
        System.out.println("队头元素: " + result1);
        System.out.println("出队元素: " + result2);
        System.out.println("队列是否为空: " + result3);
    }
}

/**
Implement a first in first out (FIFO) queue using only two stacks. The 
implemented queue should support all the functions of a normal queue (push, peek, pop, 
and empty). 

 Implement the MyQueue class: 

 
 void push(int x) Pushes element x to the back of the queue. 
 int pop() Removes the element from the front of the queue and returns it. 
 int peek() Returns the element at the front of the queue. 
 boolean empty() Returns true if the queue is empty, false otherwise. 
 

 Notes: 

 
 You must use only standard operations of a stack, which means only push to top,
 peek/pop from top, size, and is empty operations are valid. 
 Depending on your language, the stack may not be supported natively. You may 
simulate a stack using a list or deque (double-ended queue) as long as you use 
only a stack's standard operations. 
 

 
 Example 1: 

 
Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
 

 
 Constraints: 

 
 1 <= x <= 9 
 At most 100 calls will be made to push, pop, peek, and empty. 
 All the calls to pop and peek are valid. 
 

 
 Follow-up: Can you implement the queue such that each operation is amortized O(
1) time complexity? In other words, performing n operations will take overall O(
n) time even if one of those operations may take longer. 

 Related Topics Stack Design Queue 👍 6922 👎 393

*/
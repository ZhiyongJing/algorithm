package leetcode.question.map_set;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
  *@Question:  895. Maximum Frequency Stack     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 41.84%      
  *@Time  Complexity: O(1)
  *@Space Complexity: O(N)
 */

/**
 * **问题描述：**
 *
 * 设计一个支持以下操作的数据结构：
 *
 * 1. `push(int x)`：将整数 `x` 推入栈中。
 * 2. `pop()`：移除并返回栈中最频繁的元素。如果有多个元素出现频率相同，则返回最近被推入栈的那个。
 * 3. `pop()`：返回栈中最频繁的元素，而不移除它。
 *
 * **解题思路：**
 *
 * 这个问题可以通过使用两个哈希表实现，一个用于记录每个元素的频率，另一个用于记录相同频率的元素的栈。
 *
 * 1. **初始化：** 使用两个哈希表 `freq` 和 `group`，其中 `freq` 用于记录每个元素的频率，`group`
 * 用于记录相同频率的元素的栈。同时，使用变量 `maxfreq` 记录当前最大频率。
 *
 * 2. **push操作：** 当有新元素推入栈时，更新其频率，并将其加入对应频率的栈。如果更新后的频率大于 `maxfreq`，
 * 则更新 `maxfreq`。
 *
 * 3. **pop操作：** 从 `maxfreq` 对应的栈中弹出元素，更新其频率。如果弹出后栈为空，需要同时更新 `maxfreq`。
 *
 * **时间复杂度：**
 *
 * - push操作的时间复杂度为 O(1)。
 * - pop操作的时间复杂度取决于最大频率 `maxfreq`，最坏情况下为 O(N)，其中 N 是元素的总数量。
 *
 * **空间复杂度：**
 *
 * - 使用了两个哈希表，`freq` 和 `group`，它们的空间复杂度分别为 O(N) 和 O(N)。
 *
 * 总体空间复杂度为 O(N)。
 */

public class LeetCode_895_MaximumFrequencyStack {

    //leetcode submit region begin(Prohibit modification and deletion)
    class FreqStack {
        // 记录元素的频率
        Map<Integer, Integer> freq;
        // 记录相同频率的元素的栈
        Map<Integer, Stack<Integer>> group;
        // 当前最大的频率
        int maxfreq;

        // 构造函数，初始化数据结构
        public FreqStack() {
            freq = new HashMap();
            group = new HashMap();
            maxfreq = 0;
        }

        // 元素入栈操作
        public void push(int x) {
            // 获取当前元素的频率，如果没有则为0，然后加1
            int f = freq.getOrDefault(x, 0) + 1;
            freq.put(x, f);
            // 更新最大频率
            if (f > maxfreq)
                maxfreq = f;

            // 将元素放入对应频率的栈中， 如果key不存在，创建新的key, 添加对应的value并返回value，
            // 如果key存在，直接返回对应的值
            group.computeIfAbsent(f, z -> new Stack()).push(x);
        }

        // 元素出栈操作
        public int pop() {
            // 从当前最大频率的栈中弹出元素
            int x = group.get(maxfreq).pop();
            // 更新元素的频率
            freq.put(x, freq.get(x) - 1);
            // 如果当前最大频率的栈为空，更新最大频率
            if (group.get(maxfreq).size() == 0)
                maxfreq--;
            return x;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_895_MaximumFrequencyStack.FreqStack solution = new LeetCode_895_MaximumFrequencyStack().new FreqStack();

        // 测试代码
        solution.push(5);
        solution.push(7);
        solution.push(5);
        solution.push(7);
        solution.push(4);
        solution.push(5);
        int result1 = solution.pop(); // 返回 5
        int result2 = solution.pop(); // 返回 7
        int result3 = solution.pop(); // 返回 5
        System.out.println("出栈结果1: " + result1);
        System.out.println("出栈结果2: " + result2);
        System.out.println("出栈结果3: " + result3);
    }
}

/**
Design a stack-like data structure to push elements to the stack and pop the 
most frequent element from the stack. 

 Implement the FreqStack class: 

 
 FreqStack() constructs an empty frequency stack. 
 void push(int val) pushes an integer val onto the top of the stack. 
 int pop() removes and returns the most frequent element in the stack. 
 
 If there is a tie for the most frequent element, the element closest to the 
stack's top is removed and returned. 
 
 

 
 Example 1: 

 
Input
["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", 
"pop", "pop"]
[[], [5], [7], [5], [7], [4], [5], [], [], [], []]
Output
[null, null, null, null, null, null, null, 5, 7, 5, 4]

Explanation
FreqStack freqStack = new FreqStack();
freqStack.push(5); // The stack is [5]
freqStack.push(7); // The stack is [5,7]
freqStack.push(5); // The stack is [5,7,5]
freqStack.push(7); // The stack is [5,7,5,7]
freqStack.push(4); // The stack is [5,7,5,7,4]
freqStack.push(5); // The stack is [5,7,5,7,4,5]
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,
7,5,7,4].
freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is 
closest to the top. The stack becomes [5,7,5,4].
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,
7,4].
freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is 
closest to the top. The stack becomes [5,7].
 

 
 Constraints: 

 
 0 <= val <= 10⁹ 
 At most 2 * 10⁴ calls will be made to push and pop. 
 It is guaranteed that there will be at least one element in the stack before 
calling pop. 
 

 Related Topics Hash Table Stack Design Ordered Set 👍 4586 👎 66

*/
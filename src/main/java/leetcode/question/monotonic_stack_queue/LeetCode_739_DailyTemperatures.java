package leetcode.question.monotonic_stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *@Question:  739. Daily Temperatures
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 78.77%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * 当解决 LeetCode 739 题 "Daily Temperatures" 时，我们的主要思路是利用单调栈（Monotonic Stack）来找到每日温度数组中每个元素需要等待多少天才能遇到更高温度的问题。
 *
 * ### 解题思路详解：
 *
 * 1. **问题理解**：
 *    给定一个整数数组 `temperatures` 表示每日的温度值，我们需要计算每一天需要等待多少天才能找到比当前温度更高的温度。
 *
 * 2. **使用单调栈**：
 *    - 单调栈是一种特殊的栈结构，用于解决找到元素的下一个更大元素（Next Greater Element）的问题。
 *    - 在这个问题中，我们维护一个单调递减的栈，栈中存储的是数组 `temperatures` 的索引。
 *    - 栈顶元素始终是当前未找到更高温度的日期。
 *
 * 3. **算法步骤**：
 *    - 遍历数组 `temperatures`，对于每一天的温度：
 *      - 如果栈为空，将当前日期的索引压入栈中。
 *      - 如果当前日期的温度小于等于栈顶日期的温度，则将当前日期的索引压入栈中。
 *      - 如果当前日期的温度大于栈顶日期的温度，则说明找到了栈顶日期的下一个更高温度日期：
 *        - 弹出栈顶日期的索引，计算等待天数，并将结果存入对应位置的数组中。
 *        - 继续比较新的栈顶日期，直到栈为空或者当前日期的温度小于等于栈顶日期的温度。
 *      - 将当前日期的索引压入栈中，继续下一轮遍历。
 *
 * 4. **计算时间复杂度**：
 *    - 单次遍历数组是 O(N)，其中 N 是数组 `temperatures` 的长度。
 *    - 每个元素最多被压入和弹出栈一次，因此整体时间复杂度为 O(N)。
 *
 * 5. **计算空间复杂度**：
 *    - 使用了额外的空间存储结果数组 `answer` 和单调栈，它们的空间复杂度均为 O(N)，与数组 `temperatures` 的长度成正比。
 *
 * 通过以上算法，我们可以在线性时间内解决这个问题，并且空间复杂度也非常合理。
 * 单调栈的思想在解决类似的 Next Greater Element 问题时非常有效，可以帮助我们高效地找到数组中每个元素的下一个更大元素的位置。
 */
public class LeetCode_739_DailyTemperatures {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解题方法：计算每日温度的等待天数
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length; // 获取温度数组的长度
            int[] answer = new int[n]; // 创建一个数组用于存储结果
            Deque<Integer> stack = new ArrayDeque<>(); // 使用双端队列作为栈，存储温度数组的索引

            for (int currDay = 0; currDay < n; currDay++) {
                int currentTemp = temperatures[currDay]; // 获取当前日期的温度
                // 当栈不为空且当前日期的温度大于栈顶日期的温度时，计算等待天数
                while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) {
                    int prevDay = stack.pop(); // 弹出栈顶日期的索引
                    answer[prevDay] = currDay - prevDay; // 计算等待天数，并存入结果数组
                }
                stack.push(currDay); // 将当前日期的索引压入栈中
            }

            return answer; // 返回结果数组
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_739_DailyTemperatures().new Solution();

        // 测试样例
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = solution.dailyTemperatures(temperatures);
        System.out.print("等待天数数组：");
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

/**
Given an array of integers temperatures represents the daily temperatures, 
return an array answer such that answer[i] is the number of days you have to wait 
after the iᵗʰ day to get a warmer temperature. If there is no future day for which 
this is possible, keep answer[i] == 0 instead. 

 
 Example 1: 
 Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
 
 Example 2: 
 Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
 
 Example 3: 
 Input: temperatures = [30,60,90]
Output: [1,1,0]
 
 
 Constraints: 

 
 1 <= temperatures.length <= 10⁵ 
 30 <= temperatures[i] <= 100 
 

 Related Topics Array Stack Monotonic Stack 👍 13081 👎 317

*/
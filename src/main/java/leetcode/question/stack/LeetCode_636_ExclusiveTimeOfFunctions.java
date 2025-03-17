package leetcode.question.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *@Question:  636. Exclusive Time of Functions
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 73.75%
 *@Time Complexity: O(N)  // 遍历日志列表，每个日志处理一次
 *@Space Complexity: O(N/2) or O(N)  // 栈的最坏情况下存储 N/2 个函数调用
 */
/**
 * 题目描述：
 * LeetCode 636. Exclusive Time of Functions
 *
 * 给定 `n` 个函数，标号从 `0` 到 `n-1`，一个 **日志列表 `logs`** 记录了这些函数的 **开始 (start) 和结束 (end) 时间**。
 *
 * 每条日志的格式：
 * ```
 * "{function_id}:start:{timestamp}"  或  "{function_id}:end:{timestamp}"
 * ```
 * **要求：**
 * - 计算 **每个函数的独占时间**（即不包括被其他函数调用时的时间）。
 * - **嵌套调用** 也需要正确处理（即一个函数在执行时另一个函数可能会被调用）。
 *
 * **示例 1：**
 * ```
 * 输入:
 * n = 2
 * logs = [
 *     "0:start:0",
 *     "1:start:2",
 *     "1:end:5",
 *     "0:end:6"
 * ]
 * 输出: [3, 4]
 * 解释:
 * - 函数 `0` 在 `[0,1]` 运行，然后在 `[2,5]` 被 `1` 调用，最后在 `[6,6]` 运行，总时间 `3`。
 * - 函数 `1` 在 `[2,5]` 运行，时间 `4`。
 * ```
 *
 * ---
 *
 * **解题思路：**
 *
 * 由于 `logs` 记录了**函数的执行顺序**，我们可以 **使用栈 `stack` 追踪正在执行的函数**：
 *
 * **1. 遍历 `logs` 并解析每个日志**
 * - **入栈 (`start`)**：
 *   - 读取 `function_id` 和 `timestamp`，表示某个函数 `start` 运行。
 *   - 如果栈不为空：
 *     - 栈顶函数 **正在运行**，它的执行时间应当扣除 `timestamp - prev`，然后暂停。
 *   - 将 `function_id` 压入 `stack`，更新 `prev = timestamp`。
 *
 * - **出栈 (`end`)**：
 *   - 读取 `function_id` 和 `timestamp`，表示该函数 `end` 结束运行。
 *   - 栈顶函数 `stack.peek()` 运行至 `timestamp`，计算执行时间 `timestamp - prev + 1`。
 *   - 弹出 `stack`，表示该函数执行完成，继续执行前一个函数。
 *   - 更新 `prev = timestamp + 1`（`+1` 表示 `end` 时间包含最后一毫秒）。
 *
 * ---
 * **示例解析**
 *
 * **输入**
 * ```
 * logs = [
 *     "0:start:0",
 *     "1:start:2",
 *     "1:end:5",
 *     "0:end:6"
 * ]
 * ```
 * **栈变化**
 * ```
 * [0]     // 0 开始执行
 * [0,1]   // 1 开始执行，暂停 0
 * [0]     // 1 结束，计算 1 的执行时间 = 5 - 2 + 1 = 4
 * []      // 0 结束，计算 0 的执行时间 = 6 - 0 + 1 - 4 = 3
 * ```
 * **最终输出**
 * ```
 * [3, 4]
 * ```
 *
 * ---
 * **时间和空间复杂度分析**
 *
 * - **时间复杂度：O(N)**
 *   - 需要遍历 `logs` **一次**，每个日志只处理一次。
 *
 * - **空间复杂度：O(N)**
 *   - 需要 **栈 `stack`** 存储 `N/2` 个函数调用。
 *   - 额外的结果数组 **`O(N)`**。
 *
 * **总结**
 * - **使用栈追踪当前执行的函数**，遇到 `start` 入栈，`end` 计算时间并出栈。
 * - **时间复杂度 O(N)，空间复杂度 O(N)**，适用于**嵌套函数调用的独占时间计算问题**。
 */


public class LeetCode_636_ExclusiveTimeOfFunctions{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        public int[] exclusiveTime(int n, List < String > logs) {
            Stack < Integer > stack = new Stack < > (); // 栈用于存储当前正在执行的函数 ID
            int[] res = new int[n]; // 存储每个函数的独占时间
            String[] s = logs.get(0).split(":"); // 解析第一条日志
            stack.push(Integer.parseInt(s[0])); // 将第一个函数 ID 压入栈
            int i = 1, prev = Integer.parseInt(s[2]); // `prev` 记录上一个时间戳

            while (i < logs.size()) {
                s = logs.get(i).split(":"); // 解析当前日志
                if (s[1].equals("start")) { // 遇到 "start"
                    if (!stack.isEmpty()) // 栈顶函数需要计算当前运行时间
                        res[stack.peek()] += Integer.parseInt(s[2]) - prev;
                    stack.push(Integer.parseInt(s[0])); // 将当前函数 ID 压入栈
                    prev = Integer.parseInt(s[2]); // 更新 `prev` 时间戳
                } else { // 遇到 "end"
                    res[stack.peek()] += Integer.parseInt(s[2]) - prev + 1; // 计算当前函数运行时间
                    stack.pop(); // 弹出栈顶函数，表示当前函数执行完毕
                    prev = Integer.parseInt(s[2]) + 1; // 更新 `prev`，指向下一个时间片
                }
                i++;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_636_ExclusiveTimeOfFunctions().new Solution();

        // **测试用例 1**
        List<String> logs1 = Arrays.asList(
                "0:start:0",
                "1:start:2",
                "1:end:5",
                "0:end:6"
        );
        System.out.println("独占时间: " + Arrays.toString(solution.exclusiveTime(2, logs1)));
        // 预期输出: [3, 4]  (函数 0 执行 3 个时间单位，函数 1 执行 4 个时间单位)

        // **测试用例 2**
        List<String> logs2 = Arrays.asList(
                "0:start:0",
                "0:end:1",
                "1:start:2",
                "1:end:3"
        );
        System.out.println("独占时间: " + Arrays.toString(solution.exclusiveTime(2, logs2)));
        // 预期输出: [2, 2] (两个函数分别执行 2 个时间单位)
    }
}

/**
On a single-threaded CPU, we execute a program containing n functions. Each 
function has a unique ID between 0 and n-1. 

 Function calls are stored in a call stack: when a function call starts, its ID 
is pushed onto the stack, and when a function call ends, its ID is popped off 
the stack. The function whose ID is at the top of the stack is the current 
function being executed. Each time a function starts or ends, we write a log with the 
ID, whether it started or ended, and the timestamp. 

 You are given a list logs, where logs[i] represents the iᵗʰ log message 
formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:
start:3" means a function call with function ID 0 started at the beginning of 
timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the 
end of timestamp 2. Note that a function can be called multiple times, possibly 
recursively. 

 A function's exclusive time is the sum of execution times for all function 
calls in the program. For example, if a function is called twice, one call 
executing for 2 time units and another call executing for 1 time unit, the exclusive 
time is 2 + 1 = 3. 

 Return the exclusive time of each function in an array, where the value at the 
iᵗʰ index represents the exclusive time for the function with ID i. 

 
 Example 1: 
 
 
Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3,4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 for units of 
time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and 
ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit 
of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 
spends 4 units of total time executing.
 

 Example 2: 

 
Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0
:end:7"]
Output: [8]
Explanation:
Function 0 starts at the beginning of time 0, executes for 2 units of time, and 
recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4
 units of time.
Function 0 (initial call) resumes execution then immediately calls itself again.

Function 0 (2nd recursive call) starts at the beginning of time 6 and executes 
for 1 unit of time.
Function 0 (initial call) resumes execution at the beginning of time 7 and 
executes for 1 unit of time.
So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
 

 Example 3: 

 
Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0
:end:7"]
Output: [7,1]
Explanation:
Function 0 starts at the beginning of time 0, executes for 2 units of time, and 
recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4
 units of time.
Function 0 (initial call) resumes execution then immediately calls function 1.
Function 1 starts at the beginning of time 6, executes 1 unit of time, and ends 
at the end of time 6.
Function 0 resumes execution at the beginning of time 6 and executes for 2 
units of time.
So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1
 spends 1 unit of total time executing.
 

 
 Constraints: 

 
 1 <= n <= 100 
 1 <= logs.length <= 500 
 0 <= function_id < n 
 0 <= timestamp <= 10⁹ 
 No two start events will happen at the same timestamp. 
 No two end events will happen at the same timestamp. 
 Each function has an "end" log for each "start" log. 
 

 Related Topics Array Stack 👍 2039 👎 2874

*/
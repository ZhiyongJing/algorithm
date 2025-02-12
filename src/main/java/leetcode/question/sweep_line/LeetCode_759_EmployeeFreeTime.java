// package 语句，声明当前类所在的包
package leetcode.question.sweep_line;
// 导入 Java 需要的工具包

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *@Question:  759. Employee Free Time
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 57.11999999999999%
 *@Time  Complexity: O(N log N) N is the number of interval
 *@Space Complexity: O(N)
 */
/*
 * 一、题目描述
 *    在一组员工的工作时间表中，每个员工的日程安排由多个不重叠的时间区间 [start, end] 组成。
 *    要求找出所有员工共同的空闲时间段，即所有人都不工作的时间段，并返回这些空闲时间区间。
 *
 *    示例：
 *      输入：
 *      [[(1,3), (5,6)], [(2,3), (6,8)]]
 *      输出：
 *      [[3,5]] （所有人都空闲的时间段）
 *
 * 二、解题思路（超级详细）
 *    1. **事件排序 + 扫描线算法**
 *       - 由于所有时间区间都是不重叠的，所以可以将所有时间区间的起始和结束时间转换为 "事件"。
 *       - 使用 `int[] {time, type}` 表示一个事件，其中：
 *         - `type = 0` 表示“区间开始” (OPEN)
 *         - `type = 1` 表示“区间结束” (CLOSE)
 *       - 这样，我们可以将所有区间转换为一个**事件列表**，然后按照时间排序。
 *
 *    2. **排序规则**
 *       - 先按照时间 `event[0]` 升序排序
 *       - 如果时间相同，则按照 `event[1]` 升序排序（即 OPEN 事件优先于 CLOSE 事件）
 *
 *    3. **扫描线算法**
 *       - 维护一个 `bal` 变量来表示当前工作时间段的重叠数：
 *         - `bal += 1` 代表有员工开始工作
 *         - `bal -= 1` 代表有员工结束工作
 *       - 只有在 `bal == 0` 时，说明此时所有员工都不在工作，可以记录一个空闲时间段。
 *
 *    4. **具体执行步骤**
 *       - 遍历已排序的 `events` 数组：
 *         - 当 `bal == 0` 且 `prev >= 0` 时，说明 `prev` 到 `当前时间` 是一个有效的空闲时间段。
 *         - 更新 `bal` 的值（遇到 OPEN 加 1，遇到 CLOSE 减 1）
 *         - 记录 `prev = event[0]`，用于检查下一段是否仍然是空闲时间。
 *
 *    **举例解析**
 *    ```
 *    输入： [[(1,3), (5,6)], [(2,3), (6,8)]]
 *    转换为事件：
 *      [(1, OPEN), (3, CLOSE), (5, OPEN), (6, CLOSE)]
 *      [(2, OPEN), (3, CLOSE), (6, OPEN), (8, CLOSE)]
 *
 *    排序后：
 *      [(1, OPEN), (2, OPEN), (3, CLOSE), (3, CLOSE), (5, OPEN), (6, CLOSE), (6, OPEN), (8, CLOSE)]
 *
 *    扫描线计算：
 *    - 1: bal = 1
 *    - 2: bal = 2
 *    - 3: bal = 1 （第一次 CLOSE）
 *    - 3: bal = 0 （所有人都下班，prev=3）
 *    - 5: bal = 1 （发现 OPEN，空闲区间 [3,5]）
 *    - 6: bal = 0
 *    - 6: bal = 1
 *    - 8: bal = 0
 *    结果： [[3,5]]
 *    ```
 *
 * 三、时间和空间复杂度分析
 *    1. **时间复杂度**
 *       - 构造 `events` 数组需要 O(N) 遍历所有区间。
 *       - 事件排序的复杂度是 O(N log N)。
 *       - 扫描所有事件需要 O(N)。
 *       - 总体时间复杂度为 **O(N log N)**。
 *
 *    2. **空间复杂度**
 *       - 额外存储 `events` 数组，需要 O(N) 额外空间。
 *       - 存储最终的 `ans` 结果，最坏情况也需要 O(N)。
 *       - 因此空间复杂度为 **O(N)**。
 */


// 定义公共类 LeetCode_759_EmployeeFreeTime
public class LeetCode_759_EmployeeFreeTime{
    static class Interval {
        public int start; // 区间起始时间
        public int end;   // 区间结束时间

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };
// leetcode 提交区域开始（不可修改）
//leetcode submit region begin(Prohibit modification and deletion)

    // 定义区间类 Interval


    // 定义 Solution 内部类
    class Solution {
        // 计算员工的空闲时间区间
        public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
            int OPEN = 0, CLOSE = 1; // 定义开始和结束事件类型

            // 存储所有时间事件（开始和结束）
            List<int[]> events = new ArrayList();
            for (List<Interval> employee: avails) // 遍历每个员工的时间安排
                for (Interval iv: employee) {
                    events.add(new int[]{iv.start, OPEN});  // 记录区间开始时间
                    events.add(new int[]{iv.end, CLOSE});   // 记录区间结束时间
                }

            // 对所有时间事件进行排序：
            // 1. 先按照时间排序
            // 2. 若时间相同，则优先处理开始事件（OPEN 在前）
            Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

            List<Interval> ans = new ArrayList(); // 存储最终的空闲时间区间
            int prev = -1, bal = 0; // 记录上一个时间点和当前的区间平衡值

            for (int[] event: events) {
                // event[0] = 时间点，event[1] = 事件类型
                // 只有在没有员工工作的时间段（bal == 0）且 prev >= 0 时，才记录空闲时间
                if (bal == 0 && prev >= 0)
                    ans.add(new Interval(prev, event[0]));

                // 更新区间平衡值，若是 OPEN，表示进入新的工作时间 +1，否则结束 -1
                bal += event[1] == OPEN ? 1 : -1;

                // 更新 prev，继续处理下一个事件
                prev = event[0];
            }

            return ans; // 返回最终的空闲时间区间
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    // main 方法，用于测试代码
    public static void main(String[] args) {
        // 创建 Solution 实例
        Solution solution = new LeetCode_759_EmployeeFreeTime().new Solution();

        // 构造测试用例
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1, 3), new Interval(5, 6)));
        schedule.add(Arrays.asList(new Interval(2, 3), new Interval(6, 8)));

        // 预期输出: [[3, 5]] (3-5 是所有人都空闲的时间段)
        List<Interval> result = solution.employeeFreeTime(schedule);
        System.out.println("Test Case 1: ");
        for (Interval interval : result) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }
}

/**
We are given a list schedule of employees, which represents the working time 
for each employee. 

 Each employee has a list of non-overlapping Intervals, and these intervals are 
in sorted order. 

 Return the list of finite intervals representing common, positive-length free 
time for all employees, also in sorted order. 

 (Even though we are representing Intervals in the form [x, y], the objects 
inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, 
schedule[0][0].end = 2, and schedule[0][0][0] is not defined). Also, we wouldn't 
include intervals like [5, 5] in our answer, as they have zero length. 

 
 Example 1: 

 
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
 

 Example 2: 

 
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
 

 
 Constraints: 

 
 1 <= schedule.length , schedule[i].length <= 50 
 0 <= schedule[i].start < schedule[i].end <= 10^8 
 

 Related Topics Array Sorting Heap (Priority Queue) 👍 1919 👎 138

*/
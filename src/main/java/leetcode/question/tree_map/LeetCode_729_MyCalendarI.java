package leetcode.question.tree_map;

import java.util.TreeMap;

/**
 *@Question:  729. My Calendar I
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 43.52%
 *@Time  Complexity: O(logN)
 *@Space Complexity: O(N) N 是日历中已经预定的事件数量
 */

/**
 * 题目要求实现一个日历系统，能够预定事件并检查是否有重叠的事件。这里使用了 `TreeMap` 数据结构来存储已经预定的事件，其中 `key` 表示事件的起始时间，`value` 表示事件的结束时间。
 *
 * ### 解题思路：
 *
 * 1. **数据结构选择：**
 *    - 使用 `TreeMap<Integer, Integer>` 是因为它能够自动按照 `key` 的顺序排序，这样能够方便地进行区间的查找和插入操作。
 *
 * 2. **预定事件的逻辑：**
 *    - 对于要预定的新事件 `(start, end)`，首先查找 TreeMap 中小于等于 `start` 的最大 `key`，即使用 `floorKey(start)` 方法。
 *    - 同时查找 TreeMap 中大于等于 `start` 的最小 `key`，即使用 `ceilingKey(start)` 方法。
 *    - 如果前一个事件的结束时间小于等于 `start`，并且后一个事件的起始时间大于等于 `end`，则当前事件与已有事件不重叠，可以成功预定。
 *
 * 3. **时间复杂度分析：**
 *    - `TreeMap` 的 `floorKey`、`ceilingKey`、`put` 操作的平均时间复杂度是 `O(log N)`，其中 `N` 是当前已经预定的事件数量。
 *    - 因此，每次预定操作的时间复杂度是 `O(log N)`。
 *
 * 4. **空间复杂度分析：**
 *    - 空间复杂度主要取决于存储已经预定的事件所需的空间，即 `O(N)`，其中 `N` 是已经预定的事件数量。
 *
 * ### 总结：
 *
 * 这种基于 `TreeMap` 的解决方案能够高效地支持预定事件的操作，并且能够在 `O(log N)` 的时间内完成预定和检查操作。
 * 同时，空间复杂度也是合理的 `O(N)`。这种设计适合于需要频繁进行区间重叠检查的场景，保证了操作的效率和性能。
 */
public class LeetCode_729_MyCalendarI{

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyCalendar {
        TreeMap<Integer, Integer> calendar;

        MyCalendar() {
            calendar = new TreeMap();
        }

        public boolean book(int start, int end) {
            // 找到日历中比当前预定开始时间早的最后一个事件
            Integer prev = calendar.floorKey(start);
            // 找到日历中比当前预定开始时间晚的第一个事件
            Integer next = calendar.ceilingKey(start);

            // 检查是否当前预定时间段与前一个事件和后一个事件没有重叠
            if ((prev == null || calendar.get(prev) <= start) &&
                    (next == null || end <= next)) {
                // 满足条件则将当前时间段添加到日历中
                calendar.put(start, end);
                return true;
            }
            // 否则返回false，表示预定失败
            return false;
        }
    }

    /**
     * Your MyCalendar object will be instantiated and called as such:
     * MyCalendar obj = new MyCalendar();
     * boolean param_1 = obj.book(start,end);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_729_MyCalendarI.MyCalendar calendar = new LeetCode_729_MyCalendarI().new MyCalendar();

        // 测试用例
        System.out.println(calendar.book(10, 20)); // 预定成功，返回true
        System.out.println(calendar.book(15, 25)); // 预定失败，返回false
        System.out.println(calendar.book(20, 30)); // 预定成功，返回true
    }
}

/**
You are implementing a program to use as your calendar. We can add a new event 
if adding the event will not cause a double booking. 

 A double booking happens when two events have some non-empty intersection (i.e.
, some moment is common to both events.). 

 The event can be represented as a pair of integers start and end that 
represents a booking on the half-open interval [start, end), the range of real numbers x 
such that start <= x < end. 

 Implement the MyCalendar class: 

 
 MyCalendar() Initializes the calendar object. 
 boolean book(int start, int end) Returns true if the event can be added to the 
calendar successfully without causing a double booking. Otherwise, return false 
and do not add the event to the calendar. 
 

 
 Example 1: 

 
Input
["MyCalendar", "book", "book", "book"]
[[], [10, 20], [15, 25], [20, 30]]
Output
[null, true, false, true]

Explanation
MyCalendar myCalendar = new MyCalendar();
myCalendar.book(10, 20); // return True
myCalendar.book(15, 25); // return False, It can not be booked because time 15 
is already booked by another event.
myCalendar.book(20, 30); // return True, The event can be booked, as the first 
event takes every time less than 20, but not including 20. 

 
 Constraints: 

 
 0 <= start < end <= 10⁹ 
 At most 1000 calls will be made to book. 
 

 Related Topics Array Binary Search Design Segment Tree Ordered Set 👍 4197 👎 1
10

*/
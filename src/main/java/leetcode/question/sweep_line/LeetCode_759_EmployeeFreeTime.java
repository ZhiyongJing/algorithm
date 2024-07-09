package leetcode.question.sweep_line;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *@Question:  759. Employee Free Time
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N log K) 其中 N 是所有时间间隔的总数，K 是员工的数量
 *@Space Complexity: O(N)
 */
/**
 * ### 解题思路详细讲解
 *
 * #### 问题理解
 * 给定每个员工的工作时间表，找到所有员工的空闲时间，即每个员工的工作时间中没有重叠的时间段。每个员工的工作时间都是按照时间顺序排列的。
 *
 * #### 解题步骤
 *
 * 1. **使用优先队列（最小堆）来排序区间**：
 *    - 创建一个优先队列（最小堆），优先级是每个区间的开始时间。
 *    - 对于每个员工，将他们的第一个工作区间加入优先队列。
 *
 * 2. **遍历优先队列，合并区间**：
 *    - 使用一个变量 `prev` 来记录前一个区间的结束时间，初始化为优先队列中最早的开始时间。
 *    - 每次从优先队列中取出最早开始的区间，比较这个区间的开始时间与 `prev` 之间的关系。
 *    - 如果当前区间的开始时间大于 `prev`，说明存在一个空闲时间段，将这个空闲时间段加入结果列表。
 *    - 更新 `prev` 为当前区间的结束时间与 `prev` 中的较大值。
 *    - 如果当前员工还有更多的区间，将下一个区间加入优先队列。
 *
 * 3. **处理剩余区间**：
 *    - 如果优先队列中还有未处理的区间，继续从优先队列中取出区间并处理。
 *
 * 4. **返回结果**：
 *    - 最终的结果列表包含所有员工的空闲时间段。
 *
 * ### 时间和空间复杂度分析
 *
 * #### 时间复杂度：
 * - **初始化优先队列**：将每个员工的第一个区间加入优先队列，时间复杂度为 O(K)，其中 K 是员工的数量。
 * - **遍历优先队列**：每个区间最多进出优先队列一次，优先队列的操作（插入和删除）时间复杂度为 O(log K)。因为每个员工最多有 N 个区间，总的时间复杂度为 O(N log K)，其中 N 是所有区间的总数。
 *
 * #### 空间复杂度：
 * - **优先队列的空间复杂度**：优先队列最多包含 K 个元素（每个员工一个当前区间），空间复杂度为 O(K)。
 * - **结果列表的空间复杂度**：结果列表存储所有的空闲时间段，最坏情况下的空间复杂度为 O(N)。
 * - 因此，总的空间复杂度为 O(N)，其中 N 是所有区间的总数。
 *
 * 通过这种方法，可以有效地找到所有员工的空闲时间段，保证时间和空间复杂度在合理范围内。
 */

public class LeetCode_759_EmployeeFreeTime{

    // 定义区间类
    class Interval {
        public int start;
        public int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
            // 创建优先队列，根据区间开始时间排序
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                    schedule.get(a[0]).get(a[1]).start - schedule.get(b[0]).get(b[1]).start);

            // 初始化优先队列，存储每个员工的第一个区间
            for (int i = 0; i < schedule.size(); i++) {
                pq.add(new int[] {i, 0});
            }

            List<Interval> res = new ArrayList<>(); // 存储结果的列表
            // 记录第一个区间的开始时间
            int prev = schedule.get(pq.peek()[0]).get(pq.peek()[1]).start;

            // 遍历优先队列
            while (!pq.isEmpty()) {
                int[] index = pq.poll(); // 获取优先队列中最小开始时间的区间
                Interval interval = schedule.get(index[0]).get(index[1]); // 当前区间

                // 如果当前区间的开始时间大于上一个区间的结束时间，则存在空闲时间
                if (interval.start > prev) {
                    res.add(new Interval(prev, interval.start)); // 添加空闲时间到结果中
                }

                // 更新上一个区间的结束时间
                prev = Math.max(prev, interval.end);

                // 如果当前员工还有下一个区间，将下一个区间加入优先队列
                if (schedule.get(index[0]).size() > index[1] + 1) {
                    pq.add(new int[] {index[0], index[1] + 1});
                }
            }
            return res; // 返回结果列表
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_759_EmployeeFreeTime().new Solution();

        // 测试样例
        List<List<Interval>> schedule = new ArrayList<>();

        List<Interval> employee1 = new ArrayList<>();
        employee1.add(new LeetCode_759_EmployeeFreeTime().new Interval(1, 2));
        employee1.add(new LeetCode_759_EmployeeFreeTime().new Interval(5, 6));

        List<Interval> employee2 = new ArrayList<>();
        employee2.add(new LeetCode_759_EmployeeFreeTime().new Interval(1, 3));

        List<Interval> employee3 = new ArrayList<>();
        employee3.add(new LeetCode_759_EmployeeFreeTime().new Interval(4, 10));

        schedule.add(employee1);
        schedule.add(employee2);
        schedule.add(employee3);

        List<Interval> freeTimes = solution.employeeFreeTime(schedule);

        // 输出结果
        for (Interval interval : freeTimes) {
            System.out.println("Free time from " + interval.start + " to " + interval.end);
        }
        // 预期输出: Free time from 3 to 4
    }
}

/**
Related Topics Array Sorting Heap (Priority Queue) 👍 1895 👎 133

*/
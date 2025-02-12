package leetcode.question.heap;

import java.util.*;
/**
 *@Question:  253. Meeting Rooms II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 84.42%
 *@Time  Complexity: O(NlogN)
 *@Space Complexity: O(N)
 */
/**
 * 这段代码解决了LeetCode问题253：“Meeting Rooms II”。这个问题要求计算出需要多少个会议室才能安排所有的会议，
 * 即在同一时间不会有会议冲突。
 *
 * ### 解题思路：
 *
 * 1. **排序：** 首先，我们对所有的会议按照开始时间进行排序，这样我们可以一次性地处理所有的会议，并确保我们以正确的顺序来考虑它们。
 *
 * 2. **最小堆：** 然后，我们使用一个最小堆来跟踪会议室的使用情况。堆中存放的是当前会议室的结束时间，以便我们可以快速找到最早空闲的会议室。
 *
 * 3. **遍历会议：** 遍历每个会议，对于每个会议，我们都检查最早空闲的会议室。如果当前会议的开始时间晚于最早空闲会议室的结束时间，
 * 则该会议可以使用之前的会议室而不需要额外的房间。如果当前会议的开始时间早于最早空闲会议室的结束时间，则我们需要分配一个新的会议室，
 * 并将该会议室的结束时间加入到堆中。
 *
 * 4. **结果：** 最后，我们堆的大小即为所需的最少会议室数量，因为堆中的元素数量即为当前正在使用的会议室数量。
 *
 * ### 时间复杂度：
 * - **排序：** 对所有的会议按照开始时间进行排序的时间复杂度为 O(NlogN)，其中 N 是会议的数量。
 * - **遍历会议：** 遍历所有会议需要线性时间 O(N)。
 * 因此，总的时间复杂度为 O(NlogN)。
 *
 * ### 空间复杂度：
 * - 使用了一个大小为 N 的最小堆来存储会议室的结束时间，因此空间复杂度为 O(N)。
 *
 * 这就是整个解题思路及时间、空间复杂度的详细解释。
 */


public class LeetCode_253_MeetingRoomsIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 方法1：最小堆
        public int minMeetingRooms1(int[][] intervals) {

            // 检查基本情况。如果没有会议，返回 0
            if (intervals.length == 0) {
                return 0;
            }

            // 最小堆
            PriorityQueue<Integer> allocator =
                    new PriorityQueue<Integer>(
                            intervals.length,
                            new Comparator<Integer>() {
                                // 自定义比较器，比较两个整数，用于最小堆
                                public int compare(Integer a, Integer b) {
                                    return a - b;
                                }
                            });
//            PriorityQueue<Integer> allocator =
//                    new PriorityQueue<>((a, b) -> a - b);

            // 按照开始时间对会议进行排序
            Arrays.sort(
                    intervals,
                    new Comparator<int[]>() {
                        // 自定义比较器，按照会议的开始时间进行比较
                        public int compare(final int[] a, final int[] b) {
                            return a[0] - b[0];
                        }
                    });

            // 添加第一个会议的结束时间到堆中
            allocator.add(intervals[0][1]);

            // 遍历剩余的会议
            for (int i = 1; i < intervals.length; i++) {

                // 如果最早空闲的房间空闲了，则将该房间分配给这个会议。
                if (intervals[i][0] >= allocator.peek()) {
                    allocator.poll();
                }

                // 如果需要分配一个新的房间，则将结束时间添加到堆中，
                // 如果分配了旧的房间，则更新该房间的结束时间后再添加到堆中。
                allocator.add(intervals[i][1]);
            }

            // 堆的大小告诉我们需要多少个房间来容纳所有的会议。
            return allocator.size();
        }

        //方法2: sweep LINE
        // 定义一个内部类 Node，用于表示时间点的事件
        private class Node {
            public int time; // 事件发生的时间点
            public int flag; // 标志位，1 表示飞机起飞，-1 表示飞机降落

            // 构造函数，初始化时间和标志位
            public Node(int time, int flag) {
                this.time = time;
                this.flag = flag;
            }
        }
        public int minMeetingRooms(int[][] intervals) {
            int count = 0; // 记录当前空中的飞机数量
            List<Node> list = new ArrayList<Node>(); // 存储所有起飞和降落的时间事件

            // 遍历所有飞机的起飞和降落时间
            for(int[] interval: intervals) {
                // 将起飞时间作为一个事件，flag = 1
                list.add(new Node(interval[0], 1));
                // 将降落时间作为一个事件，flag = -1
                list.add(new Node(interval[1], -1));
            }

            // 对所有时间点进行排序：
            // 1. 先按时间从小到大排序
            // 2. 如果时间相同，则按照 flag 升序排序（-1 先于 1），保证同一时刻先降落再起飞
            Collections.sort(list, (a, b) -> a.time != b.time ? a.time - b.time : a.flag - b.flag);

            int maxplane = 0; // 记录历史最大同时在空中的飞机数

            // 遍历所有事件
            for(int i = 0; i < list.size(); i++) {
                Node node = list.get(i);

                // 如果是起飞事件，空中的飞机数 +1
                if(node.flag == 1) {
                    count++;
                }
                // 如果是降落事件，空中的飞机数 -1
                else {
                    count--;
                }

                // 更新最大同时在空中的飞机数
                maxplane = Math.max(maxplane, count);
            }

            return maxplane;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_253_MeetingRoomsIi().new Solution();
        // TO TEST
        // 测试代码
        //solution.
    }
}

/**
 Given an array of meeting time intervals intervals where intervals[i] = [starti,
 endi], return the minimum number of conference rooms required.


 Example 1:
 Input: intervals = [[0,30],[5,10],[15,20]]
 Output: 2

 Example 2:
 Input: intervals = [[7,10],[2,4]]
 Output: 1


 Constraints:


 1 <= intervals.length <= 10⁴
 0 <= starti < endi <= 10⁶


 Related Topics Array Two Pointers Greedy Sorting Heap (Priority Queue) Prefix
 Sum 👍 6891 👎 158

 */
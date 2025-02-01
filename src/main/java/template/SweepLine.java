package template;

import java.util.*;

public class SweepLine {

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

    /**Solution1
     * 计算某一时刻在空中的最大飞机数
     * @param airplanes 一个二维列表，每个子列表 [start, end] 代表飞机的起飞和降落时间
     * @return 最大同时在空中的飞机数量
     */
    public int countOfAirplanes(List<List<Integer>> airplanes) {
        int count = 0; // 记录当前空中的飞机数量
        List<Node> list = new ArrayList<Node>(); // 存储所有起飞和降落的时间事件

        // 遍历所有飞机的起飞和降落时间
        for(List<Integer> interval: airplanes) {
            // 将起飞时间作为一个事件，flag = 1
            list.add(new Node(interval.get(0), 1));
            // 将降落时间作为一个事件，flag = -1
            list.add(new Node(interval.get(1), -1));
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

    //Solution2
    public int minMeetingRooms(int[][] intervals) {

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
        return allocator.size();}

    // 主函数，添加测试用例
    public static void main(String[] args) {
        SweepLine sl = new SweepLine();

        // 测试用例，包含多个飞机的起飞和降落时间
        List<List<Integer>> airplanes = new ArrayList<>();
        airplanes.add(Arrays.asList(1, 10));
        airplanes.add(Arrays.asList(2, 3));
        airplanes.add(Arrays.asList(5, 8));
        airplanes.add(Arrays.asList(4, 7));

        // 计算最大同时在空中的飞机数量并打印结果
        int result = sl.countOfAirplanes(airplanes);
        System.out.println("最大同时在空中的飞机数: " + result); // 预期输出: 3
    }
}

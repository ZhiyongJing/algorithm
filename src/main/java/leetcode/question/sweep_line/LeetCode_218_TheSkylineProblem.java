package leetcode.question.sweep_line;

import java.util.*;

/**
 *@Question:  218. The Skyline Problem
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 43.82%
 *@Time  Complexity: O(n log n)
 *@Space Complexity: O(n)
 */

/**
 * ### 解题思路
 *
 * 1. **提取和排序边界**：
 *    - 首先提取每个建筑物的左右边界，并将它们存储在一个列表中。每个边界由其横坐标和对应的建筑物索引组成。
 *    - 将这些边界按照横坐标进行排序。这样确保我们从左到右逐个处理建筑物。
 *
 * 2. **初始化优先队列和结果列表**：
 *    - 使用一个最大堆（优先队列）来存储当前正在处理的建筑物的高度和右边界。这帮助我们动态维护当前的最高建筑物。
 *    - 初始化一个空的列表来存储最终的天际线关键点。
 *
 * 3. **遍历边界**：
 *    - 遍历排序后的边界列表：
 *      - 如果当前边界是某建筑物的左边界，表示一个新的建筑物开始，将其高度和右边界加入优先队列。
 *      - 如果当前边界是某建筑物的右边界，表示一个建筑物结束，从优先队列中移除其高度和右边界。
 *    - 在处理每个边界时，如果当前最高建筑物高度发生变化，记录当前的横坐标和新的高度作为一个关键点。
 *
 * 4. **移除过期建筑物**：
 *    - 在处理每个边界时，移除优先队列中所有右边界小于等于当前横坐标的建筑物。这些建筑物已经不再影响当前的天际线。
 *
 * 5. **记录关键点**：
 *    - 如果当前最高建筑物高度与之前记录的高度不同，则记录当前的横坐标和新的高度作为一个天际线关键点。
 *
 * ### 时间复杂度
 *
 * - **提取和排序边界**：
 *   - 提取边界的时间复杂度是 `O(n)`，其中 `n` 是建筑物的数量。
 *   - 排序边界的时间复杂度是 `O(n log n)`，因为我们对所有边界进行排序。
 *
 * - **遍历边界和维护优先队列**：
 *   - 遍历边界的时间复杂度是 `O(n log n)`，因为我们需要对每个边界进行操作，并且每次插入或删除操作的复杂度是 `O(log n)`。
 *
 * 综合起来，整体时间复杂度是 `O(n log n)`。
 *
 * ### 空间复杂度
 *
 * - **边界列表和优先队列**：
 *   - 边界列表和优先队列都需要存储所有的边界信息，空间复杂度是 `O(n)`。
 *   - 结果列表的空间复杂度在最坏情况下也是 `O(n)`，因为每个建筑物可能都会生成一个关键点。
 *
 * 综合起来，整体空间复杂度是 `O(n)`。
 */
public class LeetCode_218_TheSkylineProblem{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            // 初始化边界列表
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < buildings.length; ++i){
                edges.add(Arrays.asList(buildings[i][0], i)); // 添加建筑物左边界
                edges.add(Arrays.asList(buildings[i][1], i)); // 添加建筑物右边界
            }
            System.out.println("initinal edges is:" + edges);
            // 对边界进行排序，按横坐标升序排列
            Collections.sort(edges, (a, b) -> a.get(0) - b.get(0));
            System.out.println("sorted edges is:" + edges);

            // 初始化优先队列，用于存储正在处理的建筑物，按高度降序排列
            Queue<List<Integer>> live = new PriorityQueue<>((a, b) -> b.get(0) - a.get(0));
            // 初始化答案列表
            List<List<Integer>> answer = new ArrayList<>();

            int idx = 0; // 当前处理的边界索引

            // 遍历所有的边界
            while (idx < edges.size()){
                // 当前处理的横坐标
                int currX = edges.get(idx).get(0);

                // 处理所有相同横坐标的边界
                while (idx < edges.size() && edges.get(idx).get(0) == currX){
                    // 当前边界所属的建筑物索引
                    int b = edges.get(idx).get(1);

                    // 如果是建筑物的左边界，将建筑物的高度和右边界加入优先队列
                    if (buildings[b][0] == currX){
                        int right = buildings[b][1];
                        int height = buildings[b][2];
                        live.offer(Arrays.asList(height, right));
                    }
                    idx += 1;
                }

                // 移除所有已通过当前横坐标的建筑物
                while (!live.isEmpty() && live.peek().get(1) <= currX)
                    live.poll();

                // 获取当前最高的建筑物高度
                int currHeight = live.isEmpty() ? 0 : live.peek().get(0);

                // 如果高度发生变化，记录当前横坐标和高度为一个关键点
                if (answer.isEmpty() || answer.get(answer.size() - 1).get(1) != currHeight)
                    answer.add(Arrays.asList(currX, currHeight));
            }

            // 返回答案列表，即天际线
            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_218_TheSkylineProblem.Solution solution = new LeetCode_218_TheSkylineProblem().new Solution();
        // 测试样例
        int[][] buildings1 = {
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8}
        };
        List<List<Integer>> result1 = solution.getSkyline(buildings1);
        System.out.println("Skyline for buildings1: " + result1);

        int[][] buildings2 = {
                {0, 2, 3},
                {2, 5, 3}
        };
        List<List<Integer>> result2 = solution.getSkyline(buildings2);
        System.out.println("Skyline for buildings2: " + result2);
    }
}

/**
 A city's skyline is the outer contour of the silhouette formed by all the
 buildings in that city when viewed from a distance. Given the locations and heights
 of all the buildings, return the skyline formed by these buildings collectively.

 The geometric information of each building is given in the array buildings
 where buildings[i] = [lefti, righti, heighti]:


 lefti is the x coordinate of the left edge of the iᵗʰ building.
 righti is the x coordinate of the right edge of the iᵗʰ building.
 heighti is the height of the iᵗʰ building.


 You may assume all buildings are perfect rectangles grounded on an absolutely
 flat surface at height 0.

 The skyline should be represented as a list of "key points" sorted by their x-
 coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left
 endpoint of some horizontal segment in the skyline except the last point in the list,
 which always has a y-coordinate 0 and is used to mark the skyline's termination
 where the rightmost building ends. Any ground between the leftmost and rightmost
 buildings should be part of the skyline's contour.

 Note: There must be no consecutive horizontal lines of equal height in the
 output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not
 acceptable; the three lines of height 5 should be merged into one in the final
 output as such: [...,[2 3],[4 5],[12 7],...]


 Example 1:


 Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 Explanation:
 Figure A shows the buildings of the input.
 Figure B shows the skyline formed by those buildings. The red points in figure
 B represent the key points in the output list.


 Example 2:


 Input: buildings = [[0,2,3],[2,5,3]]
 Output: [[0,3],[5,0]]



 Constraints:


 1 <= buildings.length <= 10⁴
 0 <= lefti < righti <= 2³¹ - 1
 1 <= heighti <= 2³¹ - 1
 buildings is sorted by lefti in non-decreasing order.


 Related Topics Array Divide and Conquer Binary Indexed Tree Segment Tree Line
 Sweep Heap (Priority Queue) Ordered Set 👍 5867 👎 263

 */
/**
A city's skyline is the outer contour of the silhouette formed by all the 
buildings in that city when viewed from a distance. Given the locations and heights 
of all the buildings, return the skyline formed by these buildings collectively. 

 The geometric information of each building is given in the array buildings 
where buildings[i] = [lefti, righti, heighti]: 

 
 lefti is the x coordinate of the left edge of the iᵗʰ building. 
 righti is the x coordinate of the right edge of the iᵗʰ building. 
 heighti is the height of the iᵗʰ building. 
 

 You may assume all buildings are perfect rectangles grounded on an absolutely 
flat surface at height 0. 

 The skyline should be represented as a list of "key points" sorted by their x-
coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left 
endpoint of some horizontal segment in the skyline except the last point in the list, 
which always has a y-coordinate 0 and is used to mark the skyline's termination 
where the rightmost building ends. Any ground between the leftmost and rightmost 
buildings should be part of the skyline's contour. 

 Note: There must be no consecutive horizontal lines of equal height in the 
output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not 
acceptable; the three lines of height 5 should be merged into one in the final 
output as such: [...,[2 3],[4 5],[12 7],...] 

 
 Example 1: 
 
 
Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure 
B represent the key points in the output list.
 

 Example 2: 

 
Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]
 

 
 Constraints: 

 
 1 <= buildings.length <= 10⁴ 
 0 <= lefti < righti <= 2³¹ - 1 
 1 <= heighti <= 2³¹ - 1 
 buildings is sorted by lefti in non-decreasing order. 
 

 Related Topics Array Divide and Conquer Binary Indexed Tree Segment Tree Line 
Sweep Heap (Priority Queue) Ordered Set 👍 5867 👎 263

*/
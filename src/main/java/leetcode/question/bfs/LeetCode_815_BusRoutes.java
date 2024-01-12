package leetcode.question.bfs;

/**
  *@Question:  815. Bus Routes
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 83.95%
  *@Time  Complexity: O(M^2 * K + M * K * logK), M is the size of routes, and K is the maximum size of routes[i].
  *@Space Complexity: O(M^2 + logK)
 */

import java.util.*;

public class LeetCode_815_BusRoutes {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if (source == target) {
                return 0;
            }

            // 创建从站点到包含该站点的所有路线的映射
            HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
            for (int r = 0; r < routes.length; r++) {
                for (int stop : routes[r]) {
                    // 添加包含该站点的所有路线
                    ArrayList<Integer> route = adjList.getOrDefault(stop, new ArrayList<>());
                    route.add(r);
                    adjList.put(stop, route);
                }
            }

            Queue<Integer> q = new LinkedList<>();
            Set<Integer> vis = new HashSet<>(routes.length);

            // 将包含源站点的所有路线插入队列
            if(adjList.get(source) != null){
            for (int route : adjList.get(source)) {
                q.add(route);
                vis.add(route);
            }}

            int busCount = 1;
            while (!q.isEmpty()) {
                int size = q.size();

                for (int i = 0; i < size; i++) {
                    int route = q.remove();

                    // 遍历当前路线中的站点
                    for (int stop : routes[route]) {
                        // 如果找到目标站点，返回当前计数
                        if (stop == target) {
                            return busCount;
                        }

                        // 遍历从当前站点出发的可能的下一条路线
                        for (int nextRoute : adjList.get(stop)) {
                            if (!vis.contains(nextRoute)) {
                                vis.add(nextRoute);
                                q.add(nextRoute);
                            }
                        }
                    }
                }
                busCount++;
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_815_BusRoutes busRoutes = new LeetCode_815_BusRoutes();
        Solution solution = busRoutes.new Solution();

        // 测试用例1
        int[][] routes1 = {{1, 2, 7}, {3, 6, 7}};
        int source1 = 1, target1 = 6;
        int result1 = solution.numBusesToDestination(routes1, source1, target1);
        System.out.println("Test Case 1: " + result1); // Output: 2

        // 测试用例2
        int[][] routes2 = {{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}};
        int source2 = 15, target2 = 12;
        int result2 = solution.numBusesToDestination(routes2, source2, target2);
        System.out.println("Test Case 2: " + result2); // Output: -1
    }
}


/**
You are given an array routes representing bus routes where routes[i] is a bus 
route that the iᵗʰ bus repeats forever. 

 
 For example, if routes[0] = [1, 5, 7], this means that the 0ᵗʰ bus travels in 
the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever. 
 

 You will start at the bus stop source (You are not on any bus initially), and 
you want to go to the bus stop target. You can travel between bus stops by buses 
only. 

 Return the least number of buses you must take to travel from source to target.
 Return -1 if it is not possible. 

 
 Example 1: 

 
Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then 
take the second bus to the bus stop 6.
 

 Example 2: 

 
Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 1
2
Output: -1
 

 

 
 Constraints: 

 
 1 <= routes.length <= 500. 
 1 <= routes[i].length <= 10⁵ 
 All the values of routes[i] are unique. 
 sum(routes[i].length) <= 10⁵ 
 0 <= routes[i][j] < 10⁶ 
 0 <= source, target < 10⁶ 
 

 Related Topics Array Hash Table Breadth-First Search 👍 4106 👎 112

*/

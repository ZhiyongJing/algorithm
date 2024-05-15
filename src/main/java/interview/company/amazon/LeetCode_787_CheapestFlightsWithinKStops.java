package interview.company.amazon;

import java.util.*;

/**
 * @Question: 787. Cheapest Flights Within K Stops
 * @Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 59.70%
 * @Time Complexity: O(N+ E*K), Let E be the number of flights and N be the number of cities.
 * @Space Complexity: O(N+ E*K)
 */

/**
 * 这道题目要求在给定的有向加权图中找到从源节点到目标节点的最便宜的路径，其中路径的长度必须小于等于给定的最大停留次数 K。
 *
 * 解题思路如下：
 *
 * 1. **构建邻接表：** 首先，我们需要将给定的图表示成邻接表的形式。邻接表是一个哈希映射，其中键表示每个节点的索引，值是一个列表，列表中的元素是该节点的邻居节点和边的权重。我们可以使用 HashMap 来表示这个邻接表。
 *
 * 2. **广度优先搜索（BFS）：** 使用 BFS 来搜索从源节点到目标节点的最短路径。我们使用一个队列来存储节点及其到源节点的距离。在每一轮迭代中，我们从队列中弹出一个节点，并遍历其邻居节点。对于每个邻居节点，如果新路径的总权重小于已知的路径权重，则更新该邻居节点的路径权重，并将其加入队列中。这样，我们最终会找到从源节点到目标节点的最短路径。
 *
 * 3. **停止条件：** 在 BFS 中，我们需要设定停止条件，以避免无限循环。在这个问题中，停止条件是达到最大停留次数 K 或者队列为空。
 *
 * 4. **返回结果：** 最终，我们将得到目标节点的路径权重，如果目标节点的路径权重是初始值（表示没有有效的路径），则返回 -1；否则返回目标节点的路径权重。
 *
 * 时间复杂度分析：构建邻接表的时间复杂度为 O(E)，其中 E 是边的数量；BFS 的时间复杂度是 O(V+E)，其中 V 是节点的数量。因此，总的时间复杂度是 O(V+E)。
 *
 * 空间复杂度分析：使用了一个 HashMap 来表示邻接表，空间复杂度为 O(V+E)，其中 V 是节点的数量，E 是边的数量。同时，使用了一个队列来存储节点及其到源节点的距离，空间复杂度也是 O(V+E)。因此，总的空间复杂度是 O(V+E)。
 */
public class LeetCode_787_CheapestFlightsWithinKStops {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // 使用邻接表表示图
            Map<Integer, List<int[]>> adj = new HashMap<>();
            for (int[] i : flights)
                adj.computeIfAbsent(i[0], value -> new ArrayList<>()).add(new int[] { i[1], i[2] });

            // 初始化距离数组，将所有节点的距离初始化为最大值
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);

            // 使用队列进行广度优先搜索，存储的元素是节点和到达该节点的距离
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] { src, 0 });
            int stops = 0;

            // 广度优先搜索
            while (stops <= k && !q.isEmpty()) {
                int sz = q.size();
                // 遍历当前层的节点
                while (sz-- > 0) {
                    int[] temp = q.poll();
                    int node = temp[0];
                    int distance = temp[1];

                    // 如果当前节点没有出度，直接跳过
                    if (!adj.containsKey(node))
                        continue;
                    // 遍历当前节点的邻居节点
                    for (int[] e : adj.get(node)) {
                        int neighbour = e[0];
                        int price = e[1];
                        // 如果当前路径的价格加上当前节点的距离大于等于邻居节点的距离，则不处理
                        if (price + distance >= dist[neighbour])
                            continue;
                        // 更新邻居节点的距离，并加入队列中
                        dist[neighbour] = price + distance;
                        q.offer(new int[] { neighbour, dist[neighbour] });
                    }
                }
                stops++;
            }
            // 返回目标节点的距离，如果距离为最大值则返回-1
            return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_787_CheapestFlightsWithinKStops().new Solution();
        // 测试
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0;
        int dst = 2;
        int k = 1;
        System.out.println(solution.findCheapestPrice(n, flights, src, dst, k)); // 应返回 200
    }
}

/**
There are n cities connected by some number of flights. You are given an array 
flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight 
from city fromi to city toi with cost pricei. 

 You are also given three integers src, dst, and k, return the cheapest price 
from src to dst with at most k stops. If there is no such route, return -1. 

 
 Example 1: 
 
 
Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], 
src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has 
cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because 
it uses 2 stops.
 

 Example 2: 
 
 
Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1

Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has 
cost 100 + 100 = 200.
 

 Example 3: 
 
 
Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0

Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 50
0.
 

 
 Constraints: 

 
 1 <= n <= 100 
 0 <= flights.length <= (n * (n - 1) / 2) 
 flights[i].length == 3 
 0 <= fromi, toi < n 
 fromi != toi 
 1 <= pricei <= 10⁴ 
 There will not be any multiple flights between two cities. 
 0 <= src, dst, k < n 
 src != dst 
 

 Related Topics Dynamic Programming Depth-First Search Breadth-First Search 
Graph Heap (Priority Queue) Shortest Path 👍 9742 👎 404

*/
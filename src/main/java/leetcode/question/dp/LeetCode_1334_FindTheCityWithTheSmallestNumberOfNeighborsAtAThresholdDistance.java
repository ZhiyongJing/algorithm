package leetcode.question.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 *@Question:  1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 6.81%
 *@Time  Complexity: O(N^3 * logN)
 *@Space Complexity: O(N^2)
 */

/**
 * ### 解题思路
 *
 * 1. **图的表示**：
 *    - 该问题涉及到一个带权无向图的处理，城市和道路可以看作图的节点和边。我们需要找到在给定的距离阈值内邻居最少的城市。
 *
 * 2. **最短路径计算**：
 *    - 由于需要计算每对城市之间的最短路径，最合适的方法是使用 Dijkstra 算法。Dijkstra 算法能高效地计算从源城市到所有其他城市的最短路径，适用于带权非负边的图。
 *
 * 3. **步骤概述**：
 *    - **初始化**：创建邻接表来存储图的结构，并创建一个二维数组来存储每对城市之间的最短路径距离。最短路径矩阵的初始化为无穷大，表示初始时城市之间的距离未知。城市到自己的距离设置为0。
 *    - **图的构建**：根据输入的边信息填充邻接表。无向图的每条边需要双向添加到邻接表中。
 *    - **计算最短路径**：对每个城市运行 Dijkstra 算法，计算从该城市到所有其他城市的最短路径，并更新最短路径矩阵。这意味着我们需要对每个城市调用 Dijkstra 算法一次。
 *    - **统计可达城市数量**：在所有城市的最短路径矩阵计算完成后，遍历每个城市，计算在给定的距离阈值内可达的城市数量。然后找到在距离阈值内可达城市数量最少的城市。如果有多个城市的可达城市数量相同，选择编号最小的城市。
 *
 * ### 时间复杂度
 *
 * - **Dijkstra 算法的复杂度**：对于每个城市使用 Dijkstra 算法，其时间复杂度为 `O((V + E) log V)`，其中 `V` 是城市的数量，`E` 是道路的数量。
 * - **总体复杂度**：由于我们对每个城市都需要运行一次 Dijkstra 算法，总体时间复杂度为 `O(V * (V + E) log V)`。
 *
 * ### 空间复杂度
 *
 * - **邻接表**：用于存储图的结构，空间复杂度为 `O(V + E)`。
 * - **最短路径矩阵**：用于存储每对城市之间的最短路径，空间复杂度为 `O(V^2)`。
 * - **优先队列**：Dijkstra 算法使用的优先队列的空间复杂度为 `O(V)`。
 * - **总体复杂度**：总体空间复杂度为 `O(V^2 + E)`。
 */

public class LeetCode_1334_FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 查找距离阈值内邻居数量最少的城市
        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            // 邻接表用于存储图
            List<int[]>[] adjacencyList = new List[n];
            // 矩阵用于存储每对城市之间的最短路径距离
            int[][] shortestPathMatrix = new int[n][n];

            // 初始化邻接表和最短路径矩阵
            for (int i = 0; i < n; i++) {
                Arrays.fill(shortestPathMatrix[i], Integer.MAX_VALUE); // 将所有距离初始化为无穷大
                shortestPathMatrix[i][i] = 0; // 自己到自己的距离为0
                adjacencyList[i] = new ArrayList<>();
            }

            // 将边添加到邻接表中
            for (int[] edge : edges) {
                int start = edge[0];
                int end = edge[1];
                int weight = edge[2];
                adjacencyList[start].add(new int[] { end, weight });
                adjacencyList[end].add(new int[] { start, weight }); // 无向图，双向添加
            }

            // 使用Dijkstra算法计算每个城市的最短路径
            for (int i = 0; i < n; i++) {
                dijkstra(n, adjacencyList, shortestPathMatrix[i], i);
            }

            // 查找在距离阈值内可达城市数量最少的城市
            return getCityWithFewestReachable(
                    n,
                    shortestPathMatrix,
                    distanceThreshold
            );
        }

        // Dijkstra算法计算从源城市到所有其他城市的最短路径
        void dijkstra(
                int n,
                List<int[]>[] adjacencyList,
                int[] shortestPathDistances,
                int source
        ) {
            // 优先队列，按距离排序
            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) ->
                    (a[1] - b[1])
            );
            priorityQueue.add(new int[] { source, 0 });
            Arrays.fill(shortestPathDistances, Integer.MAX_VALUE); // 将所有距离初始化为无穷大
            shortestPathDistances[source] = 0; // 源城市到自己的距离为0

            // 按优先队列处理节点
            while (!priorityQueue.isEmpty()) {
                int[] current = priorityQueue.remove();
                int currentCity = current[0];
                int currentDistance = current[1];
                if (currentDistance > shortestPathDistances[currentCity]) {
                    continue;
                }

                // 更新到邻近城市的距离
                for (int[] neighbor : adjacencyList[currentCity]) {
                    int neighborCity = neighbor[0];
                    int edgeWeight = neighbor[1];
                    if (
                            shortestPathDistances[neighborCity] >
                                    currentDistance + edgeWeight
                    ) {
                        shortestPathDistances[neighborCity] = currentDistance +
                                edgeWeight;
                        priorityQueue.add(
                                new int[] {
                                        neighborCity,
                                        shortestPathDistances[neighborCity],
                                }
                        );
                    }
                }
            }
        }

        // 确定在距离阈值内可达城市数量最少的城市
        int getCityWithFewestReachable(
                int n,
                int[][] shortestPathMatrix,
                int distanceThreshold
        ) {
            int cityWithFewestReachable = -1;
            int fewestReachableCount = n;

            // 计算每个城市在距离阈值内可达的城市数量
            for (int i = 0; i < n; i++) {
                int reachableCount = 0;
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    } // 跳过自己
                    if (shortestPathMatrix[i][j] <= distanceThreshold) {
                        reachableCount++;
                    }
                }
                // 更新具有最少可达城市数量的城市
                if (reachableCount <= fewestReachableCount) {
                    fewestReachableCount = reachableCount;
                    cityWithFewestReachable = i;
                }
            }
            return cityWithFewestReachable;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1334_FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance().new Solution();
        // 测试样例
        int n1 = 4;
        int[][] edges1 = {
                {0, 1, 3},
                {1, 2, 1},
                {2, 3, 4},
                {3, 0, 2}
        };
        int distanceThreshold1 = 4;
        // 预期输出: 3
        System.out.println("City with the smallest number of neighbors at threshold distance: " + solution.findTheCity(n1, edges1, distanceThreshold1));

        int n2 = 5;
        int[][] edges2 = {
                {0, 1, 2},
                {1, 2, 3},
                {2, 3, 4},
                {3, 4, 5},
                {4, 0, 1}
        };
        int distanceThreshold2 = 3;
        // 预期输出: 0 or 1
        System.out.println("City with the smallest number of neighbors at threshold distance: " + solution.findTheCity(n2, edges2, distanceThreshold2));

        int n3 = 3;
        int[][] edges3 = {
                {0, 1, 10},
                {1, 2, 10}
        };
        int distanceThreshold3 = 15;
        // 预期输出: 2
        System.out.println("City with the smallest number of neighbors at threshold distance: " + solution.findTheCity(n3, edges3, distanceThreshold3));
    }
}

/**
There are n cities numbered from 0 to n-1. Given the array edges where edges[i] 
= [fromi, toi, weighti] represents a bidirectional and weighted edge between 
cities fromi and toi, and given the integer distanceThreshold. 

 Return the city with the smallest number of cities that are reachable through 
some path and whose distance is at most distanceThreshold, If there are multiple 
such cities, return the city with the greatest number. 

 Notice that the distance of a path connecting cities i and j is equal to the 
sum of the edges' weights along that path. 

 
 Example 1: 
 
 
Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2] 
City 1 -> [City 0, City 2, City 3] 
City 2 -> [City 0, City 1, City 3] 
City 3 -> [City 1, City 2] 
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we 
have to return city 3 since it has the greatest number.
 

 Example 2: 
 
 
Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], 
distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1] 
City 1 -> [City 0, City 4] 
City 2 -> [City 3, City 4] 
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3] 
The city 0 has 1 neighboring city at a distanceThreshold = 2.
 

 
 Constraints: 

 
 2 <= n <= 100 
 1 <= edges.length <= n * (n - 1) / 2 
 edges[i].length == 3 
 0 <= fromi < toi < n 
 1 <= weighti, distanceThreshold <= 10^4 
 All pairs (fromi, toi) are distinct. 
 

 Related Topics Dynamic Programming Graph Shortest Path 👍 3142 👎 137

*/
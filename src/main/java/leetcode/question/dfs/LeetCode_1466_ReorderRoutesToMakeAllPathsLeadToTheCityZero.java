package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *@Question:  1466. Reorder Routes to Make All Paths Lead to the City Zero
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.79%
 *@Time  Complexity: O(N  )
 *@Space Complexity: O(N )
 */

/**
 * ### 题目：1466. Reorder Routes to Make All Paths Lead to the City Zero
 *
 * **问题描述：**
 * 给定一个城市的网络，其中每条路都有一个方向（可以从一个城市到另一个城市），目标是将所有道路调整成从城市 0 出发的方向，使得所有路径都能到达城市 0。求最少需要重新调整多少条路。
 *
 * ### 解题思路：
 *
 * 1. **图的建模**：
 *    - 将城市和道路建模为一个图，其中城市作为节点，道路作为边。边的方向由输入数据提供。为了方便处理，需要用邻接表来存储每个城市的出发路和到达路的方向。使用邻接表来表示图。
 *
 * 2. **深度优先搜索（DFS）**：
 *    - 从城市 0 开始，遍历所有与城市 0 直接或间接连接的城市。在遍历过程中，统计需要重新调整的路的数量。
 *    - 对于每条从城市 `a` 到城市 `b` 的路，检查其方向是否需要调整。若需要调整，则增加计数器。
 *    - 通过 DFS 遍历所有连接的城市，并计算需要调整的路的总数。
 *
 * 3. **广度优先搜索（BFS）**：
 *    - 与 DFS 类似，从城市 0 开始使用 BFS 遍历所有连接的城市。每次访问一个新城市时，检查从当前城市出发到达的邻接城市的道路方向，并更新计数器。
 *    - 使用 BFS 的好处是能逐层处理每个城市，并且避免了 DFS 的递归深度问题。
 *
 * ### 时间复杂度
 *
 * - **O(N + M)**：其中 `N` 是城市的数量，`M` 是道路的数量。无论是 DFS 还是 BFS，都需要遍历每个城市和每条道路一次，因此时间复杂度是 O(N + M)。
 *
 * ### 空间复杂度
 *
 * - **O(N + M)**：主要用于存储邻接表，空间复杂度与图的节点和边数成正比。额外的空间还包括用于记录访问状态的数组（O(N)）和队列或递归栈的空间（O(N)），所以总空间复杂度是 O(N + M)。
 */

public class LeetCode_1466_ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 记录需要重新调整的路的数量
        int count = 0;

        // 深度优先搜索（DFS）方法
        public void dfs(int node, int parent, Map<Integer, List<List<Integer>>> adj) {
            // 如果当前节点没有邻接节点，返回
            if (!adj.containsKey(node)) {
                return;
            }
            // 遍历当前节点的所有邻接节点
            for (List<Integer> nei : adj.get(node)) {
                int neighbor = nei.get(0); // 邻接节点
                int sign = nei.get(1); // 1 表示需要调整方向的路，0 表示不需要调整方向的路
                if (neighbor != parent) { // 避免回到父节点
                    count += sign; // 如果需要调整方向，则计数器加一
                    dfs(neighbor, node, adj); // 递归访问邻接节点
                }
            }
        }

        // 解法1：使用 DFS
        public int minReorder(int n, int[][] connections) {
            // 构建邻接表
            Map<Integer, List<List<Integer>>> adj = new HashMap<>();
            for (int[] connection : connections) {
                adj.computeIfAbsent(connection[0], k -> new ArrayList<List<Integer>>()).add(
                        Arrays.asList(connection[1], 1)); // 从 connection[0] 到 connection[1] 需要调整方向
                adj.computeIfAbsent(connection[1], k -> new ArrayList<List<Integer>>()).add(
                        Arrays.asList(connection[0], 0)); // 从 connection[1] 到 connection[0] 不需要调整方向
            }
            System.out.println(adj);
            // 从城市0开始DFS
            dfs(0, -1, adj);
            return count; // 返回需要调整的路的数量
        }

        //----------------------------------------------
        // 广度优先搜索（BFS）方法
        public void bfs(int node, int n, Map<Integer, List<List<Integer>>> adj) {
            Queue<Integer> q = new LinkedList<>();
            boolean[] visit = new boolean[n]; // 记录节点是否已经访问过
            q.offer(node); // 从起始节点开始
            visit[node] = true;

            while (!q.isEmpty()) {
                node = q.poll(); // 取出队列中的节点
                if (!adj.containsKey(node)) {
                    continue;
                }
                // 遍历当前节点的所有邻接节点
                for (List<Integer> nei : adj.get(node)) {
                    int neighbor = nei.get(0); // 邻接节点
                    int sign = nei.get(1); // 1 表示需要调整方向的路，0 表示不需要调整方向的路
                    if (!visit[neighbor]) { // 如果邻接节点还未访问过
                        count += sign; // 如果需要调整方向，则计数器加一
                        visit[neighbor] = true; // 标记为已访问
                        q.offer(neighbor); // 将邻接节点加入队列
                    }
                }
            }
        }

        // 解法2：使用 BFS
        public int minReorder2(int n, int[][] connections) {
            // 构建邻接表
            Map<Integer, List<List<Integer>>> adj = new HashMap<>();
            for (int[] connection : connections) {
                adj.computeIfAbsent(connection[0], k -> new ArrayList<List<Integer>>()).add(
                        Arrays.asList(connection[1], 1)); // 从 connection[0] 到 connection[1] 需要调整方向
                adj.computeIfAbsent(connection[1], k -> new ArrayList<List<Integer>>()).add(
                        Arrays.asList(connection[0], 0)); // 从 connection[1] 到 connection[0] 不需要调整方向
            }
            // 从城市0开始BFS
            bfs(0, n, adj);
            return count; // 返回需要调整的路的数量
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1466_ReorderRoutesToMakeAllPathsLeadToTheCityZero().new Solution();

        // 测试样例
        int n = 6; // 城市数量
        int[][] connections = {
                {0, 1}, // 从城市0到城市1
                {1, 3}, // 从城市1到城市3
                {2, 3}, // 从城市2到城市3
                {4, 0}, // 从城市4到城市0
                {4, 5}  // 从城市4到城市5
        };

        // 计算最少需要重新调整的路的数量
        int result = solution.minReorder(n, connections);
        System.out.println("最少需要重新调整的路的数量是：" + result);

        // 测试解法2
        int result2 = solution.minReorder2(n, connections);
        System.out.println("最少需要重新调整的路的数量（使用BFS）是：" + result2);
    }
}
/**
There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is 
only one way to travel between two different cities (this network form a tree). 
Last year, The ministry of transport decided to orient the roads in one 
direction because they are too narrow. 

 Roads are represented by connections where connections[i] = [ai, bi] 
represents a road from city ai to city bi. 

 This year, there will be a big event in the capital (city 0), and many people 
want to travel to this city. 

 Your task consists of reorienting some roads such that each city can visit the 
city 0. Return the minimum number of edges changed. 

 It's guaranteed that each city can reach city 0 after reorder. 

 
 Example 1: 
 
 
Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can 
reach the node 0 (capital).
 

 Example 2: 
 
 
Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can 
reach the node 0 (capital).
 

 Example 3: 

 
Input: n = 3, connections = [[1,0],[2,0]]
Output: 0
 

 
 Constraints: 

 
 2 <= n <= 5 * 10⁴ 
 connections.length == n - 1 
 connections[i].length == 2 
 0 <= ai, bi <= n - 1 
 ai != bi 
 

 Related Topics Depth-First Search Breadth-First Search Graph 👍 4165 👎 119

*/
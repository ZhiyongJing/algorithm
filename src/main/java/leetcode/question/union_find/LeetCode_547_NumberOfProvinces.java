package leetcode.question.union_find;

import java.util.LinkedList;
import java.util.Queue;

/**
 *@Question:  547. Number of Provinces
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 35.21%
 *@Time  Complexity: O(N^2)
 *@Space Complexity: O(N)
 */

/**
 * 题目要求解决的问题是计算连接矩阵中的省份数量，其中矩阵中的每个元素表示两个城市是否直接相连。如果城市 A 与城市 B 直接或间接相连，则它们属于同一个省份。
 *
 * ### 解题思路
 *
 * 1. **并查集（Union Find）方法**：
 *    - 使用并查集可以有效地管理和合并不同的省份。每个城市作为并查集的一个节点，通过连接矩阵中的信息，将相连的城市合并到同一个集合中。
 *    - 初始化时，每个城市都是自己的根节点，并按需合并。
 *    - 遍历连接矩阵，对于每对相连的城市，如果它们尚未属于同一个集合（通过并查集的 `find` 操作判断），则将它们合并。
 *    - 最终，并查集中根节点的数量即为省份的数量。
 *
 * 2. **深度优先搜索（DFS）方法**：
 *    - 使用 DFS 可以递归地访问并标记所有与给定城市直接或间接相连的城市，从而确定一个省份的所有城市。
 *    - 维护一个布尔型数组 `visited` 来记录每个城市是否已经被访问过。
 *    - 遍历每个城市，如果该城市尚未被访问过，则递归地访问所有与之相连的城市，并将它们标记为已访问。
 *    - 每次开始新的搜索时，即找到一个新的省份。
 *
 * 3. **广度优先搜索（BFS）方法**：
 *    - 使用 BFS 可以非递归地访问并标记所有与给定城市直接或间接相连的城市，同样用于确定一个省份的所有城市。
 *    - 使用队列来实现 BFS，从初始城市开始，依次访问其所有相邻的未访问过的城市，并标记为已访问。
 *    - 每次开始新的 BFS 搜索时，即找到一个新的省份。
 *
 * ### 时间复杂度和空间复杂度
 *
 * - **并查集方法**：
 *   - 时间复杂度：O(N^2)，其中 N 是城市的数量。遍历连接矩阵的所有元素，并查集的操作基本为常数时间复杂度。
 *   - 空间复杂度：O(N)，并查集所需的额外空间与城市数量线性相关。
 *
 * - **DFS 和 BFS 方法**：
 *   - 时间复杂度：O(N^2)，同样需要遍历连接矩阵的所有元素，但在最坏情况下，可能会对每个城市都进行深度或广度优先搜索。
 *   - 空间复杂度：O(N)，存储访问状态和递归调用栈或队列所需的空间与城市数量线性相关。
 *
 * 这些方法都能有效地解决问题，选择其中一种取决于具体情况和个人偏好。并查集方法可能在实现上更为简洁和高效，但 DFS 和 BFS 方法在特定情况下也有其优势，如需要具体的连接路径或有特殊的需求。
 */
public class LeetCode_547_NumberOfProvinces{

    //leetcode submit region begin(Prohibit modification and deletion)
    class UnionFind {
        int[] parent;
        int[] rank;

        // 构造函数，初始化并查集
        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = i; // 初始时，每个节点的父节点是它自己
            rank = new int[size];
        }

        // 查找根节点，并进行路径压缩优化
        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // 路径压缩
            return parent[x];
        }

        // 合并两个集合
        public void union_set(int x, int y) {
            int xset = find(x), yset = find(y);
            if (xset == yset) {
                return; // 已经属于同一个集合，无需合并
            } else if (rank[xset] < rank[yset]) {
                parent[xset] = yset;
            } else if (rank[xset] > rank[yset]) {
                parent[yset] = xset;
            } else {
                parent[yset] = xset;
                rank[xset]++;
            }
        }
    }

    class Solution {
        // 解法1：并查集
        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            UnionFind dsu = new UnionFind(n); // 创建并查集对象
            int numberOfProvinces = n; // 初始时，每个城市自成一个省份

            // 遍历连接矩阵
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isConnected[i][j] == 1 && dsu.find(i) != dsu.find(j)) {
                        numberOfProvinces--; // 合并省份
                        dsu.union_set(i, j);
                    }
                }
            }

            return numberOfProvinces;
        }

        // 解法2：DFS
        public void dfs(int node, int[][] isConnected, boolean[] visited) {
            visited[node] = true; // 标记节点已访问
            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[node][i] == 1 && !visited[i]) {
                    dfs(i, isConnected, visited); // 递归访问相邻节点
                }
            }
        }

        public int findCircleNum2(int[][] isConnected) {
            int n = isConnected.length;
            int numberOfProvinces = 0; // 省份计数
            boolean[] visited = new boolean[n]; // 记录节点访问状态

            // 遍历所有城市
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    numberOfProvinces++; // 新的省份
                    dfs(i, isConnected, visited); // 深度优先搜索遍历所有连接的城市
                }
            }

            return numberOfProvinces;
        }

        // 解法3：BFS
        public void bfs(int node, int[][] isConnected, boolean[] visited) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(node); // 将起始节点加入队列
            visited[node] = true; // 标记节点已访问

            while (!queue.isEmpty()) {
                node = queue.poll(); // 取出队列中的节点

                for (int i = 0; i < isConnected.length; i++) {
                    if (isConnected[node][i] == 1 && !visited[i]) {
                        queue.offer(i); // 将相邻节点加入队列
                        visited[i] = true; // 标记节点已访问
                    }
                }
            }
        }

        public int findCircleNum3(int[][] isConnected) {
            int n = isConnected.length;
            int numberOfProvinces = 0; // 省份计数
            boolean[] visited = new boolean[n]; // 记录节点访问状态

            // 遍历所有城市
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    numberOfProvinces++; // 新的省份
                    bfs(i, isConnected, visited); // 广度优先搜索遍历所有连接的城市
                }
            }

            return numberOfProvinces;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_547_NumberOfProvinces().new Solution();

        // 测试用例1
        int[][] isConnected1 = {
                {1,1,0},
                {1,1,0},
                {0,0,1}
        };
        System.out.println("Number of provinces (Union Find): " + solution.findCircleNum(isConnected1));
        System.out.println("Number of provinces (DFS): " + solution.findCircleNum2(isConnected1));
        System.out.println("Number of provinces (BFS): " + solution.findCircleNum3(isConnected1));

        // 测试用例2
        int[][] isConnected2 = {
                {1,0,0},
                {0,1,0},
                {0,0,1}
        };
        System.out.println("Number of provinces (Union Find): " + solution.findCircleNum(isConnected2));
        System.out.println("Number of provinces (DFS): " + solution.findCircleNum2(isConnected2));
        System.out.println("Number of provinces (BFS): " + solution.findCircleNum3(isConnected2));
    }
}

/**
There are n cities. Some of them are connected, while some are not. If city a 
is connected directly with city b, and city b is connected directly with city c, 
then city a is connected indirectly with city c. 

 A province is a group of directly or indirectly connected cities and no other 
cities outside of the group. 

 You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the iᵗ
ʰ city and the jᵗʰ city are directly connected, and isConnected[i][j] = 0 
otherwise. 

 Return the total number of provinces. 

 
 Example 1: 
 
 
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
 

 Example 2: 
 
 
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
 

 
 Constraints: 

 
 1 <= n <= 200 
 n == isConnected.length 
 n == isConnected[i].length 
 isConnected[i][j] is 1 or 0. 
 isConnected[i][i] == 1 
 isConnected[i][j] == isConnected[j][i] 
 

 Related Topics Depth-First Search Breadth-First Search Union Find Graph 👍 9633
 👎 358

*/
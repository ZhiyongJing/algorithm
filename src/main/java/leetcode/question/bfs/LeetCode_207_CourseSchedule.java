package leetcode.question.bfs;

import java.util.*;

/**
  *@Question:  207. Course Schedule     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 78.47%      
  *@Time  Complexity: O(M + N)  n be the number of courses and m be the size of prerequisites.
  *@Space Complexity: O(M + N)
 */

/**
 * ### 算法思路：
 *
 * 该算法解决了一个有向图中是否存在环的问题，同时考虑了拓扑排序的思想。主要使用了两种方法，一种是基于拓扑排序的BFS（宽度优先搜索），
 * 另一种是基于深度优先搜索（DFS）。
 *
 * #### 方法一 - 拓扑排序的BFS：
 *
 * 1. **构建图：** 使用邻接表表示有向图，并同时统计每个节点的入度。
 *
 * 2. **拓扑排序：** 从入度为0的节点开始，依次删除该节点及其出边，更新相关节点的入度。这样循环直至图中所有节点都被访问。
 *
 * 3. **环的判断：** 如果最终访问的节点数量等于总节点数，说明不存在环，可以完成课程学习。
 *
 * #### 方法二 - 深度优先搜索DFS：
 *
 * 1. **构建图：** 使用邻接表表示有向图。
 *
 * 2. **DFS遍历：** 对每个节点进行深度优先遍历，判断是否存在环。在遍历的过程中，标记已访问的节点，并使用递归栈来判断是否存在环。
 *
 * 3. **环的判断：** 如果在DFS的过程中发现当前节点已经在递归栈中，说明存在环。
 *
 * ### 复杂度分析：
 *
 * #### 方法一 - 拓扑排序的BFS：
 *
 * - 时间复杂度：O(V + E)，其中 V 为节点数，E 为边数。每个节点和每条边都会被访问一次。
 * - 空间复杂度：O(V + E)，使用了额外的空间来存储邻接表和入度数组。
 *
 * #### 方法二 - 深度优先搜索DFS：
 *
 * - 时间复杂度：O(V + E)，其中 V 为节点数，E 为边数。每个节点和每条边都会被访问一次。
 * - 空间复杂度：O(V + E)，使用了额外的空间来存储邻接表和递归栈。递归栈的深度最大为节点数，即 O(V)。
 */

public class LeetCode_207_CourseSchedule {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 判断是否能完成课程学习（使用拓扑排序）
         *
         * @param numCourses    课程数量
         * @param prerequisites 课程之间的先修关系
         * @return 是否能完成课程学习
         */
        public boolean canFinish2(int numCourses, int[][] prerequisites) {
            int[] indegree = new int[numCourses];
            List<List<Integer>> adj = new ArrayList<>(numCourses);

            // 初始化邻接表
            for (int i = 0; i < numCourses; i++) {
                adj.add(new ArrayList<>());
            }

            // 构建邻接表，并统计每个节点的入度
            for (int[] prerequisite : prerequisites) {
                adj.get(prerequisite[1]).add(prerequisite[0]);
                indegree[prerequisite[0]]++;
            }
            System.out.println(adj);
            Arrays.stream(indegree).forEach(num -> System.out.print(num + "," ));
            System.out.println();

            // 使用队列进行拓扑排序
            Queue<Integer> queue = new LinkedList<>();
            // 将入度为零的节点加入队列
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            System.out.println(queue);

            int nodesVisited = 0;
            while (!queue.isEmpty()) {
                int node = queue.poll();
                nodesVisited++;

                // 遍历该节点的邻接节点
                for (int neighbor : adj.get(node)) {
                    // 删除"node -> neighbor"这条边
                    indegree[neighbor]--;
                    // 如果邻接节点的入度为零，加入队列
                    if (indegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }

            // 如果遍历过的节点数等于总节点数，则说明不存在环，可以完成课程学习
            return nodesVisited == numCourses;
        }

        /**
         * 判断是否能完成课程学习（使用深度优先搜索 DFS）
         *
         * @param numCourses    课程数量
         * @param prerequisites 课程之间的先修关系
         * @return 是否能完成课程学习
         */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> adj = new ArrayList<>(numCourses);

            // 初始化邻接表
            for (int i = 0; i < numCourses; i++) {
                adj.add(new ArrayList<>());
            }

            // 构建邻接表
            for (int[] prerequisite : prerequisites) {
                adj.get(prerequisite[1]).add(prerequisite[0]);
            }

            boolean[] visit = new boolean[numCourses];
            boolean[] inStack = new boolean[numCourses];

            // 对每个节点进行DFS，检测是否存在环
            for (int i = 0; i < numCourses; i++) {
                if (dfs(i, adj, visit, inStack)) {
                    return false;
                }
            }

            // 不存在环，可以完成课程学习
            return true;
        }

        /**
         * 深度优先搜索 DFS
         *
         * @param node    当前节点
         * @param adj     邻接表
         * @param visit   访问标记数组
         * @param inStack 递归栈标记数组，用于检测环
         * @return 是否存在环
         */
        public boolean dfs(int node, List<List<Integer>> adj, boolean[] visit, boolean[] inStack) {
            // 如果节点已经在递归栈中，说明存在环
            if (inStack[node]) {
                return true;
            }
            // 如果节点已经被访问过，说明不会再构成环
            if (visit[node]) {
                return false;
            }


            // 遍历当前节点的邻接节点
            for (int neighbor : adj.get(node)) {
                // 将当前节点标记为已访问，并加入递归栈
                visit[node] = true;
                inStack[node] = true;
                // 如果邻接节点构成环，返回true
                if (dfs(neighbor, adj, visit, inStack)) {
                    return true;
                }
                // 移出递归栈
                inStack[node] = false;
            }


            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_207_CourseSchedule().new Solution();

        // 测试用例1
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println(solution.canFinish(numCourses1, prerequisites1));  // 应该返回 true

        // 测试用例2
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println(solution.canFinish(numCourses2, prerequisites2));  // 应该返回 false

        // 测试用例3
        int numCourses3 = 4;
        int[][] prerequisites3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(solution.canFinish(numCourses2, prerequisites2));  // 应该返回 true
    }
}

/**
There are a total of numCourses courses you have to take, labeled from 0 to 
numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, 
bi] indicates that you must take course bi first if you want to take course ai. 

 
 For example, the pair [0, 1], indicates that to take course 0 you have to 
first take course 1. 
 

 Return true if you can finish all courses. Otherwise, return false. 

 
 Example 1: 

 
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
 

 Example 2: 

 
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you 
should also have finished course 1. So it is impossible.
 

 
 Constraints: 

 
 1 <= numCourses <= 2000 
 0 <= prerequisites.length <= 5000 
 prerequisites[i].length == 2 
 0 <= ai, bi < numCourses 
 All the pairs prerequisites[i] are unique. 
 

 Related Topics Depth-First Search Breadth-First Search Graph Topological Sort ?
? 15525 👎 633

*/

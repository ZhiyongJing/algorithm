package leetcode.question.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *@Question:  210. Course Schedule II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 65.05%
 *@Time  Complexity: O(V + E), V represents the number of courses, and E represents the number of prerequisites.
 *@Space Complexity: O(V + E)
 */

/**
 * 这道题是经典的拓扑排序问题，用于解决有向图中的依赖关系排序问题。在这个问题中，课程之间存在先修关系，我们需要找出一种上课的顺序，满足每个课程的先修条件。
 *
 * ### 解题思路：
 * 1. **构建有向图：** 首先，我们根据给定的先修关系构建有向图的邻接表表示。邻接表中的每个顶点表示一个课程，列表中的元素表示当前课程的后继课程。
 *
 * 2. **计算入度：** 我们统计每个顶点的入度，即指向当前顶点的边的数量。这样，入度为 0 的顶点就是没有先修课程的课程，它们可以作为拓扑排序的起点。
 *
 * 3. **拓扑排序：** 使用队列来进行拓扑排序。我们从入度为 0 的顶点开始，将其加入队列，并在拓扑排序的过程中逐步减小其邻接顶点的入度。如果某个邻接顶点的入度减小为 0，则将其加入队列。直到队列为空时，拓扑排序结束。
 *
 * 4. **检查结果：** 如果拓扑排序成功（即排序后的顶点数量等于总课程数量），则返回拓扑排序的结果，否则说明有环，无法完成所有课程的学习。
 *
 * ### 时间复杂度分析：
 * - 遍历先修关系数组构建邻接表：O(E)，E 表示先修关系的数量。
 * - 计算入度：O(E)。
 * - 拓扑排序：O(V + E)，V 表示课程数量，E 表示先修关系的数量。
 * 总时间复杂度为 O(E) + O(E) + O(V + E) = O(V + E)。
 *
 * ### 空间复杂度分析：
 * - 邻接表：O(E)，E 表示先修关系的数量。
 * - 入度数组：O(V)，V 表示课程数量。
 * - 队列：O(V)，最坏情况下所有课程都入队。
 * 总空间复杂度为 O(E) + O(V) + O(V) = O(V + E)。
 */

public class LeetCode_210_CourseScheduleIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            boolean isPossible = true;
            Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
            int[] indegree = new int[numCourses];
            int[] topologicalOrder = new int[numCourses];

            // 创建图的邻接表表示
            for (int i = 0; i < prerequisites.length; i++) {
                int dest = prerequisites[i][0];
                int src = prerequisites[i][1];
                List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
                lst.add(dest);
                adjList.put(src, lst);

                // 记录每个顶点的入度
                indegree[dest] += 1;
            }

            // 将所有入度为 0 的顶点加入队列
            Queue<Integer> q = new LinkedList<Integer>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            int i = 0;
            // 处理直到队列为空
            while (!q.isEmpty()) {
                int node = q.remove();
                topologicalOrder[i++] = node;

                // 将每个邻接顶点的入度减 1
                if (adjList.containsKey(node)) {
                    for (Integer neighbor : adjList.get(node)) {
                        indegree[neighbor]--;

                        // 如果邻接顶点的入度变为 0，则加入队列
                        if (indegree[neighbor] == 0) {
                            q.add(neighbor);
                        }
                    }
                }
            }

            // 检查拓扑排序是否可能
            if (i == numCourses) {
                return topologicalOrder;
            }

            return new int[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_210_CourseScheduleIi().new Solution();
        // 测试
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] result = solution.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(result));
        // 应返回 [0,1,2,3]，表示可行的课程顺序
    }
}

/**
 There are a total of numCourses courses you have to take, labeled from 0 to
 numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai,
 bi] indicates that you must take course bi first if you want to take course ai.


 For example, the pair [0, 1], indicates that to take course 0 you have to
 first take course 1.


 Return the ordering of courses you should take to finish all courses. If there
 are many valid answers, return any of them. If it is impossible to finish all
 courses, return an empty array.


 Example 1:


 Input: numCourses = 2, prerequisites = [[1,0]]
 Output: [0,1]
 Explanation: There are a total of 2 courses to take. To take course 1 you
 should have finished course 0. So the correct course order is [0,1].


 Example 2:


 Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 Output: [0,2,1,3]
 Explanation: There are a total of 4 courses to take. To take course 3 you
 should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after
 you finished course 0.
 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].



 Example 3:


 Input: numCourses = 1, prerequisites = []
 Output: [0]



 Constraints:


 1 <= numCourses <= 2000
 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 prerequisites[i].length == 2
 0 <= ai, bi < numCourses
 ai != bi
 All the pairs [ai, bi] are distinct.


 Related Topics Depth-First Search Breadth-First Search Graph Topological Sort ?
 ? 10604 👎 343

 */
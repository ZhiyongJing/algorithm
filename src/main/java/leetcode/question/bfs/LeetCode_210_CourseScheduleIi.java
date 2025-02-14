package leetcode.question.bfs;

import java.util.*;

public class LeetCode_210_CourseScheduleIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            // 变量 isPossible 用于标记是否可能完成所有课程
            boolean isPossible = true;

            // 使用邻接表表示有向图，key: 课程, value: 依赖它的课程列表
            Map<Integer, List<Integer>> adjList = new HashMap<>();

            // 记录每个课程的入度，即需要先完成多少门课程
            int[] indegree = new int[numCourses];

            // 用于存储最终的课程安排顺序
            int[] topologicalOrder = new int[numCourses];

            // 构建有向图
            for (int i = 0; i < prerequisites.length; i++) {
                int dest = prerequisites[i][0]; // 课程
                int src = prerequisites[i][1]; // 先修课程

                // 在邻接表中添加依赖关系
                List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
                lst.add(dest);
                adjList.put(src, lst);

                // 增加目标课程的入度
                indegree[dest] += 1;
            }

            // 创建一个队列用于存储入度为 0 的课程（可以直接学习的课程）
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) { // 没有前置课程的课程
                    q.add(i);
                }
            }

            int i = 0; // 记录当前拓扑排序的位置
            // 进行拓扑排序
            while (!q.isEmpty()) {
                int node = q.remove(); // 取出入度为 0 的课程
                topologicalOrder[i++] = node; // 将其加入拓扑排序结果

                // 遍历该课程的所有后续课程
                if (adjList.containsKey(node)) {
                    for (Integer neighbor : adjList.get(node)) {
                        indegree[neighbor]--; // 该课程的先修课程完成，入度减少

                        // 如果入度减少到 0，表示可以学习该课程
                        if (indegree[neighbor] == 0) {
                            q.add(neighbor);
                        }
                    }
                }
            }

            // 如果拓扑排序包含所有课程，说明可以完成，返回拓扑排序
            if (i == numCourses) {
                return topologicalOrder;
            }

            // 如果无法完成所有课程，返回空数组
            return new int[0];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_210_CourseScheduleIi.Solution solution = new LeetCode_210_CourseScheduleIi().new Solution();

        // 测试用例1
        int numCourses1 = 4;
        int[][] prerequisites1 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] result1 = solution.findOrder(numCourses1, prerequisites1);
        System.out.println(Arrays.toString(result1));
        // 可能的输出: [0, 1, 2, 3] 或 [0, 2, 1, 3]，表示可行的课程顺序

        // 测试用例2：没有先修课程
        int numCourses2 = 2;
        int[][] prerequisites2 = {};
        int[] result2 = solution.findOrder(numCourses2, prerequisites2);
        System.out.println(Arrays.toString(result2));
        // 可能的输出: [0,1] 或 [1,0]

        // 测试用例3：存在环，无法完成所有课程
        int numCourses3 = 3;
        int[][] prerequisites3 = {{1, 0}, {0, 1}};
        int[] result3 = solution.findOrder(numCourses3, prerequisites3);
        System.out.println(Arrays.toString(result3));
        // 输出: [] （由于存在环，无法完成所有课程）
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
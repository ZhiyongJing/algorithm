package leetcode.frequent.dfs;

import java.util.ArrayList;

/**
  *@Question:  1376. Time Needed to Inform All Employees     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 50.18%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

/**
 * 这个问题可以通过深度优先搜索（DFS）来解决。首先，我们将公司的管理关系表示为一个树，其中每个节点代表一个员工，边表示上下级关系。
 *
 * 在DFS的过程中，我们从公司领导（根节点）开始，递归地访问每个员工，并计算通知每个员工所需的总时间。对于每个员工，我们遍历其所有下属员工，
 * 递归计算下属员工获取消息所需的时间，最终得到通知当前员工所需的总时间。
 *
 * 时间复杂度分析：
 *
 * 对于每个员工，我们需要访问其下属员工，时间复杂度为 O(N)，其中 N 为公司的总员工数。因此，整体的时间复杂度为 O(N)。
 * 空间复杂度分析：
 *
 * 我们使用了邻接列表来表示员工之间的关系，其空间复杂度为 O(N)。递归调用的深度最多为公司的高度，公司的高度最坏情况下为 N，
 * 所以递归调用的空间复杂度为 O(N)。因此，整体的空间复杂度为 O(N)。
 */

public class LeetCode_1376_TimeNeededToInformAllEmployees {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int maxTime = Integer.MIN_VALUE;

        /**
         * DFS 遍历树，并计算每个员工获取消息所需的最大时间
         *
         * @param adjList     员工关系的邻接列表
         * @param informTime  员工获取消息所需的时间数组
         * @param curr        当前员工的 ID
         * @param time        从根节点到当前员工所需的时间
         */
        void DFS(ArrayList<ArrayList<Integer>> adjList, int[] informTime, int curr, int time) {
            // 记录员工获取消息所需的最大时间
            maxTime = Math.max(maxTime, time);


            // 遍历当前员工的下属员工
            for (int adjacent : adjList.get(curr)) {
                // 递归访问下属员工，获取消息所需的时间为当前员工获取消息所需的时间 + 下属员工获取消息所需的时间
                DFS(adjList, informTime, adjacent, time + informTime[curr]);
            }

        }

        /**
         * 计算通知所有员工所需的总时间
         *
         * @param n           公司总员工数
         * @param headID      公司领导的 ID
         * @param manager     每个员工的直接上级的 ID 数组
         * @param informTime  员工获取消息所需的时间数组
         * @return            通知所有员工所需的总时间
         */
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            // 创建邻接列表，用于表示员工之间的关系
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }

            // 构建邻接列表，每个索引存储下属员工的 ID
            for (int i = 0; i < n; i++) {
                if (manager[i] != -1) {
                    adjList.get(manager[i]).add(i);
                }
            }
            System.out.println(adjList);

            // 从公司领导开始 DFS，计算通知所有员工所需的总时间
            DFS(adjList, informTime, headID, 0);
            return maxTime;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_1376_TimeNeededToInformAllEmployees.Solution solution =
                new LeetCode_1376_TimeNeededToInformAllEmployees().new Solution();

        // 测试用例
        int n1 = 1, headID1 = 0;
        int[] manager1 = {-1}, informTime1 = {0};
        System.out.println(solution.numOfMinutes(n1, headID1, manager1, informTime1));  // Output: 0

        int n2 = 6, headID2 = 2;
        int[] manager2 = {2, 2, -1, 2, 2, 2}, informTime2 = {0, 0, 1, 0, 0, 0};
        System.out.println(solution.numOfMinutes(n2, headID2, manager2, informTime2));  // Output: 1
    }
}

/**
A company has n employees with a unique ID for each employee from 0 to n - 1. 
The head of the company is the one with headID. 

 Each employee has one direct manager given in the manager array where manager[
i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is 
guaranteed that the subordination relationships have a tree structure. 

 The head of the company wants to inform all the company employees of an urgent 
piece of news. He will inform his direct subordinates, and they will inform 
their subordinates, and so on until all employees know about the urgent news. 

 The i-th employee needs informTime[i] minutes to inform all of his direct 
subordinates (i.e., After informTime[i] minutes, all his direct subordinates can 
start spreading the news). 

 Return the number of minutes needed to inform all the employees about the 
urgent news. 

 
 Example 1: 

 
Input: n = 1, headID = 0, manager = [-1], informTime = [0]
Output: 0
Explanation: The head of the company is the only employee in the company.
 

 Example 2: 
 
 
Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
Output: 1
Explanation: The head of the company with id = 2 is the direct manager of all 
the employees in the company and needs 1 minute to inform them all.
The tree structure of the employees in the company is shown.
 

 
 Constraints: 

 
 1 <= n <= 10⁵ 
 0 <= headID < n 
 manager.length == n 
 0 <= manager[i] < n 
 manager[headID] == -1 
 informTime.length == n 
 0 <= informTime[i] <= 1000 
 informTime[i] == 0 if employee i has no subordinates. 
 It is guaranteed that all the employees can be informed. 
 

 Related Topics Tree Depth-First Search Breadth-First Search 👍 3943 👎 276

*/

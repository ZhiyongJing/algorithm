package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.List;
/**
 *@Question:  797. All Paths From Source to Target
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 29.14%
 *@Time  Complexity: O(2^N * N), there are 2^(n -1) - 1 path, each path take N step to build path
 *@Space Complexity: O(N)
 */
/*
【题目描述】
LeetCode 797：所有从源节点到目标节点的路径 (All Paths From Source to Target)

给定一个 **有向无环图（DAG）**，用一个列表的列表表示，其中 `graph[i]` 是一个从节点 `i` 可以直接到达的所有节点的列表。
目标是找到所有从源节点（节点 0）到目标节点（最后一个节点）的路径，并返回这些路径的列表。

图的表示方式：
- `graph[i]` 表示从节点 `i` 可以到达的所有节点。
- 例如，`graph = {{1, 2}, {3}, {3}, {}}` 表示节点 0 可以到达节点 1 和节点 2，节点 1 和节点 2 都可以到达节点 3，节点 3 没有可以到达的节点。

要求：
- 返回所有从源节点 0 到目标节点 `n-1` 的路径列表。

**示例：**
输入：`graph = {{1, 2}, {3}, {3}, {}}`
输出：`[[0, 1, 3], [0, 2, 3]]`

解释：
- 从节点 0 到目标节点 3，有两条路径：[0 -> 1 -> 3] 和 [0 -> 2 -> 3]。
*/

/*
【解题思路】
这道题的核心是用 **回溯算法** 找出所有可能的路径。
我们从节点 0 开始递归遍历所有的邻居节点，构建路径，当路径到达目标节点时，将路径加入结果集。

### 步骤 1：将图和目标节点初始化
- 定义目标节点为 `target = graph.length - 1`。
- 定义图 `graph`。
- 定义结果集 `results`，用于存储所有路径。

### 步骤 2：从源节点 0 开始回溯
- 定义回溯方法 `backtrack(currNode, path)`。
- 每次回溯时，将当前节点加入路径 `path`。
- 如果当前节点等于目标节点，将路径添加到结果集中。
- 否则，遍历当前节点的所有邻居节点，并递归调用 `backtrack`。

**举例：**
对于输入 `graph = {{1, 2}, {3}, {3}, {}}`：

- 初始路径为 `[0]`。
- 从节点 0 开始：
  - 到达节点 1，路径为 `[0, 1]`。
  - 从节点 1 到节点 3，路径为 `[0, 1, 3]`，这是有效路径，加入结果集。
  - 回退到节点 0。
  - 到达节点 2，路径为 `[0, 2]`。
  - 从节点 2 到节点 3，路径为 `[0, 2, 3]`，这是有效路径，加入结果集。

最终结果集为 `[[0, 1, 3], [0, 2, 3]]`。

### 步骤 3：回溯过程中的选择与撤销
- 每次选择一个邻居节点时，将节点加入路径。
- 在递归返回后，移除上一个选择（撤销选择），尝试下一个邻居节点。

**举例解释撤销选择：**
- 路径 `[0, 1, 3]` 完成后，回退到 `[0, 1]`。
- 从 `[0, 1]` 回退到 `[0]`。
- 从 `[0]` 继续探索节点 2。

---

### 时间和空间复杂度分析

**时间复杂度：O(2^n * n)**
- 对于一个有向无环图（DAG），最坏情况下可能有 2^n 条路径（例如，每个节点都可以到达目标节点）。
- 因此，时间复杂度是 **O(2^n)**，其中 `n` 是节点的数量。

**空间复杂度：O(n)**
- 递归的深度最多为 `n`，每次递归调用会存储一个路径 `path`。
- 最坏情况下，路径的长度等于节点的数量，因此空间复杂度为 **O(n)**。
*/

public class LeetCode_797_AllPathsFromSourceToTarget{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义回溯方法
        protected void backtrack(int currNode, List<Integer> path, int[][] graph, List<List<Integer>> results) {
            // 如果当前节点等于目标节点，将路径添加到结果集中
            if (currNode == graph.length - 1) {
                results.add(new ArrayList<Integer>(path));
                return;
            }
            // 遍历当前节点的邻居节点
//            for (int nextNode : graph[currNode]) {
//                // 在回溯前将选择标记在路径中
//                path.add(nextNode);
//                // 递归调用回溯方法
//                this.backtrack(nextNode, path,  graph, results);
//                // 移除前一个选择，尝试下一个选择
//                path.remove(path.size() - 1);
//            }
            //第二种for loop写法
            for(int i = 0; i < graph[currNode].length; i++){
                // System.out.println( graph[i][0]+" " + current +" "+ j);
                path.add(graph[currNode][i]);
                backtrack(graph[currNode][i], path, graph, results);
                path.remove(path.size() - 1);


            }
        }

        // 定义求解方法
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // 初始化目标节点、图、结果集
            int target = graph.length - 1;
            ArrayList<List<Integer>>results = new ArrayList<List<Integer>>();
            // 从源节点（节点0）开始回溯
            List<Integer> path = new ArrayList<Integer>();
            path.add(0);
            backtrack(0, path, graph, results);
            return results;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_797_AllPathsFromSourceToTarget().new Solution();
        // TO TEST
        // 添加测试样例
        int[][] graph = {{1,2}, {3}, {3}, {}};
        List<List<Integer>> paths = solution.allPathsSourceTarget(graph);
        System.out.println("All paths from source to target:");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }
}


/**
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find 
all possible paths from node 0 to node n - 1 and return them in any order. 

 The graph is given as follows: graph[i] is a list of all nodes you can visit 
from node i (i.e., there is a directed edge from node i to node graph[i][j]). 

 
 Example 1: 
 
 
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 

 Example 2: 
 
 
Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 

 
 Constraints: 

 
 n == graph.length 
 2 <= n <= 15 
 0 <= graph[i][j] < n 
 graph[i][j] != i (i.e., there will be no self-loops). 
 All the elements of graph[i] are unique. 
 The input graph is guaranteed to be a DAG. 
 

 Related Topics Backtracking Depth-First Search Breadth-First Search Graph 👍 71
70 👎 146

*/
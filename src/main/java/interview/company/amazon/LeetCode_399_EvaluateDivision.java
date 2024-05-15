package interview.company.amazon;
//package leetcode.question.dfs;
import java.util.*;

/**
 *@Question:  399. Evaluate Division
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 73.42%
 *@Time  Complexity: O(m*n)N be the number of input equations and M be the number of queries.
 *@Space Complexity: O(n)
 */

/**
 * 这个问题可以使用图的深度优先搜索（DFS）来解决。主要思路如下：
 *
 * 1. **构建图：** 将给定的方程式和对应的值构建成一个有向图。每个变量都是图中的一个节点，每个方程式都表示两个节点之间的有向边，权值为该方程式的值。
 *
 * 2. **DFS求解：** 对于每个查询，使用深度优先搜索（DFS）来查找从被除数到除数的路径，并累积路径上的权值。如果路径存在，
 * 累积的权值即为结果；如果路径不存在，则结果为-1.0。
 *
 * 算法的主要步骤：
 *
 * - 创建一个HashMap，用于存储图的邻接关系。每个节点（字符串变量）对应一个HashMap，其中包含从该节点出发的所有有向边。
 * - 遍历给定的方程式，构建图。
 * - 对于每个查询，通过DFS查找路径，并计算累积的权值。
 *
 * 接下来是算法的时间和空间复杂度分析：
 *
 * - **时间复杂度：** 构建图的过程需要遍历方程式，因此时间复杂度为 O(E)，其中 E 为方程式的数量。对于每个查询，
 * DFS的时间复杂度为 O(V + E)，其中 V 为节点的数量。因此，总的时间复杂度为 O(Q * (V + E))，其中 Q 为查询的数量。
 * - **空间复杂度：** 用于存储图的空间复杂度为 O(V + E)，其中 V 为节点的数量，E 为边的数量。DFS递归调用的空间复杂度为 O(V)。
 * 因此，总的空间复杂度为 O(V + E)。
 *
 * 这个算法在实践中通常具有较好的性能，特别是对于稀疏图。
 */

public class LeetCode_399_EvaluateDivision {
    //leetcode submit region begin(Prohibit modification and deletion)
    // 定义一个内部类 Solution
    class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values,
                                     List<List<String>> queries) {

            // 创建一个图的数据结构，使用HashMap表示
            // 图的每个节点对应一个字符串，其相邻节点和权重存储在内部HashMap中
            HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

            // Step 1). 从方程中构建图
            for (int i = 0; i < equations.size(); i++) {
                List<String> equation = equations.get(i);
                String dividend = equation.get(0), divisor = equation.get(1);
                double quotient = values[i];

                // 如果图中不包含被除数，添加该节点到图中
                if (!graph.containsKey(dividend))
                    graph.put(dividend, new HashMap<String, Double>());

                // 如果图中不包含除数，添加该节点到图中
                if (!graph.containsKey(divisor))
                    graph.put(divisor, new HashMap<String, Double>());

                // 将权重信息添加到图中
                graph.get(dividend).put(divisor, quotient);
                graph.get(divisor).put(dividend, 1 / quotient);
            }
            System.out.println(graph);

            // Step 2). 通过回溯法（DFS）计算每个查询的结果
            double[] results = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                List<String> query = queries.get(i);
                String dividend = query.get(0), divisor = query.get(1);

                // 如果图中不包含被除数或除数，结果为-1.0
                if (!graph.containsKey(dividend) || !graph.containsKey(divisor))
                    results[i] = -1.0;
                    // 如果被除数等于除数，结果为1.0
                else if (dividend.equals(divisor))
                    results[i] = 1.0;
                    // 使用深度优先搜索计算结果
                else {
                    HashSet<String> visited = new HashSet<>();
                    results[i] = backtrackEvaluate(graph, dividend, divisor, 1, visited);
                }
            }

            return results;
        }

        // 辅助函数，使用深度优先搜索计算结果
        private double backtrackEvaluate(HashMap<String, HashMap<String, Double>> graph,
                                         String currNode, String targetNode, double accProduct, Set<String> visited) {

            // 标记当前节点已访问
            visited.add(currNode);
            double ret = -1.0;

            Map<String, Double> neighbors = graph.get(currNode);
            // 如果当前节点的相邻节点中包含目标节点，计算结果
            if (neighbors.containsKey(targetNode))
                ret = accProduct * neighbors.get(targetNode);
            else {
                // 遍历相邻节点，继续深度优先搜索
                for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
                    String nextNode = pair.getKey();
                    // 如果节点已访问，跳过
                    if (visited.contains(nextNode))
                        continue;
                    // 递归计算结果
                    ret = backtrackEvaluate(graph, nextNode, targetNode,
                            accProduct * pair.getValue(), visited);
                    // 如果找到结果，中断循环
                    if (ret != -1.0)
                        break;
                }
            }

            // 取消标记，为下一次回溯做准备
            visited.remove(currNode);
            return ret;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    // 测试代码
    public static void main(String[] args) {
        leetcode.question.dfs.LeetCode_399_EvaluateDivision leetCode = new leetcode.question.dfs.LeetCode_399_EvaluateDivision();
        leetcode.question.dfs.LeetCode_399_EvaluateDivision.Solution solution = leetCode.new Solution();

        // 测试用例
        List<List<String>> equations1 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values1 = {2.0, 3.0};
        List<List<String>> queries1 = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"),
                Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));

        double[] results1 = solution.calcEquation(equations1, values1, queries1);
        System.out.println(Arrays.toString(results1));

        List<List<String>> equations2 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("bc", "cd"));
        double[] values2 = {1.5, 2.5, 5.0};
        List<List<String>> queries2 = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"), Arrays.asList("cd", "bc"));

        double[] results2 = solution.calcEquation(equations2, values2, queries2);
        System.out.println(Arrays.toString(results2));

        List<List<String>> equations3 = Arrays.asList(Arrays.asList("a", "b"));
        double[] values3 = {0.5};
        List<List<String>> queries3 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "a"),
                Arrays.asList("a", "c"), Arrays.asList("x", "y"));

        double[] results3 = solution.calcEquation(equations3, values3, queries3);
        System.out.println(Arrays.toString(results3));
    }
}

/**
 You are given an array of variable pairs equations and an array of real numbers
 values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai /
 Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

 You are also given some queries, where queries[j] = [Cj, Dj] represents the jᵗʰ
 query where you must find the answer for Cj / Dj = ?.

 Return the answers to all queries. If a single answer cannot be determined,
 return -1.0.

 Note: The input is always valid. You may assume that evaluating the queries
 will not result in division by zero and that there is no contradiction.

 Note: The variables that do not occur in the list of equations are undefined,
 so the answer cannot be determined for them.


 Example 1:


 Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a",
 "c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 Explanation:
 Given: a / b = 2.0, b / c = 3.0
 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 note: x is undefined => -1.0

 Example 2:


 Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
 queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 Output: [3.75000,0.40000,5.00000,0.20000]


 Example 3:


 Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],[
 "a","c"],["x","y"]]
 Output: [0.50000,2.00000,-1.00000,-1.00000]



 Constraints:


 1 <= equations.length <= 20
 equations[i].length == 2
 1 <= Ai.length, Bi.length <= 5
 values.length == equations.length
 0.0 < values[i] <= 20.0
 1 <= queries.length <= 20
 queries[i].length == 2
 1 <= Cj.length, Dj.length <= 5
 Ai, Bi, Cj, Dj consist of lower case English letters and digits.


 Related Topics Array Depth-First Search Breadth-First Search Union Find Graph
 Shortest Path 👍 8834 👎 829

 */

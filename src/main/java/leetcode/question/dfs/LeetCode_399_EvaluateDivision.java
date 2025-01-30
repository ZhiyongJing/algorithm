// 声明所在的包名
package leetcode.question.dfs;

// 导入Java标准库中的所有类，包含集合框架等
import java.util.*;

/**
 *@Question:  399. Evaluate Division
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 73.42%
 *@Time  Complexity: O(m*n)N be the number of input equations and M be the number of queries.
 *@Space Complexity: O(n)
 */
public class LeetCode_399_EvaluateDivision {
    //leetcode submit region begin(Prohibit modification and deletion)

    // 定义一个内部类 Solution
    class Solution {
        //Solution1: DFS

        /**
         * 计算方程组的查询结果
         *
         * @param equations 输入的方程列表，每个方程为两个变量的列表
         * @param values    输入的方程对应的值
         * @param queries   要查询的方程列表
         * @return 查询结果的数组
         */
        public double[] calcEquation(List<List<String>> equations, double[] values,
                                     List<List<String>> queries) {

            // 创建一个图的数据结构，使用HashMap表示
            // 图的每个节点对应一个字符串，其相邻节点和权重存储在内部HashMap中
            HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

            // Step 1). 从方程中构建图
            // 遍历所有方程，构建有向图
            for (int i = 0; i < equations.size(); i++) {
                // 获取当前方程的被除数和除数
                List<String> equation = equations.get(i);
                String dividend = equation.get(0), divisor = equation.get(1);
                // 获取当前方程的商
                double quotient = values[i];

                // 如果图中不包含被除数，添加该节点到图中
                if (!graph.containsKey(dividend))
                    graph.put(dividend, new HashMap<String, Double>());

                // 如果图中不包含除数，添加该节点到图中
                if (!graph.containsKey(divisor))
                    graph.put(divisor, new HashMap<String, Double>());

                // 将权重信息添加到图中
                // 被除数指向除数的边权为商
                graph.get(dividend).put(divisor, quotient);
                // 除数指向被除数的边权为1/商
                graph.get(divisor).put(dividend, 1 / quotient);
            }
            // 打印构建好的图，方便调试
            System.out.println(graph);

            // Step 2). 通过回溯法（DFS）计算每个查询的结果
            // 初始化结果数组
            double[] results = new double[queries.size()];
            // 遍历每一个查询
            for (int i = 0; i < queries.size(); i++) {
                // 获取当前查询的被除数和除数
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
                    // 创建一个集合用于记录已访问的节点，防止循环
                    HashSet<String> visited = new HashSet<>();
                    // 调用辅助函数进行DFS搜索
                    results[i] = backtrackEvaluate(graph, dividend, divisor, 1, visited);
                }
            }

            // 返回所有查询的结果
            return results;
        }

        /**
         * 辅助函数，使用深度优先搜索计算两个节点之间的乘积
         *
         * @param graph      图的邻接表表示
         * @param currNode   当前访问的节点
         * @param targetNode 目标节点
         * @param accProduct 累积的乘积
         * @param visited    已访问的节点集合
         * @return 两节点之间的乘积，如果不存在路径则返回-1.0
         */
        private double backtrackEvaluate(HashMap<String, HashMap<String, Double>> graph,
                                         String currNode, String targetNode, double accProduct, Set<String> visited) {

            // 标记当前节点已访问
            visited.add(currNode);
            // 初始化返回值为-1.0，表示未找到路径
            double ret = -1.0;

            // 获取当前节点的所有邻居
            Map<String, Double> neighbors = graph.get(currNode);
            // 如果当前节点的相邻节点中包含目标节点，直接计算结果
            if (neighbors.containsKey(targetNode))
                ret = accProduct * neighbors.get(targetNode);
            else {
                // 遍历所有相邻节点，继续深度优先搜索
                for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
                    String nextNode = pair.getKey();
                    // 如果节点已访问，跳过以避免循环
                    if (visited.contains(nextNode))
                        continue;
                    // 递归调用，累积乘积
                    ret = backtrackEvaluate(graph, nextNode, targetNode,
                            accProduct * pair.getValue(), visited);
                    // 如果找到有效路径，终止循环
                    if (ret != -1.0)
                        break;
                }
            }

            // 取消标记当前节点，为下一次回溯做准备
            visited.remove(currNode);
            // 返回最终结果
            return ret;
        }

        //Solution2: bfs
        // 这里可以添加基于BFS的解决方案
    }
    //leetcode submit region end(Prohibit modification and deletion)

    // 测试代码
    public static void main(String[] args) {
        // 创建LeetCode_399_EvaluateDivision对象
        LeetCode_399_EvaluateDivision leetCode = new LeetCode_399_EvaluateDivision();
        // 创建Solution对象
        Solution solution = leetCode.new Solution();

        // 测试用例1
        // 准备第一个测试用例的方程列表
        List<List<String>> equations1 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );
        // 准备第一个测试用例的方程值
        double[] values1 = {2.0, 3.0};
        // 准备第一个测试用例的查询列表
        List<List<String>> queries1 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );

        // 计算第一个测试用例的结果
        double[] results1 = solution.calcEquation(equations1, values1, queries1);
        // 输出第一个测试用例的结果
        System.out.println("Test Case 1 Results: " + Arrays.toString(results1));
        // 期望输出：[6.0, 0.5, -1.0, 1.0, -1.0]

        // 测试用例2
        // 准备第二个测试用例的方程列表
        List<List<String>> equations2 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("bc", "cd")
        );
        // 准备第二个测试用例的方程值
        double[] values2 = {1.5, 2.5, 5.0};
        // 准备第二个测试用例的查询列表
        List<List<String>> queries2 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc")
        );

        // 计算第二个测试用例的结果
        double[] results2 = solution.calcEquation(equations2, values2, queries2);
        // 输出第二个测试用例的结果
        System.out.println("Test Case 2 Results: " + Arrays.toString(results2));
        // 期望输出：[3.75, 0.4, 5.0, 0.2]

        // 测试用例3
        // 准备第三个测试用例的方程列表
        List<List<String>> equations3 = Arrays.asList(
                Arrays.asList("a", "b")
        );
        // 准备第三个测试用例的方程值
        double[] values3 = {0.5};
        // 准备第三个测试用例的查询列表
        List<List<String>> queries3 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "c"),
                Arrays.asList("x", "y")
        );

        // 计算第三个测试用例的结果
        double[] results3 = solution.calcEquation(equations3, values3, queries3);
        // 输出第三个测试用例的结果
        System.out.println("Test Case 3 Results: " + Arrays.toString(results3));
        // 期望输出：[0.5, 2.0, -1.0, -1.0]
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

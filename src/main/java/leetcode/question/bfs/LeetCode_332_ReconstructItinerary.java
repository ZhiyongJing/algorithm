package leetcode.question.bfs;
import java.util.*;

/**
 *@Question:  332. 重新安排行程 (Reconstruct Itinerary)
 *@Difficulty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N log N) - 每个机场的邻接列表是最小堆（优先队列），插入和删除操作为 O(log N)，N 为航班数
 *@Space Complexity: O(N) - 存储航班图的 HashMap，以及递归调用栈或迭代的 Stack
 */
/*
 * 题目描述：
 * 你需要重新构造一条行程，给定一组机票，每张机票的格式为 [出发地, 目的地]。
 * 行程必须从 "JFK" 机场开始，并且如果存在多个可行路径，必须选择字典序最小的路径。
 * 如果有多个相同的目的地，优先选择字典序最小的目的地。
 * 题目保证至少存在一条可行的行程。
 *
 * 输入：
 * - List<List<String>> tickets：表示机票，每张机票的格式为 [from, to]
 *
 * 输出：
 * - List<String>：按顺序返回重构的行程
 *
 * 示例 1：
 * 输入：
 *   tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出：
 *   ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * 示例 2：
 * 输入：
 *   tickets = [["JFK", "SFO"], ["JFK", "ATL"], ["SFO", "ATL"], ["ATL", "JFK"], ["ATL", "SFO"]]
 * 输出：
 *   ["JFK", "ATL", "JFK", "SFO", "ATL", "SFO"]
 *
 * 题目保证至少存在一条合理的行程，所有机票都必须被使用一次。
 */

/*
 * 解题思路：
 * 1. **构建邻接表**：
 *    - 使用 `Map<String, PriorityQueue<String>>` 存储机票信息，其中键是出发地，值是按照字典序排列的目的地优先队列。
 *    - 例如，对于输入 [["JFK", "SFO"], ["JFK", "ATL"], ["SFO", "ATL"], ["ATL", "JFK"], ["ATL", "SFO"]]
 *      构建的邻接表如下：
 *      {
 *        "JFK": ["ATL", "SFO"],
 *        "ATL": ["JFK", "SFO"],
 *        "SFO": ["ATL"]
 *      }
 *
 * 2. **使用 DFS（深度优先搜索）+ 递归 或者 非递归 DFS（栈） 来构造行程**
 *    - 目标是找到 **欧拉路径**（Eulerian Path），即访问所有边（机票）且每条边仅使用一次的路径。
 *    - 采用 **Hierholzer 算法**，在 DFS 结束后逆序插入行程。
 *    - 访问时总是优先访问字典序最小的目的地，确保字典序最小。
 *
 * 3. **具体步骤：**
 *    - 从 "JFK" 出发，使用 DFS 递归遍历所有目的地。
 *    - 在遍历完成时，将机场添加到结果列表中（逆序插入）。
 *    - 例如：
 *      - 如果 `tickets = [["JFK", "SFO"], ["JFK", "ATL"], ["SFO", "ATL"], ["ATL", "JFK"], ["ATL", "SFO"]]`
 *      - 递归访问顺序：
 *        1. "JFK" -> "ATL"
 *        2. "ATL" -> "JFK"
 *        3. "JFK" -> "SFO"
 *        4. "SFO" -> "ATL"
 *        5. "ATL" -> "SFO"
 *      - 逆序插入最终行程：
 *        ["JFK", "ATL", "JFK", "SFO", "ATL", "SFO"]
 *
 * 4. **非递归 DFS 版本**
 *    - 使用栈模拟 DFS 过程。
 *    - 访问时每次从栈顶取出当前机场，若当前机场仍有未访问的目的地，则将字典序最小的目的地加入栈。
 *    - 当栈顶机场无未访问的目的地时，将其插入结果列表（倒序插入）。
 */

/*
 * 时间和空间复杂度分析：
 *
 * - **时间复杂度：O(E log E)**
 *   - 机票的总数为 `E`，由于我们使用 `PriorityQueue` 维护目的地，每次插入/删除操作的复杂度为 `O(log E)`。
 *   - 在最坏情况下，我们需要遍历所有 `E` 条机票，并进行 `log E` 级别的插入/删除操作，因此总复杂度为 `O(E log E)`。
 *
 * - **空间复杂度：O(E)**
 *   - 存储邻接表 `Map<String, PriorityQueue<String>>` 需要 `O(E)` 额外空间。
 *   - 递归 DFS 需要 `O(E)` 级别的递归栈空间，或 `O(E)` 级别的栈空间（非递归版本）。
 *   - 结果列表 `route` 需要 `O(E)` 空间存储最终行程。
 *   - 所以整体空间复杂度是 `O(E)`。
 */


public class LeetCode_332_ReconstructItinerary{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 方法1：使用 BFS（广度优先搜索） + 栈
        public List<String> findItinerary(List<List<String>> tickets) {
            // 创建一个邻接表，键是出发机场，值是一个按字母顺序排列的最小堆（优先队列）
            Map<String, PriorityQueue<String>> adj = new HashMap<>();

            // 遍历所有机票，构建邻接表
            for (List<String> ticket : tickets) {
                // `computeIfAbsent` 方法用于检查 key 是否存在，不存在则创建新的 PriorityQueue
                adj.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
            }

            // 结果列表，存储最终的行程
            List<String> route = new LinkedList<>();
            // 使用栈来模拟 DFS 过程
            Stack<String> stack = new Stack<>();
            // JFK 是行程的起点
            stack.push("JFK");

            // 迭代遍历栈
            while (!stack.empty()) {
                // 当栈顶机场仍有未访问的目的地时，不断深入
                while (adj.containsKey(stack.peek()) && !adj.get(stack.peek()).isEmpty()) {
                    // 访问当前机场字典序最小的目的地，并推入栈
                    stack.push(adj.get(stack.peek()).poll());
                }
                // 栈顶元素无后续可达机场时，添加到结果列表（倒序插入）
                route.add(0, stack.pop());
            }
            return route;
        }

        // 方法2：使用 DFS（深度优先搜索） + 递归
        public List<String> findItinerary2(List<List<String>> tickets) {
            // 遍历所有机票，构建邻接表
            for (List<String> ticket : tickets) {
                targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
            }
            // 递归 DFS 访问所有航班
            visit("JFK");
            return route;
        }

        // 使用 HashMap 存储航班信息，每个出发机场对应一个最小堆，确保按字典序访问
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        // 存储最终行程
        List<String> route = new LinkedList<>();

        // 递归访问机场
        void visit(String airport) {
            // 只要当前机场仍有未访问的目的地，递归访问
            while (targets.containsKey(airport) && !targets.get(airport).isEmpty()) {
                visit(targets.get(airport).poll());
            }
            // 机场没有后续可达机场时，加入结果列表（倒序插入）
            route.add(0, airport);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_332_ReconstructItinerary().new Solution();

        // 测试样例
        List<List<String>> tickets1 = Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO")
        );

        // 预期输出: ["JFK", "ATL", "JFK", "SFO", "ATL", "SFO"]
        System.out.println(solution.findItinerary(tickets1));

        List<List<String>> tickets2 = Arrays.asList(
                Arrays.asList("JFK", "KUL"),
                Arrays.asList("JFK", "NRT"),
                Arrays.asList("NRT", "JFK")
        );

        // 预期输出: ["JFK", "NRT", "JFK", "KUL"]
        System.out.println(solution.findItinerary2(tickets2));
    }
}

/**
You are given a list of airline tickets where tickets[i] = [fromi, toi] 
represent the departure and the arrival airports of one flight. Reconstruct the 
itinerary in order and return it. 

 All of the tickets belong to a man who departs from "JFK", thus, the itinerary 
must begin with "JFK". If there are multiple valid itineraries, you should 
return the itinerary that has the smallest lexical order when read as a single 
string. 

 
 For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than [
"JFK", "LGB"]. 
 

 You may assume all tickets form at least one valid itinerary. You must use all 
the tickets once and only once. 

 
 Example 1: 
 
 
Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
 

 Example 2: 
 
 
Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],[
"ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL",
"SFO"] but it is larger in lexical order.
 

 
 Constraints: 

 
 1 <= tickets.length <= 300 
 tickets[i].length == 2 
 fromi.length == 3 
 toi.length == 3 
 fromi and toi consist of uppercase English letters. 
 fromi != toi 
 

 Related Topics Depth-First Search Graph Eulerian Circuit 👍 6035 👎 1892

*/
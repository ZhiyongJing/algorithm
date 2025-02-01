package interview.company.bloomberg;

import javafx.util.Pair;

import java.util.*;
/*
 * 题目描述:
 *
 * 给定一个货币兑换系统，其中不同货币之间的汇率是已知的，并且可以双向兑换。
 * 例如，我们知道 A -> B 的汇率是 2.0，那么 B -> A 的汇率就是 1 / 2.0 = 0.5。
 *
 * 目标:
 * 实现一个系统，支持以下两种操作：
 * 1. addExchangeRate(from, to, rate) - 添加一条汇率信息，支持双向兑换
 * 2. getExchangeRate(from, to) - 查询某两种货币之间的兑换率，返回最优的兑换路径的汇率乘积。
 *    如果无法兑换，则返回 -1.0。
 *
 * 示例：
 * 输入：
 *  addExchangeRate("A", "B", 2.0)
 *  addExchangeRate("B", "C", 3.0)
 *  addExchangeRate("C", "D", 4.0)
 * 查询：
 *  getExchangeRate("A", "D") -> 2.0 * 3.0 * 4.0 = 24.0
 *  getExchangeRate("A", "K") -> -1.0 (A 和 K 之间没有路径)
 *
 */

/*
 * 解题思路：
 *
 * 1. **使用图 (Graph) 进行建模**:
 *    - 将货币视为 **图中的节点**，每个已知汇率视为 **带权边**，边的权值是汇率值。
 *    - 由于汇率是双向的，因此如果 A -> B = 2.0，则需要同时存储 B -> A = 1 / 2.0 = 0.5。
 *    - 例如：
 *      A -> B (2.0),  B -> A (0.5)
 *      B -> C (3.0),  C -> B (1/3.0)
 *      C -> D (4.0),  D -> C (1/4.0)
 *    - 采用 **邻接表 (Adjacency List)** 存储图，使用 `HashMap<String, List<Pair<String, Double>>>`。
 *
 * 2. **构建 addExchangeRate() 方法**:
 *    - 通过 `HashMap` 维护图结构：
 *      1. 如果 `from` 货币还未添加到 `graph`，则创建新的 `ArrayList` 存储其邻居。
 *      2. 将 `to` 货币及其汇率 `rate` 添加到 `from` 的邻居列表中。
 *      3. 由于汇率是双向的，因此同时添加 `to -> from` 的边，权值为 `1 / rate`。
 *    - **举例**：
 *      ```
 *      addExchangeRate("A", "B", 2.0) -> 存入 A->B(2.0), B->A(0.5)
 *      addExchangeRate("B", "C", 3.0) -> 存入 B->C(3.0), C->B(1/3.0)
 *      addExchangeRate("C", "D", 4.0) -> 存入 C->D(4.0), D->C(1/4.0)
 *      ```
 *    - **最终形成的邻接表**：
 *      ```
 *      A -> [(B, 2.0)]
 *      B -> [(A, 0.5), (C, 3.0)]
 *      C -> [(B, 1/3.0), (D, 4.0)]
 *      D -> [(C, 1/4.0)]
 *      ```
 *
 * 3. **实现 getExchangeRate() 方法**:
 *    - **BFS 搜索最优路径**：
 *      1. **从起点货币 `from` 出发**，初始化 `Queue<Pair<String, Double>>`，起始值为 `(from, 1.0)`。
 *      2. **使用 `Set<String>` 记录访问过的节点**，避免重复计算。
 *      3. **每次从队列中取出一个货币 `current`**，如果 `current == to`，直接返回当前的汇率乘积 `rate`。
 *      4. **遍历当前货币的邻居**：
 *          - 若邻居未访问，则加入队列，并更新累计汇率 `rate * neighbor_rate`。
 *      5. **若 BFS 遍历完仍未找到 `to`，返回 -1.0**，表示无法兑换。
 *
 *    - **举例**：
 *      ```
 *      getExchangeRate("A", "D")：
 *      1. 初始状态：queue = [(A, 1.0)]
 *      2. 取出 A，遍历邻居 B，A->B 汇率为 2.0，加入队列 queue = [(B, 2.0)]
 *      3. 取出 B，遍历邻居 C，B->C 汇率为 3.0，累计 2.0 * 3.0 = 6.0，queue = [(C, 6.0)]
 *      4. 取出 C，遍历邻居 D，C->D 汇率为 4.0，累计 6.0 * 4.0 = 24.0，找到目标 D，返回 24.0。
 *      ```
 *    - **BFS 终止条件**：
 *      - 如果 `to` 货币在遍历过程中被访问，则立即返回当前累计汇率。
 *      - 如果遍历完整个可达路径仍未找到 `to`，则返回 `-1.0`（目标货币不存在或不可兑换）。
 *
 */

/*
 * 时间和空间复杂度分析：
 *
 * 1. **addExchangeRate(from, to, rate)**
 *    - **时间复杂度：O(1)** （在 `HashMap` 中添加键值对是 O(1)）
 *    - **空间复杂度：O(E)** （E 为边的数量，即汇率关系的数量）
 *
 * 2. **getExchangeRate(from, to)**
 *    - **时间复杂度：O(N)**
 *      - BFS 遍历邻接表，最坏情况下要访问所有 `N` 个货币，时间复杂度 O(N)。
 *      - 每个节点的邻居最多有 `N-1` 个，因此总遍历边的时间仍然是 O(N)。
 *    - **空间复杂度：O(N)**
 *      - 需要 `Set<String>` 记录访问过的节点 O(N)。
 *      - 需要 `Queue<Pair<String, Double>>` 存储待访问的货币，最坏情况下队列大小为 O(N)。
 *
 * 3. **整体复杂度**
 *    - **预处理（建图）是 O(E)，查询（BFS）是 O(N)，因此整体查询复杂度是 O(N)**。
 *    - **如果 N 远小于 E（即货币种类远小于汇率关系数），BFS 查询仍然高效**。
 */


class CurrencyExchange {
    // 存储汇率的图
    private final Map<String, List<Pair<String, Double>>> graph = new HashMap<>();

    /**
     * 添加汇率
     */
    public void addExchangeRate(String from, String to, double rate) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());

        // 双向边
        graph.get(from).add(new Pair<>(to, rate));
        graph.get(to).add(new Pair<>(from, 1.0 / rate));
    }

    /**
     * 计算 A -> B 的汇率
     */
    public double getExchangeRate(String from, String to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            return -1.0; // 货币不存在
        }

        // BFS 搜索最优路径
        Queue<Pair<String, Double>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new Pair<>(from, 1.0));
        visited.add(from);

        while (!queue.isEmpty()) {
            Pair<String, Double> curr = queue.poll();
            String currency = curr.getKey();
            double rate = curr.getValue();

            if (currency.equals(to)) {
                return rate; // 找到了目标货币，返回计算的汇率
            }

            for (Pair<String, Double> neighbor : graph.get(currency)) {
                if (!visited.contains(neighbor.getKey())) {
                    visited.add(neighbor.getKey());
                    queue.offer(new Pair<>(neighbor.getKey(), rate * neighbor.getValue()));
                }
            }
        }

        return -1.0; // 无法转换
    }

    // 测试代码
    public static void main(String[] args) {
        CurrencyExchange exchange = new CurrencyExchange();

        // 构建汇率表
        exchange.addExchangeRate("A", "B", 2.0);
        exchange.addExchangeRate("B", "C", 3.0);
        exchange.addExchangeRate("C", "D", 4.0);

        // 测试转换
        System.out.println("A -> D: " + exchange.getExchangeRate("A", "D")); // 预期输出: 2 * 3 * 4 = 24.0
        System.out.println("A -> K: " + exchange.getExchangeRate("A", "K")); // 预期输出: -1.0（无法转换）
        System.out.println("A -> C: " + exchange.getExchangeRate("A", "C"));
        System.out.println("D -> B: " + exchange.getExchangeRate("D", "B"));
    }
}

// 辅助 Pair 类
//class Pair<T, U> {
//    public final T getKey();
//    public final U getValue();
//
//    public Pair(T getKey(), U getValue()) {
//        this.getKey() = getKey();
//        this.getValue() = getValue();
//    }
//}

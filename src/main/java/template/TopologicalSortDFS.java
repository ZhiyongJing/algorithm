package template;

import java.util.*;


public class TopologicalSortDFS {
    private final Map<String, List<String>> graph = new HashMap<>();
    private final Set<String> visited = new HashSet<>();
    private final Set<String> recStack = new HashSet<>(); // 用于检测环
    private final Stack<String> stack = new Stack<>(); // 存储拓扑排序结果

    // 添加边
    public void addEdge(String fromNode, String toNode) {
        graph.computeIfAbsent(fromNode, k -> new ArrayList<>()).add(toNode);
    }

    // 拓扑排序方法
    public List<String> topologicalSort() {
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (topologicalSortUtil(node)) {
                    throw new IllegalStateException("图中存在环，无法进行拓扑排序");
                }
            }
        }
        List<String> sortedOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            sortedOrder.add(stack.pop());
        }
        return sortedOrder;
    }

    // DFS 辅助方法，进行拓扑排序和环检测
    //1. 将当前节点标记为访问中（加入递归栈 recStack）。
    //2. 递归访问每个邻居节点：
    //	2.1如果邻居未访问，则递归访问该邻居。
    //	2.2如果邻居已经在递归栈中，说明图中存在环，返回 true。
    //3. 访问完所有邻居后，当前节点从递归栈中移除，并推入结果栈中。
    //4. 返回 false 表示没有环。



    private boolean topologicalSortUtil(String node) {
        visited.add(node);
        recStack.add(node); // 将节点标记为访问中

        for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                if (topologicalSortUtil(neighbor)) {
                    return true; // 如果发现环，返回 true
                }
            } else if (recStack.contains(neighbor)) {
                return true; // 如果邻居在递归栈中，说明存在环
            }
        }

        recStack.remove(node); // 当前节点的所有邻居访问完毕，移出递归栈
        stack.push(node); // 将当前节点推入栈中
        return false; // 无环
    }

    public static void main(String[] args) {
        TopologicalSortDFS dag = new TopologicalSortDFS();
        dag.addEdge("1", "2");
        dag.addEdge("1", "4");
        dag.addEdge("2", "3");
        dag.addEdge("4", "3");
        dag.addEdge("4", "5");
        dag.addEdge("3", "5");
//        dag.addEdge("4", "1"); // Uncomment this line to create a cycle

        try {
            List<String> sortedOrder = dag.topologicalSort();
            System.out.println("拓扑排序结果: " + sortedOrder);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
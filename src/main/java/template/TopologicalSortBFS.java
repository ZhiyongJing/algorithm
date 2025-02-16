package template;

import java.util.*;

public class TopologicalSortBFS {


    // 拓扑排序方法
    private static List<String> topologicalSort(List<List<String>> edges) {
        //1. 存储节点的入度和邻接表, 并初始化图和入度
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> edge : edges) {
            graph.putIfAbsent(edge.get(0), new ArrayList<>());
            graph.putIfAbsent(edge.get(1), new ArrayList<>());
            graph.get(edge.get(0)).add(edge.get(1));

            inDegree.put(edge.get(1), inDegree.getOrDefault(edge.get(1), 0) + 1);
            inDegree.putIfAbsent(edge.get(0), 0);
        }
        System.out.println("indDegree: " + inDegree);//indDegree: {1=0, 2=1, 3=2, 4=1, 5=2}
        System.out.println("graph" + graph);//graph{1=[2, 4], 2=[3], 3=[5], 4=[3, 5], 5=[]}

        //2. 入度为 0 的节点加入队列. 如果有两个入度为0的节点，则这个图是分裂的
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        //3. 拓扑排序结果
        List<String> sortedOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            String node = queue.poll();
            sortedOrder.add(node);

            // 遍历当前节点的后继节点
            if (graph.containsKey(node)) {
                for (String neighbor : graph.get(node)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // 检查是否存在循环依赖
        if (sortedOrder.size() != inDegree.size()) {
            throw new RuntimeException("图中存在循环依赖，不是一个DAG！");
        }

        return sortedOrder;
    }

    public static void main(String[] args) {
        // 定义边列表
        List<List<String>> edges = new ArrayList<>();
        edges.add(Arrays.asList("1", "2"));
        edges.add(Arrays.asList("1", "4"));
        edges.add(Arrays.asList("2", "3"));
        edges.add(Arrays.asList("4", "3"));
        edges.add(Arrays.asList("4", "5"));
        edges.add(Arrays.asList("3", "5"));

        // 打印拓扑排序结果
        List<String> sortedNodes = topologicalSort(edges);
        System.out.println("拓扑排序的节点顺序: " + sortedNodes);
    }
}
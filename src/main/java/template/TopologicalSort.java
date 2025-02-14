package template;
import java.util.*;

//public class TopologicalSort {


    class Edge {
        String start;
        String end;

        public Edge(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }

    public class TopologicalSort {

        // 拓扑排序方法
        private static List<String> topologicalSort(List<Edge> edges) {
            // 存储节点的入度和邻接表
            Map<String, Integer> inDegree = new HashMap<>();
            Map<String, List<String>> graph = new HashMap<>();

            // 初始化图和入度
            for (Edge edge : edges) {
                graph.putIfAbsent(edge.start, new ArrayList<>());
                graph.putIfAbsent(edge.end, new ArrayList<>());
                graph.get(edge.start).add(edge.end);

                inDegree.put(edge.end, inDegree.getOrDefault(edge.end, 0) + 1);
                inDegree.putIfAbsent(edge.start, 0);
            }

            // 入度为 0 的节点加入队列
            Queue<String> queue = new LinkedList<>();
            for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
                if (entry.getValue() == 0) {
                    queue.offer(entry.getKey());
                }
            }

            // 拓扑排序结果
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
            List<Edge> edges = new ArrayList<>();
            edges.add(new Edge("1", "4"));
            edges.add(new Edge("1", "2"));
            edges.add(new Edge("2", "3"));
            edges.add(new Edge("4", "3"));
            edges.add(new Edge("4", "5"));
            edges.add(new Edge("3", "5"));

            // 打印拓扑排序结果
            List<String> sortedNodes = topologicalSort(edges);
            System.out.println("拓扑排序的节点顺序: " + sortedNodes);
        }
    }

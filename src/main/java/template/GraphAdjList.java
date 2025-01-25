package template;

import java.util.*;

public class GraphAdjList {


        /* 基于邻接表实现的无向图类 */

        // 邻接表，key：顶点，value：该顶点的所有邻接顶点
        Map<Integer, List<Integer>> adjList;

        /* 构造方法 */
        public GraphAdjList(Integer[][] edges) {
            this.adjList = new HashMap<>();
            // 添加所有顶点和边
            for (Integer[] edge : edges) {
                addPoint(edge[0]);
                addPoint(edge[1]);
                addEdge(edge[0], edge[1]);
            }
        }

        /* 获取顶点数量 */
        public int size() {
            return adjList.size();
        }

        /* 添加边 */
        public void addEdge(Integer point1, Integer point2) {
            if (!adjList.containsKey(point1) || !adjList.containsKey(point2) || point1 == point2)
                throw new IllegalArgumentException();
            // 添加边 point1 - point2
            adjList.get(point1).add(point2);
            adjList.get(point2).add(point1);
        }

        /* 删除边 */
        public void removeEdge(Integer point1, Integer point2) {
            if (!adjList.containsKey(point1) || !adjList.containsKey(point2) || point1 == point2)
                throw new IllegalArgumentException();
            // 删除边 point1 - point2
            adjList.get(point1).remove(point2);
            adjList.get(point2).remove(point1);
        }

        /* 添加顶点 */
        public void addPoint(Integer point) {
            if (adjList.containsKey(point))
                return;
            // 在邻接表中添加一个新链表
            adjList.put(point, new ArrayList<>());
        }

        /* 删除顶点 */
        public void removePoint(Integer point) {
            if (!adjList.containsKey(point))
                throw new IllegalArgumentException();
            // 在邻接表中删除顶点 point 对应的链表
            adjList.remove(point);
            // 遍历其他顶点的链表，删除所有包含 point 的边
            for (List<Integer> list : adjList.values()) {
                list.remove(point);
            }
        }

        /* 打印邻接表 */
        public void print() {
            System.out.println("邻接表 =");
            for (Map.Entry<Integer, List<Integer>> pair : adjList.entrySet()) {
                List<Integer> tmp = new ArrayList<>();
                for (Integer vertex : pair.getValue())
                    tmp.add(vertex);
                System.out.println(pair.getKey() + ": " + tmp + ",");
            }
        }

    /* 广度优先遍历 */
    List<Integer> graphBFS(GraphAdjList graph, Integer startVet) {
        // 顶点遍历序列
        List<Integer> res = new ArrayList<>();
        // 哈希集合，用于记录已被访问过的顶点
        Set<Integer> visited = new HashSet<>();
        visited.add(startVet);
        // 队列用于实现 BFS
        Queue<Integer> que = new LinkedList<>();
        que.offer(startVet);
        // 以顶点 vet 为起点，循环直至访问完所有顶点
        int count = 0;
        while (!que.isEmpty()) {
            count ++;
            Integer vet = que.poll(); // 队首顶点出队
            res.add(vet);            // 记录访问顶点
            // 遍历该顶点的所有邻接顶点
            for (Integer adjVet : graph.adjList.get(vet)) {
                if (visited.contains(adjVet))
                    continue;        // 跳过已被访问的顶点
                que.offer(adjVet);   // 只入队未访问的顶点
                visited.add(adjVet); // 标记该顶点已被访问
            }
        }
        System.out.println("total while iteration: " + count);
        // 返回顶点遍历序列
        return res;
    }

    /* 广度优先遍历 */
    List<List<Integer>> graphBFSByLevel(GraphAdjList graph, Integer startVet) {
        // 顶点遍历序列
        List<List<Integer>> res = new ArrayList<>();
        // 哈希集合，用于记录已被访问过的顶点
        Set<Integer> visited = new HashSet<>();
        visited.add(startVet);
        // 队列用于实现 BFS
        Queue<Integer> que = new LinkedList<>();
        que.offer(startVet);
        // 以顶点 vet 为起点，循环直至访问完所有顶点
        while (!que.isEmpty()) {
            int currentSize = que.size();
            List<Integer> currentLevel = new ArrayList<>();
            // 遍历该顶点的所有邻接顶点
            for(int i = 0; i < currentSize; i++){
                int point = que.poll();
                currentLevel.add(point);
                for(Integer adjVet : graph.adjList.get(point)){
                    if(visited.contains(adjVet)) continue;
                    que.offer(adjVet);
                    visited.add(adjVet);
                }

            }
            res.add(currentLevel);
        }
//        System.out.println("by levle total while iteration: " + count);

        // 返回顶点遍历序列
        return res;
    }

        public static void main(String... args){
            Integer[][] ini = {
                    {0, 1}, {0, 3}, // Connections from node 0
                    {1, 2}, {1, 4}, // Connections from node 1
                    {2, 5},         // Connections from node 2
                    {3, 4}, {3, 6}, // Connections from node 3
                    {4, 5}, {4, 7}, // Connections from node 4
                    {5, 8},         // Connections from node 5
                    {6, 7},         // Connections from node 6
                    {7, 8}          // Connections from node 7
            };
            GraphAdjList graph = new GraphAdjList(ini);
            graph.print();
            System.out.println(graph.graphBFS(graph, 0));
            System.out.println("bfs by level: " + graph.graphBFSByLevel(graph, 0));
        }


}





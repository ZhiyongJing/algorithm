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

    // 判断是否存在从 source 到 destination 的路径
    public boolean existingPathBFS(int n, int[][] edges, int source, int destination) {
        // 如果 edges 为空或者没有边，直接返回 true（即视为存在路径）
        if (edges == null || edges.length == 0) return true;

        // 用一个 Map 来表示邻接表，key 是节点，value 是与 key 直接相连的节点列表
        Map<Integer, List<Integer>> adj = new HashMap<>();

        // 遍历 edges 数组，构建邻接表
        for (int[] edge : edges) {
            // 对于每条边的第一个节点，添加它的相邻节点
            adj.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(edge[1]);
            // 对于每条边的第二个节点，添加它的相邻节点
            adj.computeIfAbsent(edge[1], val -> new ArrayList<>()).add(edge[0]);
        }

        // 如果 source 节点不在邻接表中，说明无法到达 destination，直接返回 false
        if (!adj.containsKey(source)) return false;

        // 使用队列进行广度优先搜索（BFS）
        Queue<Integer> queue = new LinkedList<>();
        // 使用 Set 来记录访问过的节点，避免重复访问
        Set<Integer> visited = new HashSet<>();

        // 将起始节点 source 入队，并标记为已访问
        queue.offer(source);
        visited.add(source);

        // 开始 BFS
        while (queue.size() != 0) {
            int currentSize = queue.size(); // 当前层的节点数量
            for (int i = 0; i < currentSize; i++) {
                int currentNode = queue.poll(); // 取出当前节点
                if (currentNode == destination) return true;// 如果当前节点是目标节点，返回 true 表示存在路径
                visited.add(currentNode);// 标记当前节点为已访问
                for (int child : adj.get(currentNode)) { // 遍历当前节点的所有相邻节点
                    if (visited.contains(child)) continue;  // 如果相邻节点已经访问过，跳过
                    // 将未访问的相邻节点加入队列，并标记为已访问
                    queue.offer(child);
                    visited.add(child);
                }
            }
        }
        // 如果 BFS 结束还未找到 destination，返回 false
        return false;
    }

    // 判断是否存在从 source 到 destination 的路径
    public boolean existingPathDFS(int n, int[][] edges, int source, int destination) {
        // 如果 edges 为空或者没有边，直接返回 true（即视为存在路径）
        if (edges == null || edges.length == 0) return true;

        // 用一个 Map 来表示邻接表，key 是节点，value 是与 key 直接相连的节点列表
        Map<Integer, List<Integer>> adj = new HashMap<>();

        // 遍历 edges 数组，构建邻接表
        for (int[] edge : edges) {
            // 对于每条边的第一个节点，添加它的相邻节点
            adj.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(edge[1]);
            // 对于每条边的第二个节点，添加它的相邻节点
            adj.computeIfAbsent(edge[1], val -> new ArrayList<>()).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        return dfs(adj, visited, source, destination);

    }
    private boolean dfs(Map<Integer, List<Integer>> adj, Set<Integer> visited, int currentNode, int desitination){
        if(currentNode == desitination) return true;
        if(!visited.contains(currentNode)){
            for(int child: adj.get(currentNode)){
                visited.add(child);
                return dfs(adj, visited,child, desitination);
            }
        }
        return false;
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





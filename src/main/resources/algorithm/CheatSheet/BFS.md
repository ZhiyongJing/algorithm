宽度优先搜索 BFS

## 使用条件

- 拓扑排序（100%）
- 出现连通块的关键词（100%）
- 分层遍历（100%）
- 简单图最短路径（100%）
- 给定一个变换规则，从初始状态变到终止状态最少几步（100%）

## 复杂度

- 时间复杂度：

  O(n+m)*O*(*n*+*m*)

  - `n` 是点数，`m` 是边数

- 空间复杂度：O(n)*O*(*n*)

## 代码模板

java

python

```java
1ReturnType bfs(Node startNode) {
2    // BFS 必须要用队列 queue，别用栈 stack！
3    Queue<Node> queue = new ArrayDeque<>();
4    // hashmap 有两个作用，一个是记录一个点是否被丢进过队列了，避免重复访问
5    // 另外一个是记录 startNode 到其他所有节点的最短距离
6    // 如果只求连通性的话，可以换成 HashSet 就行
7    // node 做 key 的时候比较的是内存地址
8    Map<Node, Integer> distance = new HashMap<>();
9    // 把起点放进队列和哈希表里，如果有多个起点，都放进去
10    queue.offer(startNode);
11    distance.put(startNode, 0); // or 1 if necessary
12    // while 队列不空，不停的从队列里拿出一个点，拓展邻居节点放到队列中
13    while (!queue.isEmpty()) {
14        Node node = queue.poll();
15        // 如果有明确的终点可以在这里加终点的判断
16        if (node 是终点) {
17            break or return something;
18        }
19        for (Node neighbor : node.getNeighbors()) {
20            if (distance.containsKey(neighbor)) {
21                continue;
22            }
23            queue.offer(neighbor);
24            distance.put(neighbor, distance.get(node) + 1);
25        }
26    }
27    // 如果需要返回所有点离起点的距离，就 return hashmap
28    return distance;
29    // 如果需要返回所有连通的节点, 就 return HashMap 里的所有点
30    return distance.keySet();
31    // 如果需要返回离终点的最短距离
32    return distance.get(endNode);
33}
```

**拓扑排序**

Java

Python

```text
1List<Node> topologicalSort(List<Node> nodes) {
2    // 统计所有点的入度信息，放入 hashmap 里
3    Map<Node, Integer> indegrees = getIndegrees(nodes);
4    // 将所有入度为 0 的点放到队列中
5    Queue<Node> queue = new ArrayDeque<>();
6    for (Node node : nodes) {
7        if (indegrees.get(node) == 0) {
8            queue.offer(node);
9        }
10    }
11    List<Node> topoOrder = new ArrayList<>();
12    while (!queue.isEmpty()) {
13        Node node = queue.poll();
14        topoOrder.add(node);
15        for (Node neighbor : node.getNeighbors()) {
16            // 入度减一
17            indegrees.put(neighbor, indegrees.get(neighbor) - 1);
18            // 入度减到 0 说明不再依赖任何点，可以被放到队列（拓扑序）里了
19            if (indegrees.get(neighbor) == 0) {
20                queue.offer(neighbor);
21            }
22        }
23    }
24    // 如果 queue 是空的时候，图中还有点没有被挖出来，说明存在环
25    // 有环就没有拓扑序
26    if (topoOrder.size() != nodes.size()) {
27        return 没有拓扑序;
28    }
29    return topOrder;
30}
31
32Map<Node, Integer> getIndegrees(List<Node> nodes) {
33    Map<Node, Integer> counter = new HashMap<>();
34    for (Node node : nodes) {
35        counter.put(node, 0);
36    }
37    for (Node node : nodes) {
38        for (Node neighbor : node.getNeighbors()) {
39            counter.put(neighbor, counter.get(neighbor) + 1);
40        }
41    }
42    return counter;
43}
```
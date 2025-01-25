> - 图由顶点和边组成，可以表示为一组顶点和一组边构成的集合。
> - 相较于线性关系（链表）和分治关系（树），网络关系（图）具有更高的自由度，因而更为复杂。
> - 有向图的边具有方向性，连通图中的任意顶点均可达，有权图的每条边都包含权重变量。
> - 邻接矩阵利用矩阵来表示图，每一行（列）代表一个顶点，矩阵元素代表边，用 或 表示两个顶点之间有边或无边。邻接矩阵在增删查改操作上效率很高，但空间占用较多。
> - 邻接表使用多个链表来表示图，第 个链表对应顶点 ，其中存储了该顶点的所有邻接顶点。邻接表相对于邻接矩阵更加节省空间，但由于需要遍历链表来查找边，因此时间效率较低。
> - 当邻接表中的链表过长时，可以将其转换为红黑树或哈希表，从而提升查询效率。
> - 从算法思想的角度分析，邻接矩阵体现了“以空间换时间”，邻接表体现了“以时间换空间”。
> - 图可用于建模各类现实系统，如社交网络、地铁线路等。
> - 树是图的一种特例，树的遍历也是图的遍历的一种特例。
> - 图的广度优先遍历是一种由近及远、层层扩张的搜索方式，通常借助队列实现。
> - 图的深度优先遍历是一种优先走到底、无路可走时再回溯的搜索方式，常基于递归来实现。
>
> **Q**：路径的定义是顶点序列还是边序列？
>
> 维基百科上不同语言版本的定义不一致：英文版是“路径是一个边序列”，而中文版是“路径是一个顶点序列”。以下是英文版原文：In graph theory, a path in a graph is a finite or infinite sequence of edges which joins a sequence of vertices.
>
> 在本文中，路径被视为一个边序列，而不是一个顶点序列。这是因为两个顶点之间可能存在多条边连接，此时每条边都对应一条路径。
>
> **Q**：非连通图中是否会有无法遍历到的点？
>
> 在非连通图中，从某个顶点出发，至少有一个顶点无法到达。遍历非连通图需要设置多个起点，以遍历到图的所有连通分量。
>
> **Q**：在邻接表中，“与该顶点相连的所有顶点”的顶点顺序是否有要求？
>
> 可以是任意顺序。但在实际应用中，可能需要按照指定规则来排序，比如按照顶点添加的次序，或者按照顶点值大小的顺序等，这样有助于快速查找“带有某种极值”的顶点。

# 1. 图

图（graph）是一种非线性数据结构，由**顶点**（vertex）和**边**（edge）组成。我们可以将图 $G$ 抽象地表示为一组顶点 $V$ 和一组边 $E$ 的集合。以下示例展示了一个包含 5 个顶点和 7 条边的图。 

$$
\begin{aligned}
V & = \{ 1, 2, 3, 4, 5 \} \newline
E & = \{ (1,2), (1,3), (1,5), (2,3), (2,4), (2,5), (4,5) \} \newline
G & = \{ V, E \} \newline
\end{aligned}
$$

如果将顶点看作节点，将边看作连接各个节点的引用（指针），我们就可以将图看作一种从链表拓展而来的数据结构。如下图所示，**相较于线性关系（链表）和分治关系（树），网络关系（图）的自由度更高**，因而更为复杂。

![链表、树、图之间的关系](Graph.assets/linkedlist_tree_graph.png)

## 1.1 图的常见类型与术语

根据边是否具有方向，可分为**无向图**（undirected graph）和**有向图**（directed graph），如下图所示。 

- 在无向图中，边表示两顶点之间的“双向”连接关系，例如微信或 QQ 中的“好友关系”。
- 在有向图中，边具有方向性，即 $A \rightarrow B$ 和 $A \leftarrow B$ 两个方向的边是相互独立的，例如微博或抖音上的“关注”与“被关注”关系。

![有向图与无向图](Graph.assets/directed_graph.png)

根据所有顶点是否连通，可分为**连通图**（connected graph）和**非连通图**（disconnected graph），如下图所示。

- 对于连通图，从某个顶点出发，可以到达其余任意顶点。
- 对于非连通图，从某个顶点出发，至少有一个顶点无法到达。

![连通图与非连通图](Graph.assets/connected_graph.png)

我们还可以为边添加“权重”变量，从而得到如下图所示的**有权图**（weighted graph）。例如在《王者荣耀》等手游中，系统会根据共同游戏时间来计算玩家之间的“亲密度”，这种亲密度网络就可以用有权图来表示。

![有权图与无权图](Graph.assets/weighted_graph.png)

图数据结构包含以下常用术语。

- 邻接（adjacency）：当两顶点之间存在边相连时，称这两顶点“邻接”。在上图中，顶点 1 的邻接顶点为顶点 2、3、5。
- 路径（path）：从顶点 A 到顶点 B 经过的边构成的序列被称为从 A 到 B 的“路径”。在上图中，边序列 1-5-2-4 是顶点 1 到顶点 4 的一条路径。
- 度（degree）：一个顶点拥有的边数。对于有向图，入度（in-degree）表示有多少条边指向该顶点，出度（out-degree）表示有多少条边从该顶点指出。

## 1.2 图的表示

图的常用表示方式包括“邻接矩阵”和“邻接表”。以下使用无向图进行举例。

### 1.2.1 邻接矩阵

设图的顶点数量为 $n$ ，邻接矩阵（adjacency matrix）使用一个 $n \times n$ 大小的矩阵来表示图，每一行（列）代表一个顶点，矩阵元素代表边，用 $1$ 或 $0$ 表示两个顶点之间是否存在边。

如下图所示，设邻接矩阵为 $M$、顶点列表为 $V$ ，那么矩阵元素 $M[i, j] = 1$ 表示顶点 $V[i]$ 到顶点 $V[j]$ 之间存在边，反之 $M[i, j] = 0$ 表示两顶点之间无边。

![图的邻接矩阵表示](Graph.assets/adjacency_matrix.png)

邻接矩阵具有以下特性。

- 顶点不能与自身相连，因此邻接矩阵主对角线元素没有意义。
- 对于无向图，两个方向的边等价，此时邻接矩阵关于主对角线对称。
- 将邻接矩阵的元素从 $1$ 和 $0$ 替换为权重，则可表示有权图。

使用邻接矩阵表示图时，我们可以直接访问矩阵元素以获取边，因此增删查改操作的效率很高，时间复杂度均为 $O(1)$ 。然而，矩阵的空间复杂度为 $O(n^2)$ ，内存占用较多。

### 1.2.2 邻接表

邻接表（adjacency list）使用 $n$ 个链表来表示图，链表节点表示顶点。第 $i$ 个链表对应顶点 $i$ ，其中存储了该顶点的所有邻接顶点（与该顶点相连的顶点）。下图展示了一个使用邻接表存储的图的示例。

![图的邻接表表示](Graph.assets/adjacency_list.png)

邻接表仅存储实际存在的边，而边的总数通常远小于 $n^2$ ，因此它更加节省空间。然而，在邻接表中需要通过遍历链表来查找边，因此其时间效率不如邻接矩阵。

观察上图，**邻接表结构与哈希表中的“链式地址”非常相似，因此我们也可以采用类似的方法来优化效率**。比如当链表较长时，可以将链表转化为 AVL 树或红黑树，从而将时间效率从 $O(n)$ 优化至 $O(\log n)$ ；还可以把链表转换为哈希表，从而将时间复杂度降至 $O(1)$ 。

## 1.3 图的常见应用

如下表所示，许多现实系统可以用图来建模，相应的问题也可以约化为图计算问题。

<p align="center"> 表 <id> &nbsp; 现实生活中常见的图 </p>

|          | 顶点 | 边                   | 图计算问题   |
| -------- | ---- | -------------------- | ------------ |
| 社交网络 | 用户 | 好友关系             | 潜在好友推荐 |
| 地铁线路 | 站点 | 站点间的连通性       | 最短路线推荐 |
| 太阳系   | 星体 | 星体间的万有引力作用 | 行星轨道计算 |



# 2. 图的基础操作

图的基础操作可分为对“边”的操作和对“顶点”的操作。在“邻接矩阵”和“邻接表”两种表示方法下，实现方式有所不同。

## 2.1 基于邻接矩阵的实现

给定一个顶点数量为 $n$ 的无向图，则各种操作的实现方式如下图所示。

- **添加或删除边**：直接在邻接矩阵中修改指定的边即可，使用 $O(1)$ 时间。而由于是无向图，因此需要同时更新两个方向的边。
- **添加顶点**：在邻接矩阵的尾部添加一行一列，并全部填 $0$ 即可，使用 $O(n)$ 时间。
- **删除顶点**：在邻接矩阵中删除一行一列。当删除首行首列时达到最差情况，需要将 $(n-1)^2$ 个元素“向左上移动”，从而使用 $O(n^2)$ 时间。
- **初始化**：传入 $n$ 个顶点，初始化长度为 $n$ 的顶点列表 `vertices` ，使用 $O(n)$ 时间；初始化 $n \times n$ 大小的邻接矩阵 `adjMat` ，使用 $O(n^2)$ 时间。
      ![邻接矩阵的初始化、增删边、增删顶点](Graph.assets/adjacency_matrix_steps.gif)



以下是基于邻接矩阵表示图的实现代码：

```java
package interview.company.bloomberg;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjMat {
    List<Integer> points; // 顶点列表，元素代表“顶点值”，索引代表“顶点索引”
    List<List<Integer>> adjMat; // 邻接矩阵，行列索引对应“顶点索引”

    /* 构造方法 */
    public GraphAdjMat(int[] points, int[][] edges) {
        this.points = new ArrayList<>();
        this.adjMat = new ArrayList<>();
        // 添加顶点
        for (int val : points) {
            addPoint(val);
        }
        // 添加边
        // 请注意，edges 元素代表顶点索引，即对应 points 元素索引
        for (int[] e : edges) {
            addEdge(e[0], e[1]);
        }
    }

    /* 获取顶点数量 */
    public int size() {
        return points.size();
    }

    /* 添加顶点 */
    public void addPoint(int val) {
        int n = size();
        // 向顶点列表中添加新顶点的值
        points.add(val);
        // 在邻接矩阵中添加一行
        List<Integer> newRow = new ArrayList<>(n);
        for (int j = 0; j < n; j++) {
            newRow.add(0);
        }
        adjMat.add(newRow);
        // 在邻接矩阵中添加一列
        for (List<Integer> row : adjMat) {
            row.add(0);
        }
    }

    /* 删除顶点 */
    public void removePoint(int index) {
        if (index >= size())
            throw new IndexOutOfBoundsException();
        // 在顶点列表中移除索引 index 的顶点
        points.remove(index);
        // 在邻接矩阵中删除索引 index 的行
        adjMat.remove(index);
        // 在邻接矩阵中删除索引 index 的列
        for (List<Integer> row : adjMat) {
            row.remove(index);
        }
    }

    /* 添加边 */
    // 参数 i, j 对应 points 元素索引
    public void addEdge(int i, int j) {
        // 索引越界与相等处理
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j)
            throw new IndexOutOfBoundsException();
        // 在无向图中，邻接矩阵关于主对角线对称，即满足 (i, j) == (j, i)
        adjMat.get(i).set(j, 1);
        adjMat.get(j).set(i, 1);
    }

    /* 删除边 */
    // 参数 i, j 对应 points 元素索引
    public void removeEdge(int i, int j) {
        // 索引越界与相等处理
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j)
            throw new IndexOutOfBoundsException();
        adjMat.get(i).set(j, 0);
        adjMat.get(j).set(i, 0);
    }

    /* 打印邻接矩阵 */
    public void print() {
        System.out.print("顶点列表 = ");
        System.out.println(points);
        System.out.println("邻接矩阵 =");
        this.print2DArrayMatrixFormat(adjMat);
    }

    public void print2DArrayMatrixFormat(List<List<Integer>> array) {
        for (List<Integer> row : array) {
            for (int col : row) {
                System.out.print(col + "\t"); // 使用制表符对齐
            }
            System.out.println(); // 换行
        }
    }

    public static void main(String[] args) {
        int[] points = {1, 3, 2, 5, 4};
        int[][] ini = {{0, 1}, {0, 3}, {1, 2}, {2, 3}, {2, 4}, {3, 4}};
        template.GraphAdjMat graph = new template.GraphAdjMat(points, ini);
        graph.print();

    }
}

```

## 2.2 基于邻接表的实现

设无向图的顶点总数为 $n$、边总数为 $m$ ，则可根据下图所示的方法实现各种操作。

- **添加边**：在顶点对应链表的末尾添加边即可，使用 $O(1)$ 时间。因为是无向图，所以需要同时添加两个方向的边。
- **删除边**：在顶点对应链表中查找并删除指定边，使用 $O(m)$ 时间。在无向图中，需要同时删除两个方向的边。
- **添加顶点**：在邻接表中添加一个链表，并将新增顶点作为链表头节点，使用 $O(1)$ 时间。
- **删除顶点**：需遍历整个邻接表，删除包含指定顶点的所有边，使用 $O(n + m)$ 时间。
- **初始化**：在邻接表中创建 $n$ 个顶点和 $2m$ 条边，使用 $O(n + m)$ 时间。
      ![邻接表的初始化、增删边、增删顶点](Graph.assets/adjacency_list_steps.gif)



以下是邻接表的代码实现。对比上图，实际代码有以下不同。

- 为了方便添加与删除顶点，以及简化代码，我们使用列表（动态数组）来代替链表。
- 使用哈希表来存储邻接表，`key` 为顶点实例，`value` 为该顶点的邻接顶点列表（链表）。

另外，我们在邻接表中使用 `Vertex` 类来表示顶点，这样做的原因是：如果与邻接矩阵一样，用列表索引来区分不同顶点，那么假设要删除索引为 $i$ 的顶点，则需遍历整个邻接表，将所有大于 $i$ 的索引全部减 $1$ ，效率很低。而如果每个顶点都是唯一的 `Vertex` 实例，删除某一顶点之后就无须改动其他顶点了。

```java
package interview.company.bloomberg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 基于邻接表实现的无向图类 */
public class GraphAdjList {
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

    public static void main(String... args) {
        Integer[][] ini = {{1, 5}, {1, 3}, {3, 2}, {2, 5}, {5, 4}, {2, 4}};
        template.GraphAdjList graph = new template.GraphAdjList(ini);
        graph.print();
        /**
         邻接表 =
         1: [5, 3],
         2: [3, 5, 4],
         3: [1, 2],
         4: [5, 2],
         5: [1, 2, 4],
         }
         **/
    }


```

## 2.3 效率对比

设图中共有 $n$ 个顶点和 $m$ 条边，下表对比了邻接矩阵和邻接表的时间效率和空间效率。

<p align="center"> 表 <id> &nbsp; 邻接矩阵与邻接表对比 </p>

|              | 邻接矩阵 | 邻接表（链表） | 邻接表（哈希表） |
| ------------ | -------- | -------------- | ---------------- |
| 判断是否邻接 | $O(1)$   | $O(m)$         | $O(1)$           |
| 添加边       | $O(1)$   | $O(1)$         | $O(1)$           |
| 删除边       | $O(1)$   | $O(m)$         | $O(1)$           |
| 添加顶点     | $O(n)$   | $O(1)$         | $O(1)$           |
| 删除顶点     | $O(n^2)$ | $O(n + m)$     | $O(n)$           |
| 内存空间占用 | $O(n^2)$ | $O(n + m)$     | $O(n + m)$       |

观察上表，似乎邻接表（哈希表）的时间效率与空间效率最优。但实际上，在邻接矩阵中操作边的效率更高，只需一次数组访问或赋值操作即可。综合来看，邻接矩阵体现了“以空间换时间”的原则，而邻接表体现了“以时间换空间”的原则。



# 3. 图的遍历

树代表的是“一对多”的关系，而图则具有更高的自由度，可以表示任意的“多对多”关系。因此，我们可以把树看作图的一种特例。显然，**树的遍历操作也是图的遍历操作的一种特例**。

图和树都需要应用搜索算法来实现遍历操作。图的遍历方式也可分为两种：广度优先遍历和深度优先遍历。

## 3.1 广度优先遍历

**广度优先遍历是一种由近及远的遍历方式，从某个节点出发，始终优先访问距离最近的顶点，并一层层向外扩张**。如下图所示，从左上角顶点出发，首先遍历该顶点的所有邻接顶点，然后遍历下一个顶点的所有邻接顶点，以此类推，直至所有顶点访问完毕。

![图的广度优先遍历](Graph.assets/graph_bfs.png)

### 3.1.1 算法实现

BFS 通常借助队列来实现，代码如下所示。队列具有“先入先出”的性质，这与 BFS 的“由近及远”的思想异曲同工。

1. 将遍历起始顶点 `startVet` 加入队列，并开启循环。
2. 在循环的每轮迭代中，弹出队首顶点并记录访问，然后将该顶点的所有邻接顶点加入到队列尾部。
3. 循环步骤 `2.` ，直到所有顶点被访问完毕后结束。

为了防止重复遍历顶点，我们需要借助一个哈希集合 `visited` 来记录哪些节点已被访问。

!!! tip

    哈希集合可以看作一个只存储 `key` 而不存储 `value` 的哈希表，它可以在 $O(1)$ 时间复杂度下进行 `key` 的增删查改操作。根据 `key` 的唯一性，哈希集合通常用于数据去重等场景。

```java
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
        while (!que.isEmpty()) {
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
        // 返回顶点遍历序列
        return res;
    }
```

代码相对抽象，建议对照下图来加深理解。
    ![图的广度优先遍历步骤](Graph.assets/graph_bfs_steps.gif)

!!! question "广度优先遍历的序列是否唯一？"

    不唯一。广度优先遍历只要求按“由近及远”的顺序遍历，**而多个相同距离的顶点的遍历顺序允许被任意打乱**。以上图为例，顶点 $1$、$3$ 的访问顺序可以交换，顶点 $2$、$4$、$6$ 的访问顺序也可以任意交换。

### 3.1.2 复杂度分析

**时间复杂度**：所有顶点都会入队并出队一次，使用 $O(|V|)$ 时间；在遍历邻接顶点的过程中，由于是无向图，因此所有边都会被访问 $2$ 次，使用 $O(2|E|)$ 时间；总体使用 $O(|V| + |E|)$ 时间。

**空间复杂度**：列表 `res` ，哈希集合 `visited` ，队列 `que` 中的顶点数量最多为 $|V|$ ，使用 $O(|V|)$ 空间。

## 3.2 深度优先遍历

**深度优先遍历是一种优先走到底、无路可走再回头的遍历方式**。如下图所示，从左上角顶点出发，访问当前顶点的某个邻接顶点，直到走到尽头时返回，再继续走到尽头并返回，以此类推，直至所有顶点遍历完成。

![图的深度优先遍历](Graph.assets/graph_dfs.png)

### 3.2.1 算法实现

这种“走到尽头再返回”的算法范式通常基于递归来实现。与广度优先遍历类似，在深度优先遍历中，我们也需要借助一个哈希集合 `visited` 来记录已被访问的顶点，以避免重复访问顶点。

```java
/* 深度优先遍历辅助函数 */
void dfs(GraphAdjList graph, Set<Vertex> visited, List<Vertex> res, Vertex vet) {
    res.add(vet);     // 记录访问顶点
    visited.add(vet); // 标记该顶点已被访问
    // 遍历该顶点的所有邻接顶点
    for (Vertex adjVet : graph.adjList.get(vet)) {
        if (visited.contains(adjVet))
            continue; // 跳过已被访问的顶点
        // 递归访问邻接顶点
        dfs(graph, visited, res, adjVet);
    }
}

/* 深度优先遍历 */
// 使用邻接表来表示图，以便获取指定顶点的所有邻接顶点
List<Vertex> graphDFS(GraphAdjList graph, Vertex startVet) {
    // 顶点遍历序列
    List<Vertex> res = new ArrayList<>();
    // 哈希集合，用于记录已被访问过的顶点
    Set<Vertex> visited = new HashSet<>();
    dfs(graph, visited, res, startVet);
    return res;
}
```

深度优先遍历的算法流程如下图所示。

- **直虚线代表向下递推**，表示开启了一个新的递归方法来访问新顶点。
- **曲虚线代表向上回溯**，表示此递归方法已经返回，回溯到了开启此方法的位置。

为了加深理解，建议将下图与代码结合起来，在脑中模拟（或者用笔画下来）整个 DFS 过程，包括每个递归方法何时开启、何时返回。
    ![图的深度优先遍历步骤](Graph.assets/graph_dfs_steps.gif)

!!! question "深度优先遍历的序列是否唯一？"

    与广度优先遍历类似，深度优先遍历序列的顺序也不是唯一的。给定某顶点，先往哪个方向探索都可以，即邻接顶点的顺序可以任意打乱，都是深度优先遍历。
    
    以树的遍历为例，“根 $\rightarrow$ 左 $\rightarrow$ 右”“左 $\rightarrow$ 根 $\rightarrow$ 右”“左 $\rightarrow$ 右 $\rightarrow$ 根”分别对应前序、中序、后序遍历，它们展示了三种遍历优先级，然而这三者都属于深度优先遍历。

### 3.2.2 复杂度分析

**时间复杂度**：所有顶点都会被访问 $1$ 次，使用 $O(|V|)$ 时间；所有边都会被访问 $2$ 次，使用 $O(2|E|)$ 时间；总体使用 $O(|V| + |E|)$ 时间。

**空间复杂度**：列表 `res` ，哈希集合 `visited` 顶点数量最多为 $|V|$ ，递归深度最大为 $|V|$ ，因此使用 $O(|V|)$ 空间。



## 3.3 权重图(weighted graph)遍历

Dijkstra（迪杰斯特拉）算法解决的问题是：

> 在一个有向图中，求图中一个节点到其他所有节点的最短距离

### 3.3.1 算法实现

通过Dijkstra计算图G中的最短路径时，需要指定起点s(即从顶点s开始计算)。 

此外，引进两个集合S和U。S的作用是记录已求出最短路径的顶点(以及相应的最短路径长度)，而U则是记录还未求出最短路径的顶点(以及该顶点到起点s的距离)。 

初始时，S中只有起点s；U中是除s之外的顶点，并且U中顶点的路径是"起点s到该顶点的路径"。然后，从U中找出路径最短的顶点，并将其加入到S中；接着，更新U中的顶点和顶点对应的路径。 然后，再从U中找出路径最短的顶点，并将其加入到S中；接着，更新U中的顶点和顶点对应的路径。 ... 重复该操作，直到遍历完所有顶点。

![img](Graph.assets/02.jpg)

```java
package interview.company.bloomberg;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;
/*
 * 题目描述：
 * Dijkstra 算法用于计算 **单源最短路径**，即从 **起点（source）** 到 **图中所有其他顶点的最短路径**。
 * 该算法适用于 **无负权边** 的图，并可使用 **邻接矩阵** 或 **邻接表 + 优先队列（最小堆）** 实现。
 *
 * 输入：
 * - `graph`：邻接矩阵或邻接表，表示图的所有边及其权重。
 * - `source`：起始顶点索引，从该顶点出发计算最短路径。
 *
 * 输出：
 * - `dist[]`：一个数组，其中 `dist[i]` 表示从 `source` 到 `i` 的最短路径长度。
 * - 过程日志：每一步的路径更新情况。
 *
 * 示例：
 * 输入：
 *   graph = [
 *      {0, 12, 0, 0, 0, 14, 16},
 *      {12, 0, 10, 0, 0, 0, 7},
 *      {0, 10, 0, 3, 5, 0, 6},
 *      {0, 0, 3, 0, 4, 0, 0},
 *      {0, 0, 5, 4, 0, 8, 2},
 *      {14, 0, 0, 0, 8, 0, 9},
 *      {16, 7, 6, 0, 2, 9, 0}
 *   ]
 *   source = 3
 * 输出：
 *   dist = [22, 13, 3, 0, 4, 12, 6]
 *   并打印路径更新日志：
 *   current step 0: a: INF ,b: INF ,c: 3 ,d: 0 ,e: 4 ,f: INF ,g: INF ,
 *   current step 1: a: INF ,b: 13 ,c: 3 ,d: 0 ,e: 4 ,f: INF ,g: 9 ,
 *   ...
 */

/*
 * 解题思路：
 * Dijkstra 算法是一种 **贪心算法**，用于计算 **单源最短路径**。
 *
 * **方法 1：使用邻接矩阵（O(V²)）**
 * 1️⃣ **初始化**
 * - 创建 `dist[]` 数组，初始值为 `Integer.MAX_VALUE`，表示所有顶点与 `source` 不可达。
 * - `dist[source] = 0`，起点到自身的距离为 `0`。
 * - `visited[]` 数组，记录哪些顶点已经计算了最短路径，初始值均为 `false`。
 *
 * 2️⃣ **迭代 V-1 次，每次选择当前最短的未访问顶点**
 * - 通过 `minDistance()` 找到 `dist[]` 中 **未访问** 且 **最短路径最小** 的顶点 `u`。
 * - 标记 `u` 为已访问。
 * - 遍历 `u` 的所有邻接顶点 `v`：
 *   - 如果 `v` **未访问** 且 `u -> v` 存在边：
 *   - 计算 `source -> v` 的新路径 `dist[v] = min(dist[v], dist[u] + graph[u][v])`。
 *
 * 3️⃣ **终止**
 * - 经过 `V-1` 次迭代后，所有顶点的最短路径已确定，`dist[]` 结果即为最终解。
 * - 输出 `dist[]` 以及每一步的 `current step` 状态。
 *
 * 示例计算：
 * 以 `source = 3 (d)` 作为起点，初始 `dist[]`：
 * ```
 *  d: 0, a: INF, b: INF, c: 3, e: 4, f: INF, g: INF
 * ```
 * 第一步：
 * - 选择 `c (3)`，更新 `c` 的邻居：
 * ```
 *  d: 0, a: INF, b: 13, c: 3, e: 4, f: INF, g: 9
 * ```
 * 第二步：
 * - 选择 `e (4)`，更新 `e` 的邻居：
 * ```
 *  d: 0, a: INF, b: 13, c: 3, e: 4, f: 12, g: 6
 * ```
 * ...
 * 直到所有顶点的最短路径更新完毕，最终 `dist[]`：
 * ```
 *  d: 0, a: 22, b: 13, c: 3, e: 4, f: 12, g: 6
 * ```
 *
 * **方法 2：使用优先队列优化（O((V+E) log V)）**
 * 1️⃣ **初始化**
 * - 使用 `PriorityQueue`（最小堆）维护未访问顶点的最短路径。
 * - `dist[]` 记录 `source` 到各顶点的最短距离，初始值为 `Integer.MAX_VALUE`。
 * - `minHeap` 存储 `{顶点, 最短路径}`，初始时 `{source, 0}` 入队。
 *
 * 2️⃣ **主循环**
 * - 每次取出 `minHeap` 中 `dist` 最小的顶点 `u`，然后更新 `u` 的所有邻接顶点 `v`：
 *   - 计算 `newDist = dist[u] + weight[u][v]`
 *   - 若 `newDist < dist[v]`，则更新 `dist[v]` 并将 `{v, newDist}` 放入 `minHeap`。
 *
 * 3️⃣ **终止**
 * - 直到 `minHeap` 为空时，所有最短路径已确定。
 * - `dist[]` 记录最终结果。
 *
 * 示例：
 * - `minHeap` 维护 `{3, 0} -> {c, 3} -> {e, 4} -> {g, 6} -> {b, 13} -> {f, 12} -> {a, 22}`
 *
 */

/*
 * 时间和空间复杂度分析：
 *
 * **方法 1（邻接矩阵 + 线性查找 `minDistance()`）**
 * - **时间复杂度：** `O(V^2)`
 *   - 查找 `minDistance()` 需要 `O(V)` 遍历 `dist[]`，共执行 `V` 次。
 *   - 更新所有邻接顶点 `O(V)`，总共 `V` 次。
 *   - **总复杂度：O(V^2)**
 * - **空间复杂度：** `O(V^2)`（邻接矩阵存储所有边）。
 *
 * **方法 2（邻接表 + 优先队列 `PriorityQueue`）**
 * - **时间复杂度：** `O((V+E) log V)`
 *   - `PriorityQueue` 插入和删除操作 `O(log V)`。
 *   - `V` 个顶点被 `log V` 访问，每个 `E` 条边更新 `log V` 次。
 *   - **总复杂度：O((V+E) log V)**，适用于 **稀疏图**。
 * - **空间复杂度：** `O(V + E)`
 *   - 需要存储 `V` 个顶点的 `dist[]`，以及 `E` 条边的邻接表。
 *
 * **不同实现方式的时间复杂度对比**
 * |  实现方式   | 时间复杂度 | 适用场景 |
 * |------------|------------|----------|
 * | **邻接矩阵 + 线性查找 `minDistance()`** | `O(V^2)` | 适用于小规模图 |
 * | **邻接表 + 优先队列 `PriorityQueue`** | `O((V+E) log V)` | 适用于大规模稀疏图 |
 *
 * **算法适用场景**
 * - **适用于** 稠密图（`E ≈ V^2`）时 `O(V^2)` 的实现较快，适用于较小的 `V`。
 * - **适用于** 稀疏图（`E ≪ V^2`）时，建议使用 **优先队列优化** 以提升性能。
 */


public class Dijkstra {
    /**
     * 选择当前未访问的顶点中，最短路径值最小的顶点
     * @param dist 存储从源点到各顶点的最短路径长度
     * @param visited 记录哪些顶点已经被访问
     * @return 具有最小距离的未访问顶点索引
     */
    private static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE; // 记录当前最短距离，初始为无穷大
        int minIndex = -1; // 记录最短路径的顶点索引

        // 遍历所有顶点，找到未访问且距离最小的顶点
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i]; // 更新最小距离
                minIndex = i; // 记录该顶点索引
            }
        }
        return minIndex;
    }

    /**
     * Solution1: 使用邻接矩阵实现 Dijkstra 算法
     * 计算从源点到所有顶点的最短路径
     * @param graph 邻接矩阵表示的图，graph[i][j] 表示 i 到 j 的边权重
     * @param source 源点索引
     */
    private static void dijkstra(int[][] graph, int source) {
        int numVertices = graph.length; // 顶点个数
        int[] dist = new int[numVertices]; // 存储从源点到各顶点的最短路径值
        boolean[] visited = new boolean[numVertices]; // 记录哪些顶点已经被访问

        Arrays.fill(dist, Integer.MAX_VALUE); // 初始化所有顶点的最短路径为无穷大
        dist[source] = 0; // 源点到自身的距离为 0

        // 计算所有顶点的最短路径
        for (int count = 0; count < numVertices - 1; count++) {
            int u = minDistance(dist, visited); // 选择当前未访问顶点中最短路径值最小的顶点
            visited[u] = true; // 标记该顶点已访问

            // 更新所有邻接顶点的最短路径
            for (int v = 0; v < numVertices; v++) {
                // 只有满足以下条件才进行更新：
                // 1. 该顶点未访问
                // 2. u 到 v 之间存在边（即 graph[u][v] != 0）
                // 3. u 到源点的路径值不是无穷大
                // 4. 通过 u 到达 v 使路径变短
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v]; // 更新最短路径
                }
            }

            // 输出当前步骤的最短路径信息
            System.out.print("current step " + count + ": ");
            for (int i = 0; i < numVertices; i++) {
                System.out.print((char) (i + 'a') + ": " + dist[i] + " ,");
            }
            System.out.println();
        }
    }

    /**
     * Solution2: 使用最小堆（优先队列）优化 Dijkstra 算法
     * 计算从源点到所有顶点的最短路径
     * @param graph 邻接矩阵表示的图
     * @param start 源点索引
     * @return 返回从起点到所有顶点的最短路径数组
     */
    public static int[] dijkstra2(int[][] graph, int start) {
        int numVertices = graph.length; // 图的顶点数量
        int[] dist = new int[numVertices]; // 距离数组
        boolean[] visited = new boolean[numVertices]; // 访问标记数组
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>(
                (p1, p2) -> Integer.compare(p1.getValue(), p2.getValue())
        ); // 最小堆（存储顶点及其当前最短路径值）

        // 初始化距离数组
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0; // 起点到自身的距离为 0
        minHeap.add(new Pair<>(start, 0)); // 将起点加入优先队列

        while (!minHeap.isEmpty()) {
            Pair<Integer, Integer> currentNode = minHeap.poll(); // 取出当前最短路径的顶点
            int currentVertex = currentNode.getKey();

            // 如果当前节点已经访问过，则跳过
            if (visited[currentVertex]) {
                continue;
            }
            visited[currentVertex] = true; // 标记该节点为已访问

            // 遍历所有邻接顶点
            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (graph[currentVertex][neighbor] != 0 && !visited[neighbor]) {
                    int newDist = dist[currentVertex] + graph[currentVertex][neighbor];
                    // 如果新路径更短，则更新距离
                    if (newDist < dist[neighbor]) {
                        dist[neighbor] = newDist;
                        minHeap.add(new Pair<>(neighbor, newDist)); // 将更新的节点加入优先队列
                    }
                }
            }

            // 输出当前步骤的最短路径信息
            System.out.print("current step " + minHeap.size() + ": ");
            for (int i = 0; i < dist.length; i++) {
                System.out.print((char) (i + 'a') + ": " + dist[i] + " ,");
            }
            System.out.println();
        }

        return dist; // 返回从起点到各个节点的最短距离
    }

    public static void main(String[] args) {
        // 图的邻接矩阵表示，0 表示没有边
        int[][] graph = {
                {0, 12, 0, 0, 0, 14, 16},
                {12, 0, 10, 0, 0, 0, 7},
                {0, 10, 0, 3, 5, 0, 6},
                {0, 0, 3, 0, 4, 0, 0},
                {0, 0, 5, 4, 0, 8, 2},
                {14, 0, 0, 0, 8, 0, 9},
                {16, 7, 6, 0, 2, 9, 0}
        };

        int source = 3; // 以 'd' 作为源点（索引 3）

        // 测试 Solution 1：邻接矩阵实现 Dijkstra
        System.out.println("Dijkstra using adjacency matrix:");
        dijkstra(graph, source);

        // 测试 Solution 2：优先队列实现 Dijkstra
        System.out.println("\nDijkstra using priority queue:");
        dijkstra2(graph, source);
    }
}


```

### 3.3.2 算法实现

**时间复杂度**：

> - 方法 1（邻接矩阵 + 线性查找 `minDistance()`）
>
>   > 查找 `minDistance()` 需要 `O(V)` 遍历 `dist[]`，共执行 `V` 次。更新所有邻接顶点 `O(V)`，总共 `V` 次。
>   >
>   > **总复杂度：O(V^2)**
>
>         * 方法 2（邻接表 + 优先队列 `PriorityQueue`）
>         
>   > `PriorityQueue` 插入和删除操作 `O(log V)`。`V` 个顶点被 `log V` 访问，每个 `E` 条边更新 `log V` 次。
>   >
>   > **总复杂度：O((V+E) log V)**，适用于 **稀疏图**。

**空间复杂度**：

> - 方法 1（邻接矩阵 + 线性查找 `minDistance()`）
>
>   > `O(V^2)`（邻接矩阵存储所有边）。
>
>    * 方法 2（邻接表 + 优先队列 `PriorityQueue`）
>
>      > `O(V + E)`需要存储 `V` 个顶点的 `dist[]`，以及 `E` 条边的邻接表。


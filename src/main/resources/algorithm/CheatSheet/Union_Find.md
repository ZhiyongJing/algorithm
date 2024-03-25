并查集 Union Find

## 使用条件

- 需要查询图的连通状况的问题
- 需要支持快速合并两个集合的问题

## 复杂度

- 时间复杂度 union O(1)*O*(1), find O(1)*O*(1)
- 空间复杂度 O(n)*O*(*n*)

## 代码模板

java

python

```java
1class UnionFind {
2    private Map<Integer, Integer> father;
3    private Map<Integer, Integer> sizeOfSet;
4    private int numOfSet = 0;
5    public UnionFinf() {
6        // 初始化父指针，集合大小，集合数量
7        father = new HashMap<Integer,Integer>();
8        sizeOfSet = new HashMap<Integer, Integer>();
9        numOfSet = 0;
10    }
11
12    public void add(int x) {
13        // 点如果已经出现，操作无效
14        if (father.containsKey(x)) {
15            return;
16        }
17        // 初始化点的父亲为空对象 null
18        // 初始化该点所在集合大小为 1
19        // 集合数量增加 1
20        father.put(x, null);
21        sizeOfSet.put(x, 1);
22        numOfSet++;
23    }
24
25    public void merge(int x, int y) {
26        // 找到两个节点的根
27        int rootX = find(x);
28        int rootY = find(y);
29        // 如果根不是同一个则连接
30        if (rootX != rootY) {
31            // 将一个点的根变成新的根
32            // 集合数量减少 1
33            // 计算新的根所在集合大小
34            father.put(rootX, rootY);
35            numOfSet--;
36            sizeOfSet.put(rootY, sizeOfSet.get(rootX) + sizeOfSet.get()rootY);
37        }
38    }
39
40    public int find(int x) {
41        // 指针 root 指向被查找的点 x
42        // 不断找到 root 的父亲
43        // 直到 root 指向 x 的根节点
44        int root = x;
45        while (father.get(root) != null) {
46            root = father.get(root);
47        }
48        // 将路径上所有点指向根节点 root
49        while (x != root) {
50            // 暂存 x 原本的父亲
51            // 将 x 指向根节点
52            // x 指针上移至 x 的父节点
53            int originFather = father.get(x);
54            father.put(x, root);
55            x = originFather;
56        }
57        return root;
58    }
59
60    public boolean isConnected(int x, int y) {
61        // 两个节点连通 等价于 两个节点的根相同
62        return find(x) == find(y);
63    }
64
65    public int getNumOfSet() {
66        // 获得集合数量
67        return numOfSet;
68    }
69
70    public int getSizeOfSet(int x) {
71        // 获得某个点所在集合大小
72        return sizeOfSet.get(find(x));
73    }
74}
```
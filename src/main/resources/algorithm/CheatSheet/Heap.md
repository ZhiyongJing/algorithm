堆 Heap

## 使用条件

- 找最大值或者最小值（60%）
- 找第 k 大（ pop k 次 复杂度 O(klogn) ）（50%）
- 要求 logn 时间对数据进行操作（40%）

## 堆不能解决的问题

- 查询比某个数大的最小值/最接近的值（平衡排序二叉树 Balanced BST 才可以解决）
- 找某段区间的最大值最小值（线段树 SegmentTree 可以解决）
- O(n)*O*(*n*) 找第 k 大 (使用快排中的 partition 操作)

### 代码模板

带删除特定元素功能的堆

java

python

```java
1class ValueIndexPair {
2    int val, index;
3
4    public ValueIndexPair(int val, int index) {
5        this.val = val;
6        this.index = index;
7    }
8
9}
10
11class Heap {
12    private Queue<ValueIndexPair> minheap;
13    private Set<Integer> deleteSet;
14
15    public Heap() {
16        minheap = new PriorityQueue<>((p1, p2) -> (p1.val - p2.val));
17        deleteSet = new HashSet<>();
18    }
19
20    public void push(int index, int val) {
21        minheap.add(new ValueIndexPair(val, index));
22    }
23
24    private void lazyDeletion() {
25        while (minheap.size() != 0 && deleteSet.contains(minheap.peek().index)) {
26            ValueIndexPair pair = minheap.poll();
27            deleteSet.remove(pair.index);
28        }
29    }
30
31    public ValueIndexPair top() {
32        lazyDeletion();
33        return minheap.peek();
34    }
35
36    public void pop() {
37        lazyDeletion();
38        minheap.poll();
39    }
40
41    public void delete(int index) {
42        deleteSet.add(index);
43    }
44
45    public boolean isEmpty() {
46        return minheap.size() == 0;
47    }
48
49}
```
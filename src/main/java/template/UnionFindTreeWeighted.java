package template;

import java.util.Arrays;

public class UnionFindTreeWeighted {

    //记录结点元素和该元素的父结点
    private final int[] eleAndGroup;

    //存储每个根结点对应的树中元素的个数
    private final int[] sz;

    // 记录并查集中数据的分组个数
    private int count;

    public UnionFindTreeWeighted(int n) {
        // 初始化分组的数量，默认情况下，有n个分组
        this.count = n;
        // 初始化eleAndGroup数组
        this.eleAndGroup = new int[n];
        // 初始化eleAndGroup中的元素及其所在的组的标识符，
        //让eleAndGroup数组的索引作为并查集的每个节点的元素，
        //并且让每个索引处的值就是该索引（该元素所在的组的标识符）
        for (int i = 0; i < eleAndGroup.length; i++) {
            this.eleAndGroup[i] = i;
        }
        System.out.println(Arrays.toString(eleAndGroup));//[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        this.sz = new int[n];
        Arrays.fill(this.sz, 1);
    }

    // 获取当前并查集中的数据有多少个分组
    public int count() {
        return count;
    }

    //判断并查集中元素p和元素q是否在同一分组中
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 元素p所在分组的标识符
    public int find(int p) {
        while (true) {
            if (p == eleAndGroup[p]) {
                return p;
            }

            p = eleAndGroup[p];
        }
    }

    /**
     * 把p元素所在分组和q元素所在分组合并
     */
    public void union(int p, int q) {
        // 找到p元素和q元素所在组对应的树的根节点
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        // 判断pRoot对应的树大，还是qRoot对应的树大，最终需要吧较小的树合并到较大的树中
        if (sz[pRoot] < sz[qRoot]) {
            eleAndGroup[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            eleAndGroup[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public static void main(String[] args) {
        // 初始化并查集，包含 10 个元素（0 ~ 9）
        UnionFindTreeWeighted uf = new UnionFindTreeWeighted(10);
        System.out.println(Arrays.toString(uf.eleAndGroup));

        // 进行一些合并操作
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(1, 2); // 连接 (0,1) 和 (2,3)
        uf.union(5, 6); // 连接 (4,5) 和 (6,7)
        uf.union(3, 4); // 连接 (0,1,2,3) 和 (4,5,6,7)

        // 判断一些元素是否属于同一集合
        System.out.println("0 和 3 是否连通: " + uf.connected(0, 3)); // true
        System.out.println("4 和 7 是否连通: " + uf.connected(4, 7)); // true
        System.out.println("1 和 5 是否连通: " + uf.connected(1, 5)); // true
        System.out.println("7 和 8 是否连通: " + uf.connected(7, 8)); // false

        // 当前并查集的集合数量
        System.out.println("当前集合数量: " + uf.count()); // 2
    }
}

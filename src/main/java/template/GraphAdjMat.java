package template;

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
        GraphAdjMat graph = new GraphAdjMat(points, ini);
        graph.print();

    }
}

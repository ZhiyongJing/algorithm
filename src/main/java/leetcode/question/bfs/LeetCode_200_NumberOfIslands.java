// 声明所在的包名
package leetcode.question.bfs;

// 导入Java标准库中的LinkedList和Queue类，用于后续的BFS操作
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *@Question:  200. Number of Islands
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 94.34%
 *@Time  Complexity: O(M * N) where M is the number of rows and N is the number of columns - BFS
 *@Space Complexity: O(min(M, N)) - BFS, O(m * N) for dfs and unionfind
 */

/**
 * 1. 题目描述（Problem Statement）
 *
 *    给定一个由 '1'（陆地）和 '0'（水）组成的二维网格，计算网格中“岛屿”的数量。
 *    “岛屿”被水域包围，并且只能在水平或垂直方向上连通。假设网格的四个边均被水包围。
 *
 *    示例：
 *      输入：
 *      1 1 1 1 0
 *      1 1 0 1 0
 *      1 1 0 0 0
 *      0 0 0 0 0
 *      输出：1
 *      解释：上面网格只有一整块“1”相连，其余都是“0”。
 *
 * 2. 解题思路（Solution Approaches）
 *
 *    基于给出的代码，主要有三种方法来解决“岛屿数量”这个问题：
 *
 *    （A）DFS（深度优先搜索）：
 *       - 思路：当我们找到一个为 '1' 的网格位置后，就可以通过 DFS 的方式，沿着“上下左右”四个方向对与其相连的所有陆地进行搜索和标记。
 *       - 实现：
 *         1. 遍历网格的每个位置，如果遇到 '1'，就将岛屿数量加 1，并开始执行 DFS。
 *         2. 在 DFS 过程中，每当找到一个 '1' 时，都将其标记为 '0'，防止重复访问。
 *         3. 递归搜索其相邻的四个方向（上下左右），直到无法再扩展。
 *       - 举例：假设某个位置 grid[r][c] = '1'。进入 DFS 后，会把 grid[r][c] 置为 '0' 并依次检查它的上、下、左、右。如果上侧也是 '1'，则继续递归，直到把与之相连的所有 '1' 都标记为 '0'。
 *
 *    （B）BFS（广度优先搜索）：
 *       - 思路：与 DFS 类似，当我们遇到一个 '1' 时，可以利用队列进行层层扩展搜索，把所有与其相连的陆地都一次性处理完。
 *       - 实现：
 *         1. 每当发现一个新的 '1'，岛屿数量加 1，随后将它入队并将该位置标记为 '0'。
 *         2. 利用队列弹出元素后，根据它的坐标来检查上下左右，如果发现相邻位置是 '1'，就将其标记为 '0' 并加入队列。
 *         3. 不断执行以上过程，直到队列为空。这样就能一次性把一个岛屿所有的 '1' 都找出来并标记掉。
 *       - 举例：如果某个位置 (r, c) 是 '1'，首先把它入队列，然后将 (r, c) 设为 '0'。从队列弹出该元素后，再将其邻居（(r-1,c)、(r+1,c)、(r,c-1)、(r,c+1)）若为 '1' 则同样入队并设为 '0'。直到队列为空就结束了对这一片岛屿的搜索。
 *
 *    （C）Union-Find（并查集）：
 *       - 思路：将二维网格“扁平化”成一维的并查集结构，把每个 '1' 看成一个节点，利用并查集可以快速地合并相邻节点（陆地）并最终统计连通分量的数量。
 *       - 实现：
 *         1. 初始化：给每个为 '1' 的网格位置建立一个并查集中的节点，并把计数器 count 设置为这些节点的个数。
 *         2. 遍历所有网格位置。对每个 '1'，如果与上下左右方向上的 '1' 相邻，则调用 union() 函数，将这两个节点合并在一起，并使 count 减一。
 *         3. 结束遍历后，并查集中的连通分量数量（count）即为岛屿数量。
 *       - 举例：若网格中某一行上连续两个位置 (r, c) 和 (r, c+1) 都是 '1'，则在并查集层面上，这两个索引对应的节点属于同一连通分量。
 *       在 union 操作后，这两个节点的根相同，表示它们在同一个岛上。
 *
 * 3. 时间与空间复杂度（Time and Space Complexity）
 *
 *    - 时间复杂度：
 *      • 无论使用 DFS、BFS 还是 Union-Find，整体都会对网格上的所有元素进行至少一次遍历，
 *        因此时间复杂度通常为 O(M*N)，其中 M、N 分别是网格的行数和列数。
 *      • DFS/BFS 在最坏情况下（整个网格都是 '1'）需要将整张网格都遍历一遍。
 *      • Union-Find 在合并阶段也会进行相同规模的遍历，只不过每次 union/find 操作近似 O(1)（有路径压缩和按秩合并优化）。
 *
 *    - 空间复杂度：
 *      • DFS 可能在最坏情况下（整个网格都是 '1'）导致递归深度达到 O(M*N)，因此使用递归的 DFS 可能有 O(M*N) 的栈空间开销。
 *      • BFS 最多队列里存放边界位置的节点数，通常可达 O(min(M, N))（当整个长或宽一条线上都是陆地时）。
 *      • Union-Find 需要建立一个大小为 M*N 的 parent 数组和 rank 数组，空间复杂度 O(M*N)。
 *
 *    综上所述，三种方法在时间复杂度上均为 O(M*N)，在空间复杂度上各有侧重但都在 O(M*N) 及以下的范围内。
 */


public class LeetCode_200_NumberOfIslands {

    //leetcode submit region begin(Prohibit modification and deletion)

    // 声明Solution内部类，用于封装本题求解方法
    class Solution {

        /**
         * DFS 遍历岛屿，将遍历过的陆地标记为 '0'
         *
         * @param grid 二维网格
         * @param r    当前行
         * @param c    当前列
         */

        // 声明dfs方法，使用深度优先搜索遍历
        void dfs(char[][] grid, int r, int c) {
            // nr表示网格的行数
            int nr = grid.length;
            // nc表示网格的列数
            int nc = grid[0].length;

            // 如果行或列超出了网格范围，或者当前位置不是陆地('1')而是水('0')，直接返回
            if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
                return;
            }

            // 将当前访问到的陆地标记为'0'，表示已访问
            grid[r][c] = '0';
            // 递归向上方搜索
            dfs(grid, r - 1, c);
            // 递归向下方搜索
            dfs(grid, r + 1, c);
            // 递归向左方搜索
            dfs(grid, r, c - 1);
            // 递归向右方搜索
            dfs(grid, r, c + 1);
        }

        /**
         * 计算岛屿数量，使用 DFS 遍历
         *
         * @param grid 二维网格
         * @return 岛屿数量
         */

        // numIslands方法，利用DFS统计岛屿数
        public int numIslands(char[][] grid) {
            // 如果传入的grid为空或行数为0，直接返回0
            if (grid == null || grid.length == 0) {
                return 0;
            }

            // nr表示行数
            int nr = grid.length;
            // nc表示列数
            int nc = grid[0].length;
            // numIslands记录岛屿数量
            int numIslands = 0;

            // 双重for循环遍历网格
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    // 如果当前位置是陆地('1')
                    if (grid[r][c] == '1') {
                        // 岛屿数量加1
                        ++numIslands;
                        // 调用dfs将与此陆地相连的整片陆地都标记
                        dfs(grid, r, c);
                    }
                }
            }

            // 返回岛屿数量
            return numIslands;
        }

        /**
         * 计算岛屿数量，使用 BFS 遍历
         *
         * @param grid 二维网格
         * @return 岛屿数量
         */

        // numIslands2方法，利用BFS统计岛屿数
        public int numIslands2(char[][] grid) {
            // 如果传入的grid为空或行数为0，直接返回0
            if (grid == null || grid.length == 0) {
                return 0;
            }

            // nr表示行数
            int nr = grid.length;
            // nc表示列数
            int nc = grid[0].length;
            // numIslands记录岛屿数量
            int numIslands = 0;

            // 双重for循环遍历网格
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    // 如果当前位置是陆地('1')
                    if (grid[r][c] == '1') {
                        // 岛屿数量加1
                        ++numIslands;
                        // 将当前访问位置标记为 '0'，表示已访问
                        grid[r][c] = '0';
                        // 创建一个队列，用来存储需要进行BFS遍历的节点
                        Queue<Integer> neighbors = new LinkedList<>();
                        // 将当前坐标转换为一个整数(id)并放入队列
                        neighbors.add(r * nc + c);

                        // 当队列不为空时，不断进行BFS
                        while (!neighbors.isEmpty()) {
                            // 从队列移除一个节点
                            int id = neighbors.remove();
                            // row为该节点在网格中的行index
                            int row = id / nc;
                            // col为该节点在网格中的列index
                            int col = id % nc;

                            // 检查上方是否是陆地('1')
                            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                                // 如果是，则加入队列并标记为已访问('0')
                                neighbors.add((row - 1) * nc + col);
                                grid[row - 1][col] = '0';
                            }
                            // 检查下方是否是陆地('1')
                            if (row + 1 < nr && grid[row + 1][col] == '1') {
                                neighbors.add((row + 1) * nc + col);
                                grid[row + 1][col] = '0';
                            }
                            // 检查左方是否是陆地('1')
                            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                                neighbors.add(row * nc + col - 1);
                                grid[row][col - 1] = '0';
                            }
                            // 检查右方是否是陆地('1')
                            if (col + 1 < nc && grid[row][col + 1] == '1') {
                                neighbors.add(row * nc + col + 1);
                                grid[row][col + 1] = '0';
                            }
                        }
                    }
                }
            }

            // 返回岛屿数量
            return numIslands;
        }

        // union find
        // 声明UnionFind类，用于并查集操作
        class UnionFind {
            // count记录当前连通分量（岛屿）的数量
            int count;
            // parent数组用于记录每个元素的父节点
            int[] parent;
            // rank数组用于记录并查集的树高度，用于优化合并
            int[] rank;

            // 构造函数，传入grid初始化并查集结构
            public UnionFind(char[][] grid) { // for problem 200
                // 初始化时的连通分量数为0
                count = 0;
                // m为行数
                int m = grid.length;
                // n为列数
                int n = grid[0].length;
                // parent数组大小为m*n，每个位置对应一个网格中的节点
                parent = new int[m * n];
                // rank数组大小也为m*n
                rank = new int[m * n];
                // 双重循环遍历网格
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        // 如果该位置是陆地('1')
                        if (grid[i][j] == '1') {
                            // 初始化parent为自身
                            parent[i * n + j] = i * n + j;
                            // 并查集中连通分量数+1
                            ++count;
                        }
                        // 初始化rank为0
                        rank[i * n + j] = 0;
                    }
                }
                System.out.println(Arrays.toString(parent));
            }

            // find方法，路径压缩操作
            public int find(int i) { // path compression
                // 如果当前节点的父节点不是它自己，递归向上找，并进行路径压缩
                if (parent[i] != i) parent[i] = find(parent[i]);
                // 返回根节点
                return parent[i];
            }

            // union方法，根据rank进行合并
            public void union(int x, int y) { // union with rank
                // 找到x和y的根节点
                int rootx = find(x);
                int rooty = find(y);
                // 如果它们不在同一连通分量，则进行合并
                if (rootx != rooty) {
                    // 比较两棵树的rank
                    if (rank[rootx] > rank[rooty]) {
                        // 将rank小的树的根嫁接到rank大的树的根
                        parent[rooty] = rootx;
                    } else if (rank[rootx] < rank[rooty]) {
                        parent[rootx] = rooty;
                    } else {
                        // 如果rank相等，则随意将一方设为另一方的父节点，并将rank+1
                        parent[rooty] = rootx;
                        rank[rootx] += 1;
                    }
                    // 合并成功后，连通分量数减一
                    --count;
                }
            }

            // 获取当前连通分量数
            public int getCount() {
                return count;
            }
        }

        // numIslands3方法，利用并查集(Union-Find)统计岛屿数
        public int numIslands3(char[][] grid) {
            // 若grid为空或长度为0，直接返回0
            if (grid == null || grid.length == 0) {
                return 0;
            }

            // nr表示行数
            int nr = grid.length;
            // nc表示列数
            int nc = grid[0].length;
            // num_islands用于存储结果
            int num_islands = 0;
            // 创建UnionFind实例
            UnionFind uf = new UnionFind(grid);

            // 双重循环遍历网格
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    // 如果遇到陆地
                    if (grid[r][c] == '1') {
                        // 将其标记为'0'表示已访问
                        grid[r][c] = '0';
                        // 如果上方有陆地，则合并
                        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                            uf.union(r * nc + c, (r - 1) * nc + c);
                        }
                        // 如果下方有陆地，则合并
                        if (r + 1 < nr && grid[r + 1][c] == '1') {
                            uf.union(r * nc + c, (r + 1) * nc + c);
                        }
                        // 如果左方有陆地，则合并
                        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                            uf.union(r * nc + c, r * nc + c - 1);
                        }
                        // 如果右方有陆地，则合并
                        if (c + 1 < nc && grid[r][c + 1] == '1') {
                            uf.union(r * nc + c, r * nc + c + 1);
                        }
                    }
                }
            }

            // 返回并查集的连通分量数
            return uf.getCount();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    // main方法用于测试
    public static void main(String[] args) {
        // 创建Solution对象
        Solution solution = new LeetCode_200_NumberOfIslands().new Solution();

        // Test Case 1
        // 准备第一个测试用例网格
        char[][] grid1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        // 输出DFS计算结果：期望为1
        System.out.println("Test Case 1: " + solution.numIslands(grid1)); // Output: 1

        // Test Case 2
        // 准备第二个测试用例网格
        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        // 输出BFS计算结果：期望为3
        System.out.println("Test Case 2: " + solution.numIslands2(grid2)); // Output: 3

        // Test Case 3（新增测试用例）
        // 准备第三个测试用例网格
        char[][] grid3 = {
                {'1','0','1'},
                {'0','0','0'},
                {'1','0','1'}
        };
        // 这里有4个独立的‘1’，都没有相邻陆地，因此岛屿数量应为4
        System.out.println("Test Case 3: " + solution.numIslands3(grid3)); // Output: 4
    }
}


/**
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0
's (water), return the number of islands. 

 An island is surrounded by water and is formed by connecting adjacent lands 
horizontally or vertically. You may assume all four edges of the grid are all 
surrounded by water. 

 
 Example 1: 

 
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
 

 Example 2: 

 
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 300 
 grid[i][j] is '0' or '1'. 
 

 Related Topics Array Depth-First Search Breadth-First Search Union Find Matrix 
👍 21651 👎 471

*/

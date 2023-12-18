package leetcode.frequent.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
  *@Question:  417. Pacific Atlantic Water Flow
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 51.80%
  *@Time  Complexity: O(M * N)
  *@Space Complexity: O(M * N)
 */

/**
 * ### 算法思路：
 *
 * #### BFS解法（pacificAtlantic方法）：
 *
 * 1. **初始化参数：** 为矩阵行数、列数和海拔高度矩阵分别赋值。创建两个队列，一个表示太平洋边界，一个表示大西洋边界。
 *
 * 2. **太平洋和大西洋边界：** 将太平洋边界的单元格（第一列和第一行）和大西洋边界的单元格（最后一列和最后一行）加入对应的队列。
 *
 * 3. **BFS搜索：** 对太平洋和大西洋的队列进行BFS搜索，标记可达的单元格。BFS的过程中，从当前单元格出发，沿着海拔递增的方向探索，
 * 并将可达的单元格标记为可达。
 *
 * 4. **找到交汇点：** 遍历整个矩阵，找到既能到达太平洋又能到达大西洋的单元格，将它们的坐标加入结果集。
 *
 * #### DFS解法（pacificAtlantic2方法）：
 *
 * 1. **初始化参数：** 为矩阵行数、列数和海拔高度矩阵分别赋值。创建两个布尔型二维数组，分别表示从每个海洋出发可达的位置。
 *
 * 2. **DFS搜索：** 对每个与太平洋和大西洋相邻的单元格进行DFS搜索，标记可达的单元格。DFS的过程中，从当前单元格出发，
 * 沿着海拔递增的方向递归探索，并将可达的单元格标记为可达。
 *
 * 3. **找到交汇点：** 遍历整个矩阵，找到既能到达太平洋又能到达大西洋的单元格，将它们的坐标加入结果集。
 *
 * ### 复杂度分析：
 *
 * #### BFS解法：
 *
 * - 时间复杂度：O(m * n)，其中 m 为行数，n 为列数。每个单元格只会被访问一次。
 * - 空间复杂度：O(m * n)，用于存储可达性信息的布尔型二维数组。
 *
 * #### DFS解法：
 *
 * - 时间复杂度：O(m * n)，其中 m 为行数，n 为列数。每个单元格只会被访问一次。
 * - 空间复杂度：O(m * n)，用于存储可达性信息的布尔型二维数组。递归调用栈的最大深度为 O(min(m, n))，因为在最坏情况下，
 * 所有单元格都是递归调用的路径上的一部分。
 */
public class LeetCode_417_PacificAtlanticWaterFlow {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        private int numRows;
        private int numCols;
        private int[][] landHeights;

        // 主函数，使用BFS解决问题
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            // 检查输入是否为空
            if (matrix.length == 0 || matrix[0].length == 0) {
                return new ArrayList<>();
            }

            // 保存初始值到参数
            numRows = matrix.length;
            numCols = matrix[0].length;
            landHeights = matrix;

            // 设置两个队列，分别表示与太平洋和大西洋相邻的单元格
            Queue<int[]> pacificQueue = new LinkedList<>();
            Queue<int[]> atlanticQueue = new LinkedList<>();
            for (int i = 0; i < numRows; i++) {
                pacificQueue.offer(new int[]{i, 0});
                atlanticQueue.offer(new int[]{i, numCols - 1});
            }
            for (int i = 0; i < numCols; i++) {
                pacificQueue.offer(new int[]{0, i});
                atlanticQueue.offer(new int[]{numRows - 1, i});
            }
//            pacificQueue.forEach(i -> System.out.println(Arrays.asList(i)));
//            atlanticQueue.forEach(i -> System.out.println(Arrays.asList(i)));

            // 分别对太平洋和大西洋执行BFS，找到所有可达的单元格
            boolean[][] pacificReachable = bfs(pacificQueue);
            boolean[][] atlanticReachable = bfs(atlanticQueue);

            // 找到既能到达太平洋又能到达大西洋的单元格
            List<List<Integer>> commonCells = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                        commonCells.add(Arrays.asList(i, j));
                    }
                }
            }
            return commonCells;
        }

        // BFS算法，找到所有可达的单元格
        private boolean[][] bfs(Queue<int[]> queue) {
            boolean[][] reachable = new boolean[numRows][numCols];
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                // 该单元格是可达的，标记为true
                reachable[cell[0]][cell[1]] = true;
                for (int[] dir : DIRECTIONS) { // 检查四个方向
                    int newRow = cell[0] + dir[0];
                    int newCol = cell[1] + dir[1];
                    // 检查新单元格是否在边界内
                    if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) {
                        continue;
                    }
                    // 检查新单元格是否已经访问过
                    if (reachable[newRow][newCol]) {
                        continue;
                    }
                    // 检查新单元格的高度是否大于等于当前单元格的高度，
                    // 以确保水可以从新单元格流向当前单元格
                    if (landHeights[newRow][newCol] < landHeights[cell[0]][cell[1]]) {
                        continue;
                    }
                    // 如果执行到这一步，说明新单元格是可达的
                    queue.offer(new int[]{newRow, newCol});
                }
            }
            return reachable;
        }

        // DFS算法
        public List<List<Integer>> pacificAtlantic2(int[][] matrix) {
            // 检查输入是否为空
            if (matrix.length == 0 || matrix[0].length == 0) {
                return new ArrayList<>();
            }

            // 保存初始值到参数
            numRows = matrix.length;
            numCols = matrix[0].length;
            landHeights = matrix;
            boolean[][] pacificReachable = new boolean[numRows][numCols];
            boolean[][] atlanticReachable = new boolean[numRows][numCols];

            // 遍历每个与海洋相邻的单元格，并开始DFS
            for (int i = 0; i < numRows; i++) {
                dfs(i, 0, pacificReachable);
                dfs(i, numCols - 1, atlanticReachable);
            }
            for (int i = 0; i < numCols; i++) {
                dfs(0, i, pacificReachable);
                dfs(numRows - 1, i, atlanticReachable);
            }

            // 找到既能到达太平洋又能到达大西洋的单元格
            List<List<Integer>> commonCells = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                        commonCells.add(Arrays.asList(i, j));
                    }
                }
            }
            return commonCells;
        }

        // DFS算法，找到所有可达的单元格
        private void dfs(int row, int col, boolean[][] reachable) {
            // 该单元格是可达的，标记为true
            reachable[row][col] = true;
            for (int[] dir : DIRECTIONS) { // 检查四个方向
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                // 检查新单元格是否在边界内
                if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) {
                    continue;
                }
                // 检查新单元格是否已经访问过
                if (reachable[newRow][newCol]) {
                    continue;
                }
                // 检查新单元格的高度是否大于等于当前单元格的高度，
                // 以确保水可以从新单元格流向当前单元格
                if (landHeights[newRow][newCol] < landHeights[row][col]) {
                    continue;
                }
                // 如果执行到这一步，说明新单元格是可达的
                dfs(newRow, newCol, reachable);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_417_PacificAtlanticWaterFlow().new Solution();

        // 测试BFS算法
        int[][] matrix = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        List<List<Integer>> resultBFS = solution.pacificAtlantic(matrix);
        System.out.println("BFS结果：" + resultBFS);

        // 测试DFS算法
        List<List<Integer>> resultDFS = solution.pacificAtlantic2(matrix);
        System.out.println("DFS结果：" + resultDFS);
    }
}


/**
There is an m x n rectangular island that borders both the Pacific Ocean and 
Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the 
Atlantic Ocean touches the island's right and bottom edges. 

 The island is partitioned into a grid of square cells. You are given an m x n 
integer matrix heights where heights[r][c] represents the height above sea level 
of the cell at coordinate (r, c). 

 The island receives a lot of rain, and the rain water can flow to neighboring 
less than or equal to the current cell's height. Water can flow from any cell
adjacent to an ocean into the ocean. 

 Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes 
that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic 
oceans. 

 
 Example 1: 
 
 
Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, 
as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific 
and Atlantic oceans.
 

 Example 2: 

 
Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic 
oceans.
 

 
 Constraints: 

 
 m == heights.length 
 n == heights[r].length 
 1 <= m, n <= 200 
 0 <= heights[r][c] <= 10⁵ 
 

 Related Topics Array Depth-First Search Breadth-First Search Matrix 👍 7038 👎 
1370

*/

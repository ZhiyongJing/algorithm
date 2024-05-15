package interview.company.amazon;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Question:  994. Rotting Oranges
 * @Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 76.9%
 * @Time  Complexity: O(N*M) N M is size of grid
 * @Space Complexity: O(N*M)
 */

/**
 * 这段代码解决的问题是橘子腐烂问题。给定一个网格，网格中的每个单元格可以是以下三种状态之一：
 *
 * - 0 表示单元格为空。
 * - 1 表示单元格中有新鲜的橘子。
 * - 2 表示单元格中有腐烂的橘子。
 *
 * 腐烂的橘子会污染其上、下、左、右相邻的新鲜橘子，使其变为腐烂的橘子。
 * 现在要求计算出在多少分钟后，网格中所有的新鲜橘子都将变为腐烂的橘子。如果这不可能，则返回 -1。
 *
 * 算法思路如下：
 *
 * 1. 创建一个队列用于存储腐烂的橘子的坐标。
 * 2. 遍历整个网格，将所有腐烂的橘子的坐标加入队列，同时统计新鲜橘子的数量。
 * 3. 从队列中取出腐烂的橘子，将其相邻的新鲜橘子变为腐烂的橘子，并将其加入队列。
 * 4. 重复上述过程，直到队列为空。
 * 5. 统计经过的分钟数，即为答案。
 *
 * 接下来分析时间复杂度和空间复杂度：
 *
 * - 时间复杂度：遍历整个网格需要 O(N*M) 的时间，其中 N 和 M 分别是网格的行数和列数。在 BFS 中，
 * 每个节点至多会被访问一次，因此 BFS 的时间复杂度也是 O(N*M)。
 * - 空间复杂度：除了输入网格外，额外使用了一个队列和一些辅助变量，它们的空间占用与网格大小相当，
 * 因此空间复杂度为 O(N*M)。
 */

public class LeetCode_994_RottingOranges{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // orangesRotting 方法用于计算橘子腐烂的分钟数
        public int orangesRotting(int[][] grid) {
            Queue<Pair<Integer, Integer>> queue = new ArrayDeque(); // 创建一个队列用于存储坐标对

            // Step 1). build the initial set of rotten oranges
            int freshOranges = 0; // 记录新鲜橘子的数量
            int ROWS = grid.length, COLS = grid[0].length; // 获取网格的行数和列数

            // 遍历整个网格
            for (int r = 0; r < ROWS; ++r) {
                for (int c = 0; c < COLS; ++c) {
                    if (grid[r][c] == 2) // 如果当前位置的橘子已经腐烂
                        queue.offer(new Pair(r, c)); // 将该坐标对加入队列
                    else if (grid[r][c] == 1) // 如果当前位置的橘子是新鲜的
                        freshOranges++; // 新鲜橘子数量加一
                }
            }

            // Mark the round / level, _i.e_ the ticker of timestamp
            queue.offer(new Pair(-1, -1)); // 将一个特殊的坐标对 (-1, -1) 加入队列作为标记

            // Step 2). start the rotting process via BFS
            int minutesElapsed = -1; // 记录经过的分钟数
            int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 定义上、右、下、左四个方向的偏移量数组

            // 使用 BFS 进行腐烂过程
            while (!queue.isEmpty()) {
                Pair<Integer, Integer> p = queue.poll(); // 从队列中取出一个坐标对
                int row = p.getKey(); // 获取坐标对的行索引
                int col = p.getValue(); // 获取坐标对的列索引
                if (row == -1) { // 如果遇到了特殊标记 (-1, -1)
                    // We finish one round of processing
                    minutesElapsed++; // 分钟数加一，表示完成了一轮处理
                    // to avoid the endless loop
                    if (!queue.isEmpty()) // 如果队列不为空，说明还有新的一轮需要处理
                        queue.offer(new Pair(-1, -1)); // 加入下一轮的特殊标记
                } else { // 如果当前位置的橘子已经腐烂
                    // this is a rotten orange
                    // then it would contaminate its neighbors
                    for (int[] d : directions) { // 遍历四个方向
                        int neighborRow = row + d[0]; // 计算相邻位置的行索引
                        int neighborCol = col + d[1]; // 计算相邻位置的列索引
                        if (neighborRow >= 0 && neighborRow < ROWS &&
                                neighborCol >= 0 && neighborCol < COLS) { // 确保相邻位置在网格范围内
                            if (grid[neighborRow][neighborCol] == 1) { // 如果相邻位置的橘子是新鲜的
                                // this orange would be contaminated
                                grid[neighborRow][neighborCol] = 2; // 将相邻位置的橘子标记为腐烂
                                freshOranges--; // 新鲜橘子数量减一
                                // this orange would then contaminate other oranges
                                queue.offer(new Pair(neighborRow, neighborCol)); // 将相邻位置的橘子加入队列
                            }
                        }
                    }
                }
            }

            // return elapsed minutes if no fresh orange left
            return freshOranges == 0 ? minutesElapsed : -1; // 如果没有新鲜橘子剩余，返回经过的分钟数；否则返回 -1
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_994_RottingOranges().new Solution(); // 创建 Solution 对象
        // 测试用例1
        int[][] grid1 = {{2,1,1},{1,1,0},{0,1,1}}; // 定义第一个网格
        System.out.println(solution.orangesRotting(grid1)); // 打印输出结果
        // 测试用例2
        int[][] grid2 = {{2,1,1},{0,1,1},{1,0,1}}; // 定义第二个网格
        System.out.println(solution.orangesRotting(grid2)); // 打印输出结果
    }
}

/**
You are given an m x n grid where each cell can have one of three values: 

 
 0 representing an empty cell, 
 1 representing a fresh orange, or 
 2 representing a rotten orange. 
 

 Every minute, any fresh orange that is 4-directionally adjacent to a rotten 
orange becomes rotten. 

 Return the minimum number of minutes that must elapse until no cell has a 
fresh orange. If this is impossible, return -1. 

 
 Example 1: 
 
 
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
 

 Example 2: 

 
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never 
rotten, because rotting only happens 4-directionally.
 

 Example 3: 

 
Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer 
is just 0.
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 10 
 grid[i][j] is 0, 1, or 2. 
 

 Related Topics Array Breadth-First Search Matrix 👍 12533 👎 396

*/
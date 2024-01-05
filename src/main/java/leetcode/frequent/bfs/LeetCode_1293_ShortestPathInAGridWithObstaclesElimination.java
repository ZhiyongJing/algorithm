package leetcode.frequent.bfs;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

/**
  *@Question:  1293. Shortest Path in a Grid with Obstacles Elimination
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 65.03%
  *@Time  Complexity: O(N*K) N be the number of cells in the grid, and K be the quota to eliminate obstacles.
  *@Space Complexity: O(N*K)
 */

/**
 * **算法思路详解：**
 *
 * 该算法采用广度优先搜索（BFS）来找到从起点到终点的最短路径。在这个问题中，状态由当前所在的位置和剩余的障碍物消除次数组成。
 * 通过BFS，我们从起点开始，一步一步地移动，并考虑剩余的障碍物消除次数。在每一步中，我们尝试四个方向移动，然后检查新位置是否在网格内，
 * 是否满足剩余消除障碍物的次数。如果满足条件，将新状态加入队列，继续探索。当我们到达终点时，就找到了最短路径。
 *
 * **具体步骤如下：**
 *
 * 1. 定义状态类 `StepState`，包含当前步数、当前行、当前列和剩余的障碍物消除次数。
 * 2. 初始化队列 `queue` 和集合 `seen`，将起点状态加入队列并标记为已访问。
 * 3. 进行BFS，从队列中取出当前状态，尝试四个方向的移动，生成新状态。
 * 4. 检查新状态是否满足条件，如果满足且未被访问过，则加入队列和集合。
 * 5. 重复步骤3和步骤4，直到到达终点或队列为空。
 * 6. 如果到达终点，返回最短路径的步数；否则，返回 -1 表示无法到达。
 *
 * **时间复杂度：**
 *
 * 每个状态都会被访问一次，而状态的数量不会超过网格的大小。因此，时间复杂度为 O(rows * cols)。
 *
 * **空间复杂度：**
 *
 * 空间复杂度取决于队列和集合的空间使用。在最坏情况下，队列和集合的空间复杂度都为 O(rows * cols * k)。
 */

public class LeetCode_1293_ShortestPathInAGridWithObstaclesElimination {

    // leetcode submit region begin(Prohibit modification and deletion)
    class StepState {
        /**
         * 用于保存每一步的状态信息的数据对象：
         * <步数, 行, 列, 剩余消除障碍物的次数>
         */
        public int steps, row, col, k;

        public StepState(int steps, int row, int col, int k) {
            this.steps = steps;
            this.row = row;
            this.col = col;
            this.k = k;
        }

        @Override
        public int hashCode() {
            // 在将对象放入任何容器类中时需要的哈希码生成方法
            return (this.row + 1) * (this.col + 1) * this.k;
        }

        @Override
        public boolean equals(Object other) {
            /**
             * 只有 (row, col, k) 作为状态信息是重要的
             */
            if (!(other instanceof StepState)) {
                return false;
            }
            StepState newState = (StepState) other;
            return (this.row == newState.row) && (this.col == newState.col) && (this.k == newState.k);
        }

        @Override
        public String toString() {
            return String.format("%d %d %d", this.row, this.col, this.k);
        }
    }

    class Solution {
        public int shortestPath(int[][] grid, int k) {
            int rows = grid.length, cols = grid[0].length;
            int[] target = {rows - 1, cols - 1};

            // 如果我们有足够的配额来消除障碍物，那么最短距离就是曼哈顿距离。
            if (k >= rows + cols - 2) {
                return rows + cols - 2;
            }

            Deque<StepState> queue = new LinkedList<>();
            HashSet<StepState> seen = new HashSet<>();

            // (步数, 行, 列, 剩余消除障碍物的配额)
            StepState start = new StepState(0, 0, 0, k);
            queue.addLast(start);
            seen.add(start);

            while (!queue.isEmpty()) {
                StepState curr = queue.pollFirst();

                // 到达目标位置
                if (target[0] == curr.row && target[1] == curr.col) {
                    return curr.steps;
                }

                int[] nextSteps = {curr.row, curr.col + 1, curr.row + 1, curr.col,
                        curr.row, curr.col - 1, curr.row - 1, curr.col};

                // 探索下一步的四个方向
                for (int i = 0; i < nextSteps.length; i += 2) {
                    int nextRow = nextSteps[i];
                    int nextCol = nextSteps[i + 1];

                    // 超出网格边界
                    if (0 > nextRow || nextRow == rows || 0 > nextCol || nextCol == cols) {
                        continue;
                    }

                    int nextElimination = curr.k - grid[nextRow][nextCol];
                    StepState newState = new StepState(curr.steps + 1, nextRow, nextCol, nextElimination);

                    // 如果资格符合，则将下一步添加到队列中
                    if (nextElimination >= 0 && !seen.contains(newState)) {
                        seen.add(newState);
                        queue.addLast(newState);
                    }
                }
            }

            // 未达到目标
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1293_ShortestPathInAGridWithObstaclesElimination().new Solution();
        // 测试代码
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}
        };
        int k = 1;
        int result = solution.shortestPath(grid, k);
        System.out.println(result);  // 应该输出：6
    }
}

/**
You are given an m x n integer matrix grid where each cell is either 0 (empty) 
or 1 (obstacle). You can move up, down, left, or right from and to an empty cell 
in one step. 

 Return the minimum number of steps to walk from the upper left corner (0, 0) 
to the lower right corner (m - 1, n - 1) given that you can eliminate at most k 
obstacles. If it is not possible to find such walk return -1. 

 
 Example 1: 
 
 
Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such 
path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 

 Example 2: 
 
 
Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
Output: -1
Explanation: We need to eliminate at least two obstacles to find such a walk.
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 40 
 1 <= k <= m * n 
 grid[i][j] is either 0 or 1. 
 grid[0][0] == grid[m - 1][n - 1] == 0 
 

 Related Topics Array Breadth-First Search Matrix 👍 4386 👎 82

*/

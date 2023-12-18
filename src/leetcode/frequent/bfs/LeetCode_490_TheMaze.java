package leetcode.frequent.bfs;

/**
 * @Question: 490. The Maze
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 41.83%
 * @Time Complexity: O(m*n* (m + n)), m and n are the number of rows and columns in maze
 * @Space Complexity: O(m*n)
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这道题是一个迷宫问题，我们需要判断从起点到终点是否存在一条路径，使得球能够滚到目的地。
 *
 * **算法思路：**
 *
 * 1. **深度优先搜索 (DFS) 方法：** 通过 DFS，我们可以从起点开始，尝试朝着四个方向滚动球，一直滚到不能再滚动为止。
 * 在这个过程中，我们标记访问过的位置，以避免无限循环。如果最终球停在了目的地，说明存在路径。
 *
 * 2. **广度优先搜索 (BFS) 方法：** 通过 BFS，我们可以将球的位置加入队列，然后不断遍历队列中的位置。
 * 在每个位置上，我们可以朝着四个方向滚动球，直到碰到墙壁为止。如果最终球停在了目的地，说明存在路径。
 *
 * **时间复杂度：**
 *
 * - 在 DFS 中，我们遍历整个迷宫，每次尝试四个方向，因此时间复杂度为 O(m * n * (m + n))，其中 m 和 n 分别为迷宫的行数和列数。
 *
 * - 在 BFS 中，我们同样需要遍历整个迷宫，每次尝试四个方向，因此时间复杂度也为 O(m * n * (m + n))。
 *
 * **空间复杂度：**
 *
 * - 在 DFS 中，我们使用了递归调用栈以及标记访问位置的数组，因此空间复杂度为 O(m * n)。
 *
 * - 在 BFS 中，我们使用了队列以及标记访问位置的数组，因此空间复杂度为 O(m * n)。
 *
 * 综合来看，这两种方法的时间复杂度和空间复杂度都是相同的。
 */

public class LeetCode_490_TheMaze {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 使用深度优先搜索（DFS）判断是否存在路径
        public boolean dfs(int m, int n, int[][] maze, int[] curr, int[] destination,
                           boolean[][] visit) {
            if (visit[curr[0]][curr[1]]) {
                return false;
            }
            if (curr[0] == destination[0] && curr[1] == destination[1]) {
                return true;
            }

            visit[curr[0]][curr[1]] = true;
            int[] dirX = {0, 1, 0, -1};
            int[] dirY = {-1, 0, 1, 0};

            for (int i = 0; i < 4; i++) {
                int r = curr[0], c = curr[1];
                // 将球沿着所选方向移动，直到无法移动为止。
                while (r >= 0 && r < m && c >= 0 && c < n && maze[r][c] == 0) {
                    r += dirX[i];
                    c += dirY[i];
                }
                // 恢复最后一次移动，以获取球滚动到的单元格。
                if (dfs(m, n, maze, new int[]{r - dirX[i], c - dirY[i]}, destination, visit)) {
                    return true;
                }
            }
            return false;
        }

        // 判断是否存在路径
        public boolean hasPath(int[][] maze, int[] start, int[] destination) {
            int m = maze.length;
            int n = maze[0].length;
            boolean[][] visit = new boolean[m][n];
            return dfs(m, n, maze, start, destination, visit);
        }

        // 使用广度优先搜索（BFS）判断是否存在路径
        public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
            int m = maze.length;
            int n = maze[0].length;
            boolean[][] visit = new boolean[m][n];
            int[] dirX = {0, 1, 0, -1};
            int[] dirY = {-1, 0, 1, 0};

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(start);
            visit[start[0]][start[1]] = true;

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                if (curr[0] == destination[0] && curr[1] == destination[1]) {
                    return true;
                }
                for (int i = 0; i < 4; i++) {
                    int r = curr[0];
                    int c = curr[1];
                    // 将球沿着所选方向移动，直到无法移动为止。
                    while (r >= 0 && r < m && c >= 0 && c < n && maze[r][c] == 0) {
                        r += dirX[i];
                        c += dirY[i];
                    }
                    // 恢复最后一次移动，以获取球滚动到的单元格。
                    r -= dirX[i];
                    c -= dirY[i];
                    if (!visit[r][c]) {
                        queue.offer(new int[]{r, c});
                        visit[r][c] = true;
                    }
                }
            }
            return false;
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_490_TheMaze().new Solution();
        // 测试代码
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] start = {0, 4};
        int[] destination = {4, 4};
        boolean result = solution.hasPath(maze, start, destination);
        System.out.println(result);  // 输出：true
    }
}
 /**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (
 * represented as 1). The ball can go through the empty spaces by rolling up, down, left
 * or right, but it won't stop rolling until hitting a wall. When the ball stops,
 * it could choose the next direction.
 * <p>
 * Given the m x n maze, the ball's start position and the destination, where
 * start = [startrow, startcol] and destination = [destinationrow, destinationcol],
 * return true if the ball can stop at the destination, otherwise return false.
 * <p>
 * You may assume that the borders of the maze are all walls (see examples).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]],
 * start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right ->
 * down -> right.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]],
 * start = [0,4], destination = [3,2]
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination. Notice
 * that you can pass through the destination but you cannot stop there.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]],
 * start = [4,3], destination = [0,1]
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * start.length == 2
 * destination.length == 2
 * 0 <= startrow, destinationrow <= m
 * 0 <= startcol, destinationcol <= n
 * Both the ball and the destination exist in an empty space, and they will not
 * be in the same position initially.
 * The maze contains at least 2 empty spaces.
 * <p>
 * <p>
 * Related Topics Array Depth-First Search Breadth-First Search Matrix 👍 1796 👎
 * 179
 */

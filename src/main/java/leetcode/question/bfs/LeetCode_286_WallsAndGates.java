package leetcode.question.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *@Question:  286. Walls and Gates
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 84.95%
 *@Time  Complexity: O(m * N)
 *@Space Complexity: O(M * N)
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 286 - Walls and Gates（墙与门）
 *
 * 给定一个 m x n 的二维网格 `rooms`，每个单元格可以是以下三种类型之一：
 * - 0：门（Gate）
 * - -1：墙（Wall）
 * - INF（即 2^31 - 1）：表示空房间（Empty）
 *
 * 要求：
 * 请将每个空房间填入距离最近门的距离（单位为步长）。如果无法到达任何门，则值保持不变（即保持为 INF）。
 * 可以从一个房间向上、下、左、右四个方向移动一步。
 *
 * 示例输入：
 * [
 *   [INF, -1,  0, INF],
 *   [INF, INF, INF, -1],
 *   [INF, -1, INF, -1],
 *   [  0, -1, INF, INF]
 * ]
 *
 * 输出：
 * [
 *   [3, -1, 0, 1],
 *   [2, 2, 1, -1],
 *   [1, -1, 2, -1],
 *   [0, -1, 3, 4]
 * ]
 *
 * -------------------------------------------------------------------
 * 第二步：解题思路（基于代码逐步详细讲解，并举例）
 * -------------------------------------------------------------------
 * 本题使用 **广度优先搜索（BFS）** 来解决。整体流程如下：
 *
 * ✅ 核心思想：
 * - 以所有的“门”为起点，同时进行 BFS 扩散；
 * - 每当访问到一个空房间（INF），就将其值更新为到最近门的步数；
 * - 由于 BFS 是逐层扩展的，因此第一次到达某个房间的路径就是最短路径。
 *
 * ✅ 步骤详解：
 * 1. 遍历整个二维网格，找到所有为 0 的门，将它们的坐标加入 BFS 队列。
 *    例：门坐标为 (0,2) 和 (3,0)
 *
 * 2. 对队列中的每个点进行 BFS，向四个方向扩散（上下左右）。
 *    使用方向数组 DIRECTIONS 实现方向移动：{上, 下, 左, 右}
 *
 * 3. 对于每个方向上的邻居坐标 (r, c)：
 *    - 如果越界或是墙（-1）或已经被访问（即不是 INF），跳过；
 *    - 否则将该房间值设置为当前房间值 + 1（步数+1），表示到达最近门的距离；
 *    - 并将该点加入队列继续向外扩展。
 *
 * 4. 最终 BFS 结束后，所有可达门的空房间都被填入了最短距离。
 *
 * ✅ 举例说明：
 * 初始状态中，两个门的位置为 (0,2) 和 (3,0)，BFS 从这两个门同时出发。
 * - 第一层扩展，把它们周围的 INF 变成 1；
 * - 第二层扩展，把周围 INF 变成 2；
 * - 直到所有可到达房间都填好距离。
 *
 * BFS 特性保证了第一次访问的就是最短路径，因此结果正确。
 *
 * -------------------------------------------------------------------
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * 时间复杂度：O(m * n)
 * - 每个位置最多只会被加入队列并处理一次，因此总处理量为网格大小。
 * - 初始化时扫描一次网格找门也是 O(m * n)。
 *
 * 空间复杂度：O(m * n)
 * - 最坏情况下队列中会放入所有空房间的坐标，空间为 O(m * n)。
 * - 方向数组和常量为常数空间。
 */


public class LeetCode_286_WallsAndGates{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义常量：INF（空房间的值）和GATE（门的值）
        private static final int EMPTY = Integer.MAX_VALUE;
        private static final int GATE = 0;

        // 定义四个方向：下、上、右、左，用于BFS搜索
        private final List<int[]> DIRECTIONS = Arrays.asList(
                new int[] { 1,  0},
                new int[] {-1,  0},
                new int[] { 0,  1},
                new int[] { 0, -1}
        );

        public void wallsAndGates(int[][] rooms) {
            // 获取行数
            int m = rooms.length;
            // 如果没有行，直接返回
            if (m == 0) return;
            // 获取列数
            int n = rooms[0].length;
            // 初始化BFS队列
            Queue<int[]> q = new LinkedList<>();

            // 将所有门的位置加入队列作为BFS起点
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    // 如果是门，就加入队列
                    if (rooms[row][col] == GATE) {
                        q.add(new int[] { row, col });
                    }
                }
            }

            // BFS开始
            while (!q.isEmpty()) {
                // 取出当前处理的点
                int[] point = q.poll();
                int row = point[0];
                int col = point[1];

                // 遍历上下左右四个方向
                for (int[] direction : DIRECTIONS) {
                    int r = row + direction[0];
                    int c = col + direction[1];
                    // 如果出界，或者当前位置不是空房间，跳过
                    if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                        continue;
                    }
                    // 更新当前位置为距离门的距离（+1）
                    rooms[r][c] = rooms[row][col] + 1;
                    // 将当前位置加入队列，继续BFS
                    q.add(new int[] { r, c });
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_286_WallsAndGates().new Solution();

        // 定义常量INF为Integer.MAX_VALUE，表示空房间
        final int INF = Integer.MAX_VALUE;

        // 测试用例1
        int[][] rooms = {
                {INF, -1,   0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {  0, -1, INF, INF}
        };

        solution.wallsAndGates(rooms);

        // 打印输出结果
        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }

        /*
        期望输出：
        [3, -1,  0,  1]
        [2,  2,  1, -1]
        [1, -1,  2, -1]
        [0, -1,  3,  4]
         */
    }
}

/**
You are given an m x n grid rooms initialized with these three possible values. 


 
 -1 A wall or an obstacle. 
 0 A gate. 
 INF Infinity means an empty room. We use the value 2³¹ - 1 = 2147483647 to 
represent INF as you may assume that the distance to a gate is less than 2147483647.
 
 

 Fill each empty room with the distance to its nearest gate. If it is 
impossible to reach a gate, it should be filled with INF. 

 
 Example 1: 
 
 
Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-
1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 

 Example 2: 

 
Input: rooms = [[-1]]
Output: [[-1]]
 

 
 Constraints: 

 
 m == rooms.length 
 n == rooms[i].length 
 1 <= m, n <= 250 
 rooms[i][j] is -1, 0, or 2³¹ - 1. 
 

 Related Topics Array Breadth-First Search Matrix 👍 3227 👎 70

*/
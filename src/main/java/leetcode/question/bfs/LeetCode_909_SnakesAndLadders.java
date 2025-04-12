package leetcode.question.bfs;
import javafx.util.Pair;

import java.util.*;

/**
 *@Question:  909. Snakes and Ladders
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 52.3%
 *@Time  Complexity: O(N^2) for solution1, O(N^2 * logN) for solution2. N is number of rows and columns.
 *@Space Complexity: O(N^2)
 */
/**
 * ===============================================
 * LeetCode 909. Snakes and Ladders（蛇梯棋）
 * ===============================================
 *
 * 【一、题目描述】
 * 给定一个 N x N 的棋盘，棋盘按“之”字形编号（从左下角开始，第一行从左到右，第二行从右到左，依此类推）。
 * 棋盘中的每一格可能是：
 * - -1：表示普通格子；
 * - 正整数 x：表示玩家到达该格子后，必须立即移动到编号为 x 的格子（梯子或蛇）；
 *
 * 起始点是编号为 1 的格子，目标是编号为 N*N 的格子。
 * 玩家每次可以掷骰子获得 1~6 的步数。
 *
 * 问：从起点到终点的最少步数是多少？如果无法到达，返回 -1。
 *
 *
 * 【二、解题思路（基于代码详细讲解）】
 * 本题本质是 **在编号为 1~N² 的图中寻找最短路径问题**，可使用 BFS 或 Dijkstra 算法实现。
 *
 * 【步骤一：坐标映射】
 * - 棋盘是二维数组 `board[i][j]`，但实际格子编号为 1~N²；
 * - 要先建立一个编号到坐标的映射数组 `cells[label] = (row, col)`；
 * - 遍历从左下角开始按“之”字形编号，比如：
 *   - 6x6 棋盘，编号顺序如下：
 *     36 35 34 33 32 31
 *     25 26 27 28 29 30
 *     24 23 22 21 20 19
 *     13 14 15 16 17 18
 *     12 11 10  9  8  7
 *      1  2  3  4  5  6
 *
 * 【步骤二：广度优先搜索 BFS】
 * - 将第 1 格放入队列，起始步数为 0；
 * - 每次从队列中取出当前格子编号 curr，尝试掷骰子走 1~6 步：
 *   - 计算下一步编号 next = curr + i；
 *   - 通过映射得到 next 对应坐标 (row, col)；
 *   - 如果该格子是梯子/蛇（即 board[row][col] != -1），则跳转到 destination；
 *   - 如果 destination 没被访问过，加入队列并记录最小步数；
 * - 最终 `dist[N*N]` 即为最小步数。
 *
 * ✅ 示例解释：
 * 输入：
 *   board = [
 *     [-1, -1, -1, -1, -1, -1],
 *     [-1, -1, -1, -1, -1, -1],
 *     [-1, -1, -1, -1, -1, -1],
 *     [-1, 35, -1, -1, 13, -1],
 *     [-1, -1, -1, -1, -1, -1],
 *     [-1, 15, -1, -1, -1, -1]
 *   ]
 * - 初始在 1 号格，走 6 步范围为 [2, 3, 4, 5, 6, 7]
 * - 7 是梯子跳到 15，再走 [16~21]，遇到 17 是梯子跳到 13...
 * - 最终走到 36 只需 4 步。
 *
 * 【步骤三：Dijkstra 算法（可选）】
 * - 也可以使用优先队列维护每一格的最小步数；
 * - 不断取出当前步数最少的格子并尝试扩展；
 * - 对于更复杂的图或带权边可进一步推广。
 *
 *
 * 【三、时间与空间复杂度分析】
 *
 * 方法一：BFS
 * - 时间复杂度：O(N²)
 *   - 最多访问 N² 个格子，每个格子尝试 6 个方向；
 * - 空间复杂度：O(N²)
 *   - 坐标映射数组 + 队列 + 步数数组；
 *
 * 方法二：Dijkstra（使用最小堆）
 * - 时间复杂度：O(N² * log N²) ≈ O(N² * log N)
 *   - 每个点最多入堆一次，堆操作为 logN²；
 * - 空间复杂度：O(N²)
 */


public class LeetCode_909_SnakesAndLadders{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法1：BFS遍历整张棋盘
        public int snakesAndLadders(int[][] board) {
            int n = board.length;

            // cells[i] 存储编号为 i 的格子对应的棋盘坐标 (row, col)
            Pair<Integer, Integer>[] cells = new Pair[n * n + 1];

            // 编号从 1 开始到 n*n
            int label = 1;

            // 用于控制每一行的列顺序（从左到右或右到左）
            Integer[] columns = new Integer[n];
            for (int i = 0; i < n; i++) {
                columns[i] = i;
            }

            // 从左下角开始编号，模拟蛇梯棋的编号顺序（之字形）
            for (int row = n - 1; row >= 0; row--) {
                for (int column : columns) {
                    cells[label++] = new Pair<>(row, column);
                }
                // 每一行改变列方向（之字形）
                Collections.reverse(Arrays.asList(columns));
            }
            System.out.println(cells);


            // 记录每个位置到起点的最短步数，初始化为 -1（未访问）
            int[] dist = new int[n * n + 1];
            Arrays.fill(dist, -1);

            // BFS 队列，记录当前在第几个格子
            Queue<Integer> q = new LinkedList<>();
            dist[1] = 0; // 起点步数为0
            q.add(1);

            while (!q.isEmpty()) {
                int curr = q.remove();

                // 试图掷骰子走1~6步
                for (int next = curr + 1; next <= Math.min(curr + 6, n * n); next++) {
                    // 获取下一步编号对应的坐标位置
                    int row = cells[next].getKey(), column = cells[next].getValue();

                    // 判断是否有梯子或蛇
                    int destination = board[row][column] != -1 ? board[row][column] : next;

                    // 如果这个位置没访问过，加入队列
                    if (dist[destination] == -1) {
                        dist[destination] = dist[curr] + 1;
                        q.add(destination);
                    }
                }
            }

            // 返回终点的最小步数
            return dist[n * n];
        }

        // 解法2：Dijkstra 算法（可选替代 BFS，用于处理带权图）
        public int snakesAndLadders2(int[][] board) {
            int n = board.length;

            // 初始化编号坐标映射
            Pair<Integer, Integer>[] cells = new Pair[n * n + 1];
            int label = 1;
            Integer[] columns = new Integer[n];
            for (int i = 0; i < n; i++) {
                columns[i] = i;
            }
            for (int row = n - 1; row >= 0; row--) {
                for (int column : columns) {
                    cells[label++] = new Pair<>(row, column);
                }
                Collections.reverse(Arrays.asList(columns));
            }

            // 记录最短路径距离
            int[] dist = new int[n * n + 1];
            Arrays.fill(dist, -1);

            // 定义带距离的顶点结构
            class Vertex implements Comparable<Vertex> {
                int distance, label;
                public Vertex(int distance, int label) {
                    this.distance = distance;
                    this.label = label;
                }
                public int compareTo(Vertex v) {
                    return this.distance - v.distance;
                }
            }

            // 优先队列实现 Dijkstra
            PriorityQueue<Vertex> q = new PriorityQueue<>();
            dist[1] = 0;
            q.add(new Vertex(0, 1));

            while (!q.isEmpty()) {
                Vertex vertex = q.remove();
                int d = vertex.distance, curr = vertex.label;

                // 跳过已处理节点
                if (d != dist[curr]) continue;

                // 掷骰子尝试走 1~6 步
                for (int next = curr + 1; next <= Math.min(curr + 6, n * n); next++) {
                    int row = cells[next].getKey(), column = cells[next].getValue();
                    int destination = board[row][column] != -1 ? board[row][column] : next;

                    // 如果是首次访问或找到更短路径，更新
                    if (dist[destination] == -1 || dist[curr] + 1 < dist[destination]) {
                        dist[destination] = dist[curr] + 1;
                        q.add(new Vertex(dist[destination], destination));
                    }
                }
            }

            // 返回终点步数
            return dist[n * n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_909_SnakesAndLadders().new Solution();

        // 示例 1：标准蛇梯棋 6x6
        int[][] board1 = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        System.out.println("最小步数（BFS）: " + solution.snakesAndLadders(board1));   // 预期输出 4
        System.out.println("最小步数（Dijkstra）: " + solution.snakesAndLadders2(board1)); // 预期输出 4

        // 示例 2：无蛇无梯
        int[][] board2 = {
                {-1, -1, -1},
                {-1, -1, -1},
                {-1, -1, -1}
        };
        System.out.println("无梯子或蛇，最小步数: " + solution.snakesAndLadders(board2)); // 预期输出 2
    }
}

/**
You are given an n x n integer matrix board where the cells are labeled from 1 
to n² in a Boustrophedon style starting from the bottom left of the board (i.e. 
board[n - 1][0]) and alternating direction each row. 

 You start on square 1 of the board. In each move, starting from square curr, 
do the following: 

 
 Choose a destination square next with a label in the range [curr + 1, min(curr 
+ 6, n²)]. 
 

 
 This choice simulates the result of a standard 6-sided die roll: i.e., there 
are always at most 6 destinations, regardless of the size of the board. 
 
 
 If next has a snake or ladder, you must move to the destination of that snake 
or ladder. Otherwise, you move to next. 
 The game ends when you reach the square n². 


 A board square on row r and column c has a snake or ladder if board[r][c] != -1
. The destination of that snake or ladder is board[r][c]. Squares 1 and n² are 
not the starting points of any snake or ladder. 

 Note that you only take a snake or ladder at most once per dice roll. If the 
destination to a snake or ladder is the start of another snake or ladder, you do 
not follow the subsequent snake or ladder. 

 
 For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your 
destination square is 2. You follow the ladder to square 3, but do not follow 
the subsequent ladder to 4. 
 

 Return the least number of dice rolls required to reach the square n². If it 
is not possible to reach the square, return -1. 

 
 Example 1: 
 
 
Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,
35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
Output: 4
Explanation: 
In the beginning, you start at square 1 (at row 5, column 0).
You decide to move to square 2 and must take the ladder to square 15.
You then decide to move to square 17 and must take the snake to square 13.
You then decide to move to square 14 and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
This is the lowest possible number of moves to reach the last square, so return 
4.
 

 Example 2: 

 
Input: board = [[-1,-1],[-1,3]]
Output: 1
 

 
 Constraints: 

 
 n == board.length == board[i].length 
 2 <= n <= 20 
 board[i][j] is either -1 or in the range [1, n²]. 
 The squares labeled 1 and n² are not the starting points of any snake or 
ladder. 
 

 Related Topics Array Breadth-First Search Matrix 👍 3079 👎 1154

*/
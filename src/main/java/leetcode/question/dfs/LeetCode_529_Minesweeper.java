package leetcode.question.dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *@Question:  529. Minesweeper
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 69.38%
 *@Time Complexity: O(m * n)  // 最坏情况下需要遍历整个棋盘
 *@Space Complexity: O(m * n)  // BFS 可能需要存储整个棋盘的状态
 */
/*
 * 529. Minesweeper（扫雷游戏）
 *
 * =====================
 * 题目描述：
 * =====================
 * 给定一个 `m x n` 的二维字符网格 `board` 代表一个扫雷游戏的棋盘：
 * - `'M'` 代表**未被挖出的地雷（Mine）**
 * - `'E'` 代表**未被挖出的空单元格（Empty）**
 * - 数字 `1-8` 代表**该单元格周围的地雷数量**
 * - `'B'` 代表**无地雷的安全区域**
 *
 * 玩家点击棋盘上的一个单元格 `click = [row, col]` 后，游戏有以下两种情况：
 * 1. **点击到了地雷（'M'），游戏结束**：
 *    - 将该单元格改为 `'X'`，表示游戏结束。
 * 2. **点击到了空单元格（'E'）**：
 *    - 计算其**周围 8 个方向的地雷数量**：
 *      - **如果有地雷**，用数字 `'1' - '8'` 代表地雷数。
 *      - **如果无地雷**，标记为 `'B'`，并继续**向外扩展**（即递归或 BFS）。
 *
 * 示例 1：
 * 输入：
 * board =
 * [['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'M', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E']]
 * click = [1,2]
 *
 * 输出：
 * [['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'X', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E']]
 *
 * =====================
 * 超详细解题思路：
 * =====================
 * 方案1：深度优先搜索（DFS）
 * -------------------------------------
 * 1️⃣ **检查点击的单元格**
 *    - 如果 `board[click[0]][click[1]] == 'M'`，表示玩家点击到了地雷，直接变为 `'X'` 并返回。
 *    - 否则，说明玩家点击了空单元格 `'E'`，需要进行扩展搜索。
 *
 * 2️⃣ **递归 DFS 搜索**
 *    - **终止条件**：
 *      - 如果坐标超出边界，或者该位置不是 `'E'`（已被访问），直接返回。
 *    - **计算周围地雷数 `num`**：
 *      - 遍历 8 个方向，统计 `'M'` 的数量。
 *    - **情况1：num > 0**：
 *      - 说明当前单元格周围有地雷，标记为数字 `num`，停止扩展。
 *    - **情况2：num == 0**：
 *      - 说明周围没有地雷，将其标记为 `'B'` 并继续递归访问 8 个方向的邻居。
 *
 * 3️⃣ **示例解析**
 *    **输入**：
 *    ```
 *    board =
 *    [['E', 'E', 'E', 'E', 'E'],
 *     ['E', 'E', 'M', 'E', 'E'],
 *     ['E', 'E', 'E', 'E', 'E'],
 *     ['E', 'E', 'E', 'E', 'E']]
 *    click = [0, 0]
 *    ```
 *    **步骤**：
 *    - 递归访问 `(0,0)`，计算周围地雷数：
 *      - `(1,1)` 方向有一个 `'M'`（地雷数 = 1）。
 *    - 结果：
 *      ```
 *      [['1', 'E', 'E', 'E', 'E'],
 *       ['E', 'E', 'M', 'E', 'E'],
 *       ['E', 'E', 'E', 'E', 'E'],
 *       ['E', 'E', 'E', 'E', 'E']]
 *      ```
 *
 * =====================
 * 方案2：广度优先搜索（BFS）
 * -------------------------------------
 * 1️⃣ **使用队列存储需要访问的单元格**
 *    - **初始化队列**：将点击的坐标 `click` 入队。
 *    - **方向数组 `dir`**：定义 8 个方向的坐标偏移 `[上, 下, 左, 右, 左上, 右下, 右上, 左下]`。
 *    - **处理队列中的单元格**：
 *      1. 取出队列头部的坐标 `(row, col)`。
 *      2. **计算周围 8 个方向的地雷数量**：
 *         - 如果地雷数 `count > 0`，将 `board[row][col]` 设置为 `'count'`，停止扩展。
 *         - 否则，标记为 `'B'`（安全区域），并继续向外扩展：
 *            - 遍历 8 个方向，将 **未被点击的 `'E'`** 加入队列，并标记为 `'B'` 避免重复搜索。
 *
 * 2️⃣ **示例解析**
 *    **输入**：
 *    ```
 *    board =
 *    [['E', 'E', 'E', 'E', 'E'],
 *     ['E', 'E', 'M', 'E', 'E'],
 *     ['E', 'E', 'E', 'E', 'E'],
 *     ['E', 'E', 'E', 'E', 'E']]
 *    click = [0, 0]
 *    ```
 *    **步骤**：
 *    - BFS 访问 `(0,0)`，计算周围地雷数：
 *      - `(1,1)` 方向有一个 `'M'`（地雷数 = 1）。
 *    - 结果：
 *      ```
 *      [['1', 'E', 'E', 'E', 'E'],
 *       ['E', 'E', 'M', 'E', 'E'],
 *       ['E', 'E', 'E', 'E', 'E'],
 *       ['E', 'E', 'E', 'E', 'E']]
 *      ```
 *
 * =====================
 * 时间 & 空间复杂度分析：
 * =====================
 * **方案1：DFS**
 * - 时间复杂度 O(m * n)：
 *   - 在最坏情况下，整个棋盘都是 `'E'`，需要完全展开。
 * - 空间复杂度 O(m * n)：
 *   - 递归的深度最坏情况下等于棋盘大小，递归栈的存储空间为 `O(m * n)`。
 *
 * **方案2：BFS**
 * - 时间复杂度 O(m * n)：
 *   - 在最坏情况下，所有单元格都被加入队列并访问一次。
 * - 空间复杂度 O(m * n)：
 *   - 需要存储 BFS 队列中的所有节点，最坏情况下，队列大小为 `O(m * n)`。
 *
 * =====================
 * 结论：
 * - **如果棋盘较小，DFS 方法更直观**。
 * - **如果棋盘较大，BFS 更稳定，避免递归栈溢出**。
 */public class LeetCode_529_Minesweeper{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // solution1: 使用深度优先搜索（DFS）来更新棋盘
        public char[][] updateBoard(char[][] board, int[] click) {
            int x = click[0], y = click[1];

            // 如果点击的位置是地雷 'M'，则修改为 'X' 表示游戏结束
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
                return board;
            }

            // 否则，使用 DFS 进行递归扩展
            dfs(board, x, y);
            return board;
        }

        // 方向数组：用于表示 8 个相邻单元格的方向（上、下、左、右、左上、右下、右上、左下）
        int[] dx = {-1, 0, 1, -1, 1, 0, 1, -1};
        int[] dy = {-1, 1, 1, 0, -1, -1, 0, 1};

        // 深度优先搜索（DFS）用于扩展点击的区域
        private void dfs(char[][] board, int x, int y) {
            // 如果当前坐标超出边界或当前单元格不是 'E'，则直接返回
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E')  return;

            // 计算当前位置周围的地雷数量
            int num = getNumsOfBombs(board, x, y);

            // 如果周围有地雷，则将该单元格标记为地雷数量，不继续递归
            if (num > 0) {
                board[x][y] = (char)('0' + num);
            } else {
                // 否则，将该单元格标记为 'B'，表示无地雷的安全区域
                board[x][y] = 'B';

                // 继续递归遍历 8 个方向的相邻单元格
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    dfs(board, nx, ny);
                }
            }
        }

        // 计算当前位置周围的地雷数量
        private int getNumsOfBombs(char[][] board, int x, int y) {
            int num = 0;

            // 遍历 8 个方向
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                // 如果超出边界，则跳过
                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) continue;

                // 如果相邻单元格是地雷 'M' 或已经爆炸的 'X'，则地雷数 +1
                if (board[nx][ny] == 'M' || board[nx][ny] == 'X') {
                    num++;
                }
            }
            return num;
        }

        // solution2: 使用广度优先搜索（BFS）来更新棋盘
        public char[][] updateBoard2(char[][] board, int[] click) {
            // 获取棋盘的行数和列数
            int m = board.length , n = board[0].length;

            // 方向数组：定义 8 个方向的坐标偏移量（上、下、左、右、左上、右下、右上、左下）
            int[][] dir = new int[][] {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,1},{-1,1},{1,-1}};

            // 使用队列进行广度优先搜索（BFS）
            Queue<int[]> q = new LinkedList<>();
            q.add(click);

            // 如果点击的地方是地雷 'M'，直接标记为 'X' 并返回最终的棋盘
            if(board[click[0]][click[1]] == 'M'){
                board[click[0]][click[1]] = 'X';
                return board;
            }

            // 开始 BFS 遍历
            while(!q.isEmpty()){
                // 取出当前要处理的单元格
                int[] curr = q.poll();
                int row = curr[0], col = curr[1] , count = 0 ;

                // 遍历 8 个方向，计算当前单元格周围地雷的数量
                for(int[] d : dir){
                    int r = row + d[0], c = col + d[1];

                    // 如果坐标超出边界，则跳过
                    if(r >= m || c >= n || r < 0 || c < 0) continue;

                    // 如果周围有地雷 'M'，计数 +1
                    if(board[r][c] == 'M') count++;
                }

                // 如果周围有地雷，则标记当前单元格为地雷数量
                if(count > 0)
                    board[row][col] = (char)(count + '0');
                else{
                    // 否则，标记为 'B'（表示该区域无地雷），继续扩展搜索
                    board[row][col] = 'B';

                    // 继续检查 8 个方向的邻居
                    for(int[] d : dir){
                        int r = row + d[0] , c = col + d[1];

                        // 如果坐标超出边界，则跳过
                        if(r >= m || c >= n || r < 0 || c < 0) continue;

                        // 如果是未被点击的空单元格 'E'，加入队列，并标记为 'B'
                        if(board[r][c] == 'E'){
                            q.add(new int[] {r,c});
                            board[r][c] = 'B';
                        }
                    }
                }
            }

            // 返回最终更新后的棋盘
            return board;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_529_Minesweeper().new Solution();

        // 测试样例 1：基本情况（点击地雷）
        char[][] board1 = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        int[] click1 = {1, 2};  // 点击地雷
        System.out.println("测试样例 1（点击地雷）：");
        printBoard(solution.updateBoard(board1, click1));

        // 测试样例 2：点击空白区域
        char[][] board2 = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        int[] click2 = {0, 0};  // 点击空白区域
        System.out.println("测试样例 2（点击空白）：");
        printBoard(solution.updateBoard(board2, click2));
    }

    // 辅助方法：打印棋盘
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}


/**
Let's play the minesweeper game (Wikipedia, online game)! 

 You are given an m x n char matrix board representing the game board where: 

 
 'M' represents an unrevealed mine, 
 'E' represents an unrevealed empty square, 
 'B' represents a revealed blank square that has no adjacent mines (i.e., above,
 below, left, right, and all 4 diagonals), 
 digit ('1' to '8') represents how many mines are adjacent to this revealed 
square, and 
 'X' represents a revealed mine. 
 

 You are also given an integer array click where click = [clickr, clickc] 
represents the next click position among all the unrevealed squares ('M' or 'E'). 

 Return the board after revealing this position according to the following 
rules: 

 
 If a mine 'M' is revealed, then the game is over. You should change it to 'X'. 

 If an empty square 'E' with no adjacent mines is revealed, then change it to a 
revealed blank 'B' and all of its adjacent unrevealed squares should be 
revealed recursively. 
 If an empty square 'E' with at least one adjacent mine is revealed, then 
change it to a digit ('1' to '8') representing the number of adjacent mines. 
 Return the board when no more squares will be revealed. 
 

 
 Example 1: 
 
 
Input: board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E",
"E"],["E","E","E","E","E"]], click = [3,0]
Output: [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B",
"B","B","B","B"]]
 

 Example 2: 
 
 
Input: board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1",
"B"],["B","B","B","B","B"]], click = [1,2]
Output: [["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B",
"B","B","B","B"]]
 

 
 Constraints: 

 
 m == board.length 
 n == board[i].length 
 1 <= m, n <= 50 
 board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'. 
 click.length == 2 
 0 <= clickr < m 
 0 <= clickc < n 
 board[clickr][clickc] is either 'M' or 'E'. 
 

 Related Topics Array Depth-First Search Breadth-First Search Matrix 👍 2020 👎 
1078

*/
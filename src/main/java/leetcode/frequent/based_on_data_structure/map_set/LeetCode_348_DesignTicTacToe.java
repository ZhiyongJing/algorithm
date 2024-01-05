package leetcode.frequent.based_on_data_structure.map_set;

/**
 *@Question:  348. Design Tic-Tac-Toe
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 61.78%
 *@Time  Complexity: O(1) for each move
 *@Space Complexity: O(N) where N is the size of the board
 */

/**
 * ### 解题思路
 *
 * 这是一个设计井字棋游戏的问题，主要思路是通过维护几个数组来记录每个玩家在行、列、对角线和反对角线上的落子情况，
 * 然后在每次落子后检查是否有玩家胜利。
 *
 * - 使用 `rows` 数组记录每行的总和，`cols` 数组记录每列的总和，`diagonal` 记录主对角线的总和，
 * `antiDiagonal` 记录副对角线的总和。
 * - 在每次玩家落子时，更新相应的数组，并判断是否有玩家胜利。
 *
 * ### 代码解释
 *
 * - `TicTacToe` 类的构造函数初始化了 `rows` 和 `cols` 数组，表示井字棋盘的行和列。
 * - `move` 方法接收玩家的行、列以及玩家编号，返回当前胜利的玩家编号或0（表示没有玩家胜利）。
 *
 * ### 复杂度分析
 *
 * - 时间复杂度：每次落子的操作都是 O(1) 的时间复杂度，因此整体的时间复杂度也是 O(1)。
 * - 空间复杂度：需要额外的空间来存储 `rows`、`cols`、`diagonal`、`antiDiagonal` 数组，
 * 它们的长度为 n，因此空间复杂度为 O(n)。这里 n 表示棋盘的大小。
 */

public class LeetCode_348_DesignTicTacToe {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class TicTacToe {
        int[] rows;
        int[] cols;
        int diagonal;
        int antiDiagonal;

        // 构造函数，初始化棋盘的行和列数组
        public TicTacToe(int n) {
            rows = new int[n];
            cols = new int[n];
        }

        /**
         * 在棋盘上落子，并判断当前玩家是否胜利
         *
         * @param row     落子的行
         * @param col     落子的列
         * @param player  当前玩家，1表示玩家1，-1表示玩家2
         * @return 胜利的玩家，1或-1；如果没有胜利则返回0
         */
        public int move(int row, int col, int player) {
            int currentPlayer = (player == 1) ? 1 : -1;

            // 更新当前玩家在行和列数组中的值
            rows[row] += currentPlayer;
            cols[col] += currentPlayer;

            // 更新对角线的值
            if (row == col) {
                diagonal += currentPlayer;
            }

            // 更新反对角线的值
            if (col == (cols.length - row - 1)) {
                antiDiagonal += currentPlayer;
            }

            int n = rows.length;

            // 检查当前玩家是否胜利
            if (Math.abs(rows[row]) == n ||
                    Math.abs(cols[col]) == n ||
                    Math.abs(diagonal) == n ||
                    Math.abs(antiDiagonal) == n) {
                return player;
            }

            // 没有玩家胜利
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 创建一个3x3的棋盘
        TicTacToe ticTacToe = new LeetCode_348_DesignTicTacToe().new TicTacToe(3);

        // 玩家1在第0行第0列落子
        System.out.println("Move Result: " + ticTacToe.move(0, 0, 1)); // 预期输出: 0，没有胜利者

        // 玩家2在第0行第2列落子
        System.out.println("Move Result: " + ticTacToe.move(0, 2, -1)); // 预期输出: 0，没有胜利者

        // 玩家1在第2行第2列落子
        System.out.println("Move Result: " + ticTacToe.move(2, 2, 1)); // 预期输出: 0，没有胜利者

        // 玩家2在第1行第1列落子
        System.out.println("Move Result: " + ticTacToe.move(1, 1, -1)); // 预期输出: 0，没有胜利者

        // 玩家1在第2行第0列落子
        System.out.println("Move Result: " + ticTacToe.move(2, 0, 1)); // 预期输出: 0，没有胜利者

        // 玩家2在第1行第0列落子
        System.out.println("Move Result: " + ticTacToe.move(1, 0, -1)); // 预期输出: 0，没有胜利者

        // 玩家1在第2行第1列落子，此时玩家1胜利
        System.out.println("Move Result: " + ticTacToe.move(2, 1, 1)); // 预期输出: 1，玩家1胜利
    }
}

/**
Assume the following rules are for the tic-tac-toe game on an n x n board 
between two players: 

 
 A move is guaranteed to be valid and is placed on an empty block. 
 Once a winning condition is reached, no more moves are allowed. 
 A player who succeeds in placing n of their marks in a horizontal, vertical, 
or diagonal row wins the game. 
 

 Implement the TicTacToe class: 

 
 TicTacToe(int n) Initializes the object the size of the board n. 
 int move(int row, int col, int player) Indicates that the player with id 
player plays at the cell (row, col) of the board. The move is guaranteed to be a 
valid move, and the two players alternate in making moves. Return 
 
 0 if there is no winner after the move, 
 1 if player 1 is the winner after the move, or 
 2 if player 2 is the winner after the move. 
 
 

 
 Example 1: 

 
Input
["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
[[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1
]]
Output
[null, 0, 0, 0, 0, 0, 0, 1]

Explanation
TicTacToe ticTacToe = new TicTacToe(3);
Assume that player 1 is "X" and player 2 is "O" in the board.
ticTacToe.move(0, 0, 1); // return 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

ticTacToe.move(0, 2, 2); // return 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

ticTacToe.move(2, 2, 1); // return 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

ticTacToe.move(1, 1, 2); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

ticTacToe.move(2, 0, 1); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

ticTacToe.move(1, 0, 2); // return 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
 

 
 Constraints: 

 
 2 <= n <= 100 
 player is 1 or 2. 
 0 <= row, col < n 
 (row, col) are unique for each different call to move. 
 At most n² calls will be made to move. 
 

 
 Follow-up: Could you do better than O(n²) per move() operation? 

 Related Topics Array Hash Table Design Matrix Simulation 👍 2019 👎 115

*/
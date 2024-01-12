package leetcode.question.dfs;

/**
  *@Question:  37. Sudoku Solver     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 68.64%      
  *@Time  Complexity: O((9!)^9)
  *@Space Complexity: O(1)
 */

/**
 * **算法思路：**
 *
 * 该算法使用回溯法解决数独问题。回溯法是一种深度优先搜索的方法，在搜索过程中不断尝试各种可能的选择，如果发现某个选择不能得到正确的解，就会回溯到之前的状态，尝试其他的选择。
 *
 * 具体步骤如下：
 *
 * 1. **初始化：** 创建三个二维数组 `rows`、`columns` 和 `boxes`，用于记录每行、每列和每个 3x3 的方块中数字的出现次数。
 * 同时，遍历数独棋盘，将已有的数字在相应的行、列和方块中的出现次数记录下来。
 *
 * 2. **递归回溯：** 从数独的左上角开始，递归调用 `backtrack` 函数，尝试在每个空白格中填入数字。对于每个空白格，
 * 从 1 到 9 依次尝试，检查是否符合数独的规则（同一行、同一列、同一个 3x3 方块中不能出现相同的数字）。
 * 如果符合规则，就尝试放置该数字，并继续递归调用 `backtrack`。
 *
 * 3. **终止条件：** 如果已经到达数独的最后一个单元格，表示数独已解决，将 `sudokuSolved` 设为 true。
 *
 * 4. **回溯：** 如果在某个位置无法放置数字，或者递归调用 `backtrack` 后发现数独已解决，就回溯到之前的状态。
 * 这时需要将当前位置的数字移除，以及相应的行、列和方块的数字出现次数减一。
 *
 * 5. **最终解：** 最终通过上述过程，找到数独的解或者确定无解。
 *
 * **时间复杂度：**
 *
 * 数独问题的时间复杂度取决于尝试填入每个空白格的数字的次数。对于每个空白格，有 9 种选择（数字 1 到 9），
 * 因此总的时间复杂度是 O((9!)^9)，其中 9! 表示数字 1 到 9 的全排列。
 *
 * **空间复杂度：**
 *
 * 空间复杂度是 O(1)，因为使用了常数大小的额外空间用于记录每行、每列和每个 3x3 方块中数字的出现次数，以及一些递归调用的栈空间。
 */

public class LeetCode_37_SudokuSolver{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 在每个 3x3 的方块中，有 9 个数字（1-9）
    // 行数（row size）和列数（column size）
    int n = 3;
    int N = n * n;

    // 存储数独中每一行、每一列和每一个 3x3 方块中数字的出现次数
    int[][] rows = new int[N][N + 1];
    int[][] columns = new int[N][N + 1];
    int[][] boxes = new int[N][N + 1];

    // 数独棋盘
    char[][] board;

    // 标志数独是否已解决
    boolean sudokuSolved = false;

    // 检查是否可以放置数字 d 到指定的位置 (row, col)
    public boolean couldPlace(int d, int row, int col) {
        int idx = (row / n) * n + col / n; // 计算方块的索引
        // 检查在行、列和方块中是否已经有数字 d
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    // 将数字 d 放置到指定的位置 (row, col) 中
    public void placeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n; // 计算方块的索引
        // 增加行、列和方块中数字 d 的出现次数
        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        // 在数独棋盘中放置数字 d
        board[row][col] = (char) (d + '0');
    }

    // 移除数字 d 从指定的位置 (row, col) 中
    public void removeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n; // 计算方块的索引
        // 减少行、列和方块中数字 d 的出现次数
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        // 将数字 d 从数独棋盘中移除
        board[row][col] = '.';
    }

    // 递归调用，继续尝试放置下一个位置的数字，直到找到数独的解或者尝试完所有可能的数字
    public void placeNextNumbers(int row, int col) {
        // 如果已经到达数独的最后一个单元格，表示数独已解决
        if ((col == N - 1) && (row == N - 1)) {
            sudokuSolved = true;
        } else {
            // 如果在行的末尾，转到下一行的第一个位置
            if (col == N - 1) {
                backtrack(row + 1, 0);
            }
            // 否则，转到下一列
            else {
                backtrack(row, col + 1);
            }
        }
    }

    // 回溯算法的主体函数
    public void backtrack(int row, int col) {
        // 如果当前单元格是空白格
        if (board[row][col] == '.') {
            // 尝试将数字 1 到 9 放置到当前位置
            for (int d = 1; d < 10; d++) {
                if (couldPlace(d, row, col)) {
                    // 如果可以放置数字，放置并继续递归
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    // 如果数独已解决，无需回溯，因为有唯一解
                    if (!sudokuSolved) {
                        removeNumber(d, row, col);
                    }
                }
            }
        } else {
            // 如果当前位置已有数字，直接继续下一步
            placeNextNumbers(row, col);
        }
    }

    // 解数独的主函数
    public void solveSudoku(char[][] board) {
        this.board = board;

        // 初始化行、列和方块中的数字出现次数
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }

        // 从数独的第一个位置开始回溯求解
        backtrack(0, 0);
    }

}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_37_SudokuSolver().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Write a program to solve a Sudoku puzzle by filling the empty cells. 

 A sudoku solution must satisfy all of the following rules: 

 
 Each of the digits 1-9 must occur exactly once in each row. 
 Each of the digits 1-9 must occur exactly once in each column. 
 Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes 
of the grid. 
 

 The '.' character indicates empty cells. 

 
 Example 1: 
 
 
Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",
".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",
".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".",
"6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],
[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4",
"8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"]
,["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9
","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4
","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is 
shown below:
 



 
 Constraints: 

 
 board.length == 9 
 board[i].length == 9 
 board[i][j] is a digit or '.'. 
 It is guaranteed that the input board has only one solution. 
 

 Related Topics Array Hash Table Backtracking Matrix 👍 9048 👎 231

*/

package leetcode.question.two_pointer;

/**
 *@Question:  723. Candy Crush
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 67.89%
 *@Time Complexity: O(M^2 * N^2)
 *@Space Complexity: O(1)
 */
/**
 * @题目描述:
 * 723. Candy Crush
 * 给定一个二维数组 `board`，表示一个棋盘，其中每个格子包含一个正整数，代表不同种类的糖果。
 * 如果至少有三个相同的糖果在水平或垂直方向相邻，这些糖果会被消除，消除后上方的糖果会下落填补空格。
 * 重复此过程，直到没有可以消除的糖果为止，最终返回棋盘的状态。
 *
 * @解题思路:
 * 1. 定义两个主要操作：
 *    - **findAndCrush**: 找到可以被消除的糖果并将其标记为即将消除的状态。
 *    - **drop**: 将糖果下落到空格中。
 *
 * 2. 主循环:
 *    - 不断调用 `findAndCrush` 和 `drop`，直到 `findAndCrush` 返回棋盘已经稳定（没有可消除的糖果）。
 *
 * 3. 详细步骤：
 *    - **步骤 1: 检查垂直方向的糖果**：
 *      遍历棋盘每个格子，检查当前糖果是否与上下两个糖果相同。
 *      如果是，将它们标记为负数（表示即将被消除）。
 *      示例：
 *      输入：
 *      3 3 3
 *      4 4 5
 *      输出：
 *      -3 -3 -3
 *      4  4  5
 *
 *    - **步骤 2: 检查水平方向的糖果**：
 *      遍历棋盘每个格子，检查当前糖果是否与左右两个糖果相同。
 *      如果是，将它们标记为负数。
 *      示例：
 *      输入：
 *      3 3 3
 *      4 5 6
 *      输出：
 *      -3 -3 -3
 *      4  5  6
 *
 *    - **步骤 3: 消除糖果**：
 *      将所有标记为负数的格子设置为 0（空格）。
 *      示例：
 *      输入：
 *      -3 -3 -3
 *      4   5   6
 *      输出：
 *      0  0  0
 *      4  5  6
 *
 *    - **步骤 4: 下落糖果**：
 *      从下往上遍历每一列，将非零糖果移动到最底部。
 *      示例：
 *      输入：
 *      0  0  0
 *      4  5  6
 *      输出：
 *      4  5  6
 *      0  0  0
 *
 * 4. 主函数：
 *    - 初始化棋盘大小。
 *    - 使用循环调用 `findAndCrush` 和 `drop`，直到棋盘稳定。
 *    - 返回最终棋盘状态。
 *
 * @时间复杂度:
 * - 每次操作中，`findAndCrush` 和 `drop` 都需要遍历整个棋盘。
 * - 在最坏情况下，需要对棋盘进行多次循环操作。
 * - 时间复杂度为 O(M^2 * N^2)，其中 M 为棋盘行数，N 为棋盘列数。
 *
 * @空间复杂度:
 * - 使用常数空间存储变量，因此空间复杂度为 O(1)。
 */


public class LeetCode_723_CandyCrush {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int m, n; // 定义全局变量 m 和 n，分别表示棋盘的行数和列数

        // 找到可以消除的糖果并执行消除操作
        boolean findAndCrush(int[][] board) {
            boolean complete = true; // 标记当前棋盘是否已经完成，没有更多可以消除的糖果

            // 检查垂直方向的相邻糖果
            for (int r = 1; r < m - 1; r++) { // 遍历每一行（从第 2 行到倒数第 2 行）
                for (int c = 0; c < n; c++) { // 遍历每一列
                    if (board[r][c] == 0) { // 如果当前格子为空，跳过
                        continue;
                    }
                    // 检查当前糖果是否与上下两个糖果相同
                    if (Math.abs(board[r][c]) == Math.abs(board[r - 1][c]) && Math.abs(board[r][c]) == Math.abs(board[r + 1][c])) {
                        board[r][c] = -Math.abs(board[r][c]); // 标记当前糖果为即将消除的状态
                        board[r - 1][c] = -Math.abs(board[r - 1][c]); // 标记上方糖果为即将消除的状态
                        board[r + 1][c] = -Math.abs(board[r + 1][c]); // 标记下方糖果为即将消除的状态
                        complete = false; // 更新标记，表示棋盘仍有糖果可以消除
                    }
                }
            }

            // 检查水平方向的相邻糖果
            for (int r = 0; r < m; r++) { // 遍历每一行
                for (int c = 1; c < n - 1; c++) { // 遍历每一列（从第 2 列到倒数第 2 列）
                    if (board[r][c] == 0) { // 如果当前格子为空，跳过
                        continue;
                    }
                    // 检查当前糖果是否与左右两个糖果相同
                    if (Math.abs(board[r][c]) == Math.abs(board[r][c - 1]) && Math.abs(board[r][c]) == Math.abs(board[r][c + 1])) {
                        board[r][c] = -Math.abs(board[r][c]); // 标记当前糖果为即将消除的状态
                        board[r][c - 1] = -Math.abs(board[r][c - 1]); // 标记左侧糖果为即将消除的状态
                        board[r][c + 1] = -Math.abs(board[r][c + 1]); // 标记右侧糖果为即将消除的状态
                        complete = false; // 更新标记，表示棋盘仍有糖果可以消除
                    }
                }
            }

            // 将所有标记为即将消除的糖果设置为 0（表示消除）
            for (int r = 0; r < m; r++) { // 遍历每一行
                for (int c = 0; c < n; c++) { // 遍历每一列
                    if (board[r][c] < 0) { // 如果糖果为负数，表示即将消除
                        board[r][c] = 0; // 将糖果设置为 0
                    }
                }
            }

            return complete; // 返回棋盘是否已经完成
        }

        // 将糖果下落到空格
        void drop(int[][] board) {
            for (int c = 0; c < n; c++) { // 遍历每一列
                int lowestZero = -1; // 记录当前列中最低的空格位置

                // 从下往上遍历每一行
                for (int r = m - 1; r >= 0; r--) {
                    if (board[r][c] == 0) { // 如果当前格子为空
                        lowestZero = Math.max(lowestZero, r); // 更新最低空格的位置
                    } else if (lowestZero >= 0) { // 如果当前格子非空，且存在空格
                        // 交换当前糖果与最低空格的位置
                        int temp = board[r][c];
                        board[r][c] = board[lowestZero][c];
                        board[lowestZero][c] = temp;
                        lowestZero--; // 更新最低空格位置
                    }
                }
            }
        }

        // 主函数，执行消除糖果的逻辑
        public int[][] candyCrush(int[][] board) {
            m = board.length; // 获取棋盘的行数
            n = board[0].length; // 获取棋盘的列数

            // 重复执行找到消除和下落的操作，直到没有糖果可以消除
            while (!findAndCrush(board)) {
                drop(board); // 执行糖果下落
            }

            return board; // 返回最终的棋盘状态
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_723_CandyCrush().new Solution();

        // 测试样例 1
        int[][] board1 = {
                {110, 5, 112, 113, 114},
                {210, 211, 5, 213, 214},
                {310, 311, 3, 313, 314},
                {410, 411, 412, 5, 414},
                {5, 1, 512, 3, 3},
                {610, 4, 1, 613, 614},
                {710, 1, 2, 713, 714},
                {810, 1, 2, 1, 1},
                {1, 1, 2, 2, 2},
                {4, 1, 4, 4, 1014}
        };
        int[][] result1 = solution.candyCrush(board1);
        for (int[] row : result1) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
}

/**
This question is about implementing a basic elimination algorithm for Candy 
Crush. 

 Given an m x n integer array board representing the grid of candy where board[
i][j] represents the type of candy. A value of board[i][j] == 0 represents that 
the cell is empty. 

 The given board represents the state of the game following the player's move. 
Now, you need to restore the board to a stable state by crushing candies 
according to the following rules: 

 
 If three or more candies of the same type are adjacent vertically or 
horizontally, crush them all at the same time - these positions become empty. 
 After crushing all candies simultaneously, if an empty space on the board has 
candies on top of itself, then these candies will drop until they hit a candy or 
bottom at the same time. No new candies will drop outside the top boundary. 
 After the above steps, there may exist more candies that can be crushed. If so,
 you need to repeat the above steps. 
 If there does not exist more candies that can be crushed (i.e., the board is 
stable), then return the current board. 
 

 You need to perform the above rules until the board becomes stable, then 
return the stable board. 

 
 Example 1: 
 
 
Input: board = [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410
,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],
[1,1,2,2,2],[4,1,4,4,1014]]
Output: [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[31
0,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[81
0,411,512,713,1014]]
 

 Example 2: 

 
Input: board = [[1,3,5,5,2],[3,4,3,3,1],[3,2,4,5,2],[2,4,4,5,5],[1,4,4,1,1]]
Output: [[1,3,0,0,0],[3,4,0,5,2],[3,2,0,3,1],[2,4,0,5,2],[1,4,3,1,1]]
 

 
 Constraints: 

 
 m == board.length 
 n == board[i].length 
 3 <= m, n <= 50 
 1 <= board[i][j] <= 2000 
 

 Related Topics Array Two Pointers Matrix Simulation 👍 1025 👎 530

*/
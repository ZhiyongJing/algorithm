package leetcode.question.dfs;
/**
 *@Question:  419. Battleships in a Board
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 54.18999999999999%
 *@Time  Complexity: O(M * N)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目描述
 *
 * **题目编号：419. Battleships in a Board**
 *
 * **问题描述：**
 *
 * 在一个二维棋盘上，有一些战舰（标记为 `'X'`）和水域（标记为 `'.'`）。战舰可以是水平或垂直放置的。你的任务是计算棋盘上战舰的数量。一个战舰是一个由 `'X'` 组成的区域，该区域可以横向或纵向扩展，但不会交叉或形成其他形状。
 *
 * **示例：**
 *
 * - **输入：**
 *   ```
 *   X . . X
 *   . . . X
 *   . . . X
 *   ```
 * - **输出：** `2`
 * - **解释：** 棋盘上有两个战舰，一个水平战舰和一个垂直战舰。
 *
 * ### 解题思路
 *
 * 1. **基本概念：**
 *
 *    - 战舰是由连续的 `'X'` 组成的行或列的块，且每个战舰只有一个起始点。
 *    - 一个战舰的起始点是在该战舰的左边和上边的水域（即没有其他战舰的部分）旁边的位置。
 *
 * 2. **遍历棋盘：**
 *
 *    - 遍历棋盘的每个位置 `(i, j)`。
 *    - 对于每个位置，如果该位置是 `'X'`（战舰的一部分），需要判断这个位置是否是一个新的战舰的起始点。
 *
 * 3. **判断战舰的起始点：**
 *
 *    - **上方检查**：如果当前位置上方的 `(i-1, j)` 位置是 `'X'`，说明当前位置已经在一个垂直战舰的一部分中，不是一个新的战舰。
 *    - **左侧检查**：如果当前位置左侧的 `(i, j-1)` 位置是 `'X'`，说明当前位置已经在一个水平战舰的一部分中，不是一个新的战舰。
 *    - 如果当前位置没有被标记为战舰的一部分（即上方和左侧都不是 `'X'`），则当前位置是一个新的战舰的起始点。
 *
 * 4. **战舰计数：**
 *
 *    - 每当发现一个新的战舰的起始点时，增加战舰的计数。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**：`O(M * N)`
 *   遍历棋盘的每个位置，因此时间复杂度为棋盘的行数和列数的乘积，即 `M * N`。
 *
 * - **空间复杂度**：`O(1)`
 *   只使用了常量空间来存储计数变量，不依赖于输入规模。棋盘是直接操作的，没有使用额外的存储空间。
 */

public class LeetCode_419_BattleshipsInABoard{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 计算棋盘上战舰的数量
        public int countBattleships(char[][] board) {
            int m = board.length;  // 获取棋盘的行数
            if (m == 0) return 0;  // 如果棋盘没有行，则返回 0
            int n = board[0].length;  // 获取棋盘的列数

            int count = 0;  // 记录战舰的数量

            // 遍历棋盘的每个位置
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 如果当前位置是空白，则跳过
                    if (board[i][j] == '.') continue;
                    // 如果当前位置上方已经有战舰部分，则跳过
                    if (i > 0 && board[i - 1][j] == 'X') continue;
                    // 如果当前位置左侧已经有战舰部分，则跳过
                    if (j > 0 && board[i][j - 1] == 'X') continue;
                    // 当前坐标是战舰的起始点，战舰的数量加 1
                    count++;
                }
            }

            return count;  // 返回战舰的数量
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_419_BattleshipsInABoard().new Solution();

        // 测试样例 1
        char[][] board1 = {
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        };
        System.out.println("测试样例 1：");
        // 期望输出：2，棋盘上有两个战舰，一个水平战舰，一个垂直战舰
        System.out.println("战舰数量: " + solution.countBattleships(board1));

        // 测试样例 2
        char[][] board2 = {
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'}
        };
        System.out.println("测试样例 2：");
        // 期望输出：0，棋盘上没有战舰
        System.out.println("战舰数量: " + solution.countBattleships(board2));

        // 测试样例 3
        char[][] board3 = {
                {'X', 'X', 'X'},
                {'.', '.', '.'},
                {'X', 'X', 'X'}
        };
        System.out.println("测试样例 3：");
        // 期望输出：2，棋盘上有两个战舰，一个水平战舰，一个垂直战舰
        System.out.println("战舰数量: " + solution.countBattleships(board3));
    }
}

/**
Given an m x n matrix board where each cell is a battleship 'X' or empty '.', 
return the number of the battleships on board. 

 Battleships can only be placed horizontally or vertically on board. In other 
words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k 
rows, 1 column), where k can be of any size. At least one horizontal or vertical 
cell separates between two battleships (i.e., there are no adjacent battleships). 


 
 Example 1: 
 
 
Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
Output: 2
 

 Example 2: 

 
Input: board = [["."]]
Output: 0
 

 
 Constraints: 

 
 m == board.length 
 n == board[i].length 
 1 <= m, n <= 200 
 board[i][j] is either '.' or 'X'. 
 

 
 Follow up: Could you do it in one-pass, using only O(1) extra memory and 
without modifying the values board? 

 Related Topics Array Depth-First Search Matrix 👍 2292 👎 971

*/
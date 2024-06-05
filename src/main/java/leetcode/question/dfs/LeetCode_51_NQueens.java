package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *@Question:  51. N-Queens
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 79.3%
 *@Time  Complexity: O(n!)
 *@Space Complexity: O(n)
 */

/**
 * ### 解题思路
 *
 * 题目要求求解N皇后问题，即在 `n x n` 的棋盘上放置 `n` 个皇后，使得每个皇后都不能攻击其他任何一个皇后。皇后可以攻击同一行、同一列和同一对角线上的任何棋子。
 *
 * 为了实现这一目标，我们可以使用回溯算法来逐行放置皇后，同时确保每次放置的皇后不会与之前放置的皇后互相攻击。
 *
 * #### 步骤详解
 *
 * 1. **初始化棋盘**：
 *    - 创建一个 `n x n` 的棋盘，并将所有位置初始化为 `'.'`，表示空位。
 *
 * 2. **回溯算法**：
 *    - 使用一个递归函数 `backtrack` 从第0行开始逐行尝试放置皇后。
 *    - **终止条件**：当当前行号等于 `n` 时，表示已经成功放置了 `n` 个皇后，保存当前棋盘配置。
 *    - **遍历每列**：
 *      - 对于当前行的每一列，计算当前皇后的对角线和反对角线位置。
 *      - **剪枝**：如果当前列、对角线或反对角线已被占用，则跳过当前列。
 *      - **放置皇后**：在当前位置放置皇后，并记录当前列、对角线和反对角线。
 *      - 递归调用 `backtrack` 尝试在下一行放置皇后。
 *      - **回溯**：移除当前皇后，恢复棋盘和标记状态，尝试下一列。
 *
 * 3. **生成棋盘配置**：
 *    - 使用辅助函数将当前棋盘状态转换为字符串列表，便于输出。
 *
 * #### 细节
 * - 使用 `Set` 数据结构来记录已被占用的列、对角线和反对角线。
 * - 利用对角线和反对角线的数学特性：对角线的索引差相等，反对角线的索引和相等。
 *
 * ### 时间和空间复杂度分析
 *
 * #### 时间复杂度
 * - **最坏情况下**：对于每一行，我们尝试所有列，在每次尝试中，我们需要检查当前列、对角线和反对角线的状态。虽然对称性和剪枝减少了尝试次数，但最坏情况下，我们仍需要考虑每个可能的位置组合。
 * - 理论上，对于 `n` 个皇后，时间复杂度是 `O(n!)`，因为我们需要在每行选择一个位置，但由于剪枝和对称性，实际复杂度通常小于 `O(n!)`。
 *
 * #### 空间复杂度
 * - **递归调用栈**：递归深度为 `n`，即 `O(n)`。
 * - **标记集合**：用于记录已占用的列、对角线和反对角线的 `Set`，每个集合的大小为 `O(n)`。
 * - **棋盘状态**：存储当前棋盘状态的二维数组 `char[][]`，大小为 `O(n^2)`。
 * - **结果列表**：保存所有解法的列表，每个解法的大小为 `O(n^2)`，最坏情况下可能有 `O(n!)` 个解法。
 *
 * 综合考虑，空间复杂度为 `O(n^2 + n + n!)`，其中 `O(n!)` 是结果列表的复杂度。

 */
public class LeetCode_51_NQueens{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int size; // 棋盘大小，即n
        private List<List<String>> solutions = new ArrayList<List<String>>(); // 存储所有解法的列表

        public List<List<String>> solveNQueens(int n) {
            size = n; // 初始化棋盘大小
            char emptyBoard[][] = new char[size][size]; // 初始化空棋盘
            for (int i = 0; i < n; i++) { // 遍历棋盘的行
                for (int j = 0; j < n; j++) { // 遍历棋盘的列
                    emptyBoard[i][j] = '.'; // 将棋盘的每个位置初始化为'.'
                }
            }

            backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>(), emptyBoard); // 开始回溯
            return solutions; // 返回所有的解法
        }

        // 使用辅助函数来获取正确输出格式的解
        private List<String> createBoard(char[][] state) {
            List<String> board = new ArrayList<String>(); // 存储当前棋盘状态的列表
            for (int row = 0; row < size; row++) { // 遍历棋盘的每一行
                String current_row = new String(state[row]); // 将当前行转换为字符串
                board.add(current_row); // 将当前行加入列表
            }

            return board; // 返回当前棋盘状态的列表
        }

        private void backtrack(int row, Set<Integer> diagonals, Set<Integer> antiDiagonals,
                               Set<Integer> cols, char[][] state) {
            // 基本情况 - 所有皇后都已放置
            if (row == size) {
                solutions.add(createBoard(state)); // 将当前棋盘状态加入解法列表
                return; // 返回
            }

            for (int col = 0; col < size; col++) { // 遍历当前行的每一列
                int currDiagonal = row - col; // 当前对角线
                int currAntiDiagonal = row + col; // 当前反对角线
                // 如果当前列或对角线或反对角线已有皇后，则跳过
                if (cols.contains(col) || diagonals.contains(currDiagonal) ||
                        antiDiagonals.contains(currAntiDiagonal)) {
                    continue;
                }

                // "添加"皇后到棋盘
                cols.add(col); // 将当前列加入已放置皇后的列集合
                diagonals.add(currDiagonal); // 将当前对角线加入已放置皇后的对角线集合
                antiDiagonals.add(currAntiDiagonal); // 将当前反对角线加入已放置皇后的反对角线集合
                state[row][col] = 'Q'; // 将当前棋盘位置标记为皇后

                // 使用更新后的棋盘状态递归调用回溯函数
                backtrack(row + 1, diagonals, antiDiagonals, cols, state);

                // "移除"皇后从棋盘，因为我们已经探索了所有可能的路径
                cols.remove(col); // 从已放置皇后的列集合中移除当前列
                diagonals.remove(currDiagonal); // 从已放置皇后的对角线集合中移除当前对角线
                antiDiagonals.remove(currAntiDiagonal); // 从已放置皇后的反对角线集合中移除当前反对角线
                state[row][col] = '.'; // 将当前棋盘位置重置为'.'
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_51_NQueens().new Solution();

        // 测试用例1: 4皇后问题
        int n1 = 4;
        List<List<String>> solutions1 = solution.solveNQueens(n1);
        System.out.println("4皇后问题的解法数量: " + solutions1.size());
        for (List<String> solution : solutions1) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }

        // 测试用例2: 8皇后问题
        int n2 = 8;
        List<List<String>> solutions2 = solution.solveNQueens(n2);
        System.out.println("8皇后问题的解法数量: " + solutions2.size());
        for (List<String> solution : solutions2) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}

/**
The n-queens puzzle is the problem of placing n queens on an n x n chessboard 
such that no two queens attack each other. 

 Given an integer n, return all distinct solutions to the n-queens puzzle. You 
may return the answer in any order. 

 Each solution contains a distinct board configuration of the n-queens' 
placement, where 'Q' and '.' both indicate a queen and an empty space, respectively. 

 
 Example 1: 
 
 
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown 
above
 

 Example 2: 

 
Input: n = 1
Output: [["Q"]]
 

 
 Constraints: 

 
 1 <= n <= 9 
 

 Related Topics Array Backtracking 👍 11674 👎 251

*/

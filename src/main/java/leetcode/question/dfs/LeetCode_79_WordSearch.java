package leetcode.question.dfs;

/**
 *@Question:  79. Word Search
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 76.87%
 *@Time  Complexity: O(N * 3^L)  N是board中单元格的数量，L是word的长度
 * 第一步可以选择 4 个方向
 * 除第一步外，每个后续步骤最多只能向 3 个方向延伸（不能回头）
 * 所以是 4 * 3^(L-1)，约等于 O(3^L)
 *@Space Complexity: O(L)  递归调用的栈空间
 */

/**
 * ### 解题思路
 *
 * 题目要求在一个二维字符网格中寻找一个单词，单词可以通过上下左右相邻的单元格来构成，但每个单元格只能使用一次。为了解决这个问题，我们可以使用深度优先搜索（DFS）来遍历所有可能的路径，并使用回溯法来探索不同的路径。
 *
 * #### 具体步骤如下：
 *
 * 1. **初始化**：
 *    - 保存输入的棋盘 `board` 和它的行列数 `ROWS` 和 `COLS` 为类的成员变量。
 *    - 遍历棋盘的每一个单元格 `(row, col)`，尝试从该单元格开始进行 DFS 搜索。
 *
 * 2. **DFS 递归函数**：
 *    - 递归函数 `backtrack(row, col, word, index)` 主要任务是从当前的单元格 `(row, col)` 开始，匹配单词 `word` 的第 `index` 个字符。
 *    - **边界条件**：
 *      - 如果 `index` 等于单词的长度，说明已经成功匹配整个单词，返回 `true`。
 *      - 如果当前位置超出了棋盘边界，或者当前单元格的字符不匹配 `word` 的第 `index` 个字符，返回 `false`。
 *    - **标记当前单元格为访问过**：
 *      - 为了避免重复访问当前路径中的单元格，将当前单元格的值临时修改为一个特殊标记，比如 `'#'`。
 *    - **探索相邻的四个方向**（上、下、左、右）：
 *      - 使用一个方向数组 `rowOffsets` 和 `colOffsets` 来表示四个方向的移动。
 *      - 对于每一个方向，递归调用 `backtrack` 函数。
 *    - **回溯**：
 *      - 在递归返回之前，将当前单元格恢复为原来的字符，以便其他路径能够正确访问。
 *    - 如果所有方向都没有找到匹配的路径，返回 `false`。
 *
 * 3. **整体逻辑**：
 *    - 对于每一个单元格 `(row, col)` 作为起点，调用 `backtrack` 函数。
 *    - 如果从某个单元格起点的 DFS 返回 `true`，说明已经找到匹配的路径，直接返回 `true`。
 *    - 如果遍历所有单元格都没有找到匹配的路径，返回 `false`。
 *
 * ### 时间和空间复杂度分析
 *
 * - **时间复杂度**：`O(N * 3^L)`，其中 `N` 是棋盘上的单元格总数，`L` 是单词的长度。
 *   - 最坏情况下，我们需要遍历每一个单元格作为起点，并尝试从每个起点进行 DFS 搜索。
 *   - 在 DFS 搜索过程中，每一步最多有 3 个方向可以选择（不包括来时的方向），因此对于长度为 `L` 的单词，最多需要进行 `3^L` 次搜索。
 *
 * - **空间复杂度**：`O(L)`，主要是递归调用的栈空间。
 *   - 在递归过程中，递归深度最大为单词的长度 `L`，因此需要 `O(L)` 的栈空间来存储递归调用。
 *
 * 通过这种方法，我们可以有效地在二维字符网格中寻找给定的单词，同时保证每个单元格只使用一次。
 */

public class LeetCode_79_WordSearch{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 存储棋盘和棋盘的行列数
        private char[][] board;
        private int ROWS;
        private int COLS;

        public boolean exist(char[][] board, String word) {
            this.board = board;
            this.ROWS = board.length;
            this.COLS = board[0].length;

            // 遍历每个单元格，作为 DFS 的起点
            for (int row = 0; row < this.ROWS; ++row) {
                for (int col = 0; col < this.COLS; ++col) {
                    // 如果找到匹配的路径，返回 true
                    if (this.backtrack(row, col, word, 0)) {
                        return true;
                    }
                }
            }
            // 如果所有路径都不匹配，返回 false
            return false;
        }

        // 深度优先搜索辅助函数
        protected boolean backtrack(int row, int col, String word, int index) {
            // 步骤 1：检查是否已经找到匹配的单词
            if (index >= word.length()) {
                return true;
            }

            // 步骤 2：检查边界条件和当前单元格是否匹配
            if (
                    row < 0 ||
                            row == this.ROWS ||
                            col < 0 ||
                            col == this.COLS ||
                            this.board[row][col] != word.charAt(index)
            ) {
                return false;
            }

            // 步骤 3：标记当前路径，然后继续探索相邻单元格
            this.board[row][col] = '#';  // 标记为已访问

            int[] rowOffsets = { 0, 1, 0, -1 };
            int[] colOffsets = { 1, 0, -1, 0 };
            for (int d = 0; d < 4; ++d) {
                // 递归探索相邻单元格
                if (
                        this.backtrack(
                                row + rowOffsets[d],
                                col + colOffsets[d],
                                word,
                                index + 1
                        )
                ) {
                    // 如果找到匹配的路径，返回 true
                    return true;
                }
            }

            // 步骤 4：恢复当前单元格的值，并返回 false
            this.board[row][col] = word.charAt(index);
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_79_WordSearch().new Solution();
        // 测试样例
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";

        System.out.println(solution.exist(board, word1)); // 输出: true
        System.out.println(solution.exist(board, word2)); // 输出: true
        System.out.println(solution.exist(board, word3)); // 输出: false
    }
}

/**
Given an m x n grid of characters board and a string word, return true if word 
exists in the grid. 

 The word can be constructed from letters of sequentially adjacent cells, where 
adjacent cells are horizontally or vertically neighboring. The same letter cell 
may not be used more than once. 

 
 Example 1: 
 
 
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
"ABCCED"
Output: true
 

 Example 2: 
 
 
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
"SEE"
Output: true
 

 Example 3: 
 
 
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
"ABCB"
Output: false
 

 
 Constraints: 

 
 m == board.length 
 n = board[i].length 
 1 <= m, n <= 6 
 1 <= word.length <= 15 
 board and word consists of only lowercase and uppercase English letters. 
 

 
 Follow up: Could you use search pruning to make your solution faster with a 
larger board? 

 Related Topics Array String Backtracking Matrix 👍 15740 👎 662

*/
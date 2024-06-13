package leetcode.question.map_set;
import java.util.HashSet;

/**
 *@Question:  36. Valid Sudoku
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 76.86%
 *@Time  Complexity: O(N^2)
 *@Space Complexity: O(N^2)
 */

/**
 * 这道题目是要求判断给定的数独是否有效。一个有效的数独（填充完毕）必须满足以下条件：
 *
 * 1. 每一行必须包含 1-9 的数字，且不能有重复。
 * 2. 每一列必须包含 1-9 的数字，且不能有重复。
 * 3. 每一个 3x3 的子数独（九宫格）必须包含 1-9 的数字，且不能有重复。
 *
 * 解题思路：
 * 为了解决这个问题，我们可以使用哈希集合来记录每一行、每一列以及每一个 3x3 的子数独中已经出现过的数字。遍历数独的每一个格子，依次检查当前数字在行、列以及九宫格中是否已经出现过，如果出现过，则该数独不是有效的。
 *
 * 具体步骤如下：
 * 1. 定义三个长度为 9 的哈希集合数组 `rows`、`cols` 和 `boxes`，分别用于记录每一行、每一列以及每一个九宫格中已经出现过的数字。
 * 2. 遍历数独的每一个格子，对于每一个格子：
 *    - 检查当前位置是否为空，如果为空，则跳过。
 *    - 检查当前数字是否已经出现在当前行、当前列以及当前九宫格中，如果出现过，则返回 false，说明数独无效。
 *    - 如果当前数字未出现过，则将其加入对应的行、列以及九宫格的哈希集合中。
 * 3. 如果遍历完所有格子后没有发现重复的数字，则返回 true，说明数独有效。
 *
 * 时间复杂度分析：
 * - 遍历数独的每一个格子需要 O(N^2) 的时间复杂度，其中 N 是数独的大小，因此时间复杂度为 O(N^2)。
 *
 * 空间复杂度分析：
 * - 我们使用了三个长度为 9 的哈希集合数组来记录每一行、每一列以及每一个九宫格中已经出现过的数字，因此空间复杂度为 O(N^2)。
 */

public class LeetCode_36_ValidSudoku{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            int N = 9; // 数独的大小为 9x9

            // 使用哈希集合记录状态
            HashSet<Character>[] rows = new HashSet[N]; // 用于记录每一行的数字情况
            HashSet<Character>[] cols = new HashSet[N]; // 用于记录每一列的数字情况
            HashSet<Character>[] boxes = new HashSet[N]; // 用于记录每一个九宫格的数字情况
            for (int r = 0; r < N; r++) {
                rows[r] = new HashSet<Character>();
                cols[r] = new HashSet<Character>();
                boxes[r] = new HashSet<Character>();
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    char val = board[r][c];

                    // 检查该位置是否填充了数字
                    if (val == '.') {
                        continue;
                    }

                    // 检查行
                    if (rows[r].contains(val)) {
                        return false;
                    }
                    rows[r].add(val);

                    // 检查列
                    if (cols[c].contains(val)) {
                        return false;
                    }
                    cols[c].add(val);

                    // 检查九宫格
                    int idx = (r / 3) * 3 + c / 3; // 计算当前位置属于哪个九宫格
                    if (boxes[idx].contains(val)) {
                        return false;
                    }
                    boxes[idx].add(val);
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_36_ValidSudoku.Solution solution = new LeetCode_36_ValidSudoku().new Solution();
        // 测试代码
        // 假设给定一个数独 board
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        // 输出测试结果
        System.out.println("结果：" + solution.isValidSudoku(board));
    }
}

/**
 Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be
 validated according to the following rules:


 Each row must contain the digits 1-9 without repetition.
 Each column must contain the digits 1-9 without repetition.
 Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9
 without repetition.


 Note:


 A Sudoku board (partially filled) could be valid but is not necessarily
 solvable.
 Only the filled cells need to be validated according to the mentioned rules.



 Example 1:


 Input: board =
 [["5","3",".",".","7",".",".",".","."]
 ,["6",".",".","1","9","5",".",".","."]
 ,[".","9","8",".",".",".",".","6","."]
 ,["8",".",".",".","6",".",".",".","3"]
 ,["4",".",".","8",".","3",".",".","1"]
 ,["7",".",".",".","2",".",".",".","6"]
 ,[".","6",".",".",".",".","2","8","."]
 ,[".",".",".","4","1","9",".",".","5"]
 ,[".",".",".",".","8",".",".","7","9"]]
 Output: true


 Example 2:


 Input: board =
 [["8","3",".",".","7",".",".",".","."]
 ,["6",".",".","1","9","5",".",".","."]
 ,[".","9","8",".",".",".",".","6","."]
 ,["8",".",".",".","6",".",".",".","3"]
 ,["4",".",".","8",".","3",".",".","1"]
 ,["7",".",".",".","2",".",".",".","6"]
 ,[".","6",".",".",".",".","2","8","."]
 ,[".",".",".","4","1","9",".",".","5"]
 ,[".",".",".",".","8",".",".","7","9"]]
 Output: false
 Explanation: Same as Example 1, except with the 5 in the top left corner being
 modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is
 invalid.



 Constraints:


 board.length == 9
 board[i].length == 9
 board[i][j] is a digit 1-9 or '.'.


 Related Topics Array Hash Table Matrix 👍 10500 👎 1109

 */
package leetcode.frequent.bfs;

import java.util.LinkedList;
import java.util.List;

/**
  *@Question:  130. Surrounded Regions
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 43.14%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

/**
 * 这个算法的主要思路是通过深度优先搜索（DFS）或广度优先搜索（BFS）来标记所有与边界上的'O'相连的'O'，
 * 然后再遍历整个矩阵，将未被标记的'O'翻转成'X'，而被标记的'O'表示与边界相连，不需要被翻转。
 *
 * 算法步骤：
 *
 * 1. 初始化一个列表`borders`，用于存储矩阵边界上的所有'O'的坐标。
 * 2. 遍历边界上的所有元素，将其加入`borders`。
 * 3. 对`borders`中的每个坐标，进行深度优先搜索（DFS）或广度优先搜索（BFS），将与边界相连的所有'O'标记为'E'。
 * 4. 遍历整个矩阵，将未被标记为'E'的'O'翻转成'X'，将标记为'E'的'O'恢复成'O'。
 * 5. 完成矩阵的修改。
 *
 * 时间复杂度分析：遍历边界的时间复杂度是O(N)，其中N是矩阵中元素的个数。DFS或BFS的时间复杂度也是O(N)，
 * 因为每个元素至多被访问一次。因此，总体时间复杂度为O(N)。
 *
 * 空间复杂度分析：空间复杂度主要由`borders`列表和DFS或BFS的递归调用栈占用。`borders`的空间复杂度是O(N)，
 * DFS或BFS的递归调用栈的深度也是O(N)，因此总体空间复杂度为O(N)。
 *
 * 综上所述，这个算法是一种时间复杂度和空间复杂度都为O(N)的解决方案。
 */



public class LeetCode_130_SurroundedRegions {

    //leetcode submit region begin(Prohibit modification and deletion)
    // 定义一个Pair类用于表示坐标
    class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }


    public class Solution {
        protected Integer ROWS = 0;
        protected Integer COLS = 0;

        // DFS方法
        public void solve(char[][] board) {
            if (board == null || board.length == 0) {
                return;
            }
            this.ROWS = board.length;
            this.COLS = board[0].length;

            List<Pair<Integer, Integer>> borders = new LinkedList<Pair<Integer, Integer>>();
            // Step 1). 构建边界坐标列表
            for (int r = 0; r < this.ROWS; ++r) {
                borders.add(new Pair(r, 0));
                borders.add(new Pair(r, this.COLS - 1));
            }
            for (int c = 0; c < this.COLS; ++c) {
                borders.add(new Pair(0, c));
                borders.add(new Pair(this.ROWS - 1, c));
            }

            // Step 2). 标记可以逃脱的单元格
            for (Pair<Integer, Integer> pair : borders) {
                this.DFS(board, pair.first, pair.second);
            }

            // Step 3). 翻转单元格到最终状态
            for (int r = 0; r < this.ROWS; ++r) {
                for (int c = 0; c < this.COLS; ++c) {
                    if (board[r][c] == 'O')
                        board[r][c] = 'X';
                    if (board[r][c] == 'E')
                        board[r][c] = 'O';
                }
            }
        }

        // DFS递归遍历
        protected void DFS(char[][] board, int row, int col) {
            if (board[row][col] != 'O')
                return;

            board[row][col] = 'E';
            if (col < this.COLS - 1)
                this.DFS(board, row, col + 1);
            if (row < this.ROWS - 1)
                this.DFS(board, row + 1, col);
            if (col > 0)
                this.DFS(board, row, col - 1);
            if (row > 0)
                this.DFS(board, row - 1, col);
        }

        // BFS方法
        public void solve2(char[][] board) {
            if (board == null || board.length == 0) {
                return;
            }
            this.ROWS = board.length;
            this.COLS = board[0].length;

            List<Pair<Integer, Integer>> borders = new LinkedList<Pair<Integer, Integer>>();
            // Step 1). 构建边界坐标列表
            for (int r = 0; r < this.ROWS; ++r) {
                borders.add(new Pair(r, 0));
                borders.add(new Pair(r, this.COLS - 1));
            }
            for (int c = 0; c < this.COLS; ++c) {
                borders.add(new Pair(0, c));
                borders.add(new Pair(this.ROWS - 1, c));
            }

            // Step 2). 标记可以逃脱的单元格
            for (Pair<Integer, Integer> pair : borders) {
                this.BFS(board, pair.first, pair.second);
            }

            // Step 3). 翻转单元格到最终状态
            for (int r = 0; r < this.ROWS; ++r) {
                for (int c = 0; c < this.COLS; ++c) {
                    if (board[r][c] == 'O')
                        board[r][c] = 'X';
                    if (board[r][c] == 'E')
                        board[r][c] = 'O';
                }
            }
        }

        // BFS迭代遍历
        protected void BFS(char[][] board, int r, int c) {
            LinkedList<Pair<Integer, Integer>> queue = new LinkedList<Pair<Integer, Integer>>();
            queue.offer(new Pair<>(r, c));

            while (!queue.isEmpty()) {
                Pair<Integer, Integer> pair = queue.pollFirst();
                int row = pair.first, col = pair.second;
                if (board[row][col] != 'O')
                    continue;

                board[row][col] = 'E';
                if (col < this.COLS - 1)
                    queue.offer(new Pair<>(row, col + 1));
                if (row < this.ROWS - 1)
                    queue.offer(new Pair<>(row + 1, col));
                if (col > 0)
                    queue.offer(new Pair<>(row, col - 1));
                if (row > 0)
                    queue.offer(new Pair<>(row - 1, col));
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_130_SurroundedRegions().new Solution();
        // TO TEST
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solution.solve(board);
        // 打印测试结果
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
/**
Given an m x n matrix board containing 'X' and 'O', capture all regions that 
are 4-directionally surrounded by 'X'. 

 A region is captured by flipping all 'O's into 'X's in that surrounded region. 


 
 Example 1: 
 
 
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O",
"X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]
]
Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped.
 

 Example 2: 

 
Input: board = [["X"]]
Output: [["X"]]
 

 
 Constraints: 

 
 m == board.length 
 n == board[i].length 
 1 <= m, n <= 200 
 board[i][j] is 'X' or 'O'. 
 

 Related Topics Array Depth-First Search Breadth-First Search Union Find Matrix 
👍 8168 👎 1702

*/

package leetcode.question.bfs;

/**
 * @Question:  542. 01 Matrix
 * @Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 66.58%
 * @Time  Complexity: O(M*N) m as the number of rows and n as the number of columns,
 * @Space Complexity: O(M*N)
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * **算法思路：**
 *
 * 这个问题的目标是计算矩阵中每个位置到最近的0的距离。为了解决这个问题，使用广度优先搜索（BFS）来进行遍历。
 *
 * 1. **初始化：** 创建一个新的矩阵`matrix`用于存储最终的结果，一个`boolean`类型的二维数组`seen`用于标记每个位置是否已经访问过，
 * 以及一个队列`queue`用于BFS。
 *
 * 2. **将所有值为0的位置加入队列：** 遍历原矩阵，将所有值为0的位置加入队列，并将它们的步数设为0。同时，将`seen`数组中对应位置标记为`true`。
 *
 * 3. **进行BFS：** 在队列不为空的情况下，从队列中弹出一个位置，表示当前正在处理的位置。然后，遍历其四个相邻位置，
 * 如果相邻位置是合法的且未被访问过，将其加入队列，并更新`matrix`中的值为步数。同时，将相邻位置标记为已访问。
 *
 * 4. **返回结果：** 当队列为空时，BFS完成，返回最终的矩阵`matrix`。
 *
 * **时间复杂度：**
 *
 * - 在最坏情况下，每个位置都会被访问一次，而每次访问都需要O(1)的时间。因此，总体时间复杂度为O(N)，其中N是矩阵中的元素总数。
 *
 * **空间复杂度：**
 *
 * - 使用了一个新的矩阵`matrix`来存储结果，一个`boolean`类型的二维数组`seen`来标记访问过的位置，以及一个队列`queue`。因此，空间复杂度为O(N)。
 */

public class LeetCode_542_Zero1Matrix {

    //leetcode submit region begin(Prohibit modification and deletion)

    // 定义表示状态的类，包含行、列和步数
    class State {
        int row;
        int col;
        int steps;

        State(int row, int col, int steps) {
            this.row = row;
            this.col = col;
            this.steps = steps;
        }
    }

    class Solution {
        int m; // 矩阵的行数
        int n; // 矩阵的列数
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 表示四个方向的数组

        public int[][] updateMatrix(int[][] mat) {
            m = mat.length; // 获取矩阵的行数
            n = mat[0].length; // 获取矩阵的列数

            int[][] matrix = new int[m][n]; // 创建一个新的矩阵用于存储结果
            boolean[][] seen = new boolean[m][n]; // 创建一个二维数组表示是否访问过
            Queue<State> queue = new LinkedList<>(); // 使用队列进行BFS

            // 遍历原矩阵，将值为0的位置加入队列，表示已经访问过，并设置初始步数为0
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    matrix[row][col] = mat[row][col];
                    if (mat[row][col] == 0) {
                        queue.add(new State(row, col, 0));
                        seen[row][col] = true;
                    }
                }
            }
            System.out.println(queue);

            // BFS过程
            while (!queue.isEmpty()) {
                State state = queue.remove();
                int row = state.row, col = state.col, steps = state.steps;

                for (int[] direction : directions) {
                    int nextRow = row + direction[0], nextCol = col + direction[1];
                    // 判断下一个位置是否合法且未访问过
                    if (valid(nextRow, nextCol) && !seen[nextRow][nextCol]) {
                        seen[nextRow][nextCol] = true;
                        queue.add(new State(nextRow, nextCol, steps + 1));
                        matrix[nextRow][nextCol] = steps + 1; // 更新矩阵的值为步数
                    }
                }
            }

            return matrix;
        }

        // 判断坐标是否合法
        public boolean valid(int row, int col) {
            return 0 <= row && row < m && 0 <= col && col < n;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    // 测试函数
    public static void main(String[] args) {
        Solution solution = new LeetCode_542_Zero1Matrix().new Solution();
        int[][] mat = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] result = solution.updateMatrix(mat);

        // 打印结果
        System.out.println("原矩阵：");
        printMatrix(mat);

        System.out.println("\n更新后的矩阵：");
        printMatrix(result);
    }

    // 打印矩阵
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/**
Given an m x n binary matrix mat, return the distance of the nearest 0 for each 
cell. 

 The distance between two adjacent cells is 1. 

 
 Example 1: 
 
 
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
 

 Example 2: 
 
 
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
 

 
 Constraints: 

 
 m == mat.length 
 n == mat[i].length 
 1 <= m, n <= 10⁴ 
 1 <= m * n <= 10⁴ 
 mat[i][j] is either 0 or 1. 
 There is at least one 0 in mat. 
 

 Related Topics Array Dynamic Programming Breadth-First Search Matrix 👍 9013 👎
 399

*/

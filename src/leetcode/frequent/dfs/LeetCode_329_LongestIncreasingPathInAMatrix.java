package leetcode.frequent.dfs;

/**
  *@Question:  329. Longest Increasing Path in a Matrix     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 68.28%      
  *@Time  Complexity: O(m*n)
  *@Space Complexity: O(m*n)
 */

/**
 * 这个算法使用深度优先搜索（DFS）和记忆化搜索（Memoization）来解决矩阵中查找最长递增路径的问题。以下是算法的思路：
 *
 * ### 算法思路：
 *
 * 1. **深度优先搜索（DFS）：** 从矩阵中的每个点出发，沿着递增的路径进行深度优先搜索。对于每个点，探索上、右、下、左四个方向，
 * 如果存在递增路径，则继续深度优先搜索。
 *
 * 2. **记忆化搜索（Memoization）：** 为了避免重复计算，使用一个二维数组 `cache` 来缓存中间结果。`cache[i][j]`
 * 表示从矩阵中的位置 `(i, j)` 开始的最长递增路径的长度。
 *
 * 3. **递归规则：** 对于当前位置 `(i, j)`，遍历四个方向，如果存在递增路径，就递归计算该方向的最长递增路径，
 * 并更新 `cache[i][j]`。最终，返回当前位置的最长递增路径长度。
 *
 * ### 时间复杂度：
 *
 * - 对于每个矩阵中的位置，都进行一次深度优先搜索，而且使用了记忆化搜索避免了重复计算。因此，时间复杂度为 **O(m * n)**，
 * 其中 `m` 是矩阵的行数，`n` 是矩阵的列数。
 *
 * ### 空间复杂度：
 *
 * - 使用了一个二维数组 `cache` 来缓存中间结果，其大小为矩阵的大小，因此空间复杂度为 **O(m * n)**。
 *
 * 总体来说，这个算法通过深度优先搜索和记忆化搜索有效地解决了问题，并在合理的时间和空间复杂度内完成计算。
 */

public class LeetCode_329_LongestIncreasingPathInAMatrix {

    //leetcode submit region begin(Prohibit modification and deletion)
    // DFS + Memoization Solution
    // Accepted and Recommended
    public class Solution {
        // 方向数组，表示上、右、下、左四个方向
        private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private int m, n;  // 矩阵的行数和列数

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0) return 0;
            m = matrix.length; n = matrix[0].length;
            int[][] cache = new int[m][n];  // 用于缓存中间结果，避免重复计算
            int ans = 0;
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j)
                    ans = Math.max(ans, dfs(matrix, i, j, cache));
            return ans;
        }

        // 深度优先搜索函数
        private int dfs(int[][] matrix, int i, int j, int[][] cache) {
            if (cache[i][j] != 0) return cache[i][j];  // 如果已经计算过，直接返回缓存结果
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                // 检查四个方向上是否存在递增路径
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
                    cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
            }
            return ++cache[i][j];  // 返回当前位置的最长递增路径长度，并将结果缓存
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_329_LongestIncreasingPathInAMatrix().new Solution();
        // TO TEST
        int[][] matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        int result = solution.longestIncreasingPath(matrix);
        System.out.println("The length of the longest increasing path is: " + result);
    }
}

/**
Given an m x n integers matrix, return the length of the longest increasing 
path in matrix. 

 From each cell, you can either move in four directions: left, right, up, or 
down. You may not move diagonally or move outside the boundary (i.e., wrap-around 
is not allowed). 

 
 Example 1: 
 
 
Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
 

 Example 2: 
 
 
Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is 
not allowed.
 

 Example 3: 

 
Input: matrix = [[1]]
Output: 1
 

 
 Constraints: 

 
 m == matrix.length 
 n == matrix[i].length 
 1 <= m, n <= 200 
 0 <= matrix[i][j] <= 2³¹ - 1 
 

 Related Topics Array Dynamic Programming Depth-First Search Breadth-First 
Search Graph Topological Sort Memoization Matrix 👍 8683 👎 128

*/

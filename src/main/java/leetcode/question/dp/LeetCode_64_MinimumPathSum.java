package leetcode.question.dp;

/**
  *@Question:  64. Minimum Path Sum     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 53.47%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_64_MinimumPathSum{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //Solution 1: Top-Down
    public int minPathSum1(int[][] grid) {
        if(grid == null) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        memo[0][0] = grid[0][0];
        for(int i = 1; i < m; i++){
            memo[i][0] = memo[i-1][0] + grid[i][0];
        }
        for(int j = 1; j < n; j++){
            memo[0][j] = memo[0][j-1] + grid[0][j];
        }

        return dfs(memo, grid, m-1, n-1);
    }
    private int dfs(int[][] memo, int[][] grid, int i, int j){
        if( i == 0 && j ==0){
            return grid[0][0];
        }
        if(i < 0 || j < 0){
            return Integer.MAX_VALUE;
        }
        if(memo[i][j] != 0){
            return memo[i][j];
        }
        int sum = Math.min(dfs(memo, grid, i -1, j), dfs(memo, grid, i, j-1)) + grid[i][j];
        memo[i][j] = sum;
        return sum;
    }
    //Solution2: Bottom-Up
    //dp[i,j] = min(dp[i-1, j] , dp[i][j-1]) + grid[i][j]
    public int minPathSum2(int[][] grid) {
        if(grid == null){
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int j = 1; j < n; j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for(int i = 1; i< m; i++){
            for(int j = 1;j < n; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
            //Solution3:
    public int minPathSum(int[][] grid) {
        if(grid == null) return 0;
        int m = grid.length, n = grid[0].length;
        int[]dp = new int[n];
        dp[0] = grid[0][0];
        for(int j = 1; j < n; j++){
            dp[j] = dp[j-1] + grid[0][j];
        }
        for(int i = 1; i < m; i++){
            dp[0] = dp[0] + grid[i][0];
            for(int j = 1; j < n; j++){
                dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
            }
        }
        return dp[n-1];
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_64_MinimumPathSum().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a m x n grid filled with non-negative numbers, find a path from top left 
to bottom right, which minimizes the sum of all numbers along its path. 

 Note: You can only move either down or right at any point in time. 

 
 Example 1: 
 
 
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 

 Example 2: 

 
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 200 
 0 <= grid[i][j] <= 200 
 

 Related Topics Array Dynamic Programming Matrix 👍 12062 👎 157

*/
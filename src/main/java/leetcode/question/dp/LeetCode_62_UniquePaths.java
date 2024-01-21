package leetcode.question.dp;

import java.util.Arrays;

/**
  *@Question:  62. Unique Paths     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 64.87%      
  *@Time  Complexity: O(N*M)
  *@Space Complexity: O(N*M)
 */

public class LeetCode_62_UniquePaths{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //Solution1: Bottom-Up DP
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for(int[] arr : dp) {
            Arrays.fill(arr, 1);
        }
        for(int col = 1; col < m; ++col) {
            for(int row = 1; row < n; ++row) {
                dp[col][row] = dp[col - 1][row] + dp[col][row - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_62_UniquePaths().new Solution();
        // TO TEST
        //solution.
    }
}
/**
There is a robot on an m x n grid. The robot is initially located at the top-
left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner 
(i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any 
point in time. 

 Given the two integers m and n, return the number of possible unique paths 
that the robot can take to reach the bottom-right corner. 

 The test cases are generated so that the answer will be less than or equal to 2
 * 10⁹. 

 
 Example 1: 
 
 
Input: m = 3, n = 7
Output: 28
 

 Example 2: 

 
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the 
bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
 

 
 Constraints: 

 
 1 <= m, n <= 100 
 

 Related Topics Math Dynamic Programming Combinatorics 👍 16117 👎 423

*/
package leetcode.question.dp;

/**
  *@Question:  70. Climbing Stairs     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 55.50999999999999%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_70_ClimbingStairs{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //Solution 1: Top-Down
    //dp[n] = dp[n-1] + dp[n-2]
    public int climbStairs1(int n) {
        int[] mem = new int[n+1];
        return dfs(mem, n);
    }
    private int dfs(int[] mem, int n){
        if(n == 0 || n == 1 || n == 2){
            return n;
        }
        if(mem[n -1] != 0){
            return mem[n -1];
        }
        int count = dfs(mem, n -1) + dfs( mem, n -2);
        mem[n - 1] = count;
        return count;
    }

    //Solution2: Bottom-Up
    public int climbStairs2(int n) {
        if(n == 1 || n ==2){
            return n;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    //Solution2: 基于Solution2 空间优化
    public int climbStairs(int n) {
        if(n == 1 || n ==2){
            return n;
        }
        int a = 1;
        int b = 2;
        for(int i = 3; i<= n; i++){
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }


    }
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_70_ClimbingStairs().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are climbing a staircase. It takes n steps to reach the top. 

 Each time you can either climb 1 or 2 steps. In how many distinct ways can you 
climb to the top? 

 
 Example 1: 

 
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
 

 Example 2: 

 
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 

 
 Constraints: 

 
 1 <= n <= 45 
 

 Related Topics Math Dynamic Programming Memoization 👍 21189 👎 753

*/
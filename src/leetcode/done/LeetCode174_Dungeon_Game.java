package leetcode.done;
import java.util.*;
//174. Dungeon Game
//Hard
public class LeetCode174_Dungeon_Game {
    class Solution {
        public int calculateMinimumHP(int[][] grid) {
            if(grid == null || grid.length == 0){
                return 0;
            }
            int[][]dp = new int[grid.length][grid[0].length];

            int rows = dp.length -1;
            int cols =dp[0].length -1;

            for(int i = rows; i >= 0; i--){
                for(int j = cols; j >= 0; j--){
                    if(i == rows && j == cols){
                        dp[i][j] = Math.max(1 - grid[i][j], 1);
                    }
                    else if(j == cols){
                        dp[i][j] = Math.max(dp[i+1][j] - grid[i][j], 1);
                    }
                    else if(i == rows){
                        dp[i][j] = Math.max(dp[i][j+1] - grid[i][j], 1);
                    }
                    else{
                        dp[i][j] = Math.max(Math.min(dp[i+1][j] - grid[i][j], dp[i][j+1] - grid[i][j]), 1);
                    }
                }
            }
            System.out.println(Arrays.deepToString(dp).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

            return dp[0][0];

        }
    }
}

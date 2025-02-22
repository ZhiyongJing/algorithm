package leetcode.question.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
  *@Question:  1162. As Far from Land as Possible
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 29.109999999999996%
  *@Time  Complexity: O(N^2)
  *@Space Complexity: O(N^2)
 */

public class LeetCode_1162_AsFarFromLandAsPossible{

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxDistance(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int nRow = grid.length;
        int nCol = grid[1].length;
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < nRow; i++){
            for(int j = 0; j < nCol; j++){
                if(grid[i][j] == 1) queue.offer(new int[]{i, j});
            }

        }
        if(queue.size() == 0 || queue.size() == nRow * nCol) return -1;

        int distance = -1;

        int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

        while(queue.size() != 0){
            int currentLevel = queue.size();
            distance++;
            for(int i = 0; i < currentLevel; i++){
                int[] node = queue.poll();
                int row = node[0];
                int col = node[1];

                for(int[] dir: dirs){
                    int r = row + dir[0];
                    int c= col + dir[1];
                    if(r > -1 && r < nRow && c > -1 && c < nCol && grid[r][c] == 0){
                        grid[r][c] = 2;
                        queue.add(new int[]{r, c});
                    }
                }

            }

        }




        return distance;

        
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1162_AsFarFromLandAsPossible().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given an n x n grid containing only values 0 and 1, where 0 represents water 
and 1 represents land, find a water cell such that its distance to the nearest 
land cell is maximized, and return the distance. If no land or water exists in the 
grid, return -1. 

 The distance used in this problem is the Manhattan distance: the distance 
between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|. 

 
 Example 1: 
 
 
Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with 
distance 2.
 

 Example 2: 
 
 
Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with 
distance 4.
 

 
 Constraints: 

 
 n == grid.length 
 n == grid[i].length 
 1 <= n <= 100 
 grid[i][j] is 0 or 1 
 

 Related Topics Array Dynamic Programming Breadth-First Search Matrix 👍 4177 👎
 112

*/
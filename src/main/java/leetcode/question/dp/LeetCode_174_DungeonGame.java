package leetcode.question.dp;

import java.util.Arrays;

/**
 *@Question:  174. Dungeon Game
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 43.7%
 *@Time  Complexity: O(m * n) 其中 m 和 n 分别是地牢的行数和列数
 *@Space Complexity: O(m * n)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求我们计算骑士从左上角到右下角的路径上所需的最小初始生命值，以确保在整个过程中生命值始终大于0。为了高效地解决这个问题，我们可以使用动态规划（Dynamic Programming，DP）的方法。
 *
 * #### 动态规划思路
 *
 * 1. **定义状态**：
 *    - 用一个二维数组 `dp` 来存储每个位置所需的最小生命值，其中 `dp[i][j]` 表示骑士在位置 `(i, j)` 时所需的最小生命值。
 *
 * 2. **状态转移方程**：
 *    - 由于骑士只能向右或向下移动，因此对于位置 `(i, j)`，其最小生命值可以通过从右边或下边转移过来：
 *      - 从右边转移过来：`rightHealth = dp[i][j+1]`
 *      - 从下边转移过来：`downHealth = dp[i+1][j]`
 *      - 因此，`dp[i][j] = max(1, min(rightHealth, downHealth) - dungeon[i][j])`，其中 `dungeon[i][j]` 是当前位置的值。
 *
 * 3. **初始化**：
 *    - 我们从右下角开始逆向填充 `dp` 数组。首先初始化终点位置 `dp[rows-1][cols-1]` 所需的最小生命值：
 *      - 如果终点位置的值为非负值，那么骑士至少需要1点生命值才能存活，即 `dp[rows-1][cols-1] = 1`
 *      - 如果终点位置的值为负值，那么骑士需要至少 `1 - dungeon[rows-1][cols-1]` 点生命值才能存活。
 *
 * 4. **填表**：
 *    - 我们从右下角开始，逆向遍历地牢矩阵，逐步填充 `dp` 数组。对于每个位置 `(i, j)`，我们计算其最小生命值，并更新 `dp[i][j]`。
 *
 * 5. **记录结果**：
 *    - 最终，`dp[0][0]` 就是骑士在进入地牢前所需的最小初始生命值。
 *
 * ### 时间和空间复杂度
 *
 * #### 时间复杂度
 * - **时间复杂度**为 `O(m * n)`，其中 `m` 和 `n` 分别是地牢矩阵的行数和列数。因为我们需要遍历地牢矩阵的每个位置，并进行常数时间的计算。
 *
 * #### 空间复杂度
 * - **空间复杂度**为 `O(m * n)`，因为我们需要一个大小为 `m x n` 的二维数组 `dp` 来存储每个位置的最小生命值。
 *
 * ### 总结
 * 通过动态规划方法，我们能够高效地计算出骑士在进入地牢前所需的最小初始生命值。此方法利用了子问题的结果，避免了重复计算，显著提高了算法的效率。
 */
public class LeetCode_174_DungeonGame{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int inf = Integer.MAX_VALUE; // 定义一个表示无穷大的值
        int[][] dp; // 定义一个二维数组用于存储每个位置所需的最小生命值
        int rows, cols; // 定义行数和列数

        // 计算从当前位置到达指定位置所需的最小生命值
        public int getMinHealth(int currCell, int nextRow, int nextCol) {
            // 如果指定位置超出边界，返回无穷大
            if (nextRow >= this.rows || nextCol >= this.cols) return inf;
            int nextCell = this.dp[nextRow][nextCol];
            // 骑士至少需要1点生命值才能存活
            return Math.max(1, nextCell - currCell);
        }

        // 计算骑士在进入地牢前需要的最小生命值
        public int calculateMinimumHP(int[][] dungeon) {
            this.rows = dungeon.length; // 获取地牢的行数
            this.cols = dungeon[0].length; // 获取地牢的列数
            this.dp = new int[rows][cols]; // 初始化 dp 数组
            for (int[] arr : this.dp) {
                Arrays.fill(arr, this.inf); // 将 dp 数组中的所有元素填充为无穷大
            }
            int currCell, rightHealth, downHealth, nextHealth, minHealth;
            // 从右下角向左上角遍历地牢
            for (int row = this.rows - 1; row >= 0; --row) {
                for (int col = this.cols - 1; col >= 0; --col) {
                    currCell = dungeon[row][col]; // 当前格子的值

                    // 获取向右和向下移动所需的最小生命值
                    rightHealth = getMinHealth(currCell, row, col + 1);
                    downHealth = getMinHealth(currCell, row + 1, col);
                    nextHealth = Math.min(rightHealth, downHealth); // 选择两个方向中较小的那个

                    if (nextHealth != inf) {
                        minHealth = nextHealth;
                    } else {
                        // 如果是终点格子，计算所需的最小生命值
                        minHealth = currCell >= 0 ? 1 : 1 - currCell;
                    }
                    this.dp[row][col] = minHealth; // 更新 dp 数组
                }
            }
            return this.dp[0][0]; // 返回起点格子所需的最小生命值
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_174_DungeonGame().new Solution();
        // 测试样例
        int[][] dungeon1 = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println("Minimum HP required (test case 1): " + solution.calculateMinimumHP(dungeon1)); // 7

        int[][] dungeon2 = {
                {0, -3},
                {-10, 5}
        };
        System.out.println("Minimum HP required (test case 2): " + solution.calculateMinimumHP(dungeon2)); // 4
    }
}

/**
The demons had captured the princess and imprisoned her in the bottom-right 
corner of a dungeon. The dungeon consists of m x n rooms laid out in a 2D grid. Our 
valiant knight was initially positioned in the top-left room and must fight his 
way through dungeon to rescue the princess. 

 The knight has an initial health point represented by a positive integer. If 
at any point his health point drops to 0 or below, he dies immediately. 

 Some of the rooms are guarded by demons (represented by negative integers), so 
the knight loses health upon entering these rooms; other rooms are either empty 
(represented as 0) or contain magic orbs that increase the knight's health (
represented by positive integers). 

 To reach the princess as quickly as possible, the knight decides to move only 
rightward or downward in each step. 

 Return the knight's minimum initial health so that he can rescue the princess. 


 Note that any room can contain threats or power-ups, even the first room the 
knight enters and the bottom-right room where the princess is imprisoned. 

 
 Example 1: 
 
 
Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
Output: 7
Explanation: The initial health of the knight must be at least 7 if he follows 
the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
 

 Example 2: 

 
Input: dungeon = [[0]]
Output: 1
 

 
 Constraints: 

 
 m == dungeon.length 
 n == dungeon[i].length 
 1 <= m, n <= 200 
 -1000 <= dungeon[i][j] <= 1000 
 

 Related Topics Array Dynamic Programming Matrix 👍 5772 👎 106

*/
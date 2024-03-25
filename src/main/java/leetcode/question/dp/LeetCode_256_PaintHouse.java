package leetcode.question.dp;

/**
 *@Question:  256. Paint House
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 14.35%
 *@Time  Complexity: O(N) for all
 *@Space Complexity: O(N) for solution1, O(1) for solution 2 and 3
 */

import java.util.HashMap;
import java.util.Map;

/**
 * **解题思路：**
 *
 * 这题的目标是找到涂每个房子的最小花费，有三种颜色可选。动态规划是一种常见的解题思路，我们可以定义一个二维数组 `dp`，其中 `dp[i][j]` 表示涂第 `i` 个房子颜色 `j` 的最小花费。
 *
 * **Solution 1: 自顶向下的动态规划**
 *
 * 首先，我们可以使用递归的方式自顶向下计算，即对于每个房子，我们尝试每种颜色，计算其花费并递归计算下一个房子的最小花费。我们使用一个 HashMap 缓存计算结果，以避免重复计算。递归的终止条件是到达最后一个房子。
 *
 * **Solution 2: 自底向上的动态规划**
 *
 * 其次，我们可以使用自底向上的动态规划。我们从倒数第二个房子开始向前遍历，对于每个房子，我们计算涂每种颜色的最小花费。我们逐步计算每个房子的最小花费，最终得到第一个房子的最小花费。这种方法避免了递归带来的重复计算。
 *
 * **Solution 3: 基于 Solution 2 的空间优化**
 *
 * 在 Solution 2 的基础上，我们发现每次只需要关心上一个房子的最小花费即可，不需要记录整个 dp 数组。因此，我们可以使用一个一维数组 `previousRow` 来存储上一个房子的最小花费，逐步更新这个数组。这样可以降低空间复杂度。
 *
 * **时间复杂度：**
 *
 * - Solution 1: 自顶向下的动态规划，时间复杂度为 O(N)，其中 N 为房子的数量。
 * - Solution 2: 自底向上的动态规划，时间复杂度为 O(N)。
 * - Solution 3: 基于 Solution 2 的空间优化，时间复杂度为 O(N)。
 *
 * **空间复杂度：**
 *
 * - Solution 1: 自顶向下的动态规划，空间复杂度为 O(N)。
 * - Solution 2: 自底向上的动态规划，空间复杂度为 O(1)。
 * - Solution 3: 基于 Solution 2 的空间优化，空间复杂度为 O(1)。
 */

public class LeetCode_256_PaintHouse{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //Solution1: 自顶向下的动态规划
        private int[][] costs; // 存储房子涂不同颜色的花费
        private Map<String, Integer> memo; // 缓存计算结果

        public int minCost1(int[][] costs) {
            if (costs.length == 0) {
                return 0;
            }
            this.costs = costs;
            this.memo = new HashMap<>();
            // 从第一个房子开始涂色，尝试每种颜色，并计算最小花费
            return Math.min(paintCost(0, 0), Math.min(paintCost(0, 1), paintCost(0, 2)));
        }

        private int paintCost(int n, int color) {
            if (memo.containsKey(getKey(n, color))) {
                return memo.get(getKey(n, color));
            }
            int totalCost = costs[n][color];
            if (n == costs.length - 1) {
                // 如果当前是最后一个房子，则无需计算下一层
            } else if (color == 0) { // 红色
                totalCost += Math.min(paintCost(n + 1, 1), paintCost(n + 1, 2));
            } else if (color == 1) { // 绿色
                totalCost += Math.min(paintCost(n + 1, 0), paintCost(n + 1, 2));
            } else { // 蓝色
                totalCost += Math.min(paintCost(n + 1, 0), paintCost(n + 1, 1));
            }
            memo.put(getKey(n, color), totalCost);

            return totalCost;
        }

        private String getKey(int n, int color) {
            return String.valueOf(n) + " " + String.valueOf(color);
        }

        //Solution2: 自底向上的动态规划
        public int minCost(int[][] costs) {

            for (int n = costs.length - 2; n >= 0; n--) {
                // 计算涂红色的总花费
                costs[n][0] += Math.min(costs[n + 1][1], costs[n + 1][2]);
                // 计算涂绿色的总花费
                costs[n][1] += Math.min(costs[n + 1][0], costs[n + 1][2]);
                // 计算涂蓝色的总花费
                costs[n][2] += Math.min(costs[n + 1][0], costs[n + 1][1]);
            }

            if (costs.length == 0) return 0;

            // 返回最上层每种颜色的最小花费
            return Math.min(Math.min(costs[0][0], costs[0][1]), costs[0][2]);
        }

        //Solution3: 基于Solution2的空间优化
        public int minCost3(int[][] costs) {

            if (costs.length == 0) return 0;

            int[] previousRow = costs[costs.length -1];

            for (int n = costs.length - 2; n >= 0; n--) {

                int[] currentRow = costs[n].clone();
                // 计算涂红色的总花费
                currentRow[0] += Math.min(previousRow[1], previousRow[2]);
                // 计算涂绿色的总花费
                currentRow[1] += Math.min(previousRow[0], previousRow[2]);
                // 计算涂蓝色的总花费
                currentRow[2] += Math.min(previousRow[0], previousRow[1]);
                previousRow = currentRow;
            }

            // 返回最上层每种颜色的最小花费
            return Math.min(Math.min(previousRow[0], previousRow[1]), previousRow[2]);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_256_PaintHouse().new Solution();
        // 测试代码
        int[][] costs = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        int result = solution.minCost(costs);
        System.out.println(result); // 输出: 10
    }
}

/**
There is a row of n houses, where each house can be painted one of three colors:
 red, blue, or green. The cost of painting each house with a certain color is 
different. You have to paint all the houses such that no two adjacent houses have 
the same color. 

 The cost of painting each house with a certain color is represented by an n x 3
 cost matrix costs. 

 
 For example, costs[0][0] is the cost of painting house 0 with the color red; 
costs[1][2] is the cost of painting house 1 with color green, and so on... 
 

 Return the minimum cost to paint all houses. 

 
 Example 1: 

 
Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 
into blue.
Minimum cost: 2 + 5 + 3 = 10.
 

 Example 2: 

 
Input: costs = [[7,6,2]]
Output: 2
 

 
 Constraints: 

 
 costs.length == n 
 costs[i].length == 3 
 1 <= n <= 100 
 1 <= costs[i][j] <= 20 
 

 Related Topics Array Dynamic Programming 👍 2246 👎 129

*/
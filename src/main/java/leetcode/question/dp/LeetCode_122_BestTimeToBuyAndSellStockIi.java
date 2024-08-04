package leetcode.question.dp;

/**
  *@Question:  122. Best Time to Buy and Sell Stock II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 69.12%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * **问题描述**：这道题的目标是找出在给定的价格数组中，能够获得的最大利润。允许多次买入和卖出股票。我们的目标是最大化利润。
 *
 * #### 解题思路
 *
 * 1. **Solution1**:
 *    - **概念**：使用两个指针 `valley`（谷底）和 `peak`（峰顶）来标记每次的买入和卖出时机。
 *    - **步骤**：
 *      1. 从左到右遍历价格数组，找到每个价格区间中的谷底（valley）和峰顶（peak）。
 *      2. 谷底是当前价格低于接下来价格的点，即价格开始上升的点。
 *      3. 峰顶是当前价格高于接下来价格的点，即价格开始下降的点。
 *      4. 计算当前谷底到峰顶的利润，并将其加到总利润中。
 *      5. 重复以上步骤，直到遍历完整个价格数组。
 *    - **效果**：通过这种方法，我们可以在每次价格上升的区间内获取利润并累积，确保不会错过任何可以获得的利润。
 *
 * 2. **Solution2**:
 *    - **概念**：空间优化的方法，不显式标记谷底和峰顶。
 *    - **步骤**：
 *      1. 从第二天开始，遍历价格数组。
 *      2. 如果当前价格高于前一天的价格，说明价格在上涨。计算当天的利润并累加到总利润中。
 *      3. 只关心价格是否上涨，而不需要标记具体的谷底和峰顶。
 *    - **效果**：这种方法简化了实现，通过直接比较相邻价格并累积利润，降低了空间复杂度。
 *
 * #### 时间复杂度
 *
 * - **Solution1**：时间复杂度为 O(N)，其中 N 是价格数组的长度。因为我们只遍历了一次价格数组，并在每次上升区间中计算利润。
 * - **Solution2**：时间复杂度也是 O(N)。因为我们同样遍历了一次价格数组，并在每次价格上涨时累积利润。
 *
 * #### 空间复杂度
 *
 * - **Solution1**：空间复杂度为 O(1)。只使用了常数个额外变量（如 `valley`、`peak` 和 `maxprofit`）。
 * - **Solution2**：空间复杂度也为 O(1)。只使用了一个额外的变量（`maxprofit`）来记录总利润。
 *
 * ### 总结
 *
 * - **Solution1** 更直观，通过显式标记谷底和峰顶来计算利润，但略微复杂。
 * - **Solution2** 更简洁，直接计算价格上涨的利润，适用于要求空间优化的场景。
 */

public class LeetCode_122_BestTimeToBuyAndSellStockIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // Solution1
        public int maxProfit(int[] prices) {
            int i = 0;
            int valley = prices[0];
            int peak = prices[0];
            int maxprofit = 0;
            while (i < prices.length - 1) {
                // 找到 valley
                while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                    i++;
                valley = prices[i];
                // 找到 peak
                while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                    i++;
                peak = prices[i];
                // 计算利润并累加
                maxprofit += peak - valley;
            }
            return maxprofit;
        }

        // Solution2: 空间优化
        public int maxProfit2(int[] prices) {
            int maxprofit = 0;
            for (int i = 1; i < prices.length; i++) {
                // 如果价格上升，则计算利润并累加
                if (prices[i] > prices[i - 1])
                    maxprofit += prices[i] - prices[i - 1];
            }
            return maxprofit;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_122_BestTimeToBuyAndSellStockIi().new Solution();
        // 测试代码
        int[] prices = {7, 1, 5, 3, 6, 4};
        int result1 = solution.maxProfit(prices);
        int result2 = solution.maxProfit2(prices);
        System.out.println("Solution 1: " + result1); // 输出：7
        System.out.println("Solution 2: " + result2); // 输出：7
    }
}

/**
You are given an integer array prices where prices[i] is the price of a given 
stock on the iᵗʰ day. 

 On each day, you may decide to buy and/or sell the stock. You can only hold at 
most one share of the stock at any time. However, you can buy it then 
immediately sell it on the same day. 

 Find and return the maximum profit you can achieve. 

 
 Example 1: 

 
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-
1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
 

 Example 2: 

 
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-
1 = 4.
Total profit is 4.
 

 Example 3: 

 
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the 
stock to achieve the maximum profit of 0.
 

 
 Constraints: 

 
 1 <= prices.length <= 3 * 10⁴ 
 0 <= prices[i] <= 10⁴ 
 

 Related Topics Array Dynamic Programming Greedy 👍 13031 👎 2652

*/
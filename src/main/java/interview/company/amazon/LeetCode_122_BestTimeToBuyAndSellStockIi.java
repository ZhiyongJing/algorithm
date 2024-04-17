package interview.company.amazon;

/**
 *@Question:  122. Best Time to Buy and Sell Stock II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 69.12%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * 解题思路：
 * 这道题的目标是获取最大利润，可以多次买卖股票。我们可以通过遍历价格数组，找到每次价格上升的区间，然后计算利润并累加。
 *
 * Solution1:
 * - 使用两个指针 i，分别表示 valley（谷底）和 peak（峰顶）。
 * - 在 prices 数组上遍历，找到每次上升的区间（即 valley 和 peak）。
 * - 计算每次上升的区间的利润，并累加得到最终结果。
 *
 * Solution2:
 * - 基于 Solution1 的空间优化，去除 valley 和 peak 的变量。
 * - 直接遍历 prices 数组，比较当前价格和前一天的价格，如果价格上升，则计算利润并累加。
 *
 * 时间复杂度：
 * - Solution1: O(N)，其中 N 为 prices 数组的长度。
 * - Solution2: O(N)。
 *
 * 空间复杂度：
 * - Solution1: O(1)。
 * - Solution2: O(1)。
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
        leetcode.question.dp.LeetCode_122_BestTimeToBuyAndSellStockIi.Solution solution = new leetcode.question.dp.LeetCode_122_BestTimeToBuyAndSellStockIi().new Solution();
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
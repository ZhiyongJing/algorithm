package leetcode.question.dp;

/**
 *@Question:  123. 买卖股票的最佳时机 III (Best Time to Buy and Sell Stock III)
 *@Difculty:  3 [1->简单, 2->中等, 3->困难]
 *@Frequency: 52.05%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

public class LeetCode_123_BestTimeToBuyAndSellStockIii{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            // 初始化第一次买入和第二次买入的成本为最大值
            int t1Cost = Integer.MAX_VALUE,
                    t2Cost = Integer.MAX_VALUE;
            // 初始化第一次利润和第二次利润为0
            int t1Profit = 0,
                    t2Profit = 0;

            for (int price : prices) {
                // 如果当前价格更低，更新第一次买入成本
                t1Cost = Math.min(t1Cost, price);
                // 如果当前价格卖出可以获得更高利润，更新第一次利润
                t1Profit = Math.max(t1Profit, price - t1Cost);
                // 如果第一次利润减去当前价格作为新成本可以获得更低成本，更新第二次买入成本
                t2Cost = Math.min(t2Cost, price - t1Profit);
                // 如果第二次买入成本加上当前价格作为新利润可以获得更高利润，更新第二次利润
                t2Profit = Math.max(t2Profit, price - t2Cost);
            }

            return t2Profit; // 返回最终结果，即第二次卖出后的最大利润
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_123_BestTimeToBuyAndSellStockIii().new Solution();
        // 测试代码
        int[] prices = {3,3,5,0,0,3,1,4};
        int result = solution.maxProfit(prices);
        System.out.println("最大利润：" + result);
    }
}

/**
You are given an array prices where prices[i] is the price of a given stock on 
the iᵗʰ day. 

 Find the maximum profit you can achieve. You may complete at most two 
transactions. 

 Note: You may not engage in multiple transactions simultaneously (i.e., you 
must sell the stock before you buy again). 

 
 Example 1: 

 
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-
0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3. 

 Example 2: 

 
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-
1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are 
engaging multiple transactions at the same time. You must sell before buying 
again.
 

 Example 3: 

 
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 

 
 Constraints: 

 
 1 <= prices.length <= 10⁵ 
 0 <= prices[i] <= 10⁵ 
 

 Related Topics Array Dynamic Programming 👍 9365 👎 173

*/
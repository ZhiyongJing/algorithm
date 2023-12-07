package leetcode.done;
//122. Best Time to Buy and Sell Stock II
//Medium

//Time complexity : O(n)
//Space complexity: O(1)
public class LeetCode122_Best_Time_to_Buy_and_Sell_Stock_II {
    class Solution {
        public int maxProfit(int[] prices) {
            int maxprofit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1])
                    maxprofit += prices[i] - prices[i - 1];
            }
            return maxprofit;
        }
    }
}

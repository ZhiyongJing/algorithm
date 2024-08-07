package leetcode.done;
//121. Best Time to Buy and Sell Stock
//Easy

//Time complexity: O(n). Only a single pass is needed.
//Space complexity: O(1).
public class LeetCode121_Best_Time_to_Buy_and_Sell_Stock {
    public class Solution {
        public int maxProfit(int prices[]) {
            int minprice = Integer.MAX_VALUE;
            int maxprofit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minprice)
                    minprice = prices[i];
                else if (prices[i] - minprice > maxprofit)
                    maxprofit = prices[i] - minprice;
            }
            return maxprofit;
        }
    }
}

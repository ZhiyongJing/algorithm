package leetcode.question.dp;

/**
  *@Question:  121. Best Time to Buy and Sell Stock     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 97.82%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

public class LeetCode_121_BestTimeToBuyAndSellStock{
    
//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    public int maxProfit1(int prices[]) {
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
    //More dp way
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {

            minprice = Math.min(prices[i], minprice);
            maxprofit = Math.max(maxprofit, prices[i] - minprice);

        }
        return maxprofit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_121_BestTimeToBuyAndSellStock().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given an array prices where prices[i] is the price of a given stock on 
the iᵗʰ day. 

 You want to maximize your profit by choosing a single day to buy one stock and 
choosing a different day in the future to sell that stock. 

 Return the maximum profit you can achieve from this transaction. If you cannot 
achieve any profit, return 0. 

 
 Example 1: 

 
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-
1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must 
buy before you sell.
 

 Example 2: 

 
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 

 
 Constraints: 

 
 1 <= prices.length <= 10⁵ 
 0 <= prices[i] <= 10⁴ 
 

 Related Topics Array Dynamic Programming 👍 29755 👎 1054

*/
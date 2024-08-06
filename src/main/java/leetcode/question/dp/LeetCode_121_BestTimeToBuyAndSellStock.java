package leetcode.question.dp;

/**
 *@Question:  121. Best Time to Buy and Sell Stock
 *@Difficulty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 97.82%
 *@Time Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目解析
 *
 * **题目：121. 买卖股票的最佳时机**
 *
 * 给定一个数组 `prices`，其中 `prices[i]` 表示第 `i` 天的股票价格。你只能进行一次买卖操作（即买一次和卖一次股票）。请你设计一个算法来计算你所能获取的最大利润。注意你不能在买入股票前卖出股票。
 *
 * ### 解题思路
 *
 * 1. **初始化变量**：
 *    - `minprice`：表示历史中的最低买入价格，初始值为 `Integer.MAX_VALUE`（表示无穷大）。
 *    - `maxprofit`：表示最大利润，初始值为 0。
 *
 * 2. **遍历价格数组**：
 *    - 对于数组中的每一个价格 `prices[i]`：
 *      - 如果当前价格比 `minprice` 更低，更新 `minprice` 为当前价格。这代表更新最低买入价格。
 *      - 否则，计算当前价格卖出的利润 `current profit = prices[i] - minprice`，并更新 `maxprofit` 为最大值 `max(maxprofit, current profit)`。
 *
 * 3. **返回结果**：
 *    - 最后返回 `maxprofit`，即为最大可能的利润。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**：`O(N)`，其中 `N` 是 `prices` 数组的长度。我们只需要遍历一次数组，因此时间复杂度是线性的。
 * - **空间复杂度**：`O(1)`，我们只使用了常数级别的额外空间来存储 `minprice` 和 `maxprofit`。
 *
 * ### 小结
 *
 * 这个问题的关键在于找到一个最低点买入和一个最高点卖出，并且卖出点必须在买入点之后。通过一次遍历数组，我们可以在保持线性时间复杂度的同时，实时更新最低买入价格和最大利润。
 */

public class LeetCode_121_BestTimeToBuyAndSellStock {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        // 第一种实现方法
        public int maxProfit1(int prices[]) {
            int minprice = Integer.MAX_VALUE; // 最低价格初始化为无穷大
            int maxprofit = 0; // 最大利润初始化为0
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minprice) // 更新最低价格
                    minprice = prices[i];
                else if (prices[i] - minprice > maxprofit) // 更新最大利润
                    maxprofit = prices[i] - minprice;
            }
            return maxprofit; // 返回最大利润
        }

        // 更加DP的实现方法
        public int maxProfit(int prices[]) {
            int minprice = Integer.MAX_VALUE; // 最低价格初始化为无穷大
            int maxprofit = 0; // 最大利润初始化为0
            for (int i = 0; i < prices.length; i++) {
                // 更新最低价格
                minprice = Math.min(prices[i], minprice);
                // 计算并更新最大利润
                maxprofit = Math.max(maxprofit, prices[i] - minprice);
            }
            return maxprofit; // 返回最大利润
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_121_BestTimeToBuyAndSellStock().new Solution();
        // 测试样例
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("最大利润是：" + solution.maxProfit(prices));
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
package leetcode.done;
//188. Best Time to Buy and Sell Stock IV
//Hard

//Time Complexity: O(nk)
//Space Complexity:O(nk)
public class LeetCode188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public class Solution {
//        public int maxProfit(int k, int[] prices) {
//            if(prices.length < 2 || k == 0) return 0; // all buy sell on same day
//
//            // dp is a rolling dp array
//            // dp i means one of the k transactions, dp[i][0] max for hold, dp[i][1] max for sell
//            int[][] dp = new int[k][2];
//
//            for(int i = 0; i < k; i++) {
//                dp[i][0] = -prices[0];
//                dp[i][1] = 0;
//            }
//
//            for(int price: prices) {
//                int[][] newDp = new int[k][2];
//                for(int j = 0; j < k; j++) {
//                    // we can only buy after last transaction sell, or perviously already hold
//                    newDp[j][0] = j == 0? Math.max(dp[j][0], -price): Math.max(dp[j][0],dp[j-1][1]-price);
//                    newDp[j][1] = Math.max(dp[j][1], dp[j][0]+price);
//                }
//                dp = newDp;
//            }
//
//            return dp[k-1][1];
//        }
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;

            // solve special cases
            if (n <= 0 || k <= 0) {
                return 0;
            }

            if (2 * k > n) {
                int res = 0;
                for (int i = 1; i < n; i++) {
                    res += Math.max(0, prices[i] - prices[i - 1]);
                }
                return res;
            }

            // dp[i][used_k][ishold] = balance
            // ishold: 0 nothold, 1 hold
            int[][][] dp = new int[n][k + 1][2];

            // initialize the array with -inf
            // we use -1e9 here to prevent overflow
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= k; j++) {
                    dp[i][j][0] = -1000000000;
                    dp[i][j][1] = -1000000000;
                }
            }

            // set starting value
            dp[0][0][0] = 0;
            dp[0][1][1] = -prices[0];

            // fill the array
            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= k; j++) {
                    // transition equation
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    // you can't hold stock without any transaction
                    if (j > 0) {
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                    }
                }
            }

            int res = 0;
            for (int j = 0; j <= k; j++) {
                res = Math.max(res, dp[n - 1][j][0]);
            }

            return res;
        }
    }
}

package leetcode.frequent.based_on_data_structure.heap;

import java.util.PriorityQueue;

/**
  *@Question:  264. Ugly Number II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 50.55%      
  *@Time  Complexity: O(NlogN) for Heap， O(N) for dp
  *@Space Complexity: O(N) for both
 */

public class LeetCode_264_UglyNumberIi{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //Solution 1: Heap
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add(1l);
        for (int i = 0; i < n - 1; ++i) {
            long val = pq.remove();
            while (pq.size() > 0 && pq.peek() == val) pq.remove(); // remove duplicates
            pq.add(val * 2);
            pq.add(val * 3);
            pq.add(val * 5);
        }
        // return (int) pq.remove();   //
        return pq.remove().intValue();
    }
    //Solution 2: DP. 更快
    int BOUND = 1690;
    public int nthUglyNumber2(int n) {
        // dp[i] denote the i + 1 ugly numbers
        int[] dp = new int[BOUND];
        dp[0] = 1;
        // the smallest number that is not multiplied by 2, 3, 5
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < BOUND; ++i) {
            dp[i] = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            if (dp[i] == dp[i2] * 2) ++i2;
            if (dp[i] == dp[i3] * 3) ++i3;
            if (dp[i] == dp[i5] * 5) ++i5;
        }
        return dp[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_264_UglyNumberIi().new Solution();
        // TO TEST
        //solution.
    }
}
/**
An ugly number is a positive integer whose prime factors are limited to 2, 3, 
and 5. 

 Given an integer n, return the nᵗʰ ugly number. 

 
 Example 1: 

 
Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 
ugly numbers.
 

 Example 2: 

 
Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are 
limited to 2, 3, and 5.
 

 
 Constraints: 

 
 1 <= n <= 1690 
 

 Related Topics Hash Table Math Dynamic Programming Heap (Priority Queue) 👍 580
4 👎 295

*/
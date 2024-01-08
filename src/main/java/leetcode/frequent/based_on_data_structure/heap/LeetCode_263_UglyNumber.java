package leetcode.frequent.based_on_data_structure.heap;

/**
  *@Question:  263. Ugly Number     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 42.44%      
  *@Time  Complexity: O(logN)
  *@Space Complexity: O(1)
 */

public class LeetCode_263_UglyNumber{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isUgly(int n) {
        // A non-positive integer cannot be ugly
        if (n <= 0) {
            return false;
        }

        // Factorize by dividing with permitted factors
        for (int factor : new int[] { 2, 3, 5 }) {
            n = keepDividingWhenDivisible(n, factor);
        }
        System.out.println(n);

        // Check if the integer is reduced to 1 or not.
        return n == 1;
    }

    // Keep dividing dividend by divisor when division is possible.
    private int keepDividingWhenDivisible(int dividend, int divisor) {
        while (dividend % divisor == 0) {
            dividend /= divisor;
        }
        return dividend;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_263_UglyNumber().new Solution();
        // TO TEST
        //solution.
    }
}
/**
An ugly number is a positive integer whose prime factors are limited to 2, 3, 
and 5. 

 Given an integer n, return true if n is an ugly number. 

 
 Example 1: 

 
Input: n = 6
Output: true
Explanation: 6 = 2 × 3
 

 Example 2: 

 
Input: n = 1
Output: true
Explanation: 1 has no prime factors, therefore all of its prime factors are 
limited to 2, 3, and 5.
 

 Example 3: 

 
Input: n = 14
Output: false
Explanation: 14 is not ugly since it includes the prime factor 7.
 

 
 Constraints: 

 
 -2³¹ <= n <= 2³¹ - 1 
 

 Related Topics Math 👍 3198 👎 1662

*/
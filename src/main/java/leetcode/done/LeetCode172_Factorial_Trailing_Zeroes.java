package leetcode.done;

//172. Factorial Trailing Zeroes
//Medium
// O(logN)
public class LeetCode172_Factorial_Trailing_Zeroes {
    class Solution {
        public int trailingZeroes(int n) {
            int zeroCount = 0;
            // We need to use long because currentMultiple can potentially become
            // larger than an int.
            long currentMultiple = 5;
            while (n >= currentMultiple) {
                zeroCount += (n / currentMultiple);
                currentMultiple *= 5;
            }
            return zeroCount;
        }
    }
}

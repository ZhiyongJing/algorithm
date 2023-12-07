package leetcode.done;
//201. Bitwise AND of Numbers Range
//Medium
public class LeetCode201_Bitwise_AND_Of_Numbers_Range {
    class Solution {
        public int rangeBitwiseAnd(int m, int n) {
            int shift = 0;
            // find the common 1-bits
            while (m < n) {
                m >>= 1;
                n >>= 1;
                ++shift;
            }
            return m << shift;
        }
    }
}

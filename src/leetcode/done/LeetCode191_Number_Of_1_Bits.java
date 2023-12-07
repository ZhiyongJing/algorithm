package leetcode.done;
//191. Number of 1 Bits
//Easy
//Running time O(1)
public class LeetCode191_Number_Of_1_Bits {
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int bits = 0;
            int mask = 1;
            for (int i = 0; i < 32; i++) {
                if ((n & mask) != 0) {
                    bits++;
                }
                mask <<= 1;
            }
            return bits;
        }
    }
}

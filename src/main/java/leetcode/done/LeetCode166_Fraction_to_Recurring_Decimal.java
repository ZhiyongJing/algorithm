package leetcode.done;

import java.util.HashMap;
import java.util.Map;

//166. Fraction to Recurring Decimal
//Medium
public class LeetCode166_Fraction_to_Recurring_Decimal {
    static class  Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) {
                return "0";
            }
            StringBuilder fraction = new StringBuilder();
            // If either one is negative (not both)
            if (numerator < 0 ^ denominator < 0) {
                fraction.append("-");
            }
            // Convert to Long or else abs(-2147483648) overflows
            long dividend = Math.abs(Long.valueOf(numerator));
            long divisor = Math.abs(Long.valueOf(denominator));
            fraction.append(String.valueOf(dividend / divisor));
            long remainder = dividend % divisor;
            if (remainder == 0) {
                return fraction.toString();
            }
            fraction.append(".");
            Map<Long, Integer> map = new HashMap<>();

            while (remainder != 0) {
                if (map.containsKey(remainder)) {
                    fraction.insert(map.get(remainder), "(");
                    fraction.append(")");
                    break;
                }
                map.put(remainder, fraction.length());
                remainder *= 10;
                fraction.append(String.valueOf(remainder / divisor));
                remainder %= divisor;
            }
            return fraction.toString();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.fractionToDecimal(2,1));
    }
}

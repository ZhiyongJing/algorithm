package leetcode.done;

//171. Excel Sheet Column Number
//Easy
//O(n)
public class LeetCode171_Excel_Sheet_Column_Number {
    class Solution {
        public int titleToNumber(String s) {
            int result = 0;
            int n = s.length();
            for (int i = 0; i < n; i++) {
                result = result * 26;
                // In Java, subtracting characters is subtracting ASCII values of characters
                result += (s.charAt(i) - 'A' + 1);
            }
            return result;
        }
    }
}

package leetcode.question.string_list;

/**
 *@Question:  8. String to Integer (atoi)
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 62.38%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * 这道题目要求实现一个字符串转换成整数的函数，即将字符串表示的数字转换为整数。要求考虑输入可能存在的各种情况，包括空格、正负号、非数字字符等。
 *
 * 解题思路如下：
 * 1. 首先去除字符串开头的空格。
 * 2. 然后判断字符串的第一个字符是否为正负号，若存在则更新符号位，同时将索引指向下一个字符。
 * 3. 接着遍历字符串中的数字字符，将其转换为对应的数字值，并逐步构建整数结果。在此过程中，需要注意判断是否存在溢出的情况。
 * 4. 最后根据符号位返回结果。
 *
 * 时间复杂度：由于需要遍历字符串中的所有字符，因此时间复杂度为 O(N)，其中 N 为字符串的长度。
 * 空间复杂度：由于只需要几个额外的变量来存储中间结果，因此空间复杂度为 O(1)。
 */
public class LeetCode_8_StringToIntegerAtoi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义Solution类
        public int myAtoi(String input) {
            int sign = 1; // 符号位，表示正负号，默认为正
            int result = 0; // 结果
            int index = 0; // 索引，指向当前遍历的字符
            int n = input.length(); // 输入字符串的长度

            // 从输入字符串的开头去除所有空格
            while (index < n && input.charAt(index) == ' ') {
                index++;
            }

            // 若存在正负号，则更新符号位
            if (index < n && input.charAt(index) == '+') {
                sign = 1;
                index++;
            } else if (index < n && input.charAt(index) == '-') {
                sign = -1;
                index++;
            }

            // 遍历输入字符串中的数字字符，直到遇到非数字字符为止
            while (index < n && Character.isDigit(input.charAt(index))) {
                int digit = input.charAt(index) - '0'; // 当前数字字符对应的数字值

                // 检查是否溢出
                if (
                        (result > Integer.MAX_VALUE / 10) ||
                                (result == Integer.MAX_VALUE / 10 &&
                                        digit > Integer.MAX_VALUE % 10)
                ) {
                    // 若溢出，根据符号位返回最大或最小整数值
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                // 将当前数字字符添加到结果中
                result = 10 * result + digit;
                index++;
            }

            // 返回结果，根据符号位决定正负
            return sign * result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_8_StringToIntegerAtoi().new Solution();
        // TO TEST
        // 示例测试用例
        System.out.println(solution.myAtoi("42")); // 应该返回 42
    }
}

/**
Implement the myAtoi(string s) function, which converts a string to a 32-bit 
signed integer. 

 The algorithm for myAtoi(string s) is as follows: 

 
 Whitespace: Ignore any leading whitespace (" "). 
 Signedness: Determine the sign by checking if the next character is '-' or '+',
 assuming positivity is neither present. 
 Conversion: Read the integer by skipping leading zeros until a non-digit 
character is encountered or the end of the string is reached. If no digits were read, 
then the result is 0. 
 Rounding: If the integer is out of the 32-bit signed integer range [-2³¹, 2³¹ -
 1], then round the integer to remain in the range. Specifically, integers less 
than -2³¹ should be rounded to -2³¹, and integers greater than 2³¹ - 1 should 
be rounded to 2³¹ - 1. 
 

 Return the integer as the final result. 

 
 Example 1: 

 
 Input: s = "42" 
 

 Output: 42 

 Explanation: 

 
The underlined characters are what is read in and the caret is the current 
reader position.
Step 1: "42" (no characters read because there is no leading whitespace)
         ^
Step 2: "42" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "42" ("42" is read in)
           ^
 


 Example 2: 

 
 Input: s = " -042" 
 

 Output: -42 

 Explanation: 

 
Step 1: "   -042" (leading whitespace is read and ignored)
            ^
Step 2: "   -042" ('-' is read, so the result should be negative)
             ^
Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
               ^
 


 Example 3: 

 
 Input: s = "1337c0d3" 
 

 Output: 1337 

 Explanation: 

 
Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
         ^
Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character 
is a non-digit)
             ^
 


 Example 4: 

 
 Input: s = "0-1" 
 

 Output: 0 

 Explanation: 

 
Step 1: "0-1" (no characters read because there is no leading whitespace)
         ^
Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "0-1" ("0" is read in; reading stops because the next character is a 
non-digit)
          ^
 


 Example 5: 

 
 Input: s = "words and 987" 
 

 Output: 0 

 Explanation: 

 Reading stops at the first non-digit character 'w'. 

 
 Constraints: 

 
 0 <= s.length <= 200 
 s consists of English letters (lower-case and upper-case), digits (0-9), ' ', 
'+', '-', and '.'. 
 

 Related Topics String 👍 4421 👎 13546

*/
package leetcode.question.string_list;

/**
 * @Question:  65. Valid Number
 * @Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 61.72%
 * @Time  Complexity: O(N)
 * @Space Complexity: O(1)
 */

/**
 * 这段代码是用来判断一个给定的字符串是否表示一个有效的数字。其主要思路是遍历字符串中的每个字符，
 * 并根据数字、指数和小数点的规则进行检查。以下是详细的解题思路：
 *
 * 1. **初始化标志位**：
 *    - `seenDigit`：用于记录是否遇到过数字。
 *    - `seenExponent`：用于记录是否遇到过指数符号（'e' 或 'E'）。
 *    - `seenDot`：用于记录是否遇到过小数点。
 *
 * 2. **遍历字符串**：
 *    - 对于字符串中的每个字符，根据其类型进行不同的处理。
 *    - 如果是数字字符，则将 `seenDigit` 设置为 true。
 *    - 如果是正负号字符（'+' 或 '-'），则判断其是否位于开头或紧跟在指数符号后面，若不是则返回 false。
 *    - 如果是指数符号，则判断其前面是否已经遇到过数字，并且之前不能已经遇到过指数符号，若不符合条件则返回 false。同时，
 *    将 `seenExponent` 置为 true，并将 `seenDigit` 重置为 false，因为指数部分也需要检查是否有数字。
 *    - 如果是小数点，则判断其前面是否已经遇到过小数点或指数符号，若已经遇到过则返回 false。将 `seenDot` 置为 true。
 *    - 如果是其他字符，则直接返回 false。
 *
 * 3. **最终判断**：
 *    - 最后返回 `seenDigit` 的值，表示字符串中是否出现过数字。
 *
 * 时间复杂度：遍历整个字符串需要 O(N) 的时间，其中 N 是字符串的长度。
 *
 * 空间复杂度：只使用了常量级的额外空间来存储标志位，因此空间复杂度为 O(1)。
 *
 * 这样，该算法实现了对字符串是否表示有效数字的判断，并且具有线性的时间复杂度和常量的空间复杂度。
 */
public class LeetCode_65_ValidNumber{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 判断给定字符串是否是一个有效数字
         *
         * @param s 给定的字符串
         * @return 如果是有效数字，则返回true；否则返回false
         */
        public boolean isNumber(String s) {
            // 初始化标志位，用于记录是否出现过数字、指数和小数点
            boolean seenDigit = false;
            boolean seenExponent = false;
            boolean seenDot = false;

            // 遍历字符串中的每个字符
            for (int i = 0; i < s.length(); i++) {
                char curr = s.charAt(i);
                if (Character.isDigit(curr)) {
                    seenDigit = true; // 如果当前字符是数字，则标记为出现过数字
                } else if (curr == '+' || curr == '-') {
                    // 如果当前字符是加号或减号，则必须位于开头，或者紧跟在 'e' 或 'E' 后面
                    if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                        return false;
                    }
                } else if (curr == 'e' || curr == 'E') {
                    // 如果当前字符是 'e' 或 'E'，则前面必须已经出现过数字，并且还不能出现过指数
                    if (seenExponent || !seenDigit) {
                        return false;
                    }
                    seenExponent = true; // 标记为出现过指数
                    seenDigit = false; // 重置数字标志位，因为指数部分也需要判断是否出现数字
                } else if (curr == '.') {
                    // 如果当前字符是小数点，则之前不能出现过小数点或指数
                    if (seenDot || seenExponent) {
                        return false;
                    }
                    seenDot = true; // 标记为出现过小数点
                } else {
                    return false; // 如果是其他字符，则直接返回false
                }
            }

            return seenDigit; // 最终返回数字标志位，表示是否出现过数字
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_65_ValidNumber().new Solution();
        // 测试
        String[] testCases = {"0", " 0.1 ", "abc", "1 a", "2e10", "-90e3", "1e", "e3", "6e-1", "99e2.5", "53.5e93", "-123", "3.1416", "-1E-16", "0123", "12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"};
        for (String testCase : testCases) {
            System.out.println("Is \"" + testCase + "\" a valid number? " + solution.isNumber(testCase));
        }
    }
}

/**
 Given a string s, return whether s is a valid number. For example, all the
 following are valid numbers: "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90
 E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789", while the following are not
 valid numbers: "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53".

 Formally, a valid number is defined using one of the following definitions:


 An integer number followed by an optional exponent.
 A decimal number followed by an optional exponent.


 An integer number is defined with an optional sign '-' or '+' followed by
 digits.

 A decimal number is defined with an optional sign '-' or '+' followed by one
 of the following definitions:


 Digits followed by a dot '.'.
 Digits followed by a dot '.' followed by digits.
 A dot '.' followed by digits.


 An exponent is defined with an exponent notation 'e' or 'E' followed by an
 integer number.

 The digits are defined as one or more digits.


 Example 1:


 Input: s = "0"


 Output: true

 Example 2:


 Input: s = "e"


 Output: false

 Example 3:


 Input: s = "."


 Output: false


 Constraints:


 1 <= s.length <= 20
 s consists of only English letters (both uppercase and lowercase), digits (0-9)
 , plus '+', minus '-', or dot '.'.


 Related Topics String 👍 1226 👎 1960

 */
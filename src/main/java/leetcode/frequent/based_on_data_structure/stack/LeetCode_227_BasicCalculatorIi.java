package leetcode.frequent.based_on_data_structure.stack;
import java.util.Stack;

/**
 *@Question:  227. Basic Calculator II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 80.27%
 *@Time  Complexity: O(N) - N 是字符串的长度
 *@Space Complexity: O(N) - N 是字符串的长度
 */

/**
 * **解题思路：**
 *
 * 这题是一个基本计算器的问题，要求实现对一个包含非负整数、加法、减法、乘法、除法以及空格字符的字符串进行运算。
 * 这里采用栈的数据结构来辅助计算。
 *
 * 具体思路如下：
 *
 * 1. 使用一个栈来保存数字和运算符。初始化数字为0，运算符为 '+'，准备开始第一个数字的计算。
 * 2. 遍历字符串，对于每一个字符：
 *    - 如果是数字字符，构建完整的数字，同时更新当前的运算符。
 *    - 如果是运算符或者字符串的末尾，根据之前保存的运算符进行相应的计算，并将结果入栈。
 * 3. 最后，栈中的所有结果相加，得到最终结果。
 *
 * **时间复杂度：**
 *
 * 遍历字符串的过程是O(N)，其中 N 是输入字符串的长度。
 *
 * **空间复杂度：**
 *
 * 在最坏情况下，所有的字符都会入栈，最终的空间复杂度是O(N)，其中 N 是输入字符串的长度。
 */

public class LeetCode_227_BasicCalculatorIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int calculate(String s) {

            if (s == null || s.isEmpty()) return 0;
            int len = s.length();
            Stack<Integer> stack = new Stack<Integer>();
            int currentNumber = 0;
            char operation = '+';

            for (int i = 0; i < len; i++) {
                char currentChar = s.charAt(i);

                if (Character.isDigit(currentChar)) {
                    // 构建当前数字
                    currentNumber = (currentNumber * 10) + (currentChar - '0');
                }

                if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                    // 遇到非数字字符或字符串末尾时，根据之前保存的操作符进行运算，并将结果入栈
                    if (operation == '-') {
                        stack.push(-currentNumber);
                    } else if (operation == '+') {
                        stack.push(currentNumber);
                    } else if (operation == '*') {
                        stack.push(stack.pop() * currentNumber);
                    } else if (operation == '/') {
                        stack.push(stack.pop() / currentNumber);
                    }
                    // 更新操作符和当前数字
                    operation = currentChar;
                    currentNumber = 0;
                }
            }

            int result = 0;
            // 计算栈中的所有结果相加，得到最终结果
            while (!stack.isEmpty()) {
                result += stack.pop();
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_227_BasicCalculatorIi.Solution solution = new LeetCode_227_BasicCalculatorIi().new Solution();

        // 测试代码
        int result = solution.calculate("3+2*2"); // 返回 7
        System.out.println("计算结果: " + result);

        result = solution.calculate(" 3/2 "); // 返回 1
        System.out.println("计算结果: " + result);

        result = solution.calculate(" 3+5 / 2 "); // 返回 5
        System.out.println("计算结果: " + result);
    }
}

/**
Given a string s which represents an expression, evaluate this expression and 
return its value. 

 The integer division should truncate toward zero. 

 You may assume that the given expression is always valid. All intermediate 
results will be in the range of [-2³¹, 2³¹ - 1]. 

 Note: You are not allowed to use any built-in function which evaluates strings 
as mathematical expressions, such as eval(). 

 
 Example 1: 
 Input: s = "3+2*2"
Output: 7
 
 Example 2: 
 Input: s = " 3/2 "
Output: 1
 
 Example 3: 
 Input: s = " 3+5 / 2 "
Output: 5
 
 
 Constraints: 

 
 1 <= s.length <= 3 * 10⁵ 
 s consists of integers and operators ('+', '-', '*', '/') separated by some 
number of spaces. 
 s represents a valid expression. 
 All the integers in the expression are non-negative integers in the range [0, 2
³¹ - 1]. 
 The answer is guaranteed to fit in a 32-bit integer. 
 

 Related Topics Math String Stack 👍 5971 👎 792

*/
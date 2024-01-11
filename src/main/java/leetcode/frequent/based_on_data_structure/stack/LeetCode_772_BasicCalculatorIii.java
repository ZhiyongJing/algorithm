package leetcode.frequent.based_on_data_structure.stack;

/**
  *@Question:  772. Basic Calculator III     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 59.14%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 这个题目是一个基本计算器的问题，涉及到表达式中的加减乘除和括号运算。下面是代码的解题思路详细讲解：
 *
 * 1. **辅助函数 evaluate:**
 *    - 该函数用于计算两个操作数的结果，根据给定的运算符进行相应的运算。
 *    - 输入：运算符(operator)、两个操作数(first 和 second)。
 *    - 输出：计算结果的字符串形式。
 *
 * 2. **主函数 calculate:**
 *    - 使用栈来辅助计算表达式的值。
 *    - 遍历输入字符串 s 中的每个字符，处理数字、运算符和括号。
 *    - 使用 curr 变量记录当前数字。
 *    - 使用 previousOperator 变量记录前一个运算符，默认为 '+'。
 *    - 在遍历过程中，根据不同情况进行处理：
 *      - 遇到数字字符时，累加到 curr 中。
 *      - 遇到左括号时，将前一个运算符入栈，并重置前一个运算符为 '+'。
 *      - 遇到右括号时，弹出栈中的元素，计算括号内的结果，并更新 curr 和前一个运算符。
 *      - 遇到其他运算符时，根据前一个运算符的优先级决定是否进行计算，然后更新 curr 和前一个运算符。
 *      - 遍历结束后，栈中的元素即为最终的计算结果。
 *
 * 3. **时间复杂度：**
 *    - 时间复杂度为 O(n)，其中 n 为输入字符串 s 的长度。每个字符只需要遍历一次。
 *
 * 4. **空间复杂度：**
 *    - 空间复杂度为 O(n)，主要是由栈的使用造成的。在最坏情况下，栈的大小与输入字符串长度相等。
 *
 * 综上所述，该算法通过使用栈来处理表达式中的括号和运算符，通过一次遍历字符串即可得到最终的计算结果。
 * 算法的时间复杂度和空间复杂度都是相对较低的。
 */

public class LeetCode_772_BasicCalculatorIii{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 辅助函数，用于计算两个操作数的结果
        private String evaluate(char operator, String first, String second) {
            int x = Integer.parseInt(first);
            int y = Integer.parseInt(second);
            int res = 0;

            if (operator == '+') {
                res = x;
            } else if (operator == '-') {
                res = -x;
            } else if (operator == '*') {
                res = x * y;
            } else {
                res = x / y;
            }

            return Integer.toString(res);
        }

        // 主函数，计算表达式的值
        public int calculate(String s) {
            Stack<String> stack = new Stack<>(); // 使用栈来辅助计算
            String curr = "";
            char previousOperator = '+';
            s += "@"; // 添加一个结束标志，方便处理最后一个操作数
            Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

            for (char c: s.toCharArray()) {
                if (Character.isDigit(c)) {
                    curr += c;
                } else if (c == '(') {
                    stack.push("" + previousOperator); // 将前一个操作符转换为字符串后入栈
                    previousOperator = '+';
                } else {
                    if (previousOperator == '*' || previousOperator == '/') {
                        stack.push(evaluate(previousOperator, stack.pop(), curr));
                    } else {
                        stack.push(evaluate(previousOperator, curr, "0"));
                    }

                    curr = "";
                    previousOperator = c;
                    if (c == ')') {
                        int currentTerm = 0;
                        while (!operators.contains(stack.peek())) {
                            currentTerm += Integer.parseInt(stack.pop());
                        }

                        curr = Integer.toString(currentTerm);
                        previousOperator = stack.pop().charAt(0); // 将栈中弹出的字符串转换为字符
                    }
                }
            }

            int ans = 0;
            for (String num: stack) {
                ans += Integer.parseInt(num);
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_772_BasicCalculatorIii().new Solution();
        // 测试代码
        // int result = solution.calculate("your_test_expression");
        // System.out.println(result);
    }
}

/**
Implement a basic calculator to evaluate a simple expression string. 

 The expression string contains only non-negative integers, '+', '-', '*', '/' 
operators, and open '(' and closing parentheses ')'. The integer division should 
truncate toward zero. 

 You may assume that the given expression is always valid. All intermediate 
results will be in the range of [-2³¹, 2³¹ - 1]. 

 Note: You are not allowed to use any built-in function which evaluates strings 
as mathematical expressions, such as eval(). 

 
 Example 1: 

 
Input: s = "1+1"
Output: 2
 

 Example 2: 

 
Input: s = "6-4/2"
Output: 4
 

 Example 3: 

 
Input: s = "2*(5+5*2)/3+(6/2+8)"
Output: 21
 

 
 Constraints: 

 
 1 <= s <= 10⁴ 
 s consists of digits, '+', '-', '*', '/', '(', and ')'. 
 s is a valid expression. 
 

 Related Topics Math String Stack Recursion 👍 1100 👎 282

*/
package leetcode.question.stack;
import java.util.Stack;
/**
  *@Question:  224. Basic Calculator     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 67.53%      
  *@Time  Complexity: O(N) - N 是字符串的长度
  *@Space Complexity: O(N) - N 是字符串的长度
 */

/**
 * 这是一个基本计算器的实现，可以处理加法、减法和括号。以下是解题思路：
 *
 * 1. 使用栈来保存中间结果和操作数，以及符号。
 * 2. 遍历输入字符串，处理每个字符：
 *    - 如果是数字，构建完整的操作数。
 *    - 如果是'+'，将之前的结果加上当前的操作数。
 *    - 如果是'-'，将之前的结果减去当前的操作数。
 *    - 如果是'('，将之前的结果和符号入栈，然后重置结果和符号。
 *    - 如果是')'，先计算当前结果和符号，再加到栈顶的下一个操作数上。
 * 3. 最后，将栈中的所有结果相加即可得到最终结果。
 *
 * 时间复杂度：O(N)，其中 N 是输入字符串的长度。我们只需一次遍历字符串。
 *
 * 空间复杂度：O(N)，最坏情况下，所有的字符都入栈，其中 N 是输入字符串的长度。
 *
 * 在代码中，我们使用了一个栈来保存中间结果，一个变量 `operand` 来构建操作数，一个变量 `result` 来保存当前的结果，
 * 以及一个变量 `sign` 来表示当前的符号。
 * 栈中保存了两类元素，一个是结果，一个是符号，因此栈的深度最多为括号的层数。
 */


public class LeetCode_224_BasicCalculator {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int calculate(String s) {

            Stack<Integer> stack = new Stack<Integer>();
            int operand = 0;
            int result = 0; // 用于保存当前的运算结果
            int sign = 1;  // 1 表示正数，-1 表示负数

            for (int i = 0; i < s.length(); i++) {
                System.out.println(s +"==" + stack);

                char ch = s.charAt(i);
                if (Character.isDigit(ch)) {

                    // 构建操作数，因为数字可能是多位数
                    operand = 10 * operand + (int) (ch - '0');

                } else if (ch == '+') {

                    // 对左侧表达式进行求值，使用 result、sign、operand
                    result += sign * operand;

                    // 保存最近遇到的 '+' 符号
                    sign = 1;

                    // 重置操作数
                    operand = 0;

                } else if (ch == '-') {

                    // 对左侧表达式进行求值
                    result += sign * operand;
                    sign = -1;
                    operand = 0;

                } else if (ch == '(') {

                    // 将当前结果和符号入栈，为以后使用
                    // 先入栈结果，再入栈符号
                    stack.push(result);
                    stack.push(sign);

                    // 重置操作数和结果，新的子表达式开始新的求值
                    sign = 1;
                    result = 0;

                } else if (ch == ')') {

                    // 对左侧表达式进行求值
                    result += sign * operand;

                    // ')' 标志着括号内表达式的结束
                    // 它的结果乘以栈顶的符号，再加上栈顶的结果
                    result *= stack.pop();

                    // 然后加到栈顶的下一个操作数上
                    // 因为 stack.pop() 是在这个括号前计算的结果
                    // (栈顶的操作数) + (栈顶的符号 * 括号内计算的结果)
                    result += stack.pop();

                    // 重置操作数
                    operand = 0;
                }
            }
            // 返回最终结果
            return result + (sign * operand);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_224_BasicCalculator.Solution solution = new LeetCode_224_BasicCalculator().new Solution();

        // 测试代码
        int result = solution.calculate("1 + 1"); // 返回 2
        System.out.println("计算结果: " + result);

        result = solution.calculate(" 2-1 + 2 "); // 返回 3
        System.out.println("计算结果: " + result);

        result = solution.calculate("(1+(4+5+2)-3)+(6+8)"); // 返回 23
        System.out.println("计算结果: " + result);
    }
}


/**
Given a string s representing a valid expression, implement a basic calculator 
to evaluate it, and return the result of the evaluation. 

 Note: You are not allowed to use any built-in function which evaluates strings 
as mathematical expressions, such as eval(). 

 
 Example 1: 

 
Input: s = "1 + 1"
Output: 2
 

 Example 2: 

 
Input: s = " 2-1 + 2 "
Output: 3
 

 Example 3: 

 
Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23
 

 
 Constraints: 

 
 1 <= s.length <= 3 * 10⁵ 
 s consists of digits, '+', '-', '(', ')', and ' '. 
 s represents a valid expression. 
 '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid). 
 '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid). 
 There will be no two consecutive operators in the input. 
 Every number and running calculation will fit in a signed 32-bit integer. 
 

 Related Topics Math String Stack Recursion 👍 6050 👎 448

*/
package leetcode.done;

import java.util.Stack;

public class LeetCode224_Basic_Calculator {
    // class Solution {
//     int i;

//     public int calculate(String s) {

//         int operand = 0;
//         int result = 0; // For the on-going result
//         int nextOperandSign = 1;  // 1 means positive, -1 means negative

//         while(i < s.length()) {

//             char ch = s.charAt(i++);

//             if (ch == ' ' || Character.isDigit(ch)) {
//                 operand = (ch == ' ') ? operand : 10 * operand + (ch - '0');

//             } else if (ch == '(') {
//                 operand = calculate(s);

//             } else if (ch == ')') {
//                 break; // Sub-expression we were evaluating has ended. Exit now...

//             } else {
//                 // If we're here, we just read the operator for the next operand/expression.
//                 // Evaluate the existing expression already read, reset operand, set sign for next incoming operand.
//                 result += nextOperandSign * operand;
//                 nextOperandSign = ch == '+' ? 1 : -1;
//                 operand = 0;
//             }
//         }

    //         return result + (nextOperandSign * operand);
//     }
// }
    static class Solution {
        public int calculate(String s) {

            Stack<Integer> stack = new Stack<Integer>();
            int operand = 0;
            int result = 0; // For the on-going result
            int sign = 1;  // 1 means positive, -1 means negative

            for (int i = 0; i < s.length(); i++) {
                System.out.println("stack is: " + stack.toString());
                System.out.println("resutl is: " + result);
//                System.out.println("operand is: " + operand);
//                System.out.println("sign is: " + sign);
                char ch = s.charAt(i);
                if (Character.isDigit(ch)) {

                    // Forming operand, since it could be more than one digit
                    operand = 10 * operand + (int) (ch - '0');

                } else if (ch == '+') {

                    // Evaluate the expression to the left,
                    // with result, sign, operand
                    result += sign * operand;

                    // Save the recently encountered '+' sign
                    sign = 1;

                    // Reset operand
                    operand = 0;

                } else if (ch == '-') {

                    result += sign * operand;
                    sign = -1;
                    operand = 0;

                } else if (ch == '(') {

                    // Push the result and sign on to the stack, for later
                    // We push the result first, then sign
                    stack.push(result);
                    stack.push(sign);

                    // Reset operand and result, as if new evaluation begins for the new sub-expression
                    sign = 1;
                    result = 0;

                } else if (ch == ')') {

                    // Evaluate the expression to the left
                    // with result, sign and operand
                    result += sign * operand;

                    // ')' marks end of expression within a set of parenthesis
                    // Its result is multiplied with sign on top of stack
                    // as stack.pop() is the sign before the parenthesis
                    result *= stack.pop();

                    // Then add to the next operand on the top.
                    // as stack.pop() is the result calculated before this parenthesis
                    // (operand on stack) + (sign on stack * (result from parenthesis))
                    result += stack.pop();

                    // Reset the operand
                    operand = 0;
                }
            }
            return result + (sign * operand);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        s.calculate("1 + 3 + (5 + 7) + 9");
        s.calculate("1 + 2");

    }
}

package leetcode.done;

import java.util.Stack;

public class LeetCode227_Basic_Calculator_II {
    static class Solution {
        public int calculate(String s) {

            if (s == null || s.isEmpty()) return 0;
            int len = s.length();
            Stack<Integer> stack = new Stack<Integer>();
            int currentNumber = 0;
            char operation = '+';
            for (int i = 0; i < len; i++) {
                System.out.println("stack is: " + stack.toString());
                char currentChar = s.charAt(i);
                if (Character.isDigit(currentChar)) {
                    currentNumber = (currentNumber * 10) + (currentChar - '0');
                }
                if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                    if (operation == '-') {
                        stack.push(-currentNumber);
                    }
                    else if (operation == '+') {
                        stack.push(currentNumber);
                    }
                    else if (operation == '*') {
                        stack.push(stack.pop() * currentNumber);
                    }
                    else if (operation == '/') {
                        stack.push(stack.pop() / currentNumber);
                    }
                    operation = currentChar;
                    currentNumber = 0;
                }
            }
            int result = 0;
            while (!stack.isEmpty()) {
                result += stack.pop();
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.calculate("3+2*2"));
    }



}

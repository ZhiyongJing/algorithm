package leetcode.frequent.based_on_data_structure.stack;

import java.util.Stack;

/**
  *@Question:  150. Evaluate Reverse Polish Notation     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 45.8%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

public class LeetCode_150_EvaluateReversePolishNotation{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            if (!"+-*/".contains(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int number2 = stack.pop();
            int number1 = stack.pop();

            int result = 0;

            switch (token) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    result = number1 / number2;
                    break;
            }

            stack.push(result);

        }

        return stack.pop();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_150_EvaluateReversePolishNotation().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given an array of strings tokens that represents an arithmetic 
expression in a Reverse Polish Notation. 

 Evaluate the expression. Return an integer that represents the value of the 
expression. 

 Note that: 

 
 The valid operators are '+', '-', '*', and '/'. 
 Each operand may be an integer or another expression. 
 The division between two integers always truncates toward zero. 
 There will not be any division by zero. 
 The input represents a valid arithmetic expression in a reverse polish 
notation. 
 The answer and all the intermediate calculations can be represented in a 32-
bit integer. 
 

 
 Example 1: 

 
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
 

 Example 2: 

 
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
 

 Example 3: 

 
Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 

 
 Constraints: 

 
 1 <= tokens.length <= 10⁴ 
 tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the 
range [-200, 200]. 
 

 Related Topics Array Math Stack 👍 6856 👎 1005

*/
package leetcode.question.stack;

import java.util.HashMap;
import java.util.Stack;

/**
  *@Question:  20. Valid Parentheses     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 98.77%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_20_ValidParentheses{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // Hash table that takes care of the mappings.
    private HashMap<Character, Character> mappings;

    // Initialize hash map with mappings. This simply makes the code easier to read.
    public Solution() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid(String s) {

        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (this.mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_20_ValidParentheses().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 determine if the input string is valid. 

 An input string is valid if: 

 
 Open brackets must be closed by the same type of brackets. 
 Open brackets must be closed in the correct order. 
 Every close bracket has a corresponding open bracket of the same type. 
 

 
 Example 1: 

 
Input: s = "()"
Output: true
 

 Example 2: 

 
Input: s = "()[]{}"
Output: true
 

 Example 3: 

 
Input: s = "(]"
Output: false
 

 
 Constraints: 

 
 1 <= s.length <= 10⁴ 
 s consists of parentheses only '()[]{}'. 
 

 Related Topics String Stack 👍 23067 👎 1612

*/
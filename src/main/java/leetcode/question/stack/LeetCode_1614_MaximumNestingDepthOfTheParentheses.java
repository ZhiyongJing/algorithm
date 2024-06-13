package leetcode.question.stack;

import java.util.Stack;
/**
  *@Question:  1614. Maximum Nesting Depth of the Parentheses     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 43.61%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

public class LeetCode_1614_MaximumNestingDepthOfTheParentheses{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxDepth(String s) {
        int ans = 0;

        Stack<Character> st = new Stack<Character>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                st.pop();
            }

            ans = Math.max(ans, st.size());
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_1614_MaximumNestingDepthOfTheParentheses().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a valid parentheses string s, return the nesting depth of s. The nesting 
depth is the maximum number of nested parentheses. 

 
 Example 1: 

 
 Input: s = "(1+(2*3)+((8)/4))+1" 
 

 Output: 3 

 Explanation: 

 Digit 8 is inside of 3 nested parentheses in the string. 

 Example 2: 

 
 Input: s = "(1)+((2))+(((3)))" 
 

 Output: 3 

 Explanation: 

 Digit 3 is inside of 3 nested parentheses in the string. 

 Example 3: 

 
 Input: s = "()(())((()()))" 
 

 Output: 3 

 
 Constraints: 

 
 1 <= s.length <= 100 
 s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'. 
 It is guaranteed that parentheses expression s is a VPS. 
 

 Related Topics String Stack 👍 2368 👎 494

*/
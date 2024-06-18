package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
  *@Question:  394. Decode String     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 79.05%      
  *@Time  Complexity: O(N * maxK) where maxK is the maximum value of k and n is the length of a given string s
  *@Space Complexity: O(n)
 */

public class LeetCode_394_DecodeString{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //solution1: recursion
    int index = 0;
    String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index)))
                result.append(s.charAt(index++));
            else {
                int k = 0;
                // build k while next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index)))
                    k = k * 10 + s.charAt(index++) - '0';

                // ignore the opening bracket '['
                index++;
                String decodedString = decodeString(s);
                // ignore the closing bracket ']'
                index++;
                // build k[decodedString] and append to the result
                System.out.println(decodedString);
                while (k-- > 0)

                    result.append(decodedString);
            }
        }
        return new String(result);
    }

    //Solution2: Stack
    public String decodeString2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();
                // get the encoded string
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                // pop [ from the stack
                stack.pop();
                int base = 1;
                int k = 0;
                // get the number k
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString], by pushing decodedString k times into stack
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            }
            // push the current character to stack
            else {
                stack.push(s.charAt(i));
            }
        }
        // get the result from stack
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }
}

//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_394_DecodeString().new Solution();
        String s = "abcdefg";
        int index = 0;
        System.out.println(s.charAt(index++));
        System.out.println(index);
        // TO TEST
        //solution.
    }
}
/**
Given an encoded string, return its decoded string. 

 The encoding rule is: k[encoded_string], where the encoded_string inside the 
square brackets is being repeated exactly k times. Note that k is guaranteed to 
be a positive integer. 

 You may assume that the input string is always valid; there are no extra white 
spaces, square brackets are well-formed, etc. Furthermore, you may assume that 
the original data does not contain any digits and that digits are only for those 
repeat numbers, k. For example, there will not be input like 3a or 2[4]. 

 The test cases are generated so that the length of the output will never 
exceed 10⁵. 

 
 Example 1: 

 
Input: s = "3[a]2[bc]"
Output: "aaabcbc"
 

 Example 2: 

 
Input: s = "3[a2[c]]"
Output: "accaccacc"
 

 Example 3: 

 
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 

 
 Constraints: 

 
 1 <= s.length <= 30 
 s consists of lowercase English letters, digits, and square brackets '[]'. 
 s is guaranteed to be a valid input. 
 All the integers in s are in the range [1, 300]. 
 

 Related Topics String Stack Recursion 👍 12081 👎 553

*/

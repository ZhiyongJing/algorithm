package leetcode.question.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
  *@Question:  1249. Minimum Remove to Make Valid Parentheses     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 88.2%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */


/**
 * 这道题的目标是移除最少数量的括号，使得输入字符串成为有效的括号序列。例如，字符串 "lee(t(c)o)de)" 可以通过移除两个括号变为有效的括号序列 "lee(t(c)o)de"。
 *
 * ### 解题思路
 *
 * 1. 创建一个栈 `stack` 来追踪左括号的索引。
 * 2. 遍历输入字符串，对于每个字符：
 *    - 如果是左括号 `'('`，将其索引入栈。
 *    - 如果是右括号 `')'`，判断栈是否为空：
 *      - 若栈为空，表示当前右括号没有匹配的左括号，记录该右括号的索引。
 *      - 若栈不为空，弹出栈顶的左括号索引，匹配成功。
 * 3. 遍历结束后，栈中剩余的左括号索引和无法匹配的右括号索引都需要被移除。
 * 4. 构建最终的字符串，根据记录的索引来排除需要移除的括号。
 *
 * ### 时间复杂度
 * - 遍历字符串的时间复杂度为 O(N)，其中 N 为字符串长度。
 *
 * ### 空间复杂度
 * - 使用了一个栈 `stack` 和一个集合 `indexesToRemove` 来记录需要移除的索引，它们的空间复杂度都是 O(N)。
 *
 * 综上所述，该算法的时间复杂度和空间复杂度都是线性的，是一个相对高效的解法。
 */

public class LeetCode_1249_MinimumRemoveToMakeValidParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minRemoveToMakeValid(String s) {
            Set<Integer> indexesToRemove = new HashSet<>(); // 用于记录需要移除的括号的索引
            Stack<Integer> stack = new Stack<>(); // 使用栈来辅助匹配括号
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } if (s.charAt(i) == ')') {
                    if (stack.isEmpty()) {
                        indexesToRemove.add(i); // 如果栈为空，说明当前右括号没有匹配的左括号，将其索引记录到set中
                    } else {
                        stack.pop(); // 栈不为空，匹配到一个左括号，将其出栈
                    }
                }
            }
            // 将栈中剩余的左括号的索引放入set中
            while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (!indexesToRemove.contains(i)) {
                    sb.append(s.charAt(i)); // 根据set中记录的索引，构建最终的字符串
                }
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1249_MinimumRemoveToMakeValidParentheses().new Solution();

        // 测试用例
        String result = solution.minRemoveToMakeValid("lee(t(c)o)de)");
        System.out.println("Result: " + result);  // 输出："lee(t(c)o)de"
    }
}

/**
Given a string s of '(' , ')' and lowercase English characters. 

 Your task is to remove the minimum number of parentheses ( '(' or ')', in any 
positions ) so that the resulting parentheses string is valid and return any 
valid string. 

 Formally, a parentheses string is valid if and only if: 

 
 It is the empty string, contains only lowercase characters, or 
 It can be written as AB (A concatenated with B), where A and B are valid 
strings, or 
 It can be written as (A), where A is a valid string. 
 

 
 Example 1: 

 
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 

 Example 2: 

 
Input: s = "a)b(c)d"
Output: "ab(c)d"
 

 Example 3: 

 
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s[i] is either'(' , ')', or lowercase English letter. 
 

 Related Topics String Stack 👍 6064 👎 113

*/
package leetcode.question.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 *@Question:  20. Valid Parentheses
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 98.77%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 题目说明
 *
 * **题目**：给定一个只包含 `(`、`)`、`{`、`}`、`[` 和 `]` 的字符串 `s`，判断字符串中的括号是否有效。一个括号字符串有效的条件是：
 * 1. 每个开括号必须有一个匹配的闭括号。
 * 2. 匹配的括号必须按正确的顺序配对。
 *
 * 例如：
 * - `()`: 有效
 * - `()[]{}`: 有效
 * - `(]`: 无效
 * - `([)]`: 无效
 * - `{[]}`: 有效
 *
 * ### 解题思路
 *
 * **使用栈（Stack）来解决问题**：
 *
 * 1. **哈希表存储匹配关系**：
 *    - 使用哈希表（`HashMap`）来存储每种闭括号与其对应的开括号。例如，`')'` 对应 `'('`，`'}'` 对应 `'{'`，`']'` 对应 `'['`。
 *
 * 2. **遍历字符串**：
 *    - 遍历字符串中的每个字符。
 *    - 如果字符是开括号（如 `'('`、`'{'`、`'['`），将其推入栈中。
 *    - 如果字符是闭括号（如 `')'`、`'}'`、`']'`），则从栈中弹出一个开括号，并检查是否匹配：
 *      - 如果栈为空，表示没有对应的开括号，字符串无效。
 *      - 如果栈非空，则取出栈顶的开括号，并查看它是否与当前的闭括号匹配。如果不匹配，则字符串无效。
 *
 * 3. **最终检查**：
 *    - 遍历结束后，如果栈为空，说明所有的开括号都有匹配的闭括号，并且是按照正确的顺序配对的，因此字符串有效。
 *    - 如果栈非空，说明有未匹配的开括号，字符串无效。
 *
 * ### 时间复杂度
 *
 * - **O(N)**：其中 N 是字符串 `s` 的长度。我们需要遍历字符串中的每个字符一次，因此时间复杂度是 O(N)。
 *
 * ### 空间复杂度
 *
 * - **O(N)**：栈的空间复杂度最坏情况下为 O(N)。在最坏的情况下（例如所有的字符都是开括号），栈可能需要存储所有的开括号。
 */

public class LeetCode_20_ValidParentheses{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 哈希表用于存储括号的匹配关系
        private HashMap<Character, Character> mappings;

        // 构造函数初始化哈希表，存储每种闭合括号对应的开括号
        public Solution() {
            this.mappings = new HashMap<Character, Character>();
            this.mappings.put(')', '(');
            this.mappings.put('}', '{');
            this.mappings.put(']', '[');
        }

        public boolean isValid(String s) {
            // 初始化一个栈，用于保存开括号
            Stack<Character> stack = new Stack<Character>();

            // 遍历字符串中的每个字符
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                // 如果当前字符是闭合括号
                if (this.mappings.containsKey(c)) {
                    // 弹出栈顶元素，如果栈为空，则设置一个虚拟值 '#'
                    char topElement = stack.empty() ? '#' : stack.pop();

                    // 检查栈顶元素是否与当前闭合括号的开括号匹配
                    if (topElement != this.mappings.get(c)) {
                        return false; // 不匹配则返回 false
                    }
                } else {
                    // 如果是开括号，则入栈
                    stack.push(c);
                }
            }

            // 最后检查栈是否为空，若为空则括号匹配正确，否则不匹配
            return stack.isEmpty();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_20_ValidParentheses().new Solution();

        // 测试样例
        String s1 = "()";           // 应该返回 true
        String s2 = "()[]{}";       // 应该返回 true
        String s3 = "(]";           // 应该返回 false
        String s4 = "([)]";         // 应该返回 false
        String s5 = "{[]}";         // 应该返回 true

        System.out.println(solution.isValid(s1));
        System.out.println(solution.isValid(s2));
        System.out.println(solution.isValid(s3));
        System.out.println(solution.isValid(s4));
        System.out.println(solution.isValid(s5));
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
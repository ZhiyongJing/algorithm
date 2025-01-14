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
 * ==============================
 * 题目描述：1249. Minimum Remove to Make Valid Parentheses
 * ==============================
 * 给定一个字符串 s，其中包含字母、小写括号 '(' 和 ')'
 * 要求移除尽可能少的括号，使得剩下的字符串是一个**有效括号字符串**。
 * 有效括号字符串的定义是：
 *  - 每个左括号 '(' 都必须有一个对应的右括号 ')'
 *  - 每个右括号 ')' 都必须有一个对应的左括号 '('
 *
 * 返回移除括号后形成的最短有效括号字符串。
 *
 * 示例：
 * 输入: "lee(t(c)o)de)"
 * 输出: "lee(t(c)o)de"
 *
 * 输入: "a)b(c)d"
 * 输出: "ab(c)d"
 *
 * 输入: "))(("
 * 输出: ""
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * 1. **利用栈来辅助匹配括号**
 *    - 栈用于存储未匹配的左括号的索引。
 *    - 遍历字符串时：
 *      - 遇到左括号 '(' 就将其索引压入栈中。
 *      - 遇到右括号 ')' 时：
 *        - 如果栈为空，说明当前右括号没有匹配的左括号，需要移除该右括号。
 *        - 如果栈不为空，说明可以匹配一个左括号，将栈顶元素弹出。
 *    - 经过上述遍历后，栈中剩余的左括号索引表示未被匹配的左括号，需要移除。
 *
 * **举例解释**：
 * - 示例1：s = "lee(t(c)o)de)"
 *   - 遍历过程：
 *     - 遇到 '('，将其索引入栈。
 *     - 遇到 ')' 且栈不为空，弹出栈顶的 '('。
 *     - 遍历结束后发现栈为空，但有一个未匹配的右括号，需移除。
 *   - 最终结果为："lee(t(c)o)de"。
 *
 * - 示例2：s = "a)b(c)d"
 *   - 遍历过程：
 *     - 遇到 'a'，跳过。
 *     - 遇到 ')'，栈为空，记录需要移除的索引。
 *     - 遇到 '('，将其索引入栈。
 *     - 遍历结束后，移除记录的 ')'。
 *   - 最终结果为："ab(c)d"。
 *
 * - 示例3：s = "))(("
 *   - 遍历过程：
 *     - 前两个字符都是 ')' 且栈为空，记录索引。
 *     - 后两个字符是 '('，将索引入栈。
 *   - 遍历结束后，需移除栈中剩余的 '(' 和记录的 ')'。
 *   - 最终结果为：""。
 *
 * **核心思路总结**：
 * - 利用栈找到不匹配的左括号 '(' 和右括号 ')'。
 * - 遍历字符串，将需要移除的括号索引记录在集合中。
 * - 最后构建一个新的字符串，将不需要移除的字符添加到结果中。
 */

/**
 * ==============================
 * 时间复杂度分析：
 * ==============================
 * 时间复杂度：O(N)，其中 N 是字符串的长度。
 * - 遍历字符串的过程中，每个字符只被访问一次。
 * - 栈的入栈和出栈操作平均时间复杂度是 O(1)。
 * - 最后构建结果字符串的过程也是 O(N)。
 *
 * 空间复杂度：O(N)。
 * - 使用了栈来存储未匹配的左括号索引，最多存储 N/2 个索引。
 * - 使用了集合来记录需要移除的括号索引，最多记录 N 个索引。
 * - 使用了 StringBuilder 来构建结果字符串，其空间复杂度也是 O(N)。
 */


public class LeetCode_1249_MinimumRemoveToMakeValidParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minRemoveToMakeValid(String s) {
            // 用于存储需要移除的括号的索引
            Set<Integer> indexesToRemove = new HashSet<>();
            // 栈用于记录左括号的索引，便于后续匹配
            Stack<Integer> stack = new Stack<>();

            // 遍历字符串的每个字符
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    // 遇到左括号，将其索引入栈
                    stack.push(i);
                }
                if (s.charAt(i) == ')') {
                    if (stack.isEmpty()) {
                        // 如果栈为空，说明当前右括号没有匹配的左括号
                        // 记录该右括号的索引
                        indexesToRemove.add(i);
                    } else {
                        // 栈不为空，说明可以匹配一个左括号，弹出栈顶
                        stack.pop();
                    }
                }
            }

            // 将栈中剩余的左括号索引记录到需要移除的集合中
            while (!stack.isEmpty()) {
                indexesToRemove.add(stack.pop());
            }

            // 使用StringBuilder来构造结果字符串
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (!indexesToRemove.contains(i)) {
                    // 如果当前索引不在需要移除的集合中，将字符加入结果
                    sb.append(s.charAt(i));
                }
            }

            // 返回构造的有效括号字符串
            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 创建Solution对象进行测试
        Solution solution = new LeetCode_1249_MinimumRemoveToMakeValidParentheses().new Solution();

        // 测试用例1
        String result1 = solution.minRemoveToMakeValid("lee(t(c)o)de)");
        System.out.println("Result1: " + result1);  // 输出："lee(t(c)o)de"

        // 测试用例2
        String result2 = solution.minRemoveToMakeValid("a)b(c)d");
        System.out.println("Result2: " + result2);  // 输出："ab(c)d"

        // 测试用例3
        String result3 = solution.minRemoveToMakeValid("))((");
        System.out.println("Result3: " + result3);  // 输出：""

        // 测试用例4
        String result4 = solution.minRemoveToMakeValid("(a(b(c)d)");
        System.out.println("Result4: " + result4);  // 输出："a(b(c)d"
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
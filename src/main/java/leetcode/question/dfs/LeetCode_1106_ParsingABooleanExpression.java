package leetcode.question.dfs;
/**
 *@Question:  1106. Parsing A Boolean Expression
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 45.89%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 1. **表达式解析**:
 *    - 表达式使用布尔逻辑运算符和括号来定义布尔值计算。主要的运算符有 `!` (逻辑非)、`&` (逻辑与) 和 `|` (逻辑或)。
 *    - `!` 操作符是单目运算符，它作用于一个布尔值，并将其取反。
 *    - `&` 操作符是双目运算符，它对两个布尔值进行逻辑与运算，结果为两个布尔值都为 `true` 时为 `true`，否则为 `false`。
 *    - `|` 操作符也是双目运算符，它对两个布尔值进行逻辑或运算，结果为两个布尔值中至少一个为 `true` 时为 `true`，否则为 `false`。
 *
 * 2. **递归解析**:
 *    - **基本情况**: 当遇到一个单一的布尔值 `t` 或 `f` 时，直接返回对应的布尔值。
 *    - **处理操作符**:
 *      - **`!`**: 解析操作符后的布尔值，并对其取反。
 *      - **`&` 和 `|`**: 解析操作符后的多个布尔值或子表达式，并根据操作符对它们进行计算。需要处理逗号分隔的多个子表达式，并在遇到右括号 `)` 时结束当前子表达式的处理。
 *    - **递归解析子表达式**: 遇到左括号 `(` 时，递归调用解析函数处理子表达式，遇到右括号 `)` 时结束递归并返回结果。
 *
 * 3. **操作步骤**:
 *    - 从表达式的开头开始，根据操作符决定解析方式。
 *    - 使用递归函数处理每个子表达式，并根据操作符计算最终的布尔结果。
 *    - 对于复杂表达式，递归函数将处理所有括号中的内容，并在每一层递归完成后返回结果。
 *
 * ### 时间复杂度
 *
 * - **时间复杂度**: O(N)
 *   - 解析每个字符的时间复杂度是 O(1)，由于每个字符被处理一次，因此总体时间复杂度是 O(N)，其中 N 是表达式的长度。
 *
 * ### 空间复杂度
 *
 * - **空间复杂度**: O(N)
 *   - 空间复杂度主要由递归调用栈和存储表达式字符的空间组成。递归调用栈的深度最坏情况下是 O(N)，尤其在表达式嵌套较深时。存储表达式字符的空间是 O(N)。
 *
 * 整体而言，该算法通过递归处理嵌套布尔表达式，并根据操作符和布尔值计算最终结果。它有效地利用递归和字符遍历来解析复杂的布尔逻辑表达式。
 */

public class LeetCode_1106_ParsingABooleanExpression{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int idx = 0; // 当前解析的索引位置

        public boolean parseBoolExpr(final String expression) {
            this.idx = 0; // 初始化索引位置

            if(expression.length() == 1) {
                // 如果表达式只有一个字符，则直接返回 true 或 false
                return expression.charAt(0) == 't'; // 't' 表示 true，'f' 表示 false
            }

            return this.helper(expression); // 调用辅助方法解析整个表达式
        }

        private boolean helper(final String s) {
            final char operator = s.charAt(this.idx); // 当前操作符（'!', '&', '|')

            this.idx += 2; // 跳过操作符和开括号

            char c = s.charAt(this.idx); // 读取第一个子表达式或布尔值

            boolean result = false; // 结果初始化为 false

            if(c == 't') {
                result = true; // 如果是 't'，结果为 true
                this.idx++;
            } else if(c == 'f') {
                result = false; // 如果是 'f'，结果为 false
                this.idx++;
            } else {
                // 否则递归调用 helper 解析子表达式
                result = this.helper(s);
            }

            // 处理逗号和括号之间的其他布尔值或子表达式
            c = s.charAt(this.idx);

            while(c != ')') {
                if(c == ',') {
                    c = s.charAt(++this.idx); // 跳过逗号
                    continue;
                }

                boolean curr = false; // 当前布尔值初始化为 false

                if(c == 't') {
                    curr = true; // 如果是 't'，当前布尔值为 true
                    this.idx++;
                } else if(c == 'f') {
                    curr = false; // 如果是 'f'，当前布尔值为 false
                    this.idx++;
                } else {
                    // 否则递归调用 helper 解析子表达式
                    curr = helper(s);
                }

                // 根据操作符计算结果
                if(operator == '&') {
                    result &= curr; // '&' 操作符：逻辑与
                } else if(operator == '|') {
                    result |= curr; // '|' 操作符：逻辑或
                }

                c = s.charAt(this.idx); // 继续读取下一个字符
            }

            this.idx++; // 跳过右括号

            // 处理 '!' 操作符
            return operator == '!' ? !result : result; // '!' 操作符：逻辑非
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1106_ParsingABooleanExpression().new Solution();

        // 测试样例 1: "!(&(t,f))" 应该返回 false
        String expression1 = "!(&(t,f))";
        System.out.println(solution.parseBoolExpr(expression1)); // 输出: false

        // 测试样例 2: "|(t,f)" 应该返回 true
        String expression2 = "|(t,f)";
        System.out.println(solution.parseBoolExpr(expression2)); // 输出: true

        // 测试样例 3: "&(t,t,f)" 应该返回 false
        String expression3 = "&(t,t,f)";
        System.out.println(solution.parseBoolExpr(expression3)); // 输出: false

        // 测试样例 4: "|(&(t,f,t),!(t))" 应该返回 true
        String expression4 = "|(&(t,f,t),!(t))";
        System.out.println(solution.parseBoolExpr(expression4)); // 输出: true
    }
}

/**
A boolean expression is an expression that evaluates to either true or false. 
It can be in one of the following shapes: 

 
 't' that evaluates to true. 
 'f' that evaluates to false. 
 '!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
 
 '&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of 
the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1. 
 '|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the 
inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1. 
 

 Given a string expression that represents a boolean expression, return the 
evaluation of that expression. 

 It is guaranteed that the given expression is valid and follows the given 
rules. 

 
 Example 1: 

 
Input: expression = "&(|(f))"
Output: false
Explanation: 
First, evaluate |(f) --> f. The expression is now "&(f)".
Then, evaluate &(f) --> f. The expression is now "f".
Finally, return false.
 

 Example 2: 

 
Input: expression = "|(f,f,f,t)"
Output: true
Explanation: The evaluation of (false OR false OR false OR true) is true.
 

 Example 3: 

 
Input: expression = "!(&(f,t))"
Output: true
Explanation: 
First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is 
now "!(f)".
Then, evaluate !(f) --> NOT false --> true. We return true.
 

 
 Constraints: 

 
 1 <= expression.length <= 2 * 10⁴ 
 expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', 
and ','. 
 

 Related Topics String Stack Recursion 👍 1185 👎 62

*/
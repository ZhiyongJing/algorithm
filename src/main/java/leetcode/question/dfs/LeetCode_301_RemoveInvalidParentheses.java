package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
  *@Question:  301. Remove Invalid Parentheses
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 59.96%
  *@Time  Complexity: O(2^n)
  *@Space Complexity: O(n)
 */

/**
 * **算法思路：**
 *
 * 这道题的目标是移除最小数量的无效括号，使得剩余的括号组成一个有效的表达式。为了解决这个问题，
 * 我们可以使用深度优先搜索（DFS）的方法来遍历所有可能的字符串组合，然后检查它们是否是有效的表达式。
 *
 * 具体步骤如下：
 *
 * 1. **计算多余的左右括号数量：** 首先，我们遍历输入字符串，记录多余的左右括号的数量。遍历过程中，如果发现多余的右括号，
 * 需要找到对应的多余左括号，因此需要维护左括号的数量。
 *
 * 2. **深度优先搜索：** 利用递归进行深度优先搜索，生成所有可能的字符串组合。在递归过程中，我们有以下情况：
 *
 *    - 如果当前字符是非括号字符，直接递归到下一个字符。
 *    - 如果当前字符是左括号，考虑包含当前左括号和不包含当前左括号两种情况，然后递归到下一个字符。
 *    - 如果当前字符是右括号，考虑包含当前右括号和不包含当前右括号两种情况，然后递归到下一个字符。在递归时，需要注意右括号的数量不能超过左括号的数量。
 *
 * 3. **结果验证：** 在递归到字符串的末尾时，检查生成的字符串是否是有效的表达式。如果是有效的，将其添加到结果集中。
 *
 * 4. **返回结果：** 返回结果集作为最终的答案。
 *
 * **时间复杂度：** 在最坏情况下，我们可能需要遍历所有可能的字符串组合，因此时间复杂度是指数级别的，为O(2^n)，其中n是输入字符串的长度。
 *
 * **空间复杂度：** 空间复杂度主要取决于递归调用的深度，即栈的深度。在最坏情况下，栈的深度为输入字符串的长度，因此空间复杂度为O(n)。
 * 此外，存储结果集需要额外的空间，但由于结果集的数量是指数级别的，因此可以忽略。
 */

public class LeetCode_301_RemoveInvalidParentheses{

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 用于存储所有有效的表达式
    private Set<String> validExpressions = new HashSet<String>();

    // 递归函数，用于生成所有可能的有效表达式
    private void recurse(
            String s,
            int index,
            int leftCount,
            int rightCount,
            int leftRem,
            int rightRem,
            StringBuilder expression) {

        // 如果已经遍历到字符串末尾，就检查生成的表达式是否有效，
        // 以及是否删除了正确数量的左右括号
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                this.validExpressions.add(expression.toString());
            }
        } else {
            char character = s.charAt(index);
            int length = expression.length();

            // 舍弃的情况。在这里有一个剪枝条件，
            // 如果当前括号的剩余数量为0，则不再递归。
            if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
                this.recurse(
                        s,
                        index + 1,
                        leftCount,
                        rightCount,
                        leftRem - (character == '(' ? 1 : 0),
                        rightRem - (character == ')' ? 1 : 0),
                        expression);
            }

            expression.append(character);
            System.out.println("now expression is: " + expression);

            // 如果当前字符不是括号，则直接递归到下一个字符
            if (character != '(' && character != ')') {
                this.recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);
            } else if (character == '(') {
                // 考虑左括号的情况
                this.recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);
            } else if (rightCount < leftCount) {
                // 考虑右括号的情况
                this.recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
            }

            // 回溯删除字符
            expression.deleteCharAt(length);
            System.out.println("backtracing expression is: " + expression);
        }
    }

    // 主函数，用于解决问题
    public List<String> removeInvalidParentheses(String s) {

        int left = 0, right = 0;

        // 首先，找出多余的左右括号数量
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // 如果没有匹配的左括号，记录为多余的右括号
                right = left == 0 ? right + 1 : right;

                // 减少左括号的数量，因为我们已经找到了一个可以匹配的右括号
                left = left > 0 ? left - 1 : left;
            }
        }
        System.out.println(left + "-" + right);

        // 调用递归函数生成所有可能的有效表达式
        this.recurse(s, 0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<String>(this.validExpressions);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_301_RemoveInvalidParentheses().new Solution();
        solution.removeInvalidParentheses("(a)())()");
        solution.removeInvalidParentheses(")(");
        // TO TEST
        //solution.
    }
}
/**
Given a string s that contains parentheses and letters, remove the minimum
number of invalid parentheses to make the input string valid.

 Return a list of unique strings that are valid with the minimum number of 
removals. You may return the answer in any order.


 Example 1: 


Input: s = "()())()"
Output: ["(())()","()()()"]


 Example 2: 


Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]


 Example 3: 


Input: s = ")("
Output: [""]



 Constraints: 


 1 <= s.length <= 25 
 s consists of lowercase English letters and parentheses '(' and ')'. 
 There will be at most 20 parentheses in s. 


 Related Topics String Backtracking Breadth-First Search 👍 5711 👎 272

*/

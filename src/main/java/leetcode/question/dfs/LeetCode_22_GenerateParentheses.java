package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  22. Generate Parentheses
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 90.06%
 *@Time  Complexity: O(4^n / sqrt(n)) - 解析不是很直观，但是可以近似认为是 O(4^n)
 *@Space Complexity: O(4^n / sqrt(n)) - 解析不是很直观，但是可以近似认为是 O(4^n)
 */

public class LeetCode_22_GenerateParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 主方法，生成有效括号组合的入口
        public List<String> generateParenthesis(int n) {
            List<String> answer = new ArrayList<>();
            // 调用回溯方法，初始化一个空的字符串列表和空的字符串
            backtracking(answer, new StringBuilder(), 0, 0, n);

            return answer;
        }

        // 回溯方法，用于生成有效括号组合
        private void backtracking(List<String> answer, StringBuilder curString, int leftCount, int rightCount, int n) {
            // 当前字符串长度等于 2n，即得到一个可能的有效括号组合
            if (curString.length() == 2 * n) {
                answer.add(curString.toString());
                return;
            }
            // 尝试添加左括号
            if (leftCount < n) {
                curString.append("(");
                // 递归调用，左括号数量加1
                backtracking(answer, curString, leftCount + 1, rightCount, n);
                // 回溯，移除最后添加的左括号
                curString.deleteCharAt(curString.length() - 1);
            }
            // 尝试添加右括号，但要确保右括号的数量不超过左括号
            if (leftCount > rightCount) {
                curString.append(")");
                // 递归调用，右括号数量加1
                backtracking(answer, curString, leftCount, rightCount + 1, n);
                // 回溯，移除最后添加的右括号
                curString.deleteCharAt(curString.length() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_22_GenerateParentheses().new Solution();
        // TO TEST
        // Example usage:
        int n = 3;
        List<String> result = solution.generateParenthesis(n);
        System.out.println(result);
    }
}
/**
 * 当解释这个算法时，可以按照以下思路进行讲解：
 *
 * ### 算法思路：
 *
 * 1. **回溯法：**
 *    - 这个算法采用回溯法（Backtracking），通过递归的方式生成所有可能的有效括号组合。
 *    - 在递归的过程中，尝试添加左括号和右括号，并通过条件来确保生成的括号组合是有效的。
 *
 * 2. **回溯函数 `backtracking`：**
 *    - `backtracking` 函数是核心的递归函数，用于生成所有可能的有效括号组合。
 *    - 递归的终止条件是当前字符串长度等于 `2n`，表示得到一个可能的有效括号组合，将其添加到最终结果列表中。
 *    - 在递归调用之前和之后，进行相应的回溯操作，即添加和移除括号。
 *
 * 3. **尝试添加左括号和右括号：**
 *    - 在递归的过程中，首先尝试添加左括号。如果左括号的数量小于 `n`，则可以添加左括号，递归调用 `backtracking`。
 *    - 接着，尝试添加右括号。为了确保生成的括号组合有效，只有在已添加的左括号数量大于已添加的右括号数量时，才能添加右括号。然后递归调用 `backtracking`。
 *
 * 4. **回溯操作：**
 *    - 在递归调用之前和之后，需要进行相应的回溯操作，即添加和移除括号。
 *    - 如果添加了左括号，需要在递归调用之后将最后添加的左括号移除。
 *    - 如果添加了右括号，需要在递归调用之后将最后添加的右括号移除。
 *
 * ### 代码关键点：
 *
 * - 使用一个 `StringBuilder` 类型的 `curString` 来动态构建当前的括号组合。
 * - 使用 `leftCount` 和 `rightCount` 来跟踪已添加的左括号和右括号的数量。
 * - 在尝试添加左括号和右括号之前，通过条件判断确保添加的括号是有效的。
 *
 * ### 复杂度分析：
 *
 * - 时间复杂度：近似为 O(4^n / sqrt(n))，其中 n 是括号对数。这是因为在最坏情况下，我们可能需要生成指数级别的括号组合。
 * - 空间复杂度：近似为 O(4^n / sqrt(n))，递归栈的深度为 2n，在每个递归层级上，可能需要 O(n) 的空间来存储当前的括号组合。
 *
 * 通过这样的回溯思路，算法能够穷举所有可能的括号组合，并最终得到所有有效的括号序列。
 */

/**
Given n pairs of parentheses, write a function to generate all combinations of 
well-formed parentheses. 

 
 Example 1: 
 Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
 
 Example 2: 
 Input: n = 1
Output: ["()"]
 
 
 Constraints: 

 
 1 <= n <= 8 
 

 Related Topics String Dynamic Programming Backtracking 👍 20096 👎 845

*/

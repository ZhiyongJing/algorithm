package leetcode.frequent.dfs;

/**
  *@Question:  856. Score of Parentheses     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 58.42%      
  *@Time  Complexity: O(n^2)
  *@Space Complexity: O(n)
 */
/**
 * ### 算法思路：
 *
 * 这个算法的思路是通过递归计算平衡括号字符串的分数。主要的思路如下：
 *
 * 1. **基本概念：**
 *    - 平衡括号字符串：字符串中左右括号数量相等，且每个右括号都有一个匹配的左括号。
 *    - 括号对的性质：括号对的分数可以通过递归计算。
 *
 * 2. **递归计算分数的规则：**
 *    - 对于平衡的括号对 "()"，分数为 1。
 *    - 对于平衡的括号对 "(A)"，分数为 2 * A，其中 A 是一个平衡括号字符串的分数。
 *
 * 3. **算法步骤：**
 *    - 主方法 `scoreOfParentheses`：
 *      - 接收一个平衡括号字符串 `S` 作为输入。
 *      - 调用辅助方法 `F`，计算整个字符串的分数。
 *      - 返回计算得到的分数。
 *
 *    - 辅助方法 `F`：
 *      - 接收三个参数：平衡括号字符串 `S`、子串起始位置 `i`、子串终止位置 `j`。
 *      - 遍历字符串 `S`，通过平衡度的增减找到平衡的括号对。
 *      - 对于平衡的括号对，根据括号对的形式计算分数：
 *        - 如果是 "()"，分数加1。
 *        - 如果是其他形式，分数加上两倍的递归调用结果，其中递归调用计算的是括号对内部的分数。
 *      - 递归调用的起始位置更新为 `i + 1`，即指向下一个未处理的字符位置。
 *      - 返回当前子串的分数。
 *
 * 4. **递归结束条件：**
 *    - 递归调用在找到平衡的括号对时进行，直到计算整个字符串的分数为止。
 *
 * ### 时间复杂度和空间复杂度：
 *
 * - **时间复杂度：** O(n)，其中 n 是括号字符串的长度。每个字符只需遍历一次，因此时间复杂度为线性。
 *
 * - **空间复杂度：** O(1)。递归调用并没有使用额外的空间，主要是通过参数传递和局部变量存储状态。因此，空间复杂度是常量级别的。
 */

public class LeetCode_856_ScoreOfParentheses {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 主方法，计算字符串的分数
        public int scoreOfParentheses(String S) {
            return F(S, 0, S.length());
        }

        // 辅助方法，计算字符串 S[i:j] 的分数
        public int F(String S, int i, int j) {
            // 当前子串的分数
            int ans = 0;
            // 当前子串的平衡度
            int bal = 0;

            // 将字符串分解为基本单元
            for (int k = i; k < j; ++k) {
                // 如果是左括号，平衡度加1；如果是右括号，平衡度减1
                bal += S.charAt(k) == '(' ? 1 : -1;
                // 当平衡度为0时，说明找到一个平衡的括号对
                if (bal == 0) {
                    // 如果是"()"，分数加1；否则，分数加上两倍的递归调用结果
                    if (k - i == 1) {
                        ans++;
                    } else {
                        ans += 2 * F(S, i + 1, k);
                    }
                    // 更新 i，指向下一个未处理的字符位置
                    i = k + 1;
                }
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_856_ScoreOfParentheses().new Solution();
        // TO TEST
        // 示例调用：
        String s1 = "()";
        int result1 = solution.scoreOfParentheses(s1);
        System.out.println(result1);  // 输出: 1

        String s2 = "(())";
        int result2 = solution.scoreOfParentheses(s2);
        System.out.println(result2);  // 输出: 2

        String s3 = "()()";
        int result3 = solution.scoreOfParentheses(s3);
        System.out.println(result3);  // 输出: 2
    }
}


/**
Given a balanced parentheses string s, return the score of the string. 

 The score of a balanced parentheses string is based on the following rule: 

 
 "()" has score 1. 
 AB has score A + B, where A and B are balanced parentheses strings. 
 (A) has score 2 * A, where A is a balanced parentheses string. 
 

 
 Example 1: 

 
Input: s = "()"
Output: 1
 

 Example 2: 

 
Input: s = "(())"
Output: 2
 

 Example 3: 

 
Input: s = "()()"
Output: 2
 

 
 Constraints: 

 
 2 <= s.length <= 50 
 s consists of only '(' and ')'. 
 s is a balanced parentheses string. 
 

 Related Topics String Stack 👍 5300 👎 185

*/

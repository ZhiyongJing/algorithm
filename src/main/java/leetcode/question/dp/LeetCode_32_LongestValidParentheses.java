package leetcode.question.dp;

import java.util.Stack;

/**
 *@Question:  32. Longest Valid Parentheses
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 64.19%
 *@Time  Complexity: O(n)
 *@Space Complexity: O(n) for solution 1, and solution2, O(1) for solution3
 */

/**
 * 当解决问题 "32. Longest Valid Parentheses"（最长有效括号）时，我们可以采用几种不同的方法来实现，每种方法都有其独特的思路和复杂度特性。
 *
 * ### 解题思路详解：
 *
 * 1. **动态规划（DP）方法：**
 *
 *    - **思路：** 使用动态规划来解决最长有效括号的问题。定义 `dp[i]` 表示以第 `i` 个字符结尾的最长有效括号的长度。
 *      - 如果 `s[i]` 为 `(`，则 `dp[i] = 0`，因为以 `(` 结尾无法构成有效括号。
 *      - 如果 `s[i]` 为 `)`：
 *        - 如果 `s[i-1]` 为 `(`，则 `dp[i] = dp[i-2] + 2`（如果 `i-2 >= 0`）。
 *        - 如果 `s[i-1]` 为 `)`，且存在 `s[i-dp[i-1]-1]` 为 `(`，则 `dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2`（如果 `i-dp[i-1]-2 >= 0`）。
 *
 *    - **时间复杂度：** O(n)，其中 n 是字符串 `s` 的长度。我们需要遍历一次字符串，并在每个位置上执行常数时间的操作。
 *    - **空间复杂度：** O(n)，需要额外的长度为 n 的 `dp` 数组来存储中间结果。
 *
 * 2. **栈（Stack）方法：**
 *
 *    - **思路：** 使用栈来解决最长有效括号的问题。栈中存储的是字符在字符串中的索引。
 *      - 初始化栈，并将 `-1` 压入栈中，表示开始的辅助计算。
 *      - 遍历字符串 `s`，当遇到 `(`，将其索引压入栈中；当遇到 `)`，弹出栈顶元素。
 *      - 如果弹出后栈为空，则将当前 `)` 的索引入栈，作为新的辅助计算起点；否则，计算当前有效括号长度。
 *
 *    - **时间复杂度：** O(n)，其中 n 是字符串 `s` 的长度。我们需要遍历一次字符串，并在每个位置上执行常数时间的操作。
 *    - **空间复杂度：** O(n)，最坏情况下，栈的空间复杂度为 O(n)。
 *
 * 3. **双指针（Two Pointer）方法：**
 *
 *    - **思路：** 使用两个指针 `left` 和 `right` 来解决最长有效括号的问题。
 *      - 从左向右遍历字符串 `s`，增加 `left` 和 `right` 计数器：
 *        - 当 `left == right` 时，计算当前有效括号长度。
 *        - 当 `right > left` 时，重置 `left` 和 `right`。
 *      - 从右向左遍历字符串 `s`，同样增加 `left` 和 `right` 计数器，并重复上述过程。
 *
 *    - **时间复杂度：** O(n)，其中 n 是字符串 `s` 的长度。我们需要遍历两次字符串，每次遍历执行线性时间的操作。
 *    - **空间复杂度：** O(1)，只需要常数级别的额外空间来存储指针和计数器。
 *
 * ### 总结：
 *
 * - 动态规划方法利用状态转移方程来记录以每个字符结尾的最长有效括号长度，适合处理字符串的局部信息。
 * - 栈方法通过维护索引栈来动态更新当前可能的有效括号长度，适合处理全局的括号匹配情况。
 * - 双指针方法利用两次遍历来处理左右括号的数量关系，是一种空间效率较高的方法。
 *
 * 选择合适的方法取决于具体的问题需求和输入数据特性。
 */
public class LeetCode_32_LongestValidParentheses{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        //Solution1: DP
        // 使用动态规划解决问题
        public int longestValidParentheses(String s) {
            int maxans = 0; // 最长有效括号的长度
            int dp[] = new int[s.length()]; // 用于存储每个位置的最长有效括号长度
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') { // 形如"(...)"
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') { // 形如"(...))"
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]); // 更新最长有效括号长度
                }
            }
            return maxans; // 返回最长有效括号长度
        }

        //Solution2: Stack
        // 使用栈解决问题
        public int longestValidParentheses2(String s) {
            int maxans = 0; // 最长有效括号的长度
            Stack<Integer> stack = new Stack<>(); // 用于存储'('的索引
            stack.push(-1); // 初始化栈，-1用于辅助计算长度
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') { // 当前字符是'('时，将其索引压入栈中
                    stack.push(i);
                } else { // 当前字符是')'时
                    stack.pop(); // 弹出栈顶元素，表示匹配成功的'('
                    if (stack.empty()) { // 如果栈为空
                        stack.push(i); // 将当前')'的索引入栈，辅助计算长度
                    } else {
                        maxans = Math.max(maxans, i - stack.peek()); // 计算当前最长有效括号长度
                    }
                }
            }
            return maxans; // 返回最长有效括号长度
        }

        //Solution3: two pointer
        // 使用双指针解决问题
        public int longestValidParentheses3(String s) {
            int left = 0, right = 0, maxlength = 0; // 初始化左右括号计数和最长有效括号长度
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') { // 遇到'('增加左括号计数
                    left++;
                } else { // 遇到')'增加右括号计数
                    right++;
                }
                if (left == right) { // 当左右括号相等时，计算当前有效括号长度
                    maxlength = Math.max(maxlength, 2 * right);
                } else if (right > left) { // 右括号数大于左括号数，重置计数
                    left = right = 0;
                }
            }
            left = right = 0; // 重置左右括号计数
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') { // 遇到'('增加左括号计数
                    left++;
                } else { // 遇到')'增加右括号计数
                    right++;
                }
                if (left == right) { // 当左右括号相等时，计算当前有效括号长度
                    maxlength = Math.max(maxlength, 2 * left);
                } else if (left > right) { // 左括号数大于右括号数，重置计数
                    left = right = 0;
                }
            }
            return maxlength; // 返回最长有效括号长度
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_32_LongestValidParentheses().new Solution();
        // TO TEST
        String s = "(()())";
        System.out.println(solution.longestValidParentheses(s)); // 预期输出：6
        System.out.println(solution.longestValidParentheses2(s)); // 预期输出：6
        System.out.println(solution.longestValidParentheses3(s)); // 预期输出：6
    }
}

/**
Given a string containing just the characters '(' and ')', return the length of 
the longest valid (well-formed) parentheses substring. 

 
 Example 1: 

 
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
 

 Example 2: 

 
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
 

 Example 3: 

 
Input: s = ""
Output: 0
 

 
 Constraints: 

 
 0 <= s.length <= 3 * 10⁴ 
 s[i] is '(', or ')'. 
 

 Related Topics String Dynamic Programming Stack 👍 12282 👎 406

*/
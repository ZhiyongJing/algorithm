package leetcode.question.monotonic_stack_queue;

import java.util.Stack;

/**
 *@Question:  402. Remove K Digits
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 67.19%
 *@Time  Complexity: O(N), where N is the length of the input string num.
 *@Space Complexity: O(N), as we use a stack (LinkedList) to store the digits.
 */
/**
 * 题目描述：
 * 402. Remove K Digits
 *
 * 给定一个非负整数 `num` 以字符串的形式表示，以及一个整数 `k`，
 * 需要**删除 `k` 个数字**，使得剩下的数字形成的整数 **最小**。
 *
 * **要求**
 * - 结果不能有前导零（除非结果为 `0`）。
 * - 结果不能为空，如果所有数字都删除了，返回 `"0"`。
 * - 不能重新排列数字，必须按照 `num` 中的原始顺序构造结果。
 *
 * **示例 1**
 * ```plaintext
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释:
 * - 移除 "4", "3", "2"，剩下 "1219"，是最小的可能结果。
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释:
 * - 移除 "1"，剩下 "0200"，去掉前导零，最终为 "200"。
 * ```
 *
 * **示例 3**
 * ```plaintext
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释:
 * - 需要移除 2 个数字，删除 "1" 和 "0"，最终为空，返回 "0"。
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * 采用 **单调栈 + 贪心策略**，保证：
 * 1. **删除 `k` 个数字后，剩余的数值最小**。
 * 2. **数字顺序不能变**，所以使用 **单调递增栈** 来存储最终的数字。
 * 3. **去除前导零**，保证返回的是有效的整数。
 *
 * **步骤**
 * 1. **遍历 `num`，维护单调递增栈**
 *    - 若当前数字 `digit` **小于** 栈顶元素 `stack.peekLast()`，并且 `k > 0`，则移除栈顶元素：
 *      - 这样可以得到更小的字典序数字。
 *      - 继续移除，直到 `k == 0` 或者栈为空，保证 `k` 下降。
 * 2. **如果 `k` 仍然大于 `0`**
 *    - 说明还需要删除 `k` 个元素，从栈的 **末尾** 删除（因为前面的数字已经是最优解）。
 * 3. **去除前导零**
 *    - 通过遍历字符串，忽略前导零，确保返回的数字有效。
 * 4. **如果最终结果为空，则返回 `"0"`**
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `num = "1432219", k = 3`
 *
 * **1. 遍历 `num` 维护栈**
 * ```plaintext
 * i = 0, stack = [1]       // 1 入栈
 * i = 1, stack = [1, 4]    // 4 入栈
 * i = 2, stack = [1, 3]    // 4 > 3, 移除 4，k=2
 * i = 3, stack = [1, 2]    // 3 > 2, 移除 3，k=1
 * i = 4, stack = [1, 2, 2] // 2 入栈
 * i = 5, stack = [1, 2, 2, 1] // 2 > 1, 移除 2，k=0
 * i = 6, stack = [1, 2, 2, 1, 9] // 9 入栈
 * ```
 * **2. 最终栈内容：** `["1", "2", "1", "9"]`，结果 `"1219"`
 *
 * ---
 *
 * **3. 时间复杂度分析**
 * - **遍历 `num` 处理 `stack`：O(N)**
 * - **移除 `k` 个元素：O(K)**
 * - **构建最终字符串：O(N)**
 * - **总时间复杂度：O(N)**
 *
 * **4. 空间复杂度分析**
 * - **栈 `stack`：最多存储 `num.length()` 个元素，O(N)**
 * - **最终结果字符串 `O(N)`**
 * - **总空间复杂度：O(N)**
 */


public class LeetCode_402_RemoveKDigits{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeKdigits(String num, int k) {
            // 使用双端队列（LinkedList）作为单调栈
            Stack<Character> stack = new Stack<Character>();

            // 遍历 num 中的每一位数字
            for(char digit : num.toCharArray()) {
                // 如果栈不为空，并且当前数字比栈顶数字小，并且还可以移除数字
                while(stack.size() > 0 && k > 0 && stack.peek() > digit) {
                    stack.pop(); // 移除栈顶较大的数字
                    k -= 1; // 需要删除的数字减少 1
                }
                // 将当前数字添加到栈中
                stack.push(digit);
            }

            /* 如果 k 还大于 0，说明还需要删除 k 个数字，从尾部删除 */
            for(int i = 0; i < k; ++i) {
                stack.pop();
            }

            // 构建最终的字符串，同时去除前导零
            StringBuilder ret = new StringBuilder();
            boolean leadingZero = true;
            for(char digit: stack) {
                if(leadingZero && digit == '0') continue; // 跳过前导零
                leadingZero = false;
                ret.append(digit);
            }

            /* 如果最终结果为空，则返回 "0" */
            if (ret.length() == 0) return "0";
            return ret.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_402_RemoveKDigits().new Solution();

        // 测试样例 1
        String num1 = "1432219";
        int k1 = 3;
        System.out.println(solution.removeKdigits(num1, k1)); // 预期输出: "1219"

        // 测试样例 2
        String num2 = "10200";
        int k2 = 1;
        System.out.println(solution.removeKdigits(num2, k2)); // 预期输出: "200"

        // 测试样例 3
        String num3 = "10";
        int k3 = 2;
        System.out.println(solution.removeKdigits(num3, k3)); // 预期输出: "0"

        // 测试样例 4
        String num4 = "112";
        int k4 = 1;
        System.out.println(solution.removeKdigits(num4, k4)); // 预期输出: "11"

        // 测试样例 5
        String num5 = "9";
        int k5 = 1;
        System.out.println(solution.removeKdigits(num5, k5)); // 预期输出: "0"
    }
}

/**
Given string num representing a non-negative integer num, and an integer k, 
return the smallest possible integer after removing k digits from num. 

 
 Example 1: 

 
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 
which is the smallest.
 

 Example 2: 

 
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output 
must not contain leading zeroes.
 

 Example 3: 

 
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing 
which is 0.
 

 
 Constraints: 

 
 1 <= k <= num.length <= 10⁵ 
 num consists of only digits. 
 num does not have any leading zeros except for the zero itself. 
 

 Related Topics String Stack Greedy Monotonic Stack 👍 9822 👎 516

*/
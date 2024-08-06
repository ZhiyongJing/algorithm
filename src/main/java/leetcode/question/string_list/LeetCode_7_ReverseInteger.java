package leetcode.question.string_list;
/**
 *@Question:  7. Reverse Integer
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 84.41%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * **题目：7. Reverse Integer**
 *
 * **问题描述：**
 * 给定一个 32 位有符号整数 `x`，将 `x` 中的数字反转。如果反转后整数超出 32 位有符号整数的范围 `[−2^31, 2^31−1]`，则返回 0。
 *
 * **解题思路：**
 *
 * ### 解题步骤：
 *
 * 1. **初始化反转结果变量**：
 *    - 定义一个变量 `rev` 用于存储反转后的结果，初始值为 0。
 *
 * 2. **逐位反转**：
 *    - 使用 `while` 循环处理每个数字，直到 `x` 为 0。
 *    - 在每次循环中，提取 `x` 的最低位数字（即 `x % 10`），将其添加到 `rev` 的末尾。
 *
 * 3. **处理整数溢出**：
 *    - 在每次添加新的数字前，检查是否会发生溢出：
 *      - **正溢出**：当 `rev` 超过 `Integer.MAX_VALUE / 10` 或 `rev` 等于 `Integer.MAX_VALUE / 10` 且当前数字大于 7 时，结果会溢出。
 *      - **负溢出**：当 `rev` 小于 `Integer.MIN_VALUE / 10` 或 `rev` 等于 `Integer.MIN_VALUE / 10` 且当前数字小于 -8 时，结果会溢出。
 *    - 如果溢出，则返回 0。
 *
 * 4. **更新反转结果**：
 *    - 每次循环中，将 `pop` 乘以 10 加到 `rev` 上，更新 `rev` 的值。
 *
 * 5. **返回结果**：
 *    - 最终返回 `rev` 作为反转后的整数。
 *
 * ### 时间复杂度
 *
 * - **O(log N)**：因为每次操作将 `x` 除以 10，`x` 的位数在反转过程中逐渐减少。具体而言，处理一个整数的时间复杂度是 O(log N)，其中 N 是整数的绝对值。
 *
 * ### 空间复杂度
 *
 * - **O(1)**：在算法中只使用了固定数量的额外空间来存储变量（如 `rev` 和 `pop`），不依赖于输入的大小，因此空间复杂度是常量级的 O(1)。
 */

public class LeetCode_7_ReverseInteger{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse(int x) {
            int rev = 0; // 初始化反转后的整数
            while (x != 0) {
                int pop = x % 10; // 取出当前最低位的数字
                x /= 10; // 去掉当前最低位的数字
                // 检查是否会发生整数溢出（正溢出）
                if (
                        rev > Integer.MAX_VALUE / 10 ||
                                (rev == Integer.MAX_VALUE / 10 && pop > 7)
                ) return 0; // 溢出，返回 0
                // 检查是否会发生整数溢出（负溢出）
                if (
                        rev < Integer.MIN_VALUE / 10 ||
                                (rev == Integer.MIN_VALUE / 10 && pop < -8)
                ) return 0; // 溢出，返回 0
                rev = rev * 10 + pop; // 更新反转后的整数
            }
            return rev; // 返回反转后的整数
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_7_ReverseInteger().new Solution();

        // 测试样例
        int test1 = 123; // 正整数
        int result1 = solution.reverse(test1);
        System.out.println("输入: " + test1 + "，反转后的结果: " + result1); // 输出: 321

        int test2 = -123; // 负整数
        int result2 = solution.reverse(test2);
        System.out.println("输入: " + test2 + "，反转后的结果: " + result2); // 输出: -321

        int test3 = 1534236469; // 超过32位整数范围的数字
        int result3 = solution.reverse(test3);
        System.out.println("输入: " + test3 + "，反转后的结果: " + result3); // 输出: 0（溢出）

        int test4 = 0; // 边界情况
        int result4 = solution.reverse(test4);
        System.out.println("输入: " + test4 + "，反转后的结果: " + result4); // 输出: 0
    }
}

/**
Given a signed 32-bit integer x, return x with its digits reversed. If 
reversing x causes the value to go outside the signed 32-bit integer range [-2³¹, 2³¹ - 1
], then return 0. 

 Assume the environment does not allow you to store 64-bit integers (signed or 
unsigned). 

 
 Example 1: 

 
Input: x = 123
Output: 321
 

 Example 2: 

 
Input: x = -123
Output: -321
 

 Example 3: 

 
Input: x = 120
Output: 21
 

 
 Constraints: 

 
 -2³¹ <= x <= 2³¹ - 1 
 

 Related Topics Math 👍 13127 👎 13560

*/
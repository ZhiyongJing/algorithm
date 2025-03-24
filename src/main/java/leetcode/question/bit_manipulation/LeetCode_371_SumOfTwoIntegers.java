package leetcode.question.bit_manipulation;

/**
 *@Question:  371. Sum of Two Integers
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 57.07%
 *@Time  Complexity: O(1)（最多 32 次循环）
 *@Space Complexity: O(1)
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 371 - Sum of Two Integers（两整数之和）
 *
 * 不使用加号（+）或减号（-）运算符，计算两个整数 a 和 b 的和。
 * 你只能使用位运算（如 AND、OR、XOR、左移、右移）来实现。
 *
 * 示例：
 * 输入：a = 1, b = 2
 * 输出：3
 *
 * 输入：a = -2, b = 3
 * 输出：1
 *
 * -------------------------------------------------------------------
 *
 * 第二步：解题思路（基于代码的超级详细讲解 + 举例解释）
 * -------------------------------------------------------------------
 * ✅ 本题的核心在于使用 **位运算** 来模拟加法：
 * - “无进位和”使用 XOR 运算：a ^ b
 * - “进位”使用 AND 运算并左移： (a & b) << 1
 * - 将这两部分加在一起，即得到新的结果
 * - 不断重复，直到进位为 0，说明没有新的进位需要处理了
 *
 * ✅ 步骤详解：
 * 1. 初始化：输入整数 a 和 b
 * 2. 在 while 循环中：
 *    - 使用 XOR 运算模拟“无进位加法”，得到初步的和 answer = a ^ b
 *    - 使用 AND 运算得到进位位，并左移一位 carry = (a & b) << 1
 *    - 将无进位和更新为新的 a，将进位 carry 更新为新的 b
 *    - 循环继续，直到 carry 为 0
 * 3. 最后返回 a，即为最终结果
 *
 * ✅ 举例解释：
 * 示例：a = 3 (011), b = 5 (101)
 * 第一次：
 *   - a ^ b = 011 ^ 101 = 110 → 无进位加法结果为 6
 *   - a & b = 011 & 101 = 001 → 进位为 1，左移一位 = 010
 * 第二次：
 *   - a = 6, b = 2
 *   - a ^ b = 110 ^ 010 = 100 → 当前和为 4
 *   - a & b = 110 & 010 = 010 → 进位 = 010 左移一位 = 100
 * 第三次：
 *   - a = 4, b = 4
 *   - a ^ b = 000, a & b = 100 → 进位左移 = 1000
 * 第四次：
 *   - a = 0, b = 8
 *   - a ^ b = 8, a & b = 0 → 无进位无进位了，结束
 * 最终结果是 a = 8
 * 实际：3 + 5 = 8，结果正确
 *
 * ✅ 对于负数，也适用，因为 Java 中的整数是以二进制补码表示的，位运算同样有效。
 *
 * -------------------------------------------------------------------
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * 时间复杂度：O(1)
 * - 由于整数为 32 位，最多执行 32 次循环（每次至少消除一位进位），因此是常数时间复杂度
 *
 * 空间复杂度：O(1)
 * - 仅使用了固定数量的变量（answer, carry 等），不使用额外数据结构
 */


public class LeetCode_371_SumOfTwoIntegers{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int getSum(int a, int b) {
            // 当 b ≠ 0 时，表示还有进位未处理
            while (b != 0) {
                // 使用位运算模拟加法：
                // XOR 运算得出“无进位和”
                int answer = a ^ b;

                // AND 运算得出“进位”位置，然后左移一位表示进位影响下一位
                int carry = (a & b) << 1;

                // 将当前无进位和赋值给 a，进位赋值给 b
                // 下一轮循环继续处理进位
                a = answer;
                b = carry;
            }

            // 当进位为 0，a 中存储的就是最终的和
            return a;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_371_SumOfTwoIntegers().new Solution();

        // 测试用例 1：正数 + 正数
        System.out.println(solution.getSum(3, 5)); // 输出：8

        // 测试用例 2：正数 + 0
        System.out.println(solution.getSum(7, 0)); // 输出：7

        // 测试用例 3：负数 + 正数
        System.out.println(solution.getSum(-4, 6)); // 输出：2

        // 测试用例 4：负数 + 负数
        System.out.println(solution.getSum(-3, -6)); // 输出：-9

        // 测试用例 5：a = 0, b = 0
        System.out.println(solution.getSum(0, 0)); // 输出：0
    }
}

/**
Given two integers a and b, return the sum of the two integers without using 
the operators + and -. 

 
 Example 1: 
 Input: a = 1, b = 2
Output: 3
 
 Example 2: 
 Input: a = 2, b = 3
Output: 5
 
 
 Constraints: 

 
 -1000 <= a, b <= 1000 
 

 Related Topics Math Bit Manipulation 👍 4416 👎 5661

*/
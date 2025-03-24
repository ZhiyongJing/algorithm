package leetcode.question.string_list;

/**
 *@Question:  29. Divide Two Integers
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 63.05%
 *@Time  Complexity: O(logN) let n be the absolute value of dividend.
 *@Space Complexity: O(1)
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 29 - Divide Two Integers
 *
 * 给你两个整数 dividend（被除数）和 divisor（除数），不使用乘法、除法和 mod 运算符，返回两数相除的商。
 * 商需去掉小数部分，保留整数部分（向 0 取整）。
 *
 * 特别说明：
 * - 结果需在 32 位有符号整数范围内：[-2^31, 2^31 - 1]。
 * - 如果结果溢出，则返回 Integer.MAX_VALUE。
 *
 * 示例：
 * 输入：dividend = 10, divisor = 3
 * 输出：3
 * 解释：10 / 3 = 3.333... 向 0 取整得到 3
 *
 * 输入：dividend = 7, divisor = -3
 * 输出：-2
 *
 * -------------------------------------------------------------------
 *
 * 第二步：解题思路（基于代码逐行讲解 + 每一步举例）
 * -------------------------------------------------------------------
 * 核心思想：使用 **位运算和减法** 实现整数除法，同时避免溢出。
 * 由于 Integer.MIN_VALUE = -2^31 不能取正数，因此统一将所有数转换为负数计算。
 *
 * 步骤详解：
 *
 * 1. 特殊情况判断：
 *    - 如果 dividend 是 Integer.MIN_VALUE（-2^31）且 divisor 为 -1，则结果会超出正整数范围。
 *      例如：-2147483648 / -1 = 2147483648，超出 int 最大值，因此直接返回 Integer.MAX_VALUE。
 *
 * 2. 将 dividend 和 divisor 转换为负数，并记录负号个数（negatives）。
 *    - 原因：-2^31 不能变成正数，但正数能变负数。
 *    - 若最终负号数量为 1，则说明结果应为负数；否则为正数。
 *
 * 3. 将 divisor 不断加倍（即左移），直到大于 dividend 或超过 Integer.MIN_VALUE / 2。
 *    - 记录最大能加倍的次数 maxBit。
 *    - 举例：dividend = -40, divisor = -3：
 *      - -3 → -6 → -12 → -24（下一次 -48 超出 -40，停止）
 *      - 得到最大位数 maxBit = 3
 *
 * 4. 从最大 bit 倒序迭代，依次检查当前倍数的 divisor 是否仍小于 dividend。
 *    - 如果成立，则减去该倍数，并将商中对应 bit 设置为 1（即商 += 1 << bit）。
 *    - 每轮将 divisor 右移一位（即除以 2），准备判断下一个较小倍数。
 *    - 举例继续：
 *      - dividend = -40，divisor 加倍到 -24（bit = 3）
 *        - -24 >= -40，商 += 1<<3 = 8，dividend -= -24 → -16
 *        - divisor 减半为 -12（bit = 2）：
 *          - -12 >= -16，商 += 1<<2 = 4，dividend -= -12 → -4
 *        - divisor 减半为 -6，不满足条件，跳过
 *        - divisor 减半为 -3，-3 >= -4，商 += 1 → 13，dividend -= -3 → -1
 *
 * 5. 根据负号数量决定是否将最终商取负数。
 *    - negatives == 1 → 保留负数；否则取反为正数。
 *
 * -------------------------------------------------------------------
 *
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * 时间复杂度：O(log N)
 * - N 为 dividend 的绝对值
 * - 每次将 divisor 加倍，最多能加倍约 logN 次（直到接近 dividend）
 * - 随后最多进行 logN 次减法来构建最终商，因此整体为 O(log N)
 *
 * 空间复杂度：O(1)
 * - 仅使用了固定数量的整数变量（没有使用额外数组或集合）
 * - 因此空间复杂度为常数级
 */


public class LeetCode_29_DivideTwoIntegers{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 表示 Integer.MIN_VALUE / 2，避免在移位或加倍时溢出
        private  int HALF_INT_MIN = -1073741824;

        public int divide(int dividend, int divisor) {
            // 特殊情况处理：-2^31 / -1 会溢出，因此直接返回 Integer.MAX_VALUE
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            // 特殊情况处理：-2^31 / 1，结果仍是 -2^31，直接返回
            if (dividend == Integer.MIN_VALUE && divisor == 1) {
                return Integer.MIN_VALUE;
            }

            /* 将被除数和除数都转换为负数，避免正数计算时溢出
             * 同时记录负号的个数，如果只有一个负数则最后结果为负 */
            int negatives = 2;
            if (dividend > 0) {
                negatives--;
                dividend = -dividend;
            }
            if (divisor > 0) {
                negatives--;
                divisor = -divisor;
            }

            /* 接下来我们希望找到 divisor 的最大加倍值，使其不超过 dividend
             * 如果加倍后的值小于 HALF_INT_MIN，说明再加倍会溢出，因此停止 */
            int maxBit = 0;
            while (divisor >= HALF_INT_MIN && divisor + divisor >= dividend) {
                maxBit += 1;
                divisor += divisor; // 将除数翻倍（加倍）
            }

            // 初始化结果为 0
            int quotient = 0;
            /* 从最大 bit 开始，逐步右移除数（即除数减半），直到不能右移 */
            for (int bit = maxBit; bit >= 0; bit--) {
                /* 如果当前除数仍小于等于被除数，说明当前倍数可被使用 */
                if (divisor >= dividend) {
                    // 在 quotient 中添加该倍数的值
                    quotient -= (1 << bit);
                    // 减去已经计算的部分
                    dividend -= divisor;
                }
                // 右移除数（等价于除以 2），为下一位的比较做准备
                divisor = (divisor + 1) >> 1;
            }

            /* 如果原本只有一个负号，说明结果应为负；否则取反为正 */
            if (negatives != 1) {
                quotient = -quotient;
            }
            return quotient;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_29_DivideTwoIntegers().new Solution();

        // 测试用例 1：10 / 3 = 3
        System.out.println(solution.divide(10, 3)); // 输出: 3

        // 测试用例 2：7 / -3 = -2
        System.out.println(solution.divide(7, -3)); // 输出: -2

        // 测试用例 3：-2147483648 / -1，会溢出，返回 Integer.MAX_VALUE
        System.out.println(solution.divide(-2147483648, -1)); // 输出: 2147483647

        // 测试用例 4：-2147483648 / 1，正常情况
        System.out.println(solution.divide(-2147483648, 1)); // 输出: -2147483648

        // 测试用例 5：0 / 1 = 0
        System.out.println(solution.divide(0, 1)); // 输出: 0

        // 测试用例 6：1 / 1 = 1
        System.out.println(solution.divide(1, 1)); // 输出: 1
    }
}

/**
Given two integers dividend and divisor, divide two integers without using 
multiplication, division, and mod operator. 

 The integer division should truncate toward zero, which means losing its 
fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be 
truncated to -2. 

 Return the quotient after dividing dividend by divisor. 

 Note: Assume we are dealing with an environment that could only store integers 
within the 32-bit signed integer range: [−2³¹, 2³¹ − 1]. For this problem, if 
the quotient is strictly greater than 2³¹ - 1, then return 2³¹ - 1, and if the 
quotient is strictly less than -2³¹, then return -2³¹. 

 
 Example 1: 

 
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
 

 Example 2: 

 
Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 

 
 Constraints: 

 
 -2³¹ <= dividend, divisor <= 2³¹ - 1 
 divisor != 0 
 

 Related Topics Math Bit Manipulation 👍 5517 👎 15043

*/
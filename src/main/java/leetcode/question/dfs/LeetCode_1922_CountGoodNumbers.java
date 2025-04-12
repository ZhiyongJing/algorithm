package leetcode.question.dfs;
/**
 *@Question:  1922. Count Good Numbers
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 61.83%
 *@Time  Complexity: O(logN)
 *@Space Complexity: O(1)
 */
/**
 * ===============================================
 * LeetCode 1922. Count Good Numbers
 * ===============================================
 *
 * 【一、题目描述】
 * 给定一个长度为 n 的数字序列（所有位都在 [0-9] 范围内），我们定义：
 * - 偶数下标位置（从 0 开始计数）只能是 0/2/4/6/8，共 5 种偶数；
 * - 奇数下标位置只能是质数 2/3/5/7，共 4 种质数；
 *
 * 问题：长度为 n 的所有可能“好数字串”的数量是多少？
 * 返回该数量对 10^9 + 7 取模的结果。
 *
 * 例子：
 * 输入：n = 1     输出：5     （只能填在 index=0，合法数字：0/2/4/6/8）
 * 输入：n = 4     输出：400   （偶数位2个、奇数位2个 → 5^2 * 4^2 = 25 * 16 = 400）
 *
 *
 * 【二、解题思路详解】
 *
 * ✅ 思路一：数学乘法原理 + 快速幂（快速计算幂次）
 *
 * 观察可知：
 * - 所有偶数下标位置可以填入 5 种数字 → 个数 = ⌈n / 2⌉
 * - 所有奇数下标位置可以填入 4 种质数 → 个数 = ⌊n / 2⌋
 *
 * 所以总的组合数为：
 *     total = 5^(⌈n/2⌉) × 4^(⌊n/2⌋)
 *
 * 但由于 n 很大（最多 10^15），普通幂运算会溢出，因此必须使用：
 *     ✅ 快速幂（Binary Exponentiation）来加速运算。
 *
 *
 * ✅ 举例说明：
 * 示例 1：n = 1
 * - index=0 是偶数位 → 只能填 0/2/4/6/8 → 5 种
 * - 没有奇数位 → 答案是 5^1 * 4^0 = 5
 *
 * 示例 2：n = 4
 * - index 0,2 → 偶数位 → 5^2 = 25
 * - index 1,3 → 奇数位 → 4^2 = 16
 * - 总数 = 25 * 16 = 400
 *
 * 示例 3：n = 50
 * - 偶数位数 = 25，奇数位数 = 25
 * - 总数 = 5^25 * 4^25
 * - 使用快速幂可在 log(n) 时间内完成
 *
 *
 * 【三、时间与空间复杂度】
 *
 * 时间复杂度：O(log n)
 * - 每次快速幂计算为 O(log n)，共调用两次（5 的幂 + 4 的幂）
 * - 整体为 O(log n)
 *
 * 空间复杂度：O(1)
 * - 除了循环变量，没有额外空间开销
 */

public class LeetCode_1922_CountGoodNumbers{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 模数，取模是为了防止结果溢出
        long mod = 1000000007;

        public int countGoodNumbers(long n) {
            // n 位数中：
            // 偶数位可以放 0/2/4/6/8（共 5 种）
            // 奇数位可以放质数 2/3/5/7（共 4 种）
            // 整体方案数为：5^(n/2 上取整) * 4^(n/2 下取整)
            return (int) ((fastPower(5, (n + 1) / 2) * fastPower(4, n / 2)) % mod);
        }

        // 快速幂算法，计算 x^y % mod
        private long fastPower(long base, long exponent) {
            long result = 1;
            base = base % mod;

            while (exponent > 0) {
                // 如果当前指数是奇数，就乘 base
                if (exponent % 2 == 1) {
                    result = (result * base) % mod;
                }
                // base 平方，指数除以 2
                base = (base * base) % mod;
                exponent /= 2;
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1922_CountGoodNumbers().new Solution();

        // 测试用例 1：n = 1（只一位，偶数位放 0/2/4/6/8）
        System.out.println("输入 n=1，输出：" + solution.countGoodNumbers(1)); // 预期输出：5

        // 测试用例 2：n = 4（偶数位两位，奇数位两位）
        System.out.println("输入 n=4，输出：" + solution.countGoodNumbers(4)); // 预期输出：5^2 * 4^2 = 25 * 16 = 400

        // 测试用例 3：n = 50（测试大数快速幂是否正确）
        System.out.println("输入 n=50，输出：" + solution.countGoodNumbers(50));

        // 测试用例 4：n = 100000000000000L（测试超大数）
        System.out.println("输入 n=10^14，输出：" + solution.countGoodNumbers(100000000000000L));
    }
}

/**
A digit string is good if the digits (0-indexed) at even indices are even and 
the digits at odd indices are prime (2, 3, 5, or 7). 

 
 For example, "2582" is good because the digits (2 and 8) at even positions are 
even and the digits (5 and 2) at odd positions are prime. However, "3245" is 
not good because 3 is at an even index but is not even. 
 

 Given an integer n, return the total number of good digit strings of length n. 
Since the answer may be large, return it modulo 10⁹ + 7. 

 A digit string is a string consisting of digits 0 through 9 that may contain 
leading zeros. 

 
 Example 1: 

 
Input: n = 1
Output: 5
Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".
 

 Example 2: 

 
Input: n = 4
Output: 400
 

 Example 3: 

 
Input: n = 50
Output: 564908303
 

 
 Constraints: 

 
 1 <= n <= 10¹⁵ 
 

 Related Topics Math Recursion 👍 1695 👎 489

*/
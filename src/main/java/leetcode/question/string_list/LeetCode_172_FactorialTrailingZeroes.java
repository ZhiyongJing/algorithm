package leetcode.question.string_list;
/**
 *@Question:  172. Factorial Trailing Zeroes
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 43.0%
 *@Time  Complexity: O(logN)
 *@Space Complexity: O(1)
 */
/**
 * ### 题目说明
 *
 * **题目:** 172. Factorial Trailing Zeroes
 *
 * **描述:** 给定一个整数 `n`，返回 `n!`（n 的阶乘）的末尾零的数量。
 *
 * **示例:**
 *
 * - 输入: `n = 3`
 * - 输出: `0`
 * - 解释: `3! = 6`，末尾没有零。
 *
 * - 输入: `n = 5`
 * - 输出: `1`
 * - 解释: `5! = 120`，末尾有 1 个零。
 *
 * ### 解题思路
 *
 * 要找出 `n!` 的末尾零的数量，我们需要计算 `n!` 末尾零的个数。末尾零的形成是因为乘法结果中含有因子 `10`，
 * 而 `10` 是由因子 `2` 和因子 `5` 组合而成的。由于在计算 `n!` 时因子 `2` 比因子 `5` 更频繁出现，所以问题简化为计算因子 `5` 的数量。
 *
 * #### 具体步骤:
 *
 * 1. **因子计数:**
 *    - 需要统计 `n!` 中因子 `5` 的数量。每个 `5` 与 `2` 组合形成一个 `10`，从而贡献一个末尾零。
 *
 * 2. **倍数考虑:**
 *    - 由于一个数可能包含多个 `5` 的倍数，例如 `25` (即 `5^2`)、`125` (即 `5^3`) 等。每个 `25` 贡献两个因子 `5`，每个 `125` 贡献三个因子 `5`，
 *    以此类推。因此，需要计算所有可能的 `5` 的倍数在 `n!` 中出现的次数。
 *
 * 3. **算法流程:**
 *    - 从 `5` 开始，逐步考虑其倍数 (即 `5`, `25`, `125`, ...)。
 *    - 对于每一个倍数，计算 `n` 除以该倍数的商，这个商表示在 `n!` 中有多少个该倍数。
 *    - 累加所有倍数的因子 `5` 的数量。
 *
 * ### 时间复杂度与空间复杂度
 *
 * - **时间复杂度:** O(log₅(N))。每次迭代都将倍数 `currentMultiple` 乘以 `5`，计算 `n / currentMultiple`，因此迭代次数与 `n` 的对数成正比。
 *
 * - **空间复杂度:** O(1)。只使用了常量级别的额外空间来存储变量，例如 `zeroCount` 和 `currentMultiple`。
 *
 * ### 总结
 *
 * 该问题的关键是找出 `n!` 中有多少个因子 `5`。通过逐步检查 `5` 的倍数，并累加每个倍数在 `n!` 中的出现次数，我们可以高效地得到结果。该方法的时间复杂度为 O(log₅(N))，空间复杂度为 O(1)，适用于大规模的 `n`。
 */
public class LeetCode_172_FactorialTrailingZeroes{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trailingZeroes(int n) {
            int zeroCount = 0; // 用于记录末尾零的数量
            // 当前倍数从 5 开始，后续逐步倍增
            long currentMultiple = 5;
            while (n >= currentMultiple) {
                // 计算 n 中包含多少个 currentMultiple
                zeroCount += (n / currentMultiple);
                // 将 currentMultiple 乘以 5 以考虑更高倍数
                currentMultiple *= 5;
            }
            return zeroCount; // 返回末尾零的数量
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_172_FactorialTrailingZeroes().new Solution();
        // 测试样例
        int result1 = solution.trailingZeroes(3); // 应该输出 0
        System.out.println(result1);

        int result2 = solution.trailingZeroes(5); // 应该输出 1
        System.out.println(result2);

        int result3 = solution.trailingZeroes(30); // 应该输出 7
        System.out.println(result3);
    }
}

/**
Given an integer n, return the number of trailing zeroes in n!. 

 Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1. 

 
 Example 1: 

 
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.
 

 Example 2: 

 
Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.
 

 Example 3: 

 
Input: n = 0
Output: 0
 

 
 Constraints: 

 
 0 <= n <= 10⁴ 
 

 
 Follow up: Could you write a solution that works in logarithmic time 
complexity? 

 Related Topics Math 👍 3204 👎 1957

*/
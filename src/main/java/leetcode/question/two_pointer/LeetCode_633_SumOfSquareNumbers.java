package leetcode.question.two_pointer;
/**
 *@Question:  633. Sum of Square Numbers
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 57.92%
 *@Time Complexity: O(sqrt(c) log C) for solution and 2, We iterate over sqrt(c)
 *   values for choosing a. For every a chosen, finding square root of c−a^2 takes O(logc) time in the worst case.
 *@Space Complexity: O(1) for  solutions1, logC for solution2 Binary Search will take O(logc) space.
 */
/**
 * 题目描述：
 * LeetCode 633. Sum of Square Numbers
 * 给定一个非负整数 `c`，判断是否存在两个整数 `a` 和 `b`，使得：
 * ```
 * a^2 + b^2 = c
 * ```
 *
 * **示例 1：**
 * ```
 * 输入: c = 5
 * 输出: true
 * 解释: 1^2 + 2^2 = 5
 * ```
 *
 * **示例 2：**
 * ```
 * 输入: c = 3
 * 输出: false
 * ```
 *
 * **示例 3：**
 * ```
 * 输入: c = 4
 * 输出: true
 * 解释: 0^2 + 2^2 = 4
 * ```
 */

/**
 * 解题思路：
 * **方法 1：平方根检测**
 * 1. 遍历 `a` 从 `0` 到 `sqrt(c)`，计算 `b = sqrt(c - a^2)`。
 * 2. 如果 `b` 是整数，则返回 `true`，否则继续尝试下一个 `a`。
 * 3. 若遍历完 `a` 仍未找到符合条件的 `(a, b)`，则返回 `false`。
 *
 * **举例分析**
 * ```
 * 输入: c = 5
 * 遍历:
 *   a = 0, b = sqrt(5 - 0^2) = sqrt(5) (不是整数)
 *   a = 1, b = sqrt(5 - 1^2) = sqrt(4) = 2 (整数)
 * 输出: true
 * ```
 *
 * ---
 *
 * **方法 2：二分查找**
 * 1. 遍历 `a` 从 `0` 到 `sqrt(c)`，计算 `b^2 = c - a^2`。
 * 2. 在 `[0, b]` 范围内使用**二分查找**判断是否存在 `b` 使得 `b^2 = c - a^2`。
 * 3. 若找到 `b`，返回 `true`，否则继续尝试下一个 `a`。
 *
 * **举例分析**
 * ```
 * 输入: c = 5
 * 遍历:
 *   a = 0, 目标值 b^2 = 5, 二分查找 [0, 5] -> 失败
 *   a = 1, 目标值 b^2 = 4, 二分查找 [0, 4] -> 成功 (b=2)
 * 输出: true
 * ```
 */

/**
 * 时间和空间复杂度分析：
 *
 * **方法 1：平方根检测**
 * 1. **时间复杂度：O(sqrt(c))**
 *    - `a` 从 `0` 遍历到 `sqrt(c)`，每次 `O(1)` 计算 `b = sqrt(c - a^2)`。
 * 2. **空间复杂度：O(1)**
 *    - 只使用常数级变量 `a, b`。
 *
 * ---
 *
 * **方法 2：二分查找**
 * 1. **时间复杂度：O(sqrt(c) log c)**
 *    - `a` 从 `0` 遍历到 `sqrt(c)`（`O(sqrt(c))`）。
 *    - 每次在 `[0, b]` 进行二分查找（`O(log c)`）。
 *    - **总复杂度 O(sqrt(c) log c)**。
 * 2. **空间复杂度：O(1)**
 *    - 仅使用常数级变量 `a, b, s, e, mid`。
 */


public class LeetCode_633_SumOfSquareNumbers{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // 方法 1：使用平方根检测
        public boolean judgeSquareSum(int c) {
            // 遍历所有可能的 a 值，范围为 [0, sqrt(c)]
            for (long a = 0; a * a <= c; a++) {
                // 计算 b 的平方是否为 c - a^2
                double b = Math.sqrt(c - a * a);
                // 如果 b 是整数，则存在 (a, b) 满足条件
                if (b == (int) b) return true;
            }
            return false;
        }

        // 方法 2：使用二分查找
        public boolean judgeSquareSum2(int c) {
            // 遍历所有可能的 a 值
            for (long a = 0; a * a <= c; a++) {
                int b = c - (int) (a * a);
                // 在 [0, b] 范围内二分查找是否存在 b
                if (binary_search(0, b, b)) return true;
            }
            return false;
        }

        // 二分查找 b 是否为完全平方数
        public boolean binary_search(long s, long e, int n) {
            if (s > e) return false;
            long mid = s + (e - s) / 2;
            if (mid * mid == n) return true;
            if (mid * mid > n) return binary_search(s, mid - 1, n);
            return binary_search(mid + 1, e, n);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_633_SumOfSquareNumbers().new Solution();

        // 测试用例 1
        System.out.println(solution.judgeSquareSum(5));
        // 期望输出: true (1^2 + 2^2 = 5)

        // 测试用例 2
        System.out.println(solution.judgeSquareSum(3));
        // 期望输出: false (不存在 a^2 + b^2 = 3)

        // 测试用例 3
        System.out.println(solution.judgeSquareSum(4));
        // 期望输出: true (0^2 + 2^2 = 4)

        // 测试用例 4
        System.out.println(solution.judgeSquareSum(25));
        // 期望输出: true (3^2 + 4^2 = 25)

        // 测试用例 5
        System.out.println(solution.judgeSquareSum2(100));
        // 期望输出: true (6^2 + 8^2 = 100)
    }
}

/**
Given a non-negative integer c, decide whether there're two integers a and b 
such that a² + b² = c. 

 
 Example 1: 

 
Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5
 

 Example 2: 

 
Input: c = 3
Output: false
 

 
 Constraints: 

 
 0 <= c <= 2³¹ - 1 
 

 Related Topics Math Two Pointers Binary Search 👍 3298 👎 613

*/
package leetcode.question.string_list;

/**
 *@Question:  50. Pow(x, n)
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 79.87%
 *@Time  Complexity: O(log n)
 *@Space Complexity: O(log n)
 */

/**
 * 这段代码实现了计算给定数 x 的 n 次幂的功能，其中使用了二分指数算法。以下是解题思路的详细讲解：
 *
 * 1. **二分指数算法**：
 *    - 二分指数算法是一种有效的方法，用于计算一个数的指数。它通过将指数 n 逐步缩小，从而降低了计算的时间复杂度。
 *    - 算法的基本思想是：利用指数的二进制表示形式，将 n 分解为若干个小指数的和。例如，若 n 的二进制表示为 1011，那么 x^n 就等于 x^8 * x^2 * x^1。
 *    - 然后，利用递归或迭代的方式，对这些小指数依次进行计算，最终得到 x 的 n 次幂的结果。
 *
 * 2. **解题思路**：
 *    - 首先定义了一个私有的递归函数 `binaryExp`，用于执行二分指数计算。该函数接受两个参数：底数 x 和指数 n。
 *    - 在递归函数中，首先处理了 n 等于 0 的情况，直接返回 1。
 *    - 然后处理了 n 小于 0 的情况，将其转换为正数的情况，避免了负指数的计算。
 *    - 接下来根据 n 的奇偶性，分别对 n 进行了不同的处理：
 *      - 若 n 为奇数，则对 n - 1 进行二分指数计算，然后将结果乘以 x。
 *      - 若 n 为偶数，则直接对 n 进行二分指数计算。
 *    - 主函数 `myPow` 则调用了递归函数 `binaryExp`，并将 n 强制转换为 long 类型，以处理 n 可能为负数的情况。
 *
 * 3. **时间复杂度**：
 *    - 由于二分指数算法在每次递归调用时将指数减半，因此总的时间复杂度为 O(log n)。这是因为将 n 缩减为其一半所需的操作次数随着 n 的增大而减少。
 *
 * 4. **空间复杂度**：
 *    - 递归函数 `binaryExp` 的空间复杂度取决于递归调用的深度，即递归栈的大小。在最坏情况下，递归栈的深度为 O(log n)，因此空间复杂度为 O(log n)。
 *
 * 因此，该算法的时间复杂度和空间复杂度都是 O(log n)。
 */

public class LeetCode_50_PowxN{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义递归函数来执行二分指数计算
        private double binaryExp(double x, long n) {
            // 基本情况，用于终止递归调用。
            if (n == 0) {
                return 1;
            }

            // 处理 n 小于 0 的情况。
            if (n < 0) {
                return 1.0 / binaryExp(x, -1 * n);
            }

            // 执行二分指数计算。
            // 如果 n 是奇数，我们对 n - 1 执行二分指数计算，然后将结果乘以 x。
            if (n % 2 == 1) {
                return x * binaryExp(x * x, (n - 1) / 2);
            }
            // 否则，我们对 n 执行二分指数计算。
            else {
                return binaryExp(x * x, n / 2);
            }
        }

        // 主函数，用于调用二分指数计算函数。
        public double myPow(double x, int n) {
            return binaryExp(x, (long) n);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    // 测试函数
    public static void main(String[] args) {
        Solution solution = new LeetCode_50_PowxN().new Solution();

        // 测试用例
        double x1 = 2.0;
        int n1 = 10;
        System.out.println(solution.myPow(x1, n1)); // 应返回 1024.0

        double x2 = 2.1;
        int n2 = 3;
        System.out.println(solution.myPow(x2, n2)); // 应返回 9.261

        double x3 = 2.0;
        int n3 = -2;
        System.out.println(solution.myPow(x3, n3)); // 应返回 0.25
    }
}

/**
 Implement pow(x, n), which calculates x raised to the power n (i.e., xⁿ).


 Example 1:


 Input: x = 2.00000, n = 10
 Output: 1024.00000


 Example 2:


 Input: x = 2.10000, n = 3
 Output: 9.26100


 Example 3:


 Input: x = 2.00000, n = -2
 Output: 0.25000
 Explanation: 2⁻² = 1/2² = 1/4 = 0.25



 Constraints:


 -100.0 < x < 100.0
 -2³¹ <= n <= 2³¹-1
 n is an integer.
 Either x is not zero or n > 0.
 -10⁴ <= xⁿ <= 10⁴


 Related Topics Math Recursion 👍 9516 👎 9349

 */
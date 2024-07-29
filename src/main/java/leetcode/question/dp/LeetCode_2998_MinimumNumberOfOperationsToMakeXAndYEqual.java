package leetcode.question.dp;

import java.util.Arrays;

/**
 *@Question:  2998. Minimum Number of Operations to Make X and Y Equal
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 54.1%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 1. **递归和记忆化搜索**：
 *     - 本问题的核心是将一个数 `x` 转化为另一个数 `y` 所需的最小操作次数。通过递归和记忆化搜索，我们可以高效地解决这个问题。
 *     - 我们定义一个递归函数 `solve(x, y)`，用于计算从 `x` 变到 `y` 的最小操作次数。
 *
 * 2. **基本情况**：
 *     - 如果 `x` 小于等于 `y`，那么只需要执行 `y - x` 次加法操作，即每次将 `x` 增加1，直到 `x` 等于 `y`。
 *     - 如果 `dp[x]` 已经计算过，直接返回存储的结果。`dp` 数组用于记忆化存储中间结果，以避免重复计算。
 *
 * 3. **递归求解**：
 *     - 初始化结果 `res` 为 `x` 和 `y` 之间的绝对差值，这意味着我们可以通过执行 `|x - y|` 次加减操作将 `x` 变为 `y`。
 *     - 尝试通过以下几种操作减少 `x` 的值，并递归计算最小操作次数：
 *         - **除以5**：如果 `x` 可以被5整除，则递归计算 `solve(x / 5, y)`，并加上1次除法操作和 `x % 5` 次加法操作。
 *         - **除以5后加1**：如果 `x` 不能被5整除，则递归计算 `solve(x / 5 + 1, y)`，并加上1次除法操作和 `(5 - x % 5)` 次加法操作。
 *         - **除以11**：如果 `x` 可以被11整除，则递归计算 `solve(x / 11, y)`，并加上1次除法操作和 `x % 11` 次加法操作。
 *         - **除以11后加1**：如果 `x` 不能被11整除，则递归计算 `solve(x / 11 + 1, y)`，并加上1次除法操作和 `(11 - x % 11)` 次加法操作。
 *
 * 4. **最终返回**：
 *     - 将计算结果存储在 `dp[x]` 中，并返回。这样可以避免重复计算相同的 `x` 值，从而提高效率。
 *
 * ### 时间和空间复杂度
 *
 * **时间复杂度**：
 * - 由于每个 `x` 值只计算一次，并且每次递归调用最多进行常数次操作（除以5或除以11），时间复杂度为 `O(x)`，其中 `x` 是初始输入值。这个复杂度来源于记忆化搜索，每个 `x` 值只被计算一次。
 *
 * **空间复杂度**：
 * - 使用了一个大小为 `10011` 的 `dp` 数组来存储中间结果，因此空间复杂度为 `O(1)`。这是假设 `x` 的最大值不超过 `10010`，实际空间复杂度依赖于 `x` 的范围。如果 `x` 更大，则 `dp` 数组的大小需要相应增大。
 *
 * ### 总结
 *
 * 通过递归和记忆化搜索，我们能够高效地计算将 `x` 转化为 `y` 所需的最小操作次数。通过存储中间结果，我们避免了重复计算，从而显著提高了算法的效率。
 */

public class LeetCode_2998_MinimumNumberOfOperationsToMakeXAndYEqual{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 用于记忆化存储中间结果的数组
        private int[] dp;

        // 解决 x 到 y 的最小操作数问题
        public int solve(int x, int y) {
            // 如果 x 小于等于 y，直接返回 y - x，表示只需增加操作
            if (x <= y) return y - x;
            // 如果 dp[x] 已经计算过，直接返回结果
            if (dp[x] != -1) return dp[x];
            // 初始化结果为 x 和 y 之间的绝对差值
            int res = Math.abs(x - y);
            // 尝试通过除以 5 的方式减少 x，并取最小值
            res = Math.min(res, 1 + x % 5 + solve(x / 5, y));
            // 尝试通过除以 5 后再加 1 的方式减少 x，并取最小值
            res = Math.min(res, 1 + (5 - x % 5) + solve(x / 5 + 1, y));
            // 尝试通过除以 11 的方式减少 x，并取最小值
            res = Math.min(res, 1 + x % 11 + solve(x / 11, y));
            // 尝试通过除以 11 后再加 1 的方式减少 x，并取最小值
            res = Math.min(res, 1 + (11 - x % 11) + solve(x / 11 + 1, y));
            // 将计算结果存储在 dp[x] 中，并返回
            return dp[x] = res;
        }

        // 计算使 x 和 y 相等的最小操作数
        public int minimumOperationsToMakeEqual(int x, int y) {
            // 初始化 dp 数组，长度为 10011，初始值为 -1
            dp = new int[10011];
            Arrays.fill(dp, -1);
            // 调用 solve 方法并返回结果
            return solve(x, y);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_2998_MinimumNumberOfOperationsToMakeXAndYEqual().new Solution();
        // 测试样例
        int x1 = 100, y1 = 200;
        System.out.println("Minimum operations to make " + x1 + " and " + y1 + " equal: " + solution.minimumOperationsToMakeEqual(x1, y1)); // 预期输出: 100

        int x2 = 1234, y2 = 4321;
        System.out.println("Minimum operations to make " + x2 + " and " + y2 + " equal: " + solution.minimumOperationsToMakeEqual(x2, y2)); // 预期输出: 预期结果

        int x3 = 5000, y3 = 5;
        System.out.println("Minimum operations to make " + x3 + " and " + y3 + " equal: " + solution.minimumOperationsToMakeEqual(x3, y3)); // 预期输出: 预期结果
    }
}

/**
You are given two positive integers x and y. 

 In one operation, you can do one of the four following operations: 

 
 Divide x by 11 if x is a multiple of 11. 
 Divide x by 5 if x is a multiple of 5. 
 Decrement x by 1. 
 Increment x by 1. 
 

 Return the minimum number of operations required to make x and y equal. 

 
 Example 1: 

 
Input: x = 26, y = 1
Output: 3
Explanation: We can make 26 equal to 1 by applying the following operations: 
1. Decrement x by 1
2. Divide x by 5
3. Divide x by 5
It can be shown that 3 is the minimum number of operations required to make 26 
equal to 1.
 

 Example 2: 

 
Input: x = 54, y = 2
Output: 4
Explanation: We can make 54 equal to 2 by applying the following operations: 
1. Increment x by 1
2. Divide x by 11 
3. Divide x by 5
4. Increment x by 1
It can be shown that 4 is the minimum number of operations required to make 54 
equal to 2.
 

 Example 3: 

 
Input: x = 25, y = 30
Output: 5
Explanation: We can make 25 equal to 30 by applying the following operations: 
1. Increment x by 1
2. Increment x by 1
3. Increment x by 1
4. Increment x by 1
5. Increment x by 1
It can be shown that 5 is the minimum number of operations required to make 25 
equal to 30.
 

 
 Constraints: 

 
 1 <= x, y <= 10⁴ 
 

 Related Topics Dynamic Programming Breadth-First Search Memoization 👍 238 👎 2
1

*/
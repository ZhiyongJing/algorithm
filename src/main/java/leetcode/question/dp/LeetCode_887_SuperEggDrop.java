package leetcode.question.dp;

import java.util.HashMap;
import java.util.Map;

/**
  *@Question:  887. Super Egg Drop
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 51.11%
  *@Time  Complexity: O(K * N * logN) for solution 1, O(K*N) for solution2
  *@Space Complexity: O(KN)
 */
/**
 * 题目描述：
 * --------------------------
 * LeetCode 887 - Super Egg Drop（超级鸡蛋掉落）
 *
 * 你有 `K` 个鸡蛋，和 `N` 层高的建筑，目标是找到最高的安全楼层 `F`：
 * 1. **如果鸡蛋从某一层掉落后没有碎，则它不会碎在更低的楼层。**
 * 2. **如果鸡蛋从某一层掉落后碎了，则它会碎在更高的楼层。**
 * 3. **如果鸡蛋摔碎了，你不能再使用它。**
 * 4. **你可以在任意楼层（1 ~ N）扔鸡蛋。**
 * 5. **最坏情况下，计算找到 F 需要的最少尝试次数。**
 *
 * **示例**
 * ```
 * 输入: K = 2, N = 6
 * 输出: 3
 * 解释:
 * - 在第 3 层扔一个鸡蛋:
 *   - 如果碎了：继续在 1 和 2 层寻找（需要 2 次）。
 *   - 如果没碎：继续在 4-6 层寻找（需要 2 次）。
 * - 总共最多 3 次尝试。
 * ```
 *
 *
 * 解题思路：
 * --------------------------
 * 该问题是 **经典的动态规划 + 二分搜索** 问题，核心思想是 **二分尝试 + 记忆化搜索**。
 *
 * **定义状态转移方程**
 * 设 `dp(K, N)` 为 **K 个鸡蛋、N 层楼时的最少尝试次数**。
 *
 * **基本情况**
 * - `dp(1, N) = N`（如果只有 1 个鸡蛋，最坏情况下需要从 1 层开始尝试）
 * - `dp(K, 0) = 0`（如果没有楼层，不需要尝试）
 *
 * **二分优化递归**
 * - 在 `X` 层扔鸡蛋：
 *   1. **如果碎了**，则剩余 `K-1` 个鸡蛋，需要在 `X-1` 层楼内找到 `F`（`dp(K-1, X-1)`）。
 *   2. **如果没碎**，则 `F` 在 `N-X` 层楼内，还剩 `K` 个鸡蛋（`dp(K, N-X)`）。
 * - 目标是最小化 **最坏情况下的尝试次数**：
 *   ```
 *   dp(K, N) = 1 + min{ max(dp(K-1, X-1), dp(K, N-X)) }  (1 <= X <= N)
 *   ```
 * - 采用 **二分搜索** 来加速搜索 `X`，使得 `dp(K-1, X-1) ≈ dp(K, N-X)`，从而减少时间复杂度。
 *
 * **示例分析**
 * --------------------------
 * **示例 1：K=2, N=6**
 * ```
 * 第 3 层：
 * - 碎了 -> 在 1-2 层寻找，最多需要 2 次。
 * - 没碎 -> 在 4-6 层寻找，最多需要 2 次。
 * ```
 * 总共 `3` 次尝试。
 *
 * **示例 2：K=3, N=14**
 * ```
 * 第 4 层：
 * - 碎了 -> 在 1-3 层寻找（最多 3 次）。
 * - 没碎 -> 在 5-14 层寻找（最多 4 次）。
 * ```
 * 总共 `4` 次尝试。
 *
 *
 * 时间和空间复杂度分析：
 * ---------------------------------
 * **方法 1：递归 + 记忆化搜索**
 * - **时间复杂度**: `O(K * log N)`
 * - **空间复杂度**: `O(K * N)`（存储 `memo` 记忆化数组）
 *
 * **方法 2：动态规划**
 * - **时间复杂度**: `O(K * N)`
 * - **空间复杂度**: `O(N)`
 */

public class LeetCode_887_SuperEggDrop{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 解法 1：动态规划 + 二分搜索
         * @param K 鸡蛋的个数
         * @param N 楼层的数量
         * @return 找到临界楼层 F 所需的最小尝试次数
         */
        public int superEggDrop(int K, int N) {
            return dp(K, N);
        }

        // 记忆化存储，用于缓存已经计算的结果，避免重复计算
        Map<Integer, Integer> memo = new HashMap();

        /**
         * 使用动态规划计算 dp(K, N)
         * @param K 鸡蛋数量
         * @param N 楼层数
         * @return 最小尝试次数
         */
        public int dp(int K, int N) {
            // 记忆化搜索，避免重复计算
            if (!memo.containsKey(N * 100 + K)) {
                int ans;
                // 如果楼层为 0，则不需要尝试
                if (N == 0)
                    ans = 0;
                    // 如果只有 1 个鸡蛋，则必须从第 1 层开始逐层尝试，最坏情况下尝试 N 次
                else if (K == 1)
                    ans = N;
                else {
                    int lo = 1, hi = N;
                    // 使用二分搜索优化搜索的楼层
                    while (lo + 1 < hi) {
                        int x = (lo + hi) / 2;
                        int t1 = dp(K-1, x-1); // 鸡蛋碎了，在 [1, x-1] 之间寻找
                        int t2 = dp(K, N-x);   // 鸡蛋没碎，在 [x+1, N] 之间寻找

                        if (t1 < t2)
                            lo = x;  // 说明 t2 更大，需要提高搜索范围
                        else if (t1 > t2)
                            hi = x;  // 说明 t1 更大，需要降低搜索范围
                        else
                            lo = hi = x; // 两者相等，直接选择 x
                    }

                    // 取最优方案，1 + max(碎的情况, 没碎的情况)
                    ans = 1 + Math.min(Math.max(dp(K-1, lo-1), dp(K, N-lo)),
                            Math.max(dp(K-1, hi-1), dp(K, N-hi)));
                }

                // 记忆化存储计算结果
                memo.put(N * 100 + K, ans);
            }

            return memo.get(N * 100 + K);
        }

        /**
         * 解法 2：动态规划
         * @param K 鸡蛋的个数
         * @param N 楼层的数量
         * @return 找到临界楼层 F 所需的最小尝试次数
         */
        public int superEggDrop2(int K, int N) {
            // 初始化 dp[i] 表示 1 个鸡蛋的情况，需要尝试 i 次
            int[] dp = new int[N+1];
            for (int i = 0; i <= N; ++i)
                dp[i] = i;

            // 遍历不同数量的鸡蛋
            for (int k = 2; k <= K; ++k) {
                // 创建新的 dp 数组存储 k 个鸡蛋的结果
                int[] dp2 = new int[N+1];
                int x = 1;
                for (int n = 1; n <= N; ++n) {
                    // 使用二分优化查找最优的 x
                    while (x < n && Math.max(dp[x-1], dp2[n-x]) > Math.max(dp[x], dp2[n-x-1]))
                        x++;

                    // 更新 dp2[n]，1 + max(碎了的情况, 没碎的情况)
                    dp2[n] = 1 + Math.max(dp[x-1], dp2[n-x]);
                }

                // 更新 dp 数组，作为下一轮计算的基础
                dp = dp2;
            }

            return dp[N];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_887_SuperEggDrop.Solution solution = new LeetCode_887_SuperEggDrop().new Solution();

        // 测试用例
        System.out.println(solution.superEggDrop(1, 10)); // 输出 10
        System.out.println(solution.superEggDrop(2, 6));  // 输出 3
        System.out.println(solution.superEggDrop(3, 14)); // 输出 4

        System.out.println(solution.superEggDrop2(1, 10)); // 输出 10
        System.out.println(solution.superEggDrop2(2, 6));  // 输出 3
        System.out.println(solution.superEggDrop2(3, 14)); // 输出 4
    }
}
/**
You are given k identical eggs and you have access to a building with n floors 
labeled from 1 to n. 

 You know that there exists a floor f where 0 <= f <= n such that any egg 
dropped at a floor higher than f will break, and any egg dropped at or below floor f 
will not break. 

 Each move, you may take an unbroken egg and drop it from any floor x (where 1 <
= x <= n). If the egg breaks, you can no longer use it. However, if the egg 
does not break, you may reuse it in future moves. 

 Return the minimum number of moves that you need to determine with certainty 
what the value of f is. 

 
 Example 1: 

 
Input: k = 1, n = 2
Output: 2
Explanation: 
Drop the egg from floor 1. If it breaks, we know that f = 0.
Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
If it does not break, then we know f = 2.
Hence, we need at minimum 2 moves to determine with certainty what the value of 
f is.
 

 Example 2: 

 
Input: k = 2, n = 6
Output: 3
 

 Example 3: 

 
Input: k = 3, n = 14
Output: 4
 

 
 Constraints: 

 
 1 <= k <= 100 
 1 <= n <= 10⁴ 
 

 Related Topics Math Binary Search Dynamic Programming 👍 3692 👎 198

*/
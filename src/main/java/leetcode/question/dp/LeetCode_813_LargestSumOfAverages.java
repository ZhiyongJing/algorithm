package leetcode.question.dp;

/**
  *@Question:  813. Largest Sum of Averages     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 43.6%      
  *@Time  Complexity: O(K*n^2)
  *@Space Complexity: O(N)
 */

/**
 * 这个问题的解题思路基于动态规划的思想。让我们来详细解释一下：
 *
 * ### 解题思路：
 *
 * 1. **前缀和数组：** 我们首先构建一个前缀和数组 `P`，其中 `P[i+1]` 表示数组 `A` 中前 `i+1` 个元素的和。
 * 这样我们可以用 `P[j] - P[i]` 表示数组从位置 `i` 到 `j` 的元素和。
 *
 * 2. **初始化一分组的情况：** 我们初始化一个长度为 `N` 的数组 `dp`，其中 `dp[i]` 表示将数组
 * 从位置 `i` 到 `N-1` 分成一个分组时的平均值。初始时，我们将 `dp[i]` 设置为从位置 `i` 到数组末尾的平均值，
 * 即 `(P[N] - P[i]) / (N - i)`。
 *
 * 3. **逐步增加分组数目：** 接下来，我们使用动态规划逐步增加分组数目。对于每个 `k`（其中 `k` 从 `0` 到 `K-1`），
 * 我们遍历数组中的每个位置 `i`，并考虑将数组从位置 `i` 到 `N-1` 分成一个新的分组。我们用 `(P[j] - P[i]) / (j - i)`
 * 表示新分组的平均值，然后加上之前分组的平均值 `dp[j]`。我们更新 `dp[i]` 为这两者中的较大值。
 *
 * 4. **结果：** 最终，`dp[0]` 就是我们所求的将数组划分为 `K` 个分组后的最大平均值和。
 *
 * ### 时间复杂度：
 *
 * - 构建前缀和数组的时间复杂度为 O(N)。
 * - 动态规划的嵌套循环中，外层循环执行 K-1 次，内层循环的总执行次数为 N*(N-1)/2 次。因此，动态规划的时间复杂度为 O(K*N^2)。
 *
 * 总的时间复杂度为 O(N + K*N^2)。
 *
 * ### 空间复杂度：
 *
 * - 使用了一个长度为 N+1 的前缀和数组，空间复杂度为 O(N)。
 * - 使用了一个长度为 N 的 dp 数组，空间复杂度为 O(N)。
 *
 * 总的空间复杂度为 O(N)。
 */

public class LeetCode_813_LargestSumOfAverages {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double largestSumOfAverages(int[] A, int K) {
            int N = A.length;
            double[] P = new double[N+1];

            // 计算前缀和数组 P
            for (int i = 0; i < N; ++i)
                P[i+1] = P[i] + A[i];

            double[] dp = new double[N];

            // 初始化 dp 数组，表示只有一个分组时的平均值
            for (int i = 0; i < N; ++i)
                dp[i] = (P[N] - P[i]) / (N - i);

            // 进行动态规划，逐步增加分组数目
            for (int k = 0; k < K-1; ++k)
                for (int i = 0; i < N; ++i)
                    for (int j = i+1; j < N; ++j)
                        // 更新 dp[i]，选择最大的平均值和
                        dp[i] = Math.max(dp[i], (P[j]-P[i]) / (j-i) + dp[j]);

            return dp[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_813_LargestSumOfAverages().new Solution();
        // 测试代码
        int[] testArray = {9, 1, 2, 3, 9};
        int k = 3;
        double result = solution.largestSumOfAverages(testArray, k);
        System.out.println("最大平均值和为：" + result);
    }
}

/**
You are given an integer array nums and an integer k. You can partition the 
array into at most k non-empty adjacent subarrays. The score of a partition is the 
sum of the averages of each subarray. 

 Note that the partition must use every integer in nums, and that the score is 
not necessarily an integer. 

 Return the maximum score you can achieve of all the possible partitions. 
Answers within 10⁻⁶ of the actual answer will be accepted. 

 
 Example 1: 

 
Input: nums = [9,1,2,3,9], k = 3
Output: 20.00000
Explanation: 
The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 +
 (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 

 Example 2: 

 
Input: nums = [1,2,3,4,5,6,7], k = 4
Output: 20.50000
 

 
 Constraints: 

 
 1 <= nums.length <= 100 
 1 <= nums[i] <= 10⁴ 
 1 <= k <= nums.length 
 

 Related Topics Array Dynamic Programming Prefix Sum 👍 2061 👎 95

*/
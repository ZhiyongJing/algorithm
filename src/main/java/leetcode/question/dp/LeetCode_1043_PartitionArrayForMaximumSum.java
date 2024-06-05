package leetcode.question.dp;

import java.util.Arrays;

/**
 *@Question:  1043. Partition Array for Maximum Sum
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N * K)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求我们将数组划分成若干个子数组，使得每个子数组的元素乘积之和最大。我们可以使用动态规划（DP）来解决这个问题，具体有两种方法：自顶向下的递归（带记忆化）和自底向上的动态规划。
 *
 * #### 方法1：自顶向下的递归（带记忆化）
 *
 * 1. **定义递归函数**：
 *    - 创建一个递归函数`maxSum`，该函数计算从`start`位置到数组末尾的最大和。
 *    - 该函数接受四个参数：数组`arr`，划分长度`k`，记忆化数组`dp`，当前开始位置`start`。
 *
 * 2. **递归基例**：
 *    - 如果`start`已经超出数组长度，返回0。
 *    - 如果`dp[start]`已经计算过，则直接返回`dp[start]`的值。
 *
 * 3. **递归计算**：
 *    - 初始化当前最大值`currMax`和答案`ans`为0。
 *    - 对于从`start`到`start + k`的每一个位置，计算当前子数组的最大值和其对应的最大和。
 *    - 更新答案为当前子数组的最大和与之前计算的最大和中的较大值。
 *
 * 4. **记忆化存储**：
 *    - 将计算出的最大和存储在`dp[start]`中，以便后续使用。
 *
 * 5. **调用递归函数**：
 *    - 在主函数中初始化`dp`数组并填充为-1（表示未计算）。
 *    - 调用递归函数从位置0开始计算。
 *
 * #### 方法2：自底向上的动态规划
 *
 * 1. **定义DP数组**：
 *    - 创建一个DP数组`dp`，`dp[i]`表示从位置`i`到数组末尾的最大和。
 *
 * 2. **初始化DP数组**：
 *    - 初始化`dp`数组为0。
 *
 * 3. **自底向上计算**：
 *    - 从数组的末尾向前计算，每个位置`start`的最大和。
 *    - 对于每个位置`start`，尝试将其与之后的`k`个元素组成子数组，并计算这些子数组的最大和。
 *    - 更新`dp[start]`为当前子数组的最大和与之前计算的最大和中的较大值。
 *
 * 4. **返回结果**：
 *    - 返回`dp[0]`，即从位置0开始的最大和。
 *
 * ### 时间和空间复杂度
 *
 * #### 时间复杂度
 * - **方法1（自顶向下的递归）**：时间复杂度为O(N * K)，其中N是数组的长度，K是给定的划分长度。对于每个位置，我们最多需要检查K个元素。
 * - **方法2（自底向上的动态规划）**：时间复杂度为O(N * K)，同样是对于每个位置，我们最多需要检查K个元素。
 *
 * #### 空间复杂度
 * - **方法1（自顶向下的递归）**：空间复杂度为O(N)，主要用于存储记忆化数组`dp`，以及递归调用栈的深度。
 * - **方法2（自底向上的动态规划）**：空间复杂度为O(N)，主要用于存储DP数组`dp`。
 *
 * ### 总结
 * 使用动态规划来解决数组划分问题，可以有效地优化时间复杂度。通过记忆化递归和自底向上的DP方法，我们能够在合理的时间和空间复杂度内求解该问题。
 */
public class LeetCode_1043_PartitionArrayForMaximumSum{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 递归函数，用于计算从start开始到数组末尾的最大和
        private int maxSum(int[] arr, int k, int[] dp, int start) {
            int N = arr.length;

            // 基本情况，如果start超出数组长度，返回0
            if (start >= N) {
                return 0;
            }

            // 如果dp[start]已经计算过，则直接返回
            if (dp[start] != -1) {
                return dp[start];
            }

            int currMax = 0, ans = 0;
            int end = Math.min(N, start + k);
            for (int i = start; i < end; i++) {
                currMax = Math.max(currMax, arr[i]);
                // 存储当前子数组所有选项的最大值
                ans = Math.max(ans, currMax * (i - start + 1) + maxSum(arr, k, dp, i + 1));
            }

            // 将结果存储在dp数组中以便重复使用
            return dp[start] = ans;
        }

        // 解决方案1：自顶向下
        public int maxSumAfterPartitioning(int[] arr, int k) {
            int[] dp = new int[arr.length];
            Arrays.fill(dp, -1);

            return maxSum(arr, k, dp, 0);
        }

        // 解决方案2：自底向上DP
        public int maxSumAfterPartitioning2(int[] arr, int k) {
            int N = arr.length;

            int[] dp = new int[N + 1];
            Arrays.fill(dp, 0);

            for (int start = N - 1; start >= 0; start--) {
                int currMax = 0;
                int end = Math.min(N, start + k);

                for (int i = start; i < end; i++) {
                    currMax = Math.max(currMax, arr[i]);
                    // 存储当前子数组所有选项的最大值
                    dp[start] = Math.max(dp[start], dp[i + 1] + currMax * (i - start + 1));
                }
            }
            return dp[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    // 测试代码
    public static void main(String[] args) {
        Solution solution = new LeetCode_1043_PartitionArrayForMaximumSum().new Solution();

        // 测试样例1
        int[] arr1 = {1, 15, 7, 9, 2, 5, 10};
        int k1 = 3;
        System.out.println(solution.maxSumAfterPartitioning(arr1, k1)); // 输出: 84

        // 测试样例2
        int[] arr2 = {1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3};
        int k2 = 4;
        System.out.println(solution.maxSumAfterPartitioning(arr2, k2)); // 输出: 83

        // 测试样例3
        int[] arr3 = {1};
        int k3 = 1;
        System.out.println(solution.maxSumAfterPartitioning(arr3, k3)); // 输出: 1
    }
}

/**
Given an integer array arr, partition the array into (contiguous) subarrays of 
length at most k. After partitioning, each subarray has their values changed to 
become the maximum value of that subarray. 

 Return the largest sum of the given array after partitioning. Test cases are 
generated so that the answer fits in a 32-bit integer. 

 
 Example 1: 

 
Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
 

 Example 2: 

 
Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
 

 Example 3: 

 
Input: arr = [1], k = 1
Output: 1
 

 
 Constraints: 

 
 1 <= arr.length <= 500 
 0 <= arr[i] <= 10⁹ 
 1 <= k <= arr.length 
 

 Related Topics Array Dynamic Programming 👍 4634 👎 410

*/
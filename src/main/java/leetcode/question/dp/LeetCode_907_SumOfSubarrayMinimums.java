package leetcode.question.dp;

/**
 *@Question:  907. Sum of Subarray Minimums
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 74.36%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

import java.util.Stack;

/**
 * ### 解题思路
 *
 * 这道题目要求计算所有子数组的最小值之和。由于子数组的数量非常多，直接枚举每个子数组的最小值显然不现实，因此需要寻找一种更高效的方法。
 *
 * #### 方法概述
 * 我们可以使用动态规划和单调栈相结合的方法来解决这个问题：
 *
 * 1. **动态规划 (Dynamic Programming)**：
 *    - 定义一个 `dp` 数组，其中 `dp[i]` 表示以第 `i` 个元素结尾的子数组的最小值之和。
 *    - 计算 `dp[i]` 的过程中需要用到 `dp[previousSmaller]`，其中 `previousSmaller` 是第 `i` 个元素之前第一个比 `i` 小的元素的索引。
 *
 * 2. **单调栈 (Monotonic Stack)**：
 *    - 使用单调栈来找到每个元素左侧第一个小于它的元素的索引。
 *    - 单调栈在遍历过程中保持栈内元素的单调递增顺序，这样可以在 O(1) 时间复杂度内找到所需的 `previousSmaller`。
 *
 * #### 具体步骤
 *
 * 1. 初始化：
 *    - 创建一个栈 `stack` 用于存储数组元素的索引。
 *    - 创建一个 `dp` 数组，初始值设为 0。
 *    - 初始化一个常数 `MOD` 用于结果取模。
 *
 * 2. 遍历数组：
 *    - 对于每个元素 `arr[i]`，从栈中弹出所有大于等于 `arr[i]` 的元素，确保栈内元素保持单调递增。
 *    - 如果栈不为空，栈顶元素就是 `previousSmaller` 元素的索引，否则 `arr[i]` 是当前最小值，贡献所有以 `i` 结尾的子数组。
 *    - 计算 `dp[i]`，如果存在 `previousSmaller` 元素，`dp[i]` 为 `dp[previousSmaller]` 加上 `arr[i]` 作为最小值的子数组个数乘以 `arr[i]`。否则，`dp[i]` 为 `(i + 1) * arr[i]`。
 *    - 将当前元素索引 `i` 压入栈中。
 *
 * 3. 计算结果：
 *    - 将 `dp` 数组中的所有元素相加得到最终结果。
 *
 * ### 时间和空间复杂度分析
 *
 * - **时间复杂度**：
 *   - 每个元素最多被压入和弹出栈一次，因此单调栈的操作时间复杂度为 O(N)。
 *   - 遍历数组并更新 `dp` 数组的时间复杂度为 O(N)。
 *   - 因此，整体时间复杂度为 O(N)。
 *
 * - **空间复杂度**：
 *   - `dp` 数组和栈的空间复杂度均为 O(N)。
 *   - 因此，整体空间复杂度为 O(N)。
 *
 * ### 总结
 *
 * 通过结合动态规划和单调栈，我们可以在 O(N) 的时间和空间复杂度内高效地计算出所有子数组的最小值之和。
 * 这种方法利用单调栈来快速找到每个元素之前第一个比它小的元素的索引，从而大大减少了计算量。
 */
public class LeetCode_907_SumOfSubarrayMinimums{

//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int sumSubarrayMins(int[] arr) {
            int MOD = 1000000007;  // 定义一个常数，用于结果取模

            Stack<Integer> stack = new Stack<>();  // 创建一个栈，用于存放数组元素的索引

            // 创建一个与输入数组 `arr` 大小相同的 dp 数组
            int[] dp = new int[arr.length];

            // 构建一个单调递增栈
            for (int i = 0; i < arr.length; i++) {
                // 当栈不为空且栈顶元素大于或等于当前元素时，弹出栈顶元素
                while (!stack.empty() && arr[stack.peek()] >= arr[i]) {
                    stack.pop();
                }

                // 如果存在 previousSmaller 元素
                if (stack.size() > 0) {
                    int previousSmaller = stack.peek();  // 获取上一个较小元素的索引
                    dp[i] = dp[previousSmaller] + (i - previousSmaller) * arr[i];  // 计算当前元素作为最小值的子数组个数
                } else {
                    // 如果不存在 previousSmaller 元素，则当前元素贡献所有以 i 结尾的子数组
                    dp[i] = (i + 1) * arr[i];
                }
                // 将当前索引压入栈中
                stack.push(i);
            }

            // 将 dp 数组中的所有元素相加得到答案
            long sumOfMinimums = 0;
            for (int count : dp) {
                sumOfMinimums += count;
                sumOfMinimums %= MOD;  // 取模
            }

            return (int) sumOfMinimums;  // 返回结果
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_907_SumOfSubarrayMinimums().new Solution();

        // 测试样例
        int[] arr1 = {3, 1, 2, 4};
        System.out.println(solution.sumSubarrayMins(arr1));  // 输出: 17

        int[] arr2 = {11, 81, 94, 43, 3};
        System.out.println(solution.sumSubarrayMins(arr2));  // 输出: 444
    }
}

/**
Given an array of integers arr, find the sum of min(b), where b ranges over 
every (contiguous) subarray of arr. Since the answer may be large, return the 
answer modulo 10⁹ + 7. 

 
 Example 1: 

 
Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,
4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
 

 Example 2: 

 
Input: arr = [11,81,94,43,3]
Output: 444
 

 
 Constraints: 

 
 1 <= arr.length <= 3 * 10⁴ 
 1 <= arr[i] <= 3 * 10⁴ 
 

 Related Topics Array Dynamic Programming Stack Monotonic Stack 👍 7976 👎 608

*/
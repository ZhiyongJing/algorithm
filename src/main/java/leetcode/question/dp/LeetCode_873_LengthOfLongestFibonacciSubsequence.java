package leetcode.question.dp;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  873. Length of Longest Fibonacci Subsequence
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 9.34%
 *@Time  Complexity: O(N^2), N is length of arr
 *@Space Complexity: O(N^2)
 */
/**
 * 题目描述：
 * LeetCode 873. Length of Longest Fibonacci Subsequence
 * 给定一个严格递增的数组 `arr`，要求找出最长的斐波那契式子序列的长度。
 * 斐波那契式子序列的定义：
 * - 至少包含 3 个元素
 * - 满足 `arr[i] + arr[j] == arr[k]` (i < j < k)
 *
 * 示例 1：
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释: 最长的斐波那契子序列为 [1,2,3,5,8]
 *
 * 示例 2：
 * 输入: arr = [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释: 最长的斐波那契子序列为 [1,11,12] 或 [7,11,18]
 *
 * 示例 3：
 * 输入: arr = [1,2,3,5,10,20,40]
 * 输出: 0
 * 解释: 无法找到满足条件的子序列。
 *
 * 题目要求返回最长的斐波那契式子序列的长度，如果没有符合条件的序列，返回 0。
 */

/**
 * 解题思路：
 * 该问题可以用 **动态规划 + 哈希表** 解决。
 * 主要思路是：
 * 1. **定义 DP 状态**
 *    - 定义 `dp[i][j]` 表示以 `arr[i]` 和 `arr[j]` 结尾的最长斐波那契子序列的长度。
 *    - 目标是遍历所有可能的 `(i, j)`，并找到最长的有效子序列。
 *
 * 2. **使用哈希表优化查找**
 *    - 由于 `arr[k] = arr[i] + arr[j]`，我们需要快速查找 `arr[i]` 是否在 `arr` 中。
 *    - 用 `HashMap<Integer, Integer> valToIdx` 存储 `arr` 中每个数值的索引，保证 O(1) 查找。
 *
 * 3. **填充 dp 数组**
 *    - 遍历 `arr` 的所有索引 `(curr, prev)`：
 *      - 计算 `arr[curr] - arr[prev]` 作为可能的前一个斐波那契数 `arr[prevIdx]`
 *      - 若 `prevIdx` 存在并满足 `arr[prevIdx] < arr[prev]`，则：
 *        - `dp[prev][curr] = dp[prevIdx][prev] + 1`
 *      - 否则：
 *        - `dp[prev][curr] = 2`（至少是长度 2）
 *      - 更新 `maxLen`
 *
 * 4. **最终返回最长斐波那契子序列的长度**
 *    - 如果 `maxLen > 2`，返回 `maxLen`，否则返回 `0`。
 *
 * 举例：
 * 以 `arr = [1,2,3,4,5,6,7,8]` 为例：
 * - 初始状态：
 *   - `dp` 数组为空，`valToIdx = {1:0, 2:1, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7}`
 * - 计算 `(curr=2, prev=1)` 时：
 *   - `arr[2] - arr[1] = 3 - 2 = 1`
 *   - `1` 存在于数组，`prevIdx = 0`
 *   - `dp[1][2] = dp[0][1] + 1 = 3`
 * - 计算 `(curr=3, prev=2)` 时：
 *   - `arr[3] - arr[2] = 4 - 3 = 1`
 *   - `1` 在数组中，但 `dp[0][2]` 之前没计算过，所以 `dp[2][3] = 2`
 * - 计算 `(curr=4, prev=2)` 时：
 *   - `arr[4] - arr[2] = 5 - 3 = 2`
 *   - `2` 存在于数组，`prevIdx = 1`
 *   - `dp[2][4] = dp[1][2] + 1 = 4`
 * - 按此方法继续计算，最终得到最长序列 `[1,2,3,5,8]`，长度 `5`。
 */

/**
 * 时间和空间复杂度分析：
 *
 * 1. **时间复杂度：O(N^2)**
 *    - 需要遍历所有的 `(i, j)` 组合，共 `O(N^2)`
 *    - 每次查找 `arr[i]` 是否存在于哈希表是 `O(1)`，所以总体 `O(N^2)`
 *
 * 2. **空间复杂度：O(N^2)**
 *    - 需要 `dp[i][j]` 数组存储每对 `(i, j)` 的最长斐波那契子序列长度，共 `O(N^2)`
 *    - 还需要 `O(N)` 额外空间存储 `HashMap`，但 `O(N^2)` 仍是主导。
 */


public class LeetCode_873_LengthOfLongestFibonacciSubsequence{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int lenLongestFibSubseq(int[] arr) {
            int maxLen = 0; // 记录最长的斐波那契子序列的长度

            // dp[i][j] 表示以 arr[i], arr[j] 结尾的最长斐波那契子序列的长度
            int[][] dp = new int[arr.length][arr.length];

            // 记录数组中的值及其索引，方便 O(1) 时间查找某个数是否存在
            Map<Integer, Integer> valToIdx = new HashMap<>();

            // 遍历数组，填充 dp 数组
            for (int curr = 0; curr < arr.length; curr++) {
                valToIdx.put(arr[curr], curr); // 将当前元素加入哈希表

                for (int prev = 0; prev < curr; prev++) {
                    // 计算前一个可能的斐波那契数
                    int diff = arr[curr] - arr[prev];

                    // 查找该数在数组中的索引
                    int prevIdx = valToIdx.getOrDefault(diff, -1);

                    // 只有当 diff 小于 arr[prev] 并且 diff 在数组中存在时，才是合法的斐波那契数列
                    if (diff < arr[prev] && prevIdx >= 0) {
                        // dp[prevIdx][prev] 已经存储了以 (prevIdx, prev) 结尾的序列长度
                        dp[prev][curr] = dp[prevIdx][prev] + 1;
                    } else {
                        // 否则，至少是一个长度为 2 的序列
                        dp[prev][curr] = 2;
                    }

                    // 更新最长子序列的长度
                    maxLen = Math.max(maxLen, dp[prev][curr]);
                }
            }

            // 如果最长子序列的长度小于等于 2，说明没有满足条件的斐波那契子序列，返回 0
            return maxLen > 2 ? maxLen : 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_873_LengthOfLongestFibonacciSubsequence().new Solution();

        // 测试样例
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8}; // 期望输出: 5 (序列: [1,2,3,5,8])
        System.out.println(solution.lenLongestFibSubseq(arr1));

        int[] arr2 = {1, 3, 7, 11, 12, 14, 18}; // 期望输出: 3 (序列: [1,11,12] 或 [7,11,18])
        System.out.println(solution.lenLongestFibSubseq(arr2));

        int[] arr3 = {1, 2, 3, 5, 10, 20, 40}; // 期望输出: 0 (无有效斐波那契序列)
        System.out.println(solution.lenLongestFibSubseq(arr3));
    }
}

/**
A sequence x1, x2, ..., xn is Fibonacci-like if: 

 
 n >= 3 
 xi + xi+1 == xi+2 for all i + 2 <= n 
 

 Given a strictly increasing array arr of positive integers forming a sequence, 
return the length of the longest Fibonacci-like subsequence of arr. If one does 
not exist, return 0. 

 A subsequence is derived from another sequence arr by deleting any number of 
elements (including none) from arr, without changing the order of the remaining 
elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8]. 

 
 Example 1: 

 
Input: arr = [1,2,3,4,5,6,7,8]
Output: 5
Explanation: The longest subsequence that is fibonacci-like: [1,2,3,5,8]. 

 Example 2: 

 
Input: arr = [1,3,7,11,12,14,18]
Output: 3
Explanation: The longest subsequence that is fibonacci-like: [1,11,12], [3,11,14
] or [7,11,18]. 

 
 Constraints: 

 
 3 <= arr.length <= 1000 
 1 <= arr[i] < arr[i + 1] <= 10⁹ 
 

 Related Topics Array Hash Table Dynamic Programming 👍 2608 👎 105

*/
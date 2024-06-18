package leetcode.question.dp;
/**
 *@Question:  718. Maximum Length of Repeated Subarray
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 31.319999999999997%
 *@Time  Complexity: O(M * N) where M,N are the lengths of A, B
 *@Space Complexity: O(M * N)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求我们找到两个数组中最长的公共子数组的长度。为了高效地解决这个问题，我们可以使用动态规划（Dynamic Programming，DP）的方法。
 *
 * #### 动态规划思路
 *
 * 1. **定义状态**：
 *    - 用一个二维数组 `dp` 来存储中间结果，其中 `dp[i][j]` 表示数组 `A` 中以 `A[i]` 结尾和数组 `B` 中以 `B[j]` 结尾的最长公共子数组的长度。
 *
 * 2. **状态转移方程**：
 *    - 如果 `A[i] == B[j]`，那么 `dp[i][j] = dp[i+1][j+1] + 1`。因为如果 `A[i]` 和 `B[j]` 相等，那么我们可以将当前的公共子数组长度加一。
 *    - 如果 `A[i] != B[j]`，那么 `dp[i][j] = 0`，表示以 `A[i]` 和 `B[j]` 结尾的公共子数组长度为0，因为结尾的元素不相同。
 *
 * 3. **初始化**：
 *    - 初始化一个二维数组 `dp`，大小为 `(A.length + 1) x (B.length + 1)`，所有元素初始值为0。
 *    - 之所以大小要比数组长度多1，是为了处理边界情况，即当 `i` 或 `j` 为数组长度时，不存在 `A[i]` 或 `B[j]`，这种情况下最长公共子数组的长度为0。
 *
 * 4. **填表**：
 *    - 我们从后向前遍历数组 `A` 和 `B`，逐步填充 `dp` 数组。这样可以利用已经计算的子问题结果来计算当前问题。
 *
 * 5. **记录结果**：
 *    - 在填表的过程中，我们维护一个变量 `ans` 来记录 `dp[i][j]` 的最大值，这个最大值即为两个数组中最长的公共子数组的长度。
 *
 * ### 时间和空间复杂度
 *
 * #### 时间复杂度
 * - **时间复杂度**为 `O(M * N)`，其中 `M` 和 `N` 分别是数组 `A` 和 `B` 的长度。因为我们需要遍历 `A` 和 `B` 的所有元素，填充 `dp` 数组。
 *
 * #### 空间复杂度
 * - **空间复杂度**为 `O(M * N)`，因为我们需要一个大小为 `(M + 1) x (N + 1)` 的二维数组 `dp` 来存储中间结果。虽然空间复杂度不是最优的，但是可以有效解决问题。
 *
 * ### 总结
 * 通过动态规划方法，我们能够高效地计算出两个数组中最长的公共子数组的长度。此方法利用了子问题的结果，避免了重复计算，显著提高了算法的效率。
 */

public class LeetCode_718_MaximumLengthOfRepeatedSubarray{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findLength(int[] A, int[] B) {
            // 初始化答案
            int ans = 0;
            // 创建一个二维数组memo用于存储子问题的解
            int[][] memo = new int[A.length + 1][B.length + 1];
            // 从后向前遍历数组A
            for (int i = A.length - 1; i >= 0; --i) {
                // 从后向前遍历数组B
                for (int j = B.length - 1; j >= 0; --j) {
                    // 如果A[i]和B[j]相等
                    if (A[i] == B[j]) {
                        // 计算memo[i][j]，表示以A[i]和B[j]为起点的最长公共子数组长度
                        memo[i][j] = memo[i+1][j+1] + 1;
                        // 更新答案
                        if (ans < memo[i][j]) {
                            ans = memo[i][j];
                        }
                    }
                }
            }
            // 返回最终答案
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_718_MaximumLengthOfRepeatedSubarray().new Solution();
        // 测试样例
        int[] A1 = {1, 2, 3, 2, 1};
        int[] B1 = {3, 2, 1, 4, 7};
        System.out.println("Maximum length of repeated subarray (test case 1): " + solution.findLength(A1, B1)); // 3

        int[] A2 = {0, 0, 0, 0, 0};
        int[] B2 = {0, 0, 0, 0, 0};
        System.out.println("Maximum length of repeated subarray (test case 2): " + solution.findLength(A2, B2)); // 5
    }
}

/**
Given two integer arrays nums1 and nums2, return the maximum length of a 
subarray that appears in both arrays. 

 
 Example 1: 

 
Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].
 

 Example 2: 

 
Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5
Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 

 
 Constraints: 

 
 1 <= nums1.length, nums2.length <= 1000 
 0 <= nums1[i], nums2[i] <= 100 
 

 Related Topics Array Binary Search Dynamic Programming Sliding Window Rolling 
Hash Hash Function 👍 6765 👎 167

*/
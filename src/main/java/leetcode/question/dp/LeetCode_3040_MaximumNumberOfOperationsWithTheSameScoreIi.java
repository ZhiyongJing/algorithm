package leetcode.question.dp;
/**
 *@Question:  3040. Maximum Number of Operations With the Same Score II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.540000000000006%
 *@Time  Complexity: O(n^2)
 *@Space Complexity: O(n^2)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求在数组中找到最大数量的操作，使得每个操作的得分相同。操作的得分由两个相邻的数字的和决定。问题的核心在于如何有效地使用动态规划来计算最大操作次数。
 *
 * 1. **问题建模**：
 *    - 我们需要找到一个有效的操作策略，使得每次操作的和保持一致。
 *    - 操作的得分由两个相邻数字的和决定，所以我们需要考虑所有可能的得分，并确保在子数组中保持一致。
 *
 * 2. **动态规划的定义**：
 *    - 使用动态规划来存储中间结果，避免重复计算。具体来说，我们定义一个 `memoization` 数组来记录子数组 `[left, right]` 的最大操作次数。
 *    - 对于每个子数组，我们考虑三种操作：
 *      1. 移除 `[left]` 和 `[left+1]` 的和。
 *      2. 移除 `[left]` 和 `[right]` 的和。
 *      3. 移除 `[right]` 和 `[right-1]` 的和。
 *    - 在每种操作下，我们递归地处理剩余的子数组，并将结果存入 `memoization` 数组。
 *
 * 3. **操作策略**：
 *    - 对于每种操作，计算得分是否等于 `previousSum`（之前的和）。
 *    - 如果等于，则可以进行这个操作，并递归地处理剩余的子数组。
 *    - 更新 `maxOps` 为当前操作下的最大操作次数，并返回 `memoization` 数组中的结果。
 *
 * ### 时间复杂度
 *
 * - **时间复杂度**：O(n^3)
 *   - 递归方法会处理多个子数组，每个子数组的操作复杂度为 O(n)。
 *   - 在最坏的情况下，递归的深度和子数组的数量都是 O(n^2)，每次递归处理的操作为 O(n)，因此总的时间复杂度为 O(n^3)。
 *
 * ### 空间复杂度
 *
 * - **空间复杂度**：O(n^2)
 *   - 使用了一个 `memoization` 数组来存储每个子数组的结果，这个数组的大小为 `n x n`。
 *   - 递归调用栈的深度最大为 O(n)，但由于递归的主要空间消耗来自于 `memoization` 数组，所以总空间复杂度为 O(n^2)。
 */

public class LeetCode_3040_MaximumNumberOfOperationsWithTheSameScoreIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxOperations(int[] nums) {
            int maxOperations = 0;  // 记录最大操作次数
            int len = nums.length;  // 数组长度
            int[][] memoization = new int[len][len];  // 备忘录，记录子问题的结果
            int start = 0;  // 初始左边界
            int end = len - 1;  // 初始右边界

            // 从不同的起始点尝试不同的操作，并更新最大操作次数
            maxOperations = Math.max(maxOperations,
                    findMaxOpsHelper(nums, start + 2, end, nums[start] + nums[start + 1], memoization) + 1);
            maxOperations = Math.max(maxOperations,
                    findMaxOpsHelper(nums, start + 1, end - 1, nums[start] + nums[end], memoization) + 1);
            maxOperations = Math.max(maxOperations,
                    findMaxOpsHelper(nums, start, end - 2, nums[end] + nums[end - 1], memoization) + 1);

            return maxOperations;  // 返回最大操作次数
        }

        // 递归方法，尝试在区间 [left, right] 上进行操作
        private int findMaxOpsHelper(int[] nums, int left, int right, int previousSum, int[][] memoization) {
            if (left >= right)  // 当左边界大于等于右边界时，返回0
                return 0;
            if (memoization[left][right] != 0)  // 如果该子问题已解决，直接返回结果
                return memoization[left][right];

            int maxOps = 0;  // 初始化最大操作次数
            // 如果当前两个数字的和等于 previousSum，尝试这个操作并递归
            if (nums[left] + nums[left + 1] == previousSum)
                maxOps = Math.max(maxOps, findMaxOpsHelper(nums, left + 2, right, previousSum, memoization) + 1);
            // 如果当前两个数字的和等于 previousSum，尝试这个操作并递归
            if (nums[left] + nums[right] == previousSum)
                maxOps = Math.max(maxOps, findMaxOpsHelper(nums, left + 1, right - 1, previousSum, memoization) + 1);
            // 如果当前两个数字的和等于 previousSum，尝试这个操作并递归
            if (nums[right] + nums[right - 1] == previousSum)
                maxOps = Math.max(maxOps, findMaxOpsHelper(nums, left, right - 2, previousSum, memoization) + 1);

            return memoization[left][right] = maxOps;  // 将结果存入备忘录并返回
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_3040_MaximumNumberOfOperationsWithTheSameScoreIi().new Solution();

        // 测试样例
        int[] nums1 = {1, 2, 3, 4, 5};  // 示例数组
        System.out.println(solution.maxOperations(nums1));  // 输出最大操作次数

        int[] nums2 = {3, 3, 3, 3, 3, 3};  // 示例数组
        System.out.println(solution.maxOperations(nums2));  // 输出最大操作次数

        int[] nums3 = {1, 1, 1, 1, 1, 1, 1};  // 示例数组
        System.out.println(solution.maxOperations(nums3));  // 输出最大操作次数
    }
}

/**
Given an array of integers called nums, you can perform any of the following 
operation while nums contains at least 2 elements: 

 
 Choose the first two elements of nums and delete them. 
 Choose the last two elements of nums and delete them. 
 Choose the first and the last elements of nums and delete them. 
 

 The score of the operation is the sum of the deleted elements. 

 Your task is to find the maximum number of operations that can be performed, 
such that all operations have the same score. 

 Return the maximum number of operations possible that satisfy the condition 
mentioned above. 

 
 Example 1: 

 
Input: nums = [3,2,1,2,3,4]
Output: 3
Explanation: We perform the following operations:
- Delete the first two elements, with score 3 + 2 = 5, nums = [1,2,3,4].
- Delete the first and the last elements, with score 1 + 4 = 5, nums = [2,3].
- Delete the first and the last elements, with score 2 + 3 = 5, nums = [].
We are unable to perform any more operations as nums is empty.
 

 Example 2: 

 
Input: nums = [3,2,6,1,4]
Output: 2
Explanation: We perform the following operations:
- Delete the first two elements, with score 3 + 2 = 5, nums = [6,1,4].
- Delete the last two elements, with score 1 + 4 = 5, nums = [6].
It can be proven that we can perform at most 2 operations.
 

 
 Constraints: 

 
 2 <= nums.length <= 2000 
 1 <= nums[i] <= 1000 
 

 Related Topics Array Dynamic Programming Memoization 👍 149 👎 14

*/
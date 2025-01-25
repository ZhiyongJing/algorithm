package leetcode.question.prefix_sum;

import java.util.HashMap;

/**
 * @Question:  325. Maximum Size Subarray Sum Equals k
 * @Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 36.32%
 * @Time  Complexity: O(N) N as the length of nums
 * @Space Complexity: O(N)
 */


/**
 *
 */

/**
 * 题目描述：
 * ---------------
 * 给定一个整数数组 nums 和一个目标整数 k，要求找到最长的子数组，使得该子数组的元素和等于 k。
 * 子数组是数组的连续部分，长度可以为 1 到 n。
 *
 * 例如：
 * 输入: nums = [1, -1, 5, -2, 3], k = 3
 * 输出: 4
 * 解释: 具有最大长度的子数组是 [1, -1, 5, -2]，其和为 3，长度为 4。
 *
 * ---------------
 *
 * 解题思路：
 * 1. **前缀和（Prefix Sum）**
 *    - 定义 `prefixSum` 变量来存储当前遍历到的数组前缀和，即 `prefixSum = nums[0] + nums[1] + ... + nums[i]`。
 *    - 目标是找到一个区间 `[i, j]`，使得 `prefixSum[j] - prefixSum[i] = k`，从而确定符合条件的子数组长度。
 *
 * 2. **哈希表存储前缀和**
 *    - 维护一个哈希表 `indices`，存储每个前缀和 `prefixSum` 第一次出现的索引，避免重复存储。
 *    - 这样可以确保获取最长的子数组长度，因为索引越早，子数组长度就越长。
 *    - 当 `prefixSum - k` 在 `indices` 中存在时，意味着 `prefixSum[i]` 之后的某段子数组和等于 `k`，计算子数组长度并更新最大长度。
 *
 * 3. **遍历数组并更新最长子数组**
 *    - 每次计算 `prefixSum`，检查是否已经等于 `k`，如果等于 `k`，更新 `longestSubarray`。
 *    - 如果 `prefixSum - k` 之前出现过，计算 `i - indices.get(prefixSum - k)` 作为子数组长度，并更新 `longestSubarray`。
 *    - 只存储 `prefixSum` 首次出现的索引，以确保最长子数组。
 *
 * 示例：
 * ----------
 * 输入: nums = [1, -1, 5, -2, 3], k = 3
 * 计算前缀和：
 * 1. i = 0, prefixSum = 1，存入哈希表 {1: 0}
 * 2. i = 1, prefixSum = 0，存入哈希表 {1: 0, 0: 1}
 * 3. i = 2, prefixSum = 5，存入哈希表 {1: 0, 0: 1, 5: 2}
 * 4. i = 3, prefixSum = 3，找到 prefixSum - k = 0，长度 = 4
 * 5. i = 4, prefixSum = 6，找到 prefixSum - k = 3，长度 = 2
 * 最大子数组长度为 4。
 *
 * ---------------
 *
 * 时间和空间复杂度分析：
 * 1. **时间复杂度：O(n)**
 *    - 只需遍历 `nums` 一次，因此时间复杂度为 O(n)。
 *    - 哈希表的插入、查询操作都是 O(1) 的平均时间复杂度。
 *
 * 2. **空间复杂度：O(n)**
 *    - 需要使用一个哈希表存储 `prefixSum`，最多存储 `n` 个不同的前缀和，因此空间复杂度为 O(n)。
 *
 */


public class LeetCode_325_MaximumSizeSubarraySumEqualsK {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算最大子数组长度
         *
         * @param nums 数组
         * @param k 目标和
         * @return 最大子数组长度
         */
        public int maxSubArrayLen(int[] nums, int k) {
            int prefixSum = 0; // 当前位置前缀和
            int longestSubarray = 0; // 记录最大子数组的长度
            HashMap<Integer, Integer> indices = new HashMap<>(); // 保存前缀和及其对应的索引

            // 遍历数组
            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i]; // 计算当前前缀和

                // 如果当前前缀和等于 k，说明从索引 0 到 i 形成的子数组满足条件
                if (prefixSum == k) {
                    longestSubarray = Math.max(longestSubarray, i + 1); // 更新最大子数组长度
                }

                // 如果 prefixSum - k 在 map 中存在，则说明存在一个子数组的和等于 k
                if (indices.containsKey(prefixSum - k)) {
                    // 计算从 indices.get(prefixSum - k) + 1 到 i 之间的子数组长度
                    longestSubarray = Math.max(longestSubarray, i - indices.get(prefixSum - k));
                }

                // 只有在 map 中没有存储该前缀和时，才存储当前索引
                // 这样可以保证相同的前缀和对应的是最早出现的索引，从而形成最长的子数组
                if (!indices.containsKey(prefixSum)) {
                    indices.put(prefixSum, i);
                }
            }
            System.out.println(indices); // 打印前缀和哈希表，便于调试

            return longestSubarray; // 返回最长子数组的长度
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 创建 Solution 类的实例
        LeetCode_325_MaximumSizeSubarraySumEqualsK.Solution solution = new LeetCode_325_MaximumSizeSubarraySumEqualsK().new Solution();

        // 测试代码1
        int[] nums1 = {1, -1, 5, -2, 3};
        int k1 = 3;
        System.out.println(solution.maxSubArrayLen(nums1, k1));  // 预期输出 4

        // 测试代码2
        int[] nums2 = {-2, -1, 2, 1};
        int k2 = 1;
        System.out.println(solution.maxSubArrayLen(nums2, k2));  // 预期输出 2

        // 测试代码3
        int[] nums3 = {3, 1, -1, 4, -3, 2};
        int k3 = 5;
        System.out.println(solution.maxSubArrayLen(nums3, k3));  // 预期输出 3

        // 测试代码4
        int[] nums4 = {1, 2, 3, 4, 5};
        int k4 = 11;
        System.out.println(solution.maxSubArrayLen(nums4, k4));  // 预期输出 0 （不存在满足条件的子数组）
    }
}


/**
 Given an integer array nums and an integer k, return the maximum length of a
 subarray that sums to k. If there is not one, return 0 instead.


 Example 1:


 Input: nums = [1,-1,5,-2,3], k = 3
 Output: 4
 Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.


 Example 2:


 Input: nums = [-2,-1,2,1], k = 1
 Output: 2
 Explanation: The subarray [-1, 2] sums to 1 and is the longest.



 Constraints:


 1 <= nums.length <= 2 * 10⁵
 -10⁴ <= nums[i] <= 10⁴
 -10⁹ <= k <= 10⁹


 Related Topics Array Hash Table Prefix Sum 👍 2001 👎 62

 */

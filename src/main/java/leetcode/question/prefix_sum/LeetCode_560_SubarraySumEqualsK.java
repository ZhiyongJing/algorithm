package leetcode.question.prefix_sum;

import java.util.HashMap;

/**
 *@Question:  560. Subarray Sum Equals K
 *@Difficulty: Medium
 *@Frequency: 86.85%
 *@Time Complexity: O(N) - 只需遍历数组一次
 *@Space Complexity: O(N) - 需要存储前缀和的哈希表
 */
/**
 * 题目描述：
 * ------------
 * 给定一个整数数组 `nums` 和一个目标整数 `k`，找到数组中**和等于 `k` 的连续子数组的个数**。
 *
 * - **子数组**：数组的连续部分，例如 `[3,4,7]` 是 `[3,4,7,2,-3,1,4,2]` 的一个子数组，而 `[3,7,2]` 不是。
 * - **返回满足条件的子数组数量**，即子数组的元素总和等于 `k`。
 *
 * 示例：
 * 1. 输入：`nums = [1, 1, 1], k = 2`
 *    - 可能的子数组：
 *      - `[1, 1]` (索引 `0-1`)
 *      - `[1, 1]` (索引 `1-2`)
 *    - 输出：`2`
 *
 * 2. 输入：`nums = [3, 4, 7, 2, -3, 1, 4, 2], k = 7`
 *    - 可能的子数组：
 *      - `[3, 4]`
 *      - `[7]`
 *      - `[4, 7, 2, -3, 1]`
 *      - `[7, 2, -3, 1]`
 *    - 输出：`4`
 *
 * ------------
 *
 * 解题思路：
 * ------------
 * **方法：前缀和 + 哈希表**
 *
 * 1. **使用前缀和计算子数组和**
 *    - 设 `prefixSum[i]` 表示从索引 `0` 到 `i` 的子数组总和：
 *      ```
 *      prefixSum[i] = nums[0] + nums[1] + ... + nums[i]
 *      ```
 *    - 要找到某个子数组 `[j+1, i]` 使其和等于 `k`，等价于：
 *      ```
 *      prefixSum[i] - prefixSum[j] = k  ⟹ prefixSum[j] = prefixSum[i] - k
 *      ```
 *    - 这意味着**如果 `prefixSum[i] - k` 在哈希表中出现过，那么存在 `map.get(prefixSum[i] - k)` 个子数组满足条件**。
 *
 * 2. **使用哈希表记录前缀和的出现次数**
 *    - 维护一个 `Map<Integer, Integer>`，用于存储前缀和 `prefixSum` 及其出现的次数。
 *    - 初始化 `map.put(0, 1)`，表示前缀和为 `0` 出现过一次，确保能计算完整子数组。
 *    - 遍历 `nums`：
 *      - 计算 `prefixSum += nums[i]`
 *      - 查找 `prefixSum - k` 是否在 `map` 中，如果存在，则累加计数 `count += map.get(prefixSum - k)`
 *      - 记录 `prefixSum` 出现次数 `map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1)`
 *
 * ------------
 *
 * **示例解释**
 * 示例：`nums = [3, 4, 7, 2, -3, 1, 4, 2], k = 7`
 * - 计算前缀和：
 *   ```
 *   prefixSum = [3, 7, 14, 16, 13, 14, 18, 20]
 *   ```
 * - 计算 `count`：
 *   ```
 *   prefixSum - k = [0, 3, 7, 9, 6, 7, 11, 13]
 *   ```
 * - `map` 记录：
 *   ```
 *   {0:1, 3:1, 7:1, 14:2, 16:1, 13:1, 18:1, 20:1}
 *   ```
 * - 最终找到 `4` 个子数组满足条件。
 *
 * ------------
 *
 * **时间和空间复杂度分析**
 * ------------
 * **时间复杂度：O(N)**
 * - 只需遍历 `nums` 一次，每次操作 `map` 为 `O(1)`，因此总复杂度为 `O(N)`。
 *
 * **空间复杂度：O(N)**
 * - 哈希表最多存储 `N` 个不同的前缀和，因此空间复杂度为 `O(N)`。
 */


public class LeetCode_560_SubarraySumEqualsK{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        /**
         * 计算和等于 k 的子数组数量
         *
         * @param nums 数组
         * @param k 目标和
         * @return 子数组数量
         */
        public int subarraySum(int[] nums, int k) {
            int count = 0; // 记录满足条件的子数组数量
            int sum = 0; // 记录当前的前缀和
            HashMap<Integer, Integer> map = new HashMap<>(); // 用于存储前缀和出现的次数
            map.put(0, 1); // 初始化前缀和 0 出现 1 次，保证能计算到完整数组的情况

            // 遍历数组
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i]; // 更新当前的前缀和

                // 如果 sum - k 存在于 map 中，说明存在一个子数组满足和为 k
                if (map.containsKey(sum - k)) {
                    count += map.get(sum - k); // 累加所有符合条件的子数组数量
                }

                // 记录当前前缀和的出现次数
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count; // 返回满足条件的子数组总数
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_560_SubarraySumEqualsK().new Solution();

        // 测试用例 1
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.println(solution.subarraySum(nums1, k1)); // 预期输出：2

        // 测试用例 2
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println(solution.subarraySum(nums2, k2)); // 预期输出：2

        // 测试用例 3
        int[] nums3 = {3, 4, 7, 2, -3, 1, 4, 2};
        int k3 = 7;
        System.out.println(solution.subarraySum(nums3, k3)); // 预期输出：4

        // 测试用例 4
        int[] nums4 = {1, -1, 0};
        int k4 = 0;
        System.out.println(solution.subarraySum(nums4, k4)); // 预期输出：3

        // 测试用例 5
        int[] nums5 = {2, -2, 2, -2};
        int k5 = 0;
        System.out.println(solution.subarraySum(nums5, k5)); // 预期输出：6
    }
}

/**
Given an array of integers nums and an integer k, return the total number of 
subarrays whose sum equals to k. 

 A subarray is a contiguous non-empty sequence of elements within an array. 

 
 Example 1: 
 Input: nums = [1,1,1], k = 2
Output: 2
 
 Example 2: 
 Input: nums = [1,2,3], k = 3
Output: 2
 
 
 Constraints: 

 
 1 <= nums.length <= 2 * 10⁴ 
 -1000 <= nums[i] <= 1000 
 -10⁷ <= k <= 10⁷ 
 

 Related Topics Array Hash Table Prefix Sum 👍 22488 👎 714

*/
package leetcode.question.two_pointer;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  992. Subarrays with K Different Integers
 *@Difficulty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 65.48%
 *@Time Complexity: O(N), where N is the length of the array.
 *@Space Complexity: O(N),
 */
/**
 * 题目描述：
 * 992. Subarrays with K Different Integers（包含 K 个不同整数的子数组）
 *
 * 给定一个整数数组 `nums` 和一个整数 `k`，求 `nums` 中 **恰好** 包含 `k` 个不同整数的子数组个数。
 *
 * **定义**
 * - 一个子数组是数组的连续非空部分。
 * - 一个子数组的 **不同整数个数** 是子数组中不同整数的个数。
 * - **目标**: 计算 `nums` 中恰好有 `k` 个不同整数的子数组数量。
 *
 * **示例 1**
 * ```plaintext
 * 输入: nums = [1,2,1,2,3], k = 2
 * 输出: 7
 * 解释:
 * 共有 7 个子数组恰好包含 2 个不同整数：
 * [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,3]
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: nums = [1,2,1,3,4], k = 3
 * 输出: 3
 * 解释:
 * 共有 3 个子数组恰好包含 3 个不同整数：
 * [1,2,1,3], [2,1,3], [1,3,4]
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * **方法：滑动窗口 + 计算“最多包含 K 个不同整数的子数组”**
 *
 * 1. **转换问题**
 *    - 计算 **最多** 包含 `k` 个不同整数的子数组个数 `F(k)`
 *    - 计算 **最多** 包含 `k-1` 个不同整数的子数组个数 `F(k-1)`
 *    - **两者相减** 得到 **恰好** 包含 `k` 个不同整数的子数组数量：
 *      ```plaintext
 *      subarraysWithKDistinct(nums, k) = F(k) - F(k - 1)
 *      ```
 *
 * 2. **如何计算 `F(k)`**
 *    - 使用 **滑动窗口** 方法：
 *      - 维护一个窗口 `[left, right]`
 *      - 右指针 `right` 右移，扩大窗口
 *      - 若窗口内不同整数超过 `k`，左指针 `left` 右移，缩小窗口
 *      - **当前窗口 `[left, right]` 的所有子数组个数为 `right - left + 1`**
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `nums = [1,2,1,2,3]`, `k = 2`
 *
 * **计算 `F(2)`**
 * ```plaintext
 * 右指针扩展窗口：
 * [1]     -> 1
 * [1,2]   -> 2
 * [1,2,1] -> 3
 * [1,2,1,2] -> 4
 * [1,2,1,2,3] -> 2
 * 总计：12
 * ```
 *
 * **计算 `F(1)`**
 * ```plaintext
 * 右指针扩展窗口：
 * [1]     -> 1
 * [2]     -> 1
 * [1]     -> 1
 * [2]     -> 1
 * [3]     -> 1
 * 总计：5
 * ```
 *
 * **计算最终结果**
 * ```plaintext
 * F(2) - F(1) = 12 - 5 = 7
 * ```
 * **最终输出** `7`
 *
 * ---
 *
 * **时间复杂度分析**
 * - **滑动窗口遍历 `O(N)`**
 *   - 右指针 `right` 遍历整个数组 `O(N)`
 *   - 左指针 `left` 只会前进，不会回退 `O(N)`
 *   - **总时间复杂度：O(N)**
 *
 * **空间复杂度分析**
 * - **使用 `freqMap` 记录 `K` 个不同整数的频率**
 * - **最坏情况下** `K = N`，需要 `O(N)` 额外空间
 * - **一般情况下** 仅存 `K` 个不同元素，`O(K) ≈ O(1)`
 * - **总空间复杂度：O(K)**
 */

public class LeetCode_992_SubarraysWithKDifferentIntegers{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int subarraysWithKDistinct(int[] nums, int k) {
            // 计算最多包含 K 个不同整数的子数组数量 - 计算最多包含 K-1 个不同整数的子数组数量
            return slidingWindowAtMost(nums, k) - slidingWindowAtMost(nums, k - 1);
        }

        // 计算最多包含 distinctK 个不同整数的子数组数量
        private int slidingWindowAtMost(int[] nums, int distinctK) {
            // 使用 HashMap 记录窗口内元素的出现次数
            Map<Integer, Integer> freqMap = new HashMap<>();
            int left = 0, totalCount = 0;

            // 右指针遍历整个数组
            for (int right = 0; right < nums.length; right++) {
                // 增加当前元素的出现次数
                freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

                // 如果窗口内的不同元素个数超过 distinctK，则收缩窗口
                while (freqMap.size() > distinctK) {
                    // 减少左侧元素的出现次数
                    freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                    // 如果左侧元素的次数降为 0，则从 Map 中移除
                    if (freqMap.get(nums[left]) == 0) {
                        freqMap.remove(nums[left]);
                    }
                    // 左指针右移
                    left++;
                }

                // 计算当前窗口内的子数组数量（所有以 right 结尾的子数组）
                totalCount += (right - left + 1);
            }
            return totalCount;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_992_SubarraysWithKDifferentIntegers().new Solution();

        // 测试样例 1
        int[] nums1 = {1, 2, 1, 2, 3};
        int k1 = 2;
        System.out.println(solution.subarraysWithKDistinct(nums1, k1)); // 预期输出: 7

        // 测试样例 2
        int[] nums2 = {1, 2, 1, 3, 4};
        int k2 = 3;
        System.out.println(solution.subarraysWithKDistinct(nums2, k2)); // 预期输出: 3

        // 测试样例 3
        int[] nums3 = {2, 1, 2, 1, 2, 1, 2, 1};
        int k3 = 2;
        System.out.println(solution.subarraysWithKDistinct(nums3, k3)); // 预期输出: 22

        // 测试样例 4
        int[] nums4 = {1, 2, 3, 4, 5};
        int k4 = 1;
        System.out.println(solution.subarraysWithKDistinct(nums4, k4)); // 预期输出: 5

        // 测试样例 5
        int[] nums5 = {1, 2, 3, 1, 2, 3, 1, 2, 3};
        int k5 = 3;
        System.out.println(solution.subarraysWithKDistinct(nums5, k5)); // 预期输出: 18
    }
}

/**
Given an integer array nums and an integer k, return the number of good 
subarrays of nums. 

 A good array is an array where the number of different integers in that array 
is exactly k. 

 
 For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3. 
 

 A subarray is a contiguous part of an array. 

 
 Example 1: 

 
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [
1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 

 Example 2: 

 
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1
,3], [1,3,4].
 

 
 Constraints: 

 
 1 <= nums.length <= 2 * 10⁴ 
 1 <= nums[i], k <= nums.length 
 

 Related Topics Array Hash Table Sliding Window Counting 👍 6318 👎 102

*/
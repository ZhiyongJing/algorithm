package leetcode.question.prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  523. Continuous Subarray Sum
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.18000000000001%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求判断是否存在一个和为k的倍数的非空子数组，我们可以使用前缀和来解决。
 *
 * #### 解法
 *
 * 1. **前缀和数组：** 我们使用一个哈希表 `map` 来存储每个前缀和对 k 取余数的结果以及对应的下标。
 * 初始时，将前缀和 `sum` 初始化为 0，遍历数组，更新前缀和并计算 `sum % k`，然后根据不同情况进行判断。
 *
 * 2. **判断条件：**
 *    - 如果 `sum` 为 0 且当前下标大于 0，说明存在长度至少为 2 的子数组，和为 k 的倍数。
 *    - 如果哈希表中已存在相同余数 `sum`，并且当前下标与哈希表中对应的下标的距离大于 1，说明存在长度至少为 2 的子数组，和为 k 的倍数。
 *
 * 3. **更新哈希表：** 将当前余数 `sum` 和对应的下标存入哈希表。
 *
 * #### 时间复杂度
 *
 * 遍历数组的过程中，对于每个元素，我们都进行了 O(1) 的哈希表操作，因此总时间复杂度为 O(N)，其中 N 是数组的长度。
 *
 * #### 空间复杂度
 *
 * 哈希表 `map` 存储了每个前缀和对 k 取余数的结果以及对应的下标，因此空间复杂度为 O(N)，其中 N 是数组的长度。
 */
//sum1 + sum2 = 6x + y
//sum1 = 6z + y


public class LeetCode_523_ContinuousSubarraySum{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            // 使用哈希表存储余数与对应的下标
            Map<Integer, Integer> map = new HashMap<>();
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                sum %= k;

                // 情况1：如果 sum 为 0 且当前下标大于0，说明存在长度至少为2的子数组，和为k的倍数
                if (sum == 0 && i > 0) {
                    return true;
                }
                // 情况2：如果哈希表中已存在相同余数，并且当前下标与哈希表中对应的下标的距离大于1，说明存在长度至少为2的子数组，和为k的倍数
                if (map.containsKey(sum) && i - map.get(sum) > 1) {
                    System.out.println(map);
                    return true;
                }
                // 将当前余数和对应的下标存入哈希表
                if (!map.containsKey(sum)) {
                    map.put(sum, i);
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_523_ContinuousSubarraySum().new Solution();
        // 测试代码
        int[] nums1 = {23, 2, 4, 6, 7};
        int k1 = 6;
        System.out.println(solution.checkSubarraySum(nums1, k1));  // 应该输出 true

        int[] nums2 = {23, 2, 6, 4, 7};
        int k2 = 6;
        System.out.println(solution.checkSubarraySum(nums2, k2));  // 应该输出 true

        int[] nums3 = {23, 2, 6, 4, 7};
        int k3 = 13;
        System.out.println(solution.checkSubarraySum(nums3, k3));  // 应该输出 false
    }
}
/**
 Given an integer array nums and an integer k, return true if nums has a good
 subarray or false otherwise.

 A good subarray is a subarray where:


 its length is at least two, and
 the sum of the elements of the subarray is a multiple of k.


 Note that:


 A subarray is a contiguous part of the array.
 An integer x is a multiple of k if there exists an integer n such that x = n *
 k. 0 is always a multiple of k.



 Example 1:


 Input: nums = [23,2,4,6,7], k = 6
 Output: true
 Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to
 6.


 Example 2:


 Input: nums = [23,2,6,4,7], k = 6
 Output: true
 Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose
 elements sum up to 42.
 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.


 Example 3:


 Input: nums = [23,2,6,4,7], k = 13
 Output: false



 Constraints:


 1 <= nums.length <= 10⁵
 0 <= nums[i] <= 10⁹
 0 <= sum(nums[i]) <= 2³¹ - 1
 1 <= k <= 2³¹ - 1


 Related Topics Array Hash Table Math Prefix Sum 👍 5302 👎 517
*/

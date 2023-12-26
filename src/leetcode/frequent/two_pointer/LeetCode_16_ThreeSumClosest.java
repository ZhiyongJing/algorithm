package leetcode.frequent.two_pointer;

import java.util.Arrays;

/**
  *@Question:  16. 3Sum Closest     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 59.699999999999996%      
  *@Time  Complexity: O(N^2)
  *@Space Complexity: O(logN) or O(N) depends on the sort algorithm.
 */

/**
 * **解题思路:**
 *
 * 1. **排序数组：** 首先对输入的整数数组进行排序。这可以使用快速排序等时间复杂度为 O(NlogN) 的排序算法来实现。
 *
 * 2. **双指针法：** 排序后，使用双指针法来遍历数组。对于数组中的每个元素 nums[i]，使用双指针 lo 和 hi，分别指向数组的剩余部分。
 *
 * 3. **三数之和计算：** 在每次循环中，计算当前三个数的和 sum = nums[i] + nums[lo] + nums[hi]。
 *
 * 4. **更新差值：** 计算当前 sum 与目标值 target 的差值 diff = target - sum，并更新最小差值 diff。
 *
 * 5. **指针调整：**
 *    - 如果 diff 等于 0，表示找到了和目标值完全相等的三数之和，直接返回 target。
 *    - 如果 diff 大于 0，表示当前三数之和偏小，将 lo 指针右移一位。
 *    - 如果 diff 小于 0，表示当前三数之和偏大，将 hi 指针左移一位。
 *
 * 6. **循环结束：** 重复以上步骤，直到遍历完整个数组。在遍历过程中，不断更新最小差值 diff。
 *
 * 7. **返回结果：** 最终返回 target - diff，即最接近目标值的三数之和。
 *
 * **时间复杂度:** 排序的时间复杂度为 O(NlogN)，外层循环遍历数组，内层循环使用双指针法，总体时间复杂度为 O(N^2)。
 *
 * **空间复杂度:** 排序过程中可能使用 O(logN) 的额外空间，取决于排序算法的实现。
 * 在双指针法中，使用的额外空间为 O(1)。因此，总体空间复杂度为 O(logN) 或 O(N)，取决于排序算法。
 */
public class LeetCode_16_ThreeSumClosest {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            int diff = Integer.MAX_VALUE; // 用于存储当前最小差值的初始值
            int sz = nums.length;
            Arrays.sort(nums); // 对数组进行排序
            for (int i = 0; i < sz && diff != 0; ++i) {
                int lo = i + 1;
                int hi = sz - 1;
                while (lo < hi) {
                    int sum = nums[i] + nums[lo] + nums[hi];
                    // 更新最小差值
                    if (Math.abs(target - sum) < Math.abs(diff)) {
                        diff = target - sum;
                    }
                    // 根据当前和与目标值的比较，调整指针
                    if (sum < target) {
                        ++lo;
                    } else {
                        --hi;
                    }
                }
            }
            return target - diff;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_16_ThreeSumClosest().new Solution();

        // 测试用例
        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println(solution.threeSumClosest(nums1, target1)); // 预期输出: 2

        int[] nums2 = {0, 1, 2};
        int target2 = 3;
        System.out.println(solution.threeSumClosest(nums2, target2)); // 预期输出: 3

        int[] nums3 = {1, 1, 1, 1};
        int target3 = 0;
        System.out.println(solution.threeSumClosest(nums3, target3)); // 预期输出: 3
    }
}

/**
Given an integer array nums of length n and an integer target, find three 
integers in nums such that the sum is closest to target. 

 Return the sum of the three integers. 

 You may assume that each input would have exactly one solution. 

 
 Example 1: 

 
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 

 Example 2: 

 
Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 

 
 Constraints: 

 
 3 <= nums.length <= 500 
 -1000 <= nums[i] <= 1000 
 -10⁴ <= target <= 10⁴ 
 

 Related Topics Array Two Pointers Sorting 👍 10080 👎 533

*/

package leetcode.question.prefix_sum;

/**
 *@Question:  53. Maximum Subarray
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 87.4%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 这道题要求找到数组中连续子数组的最大和。我们可以使用动态规划的思想来解决这个问题，同时通过维护两个变量来避免使用额外的数组。
 *
 * 1. **初始化：** 我们初始化两个变量 `currentSubarray` 和 `maxSubarray`，都设置为数组的第一个元素。
 * 它们表示当前子数组的和和最大子数组的和。
 *
 * 2. **遍历数组：** 从数组的第二个元素开始，我们遍历整个数组。在遍历的过程中，我们计算当前子数组的和 `currentSubarray`，
 * 并更新 `currentSubarray` 为当前元素与 `currentSubarray + num` 的较大值。
 *
 * 3. **更新最大子数组和：** 在每一步中，我们都比较 `maxSubarray` 和 `currentSubarray`，将较大的值赋给 `maxSubarray`。
 * 这样可以确保 `maxSubarray` 始终记录数组中连续子数组的最大和。
 *
 * 4. **返回结果：** 遍历完成后，`maxSubarray` 就是整个数组中连续子数组的最大和。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度：** O(N)，其中 N 是数组的长度。我们只对数组进行了一次遍历。
 *
 * - **空间复杂度：** O(1)，只使用了常数额外空间，没有使用额外的数组。
 */

public class LeetCode_53_MaximumSubarray{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算最大子数组的和
         *
         * @param nums 输入数组
         * @return 最大子数组的和
         */
        public int maxSubArray(int[] nums) {
            // 初始化变量，使用数组的第一个元素
            int currentSubarray = nums[0];
            int maxSubarray = nums[0];

            // 从第二个元素开始遍历数组，因为第一个元素已经被使用过了
            for (int i = 1; i < nums.length; i++) {
                int num = nums[i];
                // 如果当前子数组和为负数，舍弃它；否则，继续累加
                currentSubarray = Math.max(num, currentSubarray + num);
                // 更新最大子数组和
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }

            return maxSubarray;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_53_MaximumSubarray().new Solution();
        // 测试代码
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray(nums1));  // 应该输出 6

        int[] nums2 = {1};
        System.out.println(solution.maxSubArray(nums2));  // 应该输出 1

        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println(solution.maxSubArray(nums3));  // 应该输出 23
    }
}
/**
 Given an integer array nums, find the subarray with the largest sum, and return
 its sum.


 Example 1: 


 Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 Output: 6
 Explanation: The subarray [4,-1,2,1] has the largest sum 6.


 Example 2: 


 Input: nums = [1]
 Output: 1
 Explanation: The subarray [1] has the largest sum 1.


 Example 3: 


 Input: nums = [5,4,-1,7,8]
 Output: 23
 Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.



 Constraints: 


 1 <= nums.length <= 10⁵ 
 -10⁴ <= nums[i] <= 10⁴ 



 Follow up: If you have figured out the O(n) solution, try coding another 
 solution using the divide and conquer approach, which is more subtle.

 Related Topics Array Divide and Conquer Dynamic Programming 👍 32831 👎 1374

 */

package leetcode.frequent.binary_search;

/**
 *@Question:  410. Split Array Largest Sum
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 74.54%
 *@Time  Complexity: O(NlogS) N is the length of the array and S is the sum of integers in the array
 *@Space Complexity: O(1)
 */

/**
 * **算法思路：**
 *
 * 这个问题是一个二分查找的应用，目标是找到一个最小的最大值，使得数组可以划分为不超过 `m` 个子数组，每个子数组的和不超过这个最小的最大值。具体的算法思路如下：
 *
 * 1. **最大最小值范围：** 首先，找到数组中元素的最大值（`maxElement`）和所有元素的和（`sum`）。在二分查找时，左边界 `left` 可以设为 `maxElement`，
 * 右边界 `right` 可以设为 `sum`。
 *
 * 2. **二分查找：** 在每一次迭代中，计算中间值 `maxSumAllowed`。然后，通过一个辅助函数 `minimumSubarraysRequired` 计算在给定的最大和允许的情况下，
 * 数组可以被划分的最小子数组个数。如果这个数量小于等于 `m`，说明我们可以尝试找到更小的最大值，所以将右边界 `right` 更新为 `maxSumAllowed - 1`；
 * 否则，说明最大值太小，需要增大，所以将左边界 `left` 更新为 `maxSumAllowed + 1`。
 *
 * 3. **返回结果：** 当 `left` 和 `right` 相遇时，说明找到了满足条件的最小最大值，即 `left - 1`。
 *
 * **时间复杂度：**
 *
 * - 在二分查找的每一次迭代中，都需要调用辅助函数 `minimumSubarraysRequired` 来计算子数组的个数，这个函数的时间复杂度为 O(N)，
 * 其中 N 是数组的长度。二分查找的迭代次数为 O(logS)，其中 S 是数组的元素和。因此，总体时间复杂度为 O(N logS)。
 *
 * **空间复杂度：**
 *
 * - 空间复杂度为 O(1)，没有使用额外的数据结构，只使用了常数级别的额外空间。
 */

public class LeetCode_410_SplitArrayLargestSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算在给定的最大和允许的情况下，数组可以被划分的最小子数组个数
         * @param nums 给定的数组
         * @param maxSumAllowed 最大和允许的值
         * @return 最小子数组个数
         */
        private int minimumSubarraysRequired(int[] nums, int maxSumAllowed) {
            int currentSum = 0;
            int splitsRequired = 0;

            for (int element : nums) {
                // 只有在当前和不超过最大和允许的情况下才添加元素
                if (currentSum + element <= maxSumAllowed) {
                    currentSum += element;
                } else {
                    // 如果添加元素使得和超过最大和允许，增加划分数并重置和
                    currentSum = element;
                    splitsRequired++;
                }
            }

            // 返回子数组的个数，即划分数 + 1
            return splitsRequired + 1;
        }

        /**
         * 划分数组使得每个子数组的和不超过给定的最大值
         * @param nums 给定的数组
         * @param m 需要划分的子数组个数
         * @return 划分后的子数组中，每个子数组的和的最大值的最小可能值
         */
        public int splitArray(int[] nums, int m) {
            // 计算数组所有元素的和以及最大元素值
            int sum = 0;
            int maxElement = Integer.MIN_VALUE;
            for (int element : nums) {
                sum += element;
                maxElement = Math.max(maxElement, element);
            }

            // 定义二分查找的左右边界
            int left = maxElement;
            int right = sum;
            int minimumLargestSplitSum = 0;
            while (left <= right) {
                // 计算中间值
                int maxSumAllowed = left + (right - left) / 2;

                // 计算最小划分数，如果划分数小于等于 m，向左移动，即找更小的值
                if (minimumSubarraysRequired(nums, maxSumAllowed) <= m) {
                    right = maxSumAllowed - 1;
                    minimumLargestSplitSum = maxSumAllowed;
                } else {
                    // 如果划分数大于 m，向右移动
                    left = maxSumAllowed + 1;
                }
            }

            return minimumLargestSplitSum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_410_SplitArrayLargestSum().new Solution();

        // 测试代码
        int[] testArray = {7, 2, 5, 10, 8};
        int m = 2;
        int result = solution.splitArray(testArray, m);

        System.out.println("划分后的子数组中，每个子数组的和的最大值的最小可能值是：" + result);
    }
}

/**
Given an integer array nums and an integer k, split nums into k non-empty 
subarrays such that the largest sum of any subarray is minimized. 

 Return the minimized largest sum of the split. 

 A subarray is a contiguous part of the array. 

 
 Example 1: 

 
Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum 
among the two subarrays is only 18.
 

 Example 2: 

 
Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among 
the two subarrays is only 9.
 

 
 Constraints: 

 
 1 <= nums.length <= 1000 
 0 <= nums[i] <= 10⁶ 
 1 <= k <= min(50, nums.length) 
 

 Related Topics Array Binary Search Dynamic Programming Greedy Prefix Sum 👍 928
4 👎 193

*/

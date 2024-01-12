package leetcode.question.binary_search;

/**
  *@Question:  34. Find First and Last Position of Element in Sorted Array     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 78.75%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

import java.util.Arrays;

/**
 * 这个算法采用了二分查找的思想，但稍微复杂一些，因为我们要查找目标元素的第一个和最后一个位置。
 *
 * ### 算法思路：
 *
 * 1. **查找第一个出现的位置：** 定义一个辅助方法 `findBound`，它接受三个参数：数组 `nums`、目标元素 `target`
 * 以及一个布尔值 `isFirst`，表示是否查找目标元素的第一个位置。在该方法中，使用二分查找的变种，不断调整搜索范围，直到找到目标元素的第一个位置。
 *
 * 2. **查找最后一个出现的位置：** 同样在 `findBound` 方法中，当 `isFirst` 为 `false` 时，调整搜索范围，直到找到目标元素的最后一个位置。
 *
 * 3. **主方法 `searchRange`：** 在主方法中，首先调用 `findBound` 找到目标元素的第一个位置，如果找不到，直接返回 `{-1, -1}`。
 * 然后再次调用 `findBound` 找到目标元素的最后一个位置。最终返回包含这两个位置的数组。
 *
 * ### 时间复杂度：
 *
 * - 由于每次调用 `findBound` 都是二分查找，时间复杂度为 O(logN)。
 * - 主方法 `searchRange` 调用了两次 `findBound`，所以总体时间复杂度为 O(logN)。
 *
 * ### 空间复杂度：
 *
 * - 空间复杂度为 O(1)，因为没有使用额外的数据结构，只使用了常数级别的变量。
 */
public class LeetCode_34_FindFirstAndLastPositionOfElementInSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 在排序数组中查找元素的第一个和最后一个位置
         *
         * @param nums   排序数组
         * @param target 目标元素
         * @return 包含目标元素的第一个和最后一个位置的数组，如果未找到返回[-1, -1]
         */
        public int[] searchRange(int[] nums, int target) {
            // 查找第一个和最后一个位置的方法
            int firstOccurrence = this.findBound(nums, target, true);

            if (firstOccurrence == -1) {
                // 如果找不到目标元素，返回{-1, -1}
                return new int[]{-1, -1};
            }

            int lastOccurrence = this.findBound(nums, target, false);

            return new int[]{firstOccurrence, lastOccurrence};
        }

        /**
         * 二分查找辅助方法
         *
         * @param nums    排序数组
         * @param target  目标元素
         * @param isFirst 是否查找第一个出现的位置
         * @return 目标元素的位置，未找到返回-1
         */
        private int findBound(int[] nums, int target, boolean isFirst) {
            int N = nums.length;
            int begin = 0, end = N - 1;

            while (begin <= end) {

                int mid = (begin + end) / 2;

                if (nums[mid] == target) {

                    if (isFirst) {
                        // 找到目标元素的第一个位置
                        if (mid == begin || nums[mid - 1] != target) {
                            return mid;
                        }

                        // 在左侧继续搜索边界
                        end = mid - 1;

                    } else {
                        // 找到目标元素的最后一个位置
                        if (mid == end || nums[mid + 1] != target) {
                            return mid;
                        }

                        // 在右侧继续搜索边界
                        begin = mid + 1;
                    }

                } else if (nums[mid] > target) {
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            }

            return -1; // 如果未找到目标元素，返回-1
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_34_FindFirstAndLastPositionOfElementInSortedArray.Solution solution =
                new LeetCode_34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();

        // 测试代码
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = solution.searchRange(nums, target);

        // 预期输出: [3, 4]
        System.out.println(Arrays.toString(result));
    }
}

/**
Given an array of integers nums sorted in non-decreasing order, find the 
starting and ending position of a given target value. 

 If target is not found in the array, return [-1, -1]. 

 You must write an algorithm with O(log n) runtime complexity. 

 
 Example 1: 
 Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
 
 Example 2: 
 Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 
 Example 3: 
 Input: nums = [], target = 0
Output: [-1,-1]
 
 
 Constraints: 

 
 0 <= nums.length <= 10⁵ 
 -10⁹ <= nums[i] <= 10⁹ 
 nums is a non-decreasing array. 
 -10⁹ <= target <= 10⁹ 
 

 Related Topics Array Binary Search 👍 19646 👎 475

*/

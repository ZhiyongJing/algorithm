package leetcode.frequent.binary_search;

/**
  *@Question:  33. Search in Rotated Sorted Array     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 81.44%      
  *@Time  Complexity: O(logN)
  *@Space Complexity: O(1)
 */

/**
 * 这个算法使用了二分查找的变种来在旋转排序数组中搜索目标值。下面是算法的详细思路：
 *
 * ### 算法思路：
 *
 * 1. **初始化指针：** 使用两个指针 `left` 和 `right` 分别指向数组的起始和结束位置。
 *
 * 2. **循环查找：** 在每一次循环中，计算中间位置 `mid`，并分为以下三种情况：
 *
 *    - **情况1：找到目标值 `target`：** 如果 `nums[mid]` 等于目标值，返回 `mid`。
 *
 *    - **情况2：mid左侧的子数组是有序的：** 如果 `nums[mid] >= nums[left]`，说明 mid 左侧的子数组是有序的。
 *    在这种情况下，判断目标值是否在有序的左侧区间 `[left, mid - 1]` 中。如果是，则更新 `right = mid - 1`；否则，
 *    说明目标值在右侧非有序的区间 `[mid + 1, right]`，更新 `left = mid + 1`。
 *
 *    - **情况3：mid右侧的子数组是有序的：** 如果 `nums[mid] < nums[left]`，说明 mid 右侧的子数组是有序的。
 *    在这种情况下，判断目标值是否在有序的右侧区间 `[mid + 1, right]` 中。如果是，则更新 `left = mid + 1`；否则，
 *    说明目标值在左侧非有序的区间 `[left, mid - 1]`，更新 `right = mid - 1`。
 *
 * 3. **返回结果：** 如果整个循环过程中都未找到目标值，返回 -1。
 *
 * ### 时间复杂度：
 *
 * - 由于每一步都是二分查找，每次循环都会将搜索范围缩小一半。因此，时间复杂度为 O(logN)。
 *
 * ### 空间复杂度：
 *
 * - 空间复杂度为 O(1)，因为只使用了常数级别的变量。
 */

public class LeetCode_33_SearchInRotatedSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 在旋转排序数组中搜索目标值
         *
         * @param nums   旋转排序数组
         * @param target 目标值
         * @return 目标值的索引，如果不存在返回-1
         */
        public int search(int[] nums, int target) {
            int n = nums.length;
            int left = 0, right = n - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                // 情况1：找到目标值
                if (nums[mid] == target) {
                    return mid;
                }

                // 情况2：mid左侧的子数组是有序的
                else if (nums[mid] >= nums[left]) {
                    if (target >= nums[left] && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }

                // 情况3：mid右侧的子数组是有序的
                else {
                    if (target <= nums[right] && target > nums[mid]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }

            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_33_SearchInRotatedSortedArray().new Solution();

        // 测试代码
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = solution.search(nums, target);

        // 预期输出: 4
        System.out.println(result);
    }
}

/**
There is an integer array nums sorted in ascending order (with distinct values).
 

 Prior to being passed to your function, nums is possibly rotated at an unknown 
pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], 
nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For 
example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1
,2]. 

 Given the array nums after the possible rotation and an integer target, return 
the index of target if it is in nums, or -1 if it is not in nums. 

 You must write an algorithm with O(log n) runtime complexity. 

 
 Example 1: 
 Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
 
 Example 2: 
 Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 
 Example 3: 
 Input: nums = [1], target = 0
Output: -1
 
 
 Constraints: 

 
 1 <= nums.length <= 5000 
 -10⁴ <= nums[i] <= 10⁴ 
 All values of nums are unique. 
 nums is an ascending array that is possibly rotated. 
 -10⁴ <= target <= 10⁴ 
 

 Related Topics Array Binary Search 👍 25006 👎 1466

*/

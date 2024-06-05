package leetcode.question.binary_search;

/**
 *@Question:  81. Search in Rotated Sorted Array II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 27.24%
 *@Time  Complexity: : O(N) worst case, O(logN) best case, where NNN is the length of the input array.
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 题目要求在旋转过的有序数组中搜索目标元素是否存在。由于数组是有序的，我们可以使用修改后的二分搜索算法来解决这个问题。
 *
 * #### 步骤详解
 *
 * 1. **初始化指针**：
 *    - 初始化起始指针 `start` 为数组的起始索引，结束指针 `end` 为数组的结束索引。
 *
 * 2. **二分搜索**：
 *    - 在每次迭代中，我们计算中间索引 `mid`，并检查中间元素是否等于目标元素。
 *    - 如果找到目标元素，则返回 `true`。
 *    - 如果没有找到目标元素，我们检查是否可以在当前二分搜索空间内进行二分搜索。
 *    - 如果不能，我们递增起始指针 `start`，继续迭代。
 *    - 如果可以，在判断目标元素和中间元素属于哪个有序数组后，更新搜索空间的起始指针和结束指针。
 *
 * 3. **结束条件**：
 *    - 当起始指针大于结束指针时，说明搜索空间为空，返回 `false`。
 *
 * #### 时间和空间复杂度分析
 *
 * #### 时间复杂度
 * - 在最坏情况下，即数组中的所有元素都相同且等于目标元素，时间复杂度为 `O(n)`，其中 `n` 是数组的长度。
 * - 在最佳情况下，即数组中的所有元素都不同且等于目标元素，时间复杂度为 `O(log n)`，其中 `n` 是数组的长度。
 *
 * #### 空间复杂度
 * - 空间复杂度为 `O(1)`，没有使用额外的数据结构，只使用了常数级的额外空间。
 */
public class LeetCode_81_SearchInRotatedSortedArrayIi{

//leetcode submit region begin(Prohibit modification and deletion)

    // 定义内部类Solution
    class Solution {
        // 定义search方法，用于搜索目标元素在旋转有序数组中是否存在
        public boolean search(int[] nums, int target) {
            int n = nums.length; // 获取数组长度
            if (n == 0) return false; // 如果数组为空，返回false
            int end = n - 1; // 初始化数组的结束索引
            int start = 0; // 初始化数组的起始索引

            // 使用二分搜索找到目标元素
            while (start <= end) { // 当起始索引小于等于结束索引时执行循环
                int mid = start + (end - start) / 2; // 计算中间元素的索引

                // 如果中间元素等于目标元素，返回true
                if (nums[mid] == target) {
                    return true;
                }

                // 判断是否可以使用二分搜索减少搜索空间
                if (!isBinarySearchHelpful(nums, start, nums[mid])) {
                    start++; // 如果不能，增加起始索引，继续循环
                    continue;
                }
                // 确定中间元素属于哪个数组
                boolean pivotArray = existsInFirst(nums, start, nums[mid]);

                // 确定目标元素属于哪个数组
                boolean targetArray = existsInFirst(nums, start, target);
                // 如果中间元素和目标元素属于不同的数组
                if (pivotArray ^ targetArray) { // 如果中间元素和目标元素属于不同的数组
                    if (pivotArray) {
                        start = mid + 1; // 如果中间元素属于第一个数组，调整起始索引为mid+1
                    } else {
                        end = mid - 1; // 如果中间元素属于第二个数组，调整结束索引为mid-1
                    }
                } else { // 如果中间元素和目标元素属于相同的数组
                    if (nums[mid] < target) {
                        start = mid + 1; // 如果中间元素小于目标元素，调整起始索引为mid+1
                    } else {
                        end = mid - 1; // 如果中间元素大于等于目标元素，调整结束索引为mid-1
                    }
                }
            }
            return false; // 如果未找到目标元素，返回false
        }

        // 判断是否可以使用二分搜索减少搜索空间
        private boolean isBinarySearchHelpful(int[] arr, int start, int element) {
            return arr[start] != element;
        }

        // 确定元素是否属于第一个数组
        private boolean existsInFirst(int[] arr, int start, int element) {
            return arr[start] <= element;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_81_SearchInRotatedSortedArrayIi().new Solution();

        // 测试用例1: nums = [2,5,6,0,0,1,2], target = 0
        int[] nums1 = {2,5,6,0,0,1,2};
        int target1 = 0;
        System.out.println("Test Case 1: " + solution.search(nums1, target1)); // 应返回true

        // 测试用例2: nums = [2,5,6,0,0,1,2], target = 3
        int[] nums2 = {2,5,6,0,0,1,2};
        int target2 = 3;
        System.out.println("Test Case 2: " + solution.search(nums2, target2)); // 应返回false
    }
}

/**
There is an integer array nums sorted in non-decreasing order (not necessarily 
with distinct values). 

 Before being passed to your function, nums is rotated at an unknown pivot 
index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], 
..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1
,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,
4,4]. 

 Given the array nums after the rotation and an integer target, return true if 
target is in nums, or false if it is not in nums. 

 You must decrease the overall operation steps as much as possible. 

 
 Example 1: 
 Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
 
 Example 2: 
 Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
 
 
 Constraints: 

 
 1 <= nums.length <= 5000 
 -10⁴ <= nums[i] <= 10⁴ 
 nums is guaranteed to be rotated at some pivot. 
 -10⁴ <= target <= 10⁴ 
 

 
 Follow up: This problem is similar to Search in Rotated Sorted Array, but nums 
may contain duplicates. Would this affect the runtime complexity? How and why? 

 Related Topics Array Binary Search 👍 8376 👎 1035

*/
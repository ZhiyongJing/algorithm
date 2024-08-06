package leetcode.question.binary_search;
/**
 *@Question:  153. Find Minimum in Rotated Sorted Array
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.330000000000005%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目与解题思路
 *
 * **题目**: 153. 寻找旋转排序数组中的最小值
 *
 * 给定一个已旋转的升序排列数组，其中元素都是唯一的。需要找出数组中的最小元素。例如，数组 `[3, 4, 5, 1, 2]` 是数组 `[1, 2, 3, 4, 5]` 旋转后的结果。数组没有重复元素。
 *
 * **解题思路**:
 *
 * 1. **特殊情况判断**: 如果数组的最后一个元素大于第一个元素，说明数组未被旋转，最小值就是第一个元素。
 *
 * 2. **二分查找**:
 *    - 初始化左右指针 `left` 和 `right`，分别指向数组的开头和末尾。
 *    - 计算中间元素 `mid`，并根据以下情况进行处理：
 *      - 如果中间元素大于其下一个元素，则下一个元素即为最小值（旋转点）。
 *      - 如果中间元素小于其前一个元素，则中间元素即为最小值。
 *      - 否则，判断中间元素与数组第一个元素的大小关系：
 *        - 如果 `nums[mid]` 大于 `nums[0]`，说明最小值在右半部分，更新 `left` 为 `mid + 1`。
 *        - 否则，说明最小值在左半部分，更新 `right` 为 `mid - 1`。
 *    - 继续上述过程，直到找到最小值。
 *
 * ### 时间复杂度与空间复杂度
 *
 * - **时间复杂度**: O(log N)
 *   - 由于使用了二分查找算法，每次查找的范围都会缩小一半，所以时间复杂度是 O(log N)，其中 N 是数组的长度。
 *
 * - **空间复杂度**: O(1)
 *   - 只使用了固定数量的额外变量（如 `left`、`right`、`mid` 等），不依赖于输入数组的大小。
 */
public class LeetCode_153_FindMinimumInRotatedSortedArray{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMin(int[] nums) {
            // 如果数组中只有一个元素，则返回该元素。
            if (nums.length == 1) {
                return nums[0];
            }

            // 初始化左右指针。
            int left = 0, right = nums.length - 1;

            // 如果数组是升序排列，没有旋转的情况，直接返回第一个元素。
            if (nums[right] > nums[0]) {
                return nums[0];
            }

            // 使用二分查找法来寻找旋转点，即最小值
            while (right >= left) {
                // 计算中间元素的索引
                int mid = left + (right - left) / 2;

                // 如果中间元素大于其下一个元素，则说明下一个元素是最小值
                if (nums[mid] > nums[mid + 1]) {
                    return nums[mid + 1];
                }

                // 如果中间元素小于其前一个元素，则中间元素是最小值
                if (nums[mid - 1] > nums[mid]) {
                    return nums[mid];
                }

                // 如果中间元素大于数组的第一个元素，说明最小值在右半部分
                if (nums[mid] > nums[0]) {
                    left = mid + 1;
                } else {
                    // 否则，最小值在左半部分
                    right = mid - 1;
                }
            }

            // 如果未找到，则返回一个较大的数，理论上不会达到这里
            return Integer.MAX_VALUE;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_153_FindMinimumInRotatedSortedArray().new Solution();

        // 测试样例
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println(solution.findMin(nums1)); // 输出: 1

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.findMin(nums2)); // 输出: 0

        int[] nums3 = {11, 13, 15, 17};
        System.out.println(solution.findMin(nums3)); // 输出: 11
    }
}

/**
Suppose an array of length n sorted in ascending order is rotated between 1 and 
n times. For example, the array nums = [0,1,2,4,5,6,7] might become: 

 
 [4,5,6,7,0,1,2] if it was rotated 4 times. 
 [0,1,2,4,5,6,7] if it was rotated 7 times. 
 

 Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results 
in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]]. 

 Given the sorted rotated array nums of unique elements, return the minimum 
element of this array. 

 You must write an algorithm that runs in O(log n) time. 

 
 Example 1: 

 
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 

 Example 2: 

 
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 

 Example 3: 

 
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
 

 
 Constraints: 

 
 n == nums.length 
 1 <= n <= 5000 
 -5000 <= nums[i] <= 5000 
 All the integers of nums are unique. 
 nums is sorted and rotated between 1 and n times. 
 

 Related Topics Array Binary Search 👍 13265 👎 587

*/
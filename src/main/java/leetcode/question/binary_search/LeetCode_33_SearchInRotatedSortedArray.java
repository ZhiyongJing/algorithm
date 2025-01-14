package leetcode.question.binary_search;

/**
  *@Question:  33. Search in Rotated Sorted Array     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 81.44%      
  *@Time  Complexity: O(logN)
  *@Space Complexity: O(1)
 */
/**
 * ==============================
 * 题目描述：LeetCode 33 - Search in Rotated Sorted Array
 * ==============================
 * 给定一个经过旋转的排序数组，在数组中搜索一个目标值，并返回其索引位置。
 * 如果目标值不存在于数组中，返回 -1。
 *
 * **输入数组特性：**
 * - 原数组是按照升序排序的，但在某个未知的点被旋转，例如：
 *   原数组：[0, 1, 2, 4, 5, 6, 7]
 *   旋转后：[4, 5, 6, 7, 0, 1, 2]
 * - 你必须设计一个时间复杂度为 O(log N) 的算法解决此问题。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * **核心思路：二分查找 (Binary Search)**
 * 由于数组被旋转过，我们不能直接使用普通的二分查找。
 * 因此，我们需要通过二分查找时的中点 `mid` 将数组划分为两部分：
 * - 一部分是**有序的**，另一部分是**可能包含旋转点的无序部分**。
 *
 * 我们通过判断哪一部分是有序的，并在有序部分内判断目标值是否可能存在，从而决定如何收缩搜索区间。
 *
 * ------------------------------
 * **步骤1：定义左右边界 left 和 right，使用二分查找**
 * - 初始时：left = 0, right = n - 1
 * - 每次计算中点 mid = left + (right - left) / 2
 *
 * **步骤2：判断中点是否为目标值**
 * - 如果 nums[mid] == target，直接返回 mid。
 *
 * **步骤3：判断哪一部分是有序的**
 * - 如果 nums[mid] >= nums[left]，说明左侧部分是有序的。
 * - 否则，右侧部分是有序的。
 *
 * **步骤4：在有序部分内判断目标值是否可能存在**
 * - 如果目标值在有序部分的范围内，调整左右边界以缩小搜索区间。
 * - 否则，继续在无序部分查找。
 *
 * ------------------------------
 * **举例解释：**
 *
 * **示例1：**
 * 输入：nums = [4, 5, 6, 7, 0, 1, 2], target = 0
 *
 * - 初始：left = 0, right = 6
 * - 第一次循环：
 *   mid = 3，nums[mid] = 7
 *   nums[mid] >= nums[left]，左侧 [4, 5, 6, 7] 有序。
 *   目标值 0 不在 [4, 5, 6, 7] 之间，因此继续在右侧查找。
 *   调整边界：left = 4, right = 6
 *
 * - 第二次循环：
 *   mid = 5，nums[mid] = 1
 *   nums[mid] >= nums[left]，左侧 [0, 1] 有序。
 *   目标值 0 在 [0, 1] 之间，继续在左侧查找。
 *   调整边界：left = 4, right = 4
 *
 * - 第三次循环：
 *   mid = 4，nums[mid] = 0
 *   找到目标值，返回 4。
 *
 * 输出：4
 *
 * ------------------------------
 * **示例2：**
 * 输入：nums = [4, 5, 6, 7, 0, 1, 2], target = 3
 *
 * - 初始：left = 0, right = 6
 * - 第一次循环：
 *   mid = 3，nums[mid] = 7
 *   左侧 [4, 5, 6, 7] 有序，目标值 3 不在其中，继续在右侧查找。
 *   调整边界：left = 4, right = 6
 *
 * - 第二次循环：
 *   mid = 5，nums[mid] = 1
 *   左侧 [0, 1] 有序，目标值 3 不在其中，继续在右侧查找。
 *   调整边界：left = 6, right = 6
 *
 * - 第三次循环：
 *   mid = 6，nums[mid] = 2
 *   目标值 3 不等于 2，且没有更多元素可查找，返回 -1。
 *
 * 输出：-1
 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：O(log N)**
 * - 二分查找的时间复杂度是 O(log N)，每次将搜索区间缩小一半。
 *
 * **空间复杂度：O(1)**
 * - 只使用了常数空间来存储边界指针和中间变量，没有使用额外的数据结构。
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

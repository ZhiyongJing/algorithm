package leetcode.question.binary_search;

/**
 *@Question:  1060. Missing Element in Sorted Array
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 27.77%
 *@Time  Complexity: O(logN)
 *@Space Complexity: O(1)
 */

/**
 * 这道题目的算法思路基于二分查找。让我们来详细讲解一下：
 *
 * **问题描述：**
 *
 * 给定一个升序排列的整数数组 `nums`，数组中可能有一些数字缺失。我们需要找到排序数组中第 `k` 个缺失的数字。
 *
 * **算法思路：**
 *
 * 1. **初始化指针：** 使用二分查找，我们首先初始化左右两个指针 `left` 和 `right`。`left` 指向数组的起始位置，`right` 指向数组的末尾。
 *
 * 2. **二分查找：** 在每一次迭代中，我们计算中间位置 `mid`，并检查中间位置缺失的元素数量。
 *
 *    - 如果 `nums[mid] - nums[0] - mid < k`，表示在 `mid` 右侧缺失的元素数量小于 `k`，
 *    说明第 `k` 个缺失的元素在 `mid` 的右侧。因此，我们将 `left` 更新为 `mid`。
 *
 *    - 如果 `nums[mid] - nums[0] - mid >= k`，表示在 `mid` 右侧缺失的元素数量大于等于 `k`，
 *    说明第 `k` 个缺失的元素在 `mid` 的左侧。因此，我们将 `right` 更新为 `mid - 1`。
 *
 * 3. **返回结果：** 当 `left` 和 `right` 相遇时，表示我们找到了目标位置，即排序数组中第 `k` 个缺失的元素。
 * 返回结果为 `nums[0] + k + left`。
 *
 * **时间复杂度：**
 *
 * 由于算法采用二分查找，每次迭代都将搜索空间减半，因此时间复杂度为 O(logN)，其中 N 是数组的长度。
 *
 * **空间复杂度：**
 *
 * 算法的空间复杂度为 O(1)，因为只使用了常数级别的额外空间。
 */

public class LeetCode_1060_MissingElementInSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int missingElement(int[] nums, int k) {
            int n = nums.length;
            int left = 0, right = n - 1;

            // 使用二分查找rightmost index such that [nums[0], nums[left]] has fewer missing elements than k
            // (4)，5，6，(7)，8，(9)，(10)
            //so the number of missing elements:(nums[i] - nums[0] + 1) - (i + 1) = nums[i] - nums[0] - i
            while (left < right) {
                int mid = right - (right - left) / 2;

                // 计算中间位置缺失的元素数量
                if (nums[mid] - nums[0] - mid < k) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            System.out.println(left);

            // 返回结果
            return nums[0] + k + left;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1060_MissingElementInSortedArray().new Solution();

        // 测试代码
        int[] testArray = {4, 7, 9, 10};
        int k = 3;
        int result = solution.missingElement(testArray, k);

        System.out.println("在排序数组中缺失第 " + k + " 个元素的结果是：" + result);
    }
}

/**
Given an integer array nums which is sorted in ascending order and all of its 
elements are unique and given also an integer k, return the kᵗʰ missing number 
starting from the leftmost number of the array. 

 
 Example 1: 

 
Input: nums = [4,7,9,10], k = 1
Output: 5
Explanation: The first missing number is 5.
 

 Example 2: 

 
Input: nums = [4,7,9,10], k = 3
Output: 8
Explanation: The missing numbers are [5,6,8,...], hence the third missing 
number is 8.
 

 Example 3: 

 
Input: nums = [1,2,4], k = 3
Output: 6
Explanation: The missing numbers are [3,5,6,7,...], hence the third missing 
number is 6.
 

 
 Constraints: 

 
 1 <= nums.length <= 5 * 10⁴ 
 1 <= nums[i] <= 10⁷ 
 nums is sorted in ascending order, and all the elements are unique. 
 1 <= k <= 10⁸ 
 

 
Follow up: Can you find a logarithmic time complexity (i.e., 
O(log(n))) solution?

 Related Topics Array Binary Search 👍 1609 👎 57

*/

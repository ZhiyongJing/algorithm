package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 *@Question:  259. 3Sum Smaller
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 32.68%
 *@Time  Complexity: O(N^2)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目描述
 *
 * **题目编号：259. 3Sum Smaller**
 *
 * **问题描述：**
 *
 * 给定一个整数数组 `nums` 和一个整数 `target`，你的任务是计算数组中所有三元组的和小于 `target` 的组合数。三元组的定义为 `(nums[i], nums[j], nums[k])`，其中 `i < j < k`。
 *
 * **示例：**
 *
 * - **输入：** `nums = [-2, 0, 1, 3]`，`target = 2`
 * - **输出：** `2`
 * - **解释：** 数组中满足条件的三元组是 `(-2, 0, 1)` 和 `(-2, 0, 3)`。
 *
 * ### 解题思路
 *
 * 1. **排序：**
 *
 *    首先，将数组 `nums` 进行排序。这是为了方便后续使用双指针技术，这样可以减少时间复杂度并使问题变得更容易处理。
 *
 * 2. **固定第一个元素：**
 *
 *    对于排序后的数组，固定第一个元素 `nums[i]`，然后使用双指针技术来处理剩余部分。固定一个元素后，我们的问题就变成了在剩余的数组中寻找两个数，使得它们的和加上固定的元素小于目标值 `target`。
 *
 * 3. **双指针技术：**
 *
 *    - **初始化指针：** 使用两个指针 `left` 和 `right`，`left` 从固定元素的下一个位置开始，`right` 从数组的末尾开始。
 *    - **移动指针：** 计算 `nums[left] + nums[right]` 的和：
 *      - 如果和小于 `target`，这说明 `left` 到 `right` 之间所有的数与 `left` 指向的数的和都小于 `target`。因此，将符合条件的三元组的数量加上 `right - left`，并将 `left` 右移以尝试新的组合。
 *      - 如果和大于或等于 `target`，则将 `right` 左移以减少和的值，直到找到一个合适的和或者 `left` 大于或等于 `right`。
 *
 * 4. **累加结果：**
 *
 *    对于每一个固定的元素，使用双指针计算所有可能的两数之和，并将结果累加到总数中，最终返回这个总数。
 *
 * ### 时间复杂度
 *
 * - **排序时间复杂度：** `O(N log N)`，其中 `N` 是数组的长度。
 * - **双指针遍历时间复杂度：** 对于每个固定的元素，双指针的操作时间复杂度为 `O(N)`。因为每个元素最多被访问 `N` 次，所以总体时间复杂度为 `O(N^2)`。
 *
 * 综上，算法的总时间复杂度为 `O(N^2)`。
 *
 * ### 空间复杂度
 *
 * - **排序使用的额外空间：** `O(1)`，排序是就地进行的。
 * - **其他变量：** 除了输入数据和返回值外，算法只使用了常量空间来存储索引和指针。因此空间复杂度为 `O(1)`。
 *
 * 综上所述，算法的空间复杂度为 `O(1)`。
 */

public class LeetCode_259_ThreeSumSmaller {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 计算数组中所有三元组的和小于 target 的组合数
        public int threeSumSmaller(int[] nums, int target) {
            // 对数组进行排序，以便使用双指针技巧
            Arrays.sort(nums);
            int sum = 0;  // 记录满足条件的三元组的数量
            // 遍历每个元素，固定第一个元素
            for (int i = 0; i < nums.length - 2; i++) {
                // 调用 twoSumSmaller 方法，计算剩余部分的有效三元组数量
                sum += twoSumSmaller(nums, i + 1, target - nums[i]);
            }
            return sum;  // 返回符合条件的三元组总数
        }

        // 计算数组中从 startIndex 开始的两数之和小于 target 的组合数
        private int twoSumSmaller(int[] nums, int startIndex, int target) {
            int sum = 0;  // 记录符合条件的二元组的数量
            int left = startIndex;  // 左指针初始化为 startIndex
            int right = nums.length - 1;  // 右指针初始化为数组末尾
            // 双指针遍历
            while (left < right) {
                // 如果当前左指针和右指针指向的元素之和小于 target
                if (nums[left] + nums[right] < target) {
                    // 所有从左指针到右指针的元素与左指针指向的元素的和都小于 target
                    sum += right - left;
                    left++;  // 左指针右移
                } else {
                    right--;  // 右指针左移
                }
            }
            return sum;  // 返回符合条件的二元组数量
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_259_ThreeSumSmaller().new Solution();

        // 测试样例 1
        int[] nums1 = {-2, 0, 1, 3};
        int target1 = 2;
        System.out.println("测试样例 1：");
        // 计算结果应该是 2，满足条件的三元组为 (-2, 0, 1) 和 (-2, 0, 3)
        System.out.println("三元组数量小于 " + target1 + " 的数量: " + solution.threeSumSmaller(nums1, target1));

        // 测试样例 2
        int[] nums2 = {1, 2, 3, 4, 5};
        int target2 = 8;
        System.out.println("测试样例 2：");
        // 计算结果应该是 7，满足条件的三元组包括 (1, 2, 3), (1, 2, 4), (1, 2, 5), (1, 3, 4), (1, 3, 5), (1, 4, 5), (2, 3, 4)
        System.out.println("三元组数量小于 " + target2 + " 的数量: " + solution.threeSumSmaller(nums2, target2));
    }
}

/**
Given an array of n integers nums and an integer target, find the number of 
index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + 
nums[j] + nums[k] < target. 
 
 Example 1: 

 
Input: nums = [-2,0,1,3], target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
[-2,0,1]
[-2,0,3]
 

 Example 2: 

 
Input: nums = [], target = 0
Output: 0
 

 Example 3: 

 
Input: nums = [0], target = 0
Output: 0
 

 
 Constraints: 

 
 n == nums.length 
 0 <= n <= 3500 
 -100 <= nums[i] <= 100 
 -100 <= target <= 100 
 

 Related Topics Array Two Pointers Binary Search Sorting 👍 1556 👎 159

*/
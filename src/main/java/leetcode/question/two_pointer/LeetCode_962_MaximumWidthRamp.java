package leetcode.question.two_pointer;

/**
 *@Question:  962. Maximum Width Ramp
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 90.54%
 *@Time Complexity: O(N)
 *@Space Complexity: O(N)
 */
/*
 * 第一步：题目描述
 * 给定一个整数数组 `nums`，你需要找出一对索引 `(i, j)`，其中 `i < j` 且 `nums[i] <= nums[j]`。目标是找到最大的宽度 `j - i`。
 * 也就是说，我们要找到一对 `(i, j)`，使得 `nums[i] <= nums[j]` 并且 `j - i` 最大。
 * 返回最大宽度，即最大差值 `j - i`。
 *
 * 例如：
 * 输入：`nums = [6, 0, 8, 2, 1, 5]`
 * 输出：`4`
 * 解释：最优解是 `(i = 0, j = 4)`，因为 `nums[0] = 6` 和 `nums[4] = 1` 满足 `nums[0] <= nums[4]` 且 `j - i = 4`。
 */

/*
 * 第二步：解题思路
 * 1. 构建一个辅助数组 `rightMax`，用于存储每个位置从右侧开始的最大值。
 *    - 从右侧向左遍历 `nums` 数组来填充 `rightMax` 数组。
 *    - 例如，对于 `nums = [6, 0, 8, 2, 1, 5]`，我们会填充 `rightMax = [8, 8, 8, 5, 5, 5]`，其中 `rightMax[i]` 保存从索引 `i` 到数组末尾的最大值。
 *
 * 2. 使用两个指针 `left` 和 `right`，分别从左侧和右侧遍历数组，检查 `nums[left] <= rightMax[right]` 是否成立。
 *    - 初始时，`left` 和 `right` 都指向数组的第一个元素。
 *    - 如果 `nums[left] <= rightMax[right]`，则计算并更新最大宽度 `maxWidth = Math.max(maxWidth, right - left)`，然后右指针 `right++`。
 *    - 如果 `nums[left] > rightMax[right]`，则移动左指针 `left++`。
 *
 * 3. 通过以上过程，最终得到最大宽度 `maxWidth`。
 *
 * 举例：
 * 假设 `nums = [6, 0, 8, 2, 1, 5]`，我们首先构建 `rightMax = [8, 8, 8, 5, 5, 5]`。然后通过双指针遍历：
 * - 初始 `left = 0, right = 0`，`nums[left] = 6, rightMax[right] = 8`，`nums[left] <= rightMax[right]`，更新 `maxWidth = 0`。
 * - 继续遍历，直到找到最大宽度。
 */

/*
 * 第三步：时间和空间复杂度
 * 时间复杂度：
 *  - 构建 `rightMax` 数组的时间复杂度为 O(N)，因为我们从右侧开始遍历一次。
 *  - 双指针遍历数组的时间复杂度为 O(N)，因为每个指针最多遍历数组一次。
 *  - 因此，总时间复杂度为 O(N)。
 *
 * 空间复杂度：
 *  - 我们使用了一个额外的数组 `rightMax` 来存储每个位置的最大值，其空间复杂度为 O(N)。
 *  - 因此，总空间复杂度为 O(N)。
 */


public class LeetCode_962_MaximumWidthRamp{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int maxWidthRamp(int[] nums) {
            int n = nums.length;
            // 初始化一个数组来存储从右侧开始的每个位置的最大值
            int[] rightMax = new int[n];

            // 从右侧开始填充rightMax数组的值
            rightMax[n - 1] = nums[n - 1]; // 最后一个位置的最大值就是其本身
            for (int i = n - 2; i >= 0; i--) {
                // 计算当前索引的最大值与其右侧的最大值之间的较大者
                rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
            }

            // 初始化两个指针left和right
            int left = 0, right = 0;
            int maxWidth = 0; // 用来记录最大宽度

            // 使用双指针来遍历数组
            while (right < n) {
                // 如果当前左指针位置的值大于右指针位置的最大值，则左指针向右移动
                while (left < right && nums[left] > rightMax[right]) {
                    left++;
                }
                // 更新最大宽度
                maxWidth = Math.max(maxWidth, right - left);
                // 右指针向右移动
                right++;
            }

            return maxWidth; // 返回最大宽度
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_962_MaximumWidthRamp().new Solution();

        // 测试样例1
        int[] nums1 = {6, 0, 8, 2, 1, 5};
        System.out.println(solution.maxWidthRamp(nums1));  // 输出：4

        // 测试样例2
        int[] nums2 = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
        System.out.println(solution.maxWidthRamp(nums2));  // 输出：7
    }
}

/**
A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= 
nums[j]. The width of such a ramp is j - i. 

 Given an integer array nums, return the maximum width of a ramp in nums. If 
there is no ramp in nums, return 0. 

 
 Example 1: 

 
Input: nums = [6,0,8,2,1,5]
Output: 4
Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 
and nums[5] = 5.
 

 Example 2: 

 
Input: nums = [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 
and nums[9] = 1.
 

 
 Constraints: 

 
 2 <= nums.length <= 5 * 10⁴ 
 0 <= nums[i] <= 5 * 10⁴ 
 

 Related Topics Array Two Pointers Stack Monotonic Stack 👍 2641 👎 88

*/
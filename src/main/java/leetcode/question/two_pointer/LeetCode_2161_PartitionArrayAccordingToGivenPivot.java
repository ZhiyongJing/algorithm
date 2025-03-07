package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 *@Question:  2161. Partition Array According to Given Pivot
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 35.54%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * 题目描述：
 *
 * 给定一个整数数组 `nums` 和一个整数 `pivot`，请你重新排列数组，使得：
 * - 所有 **小于 pivot** 的元素出现在数组的左侧
 * - 所有 **等于 pivot** 的元素出现在数组的中间
 * - 所有 **大于 pivot** 的元素出现在数组的右侧
 *
 * 需要保证：
 * - **相对顺序不变**（即相对位置应与原数组中的相对顺序一致）
 * - 返回 **重新排列后的数组**
 *
 * 示例 1：
 * 输入：nums = [9,12,5,10,14,3,10], pivot = 10
 * 输出：[9,5,3,10,10,12,14]
 * 解释：
 * - 小于 pivot 的元素：[9, 5, 3]
 * - 等于 pivot 的元素：[10, 10]
 * - 大于 pivot 的元素：[12, 14]
 *
 * 示例 2：
 * 输入：nums = [-3, 4, 3, 2], pivot = 3
 * 输出：[-3, 2, 3, 4]
 * 解释：
 * - 小于 pivot 的元素：[-3, 2]
 * - 等于 pivot 的元素：[3]
 * - 大于 pivot 的元素：[4]
 *
 * 约束：
 * - 1 <= nums.length <= 10^5
 * - -10^6 <= nums[i], pivot <= 10^6
 */

/**
 * 解题思路：
 *
 * **使用双指针遍历数组并构造新数组**
 * 1. **创建一个新数组 `ans`**：
 *    - 这个数组用于存储最终的结果，其长度等于 `nums` 的长度。
 *
 * 2. **定义两个指针**：
 *    - `lessI = 0`（指向存放小于 pivot 的位置）
 *    - `greaterI = nums.length - 1`（指向存放大于 pivot 的位置）
 *
 * 3. **遍历 `nums` 数组**：
 *    - 使用 **两个方向的索引**：
 *      - `i` 从左向右遍历，用于寻找小于 pivot 的元素并存入 `ans`
 *      - `j` 从右向左遍历，用于寻找大于 pivot 的元素并存入 `ans`
 *
 * 4. **填充 `ans` 数组**：
 *    - 如果 `nums[i] < pivot`，放入 `ans[lessI]`，并递增 `lessI`
 *    - 如果 `nums[j] > pivot`，放入 `ans[greaterI]`，并递减 `greaterI`
 *
 * 5. **填充 pivot**：
 *    - 遍历 `ans` 的中间区域，将 pivot 填充到 `lessI` 到 `greaterI` 之间。
 *
 * 6. **返回 `ans` 作为最终结果**。
 *
 * **举例分析**
 *
 * **示例 1**：
 * 输入：`nums = [9,12,5,10,14,3,10]`, `pivot = 10`
 *
 * **初始化**：
 * - `ans = [_, _, _, _, _, _, _]`
 * - `lessI = 0`
 * - `greaterI = 6`
 *
 * **遍历 nums 并填充 `ans`**
 * - `nums[0] = 9`，小于 10，存入 `ans[0]`，`lessI++`
 * - `nums[1] = 12`，大于 10，存入 `ans[6]`，`greaterI--`
 * - `nums[2] = 5`，小于 10，存入 `ans[1]`，`lessI++`
 * - `nums[3] = 10`，跳过（暂不存入）
 * - `nums[4] = 14`，大于 10，存入 `ans[5]`，`greaterI--`
 * - `nums[5] = 3`，小于 10，存入 `ans[2]`，`lessI++`
 * - `nums[6] = 10`，跳过（暂不存入）
 *
 * **此时 `ans = [9,5,3,_,_,12,14]`**
 *
 * **填充 pivot**
 * - 在 `lessI = 3` 到 `greaterI = 4` 之间填充 `10`
 * - 结果：`ans = [9,5,3,10,10,12,14]`
 *
 * **示例 2**：
 * 输入：`nums = [-3,4,3,2]`, `pivot = 3`
 *
 * **初始化**：
 * - `ans = [_, _, _, _]`
 * - `lessI = 0`
 * - `greaterI = 3`
 *
 * **遍历 nums 并填充 `ans`**
 * - `nums[0] = -3`，小于 3，存入 `ans[0]`
 * - `nums[1] = 4`，大于 3，存入 `ans[3]`
 * - `nums[2] = 3`，跳过
 * - `nums[3] = 2`，小于 3，存入 `ans[1]`
 *
 * **此时 `ans = [-3,2,_,4]`**
 *
 * **填充 pivot**
 * - `ans = [-3,2,3,4]`
 */

/**
 * 时间和空间复杂度：
 *
 * - **时间复杂度**：O(N)
 *   - 每个元素只被遍历一次，执行常数次操作，因此是 O(N)。
 *
 * - **空间复杂度**：O(1)（如果允许修改原数组），O(N)（如果使用额外数组）
 *   - 如果使用额外数组存储结果，则需要 O(N) 额外空间。
 *   - 如果修改原数组，可以使用 **三次遍历** 完成排序（第一遍统计小于 pivot 的数量，第二遍放 pivot，第三遍放大于 pivot 的数）。
 */


public class LeetCode_2161_PartitionArrayAccordingToGivenPivot{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 根据给定的 pivot 对数组进行重新排序：
         * - 小于 pivot 的元素放在左侧
         * - 等于 pivot 的元素放在中间
         * - 大于 pivot 的元素放在右侧
         *
         * @param nums 输入的整数数组
         * @param pivot 参考值
         * @return 重新排列后的数组
         */
        public int[] pivotArray(int[] nums, int pivot) {
            // 创建一个与 nums 相同长度的新数组 ans
            int[] ans = new int[nums.length];

            // 维护两个指针：
            int lessI = 0;               // 用于存储小于 pivot 的元素
            int greaterI = nums.length - 1; // 用于存储大于 pivot 的元素

            // 遍历数组，同时使用 i 从左到右，j 从右到左进行扫描
            for (int i = 0, j = nums.length - 1; i < nums.length; i++, j--) {
                // 将小于 pivot 的元素存入 ans 的左侧
                if (nums[i] < pivot) {
                    ans[lessI] = nums[i];
                    lessI++;
                }
                // 将大于 pivot 的元素存入 ans 的右侧
                if (nums[j] > pivot) {
                    ans[greaterI] = nums[j];
                    greaterI--;
                }
            }

            // 填充中间部分的 pivot 元素
            while (lessI <= greaterI) {
                ans[lessI] = pivot;
                lessI++;
            }

            // 返回重新排列后的数组
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_2161_PartitionArrayAccordingToGivenPivot().new Solution();

        // 测试样例 1
        int[] nums1 = {9, 12, 5, 10, 14, 3, 10};
        int pivot1 = 10;
        System.out.println("测试样例 1 结果：" + Arrays.toString(solution.pivotArray(nums1, pivot1)));
        // 预期输出：[9, 5, 3, 10, 10, 12, 14] （小于 pivot 的在左侧，等于 pivot 的在中间，大于 pivot 的在右侧）

        // 测试样例 2
        int[] nums2 = {-3, 4, 3, 2};
        int pivot2 = 3;
        System.out.println("测试样例 2 结果：" + Arrays.toString(solution.pivotArray(nums2, pivot2)));
        // 预期输出：[-3, 2, 3, 4] （小于 pivot 的在左侧，等于 pivot 的在中间，大于 pivot 的在右侧）

        // 测试样例 3
        int[] nums3 = {1, 2, 3, 4, 5};
        int pivot3 = 3;
        System.out.println("测试样例 3 结果：" + Arrays.toString(solution.pivotArray(nums3, pivot3)));
        // 预期输出：[1, 2, 3, 4, 5] （已经符合要求，无需改变顺序）
    }
}

/**
You are given a 0-indexed integer array nums and an integer pivot. Rearrange 
nums such that the following conditions are satisfied: 

 
 Every element less than pivot appears before every element greater than pivot. 

 Every element equal to pivot appears in between the elements less than and 
greater than pivot. 
 The relative order of the elements less than pivot and the elements greater 
than pivot is maintained. 
 
 More formally, consider every pi, pj where pi is the new position of the iᵗʰ 
element and pj is the new position of the jᵗʰ element. If i < j and both elements 
are smaller (or larger) than pivot, then pi < pj. 
 
 

 Return nums after the rearrangement. 

 
 Example 1: 

 
Input: nums = [9,12,5,10,14,3,10], pivot = 10
Output: [9,5,3,10,10,12,14]
Explanation: 
The elements 9, 5, and 3 are less than the pivot so they are on the left side 
of the array.
The elements 12 and 14 are greater than the pivot so they are on the right side 
of the array.
The relative ordering of the elements less than and greater than pivot is also 
maintained. [9, 5, 3] and [12, 14] are the respective orderings.
 

 Example 2: 

 
Input: nums = [-3,4,3,2], pivot = 2
Output: [-3,2,4,3]
Explanation: 
The element -3 is less than the pivot so it is on the left side of the array.
The elements 4 and 3 are greater than the pivot so they are on the right side 
of the array.
The relative ordering of the elements less than and greater than pivot is also 
maintained. [-3] and [4, 3] are the respective orderings.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 -10⁶ <= nums[i] <= 10⁶ 
 pivot equals to an element of nums. 
 

 Related Topics Array Two Pointers Simulation 👍 1623 👎 113

*/
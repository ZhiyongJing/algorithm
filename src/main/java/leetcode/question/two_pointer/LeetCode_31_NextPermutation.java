package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 *@Question:  31. Next Permutation
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 82.29%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * **题目：31. Next Permutation**
 *
 * **问题描述：**
 * 给定一个整数数组 `nums`，要求找到 `nums` 的下一个排列（字典序的下一个排列）。如果 `nums` 已经是最大的排列，则返回最小的排列，即升序排列。
 *
 * **解题思路：**
 *
 * ### 解题步骤：
 *
 * 1. **寻找递增对**：
 *    - 从数组的倒数第二个元素开始，向前查找第一个元素 `nums[i]`，使得 `nums[i] < nums[i + 1]`。
 *    这个元素 `nums[i]` 表示需要被替换的元素位置，因为在当前位置之后的排列是从当前元素开始的最小排列。
 *
 * 2. **找到下一个更大的元素**：
 *    - 如果找到了这样的 `i`，从数组的末尾开始查找，找到第一个比 `nums[i]` 大的元素 `nums[j]`。
 *    交换 `nums[i]` 和 `nums[j]`，这将把 `nums[i]` 的位置替换为比它大的最小元素，形成新的排列。
 *
 * 3. **反转后续部分**：
 *    - 在交换完成后，数组中 `i` 之后的部分（即 `nums[i + 1]` 到末尾）是降序的。为了得到下一个排列，
 *    需要将这一部分反转，以形成升序排列，从而保证返回的排列是字典序的下一个排列。
 *
 * 4. **处理边界情况**：
 *    - 如果数组已经是最大的排列（即没有找到递增对），则将整个数组反转，得到最小的排列。
 *
 * ### 时间复杂度
 *
 * - **O(N)**：其中 `N` 是数组的长度。找到递增对和进行交换、反转操作均为 O(N) 时间复杂度的操作。
 *
 * ### 空间复杂度
 *
 * - **O(1)**：算法只使用了常量级的额外空间来进行交换和反转操作，因此空间复杂度为 O(1)。
 *
 * ### 总结
 *
 * 通过上述步骤，我们可以有效地找到下一个字典序排列或返回最小排列，操作时间和空间复杂度均为优化的标准。
 */
public class LeetCode_31_NextPermutation{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2; // 从倒数第二个元素开始
            // 寻找第一个从右向左的递增对
            while (i >= 0 && nums[i + 1] <= nums[i]) {
                i--;
            }
            if (i >= 0) { // 如果找到了递增对
                int j = nums.length - 1; // 从最后一个元素开始
                // 寻找第一个大于 nums[i] 的元素
                while (nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j); // 交换 nums[i] 和 nums[j]
            }
            reverse(nums, i + 1); // 反转 i 后面的部分
        }

        private void reverse(int[] nums, int start) {
            int i = start, j = nums.length - 1;
            // 反转从 start 到末尾的部分
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_31_NextPermutation().new Solution();

        // 测试样例
        int[] test1 = {1, 2, 3}; // 顺序排列
        solution.nextPermutation(test1);
        System.out.println("输入: [1, 2, 3]，下一个排列: " + Arrays.toString(test1)); // 输出: [1, 3, 2]

        int[] test2 = {3, 2, 1}; // 递减排列
        solution.nextPermutation(test2);
        System.out.println("输入: [3, 2, 1]，下一个排列: " + Arrays.toString(test2)); // 输出: [1, 2, 3]

        int[] test3 = {1, 1, 5}; // 有重复元素
        solution.nextPermutation(test3);
        System.out.println("输入: [1, 1, 5]，下一个排列: " + Arrays.toString(test3)); // 输出: [1, 5, 1]

        int[] test4 = {1, 2, 7, 5, 4, 3, 2}; // 混合排列
        solution.nextPermutation(test4);
        System.out.println("输入: [1, 2, 7, 5, 4, 3, 2]，下一个排列: " + Arrays.toString(test4)); // 输出: [1, 2, 7, 5, 4, 3, 2] -> [1, 2, 7, 5, 4, 3, 2]
    }
}

/**
A permutation of an array of integers is an arrangement of its members into a 
sequence or linear order. 

 
 For example, for arr = [1,2,3], the following are all the permutations of arr: 
[1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]. 
 

 The next permutation of an array of integers is the next lexicographically 
greater permutation of its integer. More formally, if all the permutations of the 
array are sorted in one container according to their lexicographical order, then 
the next permutation of that array is the permutation that follows it in the 
sorted container. If such arrangement is not possible, the array must be rearranged 
as the lowest possible order (i.e., sorted in ascending order). 

 
 For example, the next permutation of arr = [1,2,3] is [1,3,2]. 
 Similarly, the next permutation of arr = [2,3,1] is [3,1,2]. 
 While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does 
not have a lexicographical larger rearrangement. 
 

 Given an array of integers nums, find the next permutation of nums. 

 The replacement must be in place and use only constant extra memory. 

 
 Example 1: 

 
Input: nums = [1,2,3]
Output: [1,3,2]
 

 Example 2: 

 
Input: nums = [3,2,1]
Output: [1,2,3]
 

 Example 3: 

 
Input: nums = [1,1,5]
Output: [1,5,1]
 

 
 Constraints: 

 
 1 <= nums.length <= 100 
 0 <= nums[i] <= 100 
 

 Related Topics Array Two Pointers 👍 18460 👎 4702

*/
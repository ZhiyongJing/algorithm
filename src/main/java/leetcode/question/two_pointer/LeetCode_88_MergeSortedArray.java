package leetcode.question.two_pointer;

/**
 *@Question:  88. Merge Sorted Array
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 97.96%
 *@Time  Complexity: O(N+M)
 *@Space Complexity: O(1)
 */

import java.util.Arrays;

/**
 * ### 题目：88. Merge Sorted Array
 *
 * **问题描述：**
 * 给定两个按非递减顺序排列的整数数组 `nums1` 和 `nums2`，以及 `nums1` 的有效长度 `m` 和 `nums2` 的有效长度 `n`。要求将 `nums2` 合并到 `nums1` 中，使得 `nums1` 也是按非递减顺序排列。
 *
 * ### 解题思路：
 *
 * 1. **从数组末尾开始合并**：
 *    - **初始化指针**：设置指针 `p1` 指向 `nums1` 的有效部分的末尾，指针 `p2` 指向 `nums2` 的末尾，指针 `p` 指向 `nums1` 的末尾位置（即整个数组的最后一个位置）。
 *    - **比较并插入**：从数组末尾开始，将 `nums1[p1]` 和 `nums2[p2]` 中较大的元素放到 `nums1[p]` 位置，然后移动相应的指针。继续比较和插入，直到遍历完 `nums2` 的所有元素。
 *
 * 2. **处理剩余元素**：
 *    - 如果 `nums2` 的所有元素都已经插入完毕，但 `nums1` 中还有剩余元素，则这些元素已经在正确的位置，不需要进一步处理。
 *    - 如果 `nums2` 中的元素还没有全部处理完（即 `p2` 没有小于 0），则继续处理 `nums2` 中剩余的元素。由于 `nums1` 和 `nums2` 都是已排序的，剩余的 `nums2` 元素已经是有序的，直接复制到 `nums1` 中。
 *
 * ### 时间复杂度
 *
 * - **O(N + M)**：其中 `N` 是 `nums1` 中有效元素的数量，`M` 是 `nums2` 中的元素数量。合并过程中，每个元素被处理一次，因此时间复杂度是 O(N + M)。
 *
 * ### 空间复杂度
 *
 * - **O(1)**：该算法只使用了常量级别的额外空间来存储指针，因此空间复杂度是 O(1)。合并是原地进行的，没有使用额外的存储空间。
 */
public class LeetCode_88_MergeSortedArray{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            // 设置 p1 指向 nums1 的有效部分的末尾，p2 指向 nums2 的末尾
            int p1 = m - 1;
            int p2 = n - 1;

            // 从 nums1 的末尾开始向前遍历，逐个插入更大的元素
            for (int p = m + n - 1; p >= 0; p--) {
                if (p2 < 0) { // 如果 nums2 中的元素已经全部合并完毕
                    break;
                }
                // 将 nums1[p1] 和 nums2[p2] 中较大的元素放到 nums1[p] 中
                if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                    nums1[p] = nums1[p1--];
                } else {
                    nums1[p] = nums2[p2--];
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_88_MergeSortedArray().new Solution();

        // 测试样例
        int[] nums1 = new int[6]; // nums1 数组的总大小是 6
        int m = 3; // nums1 中有效元素的数量
        int[] nums2 = {2, 5, 6}; // nums2 数组
        int n = 3; // nums2 中元素的数量

        // nums1 初始值为 [1, 2, 3]，需要将 nums2 合并进来
        nums1[0] = 1;
        nums1[1] = 2;
        nums1[2] = 3;

        solution.merge(nums1, m, nums2, n);
        System.out.println("合并后的 nums1: " + Arrays.toString(nums1));
        // 期望输出: 合并后的 nums1: [1, 2, 2, 3, 5, 6]
    }
}

/**
 You are given two integer arrays nums1 and nums2, sorted in non-decreasing
 order, and two integers m and n, representing the number of elements in nums1 and
 nums2 respectively.

 Merge nums1 and nums2 into a single array sorted in non-decreasing order.

 The final sorted array should not be returned by the function, but instead be
 stored inside the array nums1. To accommodate this, nums1 has a length of m + n,
 where the first m elements denote the elements that should be merged, and the
 last n elements are set to 0 and should be ignored. nums2 has a length of n.


 Example 1:


 Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 Output: [1,2,2,3,5,6]
 Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 The result of the merge is [1,2,2,3,5,6] with the underlined elements coming
 from nums1.


 Example 2:


 Input: nums1 = [1], m = 1, nums2 = [], n = 0
 Output: [1]
 Explanation: The arrays we are merging are [1] and [].
 The result of the merge is [1].


 Example 3:


 Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 Output: [1]
 Explanation: The arrays we are merging are [] and [1].
 The result of the merge is [1].
 Note that because m = 0, there are no elements in nums1. The 0 is only there to
 ensure the merge result can fit in nums1.



 Constraints:


 nums1.length == m + n
 nums2.length == n
 0 <= m, n <= 200
 1 <= m + n <= 200
 -10⁹ <= nums1[i], nums2[j] <= 10⁹



 Follow up: Can you come up with an algorithm that runs in O(m + n) time?

 Related Topics Array Two Pointers Sorting 👍 13660 👎 1626

 */
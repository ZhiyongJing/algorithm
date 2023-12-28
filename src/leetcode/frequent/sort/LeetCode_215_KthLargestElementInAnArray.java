package leetcode.frequent.sort;

/**
  *@Question:  215. Kth Largest Element in an Array     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 93.28%      
  *@Time  Complexity: O(N) For quick select, O(NlogK) for min-heap heap is limited to a size of k, operations cost at most O(logk).
  *@Space Complexity: O(N)For quick select, O(K) for min-heap
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * **解题思路：**
 *
 * 这道题目要求在给定的整数数组中找到第 k 大的元素。题解使用了两种方法，一种是基于快速选择（QuickSelect）的方法，
 * 另一种是基于小顶堆（Min-heap）的方法。
 *
 * 1. **快速选择（QuickSelect）：**
 *    - 利用快速排序中的划分思想，选择数组中的一个元素作为主元（pivot）。
 *    - 根据主元将数组分为三个部分：比主元大的元素、与主元相等的元素、比主元小的元素。
 *    - 根据 k 与这三个部分的大小关系，决定在哪个部分继续查找第 k 大的元素。
 *    - 递归执行上述步骤，直到找到第 k 大的元素为止。
 *
 * 2. **小顶堆（Min-heap）：**
 *    - 使用小顶堆来维护数组中的前 k 个最大元素。
 *    - 遍历数组，将元素添加到小顶堆中。
 *    - 当堆的大小超过 k 时，移除堆顶元素，保持堆的大小为 k。
 *    - 最终，堆顶元素即为第 k 大的元素。
 *
 * **时间复杂度：**
 *
 * - **快速选择解法：** 在平均情况下，时间复杂度为 O(N)，其中 N 为数组的长度。最坏情况下为 O(N^2)，
 * 但由于使用了随机选择主元的方法，概率上避免了最坏情况的发生。
 * - **小顶堆解法：** 插入和移除堆顶元素的时间复杂度为 O(logK)，共需进行 N 次操作。因此，总体时间复杂度为 O(NlogK)。
 *
 * **空间复杂度：**
 *
 * - **快速选择解法：** O(N)，主要消耗在递归调用和辅助数组上。
 * - **小顶堆解法：** O(K)，堆的大小为 K，因此空间复杂度为 O(K)。
 */

public class LeetCode_215_KthLargestElementInAnArray {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法1: 快速选择
        public int findKthLargest(int[] nums, int k) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }

            return quickSelect(list, k);
        }

        public int quickSelect(List<Integer> nums, int k) {
            int pivotIndex = new Random().nextInt(nums.size());
            int pivot = nums.get(pivotIndex);

            List<Integer> left = new ArrayList<>();
            List<Integer> mid = new ArrayList<>();
            List<Integer> right = new ArrayList<>();

            for (int num : nums) {
                if (num > pivot) {
                    left.add(num);
                } else if (num < pivot) {
                    right.add(num);
                } else {
                    mid.add(num);
                }
            }

            if (k <= left.size()) {
                return quickSelect(left, k);
            }

            if (left.size() + mid.size() < k) {
                return quickSelect(right, k - left.size() - mid.size());
            }

            return pivot;
        }

        // 解法2: 小顶堆
        public int findKthLargest2(int[] nums, int k) {
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            for (int num : nums) {
                heap.add(num);
                if (heap.size() > k) {
                    heap.remove();
                }
            }

            return heap.peek();
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_215_KthLargestElementInAnArray().new Solution();

        // 测试用例 1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        int result1 = solution.findKthLargest(nums1, k1);
        System.out.println("Test Case 1: " + result1);

        // 测试用例 2
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        int result2 = solution.findKthLargest(nums2, k2);
        System.out.println("Test Case 2: " + result2);
    }
}

/**
Given an integer array nums and an integer k, return the kᵗʰ largest element in 
the array. 

 Note that it is the kᵗʰ largest element in the sorted order, not the kᵗʰ 
distinct element. 

 Can you solve it without sorting? 

 
 Example 1: 
 Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
 
 Example 2: 
 Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
 
 
 Constraints: 

 
 1 <= k <= nums.length <= 10⁵ 
 -10⁴ <= nums[i] <= 10⁴ 
 

 Related Topics Array Divide and Conquer Sorting Heap (Priority Queue) 
Quickselect 👍 16432 👎 815

*/

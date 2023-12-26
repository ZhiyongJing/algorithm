package leetcode.frequent.binary_search;

/**
 *@Question:  4. Median of Two Sorted Arrays
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 92.68%
 *@Time  Complexity: O(log(min(m, n)))，其中 m 和 n 分别是两个数组的长度。
 *@Space Complexity: O(1)
 */

/**
 * **解题思路：**
 *
 * 这道题目要求在两个已排序的数组中找到它们的中位数。为了达到时间复杂度 O(log(min(m, n))) 的要求，我们可以使用二分查找的思想。
 *
 * **主要思路：**
 *
 * 1. 选择较短的数组作为第一个数组（若两个数组长度相等则随意选择一个）。设较短数组的长度为 m，较长数组的长度为 n。
 *
 * 2. 在较短数组中选择一个分割点 partitionA，同时计算出在较长数组中对应的分割点 partitionB，使得 partitionA 左侧的元素都小于 partitionB 右侧的元素，
 * 且 partitionB 左侧的元素都小于 partitionA 右侧的元素。
 *
 * 3. 根据分割点计算中位数：
 *    - 如果 m + n 为偶数，则中位数为两个分割点左侧元素的最大值和右侧元素的最小值的平均值。
 *    - 如果 m + n 为奇数，则中位数为两个分割点左侧元素的最大值。
 *
 * 4. 根据中位数是否满足条件，调整分割点的位置，继续进行二分查找。
 *
 * **时间复杂度：**
 *
 * - 由于每一步都进行二分查找，每次将搜索范围缩小一半，因此时间复杂度为 O(log(min(m, n)))。
 *
 * **空间复杂度：**
 *
 * - 由于只使用了常数个变量，空间复杂度为 O(1)。
 */

public class LeetCode_4_MedianOfTwoSortedArrays {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 确保 nums1 是较短的数组
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int m = nums1.length, n = nums2.length;
            int left = 0, right = m;

            while (left <= right) {
                // 在 nums1 中选择一个分割点
                int partitionA = (left + right) / 2;
                // 在 nums2 中计算对应的分割点
                int partitionB = (m + n + 1) / 2 - partitionA;

                // 计算分割点左右的元素值
                int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : nums1[partitionA - 1];
                int minRightA = (partitionA == m) ? Integer.MAX_VALUE : nums1[partitionA];
                int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : nums2[partitionB - 1];
                int minRightB = (partitionB == n) ? Integer.MAX_VALUE : nums2[partitionB];

                // 判断当前分割是否合适
                if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                    // 如果数组总长度为偶数，取中间两个元素的平均值
                    if ((m + n) % 2 == 0) {
                        return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                    } else {
                        // 如果数组总长度为奇数，取左半部分的最大值
                        return Math.max(maxLeftA, maxLeftB);
                    }
                } else if (maxLeftA > minRightB) {
                    // 调整分割点，使其向左移动
                    right = partitionA - 1;
                } else {
                    // 调整分割点，使其向右移动
                    left = partitionA + 1;
                }
            }
            return 0.0;
        }

        //another binary search method
        public double findMedianSortedArrays2(int[] A, int[] B) {
            int na = A.length, nb = B.length;
            int n = na + nb;
            if ((na + nb) % 2 == 1) {
                return solve(A, B, n / 2, 0, na - 1, 0, nb - 1);
            } else {
                return (double)(solve(A, B, n / 2, 0, na - 1, 0, nb - 1) + solve(A, B, n / 2 - 1, 0, na - 1, 0, nb - 1)) / 2;
            }
        }

        public int solve(int[] A, int[] B, int k, int aStart, int aEnd, int bStart, int bEnd) {
            // If the segment of on array is empty, it means we have passed all
            // its element, just return the corresponding element in the other array.
            if (aEnd < aStart) {
                return B[k - aStart];
            }
            if (bEnd < bStart) {
                return A[k - bStart];
            }

            // Get the middle indexes and middle values of A and B.
            int aIndex = (aStart + aEnd) / 2, bIndex = (bStart + bEnd) / 2;
            int aValue = A[aIndex], bValue = B[bIndex];

            // If k is in the right half of A + B, remove the smaller left half.
            if (aIndex + bIndex < k) {
                if (aValue > bValue) {
                    return solve(A, B, k, aStart, aEnd, bIndex + 1, bEnd);
                } else {
                    return solve(A, B, k, aIndex + 1, aEnd, bStart, bEnd);
                }
            }
            // Otherwise, remove the larger right half.
            else {
                if (aValue > bValue) {
                    return solve(A, B, k, aStart, aIndex - 1, bStart, bEnd);
                } else {
                    return solve(A, B, k, aStart, aEnd, bStart, bIndex - 1);
                }
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_4_MedianOfTwoSortedArrays().new Solution();

        // 测试代码
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double result1 = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println("测试数组1和数组2的中位数：" + result1);

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        double result2 = solution.findMedianSortedArrays(nums3, nums4);
        System.out.println("测试数组3和数组4的中位数：" + result2);
    }
}

/**
 Given two sorted arrays nums1 and nums2 of size m and n respectively, return
 the median of the two sorted arrays.

 The overall run time complexity should be O(log (m+n)).


 Example 1:


 Input: nums1 = [1,3], nums2 = [2]
 Output: 2.00000
 Explanation: merged array = [1,2,3] and median is 2.


 Example 2:


 Input: nums1 = [1,2], nums2 = [3,4]
 Output: 2.50000
 Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.



 Constraints:


 nums1.length == m
 nums2.length == n
 0 <= m <= 1000
 0 <= n <= 1000
 1 <= m + n <= 2000
 -10⁶ <= nums1[i], nums2[i] <= 10⁶


 Related Topics Array Binary Search Divide and Conquer 👍 27021 👎 2975

 */

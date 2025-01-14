package leetcode.question.binary_search;

/**
 *@Question:  4. Median of Two Sorted Arrays
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 92.68%
 *@Time  Complexity: O(log(min(m, n)))，其中 m 和 n 分别是两个数组的长度。
 *@Space Complexity: O(1)
 */
/*
【题目描述】
LeetCode 第 4 题：两个排序数组的中位数 (Median of Two Sorted Arrays)
给定两个**已经排序好的数组** `nums1` 和 `nums2`，需要找出这两个数组的**中位数**，并且要求时间复杂度为 **O(log(m + n))**。
数组的长度可以不同，且数组中可能存在重复元素。

中位数的定义：
- 如果数组长度是奇数，则中位数是中间的那个数；
- 如果数组长度是偶数，则中位数是中间两个数的平均值。

例如：
输入：nums1 = [1, 3], nums2 = [2]
输出：2.0
解释：合并后的数组为 [1, 2, 3]，中位数是 2。

输入：nums1 = [1, 2], nums2 = [3, 4]
输出：2.5
解释：合并后的数组为 [1, 2, 3, 4]，中位数是 (2 + 3) / 2 = 2.5。

注意：
- 你不能直接合并两个数组然后排序，因为这样会导致时间复杂度为 O(m + n)。
- 必须使用二分查找来优化时间复杂度到 O(log(m + n))。

*/

/*
【解题思路】
采用**二分查找法**来解决这个问题，将较短的数组进行二分，并在另一个数组中计算对应的分割点，确保分割后左半部分的最大值小于等于右半部分的最小值。

### 步骤 1：确保数组 nums1 的长度小于等于 nums2
为了简化二分查找的逻辑，我们总是对较短的数组进行二分。如果 `nums1` 的长度大于 `nums2`，则交换两者，确保 `nums1` 是较短的数组。

示例：
nums1 = [1, 3], nums2 = [2, 4, 6]
二分查找会在 nums1 上进行。

---

### 步骤 2：在较短的数组上使用二分查找
使用二分查找法，在数组 `nums1` 中找到一个分割点，使得分割后的左半部分的最大值小于等于右半部分的最小值。

- 分割点的概念：
  将两个数组分成左半部分和右半部分，假设分割点为 `partitionA` 和 `partitionB`。
  - `partitionA` 是数组 nums1 的分割点
  - `partitionB` 是数组 nums2 的分割点
  - 分割后：
    - 左半部分：包含 nums1 的前 `partitionA` 个元素和 nums2 的前 `partitionB` 个元素
    - 右半部分：包含 nums1 的后面部分和 nums2 的后面部分

公式：
`partitionB = (m + n + 1) / 2 - partitionA`

---

### 步骤 3：计算分割后的四个关键值
根据分割点，计算分割点左右的四个关键值：
1. `maxLeftA`：nums1 左半部分的最大值
2. `minRightA`：nums1 右半部分的最小值
3. `maxLeftB`：nums2 左半部分的最大值
4. `minRightB`：nums2 右半部分的最小值

处理边界情况：
- 如果分割点在数组的最左侧，则左半部分的最大值设为 `Integer.MIN_VALUE`。
- 如果分割点在数组的最右侧，则右半部分的最小值设为 `Integer.MAX_VALUE`。

---

### 步骤 4：检查分割是否有效
分割点是有效的，当满足以下条件时：
`maxLeftA <= minRightB && maxLeftB <= minRightA`

- 如果满足条件：
  - 如果数组总长度是偶数，中位数是左半部分最大值和右半部分最小值的平均值。
  - 如果数组总长度是奇数，中位数是左半部分的最大值。

示例：
nums1 = [1, 3], nums2 = [2]
分割点：
- nums1 分割点：partitionA = 1（分割为 [1] 和 [3]）
- nums2 分割点：partitionB = 1（分割为 [2] 和 []）

关键值：
- maxLeftA = 1
- minRightA = 3
- maxLeftB = 2
- minRightB = 正无穷大（因为右半部分为空）

中位数 = 2.0

---

### 步骤 5：调整分割点
如果分割点不符合条件，需要调整分割点：
- 如果 `maxLeftA > minRightB`，说明 nums1 的分割点太靠右了，需要向左移动；
- 如果 `maxLeftB > minRightA`，说明 nums1 的分割点太靠左了，需要向右移动。

示例：
nums1 = [1, 2], nums2 = [3, 4]
初始分割点：
- partitionA = 1，partitionB = 1

关键值：
- maxLeftA = 1
- minRightA = 2
- maxLeftB = 3
- minRightB = 4

调整分割点后：
- partitionA = 2，partitionB = 0

关键值：
- maxLeftA = 2
- minRightA = 正无穷大
- maxLeftB = 负无穷大
- minRightB = 3

中位数 = (2 + 3) / 2 = 2.5

*/

/*
【时间和空间复杂度分析】

### 时间复杂度：
由于采用二分查找法，时间复杂度为 **O(log(min(m, n)))**，其中 `m` 和 `n` 是两个数组的长度。
我们总是对较短的数组进行二分查找，因此时间复杂度取决于较短数组的长度。

### 空间复杂度：
空间复杂度为 **O(1)**，因为只使用了常数级的额外空间。
*/


public class LeetCode_4_MedianOfTwoSortedArrays {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 确保 nums1 是较短的数组，从而减少二分查找的复杂性
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int m = nums1.length, n = nums2.length;
            int left = 0, right = m;  // 初始化二分查找的左右边界

            while (left <= right) {
                // 在 nums1 中选择一个分割点
                int partitionA = (left + right) / 2;
                // 在 nums2 中计算对应的分割点，使左右子数组长度相等
                int partitionB = (m + n + 1) / 2 - partitionA;

                // 获取分割点左侧和右侧的元素值，边界情况用极值处理
                int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : nums1[partitionA - 1];
                int minRightA = (partitionA == m) ? Integer.MAX_VALUE : nums1[partitionA];
                int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : nums2[partitionB - 1];
                int minRightB = (partitionB == n) ? Integer.MAX_VALUE : nums2[partitionB];

                // 检查是否满足中位数条件
                if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                    // 如果总长度是偶数，中位数为左半部分最大值和右半部分最小值的平均
                    if ((m + n) % 2 == 0) {
                        return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                    } else {
                        // 如果总长度是奇数，中位数是左半部分的最大值
                        return Math.max(maxLeftA, maxLeftB);
                    }
                } else if (maxLeftA > minRightB) {
                    // 如果 nums1 左侧最大值大于 nums2 右侧最小值，需左移分割点
                    right = partitionA - 1;
                } else {
                    // 否则右移分割点
                    left = partitionA + 1;
                }
            }
            return 0.0;  // 理论上不会到达这里
        }

        // ！！！Solution 2: 合并排序法，虽然时间复杂度较高，但适用于输入数组无序的情况
        private int p1 = 0, p2 = 0;  // 两个指针分别指向 nums1 和 nums2 的当前位置

        // 获取两个数组中当前的最小值，同时移动对应的指针
        private int getMin(int[] nums1, int[] nums2) {
            if (p1 < nums1.length && p2 < nums2.length) {
                return nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
            } else if (p1 < nums1.length) {
                return nums1[p1++];
            } else if (p2 < nums2.length) {
                return nums2[p2++];
            }
            return -1;  // 理论上不会到达这里
        }

        public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            // 判断总长度是奇数还是偶数
            if ((m + n) % 2 == 0) {
                // 偶数情况：获取中间两个数并计算平均值
                for (int i = 0; i < (m + n) / 2 - 1; ++i) {
                    int tmp = getMin(nums1, nums2);
                }
                return (double)(getMin(nums1, nums2) + getMin(nums1, nums2)) / 2;
            } else {
                // 奇数情况：获取中间数
                for (int i = 0; i < (m + n) / 2; ++i) {
                    int tmp = getMin(nums1, nums2);
                }
                return getMin(nums1, nums2);
            }
        }

        // 使用二分查找的另一种方法
        public double findMedianSortedArrays3(int[] A, int[] B) {
            int na = A.length, nb = B.length;
            int n = na + nb;
            // 判断总长度是奇数还是偶数
            if ((na + nb) % 2 == 1) {
                return solve(A, B, n / 2, 0, na - 1, 0, nb - 1);
            } else {
                return (double)(solve(A, B, n / 2, 0, na - 1, 0, nb - 1) + solve(A, B, n / 2 - 1, 0, na - 1, 0, nb - 1)) / 2;
            }
        }

        public int solve(int[] A, int[] B, int k, int aStart, int aEnd, int bStart, int bEnd) {
            // 如果一个数组段为空，直接返回另一个数组中第 k 个元素
            if (aEnd < aStart) {
                return B[k - aStart];
            }
            if (bEnd < bStart) {
                return A[k - bStart];
            }

            // 获取 A 和 B 的中间索引及其对应的值
            int aIndex = (aStart + aEnd) / 2, bIndex = (bStart + bEnd) / 2;
            int aValue = A[aIndex], bValue = B[bIndex];

            // 如果 k 在右半部分，则移除左半部分较小的部分
            if (aIndex + bIndex < k) {
                if (aValue > bValue) {
                    return solve(A, B, k, aStart, aEnd, bIndex + 1, bEnd);
                } else {
                    return solve(A, B, k, aIndex + 1, aEnd, bStart, bEnd);
                }
            }
            // 否则移除右半部分较大的部分
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

        int[] nums5 = {0, 0};
        int[] nums6 = {0, 0};
        double result3 = solution.findMedianSortedArrays(nums5, nums6);
        System.out.println("测试数组5和数组6的中位数：" + result3);
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

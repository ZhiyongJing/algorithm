package leetcode.question.binary_search;

import javafx.util.Pair;

import java.util.Arrays;

/**
 *@Question:  1508. Range Sum of Sorted Subarray Sums
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 87.96%
 *@Time  Complexity: O(N * log(sum)), sum is the total sum of nums array, n is the size
 *@Space Complexity: O(1)
 */
/**
 * @Question: 1508. Range Sum of Sorted Subarray Sums
 *
 * @Description:
 * 给定一个数组 nums 和三个整数 n（数组长度）、left 和 right。
 * 要求计算从第 left 小到第 right 小的所有子数组和的总和，并对结果取模 MOD = 10^9 + 7。
 * 子数组和的定义是：数组中任意连续子数组元素之和。
 *
 * @解题思路:
 *
 * 1. **问题分析与目标**:
 *    - 我们需要找到所有可能子数组和的集合，按升序排列后，从第 left 到第 right 的和取总。
 *    - 子数组和总数为 n * (n + 1) / 2（所有子数组的数量）。
 *
 * 2. **计算逻辑**:
 *    - 子数组和需要按升序排列，可以借助 **二分查找** 和 **滑动窗口** 优化计算过程。
 *
 * 3. **具体步骤**:
 *    1. **确定范围**:
 *       - 子数组和的最小值为数组中最小元素。
 *       - 子数组和的最大值为数组所有元素的总和。
 *       - 使用二分查找的左右边界为 [最小值, 最大值]。
 *
 *       **示例**:
 *       - nums = [1, 2, 3]。
 *         - 最小值 = 1（数组中最小元素）。
 *         - 最大值 = 1 + 2 + 3 = 6（数组总和）。
 *
 *    2. **二分查找**:
 *       - 每次选定一个中间值 mid，计算小于等于 mid 的子数组和总数（及其和）。
 *       - 如果数量 >= k（目标数量），说明 mid 可以进一步缩小，右边界减少。
 *       - 否则，左边界增加。
 *
 *       **示例**:
 *       - 假设 nums = [1, 2, 3]，目标是找第 2 小的子数组和。
 *         - 初始范围 [1, 6]，mid = (1 + 6) / 2 = 3。
 *         - 小于等于 3 的子数组和数量为 3（分别是 [1], [2], [1, 2]），继续缩小范围。
 *
 *    3. **统计结果**:
 *       - 找到目标区间后，累计子数组和并减去多余部分。
 *       - 使用滑动窗口统计所有子数组和的累加值。
 *
 *       **示例**:
 *       - nums = [1, 2, 3]。
 *         - 目标区间 [1, 2]。
 *         - 第 1 小为 1，第 2 小为 2，总和 = 1 + 2 = 3。
 *
 * @时间复杂度:
 * - 二分查找的次数为 O(log(SUM))，SUM 为数组元素总和。
 * - 每次二分查找中滑动窗口遍历数组，复杂度为 O(N)。
 * - 总时间复杂度为 O(N * log(SUM))。
 *
 * @空间复杂度:
 * - 空间复杂度为 O(1)，只使用了常数辅助变量。
 */


public class LeetCode_1508_RangeSumOfSortedSubarraySums {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        private static final int MOD = 1000000007; // 定义一个常量 MOD，用于结果取模操作

        // 主函数，计算从第 left 个到第 right 个最小子数组和的总和
        public int rangeSum(int[] nums, int n, int left, int right) {
            // 使用 sumOfFirstK 计算第 right 个和第 (left - 1) 个最小子数组和的差值，结果取模
            long result =
                    (sumOfFirstK(nums, n, right) - sumOfFirstK(nums, n, left - 1)) %
                            MOD;
            // 确保结果为非负数
            return (int) ((result + MOD) % MOD);
        }

        // 辅助函数，计算小于等于目标值 target 的子数组数量及其总和
        private Pair<Integer, Long> countAndSum(
                int[] nums,
                int n,
                int target
        ) {
            int count = 0; // 子数组数量
            long currentSum = 0, totalSum = 0, windowSum = 0; // 当前窗口和及总和
            for (int j = 0, i = 0; j < n; ++j) { // 遍历数组
                currentSum += nums[j]; // 更新当前窗口和
                windowSum += nums[j] * (j - i + 1); // 更新窗口的子数组和
                while (currentSum > target) { // 如果当前窗口和超过目标值
                    windowSum -= currentSum; // 减去多余部分
                    currentSum -= nums[i++]; // 移动窗口左边界
                }
                count += j - i + 1; // 累加子数组数量
                totalSum += windowSum; // 累加总和
            }
            return new Pair<>(count, totalSum); // 返回结果
        }

        // 辅助函数，找到前 k 个最小子数组和的总和
        private long sumOfFirstK(int[] nums, int n, int k) {
            int minSum = Arrays.stream(nums).min().getAsInt(); // 数组中的最小元素
            int maxSum = Arrays.stream(nums).sum(); // 数组的总和
            int left = minSum, right = maxSum; // 二分查找的左右边界

            while (left <= right) { // 二分查找
                int mid = left + (right - left) / 2; // 中间值
                if (countAndSum(nums, n, mid).getKey() >= k) // 判断小于等于 mid 的子数组数量是否足够
                    right = mid - 1; // 缩小右边界
                else
                    left = mid + 1; // 增大左边界
            }
            Pair<Integer, Long> result = countAndSum(nums, n, left); // 计算结果
            // 考虑可能有多个子数组和等于 left 的情况
            return result.getValue() - left * (result.getKey() - k);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_1508_RangeSumOfSortedSubarraySums.Solution solution = new LeetCode_1508_RangeSumOfSortedSubarraySums().new Solution();

        // 测试样例 1
        int[] nums1 = {1, 2, 3, 4};
        int n1 = 4, left1 = 1, right1 = 5;
        System.out.println(solution.rangeSum(nums1, n1, left1, right1)); // 输出结果

        // 测试样例 2
        int[] nums2 = {1, 2, 3, 4};
        int n2 = 4, left2 = 2, right2 = 3;
        System.out.println(solution.rangeSum(nums2, n2, left2, right2)); // 输出结果

        // 测试样例 3
        int[] nums3 = {1, 2, 3, 4};
        int n3 = 4, left3 = 3, right3 = 4;
        System.out.println(solution.rangeSum(nums3, n3, left3, right3)); // 输出结果
    }
}

/**
 You are given the array nums consisting of n positive integers. You computed
 the sum of all non-empty continuous subarrays from the array and then sorted them
 in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.

 Return the sum of the numbers from index left to index right (indexed from 1), 
 inclusive, in the new array. Since the answer can be a huge number return it
 modulo 10⁹ + 7.


 Example 1: 


 Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
 Output: 13
 Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting
 them in non-decreasing order we have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 1
 0]. The sum of the numbers from index le = 1 to ri = 5 is 1 + 2 + 3 + 3 + 4 = 13
 .


 Example 2: 


 Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
 Output: 6
 Explanation: The given array is the same as example 1. We have the new array [1,
 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 3 to ri = 4
 is 3 + 3 = 6.


 Example 3: 


 Input: nums = [1,2,3,4], n = 4, left = 1, right = 10
 Output: 50



 Constraints: 


 n == nums.length 
 1 <= nums.length <= 1000 
 1 <= nums[i] <= 100 
 1 <= left <= right <= n * (n + 1) / 2 


 Related Topics Array Two Pointers Binary Search Sorting 👍 1544 👎 261

 */
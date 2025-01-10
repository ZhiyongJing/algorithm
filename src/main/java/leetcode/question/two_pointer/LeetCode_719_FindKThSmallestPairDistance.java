package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 *@Question:  719. Find K-th Smallest Pair Distance
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 74.78%
 *@Time  Complexity: O(N*log M + N*logN), N is the number of elements, and M be the maximum possible distance.
 *@Space Complexity: O(1)
 */
/**
 * 题目描述：
 *
 * 给定一个整数数组 `nums` 和一个整数 `k`，要求找到 `nums` 数组中所有对的距离，并返回第 `k` 小的距离。对于任意一对 `nums[i]` 和 `nums[j]`，其距离为 `|nums[i] - nums[j]|`，其中 `i < j`。
 *
 * 示例：
 * 输入: `nums = [1, 3, 1]`, `k = 1`
 * 输出: 0
 * 解释: 该数组中所有的对距离为 [0, 2, 2]，第 1 小的距离为 0。
 *
 * 输入: `nums = [1, 1, 1]`, `k = 2`
 * 输出: 0
 * 解释: 该数组中所有的对距离为 [0, 0, 0]，第 2 小的距离为 0。
 */

/**
 * 解题思路：
 *
 * 1. 排序数组：
 *    - 由于我们需要计算所有对的距离，排序后的数组使得计算距离变得更加高效。
 *    - 例如，给定 `nums = [1, 3, 1]`，排序后的结果是 `[1, 1, 3]`，这可以使得计算两个元素之间的距离更加简洁。
 *
 * 2. 二分查找：
 *    - 由于数组已排序，我们可以采用二分查找的方法来查找第 `k` 小的对的距离。
 *    - 设置二分查找的范围为 `[0, max(nums) - min(nums)]`，并根据当前的中间值来调整范围。
 *    - 例如，假设排序后的数组为 `[1, 1, 3]`，我们可以设定 `low = 0` 和 `high = 2`，这对应于可能的最小和最大距离。然后在 `low` 到 `high` 的范围内进行二分查找。
 *
 * 3. 计算对数：
 *    - 对于每个 `mid` 值，计算 `nums` 中所有对的距离小于等于 `mid` 的对数。
 *    - 使用滑动窗口的方式，遍历数组，并调整左右指针来计算符合条件的对数。
 *    - 例如，当 `mid = 1` 时，通过滑动窗口可以得到距离小于等于 1 的对数。
 *
 * 4. 调整二分查找区间：
 *    - 如果当前的对数小于 `k`，说明距离 `mid` 太小，需要增大距离；如果对数大于或等于 `k`，说明当前的距离合适，继续缩小范围，直到找到第 `k` 小的距离。
 *    - 例如，如果 `mid = 1` 时，计算出的对数小于 `k`，则需要增加 `mid`，否则减小 `mid`。
 *
 * 5. 最终返回 `low`，此时 `low` 就是第 `k` 小的距离。
 */

/**
 * 时间复杂度：
 *    - 排序的时间复杂度为 `O(N log N)`，其中 `N` 是数组的长度。
 *    - 二分查找的复杂度为 `O(log(max - min))`，其中 `max` 和 `min` 分别是数组的最大值和最小值。
 *    - 每次二分查找需要调用 `countPairsWithMaxDistance` 函数，该函数的时间复杂度为 `O(N)`。
 *    - 因此，总体时间复杂度为 `O(N log N + N log(max - min))`，其中排序的时间和二分查找的时间相加。
 *    - 这里假设 `max - min` 和 `N` 是相对较小的，因此时间复杂度可以简化为 `O(N log N)`。
 *
 * 空间复杂度：
 *    - 由于我们只使用了常量级别的额外空间来进行二分查找和滑动窗口操作，所以空间复杂度为 `O(1)`。
 */


public class LeetCode_719_FindKThSmallestPairDistance {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 主函数，返回第k小的距离
        public int smallestDistancePair(int[] nums, int k) {
            // 排序数组，方便计算距离
            Arrays.sort(nums);
            int arraySize = nums.length;

            // 初始化二分查找的范围
            int low = 0; // 最小可能的距离为0
            int high = nums[arraySize - 1] - nums[0]; // 最大可能的距离为数组最大值和最小值之差

            // 二分查找合适的距离
            while (low < high) {
                int mid = (low + high) / 2; // 计算中间的距离

                // 计算当前距离下小于等于mid的对数
                int count = countPairsWithMaxDistance(nums, mid);

                // 根据对数的大小调整二分查找的范围
                if (count < k) {
                    low = mid + 1; // 如果对数小于k，说明mid距离太小，需要增加
                } else {
                    high = mid; // 如果对数大于或等于k，说明mid距离合适，继续缩小范围
                }
            }
            // 当low等于high时，返回当前距离
            return low;
        }

        // 计算小于等于maxDistance的对数
        private int countPairsWithMaxDistance(int[] nums, int maxDistance) {
            int count = 0; // 对数计数器
            int arraySize = nums.length;
            int left = 0; // 左指针初始化为0

            // 遍历右指针
            for (int right = 0; right < arraySize; ++right) {
                // 调整左指针，使得nums[right] - nums[left] <= maxDistance
                while (nums[right] - nums[left] > maxDistance) {
                    ++left; // 如果差值大于maxDistance，则左指针向右移动
                }
                // 统计当前右指针下，符合条件的对数
                count += right - left; // 所有从left到right的对都符合条件
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_719_FindKThSmallestPairDistance().new Solution();
        // 测试样例1
        int[] nums1 = {1, 3, 1};
        int k1 = 1;
        System.out.println(solution.smallestDistancePair(nums1, k1)); // 输出 0

        // 测试样例2
        int[] nums2 = {1, 1, 1};
        int k2 = 2;
        System.out.println(solution.smallestDistancePair(nums2, k2)); // 输出 0

        // 测试样例3
        int[] nums3 = {1, 2, 3, 4, 5};
        int k3 = 3;
        System.out.println(solution.smallestDistancePair(nums3, k3)); // 输出 1
    }
}

/**
The distance of a pair of integers a and b is defined as the absolute 
difference between a and b. 

 Given an integer array nums and an integer k, return the kᵗʰ smallest distance 
among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length. 

 
 Example 1: 

 
Input: nums = [1,3,1], k = 1
Output: 0
Explanation: Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1ˢᵗ smallest distance pair is (1,1), and its distance is 0.
 

 Example 2: 

 
Input: nums = [1,1,1], k = 2
Output: 0
 

 Example 3: 

 
Input: nums = [1,6,1], k = 3
Output: 5
 

 
 Constraints: 

 
 n == nums.length 
 2 <= n <= 10⁴ 
 0 <= nums[i] <= 10⁶ 
 1 <= k <= n * (n - 1) / 2 
 

 Related Topics Array Two Pointers Binary Search Sorting 👍 3778 👎 120

*/
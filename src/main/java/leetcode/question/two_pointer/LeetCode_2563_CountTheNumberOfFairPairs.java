package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 *@Question:  2563. Count the Number of Fair Pairs
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 76.7%
 *@Time Complexity: O(N * logN)
 *@Space Complexity: O(N)
 */
/**
 * 题目描述：
 * 给定一个整数数组 `nums` 和两个整数 `lower` 和 `upper`，请计算满足以下条件的“公平”对的数量：
 * 1. 对于每对 `(i, j)`，数组中的两个数 `nums[i]` 和 `nums[j]`，其和 `nums[i] + nums[j]` 满足 `lower <= nums[i] + nums[j] <= upper`，并且 `i < j`。
 * 2. 输出符合条件的所有“公平”对的个数。
 *
 * 输入：
 * - 一个整数数组 `nums`，表示给定的数组。
 * - 一个整数 `lower`，表示范围下界。
 * - 一个整数 `upper`，表示范围上界。
 *
 * 输出：
 * - 返回满足条件的“公平”对的个数。
 *
 * 示例：
 * 输入：`nums = [1, 3, 5, 7]`, `lower = 4`, `upper = 8`
 * 输出：3
 * 解释：
 * 满足条件的对为：(1, 3), (1, 5), (3, 5)
 *
 * 输入：`nums = [1, 2, 3, 4, 5]`, `lower = 5`, `upper = 10`
 * 输出：6
 * 解释：
 * 满足条件的对为：(1, 4), (1, 5), (2, 3), (2, 4), (2, 5), (3, 4)
 */

/**
 * 解题思路：
 *
 * 1. **排序数组**：
 *    - 首先我们需要对数组 `nums` 进行排序。排序使得我们可以通过双指针法更高效地找到符合条件的配对。
 *    - 排序后的数组会使得我们能够更容易地对配对的和进行限制。
 *    - 例如：对于输入 `nums = [3, 1, 5, 7]`，排序后变成 `nums = [1, 3, 5, 7]`。
 *
 * 2. **使用双指针法**：
 *    - 我们可以使用两个指针 `left` 和 `right` 来遍历数组，`left` 从数组的左边开始，`right` 从数组的右边开始。
 *    - 我们计算当前两个数的和 `nums[left] + nums[right]`，如果和小于给定的目标值（例如 `lower` 或 `upper`），我们就可以向右移动左指针 `left`，否则左指针不动，右指针 `right` 向左移动。
 *    - 通过移动指针，我们可以找到所有符合条件的配对。
 *    - 举个例子：对于 `nums = [1, 3, 5, 7]` 和 `lower = 4`，`upper = 8`，我们可以使用双指针法，快速找到所有满足和在 [4, 8] 范围内的配对。
 *
 * 3. **计算“公平”对的数量**：
 *    - 通过双指针法，我们可以分别计算满足 `nums[i] + nums[j] < upper + 1` 和 `nums[i] + nums[j] < lower` 的配对数量。
 *    - 计算这两个数量的差值，就能得到满足 `lower <= nums[i] + nums[j] <= upper` 的配对数量。
 *    - 举例：对于 `nums = [1, 3, 5, 7]`，我们通过两次调用 `lower_bound` 方法来分别计算满足小于等于 `upper + 1` 和小于 `lower` 的配对数量，最终得出结果。
 *
 * 4. **总结**：
 *    - 排序数组时间复杂度为 O(N log N)。
 *    - 双指针遍历数组的时间复杂度为 O(N)。
 *    - 因此，整体算法的时间复杂度为 O(N log N)。
 *    - 空间复杂度为 O(1)，没有使用额外空间。
 */

/**
 * 时间复杂度：
 * 1. 排序数组所需的时间复杂度为 O(N log N)，其中 N 是数组的大小。
 * 2. 使用双指针法遍历数组，时间复杂度为 O(N)。
 * 因此，总的时间复杂度为 O(N log N)。
 *
 * 空间复杂度：
 * 1. 排序过程中可能需要 O(N) 的空间来存储排序结果。
 * 2. 使用双指针遍历时，只用了少量的额外空间，因此空间复杂度为 O(N)。
 */

public class LeetCode_2563_CountTheNumberOfFairPairs {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 计算满足条件的"公平"对数
         * @param nums 数组
         * @param lower 下界
         * @param upper 上界
         * @return 满足条件的对数
         */
        public long countFairPairs(int[] nums, int lower, int upper) {
            // 对数组进行排序，以便通过双指针方法快速计算
            Arrays.sort(nums);
            // 返回区间 [lower, upper] 中的对数差
            return lower_bound(nums, upper + 1) - lower_bound(nums, lower);
        }

        /**
         * 计算数组中满足两个数之和小于 `value` 的对数
         * @param nums 数组
         * @param value 边界值
         * @return 满足条件的对数
         */
        private long lower_bound(int[] nums, int value) {
            int left = 0, right = nums.length - 1;
            long result = 0;
            // 双指针方式计算
            while (left < right) {
                int sum = nums[left] + nums[right];
                // 如果当前两数之和小于 value，说明左指针可以向右移动并且该右指针所指向的数可以和
                // 当前左指针的数形成多组合法的对
                if (sum < value) {
                    result += (right - left);  // 所以，增加满足条件的对数（右指针到左指针之间的所有数都有效）
                    left++;  // 向右移动左指针，考虑下一个较大的数
                } else {
                    // 否则，减小右指针，寻找符合条件的对
                    right--;  // 向左移动右指针
                }
            }
            // 返回符合条件的对数
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 创建解法对象
        Solution solution = new LeetCode_2563_CountTheNumberOfFairPairs().new Solution();

        // 测试用例1
        int[] nums1 = {1, 3, 5, 7};
        int lower1 = 4, upper1 = 8;
        System.out.println(solution.countFairPairs(nums1, lower1, upper1));  // 结果应为3

        // 测试用例2
        int[] nums2 = {1, 2, 3, 4, 5};
        int lower2 = 5, upper2 = 10;
        System.out.println(solution.countFairPairs(nums2, lower2, upper2));  // 结果应为6

        // 测试用例3
        int[] nums3 = {-1, 0, 1, 2, 3};
        int lower3 = 0, upper3 = 4;
        System.out.println(solution.countFairPairs(nums3, lower3, upper3));  // 结果应为4
    }
}

/**
Given a 0-indexed integer array nums of size n and two integers lower and upper,
 return the number of fair pairs. 

 A pair (i, j) is fair if: 

 
 0 <= i < j < n, and 
 lower <= nums[i] + nums[j] <= upper 
 

 
 Example 1: 

 
Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5
).
 

 Example 2: 

 
Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 nums.length == n 
 -10⁹ <= nums[i] <= 10⁹ 
 -10⁹ <= lower <= upper <= 10⁹ 
 

 Related Topics Array Two Pointers Binary Search Sorting 👍 1554 👎 106

*/
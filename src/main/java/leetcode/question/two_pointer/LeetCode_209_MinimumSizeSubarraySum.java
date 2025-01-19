package leetcode.question.two_pointer;
/**
 *@Question:  209. Minimum Size Subarray Sum
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.72%
 *@Time  Complexity: O(N)  // 采用滑动窗口，每个元素最多被访问两次（一次进窗口，一次出窗口），所以时间复杂度为 O(N)
 *@Space Complexity: O(1)  // 仅使用了常数级别的额外空间
 */
/**
 * 题目描述：
 * 给定一个包含正整数的数组 `nums` 和一个目标值 `target`，
 * 找出**最短的连续子数组**，使得该子数组的元素之和**大于等于** `target`，
 * 返回满足条件的**最短子数组长度**。
 * 如果没有符合条件的子数组，则返回 `0`。
 *
 * 示例：
 * 输入: target = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 最短的子数组为 [4,3] 或 [3,4]，长度为 2。
 *
 * 输入: target = 4, nums = [1,4,4]
 * 输出: 1
 * 解释: 最短的子数组为 [4]，长度为 1。
 *
 * 输入: target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出: 0
 * 解释: 数组中所有元素加起来都小于 11，因此返回 0。
 */

/**
 * 解题思路：
 * 该问题可以使用**滑动窗口**（双指针）技术来高效求解，避免暴力解法的 O(N²) 时间复杂度。
 * 滑动窗口的核心思想是：
 * 1. 维护两个指针 `left` 和 `right`，它们分别表示窗口的左右边界，初始化都指向数组起始位置。
 * 2. `right` 指针向右移动，扩展窗口，使窗口内的元素和逐渐增大，直到**满足目标和 `target`**。
 * 3. 一旦窗口和 `sumOfCurrentWindow` 大于等于 `target`，尝试**移动 `left` 指针收缩窗口**，以寻找更短的子数组。
 * 4. 在收缩窗口的过程中，每次满足 `sumOfCurrentWindow >= target`，更新**最小子数组长度**。
 * 5. 继续扩展 `right` 指针，重复上述过程，直到遍历完整个数组。
 * 6. 如果遍历完数组后没有找到符合条件的子数组，则返回 `0`。
 *
 * **示例步骤解析：**
 * 示例输入：target = 7, nums = [2,3,1,2,4,3]
 *
 * 初始化：left = 0, right = 0, sumOfCurrentWindow = 0, res = Integer.MAX_VALUE
 *
 * - **Step 1**: right = 0，加入 `nums[0] = 2`，sumOfCurrentWindow = 2，尚未达到 target=7。
 * - **Step 2**: right = 1，加入 `nums[1] = 3`，sumOfCurrentWindow = 5，尚未达到 target=7。
 * - **Step 3**: right = 2，加入 `nums[2] = 1`，sumOfCurrentWindow = 6，尚未达到 target=7。
 * - **Step 4**: right = 3，加入 `nums[3] = 2`，sumOfCurrentWindow = 8，满足 target=7。
 *     - 计算当前子数组长度 `right - left + 1 = 4`，更新 `res = 4`。
 *     - 尝试收缩窗口，left = 1，sumOfCurrentWindow = 6，不满足 target，停止收缩。
 * - **Step 5**: right = 4，加入 `nums[4] = 4`，sumOfCurrentWindow = 10，满足 target=7。
 *     - 计算当前子数组长度 `right - left + 1 = 4`，`res` 仍然是 4，不更新。
 *     - 收缩窗口，left = 2，sumOfCurrentWindow = 7，仍然满足 target。
 *     - 计算当前子数组长度 `right - left + 1 = 3`，更新 `res = 3`。
 *     - 继续收缩，left = 3，sumOfCurrentWindow = 6，不满足 target，停止收缩。
 * - **Step 6**: right = 5，加入 `nums[5] = 3`，sumOfCurrentWindow = 9，满足 target=7。
 *     - 计算当前子数组长度 `right - left + 1 = 3`，`res` 仍然是 3，不更新。
 *     - 收缩窗口，left = 4，sumOfCurrentWindow = 7，仍然满足 target。
 *     - 计算当前子数组长度 `right - left + 1 = 2`，更新 `res = 2`。
 *     - 继续收缩，left = 5，sumOfCurrentWindow = 3，不满足 target，停止收缩。
 *
 * 遍历结束，返回 `res = 2`，即最短子数组长度为 2。
 */

/**
 * 时间和空间复杂度分析：
 * - **时间复杂度：O(N)**
 *   - 每个元素最多被访问两次（一次是 `right` 扩展窗口，一次是 `left` 收缩窗口），所以整体时间复杂度是 `O(N)`。
 *   - 相比于暴力解法的 `O(N²)`，大大提高了效率。
 *
 * - **空间复杂度：O(1)**
 *   - 仅使用了几个额外变量 (`left`, `right`, `sumOfCurrentWindow`, `res`)，没有使用额外的数据结构，因此是常数级 `O(1)`。
 */


public class LeetCode_209_MinimumSizeSubarraySum{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            // 定义滑动窗口的左右边界，初始化为 0
            int left = 0, right = 0;
            // 记录当前窗口内的元素总和
            int sumOfCurrentWindow = 0;
            // 记录满足条件的最小子数组长度，初始化为一个较大值
            int res = Integer.MAX_VALUE;

            // 遍历数组，right 指针用于扩展窗口
            for(right = 0; right < nums.length; right++) {
                // 将当前 right 指向的元素加入窗口
                sumOfCurrentWindow += nums[right];

                // 当窗口内元素之和大于等于目标值时，尝试收缩窗口
                while (sumOfCurrentWindow >= target) {
                    // 更新满足条件的最小子数组长度
                    res = Math.min(res, right - left + 1);
                    // 移动左指针以尝试缩小窗口
                    sumOfCurrentWindow -= nums[left++];
                }
            }

            // 如果 res 仍然是初始化值，说明没有满足条件的子数组，返回 0
            return res == Integer.MAX_VALUE ? 0 : res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_209_MinimumSizeSubarraySum().new Solution();

        // 测试样例
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;
        System.out.println(solution.minSubArrayLen(target1, nums1)); // 预期输出: 2 (子数组 [4,3] 或 [3,4])

        int[] nums2 = {1, 4, 4};
        int target2 = 4;
        System.out.println(solution.minSubArrayLen(target2, nums2)); // 预期输出: 1 (子数组 [4])

        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        System.out.println(solution.minSubArrayLen(target3, nums3)); // 预期输出: 0 (无满足条件的子数组)
    }
}

/**
Given an array of positive integers nums and a positive integer target, return 
the minimal length of a subarray whose sum is greater than or equal to target. 
If there is no such subarray, return 0 instead. 

 
 Example 1: 

 
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem 
constraint.
 

 Example 2: 

 
Input: target = 4, nums = [1,4,4]
Output: 1
 

 Example 3: 

 
Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
 

 
 Constraints: 

 
 1 <= target <= 10⁹ 
 1 <= nums.length <= 10⁵ 
 1 <= nums[i] <= 10⁴ 
 

 
Follow up: If you have figured out the 
O(n) solution, try coding another solution of which the time complexity is 
O(n log(n)).

 Related Topics Array Binary Search Sliding Window Prefix Sum 👍 13103 👎 474

*/
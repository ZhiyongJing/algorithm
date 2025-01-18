package leetcode.question.prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  525. Contiguous Array
 *@Difficulty:  Medium
 *@Frequency:  65.45%
 *@Time Complexity: O(N) - 仅需一次遍历数组
 *@Space Complexity: O(N) - 需要存储前缀和的索引
 */
/**
 * 题目描述：
 * ------------
 * 给定一个仅包含 0 和 1 的数组 `nums`，我们需要找到一个最长的连续子数组，
 * 该子数组内的 0 和 1 的数量相等。
 *
 * 例如：
 * 输入：nums = [0,1,1,0,1,1,1,0]
 * 输出：4
 * 解释：最长的平衡子数组是 [1,1,0,1]，长度为 4。
 *
 * ------------
 *
 * 解题思路：
 * ------------
 * 1. **使用前缀和 + 哈希表（HashMap）**
 *    - 定义 `count` 变量遍历数组：
 *      - 看到 `1`，`count += 1`
 *      - 看到 `0`，`count -= 1`
 *    - 如果在 `i` 位置 `count` 之前出现过，则表示从 `map.get(count) + 1` 到 `i` 之间的子数组是平衡的。
 *    - 记录 `count` 首次出现的索引值，并在后续遇到相同 `count` 值时计算最长子数组长度。
 *
 * 2. **哈希表存储前缀和出现的最早索引**
 *    - `map.put(0, -1)` 设定初始值，保证可以计算完整数组的情况。
 *    - 当 `count` 第一次出现时，记录索引，确保最长子数组长度计算。
 *
 * ------------
 *
 * 示例分析：
 * ------------
 * 示例 1：
 * 输入：nums = [0,1]
 * 过程：
 * - `count` 变化：`[-1, 0]`
 * - `map` 记录：`{0: -1, -1: 0}`
 * - 计算长度：`i - map.get(0) = 1 - (-1) = 2`
 * 输出：`2`
 *
 * 示例 2：
 * 输入：nums = [0, 1, 1, 0, 1, 1, 1, 0]
 * 过程：
 * - `count` 变化：`[-1, 0, 1, 0, 1, 2, 3, 2]`
 * - `map` 记录：`{0: -1, -1: 0, 1: 2, 2: 5, 3: 6}`
 * - 最长平衡子数组 `[1, 1, 0, 1]`，长度 `4`
 * 输出：`4`
 *
 * ------------
 *
 * 时间和空间复杂度：
 * ------------
 * - **时间复杂度：O(N)**：
 *   - 只需要遍历一次数组，`put` 和 `get` 操作平均 O(1)，总时间复杂度 O(N)。
 * - **空间复杂度：O(N)**：
 *   - 哈希表 `map` 最多存储 N 个不同前缀和，最坏情况下 O(N)。
 */


public class LeetCode_525_ContiguousArray{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        /**
         * 计算最长的连续子数组，该子数组包含相同数量的 0 和 1
         *
         * @param nums 仅包含 0 和 1 的数组
         * @return 最长的子数组长度
         */
        public int findMaxLength(int[] nums) {
            // 哈希表用于存储前缀和及其对应的最早索引
            Map<Integer, Integer> map = new HashMap<>();
            // 设定初始条件：count = 0 对应的索引为 -1，方便计算完整数组的情况
            map.put(0, -1);
            int maxlen = 0; // 记录最长子数组的长度
            int count = 0;   // 记录 0 和 1 的平衡状态，0 视为 -1，1 视为 +1

            // 遍历整个数组
            for (int i = 0; i < nums.length; i++) {
                // 如果是 1，则 count +1，如果是 0，则 count -1
                count = count + (nums[i] == 1 ? 1 : -1);

                // 如果当前 count 之前出现过，说明在 map.get(count) 之后到当前索引 i 之间的子数组是平衡的
                if (map.containsKey(count)) {
                    // 计算当前子数组的长度，并更新 maxlen
                    maxlen = Math.max(maxlen, i - map.get(count));
                } else {
                    // 如果 count 不在哈希表中，记录它的最早出现索引
                    map.put(count, i);
                }
            }
            System.out.println(map);
            return maxlen;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_525_ContiguousArray().new Solution();

        // 测试用例 1
        int[] nums1 = {0, 1};
        System.out.println(solution.findMaxLength(nums1)); // 预期输出：2

        // 测试用例 2
        int[] nums2 = {0, 1, 0};
        System.out.println(solution.findMaxLength(nums2)); // 预期输出：2

        // 测试用例 3
        int[] nums3 = {0, 1, 1, 0, 1, 1, 1, 0};
        System.out.println(solution.findMaxLength(nums3)); // 预期输出：4

        // 测试用例 4
        int[] nums4 = {0, 0, 1, 1, 0, 1, 1, 0, 0};
        System.out.println(solution.findMaxLength(nums4)); // 预期输出：8

        // 测试用例 5
        int[] nums5 = {1, 1, 1, 1, 1, 0, 0, 0, 0, 0};
        System.out.println(solution.findMaxLength(nums5)); // 预期输出：10
    }
}

/**
Given a binary array nums, return the maximum length of a contiguous subarray 
with an equal number of 0 and 1. 

 
 Example 1: 

 
Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0
 and 1.
 

 Example 2: 

 
Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal 
number of 0 and 1.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 nums[i] is either 0 or 1. 
 

 Related Topics Array Hash Table Prefix Sum 👍 8171 👎 411

*/
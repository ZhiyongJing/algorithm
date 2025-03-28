package leetcode.question.string_list;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  228. Summary Ranges
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 71.84%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * 第一步：题目描述
 * ------------------------------------------------------------------
 * LeetCode 228 - Summary Ranges（汇总区间）
 *
 * 给定一个 **升序排列的整数数组 nums**，请你将其中的连续数字区间合并，并以字符串形式输出每个区间。
 * 要求输出格式如下：
 *   - 若区间为一个单独的数，如 [3]，输出为 "3"
 *   - 若区间为一段连续区间，如 [4,5,6]，输出为 "4->6"
 *
 * 返回所有合并后区间的列表（List<String>），按原数组中顺序排列。
 *
 * 示例：
 * 输入: [0,1,2,4,5,7]
 * 输出: ["0->2","4->5","7"]
 *
 * ------------------------------------------------------------------
 *
 * 第二步：解题思路（详细解释每一步 + 举例）
 * ------------------------------------------------------------------
 * ✅ 整体思路：
 * - 遍历数组，尝试找到每一段连续区间的起始值 start 和结束值 end
 * - 如果当前值和下一个值相差为 1，说明是连续的，继续向后查找
 * - 如果不连续了，就将当前连续段记录下来：
 *     - 如果 start == end，则输出单个数字
 *     - 如果 start ≠ end，则输出区间字符串 "start->end"
 *
 * ✅ 核心技巧：
 * - 使用 while 循环在数组中往后找，直到当前段结束
 * - 每段处理完之后，将 i 停在最后一个属于该段的元素（i++ 会在 for 循环中继续）

 * ✅ 举例说明：
 * 示例 nums = [0,1,2,4,5,7]
 *
 * i = 0, start = 0
 *   → nums[0]+1 = 1 == nums[1] → 连续
 *   → nums[1]+1 = 2 == nums[2] → 连续
 *   → nums[2]+1 = 3 ≠ nums[3] → 终止
 *   → 区间 [0->2] 加入结果
 *
 * i = 3, start = 4
 *   → nums[3]+1 = 5 == nums[4] → 连续
 *   → nums[4]+1 = 6 ≠ nums[5] → 终止
 *   → 区间 [4->5] 加入结果
 *
 * i = 5, start = 7
 *   → nums[5] 是单个数 → 直接加入 "7"
 *
 * 返回结果：["0->2", "4->5", "7"]
 *
 * ------------------------------------------------------------------
 *
 * 第三步：时间与空间复杂度分析
 * ------------------------------------------------------------------
 * 时间复杂度：O(N)
 * - 我们遍历了数组中的每个元素一次，即使内层 while 循环也最多走一遍所有元素
 *
 * 空间复杂度：O(1)（不考虑输出结果）
 * - 除了结果列表外，没有使用额外的存储结构（不改变原数组）
 * - 若考虑结果列表，则为 O(K)，K 为区间段数量
 */


public class LeetCode_228_SummaryRanges{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            // 初始化返回的结果列表
            List<String> ranges = new ArrayList<>();

            // 遍历整个数组
            for (int i = 0; i < nums.length; i++) {
                // 将当前数字设为起始值
                int start = nums[i];

                // 如果下一个元素和当前元素连续（差值为1），则继续向后遍历，寻找连续段的末尾
                while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) {
                    i++;
                }

                // 如果起始值和当前值不同，说明有一段连续区间，格式为 start->end
                if (start != nums[i]) {
                    ranges.add(start + "->" + nums[i]);
                } else {
                    // 如果相同，说明是单个数字
                    ranges.add(String.valueOf(start));
                }
            }

            // 返回所有的区间字符串
            return ranges;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_228_SummaryRanges().new Solution();

        // ✅ 测试用例 1：连续段 + 单独数字
        int[] nums1 = {0, 1, 2, 4, 5, 7};
        System.out.println(solution.summaryRanges(nums1)); // 输出: ["0->2", "4->5", "7"]

        // ✅ 测试用例 2：全是单个数
        int[] nums2 = {1, 3, 5, 7};
        System.out.println(solution.summaryRanges(nums2)); // 输出: ["1", "3", "5", "7"]

        // ✅ 测试用例 3：完全连续
        int[] nums3 = {0, 1, 2, 3, 4};
        System.out.println(solution.summaryRanges(nums3)); // 输出: ["0->4"]

        // ✅ 测试用例 4：只有一个数
        int[] nums4 = {10};
        System.out.println(solution.summaryRanges(nums4)); // 输出: ["10"]

        // ✅ 测试用例 5：空数组
        int[] nums5 = {};
        System.out.println(solution.summaryRanges(nums5)); // 输出: []
    }
}

/**
You are given a sorted unique integer array nums. 

 A range [a,b] is the set of all integers from a to b (inclusive). 

 Return the smallest sorted list of ranges that cover all the numbers in the 
array exactly. That is, each element of nums is covered by exactly one of the 
ranges, and there is no integer x such that x is in one of the ranges but not in 
nums. 

 Each range [a,b] in the list should be output as: 

 
 "a->b" if a != b 
 "a" if a == b 
 

 
 Example 1: 

 
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: The ranges are:
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"
 

 Example 2: 

 
Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: The ranges are:
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"
 

 
 Constraints: 

 
 0 <= nums.length <= 20 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 All the values of nums are unique. 
 nums is sorted in ascending order. 
 

 Related Topics Array 👍 4228 👎 2302

*/
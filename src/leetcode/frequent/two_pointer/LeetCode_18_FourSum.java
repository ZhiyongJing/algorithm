package leetcode.frequent.two_pointer;

/**
  *@Question:  18. 4Sum     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 74.85%      
  *@Time  Complexity: O(N^3)
  *@Space Complexity: O(n)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ### 解题思路
 *
 * 这道题目是 4Sum，要求找出数组中所有四个数的和等于目标值 `target` 的组合，而且不能包含重复的组合。
 *
 * 1. **排序数组：** 首先，对输入的数组进行排序，这样可以方便后续的双指针法。
 * 2. **K Sum 递归：** 使用递归的思想，将问题转化为 K 个数的和等于目标值的子问题。
 * 3. **Two Sum 双指针：** 在 K Sum 的递归中，当 K 等于 2 时，使用双指针法找到两个数的和等于目标值。
 *
 * #### K Sum 递归
 *
 * - 如果 `k == 2`，转为 Two Sum 问题，通过左右双指针寻找两个数的和等于 `target`。
 * - 如果 `k > 2`，从 `start` 开始迭代数组，递归地寻找 `k - 1` 个数的和等于 `target - nums[i]`。
 *
 * #### Two Sum 双指针
 *
 * - 定义两个指针 `lo` 和 `hi` 分别指向当前元素的后面和数组末尾。
 * - 使用 `while` 循环，循环条件是 `lo < hi`。
 * - 计算当前两个指针指向的元素之和 `currSum`。
 *   - 如果 `currSum` 小于 `target`，增加 `lo`。
 *   - 如果 `currSum` 大于 `target`，减少 `hi`。
 *   - 如果 `currSum` 等于 `target`，将结果添加到列表中，同时避免重复元素。
 *
 * ### 时间复杂度
 *
 * 排序数组的时间复杂度为 O(NlogN)，而 K Sum 递归的时间复杂度为 O(N^(k-1))，因此总体时间复杂度为 O(NlogN + N^3)。这是因为数组排序的影响相对较小。
 *
 * ### 空间复杂度
 *
 * 空间复杂度主要取决于递归调用的栈空间。由于每次递归调用都会降低 k 的值，所以递归深度最多为 k。因此，空间复杂度为 O(k)。
 */

public class LeetCode_18_FourSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            // 先对数组进行排序，方便后续双指针法的实施
            Arrays.sort(nums);
            // 调用 kSum 方法，k 为 4，即寻找四数之和等于 target 的组合
            return kSum(nums, target, 0, 4);
        }

        // kSum 方法用于寻找 k 数之和等于 target 的组合
        public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
            List<List<Integer>> res = new ArrayList<>();

            // 如果已经没有数字可添加，返回结果
            if (start == nums.length) {
                return res;
            }

            // 计算 k 个数的平均值
            long average_value = target / k;

            // 如果数组中最小值大于平均值，或者最大值小于平均值，则不可能得到和为 target 的组合
            if  (nums[start] > average_value || average_value > nums[nums.length - 1]) {
                return res;
            }

            // 如果 k 等于 2，则调用 twoSum 方法，寻找两数之和等于 target
            if (k == 2) {
                return twoSum(nums, target, start);
            }

            for (int i = start; i < nums.length; ++i) {
                // 避免重复的元素
                if (i == start || nums[i - 1] != nums[i]) {
                    // 递归调用 kSum，减小 k，寻找剩余的数之和
                    for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                        res.add(new ArrayList<>(Arrays.asList(nums[i])));
                        res.get(res.size() - 1).addAll(subset);
                    }
                }
            }

            return res;
        }

        // twoSum 方法用于寻找两数之和等于 target
        public List<List<Integer>> twoSum(int[] nums, long target, int start) {
            List<List<Integer>> res = new ArrayList<>();
            int lo = start, hi = nums.length - 1;

            while (lo < hi) {
                int currSum = nums[lo] + nums[hi];
                if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
                    ++lo;
                } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
                    --hi;
                } else {
                    res.add(Arrays.asList(nums[lo++], nums[hi--]));
                }
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_18_FourSum().new Solution();
        // TO TEST
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> result = solution.fourSum(nums, target);
        System.out.println(result);
    }
}

/**
Given an array nums of n integers, return an array of all the unique 
quadruplets [nums[a], nums[b], nums[c], nums[d]] such that: 

 
 0 <= a, b, c, d < n 
 a, b, c, and d are distinct. 
 nums[a] + nums[b] + nums[c] + nums[d] == target 
 

 You may return the answer in any order. 

 
 Example 1: 

 
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 

 Example 2: 

 
Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
 

 
 Constraints: 

 
 1 <= nums.length <= 200 
 -10⁹ <= nums[i] <= 10⁹ 
 -10⁹ <= target <= 10⁹ 
 

 Related Topics Array Two Pointers Sorting 👍 10798 👎 1303

*/

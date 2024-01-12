package leetcode.question.dfs;

import java.util.HashMap;

/**
  *@Question:  377. Combination Sum IV     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 58.47%      
  *@Time  Complexity: O(T*N) T be the target value, and N be the number of elements in the input array
  *@Space Complexity: O(T)
 */

/**
 * 这是一个动态规划的问题。让我们逐步解释算法的思路：
 *
 * ### 算法思路
 *
 * 1. **问题定义：** 给定一个包含不同整数的数组 `nums` 和一个目标整数 `target`，要求计算可以从数组中选择的数字的组合数量，
 * 使得它们的和等于 `target`。
 *
 * 2. **递归定义问题：** 问题可以通过递归来定义。设 `f(target)` 表示组合和为 `target` 的数量，那么可以通过以下公式定义：
 *     f(target) = f(target - nums[0]) + f(target - nums[1]) + ... + f(target - nums[i])
 *     其中，`nums[i]` 表示数组中的每一个元素。
 *
 * 3. **动态规划思路：** 为了避免重复计算，我们使用一个 HashMap（memoization）来存储已经计算过的结果。
 * 在每次计算 `f(target)` 时，我们首先检查 `memo` 中是否已经有了对应的结果，如果有，直接返回。
 * 否则，我们按照递归定义计算结果，并将结果存储在 `memo` 中。
 *
 * 4. **Base Case：** 当 `target` 为 0 时，表示找到一种组合方式，返回 1；当 `target` 小于 0 时，表示当前组合不成立，返回 0。
 *
 * ### 时间复杂度
 *
 * 在递归计算中，每个 `target` 对应的结果只会计算一次，并且结果被存储在 `memo` 中，以避免重复计算。因此，
 * 总的时间复杂度为 O(N * target)，其中 N 是数组 `nums` 的长度。
 *
 * ### 空间复杂度
 *
 * 空间复杂度主要取决于递归调用的深度和 `memo` 存储的结果。递归调用深度最多为 `target`，所以递归栈的空间复杂度为
 * O(target)。`memo` 存储了每个 `target` 对应的结果，因此 `memo` 的空间复杂度也为 O(target)。因此，总的空间复杂度为 O(target)。
 */


public class LeetCode_377_CombinationSumIv{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private HashMap<Integer, Integer> memo;

        /**
         * 计算组合总和的方法
         * @param nums 数组
         * @param target 目标和
         * @return 可能的组合数量
         */
        public int combinationSum4(int[] nums, int target) {
            // 小的优化
            // Arrays.sort(nums);
            memo = new HashMap<>();
            return combs(nums, target);
        }

        /**
         * 递归计算组合总和的辅助方法
         * @param nums 数组
         * @param remain 剩余目标和
         * @return 可能的组合数量
         */
        private int combs(int[] nums, int remain) {
            // 如果剩余目标为0，表示找到了一种组合方式，返回1
            if (remain == 0)
                return 1;
            // 如果已经计算过这个剩余目标的组合数量，直接返回之前计算的结果
            if (memo.containsKey(remain))
                return memo.get(remain);

            int result = 0;
            // 遍历数组中的每个数字
            for (int num : nums) {
                // 如果当前数字小于等于剩余目标，递归计算剩余目标减去当前数字的组合数量
                if (remain - num >= 0)
                    result += combs(nums, remain - num);
                // 小的优化，提前结束循环
                // else
                //     break;
            }
            // 将计算结果存储到memo中，避免重复计算
            memo.put(remain, result);
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_377_CombinationSumIv.Solution solution = new LeetCode_377_CombinationSumIv().new Solution();
        // 测试用例1
        int[] nums1 = {1, 2, 3};
        int target1 = 4;
        System.out.println("测试用例1: " + solution.combinationSum4(nums1, target1)); // 预期输出: 7

        // 测试用例2
        int[] nums2 = {9};
        int target2 = 3;
        System.out.println("测试用例2: " + solution.combinationSum4(nums2, target2)); // 预期输出: 0
    }
}
/**
Given an array of distinct integers nums and a target integer target, return 
the number of possible combinations that add up to target. 

 The test cases are generated so that the answer can fit in a 32-bit integer. 

 
 Example 1: 

 
Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
 

 Example 2: 

 
Input: nums = [9], target = 3
Output: 0
 

 
 Constraints: 

 
 1 <= nums.length <= 200 
 1 <= nums[i] <= 1000 
 All the elements of nums are unique. 
 1 <= target <= 1000 
 

 
 Follow up: What if negative numbers are allowed in the given array? How does 
it change the problem? What limitation we need to add to the question to allow 
negative numbers? 

 Related Topics Array Dynamic Programming 👍 7203 👎 641

*/

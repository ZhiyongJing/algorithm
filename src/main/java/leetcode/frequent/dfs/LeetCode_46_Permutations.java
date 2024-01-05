package leetcode.frequent.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Question: 46. Permutations
 * @Difculty: 2 [1->简单, 2->中等, 3->困难]
 * @Frequency: 76.38%
 * @Time  Complexity: O(n * n!)  // 时间复杂度为 n * n!，其中 n 为数组长度
 * @Space Complexity: O(n)      // 空间复杂度为 n，递归调用栈的深度
 */
public class LeetCode_46_Permutations {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 返回给定数组的所有可能排列
         *
         * @param nums 给定的整数数组
         * @return 所有可能的排列列表
         */
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            backtrack(new ArrayList<>(), ans, nums);
            return ans;
        }

        /**
         * 回溯算法，用于生成所有可能的排列
         *
         * @param curr 当前排列的列表
         * @param ans  存储所有可能排列的列表
         * @param nums 给定的整数数组
         */
        public void backtrack(List<Integer> curr, List<List<Integer>> ans, int[] nums) {
            // 如果当前排列长度等于数组长度，表示找到一个完整排列
            if (curr.size() == nums.length) {
                ans.add(new ArrayList<>(curr));
                return;
            }

            // 遍历数组中的每个元素
            for (int num : nums) {
                // 如果当前排列不包含该元素，才进行处理
                if (!curr.contains(num)) {
                    curr.add(num);  // 将元素添加到当前排列中
                    backtrack(curr, ans, nums);  // 递归调用，继续生成下一个位置的排列
                    curr.remove(curr.size() - 1);  // 回溯，移除最后添加的元素，尝试其他可能的排列
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_46_Permutations().new Solution();

        // 测试代码
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> result1 = solution.permute(nums1);
        System.out.println("Example 1: " + result1);

        int[] nums2 = {0, 1};
        List<List<Integer>> result2 = solution.permute(nums2);
        System.out.println("Example 2: " + result2);

        int[] nums3 = {1};
        List<List<Integer>> result3 = solution.permute(nums3);
        System.out.println("Example 3: " + result3);
    }
}

/**
Given an array nums of distinct integers, return all the possible permutations. 
You can return the answer in any order. 

 
 Example 1: 
 Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 
 Example 2: 
 Input: nums = [0,1]
Output: [[0,1],[1,0]]
 
 Example 3: 
 Input: nums = [1]
Output: [[1]]
 
 
 Constraints: 

 
 1 <= nums.length <= 6 
 -10 <= nums[i] <= 10 
 All the integers of nums are unique. 
 

 Related Topics Array Backtracking 👍 18295 👎 295

*/

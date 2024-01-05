package leetcode.frequent.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Question: 78. 子集
 * @Difculty: 2 [1->简单, 2->中等, 3->困难]
 * @Frequency: 71.47%
 * @Time  Complexity: O(n*2^n)
 * @Space Complexity: O(n)
 */

/**
 * 这个算法使用了回溯法来生成给定整数数组的所有子集。下面是算法的思路：
 *
 * 1. **回溯框架**：定义了一个`backtrack`方法，通过递归实现回溯。这个方法接受三个参数：
 *    - `first`：当前处理的起始位置。
 *    - `curr`：当前组合的列表。
 *    - `nums`：给定的整数数组。
 *
 * 2. **终止条件**：当当前组合的大小等于所需子集的大小时，将当前组合添加到结果列表中，然后返回。
 *
 * 3. **递归遍历**：使用一个循环从起始位置 `first` 开始，将数组中的元素依次添加到当前组合中，然后递归调用 `backtrack` 方法。
 *
 * 4. **回溯**：在递归调用后，需要进行回溯操作，即将最后添加的元素移除，以便尝试其他可能的组合。
 *
 * 5. **迭代所有子集大小**：在主方法`subsets`中，通过迭代子集大小（从0到n），调用`backtrack`方法，生成所有可能的子集。
 *
 * **时间复杂度**：O(n * 2^n)，其中 n 是输入数组的长度。这是因为对于每个元素，它可以选择存在或不存在，共有 2^n 种可能的子集，
 * 而每个子集的构造需要 O(n) 的时间。
 *
 * **空间复杂度**：O(n)。递归调用的栈空间最大为 n，因为每个元素都有可能在或不在当前组合中。
 *
 * 总体而言，这是一个经典的回溯算法，时间复杂度主要受到子集的数量影响，而空间复杂度则主要受到递归调用的深度（栈空间）影响。
 */
public class LeetCode_78_Subsets {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> output = new ArrayList<>();
        int n, k;

        /**
         * 回溯算法
         * @param first 当前处理的起始位置
         * @param curr 当前组合的列表
         * @param nums 给定的整数数组
         */
        public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
            // 如果组合完成
            if (curr.size() == k) {
                output.add(new ArrayList<>(curr));
                return;
            }
            for (int i = first; i < n; ++i) {
                // 将 i 添加到当前组合
                curr.add(nums[i]);

                // 使用下一个整数完成组合
                backtrack(i + 1, curr, nums);

                // 回溯
                curr.remove(curr.size() - 1);
            }
        }

        /**
         * 返回给定整数数组的所有可能子集（幂集）
         * @param nums 给定的整数数组
         * @return 所有可能的子集列表
         */
        public List<List<Integer>> subsets(int[] nums) {
            n = nums.length;
            for (k = 0; k < n + 1; ++k) {
                backtrack(0, new ArrayList<>(), nums);
            }
            return output;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_78_Subsets().new Solution();

        // 测试代码
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> result1 = solution.subsets(nums1);
        System.out.println("Example 1: " + result1);

        int[] nums2 = {0};
        List<List<Integer>> result2 = solution.subsets(nums2);
        System.out.println("Example 2: " + result2);
    }
}

/**
Given an integer array nums of unique elements, return all possible subsets (
the power set). 

 The solution set must not contain duplicate subsets. Return the solution in 
any order. 

 
 Example 1: 

 
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 

 Example 2: 

 
Input: nums = [0]
Output: [[],[0]]
 

 
 Constraints: 

 
 1 <= nums.length <= 10 
 -10 <= nums[i] <= 10 
 All the numbers of nums are unique. 
 

 Related Topics Array Backtracking Bit Manipulation 👍 16229 👎 243

*/

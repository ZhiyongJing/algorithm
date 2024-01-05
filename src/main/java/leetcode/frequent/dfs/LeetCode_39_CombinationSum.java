package leetcode.frequent.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *@Question:  39. 组合总和
 *@Difculty:  2 [1->简单, 2->中等, 3->困难]
 *@Frequency: 65.5%
 *@Time  Complexity: O(N(T/M+1))，其中N是候选者的数量，T是目标值，M是候选者中的最小值
 *@Space Complexity: O(T/M)
 */

/**
 * **算法思路：**
 *
 * 这是一个使用回溯算法解决组合总和问题的示例。回溯算法的核心思想是通过尝试不同的选择，逐步构建出问题的解。在这个问题中，
 * 我们需要找到所有和为目标值的组合，可以重复使用候选数组中的元素。
 *
 * 具体步骤如下：
 * 1. 使用回溯算法，定义一个函数 `backtrack`，该函数接收当前还需要满足的目标值 `remain`、当前组合 `comb`、
 * 当前开始尝试的索引 `start`、候选者数组 `candidates`、以及存放结果的集合 `results`。
 * 2. 如果 `remain` 等于 0，说明当前组合满足条件，将其加入结果集。
 * 3. 如果 `remain` 小于 0，说明当前组合超出目标值，停止继续探索。
 * 4. 遍历候选者数组，从索引 `start` 开始，依次尝试将每个数字加入组合，并递归调用 `backtrack`，继续尝试下一步。
 * 5. 在递归调用后，需要进行回溯，即将组合中的最后一个数字移除，以便尝试其他可能性。
 *
 * **时间复杂度：**
 *
 * 假设数组中有 N 个元素，目标值为 T。在最坏情况下，每个数字都可能被选择多次，因此时间复杂度为 O(N^(T/M+1))，
 * 其中 M 为候选者数组中的最小值。这是因为在每个递归步骤中，都有 T/M 种可能性。
 *
 * **空间复杂度：**
 *
 * 空间复杂度主要取决于递归调用的深度，即组合的长度。在最坏情况下，组合的长度为 T/M，因此空间复杂度为 O(T/M)。
 * 这是因为在递归调用过程中，使用了一个辅助的链表（`comb`）来存储当前组合。
 */

public class LeetCode_39_CombinationSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 回溯算法的具体实现
         *
         * @param remain      当前还需要满足的目标值
         * @param comb        当前组合
         * @param start       当前开始尝试的索引
         * @param candidates  候选者数组
         * @param results     结果集合
         */
        protected void backtrack(
                int remain,
                LinkedList<Integer> comb,
                int start, int[] candidates,
                List<List<Integer>> results) {

            if (remain == 0) {
                // 当前组合满足条件，将其加入结果集
                results.add(new ArrayList<>(comb));
                return;
            } else if (remain < 0) {
                // 当前组合超出目标值，停止继续探索
                return;
            }

            for (int i = start; i < candidates.length; ++i) {
                // 将当前数字加入组合
                comb.add(candidates[i]);
                // 递归尝试下一步
                this.backtrack(remain - candidates[i], comb, i, candidates, results);
                // 回溯，移除组合中的最后一个数字
                comb.removeLast();
            }
        }

        /**
         * 主函数，用于调用回溯算法
         *
         * @param candidates 候选者数组
         * @param target     目标值
         * @return           返回所有满足条件的组合集合
         */
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> results = new ArrayList<>();
            LinkedList<Integer> comb = new LinkedList<>();

            this.backtrack(target, comb, 0, candidates, results);
            return results;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_39_CombinationSum().new Solution();
        // 测试用例1
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        List<List<Integer>> result1 = solution.combinationSum(candidates1, target1);
        System.out.println("测试用例1结果：" + result1);

        // 测试用例2
        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        List<List<Integer>> result2 = solution.combinationSum(candidates2, target2);
        System.out.println("测试用例2结果：" + result2);

        // 测试用例3
        int[] candidates3 = {2};
        int target3 = 1;
        List<List<Integer>> result3 = solution.combinationSum(candidates3, target3);
        System.out.println("测试用例3结果：" + result3);
    }
}

/**
Given an array of distinct integers candidates and a target integer target, 
return a list of all unique combinations of candidates where the chosen numbers sum 
to target. You may return the combinations in any order. 

 The same number may be chosen from candidates an unlimited number of times. 
Two combinations are unique if the frequency of at least one of the chosen numbers 
is different. 

 The test cases are generated such that the number of unique combinations that 
sum up to target is less than 150 combinations for the given input. 

 
 Example 1: 

 
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple 
times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
 

 Example 2: 

 
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
 

 Example 3: 

 
Input: candidates = [2], target = 1
Output: []
 

 
 Constraints: 

 
 1 <= candidates.length <= 30 
 2 <= candidates[i] <= 40 
 All elements of candidates are distinct. 
 1 <= target <= 40 
 

 Related Topics Array Backtracking 👍 17964 👎 372

*/

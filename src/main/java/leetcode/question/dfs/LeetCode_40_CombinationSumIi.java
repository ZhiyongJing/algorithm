package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Question:  40. Combination Sum II
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 40.97%
 *@Time  Complexity: O(2^N)
 *@Space Complexity: O(N)
 */

/**
 * 好的，下面是对题目 "40. Combination Sum II" 的详细解题思路以及时间和空间复杂度分析。
 *
 * ### 题目描述
 * 给定一个数组 `candidates` 和一个目标数 `target`，找出 `candidates` 中所有可以使数字和为 `target` 的组合。`candidates` 中的每个数字在每个组合中只能使用一次。
 *
 * ### 解题思路
 *
 * 我们使用回溯算法来解决这个问题。回溯算法通过递归构建候选解决方案，并在发现不满足条件时“回退”到上一步。
 *
 * #### 步骤
 *
 * 1. **排序数组**：首先对 `candidates` 数组进行排序。排序的目的是为了后续去重和优化搜索过程。
 * 2. **初始化变量**：创建一个列表 `results` 用来存储所有满足条件的组合；创建一个链表 `comb` 用来存储当前组合。
 * 3. **定义回溯函数**：编写 `backtrack` 函数，用来生成所有可能的组合：
 *    - **终止条件**：如果 `remain`（剩余目标值）为0，说明当前组合满足条件，将其加入 `results`。
 *    - **遍历候选数组**：从当前索引 `curr` 开始遍历 `candidates` 数组。
 *      - **跳过重复元素**：如果当前元素和前一个元素相同，跳过当前元素，以避免生成重复的组合。
 *      - **提前终止**：如果当前元素大于 `remain`，可以直接停止循环，因为后面的元素也会大于 `remain`。
 *      - **选择当前元素**：将当前元素加入 `comb`，并递归调用 `backtrack` 函数，将 `remain` 减去当前元素，更新当前索引。
 *      - **回退**：递归调用结束后，从 `comb` 中移除当前元素，进行下一次选择。
 *
 * ### 时间和空间复杂度分析
 *
 * #### 时间复杂度
 * - **最坏情况下**：回溯算法会遍历所有可能的组合。设 `n` 为数组 `candidates` 的长度，组合的总数为 `O(2^n)`。
 * 但由于我们进行了剪枝操作（如提前终止和跳过重复元素），实际复杂度会小于 `O(2^n)`。
 * - **排序**：`Arrays.sort(candidates)` 的时间复杂度为 `O(n log n)`。
 *
 * 综合考虑，时间复杂度为 `O(2^n * n)`。
 *
 * #### 空间复杂度
 * - **递归调用栈**：递归深度最多为 `n`（数组长度），每层递归调用会存储当前状态。
 * - **结果列表**：结果列表 `results` 可能会包含大量组合，每个组合的长度最多为 `n`。
 *
 * 综合考虑，空间复杂度为 `O(n * k)`，其中 `k` 是所有组合的总数。
 *
 * 这个解题思路通过排序、回溯和剪枝操作，高效地找到所有满足条件的组合，并避免重复组合的产生。
 */
public class LeetCode_40_CombinationSumIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> results = new ArrayList<>(); // 初始化结果列表
            List<Integer> comb = new ArrayList<>(); // 初始化当前组合列表

            Arrays.sort(candidates); // 将候选数组排序

            backtrack(candidates, comb, target, 0, results); // 开始回溯算法
            return results; // 返回所有满足条件的组合
        }

        private void backtrack(
                int[] candidates, // 候选数组
                List<Integer> comb, // 当前组合
                Integer remain, // 剩余目标值
                Integer curr, // 当前索引
                List<List<Integer>> results // 结果列表
        ) {
            if (remain == 0) { // 如果剩余值为0，表示找到一个满足条件的组合
                results.add(new ArrayList<>(comb)); // 将当前组合加入结果列表
                return; // 结束当前递归
            }

            for (int nextCurr = curr; nextCurr < candidates.length; ++nextCurr) { // 遍历候选数组
                if (nextCurr > curr && candidates[nextCurr] == candidates[nextCurr - 1]) continue; // 跳过重复的元素

                Integer pick = candidates[nextCurr]; // 当前选择的元素
                if (remain - pick < 0) break; // 如果当前选择的元素超过剩余值，停止循环

                comb.add(pick); // 将当前选择的元素加入当前组合
                backtrack(candidates, comb, remain - pick, nextCurr + 1, results); // 递归调用回溯算法
                comb.remove(comb.size() - 1); // 从当前组合中移除最后一个元素，准备下一个选择
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_40_CombinationSumIi().new Solution();

        // 测试用例1
        int[] candidates1 = {10,1,2,7,6,1,5};
        int target1 = 8;
        System.out.println(solution.combinationSum2(candidates1, target1));

        // 测试用例2
        int[] candidates2 = {2,5,2,1,2};
        int target2 = 5;
        System.out.println(solution.combinationSum2(candidates2, target2));
    }
}

/**
Given a collection of candidate numbers (candidates) and a target number (
target), find all unique combinations in candidates where the candidate numbers sum 
to target. 

 Each number in candidates may only be used once in the combination. 

 Note: The solution set must not contain duplicate combinations. 

 
 Example 1: 

 
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
 

 Example 2: 

 
Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
 

 
 Constraints: 

 
 1 <= candidates.length <= 100 
 1 <= candidates[i] <= 50 
 1 <= target <= 30 
 

 Related Topics Array Backtracking 👍 10361 👎 292

*/
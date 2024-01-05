package leetcode.frequent.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Question: 77. Combinations
 * @Difculty: 2 [1->简单, 2->中等, 3->困难]
 * @Frequency: 68.72%
 * @Time  Complexity: O(C(n,k))  // 时间复杂度为组合数 C(n,k)，最坏情况下为 O(n choose k)
 * @Space Complexity: O(k)      // 空间复杂度为 k，递归调用栈的深度
 */

/**
 * 算法思路：
 *
 * 该算法采用回溯法，通过递归实现。在每一步递归中，从当前数字开始，依次尝试选择数字，然后递归调用自身，
 * 直到选择的数字个数达到了目标值 `k`，将当前选择的组合加入结果列表中。在每一步递归中，采用循环遍历的方式选择下一个数字，
 * 并在递归调用前后进行回溯操作。
 *
 * 时间复杂度：
 *
 * 在最坏的情况下，算法需要生成组合数 `C(n, k)`，其中 n 为上限，k 为选择的数字个数。因此，时间复杂度为 O(C(n, k))，
 * 最坏情况下为 O(n choose k)。
 *
 * 空间复杂度：
 *
 * 空间复杂度主要由递归调用栈的深度决定，即回溯时的最大深度。在每一层递归中，都会生成一个组合，并在最后加入结果列表。
 * 因此，空间复杂度为 O(k)，其中 k 为选择的数字个数。
 */
public class LeetCode_77_Combinations {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 返回从范围 [1, n] 中选择 k 个数字的所有可能组合
         *
         * @param n 范围的上限
         * @param k 选择的数字个数
         * @return 所有可能的组合列表
         */
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(new ArrayList<>(), 1, n, k, result);
            return result;
        }

        /**
         * 回溯算法，用于生成所有可能的组合
         *
         * @param curr      当前组合的列表
         * @param firstNum  当前处理的起始数字
         * @param n         范围的上限
         * @param k         选择的数字个数
         * @param ans       存储所有可能组合的列表
         */
        public void backtrack(List<Integer> curr, int firstNum, int n, int k, List<List<Integer>> ans) {
            // 如果当前组合长度等于选择的数字个数，表示找到一个完整组合
            if (curr.size() == k) {
                ans.add(new ArrayList<>(curr));
                return;
            }

            // 遍历 [firstNum, n] 范围内的数字
            for (int num = firstNum; num <= n; num++) {
                curr.add(num);  // 将数字添加到当前组合中
                backtrack(curr, num + 1, n, k, ans);  // 递归调用，继续生成下一个位置的组合
                curr.remove(curr.size() - 1);  // 回溯，移除最后添加的数字，尝试其他可能的组合
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_77_Combinations().new Solution();

        // 测试代码
        int n1 = 4, k1 = 2;
        List<List<Integer>> result1 = solution.combine(n1, k1);
        System.out.println("Example 1: " + result1);

        int n2 = 1, k2 = 1;
        List<List<Integer>> result2 = solution.combine(n2, k2);
        System.out.println("Example 2: " + result2);
    }
}

/**
Given two integers n and k, return all possible combinations of k numbers 
chosen from the range [1, n]. 

 You may return the answer in any order. 

 
 Example 1: 

 
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to 
be the same combination.
 

 Example 2: 

 
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.
 

 
 Constraints: 

 
 1 <= n <= 20 
 1 <= k <= n 
 

 Related Topics Backtracking 👍 7903 👎 206

*/

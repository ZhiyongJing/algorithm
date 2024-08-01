package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *@Question:  216. Combination Sum III
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O((9! * K)/(9-k)!
 *@Space Complexity: O(K)
 */

/**
 * ### 解题思路
 *
 * 题目要求找到所有由1到9组成的k个数的组合，使得这些数的和等于n，并且每个数只能使用一次。我们可以用回溯算法来解决这个问题。
 *
 * 1. **初始化**:
 *    - 使用一个数组`jumps`来存储每个数字可能跳到的下一个数字，防止每次计算的时候重新遍历。
 *    - 使用`remain`表示剩余需要达到的目标和。
 *    - 使用`k`表示还需要选择的数字个数。
 *    - 使用`comb`存储当前的组合。
 *    - 使用`results`存储所有可能的组合。
 *
 * 2. **回溯函数`backtrack`**:
 *    - 终止条件:
 *      - 当`remain`为0且`comb.size()`等于`k`时，找到一个有效组合，加入结果集。
 *      - 当`remain`小于0或`comb.size()`等于`k`时，终止当前路径，因为已超出限制。
 *    - 递归探索:
 *      - 从当前起始位置`next_start`到9的范围内遍历，每次选择一个数字，将其加入当前组合`comb`，然后递归调用`backtrack`函数，更新剩余和`remain`、起始位置`next_start`。
 *      - 回溯: 在递归返回后，将当前数字从组合中移除，继续探索下一个可能的组合。
 *
 * ### 时间复杂度
 *
 * - 在最坏情况下，所有的组合情况都要被遍历。对于每个组合长度k，有`C(9, k)`种组合方式，每次递归调用的深度为`k`。因此，时间复杂度可以表示为`O(C(9, k) * k)`。考虑到`k`的最大值为9，我们可以将时间复杂度简化为`O(2^9)`，因为这是所有组合数的总和。
 *
 * ### 空间复杂度
 *
 * - 递归的最大深度为`k`，即递归栈的最大空间为`O(k)`。
 * - 额外使用的空间主要是`comb`和`results`的空间消耗。`comb`最大为`k`，`results`则最多为`C(9, k)`个组合，每个组合最多有`k`个数字。
 *
 * 综合考虑，空间复杂度为`O(C(9, k) + k)`，最坏情况下的空间复杂度为`O(2^9)`。
 */

public class LeetCode_216_CombinationSumIii{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 回溯算法的辅助函数
        protected void backtrack(
                int remain, // 剩余的目标和
                int k, // 需要的数字个数
                LinkedList<Integer> comb, // 当前的组合
                int next_start, // 下一个开始的数字
                List<List<Integer>> results // 保存所有可能组合的结果列表
        ) {
            if (remain == 0 && comb.size() == k) {
                // 如果剩余和为0且组合中的数字个数为k，说明找到一个满足条件的组合
                results.add(new ArrayList<Integer>(comb)); // 深拷贝当前的组合到结果列表
                return;
            } else if (remain < 0 || comb.size() == k) {
                // 如果剩余和小于0或者组合中的数字个数已达到k个，则不需要再继续探索
                return;
            }

            // 从下一个开始的数字到9（因为数字只能是1到9）
            for (int i = next_start; i < 9; ++i) {
                comb.add(i + 1); // 将数字加入当前组合
                // 递归调用，更新剩余和，数字个数保持不变，数字起始位置后移
                this.backtrack(remain - i - 1, k, comb, i + 1, results);
                comb.removeLast(); // 回溯，移除当前数字
            }
        }

        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> results = new ArrayList<List<Integer>>(); // 存储所有结果的列表
            LinkedList<Integer> comb = new LinkedList<Integer>(); // 当前组合的列表

            this.backtrack(n, k, comb, 0, results); // 开始回溯算法
            return results;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_216_CombinationSumIii().new Solution();

        // 测试用例
        System.out.println(solution.combinationSum3(3, 7)); // 输出: [[1,2,4]]
        System.out.println(solution.combinationSum3(3, 9)); // 输出: [[1,2,6], [1,3,5], [2,3,4]]
        System.out.println(solution.combinationSum3(4, 1)); // 输出: []
    }
}

/**
Find all valid combinations of k numbers that sum up to n such that the 
following conditions are true: 

 
 Only numbers 1 through 9 are used. 
 Each number is used at most once. 
 

 Return a list of all possible valid combinations. The list must not contain 
the same combination twice, and the combinations may be returned in any order. 

 
 Example 1: 

 
Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations. 

 Example 2: 

 
Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
 

 Example 3: 

 
Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2
+3+4 = 10 and since 10 > 1, there are no valid combination.
 

 
 Constraints: 

 
 2 <= k <= 9 
 1 <= n <= 60 
 

 Related Topics Array Backtracking 👍 5988 👎 112

*/
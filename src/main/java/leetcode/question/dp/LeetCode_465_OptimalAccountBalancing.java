package leetcode.question.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Question:  465. Optimal Account Balancing
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 80.39%
 *@Time  Complexity: O(N * 2^N)
 *@Space Complexity: O(2^N)
 */

/**
 * 好的，下面是详细的解题思路以及时间和空间复杂度的分析：
 *
 * ### 解题思路
 *
 * 1. **交易净值计算**：
 *     - 我们需要计算每个人在所有交易后的净收入/支出。创建一个哈希映射 `creditMap`，键为每个人的ID，值为他的净收入/支出。
 *     - 遍历每个交易，对于每个交易 `transactions[i] = [giver, receiver, amount]`，我们更新 `giver` 和 `receiver` 的净收入/支出。具体来说，`giver` 的净收入减少 `amount`，`receiver` 的净收入增加 `amount`。
 *
 * 2. **净值列表**：
 *     - 我们只关心净收入/支出不为0的人。将所有净值不为0的值存入列表 `creditList`。
 *
 * 3. **状态压缩和记忆化搜索**：
 *     - 使用状态压缩来表示所有可能的净值组合。设 `n` 为 `creditList` 的长度，则有 `2^n` 个子集。
 *     - 创建一个长度为 `2^n` 的数组 `memo`，用于记忆化存储每个子集的最小交易次数。`memo[mask]` 表示由二进制 `mask` 表示的子集的最小交易次数。
 *     - 初始状态：`memo[0] = 0`，表示空集的交易次数为0。
 *
 * 4. **深度优先搜索 (DFS)**：
 *     - 使用DFS来遍历所有可能的子集组合，计算每个子集的最小交易次数。
 *     - 对于当前子集 `totalMask`，我们枚举子集中的每一个人（即遍历每个二进制位为1的人），递归计算去掉当前人的子集的最小交易次数。
 *     - 计算当前子集的净收入/支出总和 `balanceSum`，如果 `balanceSum == 0`，则当前子集可以通过一次交易平衡，将 `answer` 增加1。
 *
 * 5. **返回结果**：
 *     - 我们的目标是找到最小的交易次数，即 `n - dfs((1 << n) - 1, memo)`。
 *
 * ### 时间和空间复杂度
 *
 * **时间复杂度**：
 * - 由于使用状态压缩来表示所有可能的净值组合，共有 `2^n` 个子集，每个子集需要进行DFS遍历，最坏情况下每次递归调用遍历 `n` 个元素。因此，时间复杂度为 `O(n * 2^n)`。
 *
 * **空间复杂度**：
 * - 主要空间开销在于 `memo` 数组，其大小为 `2^n`。此外，`creditList` 的长度为 `n`。因此，空间复杂度为 `O(2^n + n)`。
 *
 * ### 总结
 *
 * 通过计算每个人的净收入/支出，使用状态压缩和记忆化搜索，我们能够高效地找到最小的交易次数来平衡所有账户。时间复杂度和空间复杂度都是指数级的，适合处理人数不多的情况。
 */

public class LeetCode_465_OptimalAccountBalancing{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minTransfers(int[][] transactions) {
            // 创建一个哈希映射来存储每个人的净收入/支出
            Map<Integer, Integer> creditMap = new HashMap<>();

            // 遍历所有交易，计算每个人的净收入/支出
            for (int[] t : transactions) {
                // t[0]给t[1]转账t[2]，所以t[0]减少t[2]，t[1]增加t[2]
                creditMap.put(t[0], creditMap.getOrDefault(t[0], 0) + t[2]);
                creditMap.put(t[1], creditMap.getOrDefault(t[1], 0) - t[2]);
            }

            // 将所有非零的净收入/支出存入列表
            creditList = new ArrayList<>();
            for (int amount : creditMap.values()) {
                if (amount != 0) {
                    creditList.add(amount);
                }
            }

            int n = creditList.size();
            // 创建一个记忆化数组，用于存储每个子集的最小交易次数
            int[] memo = new int[1 << n];
            // 初始化所有值为-1，表示未计算
            Arrays.fill(memo, -1);
            // 空集的交易次数为0
            memo[0] = 0;
            // 调用DFS方法计算最小交易次数
            return n - dfs((1 << n) - 1, memo);
        }

        List<Integer> creditList;
        private int dfs(int totalMask, int[] memo) {
            // 如果当前子集的最小交易次数已经计算过，直接返回
            if (memo[totalMask] != -1) {
                return memo[totalMask];
            }

            int balanceSum = 0, answer = 0;

            // 枚举子集中的每一个人
            for (int i = 0; i < creditList.size(); i++) {
                int curBit = 1 << i;
                // 如果当前人在子集中
                if ((totalMask & curBit) != 0) {
                    // 加上当前人的净收入/支出
                    balanceSum += creditList.get(i);
                    // 递归计算去掉当前人的子集的最小交易次数
                    answer = Math.max(answer, dfs(totalMask ^ curBit, memo));
                }
            }

            // 如果当前子集的净收入/支出总和为0，则可以通过一次交易平衡
            memo[totalMask] = answer + (balanceSum == 0 ? 1 : 0);
            return memo[totalMask];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_465_OptimalAccountBalancing().new Solution();
        // 测试样例
        int[][] transactions = {
                {0, 1, 10},
                {2, 0, 5}
        };
        // 预期输出：2
        System.out.println(solution.minTransfers(transactions));

        int[][] transactions2 = {
                {0, 1, 10},
                {1, 0, 1},
                {1, 2, 5},
                {2, 0, 5}
        };
        // 预期输出：1
        System.out.println(solution.minTransfers(transactions2));
    }
}

/**
You are given an array of transactions transactions where transactions[i] = [
fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to 
the person with ID = toi. 

 Return the minimum number of transactions required to settle the debt. 

 
 Example 1: 

 
Input: transactions = [[0,1,10],[2,0,5]]
Output: 2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays 
person #0 and #2 $5 each.
 

 Example 2: 

 
Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
Output: 1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 

 
 Constraints: 

 
 1 <= transactions.length <= 8 
 transactions[i].length == 3 
 0 <= fromi, toi < 12 
 fromi != toi 
 1 <= amounti <= 100 
 

 Related Topics Array Dynamic Programming Backtracking Bit Manipulation Bitmask 
👍 1456 👎 151

*/
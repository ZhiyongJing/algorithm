package leetcode.question.dfs;

import java.util.Arrays;
import java.util.HashMap;

/**
  *@Question:  403. Frog Jump     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 91.34%      
  *@Time  Complexity: O(n^2)
  *@Space Complexity: O(n^2)
 */

/**
 * **算法思路：**
 *
 * 这道题目要求判断青蛙是否能够成功跳到最后一块石头。青蛙在每一步可以选择跳跃的距离，规则是上一次跳跃了 `k` 个单位，
 * 下一次可以选择跳跃 `k-1`、`k`、`k+1` 个单位。基于这个规则，我们可以通过深度优先搜索（DFS）来逐步探索每一种可能的跳跃方案。
 *
 * 使用 HashMap 将每块石头的位置映射为索引，以便快速查找。DFS 函数接收当前的石头索引、上一次的跳跃距离、以及数组 `stones` 和 DP 数组，
 * 判断是否能够成功跳到最后一块石头。在 DFS 中，我们遍历三种可能的跳跃距离 `k-1`、`k`、`k+1`，如果下一块石头存在于数组 `stones` 中，
 * 我们就递归调用 DFS 函数。在递归调用过程中，我们标记已经访问过的状态，以避免重复计算。
 *
 * 为了避免重复计算，我们使用一个 DP 数组来存储中间结果，将 `dp[i][j]` 标记为在石头 `i` 处，上一次跳跃距离为 `j` 时的结果。
 * 如果成功到达最后一块石头，我们返回 `true`，否则返回 `false`。
 *
 * **时间复杂度：**
 * 在最坏的情况下，我们需要考虑每一块石头，对于每一块石头，我们需要遍历上一次的跳跃距离 `k-1`、`k`、`k+1`。
 * 因此，总的时间复杂度为 O(n * m^3)，其中 n 是石头的数量，m 是数组中最长字符串的长度。
 *
 * **空间复杂度：**
 * 空间复杂度取决于递归调用的深度，以及 DP 数组的大小。递归深度为 O(n)（石头的数量），DP 数组大小为 O(n * m)（
 * n 是石头的数量，m 是数组中最长字符串的长度）。因此，总的空间复杂度为 O(n * m^2^)
 */

public class LeetCode_403_FrogJump {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        HashMap<Integer, Integer> mark = new HashMap<>();
        int dp[][] = new int[2001][2001];

        // 判断是否能跳到最后一块石头的主函数
        boolean solve(int[] stones, int n, int index, int prevJump) {
            // 如果已经到达最后一块石头，返回true
            if (index == n - 1) {
                return true;
            }

            // 如果这个子问题已经计算过，直接返回结果
            if (dp[index][prevJump] != -1) {
                return dp[index][prevJump] == 1;
            }

            boolean ans = false;
            // 遍历三种可能的跳跃距离 {k - 1, k, k + 1}
            for (int nextJump = prevJump - 1; nextJump <= prevJump + 1; nextJump++) {
                if (nextJump > 0 && mark.containsKey(stones[index] + nextJump)) {
                    ans = ans || solve(stones, n, mark.get(stones[index] + nextJump), nextJump);
                }
            }

            // 存储计算结果以备后用
            dp[index][prevJump] = (ans ? 1 : 0);
            return ans;
        }

        public boolean canCross(int[] stones) {
            // 使用HashMap标记石头位置，方便查找
            for (int i = 0; i < stones.length; i++) {
                mark.put(stones[i], i);
            }

            // 将所有状态初始化为-1，表示尚未计算过
            for (int i = 0; i < 2000; i++) {
                Arrays.fill(dp[i], -1);
            }

            // 调用solve函数判断是否能跳到最后一块石头
            return solve(stones, stones.length, 0, 0);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_403_FrogJump().new Solution();
        // 测试
        System.out.println(solution.canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17})); // 应返回 true
        System.out.println(solution.canCross(new int[]{0, 1, 2, 3, 4, 8, 9, 11})); // 应返回 false
    }
}

/**
A frog is crossing a river. The river is divided into some number of units, and 
at each unit, there may or may not exist a stone. The frog can jump on a stone, 
but it must not jump into the water. 

 Given a list of stones positions (in units) in sorted ascending order, 
determine if the frog can cross the river by landing on the last stone. Initially, the 
frog is on the first stone and assumes the first jump must be 1 unit. 

 If the frog's last jump was k units, its next jump must be either k - 1, k, or 
k + 1 units. The frog can only jump in the forward direction. 

 
 Example 1: 

 
Input: stones = [0,1,3,5,6,8,12,17]
Output: true
Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd 
stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units 
to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
 

 Example 2: 

 
Input: stones = [0,1,2,3,4,8,9,11]
Output: false
Explanation: There is no way to jump to the last stone as the gap between the 5
th and 6th stone is too large.
 

 
 Constraints: 

 
 2 <= stones.length <= 2000 
 0 <= stones[i] <= 2³¹ - 1 
 stones[0] == 0 
 stones is sorted in a strictly increasing order. 
 

 Related Topics Array Dynamic Programming 👍 5395 👎 244

*/

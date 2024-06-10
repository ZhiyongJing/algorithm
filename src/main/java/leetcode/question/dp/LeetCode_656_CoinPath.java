package leetcode.question.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Question:  656. Coin Path
 *@Difficulty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N * B) array of size n is filled only once. We also do a traversal over the next array,
 * which will go upto  B steps. Here, nnn refers to the number of nodes in the given tree.
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 题目要求在一个数组`A`中找到一个从第一个元素到最后一个元素的路径，使得路径上的数字和最小，并且每一步最多可以向前跳跃`B`步。
 *
 * #### 解法1：自顶向下（递归 + 记忆化）
 *
 * 1. **递归定义**：定义一个递归函数`jump(A, B, i, next, memo)`，表示从索引`i`开始跳到数组末尾的最小花费。
 * 2. **记忆化**：使用`memo`数组存储每个索引的最小花费，避免重复计算。
 * 3. **递归基**：如果当前索引`i`是最后一个元素且其值非负，则返回该值作为花费。
 * 4. **状态转移**：尝试从当前索引`i`向前跳跃最多`B`步，计算每个可跳跃位置的花费，如果该位置非负，则更新最小花费和下一步跳跃的位置。
 * 5. **路径构建**：使用`next`数组记录从每个索引跳跃的下一个位置，根据该数组构建最终的最小花费路径。
 *
 * #### 解法2：自底向上（动态规划）
 *
 * 1. **定义状态**：定义`dp[i]`为从索引`i`到数组末尾的最小花费。
 * 2. **初始化**：从倒数第二个位置开始向前计算，`next`数组记录从每个索引跳跃的下一个位置。
 * 3. **状态转移**：对于每个位置`i`，尝试向前跳跃最多`B`步，计算每个可跳跃位置的花费，如果该位置非负，则更新最小花费和下一步跳跃的位置。
 * 4. **路径构建**：根据`next`数组构建最终的最小花费路径。
 *
 * ### 时间复杂度
 *
 * 两种解法的时间复杂度都是 (O(n  B))：
 * - 对于每个元素，我们最多向前跳跃`B`步，计算每一步的最小花费。
 * - `n` 是数组 `A` 的长度。
 *
 * ### 空间复杂度
 *
 * 两种解法的空间复杂度都是 (O(n))：
 * - 需要`next`数组记录每个索引的下一步跳跃位置，大小为`n`。
 * - 需要`memo`或`dp`数组记录每个索引的最小花费，大小为`n`。
 */

public class LeetCode_656_CoinPath{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // 解法1：自顶向下
        public List <Integer> cheapestJump1(int[] A, int B) {
            int[] next = new int[A.length]; // 记录下一步跳到的索引
            Arrays.fill(next, -1); // 初始化为-1
            long[] memo = new long[A.length]; // 记忆化数组，存储每一步的最小花费
            jump(A, B, 0, next, memo); // 从索引0开始递归求解
            List<Integer> res = new ArrayList<>();
            int i;
            for (i = 0; i < A.length && next[i] > 0; i = next[i]) // 根据next数组构建路径
                res.add(i + 1); // 索引转换为1-based
            if (i == A.length - 1 && A[i] >= 0) // 如果达到末尾且值非负，添加最后一个索引
                res.add(A.length);
            else
                return new ArrayList<Integer>(); // 否则返回空列表
            return res;
        }

        // 递归函数，求解从索引i开始到终点的最小花费
        public long jump(int[] A, int B, int i, int[] next, long[] memo) {
            if (memo[i] > 0) // 如果已经计算过，直接返回
                return memo[i];
            if (i == A.length - 1 && A[i] >= 0) // 如果到达终点且值非负，返回当前值
                return A[i];
            long min_cost = Integer.MAX_VALUE; // 初始化最小花费为最大值
            for (int j = i + 1; j <= i + B && j < A.length; j++) { // 从i向前跳B步以内
                if (A[j] >= 0) { // 如果该位置值非负
                    long cost = A[i] + jump(A, B, j, next, memo); // 计算跳到该位置的花费
                    if (cost < min_cost) { // 更新最小花费和下一步的位置
                        min_cost = cost;
                        next[i] = j;
                    }
                }
            }
            memo[i] = min_cost; // 记忆化存储当前索引的最小花费
            return min_cost;
        }

        // 解法2：自底向上动态规划
        public List <Integer> cheapestJump(int[] A, int B) {
            int[] next = new int[A.length]; // 记录下一步跳到的索引
            long[] dp = new long[A.length]; // dp数组，存储每一步的最小花费
            Arrays.fill(next, -1); // 初始化为-1
            List<Integer> res = new ArrayList<>();
            for (int i = A.length - 2; i >= 0; i--) { // 从倒数第二个位置向前计算
                long min_cost = Integer.MAX_VALUE; // 初始化最小花费为最大值
                for (int j = i + 1; j <= i + B && j < A.length; j++) { // 从i向前跳B步以内
                    if (A[j] >= 0) { // 如果该位置值非负
                        long cost = A[i] + dp[j]; // 计算跳到该位置的花费
                        if (cost < min_cost) { // 更新最小花费和下一步的位置
                            min_cost = cost;
                            next[i] = j;
                        }
                    }
                }
                dp[i] = min_cost; // 存储当前索引的最小花费
            }
            int i;
            for (i = 0; i < A.length && next[i] > 0; i = next[i]) // 根据next数组构建路径
                res.add(i + 1); // 索引转换为1-based
            if (i == A.length - 1 && A[i] >= 0) // 如果达到末尾且值非负，添加最后一个索引
                res.add(A.length);
            else
                return new ArrayList<Integer>(); // 否则返回空列表
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_656_CoinPath().new Solution();

        // 测试样例
        int[] A1 = {1, 2, 4, -1, 2};
        int B1 = 2;
        System.out.println(solution.cheapestJump(A1, B1)); // 输出: [1, 3, 5]

        int[] A2 = {1, 2, 4, -1, 2};
        int B2 = 1;
        System.out.println(solution.cheapestJump(A2, B2)); // 输出: []
    }
}

/**
You are given an integer array coins (1-indexed) of length n and an integer 
maxJump. You can jump to any index i of the array coins if coins[i] != -1 and you 
have to pay coins[i] when you visit index i. In addition to that, if you are 
currently at index i, you can only jump to any index i + k where i + k <= n and k is 
a value in the range [1, maxJump]. 

 You are initially positioned at index 1 (coins[1] is not -1). You want to find 
the path that reaches index n with the minimum cost. 

 Return an integer array of the indices that you will visit in order so that 
you can reach index n with the minimum cost. If there are multiple paths with the 
same cost, return the lexicographically smallest such path. If it is not 
possible to reach index n, return an empty array. 

 A path p1 = [Pa1, Pa2, ..., Pax] of length x is lexicographically smaller than 
p2 = [Pb1, Pb2, ..., Pbx] of length y, if and only if at the first j where Paj 
and Pbj differ, Paj < Pbj; when no such j exists, then x < y. 

 
 Example 1: 
 Input: coins = [1,2,4,-1,2], maxJump = 2
Output: [1,3,5]
 
 Example 2: 
 Input: coins = [1,2,4,-1,2], maxJump = 1
Output: []
 
 
 Constraints: 

 
 1 <= coins.length <= 1000 
 -1 <= coins[i] <= 100 
 coins[1] != -1 
 1 <= maxJump <= 100 
 

 Related Topics Array Dynamic Programming 👍 246 👎 110

*/
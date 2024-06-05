package leetcode.question.dp;

import java.util.Arrays;

/**
 *@Question:  279. Perfect Squares
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 82.97%
 *@Time  Complexity: O(n*sqrt(n))
 *@Space Complexity: O(n)
 */

/**
 * 这道题目是经典的动态规划问题，称为 "完全平方数"。给定一个正整数 n，找到最少数量的完全平方数
 * （例如，1、4、9、16等），它们的和等于 n。
 *
 * 解题思路如下：
 *
 * 1. 创建一个长度为 n+1 的数组 dp，用来存储到达每个数字的最小完全平方数数量。
 *
 * 2. 初始化 dp[0] 为 0，因为 0 不需要任何完全平方数来组成。
 *
 * 3. 然后，对于每个数字 i，遍历 1 到 sqrt(i)（包括），找到所有小于等于 i 的完全平方数。对于每个完全平方数，
 * 更新 dp[i] 为 dp[i] 和 dp[i - square] + 1 的最小值，其中 square 为当前的完全平方数。
 *
 * 4. 最后返回 dp[n]，即为结果。
 *
 * 时间复杂度分析：外层循环遍历 n 次，内层循环遍历 sqrt(n) 次，因此时间复杂度为 O(n*sqrt(n))。
 *
 * 空间复杂度分析：创建了一个长度为 n+1 的数组 dp，因此空间复杂度为 O(n)。
 */

public class LeetCode_279_PerfectSquares{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 使用动态规划解决
        public int numSquares(int n) {
            // 创建一个数组来存储最小的完全平方数数量
            int dp[] = new int[n + 1];
            // 将数组填充为最大值
            Arrays.fill(dp, Integer.MAX_VALUE);
            // 底部情况，表示0个完全平方数
            dp[0] = 0;

            // 预先计算完全平方数
            int max_square_index = (int) Math.sqrt(n) + 1;
            int square_nums[] = new int[max_square_index];
            for (int i = 1; i < max_square_index; ++i) {
                square_nums[i] = i * i;
            }

            for (int i = 1; i <= n; ++i) {
                for (int s = 1; s < max_square_index; ++s) {
                    // 如果当前数字小于等于当前完全平方数，则退出内层循环
                    if (i < square_nums[s])
                        break;
                    // 更新dp数组
                    dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
                }
            }
            // 返回结果
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_279_PerfectSquares().new Solution();
        // 测试用例
        int result = solution.numSquares(12);
        System.out.println("Result: " + result); // 应该输出 3
    }
}

/**
Given an integer n, return the least number of perfect square numbers that sum 
to n. 

 A perfect square is an integer that is the square of an integer; in other 
words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 
are perfect squares while 3 and 11 are not. 

 
 Example 1: 

 
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
 

 Example 2: 

 
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 

 
 Constraints: 

 
 1 <= n <= 10⁴ 
 

 Related Topics Math Dynamic Programming Breadth-First Search 👍 11132 👎 469

*/
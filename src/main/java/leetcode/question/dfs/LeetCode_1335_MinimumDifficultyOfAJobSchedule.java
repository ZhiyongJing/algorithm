package leetcode.question.dfs;

/**
  *@Question:  1335. Minimum Difficulty of a Job Schedule     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 42.68%      
  *@Time  Complexity: O(d*n^2)  n be the length of the jobDifficulty array, and d be the total number of days.
  *@Space Complexity: O(d*n)
 */

/**
 * ### 算法思路
 *
 * 这是一个动态规划问题，需要在给定天数内完成一系列工作，最终目标是找到最低的工作难度。在每一天，至少完成一个任务，而每个任务有不同的难度。
 *
 * 1. **初始化：** 首先，检查是否有足够的天数来完成所有工作。如果天数不够，直接返回 -1。
 *
 * 2. **状态定义：** 定义状态 `dp[i][j]` 表示在前 `i` 个工作中使用 `j` 天完成的最低难度。
 *
 * 3. **状态转移：** 使用递推关系来更新状态。对于每一天 `k`，在前 `i` 个工作中使用 `j` 天完成的最低难度可以通过找到前 `i-1` 个工作在 `j-1`
 * 天完成的最低难度，再加上第 `i` 个工作的难度。
 *
 *    ```
 *    dp[i][j] = min(dp[i][j], dp[i-1][k] + max(jobDifficulty[k+1:i+1]))
 *    ```
 *
 *    这里的 `k` 是从 `j-1` 天的最后一天到 `i-1` 天的最后一天。
 *
 * 4. **边界情况：** 当 `j == 1` 时，表示只有一天，最低难度就是当前工作的难度。
 *
 * 5. **结果：** 最终结果就是 `dp[n][d]`，其中 `n` 是工作的数量，`d` 是给定的天数。
 *
 * ### 详细步骤
 *
 * 1. 检查是否有足够的天数来完成所有工作，否则返回 -1。
 * 2. 初始化状态数组 `dp`，其中 `dp[i][j]` 表示在前 `i` 个工作中使用 `j` 天完成的最低难度。
 * 3. 使用状态转移方程更新状态数组 `dp`。
 * 4. 返回最终结果 `dp[n][d]`。
 *
 * ### 时间复杂度
 *
 * - 对于每个状态 `(i, j)`，需要考虑前一天的状态，因此总体时间复杂度为 O(n * d * n)，其中 `n` 是工作的数量，`d` 是给定的天数。
 *
 * ### 空间复杂度
 *
 * - 使用了一个二维数组 `dp` 存储状态，空间复杂度为 O(n * d)。
 *
 * 综合来说，该算法的时间复杂度和空间复杂度都是相对较高的，但对于题目中规定的范围是可以接受的。
 */

public class LeetCode_1335_MinimumDifficultyOfAJobSchedule {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDifficulty(int[] jobDifficulty, int d) {

            int n = jobDifficulty.length;
            // 边界情况：确保每天至少有一个工作
            if (n < d) {
                return -1;
            }

            int[][] mem = new int[n][d + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= d; j++) {
                    mem[i][j] = -1;
                }
            }

            return minDiff(0, d, jobDifficulty, mem);
        }

        private int minDiff(int i, int daysRemaining, int[] jobDifficulty, int[][] mem) {
            // 使用记忆化搜索以避免重复计算状态
            if (mem[i][daysRemaining] != -1) {
                return mem[i][daysRemaining];
            }

            // 基本情况：在最后一天完成所有剩余工作
            if (daysRemaining == 1) {
                int res = 0;
                for (int j = i; j < jobDifficulty.length; j++) {
                    res = Math.max(res, jobDifficulty[j]);
                }
                return res;
            }

            int res = Integer.MAX_VALUE;
            int dailyMaxJobDiff = 0;
            // 遍历下一天可能的起始索引，并确保剩余天数中至少有一个工作
            for (int j = i; j < jobDifficulty.length - daysRemaining + 1; j++) {
                dailyMaxJobDiff = Math.max(dailyMaxJobDiff, jobDifficulty[j]);
                res = Math.min(res, dailyMaxJobDiff + minDiff(j + 1, daysRemaining - 1, jobDifficulty, mem));
            }
            mem[i][daysRemaining] = res;
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_1335_MinimumDifficultyOfAJobSchedule.Solution solution = new LeetCode_1335_MinimumDifficultyOfAJobSchedule().new Solution();
        // 测试用例1
        int[] jobDifficulty1 = {6, 5, 4, 3, 2, 1};
        int d1 = 2;
        System.out.println("测试用例1: " + solution.minDifficulty(jobDifficulty1, d1)); // 预期输出: 7

        // 测试用例2
        int[] jobDifficulty2 = {9, 9, 9};
        int d2 = 4;
        System.out.println("测试用例2: " + solution.minDifficulty(jobDifficulty2, d2)); // 预期输出: -1

        // 测试用例3
        int[] jobDifficulty3 = {1, 1, 1};
        int d3 = 3;
        System.out.println("测试用例3: " + solution.minDifficulty(jobDifficulty3, d3)); // 预期输出: 3
    }
}
 /**
You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work 
on the iᵗʰ job, you have to finish all the jobs j where 0 <= j < i). 

 You have to finish at least one task every day. The difficulty of a job 
schedule is the sum of difficulties of each day of the d days. The difficulty of a day 
is the maximum difficulty of a job done on that day. 

 You are given an integer array jobDifficulty and an integer d. The difficulty 
of the iᵗʰ job is jobDifficulty[i]. 

 Return the minimum difficulty of a job schedule. If you cannot find a schedule 
for the jobs return -1. 

 
 Example 1: 
 
 
Input: jobDifficulty = [6,5,4,3,2,1], d = 2
Output: 7
Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7 
 

 Example 2: 

 
Input: jobDifficulty = [9,9,9], d = 4
Output: -1
Explanation: If you finish a job per day you will still have a free day. you 
cannot find a schedule for the given jobs.
 

 Example 3: 

 
Input: jobDifficulty = [1,1,1], d = 3
Output: 3
Explanation: The schedule is one job per day. total difficulty will be 3.
 

 
 Constraints: 

 
 1 <= jobDifficulty.length <= 300 
 0 <= jobDifficulty[i] <= 1000 
 1 <= d <= 10 
 

 Related Topics Array Dynamic Programming 👍 2683 👎 241

*/

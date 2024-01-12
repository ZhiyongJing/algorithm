package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
  *@Question:  1235. Maximum Profit in Job Scheduling     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 80.3%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

/**
 * ### 算法思路
 *
 * 这是一个典型的动态规划问题，需要找到在不重叠的情况下，使得总利润最大的工作集合。
 *
 * 1. **排序：** 首先，将所有的工作按照开始时间进行升序排序。这样我们可以确保在迭代时，前面的工作不会与后面的工作发生重叠。
 *
 * 2. **动态规划：** 使用动态规划来解决问题，其中 `memo[position]` 表示从当前工作开始的最大利润。
 * 我们通过递归选择当前工作或跳过它，并在两者中选择利润更大的。
 *
 * 3. **二分查找：** 为了找到下一个不冲突的工作，我们使用二分查找。这样可以有效地找到当前工作结束后，下一个可以开始的工作。
 *
 * ### 详细步骤
 *
 * 1. 对工作进行排序，以确保按照开始时间升序排列。
 * 2. 创建 `memo` 数组，用于存储从当前工作开始的最大利润，将所有值初始化为 -1。
 * 3. 编写递归函数 `findMaxProfit`，在其中考虑两种情况：选择当前工作或跳过它，并返回最大利润。
 * 4. 在递归函数中，使用二分查找找到下一个不冲突的工作。
 * 5. 在主函数中调用 `findMaxProfit`，返回最终的最大利润。
 *
 * ### 时间复杂度
 *
 * - 排序时间复杂度为 O(NlogN)，其中 N 是工作的数量。
 * - 每个工作都有两个选择：选择当前工作或跳过它，因此递归的时间复杂度为 O(2^N)。
 * - 使用 `memo` 数组进行记忆化搜索，避免重复计算，降低时间复杂度。因此，总体时间复杂度为 O(NlogN + N).
 *
 * ### 空间复杂度
 *
 * - 使用了 `memo` 数组存储已计算的结果，空间复杂度为 O(N)。
 * - 递归调用的深度为工作数量 N，因此递归栈的空间复杂度为 O(N)。
 * - 总体空间复杂度为 O(N)。
 */


public class LeetCode_1235_MaximumProfitInJobScheduling {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 最大的工作数为50000
        int[] memo = new int[50001];

        /**
         * 查找下一个可以执行的工作的索引
         * @param startTime 工作的开始时间数组
         * @param lastEndingTime 上一个工作的结束时间
         * @return 下一个可以执行的工作的索引
         */
        private int findNextJob(int[] startTime, int lastEndingTime) {
            int start = 0, end = startTime.length - 1, nextIndex = startTime.length;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (startTime[mid] >= lastEndingTime) {
                    nextIndex = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return nextIndex;
        }

        /**
         * 查找最大利润的辅助方法
         * @param jobs 工作列表
         * @param startTime 工作的开始时间数组
         * @param n 工作数量
         * @param position 当前工作的索引
         * @return 最大利润
         */
        private int findMaxProfit(List<List<Integer>> jobs, int[] startTime, int n, int position) {
            // 如果已经迭代完所有工作，返回0利润
            if (position == n) {
                return 0;
            }

            // 如果已经计算过结果，直接返回
            if (memo[position] != -1) {
                return memo[position];
            }

            // 找到下一个不冲突工作的索引
            int nextIndex = findNextJob(startTime, jobs.get(position).get(1));

            // 计算两种选择的最大利润：跳过当前工作或安排当前工作
            int maxProfit = Math.max(findMaxProfit(jobs, startTime, n, position + 1),
                    jobs.get(position).get(2) + findMaxProfit(jobs, startTime, n, nextIndex));

            // 存储最大利润结果以备将来使用（记忆化）
            return memo[position] = maxProfit;
        }

        /**
         * 计算可以获取的最大利润
         * @param startTime 工作的开始时间数组
         * @param endTime 工作的结束时间数组
         * @param profit 工作的利润数组
         * @return 可以获取的最大利润
         */
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            List<List<Integer>> jobs = new ArrayList<>();

            // 将所有值标记为-1，以便区分是否已经计算过答案
            Arrays.fill(memo, -1);

            // 将工作的详细信息存储在一个列表中，这有助于在维护其他参数的同时对工作进行排序
            int length = profit.length;
            for (int i = 0; i < length; i++) {
                ArrayList<Integer> currJob = new ArrayList<>();
                currJob.add(startTime[i]);
                currJob.add(endTime[i]);
                currJob.add(profit[i]);
                jobs.add(currJob);
            }
            jobs.sort(Comparator.comparingInt(a -> a.get(0)));

            // 将使用二分搜索的开始时间存储为单独的列表
            for (int i = 0; i < length; i++) {
                startTime[i] = jobs.get(i).get(0);
            }

            return findMaxProfit(jobs, startTime, length, 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_1235_MaximumProfitInJobScheduling.Solution solution = new LeetCode_1235_MaximumProfitInJobScheduling().new Solution();
        // 测试用例1
        int[] startTime1 = {1, 2, 3, 3};
        int[] endTime1 = {3, 4, 5, 6};
        int[] profit1 = {50, 10, 40, 70};
        System.out.println("测试用例1: " + solution.jobScheduling(startTime1, endTime1, profit1)); // 预期输出: 120

        // 测试用例2
        int[] startTime2 = {1, 2, 3, 4, 6};
        int[] endTime2 = {3, 5, 10, 6, 9};
        int[] profit2 = {20, 20, 100, 70, 60};
        System.out.println("测试用例2: " + solution.jobScheduling(startTime2, endTime2, profit2)); // 预期输出: 150

        // 测试用例3
        int[] startTime3 = {1, 1, 1};
        int[] endTime3 = {2, 3, 4};
        int[] profit3 = {5, 6, 4};
        System.out.println("测试用例3: " + solution.jobScheduling(startTime3, endTime3, profit3)); // 预期输出: 6
    }
}
/**
We have n jobs, where every job is scheduled to be done from startTime[i] to 
endTime[i], obtaining a profit of profit[i]. 

 You're given the startTime, endTime and profit arrays, return the maximum 
profit you can take such that there are no two jobs in the subset with overlapping 
time range. 

 If you choose a job that ends at time X you will be able to start another job 
that starts at time X. 

 
 Example 1: 

 

 
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 

 Example 2: 

 

 
Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,6
0]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.
 

 Example 3: 

 

 
Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 

 
 Constraints: 

 
 1 <= startTime.length == endTime.length == profit.length <= 5 * 10⁴ 
 1 <= startTime[i] < endTime[i] <= 10⁹ 
 1 <= profit[i] <= 10⁴ 
 

 Related Topics Array Binary Search Dynamic Programming Sorting 👍 5538 👎 69

*/

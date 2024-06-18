package leetcode.question.dp;
/**
 *@Question:  312. Burst Balloons
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 47.67%
 *@Time  Complexity: O(N^3)For each state, determining the maximum coins requires iterating over all balloons in the range [left, right]
 *@Space Complexity: O(N^2) We need O(N^2)to store dp, and O(N) to store [1] + nums + [1] (if fake balloons are added)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求我们找到戳破气球能够获得的最大硬币数。每次戳破一个气球，硬币数是这个气球和它左右相邻气球的乘积。因此，我们需要找到一种戳破气球的顺序，使得获得的硬币数最大。
 *
 * #### 1. 引入新气球
 * 首先，我们在原始数组的两端各添加一个值为1的假气球，以简化计算。这样，我们可以将边界条件统一处理。
 *
 * 例如：
 * - 输入：`[3, 1, 5, 8]`
 * - 新数组：`[1, 3, 1, 5, 8, 1]`
 *
 * #### 2. 递归 + 记忆化搜索（自顶向下）
 * 我们定义一个函数 `dp(memo, nums, left, right)` 来表示戳破 `nums[left]` 到 `nums[right]` 之间的所有气球能够获得的最大硬币数。通过递归，我们计算不同的子问题，并使用一个二维数组 `memo` 缓存已经计算过的结果，以避免重复计算。
 *
 * - 递归基：如果 `right < left`，表示没有气球可以戳破，返回0。
 * - 状态转移：我们选择一个位置 `i`，将其作为最后一个被戳破的气球。这个气球的贡献是 `nums[left-1] * nums[i] * nums[right+1]`，剩下的硬币数由递归计算左边和右边的结果得到。
 *
 * #### 3. 动态规划（自底向上）
 * 我们使用一个二维数组 `dp`，其中 `dp[left][right]` 表示戳破 `nums[left]` 到 `nums[right]` 之间的所有气球能够获得的最大硬币数。
 *
 * - 我们从数组的末尾开始，逐渐向前计算每一个子区间的最大硬币数。
 * - 对于每一个区间 `[left, right]`，我们尝试将每一个位置 `i` 作为最后一个被戳破的气球，并更新 `dp[left][right]` 的值。
 *
 * ### 时间复杂度
 * - 递归 + 记忆化搜索的时间复杂度是 `O(n^3)`，因为对于每一个子区间 `[left, right]`，我们需要枚举所有可能的最后一个戳破的气球位置 `i`，并且有 `O(n^2)` 个子区间。
 * - 动态规划的时间复杂度也是 `O(n^3)`，因为我们需要填充 `O(n^2)` 个 `dp` 值，每个值的计算需要 `O(n)` 的时间。
 *
 * ### 空间复杂度
 * - 递归 + 记忆化搜索的空间复杂度是 `O(n^2)`，因为需要一个二维数组 `memo` 来缓存计算结果，同时递归调用栈的空间复杂度也是 `O(n)`。
 * - 动态规划的空间复杂度是 `O(n^2)`，因为需要一个二维数组 `dp` 来存储每一个子区间的最大硬币数。
 *
 * 通过上述方法，我们能够高效地计算出戳破气球能够获得的最大硬币数，并避免了重复计算。
 */
public class LeetCode_312_BurstBalloons{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // Solution1: 自顶向下
        public int maxCoins1(int[] nums) {
            // 在nums数组前后各添加一个1
            int n = nums.length + 2;
            int[] newNums = new int[n];
            // 将原始nums数组复制到newNums中，从索引1开始
            System.arraycopy(nums, 0, newNums, 1, n - 2);
            // 设置边界的气球值为1
            newNums[0] = 1;
            newNums[n - 1] = 1;

            // 缓存dp结果
            int[][] memo = new int[n][n];

            // 我们不能戳破第一个和最后一个气球，因为它们是我们自己添加的假气球
            return dp(memo, newNums, 1, n - 2);
        }

        public int dp(int[][] memo, int[] nums, int left, int right) {
            // 返回戳破nums[left]到nums[right]所有气球后的最大硬币数
            if (right - left < 0) {
                return 0;
            }

            // 如果之前已经计算过这个子问题的结果，则直接返回缓存的结果
            if (memo[left][right] > 0) {
                return memo[left][right];
            }

            // 找出在nums[left]到nums[right]之间最后一个被戳破的气球
            int result = 0;
            for (int i = left; i <= right; i++) {
                // nums[i]是最后一个被戳破的气球
                int gain = nums[left - 1] * nums[i] * nums[right + 1];
                // nums[i]固定，递归计算左边和右边子问题的结果
                int remaining = dp(memo, nums, left, i - 1) + dp(memo, nums, i + 1, right);
                // 更新结果
                result = Math.max(result, remaining + gain);
            }
            // 将结果添加到缓存中
            memo[left][right] = result;
            return result;
        }

        // Solution2: 自底向上
        public int maxCoins(int[] nums) {
            // 在nums数组前后各添加一个1
            int n = nums.length + 2;
            int[] newNums = new int[n];
            // 将原始nums数组复制到newNums中，从索引1开始
            System.arraycopy(nums, 0, newNums, 1, n - 2);
            // 设置边界的气球值为1
            newNums[0] = 1;
            newNums[n - 1] = 1;
            // dp[i][j]表示戳破nums[left]到nums[right]所有气球后的最大硬币数
            int[][] dp = new int[n][n];
            // 不包括第一个和最后一个气球，因为它们是我们自己添加的假气球，不能戳破它们
            for (int left = n - 2; left >= 1; left--) {
                for (int right = left; right <= n - 2; right++) {
                    // 找出在newNums[left]到newNums[right]之间最后一个被戳破的气球
                    for (int i = left; i <= right; i++) {
                        // newNums[i]是最后一个被戳破的气球
                        int gain = newNums[left - 1] * newNums[i] * newNums[right + 1];
                        // 递归计算左边和右边子问题的结果
                        int remaining = dp[left][i - 1] + dp[i + 1][right];
                        // 更新dp数组
                        dp[left][right] = Math.max(remaining + gain, dp[left][right]);
                    }
                }
            }
            // 戳破newNums[1]到newNums[n-2]的所有气球，不包括第一个和最后一个气球
            return dp[1][n - 2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_312_BurstBalloons().new Solution();
        // 测试样例
        int[] nums1 = {3, 1, 5, 8};
        System.out.println("Max coins (test case 1): " + solution.maxCoins(nums1)); // 167

        int[] nums2 = {1, 5};
        System.out.println("Max coins (test case 2): " + solution.maxCoins(nums2)); // 10
    }
}

/**
You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with 
a number on it represented by an array nums. You are asked to burst all the 
balloons. 

 If you burst the iᵗʰ balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] 
coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if 
there is a balloon with a 1 painted on it. 

 Return the maximum coins you can collect by bursting the balloons wisely. 

 
 Example 1: 

 
Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 

 Example 2: 

 
Input: nums = [1,5]
Output: 10
 

 
 Constraints: 

 
 n == nums.length 
 1 <= n <= 300 
 0 <= nums[i] <= 100 
 

 Related Topics Array Dynamic Programming 👍 8905 👎 246

*/
package leetcode.question.dp;

import java.util.HashMap;

/**
 *@Question:  740. Delete and Earn
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 33.19%
 *@Time  Complexity: O(N + K)
 *@Space Complexity: O(N + K) for solution1 and solution2, O(N) for solution3
 */

/**
 * 题目 "740. Delete and Earn" 是一个典型的动态规划问题。问题的核心是从给定的整数数组中删除一些数字，以获取最大的点数。每个数字被删除时，其所有出现的数字（即相同的数字）也会被删除，并获得相应的点数。
 *
 * ### 解题思路
 *
 * 1. **理解问题**：
 *    - 给定一个整数数组 `nums`，每个数字 `nums[i]` 的点数为 `nums[i]`。
 *    - 如果选择删除数字 `x`，则数组中所有等于 `x` 的数字都会被删除，并且可以获得的点数为 `x` 的点数之和。
 *    - 目标是通过删除一些数字，使得获得的总点数最大。
 *
 * 2. **动态规划的思路**：
 *    - **状态定义**：
 *      - 定义一个数组 `dp`，其中 `dp[i]` 表示从数字 `1` 到 `i` 可以获得的最大点数。
 *    - **状态转移方程**：
 *      - 对于每个数字 `i`，有两种选择：
 *        1. 删除所有等于 `i` 的数字，获得 `i` 的点数之和，并在剩余数字中继续选择最优策略，即 `dp[i] = dp[i-2] + sum[i]`。
 *        2. 不删除 `i`，则最大点数与 `dp[i-1]` 相同，即 `dp[i] = dp[i-1]`。
 *      - 最终 `dp[i]` 的值为上述两种选择的较大值。
 *    - **初始条件**：
 *      - 对于 `i = 1`，如果数组中存在 `1`，则 `dp[1] = sum[1]`；否则 `dp[1] = 0`。
 *      - 对于 `i = 2`，选择最大的两个数字中的一个，即 `dp[2] = max(sum[1], sum[2])`。
 *
 * 3. **实现细节**：
 *    - 首先，使用哈希表 `points` 统计每个数字的点数。
 *    - 根据 `points` 构建 `dp` 数组，进行动态规划的计算。
 *    - 通过遍历 `nums` 数组，更新 `dp` 数组的值，直到计算出 `dp[maxNumber]`，其中 `maxNumber` 是数组中的最大值。
 *
 * 4. **时间复杂度**：
 *    - 建立哈希表 `points` 的时间复杂度为 O(N)，其中 N 是数组 `nums` 的长度。
 *    - 构建 `dp` 数组的时间复杂度为 O(K)，其中 K 是数组中的最大数字。
 *    - 因此，总的时间复杂度为 O(N + K)。
 *
 * 5. **空间复杂度**：
 *    - 使用了哈希表 `points` 来存储每个数字的点数，空间复杂度为 O(N)。
 *    - 使用了数组 `dp` 来存储动态规划的中间结果，空间复杂度为 O(K)。
 *    - 因此，总的空间复杂度为 O(N + K)。
 *
 *
 *这道题目是关于删除和获取点数的问题，具体如下：
 *
 * ### 解题思路
 *
 * 这里给出了三种解题方法，分别是从上往下的动态规划（top-down）、从下往上的动态规划（bottom-up）以及空间优化的动态规划。
 *
 * 1. **Solution1: Top-down**
 *    - 首先，将每个数字对应的点数存储在 `points` 哈希表中。
 *    - 使用递归函数 `maxPoints` 计算删除元素后能获取的最大点数。
 *    - `maxPoints(num)` 函数返回删除到数字 `num` 能获取的最大点数，递归地利用 `maxPoints(num-1)` 和 `maxPoints(num-2)` 的结果。
 *    - 使用 `cache` 哈希表保存计算过的结果，避免重复计算，优化性能。
 *
 * 2. **Solution2: Bottom-up**
 *    - 首先，同样地，将每个数字对应的点数存储在 `points` 哈希表中。
 *    - 定义 `maxPoints` 数组，其中 `maxPoints[num]` 表示删除到数字 `num` 能获取的最大点数。
 *    - 从数字 `1` 开始到最大数字 `maxNumber`，计算每个 `maxPoints[num]` 的值。
 *    - 利用 `maxPoints[num-1]` 和 `maxPoints[num-2]` 的结果更新当前 `maxPoints[num]`。
 *
 * 3. **Solution3: Space-optimized**
 *    - 同样，首先初始化 `points` 哈希表，并确定最大数字 `maxNumber`。
 *    - 使用两个变量 `twoBack` 和 `oneBack` 分别代表上两步和上一步的最大点数。
 *    - 从数字 `2` 开始到 `maxNumber`，更新 `oneBack` 和 `twoBack` 的值，使得它们始终保持最新的最大点数状态。
 *
 * ### 时间复杂度和空间复杂度
 *
 * - **Solution1 和 Solution2** 的时间复杂度为 **O(N + K)**，其中 N 是数组 `nums` 的长度，K 是数组中的不同元素个数。这是因为需要遍历数组 `nums` 以构建 `points` 哈希表，并且遍历最大数字 `maxNumber` 来计算 `maxPoints`。
 * - **Solution3** 的时间复杂度同样为 **O(N + K)**，但它的空间复杂度优化到了 **O(N)**，只需要额外的 `points` 哈希表和几个变量来保存中间结果。
 *
 * 这三种方法都有效地解决了问题，具体选择哪一种方法取决于对空间复杂度和代码结构的优先考虑。在实际应用中，可以根据具体的场景和输入数据的特性选择合适的方法。
 * 通过上述动态规划的思路和实现，可以有效地解决 "Delete and Earn" 问题，并在给定的时间和空间复杂度内完成计算。
 */

public class LeetCode_740_DeleteAndEarn{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private HashMap<Integer, Integer> points = new HashMap<>(); // 存储每个数字的点数映射
        private HashMap<Integer, Integer> cache = new HashMap<>(); // 用于缓存已计算的结果

        private int maxPoints(int num) {
            // 基础情况的判断
            if (num == 0) {
                return 0;
            }

            if (num == 1) {
                return points.getOrDefault(1, 0); // 如果只有一个数字，直接返回其点数
            }

            if (cache.containsKey(num)) {
                return cache.get(num); // 如果已经计算过，直接返回缓存结果
            }

            // 递归计算当前数字取或不取时的最大点数
            int gain = points.getOrDefault(num, 0);
            cache.put(num, Math.max(maxPoints(num - 1), maxPoints(num - 2) + gain)); // 计算并缓存结果
            return cache.get(num);
        }

        //Solution1: 自顶向下的动态规划
        public int deleteAndEarn1(int[] nums) {
            int maxNumber = 0;

            // 预先计算每个数字的点数
            for (int num : nums) {
                points.put(num, points.getOrDefault(num, 0) + num);
                maxNumber = Math.max(maxNumber, num); // 记录最大的数字
            }

            return maxPoints(maxNumber); // 调用递归函数计算结果
        }

        //Solution2: 自底向上的动态规划
        public int deleteAndEarn(int[] nums) {
            HashMap<Integer, Integer> points = new HashMap<>();
            int maxNumber = 0;

            // 预先计算每个数字的点数
            for (int num : nums) {
                points.put(num, points.getOrDefault(num, 0) + num);
                maxNumber = Math.max(maxNumber, num); // 记录最大的数字
            }

            // 声明数组及初始化基础情况
            int[] maxPoints = new int[maxNumber + 1];
            maxPoints[1] = points.getOrDefault(1, 0);

            for (int num = 2; num < maxPoints.length; num++) {
                // 应用递推关系式
                int gain = points.getOrDefault(num, 0);
                maxPoints[num] = Math.max(maxPoints[num - 1], maxPoints[num - 2] + gain);
            }

            return maxPoints[maxNumber]; // 返回最终结果
        }

        //Solution3: 基于Solution2的空间优化
        public int deleteAndEarn3(int[] nums) {
            int maxNumber = 0;
            HashMap<Integer, Integer> points = new HashMap<>();

            // 预先计算每个数字的点数
            for (int num : nums) {
                points.put(num, points.getOrDefault(num, 0) + num);
                maxNumber = Math.max(maxNumber, num); // 记录最大的数字
            }

            // 基础情况
            int twoBack = 0;
            int oneBack = points.getOrDefault(1, 0);

            for (int num = 2; num <= maxNumber; num++) {
                int temp = oneBack;
                oneBack = Math.max(oneBack, twoBack + points.getOrDefault(num, 0)); // 更新当前最大值
                twoBack = temp; // 更新之前的最大值
            }

            return oneBack; // 返回最终结果
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_740_DeleteAndEarn().new Solution();
        // 测试用例
        int[] nums = {3, 4, 2};
        System.out.println(solution.deleteAndEarn(nums)); // 预期输出：6
    }
}

/**
You are given an integer array nums. You want to maximize the number of points 
you get by performing the following operation any number of times: 

 
 Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must 
delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1. 
 

 Return the maximum number of points you can earn by applying the above 
operation some number of times. 

 
 Example 1: 

 
Input: nums = [3,4,2]
Output: 6
Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.
 

 Example 2: 

 
Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points. 

 
 Constraints: 

 
 1 <= nums.length <= 2 * 10⁴ 
 1 <= nums[i] <= 10⁴ 
 

 Related Topics Array Hash Table Dynamic Programming 👍 7490 👎 377

*/
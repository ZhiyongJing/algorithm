package leetcode.question.dp;
/**
 *@Question:  1014. Best Sightseeing Pair
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 85.16%
 *@Time Complexity: O(N)
 *@Space Complexity: O(N) for solution1, O(1) for solution2
 */
/**
 * 题目描述：
 * 1014. Best Sightseeing Pair（最佳观光组合）
 *
 * 在一个观光景点数组 `values` 中，每个 `values[i]` 代表景点 `i` 的评分，
 * 并且任意两个景点 `i` 和 `j` 之间的得分计算方式如下：
 *
 * `score = values[i] + values[j] + i - j`
 *
 * 其中：
 * - `values[i]` 和 `values[j]` 是两个景点的评分
 * - `i - j` 代表两个景点的距离（需要扣除）
 *
 * **要求：**
 * 找到一对 `i < j`，使得 `score` 取最大值。
 *
 * **示例 1**
 * ```plaintext
 * 输入: values = [8, 1, 5, 2, 6]
 * 输出: 11
 * 解释: 选择 `i = 0`, `j = 2`，最大得分 `values[0] + values[2] + 0 - 2 = 8 + 5 - 2 = 11`
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: values = [1, 2, 3, 4, 5]
 * 输出: 7
 * 解释: 选择 `i = 3`, `j = 4`，最大得分 `values[3] + values[4] + 3 - 4 = 4 + 5 - 1 = 7`
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * **数学转换**
 *
 * 由于 `score = values[i] + values[j] + i - j`，可以拆分成：
 *
 * `score = (values[i] + i) + (values[j] - j)`
 *
 * 其中：
 * - `values[i] + i` 代表景点 `i` 的**左侧得分**
 * - `values[j] - j` 代表景点 `j` 的**右侧得分**
 * - **最终目标：找到 `i < j` 使得 `(values[i] + i) + (values[j] - j)` 最大**
 *
 * **方法 1：动态规划（DP）**
 * 1. **定义 `maxLeftScore[i]`**
 *    - `maxLeftScore[i]` 存储**从索引 `0` 到 `i-1` 的最大 `values[k] + k`**
 * 2. **计算得分**
 *    - 遍历 `j`（右侧景点），计算 `score = maxLeftScore[j - 1] + values[j] - j`
 * 3. **更新 `maxLeftScore[j]`**
 *    - `maxLeftScore[j] = max(maxLeftScore[j - 1], values[j] + j)`
 *
 * **方法 2：空间优化**
 * 1. **不使用数组 `maxLeftScore`，只用变量 `maxLeftScore` 记录当前最大左侧得分**
 * 2. **遍历 `j` 时**
 *    - `maxScore = max(maxScore, maxLeftScore + values[j] - j)`
 *    - `maxLeftScore = max(maxLeftScore, values[j] + j)`
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `values = [8, 1, 5, 2, 6]`
 *
 * **计算过程**
 * ```plaintext
 * 初始化：maxLeftScore = values[0] + 0 = 8
 *
 * i = 1:
 *   currentRightScore = values[1] - 1 = 1 - 1 = 0
 *   maxScore = max(0, 8 + 0) = 8
 *   maxLeftScore = max(8, values[1] + 1) = max(8, 2) = 8
 *
 * i = 2:
 *   currentRightScore = values[2] - 2 = 5 - 2 = 3
 *   maxScore = max(8, 8 + 3) = 11
 *   maxLeftScore = max(8, values[2] + 2) = max(8, 7) = 8
 *
 * i = 3:
 *   currentRightScore = values[3] - 3 = 2 - 3 = -1
 *   maxScore = max(11, 8 + (-1)) = 11
 *   maxLeftScore = max(8, values[3] + 3) = max(8, 5) = 8
 *
 * i = 4:
 *   currentRightScore = values[4] - 4 = 6 - 4 = 2
 *   maxScore = max(11, 8 + 2) = 11
 *   maxLeftScore = max(8, values[4] + 4) = max(8, 10) = 10
 * ```
 * **最终最大得分为 `11`**
 *
 * ---
 *
 * **时间复杂度分析**
 * - **动态规划方法：O(N)**
 * - **空间优化方法：O(1)**
 * - **总时间复杂度：O(N)**
 *
 * **空间复杂度分析**
 * - **动态规划（使用 `maxLeftScore` 数组）：O(N)**
 * - **优化方法（仅用变量）：O(1)**
 * - **总空间复杂度：O(1)**
 */


public class LeetCode_1014_BestSightseeingPair{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法1: 动态规划
        public int maxScoreSightseeingPair(int[] values) {
            int n = values.length;
            // 创建数组存储到当前位置的最大左侧景点得分 (values[i] + i)
            int[] maxLeftScore = new int[n];
            // 初始化左侧最大得分，第一位置的得分即为 values[0] + 0
            maxLeftScore[0] = values[0];

            // 记录最大观光得分
            int maxScore = 0;

            // 遍历数组，从第二个景点开始计算
            for (int i = 1; i < n; i++) {
                // 计算当前右侧景点得分 (values[i] - i)
                int currentRightScore = values[i] - i;
                // 计算当前最大观光得分：当前右侧景点 + 之前的最优左侧景点
                maxScore = Math.max(maxScore, maxLeftScore[i - 1] + currentRightScore);

                // 计算当前位置作为左侧景点时的得分 (values[i] + i)
                int currentLeftScore = values[i] + i;
                // 更新到当前位置的最大左侧景点得分
                maxLeftScore[i] = Math.max(maxLeftScore[i - 1], currentLeftScore);
            }

            return maxScore;
        }

        // 解法2: 基于解法1进行空间优化，O(1) 空间
        public int maxScoreSightseeingPair1(int[] values) {
            int n = values.length;
            // 维护当前最优的左侧景点得分 (values[i] + i)
            int maxLeftScore = values[0];
            // 记录最大观光得分
            int maxScore = 0;

            // 遍历数组，从第二个景点开始计算
            for (int i = 1; i < n; i++) {
                // 计算当前右侧景点得分 (values[i] - i)
                int currentRightScore = values[i] - i;
                // 计算当前最大观光得分
                maxScore = Math.max(maxScore, maxLeftScore + currentRightScore);

                // 更新当前最优左侧景点得分 (values[i] + i)
                maxLeftScore = Math.max(maxLeftScore, values[i] + i);
            }

            return maxScore;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1014_BestSightseeingPair().new Solution();

        // 测试样例 1
        int[] values1 = {8, 1, 5, 2, 6};
        System.out.println(solution.maxScoreSightseeingPair(values1)); // 预期输出: 11

        // 测试样例 2
        int[] values2 = {1, 2, 3, 4, 5};
        System.out.println(solution.maxScoreSightseeingPair(values2)); // 预期输出: 7

        // 测试样例 3
        int[] values3 = {7, 8, 8, 10};
        System.out.println(solution.maxScoreSightseeingPair(values3)); // 预期输出: 16

        // 测试样例 4
        int[] values4 = {4, 3, 2, 1};
        System.out.println(solution.maxScoreSightseeingPair(values4)); // 预期输出: 6

        // 测试样例 5
        int[] values5 = {10, 20, 30, 40};
        System.out.println(solution.maxScoreSightseeingPair(values5)); // 预期输出: 57
    }
}

/**
You are given an integer array values where values[i] represents the value of 
the iᵗʰ sightseeing spot. Two sightseeing spots i and j have a distance j - i 
between them. 

 The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i -
 j: the sum of the values of the sightseeing spots, minus the distance between 
them. 

 Return the maximum score of a pair of sightseeing spots. 

 
 Example 1: 

 
Input: values = [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 

 Example 2: 

 
Input: values = [1,2]
Output: 2
 

 
 Constraints: 

 
 2 <= values.length <= 5 * 10⁴ 
 1 <= values[i] <= 1000 
 

 Related Topics Array Dynamic Programming 👍 3221 👎 75

*/
package leetcode.question.greedy;

/**
 *@Question:  134. Gas Station
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 69.27%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求我们找到一个起始的加油站，使得从该站点出发，能够绕环路行驶一周，且途中不出现油量不足的情况。为了实现这个目标，可以使用贪心算法。
 *
 * #### 步骤
 * 1. **初始化变量**：
 *    - `currGain`：当前路径上的净收益（当前路径剩余的汽油量）。如果这个值变为负数，则说明从当前起点无法继续前进，需要重新选择起点。
 *    - `totalGain`：总净收益，即所有站点的净收益之和。如果这个值为负数，说明无论从哪个站点出发都无法完成一圈。
 *    - `answer`：记录可以作为起点的站点索引。
 *
 * 2. **遍历所有站点**：
 *    - 对于每一个站点，计算净收益 `gain[i] = gas[i] - cost[i]`。
 *    - 将当前站点的净收益累加到 `totalGain` 和 `currGain` 中。
 *    - 如果 `currGain` 小于0，说明当前路径无法继续前进，此时需要将 `answer` 更新为下一个站点，并重置 `currGain` 为0。
 *
 * 3. **判断是否可以完成环路**：
 *    - 如果 `totalGain` 大于等于0，说明总的净收益是正的，可以找到一个起点使得可以完成环路。因此，返回 `answer`。
 *    - 否则，返回 -1，表示无法完成环路。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**：`O(N)`，其中 `N` 是加油站的数量。因为我们只需要遍历一次所有的加油站来计算净收益和判断是否可以完成环路。
 * - **空间复杂度**：`O(1)`，因为我们只使用了固定数量的额外空间来存储变量 `currGain`、`totalGain` 和 `answer`。
 *
 * ### 示例讲解
 *
 * 假设有以下两个示例：
 * 1. `gas = [1, 2, 3, 4, 5]` 和 `cost = [3, 4, 5, 1, 2]`
 *    - 计算净收益 `gain`：`[-2, -2, -2, 3, 3]`
 *    - 遍历时：
 *      - `currGain` 依次为 `-2, -4, -6`，在这些点均小于0，因此将 `answer` 更新为下一个点。
 *      - 到达第4个点时，`currGain` 变为 `3`，可以从该点重新开始。
 *    - 总的 `totalGain` 为 `0`，说明可以找到一个起点。因此，返回 `3`。
 *
 * 2. `gas = [2, 3, 4]` 和 `cost = [3, 4, 3]`
 *    - 计算净收益 `gain`：`[-1, -1, 1]`
 *    - 遍历时：
 *      - `currGain` 依次为 `-1, -2`，在这些点均小于0，因此将 `answer` 更新为下一个点。
 *      - 到达第2个点时，`currGain` 为 `1`。
 *    - 总的 `totalGain` 为 `-1`，说明无法找到一个起点完成环路。因此，返回 `-1`。
 *
 * 通过上述方法和步骤，我们能够有效地解决这道题目，并确保时间和空间复杂度最优。
 */
public class LeetCode_134_GasStation{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int currGain = 0, totalGain = 0, answer = 0;

            for (int i = 0; i < gas.length; ++i) {
                // 计算当前站点的净收益（当前站点的汽油量减去到达下一个站点的花费）
                totalGain += gas[i] - cost[i];
                currGain += gas[i] - cost[i];

                // 如果当前净收益小于0，说明到达不了下一个站点
                // 那么从下一个站点重新开始计算，并将当前净收益重置为0
                if (currGain < 0) {
                    answer = i + 1;
                    currGain = 0;
                }
            }

            // 如果总净收益大于等于0，说明可以完成环路，否则不能
            return totalGain >= 0 ? answer : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_134_GasStation().new Solution();
        // 测试样例
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println(solution.canCompleteCircuit(gas1, cost1)); // 输出: 3

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println(solution.canCompleteCircuit(gas2, cost2)); // 输出: -1
    }
}

/**
There are n gas stations along a circular route, where the amount of gas at the 
iᵗʰ station is gas[i]. 

 You have a car with an unlimited gas tank and it costs cost[i] of gas to 
travel from the iᵗʰ station to its next (i + 1)ᵗʰ station. You begin the journey with 
an empty tank at one of the gas stations. 

 Given two integer arrays gas and cost, return the starting gas station's index 
if you can travel around the circuit once in the clockwise direction, otherwise 
return -1. If there exists a solution, it is guaranteed to be unique 

 
 Example 1: 

 
Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 =
 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to 
station 3.
Therefore, return 3 as the starting index.
 

 Example 2: 

 
Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the 
next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only 
have 3.
Therefore, you can't travel around the circuit once no matter where you start.
 

 
 Constraints: 

 
 n == gas.length == cost.length 
 1 <= n <= 10⁵ 
 0 <= gas[i], cost[i] <= 10⁴ 
 

 Related Topics Array Greedy 👍 11828 👎 1124

*/
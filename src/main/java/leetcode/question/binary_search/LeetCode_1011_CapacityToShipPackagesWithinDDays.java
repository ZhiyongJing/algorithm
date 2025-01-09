package leetcode.question.binary_search;

/**
 *@Question:  1011. Capacity To Ship Packages Within D Days
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 72.81%
 *@Time  Complexity: O(n * log(N))，  n is the length of weights.
 *@Space Complexity: O(1)
 */

/**
 * LeetCode 1011: Capacity To Ship Packages Within D Days
 *
 * @Question:
 * 给定一个数组 `weights`，其中每个元素表示包裹的重量，以及一个整数 `days`，表示运输的总天数。
 * 目标是找到最小的运载能力，使得在指定的天数内能够运输完所有包裹。
 *
 * @Solution:
 * 1. 确定二分查找的上下界：
 *    - 运载能力的最小值（left）：必须至少等于包裹的最大重量，因为单个包裹无法拆分。
 *    - 运载能力的最大值（right）：所有包裹的总重量，因为在极端情况下可以一次性运输所有包裹。
 *
 * 2. 使用二分查找：
 *    - 取中间值 mid，作为当前假设的运载能力。
 *    - 调用辅助函数 `feasible(weights, mid, days)` 检查当前运载能力是否能够在指定天数内完成运输：
 *       - 如果可以，则尝试缩小运载能力（right = mid）。
 *       - 如果不可以，则增大运载能力（left = mid + 1）。
 *    - 最终，当 left == right 时，找到最小的运载能力。
 *
 * 3. 辅助函数 `feasible(weights, capacity, days)`：
 *    - 模拟运输过程，统计需要的天数：
 *       - 使用变量 `currentLoad` 表示当前船的负载。
 *       - 遍历包裹，如果当前负载加上包裹重量超出 capacity，则需要增加一天，并将当前包裹作为新一天的起始负载。
 *       - 如果所需的天数不超过指定的 days，则当前运载能力是可行的。
 *
 * @Time Complexity:
 * - 假设数组长度为 n，所有包裹重量的总和为 S：
 *   - 计算上下界 max(weights) 和 sum(weights)：O(n)。
 *   - 二分查找的范围为 [max(weights), sum(weights)]，迭代次数为 O(log(S))。
 *   - 每次迭代调用 `feasible`，遍历数组一次：O(n)。
 * - 总时间复杂度为 O(n * log(S))。
 *
 * @Space Complexity:
 * - 辅助变量使用常量级空间，空间复杂度为 O(1)。
 *
 * @Example:
 * Input:
 * - weights = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 * - days = 5
 * Output:
 * - 15
 * Explanation:
 * - 使用运载能力为 15 时，可以在 5 天内运输完所有包裹：
 *   - 第 1 天：运输 [1, 2, 3, 4, 5]，总重量 15。
 *   - 第 2 天：运输 [6]，总重量 6。
 *   - 第 3 天：运输 [7]，总重量 7。
 *   - 第 4 天：运输 [8]，总重量 8。
 *   - 第 5 天：运输 [9, 10]，总重量 19。
 */

public class LeetCode_1011_CapacityToShipPackagesWithinDDays {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 检查是否可以用指定的运载能力 "c" 在 "days" 天内完成运输
        Boolean feasible(int[] weights, int c, int days) {
            int daysNeeded = 1; // 记录需要的天数，初始为1天
            int currentLoad = 0; // 当前船上的总重量

            // 遍历所有包裹的重量
            for (int weight : weights) {
                currentLoad += weight; // 将当前包裹放到船上
                if (currentLoad > c) { // 如果超出运载能力
                    daysNeeded++; // 增加一天
                    currentLoad = weight; // 当前包裹成为下一天的起始包裹
                }
            }

            // 如果需要的天数不超过给定的天数，返回 true
            return daysNeeded <= days;
        }

        public int shipWithinDays(int[] weights, int days) {
            int totalLoad = 0; // 所有包裹的总重量
            int maxLoad = 0; // 单个包裹的最大重量

            // 计算总重量和最大单包裹重量
            for (int weight : weights) {
                totalLoad += weight; // 累加总重量
                maxLoad = Math.max(maxLoad, weight); // 更新最大重量
            }

            // 二分查找的边界，最低运载能力为最大单包裹重量，最高为所有包裹的总重量
            int l = maxLoad, r = totalLoad;

            // 开始二分查找
            while (l < r) {
                int mid = (l + r) / 2; // 取中间值作为当前运载能力
                if (feasible(weights, mid, days)) {
                    r = mid; // 如果可以完成运输，尝试更小的运载能力
                } else {
                    l = mid + 1; // 否则需要更大的运载能力
                }
            }
            // 最终 l 即为最小的运载能力
            return l;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1011_CapacityToShipPackagesWithinDDays().new Solution();

        // 测试用例
        int[] weights1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days1 = 5;
        System.out.println("Test Case 1: " + solution.shipWithinDays(weights1, days1));
        // 输出：15

        int[] weights2 = {3, 2, 2, 4, 1, 4};
        int days2 = 3;
        System.out.println("Test Case 2: " + solution.shipWithinDays(weights2, days2));
        // 输出：6

        int[] weights3 = {1, 2, 3, 1, 1};
        int days3 = 4;
        System.out.println("Test Case 3: " + solution.shipWithinDays(weights3, days3));
        // 输出：3
    }
}

/**
A conveyor belt has packages that must be shipped from one port to another 
within days days. 

 The iᵗʰ package on the conveyor belt has a weight of weights[i]. Each day, we 
load the ship with packages on the conveyor belt (in the order given by weights).
 We may not load more weight than the maximum weight capacity of the ship. 

 Return the least weight capacity of the ship that will result in all the 
packages on the conveyor belt being shipped within days days. 

 
 Example 1: 

 
Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 
days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of 
capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), 
(9), (10) is not allowed.
 

 Example 2: 

 
Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 
days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
 

 Example 3: 

 
Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
 

 
 Constraints: 

 
 1 <= days <= weights.length <= 5 * 10⁴ 
 1 <= weights[i] <= 500 
 

 Related Topics Array Binary Search 👍 9857 👎 248

*/
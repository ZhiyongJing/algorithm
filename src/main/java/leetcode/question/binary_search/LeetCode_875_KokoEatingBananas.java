package leetcode.question.binary_search;
/**
 *@Question:  875. Koko Eating Bananas
 *@Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 78.39%
 *@Time Complexity: O(N * logM) Let n be the length of the input array piles and m be the maximum number of bananas in a single pile from piles.
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * **问题描述**：Koko 有 `piles` 数组中的香蕉堆，每堆香蕉的数量不同。Koko 需要在 `h` 小时内吃完所有的香蕉，她每小时吃的速度是 `k` 个香蕉。求 Koko 在 `h` 小时内能吃完所有香蕉的最小速度 `k`。
 *
 * #### 解题思路
 *
 * 1. **二分查找**：
 *    - **目标**：找到最小的 `k`，使得 Koko 能够在 `h` 小时内吃完所有香蕉。
 *    - **范围**：
 *      - **左边界 (`left`)**：最低速度为 1（即每小时吃 1 个香蕉）。
 *      - **右边界 (`right`)**：最高速度为 `piles` 中的最大值（即最快速度，意味着一小时可以吃掉最大的一堆香蕉）。
 *    - **算法**：
 *      1. **初始化**：
 *         - `left` 设置为 1，`right` 设置为 `piles` 中的最大值。
 *      2. **计算中间值**：
 *         - 在 `left` 和 `right` 之间取中间值 `middle` 作为当前的测试速度。
 *      3. **计算所需时间**：
 *         - 对每堆香蕉，计算 Koko 以速度 `middle` 吃完所需的小时数，并累加得到总小时数 `hourSpent`。
 *      4. **调整搜索范围**：
 *         - 如果 `hourSpent` 小于或等于 `h`，说明 `middle` 速度能够满足要求，但可能存在更小的速度也能满足条件，因此将 `right` 更新为 `middle`。
 *         - 如果 `hourSpent` 大于 `h`，说明 `middle` 速度不足，必须增大速度，因此将 `left` 更新为 `middle + 1`。
 *      5. **终止条件**：
 *         - 当 `left` 和 `right` 相等时，即找到满足条件的最小速度 `k`。
 *
 * #### 时间复杂度
 *
 * - **时间复杂度**：O(N * logM)
 *   - **二分查找**：每次二分查找的时间复杂度为 O(logM)，其中 M 是 `piles` 数组中的最大值。
 *   - **遍历数组**：对于每次二分查找，需要遍历整个 `piles` 数组来计算总小时数，复杂度为 O(N)。
 *   - **总时间复杂度**：O(N * logM)，其中 N 是 `piles` 的长度，M 是 `piles` 中的最大值。
 *
 * #### 空间复杂度
 *
 * - **空间复杂度**：O(1)
 *   - 算法只使用了常量级别的额外空间来存储变量，没有使用额外的复杂数据结构。
 */

public class LeetCode_875_KokoEatingBananas{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            // 初始化左边界为1，右边界为piles中的最大值
            int left = 1, right = 1;
            for (int pile : piles) {
                right = Math.max(right, pile);  // 右边界是piles中最大的一堆香蕉
            }

            // 使用二分查找来确定最小的吃香蕉速度
            while (left < right) {
                // 计算中间值
                int middle = (left + right) / 2;
                int hourSpent = 0;  // 记录总共需要的小时数

                // 计算每堆香蕉用middle速度需要的小时数
                for (int pile : piles) {
                    hourSpent += Math.ceil((double) pile / middle);  // 计算每堆香蕉需要的小时数，并累加
                }

                // 如果用middle速度能在h小时内吃完，则右边界移动到middle
                if (hourSpent <= h) {
                    right = middle;
                } else {
                    // 如果不能在h小时内吃完，则左边界移动到middle + 1
                    left = middle + 1;
                }
            }

            // 当左边界和右边界重合时，找到最小的可行速度
            return right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_875_KokoEatingBananas().new Solution();
        // 测试代码
        int[] piles = {3, 6, 7, 11};  // 每堆香蕉的数量
        int h = 8;  // 限制的总小时数
        int result = solution.minEatingSpeed(piles, h);  // 计算最小吃香蕉速度
        System.out.println("Minimum eating speed: " + result);  // 输出结果
    }
}

/**
Koko loves to eat bananas. There are n piles of bananas, the iᵗʰ pile has piles[
i] bananas. The guards have gone and will come back in h hours. 

 Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses 
some pile of bananas and eats k bananas from that pile. If the pile has less 
than k bananas, she eats all of them instead and will not eat any more bananas 
during this hour. 

 Koko likes to eat slowly but still wants to finish eating all the bananas 
before the guards return. 

 Return the minimum integer k such that she can eat all the bananas within h 
hours. 

 
 Example 1: 

 
Input: piles = [3,6,7,11], h = 8
Output: 4
 

 Example 2: 

 
Input: piles = [30,11,23,4,20], h = 5
Output: 30
 

 Example 3: 

 
Input: piles = [30,11,23,4,20], h = 6
Output: 23
 

 
 Constraints: 

 
 1 <= piles.length <= 10⁴ 
 piles.length <= h <= 10⁹ 
 1 <= piles[i] <= 10⁹ 
 

 Related Topics Array Binary Search 👍 10702 👎 672

*/
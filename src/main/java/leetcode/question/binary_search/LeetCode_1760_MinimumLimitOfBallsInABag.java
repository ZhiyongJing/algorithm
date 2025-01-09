package leetcode.question.binary_search;

/**
 *@Question:  1760. Minimum Limit of Balls in a Bag
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 67.44%
 *@Time  Complexity: O(N * log M), N 是数组的长度, M 是数组中的最大元素
 *@Space Complexity: O(1)
 */
/**
 * 题目: 1760. Minimum Limit of Balls in a Bag
 * 难度: 中等 (2)
 * 频率: 67.44%
 *
 * 时间复杂度: O(N * log M)，其中 N 是数组的长度，M 是数组中的最大元素。
 * 空间复杂度: O(1)，只用了常量空间。
 */

/**
 * 题目描述:
 * 给定一个数组 `nums`，表示每个袋子中球的数量，并且你需要将每个袋子中的球数分配到多个袋子中，使得每个袋子中的球数不超过一个给定的最大值 `maxBallsInBag`。
 * 我们可以通过拆分袋子来减小袋子中的球数，但每个袋子只能分配到 `maxBallsInBag` 个球。对于每个袋子，我们可以分成多个小袋子，计算需要多少次操作（拆分次数）。
 * 你需要找到一个最小的 `maxBallsInBag`，使得我们能够在最多 `maxOperations` 次操作内将所有袋子分解。
 *
 * 你需要实现一个函数，返回最小的 `maxBallsInBag`。
 */

/**
 * 解题思路:
 * 1. **二分查找**:
 *    我们希望找到最小的 `maxBallsInBag`，使得每个袋子中的球数不会超过这个限制，并且最多能够进行 `maxOperations` 次操作。
 *    这个问题可以通过二分查找来解决，二分查找的范围是 `[1, max(nums)]`，其中 `max(nums)` 是数组中的最大值。
 *
 * 2. **检查是否可行**:
 *    对于每个可能的 `maxBallsInBag`，我们需要判断是否可以在最多 `maxOperations` 次操作内分解所有袋子。
 *    我们可以用一个辅助函数 `isPossible` 来判断。对于每个袋子，计算需要多少次操作才能把袋子中的球数限制在 `maxBallsInBag` 之内。
 *    具体来说，操作次数为 `ceil(num / maxBallsInBag) - 1`，其中 `num` 是袋子中的球数。
 *    如果袋子中的球数小于或等于 `maxBallsInBag`，则不需要进行任何操作。
 *
 *    若所有袋子的操作次数之和小于等于 `maxOperations`，则当前 `maxBallsInBag` 是可行的。
 *
 * 3. **二分查找过程**:
 *    - 初始化左边界 `left` 为 1，右边界 `right` 为数组中的最大值。
 *    - 在每次二分查找时，计算中间值 `mid`，并使用 `isPossible` 函数判断当前 `mid` 是否可行。
 *    - 如果当前的 `mid` 可以满足条件，说明我们可以尝试一个更小的 `mid`，所以将右边界 `right` 更新为 `mid`。
 *    - 如果当前的 `mid` 不可行，说明我们需要一个更大的 `maxBallsInBag`，所以将左边界 `left` 更新为 `mid + 1`。
 *
 * 4. **结束条件**:
 *    二分查找终止时，`left` 就是最小的满足条件的 `maxBallsInBag`，即我们要返回的结果。
 */

/**
 * 时间复杂度分析:
 * 1. **二分查找**: 进行二分查找时，查找的范围是从 1 到 `max(nums)`，因此需要 `O(log(max(nums)))` 次查找。
 * 2. **检查每个 `maxBallsInBag` 是否可行**: 每次检查都需要遍历数组 `nums`，其复杂度为 `O(N)`，其中 N 是数组的长度。
 *
 * 所以，总的时间复杂度为 `O(N * log(max(nums)))`，其中 N 是数组的长度，`max(nums)` 是数组中的最大值。

 * 空间复杂度分析:
 * 该解法只使用了常量的空间来存储一些临时变量（如 `left`, `right`, `mid` 和 `totalOperations`），因此空间复杂度为 `O(1)`。
 */

public class LeetCode_1760_MinimumLimitOfBallsInABag{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 主函数，返回可以分配球的最小限制值
        public int minimumSize(int[] nums, int maxOperations) {
            // 二分查找的边界：最小球数限制为1，最大球数限制为数组中的最大元素
            int left = 1;
            int right = 0;

            // 遍历 nums 数组，找到最大值作为右边界
            for (int num : nums) {
                right = Math.max(right, num);
            }

            // 使用二分查找，逐步缩小范围，找到可以满足条件的最小最大值
            while (left < right) {
                // 计算中间值
                int middle = (left + right) / 2;

                // 检查当前中间值是否满足条件，即是否可以在最大操作次数内分配球
                if (isPossible(middle, nums, maxOperations)) {
                    // 如果可以，尝试一个更小的限制值，调整右边界
                    right = middle;
                } else {
                    // 如果不行，尝试更大的限制值，调整左边界
                    left = middle + 1;
                }
            }

            // 返回找到的最小的有效最大值
            return left;
        }

        // 辅助函数，判断是否可以在 maxOperations 操作次数内完成分配
        private boolean isPossible(
                int maxBallsInBag,  // 当前测试的最大球数限制
                int[] nums,         // 每个袋子中的球的数量
                int maxOperations   // 最大的操作次数
        ) {
            int totalOperations = 0;  // 用于记录进行的总操作次数

            // 遍历每个袋子
            for (int num : nums) {
                // 计算需要的操作次数，这里使用了 Math.ceil 来确定需要拆分的次数
                int operations = (int) Math.ceil(num / (double) maxBallsInBag) - 1;
                totalOperations += operations;

                // 如果当前操作次数已经超过了允许的最大操作次数，返回 false
                if (totalOperations > maxOperations) {
                    return false;
                }
            }

            // 如果操作次数没有超过最大限制，返回 true
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1760_MinimumLimitOfBallsInABag().new Solution();
        // 测试用例
        int[] nums = {9, 7, 5, 8};
        int maxOperations = 4;
        System.out.println(solution.minimumSize(nums, maxOperations));  // 输出最小限制值
    }
}

/**
You are given an integer array nums where the iᵗʰ bag contains nums[i] balls. 
You are also given an integer maxOperations. 

 You can perform the following operation at most maxOperations times: 

 
 Take any bag of balls and divide it into two new bags with a positive number 
of balls. 
 

 
 For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two 
new bags of 2 and 3 balls. 
 
 


 Your penalty is the maximum number of balls in a bag. You want to minimize 
your penalty after the operations. 

 Return the minimum possible penalty after performing the operations. 

 
 Example 1: 

 
Input: nums = [9], maxOperations = 2
Output: 3
Explanation: 
- Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
- Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
The bag with the most number of balls has 3 balls, so your penalty is 3 and you 
should return 3.
 

 Example 2: 

 
Input: nums = [2,4,8,2], maxOperations = 4
Output: 2
Explanation:
- Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,
4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,
2,2,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [
2,2,2,2,2,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] ->
 [2,2,2,2,2,2,2,2].
The bag with the most number of balls has 2 balls, so your penalty is 2, and 
you should return 2.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 1 <= maxOperations, nums[i] <= 10⁹ 
 

 Related Topics Array Binary Search 👍 2727 👎 99

*/
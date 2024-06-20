package leetcode.question.dp;
/**
 *@Question:  2560. House Robber IV
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 40.23%
 *@Time  Complexity: O(log(max(A)) * n)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 这道题目是 "House Robber IV"，它要求我们找到一个最小的能力值，使得我们可以从数组中偷取至少 `k` 个不相邻的房子，并且这些房子的值都不超过该能力值。我们可以使用二分查找法来解决这个问题。
 *
 * #### 步骤详解：
 *
 * 1. **确定二分查找的范围**：
 *    - 我们设定 `left` 为1，因为房子的价值不可能小于1。
 *    - 设定 `right` 为 `1e9`，这是一个足够大的值，可以覆盖所有可能的房子价值。
 *
 * 2. **二分查找过程**：
 *    - 在 `left` 小于 `right` 时，进行循环。
 *    - 每次计算中间值 `mid` 作为当前的能力值。
 *    - 遍历数组 `A`，统计在当前能力值下能够偷取的房子数 `take`。
 *    - 如果当前房子的值小于等于 `mid`，则计数并跳过下一个房子（因为不能连续偷两个相邻的房子）。
 *    - 如果能够偷取的房子数 `take` 大于等于 `k`，说明当前的能力值 `mid` 足够大，可以继续缩小范围，将 `right` 设置为 `mid`。
 *    - 否则，说明当前的能力值 `mid` 太小，无法偷取到足够的房子，将 `left` 设置为 `mid + 1`。
 *
 * 3. **返回结果**：
 *    - 当 `left` 等于 `right` 时，即找到了满足条件的最小能力值。
 *
 * ### 时间复杂度
 *
 * - **二分查找的时间复杂度**：每次将搜索范围减半，因此时间复杂度为 `O(log(max(A)))`，其中 `max(A)` 是数组 `A` 中的最大值（这里设定为 `1e9`）。
 * - **遍历数组的时间复杂度**：每次需要遍历数组以统计能偷取的房子数，因此时间复杂度为 `O(n)`，其中 `n` 是数组的长度。
 *
 * 结合这两个操作，整个算法的总时间复杂度为 `O(n * log(max(A)))`。
 *
 * ### 空间复杂度
 *
 * - 算法只使用了常数空间来存储变量（如 `left`、`right`、`mid`、`take` 等），因此空间复杂度为 `O(1)`。
 *
 * 通过上述解题步骤和复杂度分析，我们可以清晰地理解该问题的解决方案及其效率。
 */

public class LeetCode_2560_HouseRobberIv{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCapability(int[] A, int k) {
            // 二分查找的左边界，从1开始
            int left = 1;
            // 二分查找的右边界，设置为一个很大的数
            int right = (int)1e9;
            // 数组的长度
            int n = A.length;

            // 当左边界小于右边界时
            while (left < right) {
                // 取中间值
                int mid = (left + right) / 2;
                // 用来记录能够偷的房子数
                int take = 0;

                // 遍历数组
                for (int i = 0; i < n; ++i)
                    // 如果当前房子的值小于等于中间值
                    if (A[i] <= mid) {
                        // 增加偷的房子数
                        take += 1;
                        // 跳过下一个房子
                        i++;
                    }

                // 如果偷的房子数大于等于k
                if (take >= k)
                    // 将右边界设置为中间值
                    right = mid;
                else
                    // 否则，将左边界设置为中间值+1
                    left = mid + 1;
            }

            // 返回左边界
            return left; //left == right
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_2560_HouseRobberIv().new Solution();
        // 测试样例
        int[] houses = {2, 3, 5, 9, 1};
        int k = 2;
        // 输出应该为3，因为我们可以偷1和2或2和3
        System.out.println(solution.minCapability(houses, k)); // Output: 3
    }
}

/**
 There are several consecutive houses along a street, each of which has some
 money inside. There is also a robber, who wants to steal money from the homes, but
 he refuses to steal from adjacent homes.

 The capability of the robber is the maximum amount of money he steals from one 
 house of all the houses he robbed.

 You are given an integer array nums representing how much money is stashed in 
 each house. More formally, the iᵗʰ house from the left has nums[i] dollars.

 You are also given an integer k, representing the minimum number of houses the 
 robber will steal from. It is always possible to steal at least k houses.

 Return the minimum capability of the robber out of all the possible ways to 
 steal at least k houses.


 Example 1: 


 Input: nums = [2,3,5,9], k = 2
 Output: 5
 Explanation:
 There are three ways to rob at least 2 houses:
 - Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
 - Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
 - Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
 Therefore, we return min(5, 9, 9) = 5.


 Example 2: 


 Input: nums = [2,7,9,3,1], k = 2
 Output: 2
 Explanation: There are 7 ways to rob the houses. The way which leads to minimum
 capability is to rob the house at index 0 and 4. Return max(nums[0], nums[4]) =
 2.



 Constraints: 


 1 <= nums.length <= 10⁵ 
 1 <= nums[i] <= 10⁹ 
 1 <= k <= (nums.length + 1)/2 


 Related Topics Array Binary Search 👍 894 👎 31

 */
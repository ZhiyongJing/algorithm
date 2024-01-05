package leetcode.frequent.binary_search;

/**
  *@Question:  644. Maximum Average Subarray II     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 0.0%      
  *@Time  Complexity: O(N * log ((max-min)/0.00001))N be the number of element in the array, and range be the difference between the maximal and minimal values in the array, i.e. range = max_val - min_val, and finally the error be the precision required in the problem.
  *@Space Complexity: O()
 */

/**
 * **算法思路：**
 *
 * 这个问题涉及到在给定数组中找到长度为 k 的子数组，使得其平均值最大。使用二分查找的思想来解决。
 * 首先，确定二分查找的上下边界，即平均值的范围。最小平均值是数组中的最小值，最大平均值是数组中的最大值。
 * 然后，在二分查找的过程中，不断调整最小和最大平均值的值，直至找到满足条件的平均值。
 *
 * 在每一次二分查找中，计算当前平均值 mid，并使用check函数检查是否存在长度为 k 的子数组，其平均值大于等于 mid。
 * 如果存在，说明当前平均值太小，需要将最小平均值调整为 mid + 0.00001，继续查找。如果不存在，说明当前平均值太大，
 * 需要将最大平均值调整为 mid，继续查找。不断迭代，直至满足所需精度的条件。
 *
 * `check` 函数的作用是检查在给定的平均值下，是否存在长度为 k 的子数组，其平均值大于等于 mid。
 * 具体实现是通过维护两个变量 `sum` 和 `prev` 来计算子数组的和，同时记录 `min_sum` 表示之前的子数组和的最小值。
 * 遍历整个数组，通过这些变量来判断是否存在满足条件的子数组。
 *
 * **时间复杂度：**
 *
 * 在每次二分查找中，执行 `check` 函数的时间复杂度为 O(N)，其中 N 是数组的长度。二分查找的次数为
 * O(log((max-min)/0.00001))，其中 max 和 min 分别是数组中的最大值和最小值。因此，
 * 总体时间复杂度为 O(N * log((max-min)/0.00001))。
 *
 * **空间复杂度：**
 *
 * 空间复杂度为 O(1)，没有使用额外的空间，只使用了常数个变量。
 *
 * 这个算法的核心思想是通过二分查找，不断调整平均值的范围，从而找到满足条件的最大平均值。
 */
public class LeetCode_644_MaximumAverageSubarrayIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * 寻找最大平均值的子数组
         *
         * @param nums 数组
         * @param k    子数组的长度
         * @return 最大平均值
         */
        public double findMaxAverage(int[] nums, int k) {
            // 初始化最大值和最小值
            double max_val = Integer.MIN_VALUE;
            double min_val = Integer.MAX_VALUE;

            // 遍历数组，更新最大值和最小值
            for (int n : nums) {
                max_val = Math.max(max_val, n);
                min_val = Math.min(min_val, n);
            }

            // 初始化前一个平均值和误差
            double prev_mid = max_val, error = Integer.MAX_VALUE;

            // 当误差大于 0.00001 时，执行二分查找
            while (error > 0.00001) {
                // 计算当前平均值
                double mid = (max_val + min_val) * 0.5;

                // 判断是否满足条件
                if (check(nums, mid, k))
                    min_val = mid;
                else
                    max_val = mid;

                // 更新误差
                error = Math.abs(prev_mid - mid);
                prev_mid = mid;
            }

            // 返回最小值，即最大平均值
            return min_val;
        }

        /**
         * 检查是否存在长度为 k 的子数组，其平均值大于等于给定的平均值 mid
         *
         * @param nums 数组
         * @param mid  给定的平均值
         * @param k    子数组的长度
         * @return 是否满足条件
         */
        public boolean check(int[] nums, double mid, int k) {
            double sum = 0, prev = 0, min_sum = 0;

            // 计算前 k 个元素与平均值的差值之和
            for (int i = 0; i < k; i++)
                sum += nums[i] - mid;

            // 如果差值之和大于等于 0，说明存在满足条件的子数组
            if (sum >= 0)
                return true;

            // 遍历数组，更新 sum、prev 和 min_sum
            for (int i = k; i < nums.length; i++) {
                sum += nums[i] - mid;
                prev += nums[i - k] - mid;
                min_sum = Math.min(prev, min_sum);
                if (sum >= min_sum)
                    return true;
            }

            // 如果遍历完整个数组都没有找到满足条件的子数组，则返回 false
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_644_MaximumAverageSubarrayIi().new Solution();

        // 测试用例
        // 预期输出: 12.75
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(solution.findMaxAverage(nums, k));
    }
}

/**
You are given an integer array nums consisting of n elements, and an integer k. 


 Find a contiguous subarray whose length is greater than or equal to k that has 
the maximum average value and return this value. Any answer with a calculation 
error less than 10⁻⁵ will be accepted. 

 
 Example 1: 

 
Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation:
- When the length is 4, averages are [0.5, 12.75, 10.5] and the maximum average 
is 12.75
- When the length is 5, averages are [10.4, 10.8] and the maximum average is 10.
8
- When the length is 6, averages are [9.16667] and the maximum average is 9.1666
7
The maximum average is when we choose a subarray of length 4 (i.e., the sub 
array [12, -5, -6, 50]) which has the max average 12.75, so we return 12.75
Note that we do not consider the subarrays of length < 4.
 

 Example 2: 

 
Input: nums = [5], k = 1
Output: 5.00000
 

 
 Constraints: 

 
 n == nums.length 
 1 <= k <= n <= 10⁴ 
 -10⁴ <= nums[i] <= 10⁴ 
 

 Related Topics Array Binary Search Prefix Sum 👍 608 👎 69

*/

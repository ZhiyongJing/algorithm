package leetcode.question.two_pointer;
/**
 *@Question:  643. Maximum Average Subarray I
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 53.98%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * ===============================================
 * LeetCode 643. Maximum Average Subarray I
 * ===============================================
 *
 * 【一、题目描述】
 * 给定一个包含 n 个整数的数组 nums，以及一个整数 k。
 * 要求从中找出长度为 k 的连续子数组，使其平均值最大，并返回这个最大平均值。
 *
 * 示例：
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均值子数组为 [12,-5,-6,50]，其平均值为 (12 - 5 - 6 + 50) / 4 = 12.75
 *
 *
 * 【二、解题思路（滑动窗口法，含详细举例）】
 * 要点：本题是典型的“固定长度滑动窗口”问题，核心思想是：
 * - 初始时计算前 k 个元素的和，作为初始窗口；
 * - 然后依次将窗口向右滑动一个位置，同时更新窗口内的元素和；
 * - 每滑动一次就比较当前窗口的和是否大于之前的最大值；
 * - 最后将最大和除以 k，即为所求的最大平均值。
 *
 * 步骤如下：
 * 1）初始化一个变量 sum 存储前 k 个元素的总和；
 * 2）设置一个变量 maxSum 初始化为 sum；
 * 3）从索引 k 开始遍历数组，每次滑动窗口：
 *    - 加入新元素 nums[i]；
 *    - 减去滑出窗口的旧元素 nums[i - k]；
 *    - 更新 sum；
 *    - 比较并更新 maxSum；
 * 4）返回 maxSum / k 的浮点数结果。
 *
 * 举例说明：
 * 输入：nums = [1, 12, -5, -6, 50, 3], k = 4
 * - 初始窗口：[1, 12, -5, -6]，sum = 1 + 12 - 5 - 6 = 2，maxSum = 2
 * - 向右滑动：
 *   - 加入 nums[4] = 50，移除 nums[0] = 1，sum = 2 + 50 - 1 = 51，更新 maxSum = 51
 *   - 加入 nums[5] = 3，移除 nums[1] = 12，sum = 51 + 3 - 12 = 42，maxSum 不变
 * - 最终 maxSum = 51，最大平均值为 51 / 4 = 12.75
 *
 *
 * 【三、时间和空间复杂度】
 * - 时间复杂度：O(N)
 *   遍历数组一次，每个元素仅参与一次加减运算，N 为数组长度。
 *
 * - 空间复杂度：O(1)
 *   使用常数个变量，无需额外数据结构。
 */

public class LeetCode_643_MaximumAverageSubarrayI{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMaxAverage(int[] nums, int k) {
            // 初始化前 k 个元素的和
            int sum = 0;
            for(int i = 0; i < k; i ++)
                sum += nums[i];

            // 初始化最大和为当前的 sum
            int maxSum = sum;

            // 使用滑动窗口遍历数组，从第 k 个元素开始
            for(int i = k; i < nums.length; i ++) {
                // 滑动窗口：加上新元素，减去窗口左端旧元素
                sum += nums[i] - nums[i - k];
                // 更新最大和
                maxSum = Math.max(maxSum, sum);
            }

            // 返回最大平均值（注意类型转换）
            return (double)maxSum / k;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_643_MaximumAverageSubarrayI().new Solution();

        // 测试用例1：普通情况
        int[] nums1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        // 预期输出：12.75（子数组 [12, -5, -6, 50] 的平均值）
        System.out.println("最大平均值为: " + solution.findMaxAverage(nums1, k1));

        // 测试用例2：所有元素都相等
        int[] nums2 = {5, 5, 5, 5, 5};
        int k2 = 2;
        // 预期输出：5.0
        System.out.println("最大平均值为: " + solution.findMaxAverage(nums2, k2));

        // 测试用例3：只有一个元素的滑动窗口
        int[] nums3 = {-1};
        int k3 = 1;
        // 预期输出：-1.0
        System.out.println("最大平均值为: " + solution.findMaxAverage(nums3, k3));
    }
}

/**
You are given an integer array nums consisting of n elements, and an integer k. 


 Find a contiguous subarray whose length is equal to k that has the maximum 
average value and return this value. Any answer with a calculation error less than 1
0⁻⁵ will be accepted. 

 
 Example 1: 

 
Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 

 Example 2: 

 
Input: nums = [5], k = 1
Output: 5.00000
 

 
 Constraints: 

 
 n == nums.length 
 1 <= k <= n <= 10⁵ 
 -10⁴ <= nums[i] <= 10⁴ 
 

 Related Topics Array Sliding Window 👍 3821 👎 350

*/
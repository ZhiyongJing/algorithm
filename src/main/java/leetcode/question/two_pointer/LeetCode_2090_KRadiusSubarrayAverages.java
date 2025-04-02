package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 *@Question:  2090. K Radius Subarray Averages
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 34.75%
 *@Time  Complexity: O(n)
 *@Space Complexity: O(n) for solution1, O(1) for solution2
 */
/**
 * ===============================================
 * LeetCode 2090. K Radius Subarray Averages
 * ===============================================
 *
 * 【一、题目描述】
 * 给定一个整数数组 nums 和一个整数 k，定义“k 半径子数组”如下：
 * - 对于数组中下标为 i 的元素，若其左右各有至少 k 个元素（共 2k + 1 个元素），
 *   则 nums[i] 的 k 半径平均值为这些元素的平均值（向下取整）。
 * - 如果 i 左边或右边元素不足 k 个，则平均值为 -1。
 *
 * 要求返回一个新数组 averages，长度与 nums 相同，
 * 其中 averages[i] 表示 nums[i] 的 k 半径平均值。
 *
 * 示例：
 * 输入：nums = [7,4,3,9,1,8,5,2,6], k = 3
 * 输出：[-1,-1,-1,5,4,4,-1,-1,-1]
 *
 *
 * 【二、解题思路】
 * 本题有两种主流做法：前缀和数组 或 滑动窗口。
 * 官方推荐滑动窗口，因为它节省空间并更直观。
 *
 * ========= 解法 1：前缀和 =========
 * - 先构建前缀和数组 prefix，其中 prefix[i+1] 表示 nums[0] 到 nums[i] 的总和；
 * - 遍历所有满足左右各有 k 个元素的下标 i：
 *     - 计算窗口左右边界为 [i - k, i + k]；
 *     - 利用前缀和公式：subSum = prefix[right + 1] - prefix[left]；
 *     - 再取平均值并向下取整；
 * - 其他位置填 -1。
 *
 * 举例：
 * nums = [7,4,3,9,1,8,5,2,6], k = 3
 * 有效的中心点范围是 i ∈ [3, 5]
 * - i=3：窗口是 [0~6]，平均值为 (7+4+3+9+1+8+5)/7 = 37/7 = 5
 * - i=4：窗口是 [1~7]，平均值为 (4+3+9+1+8+5+2)/7 = 32/7 = 4
 * - i=5：窗口是 [2~8]，平均值为 (3+9+1+8+5+2+6)/7 = 34/7 = 4
 *
 *
 * ========= 解法 2：滑动窗口 =========
 * - 先计算前 2k+1 个元素的窗口和，作为第一个有效中心点 k 的平均值；
 * - 然后每次滑动窗口一位（向右移动一位）：
 *     - 减去左端滑出的值，加入右端新值；
 *     - 更新中心点的平均值；
 * - 其余不满足条件的位置默认填 -1。
 *
 * 举例：
 * nums = [7,4,3,9,1,8,5,2,6], k = 3，windowSize = 7
 * - 初始窗口：[7,4,3,9,1,8,5]，和为 37，中心 i=3，平均值为 5
 * - 窗口向右移：
 *   - 新窗口：[4,3,9,1,8,5,2]，和为 32，i=4，平均值为 4
 *   - 新窗口：[3,9,1,8,5,2,6]，和为 34，i=5，平均值为 4
 *
 *
 * 【三、时间与空间复杂度】
 *
 * 解法1（前缀和）：
 * - 时间复杂度：O(n)
 *   构建前缀和 O(n)，遍历计算平均值 O(n)
 * - 空间复杂度：O(n)
 *   使用了额外的前缀和数组
 *
 * 解法2（滑动窗口）：
 * - 时间复杂度：O(n)
 *   每个元素最多被加/减一次
 * - 空间复杂度：O(1)
 *   使用固定数量的变量，无额外数组
 */


public class LeetCode_2090_KRadiusSubarrayAverages{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // solution1: 使用前缀和
        public int[] getAverages1(int[] nums, int k) {
            // 如果 k 为 0，说明半径为 0，平均值就是元素本身，直接返回
            if (k == 0) {
                return nums;
            }

            // 窗口大小为 2k + 1
            int windowSize = 2 * k + 1;
            int n = nums.length;

            // 初始化返回数组，默认值为 -1
            int[] averages = new int[n];
            Arrays.fill(averages, -1);

            // 如果数组长度不足以形成一个完整窗口，返回初始数组
            if (windowSize > n) {
                return averages;
            }

            // 构建前缀和数组
            // prefix[i + 1] 表示 nums[0] 到 nums[i] 的和
            long[] prefix = new long[n + 1];
            for (int i = 0; i < n; ++i) {
                prefix[i + 1] = prefix[i] + nums[i];
            }

            // 遍历所有能够构建完整窗口的中心点（即两边至少有 k 个元素）
            for (int i = k; i < (n - k); ++i) {
                // 当前窗口的左边界和右边界
                int leftBound = i - k, rightBound = i + k;
                // 利用前缀和计算子数组总和
                long subArraySum = prefix[rightBound + 1] - prefix[leftBound];
                // 计算平均值
                int average = (int) (subArraySum / windowSize);
                // 存入结果
                averages[i] = average;
            }

            return averages;
        }

        // solution2: 滑动窗口解法
        public int[] getAverages(int[] nums, int k) {
            // 如果 k 为 0，平均值就是元素本身，直接返回
            if (k == 0) {
                return nums;
            }

            // 窗口大小
            int windowSize = 2 * k + 1;
            int n = nums.length;

            // 初始化结果数组，默认值为 -1
            int[] averages = new int[n];
            Arrays.fill(averages, -1);

            // 如果数组长度不足形成一个完整窗口，直接返回
            if (windowSize > n) {
                return averages;
            }

            // 计算第一个窗口的总和
            long windowSum = 0;
            for (int i = 0; i < windowSize; ++i) {
                windowSum += nums[i];
            }

            // 第一个完整窗口的平均值位置是 k
            averages[k] = (int) (windowSum / windowSize);

            // 开始滑动窗口，计算剩下每个有效中心点的平均值
            for (int i = windowSize; i < n; ++i) {
                // 减去滑出窗口的元素，加上新进入窗口的元素
                windowSum = windowSum - nums[i - windowSize] + nums[i];
                // 新中心点的位置是 i - k
                averages[i - k] = (int) (windowSum / windowSize);
            }

            return averages;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_2090_KRadiusSubarrayAverages().new Solution();

        // 测试用例 1：普通情况
        int[] nums1 = {7, 4, 3, 9, 1, 8, 5, 2, 6};
        int k1 = 3;
        // 预期输出: [-1,-1,-1,5,4,4,-1,-1,-1]
        System.out.println("测试用例1输出: " + Arrays.toString(solution.getAverages(nums1, k1)));

        // 测试用例 2：k 为 0，返回原数组
        int[] nums2 = {100, 200, 300};
        int k2 = 0;
        // 预期输出: [100, 200, 300]
        System.out.println("测试用例2输出: " + Arrays.toString(solution.getAverages(nums2, k2)));

        // 测试用例 3：窗口大于数组长度
        int[] nums3 = {1, 2, 3};
        int k3 = 2;
        // 预期输出: [-1, -1, -1]
        System.out.println("测试用例3输出: " + Arrays.toString(solution.getAverages(nums3, k3)));

        // 测试用例 4：全部元素都能形成窗口
        int[] nums4 = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        int k4 = 1;
        // 预期输出: [-1, 2, 3, 2, 3, 1, 4, 3, -1]
        System.out.println("测试用例4输出: " + Arrays.toString(solution.getAverages(nums4, k4)));
    }
}

/**
You are given a 0-indexed array nums of n integers, and an integer k. 

 The k-radius average for a subarray of nums centered at some index i with the 
radius k is the average of all elements in nums between the indices i - k and i +
 k (inclusive). If there are less than k elements before or after the index i, 
then the k-radius average is -1. 

 Build and return an array avgs of length n where avgs[i] is the k-radius 
average for the subarray centered at index i. 

 The average of x elements is the sum of the x elements divided by x, using 
integer division. The integer division truncates toward zero, which means losing 
its fractional part. 

 
 For example, the average of four elements 2, 3, 1, and 5 is (2 + 3 + 1 + 5) / 4
 = 11 / 4 = 2.75, which truncates to 2. 
 

 
 Example 1: 
 
 
Input: nums = [7,4,3,9,1,8,5,2,6], k = 3
Output: [-1,-1,-1,5,4,4,-1,-1,-1]
Explanation:
- avg[0], avg[1], and avg[2] are -1 because there are less than k elements 
before each index.
- The sum of the subarray centered at index 3 with radius 3 is: 7 + 4 + 3 + 9 + 
1 + 8 + 5 = 37.
  Using integer division, avg[3] = 37 / 7 = 5.
- For the subarray centered at index 4, avg[4] = (4 + 3 + 9 + 1 + 8 + 5 + 2) / 7
 = 4.
- For the subarray centered at index 5, avg[5] = (3 + 9 + 1 + 8 + 5 + 2 + 6) / 7
 = 4.
- avg[6], avg[7], and avg[8] are -1 because there are less than k elements 
after each index.
 

 Example 2: 

 
Input: nums = [100000], k = 0
Output: [100000]
Explanation:
- The sum of the subarray centered at index 0 with radius 0 is: 100000.
  avg[0] = 100000 / 1 = 100000.
 

 Example 3: 

 
Input: nums = [8], k = 100000
Output: [-1]
Explanation: 
- avg[0] is -1 because there are less than k elements before and after index 0.
 

 
 Constraints: 

 
 n == nums.length 
 1 <= n <= 10⁵ 
 0 <= nums[i], k <= 10⁵ 
 

 Related Topics Array Sliding Window 👍 1965 👎 99

*/
package leetcode.question.prefix_sum;

/**
 *@Question:  327. Count of Range Sum
 *@Difficulty: Hard
 *@Frequency: 54.46%
 *@Time Complexity: O(N log N) - 归并排序的时间复杂度
 *@Space Complexity: O(N) - 额外存储前缀和数组和临时数组
 */

public class LeetCode_327_CountOfRangeSum{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int count = 0; // 统计符合条件的子数组个数
        int lower; // 范围下界
        int upper; // 范围上界

        /**
         * 计算符合 [lower, upper] 范围的子数组和的个数
         *
         * @param nums 给定数组
         * @param lower 最小和
         * @param upper 最大和
         * @return 符合条件的子数组数量
         */
        public int countRangeSum(int[] nums, int lower, int upper) {
            long[] sum = new long[nums.length + 1]; // 存储前缀和
            long[] temp = new long[nums.length + 1]; // 归并排序的临时数组
            sum[0] = 0; // 初始化前缀和
            this.lower = lower;
            this.upper = upper;

            // 计算前缀和数组
            for (int i = 1; i <= nums.length; i++) {
                sum[i] = sum[i - 1] + (long) nums[i - 1]; // sum[i] 表示从索引 0 到 i-1 的前缀和
            }

            // 使用归并排序统计符合条件的区间和
            mergesort(sum, 0, sum.length - 1, temp);
            return count;
        }

        /**
         * 归并排序 + 计算符合条件的区间和
         */
        private void mergesort(long[] sum, int start, int end, long[] temp) {
            if (start >= end) {
                return;
            }
            int mid = start + (end - start) / 2;

            // 递归排序左右两部分
            mergesort(sum, start, mid, temp);
            mergesort(sum, mid + 1, end, temp);

            // 归并并计算符合 [lower, upper] 范围的子数组个数
            merge(sum, start, mid, end, temp);
        }

        /**
         * 归并排序 + 计算符合范围的子数组和
         */
        private void merge(long[] sum, int start, int mid, int end, long[] temp) {
            int right = mid + 1; // 归并右侧部分的起始索引
            int index = start; // 临时数组的索引
            int low = mid + 1, high = mid + 1; // 符合 [lower, upper] 范围的指针

            // 遍历左半部分的元素，查找符合条件的区间和
            for (int left = start; left <= mid; left++) {
                // 计算当前 left 对应的 [low, high) 范围，使得 sum[high] - sum[left] 在 [lower, upper] 内
                while (low <= end && sum[low] - sum[left] < lower) {
                    low++;
                }
                while (high <= end && sum[high] - sum[left] <= upper) {
                    high++;
                }
                // 统计符合条件的子数组个数
                count += high - low;

                // 归并排序部分，将右半部分的小于左半部分的元素加入 temp
                while (right <= end && sum[right] < sum[left]) {
                    temp[index++] = sum[right++];
                }
                temp[index++] = sum[left]; // 左半部分当前元素加入 temp
            }

            // 归并剩余的右半部分元素
            while (right <= end) {
                temp[index++] = sum[right++];
            }

            // 拷贝回原数组
            for (int i = start; i <= end; i++) {
                sum[i] = temp[i];
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_327_CountOfRangeSum().new Solution();

        // 测试用例 1
        int[] nums1 = {-2, 5, -1};
        int lower1 = -2, upper1 = 2;
        System.out.println(solution.countRangeSum(nums1, lower1, upper1)); // 预期输出: 3

        // 测试用例 2
        int[] nums2 = {0, 0, 0};
        int lower2 = 0, upper2 = 0;
        System.out.println(solution.countRangeSum(nums2, lower2, upper2)); // 预期输出: 6

        // 测试用例 3
        int[] nums3 = {3, -1, 4, -2, 2};
        int lower3 = 2, upper3 = 6;
        System.out.println(solution.countRangeSum(nums3, lower3, upper3)); // 预期输出: 5
    }
}

/**
Given an integer array nums and two integers lower and upper, return the number 
of range sums that lie in [lower, upper] inclusive. 

 Range sum S(i, j) is defined as the sum of the elements in nums between 
indices i and j inclusive, where i <= j. 

 
 Example 1: 

 
Input: nums = [-2,5,-1], lower = -2, upper = 2
Output: 3
Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective 
sums are: -2, -1, 2.
 

 Example 2: 

 
Input: nums = [0], lower = 0, upper = 0
Output: 1
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 -10⁵ <= lower <= upper <= 10⁵ 
 The answer is guaranteed to fit in a 32-bit integer. 
 

 Related Topics Array Binary Search Divide and Conquer Binary Indexed Tree 
Segment Tree Merge Sort Ordered Set 👍 2372 👎 251

*/
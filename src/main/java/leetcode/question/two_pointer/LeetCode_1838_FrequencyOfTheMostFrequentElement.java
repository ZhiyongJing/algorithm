package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 * @Question: 1838. Frequency of the Most Frequent Element
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 72.14%
 * @Time Complexity: O(N logN ) for solution1 and solution2
 * @Space Complexity: O(logN) for solution1 sorting, O(N) for solution2
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 1838 - Frequency of the Most Frequent Element
 *
 * 给定一个整数数组 nums 和一个整数 k，你可以对数组中的元素进行任意次数的以下操作：
 * - 选择 nums 中的某个元素，并将其增加 1。
 * 你最多可以执行 k 次这样的操作。
 *
 * 目标：返回在最多进行 k 次操作后，数组中相同元素的最大频率。
 *
 * 举例：
 * 输入: nums = [1,2,4], k = 5
 * 你可以执行如下操作：
 * - 给 1 增加 3 次得到 4，
 * - 给 2 增加 1 次得到 3，再增加 1 次得到 4，
 * 结果数组为 [4,4,4]，最大频率为 3，返回 3。
 *
 * -------------------------------------------------------------------
 *
 * 第二步：解题思路（基于代码的详细解释，并对每步举例说明）
 * -------------------------------------------------------------------
 * 该题有两种解法：Solution1 使用滑动窗口，Solution2 使用二分查找 + 前缀和。
 *
 * 【解法一：滑动窗口】
 * 思路核心：使某个数成为数组中重复最多的那个数，即尽量将窗口中的数全部变为某个目标值。
 * 步骤如下：
 * 1. 将数组 nums 升序排序：保证较小的数在左边，较大的在右边，方便计算操作成本。
 * 2. 使用滑动窗口，从左到右扩展窗口。
 * 3. 维护窗口中元素之和 curr，当窗口右端为 nums[right]，尝试将窗口内所有元素变为 nums[right]。
 * 4. 判断操作数是否超过 k，如果超过则缩小左边界直到合法。
 * 5. 每次更新最大频率为窗口长度（right - left + 1）。
 *
 * 举例说明：
 * 输入：nums = [1, 2, 4], k = 5
 * 排序后：nums = [1, 2, 4]
 * 初始：left = 0, right = 0, curr = 1
 * right = 1 时，curr = 1 + 2 = 3，目标变为 2：
 *   需要操作数 = (2-1+1)*2 - 3 = 4 - 3 = 1 <= k，合法
 * right = 2 时，curr = 3 + 4 = 7，目标变为 4：
 *   需要操作数 = 3*4 - 7 = 12 - 7 = 5 == k，合法
 * 窗口为 [1,2,4]，最大频率为 3，返回 3。
 *
 * 【解法二：二分查找 + 前缀和】
 * 思路核心：固定右端 i，向左二分查找最远的左端 j，使得将 [j, i] 全部变为 nums[i] 需要的操作数不超过 k。
 * 步骤如下：
 * 1. 将数组 nums 排序。
 * 2. 构建前缀和数组 prefix[i] 表示 nums[0] 到 nums[i] 的和。
 * 3. 枚举每个位置 i，使用二分法查找最小的 j 满足：
 *    (i - j + 1) * nums[i] - sum(nums[j...i]) <= k
 * 4. 更新最大频率为 (i - j + 1)
 *
 * 举例说明：
 * nums = [1, 2, 4], k = 5, 排序后仍为 [1, 2, 4]
 * 枚举 i = 2（即目标值为 4）：
 *   需要查找最小的 j，使得将 nums[j..2] 全部变为 4 的代价 <= 5
 *   j = 0 时：窗口为 [1,2,4]，原始和为 1+2+4=7，目标和为 3*4=12，差值为 5，符合条件
 *   返回频率为 3，更新最大频率为 3。
 *
 * -------------------------------------------------------------------
 *
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * 【解法一：滑动窗口】
 * 时间复杂度：
 * - 排序：O(N log N)
 * - 遍历数组一遍：O(N)
 * - 总体时间复杂度：O(N log N)
 *
 * 空间复杂度：
 * - 仅使用了常数级别变量：O(1)（不算排序所用栈空间，可视为 O(log N)）
 *
 * 【解法二：二分查找 + 前缀和】
 * 时间复杂度：
 * - 排序：O(N log N)
 * - 构建前缀和：O(N)
 * - 每个 i 做一次二分：O(log N)，共 N 次：O(N log N)
 * - 总体时间复杂度：O(N log N)
 *
 * 空间复杂度：
 * - 前缀和数组：O(N)
 */


public class LeetCode_1838_FrequencyOfTheMostFrequentElement {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // solution1: 滑动窗口解法
        public int maxFrequency(int[] nums, int k) {
            Arrays.sort(nums); // 先对数组进行排序，确保相同元素相邻，方便计算
            int left = 0; // 滑动窗口左边界
            int ans = 0; // 记录最大频率
            long curr = 0; // 记录当前窗口内所有元素的总和

            for (int right = 0; right < nums.length; right++) { // 右边界遍历数组
                long target = nums[right]; // 当前窗口要尝试增加的目标数
                curr += target; // 增加当前元素的值到窗口总和

                // 检查当前窗口是否可以通过 k 次操作使所有元素变成 nums[right]
                while ((right - left + 1) * target - curr > k) {
                    curr -= nums[left]; // 移除窗口左边界的值
                    left++; // 窗口左边界右移
                }

                // 更新最大频率
                ans = Math.max(ans, right - left + 1);
            }

            return ans;
        }

        // solution2: 二分查找解法

        public int check(int i, int k, int[] nums, long[] prefix) {
            int target = nums[i]; // 目标数值
            int left = 0; // 二分查找的左边界
            int right = i; // 二分查找的右边界
            int best = i; // 记录符合条件的最优左边界索引

            while (left <= right) { // 进行二分查找
                int mid = (left + right) / 2; // 计算中点
                long count = i - mid + 1; // 计算窗口大小
                long finalSum = count * target; // 目标窗口和
                long originalSum = prefix[i] - prefix[mid] + nums[mid]; // 计算当前窗口的和
                long operationsRequired = finalSum - originalSum; // 计算需要的操作次数

                if (operationsRequired > k) { // 如果需要的操作次数超过 k，则缩小窗口
                    left = mid + 1; // 增大左边界
                } else { // 否则更新最优结果并缩小右边界
                    best = mid;
                    right = mid - 1;
                }
            }

            return i - best + 1; // 返回符合条件的最大窗口大小
        }

        public int maxFrequency2(int[] nums, int k) {
            Arrays.sort(nums); // 先对数组进行排序
            long[] prefix = new long[nums.length]; // 计算前缀和
            prefix[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                prefix[i] = nums[i] + prefix[i - 1]; // 构建前缀和数组
            }

            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                ans = Math.max(ans, check(i, k, nums, prefix)); // 计算每个位置的最大频率
            }

            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1838_FrequencyOfTheMostFrequentElement().new Solution();

        // 测试用例
        int[] nums1 = {1, 2, 4};
        int k1 = 5;
        System.out.println(solution.maxFrequency(nums1, k1)); // 期望输出：3

        int[] nums2 = {3, 9, 6, 2, 8};
        int k2 = 4;
        System.out.println(solution.maxFrequency(nums2, k2)); // 期望输出：2

        int[] nums3 = {1, 4, 8, 13};
        int k3 = 5;
        System.out.println(solution.maxFrequency(nums3, k3)); // 期望输出：2

        int[] nums4 = {5, 5, 5, 5};
        int k4 = 10;
        System.out.println(solution.maxFrequency(nums4, k4)); // 期望输出：4
    }
}

/**
 * The frequency of an element is the number of times it occurs in an array.
 * <p>
 * You are given an integer array nums and an integer k. In one operation, you
 * can choose an index of nums and increment the element at that index by 1.
 * <p>
 * Return the maximum possible frequency of an element after performing at most k
 * operations.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,4], k = 5
 * Output: 3
 * Explanation: Increment the first element three times and the second element two
 * times to make nums = [4,4,4].
 * 4 has a frequency of 3.
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,4,8,13], k = 5
 * Output: 2
 * Explanation: There are multiple optimal solutions:
 * - Increment the first element three times to make nums = [4,4,8,13]. 4 has a
 * frequency of 2.
 * - Increment the second element four times to make nums = [1,8,8,13]. 8 has a
 * frequency of 2.
 * - Increment the third element five times to make nums = [1,4,13,13]. 13 has a
 * frequency of 2.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [3,9,6], k = 2
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * 1 <= nums[i] <= 10⁵
 * 1 <= k <= 10⁵
 * <p>
 * <p>
 * Related Topics Array Binary Search Greedy Sliding Window Sorting Prefix Sum 👍
 * 5060 👎 249
 */
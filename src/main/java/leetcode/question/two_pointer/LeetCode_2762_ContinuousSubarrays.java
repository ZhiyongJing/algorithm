package leetcode.question.two_pointer;

import java.util.TreeMap;

/**
 *@Question:  2762. Continuous Subarrays
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 81.15%
 *@Time  Complexity: O(n * logK) size of the sorted map k is bounded by 3 (as elements can only differ by 0, 1, or 2).
 * Therefore, logk is effectively constant,
 *@Space Complexity: O(K) or O(1)
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 2762 - Continuous Subarrays（连续子数组）
 *
 * 给定一个整数数组 nums，要求你找出所有满足如下条件的 **连续子数组个数**：
 * - 子数组中任意两个元素之差的绝对值 ≤ 2（即 max - min ≤ 2）
 *
 * 你需要返回所有满足条件的连续子数组的总数（以 long 类型返回）。
 *
 * 示例：
 * 输入：nums = [1, 2, 3]
 * 输出：6
 * 解释：所有连续子数组均满足条件：
 * - [1], [2], [3], [1,2], [2,3], [1,2,3]
 *
 * -------------------------------------------------------------------
 *
 * 第二步：解题思路（基于代码的超级详细讲解 + 举例说明）
 * -------------------------------------------------------------------
 * ✅ 思路：滑动窗口 + 有序集合（TreeMap）
 *
 * 本题的核心在于：找出以每个右端点结尾、满足“最大值 - 最小值 ≤ 2”的连续子数组个数。
 * 为了高效获取当前窗口中的最大值与最小值，使用 `TreeMap` 维护一个滑动窗口的有序频率统计。
 *
 * ✅ 步骤详解：
 *
 * 1. 定义左右指针 left 和 right 表示当前窗口，初始化都为 0。
 * 2. 使用 TreeMap<Integer, Integer> freq，维护窗口内每个元素的频率，并保持有序性。
 * 3. 移动右指针，向 freq 中加入 nums[right]，更新频率。
 * 4. 检查当前窗口是否合法，即 `freq.lastKey() - freq.firstKey() ≤ 2`：
 *    - 若合法，表示以 right 为结尾的所有子数组都是合法的，个数为 (right - left + 1)
 *    - 若不合法，说明窗口中的最大最小值差大于2，移动左指针直到窗口合法（不断从 freq 中移除 nums[left]）
 * 5. 每次合法后将子数组数量累加进结果。
 *
 * ✅ 举例说明：
 * 输入 nums = [1, 4, 2, 3]
 *
 * - 初始化：left=0, right=0, freq={1:1} → 合法 → count += 1
 * - right=1, freq={1:1, 4:1} → max=4, min=1, 差值为3 → 不合法
 *   → 移动 left=1，freq={4:1} → 合法 → count += 1
 * - right=2, freq={4:1, 2:1} → max=4, min=2, 差值=2 → 合法 → count += 2
 *   → 子数组：[2], [4,2]
 * - right=3, freq={4:1, 2:1, 3:1} → max=4, min=2 → 合法 → count += 3
 *   → 子数组：[3], [2,3], [4,2,3]
 * 总数为：1 + 1 + 2 + 3 = 7
 *
 * -------------------------------------------------------------------
 *
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * 时间复杂度：O(n * log k)
 * - 外层 while 循环执行 n 次（right 遍历数组）
 * - 内层 while 循环在最坏情况下每个元素只被处理一次（left 只向前），总共 O(n)
 * - 每次 TreeMap 的 put/get/remove 操作是 O(log k)，但由于窗口内最多 3 个不同的值（最多差2），log k 近似常数
 * - 整体近似为 O(n)
 *
 * 空间复杂度：O(k)，k 为窗口中不同元素的个数，最多为 3，故可视为 O(1)
 */

public class LeetCode_2762_ContinuousSubarrays{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public long continuousSubarrays(int[] nums) {
            // 使用 TreeMap 存储当前窗口内每个元素的频率，且保持有序（支持快速获取最大最小值）
            TreeMap<Integer, Integer> freq = new TreeMap<>();
            int left = 0, right = 0; // 滑动窗口的左右指针
            int n = nums.length;
            long count = 0; // 用于记录合法子数组的总数

            // 右指针从 0 开始遍历到数组末尾
            while (right < n) {
                // 将当前元素加入频率映射中（右边界扩展）
                freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

                // 当窗口内最大值与最小值之差 > 2，说明当前窗口不合法
                while (freq.lastEntry().getKey() - freq.firstEntry().getKey() > 2) {
                    // 缩小左边界，将最左元素频率减一
                    freq.put(nums[left], freq.get(nums[left]) - 1);
                    // 如果频率为 0，移除该元素
                    if (freq.get(nums[left]) == 0) {
                        freq.remove(nums[left]);
                    }
                    left++; // 移动左指针
                }

                // 此时窗口合法，包含的所有子数组都满足条件
                // 子数组个数为 (right - left + 1)
                count += right - left + 1;
                right++; // 右指针继续扩展窗口
            }

            // 返回所有满足条件的连续子数组个数
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_2762_ContinuousSubarrays().new Solution();

        // 测试用例 1：基本情况
        int[] nums1 = {1, 2, 3};
        System.out.println(solution.continuousSubarrays(nums1)); // 输出: 6

        // 测试用例 2：所有元素都相同
        int[] nums2 = {2, 2, 2, 2};
        System.out.println(solution.continuousSubarrays(nums2)); // 输出: 10

        // 测试用例 3：有跳跃，部分不合法
        int[] nums3 = {1, 4, 2, 3};
        System.out.println(solution.continuousSubarrays(nums3)); // 输出: 7

        // 测试用例 4：边界差值为 2，合法
        int[] nums4 = {1, 3, 2};
        System.out.println(solution.continuousSubarrays(nums4)); // 输出: 6

        // 测试用例 5：只有一个元素
        int[] nums5 = {10};
        System.out.println(solution.continuousSubarrays(nums5)); // 输出: 1
    }
}

/**
You are given a 0-indexed integer array nums. A subarray of nums is called 
continuous if: 

 
 Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of 
indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2. 
 

 Return the total number of continuous subarrays. 

 A subarray is a contiguous non-empty sequence of elements within an array. 

 
 Example 1: 

 
Input: nums = [5,4,2,4]
Output: 8
Explanation: 
Continuous subarray of size 1: [5], [4], [2], [4].
Continuous subarray of size 2: [5,4], [4,2], [2,4].
Continuous subarray of size 3: [4,2,4].
There are no subarrys of size 4.
Total continuous subarrays = 4 + 3 + 1 = 8.
It can be shown that there are no more continuous subarrays.
 

 

 Example 2: 

 
Input: nums = [1,2,3]
Output: 6
Explanation: 
Continuous subarray of size 1: [1], [2], [3].
Continuous subarray of size 2: [1,2], [2,3].
Continuous subarray of size 3: [1,2,3].
Total continuous subarrays = 3 + 2 + 1 = 6.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 1 <= nums[i] <= 10⁹ 
 

 Related Topics Array Queue Sliding Window Heap (Priority Queue) Ordered Set 
Monotonic Queue 👍 1429 👎 88

*/
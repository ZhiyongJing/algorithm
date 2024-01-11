package leetcode.frequent.based_on_data_structure.heap;

import java.util.TreeMap;

/**
  *@Question:  1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 49.08%      
  *@Time  Complexity: O(NlogN)
  *@Space Complexity: O(1)
 */

/**
 * **问题描述：**
 *
 * 给定一个整数数组 `A` 和一个整数 `limit`，找到一个最长的连续子数组，使得该子数组中的最大值与最小值的绝对差不超过 `limit`。
 *
 * **解题思路：**
 *
 * 这个问题可以使用滑动窗口结合TreeMap的方法来解决。具体步骤如下：
 *
 * 1. **初始化指针和数据结构：** 使用两个指针 `i` 和 `j`，其中 `i` 表示窗口的左边界，`j` 表示窗口的右边界。
 * 同时，使用一个 `TreeMap` 来存储窗口中每个元素的出现次数。
 *
 * 2. **移动右指针：** 遍历数组，不断将元素加入窗口中，并更新 `TreeMap` 中的元素出现次数。
 *
 * 3. **维持窗口：** 在移动右指针的过程中，需要保持窗口中的最大值与最小值的差不超过 `limit`。
 * 如果超过了 `limit`，则移动左指针，缩小窗口，直到满足限制。
 *
 * 4. **计算窗口长度：** 在每次移动右指针和缩小窗口的过程中，记录窗口的长度。最终，返回最长窗口的长度。
 *
 * **时间复杂度：**
 *
 * - 遍历数组的过程中，每个元素都被放入和取出 `TreeMap` 一次，因此总的时间复杂度为 O(N * log N)，其中 N 是数组的长度。
 *
 * **空间复杂度：**
 *
 * - 使用了一个 `TreeMap` 来维护窗口中元素的出现次数，因此空间复杂度为 O(N)，其中 N 是数组的长度。
 * 由于 TreeMap 中存储的元素数量最多为数组长度，因此可认为是常数级别的额外空间。
 */
public class LeetCode_1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 寻找绝对差不超过限制的最长连续子数组
         * @param A 给定数组
         * @param limit 给定限制
         * @return 绝对差不超过限制的最长连续子数组的长度
         */
        public int longestSubarray(int[] A, int limit) {
            int i = 0, j;
            // 使用TreeMap来维护数组中元素的出现次数
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (j = 0; j < A.length; j++) {
                // 将当前元素加入TreeMap
                map.put(A[j], 1 + map.getOrDefault(A[j], 0));

                // 如果当前最大值与最小值的差超过限制，缩小窗口
                while (map.lastEntry().getKey() - map.firstEntry().getKey() > limit) {
                    map.put(A[i], map.get(A[i]) - 1);

                    // 如果某个元素的出现次数为0，将其移出TreeMap
                    if (map.get(A[i]) == 0)
                        map.remove(A[i]);
                    i++;
                }
            }

            // 返回最长连续子数组的长度
            return j - i;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit.Solution solution = new LeetCode_1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit().new Solution();

        // 测试代码
        int[] input = {8, 2, 4, 7};
        int limit = 4;
        int result = solution.longestSubarray(input, limit);
        System.out.println("绝对差不超过限制的最长连续子数组的长度: " + result);
    }
}

/**
Given an array of integers nums and an integer limit, return the size of the 
longest non-empty subarray such that the absolute difference between any two 
elements of this subarray is less than or equal to limit. 

 
 Example 1: 

 
Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.
 

 Example 2: 

 
Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute 
diff is |2-7| = 5 <= 5.
 

 Example 3: 

 
Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 1 <= nums[i] <= 10⁹ 
 0 <= limit <= 10⁹ 
 

 Related Topics Array Queue Sliding Window Heap (Priority Queue) Ordered Set 
Monotonic Queue 👍 3206 👎 132

*/
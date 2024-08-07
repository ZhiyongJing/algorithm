package leetcode.question.dp;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  1983. Widest Pair of Indices With Equal Range Sum
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目描述
 *
 * **题目编号：1983. Widest Pair of Indices With Equal Range Sum**
 *
 * **问题描述：**
 *
 * 给定两个数组 `nums1` 和 `nums2`，它们的长度相同。你需要找到满足以下条件的最宽索引对 `[i, j]`，使得 `nums1` 和 `nums2` 在这两个索引之间的子数组的范围和相等，即：
 *
 * \[ \text{sum}(nums1[i:j]) - \text{sum}(nums2[i:j]) = 0 \]
 *
 * **目标**：找到这样的索引对 `[i, j]` 的最大宽度 `j - i`。
 *
 * ### 解题思路
 *
 * 1. **前缀和转换**：
 *
 *    - **定义前缀和差**：我们将 `nums1` 和 `nums2` 中的差值计算为一个新数组 `diff`，其中 `diff[i] = nums1[i] - nums2[i]`。通过这种方式，我们需要找到子数组 `diff[i:j]` 的总和为 0 的最宽的子数组 `[i, j]`。
 *
 * 2. **前缀和累积**：
 *
 *    - **计算前缀和**：计算 `diff` 数组的前缀和 `sum`。我们希望找到两个位置 `i` 和 `j`，使得 `sum[j] - sum[i] = 0`，也就是 `sum[j] = sum[i]`。这样，子数组 `diff[i+1:j]` 的总和为 0。
 *
 * 3. **使用哈希表**：
 *
 *    - **记录前缀和及其最早出现的索引**：使用哈希表来记录每个前缀和第一次出现的位置。对于每个前缀和值，检查它是否已经出现在哈希表中：
 *      - 如果是，计算当前位置和前缀和第一次出现位置之间的距离。
 *      - 更新最宽的子数组宽度。
 *    - **更新哈希表**：将当前前缀和及其索引存入哈希表，记录它第一次出现的位置。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**：`O(N)`
 *   遍历数组一次，计算前缀和并更新哈希表的操作都是 `O(N)`。因此，整体时间复杂度为 `O(N)`。
 *
 * - **空间复杂度**：`O(1)`
 *   虽然使用了哈希表来存储前缀和及其索引，但哈希表的大小在实际使用中是与数组长度相关的。在最坏情况下，哈希表的大小可能与数组长度成正比，通常认为空间复杂度为常量级 `O(1)`。
 */

public class LeetCode_1983_WidestPairOfIndicesWithEqualRangeSum{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 计算满足范围和相等的最宽索引对
        public int widestPairOfIndices(int[] nums1, int[] nums2) {
            // 使用哈希表存储前缀和及其第一次出现的索引
            Map<Integer, Integer> hm = new HashMap<>();
            // 初始化哈希表，0 表示前缀和为 0 时，索引为 -1
            hm.put(0, -1);
            int result = 0;  // 记录结果：最宽的索引对的宽度
            int sum = 0;     // 前缀和

            // 遍历数组
            for (int i = 0; i < nums1.length; i++) {
                // 计算当前位置的前缀和：nums1[i] - nums2[i]
                sum += (nums1[i] - nums2[i]);

                // 如果当前前缀和已经在哈希表中出现过
                // 更新 result 为当前宽度与之前记录的最大宽度中的较大值
                result = Math.max(result, i - hm.getOrDefault(sum, 1000000));

                // 将当前前缀和及其索引存入哈希表
                // 如果当前前缀和没有出现过，则存储其第一次出现的索引
                hm.put(sum, hm.getOrDefault(sum, i));
            }

            // 返回最宽的索引对宽度
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1983_WidestPairOfIndicesWithEqualRangeSum().new Solution();

        // 测试样例 1
        int[] nums1_1 = {1, 2, 3, 4, 5};
        int[] nums2_1 = {2, 1, 3, 2, 1};
        System.out.println("测试样例 1：");
        // 期望输出：2，[0, 2] 是最宽的索引对，它们的范围和相等
        System.out.println("最宽索引对的宽度: " + solution.widestPairOfIndices(nums1_1, nums2_1));

        // 测试样例 2
        int[] nums1_2 = {1, 2, 3, 4};
        int[] nums2_2 = {1, 2, 3, 4};
        System.out.println("测试样例 2：");
        // 期望输出：4，整个数组的范围和相等，因此索引对 [0, 3] 是最宽的
        System.out.println("最宽索引对的宽度: " + solution.widestPairOfIndices(nums1_2, nums2_2));

        // 测试样例 3
        int[] nums1_3 = {4, 2, 1, 5, 3};
        int[] nums2_3 = {1, 4, 3, 2, 1};
        System.out.println("测试样例 3：");
        // 期望输出：1，[1, 2] 是最宽的索引对，它们的范围和相等
        System.out.println("最宽索引对的宽度: " + solution.widestPairOfIndices(nums1_3, nums2_3));
    }
}

/**
You are given two 0-indexed binary arrays nums1 and nums2. Find the widest pair 
of indices (i, j) such that i <= j and nums1[i] + nums1[i+1] + ... + nums1[j] ==
 nums2[i] + nums2[i+1] + ... + nums2[j]. 

 The widest pair of indices is the pair with the largest distance between i and 
j. The distance between a pair of indices is defined as j - i + 1. 

 Return the distance of the widest pair of indices. If no pair of indices meets 
the conditions, return 0. 

 
 Example 1: 

 
Input: nums1 = [1,1,0,1], nums2 = [0,1,1,0]
Output: 3
Explanation:
If i = 1 and j = 3:
nums1[1] + nums1[2] + nums1[3] = 1 + 0 + 1 = 2.
nums2[1] + nums2[2] + nums2[3] = 1 + 1 + 0 = 2.
The distance between i and j is j - i + 1 = 3 - 1 + 1 = 3.
 

 Example 2: 

 
Input: nums1 = [0,1], nums2 = [1,1]
Output: 1
Explanation:
If i = 1 and j = 1:
nums1[1] = 1.
nums2[1] = 1.
The distance between i and j is j - i + 1 = 1 - 1 + 1 = 1.
 

 Example 3: 

 
Input: nums1 = [0], nums2 = [1]
Output: 0
Explanation:
There are no pairs of indices that meet the requirements.
 

 
 Constraints: 

 
 n == nums1.length == nums2.length 
 1 <= n <= 10⁵ 
 nums1[i] is either 0 or 1. 
 nums2[i] is either 0 or 1. 
 

 Related Topics Array Hash Table Prefix Sum 👍 94 👎 2

*/
package leetcode.question.sort;

import java.util.Arrays;

/**
 *@Question:  164. Maximum Gap
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 49.38%
 *@Time Complexity: O(N) (使用桶排序，每个元素仅访问一次)
 *@Space Complexity: O(B) (B 为桶的数量，每个桶仅存最大和最小值)
 */
/**
 * 题目描述：
 * LeetCode 164. Maximum Gap
 * 给定一个 **无序** 的整数数组 `nums`，要求找出 **排序后** 相邻元素之间的最大差值。
 *
 * 约束：
 * - 你可以假设所有元素都是 **非负整数**，且 `nums.length >= 2`。
 * - **要求时间复杂度必须是 O(N)**（不能使用 O(N log N) 的排序算法）。
 *
 * 示例 1：
 * 输入: nums = [3,6,9,1]
 * 输出: 3
 * 解释:
 * - 排序后: [1,3,6,9]
 * - 最大间距在 [6,9] 之间，值为 `9 - 6 = 3`
 *
 * 示例 2：
 * 输入: nums = [10]
 * 输出: 0
 * 解释:
 * - 只有一个元素，无法计算间距，返回 0。
 *
 * 示例 3：
 * 输入: nums = [1,10000000]
 * 输出: 9999999
 * 解释:
 * - 排序后: [1, 10000000]
 * - 最大间距在 [1, 10000000] 之间，值为 `10000000 - 1 = 9999999`
 */

/**
 * 解题思路：
 * 题目要求时间复杂度 O(N)，这意味着我们不能使用 O(N log N) 的排序算法（如快排或归并排序）。
 * **桶排序（Bucket Sort）** 是本题的最优解法，主要思路如下：
 *
 * 1. **找出最大值和最小值**
 *    - 遍历 `nums`，计算 `mini = min(nums)` 和 `maxi = max(nums)`。
 *    - 如果 `mini == maxi`，说明数组中所有数相同，返回 `0`。
 *
 * 2. **确定桶的大小和数量**
 *    - 设数组长度为 `N`，最大间距 `gap` 可能出现在 `N-1` 个相邻元素之间。
 *    - 设桶的大小 `bucketSize = Math.max(1, (maxi - mini) / (N - 1))`：
 *      - 这样可以确保所有 `N` 个数能尽量均匀地分布到 `N-1` 个桶中。
 *    - 计算桶的个数 `bucketNum = (maxi - mini) / bucketSize + 1`，确保所有元素能被分配到桶。
 *
 * 3. **将数字分配到桶中**
 *    - 初始化 `bucketNum` 个桶，每个桶存储：
 *      - `minval`：桶中的最小值
 *      - `maxval`：桶中的最大值
 *    - 遍历 `nums`，计算每个 `num` 所属的桶索引：
 *      ```
 *      bucketIdx = (num - mini) / bucketSize
 *      ```
 *    - 更新桶的最小值 `minval` 和最大值 `maxval`。
 *
 * 4. **计算最大间距**
 *    - 由于 **同一个桶中的元素间距较小**，最大间距一定出现在 **相邻非空桶之间**。
 *    - 遍历所有桶：
 *      - 如果某个桶是空的，跳过。
 *      - 计算当前桶的 `minval` 与 **前一个非空桶的 `maxval`** 之间的差值。
 *      - 记录 `maxGap` 的最大值。
 *
 * 5. **返回 `maxGap`**
 *    - `maxGap` 就是排序后相邻元素的最大差值。
 *
 * **举例分析**
 * 假设输入 `nums = [3,6,9,1]`：
 * - **步骤 1**: 找最大最小值
 *   ```
 *   mini = 1, maxi = 9
 *   ```
 * - **步骤 2**: 计算桶大小和数量
 *   ```
 *   bucketSize = (9 - 1) / (4 - 1) = 8 / 3 = 2
 *   bucketNum = (9 - 1) / 2 + 1 = 5
 *   ```
 * - **步骤 3**: 归类到桶
 *   ```
 *   1  -> 桶 0
 *   3  -> 桶 1
 *   6  -> 桶 2
 *   9  -> 桶 4
 *   ```
 * - **步骤 4**: 计算最大间距
 *   ```
 *   最大间距 = max(3-1, 6-3, 9-6) = 3
 *   ```
 * - **最终返回** `3`
 */

/**
 * 时间和空间复杂度分析：
 *
 * 1. **时间复杂度：O(N)**
 *    - 计算 `mini` 和 `maxi` 需要 `O(N)`。
 *    - 计算 `bucketSize` 和 `bucketNum` 需要 `O(1)`。
 *    - 遍历 `nums` 将元素归类到桶需要 `O(N)`。
 *    - 遍历桶计算 `maxGap` 需要 `O(B)`（`B` 为桶的数量，最多 `N-1`）。
 *    - **总体复杂度：O(N) + O(N) + O(N) + O(N) = O(N)**。
 *
 * 2. **空间复杂度：O(B) = O(N)**
 *    - 需要 `O(B)` 额外空间存储桶，每个桶存储最大和最小值 `O(2B)`。
 *    - **总体空间复杂度：O(N)**（每个桶最多存储 2 个值）。
 */


public class LeetCode_164_MaximumGap{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
         class Bucket {
            // 标记桶是否被使用
            public boolean used = false;
            // 记录桶内的最小值
            public int minval = Integer.MAX_VALUE;
            // 记录桶内的最大值
            public int maxval = Integer.MIN_VALUE;
        }

        public int maximumGap(int[] nums) {
            // 如果数组为空或元素不足两个，无法计算间距，直接返回 0
            if (nums == null || nums.length < 2) return 0;

            // 计算数组中的最小值和最大值
            int mini = Arrays.stream(nums).min().getAsInt(),
                    maxi = Arrays.stream(nums).max().getAsInt();

            // 计算桶的大小（即最小的可能间距），至少为 1
            int bucketSize = Math.max(1, (maxi - mini) / (nums.length - 1));
            // 计算所需桶的数量
            int bucketNum = (maxi - mini) / bucketSize + 1;
            // 初始化桶数组
            Bucket[] buckets = new Bucket[bucketNum];

            // 遍历数组，将每个数字放入正确的桶中
            for (int num : nums) {
                // 计算当前数字所属的桶索引
                int bucketIdx = (num - mini) / bucketSize;
                // 如果该桶为空，则初始化
                if (buckets[bucketIdx] == null) buckets[bucketIdx] = new Bucket();

                // 标记该桶已被使用
                buckets[bucketIdx].used = true;
                // 更新桶的最小值和最大值
                buckets[bucketIdx].minval = Math.min(num, buckets[bucketIdx].minval);
                buckets[bucketIdx].maxval = Math.max(num, buckets[bucketIdx].maxval);

            }
            System.out.println(Arrays.toString(nums));
            for(Bucket bucket: buckets){
                if(bucket != null){
                    System.out.println("bucket used: " +bucket.used +", maxVal: " + bucket.maxval + ", minVal:" + bucket.minval);

                }
            }


            // 计算最大间距
            int prevBucketMax = mini, maxGap = 0;
            for (Bucket bucket : buckets) {
                // 跳过未被使用的桶
                if (bucket == null || !bucket.used) continue;

                // 计算当前桶的最小值与上一个桶的最大值之间的间距
                maxGap = Math.max(maxGap, bucket.minval - prevBucketMax);
                // 更新上一个桶的最大值
                prevBucketMax = bucket.maxval;
            }

            return maxGap;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_164_MaximumGap().new Solution();

        // 测试用例 1
//        int[] nums1 = {3, 6, 9, 1};
//        System.out.println(solution.maximumGap(nums1));
//        // 期望输出: 3 (最大间距在 [6,9] 之间)
//
//        // 测试用例 2
//        int[] nums2 = {10};
//        System.out.println(solution.maximumGap(nums2));
//        // 期望输出: 0 (只有一个元素，间距为 0)
//
//        // 测试用例 3
//        int[] nums3 = {1, 10000000};
//        System.out.println(solution.maximumGap(nums3));
//        // 期望输出: 9999999 (只有两个元素，间距最大)

        // 测试用例 4
        int[] nums4 = {1, 3, 6, 9, 15};
        System.out.println(solution.maximumGap(nums4));
        // 期望输出: 6 (最大间距在 [9,15] 之间)
    }
}

/**
Given an integer array nums, return the maximum difference between two 
successive elements in its sorted form. If the array contains less than two elements, 
return 0. 

 You must write an algorithm that runs in linear time and uses linear extra 
space. 

 
 Example 1: 

 
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) 
has the maximum difference 3.
 

 Example 2: 

 
Input: nums = [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 0 <= nums[i] <= 10⁹ 
 

 Related Topics Array Sorting Bucket Sort Radix Sort 👍 3392 👎 415

*/
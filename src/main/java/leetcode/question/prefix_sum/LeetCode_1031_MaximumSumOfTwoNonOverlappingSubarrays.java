package leetcode.question.prefix_sum;

/**
 *@Question:  1031. Maximum Sum of Two Non-Overlapping Subarrays
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 45.15%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求找到两个非重叠子数组的最大和，其中一个子数组的长度是 `L`，另一个子数组的长度是 `M`。
 *
 * #### 解法
 *
 * 1. **前缀和数组：** 首先，我们通过计算原数组的前缀和数组 `sums`，其中 `sums[i]` 表示前 `i` 个元素的和。
 *
 * 2. **遍历计算：** 我们使用两个循环分别表示两个子数组的起始位置。对于长度为 `L` 的子数组，我们计算从 `i` 到 `i+L` 的和，
 * 即 `sums[i+L] - sums[i]`，同时维护 `maxLval` 记录遍历过程中的最大和。然后，对于长度为 `M` 的子数组，我们计算从 `j` 到 `j+M` 的和，
 * 即 `sums[j+M] - sums[j]`，同时维护 `maxRval` 记录遍历过程中的最大和。
 *
 * 3. **计算结果：** 我们遍历完成后，最终的答案就是两个子数组的和的最大值。为了确保两个子数组不重叠，我们在遍历过程中维护一个变量 `ans`，
 * 更新为当前长度为 `M` 的子数组的和与 `maxLval` 之和的最大值。
 * 同时，再更新 `maxLval` 为长度为 `L` 的子数组的和与 `maxLval` 之和的最大值。
 *
 * #### 时间复杂度
 *
 * 遍历过程中的两个循环都是线性的，因此时间复杂度为 O(N)，其中 N 是数组的长度。
 *
 * #### 空间复杂度
 *
 * 额外使用了一个前缀和数组 `sums`，空间复杂度为 O(N)。
 */

public class LeetCode_1031_MaximumSumOfTwoNonOverlappingSubarrays{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSumTwoNoOverlap(int[] A, int L, int M) {
            // 计算前缀和
            int sums[] = new int[A.length+1];

            for(int i=1;i<=A.length;i++)
                sums[i] = A[i-1]+sums[i-1];

            int maxLval = 0;
            int ans=0;
            // 遍历计算以 L 为长度的子数组的最大和
            for(int i=L;i<=A.length-M;i++)
            {
                maxLval = Math.max(maxLval,sums[i]-sums[i-L]);
                // 计算以 M 为长度的子数组的和，并更新最终结果
                ans = Math.max(ans,sums[i+M]-sums[i]+maxLval);
            }
            int maxRval = 0 ;
            // 重新初始化 maxRval
            for(int i=M;i<=A.length-L;i++)
            {
                maxRval = Math.max(maxRval,sums[i]-sums[i-M]);
                // 计算以 L 为长度的子数组的和，并更新最终结果
                ans = Math.max(ans,sums[i+L]-sums[i]+maxRval);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1031_MaximumSumOfTwoNonOverlappingSubarrays().new Solution();
        // 测试代码
        int[] nums1 = {0, 6, 5, 2, 2, 5, 1, 9, 4};
        int firstLen1 = 1;
        int secondLen1 = 2;
        System.out.println(solution.maxSumTwoNoOverlap(nums1, firstLen1, secondLen1));  // 应该输出 20

        int[] nums2 = {3, 8, 1, 3, 2, 1, 8, 9, 0};
        int firstLen2 = 3;
        int secondLen2 = 2;
        System.out.println(solution.maxSumTwoNoOverlap(nums2, firstLen2, secondLen2));  // 应该输出 29

        int[] nums3 = {2, 1, 5, 6, 0, 9, 5, 0, 3, 8};
        int firstLen3 = 4;
        int secondLen3 = 3;
        System.out.println(solution.maxSumTwoNoOverlap(nums3, firstLen3, secondLen3));  // 应该输出 31
    }
}
/**
 Given an integer array nums and two integers firstLen and secondLen, return the
 maximum sum of elements in two non-overlapping subarrays with lengths firstLen
 and secondLen.

 The array with length firstLen could occur before or after the array with 
 length secondLen, but they have to be non-overlapping.

 A subarray is a contiguous part of an array. 


 Example 1: 


 Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
 Output: 20
 Explanation: One choice of subarrays is [9] with length 1, and [6,5] with
 length 2.


 Example 2: 


 Input: nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
 Output: 29
 Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with
 length 2.


 Example 3: 


 Input: nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
 Output: 31
 Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [0,3,8]
 with length 3.



 Constraints: 


 1 <= firstLen, secondLen <= 1000 
 2 <= firstLen + secondLen <= 1000 
 firstLen + secondLen <= nums.length <= 1000 
 0 <= nums[i] <= 1000 


 Related Topics Array Dynamic Programming Sliding Window 👍 2486 👎 80

 */

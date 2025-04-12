package leetcode.question.greedy;

import java.util.Arrays;

/**
 *@Question:  561. Array Partition
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 41.1%
 *@Time  Complexity: O(NlogN)
 *@Space Complexity: O(N)
 */

public class LeetCode_561_ArrayPartition{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int arrayPairSum(int[] nums) {
            // 将数组按升序排序
            Arrays.sort(nums);
            // 初始化最大总和为 0
            int maxSum = 0;
            // 每两个数一组，取较小值累加，即取排序数组中的偶数位元素
            for (int i = 0; i < nums.length; i += 2) {
                maxSum += nums[i];
            }
            // 返回最大总和
            return maxSum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_561_ArrayPartition().new Solution();

        // 测试用例 1：输入 [1,4,3,2]，排序后为 [1,2,3,4]，配对为 [1,2] [3,4]，最小值之和为 1+3=4
        int[] nums1 = {1, 4, 3, 2};
        System.out.println("测试用例 1 结果：" + solution.arrayPairSum(nums1)); // 预期输出 4

        // 测试用例 2：输入 [6,2,6,5,1,2]，排序后为 [1,2,2,5,6,6]，配对为 [1,2] [2,5] [6,6]，结果为 1+2+6=9
        int[] nums2 = {6, 2, 6, 5, 1, 2};
        System.out.println("测试用例 2 结果：" + solution.arrayPairSum(nums2)); // 预期输出 9

        // 测试用例 3：输入 [7,3]，结果为 3
        int[] nums3 = {7, 3};
        System.out.println("测试用例 3 结果：" + solution.arrayPairSum(nums3)); // 预期输出 3
    }
}

/**
Given an integer array nums of 2n integers, group these integers into n pairs (
a1, b1), (a2, b2), ..., (an, bn) such that the sum of min(ai, bi) for all i is 
maximized. Return the maximized sum. 

 
 Example 1: 

 
Input: nums = [1,4,3,2]
Output: 4
Explanation: All possible pairings (ignoring the ordering of elements) are:
1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
So the maximum possible sum is 4. 

 Example 2: 

 
Input: nums = [6,2,6,5,1,2]
Output: 9
Explanation: The optimal pairing is (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5
) + min(6, 6) = 1 + 2 + 6 = 9.
 

 
 Constraints: 

 
 1 <= n <= 10⁴ 
 nums.length == 2 * n 
 -10⁴ <= nums[i] <= 10⁴ 
 

 Related Topics Array Greedy Sorting Counting Sort 👍 2183 👎 281

*/
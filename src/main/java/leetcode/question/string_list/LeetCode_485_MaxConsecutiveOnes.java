package leetcode.question.string_list;

/**
 *@Question:  485. Max Consecutive Ones
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 58.34%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

public class LeetCode_485_MaxConsecutiveOnes{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {
            // 当前连续 1 的计数器
            int count = 0;
            // 当前记录的最大连续 1 的数量
            int maxCount = 0;

            // 遍历整个数组
            for(int i = 0; i < nums.length; i++) {
                // 如果当前元素是 1，就递增连续计数器
                if(nums[i] == 1) {
                    // 当前计数 +1
                    count += 1;
                } else {
                    // 如果遇到 0，更新最大值，并重置当前计数器
                    maxCount = Math.max(maxCount, count);
                    count = 0;
                }
            }

            // 避免数组末尾是连续 1，循环结束后未更新 maxCount 的情况
            return Math.max(maxCount, count);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_485_MaxConsecutiveOnes().new Solution();

        // ✅ 测试用例 1：正常情况
        System.out.println(solution.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1})); // 输出：3

        // ✅ 测试用例 2：全是 1
        System.out.println(solution.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 1})); // 输出：5

        // ✅ 测试用例 3：全是 0
        System.out.println(solution.findMaxConsecutiveOnes(new int[]{0, 0, 0})); // 输出：0

        // ✅ 测试用例 4：交替 0 和 1
        System.out.println(solution.findMaxConsecutiveOnes(new int[]{1, 0, 1, 0, 1})); // 输出：1

        // ✅ 测试用例 5：连续 1 在末尾
        System.out.println(solution.findMaxConsecutiveOnes(new int[]{0, 1, 1, 1})); // 输出：3

        // ✅ 测试用例 6：数组为空（题目保证至少有一个元素，此处仅作稳健性参考）
        System.out.println(solution.findMaxConsecutiveOnes(new int[]{})); // 输出：0
    }
}

/**
Given a binary array nums, return the maximum number of consecutive 1's in the 
array. 

 
 Example 1: 

 
Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. 
The maximum number of consecutive 1s is 3.
 

 Example 2: 

 
Input: nums = [1,0,1,1,0,1]
Output: 2
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 nums[i] is either 0 or 1. 
 

 Related Topics Array 👍 6157 👎 466

*/
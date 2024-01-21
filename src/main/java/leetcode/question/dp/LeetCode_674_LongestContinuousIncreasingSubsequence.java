package leetcode.question.dp;

import java.util.Arrays;

/**
  *@Question:  674. Longest Continuous Increasing Subsequence     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 30.48%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_674_LongestContinuousIncreasingSubsequence{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //Solution1: sliding window
    public int findLengthOfLCIS1(int[] nums) {
        int ans = 0;
        if(nums == null) return ans;
        int left = 0, right = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i-1] >= nums[i]){
                left = i;
            }
            else {
                right = i;
                ans = Math.max(ans, right - left + 1);

            }
        }
        return ans;
        
    }

    //Solution2: Bottom-Up DP
    public int findLengthOfLCIS (int[] nums) {
        if(nums == null || nums.length ==0) return 0;
        //定义一个动态规划数组 dp，其中 dp[i] 表示以第 i 个元素为结尾的最长连续递增子序列的长度。
        int[] dp = new int[nums.length];
        int n = nums.length;
        Arrays.fill(dp, 1);

        int maxLen = 1;
        //从第二个开始
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    //Solution3: dp 基于 solution2的空间优化
    public int findLengthOfLCIS3(int[] nums) {
        if(nums == null || nums.length ==0) return 0;
        int n = nums.length;
        int maxLen = 1;
        int len = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                len = len + 1;
            } else {
                len = 1;
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_674_LongestContinuousIncreasingSubsequence().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given an unsorted array of integers nums, return the length of the longest 
continuous increasing subsequence (i.e. subarray). The subsequence must be strictly 
increasing. 

 A continuous increasing subsequence is defined by two indices l and r (l < r) 
such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l 
<= i < r, nums[i] < nums[i + 1]. 

 
 Example 1: 

 
Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with 
length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as 
elements 5 and 7 are separated by element
4.
 

 Example 2: 

 
Input: nums = [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2] with length 1.
 Note that it must be strictly
increasing.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -10⁹ <= nums[i] <= 10⁹ 
 

 Related Topics Array 👍 2306 👎 179

*/
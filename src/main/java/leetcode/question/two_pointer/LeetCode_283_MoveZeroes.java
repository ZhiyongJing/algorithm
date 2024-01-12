package leetcode.question.two_pointer;

/**
  *@Question:  283. Move Zeroes     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 80.28%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

public class LeetCode_283_MoveZeroes{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[count] = nums[i];
                count++;
            }
        }
        while (count<nums.length)nums[count++]=0;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_283_MoveZeroes().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given an integer array nums, move all 0's to the end of it while maintaining 
the relative order of the non-zero elements. 

 Note that you must do this in-place without making a copy of the array. 

 
 Example 1: 
 Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
 
 Example 2: 
 Input: nums = [0]
Output: [0]
 
 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 

 
Follow up: Could you minimize the total number of operations done?

 Related Topics Array Two Pointers 👍 15844 👎 411

*/

package leetcode.question.sort;

import java.util.Arrays;

/**
  *@Question:  179. Largest Number     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 71.31%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_179_LargestNumber{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String largestNumber(int[] nums) {
        // 1.Convert to Integer array since Arrays.sort(A,T) forces that
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        // 2.Sort in descending order
        Arrays.sort(strs, (s1, s2) -> ((s2 + s1).compareTo(s1 + s2)));

        // 3.Append together and check final result
        String result = String.join("", strs);
        if (result.isEmpty() || result.charAt(0) == '0') {
            return "0";
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_179_LargestNumber().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a list of non-negative integers nums, arrange them such that they form 
the largest number and return it. 

 Since the result may be very large, so you need to return a string instead of 
an integer. 

 
 Example 1: 

 
Input: nums = [10,2]
Output: "210"
 

 Example 2: 

 
Input: nums = [3,30,34,5,9]
Output: "9534330"
 

 
 Constraints: 

 
 1 <= nums.length <= 100 
 0 <= nums[i] <= 10⁹ 
 

 Related Topics Array String Greedy Sorting 👍 7737 👎 625

*/

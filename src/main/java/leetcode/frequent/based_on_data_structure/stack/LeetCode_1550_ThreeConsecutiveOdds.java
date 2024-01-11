package leetcode.frequent.based_on_data_structure.stack;

/**
  *@Question:  1550. Three Consecutive Odds     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 0.0%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_1550_ThreeConsecutiveOdds{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {

        for(int i=1;i<arr.length-1;i++)
            if(arr[i]%2!=0&&arr[i+1]%2!=0&&arr[i-1]%2!=0)return true;
        return false;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_1550_ThreeConsecutiveOdds().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given an integer array arr, return true if there are three consecutive odd 
numbers in the array. Otherwise, return false.

 
 Example 1: 

 
Input: arr = [2,6,4,1]
Output: false
Explanation: There are no three consecutive odds.
 

 Example 2: 

 
Input: arr = [1,2,34,3,4,5,7,23,12]
Output: true
Explanation: [5,7,23] are three consecutive odds.
 

 
 Constraints: 

 
 1 <= arr.length <= 1000 
 1 <= arr[i] <= 1000 
 

 Related Topics Array 👍 625 👎 62

*/
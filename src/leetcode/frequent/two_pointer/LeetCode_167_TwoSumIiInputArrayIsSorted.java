package leetcode.frequent.two_pointer;

/**
  *@Question:  167. Two Sum II - Input Array Is Sorted     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 50.91%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_167_TwoSumIiInputArrayIsSorted{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length-1;
        while(left < right){
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                return new int[]{left+1, right+1};
            }
            if(sum < target){
                left++;
            }
            else right--;
        }
        return new int[]{-1, -1};
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_167_TwoSumIiInputArrayIsSorted().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a 1-indexed array of integers numbers that is already sorted in non-
decreasing order, find two numbers such that they add up to a specific target number. 
Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 <
 index2 <= numbers.length. 

 Return the indices of the two numbers, index1 and index2, added by one as an 
integer array [index1, index2] of length 2. 

 The tests are generated such that there is exactly one solution. You may not 
use the same element twice. 

 Your solution must use only constant extra space. 

 
 Example 1: 

 
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We 
return [1, 2].
 

 Example 2: 

 
Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We 
return [1, 3].
 

 Example 3: 

 
Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We 
return [1, 2].
 

 
 Constraints: 

 
 2 <= numbers.length <= 3 * 10⁴ 
 -1000 <= numbers[i] <= 1000 
 numbers is sorted in non-decreasing order. 
 -1000 <= target <= 1000 
 The tests are generated such that there is exactly one solution. 
 

 Related Topics Array Two Pointers Binary Search 👍 11134 👎 1351

*/

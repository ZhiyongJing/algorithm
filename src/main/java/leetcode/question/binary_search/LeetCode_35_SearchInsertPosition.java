package leetcode.question.binary_search;

/**
  *@Question:  35. Search Insert Position
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 65.6%
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_35_SearchInsertPosition{

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        if (nums[nums.length - 1] < target) return  nums.length;
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + ( right - left )/2;
            if(nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        if (nums[left] >= target){
            return  left;
        }
        return - 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_35_SearchInsertPosition().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a sorted array of distinct integers and a target value, return the index 
if the target is found. If not, return the index where it would be if it were 
inserted in order. 

 You must write an algorithm with O(log n) runtime complexity. 

 
 Example 1: 

 
Input: nums = [1,3,5,6], target = 5
Output: 2
 

 Example 2: 

 
Input: nums = [1,3,5,6], target = 2
Output: 1
 

 Example 3: 

 
Input: nums = [1,3,5,6], target = 7
Output: 4
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -10⁴ <= nums[i] <= 10⁴ 
 nums contains distinct values sorted in ascending order. 
 -10⁴ <= target <= 10⁴ 
 

 Related Topics Array Binary Search 👍 16795 👎 789

*/
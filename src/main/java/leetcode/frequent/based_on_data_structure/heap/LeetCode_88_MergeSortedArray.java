package leetcode.frequent.based_on_data_structure.heap;

/**
  *@Question:  88. Merge Sorted Array     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 97.96%      
  *@Time  Complexity: O(N+M)
  *@Space Complexity: O(1)
 */

public class LeetCode_88_MergeSortedArray{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Set p1 and p2 to point to the end of their respective arrays.
        int p1 = m - 1;
        int p2 = n - 1;

        // And move p backward through the array, each time writing
        // the smallest value pointed at by p1 or p2.
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_88_MergeSortedArray().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given two integer arrays nums1 and nums2, sorted in non-decreasing 
order, and two integers m and n, representing the number of elements in nums1 and 
nums2 respectively. 

 Merge nums1 and nums2 into a single array sorted in non-decreasing order. 

 The final sorted array should not be returned by the function, but instead be 
stored inside the array nums1. To accommodate this, nums1 has a length of m + n, 
where the first m elements denote the elements that should be merged, and the 
last n elements are set to 0 and should be ignored. nums2 has a length of n. 

 
 Example 1: 

 
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming 
from nums1.
 

 Example 2: 

 
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
 

 Example 3: 

 
Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to 
ensure the merge result can fit in nums1.
 

 
 Constraints: 

 
 nums1.length == m + n 
 nums2.length == n 
 0 <= m, n <= 200 
 1 <= m + n <= 200 
 -10⁹ <= nums1[i], nums2[j] <= 10⁹ 
 

 
 Follow up: Can you come up with an algorithm that runs in O(m + n) time? 

 Related Topics Array Two Pointers Sorting 👍 13660 👎 1626

*/
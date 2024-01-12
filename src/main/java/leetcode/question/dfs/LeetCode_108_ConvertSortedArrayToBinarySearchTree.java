package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
  *@Question:  108. Convert Sorted Array to Binary Search Tree     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 41.69%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_108_ConvertSortedArrayToBinarySearchTree{
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int[] nums;

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        // always choose left middle node as a root
        int p = (left + right) / 2;

        // preorder traversal: node -> left -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p - 1);
        root.right = helper(p + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return helper(0, nums.length - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_108_ConvertSortedArrayToBinarySearchTree().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given an integer array nums where the elements are sorted in ascending order, 
convert it to a height-balanced binary search tree. 

 
 Example 1: 
 
 
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

 

 Example 2: 
 
 
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -10⁴ <= nums[i] <= 10⁴ 
 nums is sorted in a strictly increasing order. 
 

 Related Topics Array Divide and Conquer Tree Binary Search Tree Binary Tree 👍 
10505 👎 527

*/

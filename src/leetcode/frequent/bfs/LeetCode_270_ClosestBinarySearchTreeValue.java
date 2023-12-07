package leetcode.frequent.bfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
  *@Question:  270. Closest Binary Search Tree Value     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 27.98%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_270_ClosestBinarySearchTreeValue{
    
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
    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int closestValue(TreeNode root, double target) {
        List<Integer> nums = new ArrayList();
        inorder(root, nums);
        return Collections.min(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_270_ClosestBinarySearchTreeValue().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary search tree and a target value, return the value in 
the BST that is closest to the target. If there are multiple answers, print the 
smallest. 

 
 Example 1: 
 
 
Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
 

 Example 2: 

 
Input: root = [1], target = 4.428571
Output: 1
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁴]. 
 0 <= Node.val <= 10⁹ 
 -10⁹ <= target <= 10⁹ 
 

 Related Topics Binary Search Tree Depth-First Search Binary Search Tree Binary 
Tree 👍 1733 👎 115

*/

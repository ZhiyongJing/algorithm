package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
/**
  *@Question:  94. Binary Tree Inorder Traversal     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 47.42%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

public class LeetCode_94_BinaryTreeInorderTraversal{
    
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_94_BinaryTreeInorderTraversal().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary tree, return the inorder traversal of its nodes' 
values. 

 
 Example 1: 
 
 
Input: root = [1,null,2,3]
Output: [1,3,2]
 

 Example 2: 

 
Input: root = []
Output: []
 

 Example 3: 

 
Input: root = [1]
Output: [1]
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 100]. 
 -100 <= Node.val <= 100 
 

 
Follow up: Recursive solution is trivial, could you do it iteratively?

 Related Topics Stack Tree Depth-First Search Binary Tree 👍 13351 👎 776

*/
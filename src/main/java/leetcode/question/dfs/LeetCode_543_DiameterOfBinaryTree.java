package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
  *@Question:  543. Diameter of Binary Tree     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 58.37%      
  *@Time Complexity: O
  *@Space Complexity: O
 */

public class LeetCode_543_DiameterOfBinaryTree{
    
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
    private int diameter;
    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }
    private int longestPath(TreeNode node){
        if(node == null) return 0;
        // recursively find the longest path in
        // both left child and right child
        int leftPath = longestPath(node.left);
        int rightPath = longestPath(node.right);

        // update the diameter if left_path plus right_path is larger
        diameter = Math.max(diameter, leftPath + rightPath);

        // return the longest one between left_path and right_path;
        // remember to add 1 for the path connecting the node and its parent
        return Math.max(leftPath, rightPath) + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_543_DiameterOfBinaryTree().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary tree, return the length of the diameter of the tree. 


 The diameter of a binary tree is the length of the longest path between any 
two nodes in a tree. This path may or may not pass through the root. 

 The length of a path between two nodes is represented by the number of edges 
between them. 

 
 Example 1: 
 
 
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 

 Example 2: 

 
Input: root = [1,2]
Output: 1
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁴]. 
 -100 <= Node.val <= 100 
 

 Related Topics Tree Depth-First Search Binary Tree 👍 12806 👎 832

*/

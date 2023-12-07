package leetcode.frequent.dfs;

import leetcode.frequent.TreeNode;

/**
  *@Question:  226. Invert Binary Tree     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 47.3%      
  *@Time Complexity: O(n)
  *@Space Complexity: O(n)
 */

public class LeetCode_226_InvertBinaryTree{
    
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_226_InvertBinaryTree().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary tree, invert the tree, and return its root. 

 
 Example 1: 
 
 
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
 

 Example 2: 
 
 
Input: root = [2,1,3]
Output: [2,3,1]
 

 Example 3: 

 
Input: root = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 100]. 
 -100 <= Node.val <= 100 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 1338
5 👎 191

*/

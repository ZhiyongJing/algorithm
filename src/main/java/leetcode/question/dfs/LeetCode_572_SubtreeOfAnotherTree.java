package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
  *@Question:  572. Subtree of Another Tree     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 34.81%      
  *@Time  Complexity: O(mn)
  *@Space Complexity: O(m+n)
 */

public class LeetCode_572_SubtreeOfAnotherTree{
    
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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        // If this node is Empty, then no tree is rooted at this Node
        // Hence, "tree rooted at node" cannot be equal to "tree rooted at subRoot"
        // "tree rooted at subRoot" will always be non-empty, as per constraints
        if (root == null) {
            return false;
        }

        // Check if the "tree rooted at root" is identical to "tree roooted at subRoot"
        if (isIdentical(root, subRoot)) {
            return true;
        }

        // If not, check for "tree rooted at root.left" and "tree rooted at root.right"
        // If either of them returns true, return true
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isIdentical(TreeNode node1, TreeNode node2) {

        // If any of the nodes is null, then both must be null
        if (node1 == null || node2 == null) {
            return node1 == null && node2 == null;
        }

        // If both nodes are non-empty, then they are identical only if
        // 1. Their values are equal
        // 2. Their left subtrees are identical
        // 3. Their right subtrees are identical
        return node1.val == node2.val && isIdentical(node1.left, node2.left) && isIdentical(node1.right, node2.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_572_SubtreeOfAnotherTree().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the roots of two binary trees root and subRoot, return true if there is a 
subtree of root with the same structure and node values of subRoot and false 
otherwise. 

 A subtree of a binary tree tree is a tree that consists of a node in tree and 
all of this node's descendants. The tree tree could also be considered as a 
subtree of itself. 

 
 Example 1: 
 
 
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true
 

 Example 2: 
 
 
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false
 

 
 Constraints: 

 
 The number of nodes in the root tree is in the range [1, 2000]. 
 The number of nodes in the subRoot tree is in the range [1, 1000]. 
 -10⁴ <= root.val <= 10⁴ 
 -10⁴ <= subRoot.val <= 10⁴ 
 

 Related Topics Tree Depth-First Search String Matching Binary Tree Hash 
Function 👍 7881 👎 460

*/

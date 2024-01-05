package leetcode.frequent.dfs;

import leetcode.util.TreeNode;

/**
  *@Question:  669. Trim a Binary Search Tree     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 29.81%      
  *@Time  Complexity: O(n)
  *@Space Complexity: O(n)
 */

public class LeetCode_669_TrimABinarySearchTree{
    
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
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return root;
        if (root.val > high) return trimBST(root.left, low, high);
        if (root.val < low) return trimBST(root.right, low, high);

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_669_TrimABinarySearchTree().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary search tree and the lowest and highest boundaries as 
low and high, trim the tree so that all its elements lies in [low, high]. 
Trimming the tree should not change the relative structure of the elements that will 
remain in the tree (i.e., any node's descendant should remain a descendant). It 
can be proven that there is a unique answer. 

 Return the root of the trimmed binary search tree. Note that the root may 
change depending on the given bounds. 

 
 Example 1: 
 
 
Input: root = [1,0,2], low = 1, high = 2
Output: [1,null,2]
 

 Example 2: 
 
 
Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
Output: [3,2,null,1]
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁴]. 
 0 <= Node.val <= 10⁴ 
 The value of each node in the tree is unique. 
 root is guaranteed to be a valid binary search tree. 
 0 <= low <= high <= 10⁴ 
 

 Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 5663 ?
? 250

*/

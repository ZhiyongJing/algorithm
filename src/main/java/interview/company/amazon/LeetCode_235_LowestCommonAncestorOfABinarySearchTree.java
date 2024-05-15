package interview.company.amazon;
//package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
 *@Question:  235. Lowest Common Ancestor of a Binary Search Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 43.41%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

public class LeetCode_235_LowestCommonAncestorOfABinarySearchTree{

//leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            // Value of p
            int pVal = p.val;

            // Value of q;
            int qVal = q.val;

            // Start from the root node of the tree
            TreeNode node = root;

            // Traverse the tree
            while (node != null) {

                // Value of ancestor/parent node.
                int parentVal = node.val;

                if (pVal > parentVal && qVal > parentVal) {
                    // If both p and q are greater than parent
                    node = node.right;
                } else if (pVal < parentVal && qVal < parentVal) {
                    // If both p and q are lesser than parent
                    node = node.left;
                } else {
                    // We have found the split point, i.e. the LCA node.
                    return node;
                }
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        leetcode.question.dfs.LeetCode_235_LowestCommonAncestorOfABinarySearchTree.Solution solution = new leetcode.question.dfs.LeetCode_235_LowestCommonAncestorOfABinarySearchTree().new Solution();
        // TO TEST
        //solution.
    }
}
/**
 Given a binary search tree (BST), find the lowest common ancestor (LCA) node of
 two given nodes in the BST.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor
 is defined between two nodes p and q as the lowest node in T that has both p and
 q as descendants (where we allow a node to be a descendant of itself).”


 Example 1:


 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 Output: 6
 Explanation: The LCA of nodes 2 and 8 is 6.


 Example 2:


 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 Output: 2
 Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of
 itself according to the LCA definition.


 Example 3:


 Input: root = [2,1], p = 2, q = 1
 Output: 2



 Constraints:


 The number of nodes in the tree is in the range [2, 10⁵].
 -10⁹ <= Node.val <= 10⁹
 All Node.val are unique.
 p != q
 p and q will exist in the BST.


 Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 10534
 👎 295

 */

/**
Given a binary search tree (BST), find the lowest common ancestor (LCA) node of 
two given nodes in the BST. 

 According to the definition of LCA on Wikipedia: “The lowest common ancestor 
is defined between two nodes p and q as the lowest node in T that has both p and 
q as descendants (where we allow a node to be a descendant of itself).” 

 
 Example 1: 
 
 
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
 

 Example 2: 
 
 
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of 
itself according to the LCA definition.
 

 Example 3: 

 
Input: root = [2,1], p = 2, q = 1
Output: 2
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [2, 10⁵]. 
 -10⁹ <= Node.val <= 10⁹ 
 All Node.val are unique. 
 p != q 
 p and q will exist in the BST. 
 

 Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 10933 
👎 308

*/
package leetcode.done;

public class LeetCode235_Lowest_Common_Ancestor_Of_A_BST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            // Value of current node or parent node.
            int parentVal = root.val;

            // Value of p
            int pVal = p.val;

            // Value of q;
            int qVal = q.val;

            if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                return lowestCommonAncestor(root.right, p, q);
            } else if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                return lowestCommonAncestor(root.left, p, q);
            } else {
                // We have found the split point, i.e. the LCA node.
                return root;
            }
        }
    }
}

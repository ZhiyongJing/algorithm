package leetcode.frequent.dfs;

import leetcode.util.TreeNode;

/**
 *@Question:  333. Largest BST Subtree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 36.1%
 *@Time  Complexity: O()
 *@Space Complexity: O()
 */

public class LeetCode_333_LargestBstSubtree{

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
        // Track previous node while doing inorder traversal.
        private TreeNode previous;

        // Function to check if given tree is a valid Binary Search Tree or not.
        private boolean isValidBST(TreeNode root) {
            // An empty tree is a valid Binary Search Tree.
            if (root == null) {
                return true;
            }


        // If left subtree is not a valid BST return false.
        if(!isValidBST(root.left)) {
            return false;
        }

        // If current node's value is not greater than the previous
        // node's value in the in-order traversal return false.
        if (previous != null && previous.val >= root.val) {
            return false;
        }

        // Update previous node to current node.
        previous = root;

        // If right subtree is not a valid BST return false.
        return isValidBST(root.right);
        }

        private int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // Add nodes in left and right subtree.
            // Add 1 and return total size.
            return 1 + countNodes(root.left) + countNodes(root.right);
        }

        public int largestBSTSubtree(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // Set previous node to NULL initially.
            previous = null;

            // If current subtree is a validBST, its children will have smaller size BST.
            if (isValidBST(root)) {
                return countNodes(root);
            }

            // Find BST in left and right subtrees of current nodes.
            return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_333_LargestBstSubtree().new Solution();
        // TO TEST
        //solution.
    }
}
/**
 Given the root of a binary tree, find the largest subtree, which is also a
 Binary Search Tree (BST), where the largest means subtree has the largest number of
 nodes.

 A Binary Search Tree (BST) is a tree in which all the nodes follow the below-
 mentioned properties:


 The left subtree values are less than the value of their parent (root) node's 
 value.
 The right subtree values are greater than the value of their parent (root) 
 node's value.


 Note: A subtree must include all of its descendants. 


 Example 1: 




 Input: root = [10,5,15,1,8,null,7]
 Output: 3
 Explanation: The Largest BST Subtree in this case is the highlighted one. The
 return value is the subtree's size, which is 3.

 Example 2: 


 Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 Output: 2



 Constraints: 


 The number of nodes in the tree is in the range [0, 10⁴]. 
 -10⁴ <= Node.val <= 10⁴ 



 Follow up: Can you figure out ways to solve it with O(n) time complexity? 

 Related Topics Dynamic Programming Tree Depth-First Search Binary Search Tree 
 Binary Tree 👍 1442 👎 119

 */

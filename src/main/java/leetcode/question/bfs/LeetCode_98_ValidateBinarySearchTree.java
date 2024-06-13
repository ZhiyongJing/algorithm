package leetcode.question.bfs;

import leetcode.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
  *@Question:  98. Validate Binary Search Tree     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 62.11%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_98_ValidateBinarySearchTree{
    
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
//    BFS

    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (prev != null && root.val <= prev) {
                return false;
            }
            prev = root.val;
            root = root.right;
        }
        return true;
    }



    // solution2: DFS
        public boolean validate(TreeNode root, Integer low, Integer high) {
            // Empty trees are valid BSTs.
            if (root == null) {
                return true;
            }

            // The current node's value must be between low and high.
            if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
                return false;
            }

            // The left and right subtree must also be valid.
            return validate(root.right, root.val, high) && validate(root.left, low, root.val);
        }

        public boolean isValidBST2(TreeNode root) {
            return validate(root, null, null);
        }

}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_98_ValidateBinarySearchTree().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary tree, determine if it is a valid binary search tree (
BST). 

 A valid BST is defined as follows: 

 
 The left subtree of a node contains only nodes with keys less than the node's 
key. 
 The right subtree of a node contains only nodes with keys greater than the 
node's key. 
 Both the left and right subtrees must also be binary search trees. 
 

 
 Example 1: 
 
 
Input: root = [2,1,3]
Output: true
 

 Example 2: 
 
 
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁴]. 
 -2³¹ <= Node.val <= 2³¹ - 1 
 

 Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 16121 
👎 1313

*/

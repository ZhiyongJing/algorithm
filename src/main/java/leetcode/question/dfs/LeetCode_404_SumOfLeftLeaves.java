package leetcode.question.dfs;
import leetcode.util.TreeNode;

/**
  *@Question:  404. Sum of Left Leaves     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 50.85999999999999%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

public class LeetCode_404_SumOfLeftLeaves{
    
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

    public int sumOfLeftLeaves(TreeNode root) {
        return processSubtree(root, false);
    }

    private int processSubtree(TreeNode subtree, boolean isLeft) {

        // Base case: This is an empty subtree.
        if (subtree == null) {
            return 0;
        }

        // Base case: This is a leaf node.
        if (subtree.left == null && subtree.right == null) {
            return isLeft ? subtree.val : 0;
        }

        // Recursive case: We need to add and return the results of the
        // left and right subtrees.
        return processSubtree(subtree.left, true) + processSubtree(subtree.right, false);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_404_SumOfLeftLeaves().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary tree, return the sum of all left leaves. 

 A leaf is a node with no children. A left leaf is a leaf that is the left 
child of another node. 

 
 Example 1: 
 
 
Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 
respectively.
 

 Example 2: 

 
Input: root = [1]
Output: 0
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 1000]. 
 -1000 <= Node.val <= 1000 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 5449
 👎 306

*/
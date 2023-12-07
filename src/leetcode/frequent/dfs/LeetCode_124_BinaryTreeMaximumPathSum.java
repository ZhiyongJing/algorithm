package leetcode.frequent.dfs;

import leetcode.frequent.TreeNode;

/**
  *@Question:  124. Binary Tree Maximum Path Sum     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 81.55%      
  *@Time Complexity: O
  *@Space Complexity: O
 */

public class LeetCode_124_BinaryTreeMaximumPathSum{
    
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
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        gainFromSubtree(root);
        return maxSum;
    }

    private int maxSum;

    // post order traversal of subtree rooted at `root`
    private int gainFromSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // add the path sum from left subtree. Note that if the path
        // sum is negative, we can ignore it, or count it as 0.
        // This is the reason we use `Math.max` here.
        int gainFromLeft = Math.max(gainFromSubtree(root.left), 0);

        // add the path sum from right subtree. 0 if negative
        int gainFromRight = Math.max(gainFromSubtree(root.right), 0);

        // if left or right path sum are negative, they are counted
        // as 0, so this statement takes care of all four scenarios
        maxSum = Math.max(maxSum, gainFromLeft + gainFromRight + root.val);

        // return the max sum for a path starting at the root of subtree
        return Math.max(gainFromLeft + root.val, gainFromRight + root.val);
    }
}

//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_124_BinaryTreeMaximumPathSum().new Solution();
        // TO TEST
        //solution.
    }
}
/**
A path in a binary tree is a sequence of nodes where each pair of adjacent 
nodes in the sequence has an edge connecting them. A node can only appear in the 
sequence at most once. Note that the path does not need to pass through the root. 

 The path sum of a path is the sum of the node's values in the path. 

 Given the root of a binary tree, return the maximum path sum of any non-empty 
path. 

 
 Example 1: 
 
 
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 

 Example 2: 
 
 
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 
42.
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 3 * 10⁴]. 
 -1000 <= Node.val <= 1000 
 

 Related Topics Dynamic Programming Tree Depth-First Search Binary Tree 👍 15874
 👎 692

*/

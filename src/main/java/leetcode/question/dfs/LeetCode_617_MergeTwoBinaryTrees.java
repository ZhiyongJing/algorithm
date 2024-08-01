package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
  *@Question:  617. Merge Two Binary Trees     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 50.31%      
  *@Time  Complexity: O(M) M is the minimum number of nodes from the two given trees.
  *@Space Complexity: O(M) The depth of the recursion tree can go upto m in the case of a skewed tree.
 */

public class LeetCode_617_MergeTwoBinaryTrees{
    
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
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null)
                return t2;
            if (t2 == null)
                return t1;
            t1.val += t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
            return t1;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_617_MergeTwoBinaryTrees().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given two binary trees root1 and root2. 

 Imagine that when you put one of them to cover the other, some nodes of the 
two trees are overlapped while the others are not. You need to merge the two trees 
into a new binary tree. The merge rule is that if two nodes overlap, then sum 
node values up as the new value of the merged node. Otherwise, the NOT null node 
will be used as the node of the new tree. 

 Return the merged tree. 

 Note: The merging process must start from the root nodes of both trees. 

 
 Example 1: 
 
 
Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
Output: [3,4,5,5,4,null,7]
 

 Example 2: 

 
Input: root1 = [1], root2 = [1,2]
Output: [2,2]
 

 
 Constraints: 

 
 The number of nodes in both trees is in the range [0, 2000]. 
 -10⁴ <= Node.val <= 10⁴ 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 8778
 👎 301

*/
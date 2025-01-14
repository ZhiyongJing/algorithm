package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
  *@Question:  144. Binary Tree Preorder Traversal
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 30.55%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

public class LeetCode_144_BinaryTreePreorderTraversal{

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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        preorderDFS(root,result);
        return result;
    }
    private void preorderDFS(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }
        result.add(root.val);
        preorderDFS(root.left, result);
        preorderDFS(root.right, result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_144_BinaryTreePreorderTraversal().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary tree, return the preorder traversal of its nodes' 
values. 

 
 Example 1: 

 
 Input: root = [1,null,2,3] 
 

 Output: [1,2,3] 

 Explanation: 

 

 Example 2: 

 
 Input: root = [1,2,3,4,5,null,8,null,null,6,7,9] 
 

 Output: [1,2,4,5,6,7,3,8,9] 

 Explanation: 

 

 Example 3: 

 
 Input: root = [] 
 

 Output: [] 

 Example 4: 

 
 Input: root = [1] 
 

 Output: [1] 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 100]. 
 -100 <= Node.val <= 100 
 

 
 Follow up: Recursive solution is trivial, could you do it iteratively? 

 Related Topics Stack Tree Depth-First Search Binary Tree 👍 8217 👎 215

*/
package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
  *@Question:  145. Binary Tree Postorder Traversal
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 52.44%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

public class LeetCode_145_BinaryTreePostorderTraversal{

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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        preorderDFS(root,result);
        return result;
    }
    private void preorderDFS(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }

        preorderDFS(root.left, result);
        preorderDFS(root.right, result);
        result.add(root.val);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_145_BinaryTreePostorderTraversal().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary tree, return the postorder traversal of its nodes' 
values. 

 
 Example 1: 

 
 Input: root = [1,null,2,3] 
 

 Output: [3,2,1] 

 Explanation: 

 

 Example 2: 

 
 Input: root = [1,2,3,4,5,null,8,null,null,6,7,9] 
 

 Output: [4,6,7,5,2,9,8,3,1] 

 Explanation: 

 

 Example 3: 

 
 Input: root = [] 
 

 Output: [] 

 Example 4: 

 
 Input: root = [1] 
 

 Output: [1] 

 
 Constraints: 

 
 The number of the nodes in the tree is in the range [0, 100]. 
 -100 <= Node.val <= 100 
 

 
Follow up: Recursive solution is trivial, could you do it iteratively?

 Related Topics Stack Tree Depth-First Search Binary Tree 👍 7280 👎 208

*/
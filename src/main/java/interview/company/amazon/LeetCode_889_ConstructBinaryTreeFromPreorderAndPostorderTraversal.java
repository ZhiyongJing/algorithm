package interview.company.amazon;

/**
  *@Question:  889. Construct Binary Tree from Preorder and Postorder Traversal     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 0.0%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_889_ConstructBinaryTreeFromPreorderAndPostorderTraversal{
    
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
    int preIndex = 0, posIndex = 0;
    public TreeNode constructFromPrePost(int[]pre, int[]post) {
        TreeNode root = new TreeNode(pre[preIndex++]);
        if (root.val != post[posIndex])
            root.left = constructFromPrePost(pre, post);
        if (root.val != post[posIndex])
            root.right = constructFromPrePost(pre, post);
        posIndex++;
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_889_ConstructBinaryTreeFromPreorderAndPostorderTraversal().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given two integer arrays, preorder and postorder where preorder is the preorder 
traversal of a binary tree of distinct values and postorder is the postorder 
traversal of the same tree, reconstruct and return the binary tree. 

 If there exist multiple answers, you can return any of them. 

 
 Example 1: 
 
 
Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

 Example 2: 

 
Input: preorder = [1], postorder = [1]
Output: [1]
 

 
 Constraints: 

 
 1 <= preorder.length <= 30 
 1 <= preorder[i] <= preorder.length 
 All the values of preorder are unique. 
 postorder.length == preorder.length 
 1 <= postorder[i] <= postorder.length 
 All the values of postorder are unique. 
 It is guaranteed that preorder and postorder are the preorder traversal and 
postorder traversal of the same binary tree. 
 

 Related Topics Array Hash Table Divide and Conquer Tree Binary Tree 👍 2719 👎 
112

*/
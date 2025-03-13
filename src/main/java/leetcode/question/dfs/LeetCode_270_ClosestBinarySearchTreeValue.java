package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
  *@Question:  270. Closest Binary Search Tree Value
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 65.41%
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_270_ClosestBinarySearchTreeValue{

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
    double closed = 1000000.0;
    int cur = 1000000;
    public int closestValue(TreeNode root, double target) {
        dfs(root, target);
        return cur;
        
    }
    private void dfs(TreeNode root, double target){
        if(root == null) return;
        if(Math.abs(root.val - target) < this.closed){
            System.out.println(Math.abs(root.val - target));

            this.closed = Math.abs(root.val - target);
            this.cur = root.val;

        }
        if(Math.abs(root.val - target) == this.closed){
            this.cur = Math.min(cur, root.val);

        }
        dfs(root.left, target);
        dfs(root.right, target);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_270_ClosestBinarySearchTreeValue().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary search tree and a target value, return the value in 
the BST that is closest to the target. If there are multiple answers, print the 
smallest. 

 
 Example 1: 
 
 
Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
 

 Example 2: 

 
Input: root = [1], target = 4.428571
Output: 1
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁴]. 
 0 <= Node.val <= 10⁹ 
 -10⁹ <= target <= 10⁹ 
 

 Related Topics Binary Search Tree Depth-First Search Binary Search Tree Binary 
Tree 👍 1859 👎 157

*/
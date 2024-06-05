package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
/**
  *@Question:  513. Find Bottom Left Tree Value     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 50.59%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

public class LeetCode_513_FindBottomLeftTreeValue{
    
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
    private int maxDepth;
    private int bottomLeftValue;

    //Solution1: DFS
    public int findBottomLeftValue(TreeNode root) {
        maxDepth = -1;
        bottomLeftValue = 0;
        dfs(root, 0);
        return bottomLeftValue;
    }

    private void dfs(TreeNode current, int depth) {
        if (current == null) {
            return;
        }
        if (depth > maxDepth) {  // If true, we discovered a new level
            maxDepth = depth;
            bottomLeftValue = current.val;
        }
        dfs(current.left, depth + 1);
        dfs(current.right, depth + 1);
    }

    //Solution2: BFS
    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode current = root;
        queue.add(current);

        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.right != null) {
                queue.add(current.right);
            }
            if (current.left != null) {
                queue.add(current.left);
            }
        }
        return current.val;
    }

}

//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_513_FindBottomLeftTreeValue().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary tree, return the leftmost value in the last row of 
the tree. 

 
 Example 1: 
 
 
Input: root = [2,1,3]
Output: 1
 

 Example 2: 
 
 
Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁴]. 
 -2³¹ <= Node.val <= 2³¹ - 1 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 3768
 👎 294

*/
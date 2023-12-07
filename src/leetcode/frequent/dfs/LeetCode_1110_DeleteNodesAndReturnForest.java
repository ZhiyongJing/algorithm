package leetcode.frequent.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
  *@Question:  1110. Delete Nodes And Return Forest     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 56.58%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_1110_DeleteNodesAndReturnForest{
    
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
    Set<Integer> to_delete_set;
    List<TreeNode> res;
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        to_delete_set = new HashSet<>();
        res = new ArrayList<>();
        for (int i : to_delete)
            to_delete_set.add(i);
        dfs(root, true);
        return res;
    }

    private TreeNode dfs(TreeNode node, boolean is_root) {
        if (node == null) return null;
        boolean deleted = to_delete_set.contains(node.val);
        node.left = dfs(node.left, deleted);
        node.right =  dfs(node.right, deleted);
        if (is_root && !deleted) res.add(node);

        return deleted ? null : node;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_1110_DeleteNodesAndReturnForest().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary tree, each node in the tree has a distinct value. 

 After deleting all nodes with a value in to_delete, we are left with a forest (
a disjoint union of trees). 

 Return the roots of the trees in the remaining forest. You may return the 
result in any order. 

 
 Example 1: 
 
 
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 

 Example 2: 

 
Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]
 

 
 Constraints: 

 
 The number of nodes in the given tree is at most 1000. 
 Each node has a distinct value between 1 and 1000. 
 to_delete.length <= 1000 
 to_delete contains distinct values between 1 and 1000. 
 

 Related Topics Array Hash Table Tree Depth-First Search Binary Tree 👍 3711 👎 
105

*/

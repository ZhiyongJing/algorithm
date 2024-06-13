package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
/**
 *@Question:  653. Two Sum IV - Input is a BST     
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 35.07%      
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

public class LeetCode_653_TwoSumIvInputIsABst{

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
    public class Solution {
        //solution 1: dfs
        public boolean findTarget(TreeNode root, int k) {
            Set < Integer > set = new HashSet();
            return find(root, k, set);
        }
        public boolean find(TreeNode root, int k, Set < Integer > set) {
            if (root == null)
                return false;
            if (set.contains(k - root.val))
                return true;
            set.add(root.val);
            return find(root.left, k, set) || find(root.right, k, set);
        }

        //Solution 2: bfs
        public boolean findTarget2(TreeNode root, int k) {
            Set < Integer > set = new HashSet();
            Queue < TreeNode > queue = new LinkedList();
            queue.add(root);
            while (!queue.isEmpty()) {
                if (queue.peek() != null) {
                    TreeNode node = queue.remove();
                    if (set.contains(k - node.val))
                        return true;
                    set.add(node.val);
                    queue.add(node.right);
                    queue.add(node.left);
                } else
                    queue.remove();
            }
            return false;
        }

    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_653_TwoSumIvInputIsABst().new Solution();
        // TO TEST
        //solution.
    }
}
/**
 Given the root of a binary search tree and an integer k, return true if there 
 exist two elements in the BST such that their sum is equal to k, or false 
 otherwise. 


 Example 1: 


 Input: root = [5,3,6,2,4,null,7], k = 9
 Output: true


 Example 2: 


 Input: root = [5,3,6,2,4,null,7], k = 28
 Output: false



 Constraints: 


 The number of nodes in the tree is in the range [1, 10⁴]. 
 -10⁴ <= Node.val <= 10⁴ 
 root is guaranteed to be a valid binary search tree. 
 -10⁵ <= k <= 10⁵ 


 Related Topics Hash Table Two Pointers Tree Depth-First Search Breadth-First 
 Search Binary Search Tree Binary Tree 👍 6647 👎 265

 */
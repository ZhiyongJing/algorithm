package leetcode.question.bfs;

import leetcode.util.TreeNode;

import java.util.LinkedList;

/**
  *@Question:  230. Kth Smallest Element in a BST     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 49.57%      
  *@Time  Complexity: O(n)
  *@Space Complexity: O(n)
 */

public class LeetCode_230_KthSmallestElementInABst{
    
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
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }

//    DFS solution
//        public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
//            if (root == null) return arr;
//            inorder(root.left, arr);
//            arr.add(root.val);
//            inorder(root.right, arr);
//            return arr;
//        }
//
//        public int kthSmallest(TreeNode root, int k) {
//            ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
//            return nums.get(k - 1);
//        }

}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_230_KthSmallestElementInABst().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the root of a binary search tree, and an integer k, return the kᵗʰ 
smallest value (1-indexed) of all the values of the nodes in the tree. 

 
 Example 1: 
 
 
Input: root = [3,1,4,null,2], k = 1
Output: 1
 

 Example 2: 
 
 
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
 

 
 Constraints: 

 
 The number of nodes in the tree is n. 
 1 <= k <= n <= 10⁴ 
 0 <= Node.val <= 10⁴ 
 

 
 Follow up: If the BST is modified often (i.e., we can do insert and delete 
operations) and you need to find the kth smallest frequently, how would you 
optimize? 

 Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 10891 
👎 207

*/

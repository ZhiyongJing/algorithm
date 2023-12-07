package leetcode.done;

import java.util.*;

//199. Binary Tree Right Side View
//Medium
//Time complexity: O(N)
public class LeetCode199_Binary_Tree_Right_Side_View {
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
      }
  }

    class Solution {
        List<Integer> rightside = new ArrayList();

        public void helper(TreeNode node, int level) {
            if (level == rightside.size())
                rightside.add(node.val);

            if (node.right != null)
                helper(node.right, level + 1);
            if (node.left != null)
                helper(node.left, level + 1);
        }

        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) return rightside;

            helper(root, 0);
            return rightside;
        }
    }
}

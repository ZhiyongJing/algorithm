package leetcode.util;


//  Definition for a binary tree node.
  public class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;
      public TreeNode() {}
      public TreeNode(int val) { this.val = val; }
      public TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
         this.right = right;
      }

    public TreeNode sampleTree() {
        // 创建一个更深的BST示例
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(8);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(12);
        root.right.left = new TreeNode(18);
        root.right.right = new TreeNode(25);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        return root;
    }
}

/**
 *         15
 *        /  \
 *       8    20
 *      / \   / \
 *     5  12 18  25
 *    / \  /
 *   3   7 10
 *        \
 *        14
 */



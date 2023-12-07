package leetcode.util;

/**
 * @Question:
 * @Difculty:
 * @Time Complexity: O
 * @Space Complexity: O
 * @UpdateTime: [12/7/23 9:20 AM]
 */

//   Definition for Node.
   public class Node {
      public int val;
      public Node left;
      public Node right;
      public Node random;
      public Node() {}
      public Node(int val) { this.val = val; }
      public Node(int val, Node left, Node right, Node random) {
           this.val = val;
           this.left = left;
           this.right = right;
           this.random = random;
       }
   }


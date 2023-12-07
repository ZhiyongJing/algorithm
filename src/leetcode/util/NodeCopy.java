package leetcode.util;

/**
 * @Question:
 * @Difculty:
 * @Time Complexity: O
 * @Space Complexity: O
 * @UpdateTime: [12/7/23 9:20 AM]
 */

//   Definition for Node.
   public class NodeCopy {
      public int val;
      public NodeCopy left;
      public NodeCopy right;
      public NodeCopy random;
      public NodeCopy() {}
      public NodeCopy(int val) { this.val = val; }
      public NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
           this.val = val;
           this.left = left;
           this.right = right;
           this.random = random;
       }
   }


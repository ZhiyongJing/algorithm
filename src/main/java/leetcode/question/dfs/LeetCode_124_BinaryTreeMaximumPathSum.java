package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
  *@Question:  124. Binary Tree Maximum Path Sum     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 81.55%      
  *@Time Complexity: O(N)
  *@Space Complexity: O(N)
 */


public class LeetCode_124_BinaryTreeMaximumPathSum{

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
        // 解题方法
        public int maxPathSum(TreeNode root) {
            maxSum = Integer.MIN_VALUE; // 初始化最大路径和为最小整数值
            gainFromSubtree(root); // 调用递归函数计算最大路径和
            return maxSum; // 返回最大路径和
        }

        // 定义全局变量存储最大路径和
        private int maxSum;

        // 后序遍历子树的递归函数
        private int gainFromSubtree(TreeNode root) {
            if (root == null) {
                return 0; // 如果节点为空，返回0
            }

            // 计算左子树的路径和，若为负数则设为0
            int gainFromLeft = Math.max(gainFromSubtree(root.left), 0);

            // 计算右子树的路径和，若为负数则设为0
            int gainFromRight = Math.max(gainFromSubtree(root.right), 0);

            // 更新最大路径和，包含当前节点的路径和与左右子树的路径和
            maxSum = Math.max(maxSum, gainFromLeft + gainFromRight + root.val);

            // 返回以当前节点为起点的最大路径和，左子树的路径和与右子树的路径和中取较大值
            return Math.max(gainFromLeft + root.val, gainFromRight + root.val);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_124_BinaryTreeMaximumPathSum().new Solution();
        // 测试代码待添加
    }
}

/**
A path in a binary tree is a sequence of nodes where each pair of adjacent 
nodes in the sequence has an edge connecting them. A node can only appear in the 
sequence at most once. Note that the path does not need to pass through the root. 

 The path sum of a path is the sum of the node's values in the path. 

 Given the root of a binary tree, return the maximum path sum of any non-empty 
path. 

 
 Example 1: 
 
 
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 

 Example 2: 
 
 
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 
42.
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 3 * 10⁴]. 
 -1000 <= Node.val <= 1000 
 

 Related Topics Dynamic Programming Tree Depth-First Search Binary Tree 👍 15874
 👎 692

*/

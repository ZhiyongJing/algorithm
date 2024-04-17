package interview.company.amazon;

import leetcode.util.TreeNode;

/**
 *@Question:  124. Binary Tree Maximum Path Sum
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 81.55%
 *@Time Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 *当然，让我们逐步解释解题思路并分析时间和空间复杂度：
 *
 * ### 解题思路：
 *
 * 这个问题需要找到二叉树中任意路径的最大路径和。路径可以从任意节点开始，结束，而不必经过根节点。
 *
 * 1. **深度优先搜索（DFS）：**
 *    - 我们可以使用深度优先搜索遍历二叉树的每个节点。
 *    - 对于每个节点，我们计算从该节点出发，经过该节点的路径的最大和，这个和有两种情况：路径只包含该节点（即节点值本身），
 *    或者包含左子树和右子树的路径和节点值。
 *    - 在计算过程中，我们保持一个全局变量 `maxSum`，记录遍历过程中出现的最大路径和。
 *
 * 2. **后序遍历：**
 *    - 我们采用后序遍历的方式遍历二叉树。后序遍历可以确保我们先访问左子树和右子树，然后再处理当前节点。
 *    - 对于当前节点，我们计算从左子树和右子树返回的路径的最大和，如果路径和为负数，则将其视为0。然后，
 *    我们将左右子树的路径和加上当前节点的值作为当前节点的路径和，并更新全局最大路径和 `maxSum`。
 *
 * ### 时间复杂度分析：
 * - 深度优先搜索遍历二叉树的时间复杂度为 O(N)，其中 N 是二叉树中的节点数量。
 *
 * ### 空间复杂度分析：
 * - 递归调用栈的最大深度取决于二叉树的高度，因此空间复杂度为 O(N)，其中 N 是二叉树中的节点数量。
 *
 * 这就是解题思路以及时间和空间复杂度的详细分析。
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
        public int maxPathSum(TreeNode root) {
            maxSum = Integer.MIN_VALUE;
            // 对树进行后序遍历
            gainFromSubtree(root);
            return maxSum;
        }

        private int maxSum;

        // 后序遍历子树`root`
        private int gainFromSubtree(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // 添加来自左子树的路径和。注意，如果路径和为负数，我们可以忽略它，或将其视为0。
            // 这就是我们在这里使用`Math.max`的原因。
            int gainFromLeft = Math.max(gainFromSubtree(root.left), 0);

            // 添加来自右子树的路径和。如果是负数，则为0。
            int gainFromRight = Math.max(gainFromSubtree(root.right), 0);

            // 如果左子树或右子树的路径和为负数，它们被视为0，因此此语句处理了所有四种情况
            maxSum = Math.max(maxSum, gainFromLeft + gainFromRight + root.val);

            // 返回以子树根节点开始的路径的最大和
            return Math.max(gainFromLeft + root.val, gainFromRight + root.val);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_124_BinaryTreeMaximumPathSum.Solution solution = new LeetCode_124_BinaryTreeMaximumPathSum().new Solution();
        // 测试代码
        //solution.
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
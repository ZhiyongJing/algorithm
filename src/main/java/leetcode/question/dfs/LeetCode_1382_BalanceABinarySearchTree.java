package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  1382. Balance a Binary Search Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 76.05%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 1. **中序遍历获得有序列表**:
 *    - 二叉搜索树（BST）的中序遍历会产生一个递增的有序序列。
 *    - 通过对原树进行中序遍历，将所有节点的值按顺序存储到一个列表中。
 *
 * 2. **构建平衡的二叉搜索树**:
 *    - 利用中序遍历得到的有序列表，构建一个平衡的二叉搜索树。
 *    - 具体步骤是：选择列表的中间元素作为当前树的根节点，然后递归地对左右子数组重复上述过程，将中间元素的左边子数组作为左子树，右边子数组作为右子树。
 *    - 通过这种方式，每次选择中间元素作为根节点可以保证树的高度尽量小，从而达到平衡的效果。
 *
 * ### 时间复杂度
 * - **O(N)**，其中N是二叉搜索树中的节点数。这个复杂度来源于两部分：
 *   1. 中序遍历的时间复杂度为O(N)，因为需要遍历每个节点一次。
 *   2. 构建平衡二叉搜索树的时间复杂度也为O(N)，因为需要处理每个节点并创建相应的树结构。
 *
 * ### 空间复杂度
 * - **O(N)**，主要包括：
 *   1. 中序遍历过程中使用的列表来存储节点的值，大小为O(N)。
 *   2. 递归调用堆栈的最大深度是O(log N)（在完全平衡的情况下），但由于中序遍历列表的存储空间占用是主要的，因此总体空间复杂度为O(N)。
 */
public class LeetCode_1382_BalanceABinarySearchTree {

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

        public TreeNode balanceBST(TreeNode root) {
            // 创建一个列表来存储二叉搜索树的中序遍历结果
            List<Integer> inorder = new ArrayList<>();
            inorderTraversal(root, inorder);

            // 构建并返回平衡后的二叉搜索树
            return createBalancedBST(inorder, 0, inorder.size() - 1);
        }

        private void inorderTraversal(TreeNode root, List<Integer> inorder) {
            // 中序遍历，将元素按顺序存入列表
            if (root == null) return;
            inorderTraversal(root.left, inorder);
            inorder.add(root.val);
            inorderTraversal(root.right, inorder);
        }

        private TreeNode createBalancedBST(List<Integer> inorder, int start, int end) {
            // 基础情况：如果start索引大于end索引，返回null
            if (start > end) return null;

            // 找到当前范围内的中间元素
            int mid = start + (end - start) / 2;

            // 递归构建左右子树
            TreeNode leftSubtree = createBalancedBST(inorder, start, mid - 1);
            TreeNode rightSubtree = createBalancedBST(inorder, mid + 1, end);

            // 使用中间元素创建新节点，并将左右子树连接到该节点
            TreeNode node = new TreeNode(inorder.get(mid), leftSubtree, rightSubtree);
            return node;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1382_BalanceABinarySearchTree().new Solution();

        // 测试代码
        // 构建一个不平衡的二叉搜索树
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        // 平衡二叉搜索树
        TreeNode balancedRoot = solution.balanceBST(root);

        // 打印平衡后的二叉搜索树的中序遍历结果
        printInorder(balancedRoot); // 应输出：1 2 3 4
    }

    // 辅助方法：中序遍历打印二叉树
    private static void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }
}

/**
Given the root of a binary search tree, return a balanced binary search tree 
with the same node values. If there is more than one answer, return any of them. 

 A binary search tree is balanced if the depth of the two subtrees of every 
node never differs by more than 1. 

 
 Example 1: 
 
 
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also 
correct.
 

 Example 2: 
 
 
Input: root = [2,1,3]
Output: [2,1,3]
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁴]. 
 1 <= Node.val <= 10⁵ 
 

 Related Topics Divide and Conquer Greedy Tree Depth-First Search Binary Search 
Tree Binary Tree 👍 3638 👎 90

*/
package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
 *@Question:  669. Trim a Binary Search Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 40.7%
 *@Time  Complexity: O(N), where N is the number of nodes in the tree.
 *@Space Complexity: O(N)
 */
/**
 * 第一步：题目描述
 * 给定一棵二叉搜索树（BST），以及两个整数 low 和 high，表示一个范围。
 * 修剪二叉搜索树，使得所有的节点值都在范围 [low, high] 之内。
 * 修剪后的树仍需保持二叉搜索树的性质，并返回修剪后的树的根节点。
 *
 * 示例：
 * 输入：root = [3, 0, 4, null, 2, null, null, 1], low = 1, high = 3
 * 输出：[3, 2, null, 1]
 *
 * 第二步：解题思路
 * 1. 使用递归方法处理修剪逻辑。
 *    - 如果当前节点为空，返回空。
 *    - 如果当前节点的值小于 low，则当前节点的左子树不可能包含合法值，修剪为递归处理右子树。
 *    - 如果当前节点的值大于 high，则当前节点的右子树不可能包含合法值，修剪为递归处理左子树。
 *    - 如果当前节点值在范围 [low, high] 内，则保留当前节点，并递归修剪其左右子树。
 *
 * 示例：
 * 输入：root = [3, 0, 4, null, 2, null, null, 1], low = 1, high = 3
 * - 初始时，根节点值为 3，在范围内，递归修剪左右子树。
 * - 左子树的根节点值为 0，小于 low，修剪为右子树（值为 2）。
 * - 右子树的根节点值为 4，大于 high，修剪为左子树（值为 null）。
 * - 继续修剪子节点，得到修剪后的树：[3, 2, null, 1]。
 *
 * 第三步：时间和空间复杂度
 * 1. 时间复杂度：O(N)，其中 N 是树中节点的数量。每个节点最多访问一次。
 * 2. 空间复杂度：O(H)，其中 H 是树的高度。递归调用栈的空间使用与树的高度成正比。
 */


public class LeetCode_669_TrimABinarySearchTree{

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
        public TreeNode trimBST(TreeNode root, int low, int high) {
            // 如果当前节点为空，直接返回空
            if (root == null) return root;

            // 如果当前节点的值大于指定范围的上界，递归修剪左子树
            if (root.val > high) return trimBST(root.left, low, high);

            // 如果当前节点的值小于指定范围的下界，递归修剪右子树
            if (root.val < low) return trimBST(root.right, low, high);

            // 如果当前节点的值在范围内，继续修剪左右子树
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);

            // 返回修剪后的当前节点
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_669_TrimABinarySearchTree().new Solution();

        // 构造测试二叉搜索树
        TreeNode root = new TreeNode(3,
                new TreeNode(0, null, new TreeNode(2, new TreeNode(1), null)),
                new TreeNode(4));

        int low = 1;
        int high = 3;

        // 修剪二叉搜索树
        TreeNode trimmedTree = solution.trimBST(root, low, high);

        // 打印修剪后的树（中序遍历）
        printInOrder(trimmedTree);
    }

    // 中序遍历打印树
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}

/**
 Given the root of a binary search tree and the lowest and highest boundaries as
 low and high, trim the tree so that all its elements lies in [low, high].
 Trimming the tree should not change the relative structure of the elements that will
 remain in the tree (i.e., any node's descendant should remain a descendant). It
 can be proven that there is a unique answer.

 Return the root of the trimmed binary search tree. Note that the root may
 change depending on the given bounds.


 Example 1:


 Input: root = [1,0,2], low = 1, high = 2
 Output: [1,null,2]


 Example 2:


 Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 Output: [3,2,null,1]



 Constraints:


 The number of nodes in the tree is in the range [1, 10⁴].
 0 <= Node.val <= 10⁴
 The value of each node in the tree is unique.
 root is guaranteed to be a valid binary search tree.
 0 <= low <= high <= 10⁴


 Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 5910 ?
 ? 262

 */
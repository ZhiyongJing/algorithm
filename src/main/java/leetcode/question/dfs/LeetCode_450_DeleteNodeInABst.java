package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
 *@Question:  450. Delete Node in a BST
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 29.55%
 *@Time Complexity: O(logN)
 *@Space Complexity: O(H)
 */

/**
 * ### 解题思路
 *
 * 题目要求在二叉搜索树（BST）中删除一个节点，并保持BST的性质。删除节点的过程可以分为三种情况：
 *
 * 1. **节点是叶子节点**：直接删除该节点，不需要其他操作。
 * 2. **节点有右子树**：用后继节点（右子树中最小的节点）替换该节点的值，然后删除后继节点。
 * 3. **节点没有右子树但有左子树**：用前驱节点（左子树中最大的节点）替换该节点的值，然后删除前驱节点。
 *
 * 以下是详细的解题思路：
 *
 * 1. **找到要删除的节点**：通过比较`key`和当前节点的值，递归地在左子树或右子树中查找要删除的节点。
 *    - 如果`key`小于当前节点的值，则在左子树中递归删除。
 *    - 如果`key`大于当前节点的值，则在右子树中递归删除。
 *    - 如果`key`等于当前节点的值，则找到了要删除的节点。
 *
 * 2. **处理找到的节点**：
 *    - **叶子节点**：如果节点没有左右子树，直接删除（将其设为`null`）。
 *    - **有右子树**：如果节点有右子树，找到后继节点（右子树中最小的节点），用后继节点的值替换当前节点的值，然后递归删除后继节点。
 *    - **没有右子树但有左子树**：如果节点没有右子树但有左子树，找到前驱节点（左子树中最大的节点），用前驱节点的值替换当前节点的值，然后递归删除前驱节点。
 *
 * ### 时间复杂度
 *
 * - **O(log N)**：在平衡二叉搜索树（例如AVL树或红黑树）中，树的高度为`log N`，查找、插入和删除操作的时间复杂度都是`O(log N)`。
 * - **O(N)**：在最坏情况下，树退化成链表，时间复杂度会变为`O(N)`，其中`N`是节点的总数。
 *
 * ### 空间复杂度
 *
 * - **O(H)**：递归调用的栈空间取决于树的高度`H`。
 *   - 在平衡二叉搜索树中，树的高度`H`为`log N`，空间复杂度为`O(log N)`。
 *   - 在最坏情况下，树退化成链表，高度为`N`，空间复杂度为`O(N)`。
 *
 * 总结：
 * - **时间复杂度**：平均情况下为`O(log N)`，最坏情况下为`O(N)`。
 * - **空间复杂度**：平均情况下为`O(log N)`，最坏情况下为`O(N)`。
 */

public class LeetCode_450_DeleteNodeInABst {

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
        /*
        找到后继节点：一步向右，然后一直向左
        */
        public int successor(TreeNode root) {
            root = root.right; // 先一步向右
            while (root.left != null) root = root.left; // 然后一直向左
            return root.val; // 返回后继节点的值
        }

        /*
        找到前驱节点：一步向左，然后一直向右
        */
        public int predecessor(TreeNode root) {
            root = root.left; // 先一步向左
            while (root.right != null) root = root.right; // 然后一直向右
            return root.val; // 返回前驱节点的值
        }

        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null; // 如果树为空，直接返回null

            // 从右子树中删除
            if (key > root.val) root.right = deleteNode(root.right, key);
                // 从左子树中删除
            else if (key < root.val) root.left = deleteNode(root.left, key);
                // 删除当前节点
            else {
                // 如果节点是叶子节点
                if (root.left == null && root.right == null) root = null;
                    // 节点不是叶子节点且有右子树
                else if (root.right != null) {
                    root.val = successor(root); // 找到后继节点并替换当前节点的值
                    root.right = deleteNode(root.right, root.val); // 删除后继节点
                }
                // 节点不是叶子节点，没有右子树，但有左子树
                else {
                    root.val = predecessor(root); // 找到前驱节点并替换当前节点的值
                    root.left = deleteNode(root.left, root.val); // 删除前驱节点
                }
            }
            return root; // 返回更新后的根节点
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_450_DeleteNodeInABst().new Solution();

        // 创建测试用例
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        // 打印删除前的树结构
        System.out.println("删除节点前的树结构:");
        printTree(root);

        // 删除节点
        int key = 3;
        root = solution.deleteNode(root, key);

        // 打印删除后的树结构
        System.out.println("\n删除节点后的树结构:");
        printTree(root);
    }

    // 辅助方法：中序遍历打印树结构
    public static void printTree(TreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.val + " ");
            printTree(root.right);
        }
    }
}

/**
Given a root node reference of a BST and a key, delete the node with the given 
key in the BST. Return the root node reference (possibly updated) of the BST. 

 Basically, the deletion can be divided into two stages: 

 
 Search for a node to remove. 
 If the node is found, delete the node. 
 

 
 Example 1: 
 
 
Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and 
delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also 
accepted.

 

 Example 2: 

 
Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.
 

 Example 3: 

 
Input: root = [], key = 0
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 10⁴]. 
 -10⁵ <= Node.val <= 10⁵ 
 Each node has a unique value. 
 root is a valid binary search tree. 
 -10⁵ <= key <= 10⁵ 
 

 
 Follow up: Could you solve it with time complexity O(height of tree)? 

 Related Topics Tree Binary Search Tree Binary Tree 👍 8998 👎 274

*/
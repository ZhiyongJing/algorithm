package leetcode.question.dfs;
import leetcode.util.TreeNode;
/**
 *@Question:  114. Flatten Binary Tree to Linked List
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 29.18%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求将二叉树展开为一个单链表，使用前序遍历的顺序。具体解题思路如下：
 *
 * 1. **递归处理每个节点**：
 *    - 对于当前节点，如果是叶子节点（即没有左右子节点），直接返回该节点。
 *    - 如果不是叶子节点，则递归地处理其左子树和右子树，分别获得展开后的左子树和右子树的尾节点。
 *
 * 2. **重新链接节点**：
 *    - 如果存在左子树，将左子树的尾节点的右指针指向右子树。
 *    - 将当前节点的右子树设为左子树，并将左子树置为 `null`。
 *    - 最后，返回右子树的尾节点（如果右子树为空，则返回左子树的尾节点）。
 *
 * 3. **处理整个树**：
 *    - 从根节点开始递归处理整个树，使其展开为一个单链表。
 *
 * ### 具体步骤
 *
 * 1. **初始化**：
 *    - 创建一个辅助方法 `flattenTree` 来递归处理每个节点。
 *    - 该方法返回当前子树展开后的尾节点。
 *
 * 2. **递归展开左子树和右子树**：
 *    - 对于当前节点，如果其左子树不为空，递归处理左子树并获得左子树的尾节点。
 *    - 同理，递归处理右子树并获得右子树的尾节点。
 *
 * 3. **调整节点链接**：
 *    - 如果左子树不为空，将左子树的尾节点的右指针指向右子树。
 *    - 将当前节点的右子树设为左子树，并将左子树置为 `null`。
 *
 * 4. **返回尾节点**：
 *    - 最终，返回右子树的尾节点。如果右子树为空，则返回左子树的尾节点。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**：O(N)，其中 N 是二叉树的节点数。因为每个节点都会被访问一次。
 * - **空间复杂度**：O(N)，最坏情况下，递归调用的栈空间是树的高度，树的高度最坏情况下是 N（即树为一条链）。
 *
 * 这种方法可以确保二叉树在前序遍历的顺序下被展开为一个单链表，并且在处理过程中不会丢失任何节点的信息。
 */

public class LeetCode_114_FlattenBinaryTreeToLinkedList{

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        // 辅助方法，用于将树展开为链表
        private TreeNode flattenTree(TreeNode node) {
            // 处理空节点的情况
            if (node == null) {
                return null;
            }

            // 如果是叶子节点，直接返回节点本身
            if (node.left == null && node.right == null) {
                return node;
            }

            // 递归展开左子树
            TreeNode leftTail = this.flattenTree(node.left);

            // 递归展开右子树
            TreeNode rightTail = this.flattenTree(node.right);

            // 如果存在左子树，我们需要将左子树插入到右子树的位置
            if (leftTail != null) {
                leftTail.right = node.right;
                node.right = node.left;
                node.left = null;
            }

            // 返回当前子树展开后的最右节点
            return rightTail == null ? leftTail : rightTail;
        }

        // 主方法，展开给定的二叉树
        public void flatten(TreeNode root) {
            this.flattenTree(root);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

//    // 定义二叉树节点类
//    public static class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//        TreeNode(int x) { val = x; }
//    }

    public static void main(String[] args) {
        Solution solution = new LeetCode_114_FlattenBinaryTreeToLinkedList().new Solution();

        // 测试用例
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        System.out.println("Before flattening:");
        printTree(root);

        solution.flatten(root);

        System.out.println("After flattening:");
        printTree(root);
    }

    // 辅助方法，前序打印二叉树
    public static void printTree(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.right;
        }
        System.out.println("null");
    }
}

/**
Given the root of a binary tree, flatten the tree into a "linked list": 

 
 The "linked list" should use the same TreeNode class where the right child 
pointer points to the next node in the list and the left child pointer is always 
null. 
 The "linked list" should be in the same order as a pre-order traversal of the 
binary tree. 
 

 
 Example 1: 
 
 
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
 

 Example 2: 

 
Input: root = []
Output: []
 

 Example 3: 

 
Input: root = [0]
Output: [0]
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 2000]. 
 -100 <= Node.val <= 100 
 

 
Follow up: Can you flatten the tree in-place (with 
O(1) extra space)?

 Related Topics Linked List Stack Tree Depth-First Search Binary Tree 👍 12135 ?
? 560

*/
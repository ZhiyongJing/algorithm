package leetcode.question.dfs;
import leetcode.util.TreeNode;

/**
 *@Question:  114. Flatten Binary Tree to Linked List
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 29.18%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N) for solution1, O(1) for solution2
 */

/**
 * ### 解题思路
 *
 * 问题要求将一个二叉树原地展开为单链表，按照树的先序遍历顺序（根-左-右）排列。我们提供了两种解法：递归法和迭代法。
 *
 * #### 解法一：递归法
 *
 * 1. **递归遍历**:
 *    - 对于每个节点，我们先递归处理其左子树，再递归处理其右子树。
 *    - 我们定义一个辅助方法 `flattenTree(TreeNode node)`，这个方法返回处理后子树的最右节点。
 *
 * 2. **处理过程**:
 *    - 如果当前节点为空，直接返回 `null`。
 *    - 如果是叶子节点（没有左子树和右子树），返回自身。
 *    - 如果有左子树，先递归处理左子树，再递归处理右子树。
 *    - 将左子树插入到右子树的位置，即：将当前节点的右子树接到左子树的最右节点的右侧，然后将左子树变为右子树，并将左子树置空。
 *
 * 3. **返回结果**:
 *    - 返回当前子树展开后的最右节点。
 *
 * #### 解法二：迭代法
 *
 * 1. **迭代遍历**:
 *    - 从根节点开始，迭代地处理每个节点。
 *    - 对于每个节点，如果存在左子树，则找到左子树中的最右节点，将这个节点的右子树指向当前节点的右子树。
 *
 * 2. **处理过程**:
 *    - 如果当前节点存在左子树，将左子树的最右节点的右子树指向当前节点的右子树。
 *    - 将左子树移到右子树的位置，然后将左子树置空。
 *    - 移动到右子树节点，继续处理。
 *
 * #### 时间和空间复杂度分析
 *
 * - **时间复杂度**:
 *   - 两种解法的时间复杂度都是 O(N)，其中 N 是二叉树的节点总数。这是因为每个节点都被访问一次。
 *
 * - **空间复杂度**:
 *   - 递归法的空间复杂度是 O(N)，这是由于递归调用栈的深度在最坏情况下（完全不平衡的树）可能达到 N。
 *   - 迭代法的空间复杂度是 O(1)，因为它只用了常数级别的额外空间。
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
        // Solution1: 使用递归方法将二叉树展开为链表
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
            TreeNode leftTail = flattenTree(node.left);

            // 递归展开右子树
            TreeNode rightTail = flattenTree(node.right);

            // 如果存在左子树，我们需要将左子树插入到右子树的位置
            if (leftTail != null) {
                leftTail.right = node.right; // 左子树的最右节点接上右子树
                node.right = node.left; // 将左子树变为右子树
                node.left = null; // 清空左子树
            }

            // 返回当前子树展开后的最右节点
            return rightTail == null ? leftTail : rightTail;
        }

        // Solution1: 递归法
        public void flatten(TreeNode root) {
            flattenTree(root);
        }

        // Solution2: 迭代法
        public void flatten2(TreeNode root) {
            // 处理空树的情况
            if (root == null) {
                return;
            }

            TreeNode node = root;

            while (node != null) {
                // 如果节点有左子树
                if (node.left != null) {
                    // 找到左子树中的最右节点
                    TreeNode rightmost = node.left;
                    while (rightmost.right != null) {
                        rightmost = rightmost.right;
                    }

                    // 重新连接节点
                    rightmost.right = node.right;
                    node.right = node.left;
                    node.left = null;
                }

                // 移动到树的右侧节点
                node = node.right;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


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
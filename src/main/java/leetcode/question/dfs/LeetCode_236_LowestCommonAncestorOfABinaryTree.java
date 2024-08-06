package leetcode.question.dfs;

import javafx.util.Pair;
import leetcode.util.TreeNode;

import java.util.Stack;

/**
 *@Question:  236. Lowest Common Ancestor of a Binary Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 79.5%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

public class LeetCode_236_LowestCommonAncestorOfABinaryTree {

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

        private TreeNode ans = null; // 用于存储找到的最近公共祖先

        // 深度优先遍历（DFS）递归函数
        private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
            // 如果当前节点为空，返回 false
            if (currentNode == null) {
                return false;
            }

            // 对左子树进行递归，返回值为 true 说明 p 或 q 在左子树中
            int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

            // 对右子树进行递归，返回值为 true 说明 p 或 q 在右子树中
            int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

            // 如果当前节点是 p 或 q
            int mid = (currentNode == p || currentNode == q) ? 1 : 0;

            // 如果 left、right 和 mid 中有两个或两个以上的值为 true，说明当前节点是 LCA
            if (mid + left + right >= 2) {
                this.ans = currentNode;
            }

            // 如果 left、right 或 mid 中有一个为 true，则返回 true
            return (mid + left + right > 0);
        }

        // 深度优先遍历求解 LCA
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // 从根节点开始遍历树
            this.recurseTree(root, p, q);
            return this.ans; // 返回找到的 LCA
        }

        // 迭代法解法
        // 三个静态标志用于跟踪后序遍历的状态

        // 左右子树都待遍历
        private static int BOTH_PENDING = 2;

        // 左子树已遍历完成
        private static int LEFT_DONE = 1;

        // 左右子树都已遍历完成
        private static int BOTH_DONE = 0;

        public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
            Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();

            // 初始化栈，将根节点和 BOTH_PENDING 状态入栈
            stack.push(new Pair<TreeNode, Integer>(root, Solution.BOTH_PENDING));

            // 标记是否找到其中一个节点
            boolean one_node_found = false;

            // 用于跟踪 LCA
            TreeNode LCA = null;

            // 当前子节点
            TreeNode child_node = null;

            // 使用栈进行后序遍历
            while (!stack.isEmpty()) {

                Pair<TreeNode, Integer> top = stack.peek();
                TreeNode parent_node = top.getKey();
                int parent_state = top.getValue();

                // 如果节点状态不是 BOTH_DONE，说明该节点不能被弹出
                if (parent_state != Solution.BOTH_DONE) {

                    // 如果左右子树都待遍历
                    if (parent_state == Solution.BOTH_PENDING) {

                        // 如果当前节点是 p 或 q
                        if (parent_node == p || parent_node == q) {

                            // 如果已经找到一个节点，说明我们找到了两个节点，返回当前的 LCA
                            if (one_node_found) {
                                return LCA;
                            } else {
                                // 否则，设置 one_node_found 为 true，标记已找到一个节点
                                one_node_found = true;

                                // 保存当前栈顶节点作为 LCA
                                LCA = stack.peek().getKey();
                            }
                        }

                        // 如果左右子树都待遍历，首先遍历左子树
                        child_node = parent_node.left;
                    } else {
                        // 遍历右子树
                        child_node = parent_node.right;
                    }

                    // 更新栈顶节点状态，因为我们已经遍历了一个子节点
                    stack.pop();
                    stack.push(new Pair<TreeNode, Integer>(parent_node, parent_state - 1));

                    // 将子节点入栈
                    if (child_node != null) {
                        stack.push(new Pair<TreeNode, Integer>(child_node, Solution.BOTH_PENDING));
                    }
                } else {
                    // 如果节点状态为 BOTH_DONE，说明可以将该节点弹出
                    // 更新 LCA 为下一个栈顶节点
                    if (LCA == stack.pop().getKey() && one_node_found) {
                        LCA = stack.peek().getKey();
                    }
                }
            }

            return null;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_236_LowestCommonAncestorOfABinaryTree().new Solution();

        // 创建测试树
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        // 构建树结构
        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        // 测试用例
        TreeNode p = node5;
        TreeNode q = node1;
        TreeNode lca = solution.lowestCommonAncestor(root, p, q);

        // 输出结果
        System.out.println("Lowest Common Ancestor of " + p.val + " and " + q.val + " is " + (lca != null ? lca.val : "null"));
    }
}

/**
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes 
in the tree. 

 According to the definition of LCA on Wikipedia: “The lowest common ancestor 
is defined between two nodes p and q as the lowest node in T that has both p and 
q as descendants (where we allow a node to be a descendant of itself).” 

 
 Example 1: 
 
 
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
 

 Example 2: 
 
 
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of 
itself according to the LCA definition.
 

 Example 3: 

 
Input: root = [1,2], p = 1, q = 2
Output: 1
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [2, 10⁵]. 
 -10⁹ <= Node.val <= 10⁹ 
 All Node.val are unique. 
 p != q 
 p and q will exist in the tree. 
 

 Related Topics Tree Depth-First Search Binary Tree 👍 16611 👎 416

*/
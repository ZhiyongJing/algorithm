package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
 *@Question:  235. Lowest Common Ancestor of a Binary Search Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 43.41%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N) for solution1, O(1) for solution2
 */

/**
 * ### 题目详解
 *
 * **题目**: 236. Lowest Common Ancestor of a Binary Tree
 *
 * **描述**:
 * 给定一个二叉树的根节点 `root` 和两个节点 `p` 和 `q`，找出 `p` 和 `q` 的最低公共祖先 (Lowest Common Ancestor, LCA)。最近公共祖先是指在树中，既是 `p` 和 `q` 的祖先，又是所有共同祖先中深度最大的节点。
 *
 * ### 解题思路
 *
 * **解法1: 递归**
 *
 * 1. **递归遍历**:
 *    - 从树的根节点开始递归遍历整个二叉树。
 *    - 对于每个节点，检查其左子树和右子树中是否包含 `p` 或 `q`。
 *
 * 2. **判断节点类型**:
 *    - 如果当前节点是 `p` 或 `q`，返回当前节点。
 *    - 否则，递归检查当前节点的左子树和右子树。
 *
 * 3. **确定公共祖先**:
 *    - 当左子树和右子树分别包含 `p` 和 `q` 时，当前节点就是 `p` 和 `q` 的最低公共祖先。
 *    - 如果只有左子树或右子树包含 `p` 或 `q`，则返回左子树或右子树的返回值。
 *
 * **解法2: 迭代**
 *
 * 1. **使用栈模拟遍历**:
 *    - 使用栈进行后序遍历，跟踪每个节点的状态（左右子树是否已经访问）。
 *    - 维护一个栈，节点和它的状态一起存储，状态表示左右子树的遍历情况。
 *
 * 2. **处理节点状态**:
 *    - 如果节点的左子树和右子树都未遍历，则先遍历左子树。
 *    - 如果节点的左子树已遍历，则遍历右子树。
 *
 * 3. **确定公共祖先**:
 *    - 在遍历过程中，记录找到的 `p` 和 `q`。
 *    - 如果找到 `p` 和 `q`，则更新最低公共祖先。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**:
 *   - 对于两种解法，时间复杂度都是 O(N)，其中 N 是树中节点的总数。原因是每个节点被访问一次。
 *
 * - **空间复杂度**:
 *   - **递归解法**: O(N)，由于递归调用栈的深度可能达到树的高度，在最坏情况下，树的高度为 N（例如，树为链状），需要 O(N) 的空间。
 *   - **迭代解法**: O(1)，仅使用常数级别的空间来存储栈和状态，与树的大小无关。虽然栈的空间在树的某些阶段可能会变大，但在最终空间使用上为常数级。
 */
public class LeetCode_235_LowestCommonAncestorOfABinarySearchTree{

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
        //Solution1: dfs
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            // 获取节点 p 的值
            int pVal = p.val;

            // 获取节点 q 的值
            int qVal = q.val;

            // 从树的根节点开始
            TreeNode node = root;

            // 遍历树
            while (node != null) {

                // 获取当前节点的值
                int parentVal = node.val;

                if (pVal > parentVal && qVal > parentVal) {
                    // 如果 p 和 q 的值都大于当前节点的值
                    // 说明 p 和 q 都在当前节点的右子树中
                    node = node.right; // 移动到右子树
                } else if (pVal < parentVal && qVal < parentVal) {
                    // 如果 p 和 q 的值都小于当前节点的值
                    // 说明 p 和 q 都在当前节点的左子树中
                    node = node.left; // 移动到左子树
                } else {
                    // 如果 p 和 q 分别在当前节点的两侧
                    // 或其中一个节点的值等于当前节点的值
                    // 那么当前节点就是 p 和 q 的最低公共祖先
                    return node;
                }
            }
            return null; // 如果没有找到公共祖先，返回 null
        }

        // Solution2: iteration
        public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

            // 获取节点 p 的值
            int pVal = p.val;

            // 获取节点 q 的值
            int qVal = q.val;

            // 从树的根节点开始
            TreeNode node = root;

            // 遍历树
            while (node != null) {

                // 获取当前节点的值
                int parentVal = node.val;

                if (pVal > parentVal && qVal > parentVal) {
                    // 如果 p 和 q 的值都大于当前节点的值
                    // 说明 p 和 q 都在当前节点的右子树中
                    node = node.right; // 移动到右子树
                } else if (pVal < parentVal && qVal < parentVal) {
                    // 如果 p 和 q 的值都小于当前节点的值
                    // 说明 p 和 q 都在当前节点的左子树中
                    node = node.left; // 移动到左子树
                } else {
                    // 如果 p 和 q 分别在当前节点的两侧
                    // 或其中一个节点的值等于当前节点的值
                    // 那么当前节点就是 p 和 q 的最低公共祖先
                    return node;
                }
            }
            return null; // 如果没有找到公共祖先，返回 null
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_235_LowestCommonAncestorOfABinarySearchTree().new Solution();

        // 创建一个示例二叉搜索树
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        // 定义两个节点 p 和 q
        TreeNode p = root.left; // 节点 2
        TreeNode q = root.right; // 节点 8

        // 获取 p 和 q 的最低公共祖先
        TreeNode lca = solution.lowestCommonAncestor(root, p, q);

        // 打印结果
        System.out.println("Lowest Common Ancestor of " + p.val + " and " + q.val + " is: " + (lca != null ? lca.val : "null"));
    }
}


/**
Given a binary search tree (BST), find the lowest common ancestor (LCA) node of 
two given nodes in the BST. 

 According to the definition of LCA on Wikipedia: “The lowest common ancestor 
is defined between two nodes p and q as the lowest node in T that has both p and 
q as descendants (where we allow a node to be a descendant of itself).” 

 
 Example 1: 
 
 
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
 

 Example 2: 
 
 
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of 
itself according to the LCA definition.
 

 Example 3: 

 
Input: root = [2,1], p = 2, q = 1
Output: 2
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [2, 10⁵]. 
 -10⁹ <= Node.val <= 10⁹ 
 All Node.val are unique. 
 p != q 
 p and q will exist in the BST. 
 

 Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 10534 
👎 295

*/

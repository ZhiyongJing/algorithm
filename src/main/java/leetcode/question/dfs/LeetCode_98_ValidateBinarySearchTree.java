package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *@Question:  98. Validate Binary Search Tree（验证二叉搜索树）
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 62.11%
 *@Time  Complexity: O(N)  （遍历每个节点一次）
 *@Space Complexity: O(N)  （最坏情况下，递归调用栈的深度）
 */
/**
 * 题目描述：
 * --------------------------
 * LeetCode 98 - Validate Binary Search Tree（验证二叉搜索树）
 *
 * 给定一个二叉树，判断它是否是 **二叉搜索树（BST）**。
 *
 * 二叉搜索树（BST）的定义：
 * 1. **左子树** 的所有节点值必须 **小于** 根节点值。
 * 2. **右子树** 的所有节点值必须 **大于** 根节点值。
 * 3. **所有子树都必须是二叉搜索树**。
 *
 * 例如：
 *  - 输入:
 *     ```
 *         2
 *        / \
 *       1   3
 *     ```
 *    输出: `true`
 *
 *  - 输入:
 *     ```
 *         5
 *        / \
 *       1   4
 *          / \
 *         3   6
 *     ```
 *    输出: `false`
 *    解释: 根节点值 `5`，但右子树包含值 `3`，不满足 BST 规则。
 *
 *
 * 解题思路：
 * --------------------------
 * 该问题有两种常见解法：
 *
 * **方法1：BFS（中序遍历）**
 * ---------------------------------
 * 1. **使用栈进行中序遍历**（左 → 根 → 右）。
 * 2. **检查遍历顺序是否严格递增**：
 *    - 使用 `prev` 变量记录上一个访问的值。
 *    - 如果 `当前节点值 <= prev`，说明不是 BST。
 * 3. **举例**：
 *    ```
 *         10
 *        /  \
 *       5    15
 *          /  \
 *         6    20
 *    ```
 *    - 中序遍历：`5 → 10 → 6 → 15 → 20`（`6` 破坏了严格递增性）。
 *    - 结果：`false`
 *
 * **方法2：DFS（区间限制）**
 * ---------------------------------
 * 1. **使用递归，维护每个节点的合法范围 `[low, high]`**：
 *    - `左子树`：值必须小于当前节点值，因此更新 `high = root.val`。
 *    - `右子树`：值必须大于当前节点值，因此更新 `low = root.val`。
 * 2. **递归检查所有节点是否符合范围**。
 * 3. **举例**：
 *    ```
 *         10
 *        /  \
 *       5    15
 *          /  \
 *         6    20
 *    ```
 *    - `10` 允许范围 `(-∞, +∞)`
 *    - `5` 允许范围 `(-∞, 10)` ✅
 *    - `15` 允许范围 `(10, +∞)` ✅
 *    - `6` 允许范围 `(10, 15)` ❌ （`6` 小于 `10`）
 *    - 结果：`false`
 *
 *
 * 时间和空间复杂度分析：
 * ---------------------------------
 * **方法1（BFS - 迭代中序遍历）**
 * - **时间复杂度**：O(N)，每个节点访问一次。
 * - **空间复杂度**：O(N)，最坏情况下（树是链状），栈的深度等于节点数。
 *
 * **方法2（DFS - 递归区间限制）**
 * - **时间复杂度**：O(N)，每个节点访问一次。
 * - **空间复杂度**：O(N)，最坏情况下（树的深度等于节点数）。
 */


public class LeetCode_98_ValidateBinarySearchTree {

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

        // 方法1: 迭代（BFS）中序遍历
        public boolean isValidBST(TreeNode root) {
            // 使用栈进行中序遍历
            Deque<TreeNode> stack = new ArrayDeque<>();
            Integer prev = null; // 记录上一个访问的节点值（用于比较）

            // 迭代遍历二叉树
            while (!stack.isEmpty() || root != null) {
                // 先遍历左子树，将所有左节点入栈
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }

                // 取出栈顶元素
                root = stack.pop();

                // 中序遍历的顺序应该是 **严格递增** 的，如果当前值 <= 前一个值，则不是 BST
                if (prev != null && root.val <= prev) {
                    return false;
                }
                prev = root.val; // 更新 prev 值

                // 遍历右子树
                root = root.right;
            }
            return true;
        }

        // 方法2: 递归（DFS）区间限制法
        public boolean validate(TreeNode root, Integer low, Integer high) {
            // 递归终止条件：空树是有效的 BST
            if (root == null) {
                return true;
            }

            // 检查当前节点的值是否在 [low, high] 范围内
            if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
                return false;
            }

            // 递归检查左右子树：
            // - 右子树的所有值必须大于当前节点，因此更新 low = root.val
            // - 左子树的所有值必须小于当前节点，因此更新 high = root.val
            return validate(root.right, root.val, high) && validate(root.left, low, root.val);
        }

        // 调用递归函数的入口
        public boolean isValidBST2(TreeNode root) {
            return validate(root, null, null);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_98_ValidateBinarySearchTree.Solution solution = new LeetCode_98_ValidateBinarySearchTree().new Solution();

        // 构建测试用例
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode root2 = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        TreeNode root3 = new TreeNode(10, new TreeNode(5, new TreeNode(1), new TreeNode(8)), new TreeNode(15, new TreeNode(12), new TreeNode(20)));

        // 测试方法1（BFS）
        System.out.println(solution.isValidBST(root1)); // true
        System.out.println(solution.isValidBST(root2)); // false
        System.out.println(solution.isValidBST(root3)); // true

        // 测试方法2（DFS）
        System.out.println(solution.isValidBST2(root1)); // true
        System.out.println(solution.isValidBST2(root2)); // false
        System.out.println(solution.isValidBST2(root3)); // true
    }
}

/**
 Given the root of a binary tree, determine if it is a valid binary search tree (
 BST).

 A valid BST is defined as follows:


 The left subtree of a node contains only nodes with keys less than the node's
 key.
 The right subtree of a node contains only nodes with keys greater than the
 node's key.
 Both the left and right subtrees must also be binary search trees.



 Example 1:


 Input: root = [2,1,3]
 Output: true


 Example 2:


 Input: root = [5,1,4,null,null,3,6]
 Output: false
 Explanation: The root node's value is 5 but its right child's value is 4.



 Constraints:


 The number of nodes in the tree is in the range [1, 10⁴].
 -2³¹ <= Node.val <= 2³¹ - 1


 Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 16121
 👎 1313

 */

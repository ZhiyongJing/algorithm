package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 *@Question:  545. Boundary of Binary Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 70.42%
 *@Time  Complexity: O(N) // 需要遍历整个树一次
 *@Space Complexity: O(N) // 递归调用栈和存储边界节点的空间
 */
/*
 * 545. Boundary of Binary Tree（二叉树的边界）
 *
 * =====================
 * 题目描述：
 * =====================
 * 给定一棵二叉树的根节点 root，要求按照以下规则返回其 **边界遍历序列**：
 * - **左边界**：从根节点开始，沿着左子树路径向下的所有节点（不包括叶子节点）。
 * - **叶子节点**：所有 **不属于左边界或右边界的叶子节点**，按照**从左到右**的顺序。
 * - **右边界**：从 **最右叶子节点向根** 逆序排列的所有节点（不包括叶子节点）。
 * - **最终边界序列** = 左边界 + 叶子节点 + 右边界（逆序）
 *
 * **示例：**
 * **输入：**
 * ```
 *        1
 *      /   \
 *     2     3
 *    / \   / \
 *   4   5 6   7
 *      / \  / \
 *     8   9 10 11
 * ```
 * **输出：** `[1, 2, 4, 8, 9, 10, 11, 7, 3]`
 *
 * =====================
 * 超详细解题思路：
 * =====================
 * 1️⃣ **整体思路**
 *    - 由于**二叉树的边界由左边界、叶子节点、右边界** 组成，因此我们要 **分类存储这些节点**：
 *      - 使用 `left_boundary` 记录左边界
 *      - 使用 `leaves` 记录叶子节点
 *      - 使用 `right_boundary` 记录右边界（最终要逆序）
 *    - 采用 **前序遍历** 遍历二叉树，判断当前节点的 **角色（左边界、右边界、叶子节点）**：
 *      - **左边界**：沿着左子树优先加入 `left_boundary`
 *      - **右边界**：沿着右子树优先加入 `right_boundary`
 *      - **叶子节点**：无左右子节点，加入 `leaves`
 *    - **最后按照顺序组合：** `左边界 + 叶子节点 + 右边界（逆序）`
 *
 * 2️⃣ **分类节点并存储**
 *    - **左边界：**
 *      - **规则**：
 *        - 若当前节点是 **根节点** 或 **左子树存在**，加入 `left_boundary`。
 *        - 若无左子树但有右子树（特殊情况），也加入 `left_boundary`。
 *      - **示例：**
 *        ```
 *        1
 *       /
 *      2
 *     /
 *    4
 *        ```
 *        - `left_boundary = [1, 2, 4]`
 *
 *    - **叶子节点：**
 *      - **规则**：
 *        - 若当前节点无左右子树，则加入 `leaves`。
 *      - **示例：**
 *        ```
 *         1
 *        / \
 *       2   3
 *      /     \
 *     4       5
 *        ```
 *        - `leaves = [4, 5]`
 *
 *    - **右边界（逆序存储）：**
 *      - **规则**：
 *        - 若当前节点是 **右子树的一部分**，加入 `right_boundary`。
 *        - 若无右子树但有左子树（特殊情况），也加入 `right_boundary`。
 *      - **示例：**
 *        ```
 *         1
 *          \
 *           3
 *            \
 *             7
 *        ```
 *        - `right_boundary（存储时逆序） = [3, 7]`
 *
 * 3️⃣ **使用前序遍历（Preorder Traversal）**
 *    - 递归遍历整棵树，使用 `flag` 变量标记当前节点的身份：
 *      - `flag == 0`：根节点
 *      - `flag == 1`：左边界
 *      - `flag == 2`：右边界
 *      - `flag == 3`：普通节点（即非边界）
 *    - **左子树遍历时传递** `leftChildFlag`
 *    - **右子树遍历时传递** `rightChildFlag`
 *
 * 4️⃣ **示例解析**
 *    **输入：**
 *    ```
 *        1
 *      /   \
 *     2     3
 *    / \   / \
 *   4   5 6   7
 *      / \  / \
 *     8   9 10 11
 *    ```
 *    **分类结果**
 *    - `left_boundary = [1, 2, 4]`
 *    - `leaves = [8, 9, 10, 11]`
 *    - `right_boundary = [7, 3]`（逆序存储）
 *    **最终结果**
 *    ```
 *    [1, 2, 4, 8, 9, 10,
*/
public class LeetCode_545_BoundaryOfBinaryTree{

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
    public class Solution {
        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
            // 左边界列表
            List<Integer> left_boundary = new LinkedList<>();
            // 右边界列表
            List<Integer> right_boundary = new LinkedList<>();
            // 叶子节点列表
            List<Integer> leaves = new LinkedList<>();

            // 前序遍历整棵树，并分类存储不同部分
            preorder(root, left_boundary, right_boundary, leaves, 0);

            // 先添加左边界，然后叶子节点，最后右边界（从下往上）
            left_boundary.addAll(leaves);
            left_boundary.addAll(right_boundary);

            // 返回完整的边界节点
            return left_boundary;
        }

        // 判断当前节点是否为叶子节点（无左右子节点）
        public boolean isLeaf(TreeNode cur) {
            return (cur.left == null && cur.right == null);
        }

        // 判断当前节点是否在右边界
        public boolean isRightBoundary(int flag) {
            return (flag == 2);
        }

        // 判断当前节点是否在左边界
        public boolean isLeftBoundary(int flag) {
            return (flag == 1);
        }

        // 判断当前节点是否为根节点
        public boolean isRoot(int flag) {
            return (flag == 0);
        }

        // 确定左子节点的标记值
        public int leftChildFlag(TreeNode cur, int flag) {
            // 如果当前节点是左边界或者根节点，则左子节点仍然属于左边界
            if (isLeftBoundary(flag) || isRoot(flag))
                return 1;
                // 如果当前节点是右边界且无右子节点，则左子节点成为右边界
            else if (isRightBoundary(flag) && cur.right == null)
                return 2;
                // 其他情况，左子节点不属于边界
            else return 3;
        }

        // 确定右子节点的标记值
        public int rightChildFlag(TreeNode cur, int flag) {
            // 如果当前节点是右边界或者根节点，则右子节点仍然属于右边界
            if (isRightBoundary(flag) || isRoot(flag))
                return 2;
                // 如果当前节点是左边界且无左子节点，则右子节点成为左边界
            else if (isLeftBoundary(flag) && cur.left == null)
                return 1;
                // 其他情况，右子节点不属于边界
            else return 3;
        }

        // 前序遍历二叉树，分类存储左边界、右边界和叶子节点
        public void preorder(TreeNode cur, List<Integer> left_boundary, List<Integer> right_boundary, List<Integer> leaves, int flag) {
            // 递归终止条件：当前节点为空
            if (cur == null)
                return;

            // 如果当前节点属于右边界，则从前面插入到右边界列表
            if (isRightBoundary(flag))
                right_boundary.add(0, cur.val);
                // 如果当前节点属于左边界或者根节点，则按顺序加入左边界列表
            else if (isLeftBoundary(flag) || isRoot(flag))
                left_boundary.add(cur.val);
                // 如果当前节点是叶子节点，则加入叶子节点列表
            else if (isLeaf(cur))
                leaves.add(cur.val);

            // 递归遍历左子树，传递新的标记值
            preorder(cur.left, left_boundary, right_boundary, leaves, leftChildFlag(cur, flag));
            // 递归遍历右子树，传递新的标记值
            preorder(cur.right, left_boundary, right_boundary, leaves, rightChildFlag(cur, flag));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_545_BoundaryOfBinaryTree().new Solution();

        // 构造测试二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);
        root.right.left.left = new TreeNode(10);
        root.right.left.right = new TreeNode(11);

        // 打印二叉树的边界
        System.out.println("二叉树的边界节点: " + solution.boundaryOfBinaryTree(root));
    }
}

/**
The boundary of a binary tree is the concatenation of the root, the left 
boundary, the leaves ordered from left-to-right, and the reverse order of the right 
boundary. 

 The left boundary is the set of nodes defined by the following: 

 
 The root node's left child is in the left boundary. If the root does not have 
a left child, then the left boundary is empty. 
 If a node in the left boundary and has a left child, then the left child is in 
the left boundary. 
 If a node is in the left boundary, has no left child, but has a right child, 
then the right child is in the left boundary. 
 The leftmost leaf is not in the left boundary. 
 

 The right boundary is similar to the left boundary, except it is the right 
side of the root's right subtree. Again, the leaf is not part of the right boundary,
 and the right boundary is empty if the root does not have a right child. 

 The leaves are nodes that do not have any children. For this problem, the root 
is not a leaf. 

 Given the root of a binary tree, return the values of its boundary. 

 
 Example 1: 
 
 
Input: root = [1,null,2,3,4]
Output: [1,3,4,2]
Explanation:
- The left boundary is empty because the root does not have a left child.
- The right boundary follows the path starting from the root's right child 2 -> 
4.
  4 is a leaf, so the right boundary is [2].
- The leaves from left to right are [3,4].
Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
 

 Example 2: 
 
 
Input: root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
Output: [1,2,4,7,8,9,10,6,3]
Explanation:
- The left boundary follows the path starting from the root's left child 2 -> 4.

  4 is a leaf, so the left boundary is [2].
- The right boundary follows the path starting from the root's right child 3 -> 
6 -> 10.
  10 is a leaf, so the right boundary is [3,6], and in reverse order is [6,3].
- The leaves from left to right are [4,7,8,9,10].
Concatenating everything results in [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,
8,9,10,6,3].
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁴]. 
 -1000 <= Node.val <= 1000 
 

 Related Topics Tree Depth-First Search Binary Tree 👍 1374 👎 2277

*/
package leetcode.question.bfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Question:  102. Binary Tree Level Order Traversal
 * @Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 58.25%
 * @Time  Complexity: O(N) [N is the number of nodes in the tree]
 * @Space Complexity: O(N) [Additional space for the result list]
 */

/*
 * 题目描述：
 * 给定一个二叉树，要求按层次遍历（Level Order Traversal），
 * 即从上到下逐层访问二叉树的每个节点，并按层输出节点值。
 *
 * 输入：
 * - 一个二叉树的根节点 root。
 *
 * 输出：
 * - 一个 List<List<Integer>>，其中每个子列表表示二叉树的某一层的节点值。
 *
 * 示例 1：
 * 输入：
 *        3
 *       / \
 *      9  20
 *        /  \
 *       15   7
 *
 * 输出：
 * [[3], [9, 20], [15, 7]]
 *
 * 约束：
 * - 树的节点数量在 [0, 2000] 之间。
 * - -1000 <= Node.val <= 1000。
 */

/*
 * 解题思路：
 * 1. **使用 BFS（广度优先搜索）进行层序遍历**
 *    - BFS 适用于层序遍历，因为它逐层处理节点。
 *    - 使用 **队列（Queue）** 进行遍历，每次处理当前层的所有节点后，进入下一层。
 *    - **示例**：
 *      输入：
 *           3
 *          / \
 *         9  20
 *           /  \
 *          15   7
 *      - BFS 过程：
 *        - 队列初始化：[3]
 *        - 处理第 1 层：[3]，添加子节点 [9, 20] -> 结果 `[[3]]`
 *        - 处理第 2 层：[9, 20]，添加子节点 [15, 7] -> 结果 `[[3], [9, 20]]`
 *        - 处理第 3 层：[15, 7] -> 结果 `[[3], [9, 20], [15, 7]]`
 *
 * 2. **使用 DFS（深度优先搜索）进行层序遍历**
 *    - DFS 适用于按深度递归，每次递归时记录当前层级 `level`。
 *    - 递归访问左子树和右子树，并存储在 `level` 对应的列表中。
 *    - **示例**：
 *      - 对于 `helper(node, level)`，`level` 代表当前层级：
 *        - 访问 `3`（level=0），存入 `[[3]]`
 *        - 访问 `9`（level=1），存入 `[[3], [9]]`
 *        - 访问 `20`（level=1），存入 `[[3], [9, 20]]`
 *        - 访问 `15`（level=2），存入 `[[3], [9, 20], [15]]`
 *        - 访问 `7`（level=2），存入 `[[3], [9, 20], [15, 7]]`
 *    - 递归深度决定了 DFS 的执行顺序。
 */

/*
 * 时间和空间复杂度分析：
 *
 * 1. **BFS 时间复杂度：O(N)**
 *    - 需要遍历所有 `N` 个节点，每个节点只访问一次，因此时间复杂度为 `O(N)`。
 *
 * 2. **DFS 时间复杂度：O(N)**
 *    - 每个节点访问一次并存入 `level` 对应列表，因此时间复杂度为 `O(N)`。
 *
 * 3. **BFS 空间复杂度：O(N)**
 *    - 最坏情况下，队列存储整层的节点数，最多 `O(N)` 个节点。
 *
 * 4. **DFS 空间复杂度：O(H)**
 *    - 递归调用的最大深度为树的高度 `H`，最坏情况下（单链树）`H = O(N)`，因此空间复杂度 `O(N)`。
 *    - 最好情况（平衡树）`H = O(log N)`，则空间复杂度 `O(log N)`。
 *
 * 总结：
 * - **BFS 适用于层次遍历（更直观）**
 * - **DFS 适用于递归方式（可用于变形题）**
 */

public class LeetCode_102_BinaryTreeLevelOrderTraversal {

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 二叉树节点的定义
     * public class TreeNode {
     *     int val; // 节点的值
     *     TreeNode left; // 左子节点
     *     TreeNode right; // 右子节点
     *     TreeNode() {} // 无参构造函数
     *     TreeNode(int val) { this.val = val; } // 仅设置节点值的构造函数
     *     TreeNode(int val, TreeNode left, TreeNode right) { // 设置节点值及左右子节点的构造函数
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        // 用于存储每一层的节点值
        List<List<Integer>> levels = new ArrayList<List<Integer>>();

        /**
         * 递归方式的辅助函数（DFS 深度优先搜索）
         * @param node  当前遍历的节点
         * @param level 当前节点所在的层级（根节点为第 0 层）
         */
        public void helper(TreeNode node, int level) {
            // 如果当前层级列表不存在，则创建新的层级列表
            if (levels.size() == level)
                levels.add(new ArrayList<Integer>());

            // 将当前节点值加入对应层级的列表中
            levels.get(level).add(node.val);

            // 递归遍历左子节点，并将层级加 1
            if (node.left != null)
                helper(node.left, level + 1);
            // 递归遍历右子节点，并将层级加 1
            if (node.right != null)
                helper(node.right, level + 1);
        }

        /**
         * 迭代方式的层序遍历（BFS 广度优先搜索）
         * @param node 根节点
         */
        public void bfs(TreeNode node) {
            // 如果根节点为空，直接返回
            if (node == null) return;

            // 使用队列来进行层序遍历
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(node); // 将根节点入队

            int level = 0; // 记录当前层级
            while (!queue.isEmpty()) {
                // 创建当前层的存储列表

//                levels.add(new ArrayList<>());
                List<Integer> currentLevel = new ArrayList<>();

                int level_length = queue.size(); // 计算当前层的节点数
                for (int i = 0; i < level_length; i++) {
                    // 取出队列中的节点
                    TreeNode currentNode = queue.remove();
//                    levels.get(level).add(currentNode.val); // 存储当前层的节点值
                    currentLevel.add(currentNode.val);
                    // 将左子节点加入队列
                    if (currentNode.left != null) queue.add(currentNode.left);
                    // 将右子节点加入队列
                    if (currentNode.right != null) queue.add(currentNode.right);
                }

                levels.add(currentLevel);
//                level++; // 进入下一层
            }
        }

        /**
         * 使用 BFS 进行二叉树层序遍历
         * @param root 根节点
         * @return 每一层的节点值列表
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) return levels; // 如果根节点为空，返回空列表
            bfs(root); // 调用 BFS 遍历
            return levels;
        }

        /**
         * 使用 DFS 进行二叉树层序遍历
         * @param root 根节点
         * @return 每一层的节点值列表
         */
        public List<List<Integer>> levelOrder2(TreeNode root) {
            if (root == null) return levels; // 如果根节点为空，返回空列表
            helper(root, 0); // 调用递归 DFS 遍历
            return levels;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_102_BinaryTreeLevelOrderTraversal outer = new LeetCode_102_BinaryTreeLevelOrderTraversal();
        Solution solution = outer.new Solution();

        // 创建测试二叉树：
        //        3
        //       / \
        //      9  20
        //        /  \
        //       15   7
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        // 测试 BFS 版本的层序遍历
        List<List<Integer>> resultBFS = solution.levelOrder(root);
        System.out.println("BFS 层序遍历结果: " + resultBFS); // 预期输出 [[3], [9, 20], [15, 7]]

        // 重新创建 solution 实例，防止复用已修改的 levels 列表
        solution = outer.new Solution();

        // 测试 DFS 版本的层序遍历
        List<List<Integer>> resultDFS = solution.levelOrder2(root);
        System.out.println("DFS 层序遍历结果: " + resultDFS); // 预期输出 [[3], [9, 20], [15, 7]]
    }
}

/**
Given the root of a binary tree, return the level order traversal of its nodes' 
values. (i.e., from left to right, level by level). 

 
 Example 1: 
 
 
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
 

 Example 2: 

 
Input: root = [1]
Output: [[1]]
 

 Example 3: 

 
Input: root = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 2000]. 
 -1000 <= Node.val <= 1000 
 

 Related Topics Tree Breadth-First Search Binary Tree 👍 14661 👎 290

*/

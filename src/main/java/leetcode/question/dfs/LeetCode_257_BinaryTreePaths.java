package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *@Question:  257. Binary Tree Paths
 *@Difficulty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 42.65%
 *@Time  Complexity: O(N * logN) for solution1, iterate tree take N, copy logN long path take logN time, O(N) for oslution2
 *@Space Complexity: O(N) for both
 */
/**
 * 题目描述：
 * LeetCode 257. Binary Tree Paths
 * 给定一个二叉树的根节点 root，返回所有从根节点到叶子节点的路径。
 * 叶子节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：
 *     1
 *    / \
 *   2   3
 *    \
 *     5
 * 输出：["1->2->5", "1->3"]
 * 解释：
 *  - 从根节点 1 出发，可以走到 2，然后再到 5，路径为 "1->2->5"
 *  - 从根节点 1 出发，可以走到 3，路径为 "1->3"
 *
 * 示例 2：
 * 输入：
 *   1
 * 输出：["1"]
 * 解释：只有一个根节点，路径只有 "1"
 *
 * 解题思路：
 * 1. **递归 DFS 方法（binaryTreePaths1）**
 *    - 使用递归的方式遍历二叉树，从根节点开始构造路径。
 *    - 递归终止条件：当前节点为空时，直接返回。
 *    - 如果当前节点是叶子节点（左右子节点都为空），则将当前路径加入结果列表。
 *    - 如果当前节点有子节点，则在当前路径后面追加 "->"，然后递归调用左右子节点。
 *
 *    举例：
 *    - 初始 `root = 1`，路径 `""`，列表 `[]`
 *    - 进入 `1`，路径变为 `"1"`
 *    - 递归进入 `2`，路径变为 `"1->2"`
 *    - 继续递归进入 `5`，路径变为 `"1->2->5"`，因为 `5` 是叶子节点，将其加入列表
 *    - 返回到 `2`，没有左子节点，回溯到 `1`
 *    - 递归进入 `3`，路径变为 `"1->3"`，因为 `3` 是叶子节点，将其加入列表
 *    - 结果列表为 `["1->2->5", "1->3"]`
 *
 * 2. **迭代方法（binaryTreePaths）**
 *    - 使用栈来模拟 DFS，遍历二叉树，同时维护路径信息。
 *    - 维护两个栈：
 *      1. `node_stack` 存储待遍历的节点。
 *      2. `path_stack` 存储到达该节点的路径。
 *    - 迭代弹出栈顶元素：
 *      - 如果是叶子节点，则将路径存入结果列表。
 *      - 如果有左/右子节点，则将子节点压入栈，并更新路径信息。
 *
 *    举例：
 *    - 初始 `node_stack = [1]`，`path_stack = ["1"]`
 *    - 弹出 `1`，路径 `"1"`
 *      - 压入 `2`，路径 `"1->2"`
 *      - 压入 `3`，路径 `"1->3"`
 *    - 弹出 `3`，是叶子节点，存入 `"1->3"`
 *    - 弹出 `2`，路径 `"1->2"`
 *      - 压入 `5`，路径 `"1->2->5"`
 *    - 弹出 `5`，是叶子节点，存入 `"1->2->5"`
 *    - 结果列表 `["1->2->5", "1->3"]`
 *
 * 时间和空间复杂度分析：
 * - 设二叉树的节点数为 N。
 * - **DFS 递归方法**
 *   - **时间复杂度：O(N)**，每个节点访问一次。
 *   - **空间复杂度：O(N)**，最坏情况下（退化为链表）递归栈深度为 O(N)。
 *
 * - **迭代方法（栈模拟 DFS）**
 *   - **时间复杂度：O(N)**，每个节点访问一次。
 *   - **空间复杂度：O(N)**，存储节点和路径信息，最坏情况下占用 O(N) 额外空间。
 */


public class LeetCode_257_BinaryTreePaths{

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
        /**
         * 递归构造二叉树的所有路径
         * @param root 当前遍历的节点
         * @param path 当前路径的字符串表示
         * @param paths 存储所有路径的列表
         */
        public void construct_paths(TreeNode root, String path, List<String> paths) {
            if (root != null) { // 确保当前节点不为空
                path += Integer.toString(root.val); // 将当前节点的值添加到路径字符串中
                if ((root.left == null) && (root.right == null)) // 如果当前节点是叶子节点
                    paths.add(path); // 将完整路径添加到列表中
                else {
                    path += "->"; // 继续构造路径
                    construct_paths(root.left, path, paths); // 递归遍历左子树
                    construct_paths(root.right, path, paths); // 递归遍历右子树
                }
            }
        }

        /**
         * 方案1：使用 DFS（深度优先搜索）
         * @param root 二叉树的根节点
         * @return 包含所有根到叶子路径的列表
         */
        public List<String> binaryTreePaths1(TreeNode root) {
            LinkedList<String> paths = new LinkedList(); // 使用链表存储路径
            construct_paths(root, "", paths); // 调用递归函数构造路径
            return paths;
        }

        /**
         * 方案2：使用栈（迭代方式）
         * @param root 二叉树的根节点
         * @return 包含所有根到叶子路径的列表
         */
        public List<String> binaryTreePaths(TreeNode root) {
            Stack<String> paths = new Stack(); // 用于存储最终结果的路径栈
            if (root == null) // 如果根节点为空，直接返回空列表
                return paths;

            Stack<TreeNode> node_stack = new Stack(); // 用于遍历节点的栈
            Stack<String> path_stack = new Stack(); // 用于存储路径字符串的栈
            node_stack.add(root); // 将根节点入栈
            path_stack.add(Integer.toString(root.val)); // 存储根节点的路径字符串
            TreeNode node;
            String path;

            // 迭代遍历二叉树
            while (!node_stack.isEmpty()) {
                node = node_stack.pop(); // 取出当前节点
                path = path_stack.pop(); // 取出当前路径
                if ((node.left == null) && (node.right == null)) // 如果是叶子节点
                    paths.add(path); // 存储完整路径
                if (node.left != null) { // 处理左子节点
                    node_stack.add(node.left);
                    path_stack.add(path + "->" + Integer.toString(node.left.val));
                }
                if (node.right != null) { // 处理右子节点
                    node_stack.add(node.right);
                    path_stack.add(path + "->" + Integer.toString(node.right.val));
                }
            }
            return paths;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_257_BinaryTreePaths().new Solution();

        // 构造测试用例
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        // 测试 DFS 递归解法
        System.out.println("DFS 递归解法:");
        List<String> result1 = solution.binaryTreePaths1(root);
        System.out.println(result1);

        // 测试 栈 迭代解法
        System.out.println("栈 迭代解法:");
        List<String> result2 = solution.binaryTreePaths(root);
        System.out.println(result2);
    }
}

/**
Given the root of a binary tree, return all root-to-leaf paths in any order. 

 A leaf is a node with no children. 

 
 Example 1: 
 
 
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]
 

 Example 2: 

 
Input: root = [1]
Output: ["1"]
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 100]. 
 -100 <= Node.val <= 100 
 

 Related Topics String Backtracking Tree Depth-First Search Binary Tree 👍 6820 
👎 317

*/
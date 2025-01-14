package leetcode.question.dfs;

import javafx.util.Pair;
import leetcode.util.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *@Question:  662. Maximum Width of Binary Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 42.27%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 题目描述：
 * 给定一棵二叉树，计算该二叉树的最大宽度。树的宽度定义为从某一层的最左边节点到最右边节点的距离。
 * 我们要计算出所有层的宽度，并返回最大宽度。你可以假设每个节点的列索引从0开始。
 *
 * 示例：
 * 输入：
 *         1
 *        / \
 *       3   2
 *      / \   \
 *     5   3   9
 * 输出：4
 * 解释：在上面的示例中，最大宽度是在第2层，宽度为4。
 *
 * 备注：
 * - 假设二叉树的节点数在 [1, 10^5] 之间。
 */

/**
 * 解题思路：
 *
 * 1. 深度优先搜索 (DFS) 解法：
 *    - 使用递归方法遍历二叉树的每一层，记录每一层的第一个节点的列索引。
 *    - 每当访问一个节点时，计算该节点的列索引与该层第一个节点的列索引之差，即为该层的宽度。
 *    - 更新全局最大宽度变量 `maxWidth`。
 *    - 递归遍历左子树和右子树时，将列索引分别设置为 2 * 当前节点的列索引（左子树）和 2 * 当前节点的列索引 + 1（右子树）。
 *    - 这个方法通过列索引的倍数来避免树的结构差异所带来的影响。
 *
 * 示例：
 *  假设我们有如下二叉树：
 *           1
 *         /   \
 *        3     2
 *       / \     \
 *      5   3     9
 *
 *  - 在第一层，根节点的列索引为0。
 *  - 在第二层，节点3的列索引为0，节点2的列索引为1。
 *  - 在第三层，节点5的列索引为0，节点3的列索引为1，节点9的列索引为3。
 *  这样我们可以根据每层的列索引计算宽度。
 *
 * 2. 广度优先搜索 (BFS) 解法：
 *    - 使用队列进行逐层遍历，每次遍历当前层的所有节点，同时记录每个节点的列索引。
 *    - 计算当前层的宽度，即该层的最后一个节点的列索引与第一个节点的列索引之差，再加1。
 *    - 遍历时，对于每个节点，如果它有左子节点，将其列索引设置为当前节点列索引的两倍；如果它有右子节点，将其列索引设置为当前节点列索引的两倍加1。
 *    - 在每次遍历完一层时，更新最大宽度 `maxWidth`。
 *
 * 示例：
 * 假设使用队列进行遍历的顺序：
 * - 第一层：根节点，列索引0。
 * - 第二层：节点3（列索引0）、节点2（列索引1）。
 * - 第三层：节点5（列索引0）、节点3（列索引1）、节点9（列索引3）。
 *
 * 最大宽度通过每层的最左节点和最右节点的列索引计算得到。
 *
 * 总结：
 *  - 深度优先搜索（DFS）通过递归遍历树，同时跟踪每一层的最左节点的列索引，并计算当前层的宽度。
 *  - 广度优先搜索（BFS）通过队列按层遍历树，同时记录每个节点的列索引来计算每层的宽度。
 */

/**
 * 时间复杂度：
 * 1. 对于DFS解法：每个节点仅访问一次，因此时间复杂度为 O(N)，其中N为树中节点的个数。
 * 2. 对于BFS解法：每个节点仅访问一次，并且每次访问需要将左右子节点加入队列，时间复杂度为 O(N)，其中N为树中节点的个数。
 *
 * 综上，DFS和BFS的时间复杂度均为 O(N)。
 *
 * 空间复杂度：
 * 1. 对于DFS解法：递归栈的空间复杂度为 O(H)，其中H为树的高度。最坏情况下，树为单链树，空间复杂度为 O(N)。
 * 2. 对于BFS解法：队列存储节点，队列的空间复杂度为 O(N)，因为在最坏情况下，队列可能需要存储整棵树的节点。
 *
 * 综上，DFS的空间复杂度为 O(H)，BFS的空间复杂度为 O(N)。
 */



public class LeetCode_662_MaximumWidthOfBinaryTree{

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
        private Integer maxWidth = 0;  // 最大宽度
        private HashMap<Integer, Integer> firstColIndexTable;  // 存储每层的第一个列索引

        // 深度优先遍历（DFS），记录每层的最大宽度
        protected void DFS(TreeNode node, Integer depth, Integer colIndex) {
            if (node == null)
                return;

            // 初始化每一层的第一个列索引
            if (!firstColIndexTable.containsKey(depth)) {
                firstColIndexTable.put(depth, colIndex);
            }

            // 获取该层的第一个列索引
            Integer firstColIndex = firstColIndexTable.get(depth);

            // 更新最大宽度
            maxWidth = Math.max(this.maxWidth, colIndex - firstColIndex + 1);

            // 继续深度优先遍历左子树和右子树，注意：优先遍历左子树
            DFS(node.left, depth + 1, 2 * colIndex);
            DFS(node.right, depth + 1, 2 * colIndex + 1);
        }

        //Solution1: dfs 深度优先搜索
        public int widthOfBinaryTree(TreeNode root) {
            // 初始化每层第一个列索引的表
            this.firstColIndexTable = new HashMap<Integer, Integer>();

            // 从根节点开始，深度为0，列索引为0
            DFS(root, 0, 0);

            // 返回最大宽度
            return this.maxWidth;
        }

        //Solution2: bfs 广度优先搜索
        public int widthOfBinaryTree2(TreeNode root) {
            if (root == null)
                return 0;

            // 用队列存储每个节点以及其对应的列索引
            LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
            Integer maxWidth = 0;

            // 添加根节点及其列索引
            queue.addLast(new Pair<>(root, 0));
            while (queue.size() > 0) {
                Pair<TreeNode, Integer> head = queue.getFirst();  // 获取当前层的第一个节点

                // 当前层的大小
                Integer currLevelSize = queue.size();
                Pair<TreeNode, Integer> elem = null;
                for (int i = 0; i < currLevelSize; ++i) {
                    elem = queue.removeFirst();  // 从队列中移除当前节点
                    TreeNode node = elem.getKey();

                    // 如果节点有左子节点，加入队列，列索引为当前列索引的两倍
                    if (node.left != null)
                        queue.addLast(new Pair<>(node.left, 2 * elem.getValue()));

                    // 如果节点有右子节点，加入队列，列索引为当前列索引的两倍加1
                    if (node.right != null)
                        queue.addLast(new Pair<>(node.right, 2 * elem.getValue() + 1));
                }

                // 计算当前层的宽度，最大列索引减去最小列索引加1
                maxWidth = Math.max(maxWidth, elem.getValue() - head.getValue() + 1);
            }

            // 返回最大宽度
            return maxWidth;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_662_MaximumWidthOfBinaryTree().new Solution();

        // 构建测试样例树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        // 测试Solution1
        int result = solution.widthOfBinaryTree(root);
        System.out.println("Maximum Width (DFS): " + result);  // 输出结果

        // 测试Solution2
        result = solution.widthOfBinaryTree2(root);
        System.out.println("Maximum Width (BFS): " + result);  // 输出结果
    }
}

/**
Given the root of a binary tree, return the maximum width of the given tree. 

 The maximum width of a tree is the maximum width among all levels. 

 The width of one level is defined as the length between the end-nodes (the 
leftmost and rightmost non-null nodes), where the null nodes between the end-nodes 
that would be present in a complete binary tree extending down to that level are 
also counted into the length calculation. 

 It is guaranteed that the answer will in the range of a 32-bit signed integer. 


 
 Example 1: 
 
 
Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,
null,9).
 

 Example 2: 
 
 
Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,
null,null,null,null,7).
 

 Example 3: 
 
 
Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 3000]. 
 -100 <= Node.val <= 100 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 9009
 👎 1244

*/
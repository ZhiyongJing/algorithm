package leetcode.question.bfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
  *@Question:  103. Binary Tree Zigzag Level Order Traversal     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 47.9%      
  *@Time  Complexity: O(n)
  *@Space Complexity: O(n)
 */

/**
 * 第一部分：题目描述
 * LeetCode 第 103 题：二叉树的锯齿形层次遍历（Binary Tree Zigzag Level Order Traversal）
 * 给定一个二叉树，要求按照锯齿形（之字形）的顺序遍历其节点值。
 * 锯齿形层次遍历的规则是：
 * - 第一层从左到右遍历。
 * - 第二层从右到左遍历。
 * - 第三层从左到右遍历，以此类推。
 * 输出是一个嵌套列表，每个内部列表表示一层的节点值。
 *
 * 示例：
 * 输入：
 *         3
 *       /   \
 *      9    20
 *          /   \
 *         15    7
 * 输出：[[3], [20, 9], [15, 7]]
 */

/**
 * 第二部分：解题思路
 *
 * 1. 使用广度优先搜索（BFS）遍历二叉树。
 *    - 广度优先搜索适合层次遍历，因为它按层处理每个节点。
 *
 * 2. 使用一个双向队列（Deque）来存储节点值。
 *    - 在遍历时，根据当前层的顺序决定是从左到右添加值，还是从右到左添加值。
 *
 * 3. 使用一个标志变量 is_order_left 控制当前层的遍历顺序。
 *    - 如果 is_order_left 为 true，则从左到右遍历。
 *    - 如果 is_order_left 为 false，则从右到左遍历。
 *
 * 详细步骤举例：
 * - 输入的二叉树结构为：
 *         3
 *       /   \
 *      9    20
 *          /   \
 *         15    7
 *
 * - 第 1 层：
 *   - 节点值为 3。
 *   - 按从左到右的顺序遍历，结果为 [3]。
 *
 * - 第 2 层：
 *   - 节点值为 9 和 20。
 *   - 按从右到左的顺序遍历，结果为 [20, 9]。
 *
 * - 第 3 层：
 *   - 节点值为 15 和 7。
 *   - 按从左到右的顺序遍历，结果为 [15, 7]。
 *
 * - 最终输出结果：[[3], [20, 9], [15, 7]]。
 */

/**
 * 第三部分：时间和空间复杂度分析
 *
 * 时间复杂度：
 * - 每个节点被访问一次，因此时间复杂度为 O(n)，其中 n 是二叉树的节点总数。
 *
 * 空间复杂度：
 * - 空间复杂度取决于队列的最大长度，即一层的最大节点数。
 * - 在最坏情况下（完全二叉树），队列中的节点数最多为 n/2，因此空间复杂度为 O(n)。
 */


public class LeetCode_103_BinaryTreeZigzagLevelOrderTraversal {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            // 如果根节点为空，直接返回空列表
            if (root == null) {
                return new ArrayList<List<Integer>>();
            }

            // 用于存储最终的结果
            List<List<Integer>> results = new ArrayList<List<Integer>>();

            // 初始化一个队列来进行 BFS，添加根节点和分隔符 null
            LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
            node_queue.addLast(root);
            node_queue.addLast(null);

            // 用于存储当前层的节点值
            LinkedList<Integer> level_list = new LinkedList<Integer>();
            // 标志变量，用于决定当前层的顺序是从左到右还是从右到左
            boolean is_order_left = true;

            // 当队列不为空时，执行循环
            while (node_queue.size() > 0) {
                // 调试输出当前队列的内容
                System.out.println(node_queue.toString());
                // 从队列中取出第一个节点
                TreeNode curr_node = node_queue.pollFirst();

                // 如果当前节点不为空
                if (curr_node != null) {
                    // 根据当前层的顺序，将节点值添加到 level_list 中
                    if (is_order_left)
                        level_list.addLast(curr_node.val); // 正序添加到尾部
                    else
                        level_list.addFirst(curr_node.val); // 逆序添加到头部

                    // 将当前节点的左子节点添加到队列中（如果存在）
                    if (curr_node.left != null)
                        node_queue.addLast(curr_node.left);
                    // 将当前节点的右子节点添加到队列中（如果存在）
                    if (curr_node.right != null)
                        node_queue.addLast(curr_node.right);

                } else {
                    // 当遇到分隔符 null 时，表示当前层的节点已经遍历完毕
                    results.add(level_list); // 将当前层的节点值列表添加到结果集中
                    level_list = new LinkedList<Integer>(); // 初始化新的 level_list 用于下一层

                    // 如果队列中还有节点，添加一个新的分隔符 null
                    if (node_queue.size() > 0)
                        node_queue.addLast(null);

                    // 翻转标志变量，切换层次顺序
                    is_order_left = !is_order_left;
                }
            }
            // 返回最终的结果
            return results;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_103_BinaryTreeZigzagLevelOrderTraversal().new Solution();
        // 测试
        // 创建示例二叉树
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        // 调用 zigzagLevelOrder 方法并打印结果
        List<List<Integer>> result = solution.zigzagLevelOrder(root);
        System.out.println(result);
    }
}

/**
Given the root of a binary tree, return the zigzag level order traversal of its 
nodes' values. (i.e., from left to right, then right to left for the next level 
and alternate between). 

 
 Example 1: 
 
 
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
 

 Example 2: 

 
Input: root = [1]
Output: [[1]]
 

 Example 3: 

 
Input: root = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 2000]. 
 -100 <= Node.val <= 100 
 

 Related Topics Tree Breadth-First Search Binary Tree 👍 10341 👎 272

*/

package leetcode.question.bfs;

import javafx.util.Pair;
import leetcode.util.TreeNode;

import java.util.*;

/**
 *@Question:  314. Binary Tree Vertical Order Traversal
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 88.5%
 *@Time  Complexity: O(N) 其中 N 为树中节点的数量
 *@Space Complexity: O(N)
 */
/**
 * 题目描述：
 * 314. Binary Tree Vertical Order Traversal (二叉树的垂直遍历)
 * 给定一棵二叉树，要求按照从左到右的垂直顺序，依次输出每列中的节点值。
 * - 每列的定义：所有节点按照其相对于根节点的水平位置归类，例如根节点所在列为 0，
 *   左子节点在列 -1，右子节点在列 +1。
 * - 如果在同一列内有多个节点，按照从上到下的顺序输出。
 *
 * 示例：
 * 输入：
 *         3
 *        / \
 *       9   20
 *          /  \
 *         15   7
 * 输出：[[9], [3, 15], [20], [7]]
 *
 * 解题思路：
 * 1. 使用层序遍历（广度优先搜索 BFS）：
 *    - 按照从上到下的顺序访问树的每一层节点。
 *    - 记录每个节点的列号，根节点列号为 0，左子节点列号减 1，右子节点列号加 1。
 * 2. 使用哈希表存储列号和节点值：
 *    - 键为列号，值为对应列中的节点值列表。
 *    - 遍历过程中，如果列号不存在于哈希表中，则初始化。
 *    - 将当前节点值加入对应列号的列表中。
 * 3. 排序列号并生成结果：
 *    - 遍历完成后，将所有列号排序。
 *    - 按列号顺序将每列的节点值加入最终结果列表。
 *
 * 举例解释：
 * 示例输入：
 *         3
 *        / \
 *       9   20
 *          /  \
 *         15   7
 *
 * - 初始化队列：[Pair(3, 0)]，列号为 0。
 * - 第一步：处理节点 3，列号 0：
 *   - 将节点值 3 加入列 0 的列表。
 *   - 将左子节点 9（列号 -1）和右子节点 20（列号 1）加入队列。
 * - 第二步：处理节点 9，列号 -1：
 *   - 将节点值 9 加入列 -1 的列表。
 * - 第三步：处理节点 20，列号 1：
 *   - 将节点值 20 加入列 1 的列表。
 *   - 将节点 20 的子节点 15（列号 0）和 7（列号 2）加入队列。
 * - 依次处理节点 15 和 7。
 * - 排序列号 [-1, 0, 1, 2]，生成结果 [[9], [3, 15], [20], [7]]。
 *
 * 时间和空间复杂度：
 * 1. 时间复杂度：O(N)
 *    - 每个节点仅访问一次，处理时间与节点数量 N 成正比。
 *    - 对列号排序的时间复杂度为 O(KlogK)，其中 K 是列号的数量。
 *    - 由于 K 通常远小于 N，因此主导时间复杂度为 O(N)。
 * 2. 空间复杂度：O(N)
 *    - 队列和哈希表均需存储节点及其对应信息，总共占用 O(N) 的空间。
 */

public class LeetCode_314_BinaryTreeVerticalOrderTraversal {

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
         * 二叉树的垂直遍历
         *
         * @param root 二叉树的根节点
         * @return 返回二叉树的垂直遍历结果
         */
        public List<List<Integer>> verticalOrder(TreeNode root) {
            // 初始化结果列表
            List<List<Integer>> output = new ArrayList();
            // 如果树为空，直接返回空结果
            if (root == null) {
                return output;
            }

            // 使用哈希表存储每一列的节点值，键为列号，值为该列对应的节点值列表
            Map<Integer, ArrayList> columnTable = new HashMap();
            // 使用队列实现层序遍历，同时存储节点和对应的列号
            Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
            // 根节点的初始列号为 0
            int column = 0;
            queue.offer(new Pair(root, column));

            // 开始广度优先搜索（BFS）
            while (!queue.isEmpty()) {
                // 取出队首元素
                Pair<TreeNode, Integer> p = queue.poll();
                // 获取当前节点和列号
                root = p.getKey();
                column = p.getValue();

                // 如果当前节点不为空
                if (root != null) {
                    // 如果当前列号在哈希表中不存在，初始化该列的节点值列表
                    if (!columnTable.containsKey(column)) {
                        columnTable.put(column, new ArrayList<Integer>());
                    }
                    // 将当前节点值加入对应列的列表
                    columnTable.get(column).add(root.val);

                    // 将左子节点加入队列，列号减 1
                    queue.offer(new Pair(root.left, column - 1));
                    // 将右子节点加入队列，列号加 1
                    queue.offer(new Pair(root.right, column + 1));
                }
            }
            System.out.println(columnTable);

            // 将所有列号排序
            List<Integer> sortedKeys = new ArrayList<Integer>(columnTable.keySet());
            Collections.sort(sortedKeys);
            // 按列号顺序将节点值加入结果列表
            for(int k : sortedKeys) {
                output.add(columnTable.get(k));
            }

            return output;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_314_BinaryTreeVerticalOrderTraversal solution = new LeetCode_314_BinaryTreeVerticalOrderTraversal();
        Solution verticalOrderSolution = solution.new Solution();

        // 测试用例1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("测试用例1: " + verticalOrderSolution.verticalOrder(root1));

        // 测试用例2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(8);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(0);
        root2.right.left = new TreeNode(1);
        root2.right.right = new TreeNode(7);
        System.out.println("测试用例2: " + verticalOrderSolution.verticalOrder(root2));

        // 测试用例3
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(9);
        root3.right = new TreeNode(8);
        root3.left.left = new TreeNode(4);
        root3.left.right = new TreeNode(0);
        root3.right.left = new TreeNode(1);
        root3.right.right = new TreeNode(7);
        root3.right.left.left = new TreeNode(2);
        root3.right.left.right = new TreeNode(5);
        System.out.println("测试用例3: " + verticalOrderSolution.verticalOrder(root3));
    }
}


/**
 Given the root of a binary tree, return the vertical order traversal of its
 nodes' values. (i.e., from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to
 right.


 Example 1:


 Input: root = [3,9,20,null,null,15,7]
 Output: [[9],[3,15],[20],[7]]


 Example 2:


 Input: root = [3,9,8,4,0,1,7]
 Output: [[4],[9],[3,0,1],[8],[7]]


 Example 3:


 Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 Output: [[4],[9,5],[3,0,1],[8,2],[7]]



 Constraints:


 The number of nodes in the tree is in the range [0, 100].
 -100 <= Node.val <= 100


 Related Topics Hash Table Tree Depth-First Search Breadth-First Search Binary
 Tree 👍 3082 👎 305

 */

package leetcode.question.bfs;

import javafx.util.Pair;
import leetcode.util.TreeNode;

import java.util.*;

/**
  *@Question:  314. Binary Tree Vertical Order Traversal     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 88.5%      
  *@Time  Complexity: O(N) 其中 N 为树中节点的数量
  *@Space Complexity: O(N)
 */

/**
 * 这道题目是要求对二叉树进行垂直遍历，即按照列的顺序从左到右输出每一列的节点值。如果有多个节点在同一列，应按从上到下的顺序输出。
 *
 * ### 算法思路：
 *
 * 1. **初始化：** 首先，我们使用一个哈希表 `columnTable` 来存储每一列的节点值。键是列号，值是该列上的节点值列表。
 * 2. **BFS 遍历：** 我们使用广度优先搜索（BFS）进行遍历。将树的根节点和其对应的列号（初始为0）加入队列中。
 * 3. **遍历节点：** 在每一轮遍历中，从队列中取出一个节点和对应的列号。将节点值加入到 `columnTable` 中对应列的节点值列表中。
 * 4. **扩展下一层：** 将当前节点的左孩子和右孩子加入到队列中，同时更新它们对应的列号。左孩子列号为当前列号减1，右孩子列号为当前列号加1。
 * 5. **按列号排序：** 遍历完成后，对 `columnTable` 中的列号进行排序，确保结果中列的顺序是从左到右的。
 * 6. **构建结果：** 将排序后的列号对应的节点值列表按顺序加入到结果列表中。
 *
 * ### 复杂度分析：
 *
 * - **时间复杂度：** 每个节点都会入队列一次，所以时间复杂度是 O(N)，其中 N 为树中节点的数量。
 * - **空间复杂度：** 使用了哈希表 `columnTable` 存储节点值，队列存储节点和列号，所以空间复杂度是 O(N)。
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
            List<List<Integer>> output = new ArrayList();
            if (root == null) {
                return output;
            }

            // 使用哈希表存储每一列的节点值
            Map<Integer, ArrayList> columnTable = new HashMap();
            Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
            int column = 0;
            queue.offer(new Pair(root, column));

            while (!queue.isEmpty()) {
                Pair<TreeNode, Integer> p = queue.poll();
                root = p.getKey();
                column = p.getValue();

                if (root != null) {
                    if (!columnTable.containsKey(column)) {
                        columnTable.put(column, new ArrayList<Integer>());
                    }
                    columnTable.get(column).add(root.val);

                    // 将左孩子和右孩子入队，列号分别减1和加1
                    queue.offer(new Pair(root.left, column - 1));
                    queue.offer(new Pair(root.right, column + 1));
                }
            }

            // 将列号排序后，按顺序添加到结果列表
            List<Integer> sortedKeys = new ArrayList<Integer>(columnTable.keySet());
            Collections.sort(sortedKeys);
            for(int k : sortedKeys) {
                output.add(columnTable.get(k));
            }

            System.out.println(columnTable);

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

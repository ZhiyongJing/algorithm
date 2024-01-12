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
 * 这是一道二叉树层次遍历的变体问题，要求按照锯齿形（之字形）的顺序返回每层的节点值。具体算法思路如下：
 *
 * 1. 首先判断根节点是否为空，如果为空，直接返回空列表。
 *
 * 2. 使用BFS（广度优先搜索）进行层次遍历。创建一个队列 `node_queue` 来存储每一层的节点。在队列中，每层的节点之间用 `null` 来分隔。
 *
 * 3. 使用变量 `is_order_left` 来标识当前层的遍历顺序是从左到右还是从右到左。如果是从左到右，
 * 将节点值添加到当前层的列表的末尾；如果是从右到左，将节点值添加到列表的开头。
 *
 * 4. 在遍历每一层的节点时，将其左右子节点按照BFS的方式加入队列，继续遍历。
 *
 * 5. 当遇到队列中的 `null` 分隔符时，表示当前层的遍历结束，将当前层的列表加入结果列表中，并清空当前层的列表，然后继续下一层的遍历。
 *
 * 6. 最终，得到结果列表，其中包含了每一层的节点值按照锯齿形遍历的顺序。
 *
 * **时间复杂度：** 每个节点都会入队和出队一次，因此时间复杂度是 O(N)，其中 N 是树中的节点数。
 *
 * **空间复杂度：** 需要使用队列存储节点，队列的最大长度不会超过树的最大宽度，即每层的节点数。在最坏的情况下，
 * 当树是平衡的时候，空间复杂度是 O(2^h)，其中 h 是树的高度。
 */

public class LeetCode_103_BinaryTreeZigzagLevelOrderTraversal {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<List<Integer>>();
            }

            List<List<Integer>> results = new ArrayList<List<Integer>>();

            // 添加根元素和分隔符来启动BFS循环
            LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
            node_queue.addLast(root);
            node_queue.addLast(null);

            LinkedList<Integer> level_list = new LinkedList<Integer>();
            boolean is_order_left = true;

            while (node_queue.size() > 0) {
                System.out.println(node_queue.toString());
                TreeNode curr_node = node_queue.pollFirst();
                if (curr_node != null) {
                    // 根据层次顺序决定是正序还是逆序添加节点值
                    if (is_order_left)
                        level_list.addLast(curr_node.val);
                    else
                        level_list.addFirst(curr_node.val);

                    // 添加左右子节点到队列中
                    if (curr_node.left != null)
                        node_queue.addLast(curr_node.left);
                    if (curr_node.right != null)
                        node_queue.addLast(curr_node.right);

                } else {
                    // 当前层的扫描结束
                    results.add(level_list);
                    level_list = new LinkedList<Integer>();
                    // 为下一层准备
                    if (node_queue.size() > 0)
                        node_queue.addLast(null);
                    is_order_left = !is_order_left;
                }
            }
            return results;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_103_BinaryTreeZigzagLevelOrderTraversal().new Solution();
        // 测试
        // 创建示例二叉树
        // TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        // List<List<Integer>> result = solution.zigzagLevelOrder(root);
        // System.out.println(result);
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

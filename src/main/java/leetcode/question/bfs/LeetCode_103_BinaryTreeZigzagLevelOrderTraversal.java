package leetcode.question.bfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
  *@Question:  103. Binary Tree Zigzag Level Order Traversal     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 47.9%      
  *@Time  Complexity: O(n)
  *@Space Complexity: O(n)
 */

/*
 * 题目描述：
 * LeetCode 103 - 二叉树的锯齿形层次遍历（Binary Tree Zigzag Level Order Traversal）
 *
 * 给定一个二叉树，按照**从左到右**，然后**从右到左**交替的顺序返回其节点值的层序遍历（即 "之" 字形遍历）。
 *
 * **输入：**
 * - `root`：二叉树的根节点。
 * **输出：**
 * - `List<List<Integer>>`：按 "之" 字形层次遍历的节点值。
 *
 * **示例：**
 * ```
 * 输入：
 *       3
 *     /   \
 *    9    20
 *        /   \
 *       15    7
 *
 * 输出:
 * [
 *   [3],
 *   [20, 9],
 *   [15, 7]
 * ]
 * ```
 */

/*
 * 解题思路：
 * 该问题是二叉树的层次遍历变形，适用于 **广度优先搜索（BFS）**。
 *
 * **方法 1：BFS + 双端队列**
 * ------------------------------------------------------
 * 1️⃣ **层次遍历**
 *    - 使用 **队列（Queue）** 进行 **广度优先搜索（BFS）**，按层遍历树的节点。
 * 2️⃣ **使用 `is_order_left` 标志**
 *    - 控制当前层的遍历方向：
 *      - `is_order_left == true`：从左到右遍历，使用 `addLast()` 添加到结果列表。
 *      - `is_order_left == false`：从右到左遍历，使用 `addFirst()` 插入到结果列表头部。
 * 3️⃣ **使用 `null` 作为层分隔符**
 *    - 以 `null` 作为每层的结束标记，遇到 `null` 时存储当前层，并切换 `is_order_left` 方向。
 *
 * **示例**
 * ```
 * 输入：
 *       3
 *     /   \
 *    9    20
 *        /   \
 *       15    7
 *
 * 解析：
 * - 第一层：[3]            -> 直接加入
 * - 第二层：[20, 9]        -> 逆序加入
 * - 第三层：[15, 7]       -> 正序加入
 *
 * 输出:
 * [[3], [20, 9], [15, 7]]
 * ```
 * **时间复杂度：O(n)**
 * **空间复杂度：O(n)**（存储结果 + 队列）
 *
 * ------------------------------------------------------
 * **时间和空间复杂度分析**
 *
 * **层次遍历（BFS）**
 * - **时间复杂度：O(n)**（遍历所有节点一次）
 * - **空间复杂度：O(n)**（使用队列存储节点）
 *
 * **推荐方法**
 * | 方法 | 时间复杂度 | 空间复杂度 | 适用场景 |
 * |------|----------|----------|--------|
 * | **BFS + 双端队列** | `O(n)` | `O(n)` | **适用于大规模二叉树** |
 */

public class LeetCode_103_BinaryTreeZigzagLevelOrderTraversal {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//            //写法1: 用null分割层
//            // 如果根节点为空，直接返回空列表
//            if (root == null) {
//                return new ArrayList<List<Integer>>();
//            }
//
//            // 用于存储最终的锯齿形层序遍历结果
//            List<List<Integer>> results = new ArrayList<List<Integer>>();
//
//            // 初始化一个双端队列（Deque）用于 BFS 层序遍历
//            LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
//            node_queue.addLast(root); // 先添加根节点
//            node_queue.addLast(null); // 作为层分隔符
//
//            // 用于存储当前层的节点值
//            LinkedList<Integer> level_list = new LinkedList<Integer>();
//            // 标志变量，决定当前层的遍历方向：true 表示从左到右，false 表示从右到左
//            boolean is_order_left = true;
//
//            // 进行 BFS 遍历，当队列不为空时继续执行
//            while (node_queue.size() > 0) {
//                // 取出当前层的第一个节点
//                TreeNode curr_node = node_queue.pollFirst();
//
//                // 如果当前节点不为空，则处理当前节点
//                if (curr_node != null) {
//                    // 根据当前层的顺序决定是从尾部插入还是从头部插入
//                    if (is_order_left)
//                        level_list.addLast(curr_node.val); // 从左到右，正常顺序
//                    else
//                        level_list.addFirst(curr_node.val); // 从右到左，头部插入实现逆序
//
//                    // 如果当前节点的左子节点存在，则加入队列
//                    if (curr_node.left != null)
//                        node_queue.addLast(curr_node.left);
//                    // 如果当前节点的右子节点存在，则加入队列
//                    if (curr_node.right != null)
//                        node_queue.addLast(curr_node.right);
//
//                } else {
//                    // 当前节点为空，表示当前层已经遍历完毕
//                    results.add(level_list); // 将当前层加入结果集
//                    level_list = new LinkedList<Integer>(); // 重新初始化用于存储下一层数据
//
//                    // 如果队列中还有节点，继续添加层分隔符（null）
//                    if (node_queue.size() > 0)
//                        node_queue.addLast(null);
//
//                    // 翻转标志变量，切换遍历方向
//                    is_order_left = !is_order_left;
//                }
//            }
//            // 返回最终的锯齿形层序遍历结果
//            return results;

            //写法2: 用size区别层
            List<List<Integer>> result = new ArrayList<>();
            if(root == null) return result;
            Queue<TreeNode> queue = new LinkedList<>();
            LinkedList<Integer> dq = new LinkedList<>();
            queue.add(root);
            boolean from_left = false;
            while (queue.size() != 0) {
                int currentLevelSize = queue.size();
                for(int i = 0; i < currentLevelSize; i++){
                    TreeNode curNode =  queue.poll();

                    if(curNode.right != null) queue.offer(curNode.right);
                    if(curNode.left != null) queue.offer(curNode.left);
                    if(!from_left){
                        dq.addFirst(curNode.val);
                    }
                    else{
                        dq.addLast(curNode.val);
                    }
                }
                from_left = from_left? false: true;
                result.add(dq);
                dq = new LinkedList<>();

            }



            return result;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_103_BinaryTreeZigzagLevelOrderTraversal().new Solution();
        // 创建测试用例
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        // 运行 zigzagLevelOrder 方法并输出结果
        List<List<Integer>> result = solution.zigzagLevelOrder(root);
        System.out.println(result); // 预期输出: [[3], [20, 9], [15, 7]]
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

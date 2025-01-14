package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *@Question:  993. Cousins in Binary Tree
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 44.48%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/*
 * 题目描述：
 * 给定一个二叉树，判断二叉树中的两个节点是否是表兄弟。表兄弟是指两个节点具有相同的深度，但它们的父节点不同。
 *
 * 解题思路：
 *
 * 解法一：深度优先搜索（DFS）
 * 1. 定义变量 `recordedDepth` 用于记录第一个节点的深度，`isCousin` 用于标记是否为表兄弟。
 * 2. 对树进行深度优先搜索，遍历树中的每个节点，寻找值为 `x` 或 `y` 的节点。
 * 3. 对每个节点，检查：
 *    - 如果该节点是目标节点之一，且没有记录过节点的深度，则记录该节点的深度。
 *    - 如果目标节点已经在同一深度中，则判断是否为同一父节点的孩子（即判断是否为兄弟节点）。
 * 4. 最终判断两个目标节点是否是表兄弟。
 *
 * 例子：
 * 输入：
 *      1
 *     / \
 *    2   3
 *   / \
 *  4   5
 *
 * 目标节点：4 和 5
 * 节点 4 和 5 位于相同的深度，但它们有相同的父节点，因此它们不是表兄弟。
 * 目标节点：4 和 3
 * 节点 4 和 3 位于不同的深度，因此它们也不是表兄弟。
 *
 * 解法二：广度优先搜索（BFS）
 * 1. 使用队列实现广度优先搜索，从根节点开始按层次遍历树。
 * 2. 每次遍历一层时，检查该层的每个节点是否包含目标节点 `x` 或 `y`。
 * 3. 如果发现两个目标节点在同一层且不为兄弟节点，返回 true。
 * 4. 如果遍历结束后没有找到符合条件的节点，则返回 false。
 *
 * 例子：
 * 输入：
 *      1
 *     / \
 *    2   3
 *   / \
 *  4   5
 *
 * 在 BFS 中，首先遍历层 1，接着遍历层 2，最后遍历层 3。如果在某一层发现目标节点且它们不为兄弟节点，则返回 true。
 *
 * 时间复杂度与空间复杂度：
 *
 * 时间复杂度：
 * - DFS 解法：O(N)，其中 N 是树中节点的个数，因为我们需要访问每个节点一次。
 * - BFS 解法：O(N)，同样是因为我们需要遍历每个节点一次。
 *
 * 空间复杂度：
 * - DFS 解法：O(H)，其中 H 是树的高度，空间复杂度由递归栈的深度决定。在最坏的情况下，树为链式结构，H 为 N。
 * - BFS 解法：O(N)，空间复杂度由队列的最大容量决定，最坏情况下队列的大小为树的宽度。
 */


public class LeetCode_993_CousinsInBinaryTree{

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
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {

        // To save the depth of the first node.
        int recordedDepth = -1; // 用于保存第一个节点的深度
        boolean isCousin = false; // 判断是否是表兄弟

        private boolean dfs(TreeNode node, int depth, int x, int y) {

            if (node == null) { // 如果当前节点为空，则返回 false
                return false;
            }

            // Don't go beyond the depth restricted by the first node found.
            if (this.recordedDepth != -1 && depth > this.recordedDepth) { // 如果深度超过记录的深度，返回 false
                return false;
            }

            if (node.val == x || node.val == y) { // 如果找到 x 或 y 节点
                if (this.recordedDepth == -1) { // 如果是第一次找到节点
                    this.recordedDepth = depth; // 记录该节点的深度
                }
                // Return true, if the second node is found at the same depth.
                return this.recordedDepth == depth; // 如果第二个节点也在同一深度，返回 true
            }

            boolean left = dfs(node.left, depth + 1, x, y); // 递归查找左子树
            boolean right = dfs(node.right, depth + 1, x, y); // 递归查找右子树

            // this.recordedDepth != depth + 1 would ensure node x and y are not
            // immediate child nodes, otherwise they would become siblings.
            if (left && right && this.recordedDepth != depth + 1) { // 如果左子树和右子树都找到节点，并且它们不在同一层次上，则说明它们是表兄弟
                this.isCousin = true; // 设定为表兄弟
            }
            return left || right; // 如果左右子树中有一个找到节点，返回 true
        }

        //solution1: dfs
        public boolean isCousins(TreeNode root, int x, int y) {

            // Recurse the tree to find x and y
            dfs(root, 0, x, y); // 从根节点开始递归查找
            return this.isCousin; // 返回是否为表兄弟
        }

        //Solution2
        public boolean isCousins2(TreeNode root, int x, int y) {

            // Queue for BFS
            Queue <TreeNode> queue = new LinkedList <> (); // 使用队列进行广度优先搜索
            queue.add(root); // 将根节点添加到队列

            while (!queue.isEmpty()) {
                boolean siblings = false; // 记录是否为兄弟节点
                boolean cousins = false; // 记录是否为表兄弟节点

                int nodesAtDepth = queue.size(); // 当前层的节点个数

                for (int i = 0; i < nodesAtDepth; i++) { // 遍历当前层的所有节点
                    // FIFO
                    TreeNode node = queue.remove(); // 从队列中取出节点

                    // Encountered the marker.
                    // Siblings should be set to false as we are crossing the boundary.
                    if (node == null) { // 如果遇到 null 标记，说明当前层已经结束
                        siblings = false; // 重置兄弟节点标记
                    } else {
                        if (node.val == x || node.val == y) { // 如果找到节点 x 或 y
                            // Set both the siblings and cousins flag to true
                            // for a potential first sibling/cousin found.
                            if (!cousins) { // 如果是第一次找到表兄弟
                                siblings = cousins = true; // 标记为兄弟和表兄弟
                            } else {
                                // If the siblings flag is still true this means we are still
                                // within the siblings boundary and hence the nodes are not cousins.
                                return !siblings; // 如果还是兄弟节点，则不是表兄弟，返回 false
                            }
                        }

                        if (node.left != null) queue.add(node.left); // 如果左子树不为空，加入队列
                        if (node.right != null) queue.add(node.right); // 如果右子树不为空，加入队列
                        // Adding the null marker for the siblings
                        queue.add(null); // 在队列中加入 null 标记，表示当前层的结束
                    }
                }
                // After the end of a level if `cousins` is set to true
                // This means we found only one node at this level
                if (cousins) return false; // 如果找到了表兄弟，则不是表兄弟，返回 false
            }
            return false; // 如果找不到表兄弟，则返回 false
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_993_CousinsInBinaryTree().new Solution();

        // 创建二叉树：[1,2,3,4,5,null,6,null,null,7]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);

        // 测试节点 5 和 6 是否为表兄弟
        boolean result = solution.isCousins(root, 5, 6);
        System.out.println(result); // 输出：false

        // 测试节点 4 和 6 是否为表兄弟
        result = solution.isCousins(root, 4, 6);
        System.out.println(result); // 输出：true
    }
}

/**
Given the root of a binary tree with unique values and the values of two 
different nodes of the tree x and y, return true if the nodes corresponding to the 
values x and y in the tree are cousins, or false otherwise. 

 Two nodes of a binary tree are cousins if they have the same depth with 
different parents. 

 Note that in a binary tree, the root node is at the depth 0, and children of 
each depth k node are at the depth k + 1. 

 
 Example 1: 
 
 
Input: root = [1,2,3,4], x = 4, y = 3
Output: false
 

 Example 2: 
 
 
Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true
 

 Example 3: 
 
 
Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [2, 100]. 
 1 <= Node.val <= 100 
 Each node has a unique value. 
 x != y 
 x and y are exist in the tree. 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 4158
 👎 213

*/
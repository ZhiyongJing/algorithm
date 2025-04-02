package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
 *@Question:  865. Smallest Subtree with all the Deepest Nodes
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 36.07%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * ===============================================
 * LeetCode 865. Smallest Subtree with all the Deepest Nodes
 * ===============================================
 *
 * 【一、题目描述】
 * 给定一棵二叉树，返回一个节点，它是所有最深节点的最小子树的根节点。
 * 最深的节点是指从根节点出发到达的最长路径上的节点。
 * 所求的最小子树是指：包含所有最深节点的最小的子树（即最小的公共祖先）。
 *
 * 例如：
 * 输入：
 *        3
 *       / \
 *      5   1
 *     / \ / \
 *    6  2 0  8
 *      / \
 *     7   4
 * 输出：节点 2（因为最深的节点是 7 和 4，它们的最近公共祖先是 2）
 *
 *
 * 【二、解题思路（DFS递归，返回结构体，逐步举例说明）】
 * 我们采用自底向上的递归（DFS）方式，遍历整棵树，在每个节点返回两个值：
 * - 最深节点的公共祖先（TreeNode node）
 * - 当前子树的最大深度（int depth）
 *
 * 定义辅助类 Result：
 *   包含两个成员变量：TreeNode node 和 int dist
 *   表示当前节点为根的子树中最深节点的最近公共祖先，以及它的深度
 *
 * 主流程是：
 * 1）从根节点开始向下递归，分别计算左右子树的最深节点深度和对应的最近公共祖先；
 * 2）比较左右子树的深度：
 *    - 如果左子树更深，则说明所有最深节点都在左子树，返回左边的 node，深度+1；
 *    - 如果右子树更深，则说明所有最深节点都在右子树，返回右边的 node，深度+1；
 *    - 如果左右子树一样深，说明当前节点是这些最深节点的共同祖先，返回当前节点，深度+1；
 * 3）最终在根节点返回的结果中的 node 就是答案。
 *
 *
 * 举例说明（以示例树为例）：
 * 输入：
 *        3
 *       / \
 *      5   1
 *     / \ / \
 *    6  2 0  8
 *      / \
 *     7   4
 *
 * 步骤：
 * - 节点 7 和 4 是最深的叶子，深度为 4；
 * - 递归走到节点 2 时，发现其左子树（7）和右子树（4）深度一致，说明 2 是它们的最近公共祖先；
 * - 返回 Result(node=2, depth=4)；
 * - 节点 5 的左右子树中：左边深度为 2（节点6），右边是刚刚返回的 4（节点2），所以选右边；
 * - 节点 3 的左右子树：左边为节点 2，深度为 4，右边最深是节点 0 和 8，深度为 3；
 * - 所以最终返回的 Result 是 node=2，depth=5；
 *
 * 最后返回的节点是 2，符合题意。
 *
 *
 * 【三、时间复杂度分析】
 * - 时间复杂度：O(N)
 *   每个节点只会被访问一次，N 为节点数。
 *
 * - 空间复杂度：O(H)
 *   H 为树的高度。由于递归调用栈的深度最多为 H。
 *   最坏情况下（如完全不平衡的树），空间复杂度为 O(N)。
 */

public class LeetCode_865_SmallestSubtreeWithAllTheDeepestNodes{

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
        // 主函数：返回包含所有最深节点的最小子树的根节点
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            // 调用 dfs 辅助函数获取结果中的 node，即所求子树的根
            return dfs(root).node;
        }

        // 辅助函数：返回以当前节点为根的子树中，最深节点的信息
        public Result dfs(TreeNode node) {
            // 如果当前节点为空，返回空节点和深度0
            if (node == null) return new Result(null, 0);

            // 递归处理左子树
            Result L = dfs(node.left),
                    // 递归处理右子树
                    R = dfs(node.right);

            // 如果左子树更深，则最深节点在左边，返回左子树的结果，深度加1
            if (L.dist > R.dist) return new Result(L.node, L.dist + 1);

            // 如果右子树更深，则最深节点在右边，返回右子树的结果，深度加1
            if (L.dist < R.dist) return new Result(R.node, R.dist + 1);

            // 如果左右子树一样深，则当前节点是最深节点的公共祖先
            return new Result(node, L.dist + 1);
        }
    }

    /**
     * Result类用于封装子树结果：
     * Result.node：当前子树中包含所有最深节点的最小公共祖先
     * Result.dist：当前子树的最大深度
     */
    class Result {
        TreeNode node;
        int dist;

        // 构造函数：初始化节点和深度
        Result(TreeNode n, int d) {
            node = n;
            dist = d;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_865_SmallestSubtreeWithAllTheDeepestNodes().new Solution();

        // 创建测试用例：
        // 构建如下二叉树：
        //        3
        //       / \
        //      5   1
        //     / \ / \
        //    6  2 0  8
        //      / \
        //     7   4
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // 预期输出：节点 2（因为最深的节点是 7 和 4，它们的最近公共祖先是 2）
        TreeNode result = solution.subtreeWithAllDeepest(root);
        System.out.println("包含所有最深节点的最小子树根节点值是: " + (result != null ? result.val : "null"));
    }
}

/**
Given the root of a binary tree, the depth of each node is the shortest 
distance to the root. 

 Return the smallest subtree such that it contains all the deepest nodes in the 
original tree. 

 A node is called the deepest if it has the largest depth possible among any 
node in the entire tree. 

 The subtree of a node is a tree consisting of that node, plus the set of all 
descendants of that node. 

 
 Example 1: 
 
 
Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest nodes of the tree.
Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 
is the smallest subtree among them, so we return it.
 

 Example 2: 

 
Input: root = [1]
Output: [1]
Explanation: The root is the deepest node in the tree.
 

 Example 3: 

 
Input: root = [0,1,3,null,2]
Output: [2]
Explanation: The deepest node in the tree is 2, the valid subtrees are the 
subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.
 

 
 Constraints: 

 
 The number of nodes in the tree will be in the range [1, 500]. 
 0 <= Node.val <= 500 
 The values of the nodes in the tree are unique. 
 

 
 Note: This question is the same as 1123: https://leetcode.com/problems/lowest-
common-ancestor-of-deepest-leaves/ 

 Related Topics Hash Table Tree Depth-First Search Breadth-First Search Binary 
Tree 👍 2711 👎 378

*/
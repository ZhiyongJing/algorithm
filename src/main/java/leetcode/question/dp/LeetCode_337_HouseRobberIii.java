package leetcode.question.dp;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Question: 337. House Robber III
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 33.93%
 * @Time Complexity: O(N)
 * @Space Complexity: O(N)
 */
/**
 * 这是一个关于二叉树的动态规划问题，问题是在一棵二叉树中选择一些节点进行偷窃，但有一个限制条件：
 * 不能同时偷窃相邻的两个节点。题目中给定的是一棵二叉树，树中的节点有一个房屋的价值，
 * 我们需要求解在不触发警报的情况下，可以偷窃到的最大金额。
 *
 * ### 解题思路：
 *
 * #### Solution1: Top-Down (递归)
 *
 * 1. **递归函数：** `helper(TreeNode node, boolean parentRobbed)`，
 * 其中 `node` 表示当前节点，`parentRobbed` 表示父节点是否被偷窃。
 * 2. 如果 `node` 为空，返回 0。
 * 3. 如果父节点被偷窃（`parentRobbed` 为 true），则当前节点不能偷窃，递归计算左右子节点的最大偷窃金额。
 * 4. 如果父节点未被偷窃，当前节点可以选择偷窃或者不偷窃，分别计算两种情况的最大偷窃金额。
 * 5. 使用两个 HashMap 分别存储已经计算过的偷窃和不偷窃的结果，避免重复计算。
 *
 * #### Solution2: Bottom-Up (动态规划)
 *
 * 1. 将二叉树转化为数组表示，使用两个数组 `dpRob` 和 `dpNotRob` 分别表示偷窃和不偷窃的情况。
 * 2. 从底部向上遍历数组，计算每个节点的偷窃和不偷窃的最大金额。
 * 3. 对于叶子节点，直接取节点的值作为偷窃金额，不偷窃金额为 0。
 * 4. 对于非叶子节点，计算偷窃和不偷窃两种情况的最大金额，依次更新数组。
 * 5. 最终返回根节点的偷窃和不偷窃两种情况的最大值。
 *
 * ### 时间复杂度：
 *
 * - Solution1 (Top-Down): O(N)，其中 N 为二叉树节点的数量。递归过程中每个节点只计算一次。
 * - Solution2 (Bottom-Up): O(N)，遍历二叉树节点并计算偷窃和不偷窃两种情况的最大金额。
 *
 * ### 空间复杂度：
 *
 * - Solution1 (Top-Down): O(N)，递归过程中使用了两个 HashMap 存储计算结果，最坏情况下需要存储所有节点的结果。
 * - Solution2 (Bottom-Up): O(N)，使用两个数组存储偷窃和不偷窃的结果，需要存储所有节点的结果。
 */
public class LeetCode_337_HouseRobberIii {

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        //Solution1: Top-Down, 便于理解，更好用
        // 存储已经计算过的节点偷窃金额，偷窃的情况
        HashMap<TreeNode, Integer> robResult = new HashMap<>();
        // 存储已经计算过的节点偷窃金额，不偷窃的情况
        HashMap<TreeNode, Integer> notRobResult = new HashMap<>();

        public int helper(TreeNode node, boolean parentRobbed) {
            if (node == null) {
                return 0;
            }
            if (parentRobbed) {
                if (robResult.containsKey(node)) {
                    return robResult.get(node);
                }
                int result = helper(node.left, false) + helper(node.right, false);
                robResult.put(node, result);
                return result;
            } else {
                if (notRobResult.containsKey(node)) {
                    return notRobResult.get(node);
                }
                int rob = node.val + helper(node.left, true) + helper(node.right, true);
                int notRob = helper(node.left, false) + helper(node.right, false);
                int result = Math.max(rob, notRob);
                notRobResult.put(node, result);
                return result;
            }
        }

        public int rob(TreeNode root) {
            return helper(root, false);
        }


        //Solution2: Bottom-Up
        // 使用数组模拟树的结构，从底部向上计算
        public int rob2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // reform tree into array-based tree
            ArrayList<Integer> tree = new ArrayList<>();
            HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
            graph.put(-1, new ArrayList<>());
            int index = -1;
            // we use two Queue to store node and index
            Queue<TreeNode> q_node = new LinkedList<>();
            q_node.add(root);
            Queue<Integer> q_index = new LinkedList<>();
            q_index.add(index);

            while (q_node.size() > 0) {
                TreeNode node = q_node.poll();
                int parentIndex = q_index.poll();
                if (node != null) {
                    index++;
                    tree.add(node.val);
                    graph.put(index, new ArrayList<>());
                    graph.get(parentIndex).add(index);
                    // push new node into Queue
                    q_node.add(node.left);
                    q_index.add(index);
                    q_node.add(node.right);
                    q_index.add(index);
                }
            }

            // represent the maximum start by node i with robbing i
            int[] dpRob = new int[index + 1];

            // represent the maximum start by node i without robbing i
            int[] dpNotRob = new int[index + 1];

            for (int i = index; i >= 0; i--) {
                ArrayList<Integer> children = graph.get(i);
                if (children == null || children.size() == 0) {
                    // if is leaf
                    dpRob[i] = tree.get(i);
                    dpNotRob[i] = 0;
                } else {
                    dpRob[i] = tree.get(i);
                    for (int child : children) {
                        dpRob[i] += dpNotRob[child];
                        dpNotRob[i] += Math.max(dpRob[child], dpNotRob[child]);
                    }
                }
            }

            return Math.max(dpRob[0], dpNotRob[0]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_337_HouseRobberIii lcr = new LeetCode_337_HouseRobberIii();
        Solution solution = lcr.new Solution();

        // 测试代码
        // 示例1
        //      3
        //     / \
        //    2   3
        //     \   \
        //      3   1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(1);
        System.out.println(solution.rob(root1)); // 预期输出: 7

        // 示例2
        //      3
        //     / \
        //    4   5
        //   / \   \
        //  1   3   1
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(1);
        System.out.println(solution.rob(root2)); // 预期输出: 9
    }
}

/**
 * The thief has found himself a new place for his thievery again. There is only
 * one entrance to this area, called root.
 * <p>
 * Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that all houses in this place form a binary tree. It
 * will automatically contact the police if two directly-linked houses were broken
 * into on the same night.
 * <p>
 * Given the root of the binary tree, return the maximum amount of money the
 * thief can rob without alerting the police.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree is in the range [1, 10⁴].
 * 0 <= Node.val <= 10⁴
 * <p>
 * <p>
 * Related Topics Dynamic Programming Tree Depth-First Search Binary Tree 👍 8313
 * 👎 138
 */
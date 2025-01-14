package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *@Question:  515. Find Largest Value in Each Tree Row
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 48.46%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 第一步：题目描述
 * 给定一棵二叉树，要求找出每一层中的最大值并返回一个列表。
 *
 * 树的层次从根节点开始，第0层是根节点，后续层次依次递增。
 * 每层的最大值需要按层次的顺序排列在列表中。
 *
 * 输入示例：
 * 输入一棵二叉树如下：
 *          1
 *         / \
 *        3   2
 *       / \    \
 *      5   3    9
 * 输出结果：
 * [1, 3, 9]
 */

/**
 * 第二步：解题思路
 * 本题可以使用两种方式解决：深度优先搜索(DFS)和广度优先搜索(BFS)。
 *
 * 方法1：深度优先搜索(DFS)
 * 1. 初始化一个空的结果列表 `ans`，用来存储每层的最大值。
 * 2. 从根节点开始遍历，记录当前深度 `depth`。
 * 3. 如果当前深度等于 `ans` 的大小，表示是第一次遍历到这一层，将当前节点的值加入列表。
 * 4. 如果已经有值，更新当前深度对应的最大值为 `max(当前值, 当前层最大值)`。
 * 5. 递归遍历左子树和右子树，深度递增。
 * 6. 最后返回结果列表。
 *
 * 示例：
 * 输入树：
 *          1
 *         / \
 *        3   2
 *       / \    \
 *      5   3    9
 * 深度遍历过程：
 * 深度0：值为1 -> ans = [1]
 * 深度1：值为3 -> ans = [1, 3]
 * 深度1：值为2 -> ans = [1, max(3, 2) = 3]
 * 深度2：值为5 -> ans = [1, 3, 5]
 * 深度2：值为3 -> ans = [1, 3, max(5, 3) = 5]
 * 深度2：值为9 -> ans = [1, 3, max(5, 9) = 9]
 * 返回结果：[1, 3, 9]
 *
 * 方法2：广度优先搜索(BFS)
 * 1. 初始化一个队列 `queue`，将根节点加入队列。
 * 2. 每次处理队列中的所有节点，即一层的所有节点。
 * 3. 在处理每一层时，记录一个当前层的最大值变量 `currMax`。
 * 4. 遍历当前层的所有节点，更新 `currMax`，并将子节点加入队列。
 * 5. 每层处理完毕后，将 `currMax` 加入结果列表。
 * 6. 当队列为空时，返回结果列表。
 *
 * 示例：
 * 输入树：
 *          1
 *         / \
 *        3   2
 *       / \    \
 *      5   3    9
 * 队列操作过程：
 * 初始队列：[1]
 * 当前层：[1]，最大值1，结果为：[1]
 * 下一层：[3, 2]，最大值3，结果为：[1, 3]
 * 下一层：[5, 3, 9]，最大值9，结果为：[1, 3, 9]
 * 返回结果：[1, 3, 9]
 */

/**
 * 第三步：时间和空间复杂度
 * 方法1：DFS
 * - 时间复杂度：O(N)，每个节点被访问一次。
 * - 空间复杂度：O(H)，H 为树的高度，递归调用栈的深度。
 *
 * 方法2：BFS
 * - 时间复杂度：O(N)，每个节点被访问一次。
 * - 空间复杂度：O(W)，W 为树的最大宽度，即队列中的最大节点数。
 */


public class LeetCode_515_FindLargestValueInEachTreeRow {

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
        List<Integer> ans;

        // 使用深度优先搜索(DFS)的解法
        public List<Integer> largestValues(TreeNode root) {
            // 初始化存储每层最大值的列表
            ans = new ArrayList<Integer>();
            // 调用深度优先搜索函数，初始深度为0
            dfs(root, 0);
            return ans; // 返回结果列表
        }

        // 深度优先搜索函数，node为当前节点，depth为当前深度
        public void dfs(TreeNode node, int depth) {
            // 如果当前节点为空，直接返回
            if (node == null) {
                return;
            }

            // 如果当前深度等于结果列表的大小，说明是第一次遍历到这一层
            if (depth == ans.size()) {
                ans.add(node.val); // 将当前节点值加入结果列表
            } else {
                // 如果已经有值，更新当前深度的最大值
                ans.set(depth, Math.max(ans.get(depth), node.val));
            }
            depth++;
            // 递归遍历左子树，深度加1
            dfs(node.left, depth );
            // 递归遍历右子树，深度加1
            dfs(node.right, depth );
        }

        // 使用广度优先搜索(BFS)的解法
        public List<Integer> largestValues2(TreeNode root) {
            // 如果根节点为空，直接返回空列表
            if (root == null) {
                return new ArrayList<Integer>();
            }

            // 初始化存储每层最大值的列表
            List<Integer> ans = new ArrayList<Integer>();
            // 初始化队列进行层级遍历
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            // 当队列不为空时，处理每一层节点
            while (!queue.isEmpty()) {
                int currentLength = queue.size(); // 当前层的节点数
                int currMax = Integer.MIN_VALUE; // 初始化当前层的最大值

                // 遍历当前层的所有节点
                for (int i = 0; i < currentLength; i++) {
                    TreeNode node = queue.remove(); // 获取队列中的节点
                    currMax = Math.max(currMax, node.val); // 更新最大值

                    // 如果左子节点存在，加入队列
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    // 如果右子节点存在，加入队列
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }

                // 当前层处理结束，将最大值加入结果列表
                ans.add(currMax);
            }

            return ans; // 返回结果列表
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_515_FindLargestValueInEachTreeRow().new Solution();

        // 构造测试用例
        TreeNode root = new TreeNode(1,
                new TreeNode(3, new TreeNode(5), new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(9))
        );

        // 测试DFS解法
        System.out.println("DFS Solution: " + solution.largestValues(root)); // 预期输出：[1, 3, 9]

        // 测试BFS解法
        System.out.println("BFS Solution: " + solution.largestValues2(root)); // 预期输出：[1, 3, 9]
    }
}

/**
Given the root of a binary tree, return an array of the largest value in each 
row of the tree (0-indexed). 

 
 Example 1: 
 
 
Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]
 

 Example 2: 

 
Input: root = [1,2,3]
Output: [1,3]
 

 
 Constraints: 

 
 The number of nodes in the tree will be in the range [0, 10⁴]. 
 -2³¹ <= Node.val <= 2³¹ - 1 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 4004
 👎 127

*/
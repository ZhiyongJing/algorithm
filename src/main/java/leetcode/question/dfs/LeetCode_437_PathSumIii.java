package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.HashMap;

/**
 *@Question:  437. Path Sum III
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 52.63%
 *@Time  Complexity: O(N) where N is the number of nodes in the tree.
 *@Space Complexity: O(H) where H is the height of the tree, due to the recursion stack and HashMap space.
 */
/**
 * 第一步：题目描述
 * 题目要求计算二叉树中路径和等于目标值的路径数量。
 * 1. 路径可以从任意节点开始，到任意节点结束，不一定包含根节点，也不一定是叶子节点。
 * 2. 每条路径只能向下延伸（即只能从父节点到子节点）。
 * 3. 输入是一棵二叉树的根节点和目标路径和，输出是满足条件的路径数量。
 *
 * 第二步：解题思路
 * 我们采用前缀和的方式优化路径和的统计。
 * 1. 前缀和：从根节点到当前节点路径上的节点值之和。
 *    - 如果两个节点的前缀和之差等于目标值 k，则说明从前一个节点到当前节点的路径满足条件。
 * 2. 哈希表记录前缀和出现的次数。
 *    - 键是前缀和，值是前缀和出现的次数。
 *    - 通过查找 "当前前缀和 - 目标和" 是否存在于哈希表中，判断是否存在满足条件的路径。
 * 3. 使用深度优先搜索（DFS）遍历二叉树，同时动态更新哈希表：
 *    - 每次访问一个节点时，更新当前前缀和。
 *    - 计算满足条件的路径数量。
 *    - 递归处理子节点。
 *    - 回溯时将当前节点的前缀和从哈希表中移除，以防止影响平行子树的计算。
 *
 * 举例说明：
 * 示例二叉树：
 *        10
 *       /  \
 *      5   -3
 *     / \     \
 *    3   2     11
 *   / \   \
 *  3  -2   1
 * 目标路径和：8
 * - 满足条件的路径有：
 *   1. 5 → 3
 *   2. 5 → 2 → 1
 *   3. -3 → 11
 * 总路径数量为 3。
 *
 * 第三步：时间和空间复杂度
 * 时间复杂度：
 * - 每个节点被访问一次，因此时间复杂度为 O(N)，其中 N 是节点总数。
 *
 * 空间复杂度：
 * - 递归栈空间最大为树的高度，最坏情况下为 O(H)，其中 H 是树的高度。
 * - 哈希表的空间复杂度取决于不同前缀和的数量，最坏情况下为 O(N)。
 * - 综合空间复杂度为 O(H + N)。
 */


public class LeetCode_437_PathSumIii {

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
        // 全局变量用于计数满足条件的路径数量
        int count = 0;
        // 存储目标路径和
        int k;
        // 用于存储前缀和出现的次数的哈希表
        HashMap<Long, Integer> h = new HashMap<>();

        /**
         * 前序遍历的辅助函数
         * @param node 当前遍历的节点
         * @param currSum 当前路径的前缀和
         */
        public void preorder(TreeNode node, long currSum) {
            if (node == null)
                return; // 如果节点为空，直接返回

            // 计算当前前缀和
            currSum += node.val;

            // 如果当前前缀和等于目标和，计数加一
            if (currSum == k)
                count++;

            // 检查是否存在满足条件的前缀和
            count += h.getOrDefault(currSum - k, 0);

            // 将当前前缀和存入哈希表
            h.put(currSum, h.getOrDefault(currSum, 0) + 1);

            // 递归遍历左子树
            preorder(node.left, currSum);

            // 递归遍历右子树
            preorder(node.right, currSum);

            // 回溯时从哈希表中移除当前前缀和
            h.put(currSum, h.get(currSum) - 1);
        }

        /**
         * 主函数：计算路径总和
         * @param root 树的根节点
         * @param sum 目标路径和
         * @return 满足条件的路径数量
         */
        public int pathSum(TreeNode root, int sum) {
            k = sum; // 初始化目标和
            preorder(root, 0L); // 开始前序遍历
            return count; // 返回结果
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_437_PathSumIii().new Solution();

        // 创建测试用例
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);

        int targetSum = 8;

        // 测试路径和
        int result = solution.pathSum(root, targetSum);
        System.out.println("Number of paths with sum " + targetSum + ": " + result);
    }
}

/**
Given the root of a binary tree and an integer targetSum, return the number of 
paths where the sum of the values along the path equals targetSum. 

 The path does not need to start or end at the root or a leaf, but it must go 
downwards (i.e., traveling only from parent nodes to child nodes). 

 
 Example 1: 
 
 
Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.
 

 Example 2: 

 
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 1000]. 
 -10⁹ <= Node.val <= 10⁹ 
 -1000 <= targetSum <= 1000 
 

 Related Topics Tree Depth-First Search Binary Tree 👍 11270 👎 538

*/
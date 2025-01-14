package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  113. Path Sum II
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.519999999999996%
 *@Time  Complexity: O(N^2)
 *@Space Complexity: O(N)
 */

public class LeetCode_113_PathSumIi {

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
        // 主要方法，接受二叉树的根节点和目标路径和
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            // 初始化返回结果的列表
            List<List<Integer>> result = new ArrayList<>();
            // 如果树的根节点为空，直接返回空列表
            if(root == null){
                return result;
            }
            // 用来存储路径的临时列表
            List<Integer> temp = new ArrayList<>();
            // 调用深度优先搜索（DFS）来查找路径
            dfs(root, 0, targetSum, result, temp);
            return result;
        }

        // 深度优先搜索的递归方法
        private void dfs(TreeNode root, int currentSum, int targetSum, List<List<Integer>> result, List<Integer> temp){
            // 如果当前节点为空，返回
            if(root == null) return ;

            // 累加当前节点的值
            currentSum += root.val;
            // 将当前节点值加入到路径中
            temp.add(root.val);

            // 如果当前节点是叶子节点且当前路径和等于目标和，记录当前路径
            if(root.left == null && root.right == null && currentSum == targetSum){
                result.add(new ArrayList<>(temp));
            }

            // 继续深度优先搜索左子树
            dfs(root.left, currentSum, targetSum, result, temp);
            // 继续深度优先搜索右子树
            dfs(root.right, currentSum, targetSum, result, temp);
            // 回溯，移除路径中的最后一个元素
            temp.remove(temp.size() - 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 创建 Solution 类的实例
        Solution solution = new LeetCode_113_PathSumIi().new Solution();

        // 创建一个测试样例的二叉树
        //        5
        //       / \
        //      4   8
        //     /   / \
        //    11  13  4
        //   /  \      \
        //  7    2      1
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        // 设定目标路径和
        int targetSum = 22;

        // 调用 pathSum 方法并打印结果
        List<List<Integer>> result = solution.pathSum(root, targetSum);
        // 输出结果
        for (List<Integer> path : result) {
            System.out.println(path);
        }
    }
}

/**
Given the root of a binary tree and an integer targetSum, return all root-to-
leaf paths where the sum of the node values in the path equals targetSum. Each 
path should be returned as a list of the node values, not node references. 

 A root-to-leaf path is a path starting from the root and ending at any leaf 
node. A leaf is a node with no children. 

 
 Example 1: 
 
 
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
 

 Example 2: 
 
 
Input: root = [1,2,3], targetSum = 5
Output: []
 

 Example 3: 

 
Input: root = [1,2], targetSum = 0
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 5000]. 
 -1000 <= Node.val <= 1000 
 -1000 <= targetSum <= 1000 
 

 Related Topics Backtracking Tree Depth-First Search Binary Tree 👍 8163 👎 159

*/
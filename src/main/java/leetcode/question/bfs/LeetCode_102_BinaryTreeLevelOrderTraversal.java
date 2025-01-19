package leetcode.question.bfs;

import leetcode.util.TreeNode;

import java.util.*;

/**
 * @Question:  102. Binary Tree Level Order Traversal
 * @Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 58.25%
 * @Time  Complexity: O(N) [N is the number of nodes in the tree]
 * @Space Complexity: O(N) [Additional space for the result list]
 */

/**
 * ### 算法思路：
 *
 * 该算法使用深度优先搜索（DFS）来进行二叉树的层序遍历。主要步骤如下：
 *
 * 1. **深度优先搜索（DFS）：** 使用递归的方式进行深度优先搜索。对于每个节点，将其值添加到相应层的列表中。同时，递归地处理左右子节点。
 *
 * 2. **层次列表（List<List<Integer>>）：** 使用一个外部列表 `levels`，其中每个内部列表表示二叉树的一层。
 * 当开始新的一层时，添加一个新的空列表。
 *
 * 3. **递归终止条件：** 当递归到叶子节点时，即当前节点为null，停止递归。
 *
 * ### 时间复杂度：
 *
 * - 对于每个节点，我们都只访问一次，因此时间复杂度为 **O(N)**，其中 `N` 是二叉树中的节点数。
 *
 * ### 空间复杂度：
 *
 * - 使用了一个二维列表 `levels` 来存储层次遍历的结果，空间复杂度为 **O(N)**，其中 `N` 是二叉树中的节点数。
 * 额外的空间复杂度主要用于存储结果列表。
 *
 * 总体而言，该算法通过深度优先搜索实现了二叉树的层序遍历，具有良好的时间和空间复杂度。
 */

public class LeetCode_102_BinaryTreeLevelOrderTraversal {

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
        List<List<Integer>> levels = new ArrayList<List<Integer>>();

        /**
         * Helper function for recursive traversal
         * @param node  current node in the traversal
         * @param level level of the current node in the tree
         */

//        //DFS way
//        public void helper(TreeNode node, int level) {
//            // Start the current level
//            if (levels.size() == level)
//                levels.add(new ArrayList<Integer>());
//
//            // Fulfill the current level
//            levels.get(level).add(node.val);
//
//            // Process child nodes for the next level
//            if (node.left != null)
//                helper(node.left, level + 1);
//            if (node.right != null)
//                helper(node.right, level + 1);
//        }

        //iteration way
        public void bfs(TreeNode node){
            if(node == null) return;
            Map<TreeNode, Boolean> visited = new HashMap<>();
            Queue<TreeNode> queue = new LinkedList<>();

            int level = 0;
            queue.add(node);
//            while(!queue.isEmpty()){
//
//                levels.add(new ArrayList<>());
//
//                int level_length = queue.size();
//                for(int i = 0; i < level_length; i++){
//                    TreeNode currentNode = queue.remove();
//                    levels.get(level).add(currentNode.val);
//                    if (currentNode.left != null) queue.add(currentNode.left);
//                    if (currentNode.right != null) queue.add(currentNode.right);
//
//                }
//                level++;
//
//            }
            while(queue.size() != 0){
                TreeNode currentNode = queue.poll();


            }

        }

        /**
         * Main function to initiate the level order traversal
         * @param root root of the binary tree
         * @return List of Lists containing the level order traversal
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) return levels;
//            helper(root, 0);
            bfs(root);
            return levels;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_102_BinaryTreeLevelOrderTraversal().new Solution();
        // TO TEST
        // Create a sample binary tree
        // TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        // List<List<Integer>> result = solution.levelOrder(root);
        // System.out.println(result);
    }
}

/**
Given the root of a binary tree, return the level order traversal of its nodes' 
values. (i.e., from left to right, level by level). 

 
 Example 1: 
 
 
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
 

 Example 2: 

 
Input: root = [1]
Output: [[1]]
 

 Example 3: 

 
Input: root = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 2000]. 
 -1000 <= Node.val <= 1000 
 

 Related Topics Tree Breadth-First Search Binary Tree 👍 14661 👎 290

*/

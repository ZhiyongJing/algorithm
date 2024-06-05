package leetcode.question.dfs;
import leetcode.util.TreeNode;

/**
 *@Question:  1457. Pseudo-Palindromic Paths in a Binary Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.38999999999999%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(H)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求找出二叉树中的伪回文路径数量。伪回文路径是指路径上的数字出现的次数中，最多只有一个数字出现的次数为奇数。
 *
 * #### 步骤详解
 *
 * 1. **先序遍历**：
 *    - 我们采用先序遍历（DFS）的方式遍历二叉树。
 *    - 在遍历的过程中，我们维护一个变量 `path`，用于记录当前路径上每个数字出现的次数。
 *
 * 2. **路径判断**：
 *    - 当遍历到叶子节点时，我们检查当前路径是否是伪回文路径。
 *    - 如果当前路径最多只有一个数字出现的次数为奇数，则将结果计数器 `count` 加一。
 *
 * 3. **递归遍历**：
 *    - 在遍历左右子树时，我们将当前节点的值添加到路径中，并递归遍历其左右子节点。
 *
 * #### 时间和空间复杂度分析
 *
 * #### 时间复杂度
 * - 在遍历二叉树的过程中，我们对每个节点都只需常数时间的操作，因此时间复杂度为 `O(N)`，其中 `N` 是二叉树中的节点数量。
 *
 * #### 空间复杂度
 * - 递归调用的深度取决于二叉树的高度 `H`，因此空间复杂度为 `O(H)`。
 */

public class LeetCode_1457_PseudoPalindromicPathsInABinaryTree{

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
        // 定义变量count，用于记录伪回文路径的数量
        int count = 0;

        // 定义先序遍历方法
        public void preorder(TreeNode node, int path) {
            // 如果节点不为空
            if (node != null) {
                // 计算当前路径中每个数字的出现次数
                path = path ^ (1 << node.val);
                // 如果是叶子节点，则检查路径是否是伪回文路径
                if (node.left == null && node.right == null) {
                    // 检查是否最多有一个数字的出现次数为奇数
                    if ((path & (path - 1)) == 0) {
                        ++count;
                    }
                }
                // 递归遍历左子树
                preorder(node.left, path);
                // 递归遍历右子树
                preorder(node.right, path) ;
            }
        }

        // 定义计算伪回文路径数量的方法
        public int pseudoPalindromicPaths (TreeNode root) {
            // 调用先序遍历方法，计算伪回文路径数量
            preorder(root, 0);
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 创建Solution对象
        Solution solution = new LeetCode_1457_PseudoPalindromicPathsInABinaryTree().new Solution();
        // TO TEST
        // 添加测试样例
        // 构造测试用例
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);

        // 测试
        int result = solution.pseudoPalindromicPaths(root);
        System.out.println("Number of pseudo-palindromic paths: " + result);
    }
}

/**
Given a binary tree where node values are digits from 1 to 9. A path in the 
binary tree is said to be pseudo-palindromic if at least one permutation of the 
node values in the path is a palindrome. 

 Return the number of pseudo-palindromic paths going from the root node to leaf 
nodes. 

 
 Example 1: 

 

 
Input: root = [2,3,1,3,1,null,1]
Output: 2 
Explanation: The figure above represents the given binary tree. There are three 
paths going from the root node to leaf nodes: the red path [2,3,3], the green 
path [2,1,1], and the path [2,3,1]. Among these paths only red path and green 
path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3
,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (
palindrome).
 

 Example 2: 

 

 
Input: root = [2,1,1,1,3,null,null,null,null,null,1]
Output: 1 
Explanation: The figure above represents the given binary tree. There are three 
paths going from the root node to leaf nodes: the green path [2,1,1], the path [
2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-
palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 

 Example 3: 

 
Input: root = [9]
Output: 1
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁵]. 
 1 <= Node.val <= 9 
 

 Related Topics Bit Manipulation Tree Depth-First Search Breadth-First Search 
Binary Tree 👍 3239 👎 126

*/
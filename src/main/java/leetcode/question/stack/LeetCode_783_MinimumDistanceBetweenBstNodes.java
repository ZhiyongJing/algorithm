package leetcode.question.stack;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  783. Minimum Distance Between BST Nodes
 *@Difficulty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 32.81%
 *@Time Complexity: O(N) // 需要遍历整个树
 *@Space Complexity: O(N) // 递归栈或存储中序遍历结果的列表
 */
/**
 * 题目描述：
 * LeetCode 783. Minimum Distance Between BST Nodes
 *
 * 给定一个二叉搜索树 (BST) 的根节点 `root`，要求找到 **任意两节点值之间的最小差值**。
 * 由于 BST 的特性，**中序遍历的结果是一个递增序列**，因此 **最小差值只可能出现在相邻的两个节点之间**。
 *
 * **示例 1：**
 * 输入：
 * ```
 *         4
 *        / \
 *       2   6
 *      / \
 *     1   3
 * ```
 * 输出：`1`
 * 解释：
 * - **中序遍历**：`[1, 2, 3, 4, 6]`
 * - **相邻差值**：
 *   - `2 - 1 = 1`
 *   - `3 - 2 = 1`
 *   - `4 - 3 = 1`
 *   - `6 - 4 = 2`
 * - 最小差值为 `1`
 *
 * ---
 *
 * **解题思路：**
 * 由于 **BST 的中序遍历是递增序列**，我们可以：
 *
 * 1. **使用中序遍历获取所有节点的值**（存入 `List`）
 * 2. **遍历该列表，计算相邻元素的差值，并找出最小值**
 *
 * **具体步骤：**
 *
 * 1. **中序遍历存储节点值**
 *    - 使用递归 **左 - 根 - 右** 顺序遍历 BST，记录所有节点值。
 *    - 存入 `List<Integer>`，保证得到的是 **递增序列**。
 *
 * 2. **计算最小相邻差值**
 *    - 遍历 `List<Integer>`，计算 **相邻两个值的差值**，更新 `minDistance`。
 *
 * ---
 * **示例解析**
 *
 * **输入：**
 * ```
 *         4
 *        / \
 *       2   6
 *      / \
 *     1   3
 * ```
 *
 * **步骤：**
 * 1. **中序遍历结果** → `[1, 2, 3, 4, 6]`
 * 2. **计算相邻节点的差值**：
 *    ```
 *    2 - 1 = 1
 *    3 - 2 = 1
 *    4 - 3 = 1
 *    6 - 4 = 2
 *    ```
 * 3. **最小差值 = `1`**
 *
 * ---
 * **时间和空间复杂度分析**
 *
 * - **时间复杂度：O(N)**
 *   - 需要遍历整个树执行中序遍历，计算最小差值，总共 `O(N) + O(N) = O(N)`。
 *
 * - **空间复杂度：O(N)**
 *   - 递归栈的最大深度为 `O(H)`（树的高度），最坏情况下 `O(N)`（链表状 BST）。
 *   - 需要存储 `N` 个节点值的 `List`，最坏情况下 `O(N)`。
 */

public class LeetCode_783_MinimumDistanceBetweenBstNodes{

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


        // 中序遍历函数
        void inorderTraversal(TreeNode root, List<Integer> inorderNodes) {
            if (root == null) { // 如果当前节点为空，则直接返回
                return;
            }

            inorderTraversal(root.left, inorderNodes); // 递归遍历左子树
            inorderNodes.add(root.val); // 访问当前节点，并存入列表
            inorderTraversal(root.right, inorderNodes); // 递归遍历右子树
        }

        public int minDiffInBST(TreeNode root) {
            // 使用列表存储二叉搜索树的中序遍历结果
            List<Integer> inorderNodes = new ArrayList<>();
            inorderTraversal(root, inorderNodes); // 进行中序遍历，获取有序节点值列表

            int minDistance = Integer.MAX_VALUE;
            // 计算相邻节点的最小差值
            for (int i = 1; i < inorderNodes.size(); i++) {
                minDistance = Math.min(minDistance, inorderNodes.get(i) - inorderNodes.get(i-1));
            }

            return minDistance; // 返回最小的差值
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_783_MinimumDistanceBetweenBstNodes().new Solution();

        // 构造测试用例
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // 测试最小距离计算
        System.out.println("BST 中最小的相邻节点差值: " + solution.minDiffInBST(root));
        // 预期输出: 1 (2-1 或 3-2)
    }
}

/**
Given the root of a Binary Search Tree (BST), return the minimum difference 
between the values of any two different nodes in the tree. 

 
 Example 1: 
 
 
Input: root = [4,2,6,1,3]
Output: 1
 

 Example 2: 
 
 
Input: root = [1,0,48,null,null,12,49]
Output: 1
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [2, 100]. 
 0 <= Node.val <= 10⁵ 
 

 
 Note: This question is the same as 530: https://leetcode.com/problems/minimum-
absolute-difference-in-bst/ 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Search Tree 
Binary Tree 👍 3560 👎 428

*/
package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
 *@Question:  108. Convert Sorted Array to Binary Search Tree
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 41.69%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(logN)
 */

/**
 * ### 解题思路
 *
 * #### 1. **问题分析**
 * - 给定一个递增排序的整数数组，需要将其转换为高度平衡的二叉搜索树（BST）。
 * - **高度平衡**的意思是树的每个节点的左右两个子树的高度差的绝对值不超过 1。
 *
 * #### 2. **二叉搜索树的性质**
 * - 对于一个节点，它的左子树中所有节点的值都小于该节点的值，右子树中所有节点的值都大于该节点的值。
 * - 为了构建一个高度平衡的 BST，选择数组的中间元素作为根节点，这样可以保证左子树和右子树的节点数量尽可能相等。
 *
 * #### 3. **构建树的策略**
 * - 使用递归的方法来构建树：
 *   1. **选择根节点**: 在当前数组范围内选择中间元素作为根节点。
 *   2. **构建左子树**: 递归地对左半部分的数组构建左子树。
 *   3. **构建右子树**: 递归地对右半部分的数组构建右子树。
 * - 具体实现中，可以通过调整数组的左右指针来递归地处理子数组。
 *
 * #### 4. **递归终止条件**
 * - 当左指针超过右指针时，表示当前子数组没有元素，返回 `null` 作为终止条件。
 *
 * ### 时间复杂度
 *
 * - **O(N)**: 每个元素在递归过程中都会被访问一次，构建过程需要线性时间。
 *
 * ### 空间复杂度
 *
 * - **O(logN)**: 递归调用栈的深度取决于树的高度。对于一个平衡的二叉搜索树，递归调用栈的最大深度为 `logN`，因此空间复杂度为 `O(logN)`。如果考虑存储结果树的节点，则需要额外的 `O(N)` 空间，但这通常不计入辅助空间复杂度。
 *
 * 这个问题的解法关键在于选择适当的根节点以保持树的平衡性，利用递归的方法自然地处理子数组并构建左右子树。选择中间元素作为根节点的策略能够有效地保证树的平衡。
 */

public class LeetCode_108_ConvertSortedArrayToBinarySearchTree{

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
        int[] nums; // 保存输入的排序数组

        // 辅助函数，用于递归地构建 BST
        public TreeNode helper(int left, int right) {
            if (left > right) return null; // 终止条件，当左指针超过右指针时，返回 null

            // 总是选择中间左边的节点作为根节点
            int p = (left + right) / 2;

            // 前序遍历：节点 -> 左 -> 右
            TreeNode root = new TreeNode(nums[p]); // 创建根节点
            root.left = helper(left, p - 1); // 递归构建左子树
            root.right = helper(p + 1, right); // 递归构建右子树
            return root; // 返回根节点
        }

        // 将排序数组转换为平衡二叉搜索树
        public TreeNode sortedArrayToBST(int[] nums) {
            this.nums = nums; // 初始化 nums 数组
            return helper(0, nums.length - 1); // 从 0 到 nums.length - 1 开始构建 BST
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_108_ConvertSortedArrayToBinarySearchTree().new Solution();
        // 测试样例
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode bstRoot = solution.sortedArrayToBST(nums);
        // 可以在这里添加代码，打印树或验证树的结构
        // 例如：打印中序遍历，验证是否是一个平衡二叉搜索树
    }
}

/**
Given an integer array nums where the elements are sorted in ascending order, 
convert it to a height-balanced binary search tree. 

 
 Example 1: 
 
 
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

 

 Example 2: 
 
 
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -10⁴ <= nums[i] <= 10⁴ 
 nums is sorted in a strictly increasing order. 
 

 Related Topics Array Divide and Conquer Tree Binary Search Tree Binary Tree 👍 
10505 👎 527

*/

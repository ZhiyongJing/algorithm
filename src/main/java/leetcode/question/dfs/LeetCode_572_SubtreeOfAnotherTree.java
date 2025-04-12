package leetcode.question.dfs;

import leetcode.util.TreeNode;

/**
 *@Question:  572. Subtree of Another Tree
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 34.81%
 *@Time  Complexity: O(mn)
 *@Space Complexity: O(m+n)
 */
/**
 * ===============================================
 * LeetCode 572. Subtree of Another Tree
 * ===============================================
 *
 * 【一、题目描述】
 * 给定两个非空二叉树 `root` 和 `subRoot`，判断 `subRoot` 是否是 `root` 的一个子树。
 *
 * 子树的定义如下：
 * - 二叉树 `subRoot` 是 `root` 的一个子树，意味着 `root` 中存在一个节点，
 *   它的子树结构与 `subRoot` 完全相同（结构相同且节点值完全一致）。
 *
 * 示例 1：
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 * 解释：subRoot 是 root 中以 4 为根的子树。
 *
 * 示例 2：
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 * 解释：虽然 root 中有节点值为 4 的子树，但结构不完全一致。
 *
 *
 * 【二、解题思路详解】
 *
 * ✅ 总体思路：使用递归遍历 `root` 树的每个节点，并调用辅助函数判断以当前节点为根的子树是否与 `subRoot` 相同。
 *
 * 步骤如下：
 * 1. 若当前 root 为空，返回 false（空树不可能包含非空子树）；
 * 2. 判断当前节点为根的子树是否与 subRoot 完全相同（使用 isIdentical 函数）；
 * 3. 若相同，返回 true；
 * 4. 否则递归判断左子树和右子树；
 *
 * ✅ isIdentical 函数作用：判断两棵树是否完全相同
 * - 若两个节点都为 null，返回 true；
 * - 若一个为 null，另一个非 null，返回 false；
 * - 若两个节点的值不等，返回 false；
 * - 否则递归比较两棵树的左子树和右子树；
 *
 * ✅ 举例分析：
 * root = [3, 4, 5, 1, 2]
 * subRoot = [4, 1, 2]
 * - 首先判断根节点 3 != 4，进入递归判断左子树 4
 * - 判断 4 == 4，继续比较子节点 1 == 1，2 == 2，结构完全一致，返回 true
 *
 * 示例 2：
 * root = [3,4,5,1,2,null,null,null,null,0]
 * subRoot = [4,1,2]
 * - 看起来结构相似，但 subRoot 中没有 0，而 root 的子树中包含 0，因此不是完全一致，返回 false
 *
 *
 * 【三、时间与空间复杂度分析】
 *
 * 时间复杂度：O(m * n)
 * - m = root 的节点数，n = subRoot 的节点数；
 * - 对于 root 中的每个节点最多都要调用一次 isIdentical（耗时 O(n)）；
 * - 最坏情况下要对每个节点都判断一次，整体复杂度为 O(m * n)；
 *
 * 空间复杂度：O(m + n)
 * - 递归调用栈深度为 O(m)（主函数） + O(n)（isIdentical）；
 * - 没有使用额外数据结构，主要空间耗在函数调用栈；
 */

public class LeetCode_572_SubtreeOfAnotherTree{

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
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            // 如果主树为空，则肯定不包含任何子树，返回 false
            if (root == null) {
                return false;
            }

            // 如果当前节点和 subRoot 所构成的树相同，返回 true
            if (isIdentical(root, subRoot)) {
                return true;
            }

            // 否则递归判断 root 的左子树或右子树是否包含 subRoot
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

        private boolean isIdentical(TreeNode node1, TreeNode node2) {
            // 如果有任意一个节点为空，则必须两个都为空才表示结构一致
            if (node1 == null || node2 == null) {
                return node1 == null && node2 == null;
            }

            // 如果两个节点都非空，则要求：
            // 1. 当前节点值相等
            // 2. 左子树完全一致
            // 3. 右子树完全一致
            return node1.val == node2.val &&
                    isIdentical(node1.left, node2.left) &&
                    isIdentical(node1.right, node2.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_572_SubtreeOfAnotherTree().new Solution();

        // 构造 root 树：[3, 4, 5, 1, 2]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        // 构造 subRoot 树：[4, 1, 2]
        TreeNode subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        // 测试是否是子树，输出结果应为 true
        boolean result = solution.isSubtree(root, subRoot);
        System.out.println("是否是子树？" + result); // 预期输出：true

        // 补充测试：加入不匹配的结构
        TreeNode subRoot2 = new TreeNode(4);
        subRoot2.left = new TreeNode(1);
        subRoot2.right = new TreeNode(0); // 与原本不同

        boolean result2 = solution.isSubtree(root, subRoot2);
        System.out.println("是否是子树？" + result2); // 预期输出：false
    }
}

/**
Given the roots of two binary trees root and subRoot, return true if there is a 
subtree of root with the same structure and node values of subRoot and false 
otherwise. 

 A subtree of a binary tree tree is a tree that consists of a node in tree and 
all of this node's descendants. The tree tree could also be considered as a 
subtree of itself. 

 
 Example 1: 
 
 
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true
 

 Example 2: 
 
 
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false
 

 
 Constraints: 

 
 The number of nodes in the root tree is in the range [1, 2000]. 
 The number of nodes in the subRoot tree is in the range [1, 1000]. 
 -10⁴ <= root.val <= 10⁴ 
 -10⁴ <= subRoot.val <= 10⁴ 
 

 Related Topics Tree Depth-First Search String Matching Binary Tree Hash 
Function 👍 7881 👎 460

*/

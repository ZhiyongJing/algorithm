package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.Stack;

/**
 *@Question:  938. Range Sum of BST
 *@Difficulty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 82.15%
 *@Time Complexity: O(N) // 遍历树的所有节点
 *@Space Complexity: O(N) // 递归栈/显式栈的空间
 */
/**
 * 题目描述：
 * LeetCode 938. Range Sum of BST
 * 给定一棵二叉搜索树 (BST) 的根节点 `root`，以及两个整数 `low` 和 `high`，
 * 返回 **所有值在 [low, high] 范围内的节点值的总和**。
 *
 * **示例 1：**
 * 输入：
 * ```
 *         10
 *        /  \
 *       5    15
 *      / \     \
 *     3   7     18
 * ```
 * `low = 7, high = 15`
 * 输出：`32`
 * 解释：
 * 符合条件的节点有 7、10、15，因此结果为 `7 + 10 + 15 = 32`
 *
 * **示例 2：**
 * 输入：
 * ```
 *         10
 *        /  \
 *       5    15
 *      / \   / \
 *     3   7 13 18
 *    /   /
 *   1   6
 * ```
 * `low = 6, high = 10`
 * 输出：`23`
 * 解释：
 * 符合条件的节点有 6、7、10，因此结果为 `6 + 7 + 10 = 23`
 *
 * ---
 *
 * **解题思路：**
 * 由于二叉搜索树 (BST) 具有以下性质：
 * - **左子树的值 < 根节点的值**
 * - **右子树的值 > 根节点的值**
 *
 * 因此，我们可以 **利用 BST 的特性减少搜索范围**，避免遍历整棵树：
 *
 * ---
 * **方法 1：DFS 递归**
 * 1. **如果当前节点为空**，直接返回 `0`，因为空节点不会贡献任何值。
 * 2. **如果当前节点值在 `[low, high]` 区间内**：
 *    - 说明该节点的值符合条件，加入 `sum`。
 *    - 继续递归左右子树，寻找其他符合条件的值。
 * 3. **如果当前节点值小于 `low`**：
 *    - 说明 **左子树的所有值都更小**，所以 **不需要搜索左子树**，直接递归右子树。
 * 4. **如果当前节点值大于 `high`**：
 *    - 说明 **右子树的所有值都更大**，所以 **不需要搜索右子树**，直接递归左子树。
 *
 * **示例 1 步骤解析（DFS）**
 * ```
 * rangeSumBST(root, 7, 15)
 * 1. 访问节点 10，符合条件，加入 `sum = 10`
 * 2. 10 > 7，需要递归左子树
 *    - 访问节点 5，不符合范围，继续向下
 *    - 访问节点 7，符合条件，加入 `sum = 10 + 7 = 17`
 * 3. 10 < 15，需要递归右子树
 *    - 访问节点 15，符合条件，加入 `sum = 17 + 15 = 32`
 *    - 访问节点 18，超出范围，不搜索
 * 4. 结束，返回 `sum = 32`
 * ```
 *
 * ---
 * **方法 2：栈迭代 (DFS)**
 * 1. 使用 **栈** 模拟递归遍历 BST，避免递归调用的额外开销。
 * 2. 初始化栈，将 `root` 压入。
 * 3. 在 `while` 循环中：
 *    - 取出栈顶节点 `node`，检查是否符合范围 `[low, high]`，若符合则累加。
 *    - 若 `node.val > low`，说明左子树可能有符合条件的值，压入左子节点。
 *    - 若 `node.val < high`，说明右子树可能有符合条件的值，压入右子节点。
 * 4. 继续直到栈为空，返回累加的 `sum`。
 *
 * **示例 2 步骤解析（栈迭代）**
 * ```
 * rangeSumBST(root, 6, 10)
 * 1. 初始化栈 [10]，sum = 0
 * 2. 取出 10，符合条件，sum += 10，压入左子树 [5]
 * 3. 取出 5，不符合条件，压入右子树 [7]
 * 4. 取出 7，符合条件，sum += 7，压入左子树 [6]
 * 5. 取出 6，符合条件，sum += 6
 * 6. 结束，返回 sum = 23
 * ```
 *
 * ---
 *
 * **时间和空间复杂度分析：**
 * - **时间复杂度：O(N)**，最坏情况下（退化成链表），需要遍历所有 `N` 个节点。
 *   - 但由于 BST **可以剪枝**，实际情况下会 **少于 O(N)**。
 * - **空间复杂度：O(H)**，其中 `H` 是树的高度：
 *   - **递归 DFS**：最坏情况下 `O(N)`（退化成链表），最佳 `O(logN)`（平衡树）。
 *   - **栈迭代 DFS**：最坏情况下 `O(N)`，平均 `O(logN)`。
 */


public class LeetCode_938_RangeSumOfBst{

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
        int ans; // 存储最终的区间和

        // solution1: 深度优先搜索 (DFS)
        public int rangeSumBST(TreeNode root, int low, int high) {
            ans = 0; // 初始化和
            dfs(root, low, high); // 递归遍历
            return ans;
        }

        public void dfs(TreeNode node, int low, int high) {
            if (node != null) { // 确保节点不为空
                if (low <= node.val && node.val <= high) // 如果当前节点值在区间内，加入到总和
                    ans += node.val;
                if (low < node.val) // 只有当当前值大于 `low`，才需要递归左子树
                    dfs(node.left, low, high);
                if (node.val < high) // 只有当当前值小于 `high`，才需要递归右子树
                    dfs(node.right, low, high);
            }
        }

        // solution2: 迭代 (栈模拟 DFS)
        public int rangeSumBST2(TreeNode root, int low, int high) {
            int ans = 0; // 存储最终的区间和
            Stack<TreeNode> stack = new Stack(); // 使用栈存储节点进行迭代
            stack.push(root); // 先把根节点放入栈

            while (!stack.isEmpty()) { // 当栈不为空时，持续遍历
                TreeNode node = stack.pop(); // 取出栈顶节点
                if (node != null) { // 确保节点不为空
                    if (low <= node.val && node.val <= high) // 如果当前节点值在区间内，加入到总和
                        ans += node.val;
                    if (low < node.val) // 只有当当前值大于 `low`，才需要将左子节点入栈
                        stack.push(node.left);
                    if (node.val < high) // 只有当当前值小于 `high`，才需要将右子节点入栈
                        stack.push(node.right);
                }
            }
            return ans; // 返回计算的总和
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_938_RangeSumOfBst().new Solution();

        // 构造测试二叉搜索树
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        // 测试范围 [7, 15]
        int low = 7, high = 15;
        System.out.println("DFS 计算的范围和: " + solution.rangeSumBST(root, low, high));
        // 预期输出: 32 (7 + 10 + 15)

        System.out.println("栈迭代计算的范围和: " + solution.rangeSumBST2(root, low, high));
        // 预期输出: 32 (7 + 10 + 15)
    }
}

/**
Given the root node of a binary search tree and two integers low and high, 
return the sum of values of all nodes with a value in the inclusive range [low, high]
. 

 
 Example 1: 
 
 
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 

 Example 2: 
 
 
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 2 * 10⁴]. 
 1 <= Node.val <= 10⁵ 
 1 <= low <= high <= 10⁵ 
 All Node.val are unique. 
 

 Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 7076 ?
? 384

*/
package leetcode.question.two_pointer;

/**
 *@Question:  1650. Lowest Common Ancestor of a Binary Tree III
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency:  96.61%
 *@Time  Complexity: O(h)，其中 h 是二叉树的高度
 *@Space Complexity: O(1)
 */
/**
 * 题目描述：
 *
 * 给定一棵二叉树，其中每个节点都有一个 `parent` 指针指向其父节点（根节点的 `parent` 为 `null`）。
 * 现在给出两个不同的节点 `p` 和 `q`，请你返回它们的最近公共祖先（LCA）。
 *
 * **最近公共祖先 (LCA, Lowest Common Ancestor)**：
 * - 对于两个节点 `p` 和 `q`，它们的 LCA 是 **树中同时具有 `p` 和 `q` 作为子孙的最低节点**。
 *
 * **示例 1**
 * 输入：
 * ```
 *         3
 *        / \
 *       5   1
 *      / \  / \
 *     6  2 0  8
 *       / \
 *      7   4
 * ```
 * - `p = 5`, `q = 1`
 * - 输出：`3`
 * - 解释：`3` 是 `5` 和 `1` 的最近公共祖先。
 *
 * **示例 2**
 * 输入：
 * ```
 *         3
 *        / \
 *       5   1
 *      / \  / \
 *     6  2 0  8
 *       / \
 *      7   4
 * ```
 * - `p = 5`, `q = 4`
 * - 输出：`5`
 * - 解释：`5` 是 `5` 和 `4` 的最近公共祖先。
 *
 * **约束**
 * - `1 <= 节点个数 <= 10^4`
 * - `p` 和 `q` **一定存在**，且 `p != q`。
 */

/**
 * 解题思路：
 *
 * **方法：双指针遍历**
 * 1. **使用两个指针 `a` 和 `b` 分别遍历 `p` 和 `q` 的祖先链**。
 * 2. **如果 `a` 和 `b` 指向的节点不同**：
 *    - `a` 沿着 `p` 的父节点向上移动
 *    - `b` 沿着 `q` 的父节点向上移动
 * 3. **如果 `a` 或 `b` 为空，则让其指向另一个节点的起始位置**：
 *    - 当 `a` 走到 `null`，让 `a` 重新指向 `q`
 *    - 当 `b` 走到 `null`，让 `b` 重新指向 `p`
 * 4. **当 `a == b` 时，说明找到了最近公共祖先**
 * 5. **返回 `a` 即为 `p` 和 `q` 的 LCA**。
 *
 * **示例分析**
 *
 * **示例 1**
 * 输入：
 * ```
 *         3
 *        / \
 *       5   1
 *      / \  / \
 *     6  2 0  8
 *       / \
 *      7   4
 * ```
 * - `p = 5`, `q = 1`
 * - 初始化：
 *   - `a = p = 5`
 *   - `b = q = 1`
 * - 迭代过程：
 *   - `a` 向上移动到 `3`，`b` 向上移动到 `3`（相遇，返回 `3`）。
 *
 * **示例 2**
 * 输入：
 * ```
 *         3
 *        / \
 *       5   1
 *      / \  / \
 *     6  2 0  8
 *       / \
 *      7   4
 * ```
 * - `p = 5`, `q = 4`
 * - 初始化：
 *   - `a = p = 5`
 *   - `b = q = 4`
 * - 迭代过程：
 *   - `a` 向上移动到 `3`
 *   - `b` 向上移动到 `2`
 *   - `b` 向上移动到 `5`（相遇，返回 `5`）。
 */

/**
 * 时间和空间复杂度：
 *
 * - **时间复杂度**：O(h)
 *   - `h` 为树的高度，每个节点最多被访问两次，因此时间复杂度为 `O(h)`。
 *   - 在最坏情况下（链状树），`h = O(n)`，则时间复杂度为 `O(n)`。
 *
 * - **空间复杂度**：O(1)
 *   - 只使用了两个指针 `a` 和 `b`，没有使用额外的数据结构，因此空间复杂度为 `O(1)`。
 */


public class LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii{
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

    class Solution {
        /**
         * 计算两个节点的最近公共祖先（LCA）
         * @param p 第一个节点
         * @param q 第二个节点
         * @return 最近公共祖先节点
         */
        public Node lowestCommonAncestor(Node p, Node q) {
            Node pNode = p, qNode = q;

            // 通过双指针方法，遍历节点的 parent，找到 LCA
            while (pNode != qNode) {
                // 如果 pNode 为空，跳到 q 的起点
                // 否则，向上移动到父节点
                if(pNode == null){
                    pNode = q;
                }else{
                    pNode = pNode.parent;
                }

                // 如果 qNode 为空，跳到 p 的起点
                // 否则，向上移动到父节点
                if(qNode == null){
                    qNode = p;
                }
                else{
                    qNode = qNode.parent;
                }
            }

            // 当 pNode == qNode 时，即找到 LCA
            return pNode;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Solution();

        // 构造测试二叉树
        Node root = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Node();
        root.val = 3;
        Node node5 = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Node();
        node5.val = 5;
        Node node1 = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Node();
        node1.val = 1;
        Node node6 = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Node();
        node6.val = 6;
        Node node2 = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Node();
        node2.val = 2;
        Node node0 = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Node();
        node0.val = 0;
        Node node8 = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Node();
        node8.val = 8;
        Node node7 = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Node();
        node7.val = 7;
        Node node4 = new LeetCode_1650_LowestCommonAncestorOfABinaryTreeIii().new Node();
        node4.val = 4;

        // 构建树的父子关系
        root.left = node5;
        root.right = node1;
        node5.parent = root;
        node1.parent = root;
        node5.left = node6;
        node5.right = node2;
        node6.parent = node5;
        node2.parent = node5;
        node2.left = node7;
        node2.right = node4;
        node7.parent = node2;
        node4.parent = node2;
        node1.left = node0;
        node1.right = node8;
        node0.parent = node1;
        node8.parent = node1;

        // 测试案例 1
        System.out.println("最近公共祖先 (5, 1): " + solution.lowestCommonAncestor(node5, node1).val); // 预期输出 3

        // 测试案例 2
        System.out.println("最近公共祖先 (5, 4): " + solution.lowestCommonAncestor(node5, node4).val); // 预期输出 5

        // 测试案例 3
        System.out.println("最近公共祖先 (7, 4): " + solution.lowestCommonAncestor(node7, node4).val); // 预期输出 2

        // 测试案例 4
        System.out.println("最近公共祖先 (6, 8): " + solution.lowestCommonAncestor(node6, node8).val); // 预期输出 3
    }
}

/**
Given two nodes of a binary tree p and q, return their lowest common ancestor (
LCA). 

 Each node will have a reference to its parent node. The definition for Node is 
below: 

 
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
 

 According to the definition of LCA on Wikipedia: "The lowest common ancestor 
of two nodes p and q in a tree T is the lowest node that has both p and q as 
descendants (where we allow a node to be a descendant of itself)." 

 
 Example 1: 
 
 
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
 

 Example 2: 
 
 
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of 
itself according to the LCA definition.
 

 Example 3: 

 
Input: root = [1,2], p = 1, q = 2
Output: 1
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [2, 10⁵]. 
 -10⁹ <= Node.val <= 10⁹ 
 All Node.val are unique. 
 p != q 
 p and q exist in the tree. 
 

 Related Topics Hash Table Two Pointers Tree Binary Tree 👍 1470 👎 57

*/
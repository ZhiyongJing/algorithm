package leetcode.question.dfs;
/**
 *@Question:  426. Convert Binary Search Tree to Sorted Doubly Linked List
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 77.54%
 *@Time  Complexity: O(N)  // 需要遍历整个 BST 一次
 *@Space Complexity: O(N)  // 递归调用栈的空间消耗（最坏情况下退化为链表）
 */
/**
 * 题目描述：
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 *
 * 给定一棵**二叉搜索树 (BST)**，将其转换为一个**排序的双向循环链表**。
 * - 你不能创建新的节点，而是要**原地修改节点的指针**。
 * - 转换后的双向链表必须保持**中序遍历的顺序**（从小到大）。
 * - 双向链表的**最后一个节点应该连接回头节点**，形成循环结构。
 *
 * 示例：
 * 输入：
 *       4
 *      / \
 *     2   5
 *    / \
 *   1   3
 *
 * 输出（转换后的双向循环链表）：
 * 1 <-> 2 <-> 3 <-> 4 <-> 5
 * ↑                       ↓
 * |_______________________|
 *
 *
 * 解题思路：
 * 这道题的核心是**中序遍历 BST**，然后在遍历过程中构造**双向链表**：
 *
 * 1️⃣ **中序遍历 BST**
 * - 由于 BST 的中序遍历是**升序排列**的，因此我们可以按此顺序**构建有序双向链表**。
 * - 使用**递归 DFS（深度优先搜索）**进行**中序遍历（左 -> 根 -> 右）**。
 *
 * 2️⃣ **构造双向链表**
 * - 维护两个指针：
 *   - `first`：指向双向链表的**头节点（最小值）**。
 *   - `last`：指向双向链表的**尾节点（最大值）**，用于动态更新链表。
 * - 遍历 BST 时，每遇到一个新节点：
 *   - **将其连接到前一个节点（last）**。
 *   - **更新 last 指针** 指向当前节点。
 *
 * 3️⃣ **闭合循环**
 * - 当遍历结束后，我们得到了**头节点（first）和尾节点（last）**。
 * - **将尾部 `last` 的右指针指向 `first`，头部 `first` 的左指针指向 `last`**，形成**循环双向链表**。
 *
 *
 * 示例解析：
 * 输入：
 *       4
 *      / \
 *     2   5
 *    / \
 *   1   3
 *
 * **步骤 1：DFS 遍历**
 * - 递归左子树 `dfs(1)`
 * - 访问 `1`，当前链表：`1`
 * - 递归右子树 `dfs(2)`，访问 `2`，当前链表：`1 <-> 2`
 * - 递归右子树 `dfs(3)`，访问 `3`，当前链表：`1 <-> 2 <-> 3`
 * - 回到 `4`，当前链表：`1 <-> 2 <-> 3 <-> 4`
 * - 递归右子树 `dfs(5)`，访问 `5`，当前链表：`1 <-> 2 <-> 3 <-> 4 <-> 5`
 *
 * **步骤 2：闭合循环**
 * - `last.right = first`（5 连接到 1）
 * - `first.left = last`（1 连接到 5）
 * - 最终形成 `1 <-> 2 <-> 3 <-> 4 <-> 5` 的循环双向链表。
 *
 *
 * 时间和空间复杂度：
 *
 * **时间复杂度：O(N)**
 * - **每个节点** 都访问 **一次**（中序遍历）。
 * - **指针修改** 也是 **O(1) 时间**，所以整体是 **O(N)**。
 *
 * **空间复杂度：O(N)**
 * - **递归栈的深度** 取决于 BST 的高度：
 *   - **最优情况（平衡 BST）：O(log N)**
 *   - **最坏情况（退化为链表）：O(N)**
 * - 由于我们**没有创建额外的数据结构**，只使用了递归栈，因此**空间复杂度为 O(N)**（最坏情况）。
 *
 *
 * 总结：
 * ✅ **使用中序遍历构建排序的双向链表**
 * ✅ **维护 `first` 和 `last` 指针来动态链接节点**
 * ✅ **最终形成循环结构，`last.right = first, first.left = last`**
 * ✅ **时间复杂度 O(N)，空间复杂度 O(N)（递归栈）**
 */


public class LeetCode_426_ConvertBinarySearchTreeToSortedDoublyLinkedList{
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
//leetcode submit region begin(Prohibit modification and deletion)
/*
// 定义二叉树节点的结构
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

    class Solution {
        // 用于存储最小（双向链表的头节点）和最大（双向链表的尾节点）的指针
        Node first = null; // 头节点（最小值）
        Node last = null;  // 尾节点（最大值）

        /**
         * 中序遍历 BST，并转换为双向链表
         * @param node 当前遍历的节点
         */
        public void helper(Node node) {
            if (node != null) { // 递归终止条件：空节点
                // 递归处理左子树
                helper(node.left);

                // 处理当前节点
                if (last != null) {
                    // 连接前一个节点 (last) 和当前节点 (node)
                    last.right = node; // 右指针指向当前节点
                    node.left = last;  // 当前节点的左指针指向前一个节点
                } else {
                    // 如果 last 为空，说明当前节点是最左侧节点，即最小值
                    first = node; // 记录最小值的节点，作为双向链表的头
                }
                last = node; // 更新 last 指向当前节点，作为最新的尾部节点

                // 递归处理右子树
                helper(node.right);
            }
        }

        /**
         * 将二叉搜索树 (BST) 转换为排序的双向链表
         * @param root BST 的根节点
         * @return 返回双向循环链表的头节点
         */
        public Node treeToDoublyList(Node root) {
            if (root == null) return null; // 边界情况：空树返回 null

            helper(root); // 进行中序遍历，并转换为双向链表

            // 闭合双向链表，使其成为循环链表
            last.right = first; // 尾节点的右指针指向头节点
            first.left = last; // 头节点的左指针指向尾节点

            return first; // 返回双向链表的头节点
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_426_ConvertBinarySearchTreeToSortedDoublyLinkedList().new Solution();

        // 构建 BST
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(3);

        // 将 BST 转换为排序的双向循环链表
        Node head = solution.treeToDoublyList(root);

        // 打印双向循环链表
        System.out.println("Doubly Linked List (forward traversal):");
        Node curr = head;
        for (int i = 0; i < 5; i++) { // 只遍历 5 次，防止无限循环
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
    }
}

/**
Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place. 

 You can think of the left and right pointers as synonymous to the predecessor 
and successor pointers in a doubly-linked list. For a circular doubly linked 
list, the predecessor of the first element is the last element, and the successor 
of the last element is the first element. 

 We want to do the transformation in place. After the transformation, the left 
pointer of the tree node should point to its predecessor, and the right pointer 
should point to its successor. You should return the pointer to the smallest 
element of the linked list. 

 
 Example 1: 

 

 
Input: root = [4,2,5,1,3]


Output: [1,2,3,4,5]

Explanation: The figure below shows the transformed BST. The solid line 
indicates the successor relationship, while the dashed line means the predecessor 
relationship.

 

 Example 2: 

 
Input: root = [2,1,3]
Output: [1,2,3]
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 2000]. 
 -1000 <= Node.val <= 1000 
 All the values of the tree are unique. 
 

 Related Topics Linked List Stack Tree Depth-First Search Binary Search Tree 
Binary Tree Doubly-Linked List 👍 2678 👎 237

*/
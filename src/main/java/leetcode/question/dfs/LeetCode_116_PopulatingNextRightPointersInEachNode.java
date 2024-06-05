package leetcode.question.dfs;
/**
 *@Question:  116. Populating Next Right Pointers in Each Node
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 48.5%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N) for solution1, O(1) for solution 2
 */

/**
 * ### 解题思路
 *
 * 这道题是要求在每个节点中，将其右侧的节点连接起来，形成一个链表。解决这个问题的方法有多种，这里我们介绍一种常用的方法，即利用 BFS（广度优先搜索）。
 *
 * #### 步骤详解
 *
 * 1. **BFS遍历**：
 *    - 我们从根节点开始，使用 BFS 遍历二叉树。
 *    - 在遍历过程中，我们维护一个变量 `leftmost`，它表示每一层的最左侧节点。
 *
 * 2. **连接节点**：
 *    - 对于每一层，我们从 `leftmost` 开始遍历该层的所有节点。
 *    - 对于每个节点，我们通过其 `left` 和 `right` 指针连接其右侧的节点。
 *
 * 3. **进入下一层**：
 *    - 遍历完当前层的所有节点后，我们将 `leftmost` 指向下一层的最左侧节点，然后继续上述操作。
 *
 * #### 时间和空间复杂度分析
 *
 * #### 时间复杂度
 * - BFS 遍历二叉树的时间复杂度是 `O(N)`，其中 `N` 是二叉树中节点的数量。
 *
 * #### 空间复杂度
 * - BFS 遍历二叉树的空间复杂度是 `O(N)`，因为我们需要使用队列来存储每一层的节点。
 */
public class LeetCode_116_PopulatingNextRightPointersInEachNode{

//leetcode submit region begin(Prohibit modification and deletion)

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


    class Solution {
        //solution1: BFS
        // 定义连接节点的方法，使用BFS遍历树
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }

            // 从根节点开始。在第一级别上不需要设置next指针
            Node leftmost = root;

            // 当到达最后一级时，我们完成
            while (leftmost.left != null) {
                // 从头结点开始迭代“链接列表”，并使用next指针为下一级别建立相应的链接
                Node head = leftmost;

                while (head != null) {
                    // 连接 1
                    head.left.next = head.right;

                    // 连接 2
                    if (head.next != null) {
                        head.right.next = head.next.left;
                    }

                    // 沿着列表前进（当前级别上的节点）
                    head = head.next;
                }

                // 进入下一级
                leftmost = leftmost.left;
            }

            return root;
        }

        //Solution2:
        // 定义连接节点的方法，使用BFS遍历树
        public Node connect2(Node root) {
            if (root == null) {
                return root;
            }

            // 从根节点开始。在第一级别上不需要设置next指针
            Node leftmost = root;

            // 当到达最后一级时，我们完成
            while (leftmost.left != null) {
                // 从头结点开始迭代“链接列表”，并使用next指针为下一级别建立相应的链接
                Node head = leftmost;

                while (head != null) {
                    // 连接 1
                    head.left.next = head.right;

                    // 连接 2
                    if (head.next != null) {
                        head.right.next = head.next.left;
                    }

                    // 沿着列表前进（当前级别上的节点）
                    head = head.next;
                }

                // 进入下一级
                leftmost = leftmost.left;
            }

            return root;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_116_PopulatingNextRightPointersInEachNode().new Solution();
        // TO TEST
        //solution.
    }
}

/**
You are given a perfect binary tree where all leaves are on the same level, and 
every parent has two children. The binary tree has the following definition: 

 
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
 

 Populate each next pointer to point to its next right node. If there is no 
next right node, the next pointer should be set to NULL. 

 Initially, all next pointers are set to NULL. 

 
 Example 1: 
 
 
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function 
should populate each next pointer to point to its next right node, just like in 
Figure B. The serialized output is in level order as connected by the next pointers, 
with '#' signifying the end of each level.
 

 Example 2: 

 
Input: root = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 2¹² - 1]. 
 -1000 <= Node.val <= 1000 
 

 
 Follow-up: 

 
 You may only use constant extra space. 
 The recursive approach is fine. You may assume implicit stack space does not 
count as extra space for this problem. 
 

 Related Topics Linked List Tree Depth-First Search Breadth-First Search Binary 
Tree 👍 9624 👎 301

*/
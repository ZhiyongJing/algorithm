package leetcode.question.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *@Question:  117. Populating Next Right Pointers in Each Node II
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 43.89%
 *@Time Complexity: O(N) - 每个节点只访问一次
 *@Space Complexity: O(N) - 最坏情况下需要存储每层的所有节点
 */

public class LeetCode_117_PopulatingNextRightPointersInEachNodeIi{
    // 定义二叉树的节点结构
    class Node {
        public int val; // 节点值
        public Node left; // 左子节点
        public Node right; // 右子节点
        public Node next; // 指向同层下一个节点的指针

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

//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public Node connect(Node root) {
            if (root == null) { // 如果根节点为空，则直接返回
                return root;
            }

            // 初始化一个队列，用于进行层序遍历
            Queue<Node> Q = new LinkedList<Node>();
            Q.add(root); // 先将根节点加入队列

            // 进行 BFS 遍历
            while (Q.size() > 0) {
                int size = Q.size(); // 记录当前层的节点数

                // 遍历当前层的所有节点
                for (int i = 0; i < size; i++) {
                    Node node = Q.poll(); // 从队列中取出当前节点

                    // 如果当前节点不是本层的最后一个节点，则将它的 next 指针指向队列中的下一个节点
                    if (i < size - 1) {
                        node.next = Q.peek();
                    }

                    // 将当前节点的左右子节点（如果存在）加入队列，进行下一层遍历
                    if (node.left != null) {
                        Q.add(node.left);
                    }
                    if (node.right != null) {
                        Q.add(node.right);
                    }
                }
            }

            // 返回已经修改的根节点
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_117_PopulatingNextRightPointersInEachNodeIi().new Solution();

        // 构造测试用例
        Node root = new LeetCode_117_PopulatingNextRightPointersInEachNodeIi().new Node(1);
        root.left = new LeetCode_117_PopulatingNextRightPointersInEachNodeIi().new Node(2);
        root.right = new LeetCode_117_PopulatingNextRightPointersInEachNodeIi().new Node(3);
        root.left.left = new LeetCode_117_PopulatingNextRightPointersInEachNodeIi().new Node(4);
        root.left.right = new LeetCode_117_PopulatingNextRightPointersInEachNodeIi().new Node(5);
        root.right.right = new LeetCode_117_PopulatingNextRightPointersInEachNodeIi().new Node(7);

        // 运行连接方法
        Node result = solution.connect(root);

        // 打印测试结果
        System.out.println("层次遍历 next 指针连接情况：");
        printTreeNextPointers(result);
    }

    // 辅助方法：层序遍历并打印 next 指针的连接情况
    public static void printTreeNextPointers(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                System.out.print(node.val + " -> " + (node.next != null ? node.next.val : "null") + "    ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }
}

/**
Given a binary tree 

 
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
 
 
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should 
populate each next pointer to point to its next right node, just like in Figure B. 
The serialized output is in level order as connected by the next pointers, with '#
' signifying the end of each level.
 

 Example 2: 

 
Input: root = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 6000]. 
 -100 <= Node.val <= 100 
 

 
 Follow-up: 

 
 You may only use constant extra space. 
 The recursive approach is fine. You may assume implicit stack space does not 
count as extra space for this problem. 
 

 Related Topics Linked List Tree Depth-First Search Breadth-First Search Binary 
Tree 👍 5956 👎 331

*/
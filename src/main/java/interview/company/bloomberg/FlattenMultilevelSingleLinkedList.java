package interview.company.bloomberg;

/**
 *@Question:   Flatten a Multilevel Single Linked List
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ==============================
 * 题目描述： Flatten a Multilevel Single Linked List
 * ==============================
 * 给定一个**多级单向链表**，链表中的每个节点可能有一个子链表。
 * 子链表也是一个单向链表，结构与主链表相同。
 * 要求将这个多级双向链表**扁平化**，使得所有节点按照深度优先遍历的顺序连接成一个单层单向链表。
 *
 * **输入链表结构：**
 * - 每个节点包含四个属性：
 *   - `val`：节点值
 *   - `next`：指向后一个节点的指针
 *   - `child`：指向子链表的指针
 *
 * **扁平化规则：**
 * - 子链表的节点应插入到主链表的当前位置和下一个节点之间。
 * - 扁平化后，所有 `child` 指针应置为空。
 */


public class FlattenMultilevelSingleLinkedList {


// Definition for a Node.
static class Node {
    public int val;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val, Node _next, Node _child) {
        val = _val;
        next = _next;
        child = _child;
    }
};



        class Solution {
            public Node flatten(Node head) {
                // 如果头结点为空，直接返回
                if (head == null) return head;
                // 创建一个伪头节点，确保 `prev` 指针从不为空
                Node dummy = new Node(0, head, null);

                // 递归地扁平化链表
                flattenDFS(dummy, head);

                // 将伪头节点与实际的头节点分离
                return dummy.next;
            }

            /* 返回扁平化链表的尾部节点 */
            public Node flattenDFS(Node prev, Node curr) {
                // 如果当前节点为空，返回前一个节点
                if (curr == null) return prev;

                // 设置前一个节点的后继指针
                prev.next = curr;

                // 保存当前节点的下一个节点，因为在递归中会改变 curr.next
                Node tempNext = curr.next;

                // 递归处理子链表，并返回子链表的尾部节点
                Node tail = flattenDFS(curr, curr.child);
                // 将当前节点的子链表指针置空
                curr.child = null;

                // 继续递归处理当前节点的下一个节点
                return flattenDFS(tail, tempNext);
            }
        }
//leetcode submit region end(Prohibit modification and deletion)


        public static void main(String[] args) {
            Solution solution = new FlattenMultilevelSingleLinkedList().new Solution();
            // 创建测试样例
            Node node1 = new Node(1, null, null);
            Node node2 = new Node(2,  null, null);
            Node node3 = new Node(3, null, null);
            Node node4 = new Node(4,  null, null);
            Node node5 = new Node(5,  null, null);
            Node node6 = new Node(6,  null, null);
            Node node7 = new Node(7,  null, null);
            Node node8 = new Node(8, null, null);
            Node node9 = new Node(9, null, null);
            Node node10 = new Node(10, null, null);
            Node node11 = new Node(11, null, null);
            Node node12 = new Node(12, null, null);

            // 构建多级双向链表
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            node4.next = node5;
            node5.next = node6;
            node3.child = node7;
            node7.next = node8;
            node8.next = node9;
            node9.next = node10;
            node8.child = node11;
            node11.next = node12;

            // 扁平化链表
            Node flattenedList = solution.flatten(node1);

            // 打印扁平化后的链表
            Node curr = flattenedList;
            while (curr != null) {
                System.out.print(curr.val + " ");
                curr = curr.next;
            }
            // 输出应该是 1 2 3 7 8 11 12 9 10 4 5 6
        }
    }

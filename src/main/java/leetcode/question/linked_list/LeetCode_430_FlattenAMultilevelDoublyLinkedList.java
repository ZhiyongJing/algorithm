package leetcode.question.linked_list;
/**
 *@Question:  430. Flatten a Multilevel Doubly Linked List
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 50.75999999999999%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路详细讲解：
 *
 * 这道题目要求我们将一个多级双向链表展平成一个单层的双向链表。具体的解题思路如下：
 *
 * 1. **使用递归辅助函数`flattenDFS`**：
 *    - 该辅助函数的作用是扁平化当前节点及其所有子节点，并返回扁平化链表的尾节点。
 *    - 函数参数包括前一个节点 `prev` 和当前节点 `curr`。
 *
 * 2. **处理当前节点**：
 *    - 如果当前节点 `curr` 为空，则直接返回前一个节点 `prev`。
 *    - 否则，将当前节点的 `prev` 指针指向 `prev`，并将 `prev` 的 `next` 指针指向当前节点 `curr`。
 *
 * 3. **保存当前节点的下一个节点**：
 *    - 因为在处理当前节点的子链表时，可能会改变 `curr.next`，所以需要提前保存 `curr.next` 到 `tempNext` 中。
 *
 * 4. **递归处理子链表**：
 *    - 如果当前节点有子链表，则递归调用 `flattenDFS`，将当前节点的子链表展平，返回子链表的尾节点 `tail`。
 *    - 将当前节点的 `child` 指针置空。
 *
 * 5. **递归处理下一个节点**：
 *    - 继续递归调用 `flattenDFS` 处理 `tempNext`，即原 `curr.next` 节点。
 *
 * 6. **返回结果**：
 *    - 最终返回扁平化后的链表头节点。
 *
 * ### 时间复杂度分析：
 *
 * - **时间复杂度**：O(N)
 *   - 每个节点被访问一次，因此总的时间复杂度为 O(N)，其中 N 是链表中所有节点的总数。
 *
 * ### 空间复杂度分析：
 *
 * - **空间复杂度**：O(N)
 *   - 由于递归调用栈的深度在最坏情况下可能达到 O(N)，所以空间复杂度为 O(N)。
 */

public class LeetCode_430_FlattenAMultilevelDoublyLinkedList{

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val, Node _prev, Node _next, Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/


    class Solution {
        public Node flatten(Node head) {
            // 如果头结点为空，直接返回
            if (head == null) return head;
            // 创建一个伪头节点，确保 `prev` 指针从不为空
            Node pseudoHead = new Node(0, null, head, null);

            // 递归地扁平化链表
            flattenDFS(pseudoHead, head);

            // 将伪头节点与实际的头节点分离
            pseudoHead.next.prev = null;
            return pseudoHead.next;
        }

        /* 返回扁平化链表的尾部节点 */
        public Node flattenDFS(Node prev, Node curr) {
            // 如果当前节点为空，返回前一个节点
            if (curr == null) return prev;
            // 设置当前节点的前驱指针
            curr.prev = prev;
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
        Solution solution = new LeetCode_430_FlattenAMultilevelDoublyLinkedList().new Solution();
        // 创建测试样例
        Node node1 = new Node(1, null, null, null);
        Node node2 = new Node(2, node1, null, null);
        Node node3 = new Node(3, node2, null, null);
        Node node4 = new Node(4, node3, null, null);
        Node node5 = new Node(5, node4, null, null);
        Node node6 = new Node(6, node5, null, null);
        Node node7 = new Node(7, null, null, null);
        Node node8 = new Node(8, node7, null, null);
        Node node9 = new Node(9, node8, null, null);
        Node node10 = new Node(10, node9, null, null);
        Node node11 = new Node(11, node8, null, null);
        Node node12 = new Node(12, node11, null, null);

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

/**
You are given a doubly linked list, which contains nodes that have a next 
pointer, a previous pointer, and an additional child pointer. This child pointer may 
or may not point to a separate doubly linked list, also containing these special 
nodes. These child lists may have one or more children of their own, and so on, 
to produce a multilevel data structure as shown in the example below. 

 Given the head of the first level of the list, flatten the list so that all 
the nodes appear in a single-level, doubly linked list. Let curr be a node with a 
child list. The nodes in the child list should appear after curr and before curr.
next in the flattened list. 

 Return the head of the flattened list. The nodes in the list must have all of 
their child pointers set to null. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:

 

 Example 2: 
 
 
Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:

 

 Example 3: 

 
Input: head = []
Output: []
Explanation: There could be empty list in the input.
 

 
 Constraints: 

 
 The number of Nodes will not exceed 1000. 
 1 <= Node.val <= 10⁵ 
 

 
 How the multilevel linked list is represented in test cases: 

 We use the multilevel linked list from Example 1 above: 

 
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL 

 The serialization of each level is as follows: 

 
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
 

 To serialize all levels together, we will add nulls in each level to signify 
no node connects to the upper node of the previous level. The serialization 
becomes: 

 
[1,    2,    3, 4, 5, 6, null]
             |
[null, null, 7,    8, 9, 10, null]
                   |
[            null, 11, 12, null]
 

 Merging the serialization of each level and removing trailing nulls we obtain: 


 
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 

 Related Topics Linked List Depth-First Search Doubly-Linked List 👍 4980 👎 323


*/
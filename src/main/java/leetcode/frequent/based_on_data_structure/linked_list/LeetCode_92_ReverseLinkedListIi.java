package leetcode.frequent.based_on_data_structure.linked_list;
import leetcode.util.ListNode;

/**
  *@Question:  92. Reverse Linked List II
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 59.20000000000001%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

/**
 * **解题思路：**
 *
 * 这道题目要求反转链表的一部分，具体是从第 m 个节点到第 n 个节点的部分。题解使用迭代法来解决这个问题。
 *
 * 1. **移动到反转起始点：**
 *    - 使用两个指针 `cur` 和 `prev`，初始化为头节点。移动这两个指针，直到 `cur` 指向反转的起始点
 *    （第 m 个节点）。
 *
 * 2. **反转节点：**
 *    - 使用三个指针 `prev`、`cur` 和 `third`。
 *    - `prev` 用于保存反转链表的前一个节点，`cur` 用于当前节点，`third` 用于保存下一个节点。
 *    - 不断地将 `cur` 指向的节点的 `next` 指针指向 `prev`，实现链表反转。
 *
 * 3. **调整连接关系：**
 *    - 在反转过程中，维护两个指针 `con` 和 `tail`，分别指向反转链表的前一个节点和尾节点。
 *    - 最终，将 `con` 的 `next` 指向反转后的链表头，将 `tail` 的 `next` 指向反转结束后的下一个节点。
 *
 * **时间复杂度：**
 *
 * - 时间复杂度为 O(N)，其中 N 为链表的长度。在遍历链表的过程中，对每个节点进行反转操作。
 *
 * **空间复杂度：**
 *
 * - 空间复杂度为 O(1)，只使用了常数级别的额外空间，不需要额外的数据结构。
 */
public class LeetCode_92_ReverseLinkedListIi {

    // leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {

            // 空链表
            if (head == null) {
                return null;
            }

            // 移动两个指针，直到它们到达列表中的正确起始点m。
            ListNode cur = head, prev = null;
            while (m > 1) {
                prev = cur;
                cur = cur.next;
                m--;
                n--;
            }

            // 用于最终连接的两个指针。
            ListNode con = prev, tail = cur;

            // 反转节点，直到 n 变为 0。
            ListNode third = null;
            while (n > 0) {
                third = cur.next;
                cur.next = prev;
                prev = cur;
                cur = third;
                n--;
            }

            // 根据算法中的说明调整最终连接。
            if (con != null) {
                con.next = prev;
            } else {
                head = prev;
            }

            tail.next = cur;
            return head;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_92_ReverseLinkedListIi().new Solution();

        // 测试用例 1
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int m1 = 2, n1 = 4;
        ListNode result1 = solution.reverseBetween(head1, m1, n1);
        printList(result1);

        // 测试用例 2
        ListNode head2 = new ListNode(5);
        int m2 = 1, n2 = 1;
        ListNode result2 = solution.reverseBetween(head2, m2, n2);
        printList(result2);
    }

    // 打印链表的方法
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}

/**
Given the head of a singly linked list and two integers left and right where 
left <= right, reverse the nodes of the list from position left to position right, 
and return the reversed list. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
 

 Example 2: 

 
Input: head = [5], left = 1, right = 1
Output: [5]
 

 
 Constraints: 

 
 The number of nodes in the list is n. 
 1 <= n <= 500 
 -500 <= Node.val <= 500 
 1 <= left <= right <= n 
 

 
Follow up: Could you do it in one pass?

 Related Topics Linked List 👍 11124 👎 542

*/
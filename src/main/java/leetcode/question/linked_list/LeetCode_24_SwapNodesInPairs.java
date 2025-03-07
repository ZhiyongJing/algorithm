package leetcode.question.linked_list;

import leetcode.util.ListNode;

/**
 *@Question:  24. Swap Nodes in Pairs
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.49999999999999%
 *@Time  Complexity: O(N) for both solutions
 *@Space Complexity: O(N) for solution1, O(1) for solution2
 */

public class LeetCode_24_SwapNodesInPairs{

//leetcode submit region begin(Prohibit modification and deletion)

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
        /**
         * 递归解法：交换相邻节点
         * @param head 链表头节点
         * @return 交换后的新链表头节点
         */
        public ListNode swapPairs1(ListNode head) {
            // 如果链表为空，或者链表中只有一个节点，直接返回
            if ((head == null) || (head.next == null)) {
                return head;
            }

            // 需要交换的两个节点
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // 递归调用，交换后续链表的节点
            firstNode.next = swapPairs1(secondNode.next);
            secondNode.next = firstNode;

            // 交换后的新头节点
            return secondNode;
        }

        /**
         * 迭代解法：交换相邻节点
         * @param head 链表头节点
         * @return 交换后的新链表头节点
         */
        public ListNode swapPairs(ListNode head) {
            // 创建一个虚拟头节点，方便操作
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            // 记录当前的前置节点
            ListNode prevNode = dummy;

            // 遍历链表，确保至少有两个节点可以交换
            while ((head != null) && (head.next != null)) {
                // 需要交换的两个节点
                ListNode firstNode = head;
                ListNode secondNode = head.next;

                // 交换操作
                prevNode.next = secondNode;
                firstNode.next = secondNode.next;
                secondNode.next = firstNode;

                // 更新 prevNode 和 head 以进行下一次交换
                prevNode = firstNode;
                head = firstNode.next; // 继续遍历
            }

            // 返回新的头节点
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_24_SwapNodesInPairs().new Solution();

        // 创建测试链表：1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));

        // 运行递归解法
        ListNode swappedHead1 = solution.swapPairs1(head);
        System.out.print("递归解法结果：");
        printList(swappedHead1);  // 预期输出：2 -> 1 -> 4 -> 3

        // 重新创建测试链表：1 -> 2 -> 3 -> 4
        head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));

        // 运行迭代解法
        ListNode swappedHead2 = solution.swapPairs(head);
        System.out.print("迭代解法结果：");
        printList(swappedHead2);  // 预期输出：2 -> 1 -> 4 -> 3
    }

    /**
     * 打印链表工具方法
     * @param head 链表头节点
     */
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
}

/**
Given a linked list, swap every two adjacent nodes and return its head. You 
must solve the problem without modifying the values in the list's nodes (i.e., only 
nodes themselves may be changed.) 

 
 Example 1: 

 
 Input: head = [1,2,3,4] 
 

 Output: [2,1,4,3] 

 Explanation: 

 

 Example 2: 

 
 Input: head = [] 
 

 Output: [] 

 Example 3: 

 
 Input: head = [1] 
 

 Output: [1] 

 Example 4: 

 
 Input: head = [1,2,3] 
 

 Output: [2,1,3] 

 
 Constraints: 

 
 The number of nodes in the list is in the range [0, 100]. 
 0 <= Node.val <= 100 
 

 Related Topics Linked List Recursion 👍 12338 👎 471

*/
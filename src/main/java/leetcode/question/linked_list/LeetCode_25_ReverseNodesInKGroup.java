package leetcode.question.linked_list;

import leetcode.util.ListNode;
/**
 *@Question:  25. Reverse Nodes in k-Group
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 68.58%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 这个问题的目标是将链表按照每 `k` 个节点一组进行反转。为了实现这个目标，我们可以分为两个主要步骤：
 *
 * 1. **反转链表中的k个节点**：
 *    - 我们需要创建一个新的链表头 `new_head`，它将指向反转后的链表。
 *    - 使用一个指针 `ptr` 遍历链表，将每个节点插入到 `new_head` 的前面，直到处理完 `k` 个节点为止。
 *
 * 2. **每k个节点反转一次链表**：
 *    - 遍历链表，定位到下一段 `k` 个节点的起始位置。
 *    - 使用反转函数对这些 `k` 个节点进行反转。
 *    - 连接反转后的链表段和前一段链表段。
 *    - 更新链表的头节点（`head`）和尾节点（`ktail`），继续处理下一段链表。
 *    - 如果链表的长度不是 `k` 的倍数，则最后剩余的部分保持原样。
 *
 * ### 详细步骤
 *
 * 1. **反转k个节点**：
 *    - 从链表的当前头节点开始，将前 `k` 个节点依次插入到 `new_head` 的前面，这样就可以得到反转后的部分链表。
 *    - 反转过程会更新节点的 `next` 指针，使得每个节点的 `next` 指向前一个节点。
 *
 * 2. **处理每k个节点**：
 *    - 遍历链表，查找下一个 `k` 个节点的范围。
 *    - 使用反转函数对这段 `k` 个节点进行反转，并将反转后的链表连接到前一段链表的尾部（`ktail`）。
 *    - 更新链表的头节点和尾节点指针，准备处理下一段链表。
 *
 * 3. **连接剩余部分**：
 *    - 如果链表的总长度不是 `k` 的倍数，那么处理完所有可能的 `k` 个节点后，链表中最后剩余的部分（少于 `k` 个节点）应直接连接到最终链表的末尾。
 *
 * ### 时间复杂度
 *
 * - **O(N)**：每个节点在链表中被访问一次，所有的节点都被处理了一遍，所以时间复杂度是线性的。
 *
 * ### 空间复杂度
 *
 * - **O(1)**：我们只使用了常数级别的额外空间来保存指针和变量。主要的操作是在链表上进行的，没有使用额外的数据结构。
 */
public class LeetCode_25_ReverseNodesInKGroup{

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
         * 反转链表中前k个节点
         *
         * @param head 链表头节点
         * @param k 要反转的节点数
         * @return 反转后的链表头节点
         */
        public ListNode reverseLinkedList(ListNode head, int k) {
            ListNode new_head = null; // 反转后的新链表头节点
            ListNode ptr = head; // 当前处理的节点

            while (k > 0) {
                // 记录下一个要处理的节点
                ListNode next_node = ptr.next;

                // 将当前节点插入到反转链表的开头
                ptr.next = new_head;
                new_head = ptr;

                // 处理下一个节点
                ptr = next_node;

                // 反转节点数减少1
                k--;
            }

            // 返回反转后的链表头节点
            return new_head;
        }

        /**
         * 每k个节点反转一次链表
         *
         * @param head 链表头节点
         * @param k 每k个节点反转一次
         * @return 反转后的链表头节点
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode ptr = head; // 当前处理的节点
            ListNode ktail = null; // 前一段反转链表的尾节点

            // 记录最终反转后的链表头节点
            ListNode new_head = null;

            // 遍历链表
            while (ptr != null) {
                int count = 0;

                // 从当前节点开始计数
                ptr = head;

                // 找到下一个k个节点的头部
                while (count < k && ptr != null) {
                    ptr = ptr.next;
                    count += 1;
                }

                // 如果找到了k个节点，进行反转
                if (count == k) {
                    // 反转k个节点并获取新的头节点
                    ListNode revHead = this.reverseLinkedList(head, k);

                    // new_head 是最终链表的头节点
                    if (new_head == null) new_head = revHead;

                    // ktail 是前一段反转链表的尾节点
                    if (ktail != null) ktail.next = revHead;

                    // 更新ktail为当前段链表的尾节点
                    ktail = head;

                    // 更新head为下一段链表的头节点
                    head = ptr;
                }
            }

            // 连接最后剩余的部分
            if (ktail != null) ktail.next = head;

            // 如果 new_head 为空，返回原链表头节点，否则返回反转后的链表头节点
            return new_head == null ? head : new_head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_25_ReverseNodesInKGroup().new Solution();

        // 创建测试链表 1->2->3->4->5，k=3
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // 反转链表中每k个节点
        ListNode result = solution.reverseKGroup(node1, 3);

        // 打印结果链表
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 输出应为: 3 2 1 4 5
    }
}

/**
Given the head of a linked list, reverse the nodes of the list k at a time, and 
return the modified list. 

 k is a positive integer and is less than or equal to the length of the linked 
list. If the number of nodes is not a multiple of k then left-out nodes, in the 
end, should remain as it is. 

 You may not alter the values in the list's nodes, only nodes themselves may be 
changed. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
 

 Example 2: 
 
 
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
 

 
 Constraints: 

 
 The number of nodes in the list is n. 
 1 <= k <= n <= 5000 
 0 <= Node.val <= 1000 
 

 
 Follow-up: Can you solve the problem in O(1) extra memory space? 

 Related Topics Linked List Recursion 👍 13748 👎 705

*/
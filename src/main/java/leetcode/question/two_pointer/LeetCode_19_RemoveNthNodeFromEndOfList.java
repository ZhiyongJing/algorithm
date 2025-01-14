package leetcode.question.two_pointer;

import leetcode.util.ListNode;

/**
 *@Question:  19. Remove Nth Node From End of List
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 64.47%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
// Step 1: 题目描述
// 题目要求从一个给定的链表中删除倒数第N个节点，并返回修改后的链表。
// 你需要设计一个算法，以O(N)的时间复杂度完成该操作，并且空间复杂度应为O(1)。

// Step 2: 解题思路

// 思路总结：
// 1. **使用虚拟头节点**：我们引入一个虚拟头节点（dummy node）来简化链表的操作，特别是处理删除头节点的情况。
// 2. **计算链表长度**：遍历链表一次，计算出链表的总长度。
// 3. **找到目标节点**：通过计算出倒数第N个节点的位置，我们可以通过一次遍历定位到倒数第N个节点的前一个节点。
// 4. **删除目标节点**：找到目标节点的前一个节点后，直接修改其 `next` 指针跳过目标节点。

// 具体步骤：
// 1. 首先，我们初始化一个虚拟头节点 `dummy`，它的 `next` 指针指向链表的头节点。这一步是为了简化处理链表头部的情况。
// 2. 计算链表的长度：遍历整个链表，统计节点数。
// 3. 计算倒数第N个节点的前一个节点的位置：根据链表长度，得出倒数第N个节点的位置，
//    然后重新遍历链表到该位置，找到倒数第N个节点的前一个节点。
// 4. 修改前一个节点的 `next` 指针，使其跳过倒数第N个节点，完成删除操作。
// 5. 最后返回链表头节点 `dummy.next`，以便处理可能删除的是头节点的情况。

// 示例：
// 例子1：
// 输入链表: 1 -> 2 -> 3 -> 4 -> 5, 删除倒数第2个节点，
// 计算链表长度为5，倒数第2个节点的位置是 `5 - 2 = 3`，
// 删除节点3，修改链表为：1 -> 2 -> 4 -> 5。

// 例子2：
// 输入链表: 1 -> 2 -> 3, 删除倒数第1个节点，
// 删除链表尾节点，修改链表为：1 -> 2。

// Step 3: 时间和空间复杂度

// 时间复杂度：O(N)，其中N是链表的长度。我们需要遍历链表两次，
// 一次用来计算链表的长度，另一次用来找到倒数第N个节点的前一个节点。
// 因此，总的时间复杂度为O(N)。

// 空间复杂度：O(1)，我们只使用了常数空间来存储指针和长度信息，没有使用额外的存储空间。
// 因此，空间复杂度为O(1)。

public class LeetCode_19_RemoveNthNodeFromEndOfList {

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
        // 删除链表中的倒数第n个节点
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // 创建一个虚拟头节点，用于简化操作
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            // 计算链表的长度
            int length = 0;
            ListNode first = head;
            // 遍历整个链表，计算节点个数
            while (first != null) {
                length++;
                first = first.next;
            }

            // 确定要删除节点的位置：从头节点起第 (length - n) 个节点
            length -= n;
            first = dummy;  // 从虚拟头节点开始

            // 通过 first 指针遍历到倒数第n个节点的前一个节点
            while (length > 0) {
                length--;
                first = first.next;
            }

            // 删除倒数第n个节点
            first.next = first.next.next;

            // 返回删除节点后的链表
            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    // 测试函数
    public static void main(String[] args) {
        Solution solution = new LeetCode_19_RemoveNthNodeFromEndOfList().new Solution();
        // 创建测试用例
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // 调用 removeNthFromEnd 方法，删除倒数第 2 个节点
        ListNode result = solution.removeNthFromEnd(head, 2);

        // 打印修改后的链表
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 期望输出: 1 2 3 5
    }
}

/**
Given the head of a linked list, remove the nᵗʰ node from the end of the list 
and return its head. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
 

 Example 2: 

 
Input: head = [1], n = 1
Output: []
 

 Example 3: 

 
Input: head = [1,2], n = 1
Output: [1]
 

 
 Constraints: 

 
 The number of nodes in the list is sz. 
 1 <= sz <= 30 
 0 <= Node.val <= 100 
 1 <= n <= sz 
 

 
 Follow up: Could you do this in one pass? 

 Related Topics Linked List Two Pointers 👍 19383 👎 829

*/
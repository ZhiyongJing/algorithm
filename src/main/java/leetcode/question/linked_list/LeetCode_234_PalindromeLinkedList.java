package leetcode.question.linked_list;

import leetcode.util.ListNode;
/**
 *@Question:  234. Palindrome Linked List
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 68.57%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 1. **寻找链表的中点**：
 *    - 使用快慢指针来找到链表的中点。快指针每次移动两步，慢指针每次移动一步。
 *    当快指针到达链表末尾时，慢指针恰好到达链表的中点。这样我们就能把链表分成前半部分和后半部分。
 *
 * 2. **反转链表的后半部分**：
 *    - 从中点开始，将链表的后半部分反转。这是因为我们需要从末尾开始检查链表是否为回文，所以反转后半部分会方便比较。
 *
 * 3. **比较前后半部分**：
 *    - 反转后半部分后，我们将链表的前半部分和反转后的后半部分进行比较。如果每个节点的值都相等，则链表是回文的。
 *
 * 4. **恢复链表**：
 *    - 为了不改变输入链表的结构，我们需要将反转的后半部分再次反转回去，这样可以保持链表的原始顺序。
 *
 * ### 时间复杂度
 *
 * - **O(N)**，其中 N 是链表的长度。主要分为三步：
 *   1. **寻找中点**需要 O(N/2) 时间。
 *   2. **反转链表的后半部分**需要 O(N/2) 时间。
 *   3. **比较前后半部分**需要 O(N/2) 时间。
 *
 *    这些步骤的总时间复杂度为 O(N)。
 *
 * ### 空间复杂度
 *
 * - **O(1)**。除了若干指针变量外，算法没有使用额外的空间。因此空间复杂度为 O(1)。
 */

public class LeetCode_234_PalindromeLinkedList{

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

        public boolean isPalindrome(ListNode head) {

            // 如果链表为空，直接返回true，因为空链表也是回文的
            if (head == null) return true;

            // 找到链表的中间节点，并将后半部分链表反转
            ListNode firstHalfEnd = endOfFirstHalf(head);
            ListNode secondHalfStart = reverseList(firstHalfEnd.next);

            // 比较前半部分和反转后的后半部分是否相同
            ListNode p1 = head;
            ListNode p2 = secondHalfStart;
            boolean result = true;
            while (result && p2 != null) {
                if (p1.val != p2.val) result = false;
                p1 = p1.next;
                p2 = p2.next;
            }

            // 恢复原始链表结构并返回结果
            firstHalfEnd.next = reverseList(secondHalfStart);
            return result;
        }

        // 反转链表的辅助函数
        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }

        // 找到链表前半部分的末尾
        private ListNode endOfFirstHalf(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_234_PalindromeLinkedList().new Solution();
        // 测试样例
        // 构造一个回文链表: 1 -> 2 -> 2 -> 1
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // 测试是否为回文链表
        boolean isPalindrome = solution.isPalindrome(node1);
        System.out.println("链表是回文的: " + isPalindrome);  // 输出: 链表是回文的: true
    }
}

/**
Given the head of a singly linked list, return true if it is a palindrome or 
false otherwise. 

 
 Example 1: 
 
 
Input: head = [1,2,2,1]
Output: true
 

 Example 2: 
 
 
Input: head = [1,2]
Output: false
 

 
 Constraints: 

 
 The number of nodes in the list is in the range [1, 10⁵]. 
 0 <= Node.val <= 9 
 

 
Follow up: Could you do it in 
O(n) time and 
O(1) space?

 Related Topics Linked List Two Pointers Stack Recursion 👍 16525 👎 884

*/
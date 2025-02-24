package leetcode.question.linked_list;

import leetcode.util.ListNode;

/**
 *@Question:  328. Odd Even Linked List
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.29%
 *@Time  Complexity: O(N), where N is the number of nodes in the linked list.
 *@Space Complexity: O(1), since we are not using extra space apart from pointers.
 */
/**
 * 题目描述：
 * 328. Odd Even Linked List
 *
 * 给定一个单链表，要求**重新排列节点的顺序**，使得：
 * - **所有奇数索引的节点排在前面**
 * - **所有偶数索引的节点排在后面**
 * - **保持原来的相对顺序**
 *
 * **注意**：
 * - 索引是从 `1` 开始计数的。
 * - **必须在 O(1) 额外空间** 和 **O(N) 时间复杂度** 内完成。
 *
 * **示例 1**
 * ```plaintext
 * 输入: 1 -> 2 -> 3 -> 4 -> 5
 * 输出: 1 -> 3 -> 5 -> 2 -> 4
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: 2 -> 1 -> 3 -> 5 -> 6 -> 4 -> 7
 * 输出: 2 -> 3 -> 6 -> 7 -> 1 -> 5 -> 4
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * 采用 **双指针遍历 + 重新连接链表**
 *
 * **1. 定义指针**
 * - `odd`  指向 **奇数索引节点**
 * - `even` 指向 **偶数索引节点**
 * - `evenHead` 记录偶数索引链表的 **头部**，用于后续连接
 *
 * **2. 遍历链表**
 * - **移动 `odd` 指针**：
 *   - `odd.next = even.next` 让 `odd` 连接下一个奇数索引的节点
 *   - `odd = odd.next` 移动 `odd` 指针
 * - **移动 `even` 指针**：
 *   - `even.next = odd.next` 让 `even` 连接下一个偶数索引的节点
 *   - `even = even.next` 移动 `even` 指针
 * - **当 `even == null` 或 `even.next == null` 结束循环**
 *
 * **3. 连接 `odd` 和 `evenHead`**
 * - `odd.next = evenHead` 让奇数索引链表的最后一个节点连接到偶数索引链表
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `1 -> 2 -> 3 -> 4 -> 5`
 *
 * **初始状态**
 * ```plaintext
 * odd = 1, even = 2, evenHead = 2
 * ```
 *
 * **第 1 轮：odd 连接 3，even 连接 4**
 * ```plaintext
 * 1 -> 3 -> 4 -> 5
 *       2 -> 4
 * ```
 *
 * **第 2 轮：odd 连接 5，even 变为 null**
 * ```plaintext
 * 1 -> 3 -> 5
 *       2 -> 4
 * ```
 *
 * **最终连接**
 * ```plaintext
 * 1 -> 3 -> 5 -> 2 -> 4
 * ```
 *
 * ---
 *
 * **3. 时间复杂度分析**
 * - 只遍历链表 **一次**，所以时间复杂度为 **O(N)**
 *
 * **4. 空间复杂度分析**
 * - 只使用了几个指针，**额外空间 O(1)**
 * - **总空间复杂度 O(1)**
 */


public class LeetCode_328_OddEvenLinkedList{

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
    public class Solution {
        public ListNode oddEvenList(ListNode head) {
            // 如果链表为空，直接返回 null
            if (head == null) return null;

            // odd 指向奇数位置的头结点，even 指向偶数位置的头结点
            ListNode odd = head, even = head.next, evenHead = even;

            // 遍历链表，重新排列奇数位和偶数位的节点
            while (even != null && even.next != null) {
                odd.next = even.next; // 连接奇数节点到下一个奇数位置
                odd = odd.next; // 移动 odd 指针到下一个奇数位置

                even.next = odd.next; // 连接偶数节点到下一个偶数位置
                even = even.next; // 移动 even 指针到下一个偶数位置
            }

            // 将奇数链表的最后一个节点连接到偶数链表的头部
            odd.next = evenHead;

            // 返回重排后的链表头部
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_328_OddEvenLinkedList().new Solution();

        // 测试样例 1
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode result1 = solution.oddEvenList(head1);
        printList(result1); // 预期输出: 1 -> 3 -> 5 -> 2 -> 4

        // 测试样例 2
        ListNode head2 = new ListNode(2, new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(7)))))));
        ListNode result2 = solution.oddEvenList(head2);
        printList(result2); // 预期输出: 2 -> 3 -> 6 -> 7 -> 1 -> 5 -> 4

        // 测试样例 3（空链表）
        ListNode head3 = null;
        ListNode result3 = solution.oddEvenList(head3);
        printList(result3); // 预期输出: 空链表

        // 测试样例 4（单节点链表）
        ListNode head4 = new ListNode(1);
        ListNode result4 = solution.oddEvenList(head4);
        printList(result4); // 预期输出: 1
    }

    // 辅助方法：打印链表
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
}

/**
Given the head of a singly linked list, group all the nodes with odd indices 
together followed by the nodes with even indices, and return the reordered list. 

 The first node is considered odd, and the second node is even, and so on. 

 Note that the relative order inside both the even and odd groups should remain 
as it was in the input. 

 You must solve the problem in O(1) extra space complexity and O(n) time 
complexity. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
 

 Example 2: 
 
 
Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]
 

 
 Constraints: 

 
 The number of nodes in the linked list is in the range [0, 10⁴]. 
 -10⁶ <= Node.val <= 10⁶ 
 

 Related Topics Linked List 👍 10523 👎 552

*/
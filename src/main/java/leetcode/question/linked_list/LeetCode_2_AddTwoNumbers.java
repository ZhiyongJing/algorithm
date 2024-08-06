package leetcode.question.linked_list;

import leetcode.util.ListNode;
/**
 *@Question:  2. Add Two Numbers
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 90.72%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 题目
 *
 * **题目：**
 * [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)
 *
 * 给定两个非空链表，表示两个非负整数。这两个整数的每一位都以逆序存储（即个位在前）。将这两个整数相加并以链表形式返回结果。
 *
 * ### 解题思路
 *
 * 1. **链表表示整数：**
 *    - 每个链表节点表示整数的一位，链表的头节点表示最低位（个位），下一节点表示次低位，以此类推。链表是逆序存储的。
 *
 * 2. **模拟手动加法：**
 *    - 从链表的头节点开始，模拟手动加法的过程，每次处理两个链表当前位的和，并考虑进位。
 *
 * 3. **处理进位：**
 *    - 计算当前位的和，并确定新的进位。每一位的和可以通过 `carry`（进位）来更新。
 *    - `carry` 可以是 0 或 1，因为两个数字的和最多是 18（9 + 9）会有进位1。
 *
 * 4. **创建新链表：**
 *    - 使用一个新的链表来存储结果。遍历两个输入链表，逐位相加并将结果节点添加到新链表中。
 *
 * 5. **边界条件：**
 *    - 处理链表长度不相等的情况：当一个链表结束时，继续处理另一个链表的剩余部分以及可能的进位。
 *    - 处理链表都为空但仍有进位的情况：最终可能需要添加一个新的节点以处理最后的进位。
 *
 * ### 时间复杂度与空间复杂度
 *
 * - **时间复杂度：** O(N)
 *   其中，N 是两个链表中节点数的较大值。我们需要遍历两个链表一次，因此时间复杂度是线性的。
 *
 * - **空间复杂度：** O(N)
 *   其中，N 是结果链表的长度。我们创建了一个新的链表来存储结果，因此空间复杂度是线性的。结果链表的长度最多为输入链表长度加1（在有进位的情况下）。
 */
public class LeetCode_2_AddTwoNumbers{

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
        // Add Two Numbers (Java improved)
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // 创建一个虚拟头节点，用于简化处理
            ListNode dummyHead = new ListNode(0);
            // curr用于指向当前操作的节点
            ListNode curr = dummyHead;
            // carry用于保存进位
            int carry = 0;
            // 只要l1、l2有一个不为空，或存在进位，就继续循环
            while (l1 != null || l2 != null || carry != 0) {
                // 从l1中取值，如果l1为空，则取0
                int x = (l1 != null) ? l1.val : 0;
                // 从l2中取值，如果l2为空，则取0
                int y = (l2 != null) ? l2.val : 0;
                // 计算当前位的总和及新的进位
                int sum = carry + x + y;
                // 更新进位
                carry = sum / 10;
                // 创建新的节点保存当前位的结果
                curr.next = new ListNode(sum % 10);
                // 移动到下一个节点
                curr = curr.next;
                // 移动l1和l2到下一个节点（如果不为空）
                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
            }
            // 返回虚拟头节点的下一个节点，即结果链表的头节点
            return dummyHead.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_2_AddTwoNumbers().new Solution();

        // 创建链表1: 2 -> 4 -> 3（代表数字 342）
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        // 创建链表2: 5 -> 6 -> 4（代表数字 465）
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        // 调用addTwoNumbers方法计算链表和
        ListNode result = solution.addTwoNumbers(l1, l2);

        // 输出结果链表: 7 -> 0 -> 8（代表数字 807）
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 输出: 7 0 8
    }
}

/**
You are given two non-empty linked lists representing two non-negative integers.
 The digits are stored in reverse order, and each of their nodes contains a 
single digit. Add the two numbers and return the sum as a linked list. 

 You may assume the two numbers do not contain any leading zero, except the 
number 0 itself. 

 
 Example 1: 
 
 
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
 

 Example 2: 

 
Input: l1 = [0], l2 = [0]
Output: [0]
 

 Example 3: 

 
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 

 
 Constraints: 

 
 The number of nodes in each linked list is in the range [1, 100]. 
 0 <= Node.val <= 9 
 It is guaranteed that the list represents a number that does not have leading 
zeros. 
 

 Related Topics Linked List Math Recursion 👍 31329 👎 6258

*/
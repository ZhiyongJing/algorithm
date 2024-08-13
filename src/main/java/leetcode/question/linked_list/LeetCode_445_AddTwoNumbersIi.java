package leetcode.question.linked_list;

import leetcode.util.ListNode;

import java.util.Stack;
/**
 *@Question:  445. Add Two Numbers II
 *@Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 54.40%
 *@Time Complexity: O(N + M) for both solutions, where N and M are the lengths of the two linked lists.
 *@Space Complexity: O(N + M) for both solutions.
 */

/**
 * ### 题目：445. Add Two Numbers II
 *
 * #### 题目描述
 * 给定两个非空链表，表示两个非负整数。数字以正序方式存储，每个节点包含一个数字。这两个数相加并返回一个新的链表，表示它们的和。
 *
 * #### 解题思路
 *
 * 1. **反转链表法**：
 *    - **步骤**：
 *      1. 将两个输入链表分别反转，使得个位数字在链表的头部。
 *      2. 从反转后的链表头部开始逐位相加，处理进位，并记录在一个新的链表中。
 *      3. 最后，将结果链表再反转一次，得到最终结果。
 *    - **优点**：实现较为直接，且便于处理进位问题。
 *    - **缺点**：需要额外的时间和空间进行两次反转操作。
 *
 * 2. **使用栈法**：
 *    - **步骤**：
 *      1. 使用两个栈分别存储两个链表的值，将数字逐位压入栈中。
 *      2. 从栈顶开始逐位弹出元素相加，处理进位。
 *      3. 将结果链表反向插入，得到最终结果。
 *    - **优点**：不需要反转链表，直接通过栈实现从低位到高位的操作。
 *    - **缺点**：需要额外的空间来存储两个栈。
 *
 * ### 时间和空间复杂度分析
 *
 * 1. **时间复杂度**：
 *    - **反转链表法**：O(N + M)，其中 N 和 M 分别是两个链表的长度。反转链表和逐位相加的操作都需要遍历链表一次，因此时间复杂度为 O(N + M)。
 *    - **使用栈法**：O(N + M)，遍历链表压栈和逐位相加的操作都需要遍历链表一次。
 *
 * 2. **空间复杂度**：
 *    - **反转链表法**：O(1)，除了输出的链表外，只需要常数级别的额外空间。
 *    - **使用栈法**：O(N + M)，需要使用两个栈来存储链表的值，因此空间复杂度为 O(N + M)。
 */

public class LeetCode_445_AddTwoNumbersIi{

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
        // 辅助方法：反转链表
        public ListNode reverseList(ListNode head) {
            ListNode prev = null, temp; // 前一个节点和临时变量
            while (head != null) { // 遍历链表
                temp = head.next; // 保存下一个节点
                head.next = prev; // 反转当前节点的指针
                prev = head; // 移动前一个节点指针
                head = temp; // 移动当前节点指针
            }
            return prev; // 返回反转后的链表头
        }

        // 解法1：反转链表后相加
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode r1 = reverseList(l1); // 反转第一个链表
            ListNode r2 = reverseList(l2); // 反转第二个链表

            int totalSum = 0, carry = 0; // 总和与进位
            ListNode ans = new ListNode(); // 用于存储结果的链表
            while (r1 != null || r2 != null) { // 遍历两个链表
                if (r1 != null) { // 处理第一个链表
                    totalSum += r1.val;
                    r1 = r1.next;
                }
                if (r2 != null) { // 处理第二个链表
                    totalSum += r2.val;
                    r2 = r2.next;
                }

                ans.val = totalSum % 10; // 当前位的值
                carry = totalSum / 10; // 更新进位
                ListNode head = new ListNode(carry); // 新的头节点
                head.next = ans; // 将新节点连接到结果链表前面
                ans = head; // 更新结果链表头
                totalSum = carry; // 重置总和为进位值
            }

            return carry == 0 ? ans.next : ans; // 如果没有进位，返回ans.next，否则返回ans
        }

        // 解法2：使用栈不反转链表
        public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
            Stack<Integer> s1 = new Stack<Integer>(); // 用于存储第一个链表的值
            Stack<Integer> s2 = new Stack<Integer>(); // 用于存储第二个链表的值

            // 将第一个链表的值压入栈中
            while(l1 != null) {
                s1.push(l1.val);
                l1 = l1.next;
            }
            // 将第二个链表的值压入栈中
            while(l2 != null) {
                s2.push(l2.val);
                l2 = l2.next;
            }

            int totalSum = 0, carry = 0; // 总和与进位
            ListNode ans = new ListNode(); // 用于存储结果的链表
            while (!s1.empty() || !s2.empty()) { // 遍历栈
                if (!s1.empty()) { // 处理第一个栈
                    totalSum += s1.pop();
                }
                if (!s2.empty()) { // 处理第二个栈
                    totalSum += s2.pop();
                }

                ans.val = totalSum % 10; // 当前位的值
                carry = totalSum / 10; // 更新进位
                ListNode head = new ListNode(carry); // 新的头节点
                head.next = ans; // 将新节点连接到结果链表前面
                ans = head; // 更新结果链表头
                totalSum = carry; // 重置总和为进位值
            }

            return carry == 0 ? ans.next : ans; // 如果没有进位，返回ans.next，否则返回ans
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_445_AddTwoNumbersIi().new Solution();

        // 创建测试链表
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        // 运行测试样例
        ListNode result = solution.addTwoNumbers(l1, l2);

        // 打印结果
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}

/**
You are given two non-empty linked lists representing two non-negative integers.
 The most significant digit comes first and each of their nodes contains a 
single digit. Add the two numbers and return the sum as a linked list. 

 You may assume the two numbers do not contain any leading zero, except the 
number 0 itself. 

 
 Example 1: 
 
 
Input: l1 = [7,2,4,3], l2 = [5,6,4]
Output: [7,8,0,7]
 

 Example 2: 

 
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [8,0,7]
 

 Example 3: 

 
Input: l1 = [0], l2 = [0]
Output: [0]
 

 
 Constraints: 

 
 The number of nodes in each linked list is in the range [1, 100]. 
 0 <= Node.val <= 9 
 It is guaranteed that the list represents a number that does not have leading 
zeros. 
 

 
 Follow up: Could you solve it without reversing the input lists? 

 Related Topics Linked List Math Stack 👍 5885 👎 289

*/
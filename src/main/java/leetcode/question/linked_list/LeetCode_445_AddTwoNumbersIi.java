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
 * ==============================
 * 题目描述：LeetCode 445 - Add Two Numbers II
 * ==============================
 * 给定两个**非空的单向链表**，每个链表表示一个**非负整数**。
 * - 每个节点表示数字的一位，数字按照**从头到尾的顺序**存储，即**高位在链表头部**。
 * - 需要将两个数字相加，并以相同的格式返回一个新的链表表示结果。
 *
 * **要求：**
 * - 不能直接反转链表。
 * - 每位数字的值范围是 [0, 9]。
 * - 允许有进位。

 * **输入/输出示例：**
 * 输入：`l1 = [7, 2, 4, 3]`，`l2 = [5, 6, 4]`
 * 输出：`[7, 8, 0, 7]`
 *
 * 解释：
 * - 链表表示的数字是 7243 和 564。
 * - 相加结果为 7807，结果链表为 `[7, 8, 0, 7]`。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * 解决链表的加法问题有两种主要方法：
 *
 * **方法1：反转链表后相加**
 * - 先将两个链表反转，这样可以按照从低位到高位的顺序进行相加。
 * - 每次将两条链表的对应节点值相加，处理进位，并将结果存入新的链表中。
 * - 最后将结果链表再次反转，得到正确的结果。
 *
 * **方法2：使用栈**
 * - 不反转链表，使用栈来模拟从低位到高位的加法。
 * - 由于栈的特性是**后进先出**，将链表的值压入栈中后，就可以按照从低位到高位的顺序弹出。
 * - 每次将两个栈顶的值相加，处理进位，并将结果存入新的链表中。
 * - 最后将结果链表返回。

 * ------------------------------
 * **方法1：反转链表的解法步骤**
 * 1. 定义一个辅助方法 `reverseList`，用于反转链表。
 * 2. 将链表 `l1` 和 `l2` 分别反转。
 * 3. 使用一个循环，将两个链表的值逐位相加，并处理进位。
 * 4. 将结果链表再次反转，返回结果。
 *
 * **举例：**
 * - 输入链表：`l1 = [7, 2, 4, 3]`，`l2 = [5, 6, 4]`
 * - 反转后：`l1 = [3, 4, 2, 7]`，`l2 = [4, 6, 5]`
 * - 按位相加：
 *   ```
 *   3 + 4 = 7
 *   4 + 6 = 10，进位 1
 *   2 + 5 + 1 = 8
 *   7 + 0 = 7
 *   ```
 * - 结果链表：`[7, 8, 0, 7]`
 * - 反转结果链表，返回最终答案。

 * ------------------------------
 * **方法2：使用栈的解法步骤**
 * 1. 定义两个栈 `s1` 和 `s2`，分别用于存储链表 `l1` 和 `l2` 的值。
 * 2. 将链表的值逐个压入栈中。
 * 3. 使用一个循环，将两个栈顶的值逐位相加，并处理进位。
 * 4. 每次创建一个新节点，将其插入到结果链表的头部。
 * 5. 返回结果链表。

 * **举例：**
 * - 输入链表：`l1 = [7, 2, 4, 3]`，`l2 = [5, 6, 4]`
 * - 压入栈中：
 *   ```
 *   s1 = [7, 2, 4, 3]
 *   s2 = [5, 6, 4]
 *   ```
 * - 按位相加：
 *   ```
 *   3 + 4 = 7
 *   4 + 6 = 10，进位 1
 *   2 + 5 + 1 = 8
 *   7 + 0 = 7
 *   ```
 * - 结果链表：`[7, 8, 0, 7]`

 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **方法1：反转链表**
 * - **时间复杂度：O(N)**
 *   - 反转链表需要 O(N) 的时间。
 *   - 遍历链表进行加法操作需要 O(N) 的时间。
 * - **空间复杂度：O(1)**
 *   - 只使用了常数级别的额外空间。

 * **方法2：使用栈**
 * - **时间复杂度：O(N)**
 *   - 遍历链表压入栈需要 O(N) 的时间。
 *   - 遍历栈进行加法操作需要 O(N) 的时间。
 * - **空间复杂度：O(N)**
 *   - 使用了两个栈来存储链表的值。
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
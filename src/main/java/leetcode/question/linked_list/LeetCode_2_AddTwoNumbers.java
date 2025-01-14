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
 * ==============================
 * 题目描述：LeetCode 2 - Add Two Numbers
 * ==============================
 * 给定两个**非空的单向链表**，每个链表表示一个非负整数。
 * - 每个节点表示数字的一位，数字按照**从低位到高位的顺序**存储。
 * - 需要将两个数字相加，并以相同的格式返回一个新的链表表示结果。
 *
 * **规则：**
 * - 两个链表的长度可能不同。
 * - 如果相加的过程中有进位，则需要在结果链表中添加一个新的节点表示进位值。
 * - 结果链表不能有前导 0（除非结果本身为 0）。
 *
 * **示例：**
 * - 输入：`l1 = [2, 4, 3]`，`l2 = [5, 6, 4]`
 * - 输出：`[7, 0, 8]`
 * - 解释：链表表示的数字是 342 和 465，相加结果为 807。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * **核心思路：逐位相加，处理进位**
 * - 使用一个**虚拟头节点**简化链表操作。
 * - 遍历两个链表，将对应位置的数字相加，并处理可能的进位。
 * - 如果链表长度不同，则短链表的空位按 0 处理。
 * - 最终，将所有节点的值存储到结果链表中，返回结果链表的头节点。
 *
 * ------------------------------
 * **解法步骤：**
 * ------------------------------
 * **步骤1：初始化**
 * - 创建一个**虚拟头节点** `dummyHead`，用于存储结果链表的头节点。
 * - 定义一个指针 `curr` 指向当前操作的节点。
 * - 定义一个变量 `carry` 保存进位值，初始化为 0。

 * **步骤2：遍历两个链表，逐位相加**
 * - 使用 `while` 循环，条件为 `l1 != null` 或 `l2 != null` 或 `carry != 0`。
 * - 在循环中：
 *   - 获取当前节点的值，如果链表为空，则按 0 处理。
 *   - 计算当前位的**总和** `sum = carry + l1.val + l2.val`。
 *   - 计算新的**进位** `carry = sum / 10`。
 *   - 创建一个新节点，值为 `sum % 10`，将其添加到结果链表中。
 *   - 将指针 `curr` 移动到下一个节点。
 *   - 将 `l1` 和 `l2` 指针分别移动到下一个节点（如果不为空）。

 * **步骤3：返回结果链表**
 * - 返回虚拟头节点 `dummyHead` 的下一个节点，即结果链表的头节点。

 * ------------------------------
 * **举例解释：**
 *
 * **示例1：**
 * - 输入：`l1 = [2, 4, 3]`，`l2 = [5, 6, 4]`
 * - 过程：
 *   ```
 *   第一步：2 + 5 + 0 = 7，进位 0，结果链表：7
 *   第二步：4 + 6 + 0 = 10，进位 1，结果链表：7 -> 0
 *   第三步：3 + 4 + 1 = 8，进位 0，结果链表：7 -> 0 -> 8
 *   ```
 * - 输出结果：`[7, 0, 8]`，表示数字 807。

 * **示例2：**
 * - 输入：`l1 = [9, 9, 9]`，`l2 = [1]`
 * - 过程：
 *   ```
 *   第一步：9 + 1 + 0 = 10，进位 1，结果链表：0
 *   第二步：9 + 0 + 1 = 10，进位 1，结果链表：0 -> 0
 *   第三步：9 + 0 + 1 = 10，进位 1，结果链表：0 -> 0 -> 0
 *   第四步：0 + 0 + 1 = 1，进位 0，结果链表：0 -> 0 -> 0 -> 1
 *   ```
 * - 输出结果：`[0, 0, 0, 1]`，表示数字 1000。

 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：O(max(M, N))**
 * - M 是链表 `l1` 的长度，N 是链表 `l2` 的长度。
 * - 需要遍历两个链表的每个节点，因此时间复杂度为 O(max(M, N))。

 * **空间复杂度：O(max(M, N))**
 * - 结果链表最多包含 `max(M, N) + 1` 个节点。
 * - 除了结果链表的空间外，只使用了常数空间存储指针和进位值。
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
        System.out.println(18/10);
        System.out.println(3%10);

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
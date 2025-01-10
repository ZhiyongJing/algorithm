package leetcode.question.two_pointer;

import leetcode.util.ListNode;

/**
 *@Question:  61. Rotate List
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 62.25%
 *@Time  Complexity: O(N), where N is the number of nodes in the linked list
 *@Space Complexity: O(1), as no extra space is used except for a few pointers
 */
/**
 * @Question: 61. Rotate List
 * @Description:
 * 给定一个链表，将其向右旋转 k 个位置，其中 k 是非负整数。
 *
 * 示例:
 * 输入: 1->2->3->4->5, k = 2
 * 输出: 4->5->1->2->3
 *
 * 解释:
 * 将链表右旋转两次：
 * 第一次旋转：5->1->2->3->4
 * 第二次旋转：4->5->1->2->3
 *
 * @Solution:
 * 1. **特殊情况处理**:
 *    - 如果链表为空 (`head == null`) 或链表只有一个节点 (`head.next == null`)，直接返回链表。
 *    - 如果 k 为 0，也无需旋转，直接返回链表。
 *
 * 2. **计算链表长度并形成环**:
 *    - 遍历链表以计算其长度 n，同时将链表尾节点与头节点相连形成环形链表。
 *
 * 3. **确定新的头尾节点**:
 *    - 根据旋转次数 k，可以忽略超过链表长度的完整旋转次数，使用 `k % n` 获取有效旋转次数。
 *    - 新的尾节点是从头节点开始移动 `n - k % n - 1` 步的位置。
 *    - 新的头节点是新尾节点的下一个节点。
 *
 * 4. **断开环形链表**:
 *    - 将新尾节点的 `next` 指针设置为 `null`，断开环形链表。
 *    - 返回新的头节点。
 *
 * @Complexity:
 * - 时间复杂度: O(N)，需要遍历链表计算长度和找到新头尾节点。
 * - 空间复杂度: O(1)，只使用了常数级别的额外空间。
 *
 * @Examples:
 * 输入: head = [1,2,3,4,5], k = 2
 * 输出: [4,5,1,2,3]
 *
 * 输入: head = [0,1,2], k = 4
 * 输出: [2,0,1]
 */


public class LeetCode_61_RotateList{

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
        public ListNode rotateRight(ListNode head, int k) {
            // 如果链表为空，直接返回 null
            if (head == null) return null;
            // 如果链表只有一个节点，无需旋转，直接返回
            if (head.next == null) return head;

            // 将链表闭合成一个环形
            ListNode old_tail = head;
            int n;
            // 找到链表的尾节点并计算链表长度 n
            for (n = 1; old_tail.next != null; n++) old_tail = old_tail.next;
            old_tail.next = head; // 将尾节点连接到头节点形成环形链表

            // 找到新尾节点的位置 (n - k % n - 1) 和新头节点的位置 (n - k % n)
            ListNode new_tail = head;
            for (int i = 0; i < n - (k % n) - 1; i++)
                new_tail = new_tail.next; // 新尾节点为从头节点移动 n - k % n - 1 步的位置
            ListNode new_head = new_tail.next; // 新头节点是新尾节点的下一个节点

            // 断开环形链表，形成新的链表
            new_tail.next = null;

            return new_head; // 返回新的头节点
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_61_RotateList().new Solution();

        // 创建测试样例链表 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // 旋转链表 k = 2
        int k = 2;
        ListNode rotatedHead = solution.rotateRight(head, k);

        // 输出旋转后的链表
        System.out.print("Rotated List: ");
        while (rotatedHead != null) {
            System.out.print(rotatedHead.val + " ");
            rotatedHead = rotatedHead.next;
        }
    }
}

/**
Given the head of a linked list, rotate the list to the right by k places. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
 

 Example 2: 
 
 
Input: head = [0,1,2], k = 4
Output: [2,0,1]
 

 
 Constraints: 

 
 The number of nodes in the list is in the range [0, 500]. 
 -100 <= Node.val <= 100 
 0 <= k <= 2 * 10⁹ 
 

 Related Topics Linked List Two Pointers 👍 10094 👎 1487

*/
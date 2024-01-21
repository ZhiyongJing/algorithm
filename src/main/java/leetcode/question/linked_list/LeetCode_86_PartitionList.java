package leetcode.question.linked_list;

import leetcode.util.ListNode;

/**
  *@Question:  86. Partition List     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 59.699999999999996%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */
/**
 * ### 解题思路
 *
 * 这道题的目标是分隔链表，将链表中小于给定值 `x` 的节点放在大于等于 `x` 的节点之前。
 * 可以通过创建两个新链表（before 和 after）来实现。
 *
 * 1. **初始化节点：** 创建两个虚拟节点 `before_head` 和 `after_head`，用于分别保存两个链表的头部。
 *
 * 2. **遍历链表：** 遍历原始链表，将节点根据值的大小分别连接到 `before` 或 `after` 链表上。
 *
 * 3. **连接两个链表：** 最后，将 `before` 链表的尾部连接到 `after_head.next`，形成一个新的链表，
 * 返回 `before_head.next`。
 *
 * ### 时间复杂度
 *
 * - 遍历链表一次，时间复杂度为 O(N)，其中 N 为链表的长度。
 *
 * ### 空间复杂度
 *
 * - 使用了常数级的额外空间，主要是创建了两个虚拟节点 `before_head` 和 `after_head`
 * 以及两个指针 `before` 和 `after`。
 *
 * 该算法通过一次链表遍历，按照值的大小将节点连接到两个新链表上，时间和空间复杂度都是线性级别的。
 */

public class LeetCode_86_PartitionList {

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
        public ListNode partition(ListNode head, int x) {

            // before 和 after 是两个指针，用于创建两个链表
            // before_head 和 after_head 用于保存两个链表的头节点。
            // 这些都初始化为创建的虚拟节点。
            ListNode before_head = new ListNode(0);
            ListNode before = before_head;
            ListNode after_head = new ListNode(0);
            ListNode after = after_head;

            while (head != null) {

                // 如果原始链表节点的值小于给定的 x，
                // 将其分配到 before 链表。
                if (head.val < x) {
                    before.next = head;
                    before = before.next;
                } else {
                    // 如果原始链表节点的值大于或等于给定的 x，
                    // 将其分配到 after 链表。
                    after.next = head;
                    after = after.next;
                }

                // 在原始链表中向前移动
                head = head.next;
            }

            // "after" 链表的最后一个节点也是重新组合链表的结束节点
            after.next = null;

            // 一旦所有节点都正确分配到两个链表中，
            // 将它们组合在一起形成一个单一的链表并返回。
            before.next = after_head.next;

            return before_head.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_86_PartitionList().new Solution();

        // 测试用例
        ListNode head = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        int x = 3;
        ListNode result = solution.partition(head, x);

        // 打印结果
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 输出：1 2 2 4 3 5
    }
}

/**
Given the head of a linked list and a value x, partition it such that all nodes 
less than x come before nodes greater than or equal to x. 

 You should preserve the original relative order of the nodes in each of the 
two partitions. 

 
 Example 1: 
 
 
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
 

 Example 2: 

 
Input: head = [2,1], x = 2
Output: [1,2]
 

 
 Constraints: 

 
 The number of nodes in the list is in the range [0, 200]. 
 -100 <= Node.val <= 100 
 -200 <= x <= 200 
 

 Related Topics Linked List Two Pointers 👍 7122 👎 816

*/
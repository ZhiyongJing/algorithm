package leetcode.question.linked_list;

import leetcode.util.ListNode;

/**
  *@Question:  82. Remove Duplicates from Sorted List II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 41.64%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */



/**
 * ### 解题思路
 *
 * 这道题的目标是删除排序链表中的重复元素，包括重复的元素全部删除。解题思路主要使用双指针，
 * 其中一个指针用于遍历链表，另一个指针用于连接不重复的节点。
 *
 * 1. **哨兵节点：** 使用哨兵节点 `sentinel` 简化删除操作，其值不影响最终结果。
 *
 * 2. **双指针遍历：** 使用指针 `head` 遍历链表，当发现重复子链表的开始时，跳过所有重复节点，将 `pred.next` 连接到重复子链表的末尾，跳过所有重复节点。
 *
 * 3. **返回结果：** 最终返回 `sentinel.next`，即去掉哨兵节点后的链表。
 *
 * ### 时间复杂度
 *
 * - 遍历链表一次，时间复杂度为 O(N)，其中 N 为链表的长度。
 *
 * ### 空间复杂度
 *
 * - 使用了常数级的额外空间，主要是两个指针 `sentinel` 和 `pred`。
 *
 * 该算法通过一次链表遍历，删除了所有重复的节点，时间和空间复杂度都是线性级别的。
 */

public class LeetCode_82_RemoveDuplicatesFromSortedListIi {

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
        public ListNode deleteDuplicates(ListNode head) {
            // 哨兵节点，用于简化删除操作
            ListNode sentinel = new ListNode(0, head);

            // predecessor 为重复子链表之前的最后一个节点
            ListNode pred = sentinel;

            while (head != null) {
                // 如果是重复子链表的开始，跳过所有重复节点
                if (head.next != null && head.val == head.next.val) {
                    // 移动到重复子链表的末尾
                    while (head.next != null && head.val == head.next.val) {
                        head = head.next;
                    }

                    // 跳过所有重复节点
                    pred.next = head.next;

                    // 否则，移动 predecessor
                } else {
                    pred = pred.next;
                }

                // 继续向前移动
                head = head.next;
            }

            return sentinel.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_82_RemoveDuplicatesFromSortedListIi().new Solution();

        // 测试用例
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
        ListNode result = solution.deleteDuplicates(head);

        // 打印结果
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 输出：1 2 5
    }
}

/**
Given the head of a sorted linked list, delete all nodes that have duplicate 
numbers, leaving only distinct numbers from the original list. Return the linked 
list sorted as well. 

 
 Example 1: 
 
 
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]
 

 Example 2: 
 
 
Input: head = [1,1,1,2,3]
Output: [2,3]
 

 
 Constraints: 

 
 The number of nodes in the list is in the range [0, 300]. 
 -100 <= Node.val <= 100 
 The list is guaranteed to be sorted in ascending order. 
 

 Related Topics Linked List Two Pointers 👍 8524 👎 223

*/
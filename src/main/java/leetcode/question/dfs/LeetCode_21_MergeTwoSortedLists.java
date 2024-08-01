package leetcode.question.dfs;

import leetcode.util.ListNode;

/**
 *@Question:  21. Merge Two Sorted Lists
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 81.63%
 *@Time  Complexity: O(M + N)
 *@Space Complexity: O(M + N) for solution 1, O(1) for solution2
 */

public class LeetCode_21_MergeTwoSortedLists{

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
        //Solution1: 使用递归方法合并两个排序链表
        public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
            // 如果l1为空，则返回l2
            if (l1 == null) {
                return l2;
                // 如果l2为空，则返回l1
            } else if (l2 == null) {
                return l1;
                // 如果l1的值小于l2的值，将l1的next指向递归调用的结果
            } else if (l1.val < l2.val) {
                l1.next = mergeTwoLists1(l1.next, l2);
                return l1;
                // 否则，将l2的next指向递归调用的结果
            } else {
                l2.next = mergeTwoLists1(l1, l2.next);
                return l2;
            }
        }

        //solution2: 使用迭代方法合并两个排序链表
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // 创建一个哨兵节点，便于处理链表头部的变化
            ListNode prehead = new ListNode(-1);

            // 维护一个当前节点，用于构建新的合并链表
            ListNode prev = prehead;
            // 当l1和l2都不为空时，循环进行合并操作
            while (l1 != null && l2 != null) {
                // 如果l1的值小于等于l2，将l1连接到当前节点，移动l1指针
                if (l1.val <= l2.val) {
                    prev.next = l1;
                    l1 = l1.next;
                    // 否则，将l2连接到当前节点，移动l2指针
                } else {
                    prev.next = l2;
                    l2 = l2.next;
                }
                // 移动当前节点指针
                prev = prev.next;
            }

            // 当l1或l2还有剩余节点时，直接连接到合并链表的末尾
            prev.next = l1 == null ? l2 : l1;

            // 返回合并链表的头节点
            return prehead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_21_MergeTwoSortedLists().new Solution();
        // 测试样例
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode mergedList = solution.mergeTwoLists(l1, l2);

        // 打印合并后的链表
        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
    }
}

/**
You are given the heads of two sorted linked lists list1 and list2. 

 Merge the two lists into one sorted list. The list should be made by splicing 
together the nodes of the first two lists. 

 Return the head of the merged linked list. 

 
 Example 1: 
 
 
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
 

 Example 2: 

 
Input: list1 = [], list2 = []
Output: []
 

 Example 3: 

 
Input: list1 = [], list2 = [0]
Output: [0]
 

 
 Constraints: 

 
 The number of nodes in both lists is in the range [0, 50]. 
 -100 <= Node.val <= 100 
 Both list1 and list2 are sorted in non-decreasing order. 
 

 Related Topics Linked List Recursion 👍 21859 👎 2134

*/
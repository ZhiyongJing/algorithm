package leetcode.question.linked_list;

import leetcode.util.ListNode;

/**
  *@Question:  82. Remove Duplicates from Sorted List II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 41.64%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_82_RemoveDuplicatesFromSortedListIi{
    
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
        // Sentinel
        ListNode sentinel = new ListNode(0, head);

        // predecessor = the last node
        // before the sublist of duplicates
        ListNode pred = sentinel;

        while (head != null) {
            // If it's a beginning of the duplicates sublist
            // skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // Move till the end of the duplicates sublist
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }

                // Skip all duplicates
                pred.next = head.next;

                // otherwise, move predecessor
            } else {
                pred = pred.next;
            }

            // move forward
            head = head.next;
        }
        return sentinel.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_82_RemoveDuplicatesFromSortedListIi().new Solution();
        // TO TEST
        //solution.
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
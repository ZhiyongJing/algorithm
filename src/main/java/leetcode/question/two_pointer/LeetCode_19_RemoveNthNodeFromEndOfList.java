package leetcode.question.two_pointer;

import leetcode.util.ListNode;

/**
  *@Question:  19. Remove Nth Node From End of List
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 64.47%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

public class LeetCode_19_RemoveNthNodeFromEndOfList{

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_19_RemoveNthNodeFromEndOfList().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the head of a linked list, remove the nᵗʰ node from the end of the list 
and return its head. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
 

 Example 2: 

 
Input: head = [1], n = 1
Output: []
 

 Example 3: 

 
Input: head = [1,2], n = 1
Output: [1]
 

 
 Constraints: 

 
 The number of nodes in the list is sz. 
 1 <= sz <= 30 
 0 <= Node.val <= 100 
 1 <= n <= sz 
 

 
 Follow up: Could you do this in one pass? 

 Related Topics Linked List Two Pointers 👍 19383 👎 829

*/
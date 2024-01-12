package leetcode.question.linked_list;

import leetcode.util.ListNode;

/**
  *@Question:  206. Reverse Linked List     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 73.4%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

public class LeetCode_206_ReverseLinkedList{
    
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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_206_ReverseLinkedList().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the head of a singly linked list, reverse the list, and return the 
reversed list. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
 

 Example 2: 
 
 
Input: head = [1,2]
Output: [2,1]
 

 Example 3: 

 
Input: head = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the list is the range [0, 5000]. 
 -5000 <= Node.val <= 5000 
 

 
 Follow up: A linked list can be reversed either iteratively or recursively. 
Could you implement both? 

 Related Topics Linked List Recursion 👍 20322 👎 380

*/
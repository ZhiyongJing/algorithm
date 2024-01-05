package leetcode.frequent.based_on_data_structure.linked_list;

import leetcode.util.ListNode;

/**
  *@Question:  876. Middle of the Linked List     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 58.4%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

public class LeetCode_876_MiddleOfTheLinkedList{
    
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
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_876_MiddleOfTheLinkedList().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given the head of a singly linked list, return the middle node of the linked 
list. 

 If there are two middle nodes, return the second middle node. 

 
 Example 1: 
 
 
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
 

 Example 2: 
 
 
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return 
the second one.
 

 
 Constraints: 

 
 The number of nodes in the list is in the range [1, 100]. 
 1 <= Node.val <= 100 
 

 Related Topics Linked List Two Pointers 👍 10841 👎 330

*/
package leetcode.frequent.based_on_data_structure.heap;

import leetcode.util.ListNode;

import java.util.PriorityQueue;

/**
  *@Question:  23. Merge k Sorted Lists     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 72.26%      
  *@Time  Complexity: O(NlogK),K 是 Heap size
  *@Space Complexity: O(K)
 */

public class LeetCode_23_MergeKSortedLists{
    
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
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>( (a, b) -> a.val - b.val);
        ListNode dummyNode = new ListNode(-1);
        ListNode curNode = dummyNode;
        for (ListNode list : lists) {
            if (list != null) {
                // 这一步很关键，不能也没有必要将空对象添加到优先队列中
                priorityQueue.add(list);
            }
        }
        System.out.println(priorityQueue);
        while (!priorityQueue.isEmpty()) {
            // 优先队列非空才能出队
            ListNode node = priorityQueue.poll();
            // 当前节点的 next 指针指向出队元素
            curNode.next = node;
            // 当前指针向前移动一个元素，指向了刚刚出队的那个元素
            curNode = curNode.next;
            if (curNode.next != null) {
                // 只有非空节点才能加入到优先队列中
                priorityQueue.add(curNode.next);
            }
        }
        return dummyNode.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_23_MergeKSortedLists().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given an array of k linked-lists lists, each linked-list is sorted in 
ascending order. 

 Merge all the linked-lists into one sorted linked-list and return it. 

 
 Example 1: 

 
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
 

 Example 2: 

 
Input: lists = []
Output: []
 

 Example 3: 

 
Input: lists = [[]]
Output: []
 

 
 Constraints: 

 
 k == lists.length 
 0 <= k <= 10⁴ 
 0 <= lists[i].length <= 500 
 -10⁴ <= lists[i][j] <= 10⁴ 
 lists[i] is sorted in ascending order. 
 The sum of lists[i].length will not exceed 10⁴. 
 

 Related Topics Linked List Divide and Conquer Heap (Priority Queue) Merge Sort 
👍 18820 👎 684

*/
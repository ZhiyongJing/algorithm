package leetcode.question.linked_list;

import leetcode.util.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  1836. Remove Duplicates From an Unsorted Linked List
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 1. **统计每个值的出现次数**：
 *    - 我们首先遍历整个链表，并使用一个哈希表 `frequency` 来记录每个节点值出现的次数。遍历过程中，每遇到一个节点值，就在哈希表中将对应的计数加1。这样，我们可以知道链表中每个值出现了多少次。
 *
 * 2. **删除重复的节点**：
 *    - 我们引入一个虚拟头节点 `dummy`，指向链表的头节点。这样可以方便地处理头节点本身可能需要被删除的情况。
 *    - 在删除重复节点的过程中，我们需要维护两个指针：`prev` 和 `current`。`prev` 用于指向前一个非重复节点，`current` 用于遍历链表的每个节点。
 *    - 遍历链表时，如果 `current` 指向的节点值在哈希表中的计数大于1，说明该值重复，我们就需要删除这个节点。具体做法是将 `prev` 的 `next` 指针指向 `current` 的下一个节点，从而将 `current` 节点从链表中移除。
 *    - 如果 `current` 节点值没有重复（计数为1），我们只需将 `prev` 移动到 `current`，然后继续遍历。
 *
 * ### 时间复杂度
 * - **O(N)**: 其中 N 是链表中的节点数量。我们需要遍历链表两次：一次是统计每个值的出现次数，另一次是遍历链表删除重复的节点。
 *
 * ### 空间复杂度
 * - **O(N)**: 我们使用了一个哈希表来存储链表中每个值的出现次数。在最坏的情况下，链表中的每个节点值都不同，因此哈希表需要存储 N 个不同的键值对。
 */

public class LeetCode_1836_RemoveDuplicatesFromAnUnsortedLinkedList{

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
    public class Solution {

        public ListNode deleteDuplicatesUnsorted(ListNode head) {
            // 创建一个虚拟节点，并指向head，方便处理头节点
            ListNode dummy = new ListNode(-1, head);
            // 使用哈希表记录每个值出现的次数
            Map<Integer, Integer> frequency = new HashMap<>();
            ListNode temp = head, current = dummy.next, prev = dummy;

            // 统计链表中每个值的出现次数
            while (temp != null) {
                frequency.put(temp.val, frequency.getOrDefault(temp.val, 0) + 1);
                temp = temp.next;
            }

            // 遍历链表，删除出现次数大于一次的节点
            while (current != null) {
                if (frequency.get(current.val) > 1) {
                    // 从链表中删除当前节点
                    prev.next = current.next;
                } else {
                    prev = current; // 移动前置指针
                }
                current = current.next; // 移动当前指针
            }
            return dummy.next; // 返回处理后的链表头节点
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1836_RemoveDuplicatesFromAnUnsortedLinkedList().new Solution();
        // 测试代码
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        ListNode result = solution.deleteDuplicatesUnsorted(head);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 输出应为：3
    }
}

// 辅助类 ListNode


/**
Given the head of a linked list, find all the values that appear more than once 
in the list and delete the nodes that have any of those values. 

 Return the linked list after the deletions. 

 
 Example 1: 
 
 
Input: head = [1,2,3,2]
Output: [1,3]
Explanation: 2 appears twice in the linked list, so all 2's should be deleted. 
After deleting all 2's, we are left with [1,3].
 

 Example 2: 
 
 
Input: head = [2,1,1,2]
Output: []
Explanation: 2 and 1 both appear twice. All the elements should be deleted.
 

 Example 3: 
 
 
Input: head = [3,2,2,1,3,2,4]
Output: [1,4]
Explanation: 3 appears twice and 2 appears three times. After deleting all 3's 
and 2's, we are left with [1,4].
 

 
 Constraints: 

 
 The number of nodes in the list is in the range [1, 10⁵] 
 1 <= Node.val <= 10⁵ 
 

 Related Topics Hash Table Linked List 👍 398 👎 11

*/
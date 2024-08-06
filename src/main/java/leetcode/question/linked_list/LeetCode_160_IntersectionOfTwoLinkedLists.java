package leetcode.question.linked_list;

import leetcode.util.ListNode;

/**
 *@Question:  160. Intersection of Two Linked Lists
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 49.86%
 *@Time  Complexity: O(N + M)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目
 *
 * 给定两个单链表，分别代表链表A和链表B。每个链表的节点包含一个整数值和一个指向下一个节点的指针。编写一个函数来获取两个链表相交的起始节点。如果两个链表没有交点，返回 `null`。
 *
 * ### 解题思路
 *
 * 1. **双指针法**：
 *    - 我们使用两个指针 `pA` 和 `pB` 分别指向链表A和链表B的头节点。
 *    - 在每一步中，指针 `pA` 和 `pB` 分别向前移动。如果指针到达链表的末尾，则重定向到另一个链表的头部。
 *    - 这个过程会在两个指针遍历两个链表的总长度之后停止。此时，如果存在交点，两个指针会在交点处相遇；否则，两指针最终会同时指向 `null`。
 *
 * 2. **为什么这种方法有效**：
 *    - 如果两个链表有交点，那么在第一次遍历结束时，指针 `pA` 和 `pB` 会切换到对方的起点，并继续遍历。由于两个指针遍历的总路径长度是相同的，所以它们将在第二次遍历时在交点处相遇。
 *    - 如果两个链表没有交点，指针 `pA` 和 `pB` 将在第二次遍历结束时同时到达 `null`。
 *
 * ### 时间复杂度
 *
 * - **O(m + n)**，其中 m 和 n 分别是链表A和链表B的长度。在最坏情况下，每个指针遍历两个链表的总长度。
 *
 * ### 空间复杂度
 *
 * - **O(1)**，我们只使用了常数级别的额外空间（指针 `pA` 和 `pB`）。
 *
 * 这种双指针法的关键在于使用两个指针同时遍历两个链表，确保即使链表长度不同，两指针也能在同一时刻到达相交节点或结束遍历。
 */
public class LeetCode_160_IntersectionOfTwoLinkedLists{

//leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // 初始化两个指针分别指向两个链表的头节点
            ListNode pA = headA;
            ListNode pB = headB;

            // 当两个指针不相等时进入循环
            while (pA != pB) {
                // 如果指针A已经遍历到链表A的末尾，则转向链表B的头节点
                pA = pA == null ? headB : pA.next;
                // 如果指针B已经遍历到链表B的末尾，则转向链表A的头节点
                pB = pB == null ? headA : pB.next;
            }
            // 返回相交节点或null（若无相交节点）
            return pA;
            // 备注：如果链表不相交，在第二次遍历时，A和B的指针仍然会对齐，
            // 只是没有公共节点，最终会同时达到各自的末尾，pA和pB都将为NULL。
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_160_IntersectionOfTwoLinkedLists().new Solution();

        // 创建测试用例
        // 链表A: 4 -> 1 -> 8 -> 4 -> 5
        // 链表B: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        ListNode intersect = new ListNode(8);
        intersect.next = new ListNode(4);
        intersect.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = intersect;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = intersect;

        // 调用方法并打印结果
        ListNode result = solution.getIntersectionNode(headA, headB);
        System.out.println(result != null ? result.val : "No intersection");
        // 输出: 8（两个链表的交点）
    }
}

/**
Given the heads of two singly linked-lists headA and headB, return the node at 
which the two lists intersect. If the two linked lists have no intersection at 
all, return null. 

 For example, the following two linked lists begin to intersect at node c1: 
 
 The test cases are generated such that there are no cycles anywhere in the 
entire linked structure. 

 Note that the linked lists must retain their original structure after the 
function returns. 

 Custom Judge: 

 The inputs to the judge are given as follows (your program is not given these 
inputs): 

 
 intersectVal - The value of the node where the intersection occurs. This is 0 
if there is no intersected node. 
 listA - The first linked list. 
 listB - The second linked list. 
 skipA - The number of nodes to skip ahead in listA (starting from the head) to 
get to the intersected node. 
 skipB - The number of nodes to skip ahead in listB (starting from the head) to 
get to the intersected node. 
 

 The judge will then create the linked structure based on these inputs and pass 
the two heads, headA and headB to your program. If you correctly return the 
intersected node, then your solution will be accepted. 

 
 Example 1: 
 
 
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, 
skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if 
the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,
6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 
nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1
 in A and B (2ⁿᵈ node in A and 3ʳᵈ node in B) are different node references. In 
other words, they point to two different locations in memory, while the nodes 
with value 8 in A and B (3ʳᵈ node in A and 4ᵗʰ node in B) point to the same 
location in memory.
 

 Example 2: 
 
 
Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB 
= 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if 
the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,
2,4]. There are 3 nodes before the intersected node in A; There are 1 node 
before the intersected node in B.
 

 Example 3: 
 
 
Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it 
reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, 
while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
 

 
 Constraints: 

 
 The number of nodes of listA is in the m. 
 The number of nodes of listB is in the n. 
 1 <= m, n <= 3 * 10⁴ 
 1 <= Node.val <= 10⁵ 
 0 <= skipA < m 
 0 <= skipB < n 
 intersectVal is 0 if listA and listB do not intersect. 
 intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect. 
 

 
Follow up: Could you write a solution that runs in 
O(m + n) time and use only 
O(1) memory?

 Related Topics Hash Table Linked List Two Pointers 👍 14380 👎 1281

*/
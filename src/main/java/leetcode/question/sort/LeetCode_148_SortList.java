package leetcode.question.sort;

import leetcode.util.ListNode;

/**
 * @Question: 148. Sort List
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 51.88%
 * @Time Complexity: O(n logn)
 * @Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 这道题使用了归并排序（Merge Sort）的思想，该排序算法具有稳定性和适用于链表等数据结构的特点。
 * 主要思路包括找到链表中点、递归地对左右两部分排序，以及合并两个有序链表。
 *
 * 1. **找到链表中点（`getMid`函数）：**
 *    - 使用两个指针，一个快指针每次移动两步，一个慢指针每次移动一步。
 *    - 当快指针到达链表尾部时，慢指针指向的位置即为链表的中点。
 *    - 同时记录中点的前一个节点（`midPrev`），以便在之后分割链表。
 *
 * 2. **递归地对左右两部分排序：**
 *    - 递归结束条件是链表为空或只有一个节点，直接返回链表。
 *    - 利用找到的中点，将原链表分为两部分，分别递归地对左右两部分进行排序。
 *
 * 3. **合并两个有序链表（`merge`函数）：**
 *    - 创建一个虚拟头节点 `dummyHead` 和一个尾节点 `tail`。
 *    - 遍历两个有序链表，比较节点值，将较小的节点加入到合并后链表中。
 *    - 最终合并后链表的头节点即为 `dummyHead.next`。
 *
 * 4. **整体过程：**
 *    - 通过递归不断地将链表分割为子问题，然后合并子问题的解。
 *    - 归并排序的时间复杂度为 O(n logn)，空间复杂度为 O(1)。
 *
 * ### 代码注释
 *
 * 1. **`sortList` 函数：**
 *    - 递归地对链表进行排序，首先找到链表中点，然后分别对左右两部分递归调用 `sortList` 函数，最后合并两个有序链表。
 *
 * 2. **`merge` 函数：**
 *    - 合并两个有序链表的函数，创建虚拟头节点和尾节点，比较两个链表的节点值，将较小的节点加入到合并后链表中。
 *
 * 3. **`getMid` 函数：**
 *    - 找到链表的中点，并断开链表。使用两个指针，一个快指针每次移动两步，一个慢指针每次移动一步，
 *    当快指针到达链表尾部时，慢指针指向的位置即为链表的中点。
 *
 * ### 时间复杂度
 *
 * - **归并排序时间复杂度：**
 *   - 归并排序的时间复杂度为 O(n logn)，其中 n 为链表的长度。
 *   - 这是由于每次递归都将链表分为两半，递归深度为 logn，每层递归的合并操作时间复杂度为 O(n)。
 *
 * ### 空间复杂度
 *
 * - **归并排序空间复杂度：**
 *   - 归并排序的空间复杂度为 O(1)，这是由于整个排序过程只需要常数级别的额外空间，主要是用于记录中点前一个节点（`midPrev`）
 *   和创建虚拟头节点、尾节点。
 *
 * 总体上，这段代码实现了归并排序算法用于链表排序，具有较好的时间和空间复杂度。
 */
public class LeetCode_148_SortList {

    // leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public ListNode sortList(ListNode head) {
            // 递归结束条件，链表为空或只有一个节点时直接返回
            if (head == null || head.next == null)
                return head;

            // 获取链表中点
            ListNode mid = getMid(head);

            // 递归地对左右两部分排序
            ListNode left = sortList(head);
            ListNode right = sortList(mid);

            // 合并两个有序链表
            return merge(left, right);
        }

        // 合并两个有序链表
        ListNode merge(ListNode list1, ListNode list2) {
            ListNode dummyHead = new ListNode();
            ListNode tail = dummyHead;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    tail.next = list1;
                    list1 = list1.next;
                    tail = tail.next;
                } else {
                    tail.next = list2;
                    list2 = list2.next;
                    tail = tail.next;
                }
                System.out.println("tail is: " + tail.val);

            }
            tail.next = (list1 != null) ? list1 : list2;
            return dummyHead.next;
        }

        // 找到链表的中点，并断开链表
        ListNode getMid(ListNode head) {
            ListNode midPrev = null;
            while (head != null && head.next != null) {
                midPrev = (midPrev == null) ? head : midPrev.next;
                head = head.next.next;
            }
            ListNode mid = midPrev.next;
            midPrev.next = null;
            return mid;
        }
    }

    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 创建 Solution 对象
        Solution solution = new LeetCode_148_SortList().new Solution();

        // 测试样例1
        ListNode head1 = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode result1 = solution.sortList(head1);
        System.out.println("Result 1: " + result1); // 预期输出：1 -> 2 -> 3 -> 4

        // 测试样例2
        ListNode head2 = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));
        ListNode result2 = solution.sortList(head2);
        System.out.println("Result 2: " + result2); // 预期输出：-1 -> 0 -> 3 -> 4 -> 5

        // 测试样例3
        ListNode head3 = null;
        ListNode result3 = solution.sortList(head3);
        System.out.println("Result 3: " + result3); // 预期输出：null
    }
}

/**
Given the head of a linked list, return the list after sorting it in ascending 
order. 

 
 Example 1: 
 
 
Input: head = [4,2,1,3]
Output: [1,2,3,4]
 

 Example 2: 
 
 
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
 

 Example 3: 

 
Input: head = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the list is in the range [0, 5 * 10⁴]. 
 -10⁵ <= Node.val <= 10⁵ 
 

 
 Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e.
 constant space)? 

 Related Topics Linked List Two Pointers Divide and Conquer Sorting Merge Sort ?
? 11051 👎 323

*/

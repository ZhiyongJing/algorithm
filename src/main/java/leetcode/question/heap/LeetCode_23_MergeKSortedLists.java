package leetcode.question.heap;
import leetcode.util.ListNode;

import java.util.PriorityQueue;

/**
 *@Question:  23. Merge k Sorted Lists
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 72.26%
 *@Time  Complexity: O(NlogK),K 是 Heap size
 *@Space Complexity: O(K)
 */

/**
 * 这个问题的解决方案可以通过使用优先队列（最小堆）来实现。下面是详细的解题思路以及时间和空间复杂度分析：
 *
 * ### 解题思路：
 *
 * 1. **使用优先队列（最小堆）**：
 *    - 我们可以创建一个优先队列来保存链表节点。队列会根据节点值的大小自动进行排序，因此我们每次取出队列中的头部节点时，就可以保证取到最小的节点。
 *    - 在Java中，我们使用PriorityQueue来实现优先队列，并传入一个比较器，以便根据节点值进行排序。
 *
 * 2. **初始化**：
 *    - 我们首先将所有链表的头节点加入到优先队列中，如果某个链表的头节点为空，则不加入队列。
 *
 * 3. **合并链表**：
 *    - 从优先队列中不断取出节点，并将其连接到结果链表中。
 *    - 如果取出的节点还有下一个节点，则将下一个节点加入到优先队列中，继续进行迭代，直到队列为空。
 *
 * ### 时间复杂度分析：
 *
 * - **初始化优先队列**：我们需要将所有链表的头节点加入到优先队列中，时间复杂度为 O(KlogK)，其中K是链表的数量，logK是优先队列插入节点的时间复杂度。
 * - **合并链表**：每个节点都会被取出和插入一次，因此总共有N个节点，时间复杂度为O(NlogK)。这是因为每次插入和删除节点的时间复杂度为logK。
 * - 因此，总体时间复杂度为 O(NlogK)。
 *
 * ### 空间复杂度分析：
 *
 * - 我们使用了一个优先队列来保存节点，其空间复杂度为 O(K)，其中K是链表的数量。
 * - 除此之外，我们还使用了一些额外的空间来保存结果链表和辅助节点等，但这些额外空间的数量不随着链表的数量而变化，因此可以忽略不计。
 * - 因此，总体空间复杂度为 O(K)。
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
        // 解题方法
        public ListNode mergeKLists(ListNode[] lists) {
            int len = lists.length; // 获取链表数组的长度
            if (len == 0) {
                return null; // 如果数组为空，则返回空
            }
            // 使用优先队列（最小堆）来对链表进行排序，比较器按照节点值升序排序
            PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>( (a, b) -> a.val - b.val);
            ListNode dummyNode = new ListNode(-1); // 创建虚拟头节点
            ListNode curNode = dummyNode; // 创建当前节点指针，初始指向虚拟头节点
            for (ListNode list : lists) {
                if (list != null) {
                    // 将非空链表头节点加入到优先队列中
                    priorityQueue.add(list);
                }
            }
            while (!priorityQueue.isEmpty()) {
                // 当优先队列非空时，不断取出最小节点
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
            return dummyNode.next; // 返回虚拟头节点的下一个节点，即合并后的链表头节点
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_23_MergeKSortedLists.Solution solution = new LeetCode_23_MergeKSortedLists().new Solution();
        // 测试代码待添加
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
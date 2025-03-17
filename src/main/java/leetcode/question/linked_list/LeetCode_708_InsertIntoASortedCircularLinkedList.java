package leetcode.question.linked_list;
/**
 *@Question:  708. Insert into a Sorted Circular Linked List
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 71.22%
 *@Time Complexity: O(N) // 最坏情况下需要遍历整个循环链表
 *@Space Complexity: O(1) // 只使用了额外的指针变量
 */
/**
 * 题目描述：
 * LeetCode 708. Insert into a Sorted Circular Linked List
 *
 * 给定一个**排序后的循环链表** `head` 和一个整数 `insertVal`，要求在链表中插入 `insertVal`，并仍然保持**循环有序**。
 *
 * **循环链表定义：**
 * - 链表中最后一个节点的 `next` 指向**头节点**，形成循环。
 * - **链表可能为空**，如果为空，则创建一个新节点，并让其 `next` 指向自身。
 * - **链表可能所有节点值相同**，在此情况下 `insertVal` 可以插入到任意位置。
 *
 * **示例 1：**
 * ```
 * 输入: head = [3, 4, 1], insertVal = 2
 * 输出: [3, 4, 1, 2] 或 [4, 1, 2, 3] 或其他循环排列
 * ```
 *
 * **示例 2：**
 * ```
 * 输入: head = [], insertVal = 1
 * 输出: [1]
 * ```
 *
 * ---
 *
 * **解题思路：**
 *
 * 由于链表是 **排序的循环链表**，我们需要找到合适的位置插入 `insertVal`。
 * **核心思路：**
 * 1. **特殊情况处理**
 *    - 如果 `head == null`，直接创建新节点并让 `next` 指向自己。
 *
 * 2. **遍历链表，找到插入点**
 *    - 维护两个指针：
 *      - `prev`：当前遍历的节点。
 *      - `curr`：`prev.next`，即下一个节点。
 *    - **三种插入情况**：
 *      - **Case 1**: `insertVal` 在 `prev` 和 `curr` 之间，满足 `prev.val <= insertVal <= curr.val`，则插入 `prev` 和 `curr` 之间。
 *      - **Case 2**: `prev.val > curr.val`（意味着 `prev` 是最大值，`curr` 是最小值），如果 `insertVal` 是最大或最小值，则插入 `prev` 和 `curr` 之间。
 *      - **Case 3**: 经过一整圈仍未找到合适位置（说明链表所有值相同），直接插入 `prev` 之后。
 *
 * 3. **返回 `head`**
 *    - 确保返回原 `head`，保持循环链表结构。
 *
 * ---
 * **示例解析**
 *
 * **输入 1**: `head = [3, 4, 1]`，插入 `2`
 * ```
 *  现有链表: 3 -> 4 -> 1 (循环)
 *  1. prev = 3, curr = 4, 发现 `3 <= 2 <= 4` 不满足，继续遍历
 *  2. prev = 4, curr = 1, 发现 `4 > 1`，且 `2` 处于 `1` 和 `4` 之间，插入
 *  结果: 3 -> 4 -> 1 -> 2 (循环)
 * ```
 * **输入 2**: `head = [1, 3]`，插入 `4`
 * ```
 *  现有链表: 1 -> 3 (循环)
 *  1. prev = 1, curr = 3, 发现 `1 <= 4 <= 3` 不满足，继续遍历
 *  2. prev = 3, curr = 1, 发现 `3 > 1`，且 `4` 是最大值，插入
 *  结果: 1 -> 3 -> 4 (循环)
 * ```
 *
 * ---
 * **时间和空间复杂度分析**
 *
 * - **时间复杂度：O(N)**
 *   - 最坏情况下需要遍历整个链表 `N` 次才能找到插入点（如所有节点值相同）。
 *
 * - **空间复杂度：O(1)**
 *   - 只使用了 **常数级别的指针** 变量 `prev` 和 `curr`，无需额外存储空间。
 */


public class LeetCode_708_InsertIntoASortedCircularLinkedList{
    static class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

    class Solution {
        public Node insert(Node head, int insertVal) {
            // 如果链表为空，创建一个新节点，并指向自身
            if (head == null) {
                Node newNode = new Node(insertVal, null);
                newNode.next = newNode;
                return newNode;
            }

            Node prev = head; // 记录当前遍历的前一个节点
            Node curr = head.next; // 记录当前遍历的节点
            boolean toInsert = false; // 是否找到插入位置的标志
            //head is not the smallest one
            while(curr != head) {
                // **Case 1**: 插入值在 `prev` 和 `curr` 之间（常规插入）
                if (prev.val <= insertVal && insertVal <= curr.val) {
                    toInsert = true;
                }
                // **Case 2**: `prev` 的值大于 `curr`（循环链表的尾部到头部）
                else if (prev.val > curr.val) {
                    // 插入值大于最大值，或小于最小值，应插入此位置
                    if (insertVal >= prev.val || insertVal <= curr.val)
                        toInsert = true;
                }

                // **如果找到了插入位置**
                if (toInsert) {
                    prev.next = new Node(insertVal, curr);
                    return head;
                }

                // **继续遍历**
                prev = curr;
                curr = curr.next;
            }

            // **Case 3**: head 是最小值
            prev.next = new Node(insertVal, curr);
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_708_InsertIntoASortedCircularLinkedList().new Solution();

        // **测试用例 1**: 插入到中间
        Node head1 = new Node(3);
        Node node2 = new Node(4);
        Node node3 = new Node(1);
        head1.next = node2;
        node2.next = node3;
        node3.next = head1;
        Node result1 = solution.insert(head1, 2);
        System.out.println("插入 2 后的循环链表: " + printCircularList(result1));
        // 预期输出: 1 -> 2 -> 3 -> 4 -> (循环)

        // **测试用例 2**: 插入到最大值之后
        Node head2 = new Node(1);
        head2.next = new Node(3, head2);
        Node result2 = solution.insert(head2, 4);
        System.out.println("插入 4 后的循环链表: " + printCircularList(result2));
        // 预期输出: 1 -> 3 -> 4 -> (循环)

        // **测试用例 3**: 插入到最小值之前
        Node head3 = new Node(3);
        head3.next = new Node(5, new Node(1, head3));
        Node result3 = solution.insert(head3, 0);
        System.out.println("插入 0 后的循环链表: " + printCircularList(result3));
        // 预期输出: 0 -> 1 -> 3 -> 5 -> (循环)

        // **测试用例 4**: 空链表插入
        Node result4 = solution.insert(null, 5);
        System.out.println("插入 5 到空链表: " + printCircularList(result4));
        // 预期输出: 5 -> (循环)
    }

    // 辅助方法：打印循环链表
    private static String printCircularList(Node head) {
        if (head == null) return "空链表";
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        do {
            sb.append(temp.val).append(" -> ");
            temp = temp.next;
        } while (temp != head);
        sb.append("(循环)");
        return sb.toString();
    }
}

/**
Given a Circular Linked List node, which is sorted in non-descending order, 
write a function to insert a value insertVal into the list such that it remains a 
sorted circular list. The given node can be a reference to any single node in the 
list and may not necessarily be the smallest value in the circular list. 

 If there are multiple suitable places for insertion, you may choose any place 
to insert the new value. After the insertion, the circular list should remain 
sorted. 

 If the list is empty (i.e., the given node is null), you should create a new 
single circular list and return the reference to that single node. Otherwise, you 
should return the originally given node. 

 
 Example 1: 

 
 
Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three 
elements. You are given a reference to the node with value 3, and we need to insert 2
 into the list. The new node should be inserted between node 1 and node 3. 
After the insertion, the list should look like this, and we should still return node 
3.
 




 Example 2: 

 
Input: head = [], insertVal = 1
Output: [1]
Explanation: The list is empty (given head is null). We create a new single 
circular list and return the reference to that single node.
 

 Example 3: 

 
Input: head = [1], insertVal = 0
Output: [1,0]
 

 
 Constraints: 

 
 The number of nodes in the list is in the range [0, 5 * 10⁴]. 
 -10⁶ <= Node.val, insertVal <= 10⁶ 
 

 Related Topics Linked List 👍 1286 👎 792

*/
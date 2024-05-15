package interview.company.amazon;

/**
  *@Question:  138. Copy List with Random Pointer     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 72.15%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

/**
 * 这个问题的解题思路是使用哈希表来存储已经访问过的节点，以及它们的副本。具体步骤如下：
 *
 * 1. 创建一个哈希表，用于存储已经访问过的节点及其副本。
 * 2. 遍历原始链表，对每个节点进行如下操作：
 *    - 如果当前节点已经在哈希表中存在，则直接从哈希表中获取其副本。
 *    - 如果当前节点不在哈希表中，创建一个新节点，将当前节点与其副本添加到哈希表中。
 * 3. 再次遍历原始链表，为每个新节点设置其 next 和 random 指针，这里需要通过哈希表来获取对应的副本节点。
 * 4. 返回新链表的头节点，即哈希表中存储的原始链表头节点的副本。
 *
 * 这种方法的时间复杂度是 O(N)，其中 N 是链表中节点的数量。在第一次遍历中，需要将原始链表的每个节点都复制一份，并将其存储到哈希表中，所以时间复杂度为 O(N)。在第二次遍历中，同样需要遍历原始链表的每个节点，并通过哈希表获取其副本节点，所以时间复杂度也是 O(N)。因此，总的时间复杂度为 O(N)。
 *
 * 空间复杂度也是 O(N)，主要是由哈希表存储已访问节点及其副本所导致的。在最坏的情况下，哈希表需要存储原始链表的所有节点及其副本，因此空间复杂度为 O(N)。
 */


public class LeetCode_138_CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // 使用HashMap来存储已访问的节点，以旧节点引用作为“键”，新节点引用作为“值”
        HashMap<Node, Node> visited = new HashMap<Node, Node>();

        // 辅助函数，用于获取克隆的节点
        public Node getClonedNode(Node node) {
            // 如果节点存在
            if (node != null) {
                // 检查节点是否在visited字典中
                if (this.visited.containsKey(node)) {
                    // 如果在visited字典中，则返回字典中的新节点引用
                    return this.visited.get(node);
                } else {
                    // 否则创建一个新节点，添加到字典中，并返回
                    this.visited.put(node, new Node(node.val));
                    return this.visited.get(node);
                }
            }
            return null;
        }

        // 复制链表的函数
        public Node copyRandomList(Node head) {
            // 处理特殊情况
            if (head == null) {
                return null;
            }

            Node oldNode = head;

            // 创建新头节点
            Node newNode = new Node(oldNode.val);
            this.visited.put(oldNode, newNode);

            // 遍历链表直到所有节点都被复制
            while (oldNode != null) {
                // 获取随机指针和下一个指针所指向节点的克隆
                newNode.random = this.getClonedNode(oldNode.random);
                newNode.next = this.getClonedNode(oldNode.next);

                // 在链表中向前移动一步
                oldNode = oldNode.next;
                newNode = newNode.next;
            }
            // 返回头节点的克隆
            return this.visited.get(head);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 测试代码待添加
    }
}

/**
A linked list of length n is given such that each node contains an additional 
random pointer, which could point to any node in the list, or null. 

 Construct a deep copy of the list. The deep copy should consist of exactly n 
brand new nodes, where each new node has its value set to the value of its 
corresponding original node. Both the next and random pointer of the new nodes should 
point to new nodes in the copied list such that the pointers in the original 
list and copied list represent the same list state. None of the pointers in the new 
list should point to nodes in the original list. 

 For example, if there are two nodes X and Y in the original list, where X.
random --> Y, then for the corresponding two nodes x and y in the copied list, x.
random --> y. 

 Return the head of the copied linked list. 

 The linked list is represented in the input/output as a list of n nodes. Each 
node is represented as a pair of [val, random_index] where: 

 
 val: an integer representing Node.val 
 random_index: the index of the node (range from 0 to n-1) that the random 
pointer points to, or null if it does not point to any node. 
 

 Your code will only be given the head of the original linked list. 

 
 Example 1: 
 
 
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 

 Example 2: 
 
 
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
 

 Example 3: 

 

 
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
 

 
 Constraints: 

 
 0 <= n <= 1000 
 -10⁴ <= Node.val <= 10⁴ 
 Node.random is null or is pointing to some node in the linked list. 
 

 Related Topics Hash Table Linked List 👍 13642 👎 1446

*/
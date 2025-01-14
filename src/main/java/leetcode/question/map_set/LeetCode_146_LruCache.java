package leetcode.question.map_set;

import java.util.HashMap;
import java.util.Map;

/**
  *@Question:  146. LRU Cache     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 96.4%      
  *@Time  Complexity: O(1)
  *@Space Complexity: O(capacity)
 */

/**
 * ==============================
 * 题目描述：LeetCode 146 - LRU Cache
 * ==============================
 * 设计并实现一个 **LRU（Least Recently Used）缓存**。
 * 1. LRU 缓存在达到容量上限时，会移除最久未使用的元素。
 * 2. 提供两个操作：
 *    - **get(key)**：获取缓存中指定 key 的值。如果 key 不存在，返回 -1。
 *    - **put(key, value)**：将一个新的键值对插入到缓存中。如果缓存容量已满，需要移除最久未使用的元素后，再插入新的键值对。
 *
 * **要求：** 两个操作的时间复杂度均为 O(1)。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * 为了实现 O(1) 的插入、删除和查找操作，使用了以下两种数据结构的组合：
 * 1. **HashMap**：用于存储 key 到节点的映射关系，以实现 O(1) 的查找操作。
 * 2. **双向链表**：用于维护缓存中元素的访问顺序。最近访问的元素移动到链表头部，最久未访问的元素在链表尾部。
 *
 * ✅ **核心操作：**
 * - 当访问某个 key 时，将对应的节点移动到链表头部。
 * - 当插入新元素时，将新节点添加到链表头部。
 * - 当缓存超过容量时，弹出链表尾部的节点（即最久未访问的节点），并从 HashMap 中移除对应的映射关系。
 *
 * ------------------------------
 * **思路步骤及举例解释：**
 * ------------------------------
 * **1. 初始化**
 * - 使用两个哨兵节点 **head** 和 **tail**，分别作为链表的头部和尾部，简化边界条件处理。
 * - **HashMap** 用于存储 key 到链表节点的映射关系。

 * **2. get(key) 操作**
 * - 如果指定的 key 不在缓存中，返回 -1。
 * - 如果 key 存在，将对应的节点移动到链表头部。
 * - 举例：
 *   - 初始缓存状态：`[(1, 1), (2, 2)]`，容量为 2。
 *   - 调用 get(1)：返回 1，并将节点 (1, 1) 移动到头部。
 *   - 更新后的链表状态：`[(1, 1), (2, 2)]`。

 * **3. put(key, value) 操作**
 * - 如果指定的 key 已经存在，更新节点的值，并将节点移动到头部。
 * - 如果 key 不存在：
 *   - 创建新节点，将其插入到链表头部。
 *   - 更新 HashMap 中的映射关系。
 *   - 如果缓存超过容量，弹出链表尾部节点，并从 HashMap 中移除对应的 key。
 * - 举例：
 *   - 初始缓存状态：`[(1, 1), (2, 2)]`，容量为 2。
 *   - 调用 put(3, 3)：缓存已满，移除尾部节点 (2, 2)，并插入新节点 (3, 3)。
 *   - 更新后的链表状态：`[(3, 3), (1, 1)]`。

 * ------------------------------
 * **操作说明：**
 * - **addNode(node)**：将节点插入到链表头部。
 * - **removeNode(node)**：从链表中移除指定节点。
 * - **moveToHead(node)**：将节点移动到链表头部。
 * - **popTail()**：弹出链表尾部节点，并返回该节点。
 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：**
 * - get(key)：O(1) —— 通过 HashMap 快速查找节点，并通过双向链表快速移动节点。
 * - put(key, value)：O(1) —— 通过 HashMap 快速查找或插入节点，并通过双向链表快速插入或删除节点。

 * **空间复杂度：O(N)**
 * - 其中 N 是缓存的容量。
 * - 需要使用 HashMap 和双向链表来存储 N 个键值对。
 */

public class LeetCode_146_LruCache {

    // leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
        }
        //head=headNext...
        private void addNode(DLinkedNode node) {
            // 将新节点添加到头节点后面
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            // 从链表中移除指定节点
            DLinkedNode prev = node.prev;
            DLinkedNode next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        private void moveToHead(DLinkedNode node) {
            // 将指定节点移动到头部
            removeNode(node);
            addNode(node);
        }

        private DLinkedNode popTail() {
            // 弹出当前尾部节点
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;

            // 使用哨兵节点，简化边界操作
            head = new DLinkedNode();
            tail = new DLinkedNode();

            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) return -1;

            // 访问后移动节点到头部，为了保持LRU顺序
            moveToHead(node);

            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);

            if (node == null) {
                // 如果节点不存在，创建新节点并加入到链表和缓存中
                DLinkedNode newNode = new DLinkedNode();
                newNode.key = key;
                newNode.value = value;

                cache.put(key, newNode);
                addNode(newNode);

                size++;

                if (size > capacity) {
                    // 如果超过容量，弹出尾部节点，并从缓存中移除
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key);
                    size--;
                }
            } else {
                // 如果节点存在，更新值并移动到头部
                node.value = value;
                moveToHead(node);
            }
        }
    }

    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LRUCache lruCache = new LeetCode_146_LruCache().new LRUCache(2);

        // 测试用例
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1)); // 返回 1
        lruCache.put(3, 3); // 该操作会使得密钥 2 作废
        System.out.println(lruCache.get(2)); // 返回 -1 (未找到)
        lruCache.put(4, 4); // 该操作会使得密钥 1 作废
        System.out.println(lruCache.get(1)); // 返回 -1 (未找到)
        System.out.println(lruCache.get(3)); // 返回 3
        System.out.println(lruCache.get(4)); // 返回 4
    }
}

/**
Design a data structure that follows the constraints of a Least Recently Used (
LRU) cache. 

 Implement the LRUCache class: 

 
 LRUCache(int capacity) Initialize the LRU cache with positive size capacity. 
 int get(int key) Return the value of the key if the key exists, otherwise 
return -1. 
 void put(int key, int value) Update the value of the key if the key exists. 
Otherwise, add the key-value pair to the cache. If the number of keys exceeds the 
capacity from this operation, evict the least recently used key. 
 

 The functions get and put must each run in O(1) average time complexity. 

 
 Example 1: 

 
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 

 
 Constraints: 

 
 1 <= capacity <= 3000 
 0 <= key <= 10⁴ 
 0 <= value <= 10⁵ 
 At most 2 * 10⁵ calls will be made to get and put. 
 

 Related Topics Hash Table Linked List Design Doubly-Linked List 👍 19822 👎 922


*/
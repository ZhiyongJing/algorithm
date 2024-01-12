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
 * **解题思路：**
 *
 * LRU（Least Recently Used）缓存是一种缓存策略，当缓存满了之后，会优先淘汰最近最少使用的数据。为了实现 LRU 缓存，
 * 我们需要使用数据结构来存储数据，并维护数据的使用顺序。
 *
 * 在这个题目中，我们使用一个哈希表（HashMap）存储键值对，其中键是缓存中的键，值是对应的节点。
 * 同时，我们使用一个双向链表来维护节点的使用顺序，链表的头部表示最近使用的节点，尾部表示最久未使用的节点。
 *
 * 为了提高操作效率，我们在链表中使用哨兵节点，即虚拟头部和虚拟尾部。这样，在添加、删除节点时，我们无需检查节点是否为空，
 * 简化了边界情况的处理。
 *
 * 具体的操作包括：
 *
 * 1. **访问节点时：**
 *    - 如果节点存在于缓存中，我们需要将该节点移到链表的头部，表示最近使用。
 *    - 如果节点不存在，返回 -1。
 *
 * 2. **插入节点时：**
 *    - 如果缓存未满，直接在链表头部插入新节点，并在哈希表中更新。
 *    - 如果缓存已满，弹出链表尾部节点，删除哈希表中的对应项，再在链表头部插入新节点。
 *
 * 3. **删除节点时：**
 *    - 从链表和哈希表中同时删除对应的节点。
 *
 * **时间复杂度：**
 *
 * - 插入、删除和访问节点的时间复杂度均为 O(1)，因为哈希表和双向链表的操作都是常数时间。
 *
 * **空间复杂度：**
 *
 * - 哈希表和双向链表的空间复杂度均为 O(capacity)，其中 capacity 为缓存的容量。
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
package interview.company.amazon;

/**
  *@Question:  146. LRU Cache
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 98.92%
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

/**
 * 这是一个LRU Cache（最近最少使用缓存）的实现。LRU Cache是一种常用的缓存淘汰策略，当缓存满时，移除最近最少使用的项，以腾出空间给新加入的项。
 *
 * **解题思路：**
 *
 * 1. 使用双向链表和哈希表实现LRU Cache。双向链表用于维护缓存中元素的顺序，哈希表用于实现元素的快速查找。
 * 2. 双向链表中的每个节点都包含一个键值对，并且有指向前一个节点和后一个节点的指针。
 * 3. 当有新元素插入缓存时，如果缓存未满，则将新元素插入链表头部，并更新哈希表。如果缓存已满，则移除链表尾部的元素，
 * 并从哈希表中删除对应的键。
 * 4. 当缓存中的元素被访问时，将该元素移到链表头部，以表示最近使用。
 * 5. 这样，链表头部的元素表示最近使用的元素，链表尾部的元素表示最久未使用的元素。
 *
 * **时间复杂度：**
 *
 * - get和put操作的时间复杂度都是O(1)，因为哈希表支持常数时间的查找和插入操作。
 *
 * **空间复杂度：**
 *
 * - 空间复杂度取决于缓存的容量，为O(capacity)，因为需要使用一个哈希表来存储键值对，并且需要维护一个双向链表来表示LRU顺序。
 */
public class LeetCode_146_LruCache{

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

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


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_146_LruCache().new Solution();
        // TO TEST
        //solution.
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
 

 Related Topics Hash Table Linked List Design Doubly-Linked List 👍 20405 👎 999


*/
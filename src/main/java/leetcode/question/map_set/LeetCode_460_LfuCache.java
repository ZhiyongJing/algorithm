package leetcode.question.map_set;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *@Question:  460. LFU Cache（最不经常使用缓存）
 *@Difficulty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 81.91%
 *@Time Complexity: O(1)（所有操作均为 O(1)）
 *@Space Complexity: O(capacity)（使用 HashMap 和 LinkedHashSet 存储键值对）
 */
/**
 * 题目描述：
 * --------------------------
 * LeetCode 460 - LFU Cache（最不经常使用缓存）
 *
 * 设计并实现一个 **最不经常使用 (LFU) 缓存**。
 * LFU 缓存的特点是：
 * 1. **每个键有一个访问频率，访问频率随着 get 或 put 操作递增**。
 * 2. **当缓存达到容量上限时，移除使用频率最小的键**，如果多个键具有相同的最小使用频率，则移除最久未使用的键。
 *
 * 需要实现 `LFUCache` 类：
 * - `LFUCache(int capacity)`: 初始化 LFU 缓存，设置最大容量 `capacity`。
 * - `int get(int key)`: 获取 `key` 对应的值，并增加 `key` 的使用频率。
 * - `void put(int key, int value)`: 插入 `key-value` 对，如果 `key` 已存在，则更新 `value` 并增加 `key` 的使用频率。
 *   - 如果 `cache` 已满，则移除最不经常使用的键。
 *
 * **示例**
 * ```
 * LFUCache cache = new LFUCache(2);
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);  // 返回 1
 * cache.put(3, 3); // 淘汰 key = 2，因为 2 和 1 频率相同，但 2 先插入
 * cache.get(2); // 返回 -1 (未找到)
 * cache.get(3); // 返回 3
 * ```
 *
 *
 * 解题思路：
 * --------------------------
 * **关键点：**
 * - 需要 **快速更新访问频率**，保证 `get` 和 `put` 操作 **O(1)**。
 * - 需要 **快速找到最不经常使用的键**，如果多个键频率相同，则移除 **最久未使用** 的键。
 *
 * **数据结构：**
 * 1. **`cache` (`HashMap<Integer, Pair<Integer, Integer>>`)**
 *    - 存储 `key -> (访问频率, 值)`
 * 2. **`frequencies` (`HashMap<Integer, LinkedHashSet<Integer>>`)**
 *    - 存储 `访问频率 -> 具有该频率的所有键`，使用 `LinkedHashSet` 维持插入顺序。
 * 3. **`minf`**
 *    - 记录当前 **最小使用频率**，方便快速查找要淘汰的键。
 *
 *
 * **详细步骤**
 * 1. **插入（put）**
 *    - **如果 `key` 存在**：
 *      - 更新 `cache[key]` 的值，并调用 `get(key)` 增加访问频率。
 *    - **如果 `cache` 已满**：
 *      - **找到 `minf` 对应的 `LinkedHashSet`**（存储所有该频率的键）。
 *      - **移除 `LinkedHashSet` 中最先插入的键**（即最久未使用）。
 *      - **删除该键的缓存**。
 *      - **插入新键**，并将 `minf` 设为 `1`（新插入的键访问频率默认为 1）。
 *
 * 2. **获取（get）**
 *    - **如果 `key` 存在**：
 *      - **获取当前频率 `freq`**。
 *      - **从 `frequencies[freq]` 移除该键**。
 *      - **如果 `frequencies[freq]` 为空**，删除该频率，并更新 `minf`。
 *      - **增加 `key` 的访问频率 `freq+1`**，并加入 `frequencies[freq+1]`。
 *      - **返回 `key` 对应的值**。
 *    - **如果 `key` 不存在**：
 *      - 返回 `-1`。
 *
 *
 * **举例分析**
 * --------------------------
 * **示例 1**
 * ```
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1); // 访问 1 次
 * cache.put(3, 3); // 淘汰 2（因 1 访问过，2 访问频率最小）
 * cache.get(2); // -1
 * cache.get(3); // 3
 * ```
 * **过程：**
 * ```
 * 初始状态:
 * cache = {1 -> (1, 1), 2 -> (1, 2)}
 * frequencies = {1 -> {1, 2}}
 *
 * get(1):
 * - frequencies 更新为 {1 -> {2}, 2 -> {1}}
 * - cache = {1 -> (2, 1), 2 -> (1, 2)}
 *
 * put(3, 3):
 * - 淘汰 2
 * - cache = {1 -> (2, 1), 3 -> (1, 3)}
 * - frequencies = {1 -> {3}, 2 -> {1}}
 * ```
 *
 *
 * 时间和空间复杂度分析：
 * ---------------------------------
 * **时间复杂度**
 * - **get 操作**: O(1)
 * - **put 操作**: O(1)
 * - **插入/删除**: O(1)（使用 `HashMap` 和 `LinkedHashSet`）
 * - **总时间复杂度：O(1)**（所有操作均摊 O(1)）
 *
 * **空间复杂度**
 * - 需要存储 `cache` 和 `frequencies`，总大小 O(capacity)
 * - **总空间复杂度：O(capacity)**。
 */


public class LeetCode_460_LfuCache {

    //leetcode submit region begin(Prohibit modification and deletion)
    class LFUCache {

        // 存储键值对的缓存，key -> (使用频率, 值)
        private Map<Integer, Pair<Integer, Integer>> cache;
        // 记录使用频率的映射，key -> 具有相同频率的键集合
        private Map<Integer, LinkedHashSet<Integer>> frequencies;
        // 记录当前最小的使用频率
        private int minf;
        // 缓存的容量
        private int capacity;

        /**
         * 插入键值对到缓存，并更新其使用频率
         * @param key 要插入的键
         * @param frequency 当前键的使用频率
         * @param value 当前键的值
         */
        private void insert(int key, int frequency, int value) {
            cache.put(key, new Pair<>(frequency, value)); // 更新缓存中的键值
            frequencies.putIfAbsent(frequency, new LinkedHashSet<>()); // 确保频率集合存在
            frequencies.get(frequency).add(key); // 在对应频率集合中加入该键
        }

        /**
         * 初始化 LFU 缓存
         * @param capacity 缓存容量
         */
        public LFUCache(int capacity) {
            cache = new HashMap<>();
            frequencies = new HashMap<>();
            minf = 0; // 初始最小使用频率
            this.capacity = capacity;
        }

        /**
         * 获取键对应的值，并增加其使用频率
         * @param key 查询的键
         * @return 如果键存在，则返回对应的值，否则返回 -1
         */
        public int get(int key) {
            Pair<Integer, Integer> frequencyAndValue = cache.get(key);
            if (frequencyAndValue == null) {
                return -1; // 如果键不存在，返回 -1
            }
            final int frequency = frequencyAndValue.getKey(); // 获取当前频率
            final Set<Integer> keys = frequencies.get(frequency); // 获取当前频率的键集合
            keys.remove(key); // 从当前频率集合中移除该键
            if (keys.isEmpty()) { // 如果当前频率的集合为空，则删除该频率
                frequencies.remove(frequency);
                if (minf == frequency) { // 如果当前频率是最小频率，则增加 minf
                    ++minf;
                }
            }
            final int value = frequencyAndValue.getValue(); // 获取键的值
            insert(key, frequency + 1, value); // 重新插入该键，并增加使用频率
            return value;
        }

        /**
         * 插入键值对，如果已存在则更新值，并增加使用频率
         * @param key 插入的键
         * @param value 插入的值
         */
        public void put(int key, int value) {
            if (capacity <= 0) {
                return; // 如果容量为 0，直接返回
            }
            Pair<Integer, Integer> frequencyAndValue = cache.get(key);
            if (frequencyAndValue != null) {
                cache.put(key, new Pair<>(frequencyAndValue.getKey(), value)); // 更新值
                get(key); // 增加使用频率
                return;
            }
            if (capacity == cache.size()) { // 如果缓存已满，删除最不经常使用的键
                final Set<Integer> keys = frequencies.get(minf);
                final int keyToDelete = keys.iterator().next(); // 选择最不经常使用的键
                cache.remove(keyToDelete); // 从缓存中删除
                keys.remove(keyToDelete); // 从使用频率集合中删除
                if (keys.isEmpty()) {
                    frequencies.remove(minf);
                }
            }
            minf = 1; // 插入新元素时，最小使用频率重置为 1
            insert(key, 1, value);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_460_LfuCache.LFUCache lfuCache = new LeetCode_460_LfuCache().new LFUCache(2);

        // 测试用例
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        System.out.println(lfuCache.get(1)); // 返回 1
        lfuCache.put(3, 3); // 淘汰键 2（因为键 2 使用频率最低）
        System.out.println(lfuCache.get(2)); // 返回 -1（键 2 已被淘汰）
        System.out.println(lfuCache.get(3)); // 返回 3
        lfuCache.put(4, 4); // 淘汰键 1（键 1 和键 3 频率相同，但键 1 先插入）
        System.out.println(lfuCache.get(1)); // 返回 -1（键 1 已被淘汰）
        System.out.println(lfuCache.get(3)); // 返回 3
        System.out.println(lfuCache.get(4)); // 返回 4
    }
}

/**
Design and implement a data structure for a Least Frequently Used (LFU) cache. 

 Implement the LFUCache class: 

 
 LFUCache(int capacity) Initializes the object with the capacity of the data 
structure. 
 int get(int key) Gets the value of the key if the key exists in the cache. 
Otherwise, returns -1. 
 void put(int key, int value) Update the value of the key if present, or 
inserts the key if not already present. When the cache reaches its capacity, it should 
invalidate and remove the least frequently used key before inserting a new item.
 For this problem, when there is a tie (i.e., two or more keys with the same 
frequency), the least recently used key would be invalidated. 
 

 To determine the least frequently used key, a use counter is maintained for 
each key in the cache. The key with the smallest use counter is the least 
frequently used key. 

 When a key is first inserted into the cache, its use counter is set to 1 (due 
to the put operation). The use counter for a key in the cache is incremented 
either a get or put operation is called on it. 

 The functions get and put must each run in O(1) average time complexity. 

 
 Example 1: 

 
Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", 
"get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  
most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, 
invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3
 

 
 Constraints: 

 
 1 <= capacity <= 10⁴ 
 0 <= key <= 10⁵ 
 0 <= value <= 10⁹ 
 At most 2 * 10⁵ calls will be made to get and put. 
 

 
 

 Related Topics Hash Table Linked List Design Doubly-Linked List 👍 5877 👎 337

*/
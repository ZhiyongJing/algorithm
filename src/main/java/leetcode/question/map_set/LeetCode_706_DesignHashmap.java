package leetcode.question.map_set;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *@Question:  706. Design HashMap
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 64.3%
 *@Time  Complexity: O(N / K) N is number of all possible keys and K is number of predefined buckets, which is 2069
 *@Space Complexity: O( K + M) K is number of predefined buckets, M is the number of unique keys.
 */
/**
 * ===============================================
 * LeetCode 706. Design HashMap
 * ===============================================
 *
 * 【一、题目描述】
 * 实现一个不使用任何内建哈希表库的 HashMap（哈希映射）。
 *
 * 实现以下方法：
 * - put(key, value)：插入 (key, value) 映射。如果该 key 已存在，则更新其对应的 value。
 * - get(key)：返回 key 所映射的 value，如果 key 不存在，返回 -1。
 * - remove(key)：移除 key 的所有映射。
 *
 * 约束：
 * - 所有值都是非负整数；
 * - 最多调用 10^4 次；
 * - 所有操作的 key 范围为 [0, 10^6]。
 *
 *
 * 【二、解题思路：拉链法实现 HashMap】
 * 为避免冲突，使用**“拉链法”**实现哈希表，每个桶使用链表存储多个键值对。
 *
 * ✅ 核心设计如下：
 * 1. 定义桶结构 `Bucket`，每个桶内部用链表保存多个 `(key, value)` 对；
 * 2. 构造函数中定义桶数组 `hash_table`，桶数使用一个质数如 2069，减少冲突；
 * 3. 将 key 取模映射到某个桶：`hash_key = key % key_space`；
 * 4. 每个操作：
 *    - `put(key, val)`：如果 key 已存在，更新；否则添加新 pair；
 *    - `get(key)`：在对应桶中顺序查找；
 *    - `remove(key)`：从桶中移除匹配的 key；
 *
 * ✅ 示例流程：
 * - 初始化：key_space = 2069，hash_table 是 2069 个空桶；
 * - put(1, 1)：hash_key = 1，插入 pair(1, 1) 到第 1 个桶；
 * - put(2, 2)：hash_key = 2，插入 pair(2, 2) 到第 2 个桶；
 * - get(1)：hash_key = 1，返回 1；
 * - get(3)：hash_key = 3，不存在，返回 -1；
 * - put(2, 1)：hash_key = 2，更新 bucket 中的 (2, 2) 为 (2, 1)；
 * - remove(2)：从第 2 个桶中移除 key = 2；
 *
 * ✅ 注意事项：
 * - 用质数作为桶数，减少哈希冲突；
 * - equals() 比较 key 时不能用 ==；
 * - 删除操作时注意不能在迭代器中直接 remove（可优化）；
 *
 *
 * 【三、时间与空间复杂度分析】
 *
 * 时间复杂度（均摊）：
 * - put(key, value)：O(N / K)，N 是键值对总数，K 是桶数量；
 * - get(key)：O(N / K)，在对应桶中遍历链表；
 * - remove(key)：O(N / K)，需要线性查找桶内元素；
 * - 因为桶数量固定，平均每个桶很短，所以近似为 O(1)；
 *
 * 空间复杂度：
 * - O(K + M)：
 *   - K 为桶数量（即固定桶数组大小）；
 *   - M 为不同 key 的数量（存储在链表中）；
 */

public class LeetCode_706_DesignHashmap{

//leetcode submit region begin(Prohibit modification and deletion)

    // 定义一个泛型的键值对结构
    class Pair<U, V> {
        public U key;
        public V value;

        public Pair(U key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 定义每个桶的结构，使用链表解决哈希冲突
    class Bucket {
        // 使用链表存储每个桶中的键值对
        private List<Pair<Integer, Integer>> bucket;

        public Bucket() {
            this.bucket = new LinkedList<Pair<Integer, Integer>>();
        }

        // 根据 key 获取 value
        public Integer get(Integer key) {
            for (Pair<Integer, Integer> pair : this.bucket) {
                if (pair.key.equals(key))
                    return pair.value;
            }
            return -1;
        }

        // 更新 key 对应的 value，如果不存在则添加新键值对
        public void update(Integer key, Integer value) {
            boolean found = false;
            for (Pair<Integer, Integer> pair : this.bucket) {
                if (pair.key.equals(key)) {
                    pair.value = value;
                    found = true;
                }
            }
            if (!found)
                this.bucket.add(new Pair<Integer, Integer>(key, value));
        }

        // 删除指定 key 的键值对
        public void remove(Integer key) {
            for (Pair<Integer, Integer> pair : this.bucket) {
                if (pair.key.equals(key)) {
                    this.bucket.remove(pair);
                    break;
                }
            }
        }
    }

    // 主类 MyHashMap 实现
    class MyHashMap {
        // 哈希表大小，使用质数避免冲突
        private int key_space;
        // 哈希表，保存每个桶
        private List<Bucket> hash_table;

        /** Initialize your data structure here. */
        public MyHashMap() {
            this.key_space = 2069; // 使用质数作为桶数，减少哈希冲突,质数没有非 1 和自身以外的因子
            this.hash_table = new ArrayList<Bucket>();
            for (int i = 0; i < this.key_space; ++i) {
                this.hash_table.add(new Bucket());
            }
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            // 计算 key 的哈希桶索引
            int hash_key = key % this.key_space;
            // 在对应桶中更新或添加键值对
            this.hash_table.get(hash_key).update(key, value);
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
         * for the key
         */
        public int get(int key) {
            // 计算哈希桶索引，查询值
            int hash_key = key % this.key_space;
            return this.hash_table.get(hash_key).get(key);
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            // 计算哈希桶索引，执行删除
            int hash_key = key % this.key_space;
            this.hash_table.get(hash_key).remove(key);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 实例化 HashMap
        MyHashMap map = new LeetCode_706_DesignHashmap().new MyHashMap();

        // 测试 put 操作
        map.put(1, 1);
        map.put(2, 2);

        // 测试 get 操作
        System.out.println("get(1): " + map.get(1)); // 应返回 1
        System.out.println("get(3): " + map.get(3)); // 应返回 -1（不存在）

        // 测试更新已有 key
        map.put(2, 1);
        System.out.println("get(2) after update: " + map.get(2)); // 应返回 1

        // 测试删除操作
        map.remove(2);
        System.out.println("get(2) after remove: " + map.get(2)); // 应返回 -1
    }
}

/**
Design a HashMap without using any built-in hash table libraries. 

 Implement the MyHashMap class: 

 
 MyHashMap() initializes the object with an empty map. 
 void put(int key, int value) inserts a (key, value) pair into the HashMap. If 
the key already exists in the map, update the corresponding value. 
 int get(int key) returns the value to which the specified key is mapped, or -1 
if this map contains no mapping for the key. 
 void remove(key) removes the key and its corresponding value if the map 
contains the mapping for the key. 
 

 
 Example 1: 

 
Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2
]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the 
existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 

 
 Constraints: 

 
 0 <= key, value <= 10⁶ 
 At most 10⁴ calls will be made to put, get, and remove. 
 

 Related Topics Array Hash Table Linked List Design Hash Function 👍 5243 👎 481


*/
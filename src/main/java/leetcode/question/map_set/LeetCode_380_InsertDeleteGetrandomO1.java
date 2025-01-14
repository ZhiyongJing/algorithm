package leetcode.question.map_set;

import java.util.*;
/**
 *@Question:  380. Insert Delete GetRandom O(1)
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 86.28%
 *@Time  Complexity: O(1)
 *@Space Complexity: O(N)
 */

/**
 * ==============================
 * 题目描述：LeetCode 380 - Insert Delete GetRandom O(1)
 * ==============================
 * 设计一个数据结构，实现以下三个操作，并且所有操作的时间复杂度为 O(1)：
 * 1. insert(val)：将元素 val 插入集合中，如果元素已存在则返回 false，否则返回 true。
 * 2. remove(val)：从集合中移除元素 val，如果元素存在则返回 true，否则返回 false。
 * 3. getRandom()：随机返回集合中的一个元素，每个元素被返回的概率相等。
 *
 * 关键点：所有操作的时间复杂度必须是 O(1)，不能使用排序、遍历等耗时操作。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * 为了实现 O(1) 的插入、删除和随机获取元素，选择使用以下两种数据结构：
 * 1. **HashMap (dict)**：用于存储元素值到索引的映射。
 * 2. **ArrayList (list)**：用于存储实际的元素值，并支持随机访问。
 *
 * ✅ **核心思想：**
 * - 使用 HashMap 的快速查找特性，将元素值映射到列表中的索引位置。
 * - 使用 ArrayList 的快速随机访问特性，支持 O(1) 时间获取随机元素。
 * - 当删除元素时，将列表的最后一个元素移动到被删除元素的位置，从而保持列表的连续性。
 *
 * ------------------------------
 * **思路步骤及举例解释：**
 * ------------------------------
 * **1. 插入操作 (insert)**
 * - 如果元素已经存在于字典中，返回 false；否则，将元素添加到列表末尾，并记录在字典中。
 * - 举例：
 *   - 初始集合为空：`[]`
 *   - insert(1)：列表变为 `[1]`，字典为 `{1=0}`，返回 true。
 *   - insert(2)：列表变为 `[1, 2]`，字典为 `{1=0, 2=1}`，返回 true。
 *   - insert(1)：元素已存在于字典中，返回 false。

 * **2. 删除操作 (remove)**
 * - 如果元素不在字典中，返回 false。
 * - 如果元素存在，将列表的最后一个元素移动到被删除元素的位置，更新字典中的索引映射，然后删除列表末尾元素。
 * - 举例：
 *   - 初始列表为 `[1, 2, 3]`，字典为 `{1=0, 2=1, 3=2}`。
 *   - remove(2)：列表变为 `[1, 3]`，字典为 `{1=0, 3=1}`，返回 true。
 *   - remove(4)：元素不存在于字典中，返回 false。

 * **3. 随机获取操作 (getRandom)**
 * - 使用随机数生成器在列表的索引范围内生成一个随机索引，并返回该索引对应的元素值。
 * - 举例：
 *   - 列表为 `[1, 3]`。
 *   - getRandom() 可能返回 1 或 3，每个元素的概率相等为 50%。
 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：**
 * - insert()：O(1) —— HashMap 的 put 操作和 ArrayList 的 add 操作均为 O(1)。
 * - remove()：O(1) —— HashMap 的 remove 操作和 ArrayList 的 set/remove 操作均为 O(1)。
 * - getRandom()：O(1) —— ArrayList 的随机访问操作为 O(1)。

 * **空间复杂度：O(N)**
 * - 需要使用一个 HashMap 和一个 ArrayList 来存储 N 个元素的值和索引映射，空间复杂度为 O(N)。
 */



public class LeetCode_380_InsertDeleteGetrandomO1 {

    //leetcode submit region begin(Prohibit modification and deletion)
    class RandomizedSet {
        // 使用 Map 存储元素值及其在列表中的索引位置，以便快速查找
        Map<Integer, Integer> dict; // 用于存储值到索引的映射
        // 使用 List 存储实际的元素值，以便支持随机访问
        List<Integer> list; // 存储实际的元素列表
        // 使用 Random 类来生成随机数，用于随机获取列表中的元素
        Random rand = new Random(); // 随机数生成器

        /** 初始化数据结构 */
        public RandomizedSet() {
            dict = new HashMap(); // 初始化字典，用于存储值到索引的映射
            list = new ArrayList(); // 初始化列表，用于存储实际的值
        }

        /** 插入一个值到集合中。如果集合中已经包含该元素，则返回 false；否则插入成功返回 true */
        public boolean insert(int val) {
            // 如果字典中已经包含这个值，说明元素已存在，返回 false
            if (dict.containsKey(val)) return false;

            // 如果值不存在，则将其添加到列表的末尾
            // 同时在字典中记录该值在列表中的索引位置
            dict.put(val, list.size());
            list.add(list.size(), val); // 将值添加到列表的末尾
            return true; // 插入成功，返回 true
        }

        /** 从集合中移除一个值。如果集合中包含该值，则移除成功返回 true；否则返回 false */
        public boolean remove(int val) {
            // 如果字典中不包含该值，说明元素不存在，返回 false
            if (!dict.containsKey(val)) return false;

            // 获取要删除的元素的索引位置
            int idx = dict.get(val);
            // 获取列表中的最后一个元素
            int lastElement = list.get(list.size() - 1);

            // 将最后一个元素移动到要删除的元素位置上
            list.set(idx, lastElement);
            // 更新字典中最后一个元素的索引位置
            dict.put(lastElement, idx);

            // 移除列表的最后一个元素（已经被移动到要删除的位置上）
            list.remove(list.size() - 1);
            // 从字典中删除要移除的元素
            dict.remove(val);
            return true; // 删除成功，返回 true
        }

        /** 随机从集合中获取一个元素 */
        public int getRandom() {
            // 使用随机数生成器在列表的索引范围内生成一个随机索引
            // 然后从列表中获取该索引对应的值并返回
            return list.get(rand.nextInt(list.size()));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 创建 RandomizedSet 对象
        RandomizedSet randomizedSet = new LeetCode_380_InsertDeleteGetrandomO1().new RandomizedSet();

        // 测试用例 1：插入新元素
        System.out.println(randomizedSet.insert(1)); // 返回 true，集合中包含 [1]
        System.out.println(randomizedSet.insert(2)); // 返回 true，集合中包含 [1, 2]
        System.out.println(randomizedSet.insert(3)); // 返回 true，集合中包含 [1, 2, 3]

        // 测试用例 2：删除元素
        System.out.println(randomizedSet.remove(2)); // 返回 true，集合中包含 [1, 3]

        // 测试用例 3：随机获取一个元素
        System.out.println(randomizedSet.getRandom()); // 返回 1 或 3，概率相等
    }
}

/**
 Implement the RandomizedSet class:


 RandomizedSet() Initializes the RandomizedSet object.
 bool insert(int val) Inserts an item val into the set if not present. Returns
 true if the item was not present, false otherwise.
 bool remove(int val) Removes an item val from the set if present. Returns true
 if the item was present, false otherwise.
 int getRandom() Returns a random element from the current set of elements (
 it's guaranteed that at least one element exists when this method is called). Each
 element must have the same probability of being returned.


 You must implement the functions of the class such that each function works in
 average O(1) time complexity.


 Example 1:


 Input
 ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert",
 "getRandom"]
 [[], [1], [2], [2], [], [1], [2], []]
 Output
 [null, true, false, true, 2, true, false, 2]

 Explanation
 RandomizedSet randomizedSet = new RandomizedSet();
 randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was
 inserted successfully.
 randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now
 contains [1,2].
 randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now
 contains [2].
 randomizedSet.insert(2); // 2 was already in the set, so return false.
 randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom()
 will always return 2.



 Constraints:


 -2³¹ <= val <= 2³¹ - 1
 At most 2 * 10⁵ calls will be made to insert, remove, and getRandom.
 There will be at least one element in the data structure when getRandom is
 called.


 Related Topics Array Hash Table Math Design Randomized 👍 9116 👎 607

 */
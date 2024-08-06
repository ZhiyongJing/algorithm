package leetcode.question.map_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 *@Question:  380. Insert Delete GetRandom O(1)
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 86.28%
 *@Time  Complexity: O(1)
 *@Space Complexity: O(N)
 */

/**
 * 这道题目要求设计一个数据结构，支持在常数时间内插入、删除和获取随机元素。这里我们使用哈希表和动态数组来实现。
 *
 * 解题思路：
 * 1. 使用哈希表 `dict` 来存储值到索引的映射，以支持常数时间的查找。
 * 2. 使用动态数组 `list` 来存储实际的元素，以支持常数时间的随机访问。
 * 3. 对于插入操作，我们首先检查值是否已经存在于哈希表中，如果存在则返回 false；
 * 否则，将值插入到数组的末尾，并在哈希表中添加值到索引的映射。
 * 4. 对于删除操作，我们首先检查值是否存在于哈希表中，如果不存在则返回 false；
 * 否则，找到该值在数组中的索引，并将数组末尾的元素移动到该位置，然后更新该元素在哈希表中的索引，最后从数组和哈希表中删除该值。
 * 5. 对于获取随机元素操作，我们只需要随机生成一个索引，然后从数组中获取该索引处的元素即可。
 *
 * 时间复杂度分析：
 * - 插入操作和获取随机元素操作的时间复杂度均为 O(1)，因为哈希表和动态数组支持常数时间的插入和随机访问。
 * - 删除操作的时间复杂度也是 O(1)，因为哈希表和动态数组的删除操作也是常数时间的。
 *
 * 空间复杂度分析：
 * - 哈希表 `dict` 和动态数组 `list` 都存储了 N 个元素，因此空间复杂度为 O(N)。
 */


public class LeetCode_380_InsertDeleteGetrandomO1{

    //leetcode submit region begin(Prohibit modification and deletion)
    class RandomizedSet {
        Map<Integer, Integer> dict; // 用于存储值到索引的映射
        List<Integer> list; // 存储实际的元素列表
        Random rand = new Random(); // 随机数生成器

        /** Initialize your data structure here. */
        public RandomizedSet() {
            dict = new HashMap(); // 初始化字典
            list = new ArrayList(); // 初始化列表
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (dict.containsKey(val)) return false; // 如果值已存在于字典中，则返回 false

            dict.put(val, list.size()); // 在字典中添加值到索引的映射
            list.add(list.size(), val); // 将值添加到列表的末尾
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (! dict.containsKey(val)) return false; // 如果值不存在于字典中，则返回 false

            // 将最后一个元素移动到要删除的元素的位置
            int lastElement = list.get(list.size() - 1);
            int idx = dict.get(val);
            list.set(idx, lastElement); // 将最后一个元素替换到删除元素的位置
            dict.put(lastElement, idx); // 更新最后一个元素的索引映射
            list.remove(list.size() - 1); // 删除列表中的最后一个元素
            dict.remove(val); // 删除字典中的值
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(rand.nextInt(list.size())); // 从列表中随机获取一个元素并返回
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        RandomizedSet randomizedSet = new LeetCode_380_InsertDeleteGetrandomO1().new RandomizedSet();
        // 测试代码
        // 添加元素
        System.out.println(randomizedSet.insert(1)); // 返回 true
        System.out.println(randomizedSet.insert(2)); // 返回 true
        System.out.println(randomizedSet.insert(3)); // 返回 true
        // 删除元素
        System.out.println(randomizedSet.remove(2)); // 返回 true
        // 获取随机元素
        System.out.println(randomizedSet.getRandom()); // 返回 1 或 3，以相等的概率
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
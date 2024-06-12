package leetcode.question.map_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *@Question:  381. Insert Delete GetRandom O(1) - Duplicates allowed
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 54.94%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * 这道题目是要求设计一个数据结构，支持在常数时间内插入、删除和获取随机元素，并且允许集合中有重复的元素。我们可以使用哈希表和动态数组来实现这个数据结构。
 *
 * 解题思路：
 * 1. 使用动态数组 `lst` 来存储实际的元素，以支持常数时间的随机访问。
 * 2. 使用哈希表 `idx` 来存储值到索引的映射，其中值是集合中的元素，索引是该元素在动态数组中的位置。为了允许集合中有重复的元素，我们将索引存储在一个集合中，而不是一个单独的整数。
 * 3. 对于插入操作，我们首先检查值是否已经存在于哈希表中，如果不存在，则将其映射到一个新的集合中，并将值添加到动态数组的末尾。
 * 4. 对于删除操作，我们首先检查值是否存在于哈希表中，如果不存在或对应集合为空，则返回 false；否则，我们从集合中移除一个索引，然后将动态数组的最后一个元素移动到要删除的位置，最后删除动态数组中的最后一个元素。
 * 5. 对于获取随机元素操作，我们只需要随机生成一个索引，然后从动态数组中获取该索引处的元素即可。
 *
 * 时间复杂度分析：
 * - 插入操作的时间复杂度为 O(1)，因为哈希表的插入操作是常数时间的，而动态数组的添加操作也是常数时间的。
 * - 删除操作的时间复杂度为 O(1)，因为哈希表的删除操作和动态数组的删除操作都是常数时间的。
 * - 获取随机元素操作的时间复杂度为 O(1)，因为动态数组支持常数时间的随机访问。
 *
 * 空间复杂度分析：
 * - 哈希表 `idx` 存储了 N 个元素的值到索引的映射，因此空间复杂度为 O(N)。
 * - 动态数组 `lst` 存储了 N 个元素，因此空间复杂度为 O(N)。
 * - 因此，总的空间复杂度为 O(N)。
 */



public class LeetCode_381_InsertDeleteGetrandomO1DuplicatesAllowed{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class RandomizedCollection {
        ArrayList<Integer> lst; // 存储元素的列表
        HashMap<Integer, Set<Integer>> idx; // 存储值到索引的映射
        java.util.Random rand = new java.util.Random(); // 随机数生成器
        /** Initialize your data structure here. */

        public RandomizedCollection() {
            lst = new ArrayList<Integer>(); // 初始化列表
            idx = new HashMap<Integer, Set<Integer>>(); // 初始化字典
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            if (!idx.containsKey(val)) idx.put(val, new LinkedHashSet<Integer>()); // 如果值不在字典中，则将其映射到一个新的 LinkedHashSet
            idx.get(val).add(lst.size()); // 将索引添加到值对应的集合中
            lst.add(val); // 将值添加到列表末尾
            return idx.get(val).size() == 1; // 如果集合中元素数量为 1，说明值是新插入的，返回 true
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if (!idx.containsKey(val) || idx.get(val).size() == 0) return false; // 如果值不在字典中或对应集合为空，则返回 false
            int remove_idx = idx.get(val).iterator().next(); // 获取要删除的元素的索引
            idx.get(val).remove(remove_idx); // 从对应集合中删除索引
            int last = lst.get(lst.size() - 1); // 获取列表中的最后一个元素
            lst.set(remove_idx, last); // 将列表中最后一个元素移动到要删除的位置
            idx.get(last).add(remove_idx); // 更新最后一个元素的索引集合
            idx.get(last).remove(lst.size() - 1); // 删除最后一个元素的原索引
            lst.remove(lst.size() - 1); // 删除列表中的最后一个元素
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return lst.get(rand.nextInt(lst.size())); // 从列表中随机获取一个元素并返回
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        interview.company.amazon.LeetCode_381_InsertDeleteGetrandomO1DuplicatesAllowed.RandomizedCollection randomizedCollection = new interview.company.amazon.LeetCode_381_InsertDeleteGetrandomO1DuplicatesAllowed().new RandomizedCollection();
        // 测试代码
        // 添加元素
        System.out.println(randomizedCollection.insert(1)); // 返回 true
        System.out.println(randomizedCollection.insert(1)); // 返回 false
        System.out.println(randomizedCollection.insert(2)); // 返回 true
        // 删除元素
        System.out.println(randomizedCollection.remove(1)); // 返回 true
        // 获取随机元素
        System.out.println(randomizedCollection.getRandom()); // 返回 1 或 2，以相等的概率
    }
}

/**
 RandomizedCollection is a data structure that contains a collection of numbers,
 possibly duplicates (i.e., a multiset). It should support inserting and
 removing specific elements and also reporting a random element.

 Implement the RandomizedCollection class:


 RandomizedCollection() Initializes the empty RandomizedCollection object.
 bool insert(int val) Inserts an item val into the multiset, even if the item
 is already present. Returns true if the item is not present, false otherwise.
 bool remove(int val) Removes an item val from the multiset if present. Returns
 true if the item is present, false otherwise. Note that if val has multiple
 occurrences in the multiset, we only remove one of them.
 int getRandom() Returns a random element from the current multiset of elements.
 The probability of each element being returned is linearly related to the
 number of the same values the multiset contains.


 You must implement the functions of the class such that each function works on
 average O(1) time complexity.

 Note: The test cases are generated such that getRandom will only be called if
 there is at least one item in the RandomizedCollection.


 Example 1:


 Input
 ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove",
 "getRandom"]
 [[], [1], [1], [2], [], [1], []]
 Output
 [null, true, false, true, 2, true, 1]

 Explanation
 RandomizedCollection randomizedCollection = new RandomizedCollection();
 randomizedCollection.insert(1);   // return true since the collection does not
 contain 1.
 // Inserts 1 into the collection.
 randomizedCollection.insert(1);   // return false since the collection contains
 1.
 // Inserts another 1 into the collection.
 Collection now contains [1,1].
 randomizedCollection.insert(2);   // return true since the collection does not
 contain 2.
 // Inserts 2 into the collection. Collection
 now contains [1,1,2].
 randomizedCollection.getRandom(); // getRandom should:
 // - return 1 with probability 2/3, or
 // - return 2 with probability 1/3.
 randomizedCollection.remove(1);   // return true since the collection contains 1
 .
 // Removes 1 from the collection. Collection
 now contains [1,2].
 randomizedCollection.getRandom(); // getRandom should return 1 or 2, both
 equally likely.



 Constraints:


 -2³¹ <= val <= 2³¹ - 1
 At most 2 * 10⁵ calls in total will be made to insert, remove, and getRandom.
 There will be at least one element in the data structure when getRandom is
 called.


 Related Topics Array Hash Table Math Design Randomized 👍 2266 👎 149

 */
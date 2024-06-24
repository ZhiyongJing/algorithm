package leetcode.question.tree_map;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
/**
 *@Question:  981. Time Based Key-Value Store
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 73.56%
 *@Time  Complexity:
 * Solution1: O(L * M * logM) for set, O(N * (L * logM + logM) for get
 * Solution2: O(M * L) for set, O(N * L * logM) for get
 *@Space Complexity: O(M * L) for set, O(1) for get
 */

/**
 * 题目要求实现一个支持设置键值对、存储时间戳和获取在给定时间戳之前或等于该时间戳的最大值的键值存储系统。这里我们使用了两种不同的实现方式来解决问题。
 *
 * ### 解题思路
 *
 * #### Solution 1: 使用 TreeMap 实现
 *
 * 1. **数据结构选择**：
 *    - 使用 `HashMap<String, TreeMap<Integer, String>>`，其中 `String` 是键，`TreeMap<Integer, String>` 是存储时间戳和值的有序映射。
 *
 * 2. **设置键值对 (`set` 方法)**：
 *    - 如果键不存在于 `HashMap` 中，则创建一个新的 `TreeMap`。
 *    - 将 `(timestamp, value)` 存储在对应键的 `TreeMap` 中。`TreeMap` 会自动根据时间戳排序。
 *
 * 3. **获取键值 (`get` 方法)**：
 *    - 如果键不存在于 `HashMap` 中，直接返回空字符串。
 *    - 使用 `floorKey(timestamp)` 方法从 `TreeMap` 中获取小于等于给定时间戳的最大键。
 *    - 如果找到对应的键，返回其对应的值；否则返回空字符串。
 *
 * #### Solution 2: 使用数组 + 二分查找
 *
 * 1. **数据结构选择**：
 *    - 使用 `HashMap<String, ArrayList<Pair<Integer, String>>>`，其中 `String` 是键，`ArrayList<Pair<Integer, String>>` 存储时间戳和值的列表。
 *
 * 2. **设置键值对 (`set` 方法)**：
 *    - 如果键不存在于 `HashMap` 中，则创建一个新的 `ArrayList`。
 *    - 将 `(timestamp, value)` 存储在对应键的 `ArrayList` 中。
 *
 * 3. **获取键值 (`get` 方法)**：
 *    - 如果键不存在于 `HashMap` 中，直接返回空字符串。
 *    - 使用二分查找在 `ArrayList` 中查找小于等于给定时间戳的最大时间戳对应的值。
 *
 * ### 时间复杂度分析
 *
 * - **Solution 1**:
 *   - `set` 方法的时间复杂度为 O(log n)，其中 n 是每个键的平均时间戳数量。
 *   - `get` 方法的时间复杂度为 O(log n)，其中 n 是每个键的平均时间戳数量。
 *
 * - **Solution 2**:
 *   - `set` 方法的时间复杂度为 O(1) 平摊，偶尔需要调整数组大小。
 *   - `get` 方法的时间复杂度为 O(log n)，其中 n 是每个键的平均时间戳数量。
 *
 * ### 空间复杂度分析
 *
 * - **Solution 1**:
 *   - 使用了 `HashMap<String, TreeMap<Integer, String>>`，空间复杂度为 O(n)，其中 n 是存储的键值对数量。
 *
 * - **Solution 2**:
 *   - 使用了 `HashMap<String, ArrayList<Pair<Integer, String>>>`，空间复杂度也为 O(n)，其中 n 是存储的键值对数量。
 *
 * ### 总结
 *
 * 两种解决方案都有效地解决了时间戳存储和检索的问题。`TreeMap` 的解决方案更为直观和简单，但可能在大数据量下效率略低；
 * 而数组 + 二分查找的方案在时间复杂度上表现更优，但需要额外的管理和维护逻辑。选择适合实际需求和数据规模的实现方式是关键。
 */

public class LeetCode_981_TimeBasedKeyValueStore{

    //leetcode submit region begin(Prohibit modification and deletion)
    class TimeMap {
        HashMap<String, TreeMap<Integer, String>> keyTimeMap;

        public TimeMap() {
            keyTimeMap = new HashMap<String, TreeMap<Integer, String>>();
        }

        // 设置键值对，并存储时间戳
        public void set(String key, String value, int timestamp) {
            if (!keyTimeMap.containsKey(key)) {
                keyTimeMap.put(key, new TreeMap<Integer, String>());
            }

            // 将 (timestamp, value) 存储在 key 对应的 TreeMap 中
            keyTimeMap.get(key).put(timestamp, value);
        }

        // 获取在给定时间戳之前或等于该时间戳的最大值
        public String get(String key, int timestamp) {
            // 如果 key 不存在，返回空字符串
            if (!keyTimeMap.containsKey(key)) {
                return "";
            }

            Integer floorKey = keyTimeMap.get(key).floorKey(timestamp);
            // 返回小于等于给定时间戳的最大值对应的值，如果不存在则返回空字符串
            if (floorKey != null) {
                return keyTimeMap.get(key).get(floorKey);
            }

            return "";
        }
    }

    //Solution2: array + binary search
    class TimeMap2 {
        HashMap<String, ArrayList<Pair<Integer, String>>> keyTimeMap;

        public TimeMap2() {
            keyTimeMap = new HashMap();
        }

        // 设置键值对，并存储时间戳
        public void set(String key, String value, int timestamp) {
            if (!keyTimeMap.containsKey(key)) {
                keyTimeMap.put(key, new ArrayList());
            }

            // 将 (timestamp, value) 存储在 key 对应的 ArrayList 中
            keyTimeMap.get(key).add(new Pair(timestamp, value));
        }

        // 获取在给定时间戳之前或等于该时间戳的最大值
        public String get(String key, int timestamp) {
            // 如果 key 不存在，返回空字符串
            if (!keyTimeMap.containsKey(key)) {
                return "";
            }

            if (timestamp < keyTimeMap.get(key).get(0).getKey()) {
                return "";
            }

            // 使用二分搜索查找小于等于给定时间戳的最大值对应的值
            int left = 0;
            int right = keyTimeMap.get(key).size();

            while (left < right) {
                int mid = (left + right) / 2;
                if (keyTimeMap.get(key).get(mid).getKey() <= timestamp) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // 如果 right 等于 0，表示没有小于等于给定时间戳的值存在
            if (right == 0) {
                return "";
            }

            // 返回小于等于给定时间戳的最大值对应的值
            return keyTimeMap.get(key).get(right - 1).getValue();
        }
    }

    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试示例
        TimeMap timeMap = new LeetCode_981_TimeBasedKeyValueStore().new TimeMap();
        timeMap.set("foo", "bar", 1); // 存储 ("foo", "bar") 在时间戳 1
        System.out.println(timeMap.get("foo", 1)); // 输出 "bar"
        System.out.println(timeMap.get("foo", 3)); // 输出 "bar"，因为在时间戳 3 之前的最大值是 1，对应的值是 "bar"
        timeMap.set("foo", "bar2", 4); // 更新 ("foo", "bar2") 在时间戳 4
        System.out.println(timeMap.get("foo", 4)); // 输出 "bar2"
        System.out.println(timeMap.get("foo", 5)); // 输出 "bar2"，因为在时间戳 5 之前的最大值是 4，对应的值是 "bar2"
    }
}

/**
Design a time-based key-value data structure that can store multiple values for 
the same key at different time stamps and retrieve the key's value at a certain 
timestamp. 

 Implement the TimeMap class: 

 
 TimeMap() Initializes the object of the data structure. 
 void set(String key, String value, int timestamp) Stores the key key with the 
value value at the given time timestamp. 
 String get(String key, int timestamp) Returns a value such that set was called 
previously, with timestamp_prev <= timestamp. If there are multiple such values,
 it returns the value associated with the largest timestamp_prev. If there are 
no values, it returns "". 
 

 
 Example 1: 

 
Input
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], 
["foo", 5]]
Output
[null, null, "bar", "bar", null, "bar2", "bar2"]

Explanation
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along 
with timestamp = 1.
timeMap.get("foo", 1);         // return "bar"
timeMap.get("foo", 3);         // return "bar", since there is no value 
corresponding to foo at timestamp 3 and timestamp 2, then the only value is at 
timestamp 1 is "bar".
timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along 
with timestamp = 4.
timeMap.get("foo", 4);         // return "bar2"
timeMap.get("foo", 5);         // return "bar2"
 

 
 Constraints: 

 
 1 <= key.length, value.length <= 100 
 key and value consist of lowercase English letters and digits. 
 1 <= timestamp <= 10⁷ 
 All the timestamps timestamp of set are strictly increasing. 
 At most 2 * 10⁵ calls will be made to set and get. 
 

 Related Topics Hash Table String Binary Search Design 👍 4752 👎 565

*/
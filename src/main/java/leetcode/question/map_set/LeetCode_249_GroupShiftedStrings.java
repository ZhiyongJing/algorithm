package leetcode.question.map_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Question:  249. Group Shifted Strings
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 74.0%
 *@Time Complexity: O(N * K) // N 是字符串数量，K 是字符串最大长度
 *@Space Complexity: O(N * K) // 需要存储所有字符串的哈希映射
 */
/**
 * 题目描述：
 * LeetCode 249. Group Shifted Strings
 *
 * 给定一组字符串 `strings`，要求将 **属于同一偏移模式** 的字符串分组。
 *
 * **偏移模式定义：**
 * - **两个字符串 `s1` 和 `s2` 是相同偏移模式**，当且仅当：
 *   - `s2` 是 `s1` 的 **每个字符都按相同位数平移** 所得。
 * - 例如：
 *   ```
 *   "abc" -> "bcd" -> "xyz" 属于同一组（字符偏移相同）
 *   "az" -> "ba" 属于同一组（字符偏移相同）
 *   ```
 *
 * **输入输出示例：**
 * ```
 * 输入: strings = ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
 * 输出: [["abc", "bcd", "xyz"], ["acef"], ["az", "ba"], ["a", "z"]]
 * ```
 *
 * ---
 *
 * **解题思路：**
 *
 * 1. **计算字符串的“偏移模式”**
 *    - 每个字符串的偏移模式可以表示为：
 *      - `s[i] - s[i-1]`（按 `26` 取模以处理循环）。
 *    - 例如：
 *      ```
 *      "abc" -> "bcd" -> "xyz" 拥有相同的偏移模式 "bb"
 *      "az" -> "ba" 拥有相同的偏移模式 "y"
 *      ```
 *
 * 2. **使用 `HashMap` 进行分组**
 *    - 以偏移模式为 `key`，将所有具有相同偏移模式的字符串存入 `HashMap`。
 *    - 遍历 `strings` 数组：
 *      - 计算当前字符串的 `hashKey`（偏移模式）。
 *      - 如果 `hashKey` **不存在**，则创建一个新的列表。
 *      - 将当前字符串添加到对应 `hashKey` 的列表中。
 *
 * 3. **收集结果**
 *    - 遍历 `HashMap`，将所有分组的字符串添加到结果列表。
 *
 * ---
 * **示例解析**
 *
 * **输入：**
 * ```
 * ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
 * ```
 * **步骤 1：计算偏移模式**
 * ```
 * "abc" -> "bb"
 * "bcd" -> "bb"
 * "xyz" -> "bb"
 * "acef" -> "be"
 * "az" -> "y"
 * "ba" -> "y"
 * "a" -> ""
 * "z" -> ""
 * ```
 * **步骤 2：分组**
 * ```
 * "bb" -> ["abc", "bcd", "xyz"]
 * "be" -> ["acef"]
 * "y" -> ["az", "ba"]
 * ""  -> ["a", "z"]
 * ```
 * **最终输出**
 * ```
 * [["abc", "bcd", "xyz"], ["acef"], ["az", "ba"], ["a", "z"]]
 * ```
 *
 * ---
 * **时间和空间复杂度分析**
 *
 * - **时间复杂度：O(N * K)**
 *   - `N` 是 `strings` 中的字符串数量，`K` 是字符串最大长度。
 *   - 计算 `hashKey` 需要 **O(K)**，遍历 `N` 个字符串总共 **O(N * K)**。
 *
 * - **空间复杂度：O(N * K)**
 *   - 需要 `O(N * K)` 存储 `HashMap` 和 `List` 结果集。
 */

public class LeetCode_249_GroupShiftedStrings{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 计算字符串的哈希值，表示其偏移模式
        String getHash(String s) {
            char[] chars = s.toCharArray();
            StringBuilder hashKey = new StringBuilder();

            // 遍历字符串，计算相邻字符的偏移量，并转换为唯一的哈希键
            for (int i = 1; i < chars.length; i++) {
                hashKey.append((char) ((chars[i] - chars[i - 1] + 26) % 26 + 'a'));
            }

            return hashKey.toString(); // 返回该字符串的哈希键
        }

        public List<List<String>> groupStrings(String[] strings) {
            // 使用 HashMap 存储相同哈希值的字符串
            Map<String, List<String>> mapHashToList = new HashMap<>();

            // 遍历字符串数组，计算哈希键，并将字符串归类到相同哈希键的组中
            for (String str : strings ) {
                String hashKey = getHash(str);
                // 如果该哈希键不存在，则初始化一个新的列表
                if (!mapHashToList.containsKey(hashKey)) {
                    mapHashToList.put(hashKey, new ArrayList<>());
                }
                // 将当前字符串加入对应的哈希键的列表中
                mapHashToList.get(hashKey).add(str);
            }
            System.out.println(mapHashToList);

            // 遍历哈希映射，收集所有分组
            List<List<String>> groups = new ArrayList<>();
            for (List<String> group : mapHashToList.values()) {
                groups.add(group);
            }

            return groups; // 返回所有分组后的字符串列表
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_249_GroupShiftedStrings().new Solution();

        // 测试用例 1
        String[] input1 = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        System.out.println("分组结果: " + solution.groupStrings(input1));
        // 预期输出: [["abc","bcd","xyz"],["acef"],["az","ba"],["a","z"]]

        // 测试用例 2
        String[] input2 = {"a", "b", "c"};
        System.out.println("分组结果: " + solution.groupStrings(input2));
        // 预期输出: [["a", "b", "c"]]

        // 测试用例 3
        String[] input3 = {"abc", "bcd", "cde", "xyz", "yza", "zab"};
        System.out.println("分组结果: " + solution.groupStrings(input3));
        // 预期输出: [["abc", "bcd", "cde", "xyz", "yza", "zab"]]
    }
}

/**
Perform the following shift operations on a string: 

 
 Right shift: Replace every letter with the successive letter of the English 
alphabet, where 'z' is replaced by 'a'. For example, "abc" can be right-shifted to 
"bcd" or "xyz" can be right-shifted to "yza". 
 Left shift: Replace every letter with the preceding letter of the English 
alphabet, where 'a' is replaced by 'z'. For example, "bcd" can be left-shifted to 
"abc" or "yza" can be left-shifted to "xyz". 
 

 We can keep shifting the string in both directions to form an endless shifting 
sequence. 

 
 For example, shift "abc" to form the sequence: ... <-> "abc" <-> "bcd" <-> ... 
<-> "xyz" <-> "yza" <-> .... <-> "zab" <-> "abc" <-> ... 
 

 You are given an array of strings strings, group together all strings[i] that 
belong to the same shifting sequence. You may return the answer in any order. 

 
 Example 1: 

 
 Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"] 
 

 Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]] 

 Example 2: 

 
 Input: strings = ["a"] 
 

 Output: [["a"]] 

 
 Constraints: 

 
 1 <= strings.length <= 200 
 1 <= strings[i].length <= 50 
 strings[i] consists of lowercase English letters. 
 

 Related Topics Array Hash Table String 👍 1736 👎 424

*/
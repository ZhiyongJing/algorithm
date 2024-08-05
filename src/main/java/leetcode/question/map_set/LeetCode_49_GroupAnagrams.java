package leetcode.question.map_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Question:  49. Group Anagrams
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 90.25%
 *@Time  Complexity: O(N * K)  // N是字符串的数量，K是每个字符串的长度
 *@Space Complexity: O(N * K) // 用于存储所有的字符串分组
 */

/**
 * ### 题目：
 * **LeetCode 49. Group Anagrams**
 * 给定一个字符串数组 `strs`，将所有的异构词（即由相同字符组成的不同字符串）分组在一起。你需要返回一个包含所有分组的列表。
 *
 * ### 解题思路：
 *
 * 1. **问题分析**：
 *    - 异构词是由相同的字符组成的，但字符的顺序不同。例如，“eat”和“tea”是异构词，因为它们由相同的字符组成。
 *    - 要将异构词分组，可以通过某种方法来统一标识这些异构词。可以将每个字符串转换为一个独特的“特征”表示形式，以便将异构词归为一组。
 *
 * 2. **使用字符频率作为标识**：
 *    - **字符频率**：我们可以用一个长度为26的数组来记录每个字符出现的次数（假设只处理小写字母）。例如，对于字符串“eat”，
 *    字符频率表示为 `[1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]`，其中索引表示字符‘a’到‘z’的出现次数。
 *    - **唯一标识**：将字符频率数组转换为一个唯一的字符串（可以使用字符的频率作为键）作为异构词的标识。这样，相同的异构词会有相同的标识。
 *
 * 3. **使用 `HashMap` 进行分组**：
 *    - **数据结构**：使用一个 `HashMap`，其中键是字符频率表示（唯一标识），值是具有相同标识的字符串列表。
 *    - **遍历字符串数组**：对于数组中的每个字符串，计算其字符频率表示，然后将字符串添加到对应的列表中（在 `HashMap` 中存储）。
 *
 * 4. **生成结果**：
 *    - **结果转换**：将 `HashMap` 中的所有值（即各个分组的字符串列表）提取出来，作为最终的结果返回。
 *
 * ### 时间复杂度：
 * - **O(N * K)**，其中 `N` 是字符串数组的长度，`K` 是每个字符串的最大长度。对于每个字符串，我们需要计算字符频率，这个操作的时间复杂度是 `O(K)`，总共处理 `N` 个字符串。
 *
 * ### 空间复杂度：
 * - **O(N * K)**，主要由以下部分组成：
 *   - 存储每个字符串及其对应的分组需要 `O(N * K)` 的空间。
 *   - `HashMap` 中存储的字符频率表示的唯一标识也需要 `O(N * K)` 的空间。
 *
 * 总体来说，这种方法能够高效地对异构词进行分组，并且利用了字符频率作为唯一标识来区分不同的异构词组。
 */

public class LeetCode_49_GroupAnagrams {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            // 如果字符串数组为空，则返回一个空的列表
            if (strs.length == 0) return new ArrayList<>();

            // 使用HashMap来存储结果，其中键是字符串的排序后的字符组成的唯一标识，值是对应的字符串列表
            Map<String, List<String>> ans = new HashMap<>();

            // 创建一个长度为26的数组用于记录每个字符出现的次数（假设只处理小写字母）
            int[] count = new int[26];

            // 遍历所有的字符串
            for (String s : strs) {
                // 重置计数器
                Arrays.fill(count, 0);

                // 统计当前字符串每个字符出现的次数
                for (char c : s.toCharArray()) {
                    count[c - 'a']++;
                }

                // 使用StringBuilder构建当前字符串的唯一标识（字符频率字符串）
                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i < 26; i++) {
                    sb.append('#'); // 分隔符
                    sb.append(count[i]); // 频率
                }

                // 获取当前字符串的唯一标识
                String key = sb.toString();

                // 如果HashMap中不包含这个唯一标识，则添加一个新的列表
                if (!ans.containsKey(key)) {
                    ans.put(key, new ArrayList<>());
                }

                // 将当前字符串添加到对应的列表中
                ans.get(key).add(s);
            }

            // 返回所有分组后的列表
            return new ArrayList<>(ans.values());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_49_GroupAnagrams().new Solution();

        // 测试样例1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams(strs1)); // 输出: [[eat, tea, ate], [tan, nat], [bat]]

        // 测试样例2
        String[] strs2 = {""};
        System.out.println(solution.groupAnagrams(strs2)); // 输出: [[]]

        // 测试样例3
        String[] strs3 = {"a"};
        System.out.println(solution.groupAnagrams(strs3)); // 输出: [[a]]
    }
}

/**
Given an array of strings strs, group the anagrams together. You can return the 
answer in any order. 

 An Anagram is a word or phrase formed by rearranging the letters of a 
different word or phrase, typically using all the original letters exactly once. 

 
 Example 1: 
 Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 
 Example 2: 
 Input: strs = [""]
Output: [[""]]
 
 Example 3: 
 Input: strs = ["a"]
Output: [["a"]]
 
 
 Constraints: 

 
 1 <= strs.length <= 10⁴ 
 0 <= strs[i].length <= 100 
 strs[i] consists of lowercase English letters. 
 

 Related Topics Array Hash Table String Sorting 👍 19299 👎 629

*/
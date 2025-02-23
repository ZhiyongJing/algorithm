package leetcode.question.map_set;
import java.util.*;

/**
 *@Question:  451. Sort Characters By Frequency
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 65.69%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 题目描述：
 * 451. Sort Characters By Frequency
 * 给定一个字符串 s，要求按照字符出现的频率进行排序，并返回排序后的字符串。
 * 如果有多个字符的频率相同，则可以任意顺序排列它们。
 *
 * 示例 1:
 * 输入: "tree"
 * 输出: "eert" 或 "eetr"（'e' 出现两次，'r' 和 't' 各一次）
 *
 * 示例 2:
 * 输入: "cccaaa"
 * 输出: "cccaaa" 或 "aaaccc"（'c' 和 'a' 各出现三次，可以交换顺序）
 *
 * 示例 3:
 * 输入: "Aabb"
 * 输出: "bbAa" 或 "bbaA"（'b' 出现两次，'A' 和 'a' 各一次，区分大小写）
 *
 * 解题思路：
 * 1. **统计字符出现频率**：
 *    - 遍历字符串 s，使用 HashMap 记录每个字符出现的次数。
 *    - 例如："tree"，统计结果为：{'t':1, 'r':1, 'e':2}。
 *
 * 2. **确定最大出现次数**：
 *    - 通过 `Collections.max(counts.values())` 找到字符串中字符的最大频率。
 *    - 例如："cccaaa"，最大频率是 3（'c' 和 'a'）。
 *
 * 3. **使用桶排序进行排序**：
 *    - 创建一个桶数组 `buckets`，索引 i 代表字符出现 i 次的所有字符。
 *    - 遍历 HashMap，将字符存入对应的桶。
 *    - 例如："tree"，最大频率是 2，因此创建 `[[], ['t', 'r'], ['e']]`。
 *
 * 4. **按频率从高到低构建字符串**：
 *    - 从最大索引的桶开始，将每个字符重复添加到结果字符串中。
 *    - 例如："tree" 变成 "eert" 或 "eetr"（'e' 先加入 2 次，'r' 和 't' 各加入 1 次）。
 *
 * 5. **返回最终结果**：
 *    - 由于使用的是桶排序，确保按频率降序排列字符。
 *
 * 时间复杂度分析：
 * - 统计字符频率需要 O(N)。
 * - 找到最大频率需要 O(N)。
 * - 遍历 HashMap 填充桶需要 O(N)。
 * - 构建最终字符串需要 O(N)。
 * - 总体时间复杂度为 **O(N)**。
 *
 * 空间复杂度分析：
 * - HashMap 存储字符频率，最多 O(N)。
 * - 桶数组大小不会超过 O(N)。
 * - 结果字符串最多 O(N)。
 * - 总体空间复杂度为 **O(N)**。
 */


public class LeetCode_451_SortCharactersByFrequency{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String frequencySort(String s) {

            // 如果输入字符串为空或null，则直接返回原字符串
            if (s == null || s.isEmpty()) return s;

            // 统计字符出现的频率
            Map<Character, Integer> counts = new HashMap<>();
            for (char c : s.toCharArray()) {
                // 使用 HashMap 统计每个字符的出现次数
                counts.put(c, counts.getOrDefault(c, 0) + 1);
            }

            // 找到字符出现的最大频率
            int maximumFrequency = Collections.max(counts.values());

            // 创建桶排序的桶，每个索引 i 存储所有出现次数为 i 的字符
            List<List<Character>> buckets = new ArrayList<>();
            for (int i = 0; i <= maximumFrequency; i++) {
                buckets.add(new ArrayList<Character>());
            }

            // 将字符按照出现频率放入对应的桶中
            for (Character key : counts.keySet()) {
                int freq = counts.get(key); // 获取当前字符的出现次数
                buckets.get(freq).add(key); // 将字符存入对应频率的桶
            }

            // 通过桶排序构建最终的排序字符串
            StringBuilder sb = new StringBuilder();
            // 从频率最大的桶开始遍历
            for (int i = buckets.size() - 1; i >= 1; i--) {
                for (Character c : buckets.get(i)) {
                    // 将字符按其出现次数重复添加到结果字符串
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
            return sb.toString(); // 返回最终的排序字符串
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_451_SortCharactersByFrequency().new Solution();

        // 测试样例
        String test1 = "tree";
        System.out.println(solution.frequencySort(test1)); // 预期输出: "eert" 或 "eetr"

        String test2 = "cccaaa";
        System.out.println(solution.frequencySort(test2)); // 预期输出: "aaaccc" 或 "cccaaa"

        String test3 = "Aabb";
        System.out.println(solution.frequencySort(test3)); // 预期输出: "bbAa" 或 "bbaA"
    }
}

/**
Given a string s, sort it in decreasing order based on the frequency of the 
characters. The frequency of a character is the number of times it appears in the 
string. 

 Return the sorted string. If there are multiple answers, return any of them. 

 
 Example 1: 

 
Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid 
answer.
 

 Example 2: 

 
Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" 
are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.
 

 Example 3: 

 
Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 

 
 Constraints: 

 
 1 <= s.length <= 5 * 10⁵ 
 s consists of uppercase and lowercase English letters and digits. 
 

 Related Topics Hash Table String Sorting Heap (Priority Queue) Bucket Sort 
Counting 👍 8575 👎 305

*/
package leetcode.question.string_list;
/**
 *@Question:  242. Valid Anagram
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 73.06%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路：
 *
 * 1. **长度检查**：
 *    - 首先，比较两个字符串 `s` 和 `t` 的长度。如果长度不同，那么它们不可能是异位词，因此可以立即返回 `false`。
 *
 * 2. **字符频率统计**：
 *    - 使用一个长度为 26 的数组 `table` 来记录每个字符在字符串 `s` 和 `t` 中的出现次数。
 *    数组的索引表示字母在字母表中的位置，例如，字母 'a' 的索引是 0，字母 'b' 的索引是 1，以此类推。
 *    - 遍历第一个字符串 `s`，对于每个字符，增加 `table` 中对应索引的位置的计数。
 *    - 接着，遍历第二个字符串 `t`，对于每个字符，减少 `table` 中对应索引的位置的计数。
 *
 * 3. **验证异位词**：
 *    - 如果在遍历 `t` 的过程中，发现 `table` 中某个位置的计数变为负数，说明 `t` 中有比 `s` 多的字符，因此返回 `false`。
 *    - 如果遍历结束后，`table` 中所有位置的计数都为 0，说明 `t` 是 `s` 的异位词，返回 `true`。
 *
 * ### 时间和空间复杂度分析：
 *
 * - **时间复杂度**：O(N)
 *   - 其中 N 是字符串的长度。因为我们需要遍历两个字符串各一次，所以总的时间复杂度是 O(N)。
 *
 * - **空间复杂度**：O(1)
 *   - 由于我们使用了一个长度固定的数组 `table`（大小为 26），用于记录每个字母的频率，因此空间复杂度是常数级的 O(1)。
 */

public class LeetCode_242_ValidAnagram {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 判断两个字符串是否是有效的字母异位词。
         *
         * @param s 第一个字符串
         * @param t 第二个字符串
         * @return 如果两个字符串是有效的字母异位词，则返回 true；否则返回 false
         */
        public boolean isAnagram(String s, String t) {
            // 如果两个字符串长度不同，则不可能是异位词
            if (s.length() != t.length()) {
                return false;
            }

            // 创建一个长度为26的数组来统计每个字母的出现次数
            int[] table = new int[26];

            // 遍历第一个字符串，统计每个字母的出现次数
            for (int i = 0; i < s.length(); i++) {
                // 字母 'a' 的 ASCII 码为97，减去 'a' 的 ASCII 码值来计算当前字母在数组中的索引
                table[s.charAt(i) - 'a']++;
            }

            // 遍历第二个字符串，减少相应字母的计数
            for (int i = 0; i < t.length(); i++) {
                // 字母 'a' 的 ASCII 码为97，减去 'a' 的 ASCII 码值来计算当前字母在数组中的索引
                table[t.charAt(i) - 'a']--;

                // 如果数组中的某个位置的计数变为负数，则说明第二个字符串中出现了不在第一个字符串中的字母
                if (table[t.charAt(i) - 'a'] < 0) {
                    return false;
                }
            }

            // 如果没有发现计数为负数的情况，则两个字符串是有效的字母异位词
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_242_ValidAnagram().new Solution();

        // 测试示例 1
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println(solution.isAnagram(s1, t1)); // 应该输出 true

        // 测试示例 2
        String s2 = "rat";
        String t2 = "car";
        System.out.println(solution.isAnagram(s2, t2)); // 应该输出 false

        // 测试示例 3
        String s3 = "listen";
        String t3 = "silent";
        System.out.println(solution.isAnagram(s3, t3)); // 应该输出 true

        // 测试示例 4
        String s4 = "hello";
        String t4 = "world";
        System.out.println(solution.isAnagram(s4, t4)); // 应该输出 false
    }
}

/**
Given two strings s and t, return true if t is an anagram of s, and false 
otherwise. 

 An Anagram is a word or phrase formed by rearranging the letters of a 
different word or phrase, typically using all the original letters exactly once. 

 
 Example 1: 
 Input: s = "anagram", t = "nagaram"
Output: true
 
 Example 2: 
 Input: s = "rat", t = "car"
Output: false
 
 
 Constraints: 

 
 1 <= s.length, t.length <= 5 * 10⁴ 
 s and t consist of lowercase English letters. 
 

 
 Follow up: What if the inputs contain Unicode characters? How would you adapt 
your solution to such a case? 

 Related Topics Hash Table String Sorting 👍 12179 👎 404

*/
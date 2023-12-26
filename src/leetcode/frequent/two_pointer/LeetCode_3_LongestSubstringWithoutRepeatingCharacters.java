package leetcode.frequent.two_pointer;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  3. Longest Substring Without Repeating Characters
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 96.61%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(K) k is size of set
 */

/**
 * **解题思路：**
 *
 * 这个问题要求找到一个字符串中最长的没有重复字符的子串。我们可以使用滑动窗口的方法来解决这个问题。
 *
 * 1. 我们使用两个指针 `left` 和 `right` 来构建一个滑动窗口，表示当前考虑的子串。
 * 2. 右指针 `right` 向右移动，每次移动都将字符加入到当前的子串中。
 * 3. 如果当前子串中没有重复字符，我们继续向右扩展窗口，同时更新最长子串的长度。
 * 4. 如果发现重复字符，我们收缩左指针 `left` 直到当前子串中没有重复字符为止，然后继续向右移动右指针。
 * 5. 重复以上步骤直到右指针到达字符串的末尾。
 *
 * **时间复杂度：**
 *
 * - 在最坏的情况下，每个字符被访问两次：一次通过右指针向右移动，一次通过左指针向右移动。因此，时间复杂度是 O(N)，其中 N 是字符串的长度。
 *
 * **空间复杂度：**
 *
 * - 使用了哈希表来存储字符出现的次数，哈希表的大小最多是字符集的大小，即 O(K)，其中 K 是字符集的大小。
 * 因此，空间复杂度是 O(K)。在英文字符集的情况下，K 最多是 26，所以可以认为空间复杂度是 O(1)。
 */

public class LeetCode_3_LongestSubstringWithoutRepeatingCharacters {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * 计算不含重复字符的最长子串的长度
         * @param s 输入字符串
         * @return 最长子串的长度
         */
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> chars = new HashMap();

            int left = 0;
            int right = 0;

            int res = 0;
            while (right < s.length()) {
                char r = s.charAt(right);
                chars.put(r, chars.getOrDefault(r, 0) + 1);

                // 如果发现重复字符，收缩左指针直到不再重复
                while (chars.get(r) > 1) {
                    char l = s.charAt(left);
                    chars.put(l, chars.get(l) - 1);
                    left++;
                }

                // 更新最长子串的长度
                res = Math.max(res, right - left + 1);

                right++;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_3_LongestSubstringWithoutRepeatingCharacters.Solution solution =
                new LeetCode_3_LongestSubstringWithoutRepeatingCharacters().new Solution();

        // 测试代码
        String input = "abcabcbb";
        int result = solution.lengthOfLongestSubstring(input);

        // 输出结果
        System.out.println("The length of the longest substring without repeating characters is: " + result);
        // 预期输出：3 ("abc" or "bca" or "cab")
    }
}

/**
Given a string s, find the length of the longest substring without repeating 
characters. 

 
 Example 1: 

 
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
 

 Example 2: 

 
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
 

 Example 3: 

 
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a 
substring.
 

 
 Constraints: 

 
 0 <= s.length <= 5 * 10⁴ 
 s consists of English letters, digits, symbols and spaces. 
 

 Related Topics Hash Table String Sliding Window 👍 38230 👎 1752

*/

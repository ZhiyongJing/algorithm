package leetcode.question.two_pointer;

/**
  *@Question:  424. Longest Repeating Character Replacement     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 71.84%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(M) m is total unique characters
 */

/**
 * **解题思路：**
 *
 * 这个问题要求找到一个字符串中，通过最多替换 k 次字符，可以得到的相同字母的最长子串长度。
 *
 * 我们可以使用滑动窗口的方法来解决这个问题：
 *
 * 1. 用两个指针 `start` 和 `end` 来表示滑动窗口，`start` 表示窗口的左边界，`end` 表示窗口的右边界。
 * 2. 用一个数组 `frequencyMap` 来记录窗口中每个字符的频率。
 * 3. 用 `maxFrequency` 记录窗口中出现次数最多的字符的频率。
 * 4. 每次向右移动 `end` 指针，更新 `frequencyMap` 和 `maxFrequency`。
 * 5. 如果窗口中字符数减去最大频率大于 `k`，说明当前窗口无法通过替换 k 次字符得到相同字母的子串，需要收缩左指针 `start`。
 * 6. 每次移动 `start` 指针时，更新对应字符的频率，直到窗口重新满足条件。
 * 7. 在每一步中，记录当前窗口的长度，最终得到最长的子串长度。
 *
 * **时间复杂度：**
 *
 * - 每个字符最多被访问两次，一次通过右指针向右移动，一次通过左指针向右移动。因此，时间复杂度是 O(N)，其中 N 是字符串的长度。
 *
 * **空间复杂度：**
 *
 * - 使用了数组 `frequencyMap` 来记录字符频率，数组的大小是固定的，是 26。因此，空间复杂度是 O(1)。
 */

public class LeetCode_424_LongestRepeatingCharacterReplacement {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算可以通过替换最多 k 次字符得到的相同字母的最长子串长度
         * @param s 输入字符串
         * @param k 最多替换的字符数
         * @return 最长子串的长度
         */
        public int characterReplacement(String s, int k) {
            int start = 0;
            int[] frequencyMap = new int[26];
            int maxFrequency = 0;
            int longestSubstringLength = 0;

            for (int end = 0; end < s.length(); end += 1) {
                // 计算当前字符的相对偏移
                int currentChar = s.charAt(end) - 'A';

                frequencyMap[currentChar] += 1;

                // 更新最大频率
                maxFrequency = Math.max(maxFrequency, frequencyMap[currentChar]);

                // 如果当前窗口无效，移动 start 指针向右
                Boolean isValid = (end + 1 - start - maxFrequency <= k);
                if (!isValid) {
                    // 计算移出窗口的字符的相对偏移
                    int outgoingChar = s.charAt(start) - 'A';

                    // 减少该字符的频率
                    frequencyMap[outgoingChar] -= 1;

                    // 向右移动 start 指针
                    start += 1;
                }

                // 窗口此时有效，记录窗口长度
                longestSubstringLength = end + 1 - start;
            }

            return longestSubstringLength;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_424_LongestRepeatingCharacterReplacement.Solution solution =
                new LeetCode_424_LongestRepeatingCharacterReplacement().new Solution();

        // 测试代码
        String input1 = "ABAB";
        int k1 = 2;
        int result1 = solution.characterReplacement(input1, k1);
        System.out.println("Example 1: " + result1);  // 预期输出: 4

        String input2 = "AABABBA";
        int k2 = 1;
        int result2 = solution.characterReplacement(input2, k2);
        System.out.println("Example 2: " + result2);  // 预期输出: 4
    }
}

/**
You are given a string s and an integer k. You can choose any character of the 
string and change it to any other uppercase English character. You can perform 
this operation at most k times. 

 Return the length of the longest substring containing the same letter you can 
get after performing the above operations. 

 
 Example 1: 

 
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
 

 Example 2: 

 
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too. 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s consists of only uppercase English letters. 
 0 <= k <= s.length 
 

 Related Topics Hash Table String Sliding Window 👍 9892 👎 445

*/

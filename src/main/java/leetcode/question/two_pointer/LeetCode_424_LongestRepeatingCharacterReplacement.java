package leetcode.question.two_pointer;

/**
  *@Question:  424. Longest Repeating Character Replacement     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 71.84%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(M) m is total unique characters
 */

/**
 * 题目描述：
 * 给定一个仅包含大写英文字母的字符串 `s`，以及一个整数 `k`，
 * 你可以选择字符串中的任意 `k` 个字符，将其替换为任何其他大写英文字母。
 *
 * 你的任务是：**找到最长的子串，使得该子串中的所有字符都相同**。
 *
 * 返回这个最长子串的长度。
 *
 * 示例：
 * 输入: s = "ABAB", k = 2
 * 输出: 4
 * 解释: 通过替换两个 'A' 为 'B'（或反之），整个字符串变为 "BBBB"，长度为 4。
 *
 * 输入: s = "AABABBA", k = 1
 * 输出: 4
 * 解释: 通过替换一个 'B' 为 'A'，最长的相同字符子串为 "AABA" 或 "ABAA"，长度为 4。
 */

/**
 * 解题思路：
 * 该问题可以使用**滑动窗口（双指针）**方法来高效求解，避免暴力解法的 O(N²) 时间复杂度。
 * 滑动窗口的核心思想是：
 * 1. 维护两个指针 `start` 和 `end`，它们分别表示窗口的左右边界，初始化都指向字符串起始位置。
 * 2. 维护一个数组 `frequencyMap`（大小为 26），用于存储窗口内各个字符的出现频率。
 * 3. `end` 指针向右移动，扩展窗口，使窗口内的元素和逐渐增大，并更新窗口内出现**最多的字符次数 `maxFrequency`**。
 * 4. 当窗口大小 `end - start + 1` **减去** `maxFrequency` **大于 k** 时，说明窗口已经无法通过 `k` 次替换变成一个纯字符的子串，
 *    需要**移动 `start` 指针**来缩小窗口，使其重新满足条件。
 * 5. 每次窗口满足条件时，更新最长子串的长度 `longestSubstringLength`。
 * 6. 遍历完整个字符串后，返回最长的有效窗口长度。
 *
 * **示例步骤解析：**
 * 示例输入：s = "AABABBA", k = 1
 *
 * 初始化：start = 0, end = 0, maxFrequency = 0, longestSubstringLength = 0
 *
 * - **Step 1**: end = 0，窗口 ["A"]，字符频率 {A:1}，maxFrequency = 1
 * - **Step 2**: end = 1，窗口 ["AA"]，字符频率 {A:2}，maxFrequency = 2
 * - **Step 3**: end = 2，窗口 ["AAB"]，字符频率 {A:2, B:1}，maxFrequency = 2
 * - **Step 4**: end = 3，窗口 ["AABA"]，字符频率 {A:3, B:1}，maxFrequency = 3
 * - **Step 5**: end = 4，窗口 ["AABAB"]，字符频率 {A:3, B:2}，maxFrequency = 3
 * - **Step 6**: end = 5，窗口 ["AABABB"]，字符频率 {A:3, B:3}，maxFrequency = 3
 *     - 由于 `窗口长度 (6) - maxFrequency (3) = 3 > k (1)`，窗口无效，start 右移
 *     - 窗口变成 ["ABABB"]，字符频率 {A:2, B:3}，maxFrequency = 3
 * - **Step 7**: end = 6，窗口 ["ABABBA"]，字符频率 {A:2, B:4}，maxFrequency = 4
 *     - 由于 `窗口长度 (5) - maxFrequency (4) = 1 <= k (1)`，窗口有效，更新 longestSubstringLength = 4。
 *
 * 最终返回 `longestSubstringLength = 4`。
 */

/**
 * 时间和空间复杂度分析：
 * - **时间复杂度：O(N)**
 *   - `end` 指针遍历整个字符串，每个字符最多被访问两次（一次进窗口，一次出窗口），所以时间复杂度为 `O(N)`。
 *   - 这种方法比暴力解法的 `O(N²)` **大大优化**。
 *
 * - **空间复杂度：O(1)**
 *   - 仅使用了一个固定大小的数组 `frequencyMap[26]` 来存储字符频率，因此额外空间为常数级 `O(1)`。
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
            // 定义滑动窗口的起始位置
            int start = 0;
            // 记录窗口内每个字符的出现频率，数组大小为 26（对应英文字母 A-Z）
            int[] frequencyMap = new int[26];
            // 记录窗口内出现频率最高的字符次数
            int maxFrequency = 0;
            // 记录满足条件的最长子串长度
            int longestSubstringLength = 0;

            // 遍历字符串，使用 `end` 指针扩展窗口
            for (int end = 0; end < s.length(); end += 1) {
                // 计算当前字符在 `A-Z` 中的相对偏移
                int currentChar = s.charAt(end) - 'A';

                // 更新当前字符在窗口内的出现次数
                frequencyMap[currentChar] += 1;

                // 更新窗口内的最大字符频率（即窗口内出现最多的某个字符的次数）
                maxFrequency = Math.max(maxFrequency, frequencyMap[currentChar]);

                // 判断当前窗口是否有效，即窗口长度 - 窗口内最多字符的次数 <= k
                Boolean isValid = (end + 1 - start - maxFrequency <= k);
                if (!isValid) {
                    // 计算即将移出窗口的字符的相对偏移
                    int outgoingChar = s.charAt(start) - 'A';

                    // 减少该字符的频率（因为 `start` 指针要向右移动）
                    frequencyMap[outgoingChar] -= 1;

                    // 向右移动 `start` 指针，缩小窗口
                    start += 1;
                }

                // 计算当前有效窗口的长度
                longestSubstringLength = end + 1 - start;
            }

            // 返回最长子串的长度
            return longestSubstringLength;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 创建 Solution 实例
        LeetCode_424_LongestRepeatingCharacterReplacement.Solution solution =
                new LeetCode_424_LongestRepeatingCharacterReplacement().new Solution();

        // 测试样例 1
        String input1 = "ABAB";
        int k1 = 2;
        int result1 = solution.characterReplacement(input1, k1);
        System.out.println("Example 1: " + result1);  // 预期输出: 4

        // 测试样例 2
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

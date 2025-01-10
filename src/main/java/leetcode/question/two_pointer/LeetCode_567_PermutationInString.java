package leetcode.question.two_pointer;

/**
 *@Question:  567. Permutation in String
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 70.74%
 *@Time Complexity: O(N), N is length of s2
 *@Space Complexity: O(1)
 */
/**
 * 第一部分：题目描述
 *
 * 给定两个字符串 s1 和 s2，判断 s2 是否包含 s1 的排列。
 * 换句话说，检查 s2 中是否存在一个子串，它的字符是 s1 字符的排列组合。
 *
 * 例如：
 * 输入: s1 = "ab", s2 = "eidbaooo"
 * 输出: true
 * 解释: s2 包含 s1 的排列 ("ba")
 *
 * 输入: s1 = "ab", s2 = "eidboaoo"
 * 输出: false
 *
 * 注意：两个字符串的字符排列顺序不重要，但每个字符出现的频率和种类需要匹配。
 *
 * 第二部分：解题思路
 *
 * 我们可以通过滑动窗口的技巧来解决这个问题。
 *
 * 1. 判断 s1 的长度是否大于 s2 的长度，如果是，直接返回 false。因为 s1 长度更大，无法在 s2 中形成排列。
 *    例如，s1 = "abc"，s2 = "ab" 时，s1 长度大于 s2，直接返回 false。
 *
 * 2. 创建两个长度为 26 的整数数组 `s1arr` 和 `s2arr`，分别用于记录 s1 和 s2 中每个字母的出现频率。
 *    `s1arr` 和 `s2arr` 都是长度为 26 的数组，表示每个字母出现的次数，字母 'a' 对应索引 0，'b' 对应索引 1，依此类推。
 *
 * 3. 初始化时，将 s1 和 s2 中前 `s1.length()` 个字符的频率填充到 `s1arr` 和 `s2arr` 中。
 *    举例：s1 = "ab"，s2 = "eidbaooo"。
 *    - `s1arr` 会记录 "ab" 中字符 'a' 和 'b' 的频率，分别是 1 和 1。
 *    - `s2arr` 会记录 s2 中前两个字符 'e' 和 'i' 的频率，分别是 1 和 1。
 *
 * 4. 使用滑动窗口遍历 s2，从下标 0 到 `s2.length() - s1.length()`。每次判断当前窗口内的字符频率是否与 s1 的频率数组匹配。
 *    - 比较 `s1arr` 和 `s2arr` 是否相同，如果相同，说明当前窗口是 s1 的排列，返回 true。
 *    - 如果不匹配，滑动窗口：移除左侧字符并加入右侧新字符，继续进行比较。
 *    例如：
 *    - s1 = "ab"，s2 = "eidbaooo"。
 *    - 第一次滑动窗口包含字符 "ei"，`s2arr` 与 `s1arr` 不匹配。
 *    - 第二次滑动窗口包含字符 "id"，`s2arr` 与 `s1arr` 不匹配。
 *    - 第三次滑动窗口包含字符 "ba"，此时 `s2arr` 与 `s1arr` 匹配，返回 true。
 *
 * 5. 如果遍历完所有可能的子串后仍然没有匹配，则返回 false。
 *    例如：s1 = "ab"，s2 = "eidboaoo"。经过滑动后，s2 中没有包含 s1 的排列，返回 false。
 *
 * 第三部分：时间复杂度与空间复杂度分析
 *
 * 时间复杂度：
 * - 填充 `s1arr` 和 `s2arr` 的时间复杂度为 O(N)，其中 N 是 s1 的长度。
 * - 滑动窗口的过程中，遍历 s2 中的每个字符，总共需要遍历 O(M - N + 1) 次，其中 M 是 s2 的长度，N 是 s1 的长度。每次窗口滑动时，都要比较两个频率数组，最多需要 O(26) 的时间。因此时间复杂度为 O(M)。
 * - 总体来说，时间复杂度为 O(N + M)，其中 N 是 s1 的长度，M 是 s2 的长度。
 *
 * 空间复杂度：
 * - 我们使用了两个长度为 26 的数组来存储字符的频率，这两个数组的大小是固定的，因此空间复杂度为 O(1)，不随输入字符串的长度变化。
 */


public class LeetCode_567_PermutationInString {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        // 主函数：检查字符串 s2 是否包含 s1 的排列
        public boolean checkInclusion(String s1, String s2) {
            // 如果 s1 的长度大于 s2 的长度，直接返回 false
            if (s1.length() > s2.length())
                return false;

            // 创建两个数组用于存储 s1 和 s2 中字符的频率
            int[] s1arr = new int[26];  // 用于记录 s1 中各字符的出现频率
            int[] s2arr = new int[26];  // 用于记录 s2 中各字符的出现频率

            // 填充 s1arr 和 s2arr，记录各自字符串中每个字符的出现次数
            for (int i = 0; i < s1.length(); i++) {
                s1arr[s1.charAt(i) - 'a']++;  // s1 字符的频率
                s2arr[s2.charAt(i) - 'a']++;  // s2 字符的频率
            }

            // 遍历 s2，逐步判断每个窗口是否是 s1 的排列
            for (int i = 0; i < s2.length() - s1.length(); i++) {
                // 判断当前窗口是否与 s1 的字符频率匹配
                if (matches(s1arr, s2arr))
                    return true;  // 如果匹配，则返回 true

                // 滑动窗口：移除左侧字符，加入右侧字符
                s2arr[s2.charAt(i + s1.length()) - 'a']++;  // 加入右侧字符
                s2arr[s2.charAt(i) - 'a']--;  // 移除左侧字符
            }

            // 最后检查剩余窗口是否与 s1 的字符频率匹配
            return matches(s1arr, s2arr);  // 如果匹配则返回 true，否则返回 false
        }

        // 辅助函数：比较两个频率数组是否相同
        public boolean matches(int[] s1arr, int[] s2arr) {
            for (int i = 0; i < 26; i++) {
                // 如果有任意一个字符的频率不同，返回 false
                if (s1arr[i] != s2arr[i])
                    return false;
            }
            // 如果所有字符频率相同，返回 true
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_567_PermutationInString().new Solution();

        // 测试样例 1：s2 包含 s1 的排列
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(solution.checkInclusion(s1, s2)); // 输出 true

        // 测试样例 2：s2 不包含 s1 的排列
        s1 = "ab";
        s2 = "eidboaoo";
        System.out.println(solution.checkInclusion(s1, s2)); // 输出 false

        // 测试样例 3：s1 大于 s2
        s1 = "abc";
        s2 = "ab";
        System.out.println(solution.checkInclusion(s1, s2)); // 输出 false
    }
}

/**
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or 
false otherwise. 

 In other words, return true if one of s1's permutations is the substring of s2.
 

 
 Example 1: 

 
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
 

 Example 2: 

 
Input: s1 = "ab", s2 = "eidboaoo"
Output: false
 

 
 Constraints: 

 
 1 <= s1.length, s2.length <= 10⁴ 
 s1 and s2 consist of lowercase English letters. 
 

 Related Topics Hash Table Two Pointers String Sliding Window 👍 12053 👎 466

*/
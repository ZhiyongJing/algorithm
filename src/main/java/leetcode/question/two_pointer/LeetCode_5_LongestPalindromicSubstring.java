package leetcode.question.two_pointer;

/**
 *@Question:  5. Longest Palindromic Substring
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 98.05%
 *@Time  Complexity: O(n^2) for solution1, O(n) for solution2
 *@Space Complexity: O(1) for solution1, O(n) for solution2
 */
/**
 * 题目描述：
 * 给定一个字符串 s，找到 s 中最长的回文子串。
 * 回文字符串指的是正着读和反着读都相同的字符串，例如 "aba"、"racecar"。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab" 或 "aba"
 * 解释：两个子串都是最长回文子串。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * 解释："bb" 是最长回文子串。
 *
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * 解释：只有一个字符，必然是最长回文子串。
 *
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a" 或 "c"
 * 解释：两个子串长度都为1，任意一个都可以作为答案。
 *
 * 约束：
 * - 1 <= s.length <= 1000
 * - s 仅由数字和英文字母组成。
 */

/**
 * 解题思路：
 *
 * 方案 1（中心扩展法）：
 * 1. 遍历字符串的每个字符，将其作为回文中心。
 * 2. 对于每个中心点，向两侧扩展，查找最大回文子串。
 * 3. 由于回文串可能是奇数或偶数长度，因此分别从 (i, i) 和 (i, i+1) 进行扩展。
 * 4. 记录最长的回文子串的起始索引和结束索引，最后从原字符串截取该子串返回。
 *
 * 举例：
 * 输入：s = "babad"
 * 1. 以 'b' 为中心扩展 -> "b"
 * 2. 以 'a' 为中心扩展 -> "bab"
 * 3. 以 'b' 为中心扩展 -> "aba"
 * 4. 以 'a' 为中心扩展 -> "a"
 * 5. 以 'd' 为中心扩展 -> "d"
 * 最长回文子串是 "bab" 或 "aba"。
 *
 * 方案 2（Manacher's Algorithm）：
 * 1. 预处理字符串，在每个字符之间插入特殊字符 '#’，以便统一处理奇偶长度的回文串。
 * 2. 维护一个回文半径数组 palindromeRadii[i]，表示以 i 为中心的回文子串的半径。
 * 3. 维护回文中心 center 和右边界 radius：
 *    - 如果 i 在当前回文半径内，则可以利用对称性减少计算。
 *    - 继续扩展以找到最大回文半径，并更新回文中心。
 * 4. 遍历找到最大的回文半径及其中心点，转换回原始字符串索引并返回子串。
 *
 * 举例：
 * 输入：s = "babad"
 * 1. 预处理："#b#a#b#a#d#"
 * 2. 计算回文半径：[0,1,0,3,0,1,0,3,0,1,0]
 * 3. 最大回文半径对应的中心点 -> 原始字符串索引 -> 得到 "aba" 或 "bab"。
 */

/**
 * 时间和空间复杂度：
 *
 * 方案 1（中心扩展法）：
 * - 时间复杂度：O(n^2)，遍历每个字符 O(n)，每次扩展最多 O(n)，整体 O(n^2)。
 * - 空间复杂度：O(1)，只使用了常数额外空间。
 *
 * 方案 2（Manacher’s Algorithm）：
 * - 时间复杂度：O(n)，每个字符最多计算一次，利用对称性减少计算。
 * - 空间复杂度：O(n)，存储转换后的字符串及回文半径数组。
 */
/**
 * 题目描述：
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。回文串是指从前往后和从后往前读取都相同的字符串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab" 或 "aba"
 * 解释：长度为 3 的回文子串 "bab" 或 "aba" 均符合要求。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * 解释：最长回文子串为 "bb"，长度为 2。
 *
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * 解释：单个字符本身就是回文串。
 *
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a" 或 "c"
 * 解释：两个字符均为回文子串，长度相同，返回任意一个均可。
 *
 * 约束：
 * - 1 <= s.length <= 1000
 * - s 仅由数字和英文字母组成。
 */

/**
 * 解题思路：
 *
 * 方案 1：中心扩展法
 * 1. 遍历字符串的每个字符，将其作为回文中心，尝试向左右扩展，找到最大回文子串。
 * 2. 由于回文串可能是奇数长度（单个字符为中心，如 "aba"）或偶数长度（两个字符为中心，如 "abba"），
 *    需要分别尝试以单个字符和两个字符为中心进行扩展。
 * 3. 维护一个变量存储当前找到的最长回文子串的起始索引和结束索引，每次扩展后检查是否更新。
 * 4. 遍历完成后，提取最长回文子串并返回。
 *
 * 例子：
 * 假设 s = "babad"，遍历到 i = 2 ('b' 在索引 2)：
 * - 以 "b" 为中心扩展："bab" 是最长回文。
 * - 以 "ba" 为中心扩展，无法形成回文。
 * - 继续遍历，最终最长回文子串可能是 "bab" 或 "aba"。
 *
 * 假设 s = "cbbd"，遍历到 i = 1 ('b' 在索引 1)：
 * - 以 "b" 为中心扩展："b" 是回文，但不是最长的。
 * - 以 "bb" 为中心扩展："bb" 是最长回文。
 *
 * 方案 2：Manacher 算法（线性时间复杂度）
 * 1. 通过在原字符串中插入特殊字符 "#"，确保所有回文子串都是奇数长度（如 "abc" -> "#a#b#c#"）。
 * 2. 维护一个数组 `palindromeRadii`，存储以每个字符为中心的回文半径。
 * 3. 维护两个变量：
 *    - `center`：当前最长回文子串的中心。
 *    - `radius`：当前最长回文子串的右边界。
 * 4. 遍历新字符串：
 *    - 计算当前字符在 `center` 的镜像位置 `mirror`，如果 `mirror` 位置的回文半径可利用，则复制其值。
 *    - 继续尝试扩展回文半径，更新 `center` 和 `radius`。
 * 5. 遍历完成后，找到最长回文子串并返回。
 *
 * 例子：
 * 假设 s = "babad"，处理后变成 `#b#a#b#a#d#`，回文半径数组为：
 * [0, 1, 0, 3, 0, 3, 0, 1, 0]
 * 其中，最大回文半径为 3，对应的子串是 "aba"。
 */

/**
 * 时间和空间复杂度：
 *
 * 方案 1（中心扩展法）：
 * - 时间复杂度：O(n^2)，因为每个字符都可能扩展 O(n) 次。
 * - 空间复杂度：O(1)，仅使用了常数额外空间。
 *
 * 方案 2（Manacher 算法）：
 * - 时间复杂度：O(n)，每个字符仅被访问一次。
 * - 空间复杂度：O(n)，用于存储新字符串和回文半径数组。
 */


public class LeetCode_5_LongestPalindromicSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 方法1：中心扩展法
         * 计算最长回文子串
         * @param s 输入字符串
         * @return 最长回文子串
         */
        public String longestPalindrome(String s) {
            // 用数组存储回文子串的起始索引和结束索引
            int[] ans = new int[]{0, 0};

            // 遍历字符串的每个字符，尝试作为回文中心
            for (int i = 0; i < s.length(); i++) {
                // 计算以 i 作为中心的奇数长度回文串的最大长度
                int oddLength = expand(i, i, s);
                if (oddLength > ans[1] - ans[0] + 1) {
                    int dist = oddLength / 2;
                    ans[0] = i - dist;
                    ans[1] = i + dist;
                }

                // 计算以 i 和 i+1 作为中心的偶数长度回文串的最大长度
                int evenLength = expand(i, i + 1, s);
                if (evenLength > ans[1] - ans[0] + 1) {
                    int dist = (evenLength / 2) - 1;
                    ans[0] = i - dist;
                    ans[1] = i + 1 + dist;
                }
            }

            // 提取最长回文子串
            int i = ans[0];
            int j = ans[1];
            return s.substring(i, j + 1);
        }

        /**
         * 辅助方法：从中心扩展查找最大回文长度
         * @param i 左指针
         * @param j 右指针
         * @param s 输入字符串
         * @return 回文子串的长度
         */
        private int expand(int i, int j, String s) {
            int left = i;
            int right = j;

            // 向两侧扩展，直到遇到不同的字符或越界
            while (
                    left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)
            ) {
                left--;
                right++;
            }

            // 计算回文子串长度
            return right - left - 1;
        }

        /**
         * 方法2：Manacher 算法
         * 使用 Manacher’s Algorithm 计算最长回文子串
         * @param s 输入字符串
         * @return 最长回文子串
         */
        public String longestPalindrome2(String s) {
            // step1: 预处理字符串，在每个字符之间插入 '#'
            StringBuilder sPrime = new StringBuilder("#");
            for (char c : s.toCharArray()) {
                sPrime.append(c).append("#");
            }

            // 获取新字符串的长度
            int n = sPrime.length();
            // 存储每个字符的回文半径
            int[] palindromeRadii = new int[n];
            // 记录当前的回文中心和右边界
            int center = 0;
            int radius = 0;

            // 遍历新字符串
            for (int i = 0; i < n; i++) {
                // 计算 i 位置的镜像位置
                int mirror = 2 * center - i;

                // 如果 i 在当前回文范围内，则初始化回文半径
                if (i < radius) {
                    palindromeRadii[i] = Math.min(radius - i, palindromeRadii[mirror]);
                }

                // 尝试扩展回文半径
                while (i + 1 + palindromeRadii[i] < n &&
                        i - 1 - palindromeRadii[i] >= 0 &&
                        sPrime.charAt(i + 1 + palindromeRadii[i]) == sPrime.charAt(i - 1 - palindromeRadii[i])) {
                    palindromeRadii[i]++;
                }

                // 更新回文中心和右边界
                if (i + palindromeRadii[i] > radius) {
                    center = i;
                    radius = i + palindromeRadii[i];
                }
            }

            // 寻找最大回文半径及其中心
            int maxLength = 0;
            int centerIndex = 0;
            for (int i = 0; i < n; i++) {
                if (palindromeRadii[i] > maxLength) {
                    maxLength = palindromeRadii[i];
                    centerIndex = i;
                }
            }

            // 计算原字符串的起始索引
            int startIndex = (centerIndex - maxLength) / 2;
            return s.substring(startIndex, startIndex + maxLength);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_5_LongestPalindromicSubstring().new Solution();

        // 测试代码
        String testString1 = "babad";
        String result1 = solution.longestPalindrome(testString1);
        System.out.println("测试字符串1的最长回文子串：" + result1); // 预期输出："bab" 或 "aba"

        String testString2 = "cbbd";
        String result2 = solution.longestPalindrome(testString2);
        System.out.println("测试字符串2的最长回文子串：" + result2); // 预期输出："bb"

        String testString3 = "a";
        String result3 = solution.longestPalindrome(testString3);
        System.out.println("测试字符串3的最长回文子串：" + result3); // 预期输出："a"

        String testString4 = "ac";
        String result4 = solution.longestPalindrome(testString4);
        System.out.println("测试字符串4的最长回文子串：" + result4); // 预期输出："a" 或 "c"
    }
}


/**
 Given a string s, return the longest palindromic substring in s.


 Example 1:


 Input: s = "babad"
 Output: "bab"
 Explanation: "aba" is also a valid answer.


 Example 2:


 Input: s = "cbbd"
 Output: "bb"



 Constraints:


 1 <= s.length <= 1000
 s consist of only digits and English letters.


 Related Topics String Dynamic Programming 👍 28249 👎 1679

 */

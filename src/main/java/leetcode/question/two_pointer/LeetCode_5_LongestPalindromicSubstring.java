package leetcode.question.two_pointer;

/**
 *@Question:  5. Longest Palindromic Substring
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 98.05%
 *@Time  Complexity: O(n)
 *@Space Complexity: O(n)
 */

/**
 * **解题思路：**
 *
 * 这道题使用 Manacher's Algorithm 来解决，该算法是用于寻找最长回文子串的线性时间算法。该算法的核心思想是通过利用已知回文子串的对称性质，
 * 减少重复的计算。
 *
 * 1. **插入特殊字符：** 在原字符串的每个字符之间插入一个特殊字符（这里选择 `#`），这样可以确保每个字符都有一个中心，
 * 且中心是唯一的。这一步的目的是统一回文子串的长度是奇数还是偶数的情况。
 *
 * 2. **遍历并计算回文半径：** 使用两个指针 `center` 和 `radius` 来维护当前已知的最右边界。遍历整个新字符串，
 * 以每个字符为中心，计算以该字符为中心的回文半径。
 *
 *    - 如果当前字符在已知回文子串的范围内，可以利用对称性质，避免重复计算，更新回文半径。
 *    - 如果当前字符不在已知回文子串的范围内，就以当前字符为中心，向两边扩展，同时更新已知的最右边界。
 *
 * 3. **寻找最长回文子串：** 遍历所有计算得到的回文半径，找到最大的回文半径及其对应的中心点。
 * 然后根据中心点和回文半径计算原字符串的起始索引，截取得到最长回文子串。
 *
 * **时间复杂度：**
 *
 * - 由于 Manacher's Algorithm 只需要线性时间，所以时间复杂度为 O(n)，其中 n 是字符串的长度。
 *
 * **空间复杂度：**
 *
 * - 使用了一个大小为 n 的数组 `palindromeRadii` 用于存储回文半径，因此空间复杂度为 O(n)。此外，
 * 使用了常数个变量，因此额外的空间复杂度为 O(1)。
 */

public class LeetCode_5_LongestPalindromicSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 寻找最长回文子串
         * @param s 输入字符串
         * @return 最长回文子串
         */
        public String longestPalindrome(String s) {
            //step1: 使用 Manacher's Algorithm，构造新字符串，插入特殊字符 '#'
            StringBuilder sPrime = new StringBuilder("#");
            for (char c: s.toCharArray()) {
                sPrime.append(c).append("#");
            }

            int n = sPrime.length();
            int[] palindromeRadii = new int[n];
            int center = 0;
            int radius = 0;

            for (int i = 0; i < n; i++) {
                int mirror = 2 * center - i;//  center - i + center

                if (i < radius) {
                    palindromeRadii[i] = Math.min(radius - i, palindromeRadii[mirror]);
                }

                while (i + 1 + palindromeRadii[i] < n &&
                        i - 1 - palindromeRadii[i] >= 0 &&
                        sPrime.charAt(i + 1 + palindromeRadii[i]) == sPrime.charAt(i - 1 - palindromeRadii[i])) {
                    palindromeRadii[i]++;
                }

                if (i + palindromeRadii[i] > radius) {
                    center = i;
                    radius = i + palindromeRadii[i];
                }
            }

            // 寻找最大回文半径及其对应的中心点
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
            // 截取最长回文子串
            String longestPalindrome = s.substring(startIndex, startIndex + maxLength);

            return longestPalindrome;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_5_LongestPalindromicSubstring().new Solution();

        // 测试代码
        String testString1 = "babad";
        String result1 = solution.longestPalindrome(testString1);
        System.out.println("测试字符串1的最长回文子串：" + result1);

        String testString2 = "cbbd";
        String result2 = solution.longestPalindrome(testString2);
        System.out.println("测试字符串2的最长回文子串：" + result2);
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

package leetcode.question.two_pointer;

/**
 *@Question:  5. Longest Palindromic Substring
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 98.05%
 *@Time  Complexity: O(n)
 *@Space Complexity: O(n)
 */
/**
 * ==============================
 * 题目描述：LeetCode 5 - Longest Palindromic Substring
 * ==============================
 * 给定一个字符串 `s`，请找出其中的**最长回文子串**。
 *
 * **回文子串的定义：**
 * - 回文字符串是指正着读和反着读都一样的字符串。
 * - 例如，`"aba"` 和 `"racecar"` 是回文字符串，而 `"hello"` 不是。
 *
 * **要求：**
 * - 你需要返回输入字符串中**最长的回文子串**。
 *
 * **输入/输出示例：**
 * - 输入：`s = "babad"`
 * - 输出：`"bab"` 或 `"aba"`（两者都是有效答案）。
 *
 * - 输入：`s = "cbbd"`
 * - 输出：`"bb"`。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * **核心思想：使用 Manacher's Algorithm 找到最长回文子串**
 *
 * - Manacher’s Algorithm 是一种优化算法，可以在 **O(n)** 的时间内找到一个字符串的最长回文子串。
 * - 通过在原字符串的每个字符之间插入一个特殊字符 `#`，我们可以将奇数长度和偶数长度的回文串统一处理。
 * - 然后使用一个回文半径数组 `palindromeRadii` 来存储每个字符为中心时回文串的半径长度。
 *
 * ------------------------------
 * **解法步骤：**
 * ------------------------------
 * **步骤1：构造新字符串 `sPrime`**
 * - 遍历原字符串 `s`，在每个字符前后插入 `#`，并在开头和结尾分别添加 `#`。
 * - 例如，原字符串 `s = "abc"` 变为 `sPrime = "#a#b#c#"`。
 * - 这样可以将奇数长度和偶数长度的回文串统一处理。
 *
 * **步骤2：定义回文半径数组 `palindromeRadii`**
 * - 创建一个数组 `palindromeRadii`，用于存储每个位置为中心时回文串的半径长度。
 * - 初始化两个变量：
 *   - `center`：当前已知回文串的中心位置。
 *   - `radius`：当前已知回文串的右边界位置。
 *
 * **步骤3：遍历新字符串 `sPrime`**
 * - 对于每个位置 `i`：
 *   1. 计算 `mirror = 2 * center - i`，即位置 `i` 关于当前回文中心的镜像位置。
 *   2. 如果当前位置在右边界内，则将镜像位置的半径值赋给当前半径值。
 *   3. 尝试向外扩展回文串，比较左右字符是否相等，直到不能继续扩展。
 *   4. 如果当前回文串的右边界超过了之前的右边界，则更新中心位置和右边界位置。
 *
 * **步骤4：找到最长回文半径及其对应的中心点**
 * - 遍历 `palindromeRadii` 数组，找到最大的回文半径值及其对应的中心点。
 * - 通过中心点和半径值计算出原字符串中的回文子串的起始和结束位置。
 *
 * **步骤5：截取原字符串中的最长回文子串**
 * - 根据计算出的起始位置和结束位置，截取原字符串中的最长回文子串并返回。
 *
 * ------------------------------
 * **举例解释：**
 * **示例1：**
 * - 输入：`s = "babad"`
 * - 构造的新字符串：`sPrime = "#b#a#b#a#d#"`
 * - 回文半径数组：`[0, 1, 0, 3, 0, 1, 0, 1, 0]`
 * - 最大回文半径为 3，对应的中心点为 3。
 * - 回文子串的起始索引为 `(3 - 3) / 2 = 0`，长度为 3。
 * - 截取原字符串中的子串：`s.substring(0, 3) = "bab"`。
 * - 输出结果：`"bab"`。

 * **示例2：**
 * - 输入：`s = "cbbd"`
 * - 构造的新字符串：`sPrime = "#c#b#b#d#"`
 * - 回文半径数组：`[0, 1, 0, 2, 0, 1, 0]`
 * - 最大回文半径为 2，对应的中心点为 3。
 * - 回文子串的起始索引为 `(3 - 2) / 2 = 1`，长度为 2。
 * - 截取原字符串中的子串：`s.substring(1, 3) = "bb"`。
 * - 输出结果：`"bb"`。

 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：O(n)**
 * - `n` 是字符串的长度。
 * - 使用 Manacher’s Algorithm，可以在线性时间内计算最长回文子串。

 * **空间复杂度：O(n)**
 * - 需要创建一个长度为 `2n + 1` 的新字符串 `sPrime`。
 * - 需要一个长度为 `2n + 1` 的回文半径数组 `palindromeRadii`。
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
            // step1: 使用 Manacher's Algorithm 构造新字符串，在每个字符之间插入特殊字符 '#'，以便处理奇数和偶数长度的回文串
            StringBuilder sPrime = new StringBuilder("#");
            for (char c : s.toCharArray()) {
                sPrime.append(c).append("#");
            }

            // 获取新字符串的长度
            int n = sPrime.length();
            // 定义一个数组来存储每个位置的回文半径
            int[] palindromeRadii = new int[n];
            // 当前已知的回文串的中心和右边界
            int center = 0;
            int radius = 0;

            // 遍历新字符串的每个字符
            for (int i = 0; i < n; i++) {
                // 计算当前字符在回文中心的镜像位置
                int mirror = 2 * center - i;

                // 如果当前字符在右边界内，则取已知半径和当前右边界距离的最小值
                if (i < radius) {
                    palindromeRadii[i] = Math.min(radius - i, palindromeRadii[mirror]);
                }

                // 尝试扩展回文半径，比较左右字符是否相等
                while (i + 1 + palindromeRadii[i] < n &&
                        i - 1 - palindromeRadii[i] >= 0 &&
                        sPrime.charAt(i + 1 + palindromeRadii[i]) == sPrime.charAt(i - 1 - palindromeRadii[i])) {
                    palindromeRadii[i]++;
                }

                // 如果当前回文串的右边界超过了之前的右边界，更新中心点和右边界
                if (i + palindromeRadii[i] > radius) {
                    center = i;
                    radius = i + palindromeRadii[i];
                }
            }

            // 寻找最大回文半径及其对应的中心点
            int maxLength = 0; // 最大回文长度
            int centerIndex = 0; // 最大回文的中心点索引
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

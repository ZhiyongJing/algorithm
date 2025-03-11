package leetcode.question.two_pointer;
/**
 *@Question:  680. Valid Palindrome II
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 92.53%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * 题目描述：
 * 680. Valid Palindrome II (验证回文字符串 II)
 *
 * 给定一个非空字符串 s，最多删除一个字符，判断是否能成为回文字符串。
 *
 * 示例：
 * 输入: "abca"
 * 输出: true
 * 解释: 通过删除 'b' 或 'c'，可以使字符串成为回文 "aca" 或 "aba"。
 *
 * 输入: "racecar"
 * 输出: true
 * 解释: 原本就是回文，不需要删除字符。
 *
 * 输入: "abcdef"
 * 输出: false
 * 解释: 不能通过删除一个字符使其成为回文。
 *
 *
 * 解题思路：
 * 1. 使用双指针方法，初始化两个指针：
 *    - `i` 指向字符串的起始位置（左侧）。
 *    - `j` 指向字符串的末尾位置（右侧）。
 *
 * 2. 从两端向中间遍历字符串：
 *    - 如果 `s[i] == s[j]`，则说明当前字符匹配，继续移动指针：
 *      - `i++` 右移
 *      - `j--` 左移
 *
 * 3. 如果 `s[i] != s[j]`（即出现字符不匹配）：
 *    - 允许删除一个字符，因此有两个选择：
 *      1. 删除 `s[i]`，检查 `s[i+1:j]` 是否是回文。
 *      2. 删除 `s[j]`，检查 `s[i:j-1]` 是否是回文。
 *    - 只要任意一个删除后的子串是回文，就返回 `true`。
 *
 * 4. 如何判断 `s[i:j]` 是否是回文？
 *    - 使用 `checkPalindrome(s, i, j)` 方法，该方法检查 `s` 在索引范围 `[i, j]` 内是否是回文：
 *      - 继续使用双指针 `left=i` 和 `right=j` 进行遍历。
 *      - 如果 `s[left] != s[right]`，直接返回 `false`。
 *      - 否则移动指针 `left++` 和 `right--`，直到遍历完整个子串。
 *
 * 5. 如果整个遍历过程中都没有遇到 `s[i] != s[j]` 的情况，说明原字符串就是回文，直接返回 `true`。
 *
 *
 * 示例讲解：
 * 例1: "abca"
 * - 初始：i=0, j=3，s[i]='a'，s[j]='a'，匹配，移动指针 i=1, j=2。
 * - i=1, j=2，s[i]='b'，s[j]='c'，不匹配：
 *   - 尝试删除 `s[1]` ("aca")，检查是否是回文：是。
 *   - 尝试删除 `s[2]` ("aba")，检查是否是回文：是。
 * - 至少有一种情况是回文，因此返回 `true`。
 *
 * 例2: "racecar"
 * - 由于本身就是回文，所以遍历过程中 `s[i] == s[j]`，最终返回 `true`。
 *
 * 例3: "abcdef"
 * - i=0, j=5，s[i]='a'，s[j]='f'，不匹配：
 *   - 删除 `s[0]` ("bcdef") 不是回文。
 *   - 删除 `s[5]` ("abcde") 不是回文。
 * - 无论删除哪个字符，都无法成为回文，返回 `false`。
 *
 *
 * 时间和空间复杂度分析：
 * 1. 遍历字符串的时间复杂度：
 *    - 在最坏情况下，需要遍历字符串一次，即 O(N)。
 *    - 如果发现一个不匹配字符，还需要额外调用 `checkPalindrome()`，其时间复杂度也是 O(N)。
 *    - 因此，总时间复杂度仍然是 O(N)。
 *
 * 2. 空间复杂度：
 *    - 只使用了双指针和少量额外变量，空间复杂度为 O(1)。
 */


public class LeetCode_680_ValidPalindromeIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 检查字符串 s 在索引 i 到 j 之间是否为回文
         * @param s 输入字符串
         * @param i 左指针
         * @param j 右指针
         * @return 如果是回文则返回 true，否则返回 false
         */
        private boolean checkPalindrome(String s, int i, int j) {
            while (i < j) { // 当左指针小于右指针时继续检查
                if (s.charAt(i) != s.charAt(j)) { // 如果对应字符不相等，则返回 false
                    return false;
                }
                i++; // 左指针右移
                j--; // 右指针左移
            }
            return true; // 经过检查后仍然是回文，则返回 true
        }

        /**
         * 判断字符串 s 是否可以通过删除最多一个字符变成回文
         * @param s 输入字符串
         * @return 如果可以变成回文，则返回 true，否则返回 false
         */
        public boolean validPalindrome(String s) {
            int i = 0; // 左指针初始化为 0
            int j = s.length() - 1; // 右指针初始化为字符串末尾索引

            while (i < j) { // 当左指针小于右指针时继续检查
                if (s.charAt(i) != s.charAt(j)) { // 发现字符不匹配的情况
                    // 进行删除操作，尝试删除左边字符或者右边字符，看是否能变成回文
                    return (checkPalindrome(s, i, j - 1) || checkPalindrome(s, i + 1, j));
                }
                i++; // 左指针右移
                j--; // 右指针左移
            }
            return true; // 如果遍历完整个字符串都没有遇到无法修复的情况，则返回 true
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_680_ValidPalindromeIi().new Solution();

        // 测试样例
        System.out.println(solution.validPalindrome("abca")); // true，可以删除 'b' 或 'c'
        System.out.println(solution.validPalindrome("racecar")); // true，本身就是回文
        System.out.println(solution.validPalindrome("abcdef")); // false，无法通过删除一个字符变成回文
        System.out.println(solution.validPalindrome("deeee")); // true，删除 'd' 后是回文 "eeee"
    }
}

/**
Given a string s, return true if the s can be palindrome after deleting at most 
one character from it. 

 
 Example 1: 

 
Input: s = "aba"
Output: true
 

 Example 2: 

 
Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
 

 Example 3: 

 
Input: s = "abc"
Output: false
 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s consists of lowercase English letters. 
 

 Related Topics Two Pointers String Greedy 👍 8501 👎 474

*/
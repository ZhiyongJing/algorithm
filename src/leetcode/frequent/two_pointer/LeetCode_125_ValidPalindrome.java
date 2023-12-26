package leetcode.frequent.two_pointer;

/**
 *@Question:  125. Valid Palindrome
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 77.04%
 *@Time  Complexity: O(n)
 *@Space Complexity: O(1)
 */

/**
 * **解题思路：**
 *
 * 1. 使用两个指针，一个从字符串开头开始，一个从结尾开始，分别用变量 `i` 和 `j` 表示。
 *
 * 2. 在循环中，移动指针 `i`，直到找到字母或数字。这里使用 `Character.isLetterOrDigit` 方法判断字符是否是字母或数字。
 *
 * 3. 在循环中，移动指针 `j`，直到找到字母或数字。
 *
 * 4. 比较忽略大小写后的字符是否相等，如果不相等，返回 `false`，说明不是回文串。
 *
 * 5. 如果整个字符串都比较完毕，返回 `true`，说明是回文串。
 *
 * **时间复杂度：**
 *
 * - 遍历字符串的过程是线性的，因此时间复杂度为 O(n)，其中 n 是字符串的长度。
 *
 * **空间复杂度：**
 *
 * - 使用了常数个变量（指针 `i`、`j`）来存储位置信息，因此空间复杂度为 O(1)。
 * 虽然有一个数组 `s`，但它是输入参数，不算在额外的空间复杂度中。
 */

public class LeetCode_125_ValidPalindrome {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 判断给定字符串是否为回文串
         * @param s 输入字符串
         * @return 是否为回文串
         */
        public boolean isPalindrome(String s) {
            // 使用两个指针，一个从字符串开头开始，一个从结尾开始
            for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
                // 移动指针 i，直到找到字母或数字
                while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                    i++;
                }
                // 移动指针 j，直到找到字母或数字
                while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                    j--;
                }

                // 比较忽略大小写后的字符是否相等
                if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                    return false;
                }
            }

            // 如果整个字符串都比较完毕，返回 true，说明是回文串
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_125_ValidPalindrome().new Solution();

        // 测试代码
        String testString1 = "A man, a plan, a canal: Panama";
        boolean result1 = solution.isPalindrome(testString1);
        System.out.println("测试字符串1是否为回文串：" + result1);

        String testString2 = "race a car";
        boolean result2 = solution.isPalindrome(testString2);
        System.out.println("测试字符串2是否为回文串：" + result2);
    }
}

/**
 A phrase is a palindrome if, after converting all uppercase letters into
 lowercase letters and removing all non-alphanumeric characters, it reads the same
 forward and backward. Alphanumeric characters include letters and numbers.

 Given a string s, return true if it is a palindrome, or false otherwise.


 Example 1:


 Input: s = "A man, a plan, a canal: Panama"
 Output: true
 Explanation: "amanaplanacanalpanama" is a palindrome.


 Example 2:


 Input: s = "race a car"
 Output: false
 Explanation: "raceacar" is not a palindrome.


 Example 3:


 Input: s = " "
 Output: true
 Explanation: s is an empty string "" after removing non-alphanumeric characters.

 Since an empty string reads the same forward and backward, it is a palindrome.



 Constraints:


 1 <= s.length <= 2 * 10⁵
 s consists only of printable ASCII characters.


 Related Topics Two Pointers String 👍 8641 👎 8105

 */

package leetcode.question.two_pointer;
/**
 *@Question:  151. Reverse Words in a String
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 65.2%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * **题目：151. Reverse Words in a String**
 *
 * **问题描述：**
 * 给定一个字符串 `s`，其中包含若干单词和空格，要求翻转字符串中单词的顺序。输出的结果应删除多余的空格，只保留一个空格分隔单词。单词之间的顺序需要翻转，但单词内部的字符顺序不变。
 *
 * **解题思路：**
 *
 * 1. **去除多余空格：**
 *    - 从字符串的两端开始遍历，去除前导空格和尾部空格。
 *    - 通过遍历字符串将多个连续的空格缩减为一个空格。
 *
 * 2. **反转整个字符串：**
 *    - 在去除多余空格后的字符串上，反转整个字符串中的所有字符。这将会导致单词之间的顺序正确，但每个单词内部的字符顺序也被颠倒。
 *
 * 3. **反转每个单词：**
 *    - 遍历反转后的字符串，找到每个单词的边界（通过空格分隔），然后对每个单词再次进行反转，使得每个单词内部的字符顺序恢复正常。
 *
 * 通过以上步骤，最终的字符串将是以正确顺序排列的单词，并且单词之间只有一个空格分隔。
 *
 * **时间复杂度分析：**
 * - **去除多余空格的时间复杂度：** `O(n)`，其中 `n` 是字符串的长度，因为我们只需遍历一次字符串。
 * - **反转整个字符串的时间复杂度：** `O(n)`，因为需要遍历一次字符串来交换字符。
 * - **反转每个单词的时间复杂度：** `O(n)`，需要再次遍历字符串，找到每个单词并进行反转。
 * - 因此，总的时间复杂度为 `O(n)`。
 *
 * **空间复杂度分析：**
 * - **额外空间：** 主要是用于存储去除多余空格后的字符串和处理中的临时变量，因此空间复杂度为 `O(n)`。
 * - 因此，总的空间复杂度为 `O(n)`。
 */
public class LeetCode_151_ReverseWordsInAString{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public StringBuilder trimSpaces(String s) {
            int left = 0, right = s.length() - 1;
            // 去除前导空格
            while (left <= right && s.charAt(left) == ' ') ++left;

            // 去除尾部空格
            while (left <= right && s.charAt(right) == ' ') --right;

            // 将多个空格缩减为一个空格
            StringBuilder sb = new StringBuilder();
            while (left <= right) {
                char c = s.charAt(left);

                if (c != ' ') sb.append(c); // 添加非空格字符
                else if (sb.charAt(sb.length() - 1) != ' ') sb.append(c); // 确保只添加一个空格

                ++left;
            }
            return sb;
        }

        public void reverse(StringBuilder sb, int left, int right) {
            // 反转字符串指定范围内的字符
            while (left < right) {
                char tmp = sb.charAt(left);
                sb.setCharAt(left++, sb.charAt(right));
                sb.setCharAt(right--, tmp);
            }
        }

        public void reverseEachWord(StringBuilder sb) {
            int n = sb.length();
            int start = 0, end = 0;

            while (start < n) {
                // 找到一个单词的末尾
                while (end < n && sb.charAt(end) != ' ') ++end;
                // 反转该单词
                reverse(sb, start, end - 1);
                // 移动到下一个单词
                start = end + 1;
                ++end;
            }
        }

        public String reverseWords(String s) {
            // 将字符串转换为StringBuilder并同时去除多余空格
            StringBuilder sb = trimSpaces(s);

            // 反转整个字符串
            reverse(sb, 0, sb.length() - 1);

            // 反转每个单词
            reverseEachWord(sb);

            return sb.toString(); // 返回最终结果
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_151_ReverseWordsInAString().new Solution();
        // 测试样例
        String input1 = "the sky is blue";
        String result1 = solution.reverseWords(input1);
        System.out.println("Reversed Words (Example 1): " + result1); // 应输出 "blue is sky the"

        String input2 = "  hello world  ";
        String result2 = solution.reverseWords(input2);
        System.out.println("Reversed Words (Example 2): " + result2); // 应输出 "world hello"
    }
}

/**
Given an input string s, reverse the order of the words. 

 A word is defined as a sequence of non-space characters. The words in s will 
be separated by at least one space. 

 Return a string of the words in reverse order concatenated by a single space. 

 Note that s may contain leading or trailing spaces or multiple spaces between 
two words. The returned string should only have a single space separating the 
words. Do not include any extra spaces. 

 
 Example 1: 

 
Input: s = "the sky is blue"
Output: "blue is sky the"
 

 Example 2: 

 
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

 

 Example 3: 

 
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single 
space in the reversed string.
 

 
 Constraints: 

 
 1 <= s.length <= 10⁴ 
 s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 
 There is at least one word in s. 
 

 
 Follow-up: If the string data type is mutable in your language, can you solve 
it in-place with O(1) extra space? 

 Related Topics Two Pointers String 👍 8501 👎 5184

*/
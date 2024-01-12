package leetcode.question.two_pointer;

/**
 *@Question:  186. Reverse Words in a String II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 19.6%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * 这道题的目标是反转字符串中的每个单词以及整个字符串。在不使用额外空间的情况下，我们需要在原地进行修改。
 *
 * **解题思路：**
 *
 * 1. 首先，我们创建一个 `reverse` 方法，该方法用于反转字符数组中指定范围的字符。
 * 2. 使用该 `reverse` 方法，我们首先反转整个字符串，将整个字符串逆序。
 * 3. 接着，我们通过迭代找到每个单词，再调用 `reverse` 方法反转每个单词。
 *
 * 具体步骤如下：
 *
 * - 反转整个字符串：`reverse(s, 0, s.length - 1)`
 * - 反转每个单词：`reverseEachWord(s)`，其中在 `reverseEachWord` 方法中，我们用两个指针 `start` 和 `end`
 * 找到每个单词的起始和结束位置，然后调用 `reverse` 方法进行反转。
 *
 * **时间复杂度：**
 *
 * - 反转整个字符串的时间复杂度为 O(N)，其中 N 是字符串的长度。
 * - 反转每个单词的时间复杂度同样是 O(N)。
 *
 * 因此，总体时间复杂度是 O(N)。
 *
 * **空间复杂度：**
 *
 * - 使用了常数级别的额外空间，主要是存储一些变量，因此空间复杂度是 O(1)。
 */

public class LeetCode_186_ReverseWordsInAStringIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 反转字符串的指定范围
         * @param s 字符数组
         * @param left 范围的左边界
         * @param right 范围的右边界
         */
        public void reverse(char[] s, int left, int right) {
            while (left < right) {
                char tmp = s[left];
                s[left++] = s[right];
                s[right--] = tmp;
            }
        }

        /**
         * 反转字符串中的每个单词
         * @param s 字符数组
         */
        public void reverseEachWord(char[] s) {
            int n = s.length;
            int start = 0, end = 0;

            while (start < n) {
                // 移动到单词的末尾
                while (end < n && s[end] != ' ') ++end;
                // 反转单词
                reverse(s, start, end - 1);
                // 移动到下一个单词
                start = end + 1;
                ++end;
            }
        }

        /**
         * 反转字符串中的每个单词以及整个字符串
         * @param s 字符数组
         */
        public void reverseWords(char[] s) {
            // 反转整个字符串
            System.out.println(s);
            reverse(s, 0, s.length - 1);
            System.out.println(s);

            // 反转每个单词
            reverseEachWord(s);
            System.out.println(s);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_186_ReverseWordsInAStringIi.Solution solution = new LeetCode_186_ReverseWordsInAStringIi().new Solution();

        // 测试代码
        char[] input = "Let's take LeetCode contest".toCharArray();
        solution.reverseWords(input);

        // 输出结果
        System.out.println("Reversed String: " + new String(input));
        // 预期输出："s'teL ekat edoCteeL tsetnoc"
    }
}

/**
Given a character array s, reverse the order of the words. 

 A word is defined as a sequence of non-space characters. The words in s will 
be separated by a single space. 

 Your code must solve the problem in-place, i.e. without allocating extra space.
 

 
 Example 1: 
 Input: s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 
 Example 2: 
 Input: s = ["a"]
Output: ["a"]
 
 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s[i] is an English letter (uppercase or lowercase), digit, or space ' '. 
 There is at least one word in s. 
 s does not contain leading or trailing spaces. 
 All the words in s are guaranteed to be separated by a single space. 
 

 Related Topics Two Pointers String 👍 1050 👎 141

*/

package leetcode.question.two_pointer;

/**
 *@Question:  345. Reverse Vowels of a String
 *@Difficulty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 63.24%
 *@Time Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * @Question: 345. Reverse Vowels of a String
 * @Difficulty: 1 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 63.24%
 * @Time Complexity: O(N)
 * @Space Complexity: O(N)
 *
 * 题目描述：
 * 给定一个字符串 s，编写一个函数，反转该字符串中的元音字母（元音字母包括 'a', 'e', 'i', 'o', 'u'，不区分大小写）。
 * 注意：你只能操作字符串中的元音字母。
 *
 * 解题思路：
 * 1. 首先定义一个辅助函数 `isVowel()` 用于判断一个字符是否是元音字母。这个函数检查字符是否在 'a', 'e', 'i', 'o', 'u' 及其大写字母范围内。
 *
 * 2. 定义两个指针 `start` 和 `end`，分别从字符串的两端开始向中间移动。
 *    - `start` 指针从字符串的开头向右移动，`end` 指针从字符串的末尾向左移动。
 *    - 在每次循环中，检查 `start` 和 `end` 指向的字符是否为元音字母。如果不是，则跳过，继续向中间移动。
 *
 * 3. 一旦找到两个元音字符，交换它们的位置。然后继续移动指针，直到两个指针相遇。
 *
 * 4. 最后将字符数组转换回字符串并返回结果。
 *
 * 举例：
 * 输入："hello"
 * 1. `start` 指向 'h'，`end` 指向 'o'，'h' 不是元音，`start` 向右移动，`start` 指向 'e'，'e' 是元音，`end` 指向 'o'，'o' 也是元音，交换这两个字符，结果变为 "holle"。
 * 2. 继续移动指针直到 `start` 超过 `end`，最终返回字符串 "holle"。
 *
 * 复杂度分析：
 * 时间复杂度：
 * - 我们最多遍历一次字符串。每次我们只需要检查并交换元音字母，因此时间复杂度为 O(N)，其中 N 是字符串的长度。
 *
 * 空间复杂度：
 * - 我们只使用了常量级的额外空间（即交换字符使用的临时变量），因此空间复杂度为 O(1)。
 */


public class LeetCode_345_ReverseVowelsOfAString{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 判断字符是否为元音字母（大小写均可）
        boolean isVowel(char c) {
            return c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u'
                    || c == 'A' || c == 'I' || c == 'E' || c == 'O' || c == 'U';
        }

        // 交换字符数组中索引为 x 和 y 的元素
        void swap(char[] chars, int x, int y) {
            char temp = chars[x];  // 暂存索引 x 的字符
            chars[x] = chars[y];  // 将索引 y 的字符赋值给索引 x
            chars[y] = temp;  // 将暂存的字符赋值给索引 y
        }

        public String reverseVowels(String s) {
            int start = 0;  // 左指针，指向字符串开头
            int end = s.length() - 1;  // 右指针，指向字符串末尾

            // 将字符串转换为字符数组，因为字符串是不可变的
            char[] sChar = s.toCharArray();

            // 当左右指针未交叉时继续操作
            while (start < end) {
                // 找到左边第一个元音字符
                while (start < s.length() && !isVowel(sChar[start])) {
                    start++;  // 向右移动左指针，直到找到元音
                }
                // 找到右边第一个元音字符
                while (end >= 0 && !isVowel(sChar[end])) {
                    end--;  // 向左移动右指针，直到找到元音
                }
                // 如果左指针小于右指针，交换两个元音字符
                if (start < end) {
                    swap(sChar, start++, end--);  // 交换后，左指针右移，右指针左移
                }
            }

            // 将字符数组转换回字符串并返回
            return new String(sChar);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_345_ReverseVowelsOfAString().new Solution();

        // 测试用例 1：输入字符串中有多个元音字符
        String test1 = "hello";
        System.out.println(solution.reverseVowels(test1));  // 输出 "holle"

        // 测试用例 2：输入字符串中只有一个元音字符
        String test2 = "leetcode";
        System.out.println(solution.reverseVowels(test2));  // 输出 "leotcede"

        // 测试用例 3：输入字符串中没有元音字符
        String test3 = "bcdfg";
        System.out.println(solution.reverseVowels(test3));  // 输出 "bcdfg"

        // 测试用例 4：输入字符串只有一个字符
        String test4 = "a";
        System.out.println(solution.reverseVowels(test4));  // 输出 "a"
    }
}

/**
Given a string s, reverse only all the vowels in the string and return it. 

 The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower 
and upper cases, more than once. 

 
 Example 1: 

 
 Input: s = "IceCreAm" 
 

 Output: "AceCreIm" 

 Explanation: 

 The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes 
"AceCreIm". 

 Example 2: 

 
 Input: s = "leetcode" 
 

 Output: "leotcede" 

 
 Constraints: 

 
 1 <= s.length <= 3 * 10⁵ 
 s consist of printable ASCII characters. 
 

 Related Topics Two Pointers String 👍 4796 👎 2818

*/
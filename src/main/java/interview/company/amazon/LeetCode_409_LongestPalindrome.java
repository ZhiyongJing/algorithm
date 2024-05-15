package interview.company.amazon;
//package leetcode.question.two_pointer;

/**
 *@Question:  409. Longest Palindrome
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 40.85%
 *@Time  Complexity: O(n)
 *@Space Complexity: O(n)
 */

/**
 * **解题思路：**
 *
 * 1. **字符出现次数统计：** 使用一个大小为 128 的数组 `arr` 统计字符串中每个字符出现的次数。
 *
 * 2. **统计奇数次字符数：** 遍历数组 `arr`，统计其中出现奇数次的字符的个数。
 * 因为在回文串中，最多只能有一个字符的出现次数是奇数，其余字符都必须出现偶数次。
 *
 * 3. **计算最长回文串长度：** 如果存在出现奇数次的字符，那么最长回文串的长度就是字符串的总长度减去奇数次字符的个数再加上 1。
 * 如果没有出现奇数次的字符，那么最长回文串的长度就是字符串的总长度。
 *
 * **时间复杂度：**
 *
 * - 遍历字符串并统计字符出现次数的过程是线性的，时间复杂度为 O(n)，其中 n 是字符串的长度。
 *
 * **空间复杂度：**
 *
 * - 使用了大小为 128 的数组 `arr` 来记录字符出现次数，因此空间复杂度为 O(1)。虽然数组大小为 128，
 * 但是它的大小是一个常数，与输入规模无关。因此，我们通常可以将其视为 O(1) 的空间复杂度。
 */

public class LeetCode_409_LongestPalindrome {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算可以构成的最长回文串的长度
         * @param s 输入的字符串
         * @return 最长回文串的长度
         */
        public int longestPalindrome(String s) {
            // 统计字符出现的次数
            int[] arr = new int[128];
            for(char c : s.toCharArray()) {
                arr[c]++;
            }

            int count = 0;
            // 遍历字符数组，计算出现奇数次的字符数
            for (int i : arr) {
                count += (i % 2);
            }

            // 最终结果为字符串长度减去出现奇数次的字符数（如果有的话）再加上 1
            return count == 0 ? s.length() : (s.length() - count + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        leetcode.question.two_pointer.LeetCode_409_LongestPalindrome.Solution solution = new leetcode.question.two_pointer.LeetCode_409_LongestPalindrome().new Solution();

        // 测试代码
        String testString = "abccccdd";
        int result = solution.longestPalindrome(testString);

        System.out.println("最长回文串的长度是：" + result);
    }
}

/**
 Given a string s which consists of lowercase or uppercase letters, return the
 length of the longest palindrome that can be built with those letters.

 Letters are case sensitive, for example, "Aa" is not considered a palindrome
 here.


 Example 1:


 Input: s = "abccccdd"
 Output: 7
 Explanation: One longest palindrome that can be built is "dccaccd", whose
 length is 7.


 Example 2:


 Input: s = "a"
 Output: 1
 Explanation: The longest palindrome that can be built is "a", whose length is 1.




 Constraints:


 1 <= s.length <= 2000
 s consists of lowercase and/or uppercase English letters only.


 Related Topics Hash Table String Greedy 👍 5162 👎 342

 */

package leetcode.question.two_pointer;

/**
 *@Question:  647. Palindromic Substrings
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 69.42%
 *@Time  Complexity: O(N^2)，其中 N 是字符串 s 的长度。
 *@Space Complexity: O(1)
 */

/**
 * **解题思路：**
 *
 * 这道题目要求计算给定字符串中的回文子串个数。我们可以使用中心扩展法（Manacher's Algorithm）来解决。
 *
 * **主要思路：**
 *
 * 1. 遍历字符串中的每个字符，以当前字符为中心，向两侧扩展，计算以该字符为中心的回文子串个数。
 *
 * 2. 分为两种情况：
 *    - 如果回文串长度为奇数，以单个字符为中心。
 *    - 如果回文串长度为偶数，以相邻字符为中心。
 *
 * 3. 在计算过程中，利用双指针 `lo` 和 `hi` 从中心向两侧展开，直到不再是回文串为止。
 *
 * **时间复杂度：**
 *
 * - 对于每个字符，都进行一次中心扩展，因此总的时间复杂度为 O(N^2)，其中 N 是字符串的长度。
 *
 * **空间复杂度：**
 *
 * - 由于只使用了常数个变量，空间复杂度为 O(1)。
 */

public class LeetCode_647_PalindromicSubstrings {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countSubstrings(String s) {
            int ans = 0;

            for (int i = 0; i < s.length(); ++i) {
                // 奇数长度的回文串，以单个字符为中心
                ans += countPalindromesAroundCenter(s, i, i);

                // 偶数长度的回文串，以相邻字符为中心
                ans += countPalindromesAroundCenter(s, i, i + 1);
            }

            return ans;
        }

        // 计算以指定中心展开的回文串个数
        private int countPalindromesAroundCenter(String ss, int lo, int hi) {
            int ans = 0;

            // 从中心往两侧展开，直到不再是回文串为止
            while (lo >= 0 && hi < ss.length()) {
                if (ss.charAt(lo) != ss.charAt(hi))
                    break;      // 第一个和最后一个字符不匹配，不再是回文串

                // 向两侧展开
                lo--;
                hi++;

                ans++;
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_647_PalindromicSubstrings().new Solution();

        // 测试代码
        String testString1 = "abc";
        int result1 = solution.countSubstrings(testString1);
        System.out.println("测试字符串1的回文子串个数：" + result1);

        String testString2 = "aaa";
        int result2 = solution.countSubstrings(testString2);
        System.out.println("测试字符串2的回文子串个数：" + result2);
    }
}

/**
Given a string s, return the number of palindromic substrings in it. 

 A string is a palindrome when it reads the same backward as forward. 

 A substring is a contiguous sequence of characters within the string. 

 
 Example 1: 

 
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

 Example 2: 

 
Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

 
 Constraints: 

 
 1 <= s.length <= 1000 
 s consists of lowercase English letters. 
 

 Related Topics String Dynamic Programming 👍 9850 👎 207

*/

package leetcode.question.binary_search;

import java.util.HashSet;
import java.util.Set;

/**
 *@Question:  1062. Longest Repeating Substring
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 30.42%
 *@Time  Complexity: O(N^2 * logN) for solution1, O(N^2) for solution2
 *@Space Complexity: O(N^2)
 */

/**
 * **题目：1062. Longest Repeating Substring**
 *
 * **问题描述：**
 * 给定一个字符串 `s`，寻找该字符串中长度最长的重复子串。重复子串是指在字符串中出现多次且相同的子串。要求返回该重复子串的长度。
 *
 * **解题思路：**
 *
 * 有两种主要的方法可以解决这个问题：**二分查找法**和**动态规划法**。
 *
 * ### 方法一：二分查找法
 *
 * 1. **基本思路：**
 *    - 使用二分查找来确定最长重复子串的长度。对于长度 `mid`，我们检查是否存在长度为 `mid` 的重复子串。
 *
 * 2. **具体步骤：**
 *    - 定义两个指针 `start` 和 `end`，初始分别为1和字符串长度减1。
 *    - 使用二分查找，在 `[start, end]` 范围内搜索。
 *    - 每次取 `mid = (start + end) / 2`，检查长度为 `mid` 的子串是否存在重复。
 *    - 如果存在长度为 `mid` 的重复子串，则 `start = mid + 1`；否则，`end = mid - 1`。
 *    - 最终，`start - 1` 即为最长重复子串的长度。
 *
 * 3. **时间复杂度：**
 *    - 检查长度为 `mid` 的重复子串的时间复杂度为 `O(N^2)`，其中 `N` 是字符串的长度。总共进行 `logN` 次检查，因此时间复杂度为 `O(N^2 * logN)`。
 *
 * 4. **空间复杂度：**
 *    - 使用了一个 `HashSet` 来存储子串，空间复杂度为 `O(N)`，其中 `N` 是字符串的长度。
 *
 * ### 方法二：动态规划法
 *
 * 1. **基本思路：**
 *    - 使用动态规划的方法，通过计算字符串中每对字符的最长公共后缀来确定最长的重复子串。
 *
 * 2. **具体步骤：**
 *    - 使用一个二维数组 `dp`，其中 `dp[i][j]` 表示以第 `i` 个字符和第 `j` 个字符结尾的最长公共后缀的长度。
 *    - 如果 `s.charAt(i - 1) == s.charAt(j - 1)`，则 `dp[i][j] = dp[i - 1][j - 1] + 1`，并更新 `maxLength`。
 *    - 遍历所有可能的 `i` 和 `j`，记录最大的 `dp[i][j]` 即为最长重复子串的长度。
 *
 * 3. **时间复杂度：**
 *    - 需要遍历所有可能的 `i` 和 `j`，时间复杂度为 `O(N^2)`。
 *
 * 4. **空间复杂度：**
 *    - 使用了一个二维数组 `dp`，空间复杂度为 `O(N^2)`，其中 `N` 是字符串的长度。
 *
 * ### 总结
 * - **二分查找法**适用于需要通过长度进行查找的问题，结合哈希表查找重复子串，提高了查找效率。时间复杂度为 `O(N^2 * logN)`，空间复杂度为 `O(N)`。
 * - **动态规划法**适用于比较所有字符对的最长公共后缀来查找重复子串。时间复杂度和空间复杂度均为 `O(N^2)`。
 */
public class LeetCode_1062_LongestRepeatingSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // solution1: 二分查找法
        public int longestRepeatingSubstring1(String s) {
            char[] characters = s.toCharArray();
            int start = 1, end = characters.length - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                // 检查是否存在长度为 mid 的重复子串
                if (hasRepeatingSubstring(characters, mid)) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            return start - 1; // 返回最长的重复子串长度
        }

        private boolean hasRepeatingSubstring(char[] characters, int length) {
            Set<String> seenSubstrings = new HashSet<>();
            // 检查给定长度的重复子串
            for (int i = 0; i <= characters.length - length; i++) {
                String substring = new String(characters, i, length);
                if (!seenSubstrings.add(substring)) {
                    return true; // 找到重复子串
                }
            }
            return false; // 未找到重复子串
        }

        // solution2: 动态规划法
        public int longestRepeatingSubstring(String s) {
            int length = s.length();
            int[][] dp = new int[length + 1][length + 1];
            int maxLength = 0;

            // 使用动态规划找到最长的公共子串
            for (int i = 1; i <= length; i++) {
                for (int j = i + 1; j <= length; j++) {
                    // 如果字符匹配，扩展公共子串的长度
                    if (s.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        maxLength = Math.max(maxLength, dp[i][j]); // 更新最长长度
                    }
                }
            }
            return maxLength; // 返回最长的重复子串长度
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1062_LongestRepeatingSubstring().new Solution();

        // 测试代码
        String testString = "banana";
        int result1 = solution.longestRepeatingSubstring1(testString);
        System.out.println("最长重复子串的长度（方法1）：" + result1); // 使用二分查找法的结果

        int result2 = solution.longestRepeatingSubstring(testString);
        System.out.println("最长重复子串的长度（方法2）：" + result2); // 使用动态规划法的结果
    }
}

/**
Given a string s, return the length of the longest repeating substrings. If no 
repeating substring exists, return 0. 

 
 Example 1: 

 
Input: s = "abcd"
Output: 0
Explanation: There is no repeating substring.
 

 Example 2: 

 
Input: s = "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which 
occurs twice.
 

 Example 3: 

 
Input: s = "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
 

 
 Constraints: 

 
 1 <= s.length <= 2000 
 s consists of lowercase English letters. 
 

 Related Topics String Binary Search Dynamic Programming Rolling Hash Suffix 
Array Hash Function 👍 696 👎 73

*/
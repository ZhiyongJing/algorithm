package leetcode.question.dp;
/**
 *@Question:  87. Scramble String
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 19.57%
 *@Time  Complexity: O(N^4) 在长度为n的情况下，我们使用四重循环，因此时间复杂度为O(N^4)。
 *@Space Complexity: O(N^3) 我们使用三维数组dp[n+1][n][n]进行动态规划，因此空间复杂度为O(N^3)。
 */

/**
 * 题目要求判断两个字符串是否为扰乱字符串。扰乱字符串的定义是通过对其进行分割并交换得到另一个字符串。解决这个问题可以使用动态规划的方法。
 *
 * ### 解题思路
 *
 * 1. **动态规划定义**：
 *    - 定义 `dp[length][i][j]` 表示长度为 `length`，起始位置分别为 `s1[i]` 和 `s2[j]` 的子串是否为扰乱字符串。
 *
 * 2. **初始化**：
 *    - 首先处理长度为1的子问题，即单个字符是否相等。
 *
 * 3. **状态转移**：
 *    - 对于长度大于1的子串，使用三重循环：外层循环遍历子串长度 `length`，中间两层循环分别遍历 `s1` 和 `s2` 的起始位置。
 *    - 内层最深层循环遍历分割点 `newLength`，判断两种情况：
 *      - 分割点在哪里，分别判断子串是否为扰乱字符串。
 *
 * 4. **结果**：
 *    - 返回 `dp[n][0][0]`，即长度为 `n` 的子串从起始位置0开始的子问题结果。
 *
 * ### 时间复杂度
 * - 由于使用了四重循环，时间复杂度为 `O(N^4)`，其中 `N` 是字符串的长度。
 *
 * ### 空间复杂度
 * - 使用了三维数组 `dp[n+1][n][n]` 存储中间状态，空间复杂度为 `O(N^3)`，其中 `N` 是字符串的长度。
 */

public class LeetCode_87_ScrambleString{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 判断字符串s1和s2是否为扰乱字符串
        public boolean isScramble(String s1, String s2) {
            int n = s1.length();
            // dp数组用来记录子问题的结果
            boolean dp[][][] = new boolean[n + 1][n][n];

            // 初始化长度为1的子串的情况
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[1][i][j] = s1.charAt(i) == s2.charAt(j);
                }
            }

            // 遍历所有长度大于1的子串
            for (int length = 2; length <= n; length++) {
                // 遍历s1中所有可能的起始位置
                for (int i = 0; i < n + 1 - length; i++) {
                    // 遍历s2中所有可能的起始位置
                    for (int j = 0; j < n + 1 - length; j++) {
                        // 遍历分割点，判断子问题的情况
                        for (int newLength = 1; newLength < length; newLength++) {
                            // dp1和dp2分别表示s1和s2的两部分是否为扰乱字符串
                            boolean dp1[] = dp[newLength][i];
                            boolean dp2[] = dp[length - newLength][i + newLength];
                            // 判断当前长度的两个部分是否为扰乱字符串
                            dp[length][i][j] |= dp1[j] && dp2[j + newLength];
                            dp[length][i][j] |= dp1[j + length - newLength] && dp2[j];
                        }
                    }
                }
            }
            // 返回长度为n的情况下，s1和s2是否为扰乱字符串
            return dp[n][0][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_87_ScrambleString().new Solution();
        // TO TEST
        // 示例测试样例
        boolean result1 = solution.isScramble("great", "rgeat"); // 应返回 true
        boolean result2 = solution.isScramble("abcde", "caebd"); // 应返回 false
        System.out.println(result1);
        System.out.println(result2);
    }
}

/**
We can scramble a string s to get a string t using the following algorithm: 

 
 If the length of the string is 1, stop. 
 If the length of the string is > 1, do the following: 
 
 Split the string into two non-empty substrings at a random index, i.e., if the 
string is s, divide it to x and y where s = x + y. 
 Randomly decide to swap the two substrings or to keep them in the same order. 
i.e., after this step, s may become s = x + y or s = y + x. 
 Apply step 1 recursively on each of the two substrings x and y. 
 
 

 Given two strings s1 and s2 of the same length, return true if s2 is a 
scrambled string of s1, otherwise, return false. 

 
 Example 1: 

 
Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: One possible scenario applied on s1 is:
"great" --> "gr/eat" // divide at random index.
"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and 
keep them in order.
"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both 
substrings. divide at random index each of them.
"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first 
substring and to keep the second substring in the same order.
"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, 
divide "at" to "a/t".
"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings 
in the same order.
The algorithm stops now, and the result string is "rgeat" which is s2.
As one possible scenario led s1 to be scrambled to s2, we return true.
 

 Example 2: 

 
Input: s1 = "abcde", s2 = "caebd"
Output: false
 

 Example 3: 

 
Input: s1 = "a", s2 = "a"
Output: true
 

 
 Constraints: 

 
 s1.length == s2.length 
 1 <= s1.length <= 30 
 s1 and s2 consist of lowercase English letters. 
 

 Related Topics String Dynamic Programming 👍 3299 👎 1272

*/
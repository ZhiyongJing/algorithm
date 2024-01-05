package leetcode.frequent.dfs;

/**
  *@Question:  1216. Valid Palindrome III     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 31.480000000000004%      
  *@Time  Complexity: O(n^2)
  *@Space Complexity: O(n^2)
 */

/**
 * ### 算法思路
 *
 * 这是一个动态规划问题，目标是判断一个字符串是否是 k-回文字符串。k-回文字符串是指通过最多删除 k 个字符可以使得字符串变成回文字符串。
 *
 * 1. **状态定义：** 定义状态 `dp[i][j]` 表示在字符串 `s` 的子串 `s[i:j]` 内，最小删除次数。
 *
 * 2. **状态转移：** 通过递归关系来更新状态。如果 `s[i] == s[j]`，那么在两端字符相等的情况下，
 * `dp[i][j] = dp[i+1][j-1]`；否则，`dp[i][j] = 1 + min(dp[i+1][j], dp[i][j-1])`，
 * 表示要么删除字符 `s[i]`，要么删除字符 `s[j]`，并递归尝试匹配。
 *
 * 3. **初始化：** 对于只剩一个字符的情况，`dp[i][i] = 0`；对于只剩两个字符的情况，`dp[i][i+1] = 1` 如果 `s[i] != s[i+1]`。
 *
 * 4. **结果：** 如果 `dp[0][n-1] <= k`，则字符串是 k-回文字符串。
 *
 * ### 详细步骤
 *
 * 1. 创建一个二维数组 `dp`，其中 `dp[i][j]` 表示在字符串 `s` 的子串 `s[i:j]` 内，最小删除次数。
 * 2. 初始化 `dp`，对于只剩一个字符的情况，`dp[i][i] = 0`；对于只剩两个字符的情况，`dp[i][i+1] = 1` 如果 `s[i] != s[i+1]`。
 * 3. 使用递归关系更新 `dp`，根据递归关系 `dp[i][j] = 1 + min(dp[i+1][j], dp[i][j-1])`。
 * 4. 如果 `dp[0][n-1] <= k`，则字符串是 k-回文字符串。
 *
 * ### 时间复杂度
 *
 * - 由于存在递归和重复计算，总体时间复杂度为 O(n^2)，其中 `n` 是字符串的长度。
 *
 * ### 空间复杂度
 *
 * - 使用了一个二维数组 `dp` 存储状态，空间复杂度为 O(n^2)，其中 `n` 是字符串的长度。
 *
 * 综合来说，该算法的时间复杂度和空间复杂度都是相对较高的，但对于题目中规定的范围是可以接受的。
 */
public class LeetCode_1216_ValidPalindromeIii {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Integer memo[][];

        // 判断字符串s在区间[i, j]内是否为回文，返回最小删除次数
        int isValidPalindrome(String s, int i, int j) {

            // 基本情况：只剩一个字符。
            if (i == j)
                return 0;

            // 基本情况2：只剩两个字符。
            if (i == j - 1)
                return s.charAt(i) != s.charAt(j) ? 1 : 0;

            // 如果已经计算过，直接返回结果。
            if (memo[i][j] != null)
                return memo[i][j];

            // 情况1：字符在`i`和`j`处相等。
            if (s.charAt(i) == s.charAt(j))
                return memo[i][j] = isValidPalindrome(s, i + 1, j - 1);

            // 情况2：字符在`i`和`j`处不相等。
            // 要么删除`i`处的字符，要么删除`j`处的字符，
            // 然后递归尝试匹配指针。
            // 需要取两者结果的最小值，并加1，表示删除的代价。
            return memo[i][j] = 1 + Math.min(isValidPalindrome(s, i + 1, j), isValidPalindrome(s, i, j - 1));
        }

        // 判断是否为k-回文
        public boolean isValidPalindrome(String s, int k) {
            memo = new Integer[s.length()][s.length()];

            // 如果通过最少删除次数构造回文的代价小于等于`k`，返回true。
            return isValidPalindrome(s, 0, s.length() - 1) <= k;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_1216_ValidPalindromeIii.Solution solution = new LeetCode_1216_ValidPalindromeIii().new Solution();

        // 测试用例1
        String s1 = "abcdeca";
        int k1 = 2;
        System.out.println("测试用例1: " + solution.isValidPalindrome(s1, k1)); // 预期输出: true

        // 测试用例2
        String s2 = "abbababa";
        int k2 = 1;
        System.out.println("测试用例2: " + solution.isValidPalindrome(s2, k2)); // 预期输出: true
    }
}
/**
Given a string s and an integer k, return true if s is a k-palindrome. 

 A string is k-palindrome if it can be transformed into a palindrome by 
removing at most k characters from it. 

 
 Example 1: 

 
Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
 

 Example 2: 

 
Input: s = "abbababa", k = 1
Output: true
 

 
 Constraints: 

 
 1 <= s.length <= 1000 
 s consists of only lowercase English letters. 
 1 <= k <= s.length 
 

 Related Topics String Dynamic Programming 👍 692 👎 9

*/

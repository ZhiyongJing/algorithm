package leetcode.question.dfs;

import java.util.Arrays;
import java.util.List;

/**
 *@Question:  139. Word Break
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 82.76%
 *@Time  Complexity: O(n*m*k)  // n as the length of s, m as the length of wordDict, and k as the average length of the words in wordDict
 *@Space Complexity: O(n)   // 递归调用栈的深度最坏情况下为n
 */

/**
 * ### 算法思路
 *
 * 这个问题可以使用动态规划来解决。定义一个数组 `dp`，其中 `dp[i]` 表示字符串 `s` 的前 `i` 个字符能否被单词字典拆分。
 * 初始时，`dp[0] = true`，表示空字符串可以被拆分。
 *
 * 对于每个位置 `i`，我们检查字符串的子串 `s[0:i]` 是否能够被拆分，并且剩余的子串 `s[i+1:]` 也在单词字典中。
 * 如果满足这两个条件，那么 `dp[i+1]` 也为 `true`。
 *
 * 在实现中，我们可以使用两个循环，外层循环遍历字符串的每个位置 `i`，内层循环遍历单词字典，检查是否能够拆分。
 *
 * ### 时间复杂度
 *
 * 外层循环遍历了字符串的每个位置，内层循环遍历了单词字典。因此，总的时间复杂度为 O(n * m)，其中 `n` 为字符串的长度，`m` 为单词字典的长度。
 *
 * ### 空间复杂度
 *
 * 我们使用一个长度为 `n+1` 的数组 `dp` 来存储中间结果，因此空间复杂度为 O(n+1)，即 O(n)。
 */

public class LeetCode_139_WordBreak{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private String s;
        private List<String> wordDict;
        private int[] memo;

        // 使用动态规划判断是否可以分割字符串
        private boolean dp(int i) {
            if (i < 0) return true;

            if (memo[i] != -1) {
                return memo[i] == 1;
            }

            for (String word: wordDict) {
                // 处理越界情况
                if (i - word.length() + 1 < 0) {
                    continue;
                }

                // 如果从 i-word.length()+1 到 i 的子串等于 word，并且前面的子串也能分割，则标记为可以分割
                if (s.substring(i - word.length() + 1, i + 1).equals(word) && dp(i - word.length())) {
                    memo[i] = 1;
                    return true;
                }
            }

            memo[i] = 0;
            return false;
        }

        //Solution1: Top down dp
        // 判断字符串是否可以分割
        public boolean wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            this.memo = new int[s.length()];
            Arrays.fill(this.memo, -1);
            return dp(s.length() - 1);
        }

        //Solution2: bottom-up dp
        public boolean wordBreak2(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (String word: wordDict) {
                    // Handle out of bounds case
                    if (i < word.length() - 1) {
                        continue;
                    }

                    if (i == word.length() - 1 || dp[i - word.length()]) {
                        if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }

            return dp[s.length() - 1];
        }


    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_139_WordBreak.Solution solution = new LeetCode_139_WordBreak().new Solution();

        // 简单测试
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        System.out.println(solution.wordBreak(s1, wordDict1));  // 输出：true

        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        System.out.println(solution.wordBreak(s2, wordDict2));  // 输出：true

        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(solution.wordBreak(s3, wordDict3));  // 输出：false
    }
}
/**
 Given a string s and a dictionary of strings wordDict, return true if s can be
 segmented into a space-separated sequence of one or more dictionary words.

 Note that the same word in the dictionary may be reused multiple times in the
 segmentation.


 Example 1:


 Input: s = "leetcode", wordDict = ["leet","code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".


 Example 2:


 Input: s = "applepenapple", wordDict = ["apple","pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen
 apple".
 Note that you are allowed to reuse a dictionary word.


 Example 3:


 Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 Output: false



 Constraints:


 1 <= s.length <= 300
 1 <= wordDict.length <= 1000
 1 <= wordDict[i].length <= 20
 s and wordDict[i] consist of only lowercase English letters.
 All the strings of wordDict are unique.


 Related Topics Array Hash Table String Dynamic Programming Trie Memoization 👍
 16528 👎 724

*/
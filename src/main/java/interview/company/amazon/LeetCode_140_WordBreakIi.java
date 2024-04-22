package interview.company.amazon;

/**
  *@Question:  140. Word Break II     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 66.6%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

/**
 * Step1: find can be separete by space
 * Step2: use dfs to build up the string
 */

public class LeetCode_140_WordBreakIi{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        // 状态定义：长度为 i 的子字符串是否符合题意
        boolean[] dp = new boolean[len + 1];

        // 预处理
        Set<String> wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
        }

        // 这个状态的设置非常关键，说明前部分的字符串已经在 wordSet 中
        dp[0] = true;
        for (int r = 1; r < len + 1; r++) {
            for (int l = 0; l < r; l++) {
                // dp[l] 写在前面会更快一点，否则还要去切片，然后再放入 hash 表判重
                if (dp[l] && wordSet.contains(s.substring(l, r))  ) {
                    dp[r] = true;
                    // 这个 break 很重要，一旦得到 dp[r] = True ，循环不必再继续
                    break;
                }
            }
        }
        List<String> res = new ArrayList<>();
        if (dp[len]) {
            LinkedList<String> queue = new LinkedList<>();
            dfs(s, len, wordSet, res, queue, dp);
            return res;
        }

        return res;
    }

    private void dfs(String s, int end, Set<String> wordSet, List<String> res, LinkedList<String> queue, boolean[] dp) {
        if (end == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String word : queue) {
                stringBuilder.append(word);
                stringBuilder.append(" ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            res.add(stringBuilder.toString());
            return;
        }
        //dp[i] 表示子串 s[0:i] （即长度为 i 的子串，其实就是前缀）可以被空格拆分，并且拆分以后的单词是否落在 wordDict 中

        for (int i = 0; i < end; i++) {
            if (dp[i]) {
                String suffix = s.substring(i, end);
                if (wordSet.contains(suffix)) {
                    queue.addFirst(suffix);
                    dfs(s, i, wordSet, res, queue, dp);
                    queue.removeFirst();
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_140_WordBreakIi().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a string s and a dictionary of strings wordDict, add spaces in s to 
construct a sentence where each word is a valid dictionary word. Return all such 
possible sentences in any order. 

 Note that the same word in the dictionary may be reused multiple times in the 
segmentation. 

 
 Example 1: 

 
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
 

 Example 2: 

 
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine",
"pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
 

 Example 3: 

 
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

 
 Constraints: 

 
 1 <= s.length <= 20 
 1 <= wordDict.length <= 1000 
 1 <= wordDict[i].length <= 10 
 s and wordDict[i] consist of only lowercase English letters. 
 All the strings of wordDict are unique. 
 Input is generated in a way that the length of the answer doesn't exceed 10⁵. 
 

 Related Topics Array Hash Table String Dynamic Programming Backtracking Trie 
Memoization 👍 6695 👎 527

*/
package interview.company.amazon;

/**
  *@Question:  139. Word Break     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 69.94%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_139_WordBreak{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_139_WordBreak().new Solution();
        // TO TEST
        //solution.
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
16919 👎 767

*/
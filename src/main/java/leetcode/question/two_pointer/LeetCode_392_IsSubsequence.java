package leetcode.question.two_pointer;

/**
  *@Question:  392. Is Subsequence
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 62.62%
  *@Time  Complexity: O(T), t is the length of target
  *@Space Complexity: O(1)
 */

public class LeetCode_392_IsSubsequence{

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isSubsequence(String s, String t) {
        int sLenght = s.length(), tLength = t.length();
        int sIndex = 0, tIndex = 0;
        while (sIndex < sLenght && tIndex < tLength){
            if(s.charAt(sIndex) == t.charAt(tIndex)){
                sIndex++;
            }
            tIndex++;


        }
        return sIndex == sLenght;
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_392_IsSubsequence().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given two strings s and t, return true if s is a subsequence of t, or false 
otherwise. 

 A subsequence of a string is a new string that is formed from the original 
string by deleting some (can be none) of the characters without disturbing the 
relative positions of the remaining characters. (i.e., "ace" is a subsequence of 
"abcde" while "aec" is not). 

 
 Example 1: 
 Input: s = "abc", t = "ahbgdc"
Output: true
 
 Example 2: 
 Input: s = "axc", t = "ahbgdc"
Output: false
 
 
 Constraints: 

 
 0 <= s.length <= 100 
 0 <= t.length <= 10⁴ 
 s and t consist only of lowercase English letters. 
 

 
Follow up: Suppose there are lots of incoming 
s, say 
s1, s2, ..., sk where 
k >= 10⁹, and you want to check one by one to see if 
t has its subsequence. In this scenario, how would you change your code?

 Related Topics Two Pointers String Dynamic Programming 👍 9986 👎 562

*/
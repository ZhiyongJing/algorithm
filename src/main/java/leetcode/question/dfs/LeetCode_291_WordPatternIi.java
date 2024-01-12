package leetcode.question.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
  *@Question:  291. Word Pattern II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 17.42%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_291_WordPatternIi{
    
//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        // base case
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        // get current pattern character
        char c = pat.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String s = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(s, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        // pattern character does not exist in the map
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);

            if (set.contains(p)) {
                continue;
            }

            // create or update it
            map.put(c, p);
            set.add(p);

            // continue to match the rest
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }

            // backtracking
            map.remove(c);
            set.remove(p);
        }

        // we've tried our best but still no luck
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_291_WordPatternIi().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a pattern and a string s, return true if s matches the pattern. 

 A string s matches a pattern if there is some bijective mapping of single 
characters to non-empty strings such that if each character in pattern is replaced 
by the string it maps to, then the resulting string is s. A bijective mapping 
means that no two characters map to the same string, and no character maps to two 
different strings. 

 
 Example 1: 

 
Input: pattern = "abab", s = "redblueredblue"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "red"
'b' -> "blue" 

 Example 2: 

 
Input: pattern = "aaaa", s = "asdasdasdasd"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "asd"
 

 Example 3: 

 
Input: pattern = "aabb", s = "xyzabcxzyabc"
Output: false
 

 
 Constraints: 

 
 1 <= pattern.length, s.length <= 20 
 pattern and s consist of only lowercase English letters. 
 

 Related Topics Hash Table String Backtracking 👍 869 👎 67

*/

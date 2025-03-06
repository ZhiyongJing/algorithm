package leetcode.question.two_pointer;

import java.util.HashMap;
import java.util.Map;

/**
  *@Question:  159. Longest Substring with At Most Two Distinct Characters
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 38.89%
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_159_LongestSubstringWithAtMostTwoDistinctCharacters{

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0;
        int right = 0;
        int n = s.length();
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        while(right < n){
            char r = s.charAt(right);
            map.put(r, map.getOrDefault(r, 0) + 1);
            while(map.size() > 2){
                char l = s.charAt(left);
                map.put(l, map.get(l) - 1);
                if(map.get(l) == 0) map.remove(l);
                left++;
            }
            result = Math.max(result , right - left + 1);
            right++;
        }
        return result;

    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_159_LongestSubstringWithAtMostTwoDistinctCharacters().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a string s, return the length of the longest substring that contains at 
most two distinct characters. 

 
 Example 1: 

 
Input: s = "eceba"
Output: 3
Explanation: The substring is "ece" which its length is 3.
 

 Example 2: 

 
Input: s = "ccaabbb"
Output: 5
Explanation: The substring is "aabbb" which its length is 5.
 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s consists of English letters. 
 

 Related Topics Hash Table String Sliding Window 👍 2236 👎 36

*/
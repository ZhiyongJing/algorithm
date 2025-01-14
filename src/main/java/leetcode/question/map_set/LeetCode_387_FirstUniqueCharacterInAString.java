package leetcode.question.map_set;

import java.util.HashMap;
import java.util.Map;

/**
  *@Question:  387. First Unique Character in a String
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 68.81%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

public class LeetCode_387_FirstUniqueCharacterInAString{

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for(int i = 0; i < s.length(); i++){
            if(map.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_387_FirstUniqueCharacterInAString().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a string s, find the first non-repeating character in it and return its 
index. If it does not exist, return -1. 

 
 Example 1: 

 
 Input: s = "leetcode" 
 

 Output: 0 

 Explanation: 

 The character 'l' at index 0 is the first character that does not occur at any 
other index. 

 Example 2: 

 
 Input: s = "loveleetcode" 
 

 Output: 2 

 Example 3: 

 
 Input: s = "aabb" 
 

 Output: -1 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s consists of only lowercase English letters. 
 

 Related Topics Hash Table String Queue Counting 👍 9130 👎 309

*/
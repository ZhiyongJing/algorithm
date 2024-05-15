package interview.company.amazon;

/**
  *@Question:  709. To Lower Case     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 10.81%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_709_ToLowerCase{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String toLowerCase(String s) {
        Map<Character, Character> h = new HashMap();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 26; ++i) {
            h.put(upper.charAt(i), lower.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (char x : s.toCharArray()) {
            sb.append(h.containsKey(x) ? h.get(x) : x);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_709_ToLowerCase().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given a string s, return the string after replacing every uppercase letter with 
the same lowercase letter. 

 
 Example 1: 

 
Input: s = "Hello"
Output: "hello"
 

 Example 2: 

 
Input: s = "here"
Output: "here"
 

 Example 3: 

 
Input: s = "LOVELY"
Output: "lovely"
 

 
 Constraints: 

 
 1 <= s.length <= 100 
 s consists of printable ASCII characters. 
 

 Related Topics String 👍 1819 👎 2765

*/
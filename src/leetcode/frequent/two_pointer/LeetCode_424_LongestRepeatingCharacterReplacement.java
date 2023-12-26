package leetcode.frequent.two_pointer;

/**
  *@Question:  424. Longest Repeating Character Replacement     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 71.84%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_424_LongestRepeatingCharacterReplacement{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int characterReplacement(String s, int k) {
        int start = 0;
        int[] frequencyMap = new int[26];
        int maxFrequency = 0;
        int longestSubstringLength = 0;

        for (int end = 0; end < s.length(); end += 1) {
            // if 'A' is 0, then what is the relative order
            // or offset of the current character entering the window
            // 0 is 'A', 1 is 'B' and so on
            int currentChar = s.charAt(end) - 'A';

            frequencyMap[currentChar] += 1;

            // the maximum frequency we have seen in any window yet
            maxFrequency = Math.max(maxFrequency, frequencyMap[currentChar]);

            // move the start pointer towards right if the current
            // window is invalid
            Boolean isValid = (end + 1 - start - maxFrequency <= k);
            if (!isValid) {
                // offset of the character moving out of the window
                int outgoingChar = s.charAt(start) - 'A';

                // decrease its frequency
                frequencyMap[outgoingChar] -= 1;

                // move the start pointer forward
                start += 1;
            }

            // the window is valid at this point, note down the length
            // size of the window never decreases
            longestSubstringLength = end + 1 - start;
        }

        return longestSubstringLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_424_LongestRepeatingCharacterReplacement().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given a string s and an integer k. You can choose any character of the 
string and change it to any other uppercase English character. You can perform 
this operation at most k times. 

 Return the length of the longest substring containing the same letter you can 
get after performing the above operations. 

 
 Example 1: 

 
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
 

 Example 2: 

 
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too. 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s consists of only uppercase English letters. 
 0 <= k <= s.length 
 

 Related Topics Hash Table String Sliding Window 👍 9892 👎 445

*/

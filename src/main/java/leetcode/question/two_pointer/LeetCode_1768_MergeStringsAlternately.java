package leetcode.question.two_pointer;

/**
  *@Question:  1768. Merge Strings Alternately
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 95.35%
  *@Time  Complexity: O(m + n)
  *@Space Complexity: O()
 */

public class LeetCode_1768_MergeStringsAlternately{

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;

        while (i < m || j < n) {
            if (i < m) {
                result.append(word1.charAt(i++));
            }
            if (j < n) {
                result.append(word2.charAt(j++));
            }
        }

        return result.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1768_MergeStringsAlternately().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given two strings word1 and word2. Merge the strings by adding letters 
in alternating order, starting with word1. If a string is longer than the other, 
append the additional letters onto the end of the merged string. 

 Return the merged string. 

 
 Example 1: 

 
Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
 

 Example 2: 

 
Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s
 

 Example 3: 

 
Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q 
merged: a p b q c   d
 

 
 Constraints: 

 
 1 <= word1.length, word2.length <= 100 
 word1 and word2 consist of lowercase English letters. 
 

 Related Topics Two Pointers String 👍 4209 👎 114

*/
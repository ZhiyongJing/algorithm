package leetcode.question.string_list;

/**
 *@Question:  214. Shortest Palindrome
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 46.45%
 *@Time  Complexity: O(N^2) for solution1, O(N) for solution2
 *@Space Complexity: O(N)
 */

public class LeetCode_214_ShortestPalindrome{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //solution 1
        public String shortestPalindrome(String s) {
            int j = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) { j += 1; }
            }
            if (j == s.length()) { return s; }
            String suffix = s.substring                             (j);
            return new StringBuilder(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;

        }

        //solution2
        public String shortestPalindrome2(String s) {
            if(s.length()<=1) return s;
            String new_s = s+"#"+new StringBuilder(s).reverse().toString();
            int[] position = new int[new_s.length()];

            for(int i=1;i<position.length;i++)
            {
                int pre_pos = position[i-1];
                while(pre_pos>0 && new_s.charAt(pre_pos)!=new_s.charAt(i))
                    pre_pos = position[pre_pos-1];
                position[i] = pre_pos+((new_s.charAt(pre_pos)==new_s.charAt(i))?1:0);
            }

            return new StringBuilder(s.substring(position[position.length-1])).reverse().toString()+s;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new Solution();
        // TO TEST
        //solution.
    }
}
/**
 You are given a string s. You can convert s to a palindrome by adding
 characters in front of it.

 Return the shortest palindrome you can find by performing this transformation.



 Example 1:
 Input: s = "aacecaaa"
 Output: "aaacecaaa"

 Example 2:
 Input: s = "abcd"
 Output: "dcbabcd"


 Constraints:


 0 <= s.length <= 5 * 10⁴
 s consists of lowercase English letters only.


 Related Topics String Rolling Hash String Matching Hash Function 👍 3550 👎 242


 */
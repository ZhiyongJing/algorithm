package leetcode.question.two_pointer;

/**
 *@Question:  408. Valid Word Abbreviation
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 98.37%
 *@Time  Complexity: O(N)，其中 N 是 word 和 abbr 的最大长度
 *@Space Complexity: O(1)
 */

public class LeetCode_408_ValidWordAbbreviation {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 验证缩写是否合法
         * @param word  原始字符串
         * @param abbr  缩写字符串
         * @return 如果 abbr 是 word 的有效缩写，则返回 true，否则返回 false
         */
        public boolean validWordAbbreviation(String word, String abbr) {
            int i = 0, j = 0; // i 遍历 word，j 遍历 abbr
            while (i < word.length() && j < abbr.length()) {
                // 如果当前字符相同，直接匹配
                if (word.charAt(i) == abbr.charAt(j)) {
                    ++i; ++j;
                    continue;
                }
                // 如果 abbr[j] 不是数字，说明格式错误
                if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                    return false;
                }
                // 读取缩写部分的数字
                int start = j;
                while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                    ++j;
                }
                // 将截取的数字转换为整数
                int num = Integer.valueOf(abbr.substring(start, j));
                // i 向前跳过 num 个字符
                i += num;
            }
            // 如果 i 和 j 都刚好到达末尾，说明匹配成功
            return i == word.length() && j == abbr.length();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_408_ValidWordAbbreviation().new Solution();

        // 测试样例 1
        String word1 = "internationalization";
        String abbr1 = "i12iz4n";
        System.out.println("测试样例 1 结果：" + solution.validWordAbbreviation(word1, abbr1));
        // 预期输出：true

        // 测试样例 2
        String word2 = "apple";
        String abbr2 = "a2e";
        System.out.println("测试样例 2 结果：" + solution.validWordAbbreviation(word2, abbr2));
        // 预期输出：false

        // 测试样例 3
        String word3 = "substitution";
        String abbr3 = "s10n";
        System.out.println("测试样例 3 结果：" + solution.validWordAbbreviation(word3, abbr3));
        // 预期输出：true

        // 测试样例 4
        String word4 = "substitution";
        String abbr4 = "s55n";
        System.out.println("测试样例 4 结果：" + solution.validWordAbbreviation(word4, abbr4));
        // 预期输出：false

        // 测试样例 5
        String word5 = "word";
        String abbr5 = "w2d";
        System.out.println("测试样例 5 结果：" + solution.validWordAbbreviation(word5, abbr5));
        // 预期输出：true
    }
}

/**
A string can be abbreviated by replacing any number of non-adjacent, non-empty 
substrings with their lengths. The lengths should not have leading zeros. 

 For example, a string such as "substitution" could be abbreviated as (but not 
limited to): 

 
 "s10n" ("s ubstitutio n") 
 "sub4u4" ("sub stit u tion") 
 "12" ("substitution") 
 "su3i1u2on" ("su bst i t u ti on") 
 "substitution" (no substrings replaced) 
 

 The following are not valid abbreviations: 

 
 "s55n" ("s ubsti tutio n", the replaced substrings are adjacent) 
 "s010n" (has leading zeros) 
 "s0ubstitution" (replaces an empty substring) 
 

 Given a string word and an abbreviation abbr, return whether the string 
matches the given abbreviation. 

 A substring is a contiguous non-empty sequence of characters within a string. 

 
 Example 1: 

 
Input: word = "internationalization", abbr = "i12iz4n"
Output: true
Explanation: The word "internationalization" can be abbreviated as "i12iz4n" (
"i nternational iz atio n").
 

 Example 2: 

 
Input: word = "apple", abbr = "a2e"
Output: false
Explanation: The word "apple" cannot be abbreviated as "a2e".
 

 
 Constraints: 

 
 1 <= word.length <= 20 
 word consists of only lowercase English letters. 
 1 <= abbr.length <= 10 
 abbr consists of lowercase English letters and digits. 
 All the integers in abbr will fit in a 32-bit integer. 
 

 Related Topics Two Pointers String 👍 834 👎 2344

*/
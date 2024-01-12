package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *@Question:  1087. Brace Expansion
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 22.33%
 *@Time  Complexity: O(n*3^(n/7）)
 *@Space Complexity: O(n)
 */

/**
 * **算法思路：**
 *
 * 1. 首先，遍历字符串 `s`，将每个字符的选项存储在 `allOptions` 中。如果字符不是 `{`，表示单个字符，直接添加到当前选项中。如果是 `{`，则找到对应的 `}`，将 `{}` 之间的字符添加到当前选项中，并对选项进行排序。
 *
 * 2. 使用递归生成所有可能的字符串。定义函数 `generateWords`，通过递归探索所有可能的字符串。递归的终止条件是当前字符串长度等于 `allOptions` 的长度，表示当前字符串已经生成完毕，可以将其加入结果集。
 *
 * 3. 在每次递归时，获取当前位置的选项，将选项中的字符添加到当前字符串中，并进入下一层递归。递归完成后，进行回溯，删除刚才添加的字符，保持回到上一层递归的状态。
 *
 * **时间复杂度：**
 *
 * - 存储选项的过程需要遍历整个字符串 `s`，时间复杂度是 O(N)。
 * - 生成所有可能字符串的过程是一个指数级的问题，最坏情况下需要生成所有的可能组合，时间复杂度为 O(2^N)。
 *
 * 因此，总体时间复杂度是 O(N + 2^N)。
 *
 * **空间复杂度：**
 *
 * - 存储选项需要额外的空间，最坏情况下可能需要 O(N) 的空间。
 * - 递归调用的栈空间，在最坏情况下可能达到 O(N)。
 *
 * 因此，总体空间复杂度是 O(N)。
 */
public class LeetCode_1087_BraceExpansion {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Character>> allOptions = new ArrayList<>();

        // 存储所有字符的选项
        void storeAllOptions(String s) {
            for (int pos = 0; pos < s.length(); pos++) {
                List<Character> currOptions = new ArrayList<>();

                // 如果第一个字符不是 '{'，意味着是单个字符
                if (s.charAt(pos) != '{') {
                    currOptions.add(s.charAt(pos));
                } else {
                    // 存储 '{' 和 '}' 之间的所有字符
                    while (s.charAt(pos) != '}') {
                        if (s.charAt(pos) >= 'a' && s.charAt(pos) <= 'z') {
                            currOptions.add(s.charAt(pos));
                        }
                        pos++;
                    }

                    // 对列表进行排序
                    Collections.sort(currOptions);

                }

                allOptions.add(currOptions);
            }
            System.out.println(allOptions);
        }

        // 生成扩展后的字符串
        void generateWords(StringBuilder currString, List<String> expandedWords) {
            // 如果当前字符串已经完成，我们可以存储并返回
            if (currString.length() == allOptions.size()) {
                expandedWords.add(currString.toString());
                return;
            }

            // 获取当前索引的选项
            List<Character> currOptions = allOptions.get(currString.length());

            // 添加字符并进入递归
            for (char c : currOptions) {
                currString.append(c);
                generateWords(currString, expandedWords);
                // 回溯到先前的状态
                currString.deleteCharAt(currString.length() - 1);
            }
        }

        public String[] expand(String s) {
            // 存储不同索引的字符选项
            storeAllOptions(s);

            List<String> expandedWords = new ArrayList<>();
            generateWords(new StringBuilder(), expandedWords);
            return expandedWords.toArray(new String[0]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1087_BraceExpansion().new Solution();

        // 测试用例1
        String s1 = "{a,b}c{d,e}f";
        String[] result1 = solution.expand(s1);
        System.out.println("Test Case 1: " + Arrays.toString(result1));

        // 测试用例2
        String s2 = "abcd";
        String[] result2 = solution.expand(s2);
        System.out.println("Test Case 2: " + Arrays.toString(result2));
    }
}

/**
You are given a string s representing a list of words. Each letter in the word 
has one or more options. 

 
 If there is one option, the letter is represented as is. 
 If there is more than one option, then curly braces delimit the options. For 
example, "{a,b,c}" represents options ["a", "b", "c"]. 
 

 For example, if s = "a{b,c}", the first character is always 'a', but the 
second character can be 'b' or 'c'. The original list is ["ab", "ac"]. 

 Return all words that can be formed in this manner, sorted in lexicographical 
order. 

 
 Example 1: 
 Input: s = "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
 
 Example 2: 
 Input: s = "abcd"
Output: ["abcd"]
 
 
 Constraints: 

 
 1 <= s.length <= 50 
 s consists of curly brackets '{}', commas ',', and lowercase English letters. 
 s is guaranteed to be a valid input. 
 There are no nested curly brackets. 
 All characters inside a pair of consecutive opening and ending curly brackets 
are different. 
 

 Related Topics String Backtracking Breadth-First Search 👍 629 👎 50

*/

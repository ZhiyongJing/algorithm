package leetcode.question.map_set;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  318. Maximum Product of Word Lengths
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 8.26%
 *@Time  Complexity: O(N^2 + L) N is the number of words and L is the total length of all words together.
 *@Space Complexity: O(N)
 */

/**
 * 这道题目要求找出字符串数组中任意两个字符串的长度乘积的最大值，其中这两个字符串不能有相同字母。为了解决这个问题，我们可以利用位运算和哈希表来优化计算过程：
 *
 * 1. **位运算表示字母集合**：
 *    - 首先，将每个字符串表示为一个位掩码（bitmask），其中每一位对应于字母 'a' 到 'z' 中的一个字母。如果字符串包含某个字母，则将对应字母的位设置为1，否则为0。
 *
 * 2. **计算每个字符串的位掩码**：
 *    - 对于每个字符串，遍历字符串的每个字符，利用位运算将其转换为位掩码。
 *
 * 3. **哈希表存储位掩码**：
 *    - 使用哈希表存储每个位掩码对应的字符串长度的最大值。因为不同的字符串可能有相同的位掩码（即包含相同的字母集合），我们需要保留长度最长的字符串对应的值。
 *
 * 4. **计算最大乘积**：
 *    - 遍历所有的位掩码，找出没有重复字母的字符串对应的最大长度，并计算乘积。在计算乘积时，需要确保乘积最大化。
 *
 * 5. **时间复杂度**：
 *    - 构建位掩码和填充哈希表的过程的时间复杂度为 \(O(N \cdot L)\)，其中 \(N\) 是字符串数组的长度，\(L\) 是所有字符串的总长度。遍历哈希表以找出最大乘积的时间复杂度为 \(O(N^2)\)。
 *
 * 6. **空间复杂度**：
 *    - 空间复杂度主要取决于哈希表的大小，即 \(O(N)\)，用于存储每个位掩码对应的最大长度值。
 *
 * 通过这种方法，我们可以有效地找出满足题目要求的最大乘积，同时保证计算效率较高。
 */
public class LeetCode_318_MaximumProductOfWordLengths{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 计算字符对应的比特位
        public int bitNumber(char ch){
            return (int)ch - (int)'a';
        }

        public int maxProduct(String[] words) {
            // 使用哈希表存储每个单词对应的比特掩码和单词长度的最大值
            Map<Integer, Integer> hashmap = new HashMap();

            int bitmask = 0, bitNum = 0;
            for (String word : words) {
                bitmask = 0;
                // 计算单词的比特掩码
                for (char ch : word.toCharArray()) {
                    // 将字符对应的比特位设置为1
                    bitmask |= 1 << bitNumber(ch);
                }
                // 存储当前比特掩码对应的单词长度的最大值
                hashmap.put(bitmask, Math.max(hashmap.getOrDefault(bitmask, 0), word.length()));
            }

            // 计算最大乘积
            int maxProd = 0;
            for (int x : hashmap.keySet())
                for (int y : hashmap.keySet())
                    // 如果两个比特掩码没有重叠（即位与操作结果为0），计算它们对应单词长度的乘积
                    if ((x & y) == 0) maxProd = Math.max(maxProd, hashmap.get(x) * hashmap.get(y));

            return maxProd;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_318_MaximumProductOfWordLengths.Solution solution = new LeetCode_318_MaximumProductOfWordLengths().new Solution();

        // 测试用例1
        String[] words1 = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        int maxProduct1 = solution.maxProduct(words1);
        System.out.println("Max product of word lengths (Expected: 16): " + maxProduct1);

        // 测试用例2
        String[] words2 = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        int maxProduct2 = solution.maxProduct(words2);
        System.out.println("Max product of word lengths (Expected: 4): " + maxProduct2);

        // 测试用例3
        String[] words3 = {"a", "aa", "aaa", "aaaa"};
        int maxProduct3 = solution.maxProduct(words3);
        System.out.println("Max product of word lengths (Expected: 0): " + maxProduct3);
    }

}

/**
Given a string array words, return the maximum value of length(word[i]) * 
length(word[j]) where the two words do not share common letters. If no such two words 
exist, return 0. 

 
 Example 1: 

 
Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
 

 Example 2: 

 
Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
 

 Example 3: 

 
Input: words = ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 

 
 Constraints: 

 
 2 <= words.length <= 1000 
 1 <= words[i].length <= 1000 
 words[i] consists only of lowercase English letters. 
 

 Related Topics Array String Bit Manipulation 👍 3498 👎 139

*/
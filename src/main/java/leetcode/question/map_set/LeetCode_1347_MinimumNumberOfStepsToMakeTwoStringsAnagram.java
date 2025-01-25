package leetcode.question.map_set;

/**
 *@Question:  1347. Minimum Number of Steps to Make Two Strings Anagram
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 69.53%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 这道题的解题思路比较简单：
 *
 * 1. 首先，我们需要统计两个字符串中每个字符的出现频率。
 * 2. 对于字符串 t 中的每个字符，我们增加其出现频率。
 * 3. 对于字符串 s 中的每个字符，我们减少其出现频率。
 * 4. 统计每个字符的频率差值，这个差值表示我们需要转换的次数，因为我们想要使得两个字符串成为字母异位词，因此字符的频率应该是相同的。
 * 5. 最后，对于每个字符的频率差值，如果大于零，表示 t 中字符的频率大于 s 中字符的频率，我们需要将多余的字符转换成 s 中的字符，转换的次数即为频率差值；
 * 如果小于等于零，表示 t 中字符的频率小于等于 s 中字符的频率，这些字符无需转换，不需要额外的步骤。
 *
 * 时间复杂度：假设字符串长度为 N，统计频率需要遍历每个字符，因此时间复杂度为 O(N)。
 *
 * 空间复杂度：我们使用了一个大小为 26 的数组来存储字符的频率，因此空间复杂度为 O(1)。
 */
public class LeetCode_1347_MinimumNumberOfStepsToMakeTwoStringsAnagram{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSteps(String s, String t) {
            // 用于存储每个字符的出现频率的数组
            int[] count = new int[26];
            // 遍历字符串 s 和 t，统计每个字符的出现频率差值
            for (int i = 0; i < s.length(); i++) {
                // 将 t 中字符的频率加一
                count[t.charAt(i) - 'a']++;
                // 将 s 中字符的频率减一
                count[s.charAt(i) - 'a']--;
            }
            System.out.println(new ArrayList<>(Arrays.asList(count)));

            int ans = 0;
            // 计算使得两个字符串成为字母异位词所需的最小步骤数
            for (int i = 0; i < 26; i++) {
                // 如果 count[i] 大于零，表示 t 中字符的频率大于 s 中字符的频率
                // 那么需要将多余的字符变成 s 中的字符
                // 这些字符转变所需的步骤数即为 count[i]
                // 如果 count[i] 小于等于零，表示 t 中字符的频率小于等于 s 中字符的频率
                // 这些字符无需转变，所以不需要额外的步骤
                ans += Math.max(0, count[i]);
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1347_MinimumNumberOfStepsToMakeTwoStringsAnagram().new Solution();
        // TO TEST
         solution.minSteps("leetcode", "practice");
    }
}

/**
You are given two strings of the same length s and t. In one step you can 
choose any character of t and replace it with another character. 

 Return the minimum number of steps to make t an anagram of s. 

 An Anagram of a string is a string that contains the same characters with a 
different (or the same) ordering. 

 
 Example 1: 

 
Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.

 

 Example 2: 

 
Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters 
to make t anagram of s.
 

 Example 3: 

 
Input: s = "anagram", t = "mangaar"
Output: 0
Explanation: "anagram" and "mangaar" are anagrams. 
 

 
 Constraints: 

 
 1 <= s.length <= 5 * 10⁴ 
 s.length == t.length 
 s and t consist of lowercase English letters only. 
 

 Related Topics Hash Table String Counting 👍 2691 👎 117

*/
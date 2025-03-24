package leetcode.question.map_set;

import java.util.HashSet;
import java.util.Set;

/**
 *@Question:  1930. Unique Length-3 Palindromic Subsequences
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(26N)
 *@Space Complexity: O(1)
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 1930 - Unique Length-3 Palindromic Subsequences
 *
 * 给定一个字符串 s，只包含小写英文字母，要求找出所有 **长度为 3 且为回文** 的子序列（不要求连续），
 * 并返回这些子序列中 **不同子序列的个数**。
 *
 * 子序列定义：可以从原字符串中删除任意字符（不一定连续）得到的新字符串。
 * 回文定义：正着读和反着读一样的字符串。
 *
 * 题目特别说明了，长度必须为 3，格式为 (x, y, x)，其中 x 和 y 是字符，x 是首尾字符，y 是中间字符。
 *
 * 示例：
 * 输入：s = "aabca"
 * 输出：3
 * 解释：长度为3的回文子序列有 "aba", "aaa", "aca"，共3个不同子序列。
 *
 * -------------------------------------------------------------------
 *
 * 第二步：解题思路（基于代码的详细讲解 + 每一步举例）
 * -------------------------------------------------------------------
 * ✅ 思路核心：枚举所有可能作为首尾的字符 x（即 'a'-'z'），
 * 然后找出该字符第一次和最后一次在字符串中出现的位置，
 * 统计这两个位置之间所有可能作为中间字符 y 的字符种类数量，
 * 最后将所有合法的 (x, y, x) 子序列个数加总。
 *
 * 步骤详解：
 *
 * 1. 首先遍历字符串，将所有出现过的字符放入 HashSet 中，目的是只枚举可能成为回文两端的字符 x。
 *    举例：s = "aabca"，出现过的字符是 {a, b, c}
 *
 * 2. 对于每一个字符 letter ∈ 出现过的字符集合（例如 a、b、c），我们尝试让它成为回文的首尾字符。
 *
 * 3. 在字符串中寻找 letter 第一次出现的位置 i 和最后一次出现的位置 j。
 *    如果 i >= j，则跳过（因为无法形成长度为 3 的子序列）。
 *    举例：s = "aabca"，letter = 'a'，则 i = 0，j = 4。
 *
 * 4. 从位置 i+1 到 j-1 之间的所有字符都可以成为回文子序列的中间字符 y。
 *    我们将这些字符加入一个 Set 中以去重。
 *    举例：i = 0, j = 4，s[i+1...j-1] = "abc"，其中 'b' 和 'c' 都可作为中间字符 y。
 *
 * 5. 每个唯一的中间字符 y，与当前首尾字符 letter 可以组成一个唯一的长度为 3 的回文子序列 (letter, y, letter)。
 *    因此，最终答案累加 between.size()。
 *
 * 6. 遍历所有可能作为首尾字符的 letter，并累加对应 between.size()，即可得出答案。
 *
 * 时间复杂度控制得很好，因为对每个字符最多做一次遍历中间区间。
 *
 * -------------------------------------------------------------------
 *
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * 时间复杂度：O(N)
 * - 遍历字符串一次收集字符：O(N)
 * - 枚举最多 26 个字符（常数），每次找首末位置 O(N)
 * - 总体最多 O(26 * N) = O(N)
 *
 * 空间复杂度：O(1)
 * - 因为字母只有 26 个，Set 最多存 26 个字符，空间是常数级的。
 * - 中间使用的集合 between 也至多存 26 个字符。
 * - 不开辟额外的数组或矩阵。
 */


public class LeetCode_1930_UniqueLength3PalindromicSubsequences{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countPalindromicSubsequence(String s) {
            // 创建一个集合，存储字符串中出现过的所有字符
            Set<Character> letters = new HashSet();
            // 遍历字符串，将每个字符加入到集合中（去重）
            for (Character c: s.toCharArray()) {
                letters.add(c);
            }

            // 初始化答案为 0
            int ans = 0;
            // 遍历每一个唯一的字符，作为回文串的两端（第一个和第三个字符）
            for (Character letter : letters) {
                // i 表示当前字符第一次出现的位置，初始化为 -1
                int i = -1;
                // j 表示当前字符最后一次出现的位置
                int j = 0;

                // 遍历字符串，寻找当前字符 letter 的首次和末次出现位置
                for (int k = 0; k < s.length(); k++) {
                    if (s.charAt(k) == letter) {
                        // 如果是第一次出现，记录位置
                        if (i == -1) {
                            i = k;
                        }

                        // 始终更新为当前字符出现的位置，最终得到最后一次出现的位置
                        j = k;
                    }
                }

                // 创建一个集合用于存储 i 和 j 之间的所有不同字符（这些字符是回文串的中间字符）
                Set<Character> between = new HashSet();
                // 遍历 i 和 j 之间的字符并加入到 between 集合中
                for (int k = i + 1; k < j; k++) {
                    between.add(s.charAt(k));
                }

                // 每个不同的中间字符都可以和两端 letter 组成一个长度为3的回文子序列
                ans += between.size();
            }

            // 返回总共找到的不同长度为3的回文子序列个数
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1930_UniqueLength3PalindromicSubsequences().new Solution();

        // 测试用例1：输入字符串 "aabca"
        // 有效的回文子序列: "aba", "aaa", "aca"
        // 预期输出为 3
        System.out.println(solution.countPalindromicSubsequence("aabca")); // 输出: 3

        // 测试用例2：输入字符串 "adc"
        // 没有符合条件的回文子序列，输出应该为 0
        System.out.println(solution.countPalindromicSubsequence("adc")); // 输出: 0

        // 测试用例3：输入字符串 "bbcbaba"
        // 回文子序列包括: "bbb", "bab", "bcb", "aba"
        // 预期输出为 4
        System.out.println(solution.countPalindromicSubsequence("bbcbaba")); // 输出: 4
    }
}

/**
Given a string s, return the number of unique palindromes of length three that 
are a subsequence of s. 

 Note that even if there are multiple ways to obtain the same subsequence, it 
is still only counted once. 

 A palindrome is a string that reads the same forwards and backwards. 

 A subsequence of a string is a new string generated from the original string 
with some characters (can be none) deleted without changing the relative order of 
the remaining characters. 

 
 For example, "ace" is a subsequence of "abcde". 
 

 
 Example 1: 

 
Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
 

 Example 2: 

 
Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
 

 Example 3: 

 
Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")
 

 
 Constraints: 

 
 3 <= s.length <= 10⁵ 
 s consists of only lowercase English letters. 
 

 Related Topics Hash Table String Bit Manipulation Prefix Sum 👍 2506 👎 101

*/
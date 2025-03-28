package leetcode.question.string_list;

/**
 *@Question:  1400. Construct K Palindrome Strings
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 78.18%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 1400 - Construct K Palindrome Strings（构造 K 个回文字符串）
 *
 * 给定一个字符串 s 和一个整数 k，问是否可以使用 s 中的所有字符，构造出恰好 k 个非空的回文字符串。
 * 要求每个字符都只能使用一次，且必须全部用完。
 *
 * 返回值为布尔值：true 表示可以构造，false 表示无法构造。
 *
 * 示例：
 * 输入：s = "annabelle", k = 2
 * 输出：true
 * 解释：可以构造出 "anna" 和 "elble"（eblle 中 "b" 是中心）
 *
 * 输入：s = "leetcode", k = 3
 * 输出：false
 *
 * -------------------------------------------------------------------
 *
 * 第二步：解题思路（基于代码的超级详细讲解 + 每一步举例说明）
 * -------------------------------------------------------------------
 * ✅ 核心思路：统计奇数字符数量，判断是否小于等于 k
 *
 * 回文字符串的结构特点：
 * - 如果一个字符出现偶数次，它可以对称分布在回文串两边；
 * - 如果一个字符出现奇数次，它只能有一个字符出现在回文中间，其余字符成对出现。
 *
 * 那么对于 s 中的所有字符：
 * - 有 oddCount 个字符出现奇数次，意味着我们至少需要 oddCount 个回文串来容纳这些“中间字符”；
 * - 如果 oddCount ≤ k，说明我们有足够的回文串容纳奇数字符，其余字符可以任意组合；
 * - 如果 s 的总长度 < k，说明字符串不够分成 k 个非空串，返回 false。
 *
 * ✅ 步骤详解：
 * 1. 如果 s.length() < k，直接返回 false（字符数不够构造 k 个非空串）；
 * 2. 遍历字符串，统计每个字符的出现次数；
 * 3. 遍历频率数组，统计有多少个字符出现奇数次（oddCount）；
 * 4. 如果 oddCount ≤ k，返回 true，否则返回 false；
 *
 * ✅ 举例说明：
 * 示例 1：s = "aabb", k = 2
 * - 频率统计：a=2, b=2（都是偶数）
 * - oddCount = 0，说明没有中间字符
 * - 可以构造两个回文串如 "aa" 和 "bb" → true
 *
 * 示例 2：s = "abc", k = 2
 * - 频率统计：a=1, b=1, c=1（全是奇数）
 * - oddCount = 3 > 2 → 至少需要 3 个回文串才行 → false
 *
 * 示例 3：s = "abc", k = 3
 * - oddCount = 3，刚好等于 k → true，可构造 "a", "b", "c"
 *
 * 示例 4：s = "aaa", k = 2
 * - a=3 → oddCount=1
 * - 1 ≤ 2 → true，例如 "a" + "aa"
 *
 * -------------------------------------------------------------------
 *
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * 时间复杂度：O(N)
 * - N 是字符串 s 的长度
 * - 遍历字符串统计频率：O(N)
 * - 遍历 26 个字符统计奇数频率：O(26) ≈ O(1)
 *
 * 空间复杂度：O(1)
 * - 使用一个固定大小的整型数组 freq[26]，与输入长度无关，属于常数空间
 */


public class LeetCode_1400_ConstructKPalindromeStrings{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean canConstruct(String s, int k) {
            // 处理边界情况：如果字符数量少于 k 个，无法构建 k 个非空回文串
            if (s.length() < k) return false;

            // 如果字符串长度刚好等于 k，每个字符都可以单独构成回文（单字母是回文）
            if (s.length() == k) return true;

            // 用于统计每个小写字母的出现次数
            int[] freq = new int[26];
            // 记录有多少个字符出现了奇数次
            int oddCount = 0;

            // 遍历字符串，统计每个字符出现的次数
            for (char chr : s.toCharArray()) {
                freq[chr - 'a']++;
            }

            // 统计有多少个字符出现了奇数次（它们至少要单独放在一个回文串中间）
            for (int count : freq) {
                if (count % 2 == 1) {
                    oddCount++;
                }
            }

            // 如果奇数字符的个数 ≤ k，就可以构建出 k 个回文字符串
            return oddCount <= k;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1400_ConstructKPalindromeStrings().new Solution();

        // ✅ 测试用例 1：可以构成两个回文子串，如 "aa" 和 "bb"
        System.out.println(solution.canConstruct("aabb", 2)); // true

        // ✅ 测试用例 2：一个字符不能构成两个回文
        System.out.println(solution.canConstruct("a", 2)); // false

        // ✅ 测试用例 3：每个字符出现一次，但 k 正好等于长度
        System.out.println(solution.canConstruct("abc", 3)); // true

        // ✅ 测试用例 4：最多只能构造 1 个回文，因为有两个字符出现奇数次
        System.out.println(solution.canConstruct("abc", 1)); // false

        // ✅ 测试用例 5：回文字符串但 k 比长度小，仍合法（把偶数部分组合）
        System.out.println(solution.canConstruct("aaa", 2)); // true

        // ✅ 测试用例 6：空字符串或 k=0 的边界情况（题目假设 s 非空，这里补充逻辑验证）
        System.out.println(solution.canConstruct("abc", 0)); // false
    }
}

/**
Given a string s and an integer k, return true if you can use all the 
characters in s to construct non-empty k palindrome strings or false otherwise. 

 
 Example 1: 

 
Input: s = "annabelle", k = 2
Output: true
Explanation: You can construct two palindromes using all characters in s.
Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + 
"b"
 

 Example 2: 

 
Input: s = "leetcode", k = 3
Output: false
Explanation: It is impossible to construct 3 palindromes using all the 
characters of s.
 

 Example 3: 

 
Input: s = "true", k = 4
Output: true
Explanation: The only possible solution is to put each character in a separate 
string.
 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s consists of lowercase English letters. 
 1 <= k <= 10⁵ 
 

 Related Topics Hash Table String Greedy Counting 👍 1748 👎 158

*/
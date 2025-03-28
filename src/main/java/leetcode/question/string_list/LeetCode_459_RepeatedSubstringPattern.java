package leetcode.question.string_list;

/**
 *@Question:  459. Repeated Substring Pattern
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 57.25%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 459 - Repeated Substring Pattern（重复的子字符串）
 *
 * 给定一个非空字符串 s，判断它是否可以由一个子字符串重复多次组成。
 * 换句话说，是否存在一个子字符串 sub，使得 s = sub + sub + ... + sub（重复若干次）。
 *
 * 示例 1：
 * 输入: s = "abab"
 * 输出: true
 * 解释: 子串 "ab" 重复 2 次组成 "abab"
 *
 * 示例 2：
 * 输入: s = "aba"
 * 输出: false
 *
 * 示例 3：
 * 输入: s = "abcabcabcabc"
 * 输出: true
 * 解释: 子串 "abc" 重复 4 次组成 "abcabcabcabc"
 *
 * -------------------------------------------------------------------
 *
 * 第二步：解题思路（基于代码的超级详细讲解 + 每一步举例说明）
 * -------------------------------------------------------------------
 * ✅ 思路核心：字符串拼接 + 去头去尾 + 包含判断
 *
 * 目标是判断字符串 s 是否可以由一个重复子串构成。直观上，我们可以枚举所有可能的子串，然后验证是否可以重复组成原字符串。但这种方法时间复杂度较高。
 *
 * 本题采用了一种非常巧妙的技巧：
 *
 * 1. 首先将字符串自身拼接一遍，形成一个新的字符串 t = s + s。
 *    例如：s = "abab"，则 t = "abababab"
 *
 * 2. 接着我们将 t 的首字符和尾字符去掉，形成 t'：
 *    t' = t.substring(1, t.length() - 1)
 *    t' = "bababa"
 *
 * 3. 如果在这个新字符串 t' 中，能找到原字符串 s，则说明 s 是由重复子串构成的。
 *    原理：若 s 是由重复子串组成的，则 s + s 的中间部分一定包含 s 本身。
 *    如果不是重复组成的，那么去掉首尾之后一定无法再形成 s。
 *
 * ✅ 举例说明：
 *
 * - 示例 1：s = "abab"
 *   t = "abababab"
 *   去掉首尾 → "bababa"
 *   "bababa" 包含 "abab" → 返回 true
 *
 * - 示例 2：s = "abc"
 *   t = "abcabc"
 *   去掉首尾 → "bcab"
 *   "bcab" 不包含 "abc" → 返回 false
 *
 * - 示例 3：s = "aaaa"
 *   t = "aaaaaaaa"
 *   去掉首尾 → "aaaaaa"
 *   "aaaaaa" 包含 "aaaa" → 返回 true
 *
 * ✅ 方法优点：
 * - 只使用字符串操作，避免手动枚举所有子串，逻辑简洁高效。
 *
 * -------------------------------------------------------------------
 *
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * 时间复杂度：O(N)
 * - 字符串拼接：O(N)
 * - substring 操作：O(N)
 * - contains 操作：最坏 O(N)
 * - 所以整体是 O(N)，其中 N 为字符串长度
 *
 * 空间复杂度：O(N)
 * - 创建了新字符串 t = s + s，占用 O(N)
 * - substring 生成新字符串（在某些语言中可能是共享底层存储），Java 中通常会复制新字符数组，因此空间为 O(N)
 */


public class LeetCode_459_RepeatedSubstringPattern{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean repeatedSubstringPattern(String s) {
            // 构造一个新字符串：原字符串自身拼接，即 t = s + s
            String t = s + s;

            // 判断：将 t 的首尾各去掉一个字符，看中间是否包含原字符串 s
            // 如果包含，说明 s 是由某个重复子串构成的
            if (t.substring(1, t.length() - 1).contains(s))
                return true;

            // 否则不是重复子串模式
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_459_RepeatedSubstringPattern().new Solution();

        // ✅ 测试用例 1：由 "ab" 重复组成
        System.out.println(solution.repeatedSubstringPattern("abab")); // true

        // ✅ 测试用例 2：单字符重复
        System.out.println(solution.repeatedSubstringPattern("aaaa")); // true

        // ✅ 测试用例 3：不能由子串重复组成
        System.out.println(solution.repeatedSubstringPattern("abc")); // false

        // ✅ 测试用例 4：较长的重复模式
        System.out.println(solution.repeatedSubstringPattern("abcabcabcabc")); // true

        // ✅ 测试用例 5：一个字符
        System.out.println(solution.repeatedSubstringPattern("a")); // false

        // ✅ 测试用例 6：复杂但仍可重复
        System.out.println(solution.repeatedSubstringPattern("xyzxyz")); // true
    }
}

/**
Given a string s, check if it can be constructed by taking a substring of it 
and appending multiple copies of the substring together. 

 
 Example 1: 

 
Input: s = "abab"
Output: true
Explanation: It is the substring "ab" twice.
 

 Example 2: 

 
Input: s = "aba"
Output: false
 

 Example 3: 

 
Input: s = "abcabcabcabc"
Output: true
Explanation: It is the substring "abc" four times or the substring "abcabc" 
twice.
 

 
 Constraints: 

 
 1 <= s.length <= 10⁴ 
 s consists of lowercase English letters. 
 

 Related Topics String String Matching 👍 6581 👎 542

*/
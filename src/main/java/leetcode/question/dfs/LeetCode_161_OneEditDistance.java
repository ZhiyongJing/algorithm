package leetcode.question.dfs;
/**
 *@Question:  161. One Edit Distance
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.72999999999999%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 题目：161. One Edit Distance
 *
 * 给定两个字符串 `s` 和 `t`，编写一个函数判断它们是否只有一个编辑操作的距离。
 *
 * 编辑操作包含以下三种：
 * 1. 插入一个字符。
 * 2. 删除一个字符。
 * 3. 替换一个字符。
 *
 * ### 解题思路：
 *
 * #### 1. **初步分析**：
 * 如果两个字符串之间的长度差大于1，那么它们肯定不可能通过一次编辑操作变得相同。例如，`s = "abc"` 和 `t = "abcde"`，长度差超过了1，必定需要多个操作。因此，当长度差超过1时，直接返回 `false`。
 *
 * #### 2. **字符串长度相等的情况**：
 * 如果 `s` 和 `t` 的长度相等，唯一可能的编辑操作就是**替换一个字符**。例如：
 * - `s = "abc"` 和 `t = "abx"`，它们只有一个字符不同，通过一次替换操作就能使它们相等。
 *
 * 对于长度相等的字符串，我们逐个字符比较，找到第一个不同的字符后，检查该字符后面的子字符串是否相等。如果相等，则说明只有一个字符不同，可以通过一次替换使它们相等。
 *
 * #### 3. **字符串长度不同的情况**：
 * 如果 `s` 和 `t` 的长度差为1，唯一可能的编辑操作是**插入或删除一个字符**。例如：
 * - `s = "abc"` 和 `t = "abcd"`，可以通过在 `s` 末尾插入字符 `d` 来使它们相等。
 * - `s = "abc"` 和 `t = "ab"`，可以通过删除 `s` 中的字符 `c` 来使它们相等。
 *
 * 在这种情况下，我们逐个字符比较，找到第一个不同的字符后，检查 `s` 当前字符之后的子字符串是否等于 `t` 剩余部分的子字符串。如果相等，则可以通过插入或删除使它们相等。
 *
 * #### 4. **特殊情况**：
 * 如果遍历完 `s` 的所有字符后仍未发现不同的字符，则 `t` 必须比 `s` 多一个字符，此时它们是通过一次插入操作（在 `s` 末尾插入字符）相等的。
 *
 * ### 步骤总结：
 * 1. 检查字符串长度差是否大于1，如果是则直接返回 `false`。
 * 2. 逐字符比较 `s` 和 `t`：
 *    - 如果长度相等，检查是否能通过替换一个字符使它们相等。
 *    - 如果长度差为1，检查是否能通过插入或删除一个字符使它们相等。
 * 3. 若遍历到末尾都没有发现不同，则检查 `t` 是否比 `s` 多一个字符。
 *
 * ### 时间复杂度分析：
 *
 * - **时间复杂度**：O(N)，其中 N 是较短的字符串 `s` 的长度。我们只需遍历较短字符串 `s` 的字符，每次比较对应字符，最坏情况是遍历完所有字符。
 *
 * - **空间复杂度**：O(1)。我们只需要常量级的空间存储几个变量，并不需要额外的空间来存储复杂的数据结构。
 *
 * ### 例子：
 * 1. `s = "abc", t = "abcd"`：可以通过插入一个字符使两者相等，返回 `true`。
 * 2. `s = "abc", t = "abx"`：可以通过替换一个字符使两者相等，返回 `true`。
 * 3. `s = "abc", t = "ab"`：可以通过删除一个字符使两者相等，返回 `true`。
 * 4. `s = "abc", t = "abcd"`：长度差超过1，直接返回 `false`。
 */

public class LeetCode_161_OneEditDistance{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 判断字符串 s 和 t 是否只有一个编辑距离
        public boolean isOneEditDistance(String s, String t) {
            int ns = s.length();  // 获取字符串 s 的长度
            int nt = t.length();  // 获取字符串 t 的长度

            // 保证字符串 s 总是比 t 短（若不是则交换 s 和 t）
            if (ns > nt) return isOneEditDistance(t, s);

            // 如果两个字符串长度差超过 1，直接返回 false
            if (nt - ns > 1) return false;

            // 遍历较短的字符串 s
            for (int i = 0; i < ns; i++) {
                // 如果在某一位置字符不相同
                if (s.charAt(i) != t.charAt(i)) {
                    if (ns == nt) {
                        // 如果长度相同，判断 s 和 t 剩余部分是否相同
                        return s.substring(i + 1).equals(t.substring(i + 1));
                    } else {
                        // 如果长度不同，判断 s 从当前位置开始和 t 的下一个字符开始的子串是否相同
                        return s.substring(i).equals(t.substring(i + 1));
                    }
                }
            }

            // 如果遍历完了 s 都没有发现不同的字符，
            // 则判断 t 是否刚好多一个字符，这种情况下也算只有一个编辑距离
            return (ns + 1 == nt);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_161_OneEditDistance().new Solution();
        // 测试用例1: s = "abc", t = "abcd"，返回 true，因为 t 只比 s 多了一个字符
        System.out.println(solution.isOneEditDistance("abc", "abcd")); // 输出: true

        // 测试用例2: s = "abc", t = "abx"，返回 true，因为 t 只在一个位置不同
        System.out.println(solution.isOneEditDistance("abc", "abx")); // 输出: true

        // 测试用例3: s = "abc", t = "ab", 返回 true，因为 t 只比 s 少了一个字符
        System.out.println(solution.isOneEditDistance("abc", "ab")); // 输出: true

        // 测试用例4: s = "abc", t = "abcde"，返回 false，因为两个字符串相差超过一个编辑距离
        System.out.println(solution.isOneEditDistance("abc", "abcde")); // 输出: false

        // 测试用例5: s = "abc", t = "ac"，返回 false，因为两个字符串相差两个编辑距离
        System.out.println(solution.isOneEditDistance("abc", "ac")); // 输出: false
    }
}

/**
Given two strings s and t, return true if they are both one edit distance apart,
 otherwise return false. 

 A string s is said to be one distance apart from a string t if you can: 

 
 Insert exactly one character into s to get t. 
 Delete exactly one character from s to get t. 
 Replace exactly one character of s with a different character to get t. 
 

 
 Example 1: 

 
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
 

 Example 2: 

 
Input: s = "", t = ""
Output: false
Explanation: We cannot get t from s by only one step.
 

 
 Constraints: 

 
 0 <= s.length, t.length <= 10⁴ 
 s and t consist of lowercase letters, uppercase letters, and digits. 
 

 Related Topics Two Pointers String 👍 1404 👎 190

*/
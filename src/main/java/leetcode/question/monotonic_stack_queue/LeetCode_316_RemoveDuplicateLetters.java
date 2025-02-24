package leetcode.question.monotonic_stack_queue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 *@Question:  316. Remove Duplicate Letters
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 63.72%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)You can only add to stack if an element has not been seen, so stack also only consists of unique elements.
 * This means that both stack and seen are bounded by constant, giving us O(1) space complexity.
 */
/**
 * 题目描述：
 * 316. Remove Duplicate Letters
 *
 * 给定一个字符串 `s`，其中只包含小写字母。要求**去除重复字符**，使得**结果字符串的字典序最小**。
 *
 * **要求**
 * - 结果字符串必须包含 `s` 中所有的 **不同字符**（去重）。
 * - 结果字符串必须是 `s` **的子序列**（字符顺序不能变）。
 * - 在所有可能的结果字符串中，**字典序最小** 的字符串作为最终结果。
 *
 * **示例 1**
 * ```plaintext
 * 输入: s = "bcabc"
 * 输出: "abc"
 * 解释: 原字符串中有 'a', 'b', 'c'，去重并按字典序排列得到 "abc"。
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: s = "cbacdcbc"
 * 输出: "acdb"
 * 解释: "acdb" 是满足要求的最小字典序子序列。
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * 采用 **单调栈 + 贪心算法**，保证：
 * 1. **栈中的字符按照字典序排列**
 * 2. **栈中的字符不重复**
 * 3. **如果栈顶字符的后面还有出现，则可以移除，以便后续加入更优解**
 *
 * **步骤**
 * 1. **统计字符的最后出现位置**：
 *    - 使用 `last_occurrence` 记录 `s[i]` 在字符串 `s` 中的最后出现索引。
 * 2. **遍历字符串 `s`**：
 *    - 只在 `stack` **未包含当前字符** 时，才考虑添加 `s[i]`。
 *    - 在 `stack` **不为空** 且 **栈顶字符比当前字符大** 且 **栈顶字符在后续仍会出现** 时：
 *      - 弹出栈顶字符（为了获得更小字典序）。
 * 3. **构建最终答案**
 *    - 遍历 `stack`，将字符拼接成最终字符串。
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `s = "cbacdcbc"`
 *
 * **1. 计算 `last_occurrence`**
 * ```plaintext
 * last_occurrence = {c:7, b:6, a:2, d:4}
 * ```
 *
 * **2. 遍历 `s`，维护 `stack`**
 * ```plaintext
 * i = 0, c -> 栈: [c]                 （c 未在栈中）
 * i = 1, b -> 栈: [b]                 （c > b，且 c 还会出现，移除 c）
 * i = 2, a -> 栈: [a]                 （b > a，且 b 还会出现，移除 b）
 * i = 3, c -> 栈: [a, c]              （c 未在栈中）
 * i = 4, d -> 栈: [a, c, d]           （d 未在栈中）
 * i = 5, c -> 忽略（c 已在栈中）
 * i = 6, b -> 栈: [a, c, d, b]        （b 未在栈中）
 * i = 7, c -> 忽略（c 已在栈中）
 * ```
 * **最终结果:** `"acdb"`
 *
 * ---
 *
 * **时间复杂度分析**
 * - **遍历 `s` 计算 `last_occurrence`：O(N)**
 * - **遍历 `s` 处理 `stack`：O(N)**
 * - **构建最终字符串：O(N)**
 * - **总时间复杂度：O(N)**
 *
 * **空间复杂度分析**
 * - **哈希表 `last_occurrence`：O(26) = O(1)**
 * - **哈希集合 `seen`：O(26) = O(1)**
 * - **栈 `stack`：最多存储 26 个字符 = O(1)**
 * - **最终答案字符串 `O(N)`**
 * - **总空间复杂度：O(1)**（只存储最多 26 个字符）
 */


public class LeetCode_316_RemoveDuplicateLetters{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicateLetters(String s) {

            // 使用单调栈来维护最终的去重且字典序最小的字符串
            Stack<Character> stack = new Stack<>();

            // 用于记录某个字符是否已经加入栈中，避免重复
            HashSet<Character> seen = new HashSet<>();

            // 记录每个字符的最后出现位置
            HashMap<Character, Integer> last_occurrence = new HashMap<>();
            for(int i = 0; i < s.length(); i++) last_occurrence.put(s.charAt(i), i);

            // 遍历字符串
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);

                // 只有当前字符还未在栈中时，才可以尝试添加
                if (!seen.contains(c)){
                    // 如果栈顶元素：
                    //  1. 存在
                    //  2. 字典序比当前字符大
                    //  3. 后续还会再次出现（可以被替换）
                    // 那么可以将栈顶字符移除，以便得到更小的字典序
                    while(!stack.isEmpty() && c < stack.peek() && last_occurrence.get(stack.peek()) > i){
                        seen.remove(stack.pop()); // 移除栈顶元素，并更新 `seen` 记录
                    }
                    // 将当前字符添加到栈中，并标记为已使用
                    seen.add(c);
                    stack.push(c);
                }
            }

            // 构建最终结果
            StringBuilder sb = new StringBuilder(stack.size());
            for (Character c : stack) sb.append(c.charValue());
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_316_RemoveDuplicateLetters().new Solution();

        // 测试样例 1
        String s1 = "bcabc";
        System.out.println(solution.removeDuplicateLetters(s1)); // 预期输出: "abc"

        // 测试样例 2
        String s2 = "cbacdcbc";
        System.out.println(solution.removeDuplicateLetters(s2)); // 预期输出: "acdb"

        // 测试样例 3
        String s3 = "abacb";
        System.out.println(solution.removeDuplicateLetters(s3)); // 预期输出: "abc"

        // 测试样例 4
        String s4 = "bbcaac";
        System.out.println(solution.removeDuplicateLetters(s4)); // 预期输出: "bac"
    }
}

/**
Given a string s, remove duplicate letters so that every letter appears once 
and only once. You must make sure your result is the smallest in lexicographical 
order among all possible results. 

 
 Example 1: 

 
Input: s = "bcabc"
Output: "abc"
 

 Example 2: 

 
Input: s = "cbacdcbc"
Output: "acdb"
 

 
 Constraints: 

 
 1 <= s.length <= 10⁴ 
 s consists of lowercase English letters. 
 

 
 Note: This question is the same as 1081: https://leetcode.com/problems/
smallest-subsequence-of-distinct-characters/ 

 Related Topics String Stack Greedy Monotonic Stack 👍 8854 👎 653

*/
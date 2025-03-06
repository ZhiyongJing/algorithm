package leetcode.question.two_pointer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *@Question:  267. Palindrome Permutation II
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time Complexity: O((N/2 + 1)!)
 *  - 最多需要生成 (N/2)! 种排列，在最坏情况下。
 *  - 另外，每次生成的排列都需要调用 `string.reverse()`，时间复杂度为 O(N/4)。
 *@Space Complexity: O(N)
 *  - 主要是用于存储字符频率的哈希表和回溯递归调用栈的额外空间。
 */
/**
 * 题目描述：
 * LeetCode 267. Palindrome Permutation II
 * 给定一个字符串 `s`（可能包含重复字符），返回所有可以**重新排列**形成的**不同回文字符串**。
 *
 * 约束：
 * - 如果 `s` 不能重新排列成回文，则返回空列表 `[]`。
 * - 结果列表中的字符串必须是**唯一的**（不含重复项）。
 *
 * 示例 1：
 * 输入: s = "aabb"
 * 输出: ["abba", "baab"]
 *
 * 示例 2：
 * 输入: s = "abc"
 * 输出: []
 * 解释: 不能组成回文。
 *
 * 示例 3：
 * 输入: s = "aaa"
 * 输出: ["aaa"]
 *
 * 示例 4：
 * 输入: s = "aabbcc"
 * 输出: ["abccba", "acbbca", "baacca", "bcaacb", "cbaabc", "cabbac"]
 */

/**
 * 解题思路：
 * 该问题的核心思想是**先判断是否可以组成回文，再使用回溯生成所有唯一的回文排列**。
 * 主要步骤如下：
 *
 * 1. **判断是否能形成回文**
 *    - 统计 `s` 中每个字符的出现次数，存入 `map[]`（ASCII 128 长度的数组）。
 *    - 计算有多少个字符的出现次数是**奇数**。
 *    - 若**超过 1 个字符的出现次数是奇数**，则无法构成回文，直接返回 `[]`。
 *    - 例如：
 *      - `"aabb"` 可以组成 `"abba"`，因为所有字符都成对出现。
 *      - `"abc"` 不能组成回文，因为 `a, b, c` 都出现 1 次，超过 1 个字符是奇数次。
 *
 * 2. **构造回文前半部分**
 *    - 遍历 `map[]`，取每个字符的一半次数存入 `st[]`（字符数组）。
 *    - 如果有**单个奇数次数的字符**，将其存入 `ch` 作为回文中心字符（仅限 1 个）。
 *    - 例如：
 *      - `"aabb"` → `st = ['a', 'b']`，无 `ch`
 *      - `"aabbc"` → `st = ['a', 'b']`，`ch = 'c'`
 *
 * 3. **生成所有唯一的回文排列**
 *    - 通过 `permute()` 递归生成 `st[]` 的所有唯一排列。
 *    - 对于每个排列 `st'`：
 *      - 组合成完整的回文 `st' + ch + reverse(st')`
 *      - 存入 `set`（避免重复）
 *    - 例如：
 *      - `st = ['a', 'b']` → 排列可能为 `["ab", "ba"]`
 *      - `ch = 'c'`（如果有） → 组合形成 `["abcba", "bacab"]`
 *    - 使用**交换法**生成全排列，避免 `Set` 排序带来的额外开销。
 *
 * 4. **返回结果**
 *    - 将 `set` 转换为 `List<String>` 返回。
 *
 * **举例分析**
 * 假设输入 `"aabb"`：
 * - 统计字符频率：`{a:2, b:2}`
 * - `count = 0`（没有奇数次字符）
 * - 取 `st = ['a', 'b']`
 * - 生成 `st` 的排列 `["ab", "ba"]`
 * - 形成回文 `["abba", "baab"]`
 * - **返回 `["abba", "baab"]`**
 *
 * 假设输入 `"aabbc"`：
 * - 统计字符频率：`{a:2, b:2, c:1}`
 * - `count = 1`（奇数次字符 `c`）
 * - 取 `st = ['a', 'b']`，`ch = 'c'`
 * - 生成 `st` 的排列 `["ab", "ba"]`
 * - 形成回文 `["abcba", "bacab"]`
 * - **返回 `["abcba", "bacab"]`**
 */

/**
 * 时间和空间复杂度分析：
 *
 * 1. **时间复杂度：O((N/2 + 1)!)**
 *    - 生成回文的关键在于**排列前半部分字符**，总共 `(N/2)!` 种排列。
 *    - 对于每个排列，需要 O(N/4) 时间反转字符串，因此总复杂度为 `O((N/2 + 1)!)`。
 *
 * 2. **空间复杂度：O(N)**
 *    - `map[]` 需要 `O(128) = O(1)` 额外空间存储字符频率。
 *    - `st[]` 需要 `O(N/2)` 空间存储回文前半部分字符。
 *    - 递归深度最多 `O(N/2)`，所以递归栈 `O(N/2)`。
 *    - 结果存储在 `Set<String>` 中，**最坏情况** `O(N!)`。
 *    - **整体空间复杂度 O(N)**，但当 `N` 很大时，结果存储可能增长至 `O(N!)`。
 */

public class LeetCode_267_PalindromePermutationIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 用于存储所有唯一的回文排列
        Set<String> set = new HashSet<>();

        public List<String> generatePalindromes(String s) {
            // 记录字符出现次数的数组（128 代表 ASCII 范围）
            int[] map = new int[128];

            // `st` 存储可用于生成回文前半部分的字符数组
            char[] st = new char[s.length() / 2];

            // 检查是否可以排列成回文，如果不能，则直接返回空列表
            if (!canPermutePalindrome(s, map)) return new ArrayList<>();

            // `ch` 用于存储可能存在的**单个中心字符**（当字符串长度为奇数时）
            char ch = 0;
            int k = 0;

            // 遍历 `map`，找出可用于回文构造的字符
            for (int i = 0; i < map.length; i++) {
                // 如果某个字符的出现次数是奇数，则它是回文的中心字符
                if (map[i] % 2 == 1) ch = (char) i;

                // 取一半的字符存入 `st`，用于生成前半部分的排列
                for (int j = 0; j < map[i] / 2; j++) {
                    st[k++] = (char) i;
                }
            }

            // 通过回溯生成所有唯一的前半部分排列
            permute(st, 0, ch);

            // 将所有可能的回文排列转换为列表返回
            return new ArrayList<String>(set);
        }

        public boolean canPermutePalindrome(String s, int[] map) {
            int count = 0;

            // 统计字符出现次数，并计算有多少个字符的出现次数是奇数
            for (int i = 0; i < s.length(); i++) {
                map[s.charAt(i)]++;

                // 如果某个字符出现偶数次，则 `count--`，否则 `count++`
                if (map[s.charAt(i)] % 2 == 0) count--;
                else count++;
            }

            // 只有 **最多一个字符出现次数是奇数** 时，才能构造回文
            return count <= 1;
        }

        public void swap(char[] s, int i, int j) {
            // 交换字符数组 `s` 中索引 `i` 和 `j` 的值
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

        void permute(char[] s, int l, char ch) {
            // 如果 `l` 达到 `s.length`，说明当前排列完成
            if (l == s.length) {
                // 生成完整的回文字符串，并加入 `set`
                set.add(
                        new String(s) + // 前半部分
                                (ch == 0 ? "" : ch) + // 中心字符（如果有）
                                new StringBuffer(new String(s)).reverse() // 反转前半部分得到后半部分
                );
            } else {
                // 回溯：尝试不同字符的排列
                for (int i = l; i < s.length; i++) {
                    // 避免生成重复排列
                    if (s[l] != s[i] || l == i) {
                        swap(s, l, i);
                        permute(s, l + 1, ch);
                        swap(s, l, i); // 回溯，恢复原数组状态
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_267_PalindromePermutationIi().new Solution();

        // 测试用例 1
        System.out.println(solution.generatePalindromes("aabb"));
        // 期望输出: ["abba", "baab"]

        // 测试用例 2
        System.out.println(solution.generatePalindromes("abc"));
        // 期望输出: [] (无法形成回文排列)

        // 测试用例 3
        System.out.println(solution.generatePalindromes("aaa"));
        // 期望输出: ["aaa"]

        // 测试用例 4
        System.out.println(solution.generatePalindromes("aabbcc"));
        // 期望输出: ["abccba", "acbbca", "baacca", "bcaacb", "cbaabc", "cabbac"]
    }
}

/**
Given a string s, return all the palindromic permutations (without duplicates) 
of it. 

 You may return the answer in any order. If s has no palindromic permutation, 
return an empty list. 

 
 Example 1: 
 Input: s = "aabb"
Output: ["abba","baab"]
 
 Example 2: 
 Input: s = "abc"
Output: []
 
 
 Constraints: 

 
 1 <= s.length <= 16 
 s consists of only lowercase English letters. 
 

 Related Topics Hash Table String Backtracking 👍 891 👎 97

*/
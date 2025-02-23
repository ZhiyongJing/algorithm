package leetcode.question.string_list;

import java.util.Stack;

/**
 *@Question:  1910. Remove All Occurrences of a Substring
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 65.12%
 *@Time  Complexity: O(N^2) for solution1, n is length of string s, O(N + M) for solution2
 *@Space Complexity: O(n) for solution1, O(n + m) for solution2
 */
/**
 * 题目描述：
 * 1910. Remove All Occurrences of a Substring
 * 给定一个字符串 `s` 和一个子字符串 `part`，要求不断移除 `s` 中所有出现的 `part`，
 * 直到 `s` 中不再包含 `part`，然后返回最终的字符串。
 *
 * 示例 1：
 * 输入: s = "daabcbaabcbc", part = "abc"
 * 输出: "dab"
 * 解释：
 * - "daabcbaabcbc" 移除 "abc" 变成 "dabaabcbc"
 * - "dabaabcbc" 移除 "abc" 变成 "dabcbc"
 * - "dabcbc" 移除 "abc" 变成 "dab"
 *
 * 示例 2：
 * 输入: s = "axxxxyyyyb", part = "xy"
 * 输出: "ab"
 * 解释：
 * - "axxxxyyyyb" 移除 "xy" 变成 "axxxyyyb"
 * - "axxxyyyb" 移除 "xy" 变成 "axxyyb"
 * - "axxyyb" 移除 "xy" 变成 "axyb"
 * - "axyb" 移除 "xy" 变成 "ab"
 *
 * 解题思路：
 * 1. **暴力方法（Solution 1）**
 *    - 直接使用 `s.contains(part)` 进行判断，每次发现 `part` 在 `s` 中出现时，就删除它。
 *    - 例如：
 *      - `s = "daabcbaabcbc"`, `part = "abc"`
 *      - `s.indexOf(part) = 2`，移除 `"abc"`，得到 `"dabaabcbc"`
 *      - `s.indexOf(part) = 5`，移除 `"abc"`，得到 `"dabcbc"`
 *      - `s.indexOf(part) = 3`，移除 `"abc"`，得到 `"dab"`
 *    - 该方法由于 `contains` 和 `substring` 操作，可能需要 O(N^2) 的时间复杂度。
 *
 * 2. **KMP + 栈方法（Solution 2）**
 *    - 预处理 `part` 的 **最长前缀后缀数组 (LPS)**，加速模式匹配过程。
 *    - 采用 **栈** 存储 `s` 中的字符，维护匹配 `part` 的状态。
 *    - 主要步骤：
 *      1. 遍历 `s`，依次将字符 `c` 压入栈 `charStack`。
 *      2. 记录 `part` 的匹配进度 `patternIndexes`，如果 `charStack` 末尾匹配到 `part`，则弹出 `part.length()` 个字符。
 *      3. 继续遍历 `s`，每次匹配失败时，利用 KMP 进行回溯加速匹配。
 *    - 例如：
 *      - `s = "daabcbaabcbc"`, `part = "abc"`
 *      - 遍历时，`charStack = ['d', 'a', 'a', 'b', 'c']`，匹配到 `"abc"`，弹出 `"abc"`
 *      - `charStack = ['d', 'a']`，继续遍历，`charStack = ['d', 'a', 'a', 'b', 'c']`，再次匹配 `"abc"`，弹出 `"abc"`
 *      - `charStack = ['d', 'a', 'b', 'c']`，最终移除 `"abc"` 后 `charStack = ['d', 'a', 'b']`
 *      - 结果为 `"dab"`
 *
 * 3. **计算 LPS 数组（用于 KMP 算法）**
 *    - LPS 记录 `part` 前缀匹配的长度，优化匹配过程：
 *      - `part = "abc"`, 计算 LPS：
 *        - `lps[0] = 0`
 *        - `lps[1] = 0`
 *        - `lps[2] = 0`
 *      - LPS 计算在 `O(M)` 时间内完成 (`M` 是 `part` 的长度)。
 *
 * 时间复杂度分析：
 * - **Solution 1（暴力法）**：
 *   - 每次 `contains()` 操作需要 O(N)，而 `substring()` 需要 O(N)。
 *   - 在最坏情况下，可能要执行 `N` 轮删除操作，总体复杂度为 **O(N^2)**。
 *
 * - **Solution 2（KMP + 栈方法）**：
 *   - 预处理 `part` 的 LPS 需要 **O(M)**。
 *   - 遍历 `s` 并使用 KMP 进行匹配，每个字符最多入栈出栈一次，复杂度 **O(N)**。
 *   - 总体复杂度 **O(N + M)**，比暴力法更高效。
 *
 * 空间复杂度分析：
 * - **Solution 1（暴力法）**：
 *   - 由于字符串 `s` 可能被多次创建，最坏情况下需要 **O(N)** 额外空间。
 *
 * - **Solution 2（KMP + 栈方法）**：
 *   - `charStack` 最多存储 `O(N)` 个字符。
 *   - `patternIndexes` 数组大小为 `O(N)`。
 *   - `lps` 数组大小为 `O(M)`。
 *   - 总体空间复杂度 **O(N + M)**。
 */


public class LeetCode_1910_RemoveAllOccurrencesOfASubstring{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // Solution 1: 直接暴力方法
        public String removeOccurrences1(String s, String part) {
            // 当 s 仍然包含子串 part 时，继续进行删除
            while (s.contains(part)) {
                // 找到 part 在 s 中的最左边索引
                int partStartIndex = s.indexOf(part);
                // 通过字符串拼接移除 part
                s = s.substring(0, partStartIndex) + s.substring(partStartIndex + part.length());
            }
            // 返回最终移除后的字符串
            return s;
        }

        // Solution 2: KMP 算法
        public String removeOccurrences(String s, String part) {
            // 预处理 KMP 的最长前缀后缀数组 (LPS)
            int[] kmpLPS = computeLongestPrefixSuffix(part);

            // 使用栈存储字符，以支持模式匹配
            Stack<Character> charStack = new Stack<>();

            // 记录模式匹配的索引
            int[] patternIndexes = new int[s.length() + 1];

            // 遍历字符串 s
            for (int strIndex = 0, patternIndex = 0; strIndex < s.length(); strIndex++) {
                char currentChar = s.charAt(strIndex);
                charStack.push(currentChar);

                if (currentChar == part.charAt(patternIndex)) {
                    // 记录当前匹配的模式索引
                    patternIndexes[charStack.size()] = ++patternIndex;

                    // 当匹配到完整的 part 后，从栈中移除该部分
                    if (patternIndex == part.length()) {
                        int remainingLength = part.length();
                        while (remainingLength != 0) {
                            charStack.pop();
                            remainingLength--;
                        }
                        // 恢复模式索引
                        patternIndex = charStack.isEmpty() ? 0 : patternIndexes[charStack.size()];
                    }
                } else {
                    if (patternIndex != 0) {
                        // 使用 KMP 进行回溯
                        strIndex--;
                        patternIndex = kmpLPS[patternIndex - 1];
                        charStack.pop();
                    } else {
                        // 重置匹配索引
                        patternIndexes[charStack.size()] = 0;
                    }
                }
            }

            // 将剩余字符转换为字符串
            StringBuilder result = new StringBuilder();
            while (!charStack.isEmpty()) {
                result.append(charStack.pop());
            }

            return result.reverse().toString();
        }

        // 计算 KMP 的最长前缀后缀数组 (LPS)
        private int[] computeLongestPrefixSuffix(String pattern) {
            int[] lps = new int[pattern.length()];
            for (int current = 1, prefixLength = 0; current < pattern.length();) {
                if (pattern.charAt(current) == pattern.charAt(prefixLength)) {
                    lps[current] = ++prefixLength;
                    current++;
                } else if (prefixLength != 0) {
                    prefixLength = lps[prefixLength - 1];
                } else {
                    lps[current] = 0;
                    current++;
                }
            }
            return lps;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1910_RemoveAllOccurrencesOfASubstring().new Solution();

        // 测试样例
        String s1 = "daabcbaabcbc";
        String part1 = "abc";
        System.out.println(solution.removeOccurrences(s1, part1)); // 预期输出: "dab"

        String s2 = "axxxxyyyyb";
        String part2 = "xy";
        System.out.println(solution.removeOccurrences(s2, part2)); // 预期输出: "ab"

        String s3 = "ababa";
        String part3 = "aba";
        System.out.println(solution.removeOccurrences(s3, part3)); // 预期输出: "b"
    }
}

/**
Given two strings s and part, perform the following operation on s until all 
occurrences of the substring part are removed: 

 
 Find the leftmost occurrence of the substring part and remove it from s. 
 

 Return s after removing all occurrences of part. 

 A substring is a contiguous sequence of characters in a string. 

 
 Example 1: 

 
Input: s = "daabcbaabcbc", part = "abc"
Output: "dab"
Explanation: The following operations are done:
- s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
- s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
- s = "dababc", remove "abc" starting at index 3, so s = "dab".
Now s has no occurrences of "abc".
 

 Example 2: 

 
Input: s = "axxxxyyyyb", part = "xy"
Output: "ab"
Explanation: The following operations are done:
- s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
- s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
- s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
- s = "axyb", remove "xy" starting at index 1 so s = "ab".
Now s has no occurrences of "xy".
 

 
 Constraints: 

 
 1 <= s.length <= 1000 
 1 <= part.length <= 1000 
 s and part consists of lowercase English letters. 
 

 Related Topics String Stack Simulation 👍 2428 👎 82

*/
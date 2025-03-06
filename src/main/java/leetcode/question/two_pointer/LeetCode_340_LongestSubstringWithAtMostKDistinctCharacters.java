package leetcode.question.two_pointer;

import java.util.HashMap;
import java.util.Map;

/**
  *@Question:  340. Longest Substring with At Most K Distinct Characters     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 39.33%      
  *@Time  Complexity: O(N)n be the length of the input string and k be the maximum number of distinct characters.
  *@Space Complexity: O(K)
 */

/**
 * 题目描述：
 * 340. Longest Substring with At Most K Distinct Characters（最多包含 K 个不同字符的最长子串）
 *
 * 给定一个字符串 `s` 和一个整数 `k`，求字符串 `s` 中**最多**包含 `k` 个不同字符的最长子串的长度。
 *
 * **示例 1**
 * ```plaintext
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 最长的子串是 "ece"，它包含最多 2 个不同的字符。
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: s = "aa", k = 1
 * 输出: 2
 * 解释: 最长的子串是 "aa"，它包含最多 1 个不同的字符。
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * **方法：滑动窗口 + HashMap 统计字符频率**
 *
 * 1. **滑动窗口维护最多 K 个不同字符**
 *    - 设定 **左右指针 `left` 和 `right`**
 *    - `right` 右移，扩展窗口，加入新字符
 *    - 使用 `counter` 记录窗口内字符出现次数
 *
 * 2. **若窗口内不同字符个数 > K**
 *    - `left` 右移，收缩窗口，直到窗口内不同字符 ≤ `k`
 *    - 移动 `left` 时，减少 `counter[s[left]]` 计数
 *    - 若 `counter[s[left]] == 0`，则从 `counter` 中移除该字符
 *
 * 3. **在每次循环更新最大子串长度**
 *    - `maxSize = max(maxSize, right - left + 1)`
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `s = "eceba", k = 2`
 *
 * **滑动窗口过程**
 * ```plaintext
 * 1. 右指针扩展窗口: "e"    -> {e:1}       -> 窗口合法，maxSize = 1
 * 2. 右指针扩展窗口: "ec"   -> {e:1, c:1}  -> 窗口合法，maxSize = 2
 * 3. 右指针扩展窗口: "ece"  -> {e:2, c:1}  -> 窗口合法，maxSize = 3
 * 4. 右指针扩展窗口: "eceb" -> {e:2, c:1, b:1} -> 超过 2 个不同字符，left 右移:
 *    - 收缩后窗口: "ceb" -> {c:1, e:1, b:1}
 *    - 仍然超过 2 个字符，继续左移:
 *    - 收缩后窗口: "eb" -> {e:1, b:1}  -> 窗口合法，maxSize = 3
 * 5. 右指针扩展窗口: "eba" -> {e:1, b:1, a:1} -> 超过 2 个不同字符，left 右移:
 *    - 收缩后窗口: "ba" -> {b:1, a:1}  -> 窗口合法，maxSize 仍为 3
 * ```
 *
 * **最终输出** `maxSize = 3`
 *
 * ---
 *
 * **时间复杂度分析**
 * - **右指针 `O(N)` 遍历整个字符串**
 * - **左指针 `O(N)` 只会前进，不会回退**
 * - **总时间复杂度**: `O(N)`
 *
 * **空间复杂度分析**
 * - **使用 `counter` 记录 `K` 个不同字符**
 * - **最坏情况 `K = N`，`counter` 存储 `N` 个字符，`O(N)`**
 * - **一般情况下，`counter` 只存储 `k` 个字符，`O(K) ≈ O(1)`**
 */



public class LeetCode_340_LongestSubstringWithAtMostKDistinctCharacters {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算包含最多 k 个不同字符的最长子串的长度
         * @param s 输入字符串
         * @param k 最大不同字符数
         * @return 最长子串的长度
         */
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int n = s.length();
            int maxSize = 0;
            Map<Character, Integer> counter = new HashMap<>();

            int left = 0;
            for (int right = 0; right < n; right++) {
                counter.put(s.charAt(right), counter.getOrDefault(s.charAt(right), 0) + 1);

                // 如果不同字符数超过 k，收缩左指针直到不再超过 k
                while (counter.size() > k) {
                    System.out.println("current left:" + left +", freq: " + counter);
                    counter.put(s.charAt(left), counter.get(s.charAt(left)) - 1);
                    if (counter.get(s.charAt(left)) == 0) {
                        counter.remove(s.charAt(left));
                    }
                    left++;
                }

                // 更新最大子串的长度
                maxSize = Math.max(maxSize, right - left + 1);
            }

            return maxSize;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_340_LongestSubstringWithAtMostKDistinctCharacters.Solution solution =
                new LeetCode_340_LongestSubstringWithAtMostKDistinctCharacters().new Solution();

        // 测试代码
        String input1 = "eceba";
        int k1 = 2;
        int result1 = solution.lengthOfLongestSubstringKDistinct(input1, k1);
        System.out.println("Example 1: " + result1);  // 预期输出: 3

        String input2 = "aabacc";
        int k2 = 2;
        int result2 = solution.lengthOfLongestSubstringKDistinct(input2, k2);
        System.out.println("Example 2: " + result2);  // 预期输出: 4
    }
}

/**
Given a string s and an integer k, return the length of the longest substring 
of s that contains at most k distinct characters. 

 
 Example 1: 

 
Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3. 

 Example 2: 

 
Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.
 

 
 Constraints: 

 
 1 <= s.length <= 5 * 10⁴ 
 0 <= k <= 50 
 

 Related Topics Hash Table String Sliding Window 👍 2767 👎 80

*/

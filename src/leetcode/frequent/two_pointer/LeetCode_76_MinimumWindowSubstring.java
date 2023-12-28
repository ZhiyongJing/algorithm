package leetcode.frequent.two_pointer;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Question:  76. Minimum Window Substring
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 73.05%
 *@Time  Complexity: O(|S| + |T|)，其中 |S| 和 |T| 分别是字符串 S 和 T 的长度。
 *@Space Complexity: O(O(|S| + |T|))
 */

/**
 * **解题思路：**
 *
 * 这个问题要求在字符串 `s` 中找到包含字符串 `t` 中所有字符的最小窗口子串。我们可以使用滑动窗口的方法解决这个问题。
 *
 * 1. 我们首先创建一个映射 `dictT`，用于存储字符串 `t` 中每个字符的出现次数。
 * 2. 我们遍历字符串 `s`，将包含在 `t` 中的字符及其索引保存在列表 `filteredS` 中。
 * 3. 使用两个指针 `l` 和 `r` 表示滑动窗口的左右边界。我们同时使用 `windowCounts` 记录当前窗口中各字符的出现次数。
 * 4. 随着右指针 `r` 的移动，我们不断更新 `windowCounts`，并判断是否包含了 `t` 中的所有字符。
 * 一旦包含了，我们就尝试通过移动左指针 `l` 来缩小窗口，直到窗口不再包含 `t` 中的所有字符。
 * 5. 在每一步中，我们保存最小窗口的长度及其起始和结束索引。
 * 6. 最终返回找到的最小窗口子串。
 *
 * **时间复杂度：**
 *
 * - 时间复杂度是 O(|S| + |T|)，其中 |S| 和 |T| 分别是字符串 `S` 和 `T` 的长度。
 * 在最坏的情况下，我们需要遍历整个字符串 `s` 以构建 `filteredS` 列表，然后在这个列表上执行滑动窗口操作。
 *
 * **空间复杂度：**
 *
 * - 空间复杂度是 O(|S| + |T|)，主要是用于存储 `dictT`、`filteredS`、`windowCounts` 和 `ans` 数组。
 * 在最坏的情况下，`filteredS` 的长度等于字符串 `s` 的长度。
 */

public class LeetCode_76_MinimumWindowSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 返回包含字符串 t 中所有字符的最小窗口子串
         * @param s 输入字符串
         * @param t 目标字符串
         * @return 最小窗口子串
         */
        public String minWindow(String s, String t) {
            if (s.length() == 0 || t.length() == 0) {
                return "";
            }

            // 用于记录 t 中字符的频率
            Map<Character, Integer> dictT = new HashMap<>();

            // 初始化 dictT
            for (int i = 0; i < t.length(); i++) {
                int count = dictT.getOrDefault(t.charAt(i), 0);
                dictT.put(t.charAt(i), count + 1);
            }
            System.out.println("dictT is: " + dictT);

            int required = dictT.size();

            // 将字符串 s 中包含在 t 中的字符和它们的索引保存到列表中
            List<Pair<Integer, Character>> filteredS = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (dictT.containsKey(c)) {
                    filteredS.add(new Pair<>(i, c));
                }
            }
            System.out.println("filteredS is: " + filteredS);


            int l = 0, r = 0, formed = 0;
            Map<Character, Integer> windowCounts = new HashMap<>();
            int[] ans = { -1, 0, 0 };

            // 在过滤后的列表上执行滑动窗口操作
            while (r < filteredS.size()) {
                char c = filteredS.get(r).getValue();
                int count = windowCounts.getOrDefault(c, 0);
                windowCounts.put(c, count + 1);

                if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                    formed++;
                }

                // 尝试收缩窗口，直到窗口不再“理想”
                while (l <= r && formed == required) {
                    c = filteredS.get(l).getValue();

                    // 保存到目前为止最小的窗口
                    int end = filteredS.get(r).getKey();
                    int start = filteredS.get(l).getKey();
                    if (ans[0] == -1 || end - start + 1 < ans[0]) {
                        ans[0] = end - start + 1;
                        ans[1] = start;
                        ans[2] = end;
                    }

                    windowCounts.put(c, windowCounts.get(c) - 1);
                    if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                        formed--;
                    }
                    l++;
                }
                r++;
            }

            // 返回结果
            return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_76_MinimumWindowSubstring.Solution solution =
                new LeetCode_76_MinimumWindowSubstring().new Solution();

        // 测试代码
        String input1 = "ADOBECODEBANC";
        String target1 = "ABC";
        String result1 = solution.minWindow(input1, target1);
        System.out.println("Example 1: " + result1);  // 预期输出: "BANC"

        String input2 = "a";
        String target2 = "a";
        String result2 = solution.minWindow(input2, target2);
        System.out.println("Example 2: " + result2);  // 预期输出: "a"

        String input3 = "a";
        String target3 = "aa";
        String result3 = solution.minWindow(input3, target3);
        System.out.println("Example 3: " + result3);  // 预期输出: ""
    }
}

/**
Given two strings s and t of lengths m and n respectively, return the minimum 
window substring of s such that every character in t (including duplicates) is 
included in the window. If there is no such substring, return the empty string "". 


 The testcases will be generated such that the answer is unique. 

 
 Example 1: 

 
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' 
from string t.
 

 Example 2: 

 
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
 

 Example 3: 

 
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

 
 Constraints: 

 
 m == s.length 
 n == t.length 
 1 <= m, n <= 10⁵ 
 s and t consist of uppercase and lowercase English letters. 
 

 
 Follow up: Could you find an algorithm that runs in O(m + n) time? 

 Related Topics Hash Table String Sliding Window 👍 16658 👎 681

*/

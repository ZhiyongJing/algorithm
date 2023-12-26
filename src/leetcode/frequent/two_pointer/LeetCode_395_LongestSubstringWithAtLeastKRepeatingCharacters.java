package leetcode.frequent.two_pointer;

/**
  *@Question:  395. Longest Substring with At Least K Repeating Characters
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 50.12%
  *@Time  Complexity: O(N^2) for divide and conquer, O(N) for sliding window
  *@Space Complexity: O(N) for divide and conquer, O(1) for sliding window
 */

import java.util.Arrays;

/**
 * 这个问题的目标是找到给定字符串中的最长子串，该子串中的每个字符出现的次数都不少于 `k` 次。这个问题可以用两种不同的方法来解决：
 *
 * **1. 分治法（Divide and Conquer）：**
 *
 * - 使用递归的方法，首先统计字符串中每个字符的出现次数。
 * - 然后，找到第一个出现次数小于 `k` 的字符，以该字符为分隔符，将字符串分成左右两部分。
 * - 对左右两部分分别递归调用函数，返回左右两部分的最大子串长度。
 * - 最终结果为左右两部分的最大子串长度的较大值。
 *
 * **2. 滑动窗口法（Sliding Window）：**
 *
 * - 维护一个滑动窗口，使得窗口内的字符种类数为 `currUnique`，其中 `1 <= currUnique <= maxUnique`。
 * - 通过滑动窗口的方式，在窗口内查找满足条件的最长子串。
 * - 遍历 `currUnique` 从 1 到 `maxUnique`，每次维护一个滑动窗口。
 * - 在滑动窗口内，统计窗口内每个字符的出现次数，同时维护 `unique` 表示窗口内不同字符的种类数，`countAtLeastK`
 * 表示窗口内出现次数至少为 `k` 的字符的种类数。
 * - 如果 `unique` 等于 `currUnique` 且 `countAtLeastK` 等于 `currUnique`，则更新结果。
 *
 * **时间复杂度：**
 *
 * - 分治法：在每次递归调用中，需要对字符串进行遍历，所以时间复杂度为 O(N^2)，其中 N 是字符串的长度。整体时间复杂度为 O(N^2)。
 * - 滑动窗口法：整体时间复杂度为 O(N)，其中 N 是字符串的长度。因为每个滑动窗口的查找过程最坏情况下需要 O(N) 的时间。
 *
 * **空间复杂度：**
 *
 * - 分治法：递归调用的栈空间为 O(N)。
 * - 滑动窗口法：使用了常数级别的额外空间，主要是统计数组，所以空间复杂度为 O(1)。
 */

public class LeetCode_395_LongestSubstringWithAtLeastKRepeatingCharacters {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 第一种解法 -> 分治法
        public int longestSubstring2(String s, int k) {
            return longestSubstringUtil(s, 0, s.length(), k);
        }

        int longestSubstringUtil(String s, int start, int end, int k) {
            if (end < k) return 0;
            int[] countMap = new int[26];

            // 更新countMap，记录每个字符的出现次数
            for (int i = start; i < end; i++)
                countMap[s.charAt(i) - 'a']++;

            for (int mid = start; mid < end; mid++) {
                if (countMap[s.charAt(mid) - 'a'] >= k) continue;
                int midNext = mid + 1;
                while (midNext < end && countMap[s.charAt(midNext) - 'a'] < k) midNext++;

                return Math.max(longestSubstringUtil(s, start, mid, k),
                        longestSubstringUtil(s, midNext, end, k));
            }
            return (end - start);
        }

        // 第二种解法 -> 滑动窗口
        public int longestSubstring(String s, int k) {
            char[] str = s.toCharArray();
            int[] countMap = new int[26];
            int maxUnique = getMaxUniqueLetters(s);
            int result = 0;

            for (int currUnique = 1; currUnique <= maxUnique; currUnique++) {
                // 重置countMap
                Arrays.fill(countMap, 0);
                int windowStart = 0, windowEnd = 0, idx = 0, unique = 0, countAtLeastK = 0;

                while (windowEnd < str.length) {
                    // 扩展滑动窗口
                    if (unique <= currUnique) {
                        idx = str[windowEnd] - 'a';
                        if (countMap[idx] == 0) unique++;
                        countMap[idx]++;
                        if (countMap[idx] == k) countAtLeastK++;
                        windowEnd++;
                    }
                    // 收缩滑动窗口
                    else {
                        idx = str[windowStart] - 'a';
                        if (countMap[idx] == k) countAtLeastK--;
                        countMap[idx]--;
                        if (countMap[idx] == 0) unique--;
                        windowStart++;
                    }
                    // 检查是否符合条件
                    if (unique == currUnique && unique == countAtLeastK)
                        result = Math.max(windowEnd - windowStart, result);
                }
            }

            return result;
        }

        // 获取字符串s中的最大唯一字符数
        int getMaxUniqueLetters(String s) {
            boolean map[] = new boolean[26];
            int maxUnique = 0;
            for (int i = 0; i < s.length(); i++) {
                if (!map[s.charAt(i) - 'a']) {
                    maxUnique++;
                    map[s.charAt(i) - 'a'] = true;
                }
            }
            System.out.println(Arrays.toString(map));
            return maxUnique;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_395_LongestSubstringWithAtLeastKRepeatingCharacters.Solution solution =
                new LeetCode_395_LongestSubstringWithAtLeastKRepeatingCharacters().new Solution();

        // 测试代码
        String input = "aaabb";
        int k = 3;
        int result = solution.longestSubstring(input, k);

        // 输出结果
        System.out.println("The length of the longest substring with at least " + k +
                " repeating characters is: " + result);
    }
}

/**
Given a string s and an integer k, return the length of the longest substring 
of s such that the frequency of each character in this substring is greater than 
or equal to k. 

 if no such substring exists, return 0. 

 
 Example 1: 

 
Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 

 Example 2: 

 
Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 
'b' is repeated 3 times.
 

 
 Constraints: 

 
 1 <= s.length <= 10⁴ 
 s consists of only lowercase English letters. 
 1 <= k <= 10⁵ 
 

 Related Topics Hash Table String Divide and Conquer Sliding Window 👍 5951 👎 4
78

*/

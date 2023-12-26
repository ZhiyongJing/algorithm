package leetcode.frequent.two_pointer;

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
 * **解题思路：**
 *
 * 这个问题要求找到一个字符串中包含最多 k 个不同字符的最长子串的长度。我们可以使用滑动窗口的方法来解决这个问题。
 *
 * 1. 我们使用两个指针 `left` 和 `right` 来构建一个滑动窗口，表示当前考虑的子串。
 * 2. 右指针 `right` 向右移动，每次移动都将字符加入到当前的子串中，并更新字符的出现次数。
 * 3. 当窗口内的不同字符数超过了 `k` 时，我们需要收缩左指针 `left`，直到窗口内的不同字符数重新满足条件。
 * 4. 在每一步中，我们更新最大子串的长度。
 *
 * **时间复杂度：**
 *
 * - 在最坏的情况下，每个字符被访问两次：一次通过右指针向右移动，一次通过左指针向右移动。因此，时间复杂度是 O(N)，其中 N 是字符串的长度。
 *
 * **空间复杂度：**
 *
 * - 使用了哈希表来存储字符出现的次数，哈希表的大小最多是 `k`，所以空间复杂度是 O(K)。
 * 在英文字符集的情况下，`K` 最多是 26，所以可以认为空间复杂度是 O(1)。
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

        String input2 = "aa";
        int k2 = 1;
        int result2 = solution.lengthOfLongestSubstringKDistinct(input2, k2);
        System.out.println("Example 2: " + result2);  // 预期输出: 2
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

package leetcode.question.binary_search;

import java.util.HashSet;

/**
 *@Question:  1062. Longest Repeating Substring
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 30.42%
 *@Time  Complexity: O(NlogN)
 *@Space Complexity: O(N)
 */

/**
 * **算法思路：**
 *
 * 这个问题的核心思想是使用二分查找来找到重复子串的长度。具体步骤如下：
 *
 * 1. **定义搜索函数：** `search` 函数用于搜索给定长度 `L` 的子串是否在字符串 `S` 中至少出现两次。
 * 它通过遍历字符串，截取长度为 `L` 的子串，计算其哈希值，并使用哈希集合 `seen` 来记录已经出现的子串的哈希值。
 * 如果发现某个子串的哈希值已经在集合中出现过，说明这个子串至少出现了两次，那么函数返回该子串的起始位置；否则返回 -1。
 *
 * 2. **二分查找：** 在 `longestRepeatingSubstring` 函数中，使用二分查找来确定最长重复子串的长度。
 * 初始时，将二分查找的左右边界分别设置为 1 和字符串长度 `n`。然后，在每一次迭代中，计算中间长度 `L`，
 * 调用 `search` 函数判断是否存在长度为 `L` 的重复子串。如果存在，说明可能存在更长的重复子串，将左边界 `left`
 * 更新为 `L + 1`；否则，将右边界 `right` 更新为 `L - 1`。最终，返回 `left - 1` 作为最长重复子串的长度。
 *
 * **时间复杂度：**
 *
 * - `search` 函数的时间复杂度为 O(n)，其中 n 是字符串的长度。在二分查找中，迭代次数为 O(logN)，每次迭代调用一次 `search` 函数，
 * 因此总体时间复杂度为 O(NlogN)。
 *
 * **空间复杂度：**
 *
 * - `search` 函数使用了哈希集合 `seen` 来存储子串的哈希值，最坏情况下可能需要 O(n) 的额外空间。因此，
 * 整个算法的空间复杂度为 O(N)，其中 N 是字符串的长度。
 */

public class LeetCode_1062_LongestRepeatingSubstring {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 搜索长度为 L 的子串，至少出现 2 次。
         * 如果子串存在，返回起始位置；否则返回 -1。
         */
        public int search(int L, int n, String S) {
            HashSet<Integer> seen = new HashSet();
            String tmp;
            int h;
            for (int start = 0; start < n - L + 1; ++start) {
                tmp = S.substring(start, start + L);
                h = tmp.hashCode();
                if (seen.contains(h)) return start;
                seen.add(h);
            }
            return -1;
        }

        public int longestRepeatingSubstring(String S) {
            int n = S.length();
            // 二分查找，L 为重复子串的长度
            int left = 1, right = n;
            int L;
            while (left <= right) {
                L = left + (right - left) / 2;
                if (search(L, n, S) != -1) left = L + 1;
                else right = L - 1;
            }

            return left - 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1062_LongestRepeatingSubstring().new Solution();

        // 测试代码
        String testString = "banana";
        int result = solution.longestRepeatingSubstring(testString);

        System.out.println("最长重复子串的长度是：" + result);
    }
}

/**
Given a string s, return the length of the longest repeating substrings. If no 
repeating substring exists, return 0. 

 
 Example 1: 

 
Input: s = "abcd"
Output: 0
Explanation: There is no repeating substring.
 

 Example 2: 

 
Input: s = "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which 
occurs twice.
 

 Example 3: 

 
Input: s = "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
 

 
 Constraints: 

 
 1 <= s.length <= 2000 
 s consists of lowercase English letters. 
 

 Related Topics String Binary Search Dynamic Programming Rolling Hash Suffix 
Array Hash Function 👍 642 👎 55

*/

package leetcode.frequent.based_on_data_structure.heap;

import java.util.PriorityQueue;

/**
  *@Question:  767. Reorganize String     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 86.01%      
  *@Time  Complexity: O(N*logK) for heap - N 为字符串长度，A 为字母表大小; O(N) for solution 2
  *@Space Complexity: O(K) for heap, k is heap size; O(K) K be total unique chars in str
 */

/**
 * **问题描述：**
 *
 * 这个问题是要求重新排列字符串，使得相邻字符不相同。如果无法重新排列成这样的字符串，则返回空字符串。
 *
 * **解题思路：**
 *
 * 这个问题可以通过两种主要的解法来解决，分别是使用最大堆（Heap）和计数法。
 *
 * **解法一 - 使用最大堆：**
 *
 * 1. 首先统计字符串中每个字符的出现次数，使用一个大小为26的数组 `charCounts` 记录。
 *
 * 2. 构建一个最大堆（PriorityQueue），按字符出现次数降序排列。堆中的每个元素是一个长度为2的数组，
 * 第一个元素是字符的ASCII码，第二个元素是字符的出现次数。
 *
 * 3. 从堆中取出元素，将其添加到结果字符串中。如果结果字符串为空，或者当前元素与结果字符串的最后一个字符不相同，
 * 则直接添加到结果字符串中。否则，将元素重新放回堆中，并取出堆中的下一个元素。
 *
 * 4. 重复步骤3直到堆为空。最终，结果字符串中的字符满足相邻不相同的条件。
 *
 * **解法二 - 使用计数法：**
 *
 * 1. 同样地，首先统计字符串中每个字符的出现次数，使用一个大小为26的数组 `charCounts` 记录。
 *
 * 2. 找到出现次数最多的字符及其出现次数，记为 `maxCount` 和 `letter`。
 *
 * 3. 如果 `maxCount` 大于字符串长度的一半（包括一半），则无法重新排列成满足条件的字符串，返回空字符串。
 *
 * 4. 创建一个字符数组 `ans`，长度与输入字符串相同。
 *
 * 5. 将出现次数最多的字符放在 `ans` 数组的偶数索引位置，然后将其余字符放在 `ans` 数组的奇数索引位置。
 *
 * 6. 最终，将 `ans` 数组转换为字符串，得到满足条件的结果。
 *
 * **时间复杂度：**
 *
 * - 使用最大堆的解法的时间复杂度为 O(N * log A)，其中 N 为字符串长度，A 为字母表的大小（常数值26）。
 *
 * - 使用计数法的解法的时间复杂度为 O(N)，其中 N 为字符串长度。
 *
 * **空间复杂度：**
 *
 * - 使用最大堆的解法的空间复杂度为 O(A)，其中 A 为字母表的大小。
 *
 * - 使用计数法的解法的空间复杂度为 O(1)。
 */


public class LeetCode_767_ReorganizeString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //Solution 1: Heap
        /**
         * 使用最大堆重构字符串
         * @param s 给定字符串
         * @return 重构后的字符串，如果无法重构返回空字符串
         */
        public String reorganizeString(String s) {
            int[] charCounts = new int[26];
            for (char c : s.toCharArray()) {
                charCounts[c - 'a']++;
            }

            // 使用最大堆按字符出现次数降序排列
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(b[1], a[1]));
            for (int i = 0; i < 26; i++) {
                if (charCounts[i] > 0) {
                    pq.offer(new int[] {i + 'a', charCounts[i]});
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!pq.isEmpty()) {
                int[] first = pq.poll();
                if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
                    sb.append((char) first[0]);
                    if (--first[1] > 0) {
                        pq.offer(first);
                    }
                } else {
                    if (pq.isEmpty()) {
                        return "";
                    }

                    int[] second = pq.poll();
                    sb.append((char) second[0]);
                    if (--second[1] > 0) {
                        pq.offer(second);
                    }

                    pq.offer(first);
                }
            }

            return sb.toString();
        }

        //Solution2:
        /**
         * 使用计数法重构字符串
         * @param s 给定字符串
         * @return 重构后的字符串，如果无法重构返回空字符串
         */
        public String reorganizeString2(String s) {
            int[] charCounts = new int[26];
            for (char c : s.toCharArray()) {
                charCounts[c - 'a']++;
            }
            int maxCount = 0, letter = 0;
            for (int i = 0; i < charCounts.length; i++) {
                if (charCounts[i] > maxCount) {
                    maxCount = charCounts[i];
                    letter = i;
                }
            }
            if (maxCount > (s.length() + 1) / 2) {
                return "";
            }
            char[] ans = new char[s.length()];
            int index = 0;

            // 将出现次数最多的字母放在偶数索引位置
            while (charCounts[letter] != 0) {
                ans[index] = (char) (letter + 'a');
                index += 2;
                charCounts[letter]--;
            }

            // 将其余字母随意放在奇数索引位置
            for (int i = 0; i < charCounts.length; i++) {
                while (charCounts[i] > 0) {
                    if (index >= s.length()) {
                        index = 1;
                    }
                    ans[index] = (char) (i + 'a');
                    index += 2;
                    charCounts[i]--;
                }
            }

            return String.valueOf(ans);
        }
    }

    /**
     * 实例化并使用Solution对象的示例代码
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_767_ReorganizeString.Solution solution = new LeetCode_767_ReorganizeString().new Solution();

        // 测试代码
        String input = "aab";
        String result = solution.reorganizeString(input);
        System.out.println("重构后的字符串: " + result);
    }
}

/**
Given a string s, rearrange the characters of s so that any two adjacent 
characters are not the same. 

 Return any possible rearrangement of s or return "" if not possible. 

 
 Example 1: 
 Input: s = "aab"
Output: "aba"
 
 Example 2: 
 Input: s = "aaab"
Output: ""
 
 
 Constraints: 

 
 1 <= s.length <= 500 
 s consists of lowercase English letters. 
 

 Related Topics Hash Table String Greedy Sorting Heap (Priority Queue) Counting 
👍 8227 👎 240

*/
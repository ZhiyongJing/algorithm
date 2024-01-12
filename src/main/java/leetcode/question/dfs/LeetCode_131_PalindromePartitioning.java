package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.List;

/**
  *@Question:  131. Palindrome Partitioning     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 61.18%      
  *@Time  Complexity: O(N*2^N)
  *@Space Complexity: O(N)
 */

/**
 * **算法思路：**
 *
 * 这是一个使用回溯法（Backtracking）解决的问题。算法的主要思路是枚举字符串中的每个位置，对于每个位置，都考虑将其作为回文子串的起点，
 * 并尝试向后扩展。当找到一个回文子串后，将其加入当前列表，然后递归搜索下一个位置。在递归的过程中，不断回溯，将当前回文子串从列表中移出，
 * 继续搜索其他可能的分割方案。
 *
 * **时间复杂度：**
 *
 * 假设字符串的长度为 \(N\)。在最坏情况下，每个位置都有两种选择：选择该位置作为回文子串的起点或不选择。因此，总的搜索空间是 \(2^N\)。
 * 对于每个可能的分割方案，需要检查是否是回文子串，这一过程的时间复杂度是 \(O(N)\)。因此，总的时间复杂度是 \(O(N \cdot 2^N)\)。
 *
 * **空间复杂度：**
 *
 * 递归调用的深度最大为字符串的长度 \(N\)，因此空间复杂度是 \(O(N)\)。此外，需要额外的空间存储当前的回文子串，最坏情况下空间复杂度也是 \(O(N)\)。
 */

public class LeetCode_131_PalindromePartitioning {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<>();
            dfs(0, result, new ArrayList<>(), s);
            return result;
        }

        // 深度优先搜索
        void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
            // 如果起始索引超出字符串长度，说明当前分割是有效的，将结果加入到最终列表中
            if (start >= s.length()) {
                result.add(new ArrayList<>(currentList));
                return;
            }

            // 遍历字符串
            for (int end = start; end < s.length(); end++) {
                // 如果是回文字符串
                if (isPalindrome(s, start, end)) {
                    // 将当前回文子串加入当前列表
                    currentList.add(s.substring(start, end + 1));
                    // 递归搜索下一个位置
                    dfs(end + 1, result, currentList, s);
                    // 回溯，将当前回文子串移出列表
                    currentList.remove(currentList.size() - 1);
                }
            }
        }

        // 判断是否是回文串
        boolean isPalindrome(String s, int low, int high) {
            while (low < high) {
                if (s.charAt(low++) != s.charAt(high--)) return false;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_131_PalindromePartitioning().new Solution();
        // 测试样例
        List<List<String>> result1 = solution.partition("aab");
        System.out.println(result1); // 预期输出: [["a","a","b"],["aa","b"]]

        List<List<String>> result2 = solution.partition("a");
        System.out.println(result2); // 预期输出: [["a"]]
    }
}

/**
Given a string s, partition s such that every substring of the partition is a 
palindrome. Return all possible palindrome partitioning of s. 

 
 Example 1: 
 Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
 
 Example 2: 
 Input: s = "a"
Output: [["a"]]
 
 
 Constraints: 

 
 1 <= s.length <= 16 
 s contains only lowercase English letters. 
 

 Related Topics String Dynamic Programming Backtracking 👍 11984 👎 395

*/

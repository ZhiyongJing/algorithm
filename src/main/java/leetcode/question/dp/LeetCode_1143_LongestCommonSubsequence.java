package leetcode.question.dp;

/**
  *@Question:  1143. Longest Common Subsequence     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 71.08%      
  *@Time  Complexity: O(M*N)
  *@Space Complexity: O(M*N), O min(M, N) for solution2
 */
/**
 * 这道题的解题思路是使用动态规划算法来求解两个字符串的最长公共子序列（Longest Common Subsequence，简称 LCS）的长度。
 *
 * ### 解题思路：
 *
 * 1. **Bottom-Up 动态规划：**
 *
 *    - 我们创建一个二维数组 `dpGrid`，其大小为 `(text1.length() + 1) * (text2.length() + 1)`，用于存储最长公共子序列的长度。
 *    - 从右下角开始，即 `dpGrid[text1.length()][text2.length()]`，对于每个格子 `(row, col)`，表示 `text1` 的前 `row` 个字符和 `text2`
 *    的前 `col` 个字符的最长公共子序列长度。
 *    - 从右下角开始向左上角遍历，对于每个格子 `(row, col)`：
 *      - 如果 `text1.charAt(row - 1) == text2.charAt(col - 1)`，即当前字符相同，则最长公共子序列长度加一，即
 *      `dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1]`。
 *      - 否则，取相邻格子中的较大值，即 `dpGrid[row][col] = Math.max(dpGrid[row + 1][col], dpGrid[row][col + 1])`。
 *    - 最终，`dpGrid[0][0]` 即为所求的最长公共子序列的长度。
 *
 * 2. **Bottom-Up 动态规划，优化空间复杂度：**
 *
 *    - 为了优化空间复杂度，我们只需使用两个一维数组 `previous` 和 `current`，分别表示上一列和当前列的最长公共子序列长度。
 *    - 在计算当前列时，我们只需用到上一列的数据，因此我们可以在内层循环中交替更新 `previous` 和 `current` 数组，从而节省空间。
 *
 * ### 时间复杂度：
 *
 * - **Bottom-Up 动态规划：** 我们需要填充一个二维数组 `dpGrid`，其大小为 `(M+1) * (N+1)`，
 * 其中 `M` 和 `N` 分别是字符串 `text1` 和 `text2` 的长度。
 * 因此，时间复杂度为 `O(M * N)`。
 *
 * - **Bottom-Up 动态规划，优化空间复杂度：** 我们只需要填充两个一维数组 `previous` 和 `current`，其大小为 `M + 1`，其中 `M` 是较短字符串的长度。
 * 因此，时间复杂度仍为 `O(M * N)`。
 *
 * ### 空间复杂度：
 *
 * - **Bottom-Up 动态规划：** 我们需要使用一个二维数组 `dpGrid`，其大小为 `(M+1) * (N+1)`，因此空间复杂度为 `O(M * N)`。
 *
 * - **Bottom-Up 动态规划，优化空间复杂度：** 我们只需要使用两个一维数组 `previous` 和 `current`，其大小为 `M + 1`，因此空间复杂度为 `O(M)`，
 * 其中 `M` 是较短字符串的长度。
 */

public class LeetCode_1143_LongestCommonSubsequence{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //Bottom-Up 动态规划解法
        public int longestCommonSubsequence(String text1, String text2) {

            // 创建一个二维数组，用于存储最长公共子序列的长度
            //对于每个格子 `(row, col)`，表示 `text1` 的前 `row` 个字符和 `text2`  的前 `col` 个字符的最长公共子序列长度。
            int[][] dpGrid = new int[text1.length() + 1][text2.length() + 1];

            // 从最后一列开始向前遍历每一列
            for (int col = text2.length() - 1; col >= 0; col--) {
                for (int row = text1.length() - 1; row >= 0; row--) {
                    // 如果当前位置的字符相同，则最长公共子 序列长度加一
                    if (text1.charAt(row) == text2.charAt(col)) {
                        dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1];
                    } else {
                        // 否则，取相邻位置中的较大值作为当前位置的最长公共子序列长度
                        dpGrid[row][col] = Math.max(dpGrid[row + 1][col], dpGrid[row][col + 1]);
                    }
                }
            }

            // 返回最长公共子序列的长度
            return dpGrid[0][0];
        }

        //Solution2: Bottom-Up 动态规划解法，优化空间复杂度
        public int longestCommonSubsequence2(String text1, String text2) {

            // 如果 text2 的长度小于 text1 的长度，则交换两者，确保 text1 的长度不大于 text2 的长度
            if (text2.length() < text1.length()) {
                String temp = text1;
                text1 = text2;
                text2 = temp;
            }

            // 创建两个数组，分别用于存储当前列和上一列的最长公共子序列长度
            int[] previous = new int[text1.length() + 1];
            int[] current = new int[text1.length() + 1];

            // 从最后一列开始向前遍历每一列
            for (int col = text2.length() - 1; col >= 0; col--) {
                for (int row = text1.length() - 1; row >= 0; row--) {
                    // 如果当前位置的字符相同，则最长公共子序列长度加一
                    if (text1.charAt(row) == text2.charAt(col)) {
                        current[row] = 1 + previous[row + 1];
                    } else {
                        // 否则，取相邻位置中的较大值作为当前位置的最长公共子序列长度
                        current[row] = Math.max(previous[row], current[row + 1]);
                    }
                }
                // 将当前列的最长公共子序列长度数组设为上一列的数组，为下一列的计算做准备
                int[] temp = previous;
                previous = current;
                current = temp;
            }

            // 返回最长公共子序列的长度，即为 previous 数组的第一个元素值
            return previous[0];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1143_LongestCommonSubsequence().new Solution();
        // 测试代码
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("测试字符串1：" + text1);
        System.out.println("测试字符串2：" + text2);
        System.out.println("最长公共子序列的长度：" + solution.longestCommonSubsequence(text1, text2));
        System.out.println("最长公共子序列的长度（优化空间复杂度）：" + solution.longestCommonSubsequence2(text1, text2));
    }
}

/**
Given two strings text1 and text2, return the length of their longest common 
subsequence. If there is no common subsequence, return 0. 

 A subsequence of a string is a new string generated from the original string 
with some characters (can be none) deleted without changing the relative order of 
the remaining characters. 

 
 For example, "ace" is a subsequence of "abcde". 
 

 A common subsequence of two strings is a subsequence that is common to both 
strings. 

 
 Example 1: 

 
Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
 

 Example 2: 

 
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
 

 Example 3: 

 
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

 
 Constraints: 

 
 1 <= text1.length, text2.length <= 1000 
 text1 and text2 consist of only lowercase English characters. 
 

 Related Topics String Dynamic Programming 👍 13172 👎 183

*/
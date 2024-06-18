package leetcode.question.dp;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  712. Minimum ASCII Delete Sum for Two Strings
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 5.26%
 *@Time  Complexity: O(m * n)
 *@Space Complexity: O(m * n) for solution1 and solution2, O(min(M, N))for solutin3
 */

/**
 * 题目要求找到使两个字符串s1和s2相等的最小ASCII删除代价。这个问题可以使用动态规划来解决。
 *
 * ### 解题思路
 *
 * 1. **定义状态**：
 *    - 定义 `computeCost[i][j]` 表示将字符串 `s1` 的前 `i` 个字符和字符串 `s2` 的前 `j` 个字符变成相等所需的最小ASCII删除代价。
 *
 * 2. **状态转移**：
 *    - 如果 `s1[i-1] == s2[j-1]`，即当前字符相同，那么 `computeCost[i][j] = computeCost[i-1][j-1]`，不需要额外的删除代价。
 *    - 如果 `s1[i-1] != s2[j-1]`，即当前字符不同，可以考虑删除 `s1[i-1]` 或者 `s2[j-1]`：
 *      - 删除 `s1[i-1]` 的代价为 `s1[i-1] + computeCost[i-1][j]`
 *      - 删除 `s2[j-1]` 的代价为 `s2[j-1] + computeCost[i][j-1]`
 *      - 需要取两者的最小值作为当前的 `computeCost[i][j]`。
 *
 * 3. **初始化**：
 *    - 当其中一个字符串为空时，需要删除另一个字符串的所有字符的ASCII值，即 `computeCost[i][0] = computeCost[i-1][0] + s1[i-1]` 和 `computeCost[0][j] = computeCost[0][j-1] + s2[j-1]`。
 *
 * 4. **边界条件**：
 *    - 如果 `i=0` 或 `j=0`，即一个字符串为空，直接计算删除所有字符的ASCII值。
 *
 * 5. **计算顺序**：
 *    - 从左到右，从上到下计算 `computeCost` 数组，确保在计算当前位置的值时，其依赖的子问题已经计算完成。
 *
 * ### 时间复杂度
 * 动态规划解法的时间复杂度为 `O(m * n)`，其中 `m` 和 `n` 分别是字符串 `s1` 和 `s2` 的长度。因为需要填充一个二维数组 `computeCost`。
 *
 * ### 空间复杂度
 * 动态规划解法的空间复杂度为 `O(m * n)`，需要使用一个二维数组 `computeCost` 来存储子问题的解。
 */
public class LeetCode_712_MinimumAsciiDeleteSumForTwoStrings{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // Hash Map to store the result of each sub-problem.
        Map<Pair<Integer, Integer>, Integer> savedResult = new HashMap<>();

        //Solution1: top-down
        /**
         * 使用递归和记忆化搜索的方法来计算最小删除代价。
         * 对于每一对字符串s1和s2的子问题(s1[0...i], s2[0...j])，计算使它们相等所需的最小ASCII删除代价。
         */
        public int minimumDeleteSum1(String s1, String s2) {
            return computeCost(s1, s2, s1.length()-1, s2.length()-1);
        }

        /**
         * 递归计算使得s1[0...i]和s2[0...j]相等的最小ASCII删除代价。
         */
        private int computeCost(String s1, String s2, int i, int j) {
            // 如果两个字符串都为空，则删除代价为0
            if (i < 0 && j < 0) {
                return 0;
            }

            // 如果已经计算过，直接返回保存的结果
            Pair<Integer, Integer> key = new Pair<>(i, j);
            if (savedResult.containsKey(key)) {
                return savedResult.get(key);
            }

            // 如果其中一个字符串为空，删除另一个字符串的所有字符的ASCII代价
            if (i < 0) {
                savedResult.put(key, s2.charAt(j) + computeCost(s1, s2, i, j-1));
                return savedResult.get(key);
            }
            if (j < 0) {
                savedResult.put(key, s1.charAt(i) + computeCost(s1, s2, i-1, j));
                return savedResult.get(key);
            }

            // 递归调用处理当前字符相等和不相等的情况，并保存结果
            if (s1.charAt(i) == s2.charAt(j)) {
                savedResult.put(key, computeCost(s1, s2, i-1, j-1));
            } else {
                savedResult.put(key, Math.min(
                        s1.charAt(i) + computeCost(s1, s2, i-1, j),
                        s2.charAt(j) + computeCost(s1, s2, i, j-1)
                ));
            }
            return savedResult.get(key);
        }

        //Solution2: Bottom-up
        /**
         * 使用动态规划的方法，从底向上计算最小ASCII删除代价。
         * 通过填充二维数组computeCost[m+1][n+1]来记录计算结果。
         */
        public int minimumDeleteSum(String s1, String s2) {
            int m = s1.length(), n = s2.length();
            int[][] computeCost = new int[m + 1][n + 1];

            // 填充基本情况的值
            for (int i = 1; i <= m; i++) {
                computeCost[i][0] = computeCost[i-1][0] + s1.charAt(i-1);
            }
            for (int j = 1; j <= n; j++) {
                computeCost[0][j] = computeCost[0][j-1] + s2.charAt(j-1);
            }

            // 使用Bellman方程填充剩余的单元格
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s1.charAt(i-1) == s2.charAt(j-1)) {
                        computeCost[i][j] = computeCost[i-1][j-1];
                    } else {
                        computeCost[i][j] = Math.min(
                                s1.charAt(i-1) + computeCost[i-1][j],
                                s2.charAt(j-1) + computeCost[i][j-1]
                        );
                    }
                }
            }

            // 返回整个输入字符串的答案
            return computeCost[m][n];
        }

        //Solution3: 基于solution2的空间优化
        /**
         * 对solution2进行空间优化，使用一维数组来记录当前行的计算结果。
         */
        public int minimumDeleteSum3(String s1, String s2) {
            // 确保s2是较短的字符串
            if (s1.length() < s2.length()) {
                return minimumDeleteSum(s2, s1);
            }

            int m = s1.length(), n = s2.length();
            int[] currRow = new int[n + 1];

            // 填充第一行的值
            for (int j = 1; j <= n; j++) {
                currRow[j] = currRow[j - 1] + s2.charAt(j - 1);
            }

            // 逐行计算答案
            for (int i = 1; i <= m; i++) {
                int diag = currRow[0];
                currRow[0] += s1.charAt(i - 1);

                for (int j = 1; j <= n; j++) {
                    int answer;

                    // 如果字符相同，则答案是左上对角线的值
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        answer = diag;
                    } else {
                        // 否则，答案是上面和左边值中的最小值加上删除字符的ASCII值
                        answer = Math.min(
                                s1.charAt(i - 1) + currRow[j],
                                s2.charAt(j - 1) + currRow[j - 1]
                        );
                    }

                    // 在覆盖currRow[j]之前，将其保存到diag中以供下一列使用
                    diag = currRow[j];
                    currRow[j] = answer;
                }
            }

            // 返回答案
            return currRow[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_712_MinimumAsciiDeleteSumForTwoStrings().new Solution();

        // 测试样例
        String s1 = "sea";
        String s2 = "eat";
        System.out.println(solution.minimumDeleteSum1(s1, s2)); // Output: 231
        System.out.println(solution.minimumDeleteSum(s1, s2));  // Output: 231
        System.out.println(solution.minimumDeleteSum3(s1, s2)); // Output: 231
    }
}

/**
Given two strings s1 and s2, return the lowest ASCII sum of deleted characters 
to make two strings equal. 

 
 Example 1: 

 
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the 
sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum 
possible to achieve this.
 

 Example 2: 

 
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d] + 101[e] + 101[e] to the sum.
Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 =
 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 4
33 or 417, which are higher.
 

 
 Constraints: 

 
 1 <= s1.length, s2.length <= 1000 
 s1 and s2 consist of lowercase English letters. 
 

 Related Topics String Dynamic Programming 👍 3958 👎 107

*/
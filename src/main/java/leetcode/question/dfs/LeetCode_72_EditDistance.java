package leetcode.question.dfs;

/**
 *@Question:  72. Edit Distance
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.63%
 *@Time  Complexity: O(m*n) where m is the length of word1 and n is the length of word2
 *@Space Complexity: O(m*n)
 */

/**
 * 算法思路：
 *
 * 编辑距离（Edit Distance）问题是一个经典的动态规划问题，其目标是计算将一个字符串转换成另一个字符串所需的最小操作次数。
 * 这里的操作包括插入、删除、替换字符。
 *
 * 这个问题可以通过递归的方式解决。具体算法思路如下：
 *
 * 1. 定义一个二维数组 `memo` 用于保存已经计算过的子问题的结果，`memo[i][j]` 表示将 `word1` 的前 `i` 个字符转换成 `word2` 的前 `j` 个字符
 * 所需的最小操作次数。
 * 2. 编写递归函数 `minDistanceRecur`，它接受两个索引参数 `word1Index` 和 `word2Index`，
 * 表示当前需要处理的字符在 `word1` 和 `word2` 中的位置。
 * 3. 若 `word1Index` 或 `word2Index` 为 0，表示有一个字符串为空，返回另一个字符串的长度，因为此时需要进行插入或删除操作。
 * 4. 若 `memo[word1Index][word2Index]` 不为 null，说明已经计算过该子问题的结果，直接返回该值。
 * 5. 检查 `word1.charAt(word1Index - 1)` 是否等于 `word2.charAt(word2Index - 1)`，即当前处理的字符是否相同。
 *    - 若相同，不需要进行操作，继续递归处理下一个字符。
 *    - 若不相同，分别计算插入、删除、替换操作的最小次数，并取最小值作为当前子问题的解。
 * 6. 将结果保存在 `memo[word1Index][word2Index]` 中，并返回该值。
 *
 * 时间复杂度分析：
 * - 递归树的深度为 `word1.length() + 1`，每层递归有常数时间操作，所以时间复杂度为 O(m * n)，其中 m 和 n 分别为 `word1` 和 `word2` 的长度。
 *
 * 空间复杂度分析：
 * - 使用了一个二维数组 `memo`，大小为 `(word1.length() + 1) * (word2.length() + 1)`，所以空间复杂度为 O(m * n)。
 */

public class LeetCode_72_EditDistance{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Integer memo[][];

        public int minDistance(String word1, String word2) {
            memo = new Integer[word1.length() + 1][word2.length() + 1];
            return minDistanceRecur(word1, word2, word1.length(), word2.length());
        }

        int minDistanceRecur(String word1, String word2, int word1Index, int word2Index) {
            if (word1Index == 0) {
                return word2Index;
            }
            if (word2Index == 0) {
                return word1Index;
            }
            if (memo[word1Index][word2Index] != null) {
                return memo[word1Index][word2Index];
            }
            int minEditDistance = 0;
            if (word1.charAt(word1Index - 1) == word2.charAt(word2Index - 1)) {
                minEditDistance = minDistanceRecur(word1, word2, word1Index - 1, word2Index - 1);
            }
            else {
                int insertOperation = minDistanceRecur(word1, word2, word1Index, word2Index - 1);
                int deleteOperation = minDistanceRecur(word1, word2, word1Index - 1, word2Index);
                int replaceOperation = minDistanceRecur(word1, word2, word1Index - 1, word2Index - 1);
                minEditDistance = Math.min(insertOperation, Math.min(deleteOperation, replaceOperation)) + 1;
            }
            memo[word1Index][word2Index] = minEditDistance;
            return minEditDistance;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_72_EditDistance().new Solution();
        // 测试用例
        System.out.println(solution.minDistance("horse", "ros"));  // Output: 3
        System.out.println(solution.minDistance("intention", "execution"));  // Output: 5
    }
}
/**
 Given two strings word1 and word2, return the minimum number of operations
 required to convert word1 to word2.

 You have the following three operations permitted on a word:


 Insert a character
 Delete a character
 Replace a character



 Example 1:


 Input: word1 = "horse", word2 = "ros"
 Output: 3
 Explanation:
 horse -> rorse (replace 'h' with 'r')
 rorse -> rose (remove 'r')
 rose -> ros (remove 'e')


 Example 2:


 Input: word1 = "intention", word2 = "execution"
 Output: 5
 Explanation:
 intention -> inention (remove 't')
 inention -> enention (replace 'i' with 'e')
 enention -> exention (replace 'n' with 'x')
 exention -> exection (replace 'n' with 'c')
 exection -> execution (insert 'u')



 Constraints:


 0 <= word1.length, word2.length <= 500
 word1 and word2 consist of lowercase English letters.


 Related Topics String Dynamic Programming 👍 14136 👎 186
*/
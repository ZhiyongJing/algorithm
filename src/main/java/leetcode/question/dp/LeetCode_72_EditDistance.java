package leetcode.question.dp;

/**
  *@Question:  72. Edit Distance     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 60.22%      
  *@Time  Complexity: O(M*N) M be the length of string word1 and N be the length of string word2.
  *@Space Complexity: O(M*N)
 */

/**
 * 这道题的解题思路是使用动态规划算法来求解字符串的最小编辑距离。
 *
 * 给定两个字符串 word1 和 word2，我们的目标是将 word1 转换为 word2，可以执行三种操作：
 *
 * 1. 插入一个字符
 * 2. 删除一个字符
 * 3. 替换一个字符
 *
 * 我们的目标是找到执行这些操作的最小次数，使得 word1 变为 word2。
 *
 * ### 解题思路：
 *
 * 1. **自顶向下的动态规划：**
 *
 *    我们可以从字符串的末尾开始向前遍历，比较字符是否相同。如果相同，则不需要任何编辑操作，直接继续向前比较；
 *    如果不同，则我们可以考虑三种操作中的一种，分别进行插入、删除或替换操作，然后继续向前递归比较。
 *    为了避免重复计算，我们使用一个二维数组 `memo` 来存储已经计算过的最小编辑距离。如果在 `memo` 中找到了已经计算过的值，则直接返回，
 *    否则继续递归计算。
 *
 * 2. **自底向上的动态规划：**
 *
 *    我们也可以使用自底向上的动态规划方法来解决这个问题。我们创建一个二维数组 `dp`，其中 `dp[i][j]` 表示将 word1 的前 `i` 个字符转换
 *    为 word2 的前 `j` 个字符所需的最小编辑距离。初始化时，当其中一个字符串为空时，最小编辑距离即为另一个字符串的长度，
 *    因此我们可以先初始化边界条件。然后，我们根据状态转移方程来填充数组 `dp`，直到计算出 `dp[word1.length()][word2.length()]`。
 *
 * ### 时间复杂度：
 *
 * - **自顶向下的动态规划：** 在递归过程中，我们需要遍历所有可能的情况，因此时间复杂度为 O(M * N)，
 * 其中 M 和 N 分别是字符串 word1 和 word2 的长度。
 *
 * - **自底向上的动态规划：** 我们需要填充一个二维数组 `dp`，其大小为 (M+1) * (N+1)，其中
 * M 和 N 分别是字符串 word1 和 word2 的长度。因此，时间复杂度为 O(M * N)。
 *
 * ### 空间复杂度：
 *
 * - **自顶向下的动态规划：** 我们使用了一个二维数组 `memo` 来存储已经计算过的最小编辑距离，
 * 其大小为 (M+1) * (N+1)，因此空间复杂度为 O(M * N)。
 *
 * - **自底向上的动态规划：** 我们只需要一个二维数组 `dp` 来存储最小编辑距离，其大小为 (M+1) * (N+1)，
 * 因此空间复杂度为 O(M * N)。
 */

public class LeetCode_72_EditDistance{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Integer memo[][];

        // 自顶向下的动态规划方法
        public int minDistance1(String word1, String word2) {
            memo = new Integer[word1.length() + 1][word2.length() + 1];
            return minDistanceRecur(word1, word2, word1.length(), word2.length());
        }

        // 递归函数，计算 word1[0...word1Index] 和 word2[0...word2Index] 的最小编辑距离
        int minDistanceRecur(String word1, String word2, int word1Index, int word2Index) {
            // 如果其中一个字符串为空，则返回另一个字符串的长度，因为需要插入相应数量的字符
            if (word1Index == 0) {
                return word2Index;
            }
            if (word2Index == 0) {
                return word1Index;
            }
            // 如果之前已经计算过当前情况的最小编辑距离，则直接返回该值
            if (memo[word1Index][word2Index] != null) {
                return memo[word1Index][word2Index];
            }
            int minEditDistance = 0;
            // 如果当前字符相同，则不需要进行编辑操作，直接进入下一轮比较
            if (word1.charAt(word1Index - 1) == word2.charAt(word2Index - 1)) {
                minEditDistance = minDistanceRecur(word1, word2, word1Index - 1, word2Index - 1);
            }
            // 如果当前字符不同，则可以进行插入、删除或替换操作，并选择其中操作次数最少的一种
            else {
                int insertOperation = minDistanceRecur(word1, word2, word1Index, word2Index - 1);
                int deleteOperation = minDistanceRecur(word1, word2, word1Index - 1, word2Index);
                int replaceOperation = minDistanceRecur(word1, word2, word1Index - 1, word2Index - 1);
                minEditDistance = Math.min(insertOperation, Math.min(deleteOperation, replaceOperation)) + 1;
            }
            // 将计算得到的最小编辑距离保存到 memo 数组中
            memo[word1Index][word2Index] = minEditDistance;
            return minEditDistance;
        }

        // 自底向上的动态规划方法
        public int minDistance(String word1, String word2) {
            int word1Length = word1.length();
            int word2Length = word2.length();

            if (word1Length == 0) {
                return word2Length;
            }
            if (word2Length == 0) {
                return word1Length;
            }

            int dp[][] = new int[word1Length + 1][word2Length + 1];
            //`dp[i][j]` 表示将 word1 的前 `i` 个字符转换  为 word2 的前 `j` 个字符所需的最小编辑距离

            // 初始化边界条件
            for (int word1Index = 1; word1Index <= word1Length; word1Index++) {
                dp[word1Index][0] = word1Index;
            }

            for (int word2Index = 1; word2Index <= word2Length; word2Index++) {
                dp[0][word2Index] = word2Index;
            }

            // 计算状态转移方程
            for (int word1Index = 1; word1Index <= word1Length; word1Index++) {
                for (int word2Index = 1; word2Index <= word2Length; word2Index++) {
                    //如果word1[i] == word2[j]，那么不用处理最后一个字符，其最优编辑过程也就是从word1[0, i-1]到word2[0, j-1]的编辑过程。
                    // 此时dp[i][j] = dp[i-1][j-1]
                    if (word2.charAt(word2Index - 1) == word1.charAt(word1Index - 1)) {
                        dp[word1Index][word2Index] = dp[word1Index - 1][word2Index - 1];
                    } else {
//                        2）如果不相同，那么根据对最后一个字符的处理分类，有如下三种可能：
//
//                        a、替换：此时对应的编辑过程就是：step 1: 将word1[0, i-1]编辑为word2[0, j-1]；step 2: 将word1[i]替换为word2[j]。
//                        此时dp[i][j] = dp[i-1][j-1] + 1。
//
//                        b、插入：此时对应的编辑过程就是：step 1: 将word1[0, i]编辑为word2[0, j-1]；
//                        step 2: 在word2[0, j-1]的末尾加上words[j]。此时dp[i][j] = dp[i][j-1] + 1。
//
//                        c、删除：此时对应的编辑过程就是：step 1: 将word1[0, i-1]编辑为word2[0, j]；
//                        step 2: 将word1[i]删除。此时dp[i][j] = dp[i-1][j] + 1。

                        dp[word1Index][word2Index] = Math.min(dp[word1Index - 1][word2Index],//删除
                                Math.min(dp[word1Index][word2Index - 1],//添加
                                        dp[word1Index - 1][word2Index - 1]) //替换
                        ) + 1;
                    }
                }
            }

            return dp[word1Length][word2Length];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_72_EditDistance().new Solution();
        // 测试代码
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("测试字符串1：" + word1);
        System.out.println("测试字符串2：" + word2);
        System.out.println("最小编辑距离（自顶向下动态规划）：" + solution.minDistance1(word1, word2));
        System.out.println("最小编辑距离（自底向上动态规划）：" + solution.minDistance(word1, word2));
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
 

 Related Topics String Dynamic Programming 👍 14438 👎 204

*/
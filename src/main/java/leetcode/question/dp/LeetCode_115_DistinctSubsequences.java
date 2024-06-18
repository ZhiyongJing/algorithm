package leetcode.question.dp;

import javafx.util.Pair;

import java.util.HashMap;

/**
 *@Question:  115. Distinct Subsequences
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 28.970000000000002%
 *@Time  Complexity: O(M * N) where M, N are the lengths of strings s and t respectively
 *@Space Complexity: O(M * N) for the top-down approach, O(M * N) for the bottom-up approach, O(N) for the space-optimized bottom-up approach
 */
/*8
题目：115. Distinct Subsequences

### 解题思路

这道题要求计算字符串 `s` 中有多少个不同的子序列等于字符串 `t`。子序列指的是从字符串中删除一些字符（不改变其余字符的相对位置）后得到的新字符串。

#### 解法1: 递归 + 记忆化搜索（Top-Down Approach）

递归方法是一种自顶向下的方法，通过递归函数来实现。在每一步，我们判断当前字符是否匹配。如果匹配，可以选择匹配或不匹配，然后分别进行递归调用。使用哈希表来存储已经计算过的结果，避免重复计算，从而提高效率。

- **时间复杂度**：主要由递归函数决定，可以达到指数级别，即 O(2^M)，其中 M 是字符串 s 的长度。
- **空间复杂度**：哈希表存储空间，最坏情况下为 O(M * N)，其中 M 和 N 分别是字符串 s 和 t 的长度。

#### 解法2: 动态规划（Bottom-Up Approach）

动态规划方法使用二维数组 dp[i][j] 来表示字符串 s 的前缀子串 s[0...i-1] 和字符串 t 的前缀子串 t[0...j-1] 的匹配次数。

- **时间复杂度**：O(M * N)，其中 M 和 N 分别是字符串 s 和 t 的长度。这是因为需要填充一个二维数组。
- **空间复杂度**：O(M * N)，需要一个二维数组来存储中间状态。

#### 解法3: 空间优化的动态规划（Space-Optimized Bottom-Up Approach）

空间优化的动态规划方法是在解法2的基础上进行优化，只使用一个一维数组来存储中间状态。这种方法可以将空间复杂度优化到 O(N)，其中 N 是字符串 t 的长度。

- **时间复杂度**：O(M * N)，与解法2相同。
- **空间复杂度**：O(N)，只需要一个长度为 N 的一维数组来存储中间状态。

### 总结

- 对于本题，递归方法虽然直观，但是效率较低，容易超时，因此通常使用动态规划来解决。
- 动态规划方法通过填充一个二维数组或者优化到一个一维数组，可以有效地解决问题，并且在时间复杂度和空间复杂度上都有所优化。

这三种方法中，动态规划是最优的选择，特别是优化到一维数组的动态规划方法，能够在时间和空间上都达到较好的效果。
 */

public class LeetCode_115_DistinctSubsequences{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 用于记忆化搜索的哈希表
        private HashMap<Pair<Integer, Integer>, Integer> memo;

        // 递归函数，用于实现 top-down 方法
        private int recurse(String s, String t, int i, int j) {
            int M = s.length();
            int N = t.length();

            // 基本情况
            if (i == M || j == N || M - i < N - j) {
                return j == t.length() ? 1 : 0;
            }

            Pair<Integer, Integer> key = new Pair<Integer, Integer>(i, j);

            // 如果哈希表中已经有了这个调用的结果，直接返回
            if (this.memo.containsKey(key)) {
                return this.memo.get(key);
            }

            // 计算这个调用的结果
            int ans = this.recurse(s, t, i + 1, j);

            // 如果字符匹配，递归调用并将结果加到 ans 中
            if (s.charAt(i) == t.charAt(j)) {
                ans += this.recurse(s, t, i + 1, j + 1);
            }

            // 缓存这个调用的结果
            this.memo.put(key, ans);
            return ans;
        }

        // Solution1: top-down 方法
        public int numDistinct1(String s, String t) {
            this.memo = new HashMap<Pair<Integer, Integer>, Integer>();
            return this.recurse(s, t, 0, 0);
        }

        // Solution2: bottom-up 方法
        public int numDistinct(String s, String t) {
            int M = s.length();
            int N = t.length();

            int[][] dp = new int[M + 1][N + 1];

            // 初始化基本情况
            for (int j = 0; j <= N; j++) {
                dp[M][j] = 0;
            }

            // 初始化基本情况
            for (int i = 0; i <= M; i++) {
                dp[i][N] = 1;
            }

            // 从后向前迭代字符串，以满足递归解法的模型
            for (int i = M - 1; i >= 0; i--) {
                for (int j = N - 1; j >= 0; j--) {
                    // 注意，我们始终需要这个结果
                    dp[i][j] = dp[i + 1][j];

                    // 如果字符匹配，将递归调用的结果加到 dp 表格中的值中
                    if (s.charAt(i) == t.charAt(j)) {
                        dp[i][j] += dp[i + 1][j + 1];
                    }
                }
            }

            return dp[0][0];
        }

        // Solution3: 基于 Solution2 的空间优化方法
        public int numDistinct3(String s, String t) {
            int M = s.length();
            int N = t.length();

            int[] dp = new int[N];

            int prev = 1;

            // 从后向前迭代字符串，以满足递归解法的模型
            for (int i = M - 1; i >= 0; i--) {
                // 每次循环从行末尾开始，初始值为 1。注意，我们
                // 从 N - 1 而不是 N 开始循环，与前面的解法不同
                prev = 1;

                for (int j = N - 1; j >= 0; j--) {
                    // 将当前单元格的值保存下来，以便计算 dp[j - 1] 的值
                    int old_dpj = dp[j];

                    // 如果字符匹配，将递归调用的结果加到 dp 表格中的值中
                    if (s.charAt(i) == t.charAt(j)) {
                        dp[j] += prev;
                    }

                    // 更新 prev 变量
                    prev = old_dpj;
                }
            }

            return dp[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_115_DistinctSubsequences().new Solution();
        // TO TEST
        // 示例 1
        String s1 = "rabbbit";
        String t1 = "rabbit";
        System.out.println("示例 1:");
        System.out.println("输入: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("输出: " + solution.numDistinct(s1, t1)); // 应输出 3

        // 示例 2
        String s2 = "babgbag";
        String t2 = "bag";
        System.out.println("\n示例 2:");
        System.out.println("输入: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("输出: " + solution.numDistinct(s2, t2)); // 应输出 5
    }
}

/**
Given two strings s and t, return the number of distinct subsequences of s 
which equals t. 

 The test cases are generated so that the answer fits on a 32-bit signed 
integer. 

 
 Example 1: 

 
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
 

 Example 2: 

 
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag 

 
 Constraints: 

 
 1 <= s.length, t.length <= 1000 
 s and t consist of English letters. 
 

 Related Topics String Dynamic Programming 👍 6572 👎 289

*/
package leetcode.question.dp;
/**
 *@Question:  691. Stickers to Spell Word
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 70.42%
 *@Time  Complexity: O(2^N * N * T) 其中 N 是 target 的长度，T 是 stickers 的数量
 *@Space Complexity: O(2^N)
 */

/**
 * ### 解题思路
 *
 * 题目要求找到使用最少数量的贴纸拼出目标字符串的最小贴纸数量。我们可以使用动态规划（DP）来解决这个问题。具体思路如下：
 *
 * #### 1. 状态表示
 *
 * - 使用一个整数数组 `dp`，其中 `dp[state]` 表示达到状态 `state`（用二进制表示）所需要的最小贴纸数量。
 * - 状态 `state` 用二进制数表示。例如，如果目标字符串长度为 `N`，则 `state` 有 `2^N` 种可能。`state = 5` 表示目标字符串的第 `0` 和第 `2` 位字符被覆盖。
 *
 * #### 2. 初始化
 *
 * - 初始状态 `dp[0] = 0`，表示没有覆盖任何字符时需要 0 张贴纸。
 * - 其他状态初始化为 -1，表示这些状态暂时不可达。
 *
 * #### 3. 状态转移
 *
 * - 遍历所有状态 `state`：
 *   - 如果当前状态 `dp[state] == -1`，表示该状态不可达，跳过。
 *   - 否则，对于每张贴纸 `sticker`，尝试覆盖目标字符串的字符，更新状态：
 *     - 对于贴纸上的每个字符，尝试覆盖目标字符串中的字符，更新 `now` 状态。
 *     - `now` 表示当前使用贴纸后的新状态。
 *     - 更新 `dp[now]` 为 `dp[state] + 1`，表示使用当前贴纸后的最小贴纸数量。
 *
 * #### 4. 最终结果
 *
 * - 返回 `dp[(1 << N) - 1]`，表示覆盖所有目标字符的最小贴纸数量。
 *
 * ### 时间和空间复杂度分析
 *
 * - **时间复杂度**：
 *   - `O(2^N * N * T)`，其中 `N` 是目标字符串的长度，`T` 是贴纸的数量。
 *   - `2^N`：所有可能的状态数。
 *   - `N`：每个状态需要遍历目标字符串的每个字符。
 *   - `T`：每个状态需要遍历每张贴纸。
 *
 * - **空间复杂度**：
 *   - `O(2^N)`，用于存储 `dp` 数组。`dp` 数组大小为 `2^N`，每个元素存储一个状态。
 *
 * ### 总结
 *
 * 通过使用动态规划，我们可以高效地计算出使用最少数量的贴纸拼出目标字符串所需的最小贴纸数量。
 * 这个方法利用状态表示和状态转移来逐步构建解决方案，最终得到覆盖所有目标字符的最优解。虽然时间和空间复杂度较高，但对于长度适中的目标字符串和贴纸数量，仍然是可行的。
 */
public class LeetCode_691_StickersToSpellWord{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minStickers(String[] stickers, String target) {
            int N = target.length(); // 目标字符串的长度
            int[] dp = new int[1 << N]; // 动态规划数组，dp[state] 表示达到状态 state 的最小贴纸数量
            for (int i = 1; i < 1 << N; i++) dp[i] = -1; // 初始化 dp 数组，除了 dp[0]，其余都设为 -1

            for (int state = 0; state < 1 << N; state++) { // 遍历所有状态
                if (dp[state] == -1) continue; // 如果当前状态不可达，跳过
                for (String sticker: stickers) { // 遍历每个贴纸
                    int now = state; // 当前状态
                    for (char letter: sticker.toCharArray()) { // 遍历贴纸上的每个字符
                        for (int i = 0; i < N; i++) { // 遍历目标字符串的每个字符
                            if (((now >> i) & 1) == 1) continue; // 如果目标字符串第 i 位字符已经被覆盖，跳过
                            if (target.charAt(i) == letter) { // 如果贴纸上的字符匹配目标字符串第 i 位字符
                                now |= 1 << i; // 更新状态，表示目标字符串第 i 位字符被覆盖
                                break; // 继续处理贴纸上的下一个字符
                            }
                        }
                    }
                    if (dp[now] == -1 || dp[now] > dp[state] + 1) { // 更新 dp[now]
                        dp[now] = dp[state] + 1; // 更新状态 now 的最小贴纸数量
                    }
                }
            }
            return dp[(1 << N) - 1]; // 返回达到状态 (1<<N)-1 的最小贴纸数量，即覆盖所有目标字符的最小贴纸数量
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_691_StickersToSpellWord().new Solution();
        // 测试样例1
        String[] stickers1 = {"with", "example", "science"};
        String target1 = "thehat";
        System.out.println(solution.minStickers(stickers1, target1)); // 输出：3

        // 测试样例2
        String[] stickers2 = {"notice", "possible"};
        String target2 = "basicbasic";
        System.out.println(solution.minStickers(stickers2, target2)); // 输出：-1（因为不可能通过给定的贴纸拼出目标字符串）

        // 测试样例3
        String[] stickers3 = {"these", "guess", "about", "garden", "him"};
        String target3 = "atomher";
        System.out.println(solution.minStickers(stickers3, target3)); // 输出：3
    }
}

/**
We are given n different types of stickers. Each sticker has a lowercase 
English word on it. 

 You would like to spell out the given string target by cutting individual 
letters from your collection of stickers and rearranging them. You can use each 
sticker more than once if you want, and you have infinite quantities of each sticker.
 

 Return the minimum number of stickers that you need to spell out target. If 
the task is impossible, return -1. 

 Note: In all test cases, all words were chosen randomly from the 1000 most 
common US English words, and target was chosen as a concatenation of two random 
words. 

 
 Example 1: 

 
Input: stickers = ["with","example","science"], target = "thehat"
Output: 3
Explanation:
We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the 
target "thehat".
Also, this is the minimum number of stickers necessary to form the target 
string.
 

 Example 2: 

 
Input: stickers = ["notice","possible"], target = "basicbasic"
Output: -1
Explanation:
We cannot form the target "basicbasic" from cutting letters from the given 
stickers.
 

 
 Constraints: 

 
 n == stickers.length 
 1 <= n <= 50 
 1 <= stickers[i].length <= 10 
 1 <= target.length <= 15 
 stickers[i] and target consist of lowercase English letters. 
 

 Related Topics Array String Dynamic Programming Backtracking Bit Manipulation 
Bitmask 👍 1217 👎 112

*/
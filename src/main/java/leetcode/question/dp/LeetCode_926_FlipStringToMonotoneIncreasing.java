package leetcode.question.dp;
/**
 *@Question:  926. Flip String to Monotone Increasing
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 19.62%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * 问题描述：
 *
 * 给定一个由字符 '0' 和 '1' 组成的字符串 `s`，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。
 *
 * 要求返回使 `s` 变成一个单调递增字符串所需的最小翻转次数。
 *
 * 解题思路：
 *
 * 1. **双指针解法**：
 *    - 首先，遍历字符串 `s` 统计字符 '0' 的个数，记为 `m`。这代表如果我们把所有的 '0' 都转换为 '1'，需要的最小翻转次数。
 *    - 使用 `ans` 记录当前的最小翻转次数，初始化为 `m`。
 *    - 遍历字符串 `s`，如果遇到 '0'，则将 `m` 减一，表示当前位置的 '0' 已经被翻转为 '1'，更新 `ans` 为当前 `ans` 和 `m` 的较小值。
 *    - 如果遇到 '1'，则将 `m` 加一，表示当前位置的 '1' 不需要翻转。
 *    - 最终返回 `ans` 即为最小翻转次数。
 *
 * 2. **动态规划解法**：
 *    - 使用两个变量 `ans` 和 `num`，其中 `ans` 记录最小翻转次数，`num` 记录当前位置之前 '1' 的个数。
 *    - 遍历字符串 `s`，如果当前位置是 '0'，则考虑将其翻转为 '1'，需要的翻转次数是 `ans + 1`（即前一个位置的最优解加一）。
 *    - 如果当前位置是 '1'，则 `num` 增加。
 *    - 每次更新 `ans` 为当前 `ans` 和 `num` 的较小值。
 *    - 最终返回 `ans` 即为最小翻转次数。
 *
 * 时间复杂度分析：
 *
 * - **双指针解法**：时间复杂度为 O(N)，其中 N 是字符串 `s` 的长度。需要两次遍历字符串 `s`，每次遍历的时间复杂度为 O(N)。
 * - **动态规划解法**：时间复杂度同样为 O(N)，因为只需要一次遍历字符串 `s`。
 *
 * 空间复杂度分析：
 *
 * - **双指针解法**：空间复杂度为 O(1)，只需要常数级别的额外空间存储变量 `m` 和 `ans`。
 * - **动态规划解法**：空间复杂度为 O(1)，同样只需要常数级别的额外空间存储变量 `ans` 和 `num`。
 *
 * 综上所述，两种解法都能在线性时间内解决问题，并且使用了常数级别的额外空间，是比较高效的解决方案。
 */

public class LeetCode_926_FlipStringToMonotoneIncreasing{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // Solution1: two pointer
        // 使用双指针的解决方案
        public int minFlipsMonoIncr(String s) {
            int m = 0; // 记录字符串中 '0' 的个数
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '0') {
                    ++m;
                }
            }
            int ans = m; // 初始化最小翻转次数为 '0' 的个数
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '0') {
                    ans = Math.min(ans, --m); // 遇到 '0'，将 m 减少，更新最小翻转次数
                } else {
                    ++m; // 遇到 '1'，m 增加，表示不需要翻转
                }
            }
            return ans;
        }

        //Solution2: DP
        // 使用动态规划的解决方案
        public int minFlipsMonoIncr2(String s) {
            int ans = 0, num = 0; // ans 记录最小翻转次数，num 记录当前位置之前 '1' 的个数
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '0') {
                    ans = Math.min(num, ans + 1); // 如果当前位置是 '0'，考虑前一个位置是最优的情况
                } else {
                    ++num; // 如果当前位置是 '1'，则 '1' 的个数增加
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_926_FlipStringToMonotoneIncreasing().new Solution();
        // TO TEST
        String s = "00110";
        System.out.println(solution.minFlipsMonoIncr(s)); // Expected output: 1
        System.out.println(solution.minFlipsMonoIncr2(s)); // Expected output: 1
    }
}

/**
A binary string is monotone increasing if it consists of some number of 0's (
possibly none), followed by some number of 1's (also possibly none). 

 You are given a binary string s. You can flip s[i] changing it from 0 to 1 or 
from 1 to 0. 

 Return the minimum number of flips to make s monotone increasing. 

 
 Example 1: 

 
Input: s = "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
 

 Example 2: 

 
Input: s = "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.
 

 Example 3: 

 
Input: s = "00011000"
Output: 2
Explanation: We flip to get 00000000.
 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s[i] is either '0' or '1'. 
 

 Related Topics String Dynamic Programming 👍 4398 👎 178

*/
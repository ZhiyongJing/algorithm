package leetcode.question.dp;
/**
 *@Question:  926. Flip String to Monotone Increasing
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 19.62%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * 题目描述：
 * ----------------------
 * 给定一个仅包含字符 '0' 和 '1' 的二进制字符串 `s`，要求通过最少的翻转次数，使得 `s` 变为 **单调递增的字符串**。
 * - **单调递增字符串** 指的是 **任意位置的 '1' 都不能出现在 '0' 之前**，即字符串形态必须是 `000...111`。
 * - **允许的操作**：翻转任意一个字符，即 '0' -> '1' 或 '1' -> '0'。
 * - 目标是求 **最小翻转次数**。
 *
 * 示例：
 * 1. **输入**: `s = "00110"`
 *    - 可能的翻转方式：
 *      - 翻转 `s[2]`（索引从 0 开始）得到 `"00010"`（翻转次数 = 1）
 *      - 翻转 `s[3]` 也可以达到相同效果
 *    - **输出**: `1`
 *
 * 2. **输入**: `s = "010110"`
 *    - 可能的翻转方式：
 *      - 翻转 `s[1]` 和 `s[3]`，变成 `"000111"`（翻转次数 = 2）
 *    - **输出**: `2`
 *
 * 3. **输入**: `s = "00011000"`
 *    - 可能的翻转方式：
 *      - 翻转 `s[4]` 和 `s[5]`，变成 `"00000000"`（翻转次数 = 2）
 *    - **输出**: `2`
 *
 * ----------------------
 *
 * 解题思路：
 * ----------------------
 * **方法 1：双指针（前缀+后缀统计）**
 * 1. **计算所有 `0` 的数量**：假设最优策略是将所有 `0` 翻转为 `1`，初始化 `m = 所有 '0' 的个数`。
 * 2. **遍历字符串**：
 *    - 遇到 `'0'`：减少 `m`，表示少翻转一个 `0`（因为它本身就应该是 `0`）。
 *    - 遇到 `'1'`：增加 `m`，表示必须翻转这个 `1` 以保持单调递增。
 * 3. **最终 `m` 的最小值就是最优解**。
 *
 * **方法 2：动态规划**
 * 1. 维护 `ans`（最小翻转次数）和 `num`（前面 `1` 的个数）。
 * 2. 遍历字符串：
 *    - 如果 `s[i] == '0'`，说明有两种选择：
 *      - 直接翻转 `0` -> `1`，保持前面 `1` 的数量不变。
 *      - 让 `1` 继续保持单调递增，并翻转前面的 `1` 使其变为 `0`。
 *    - 取两种情况的最小值更新 `ans`。
 * 3. 如果 `s[i] == '1'`，则直接增加 `num` 计数（意味着 `1` 需要维持单调性）。
 *
 * **示例分析**
 * 输入：`s = "00110"`
 * - `ans = 0, num = 0`
 * - 遍历 `s`：
 *   - `s[0] = '0'` -> `ans = min(num=0, ans+1=1) = 0`
 *   - `s[1] = '0'` -> `ans = min(num=0, ans+1=1) = 0`
 *   - `s[2] = '1'` -> `num = 1`
 *   - `s[3] = '1'` -> `num = 2`
 *   - `s[4] = '0'` -> `ans = min(num=2, ans+1=1) = 1`
 * - **输出**: `1`
 *
 * ----------------------
 *
 * 时间和空间复杂度：
 * ----------------------
 * **方法 1：双指针**
 * - **时间复杂度：O(N)** （两次遍历计算 `m` 和 `ans`）
 * - **空间复杂度：O(1)** （只使用额外的整数变量）
 *
 * **方法 2：动态规划**
 * - **时间复杂度：O(N)** （单次遍历字符串）
 * - **空间复杂度：O(1)** （只存储 `ans` 和 `num`）
 *
 */


public class LeetCode_926_FlipStringToMonotoneIncreasing{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // Solution1: Two Pointer（双指针）
        // 该方法通过遍历字符串两次来计算最小的翻转次数，使字符串变成单调递增的形式
        public int minFlipsMonoIncr(String s) {
            int m = 0; // 记录字符串中 '0' 的总个数
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '0') {
                    ++m; // 统计 '0' 的总个数
                }
            }
            int ans = m; // 初始化最小翻转次数为所有 '0' 的个数（假设全部翻转为 '1'）
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '0') {
                    ans = Math.min(ans, --m); // 遇到 '0'，减少 m，并更新最小翻转次数
                } else {
                    ++m; // 遇到 '1'，增加 m（表示翻转 '1' 为 '0' 的需求）
                }
            }
            return ans;
        }

        // Solution2: Dynamic Programming（动态规划）
        // 该方法使用动态规划来计算最小翻转次数，使字符串变成单调递增的形式
        public int minFlipsMonoIncr2(String s) {
            int ans = 0; // 记录最小翻转次数
            int num = 0; // 记录当前位置之前 '1' 的个数（不翻转的 '1' 的计数）
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '0') {
                    // 如果当前字符是 '0'，有两种选择：
                    // 1. 继续维持当前翻转次数 ans
                    // 2. 翻转当前 '0'，因此 ans + 1
                    ans = Math.min(num, ans + 1);
                } else {
                    // 当前字符是 '1'，我们不需要翻转，但 '1' 的个数需要增加
                    ++num;
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_926_FlipStringToMonotoneIncreasing().new Solution();

        // 测试样例
        String s1 = "00110";
        System.out.println("Input: " + s1);
        System.out.println("Two Pointer Solution Output: " + solution.minFlipsMonoIncr(s1)); // 预期输出: 1
        System.out.println("DP Solution Output: " + solution.minFlipsMonoIncr2(s1)); // 预期输出: 1

        String s2 = "010110";
        System.out.println("Input: " + s2);
        System.out.println("Two Pointer Solution Output: " + solution.minFlipsMonoIncr(s2)); // 预期输出: 2
        System.out.println("DP Solution Output: " + solution.minFlipsMonoIncr2(s2)); // 预期输出: 2

        String s3 = "00011000";
        System.out.println("Input: " + s3);
        System.out.println("Two Pointer Solution Output: " + solution.minFlipsMonoIncr(s3)); // 预期输出: 2
        System.out.println("DP Solution Output: " + solution.minFlipsMonoIncr2(s3)); // 预期输出: 2
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
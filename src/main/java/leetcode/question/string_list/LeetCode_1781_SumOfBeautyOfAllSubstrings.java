package leetcode.question.string_list;
/**
 *@Question:  1781. Sum of Beauty of All Substrings
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 41.79%
 *@Time Complexity: O(N^2), where N is the length of the string.
 *@Space Complexity: O(1), since we only use a fixed-size frequency array.
 */
/**
 * 题目描述：
 * 1781. Sum of Beauty of All Substrings（所有子字符串美丽值的总和）
 *
 * 给定一个字符串 `s`，它的美丽值定义如下：
 * - 对于某个子字符串 `sub_s`，其美丽值为：**出现频率最高的字符频率 - 出现频率最低的字符频率**。
 * - 计算 `s` 所有子字符串的美丽值之和。
 *
 * **示例 1**
 * ```plaintext
 * 输入: s = "aabcb"
 * 输出: 5
 * 解释:
 * 所有子串及其美丽值如下：
 * - "a" -> 0
 * - "aa" -> 0
 * - "aab" -> 1
 * - "aabc" -> 1
 * - "aabcb" -> 2
 * - "a" -> 0
 * - "ab" -> 0
 * - "abc" -> 1
 * - "abcb" -> 1
 * - "b" -> 0
 * - "bc" -> 0
 * - "bcb" -> 1
 * - "c" -> 0
 * - "cb" -> 0
 * - "b" -> 0
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: s = "aabcbaa"
 * 输出: 17
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * **1. 使用双层循环遍历所有子字符串**
 * - 设 `s` 的长度为 `n`，遍历所有可能的起始位置 `i` 和结束位置 `j`：
 *   - 外层循环 `i`：子串的起点
 *   - 内层循环 `j`：子串的终点
 * - 计算 `s[i:j]` 的 **最大频率字符** 和 **最小频率字符**，求美丽值。
 *
 * **2. 统计字符出现频率**
 * - 维护一个 `freq[26]` 数组记录当前子串的字符出现次数：
 *   - `freq[ch - 'a']++` 记录 `ch` 在当前子串中的出现次数
 * - 遍历 `freq[]` 计算：
 *   - **最大字符频率 `maxFreq`**
 *   - **最小字符频率 `minFreq`**（`freq[k] > 0` 的最小值）
 * - `beauty = maxFreq - minFreq`，累加到总和 `ans`。
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `s = "aabcb"`
 *
 * **计算所有子串美丽值**
 * ```plaintext
 * 子串 "a"      -> [a=1]             -> max=1, min=1, beauty=0
 * 子串 "aa"     -> [a=2]             -> max=2, min=2, beauty=0
 * 子串 "aab"    -> [a=2, b=1]        -> max=2, min=1, beauty=1
 * 子串 "aabc"   -> [a=2, b=1, c=1]   -> max=2, min=1, beauty=1
 * 子串 "aabcb"  -> [a=2, b=2, c=1]   -> max=2, min=1, beauty=1
 * 子串 "bcb"    -> [b=2, c=1]        -> max=2, min=1, beauty=1
 * ```
 * **最终输出** `5`
 *
 * ---
 *
 * **时间复杂度分析**
 * - **遍历所有子字符串：O(N^2)**
 * - **计算 `maxFreq` 和 `minFreq`：O(26) ≈ O(1)**
 * - **总时间复杂度：O(N^2)**
 *
 * **空间复杂度分析**
 * - 只使用了一个 **固定大小** 的 `freq[26]` 数组，**空间复杂度 `O(1)`**。
 */


public class LeetCode_1781_SumOfBeautyOfAllSubstrings{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int beautySum(String s) {
            // 结果变量 ans 存储所有子串的美丽值之和
            int ans = 0;

            // 遍历字符串的所有可能起始位置 i
            for (int i = 0; i < s.length(); i++) {
                // 创建一个频率数组 freq[]，用于存储当前子串中每个字符的出现次数
                int[] freq = new int[26];

                // 遍历以 i 为起始点的所有子串，终止位置为 j
                for (int j = i; j < s.length(); j++) {
                    // 获取当前字符并更新其频率
                    char ch = s.charAt(j);
                    freq[ch - 'a']++;

                    // 初始化 max 和 min 变量
                    int max = Integer.MIN_VALUE;
                    int min = Integer.MAX_VALUE;

                    // 计算当前子串的最大和最小频率
                    for (int k = 0; k < 26; k++) {
                        if (freq[k] > 0) { // 只考虑出现过的字符
                            min = Math.min(min, freq[k]);
                            max = Math.max(max, freq[k]);
                        }
                    }

                    // 将当前子串的美丽值 (max - min) 加入结果
                    ans += max - min;
                }
            }
            // 返回所有子串的美丽值之和
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1781_SumOfBeautyOfAllSubstrings().new Solution();

        // 测试样例 1
        String s1 = "aabcb";
        System.out.println(solution.beautySum(s1)); // 预期输出: 5

        // 测试样例 2
        String s2 = "aabcbaa";
        System.out.println(solution.beautySum(s2)); // 预期输出: 17

        // 测试样例 3
        String s3 = "abc";
        System.out.println(solution.beautySum(s3)); // 预期输出: 0

        // 测试样例 4
        String s4 = "aaaa";
        System.out.println(solution.beautySum(s4)); // 预期输出: 0

        // 测试样例 5
        String s5 = "abacaba";
        System.out.println(solution.beautySum(s5)); // 预期输出: 21
    }
}

/**
The beauty of a string is the difference in frequencies between the most 
frequent and least frequent characters. 

 
 For example, the beauty of "abaacc" is 3 - 1 = 2. 
 

 Given a string s, return the sum of beauty of all of its substrings. 

 
 Example 1: 

 
Input: s = "aabcb"
Output: 5
Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb",
"abcb","bcb"], each with beauty equal to 1. 

 Example 2: 

 
Input: s = "aabcbaa"
Output: 17
 

 
 Constraints: 

 
 1 <= s.length <= 500 
 s consists of only lowercase English letters. 
 

 Related Topics Hash Table String Counting 👍 1251 👎 192

*/
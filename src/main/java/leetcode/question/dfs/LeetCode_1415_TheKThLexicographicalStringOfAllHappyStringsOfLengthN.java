package leetcode.question.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  1415. The k-th Lexicographical String of All Happy Strings of Length n
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 63.25999999999999%
 *@Time  Complexity: O(2^n * N) for solution1, O(N) for solution2
 * 该算法使用回溯法按照字典序生成快乐字符串，并在找到第 k 个字符串时停止。
 * 在最坏情况下，该算法会在终止前生成 min(k, 3⋅2^(n−1)) 个字符串。
 * 对于每个字符串，它执行 n 次递归调用（每个字符一次），每次递归调用只涉及常量时间操作，如检查当前字符是否合法、
 * 更新当前字符串等。因此，总时间复杂度为 O(k⋅n) 或 O(n*2^n)。
 * 生成的字符串数量是 O(2^n)。
 *@Space Complexity: O(N) for solution1, O(1) for solution2
 */
/**
 * 题目描述：
 * 给定整数 n 和 k，返回长度为 n 的所有"快乐字符串"的字典序中的第 k 个字符串。
 * "快乐字符串"定义如下：
 * - 仅包含字符 'a'，'b'，'c'。
 * - 任意两个相邻字符都不相同。
 *
 * 如果没有第 k 个字符串，则返回空字符串 ""。
 *
 * 示例 1：
 * 输入：n = 3, k = 9
 * 输出："cab"
 * 解释：按字典序排列，所有长度为 3 的快乐字符串如下：
 * ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]
 * 第 9 个字符串是 "cab"。
 *
 * 示例 2：
 * 输入：n = 3, k = 10
 * 输出：""
 * 解释：一共有 9 个快乐字符串，所以第 10 个字符串不存在，返回 ""。
 *
 * 约束：
 * - 1 <= n <= 10
 * - 1 <= k <= 100
 */

/**
 * 解题思路：
 *
 * 方案 1（回溯法）：
 * 1. 采用递归生成所有可能的快乐字符串，确保每次添加的字符不同于前一个字符。
 * 2. 维护一个计数变量 indexInSortedList 来追踪生成的字符串数量。
 * 3. 每生成一个完整的字符串，检查是否是第 k 个，如果是，则存储并停止递归。
 * 4. 由于回溯生成的是按字典序排列的，所以第 k 个字符串就是我们需要的结果。
 *
 * 例子：
 * 假设 n = 3, k = 4
 * 生成顺序：
 * - "aba" (1)
 * - "abc" (2)
 * - "aca" (3)
 * - "acb" (4)  <- 找到第 4 个字符串，返回 "acb"
 *
 * 方案 2（数学计算法）：
 * 1. 计算长度为 n 的所有快乐字符串的总数 total = 3 * 2^(n-1)。
 * 2. 如果 k 超过 total，直接返回 ""。
 * 3. 按照字典序选择第一个字符：
 *    - "a" 开头的字符串占 1/3，总共有 2^(n-1) 个，起始索引为 1。
 *    - "b" 开头的起始索引是 startB = 1 + 2^(n-1)。
 *    - "c" 开头的起始索引是 startC = 1 + 2 * 2^(n-1)。
 * 4. 根据 k 值决定首字母：
 *    - 如果 k 落在 "a" 的范围，首字母为 'a'，更新 k 值。
 *    - 如果 k 落在 "b" 的范围，首字母为 'b'，更新 k 值。
 *    - 如果 k 落在 "c" 的范围，首字母为 'c'，更新 k 值。
 * 5. 递归处理剩余字符：
 *    - 计算当前字符组的一半（midpoint = 2^(n-i-1)）。
 *    - 根据 k 选择下一个字符，并递归继续。
 *
 * 例子：
 * 假设 n = 3, k = 9：
 * - 总共的快乐字符串个数 = 3 * 2^(3-1) = 12。
 * - "a" 开头的占 4 个，"b" 开头的占 4 个，"c" 开头的占 4 个。
 * - 由于 k = 9，落在 "c" 组，因此第一个字符为 'c'，更新 k = 9 - 8 = 1。
 * - 在 "c" 组中：
 *   - "ca" 组的索引范围是 [8, 10]，所以选择 'a'，更新 k = 1。
 *   - "cab" 的索引是第 9 个，最终结果为 "cab"。
 */

/**
 * 时间和空间复杂度：
 *
 * 方案 1（回溯法）：
 * - 时间复杂度：O(2^n * n)，因为最多可能需要生成所有快乐字符串。
 * - 空间复杂度：O(n)，递归栈的深度最大为 n。
 *
 * 方案 2（数学计算法）：
 * - 时间复杂度：O(n)，因为我们通过数学计算直接确定每个字符。
 * - 空间复杂度：O(1)，仅使用了常数额外空间。
 */


public class LeetCode_1415_TheKThLexicographicalStringOfAllHappyStringsOfLengthN{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * 递归生成快乐字符串
         * @param n 目标字符串长度
         * @param k 需要找到的第 k 个快乐字符串
         * @param currentString 当前生成的字符串
         * @param indexInSortedList 记录当前生成的字符串数量
         * @param result 结果数组，存储找到的 k-th 字符串
         */
        private void generateHappyStrings(
                int n,
                int k,
                StringBuilder currentString,
                int[] indexInSortedList,
                String[] result
        ) {
            // 如果当前字符串长度达到目标长度
            if (currentString.length() == n) {
                indexInSortedList[0]++; // 记录当前已生成的字符串个数

                // 如果当前是第 k 个字符串，则存入结果数组
                if (indexInSortedList[0] == k) result[0] = currentString.toString();
                return;
            }

            // 尝试添加字符 'a'，'b'，'c'
            for (char currentChar = 'a'; currentChar <= 'c'; currentChar++) {
                // 如果当前字符与前一个字符相同，则跳过，确保相邻字符不同
                if (
                        currentString.length() > 0 &&
                                currentString.charAt(currentString.length() - 1) == currentChar
                ) continue;

                // 追加当前字符
                currentString.append(currentChar);

                // 递归生成下一个字符
                generateHappyStrings(
                        n,
                        k,
                        currentString,
                        indexInSortedList,
                        result
                );

                // 如果已经找到 k-th 字符串，则提前返回
                if (result[0] != null) return;

                // 回溯：删除最后一个字符，尝试其他可能的字符
                currentString.deleteCharAt(currentString.length() - 1);
            }
        }

        /**
         * 方案1：回溯法
         * @param n 目标字符串长度
         * @param k 需要找到的第 k 个快乐字符串
         * @return 第 k 个快乐字符串，若不存在返回 ""
         */
        public String getHappyString1(int n, int k) {
            StringBuilder currentString = new StringBuilder();
            String[] result = new String[1]; // 记录找到的 k-th 字符串
            int[] indexInSortedList = new int[1]; // 记录当前已生成的字符串个数

            // 生成快乐字符串并找到第 k 个
            generateHappyStrings(n, k, currentString, indexInSortedList, result);
            return result[0] == null ? "" : result[0];
        }

        /**
         * 方案2：直接计算法
         * @param n 目标字符串长度
         * @param k 需要找到的第 k 个快乐字符串
         * @return 第 k 个快乐字符串，若不存在返回 ""
         */
        public String getHappyString(int n, int k) {
            // 计算长度为 n 的快乐字符串总数 3 * 2^(n-1)
            int total = 3 * (1 << (n - 1));

            // 如果 k 超过总数，则返回空字符串
            if (k > total) return "";

            StringBuilder result = new StringBuilder(n);
            // 初始化结果字符串，默认填充 'a'
            for (int i = 0; i < n; i++) {
                result.append('a');
            }

            // 定义字符转换规则
            Map<Character, Character> nextSmallest = new HashMap<>();
            nextSmallest.put('a', 'b');
            nextSmallest.put('b', 'a');
            nextSmallest.put('c', 'a');

            Map<Character, Character> nextGreatest = new HashMap<>();
            nextGreatest.put('a', 'c');
            nextGreatest.put('b', 'c');
            nextGreatest.put('c', 'b');

            // 计算以 'a'、'b'、'c' 开头的字符串的起始索引
            int startA = 1;
            int startB = startA + (1 << (n - 1));
            int startC = startB + (1 << (n - 1));

            // 确定字符串的第一个字符
            if (k < startB) {
                result.setCharAt(0, 'a');
                k -= startA;
            } else if (k < startC) {
                result.setCharAt(0, 'b');
                k -= startB;
            } else {
                result.setCharAt(0, 'c');
                k -= startC;
            }

            // 逐步确定后续字符
            for (int charIndex = 1; charIndex < n; charIndex++) {
                // 计算当前字符所处的分组大小
                int midpoint = (1 << (n - charIndex - 1));

                // 确定当前字符
                if (k < midpoint) {
                    result.setCharAt(
                            charIndex,
                            nextSmallest.get(result.charAt(charIndex - 1))
                    );
                } else {
                    result.setCharAt(
                            charIndex,
                            nextGreatest.get(result.charAt(charIndex - 1))
                    );
                    k -= midpoint;
                }
            }

            return result.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1415_TheKThLexicographicalStringOfAllHappyStringsOfLengthN().new Solution();

        // 测试样例
        System.out.println(solution.getHappyString(3, 9));  // 预期输出: "cab"
        System.out.println(solution.getHappyString(3, 1));  // 预期输出: "aba"
        System.out.println(solution.getHappyString(3, 4));  // 预期输出: "aca"
        System.out.println(solution.getHappyString(3, 10)); // 预期输出: ""
        System.out.println(solution.getHappyString1(3, 9)); // 预期输出: "cab"
        System.out.println(solution.getHappyString1(3, 1)); // 预期输出: "aba"
    }
}

/**
A happy string is a string that: 

 
 consists only of letters of the set ['a', 'b', 'c']. 
 s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-
indexed). 
 

 For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings 
and strings "aa", "baa" and "ababbc" are not happy strings. 

 Given two integers n and k, consider a list of all happy strings of length n 
sorted in lexicographical order. 

 Return the kth string of this list or return an empty string if there are less 
than k happy strings of length n. 

 
 Example 1: 

 
Input: n = 1, k = 3
Output: "c"
Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. 
The third string is "c".
 

 Example 2: 

 
Input: n = 1, k = 4
Output: ""
Explanation: There are only 3 happy strings of length 1.
 

 Example 3: 

 
Input: n = 3, k = 9
Output: "cab"
Explanation: There are 12 different happy string of length 3 ["aba", "abc", 
"aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will 
find the 9ᵗʰ string = "cab"
 

 
 Constraints: 

 
 1 <= n <= 10 
 1 <= k <= 100 
 

 Related Topics String Backtracking 👍 1490 👎 44

*/
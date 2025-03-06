package leetcode.question.dfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *@Question:  1079. Letter Tile Possibilities
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 67.47%
 *@Time  Complexity: O(N * 2^N)
 *@Space Complexity: O(N * 2^N)
 */
/**
 * 题目描述：
 * LeetCode 1079. Letter Tile Possibilities
 * 给定一组字母牌 `tiles`（可能包含重复字母），你可以按任意顺序排列这些字母，
 * 但每个字母只能使用一次。请计算可以生成的**非空不同序列**的数量。
 *
 * 示例 1：
 * 输入: tiles = "AAB"
 * 输出: 8
 * 解释:
 * 可生成的不同序列有：
 * "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"
 *
 * 示例 2：
 * 输入: tiles = "AAABBC"
 * 输出: 188
 *
 * 题目要求返回所有可以排列出的**不同的非空字符串序列**的个数。
 */

/**
 * 解题思路：
 * 该问题可以使用 **回溯 + 递归 + 排列计算** 解决。
 * 主要思路如下：
 *
 * 1. **使用 Set 去重，生成所有可能的子序列**
 *    - 由于字母可能有重复，不能直接用全排列计算，否则会计算出重复序列。
 *    - 先将 `tiles` **排序**，确保相同字符相邻，方便处理重复情况。
 *    - 通过递归，尝试所有可能的子序列组合，每次递归时可以选择“包含当前字符”或“跳过当前字符”。
 *    - 递归时将生成的序列存入 `Set`，保证每个序列只计算一次。
 *
 * 2. **计算每个序列的不同排列方式**
 *    - 对于生成的每个唯一子序列，计算其排列方式：
 *      - 设 `seq.length = N`，若某个字符出现 `k` 次，则排列数公式：
 *        ```
 *        全排列数 = N! / (每个字符的 k! 之积)
 *        ```
 *      - 例如 `"AAB"`：
 *        - `"AAB"` 有 `3! / (2!) = 3` 种排列方式（"AAB", "ABA", "BAA"）。
 *
 * 3. **递归构造所有子序列，并计算它们的排列**
 *    - 使用 `generateSequences(tiles, current, pos, seen)` 递归构造所有子序列：
 *      - `pos` 遍历 `tiles` 的每个字符，每次选择“包含当前字符”或“跳过当前字符”。
 *      - 递归终止时，检查是否是新的有效序列，若是，则计算其排列数并返回结果。
 *
 * 4. **最终计算结果**
 *    - 遍历所有可能的非空子序列，计算它们的不同排列总数，返回结果。
 *    - 由于 `Set` 只存储唯一序列，确保不会重复计算。
 *
 * **举例分析**
 * 假设输入 `tiles = "AAB"`：
 * 1. 先将 `tiles` 排序，得到 `"AAB"`
 * 2. 生成所有可能的非空子序列：
 *    ```
 *    "A", "B", "AA", "AB", "BA", "AAB"
 *    ```
 * 3. 计算每个序列的排列：
 *    - `"A"` → 1
 *    - `"B"` → 1
 *    - `"AA"` → 1（2! / 2!）
 *    - `"AB"` → 2（2!）
 *    - `"BA"` → 2（2!）
 *    - `"AAB"` → 3（3! / 2!）
 * 4. 总数 = `1 + 1 + 1 + 2 + 2 + 3 = 8`
 *
 * **最终返回 8**
 */

/**
 * 时间和空间复杂度分析：
 *
 * 1. **时间复杂度：O(N * 2^N)**
 *    - `generateSequences()` 生成所有可能的子序列，每个字符有两种选择（包含或不包含）。
 *    - 共有 `2^N` 个子序列（包含空集），但我们排除了空字符串，所以是 `O(2^N)`。
 *    - 对于每个子序列，我们计算排列数，计算过程为 `O(N)`，因此总复杂度为 `O(N * 2^N)`。
 *
 * 2. **空间复杂度：O(N * 2^N)**
 *    - `Set` 存储 `O(2^N)` 个子序列，每个子序列最多 `O(N)` 长度，因此 `O(N * 2^N)`。
 *    - 递归深度最大 `O(N)`，但仍然 `O(N * 2^N)` 主导。
 *
 * 综上，该解法利用 **回溯 + 去重 + 排列计算** 高效求解该问题。
 */


public class LeetCode_1079_LetterTilePossibilities{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int numTilePossibilities(String tiles) {
            // 使用 Set 记录已经生成的序列，防止重复计算
            Set<String> seen = new HashSet<>();

            // 将字符排序，方便处理重复字符
            char[] chars = tiles.toCharArray();
            Arrays.sort(chars);
            String sortedTiles = new String(chars);

            // 生成所有可能的序列，并计算其排列方式（减去空字符串的情况）
            return generateSequences(sortedTiles, "", 0, seen) - 1;
        }

        private int factorial(int n) {
            // 计算 n!（n 的阶乘）
            if (n <= 1) {
                return 1;
            }

            int result = 1;
            for (int num = 2; num <= n; num++) {
                result *= num;
            }
            return result;
        }

        private int countPermutations(String seq) {
            // 统计字符串 seq 中每个字符的出现次数
            int[] charCount = new int[26];
            for (char ch : seq.toCharArray()) {
                charCount[ch - 'A']++;
            }

            // 计算排列数：seq.length! / (每个字符的阶乘之积)
            int total = factorial(seq.length());
            for (int count : charCount) {
                total /= factorial(count);
            }
            return total;
        }

        private int generateSequences(
                String tiles,
                String current,
                int pos,
                Set<String> seen
        ) {
            // 如果 pos 超过了字符串的长度，检查是否是新的有效序列
            if (pos >= tiles.length()) {
                if (seen.add(current)) { // 只有新出现的序列才计算其排列数
                    return countPermutations(current);
                }
                return 0;
            }

            // 递归两种情况：
            // 1. 不包含当前字符，跳过该字符
            // 2. 包含当前字符，将其加入 current，并继续递归
            return (
                    generateSequences(tiles, current, pos + 1, seen) +
                            generateSequences(tiles, current + tiles.charAt(pos), pos + 1, seen)
            );
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1079_LetterTilePossibilities().new Solution();

        // 测试用例
        System.out.println(solution.numTilePossibilities("AAB")); // 期望输出: 8
        System.out.println(solution.numTilePossibilities("AAABBC")); // 期望输出: 188
        System.out.println(solution.numTilePossibilities("XYZ")); // 期望输出: 15
    }
}

/**
You have n tiles, where each tile has one letter tiles[i] printed on it. 

 Return the number of possible non-empty sequences of letters you can make 
using the letters printed on those tiles. 

 
 Example 1: 

 
Input: tiles = "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", 
"ABA", "BAA".
 

 Example 2: 

 
Input: tiles = "AAABBC"
Output: 188
 

 Example 3: 

 
Input: tiles = "V"
Output: 1
 

 
 Constraints: 

 
 1 <= tiles.length <= 7 
 tiles consists of uppercase English letters. 
 

 Related Topics Hash Table String Backtracking Counting 👍 3071 👎 84

*/
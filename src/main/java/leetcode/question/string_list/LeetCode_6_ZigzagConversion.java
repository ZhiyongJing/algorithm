package leetcode.question.string_list;
/**
  *@Question:  6. Zigzag Conversion
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 74.29%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */
/**
 * ==============================
 * 题目描述：LeetCode 6 - Zigzag Conversion
 * ==============================
 * 给定一个字符串 `s` 和一个整数 `numRows`，将字符串按 **Z字形（zigzag）排列**。
 *
 * **Z字形排列规则：**
 * - 首先按照从上到下的顺序填写每一行。
 * - 到达最后一行后，按从下到上的顺序填写斜线部分，直到回到第一行。
 * - 重复上述过程，直到字符串中的所有字符都被放置。
 *
 * 按照 Z 字形排列后，逐行读取字符，拼接成新的字符串返回。
 *
 * **示例1：**
 * - 输入：`s = "PAYPALISHIRING"`，`numRows = 3`
 * - Z字形排列如下：
 *   ```
 *   P   A   H   N
 *   A P L S I I G
 *   Y   I   R
 *   ```
 * - 输出：`"PAHNAPLSIIGYIR"`
 *
 * **示例2：**
 * - 输入：`s = "PAYPALISHIRING"`，`numRows = 4`
 * - Z字形排列如下：
 *   ```
 *   P     I    N
 *   A   L S  I G
 *   Y A   H R
 *   P     I
 *   ```
 * - 输出：`"PINALSIGYAHRPI"`
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * **核心思路：按行遍历 + 周期跳跃**
 * - 我们可以将字符串看成按周期重复的“Z字形”结构。
 * - 每个完整的“Z”周期包含 `2 * (numRows - 1)` 个字符。
 * - 按行遍历字符串，逐行读取每一行的字符，并跳跃到下一个周期中的相应字符。
 * - 特殊情况：
 *   - 如果只有一行（`numRows = 1`），直接返回原字符串。
 *   - 如果是第一行或最后一行，只读取一个字符。
 *   - 如果是中间行，还需要额外读取“斜线部分”的字符。
 *
 * ------------------------------
 * **解法步骤：**
 * ------------------------------
 * **步骤1：处理特殊情况**
 * - 如果 `numRows = 1`，则无需转换，直接返回原字符串。
 *
 * **步骤2：计算每个“Z”周期的字符数**
 * - 计算一个完整“Z”周期的字符数为 `charsInSection = 2 * (numRows - 1)`。
 *
 * **步骤3：逐行遍历字符串**
 * - 对于每一行：
 *   1. 找到当前行的第一个字符，并将其加入结果字符串。
 *   2. 计算当前行在斜线部分的字符索引（仅适用于中间行）。
 *   3. 按周期跳跃到下一节的字符，继续添加到结果字符串。
 *
 * ------------------------------
 * **举例解释：**
 * **示例1：`s = "PAYPALISHIRING"`，`numRows = 3`**
 * - 原字符串：`PAYPALISHIRING`
 * - 周期长度：`charsInSection = 4`
 *
 * **Z字形排列过程：**
 * ```
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * ```
 *
 * - 第1行：`P`，跳过4个字符后是`A`，再跳过4个字符后是`H`，最后是`N`。
 * - 第2行：`A`，然后是斜线部分的`P`，跳过4个字符后是`L`，再是斜线部分的`S`，以此类推。
 * - 第3行：`Y`，跳过4个字符后是`I`，最后是`R`。
 * - 输出结果：`"PAHNAPLSIIGYIR"`。
 *
 * **示例2：`s = "PAYPALISHIRING"`，`numRows = 4`**
 * - 周期长度：`charsInSection = 6`
 *
 * **Z字形排列过程：**
 * ```
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * ```
 *
 * - 第1行：`P`，跳过6个字符后是`I`，再跳过6个字符后是`N`。
 * - 第2行：`A`，然后是斜线部分的`L`，跳过6个字符后是`S`，再是斜线部分的`I`，以此类推。
 * - 第3行：同理。
 * - 第4行：同理。
 * - 输出结果：`"PINALSIGYAHRPI"`。
 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：O(n)**
 * - 其中 `n` 是字符串的长度。
 * - 需要遍历整个字符串一次，因此时间复杂度为 O(n)。
 *
 * **空间复杂度：O(n)**
 * - 结果字符串的长度与输入字符串相同，因此需要 O(n) 的额外空间。
 *
 * **总结：**
 * - 时间复杂度和空间复杂度均为线性级别 O(n)。
 */


public class LeetCode_6_ZigzagConversion {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 将字符串以“Z字形”排列，并按行读取组成新的字符串
         * @param s 原始字符串
         * @param numRows Z字形排列的行数
         * @return 按Z字形读取后的字符串
         */
        public String convert(String s, int numRows) {
            // 如果只有一行，则无需转换，直接返回原字符串
            if (numRows == 1) {
                return s;
            }

            // 使用 StringBuilder 存储结果字符串
            StringBuilder answer = new StringBuilder();
            // 获取字符串的长度
            int n = s.length();
            // 每个“Z”字形的完整周期包含的字符数
            int charsInSection = 2 * (numRows - 1);

            // 遍历每一行
            for (int currRow = 0; currRow < numRows; ++currRow) {
                // 当前行的第一个字符的索引
                int index = currRow;

                // 遍历当前行的所有字符
                while (index < n) {
                    // 添加当前行的字符到结果中
                    answer.append(s.charAt(index));

                    // 如果当前行不是第一行或最后一行，则需要额外添加一个字符
                    if (currRow != 0 && currRow != numRows - 1) {
                        // 计算中间字符的索引
                        int charsInBetween = charsInSection - 2 * currRow;
                        int secondIndex = index + charsInBetween;

                        // 如果中间字符的索引在字符串范围内，添加到结果中
                        if (secondIndex < n) {
                            answer.append(s.charAt(secondIndex));
                        }
                    }

                    // 跳到当前行的下一个字符
                    index += charsInSection;
                }
            }

            // 返回拼接后的字符串
            return answer.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_6_ZigzagConversion().new Solution();

        // 测试用例1
        String input1 = "PAYPALISHIRING";
        int numRows1 = 3;
        String result1 = solution.convert(input1, numRows1);
        System.out.println("测试用例1结果：" + result1);  // 预期输出："PAHNAPLSIIGYIR"

        // 测试用例2
        String input2 = "PAYPALISHIRING";
        int numRows2 = 4;
        String result2 = solution.convert(input2, numRows2);
        System.out.println("测试用例2结果：" + result2);  // 预期输出："PINALSIGYAHRPI"

        // 测试用例3
        String input3 = "ABCDE";
        int numRows3 = 2;
        String result3 = solution.convert(input3, numRows3);
        System.out.println("测试用例3结果：" + result3);  // 预期输出："ACEBD"
    }
}

/**
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of 
rows like this: (you may want to display this pattern in a fixed font for 
better legibility) 

 
P   A   H   N
A P L S I I G
Y   I   R
 

 And then read line by line: "PAHNAPLSIIGYIR" 

 Write the code that will take a string and make this conversion given a number 
of rows: 

 
string convert(string s, int numRows);
 

 
 Example 1: 

 
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
 

 Example 2: 

 
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
 

 Example 3: 

 
Input: s = "A", numRows = 1
Output: "A"
 

 
 Constraints: 

 
 1 <= s.length <= 1000 
 s consists of English letters (lower-case and upper-case), ',' and '.'. 
 1 <= numRows <= 1000 
 

 Related Topics String 👍 8147 👎 15095

*/
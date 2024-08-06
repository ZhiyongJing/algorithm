package leetcode.question.map_set;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  13. Roman to Integer
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 85.55%
 *@Time  Complexity: O(1)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目解释
 *
 * **题目**: 13. Roman to Integer
 *
 * **描述**: 给定一个罗马数字字符串 `s`，将其转换为对应的整数。罗马数字由七种不同的符号组成，这些符号和它们对应的值如下：
 * - `I` = 1
 * - `V` = 5
 * - `X` = 10
 * - `L` = 50
 * - `C` = 100
 * - `D` = 500
 * - `M` = 1000
 *
 * 罗马数字的规则如下：
 * 1. 同一个符号可以重复使用，直到达到该符号的最大值。例如，`III` = 3。
 * 2. 当较小的符号出现在较大的符号之前，它们的值被从较大的符号的值中减去。例如，`IV` = 4（5 - 1）。
 * 3. 当较小的符号出现在较大的符号之后，它们的值被加到较大的符号的值中。例如，`VI` = 6（5 + 1）。
 *
 * ### 解题思路
 *
 * 1. **初始化**: 使用一个映射 `hm` 将罗马数字符号映射到对应的整数值。同时准备两个变量：`sum` 用于存储最终结果，`i` 用于遍历字符串 `s`。
 *
 * 2. **遍历字符串**:
 *    - 从左到右遍历字符串 `s`。
 *    - 每次检查当前位置及其后的字符是否组成一个合法的双字符罗马数字（如 `IV`, `IX` 等）。如果是，则将这个双字符的值加到 `sum` 中，并跳过两个字符。
 *    - 如果当前位置和下一个字符不能组成双字符罗马数字，则处理单字符罗马数字，并将其对应的值加到 `sum` 中。
 *
 * 3. **处理边界**: 字符串的最后几个字符可能只能作为单字符处理，因此在处理双字符时需要确保索引不超出边界。
 *
 * 4. **返回结果**: 遍历完成后，`sum` 即为罗马数字字符串转换后的整数值。
 *
 * ### 时间复杂度
 *
 * - **O(N)**: 其中 `N` 是字符串的长度。每个字符在最坏情况下会被检查两次（一次作为双字符，一次作为单字符），因此总的时间复杂度是线性的。
 *
 * ### 空间复杂度
 *
 * - **O(1)**: 空间复杂度是常量级的。只使用了一个固定大小的哈希表来存储罗马数字与整数值的映射，其他变量（如 `sum`, `i`）也只占用常量级别的空间。
 */

public class LeetCode_13_RomanToInteger{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int romanToInt(String s) {
            // 定义一个静态的 Map，用于存储罗马数字与其对应的整数值
            Map<String, Integer> hm = new HashMap<>();

            hm.put("I", 1);
            hm.put("V", 5);
            hm.put("X", 10);
            hm.put("L", 50);
            hm.put("C", 100);
            hm.put("D", 500);
            hm.put("M", 1000);
            hm.put("IV", 4);
            hm.put("IX", 9);
            hm.put("XL", 40);
            hm.put("XC", 90);
            hm.put("CD", 400);
            hm.put("CM", 900);
            // 初始化结果和索引变量
            int sum = 0;
            int i = 0;
            // 遍历字符串中的每一个字符
            while (i < s.length()) {
                // 如果当前位置还有足够的字符可以检查双字符组合
                if (i < s.length() - 1) {
                    // 获取当前和下一个字符组成的字符串
                    String doubleSymbol = s.substring(i, i + 2);
                    // 检查这个双字符是否在 Map 中
                    if (hm.containsKey(doubleSymbol)) {
                        // 如果在，则将其对应的值加到结果中
                        sum += hm.get(doubleSymbol);
                        // 索引跳过两个字符
                        i += 2;
                        continue; // 继续下一个循环
                    }
                }
                // 如果双字符组合不在 Map 中，则处理单字符
                String singleSymbol = s.substring(i, i + 1);
                sum += hm.get(singleSymbol); // 加入单字符对应的值
                i += 1; // 索引跳过一个字符
            }
            return sum; // 返回最终计算的整数值
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_13_RomanToInteger().new Solution();
        // 测试样例
        String romanNumeral = "IV"; // 1994
        int result = solution.romanToInt(romanNumeral);
        System.out.println(result); // 输出: 1994
    }
}

/**
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and 
M. 

 
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000 

 For example, 2 is written as II in Roman numeral, just two ones added together.
 12 is written as XII, which is simply X + II. The number 27 is written as 
XXVII, which is XX + V + II. 

 Roman numerals are usually written largest to smallest from left to right. 
However, the numeral for four is not IIII. Instead, the number four is written as 
IV. Because the one is before the five we subtract it making four. The same 
principle applies to the number nine, which is written as IX. There are six instances 
where subtraction is used: 

 
 I can be placed before V (5) and X (10) to make 4 and 9. 
 X can be placed before L (50) and C (100) to make 40 and 90. 
 C can be placed before D (500) and M (1000) to make 400 and 900. 
 

 Given a roman numeral, convert it to an integer. 

 
 Example 1: 

 
Input: s = "III"
Output: 3
Explanation: III = 3.
 

 Example 2: 

 
Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
 

 Example 3: 

 
Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 

 
 Constraints: 

 
 1 <= s.length <= 15 
 s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M'). 
 It is guaranteed that s is a valid roman numeral in the range [1, 3999]. 
 

 Related Topics Hash Table Math String 👍 14350 👎 948

*/
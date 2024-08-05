package leetcode.question.string_list;
/**
 *@Question:  43. Multiply Strings
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 65.08%
 *@Time  Complexity: O(N * M)
 *@Space Complexity: O(N + M)
 */

/**
 * **问题描述**：给定两个非负整数 `num1` 和 `num2`，它们以字符串形式表示。要求返回这两个数字的乘积，并且结果也以字符串形式表示。不能直接将字符串转换为整数来进行计算。
 *
 * **解题思路**：
 *
 * 1. **特殊情况处理**：如果 `num1` 或 `num2` 为 "0"，直接返回 "0"。
 *
 * 2. **反转字符串**：为了方便计算，将两个字符串 `num1` 和 `num2` 反转。这样处理的好处是可以直接从低位开始计算乘积并且保存结果。
 *
 * 3. **初始化结果数组**：使用一个数组 `answer` 来保存最终的乘积结果。数组的长度为 `num1.length() + num2.length()`，因为两个数字相乘的结果最多不会超过这个长度。
 *
 * 4. **逐位相乘**：
 *    - 使用两个循环遍历反转后的字符串 `firstNumber` 和 `secondNumber`。对于 `secondNumber` 中的每一位 `digit2`，与 `firstNumber` 中的每一位 `digit1` 进行相乘。
 *    - 计算当前位的乘积和进位。将乘积的个位数放入结果数组的当前位置，将进位加到结果数组的下一位。
 *    - 累加结果到 `answer` 数组中对应的位置。
 *
 * 5. **处理多余的零**：在乘积计算完成后，可能存在多余的前导零。这时需要删除结果数组中多余的零。
 *
 * 6. **反转结果**：因为最初是从低位开始计算的，所以最后要将结果反转回来。
 *
 * **时间复杂度**：
 * - 乘积的计算需要两个字符串的每个字符相乘，因此时间复杂度为 `O(n * m)`，其中 `n` 是 `num1` 的长度，`m` 是 `num2` 的长度。
 *
 * **空间复杂度**：
 * - 主要取决于结果数组 `answer` 的大小，即 `O(n + m)`，用于存储中间的计算结果和最终的乘积结果。
 *
 * 通过这种方式，我们可以避免将字符串直接转换为整数，从而处理非常大的数字，并且遵循题目要求。
 */
public class LeetCode_43_MultiplyStrings{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String multiply(String num1, String num2) {
            // 如果任一数字是 "0"，返回 "0"
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }

            // 使用 StringBuilder 反转输入字符串以便于从低位到高位处理
            StringBuilder firstNumber = new StringBuilder(num1);
            StringBuilder secondNumber = new StringBuilder(num2);
            firstNumber.reverse();
            secondNumber.reverse();

            // 结果数组，存储中间结果
            int N = firstNumber.length() + secondNumber.length();
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < N; ++i) {
                answer.append(0);
            }

            // 遍历第二个数字的每一位
            for (int place2 = 0; place2 < secondNumber.length(); place2++) {
                int digit2 = secondNumber.charAt(place2) - '0';

                // 将第二个数字的当前位乘以第一个数字的每一位
                for (int place1 = 0; place1 < firstNumber.length(); place1++) {
                    int digit1 = firstNumber.charAt(place1) - '0';

                    // 计算当前位置索引（乘积在结果中的位置）
                    int currentPos = place1 + place2;

                    // 计算当前位的乘积，加上之前的进位
                    int carry = answer.charAt(currentPos) - '0';
                    int multiplication = digit1 * digit2 + carry;

                    // 更新当前位
                    answer.setCharAt(
                            currentPos,
                            (char) ((multiplication % 10) + '0')
                    );

                    // 计算进位，并加到下一位
                    int value =
                            (answer.charAt(currentPos + 1) - '0') + multiplication / 10;
                    answer.setCharAt(currentPos + 1, (char) (value + '0'));
                }
            }

            // 移除末尾的多余零
            if (answer.charAt(answer.length() - 1) == '0') {
                answer.deleteCharAt(answer.length() - 1);
            }

            // 反转结果字符串以得到最终结果
            answer.reverse();
            return answer.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_43_MultiplyStrings().new Solution();
        // 测试用例
        String num1 = "123";
        String num2 = "456";
        String result = solution.multiply(num1, num2);
        System.out.println("Result: " + result); // 输出: "56088"
    }
}

/**
Given two non-negative integers num1 and num2 represented as strings, return 
the product of num1 and num2, also represented as a string. 

 Note: You must not use any built-in BigInteger library or convert the inputs 
to integer directly. 

 
 Example 1: 
 Input: num1 = "2", num2 = "3"
Output: "6"
 
 Example 2: 
 Input: num1 = "123", num2 = "456"
Output: "56088"
 
 
 Constraints: 

 
 1 <= num1.length, num2.length <= 200 
 num1 and num2 consist of digits only. 
 Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 
 

 Related Topics Math String Simulation 👍 7077 👎 3356

*/
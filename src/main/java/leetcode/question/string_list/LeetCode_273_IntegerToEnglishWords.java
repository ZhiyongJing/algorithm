package leetcode.question.string_list;


/**
 *@Question:  273. Integer to English Words
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 84.51%
 *@Time  Complexity: O()
 *@Space Complexity: O()
 */

/**
 * 这个问题的解题思路是将给定的整数按照英文数字的表达规则进行转换。主要思路如下：
 *
 * 1. 定义三个数组，分别用于存储 1 到 19 的英文表示、十位数的英文表示以及“千”、“百万”、“十亿”等单位的英文表示。
 *
 * 2. 创建一个辅助方法 `helper`，用于处理三位数的转换。在这个方法中，我们会根据当前三位数的大小进行不同的处理：
 *    - 如果当前数值为 0，则返回空字符串。
 *    - 如果当前数值小于 20，则直接在 LESS_THAN_20 数组中找到对应的英文表示。
 *    - 如果当前数值小于 100，则将十位数和个位数分别转换为英文表示，并以空格连接。
 *    - 如果当前数值大于等于 100，则将百位数转换为英文表示，然后加上 "Hundred"，再加上后两位数的英文表示。
 *
 * 3. 在 `numberToWords` 方法中，首先处理特殊情况，如果输入的数字为 0，则直接返回 "Zero"。然后，我们从最低位开始，
 * 每次处理三位数字，并加上对应的单位，例如 "Thousand"、"Million"、"Billion" 等。然后将数字除以 1000，
 * 继续处理下一组三位数字，直到处理完整个数字。
 *
 * 4. 最后，将处理完的结果返回，并去除首尾的空格。
 *
 * 时间复杂度分析：
 * - 对于 `numberToWords` 方法，我们需要将数字除以 1000，直到处理完整个数字。因此，时间复杂度为 O(logN)，其中 N 是给定整数的大小。
 * - 在 `helper` 方法中，我们只需要常数时间来处理每个三位数的转换，因此对于每个数字的转换，时间复杂度是 O(1)。
 *
 * 总体来说，时间复杂度为 O(logN)。
 *
 * 空间复杂度分析：
 * - 我们只需要额外的常数空间来存储数字的英文表示以及单位的英文表示，因此空间复杂度是 O(1)。
 *
 * 综上所述，该解法的时间复杂度为 O(logN)，空间复杂度为 O(1)。
 */

public class LeetCode_273_IntegerToEnglishWords{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义数字 1 到 19 的英文表示
        private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        // 定义十位数的英文表示
        private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        // 定义单位“千”，“百万”，“十亿”的英文表示
        private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

        public String numberToWords(int num) {
            if (num == 0) return "Zero"; // 特殊情况处理：输入为0时返回"Zero"

            int i = 0;
            String words = "";

            while (num > 0) {
                if (num % 1000 != 0)
                    // 调用helper方法，处理当前三位数，并添加单位后加到结果中
                    words = helper(num % 1000) + THOUSANDS[i] + " " + words;
                num /= 1000; // 将数字向右移动三位
                i++; // 单位指针后移
            }

            return words.trim(); // 返回结果，去除首尾空格
        }

        // 处理三位数的方法
        private String helper(int num) {
            if (num == 0)
                return ""; // 如果数字为0，返回空字符串
            else if (num < 20)
                return LESS_THAN_20[num] + " "; // 如果数字小于20，直接返回其英文表示
            else if (num < 100)
                return TENS[num / 10] + " " + helper(num % 10); // 如果数字小于100，返回十位数的英文表示加上个位数的英文表示
            else
                return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100); // 如果数字大于等于100，返回百位数的英文表示加上后两位数字的英文表示
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_273_IntegerToEnglishWords().new Solution();
        // TO TEST
        System.out.println(solution.numberToWords(1234567891)); // 测试输出
    }
}

/**
 Convert a non-negative integer num to its English words representation.


 Example 1:


 Input: num = 123
 Output: "One Hundred Twenty Three"


 Example 2:


 Input: num = 12345
 Output: "Twelve Thousand Three Hundred Forty Five"


 Example 3:


 Input: num = 1234567
 Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"



 Constraints:


 0 <= num <= 2³¹ - 1


 Related Topics Math String Recursion 👍 2998 👎 6193

 */
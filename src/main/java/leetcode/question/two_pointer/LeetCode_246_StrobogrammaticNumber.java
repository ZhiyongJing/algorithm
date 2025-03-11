package leetcode.question.two_pointer;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  246. Strobogrammatic Number (对称数)
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.32%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

public class LeetCode_246_StrobogrammaticNumber{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 判断一个字符串表示的数字是否是对称数
         * @param num 输入的数字字符串
         * @return 如果是对称数返回 true，否则返回 false
         */
        public boolean isStrobogrammatic(String num) {

            // 创建一个映射，用于存储可以翻转成对称的数字对
            Map<Character, Character> rotatedDigits = new HashMap<>();
            rotatedDigits.put('0', '0'); // 0 翻转后仍然是 0
            rotatedDigits.put('1', '1'); // 1 翻转后仍然是 1
            rotatedDigits.put('6', '9'); // 6 翻转后变成 9
            rotatedDigits.put('8', '8'); // 8 翻转后仍然是 8
            rotatedDigits.put('9', '6'); // 9 翻转后变成 6

            // 使用双指针，一个从左往右 (left)，一个从右往左 (right)
            for (int left = 0, right = num.length() - 1; left <= right; left++, right--) {
                char leftChar = num.charAt(left); // 获取左指针对应的字符
                char rightChar = num.charAt(right); // 获取右指针对应的字符

                // 如果左字符不在映射中，或者翻转后的字符不等于右字符，则返回 false
                if (!rotatedDigits.containsKey(leftChar) || rotatedDigits.get(leftChar) != rightChar) {
                    return false;
                }
            }

            return true; // 遍历完成后仍然符合对称数规则，则返回 true
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_246_StrobogrammaticNumber().new Solution();

        // 测试样例
        System.out.println(solution.isStrobogrammatic("69")); // true，6 翻转后变 9，9 翻转后变 6
        System.out.println(solution.isStrobogrammatic("88")); // true，8 翻转后仍然是 8
        System.out.println(solution.isStrobogrammatic("962")); // false，2 不能翻转成对称的数字
        System.out.println(solution.isStrobogrammatic("818")); // true，8-1-8 翻转后仍然是 8-1-8
        System.out.println(solution.isStrobogrammatic("123")); // false，1-2-3 不能形成对称数
    }
}

/**
Given a string num which represents an integer, return true if num is a 
strobogrammatic number. 

 A strobogrammatic number is a number that looks the same when rotated 180 
degrees (looked at upside down). 

 
 Example 1: 

 
Input: num = "69"
Output: true
 

 Example 2: 

 
Input: num = "88"
Output: true
 

 Example 3: 

 
Input: num = "962"
Output: false
 

 
 Constraints: 

 
 1 <= num.length <= 50 
 num consists of only digits. 
 num does not contain any leading zeros except for zero itself. 
 

 Related Topics Hash Table Two Pointers String 👍 611 👎 1030

*/
package leetcode.question.string_list;

/**
  *@Question:  415. Add Strings
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 64.76%
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */


public class LeetCode_415_AddStrings{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //based 10
        public String addStrings(String num1, String num2) {
            StringBuilder res = new StringBuilder();

            int carry = 0;
            int p1 = num1.length() - 1;
            int p2 = num2.length() - 1;
            while (p1 >= 0 || p2 >= 0) {
                int x1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
                int x2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
                int value = (x1 + x2 + carry) % 10;
                carry = (x1 + x2 + carry) / 10;
                res.append(value);
                p1--;
                p2--;
            }

            if (carry != 0)
                res.append(carry);

            return res.reverse().toString();
        }

        //based 36
        public String add36String(String num1, String num2){
            int n = num1.length() - 1;
            int m = num2.length() - 1;
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            while(n >= 0 || m >= 0 || carry > 0){
                int a = n >= 0 ? getInt(num1.charAt(n)) : 0;
                int b = m >= 0 ? getInt(num2.charAt(m)) : 0;
                int sum = a + b + carry;
                carry = sum / 36;
                sb.append(toChar(sum % 36));
                n--;
                m--;
            }
            return sb.reverse().toString();
        }

        private int getInt(char ch){
            if(Character.isDigit(ch)){
                return ch - '0';
            }else if(Character.isLetter(ch)){
                ch = Character.toLowerCase(ch);
                return 10 + ch - 'a';
            }
            return 0;
        }

        private char toChar(int num){
            if(num >= 0 && num < 10){
                return (char) (num + '0');
            }else if(num >= 10 && num < 36){
                return (char) (num - 10 + 'a');
            }
            return ' ';
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_415_AddStrings().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given two non-negative integers, num1 and num2 represented as string, return
the sum of num1 and num2 as a string.

 You must solve the problem without using any built-in library for handling
large integers (such as BigInteger). You must also not convert the inputs to
integers directly.


 Example 1:


Input: num1 = "11", num2 = "123"
Output: "134"


 Example 2:


Input: num1 = "456", num2 = "77"
Output: "533"


 Example 3:


Input: num1 = "0", num2 = "0"
Output: "0"



 Constraints:


 1 <= num1.length, num2.length <= 10⁴
 num1 and num2 consist of only digits.
 num1 and num2 don't have any leading zeros except for the zero itself.


 Related Topics Math String Simulation 👍 5002 👎 748

*/
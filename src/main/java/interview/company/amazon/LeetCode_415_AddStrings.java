package interview.company.amazon;

/**
  *@Question:  415. Add Strings     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 0.0%      
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
<p>Given two non-negative integers, <code>num1</code> and <code>num2</code> represented as string, return <em>the sum of</em> <code>num1</code> <em>and</em> <code>num2</code> <em>as a string</em>.</p>

<p>You must solve the problem without using any built-in library for handling large integers (such as <code>BigInteger</code>). You must also not convert the inputs to integers directly.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = "11", num2 = "123"
<strong>Output:</strong> "134"
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = "456", num2 = "77"
<strong>Output:</strong> "533"
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num1 = "0", num2 = "0"
<strong>Output:</strong> "0"
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= num1.length, num2.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>num1</code> and <code>num2</code> consist of only digits.</li> 
 <li><code>num1</code> and <code>num2</code> don't have any leading zeros except for the zero itself.</li> 
</ul>

<div><div>Related Topics</div><div><li>Math</li><li>String</li><li>Simulation</li></div></div><br><div><li>👍 4963</li><li>👎 738</li></div>
*/
package leetcode.question.two_pointer;

/**
  *@Question:  344. Reverse String
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 68.64%
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_344_ReverseString{

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void reverseString(char[] s) {
        if(s == null || s.length == 0) return;
        int left = 0, right = s.length - 1;

        while(left != right && left < s.length / 2){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_344_ReverseString().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Write a function that reverses a string. The input string is given as an array 
of characters s. 

 You must do this by modifying the input array in-place with O(1) extra memory. 


 
 Example 1: 
 Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
 
 Example 2: 
 Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
 
 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 s[i] is a printable ascii character. 
 

 Related Topics Two Pointers String 👍 8893 👎 1188

*/
package leetcode.frequent.based_on_data_structure.stack;

import java.util.Arrays;
import java.util.Stack;
/**
  *@Question:  1209. Remove All Adjacent Duplicates in String II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 62.519999999999996%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

/**
 * 这道题目的主要目标是移除字符串中所有相邻重复项，但要求每个重复项出现的次数不超过 k 次。下面是解题思路的详细讲解：
 *
 * ### 解法1：易于理解的解法
 *
 * 1. 使用 StringBuilder 对输入字符串进行可变操作，同时使用一个数组 count 记录每个字符的重复次数。
 * 2. 遍历字符串，对于每个字符，如果是第一个字符或与前一个字符不相同，将计数置为 1。
 * 3. 如果当前字符与前一个字符相同，更新计数，并检查是否达到了 k 次。若是，则删除相邻的 k 个字符。
 * 4. 时间复杂度：O(N)，其中 N 为字符串长度。每个字符只需要遍历一次。
 * 5. 空间复杂度：O(N)，使用了额外的数组来记录字符的重复次数。
 *
 * ### 解法2：使用栈的解法
 *
 * 1. 使用 StringBuilder 对输入字符串进行可变操作，同时使用一个栈 counts 记录每个字符的重复次数。
 * 2. 遍历字符串，对于每个字符，如果是第一个字符或与前一个字符不相同，将计数置为 1。
 * 3. 如果当前字符与前一个字符相同，更新计数，若达到了 k 次，删除相邻的 k 个字符。
 * 4. 时间复杂度：O(N)，其中 N 为字符串长度。每个字符只需要遍历一次。
 * 5. 空间复杂度：O(N)，使用了额外的栈来记录字符的重复次数。
 *
 * 这两种解法都是遍历一次字符串，按条件进行删除和操作，因此时间复杂度是线性的。空间复杂度主要取决于辅助数据结构的使用，都是线性的。
 *
 * 综上所述，这两种解法都是高效的，根据个人喜好和习惯可以选择使用哪一种。
 */

public class LeetCode_1209_RemoveAllAdjacentDuplicatesInStringIi{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //Solution 1: easy to figure out
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int count[] = new int[sb.length()];
        for (int i = 0; i < sb.length(); ++i) {

            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count[i] = 1;
            } else {
                count[i] = count[i - 1] + 1;
                System.out.println(sb);
                System.out.println((Arrays.toString(count)));
                if (count[i] == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }
        return sb.toString();
    }

    //Solution 2: not easy to think by using stack
    public String removeDuplicates2(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> counts = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    counts.push(incremented);
                }
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_1209_RemoveAllAdjacentDuplicatesInStringIi().new Solution();
        // TO TEST
        //solution.
    }
}
/**
You are given a string s and an integer k, a k duplicate removal consists of 
choosing k adjacent and equal letters from s and removing them, causing the left 
and the right side of the deleted substring to concatenate together. 

 We repeatedly make k duplicate removals on s until we no longer can. 

 Return the final string after all such duplicate removals have been made. It 
is guaranteed that the answer is unique. 

 
 Example 1: 

 
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete. 

 Example 2: 

 
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa" 

 Example 3: 

 
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 

 
 Constraints: 

 
 1 <= s.length <= 10⁵ 
 2 <= k <= 10⁴ 
 s only contains lowercase English letters. 
 

 Related Topics String Stack 👍 5609 👎 108

*/
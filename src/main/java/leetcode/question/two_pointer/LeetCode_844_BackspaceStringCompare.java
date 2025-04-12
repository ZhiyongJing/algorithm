package leetcode.question.two_pointer;
/**
 *@Question:  844. Backspace String Compare
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(M + N), M N are length of S and T
 *@Space Complexity: O(1)
 */
/**
 * ===============================================
 * LeetCode 844. Backspace String Compare
 * ===============================================
 *
 * 【一、题目描述】
 * 给定两个字符串 S 和 T，它们都可能包含小写字母和 '#' 字符。
 * '#' 表示退格操作，意味着会删除前一个字符（如果有）。
 *
 * 请判断处理完退格操作后，S 和 T 是否相等。
 *
 * 示例 1：
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 最终都变成 "ac"
 *
 * 示例 2：
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 最终都变成 "c"
 *
 *
 * 【二、解题思路（双指针逆序扫描 + 跳过退格）】
 * 思路核心：**从后向前遍历两个字符串，跳过被 '#' 删除的字符**，并比较对应有效字符是否相等。
 *
 * 步骤如下：
 * 1）初始化两个指针 i 和 j，分别指向 S 和 T 的最后一个字符；
 * 2）同时维护两个跳过计数器 skipS 和 skipT，表示要跳过的字符数；
 * 3）从后向前扫描：
 *    - 如果当前字符是 '#'，增加对应 skip 计数器并继续向前；
 *    - 如果当前字符不是 '#'，但 skip > 0，则说明当前字符应被跳过，skip--；
 *    - 否则当前字符是有效字符，等待比较；
 * 4）一旦两边都找到有效字符，进行比较：
 *    - 如果字符不相同，直接返回 false；
 *    - 如果一个有字符一个没有，返回 false；
 * 5）循环结束后，说明所有字符都相等，返回 true。
 *
 * 举例解释：
 * 示例：S = "bxj##tw", T = "bxo#j##tw"
 * - 处理后：S -> "tw"，T -> "tw"
 * - i 和 j 从末尾开始向前扫描
 * - 遇到 # 就跳过字符
 * - 最终每一位有效字符都相等，返回 true
 *
 *
 * 【三、时间与空间复杂度】
 * 时间复杂度：O(M + N)
 * - M 为字符串 S 的长度，N 为字符串 T 的长度
 * - 每个字符最多遍历一次（逆向 + 跳过）
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级变量（两个指针 + 两个跳过计数器）
 * - 无需构造新的字符串
 */


public class LeetCode_844_BackspaceStringCompare{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean backspaceCompare(String S, String T) {
            // 从字符串 S 和 T 的尾部开始比较
            int i = S.length() - 1, j = T.length() - 1;
            // 分别记录需要跳过的字符数量（即遇到 # 的数量）
            int skipS = 0, skipT = 0;

            // 当两个字符串还有未处理的字符时
            while (i >= 0 || j >= 0) {
                // 找到 S 中下一个有效字符
                while (i >= 0) {
                    // 遇到 #，跳过下一个字符
                    if (S.charAt(i) == '#') {skipS++; i--;}
                    // 如果需要跳过字符，跳过
                    else if (skipS > 0) {skipS--; i--;}
                    // 当前字符有效，退出循环
                    else break;
                }

                // 找到 T 中下一个有效字符
                while (j >= 0) {
                    if (T.charAt(j) == '#') {skipT++; j--;}
                    else if (skipT > 0) {skipT--; j--;}
                    else break;
                }

                // 如果两个当前字符都有效但不相等，返回 false
                if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                    return false;

                // 如果一个有字符另一个没有，返回 false
                if ((i >= 0) != (j >= 0))
                    return false;

                // 比较下一个字符
                i--; j--;
            }

            // 所有字符都匹配
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_844_BackspaceStringCompare().new Solution();

        // 测试用例 1：正常匹配
        String s1 = "ab#c";
        String t1 = "ad#c";
        // 预期输出：true（最终都变成 "ac"）
        System.out.println("测试用例1结果: " + solution.backspaceCompare(s1, t1));

        // 测试用例 2：多个连续退格
        String s2 = "ab##";
        String t2 = "c#d#";
        // 预期输出：true（都变成空字符串）
        System.out.println("测试用例2结果: " + solution.backspaceCompare(s2, t2));

        // 测试用例 3：不匹配
        String s3 = "a#c";
        String t3 = "b";
        // 预期输出：false（s -> "c"，t -> "b"）
        System.out.println("测试用例3结果: " + solution.backspaceCompare(s3, t3));

        // 测试用例 4：一个为空，一个非空
        String s4 = "a##";
        String t4 = "";
        // 预期输出：true
        System.out.println("测试用例4结果: " + solution.backspaceCompare(s4, t4));

        // 测试用例 5：边界情况
        String s5 = "bxj##tw";
        String t5 = "bxo#j##tw";
        // 预期输出：true
        System.out.println("测试用例5结果: " + solution.backspaceCompare(s5, t5));
    }
}

/**
Given two strings s and t, return true if they are equal when both are typed 
into empty text editors. '#' means a backspace character. 

 Note that after backspacing an empty text, the text will continue empty. 

 
 Example 1: 

 
Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
 

 Example 2: 

 
Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
 

 Example 3: 

 
Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".
 

 
 Constraints: 

 
 1 <= s.length, t.length <= 200 
 s and t only contain lowercase letters and '#' characters. 
 

 
 Follow up: Can you solve it in O(n) time and O(1) space? 

 Related Topics Two Pointers String Stack Simulation 👍 7692 👎 369

*/
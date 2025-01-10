package leetcode.question.two_pointer;

/**
 *@Question:  541. Reverse String II
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 46.99%
 *@Time Complexity: O(N), N is the size of the string `s`
 *@Space Complexity: O(N), space is used for the char array
 */
/**
 * @Question: 541. Reverse String II
 *
 * @Description:
 * 给定一个字符串 `s` 和一个整数 `k`，你需要对字符串进行如下操作：
 * - 每隔 `2k` 个字符，从第一个字符开始，对前 `k` 个字符进行反转。
 * - 如果剩余字符小于 `k`，则将剩余的所有字符反转。
 * - 如果剩余字符介于 `k` 和 `2k` 之间，则反转前 `k` 个字符，其余字符保持原样。
 *
 * 示例：
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 *
 * 输入: s = "abcdefgh", k = 3
 * 输出: "cbadefhg"
 *
 * @Approach:
 * 解题思路：
 * 1. 将字符串 `s` 转换为字符数组 `char[]`，方便在原地操作。
 * 2. 使用一个循环，每次以 `2k` 的步长遍历字符串：
 *    - 计算当前需要反转的子区间 `[start, end]`，其中：
 *      - `start` 是当前段的起点；
 *      - `end` 是 `start + k - 1` 和字符串末尾的较小值。
 *    - 在 `[start, end]` 区间内使用双指针方法反转字符。
 * 3. 最后将修改后的字符数组重新转换为字符串并返回。
 *
 * @TimeComplexity:
 * 时间复杂度：O(N)，其中 N 是字符串的长度。
 * - 遍历整个字符数组只需要一次，时间复杂度为 O(N)。
 * - 双指针反转每个区间最多需要 O(k)，但由于区间是分段的，因此总时间复杂度仍为 O(N)。
 *
 * @SpaceComplexity:
 * 空间复杂度：O(N)。
 * - 使用了额外的字符数组存储输入字符串，空间复杂度为 O(N)。
 */


public class LeetCode_541_ReverseStringIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseStr(String s, int k) {
            // 将字符串转为字符数组，便于操作
            char[] a = s.toCharArray();

            // 遍历字符串，每 2k 个字符为一个单位进行处理
            for (int start = 0; start < a.length; start += 2 * k) {
                // 定义当前需要反转的区间起点 `i` 和终点 `j`
                int i = start, j = Math.min(start + k - 1, a.length - 1);

                // 使用双指针方式反转区间内的字符
                while (i < j) {
                    char tmp = a[i]; // 保存左指针的字符
                    a[i++] = a[j];   // 右指针字符赋值给左指针，左指针右移
                    a[j--] = tmp;    // 保存的字符赋值给右指针，右指针左移
                }
            }

            // 将字符数组重新转为字符串并返回
            return new String(a);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_541_ReverseStringIi().new Solution();

        // 测试样例 1
        String s1 = "abcdefg";
        int k1 = 2;
        System.out.println(solution.reverseStr(s1, k1));
        // 输出: "bacdfeg"

        // 测试样例 2
        String s2 = "abcdefgh";
        int k2 = 3;
        System.out.println(solution.reverseStr(s2, k2));
        // 输出: "cbadefhg"

        // 测试样例 3
        String s3 = "a";
        int k3 = 1;
        System.out.println(solution.reverseStr(s3, k3));
        // 输出: "a"

        // 测试样例 4
        String s4 = "abcdef";
        int k4 = 4;
        System.out.println(solution.reverseStr(s4, k4));
        // 输出: "dcbaef"
    }
}

/**
Given a string s and an integer k, reverse the first k characters for every 2k 
characters counting from the start of the string. 

 If there are fewer than k characters left, reverse all of them. If there are 
less than 2k but greater than or equal to k characters, then reverse the first k 
characters and leave the other as original. 

 
 Example 1: 
 Input: s = "abcdefg", k = 2
Output: "bacdfeg"
 
 Example 2: 
 Input: s = "abcd", k = 2
Output: "bacd"
 
 
 Constraints: 

 
 1 <= s.length <= 10⁴ 
 s consists of only lowercase English letters. 
 1 <= k <= 10⁴ 
 

 Related Topics Two Pointers String 👍 2063 👎 3975

*/
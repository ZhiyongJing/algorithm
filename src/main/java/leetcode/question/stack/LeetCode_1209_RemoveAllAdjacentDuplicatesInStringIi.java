package leetcode.question.stack;

import java.util.Arrays;
import java.util.Stack;
/*
【题目描述】
LeetCode 1209：删除字符串中的所有相邻重复项 II (Remove All Adjacent Duplicates in String II)

给定一个字符串 `s` 和一个整数 `k`，要求反复删除字符串中**连续出现 k 次**的相同字符，直到字符串中不存在这样的连续字符为止，返回最终的字符串。

**示例 1：**
输入：`s = "deeedbbcccbdaa"`, `k = 3`
输出：`"aa"`
解释：
- 首先删除连续出现 3 次的 "eee"，字符串变成 `"ddbbcccbdaa"`。
- 然后删除连续出现 3 次的 "bbb"，字符串变成 `"ddcccbdaa"`。
- 再删除连续出现 3 次的 "ccc"，字符串变成 `"dddaa"`。
- 最后删除连续出现 3 次的 "ddd"，字符串变成 `"aa"`。

**示例 2：**
输入：`s = "pbbcggttciiippooaais"`, `k = 2`
输出：`"ps"`。
*/

/*
【解题思路】
我们可以使用两种主要的解法：
1. **使用计数数组（Solution 1）**
2. **使用栈（Solution 2）**

---

### 解法 1：使用计数数组

思路：
- 使用一个 `StringBuilder` 修改字符串，确保可以灵活地增删字符。
- 使用一个计数数组 `count` 来记录每个字符的连续出现次数。
- 遍历字符串的每个字符：
  - 如果当前字符与前一个字符不同，将当前字符的计数置为 1。
  - 如果当前字符与前一个字符相同，则将计数加 1。
  - 当某个字符的计数达到 `k` 时，从 `StringBuilder` 中删除这段连续的字符，并将索引回退到删除前的位置。

**举例解释：**
输入：`s = "deeedbbcccbdaa"`, `k = 3`
1. 初始字符串为 `"deeedbbcccbdaa"`，计数数组为 `[1, 2, 3, ...]`。
2. 遇到 "eee"，计数达到 3，将 "eee" 删除，字符串变成 `"ddbbcccbdaa"`。
3. 遇到 "bbb"，计数达到 3，将 "bbb" 删除，字符串变成 `"ddcccbdaa"`。
4. 遇到 "ccc"，计数达到 3，将 "ccc" 删除，字符串变成 `"dddaa"`。
5. 遇到 "ddd"，计数达到 3，将 "ddd" 删除，最终字符串为 `"aa"`。

时间复杂度：`O(n)`
空间复杂度：`O(n)`

---

### 解法 2：使用栈

思路：
- 使用 `StringBuilder` 处理字符串。
- 使用栈来记录每个字符连续出现的次数。
- 遍历字符串的每个字符：
  - 如果当前字符与前一个字符不同，将计数 1 压入栈中。
  - 如果当前字符与前一个字符相同，从栈中弹出计数并加 1。
  - 当计数达到 `k` 时，从字符串中删除这段连续的字符，并将索引回退到删除前的位置。

**举例解释：**
输入：`s = "pbbcggttciiippooaais"`, `k = 2`
1. 初始字符串为 `"pbbcggttciiippooaais"`，栈为 `[1, 1, ...]`。
2. 遇到 "bb"，计数达到 2，将 "bb" 删除，字符串变成 `"pcggttciiippooaais"`。
3. 遇到 "gg"，计数达到 2，将 "gg" 删除，字符串变成 `"pcttciiippooaais"`。
4. 遇到 "tt"，计数达到 2，将 "tt" 删除，字符串变成 `"pcciiippooaais"`。
5. 遇到 "ii"，计数达到 2，将 "ii" 删除，字符串变成 `"pccippooaais"`。
6. 遇到 "pp"，计数达到 2，将 "pp" 删除，字符串变成 `"pccooaais"`。
7. 遇到 "oo"，计数达到 2，将 "oo" 删除，字符串变成 `"pccaaais"`。
8. 遇到 "aa"，计数达到 2，将 "aa" 删除，字符串变成 `"pcs"`。

时间复杂度：`O(n)`
空间复杂度：`O(n)`

---

【时间和空间复杂度分析】

### 时间复杂度：
- 两种解法的时间复杂度均为 **O(n)**，其中 `n` 为字符串的长度。因为每个字符最多只会被遍历一次。

### 空间复杂度：
- 两种解法的空间复杂度均为 **O(n)**。
  - 对于解法 1，需要一个计数数组，空间复杂度为 `O(n)`。
  - 对于解法 2，需要一个栈来记录计数，空间复杂度为 `O(n)`。
*/


public class LeetCode_1209_RemoveAllAdjacentDuplicatesInStringIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法1：通过计数数组来移除相邻重复字符
        public String removeDuplicates(String s, int k) {
            // 使用 StringBuilder 来修改字符串
            StringBuilder sb = new StringBuilder(s);
            // 创建一个计数数组，记录每个字符连续出现的次数
            int count[] = new int[sb.length()];

            // 遍历字符串中的每个字符
            for (int i = 0; i < sb.length(); ++i) {
                // 如果是第一个字符或当前字符与前一个字符不同，将计数置为1
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    count[i] = 1;
                } else {
                    // 如果当前字符与前一个字符相同，计数加1
                    count[i] = count[i - 1] + 1;

                    // 调试输出当前字符串和计数数组

                    // 当某个字符连续出现次数达到 k 时，将其从字符串中删除
                    if (count[i] == k) {
                        sb.delete(i - k + 1, i + 1);
                        // 删除字符后，重置索引位置
                        i = i - k;
                    }
                }
                System.out.println(Arrays.toString(count));

            }
            // 返回最终的字符串
            return sb.toString();
        }

        // 解法2：使用栈来移除相邻重复字符
        public String removeDuplicates2(String s, int k) {
            // 使用 StringBuilder 来修改字符串
            StringBuilder sb = new StringBuilder(s);
            // 创建一个栈来记录每个字符连续出现的次数
            Stack<Integer> counts = new Stack<>();

            // 遍历字符串中的每个字符
            for (int i = 0; i < sb.length(); ++i) {
                // 如果是第一个字符或当前字符与前一个字符不同，将计数压栈为1
                System.out.println("current stack: " + counts);
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    counts.push(1);
                } else {
                    // 如果当前字符与前一个字符相同，从栈中弹出计数并加1
                    int incremented = counts.pop() + 1;

                    // 如果计数达到 k，将字符从字符串中删除
                    if (incremented == k) {
                        sb.delete(i - k + 1, i + 1);
                        // 删除字符后，重置索引位置
                        i = i - k;
                    } else {
                        // 如果计数未达到 k，将计数重新压入栈中
                        counts.push(incremented);
                    }
                }
            }
            // 返回最终的字符串
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1209_RemoveAllAdjacentDuplicatesInStringIi().new Solution();

        // 测试样例1
        String s1 = "deeedbbcccbdaa";
        int k1 = 3;
        System.out.println("输入: " + s1 + ", k = " + k1);
        System.out.println("输出: " + solution.removeDuplicates(s1, k1));

        // 测试样例2
        String s2 = "pbbcggttciiippooaais";
        int k2 = 2;
        System.out.println("输入: " + s2 + ", k = " + k2);
        System.out.println("输出: " + solution.removeDuplicates2(s2, k2));
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
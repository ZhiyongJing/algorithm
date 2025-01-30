package leetcode.question.dfs;

import java.util.Stack;
/**
 *@Question:  394. Decode String
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 79.05%
 *@Time  Complexity: O(N * maxK) for both solutions, where maxK is the maximum value of k and n is the length of a given string s
 *@Space Complexity: O(n) for solution1, O(m + n) for solution2
 */
/*
第一步：题目描述
LeetCode 394: Decode String (解码字符串)

给定一个编码后的字符串，按照以下规则进行解码：
- 数字 `k` 表示后续 `[...]` 括号内的子字符串需要重复 `k` 次。
- 可能存在嵌套的 `[...]` 结构，需按照嵌套顺序解析。

示例输入输出：
输入: "3[a]2[bc]"
输出: "aaabcbc"

输入: "3[a2[c]]"
输出: "accaccacc"

输入: "2[abc]3[cd]ef"
输出: "abcabccdcdcdef"

第二步：详细解题思路与举例解释
本题有两种解法：递归 (DFS) 和 栈 (Stack)。

### 解法1：递归 (DFS)
1. 用 `index` 变量记录当前处理的字符位置。
2. 遍历字符串 `s`，处理每个字符：
   - 如果是字母，直接添加到结果字符串。
   - 如果是数字 `k`，说明后面有 `[...]` 结构。
     - 读取完整的 `k`（可能是多位数）。
     - 跳过 `[`，递归调用 `decodeString(s)` 解析 `[...]` 内部内容。
     - 跳过 `]`，将解析出的字符串重复 `k` 次并添加到结果。

示例解析 `3[a2[c]]`：
- 读取 `3`，进入 `[...]` 解析 `a2[c]`
- 读取 `a`，继续解析 `2[c]`
- 读取 `2`，进入 `[...]` 解析 `c`
- `2[c]` 解析为 `cc`，返回 `acc`
- `3[acc]` 解析为 `accaccacc`
- 返回最终结果 `accaccacc`

### 解法2：使用栈 (Stack)
1. 使用两个栈：
   - `countStack` 存储重复次数 `k`。
   - `stringStack` 存储括号外的字符串。
2. 遍历字符串 `s`，处理每个字符：
   - 如果是数字 `k`，计算完整的 `k`。
   - 如果是 `[`，将 `k` 和当前字符串压入栈，并重置当前字符串。
   - 如果是 `]`，弹出栈顶的字符串，并重复 `k` 次追加到结果。
   - 如果是字母，直接追加到当前字符串。
3. 最终栈中的字符拼接成结果。

示例解析 `3[a2[c]]`：
- 遇到 `3`，存入 `countStack`。
- 遇到 `[`，存入 `stringStack`。
- 遇到 `a`，加入 `currentString`。
- 遇到 `2`，存入 `countStack`。
- 遇到 `[`，存入 `stringStack`。
- 遇到 `c`，加入 `currentString`。
- 遇到 `]`，弹出 `c`，`2[c]` → `cc`，更新 `currentString`。
- 遇到 `]`，弹出 `acc`，`3[acc]` → `accaccacc`。
- 最终返回 `accaccacc`。

第三步：时间和空间复杂度
**时间复杂度：O(N * maxK)**
- `N` 是字符串长度，`maxK` 是最大重复次数。
- 递归或栈操作在每个 `[...]` 结构时进行字符串拼接，每个字符最多处理 `maxK` 次。

**空间复杂度：O(N)**
- 递归方法的深度取决于嵌套层数，最坏情况下 `O(N)`。
- 栈方法在最坏情况下也可能存储 `O(N)` 个字符。
*/

public class LeetCode_394_DecodeString{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解决方案1：递归解法
        int index = 0; // 记录当前遍历的索引位置

        String decodeString1(String s) {
            StringBuilder result = new StringBuilder(); // 存储解码后的字符串

            // 遍历字符串，直到遇到右括号 ']' 结束
            while (index < s.length() && s.charAt(index) != ']') {
                // 如果当前字符不是数字，则直接添加到结果中
                if (!Character.isDigit(s.charAt(index))) {
                    result.append(s.charAt(index++));
                } else {
                    int k = 0;
                    // 计算重复次数 k
                    while (index < s.length() && Character.isDigit(s.charAt(index))) {
                        k = k * 10 + s.charAt(index++) - '0';
                    }

                    // 跳过左括号 '['
                    index++;

                    // 递归解析括号内的子字符串
                    String decodedString = decodeString(s);

                    // 跳过右括号 ']'
                    index++;

                    // 将 decodedString 复制 k 次并追加到结果字符串
                    System.out.println(decodedString);
                    while (k-- > 0) {
                        result.append(decodedString);
                    }
                }
            }
            return new String(result);
        }

        // 解决方案2：使用栈解法 更高效
        String decodeString(String s) {
            Stack<Integer> countStack = new Stack<>(); // 存储重复次数的栈
            Stack<StringBuilder> stringStack = new Stack<>(); // 存储字符串的栈
            StringBuilder currentString = new StringBuilder(); // 当前正在处理的字符串
            int k = 0; // 记录当前的数字 k

            for (char ch : s.toCharArray()) {
                if (Character.isDigit(ch)) {
                    // 计算 k，可能是多位数
                    k = k * 10 + ch - '0';
                } else if (ch == '[') {
                    // 遇到 '[' 时，将 k 和当前字符串存入栈中，并重置当前字符串
                    countStack.push(k);
                    stringStack.push(currentString);
                    currentString = new StringBuilder();
                    k = 0;
                } else if (ch == ']') {
                    // 遇到 ']' 时，出栈构建最终字符串
                    StringBuilder decodedString = stringStack.pop();
                    int currentK = countStack.pop(); // 获取重复次数
                    // 追加 currentString k 次
                    for (; currentK > 0; currentK--) {
                        decodedString.append(currentString);
                    }
                    currentString = decodedString; // 更新当前字符串
                } else {
                    // 普通字符直接追加到当前字符串
                    currentString.append(ch);
                }
            }
            return currentString.toString();
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_394_DecodeString().new Solution();

        // 测试示例
        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";

        System.out.println(solution.decodeString(s1)); // 输出: "aaabcbc"
        System.out.println(solution.decodeString(s2)); // 输出: "accaccacc"
        System.out.println(solution.decodeString(s3)); // 输出: "abcabccdcdcdef"
    }
}


/**
Given an encoded string, return its decoded string. 

 The encoding rule is: k[encoded_string], where the encoded_string inside the 
square brackets is being repeated exactly k times. Note that k is guaranteed to 
be a positive integer. 

 You may assume that the input string is always valid; there are no extra white 
spaces, square brackets are well-formed, etc. Furthermore, you may assume that 
the original data does not contain any digits and that digits are only for those 
repeat numbers, k. For example, there will not be input like 3a or 2[4]. 

 The test cases are generated so that the length of the output will never 
exceed 10⁵. 

 
 Example 1: 

 
Input: s = "3[a]2[bc]"
Output: "aaabcbc"
 

 Example 2: 

 
Input: s = "3[a2[c]]"
Output: "accaccacc"
 

 Example 3: 

 
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 

 
 Constraints: 

 
 1 <= s.length <= 30 
 s consists of lowercase English letters, digits, and square brackets '[]'. 
 s is guaranteed to be a valid input. 
 All the integers in s are in the range [1, 300]. 
 

 Related Topics String Stack Recursion 👍 12081 👎 553

*/

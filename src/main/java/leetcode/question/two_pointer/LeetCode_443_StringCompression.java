package leetcode.question.two_pointer;
/**
 *@Question:  443. String Compression
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 74.59%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * ### 题目描述
 *
 * **问题：** 字符串压缩
 *
 * **题目：** 给定一个字符数组 `chars`，其中每个字符都是英文字母。你需要将这个字符数组进行压缩，使得相同字符的连续序列用字符和其出现次数表示。压缩后字符数组的长度应该是最小的，并且原始字符数组 `chars` 的前 `res` 个字符包含压缩后的结果。你需要返回压缩后的长度 `res`。
 *
 * **示例：**
 *
 * - 输入：`["a","a","b","b","c","c","c"]`
 * - 输出：`6`
 * - 解释：压缩后的字符数组是 `["a","2","b","2","c","3"]`，其长度为 6。
 *
 * - 输入：`["a","b","c"]`
 * - 输出：`3`
 * - 解释：没有连续字符的重复，因此压缩后的字符数组是 `["a","b","c"]`，其长度为 3。
 *
 * ### 解题思路
 *
 * 1. **初始化变量**：
 *    - `i` 指针用于遍历原始字符数组 `chars`。
 *    - `res` 记录压缩后的字符数组的长度。
 *
 * 2. **处理每个字符组**：
 *    - 使用一个 `while` 循环遍历字符数组，检查每个字符组的长度。
 *    - 内层 `while` 循环用于计算当前字符组的长度 `groupLength`，即当前字符在连续位置的出现次数。
 *
 * 3. **填充压缩后的字符数组**：
 *    - 将当前字符（组）添加到压缩后的字符数组中。
 *    - 如果 `groupLength` 大于 1，将 `groupLength` 转换成字符，并将这些字符添加到压缩后的数组中。
 *
 * 4. **更新指针**：
 *    - 处理完当前字符组后，将指针 `i` 移动到下一个字符组的起始位置。
 *
 * 5. **返回结果**：
 *    - 遍历完成后，返回压缩后的字符数组的长度 `res`。
 *
 * ### 时间复杂度
 *
 * - **时间复杂度：** `O(N)`
 *   其中 `N` 是字符数组的长度。因为每个字符最多被访问两次（一次用于计算其长度，另一次用于将其添加到结果数组），所以时间复杂度是线性的。
 *
 * ### 空间复杂度
 *
 * - **空间复杂度：** `O(1)`
 *   只有少量额外的变量（`i`、`res`、`groupLength`），没有使用额外的数据结构来存储数据。压缩操作是就地进行的，因此空间复杂度是常数级的。
 */
public class LeetCode_443_StringCompression{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int compress(char[] chars) {
            int i = 0;  // 当前指针，指向正在处理的字符
            int res = 0;  // 用于记录压缩后的字符数组长度
            while (i < chars.length) {
                int groupLength = 1;  // 记录当前字符组的长度
                // 统计当前字符组的长度
                while (i + groupLength < chars.length && chars[i + groupLength] == chars[i]) {
                    groupLength++;
                }
                // 将当前字符写入压缩后的数组中
                chars[res++] = chars[i];
                // 如果字符组长度大于1，则需要将长度也写入数组中
                if (groupLength > 1) {
                    for (char c : Integer.toString(groupLength).toCharArray()) {
                        chars[res++] = c;
                    }
                }
                // 移动到下一个字符组的开始位置
                i += groupLength;
            }
            // 返回压缩后的数组长度
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_443_StringCompression().new Solution();

        // 测试样例1
        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int length1 = solution.compress(chars1);
        System.out.println("Compressed length: " + length1);
        System.out.print("Compressed characters: ");
        for (int i = 0; i < length1; i++) {
            System.out.print(chars1[i]);
        }
        System.out.println();

        // 测试样例2
        char[] chars2 = {'a', 'b', 'c'};
        int length2 = solution.compress(chars2);
        System.out.println("Compressed length: " + length2);
        System.out.print("Compressed characters: ");
        for (int i = 0; i < length2; i++) {
            System.out.print(chars2[i]);
        }
        System.out.println();

        // 测试样例3
        char[] chars3 = {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'};
        int length3 = solution.compress(chars3);
        System.out.println("Compressed length: " + length3);
        System.out.print("Compressed characters: ");
        for (int i = 0; i < length3; i++) {
            System.out.print(chars3[i]);
        }
        System.out.println();
    }
}

/**
Given an array of characters chars, compress it using the following algorithm: 

 Begin with an empty string s. For each group of consecutive repeating 
characters in chars: 

 
 If the group's length is 1, append the character to s. 
 Otherwise, append the character followed by the group's length. 
 

 The compressed string s should not be returned separately, but instead, be 
stored in the input character array chars. Note that group lengths that are 10 or 
longer will be split into multiple characters in chars. 

 After you are done modifying the input array, return the new length of the 
array. 

 You must write an algorithm that uses only constant extra space. 

 
 Example 1: 

 
Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a",
"2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 

 Example 2: 

 
Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a 
single character.
 

 Example 3: 

 
Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a",
"b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12". 

 
 Constraints: 

 
 1 <= chars.length <= 2000 
 chars[i] is a lowercase English letter, uppercase English letter, digit, or 
symbol. 
 

 Related Topics Two Pointers String 👍 5066 👎 7936

*/
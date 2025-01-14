package leetcode.question.two_pointer;
/**
 *@Question:  443. String Compression
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 74.59%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * ==============================
 * 题目描述：LeetCode 443 - String Compression
 * ==============================
 * 给定一个字符数组 `chars`，将其进行原地压缩。
 * 要求按照以下规则进行压缩：
 * - 使用字符出现的频率进行压缩，例如数组 `['a', 'a', 'b', 'b', 'c', 'c', 'c']` 会压缩成 `['a', '2', 'b', '2', 'c', '3']`。
 * - 如果字符的频率是 1，则不需要写频率。例如数组 `['a', 'b', 'c']` 会保持为 `['a', 'b', 'c']`。
 * - 压缩后的数组必须原地修改，并返回压缩后数组的新长度。
 *
 * **输入/输出示例：**
 * 输入：`chars = ['a', 'a', 'b', 'b', 'c', 'c', 'c']`
 * 输出：6，表示数组被压缩成 `['a', '2', 'b', '2', 'c', '3']`。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * **核心思路：双指针遍历**
 * - 使用一个指针 `i` 遍历字符数组，用另一个指针 `res` 来记录压缩后的数组长度。
 * - 每次遍历一个字符组（连续相同的字符），统计字符组的长度，并将字符和长度写入压缩后的数组中。
 *
 * ------------------------------
 * **步骤1：遍历字符数组**
 * - 初始化两个指针：`i = 0`（遍历字符的指针），`res = 0`（记录压缩后的数组长度）。
 * - 遍历每个字符组，并统计当前字符的出现次数。
 *
 * **步骤2：写入字符和频率**
 * - 将当前字符写入压缩后的数组中。
 * - 如果字符出现次数大于 1，将出现次数转换为字符串，并逐个字符写入数组中。
 *
 * **步骤3：移动到下一个字符组**
 * - 遍历完成当前字符组后，移动指针 `i` 到下一个不同的字符位置，继续压缩。
 *
 * ------------------------------
 * **举例解释：**
 *
 * **示例1：**
 * 输入：`chars = ['a', 'a', 'b', 'b', 'c', 'c', 'c']`
 *
 * - 初始状态：`i = 0`，`res = 0`
 * - 第一次循环：
 *   - 当前字符为 'a'，统计长度为 2。
 *   - 写入 'a' 和 '2' 到压缩后的数组。
 *   - 更新数组：`['a', '2', 'b', 'b', 'c', 'c', 'c']`，`res = 2`。
 *
 * - 第二次循环：
 *   - 当前字符为 'b'，统计长度为 2。
 *   - 写入 'b' 和 '2' 到压缩后的数组。
 *   - 更新数组：`['a', '2', 'b', '2', 'c', 'c', 'c']`，`res = 4`。
 *
 * - 第三次循环：
 *   - 当前字符为 'c'，统计长度为 3。
 *   - 写入 'c' 和 '3' 到压缩后的数组。
 *   - 更新数组：`['a', '2', 'b', '2', 'c', '3']`，`res = 6`。
 *
 * 输出：6
 *
 * ------------------------------
 * **示例2：**
 * 输入：`chars = ['a', 'b', 'c']`
 *
 * - 初始状态：`i = 0`，`res = 0`
 * - 第一次循环：
 *   - 当前字符为 'a'，统计长度为 1。
 *   - 写入 'a' 到压缩后的数组。
 *   - 更新数组：`['a', 'b', 'c']`，`res = 1`。
 *
 * - 第二次循环：
 *   - 当前字符为 'b'，统计长度为 1。
 *   - 写入 'b' 到压缩后的数组。
 *   - 更新数组：`['a', 'b', 'c']`，`res = 2`。
 *
 * - 第三次循环：
 *   - 当前字符为 'c'，统计长度为 1。
 *   - 写入 'c' 到压缩后的数组。
 *   - 更新数组：`['a', 'b', 'c']`，`res = 3`。
 *
 * 输出：3
 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：O(N)**
 * - 遍历字符数组的每个字符一次，统计每个字符组的长度，并进行写入操作。
 * - 其中 N 是字符数组的长度。
 *
 * **空间复杂度：O(1)**
 * - 原地修改数组，不使用额外的数据结构。
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
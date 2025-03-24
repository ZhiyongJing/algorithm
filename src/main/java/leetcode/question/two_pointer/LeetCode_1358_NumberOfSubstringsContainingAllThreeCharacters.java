package leetcode.question.two_pointer;
/**
 *@Question:  1358. Number of Substrings Containing All Three Characters
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 54.730000000000004%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * 第一部分：题目描述
 * ----------------------------------------------
 * 1358. Number of Substrings Containing All Three Characters
 * （包含所有三种字符的子字符串数量）
 *
 * 题目要求：
 * 给定一个仅包含小写字母 'a'、'b' 和 'c' 的字符串 s，计算满足以下条件的子字符串的个数：
 * - 子字符串中至少包含一个 'a'，一个 'b' 和一个 'c'。
 *
 * 示例：
 * 输入：s = "abc"
 * 输出：1
 * 解释：字符串本身 "abc" 就是满足条件的子字符串。
 *
 * 输入：s = "aaacb"
 * 输出：3
 * 解释：满足条件的子字符串是 "aacb", "acb", "cb"。
 *
 * 输入：s = "abcabc"
 * 输出：10
 * 解释：所有满足条件的子字符串包括：
 * ["abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc", "abc"]
 */


/**
 * 第二部分：详细解题思路（包含超级详细的步骤和举例）
 * ----------------------------------------------
 * 1. **使用滑动窗口（双指针）的方法**
 *    - 维护两个指针 left 和 right 作为窗口的左右边界。
 *    - right 指针用于扩展窗口，使窗口包含 'a'、'b'、'c' 三个字符。
 *    - left 指针用于收缩窗口，使窗口尽可能小但仍满足条件。
 *
 * 2. **维护字符频率数组**
 *    - 使用一个大小为 3 的数组 freq[] 记录 'a'、'b'、'c' 在窗口中的出现次数。
 *    - 例如：
 *      - 对于 "abcabc"，窗口滑动时 freq[] 可能是：
 *      - 初始: [0, 0, 0]
 *      - 右指针移动到 'a': [1, 0, 0]
 *      - 右指针移动到 'b': [1, 1, 0]
 *      - 右指针移动到 'c': [1, 1, 1]（窗口满足条件）
 *
 * 3. **当窗口包含 'a'、'b' 和 'c' 后，计算有效子字符串数量**
 *    - 由于字符串是从 left 开始的，并且 right 之后的所有子字符串也满足条件：
 *      - 例如：s = "abcabc"，当窗口 [0,2] 为 "abc" 满足条件时：
 *        - 所有从 left=0 开始的子字符串 ["abc", "abca", "abcab", "abcabc"] 都满足条件
 *        - 所以加上 len - right 到 total
 *
 * 4. **收缩窗口**
 *    - 由于窗口当前已经满足条件，我们尝试缩小窗口：
 *      - 移动 left 指针，同时减少 freq[left] 的计数。
 *      - 继续检查窗口是否仍然满足包含 'a'、'b'、'c'。
 *      - 如果仍然满足，则继续添加子字符串数量到 total。
 *
 * 5. **重复步骤，直到 right 遍历完整个字符串**
 *
 * ----------------------------------------------
 * 详细示例：
 *
 * 假设输入 s = "abcabc"：
 * 1. 初始：
 *    left = 0, right = 0, freq = [0, 0, 0], total = 0
 * 2. right = 0, 读入 'a'：
 *    freq = [1, 0, 0] （窗口不满足条件）
 * 3. right = 1, 读入 'b'：
 *    freq = [1, 1, 0] （窗口不满足条件）
 * 4. right = 2, 读入 'c'：
 *    freq = [1, 1, 1] （窗口满足条件）
 *    - total += 6 - 2 = 4 (所有从 left=0 开始的有效子字符串)
 * 5. left = 1, 移除 'a'：
 *    freq = [0, 1, 1] （窗口不再满足条件）
 * 6. right = 3, 读入 'a'：
 *    freq = [1, 1, 1] （窗口满足条件）
 *    - total += 6 - 3 = 3
 * 7. left = 2, 移除 'b'：
 *    freq = [1, 0, 1] （窗口不再满足条件）
 * 8. right = 4, 读入 'b'：
 *    freq = [1, 1, 1] （窗口满足条件）
 *    - total += 6 - 4 = 2
 * 9. left = 3, 移除 'c'：
 *    freq = [1, 1, 0] （窗口不再满足条件）
 * 10. right = 5, 读入 'c'：
 *    freq = [1, 1, 1] （窗口满足条件）
 *    - total += 6 - 5 = 1
 *
 * 最终 total = 10，输出 10。
 */


/**
 * 第三部分：时间和空间复杂度分析
 * ----------------------------------------------
 * **时间复杂度：O(N)**
 * - 由于 right 指针遍历整个字符串最多 N 次，left 只在必要时移动，总体最多移动 N 次。
 * - 每个字符最多处理 2 次（一次进入窗口，一次移出窗口）。
 * - 因此，总时间复杂度是 O(N)。
 *
 * **空间复杂度：O(1)**
 * - 仅使用了一个大小为 3 的数组 freq[] 记录字符频率。
 * - 额外使用了几个整型变量 (left, right, total)。
 * - 不使用额外的数据结构，空间复杂度为 O(1)。
 *
 * **总结：**
 * - 该算法使用双指针滑动窗口技巧，在 O(N) 的时间内找到所有满足条件的子字符串，且不需要额外空间。
 * - 是一个高效的解决方案，适用于大规模输入数据。
 */

public class LeetCode_1358_NumberOfSubstringsContainingAllThreeCharacters{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int numberOfSubstrings(String s) {
            int len = s.length(); // 获取字符串的长度
            int left = 0, right = 0; // 双指针，left 作为窗口左端，right 作为窗口右端
            int[] freq = new int[3]; // 记录字符 'a'、'b'、'c' 的出现次数
            int total = 0; // 记录满足条件的子字符串数量

            while (right < len) { // 遍历字符串，right 作为滑动窗口的右端
                char curr = s.charAt(right); // 获取当前字符
                freq[curr - 'a']++; // 递增该字符的出现次数

                // 当窗口包含了至少一个 'a'、'b'、'c' 时，开始收缩窗口
                while (hasAllChars(freq)) {
                    total += len - right; // 由于右端 right 不变，所有以 left 开头的子字符串都满足条件

                    char leftChar = s.charAt(left); // 获取窗口左端的字符
                    freq[leftChar - 'a']--; // 移除左端字符，缩小窗口
                    left++; // 移动左指针
                }

                right++; // 继续扩展右端
            }

            return total; // 返回最终满足条件的子字符串数量
        }

        private boolean hasAllChars(int[] freq) {
            // 判断是否包含至少一个 'a'、'b'、'c'
            return freq[0] > 0 && freq[1] > 0 && freq[2] > 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1358_NumberOfSubstringsContainingAllThreeCharacters().new Solution();

        // 测试用例 1: "abc" -> 3 种子字符串 ("abc")
        System.out.println(solution.numberOfSubstrings("abc")); // 预期输出: 1

        // 测试用例 2: "aaacb" -> 3 种子字符串 ("aacb", "acb", "cb")
        System.out.println(solution.numberOfSubstrings("aaacb")); // 预期输出: 3

        // 测试用例 3: "abcabc" -> 多个满足条件的子字符串
        System.out.println(solution.numberOfSubstrings("abcabc")); // 预期输出: 10

        // 测试用例 4: "ababbbc" -> 只有 "abbbc" 满足
        System.out.println(solution.numberOfSubstrings("ababbbc")); // 预期输出: 3

        // 测试用例 5: "aaaa" -> 不包含 'b' 和 'c'，返回 0
        System.out.println(solution.numberOfSubstrings("aaaa")); // 预期输出: 0
    }
}

/**
Given a string s consisting only of characters a, b and c. 

 Return the number of substrings containing at least one occurrence of all 
these characters a, b and c. 

 
 Example 1: 

 
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the 
characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab",
 "cabc" and "abc" (again). 
 

 Example 2: 

 
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the 
characters a, b and c are "aaacb", "aacb" and "acb". 
 

 Example 3: 

 
Input: s = "abc"
Output: 1
 

 
 Constraints: 

 
 3 <= s.length <= 5 x 10^4 
 s only consists of a, b or c characters. 
 

 Related Topics Hash Table String Sliding Window 👍 3998 👎 70

*/
package leetcode.question.string_list;

/**
 *@Question:  2423. Remove Letter To Equalize Frequency
 *@Difficulty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 44.61%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(26) - > O(1)
 */
/*
第一步：题目描述
LeetCode 2423: Remove Letter To Equalize Frequency (删除一个字母使频率相等)

给定一个字符串 `word`，我们可以删除其中的一个字母，使得剩下的所有字母的出现频率相等。
- 如果存在这样一个删除方案，则返回 `true`，否则返回 `false`。

示例输入输出：
输入: "aabb"
输出: `true` (删除 'b' 使得 'a' 频率相等)

输入: "abc"
输出: `true` (删除 'a' 使得 'b' 和 'c' 频率相等)

输入: "aaabbb"
输出: `false` (无法通过删除一个字符使得剩余字符频率相等)

第二步：详细解题思路与举例解释
本题的核心思想是枚举删除每个字符后的情况，并检查剩余字符是否满足频率相等。

### 解法思路
1. **统计每个字符的出现频率**
   - 使用长度为 `26` 的数组 `count[]` 记录每个字符的出现次数。
   - 遍历 `word`，对 `count[word[i] - 'a']` 进行递增。

2. **尝试删除每个字符并检查剩余字符的频率**
   - 遍历字符串 `word`，对于每个字符 `word[i]`：
     - 先将 `count[word[i] - 'a']` 减少 `1`，相当于删除该字符。
     - 通过 `equalCount(count)` 方法检查剩余字符是否具有相同频率。
     - 如果满足条件，返回 `true`。
     - 恢复 `count[word[i] - 'a']`，继续检查下一个字符。

3. **检查所有非零频率是否相等 (`equalCount` 方法)**
   - 遍历 `count[]` 数组，跳过 `0`（未出现的字符）。
   - 记录第一个非零频率 `c`，之后检查所有非零频率是否都等于 `c`。
   - 如果有不同的频率，返回 `false`，否则返回 `true`。

示例解析：
#### 示例1: `word = "aabb"`
- 统计字符频率: `count = [2, 2, 0, ..., 0]`
- 删除 'a' (变为 `[1, 2, 0, ..., 0]`)，不同频率 -> 不满足
- 删除 'b' (变为 `[2, 1, 0, ..., 0]`)，不同频率 -> 不满足
- 删除一个 'b' (变为 `[2, 1, 0, ..., 0]`)，频率变为 `[2,1]` -> 满足
- 返回 `true`

#### 示例2: `word = "abc"`
- 统计字符频率: `count = [1, 1, 1, 0, ..., 0]`
- 删除 'a' (变为 `[0, 1, 1, 0, ..., 0]`)，频率 `[1,1]` -> 满足
- 返回 `true`

#### 示例3: `word = "aaabbb"`
- 统计字符频率: `count = [3, 3, 0, ..., 0]`
- 删除 'a' 或 'b' 后，仍然有 `[2,3]` 或 `[3,2]` -> 不满足
- 返回 `false`

第三步：时间和空间复杂度
**时间复杂度：O(N)**
- 统计字符频率的过程需要 `O(N)`。
- 尝试删除每个字符后检查频率的过程也需要 `O(N)`，共 `O(N^2)`。
- 但最多只涉及 `26` 个字符，因此时间复杂度可以简化为 `O(N)`。

**空间复杂度：O(1)**
- `count[]` 数组大小固定为 `26`，因此空间复杂度为 `O(1)`。
*/


public class LeetCode_2423_RemoveLetterToEqualizeFrequency{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean equalFrequency(String word) {
            int len = word.length(); // 获取字符串的长度
            int[] count = new int[26]; // 记录每个字母的出现次数，数组索引对应字母 'a' - 'z'

            // 遍历字符串，统计每个字母的出现次数
            for (int i = 0; i < len; ++i) {
                char c = word.charAt(i); // 获取当前字符
                count[c - 'a']++; // 将字符对应的计数加一
            }

            // 遍历字符串，尝试删除每个字符后检查频率是否相等
            for (int i = 0; i < len; ++i) {
                char c = word.charAt(i); // 获取当前字符
                count[c - 'a']--; // 先减少该字符的出现次数

                if (equalCount(count)) { // 检查删除该字符后是否所有剩余字符的频率相等
                    return true; // 如果相等，返回 true
                }

                count[c - 'a']++; // 恢复该字符的出现次数，进行下一个字符的尝试
            }

            return false; // 遍历所有字符后，若都无法满足条件，则返回 false
        }

        public boolean equalCount(int[] count) {
            int c = 0; // 记录字符频率

            // 遍历频率数组，检查是否所有非零的字符频率相等
            for (int i : count) {
                if (i == 0) { // 跳过未出现的字符
                    continue;
                } else if (c == 0) { // 记录第一个非零频率的值
                    c = i;
                } else if (c == i) { // 如果当前频率等于记录的频率，继续遍历
                    continue;
                } else { // 发现不同的频率，返回 false
                    return false;
                }
            }

            return true; // 所有非零频率都相等，返回 true
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_2423_RemoveLetterToEqualizeFrequency().new Solution();

        // 测试示例
        System.out.println(solution.equalFrequency("aabb")); // 输出: true
        System.out.println(solution.equalFrequency("abc")); // 输出: true
        System.out.println(solution.equalFrequency("aaabbb")); // 输出: false
        System.out.println(solution.equalFrequency("abcc")); // 输出: true
    }
}

/**
You are given a 0-indexed string word, consisting of lowercase English letters. 
You need to select one index and remove the letter at that index from word so 
that the frequency of every letter present in word is equal. 

 Return true if it is possible to remove one letter so that the frequency of 
all letters in word are equal, and false otherwise. 

 Note: 

 
 The frequency of a letter x is the number of times it occurs in the string. 
 You must remove exactly one letter and cannot choose to do nothing. 
 

 
 Example 1: 

 
Input: word = "abcc"
Output: true
Explanation: Select index 3 and delete it: word becomes "abc" and each 
character has a frequency of 1.
 

 Example 2: 

 
Input: word = "aazz"
Output: false
Explanation: We must delete a character, so either the frequency of "a" is 1 
and the frequency of "z" is 2, or vice versa. It is impossible to make all present 
letters have equal frequency.
 

 
 Constraints: 

 
 2 <= word.length <= 100 
 word consists of lowercase English letters only. 
 

 Related Topics Hash Table String Counting 👍 714 👎 1279

*/
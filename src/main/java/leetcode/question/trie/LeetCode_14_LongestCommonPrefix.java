package leetcode.question.trie;

/**
  *@Question:  14. Longest Common Prefix
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 91.84%
  *@Time  Complexity: O(S) is the sum of all characters in all string.
  *@Space Complexity: O(1)
 */
/*
第一步：题目描述
LeetCode 14: Longest Common Prefix (最长公共前缀)

给定一个字符串数组 `strs`，找出其中所有字符串的最长公共前缀。
- 如果不存在公共前缀，返回空字符串 `""`。
- 输入保证所有字符串仅包含小写英文字母 `a-z`。

示例输入输出：
输入: ["flower", "flow", "flight"]
输出: "fl"

输入: ["dog", "racecar", "car"]
输出: "" (没有公共前缀)

第二步：详细解题思路与举例解释
本题的核心思想是：
1. 假设 `prefix` 为 `strs[0]` (第一个字符串)。
2. 遍历数组中的每个字符串 `strs[i]`，不断缩小 `prefix` 直到它是 `strs[i]` 的前缀。
3. 如果 `prefix` 变为空，则说明没有公共前缀，直接返回 `""`。

### 具体步骤：
1. **初始化 `prefix`**:
   - 设 `prefix = strs[0]`，表示初始最长公共前缀。

2. **遍历字符串数组**:
   - 对于每个 `strs[i]`，检查 `prefix` 是否是其前缀。
   - 使用 `indexOf(prefix) != 0` 来判断 `prefix` 不是 `strs[i]` 的前缀。

3. **缩短 `prefix`**:
   - 通过 `prefix = prefix.substring(0, prefix.length() - 1)` 逐步缩短 `prefix`。
   - 如果 `prefix` 变为空，说明没有公共前缀，返回 `""`。

### 示例解析：
#### 示例1: `strs = ["flower", "flow", "flight"]`
- 初始 `prefix = "flower"`
- `flow.indexOf("flower") != 0`，缩短 `prefix` 为 `"flowe"`，继续缩短到 `"flow"`。
- `flight.indexOf("flow") != 0`，缩短 `prefix` 为 `"flo"`，然后 `"fl"`。
- `flight.indexOf("fl") == 0`，最终 `prefix = "fl"`。
- 返回 `"fl"`。

#### 示例2: `strs = ["dog", "racecar", "car"]`
- 初始 `prefix = "dog"`
- `racecar.indexOf("dog") != 0`，缩短 `prefix` 为 `"do"`，再缩短到 `"d"`，最后变为空。
- 直接返回 `""`。

第三步：时间和空间复杂度
**时间复杂度：O(S)**
- 其中 `S` 是所有字符串中字符的总数。
- 在最坏情况下，需要遍历所有字符并缩短 `prefix`。

**空间复杂度：O(1)**
- 只使用了常数级别的额外空间 `prefix`，因此空间复杂度为 `O(1)`。
*/


public class LeetCode_14_LongestCommonPrefix{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //Solution1: easy to understand. 逐个对比字符串，找到最长公共前缀
        public String longestCommonPrefix(String[] strs) {
            // 如果字符串数组为空，直接返回空字符串
            if (strs.length == 0) return "";

            // 设定初始前缀为第一个字符串
            String prefix = strs[0];

            // 遍历字符串数组的其余部分
            for (int i = 1; i < strs.length; i++) {
                // 当 prefix 不是当前字符串的前缀时，缩短 prefix
                while (strs[i].indexOf(prefix) != 0) {
                    // 逐步去掉 prefix 的最后一个字符
                    prefix = prefix.substring(0, prefix.length() - 1);

                    // 如果 prefix 为空，说明没有公共前缀，直接返回空字符串
                    if (prefix.isEmpty()) return "";
                }
            }

            // 返回最长公共前缀
            return prefix;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_14_LongestCommonPrefix().new Solution();

        // 测试示例
        String[] strs1 = {"flower", "flow", "flight"};
        String[] strs2 = {"dog", "racecar", "car"};
        String[] strs3 = {"interspecies", "interstellar", "interstate"};

        // 输出最长公共前缀
        System.out.println(solution.longestCommonPrefix(strs1)); // 输出: "fl"
        System.out.println(solution.longestCommonPrefix(strs2)); // 输出: ""
        System.out.println(solution.longestCommonPrefix(strs3)); // 输出: "inters"
    }
}

/**
Write a function to find the longest common prefix string amongst an array of 
strings. 

 If there is no common prefix, return an empty string "". 

 
 Example 1: 

 
Input: strs = ["flower","flow","flight"]
Output: "fl"
 

 Example 2: 

 
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 

 
 Constraints: 

 
 1 <= strs.length <= 200 
 0 <= strs[i].length <= 200 
 strs[i] consists of only lowercase English letters if it is non-empty. 
 

 Related Topics String Trie 👍 18597 👎 4662

*/
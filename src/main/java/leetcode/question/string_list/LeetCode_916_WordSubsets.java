package leetcode.question.string_list;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  916. Word Subsets
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 85.27%
 *@Time Complexity: O(A + B), A and B represent the total information in words1 and words2, respectively.
 *@Space Complexity: O(A.length)
 */
/**
 * 题目描述：
 * 916. Word Subsets（通用单词子集）
 *
 * 给定两个字符串数组 `words1` 和 `words2`，定义 `words2` 的一个单词 `b` 是 `words1` 的通用单词，
 * 当且仅当 `words1` 中的每个单词 `a` 都包含 `b` 中的所有字符（包括重复次数）。
 *
 * **目标**：返回 `words1` 中所有满足 `words2` **所有单词字符要求** 的单词列表。
 *
 * **示例 1**
 * ```plaintext
 * 输入: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
 * 输出: ["facebook","google","leetcode"]
 * ```
 *
 * **示例 2**
 * ```plaintext
 * 输入: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
 * 输出: ["apple","google","leetcode"]
 * ```
 *
 * ---
 *
 * **解题思路**
 *
 * **1. 统计 `words2` 中所有单词的最大字母频率**
 * - `words2` 可能包含多个单词，例如 `["e", "oo"]`
 * - 每个单词 `b` 都有一个 `bCount` 统计 `b` 中各个字符的出现次数
 * - `bmax[i]` 记录 `words2` 中所有单词 `b` 的某个字母 `i` 的**最大出现次数**
 *
 * **示例**
 * ```plaintext
 * words2 = ["e", "oo"]
 * "e"  -> 频率: [0,0,0,0,1,...]  // 'e' 出现 1 次
 * "oo" -> 频率: [0,0,0,0,0,2,...]  // 'o' 出现 2 次
 * 结果 bmax:  [0,0,0,0,1,2,...]  // 'e' 至少 1 次，'o' 至少 2 次
 * ```
 *
 * **2. 遍历 `words1`，检查是否符合 `bmax` 要求**
 * - 对于 `words1` 中的每个单词 `a`：
 *   - 统计 `a` 的字母频率 `aCount[]`
 *   - 逐一比较 `aCount[i] >= bmax[i]` 是否成立
 *   - 若所有字母 `i` 满足 `aCount[i] >= bmax[i]`，则 `a` 是合法单词，加入结果
 *
 * ---
 *
 * **举例分析**
 *
 * **输入:** `words1 = ["amazon","apple","facebook","google","leetcode"]`, `words2 = ["e","o"]`
 *
 * **步骤 1：计算 `bmax`**
 * ```plaintext
 * "e"  -> 频率: [0,0,0,0,1,...]
 * "o"  -> 频率: [0,0,0,0,0,1,...]
 * 结果 bmax:  [0,0,0,0,1,1,...]  // 'e' 至少 1 次，'o' 至少 1 次
 * ```
 *
 * **步骤 2：遍历 `words1`**
 * ```plaintext
 * "amazon"    频率: [2,0,0,0,0,0,0,0,0,0,0,1,0,0,1,...] // 'o' 不满足
 * "apple"     频率: [1,0,0,0,1,2,0,0,0,0,0,1,0,0,0,...] // 'o' 不满足
 * "facebook"  频率: [1,0,0,0,1,1,0,0,0,0,0,1,0,0,1,...] // 满足
 * "google"    频率: [1,0,0,0,1,1,0,0,2,0,0,1,0,0,1,...] // 满足
 * "leetcode"  频率: [1,0,0,0,1,1,0,0,1,0,0,2,0,0,1,...] // 满足
 * ```
 *
 * **最终输出** `["facebook","google","leetcode"]`
 *
 * ---
 *
 * **时间复杂度分析**
 * - **计算 `bmax[]`（处理 `words2`）：O(B)**
 * - **检查 `words1` 是否符合 `bmax[]`（处理 `words1`）：O(A)**
 * - **总时间复杂度：O(A + B)**
 *
 * **空间复杂度分析**
 * - **存储 `bmax[]` 需要 O(26) ≈ O(1)**
 * - **存储 `ans[]` 需要 O(A.length)**
 * - **总空间复杂度：O(A.length)**
 */

public class LeetCode_916_WordSubsets{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<String> wordSubsets(String[] words1, String[] words2) {
            // 初始化一个数组 bmax[]，用于存储 words2 中每个字母出现的最大频率
            int[] bmax = count("");

            // 遍历 words2，计算每个单词的字母频率，并更新 bmax[]
            for (String b : words2) {
                int[] bCount = count(b);
                for (int i = 0; i < 26; ++i) {
                    // 记录 words2 中每个字母的最大出现次数
                    bmax[i] = Math.max(bmax[i], bCount[i]);
                }
            }

            // 结果列表，用于存储符合条件的 words1 中的单词
            List<String> ans = new ArrayList();

            // 遍历 words1，检查是否符合 word subset 条件
            search: for (String a : words1) {
                // 计算当前单词 a 的字母频率
                int[] aCount = count(a);

                // 检查 a 是否满足 bmax 要求
                for (int i = 0; i < 26; ++i) {
                    if (aCount[i] < bmax[i]) {
                        // 若 a 中某个字母的出现次数小于 bmax[]，则跳过当前单词
                        continue search;
                    }
                }
                // 若 a 满足所有字母要求，则添加到答案列表
                ans.add(a);
            }

            return ans;
        }

        // 统计字符串 S 中各个字母的出现次数
        public int[] count(String S) {
            int[] ans = new int[26]; // 用长度为 26 的数组记录 'a' - 'z' 出现次数
            for (char c : S.toCharArray()) {
                ans[c - 'a']++; // 将字母转化为索引，并计数
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_916_WordSubsets().new Solution();

        // 测试样例 1
        String[] words1_1 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2_1 = {"e","o"};
        System.out.println(solution.wordSubsets(words1_1, words2_1)); // 预期输出: ["facebook","google","leetcode"]

        // 测试样例 2
        String[] words1_2 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2_2 = {"l","e"};
        System.out.println(solution.wordSubsets(words1_2, words2_2)); // 预期输出: ["apple","google","leetcode"]

        // 测试样例 3
        String[] words1_3 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2_3 = {"e","oo"};
        System.out.println(solution.wordSubsets(words1_3, words2_3)); // 预期输出: ["facebook","google"]

        // 测试样例 4
        String[] words1_4 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2_4 = {"lo","eo"};
        System.out.println(solution.wordSubsets(words1_4, words2_4)); // 预期输出: ["google","leetcode"]

        // 测试样例 5
        String[] words1_5 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2_5 = {"ec","oc","ceo"};
        System.out.println(solution.wordSubsets(words1_5, words2_5)); // 预期输出: ["facebook","leetcode"]
    }
}

/**
You are given two string arrays words1 and words2. 

 A string b is a subset of string a if every letter in b occurs in a including 
multiplicity. 

 
 For example, "wrr" is a subset of "warrior" but is not a subset of "world". 
 

 A string a from words1 is universal if for every string b in words2, b is a 
subset of a. 

 Return an array of all the universal strings in words1. You may return the 
answer in any order. 

 
 Example 1: 

 
 Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = [
"e","o"] 
 

 Output: ["facebook","google","leetcode"] 

 Example 2: 

 
 Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = [
"lc","eo"] 
 

 Output: ["leetcode"] 

 Example 3: 

 
 Input: words1 = ["acaac","cccbb","aacbb","caacc","bcbbb"], words2 = ["c","cc",
"b"] 
 

 Output: ["cccbb"] 

 
 Constraints: 

 
 1 <= words1.length, words2.length <= 10⁴ 
 1 <= words1[i].length, words2[i].length <= 10 
 words1[i] and words2[i] consist only of lowercase English letters. 
 All the strings of words1 are unique. 
 

 Related Topics Array Hash Table String 👍 3486 👎 312

*/
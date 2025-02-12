package leetcode.question.two_pointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  *@Question:  438. Find All Anagrams in a String
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 67.76%
  *@Time  Complexity: O(N) N is length of s, k=26
  *@Space Complexity: O(K)
 */
/*
1. 题目描述：
   给定两个字符串 s 和 p，要求在 s 中找出所有 p 的「异位词」的起始索引并返回这些索引构成的列表。
   异位词是指由相同字符但排列顺序不同的字符串。例如，p = "abc" 的异位词可以是 "abc", "cba", "bac" 等。

2. 解题思路（基于滑动窗口与哈希表计数的方式，超级详细分步讲解）：

   (1) 滑动窗口的目标：
       - 使用一个大小为 p.length() 的滑动窗口在字符串 s 上滑动。
       - 维护两个哈希表：
         - pCount：统计字符串 p 中每个字符出现的次数。
         - sCount：统计当前滑动窗口中每个字符出现的次数。
       - 每当 sCount 和 pCount 相等，意味着当前窗口是一个异位词。

   (2) 构建基准哈希表 pCount：
       - 遍历 p 中的所有字符，将每个字符出现的次数存入 pCount。
       - 例如，如果 p = "abc"，那么 pCount 中 'a', 'b', 'c' 的计数分别为 1。

   (3) 初始化滑动窗口与 sCount：
       - 我们准备遍历 s 的每一个字符，依次向滑动窗口中“添加”这个字符并更新 sCount。
       - 当滑动窗口的大小（即被“关注”的字符数量）超过 p.length() 时，要“移除”滑动窗口最左侧的字符并更新 sCount。

   (4) 如何更新 sCount：
       - 当我们把一个新字符（右侧字符）放进窗口时，如果该字符已存在于 sCount，则计数加 1；否则将其计数设置为 1。
       - 如果窗口大小超过 p.length()，则需要移除最左侧字符的计数。如果移除后计数变为 0，则从 sCount 中删掉该字符的键。

   (5) 判断是否为异位词：
       - 在每次扩展或收缩窗口后，当窗口的大小刚好等于 p.length()，检查 sCount 是否和 pCount 完全相等。
       - 若相等，则说明当前窗口代表了 p 的一个异位词，对应的起始索引是 (i - p.length() + 1)。
       - 例如，如果 s = "cbaebabacd" 且 p = "abc"：
         - 当 i 为 2 时，滑动窗口中字符为 "cba"，与 pCount 中 'a':1, 'b':1, 'c':1 相同，所以记录索引 0。
         - 当 i 为 8 时，滑动窗口中字符为 "bac"，依旧与 pCount 相同，所以记录索引 6。

   (6) 收集结果：
       - 每当我们在滑动窗口末尾确认 sCount 等于 pCount，就把当前窗口的起始位置记录在结果列表中，最终返回这些起始索引。

3. 时间和空间复杂度：
   - 时间复杂度：
     对字符串 s 进行了一次遍历，滑动窗口左右指针最多各移动 n 次（其中 n = s.length()）。
     每次更新哈希表与比较都可以视为 O(1)（如果字符集大小固定，如小写英文字母为 26，那么比较哈希表或更新计数可以视为常数）。
     因此整体时间复杂度约为 O(n)。

   - 空间复杂度：
     主要来自两个哈希表 pCount 和 sCount。它们的键在字符集固定时最多存储 26 个字符的计数，因此空间复杂度可视为 O(1)；
     如果字符集不固定，则最多为 O(k)，其中 k 是字符集大小。结果列表在最坏情况下也不会超过 O(n)，但这往往视为输出所需空间。
*/


// 定义一个名为 LeetCode_438_FindAllAnagramsInAString 的公共类
public class LeetCode_438_FindAllAnagramsInAString{

    //leetcode submit region begin(Prohibit modification and deletion)
    // 定义一个内部类 Solution
    class Solution {
        // 定义一个方法 findAnagrams，用于在字符串 s 中找到字符串 p 的所有异位词的起始索引
        public List<Integer> findAnagrams(String s, String p) {
            // 定义两个变量 ns 和 np，分别表示 s 和 p 的长度
            int ns = s.length(), np = p.length();
            // 如果 s 的长度比 p 的长度更小，直接返回一个空的 ArrayList
            if (ns < np) return new ArrayList();

            // 定义两个 Map，用于统计字符频率，pCount 用于存放 p 的字符频率，sCount 用于存放滑动窗口中 s 的字符频率
            Map<Character, Integer> pCount = new HashMap();
            Map<Character, Integer> sCount = new HashMap();

            // Build a reference hashmap using string p
            // 在字符串 p 上建立一个参考的哈希表，统计 p 中每个字符出现的次数
            for (char ch : p.toCharArray()) {
                // 如果 pCount 中已经包含 ch，则在原有计数上加一
                if (pCount.containsKey(ch)) {
                    pCount.put(ch, pCount.get(ch) + 1);
                }
                // 如果 pCount 中不包含 ch，则初始化计数为 1
                else {
                    pCount.put(ch, 1);
                }
            }

            // 定义一个 List<Integer> 来存储所有符合条件的起始索引
            List<Integer> output = new ArrayList();

            // The sliding window on the string s
            // 在字符串 s 上使用滑动窗口
            for (int i = 0; i < ns; ++i) {
                // Add one more letter
                // on the right side of the window
                // 向滑动窗口的右侧增加一个字符
                char ch = s.charAt(i);
                // 如果 sCount 已经包含该字符，则在原有计数上加一
                if (sCount.containsKey(ch)) {
                    sCount.put(ch, sCount.get(ch) + 1);
                }
                // 如果 sCount 不包含该字符，则初始化计数为 1
                else {
                    sCount.put(ch, 1);
                }

                // Remove one letter
                // from the left side of the window
                // 当滑动窗口的大小超过 p 的长度时，移除最左侧的字符
                if (i >= np) {
                    ch = s.charAt(i - np);
                    // 如果要移除的字符计数为 1，直接从 sCount 中删除
                    if (sCount.get(ch) == 1) {
                        sCount.remove(ch);
                    }
                    // 否则，将计数减一
                    else {
                        sCount.put(ch, sCount.get(ch) - 1);
                    }
                }

                // Compare hashmap in the sliding window
                // with the reference hashmap
                // 比较滑动窗口 sCount 和 pCount 是否相等
                if (pCount.equals(sCount)) {
                    // 如果相等，说明当前窗口是 p 的异位词，将起始索引加入结果
                    output.add(i - np + 1);
                }
            }
            // 返回结果列表
            return output;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    // 定义 main 方法，用于测试
    public static void main(String[] args) {
        // 创建一个 Solution 实例
        Solution solution = new LeetCode_438_FindAllAnagramsInAString().new Solution();
        // TO TEST

        // 在此处添加测试用例
        // 测试用例1：
        String s1 = "cbaebabacd";
        String p1 = "abc";
        List<Integer> result1 = solution.findAnagrams(s1, p1);
        System.out.println("测试用例1结果（预期：[0, 6]）: " + result1);

        // 测试用例2：
        String s2 = "abab";
        String p2 = "ab";
        List<Integer> result2 = solution.findAnagrams(s2, p2);
        System.out.println("测试用例2结果（预期：[0, 1, 2]）: " + result2);

        // 可以根据需要再添加更多测试
    }
}

/**
Given two strings s and p, return an array of all the start indices of p's 
anagrams in s. You may return the answer in any order. 

 
 Example 1: 

 
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
 

 Example 2: 

 
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

 
 Constraints: 

 
 1 <= s.length, p.length <= 3 * 10⁴ 
 s and p consist of lowercase English letters. 
 

 Related Topics Hash Table String Sliding Window 👍 12596 👎 348

*/
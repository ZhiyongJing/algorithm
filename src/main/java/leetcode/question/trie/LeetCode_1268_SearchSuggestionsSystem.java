package leetcode.question.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Question:  1268. Search Suggestions System
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 80.81%
 *@Time  Complexity:   O(M^2) for solution1, m is the length of the search word
 *                       O(M) for solution2, M is total number of characters in products
 *@Space Complexity:   O(M^2) for solution1, m is the length of the search word
 *                      O(M) for solution2 m is the length of the search word.
 */

/**
 * 这道题目是实现一个搜索建议系统，给定一组产品名称和一个搜索词，要求返回以该搜索词开头的最多三个建议单词的列表。题目提供了两种解决方案：一种是基于二分搜索的解决方案，另一种是基于 Trie 树的解决方案。
 *
 * 解题思路：
 *
 * 1. **基于二分搜索的解决方案**：
 *    - 首先将产品名称数组按字典序排序。
 *    - 然后，遍历搜索词中的每个字符，依次将字符添加到前缀中。
 *    - 对于每个前缀，使用二分搜索查找在排序后的产品数组中以该前缀开头的单词的起始索引。
 *    - 找到起始索引后，向后遍历最多三个单词，将以该前缀开头的单词添加到结果列表中。
 *    - 循环遍历搜索词中的每个字符，直到搜索词结束，返回结果列表。
 *
 * 2. **基于 Trie 树的解决方案**：
 *    - 首先定义一个 Trie 树的数据结构，其中每个节点表示一个字母，节点之间通过 children 列表连接。
 *    - 将产品名称数组中的每个单词插入到 Trie 树中。
 *    - 对于搜索词中的每个字符，依次将字符添加到前缀中，并在 Trie 树中查找以该前缀开头的单词。
 *    - 如果找到了以该前缀开头的单词，就将这些单词添加到结果列表中，限制最多添加三个。
 *    - 继续循环遍历搜索词中的每个字符，直到搜索词结束，返回结果列表。
 *
 * 时间复杂度分析：
 * - 基于二分搜索的解决方案：
 *   - 排序产品名称数组的时间复杂度为 O(M * logM)，其中 M 是产品名称数组的长度。
 *   - 每次搜索词中的每个字符时，使用二分搜索查找前缀的时间复杂度为 O(logM)。
 *   - 总体时间复杂度为 O(M * logM + N * logM)，其中 N 是搜索词的长度。
 * - 基于 Trie 树的解决方案：
 *   - 将产品名称插入到 Trie 树的时间复杂度为 O(M * K)，其中 K 是单词的平均长度。
 *   - 对搜索词中的每个字符进行查询的时间复杂度为 O(N * K)，其中 N 是搜索词的长度。
 *   - 总体时间复杂度为 O(M * K + N * K)。
 *
 * 空间复杂度分析：
 * - 基于二分搜索的解决方案：
 *   - 需要额外空间来存储排序后的产品名称数组，空间复杂度为 O(M)。
 * - 基于 Trie 树的解决方案：
 *   - 需要额外空间来存储 Trie 树，空间复杂度取决于产品名称数组中所有单词的总长度，即 O(M * K)。
 */

public class LeetCode_1268_SearchSuggestionsSystem{

    //leetcode submit region begin(Prohibit modification and deletion)
    // 自定义 Trie 类，包含获取以指定前缀开头的 3 个单词的功能
    class Trie {

        // Trie 节点的定义
        class Node {
            boolean isWord = false;
            List<Node> children = Arrays.asList(new Node[26]);
        };
        Node Root, curr;
        List<String> resultBuffer;

        // 运行一个深度优先搜索（DFS）在 Trie 上，从给定前缀开始，并将所有单词添加到 resultBuffer 中，限制结果大小为 3
        void dfsWithPrefix(Node curr, String word) {
            if (resultBuffer.size() == 3)
                return;
            if (curr.isWord)
                resultBuffer.add(word);

            // 在所有可能的路径上运行 DFS。
            for (char c = 'a'; c <= 'z'; c++)
                if (curr.children.get(c - 'a') != null)
                    dfsWithPrefix(curr.children.get(c - 'a'), word + c);
        }
        Trie() {
            Root = new Node();
        }

        // 在 Trie 中插入字符串
        void insert(String s) {

            // 将 curr 指针指向 Trie 的根节点。
            curr = Root;
            for (char c : s.toCharArray()) {
                if (curr.children.get(c - 'a') == null)
                    curr.children.set(c - 'a', new Node());
                curr = curr.children.get(c - 'a');
            }

            // 将该节点标记为一个完成的单词。
            curr.isWord = true;
        }

        // 获取以指定前缀开头的单词
        List<String> getWordsStartingWith(String prefix) {
            curr = Root;
            resultBuffer = new ArrayList<String>();
            // 将 curr 移动到其 Trie 表示中前缀的末尾。
            for (char c : prefix.toCharArray()) {
                if (curr.children.get(c - 'a') == null)
                    return resultBuffer;
                curr = curr.children.get(c - 'a');
            }
            dfsWithPrefix(curr, prefix);
            return resultBuffer;
        }
    };
    class Solution {
        // Java 中等价于 lower_bound 的代码
        int lower_bound(String[] products, int start, String word) {
            int i = start, j = products.length, mid;
            while (i < j) {
                mid = (i + j) / 2;
                if (products[mid].compareTo(word) >= 0) j = mid;
                else i = mid + 1;
            }
            return i;
        }

        // Solution 1: 二分查找
        public List<List<String>> suggestedProducts1(String[] products, String searchWord) {
            Arrays.sort(products);
            List<List<String>> result = new ArrayList<>();
            int start = 0, bsStart = 0, n = products.length;
            String prefix = "";
            for (char c : searchWord.toCharArray()) {
                prefix += c;

                // 获取以 `prefix` 开头的单词的起始索引。
                start = lower_bound(products, bsStart, prefix);

                // 添加空列表到结果中。
                result.add(new ArrayList<>());

                // 将具有相同前缀的单词添加到结果中。
                // 循环运行直到 `i` 达到输入的末尾或 3 次或 `products[i]` 的前缀相同。
                for (int i = start; i < Math.min(start + 3, n); i++) {
                    if (products[i].length() < prefix.length() || !products[i].startsWith(prefix))
                        break;
                    result.get(result.size() - 1).add(products[i]);
                }

                // 由于已知，减小要进行二分搜索的元素的大小
                bsStart = Math.abs(start);
            }
            return result;
        }

        // Solution2: Trie
        List<List<String>> suggestedProducts(String[] products, String searchWord) {
            Trie trie = new Trie();
            List<List<String>> result = new ArrayList<>();
            // 将所有单词添加到 Trie 中。
            for (String w : products)
                trie.insert(w);
            String prefix = new String();
            for (char c : searchWord.toCharArray()) {
                prefix += c;
                result.add(trie.getWordsStartingWith(prefix));
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1268_SearchSuggestionsSystem().new Solution();
        // 测试代码
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        List<List<String>> result = solution.suggestedProducts(products, searchWord);
        for (List<String> list : result) {
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}

/**
 You are given an array of strings products and a string searchWord.

 Design a system that suggests at most three product names from products after
 each character of searchWord is typed. Suggested products should have common
 prefix with searchWord. If there are more than three products with a common prefix
 return the three lexicographically minimums products.

 Return a list of lists of the suggested products after each character of
 searchWord is typed.


 Example 1:


 Input: products = ["mobile","mouse","moneypot","monitor","mousepad"],
 searchWord = "mouse"
 Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],[
 "mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
 Explanation: products sorted lexicographically = ["mobile","moneypot","monitor",
 "mouse","mousepad"].
 After typing m and mo all products match and we show user ["mobile","moneypot",
 "monitor"].
 After typing mou, mous and mouse the system suggests ["mouse","mousepad"].


 Example 2:


 Input: products = ["havana"], searchWord = "havana"
 Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 Explanation: The only word "havana" will be always suggested while typing the
 search word.



 Constraints:


 1 <= products.length <= 1000
 1 <= products[i].length <= 3000
 1 <= sum(products[i].length) <= 2 * 10⁴
 All the strings of products are unique.
 products[i] consists of lowercase English letters.
 1 <= searchWord.length <= 1000
 searchWord consists of lowercase English letters.


 Related Topics Array String Binary Search Trie Sorting Heap (Priority Queue) 👍
 4645 👎 238

 */
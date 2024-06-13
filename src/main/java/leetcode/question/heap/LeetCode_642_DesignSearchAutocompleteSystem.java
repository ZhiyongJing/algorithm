package leetcode.question.heap;

/**
 *@Question:  642. Design Search Autocomplete System
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 70.62%
 *@Time  Complexity: O(N * K + M * (N + M/K)) N is length of sentence, K is average length of sentence, M the number of times input called
 *@Space Complexity: O(K * (N * K + M))
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * ### 解题思路
 *
 * 设计一个自动完成系统，主要思想是使用Trie（字典树）来存储和检索句子，并结合优先队列（堆）来找到最热门的句子。以下是详细的解题思路：
 *
 * 1. **数据结构选择**：
 *    - **Trie节点（TrieNode）**：
 *      - `children`: 一个字典，键为字符，值为子节点，表示Trie的树结构。
 *      - `sentences`: 一个字典，键为句子，值为出现次数，表示该节点下所有以当前路径为前缀的句子及其热度。
 *
 *    - **自动完成系统（AutocompleteSystem）**：
 *      - `root`: Trie的根节点。
 *      - `currNode`: 当前节点，用于跟踪用户输入路径的节点。
 *      - `currSentence`: 一个字符串构建器，用于记录当前输入的句子。
 *      - `dead`: 一个特殊节点，用于表示当前输入路径在Trie中不存在匹配。
 *
 * 2. **构建Trie**：
 *    - 初始化时，将所有初始句子及其出现次数添加到Trie中。遍历每个句子的每个字符，在Trie中创建相应的路径，并在每个节点的`sentences`中记录句子和它的出现次数。
 *
 * 3. **处理输入**：
 *    - 对于每个输入字符：
 *      - 如果是结束符`#`，将当前输入的句子添加到Trie中，并重置当前输入和节点。
 *      - 否则，将字符添加到当前输入句子中，尝试在Trie中找到匹配的子节点。如果找不到匹配的子节点，当前节点指向`dead`节点，并返回空列表。
 *      - 使用优先队列（堆）来维护当前节点下的最热门的三个句子。根据热度和字典序来排序，并将结果列表反转以得到正确顺序。
 *
 * 4. **优先队列（堆）维护热门句子**：
 *    - 遍历当前节点下所有的句子，使用优先队列来维护最热门的三个句子。优先队列按热度升序排列，如果热度相同则按字典序降序排列。
 *    - 当堆的大小超过3时，移除堆顶元素。
 *
 * ### 时间复杂度
 * 1. **初始化Trie**：`O(N * K)`，其中`N`是句子的数量，`K`是平均句子长度。我们需要遍历每个句子的每个字符来构建Trie。
 *
 * 2. **处理输入字符**：
 *    - 最坏情况下，每次输入字符都需要在Trie中遍历相应的路径。对于每个输入字符，我们需要处理`O(M)`次输入。
 *    - 对于每个输入字符，找到所有以当前前缀为开头的句子需要`O(N)`时间，将这些句子添加到优先队列需要`O(M/K)`时间。因此，总时间复杂度为`O(M * (N + M/K))`。
 *
 * ### 空间复杂度
 * - **Trie结构**：`O(N * K)`，存储所有句子及其字符路径。
 * - **优先队列和当前输入**：`O(M)`，其中`M`是输入的字符数量。
 * - 总空间复杂度为`O(K * (N * K + M))`，其中`K`是平均句子长度，`N`是句子的数量，`M`是输入的字符数量。
 **/

public class LeetCode_642_DesignSearchAutocompleteSystem{

    //leetcode submit region begin(Prohibit modification and deletion)
    class TrieNode {
        // 子节点映射
        Map<Character, TrieNode> children;
        // 存储句子及其出现次数
        Map<String, Integer> sentences;

        public TrieNode() {
            children = new HashMap<>();
            sentences = new HashMap<>();
        }
    }

    class AutocompleteSystem {
        TrieNode root;  // 根节点
        TrieNode currNode;  // 当前节点
        TrieNode dead;  // 用于无匹配时的节点
        StringBuilder currSentence;  // 当前输入的句子

        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();  // 初始化根节点
            // 将所有句子和次数添加到Trie中
            for (int i = 0; i < sentences.length; i++) {
                addToTrie(sentences[i], times[i]);
            }

            currSentence = new StringBuilder();  // 初始化当前输入句子
            currNode = root;  // 当前节点指向根节点
            dead = new TrieNode();  // 初始化无匹配节点
        }

        public List<String> input(char c) {
            // 如果输入的是结束符'#'
            if (c == '#') {
                addToTrie(currSentence.toString(), 1);  // 将当前句子添加到Trie中
                currSentence.setLength(0);  // 重置当前句子
                currNode = root;  // 重置当前节点
                return new ArrayList<String>();  // 返回空列表
            }

            currSentence.append(c);  // 将当前字符添加到当前句子中
            // 如果当前字符在Trie中没有匹配
            if (!currNode.children.containsKey(c)) {
                currNode = dead;  // 当前节点指向无匹配节点
                return new ArrayList<String>();  // 返回空列表
            }

            currNode = currNode.children.get(c);  // 当前节点指向匹配的子节点
            // 优先队列用于找出最热门的三个句子
            PriorityQueue<String> heap = new PriorityQueue<>((a, b) -> {
                int hotA = currNode.sentences.get(a);
                int hotB = currNode.sentences.get(b);
                if (hotA == hotB) {
                    return b.compareTo(a);  // 按字典顺序比较
                }
                return hotA - hotB;  // 按热度比较
            });

            // 将所有句子添加到优先队列中
            for (String sentence: currNode.sentences.keySet()) {
                heap.add(sentence);
                if (heap.size() > 3) {
                    heap.remove();  // 保持堆的大小为3
                }
            }

            List<String> ans = new ArrayList<>();
            while (!heap.isEmpty()) {
                ans.add(heap.remove());  // 将堆中的元素添加到结果列表
            }

            Collections.reverse(ans);  // 反转列表以得到正确顺序
            return ans;
        }

        private void addToTrie(String sentence, int count) {
            TrieNode node = root;
            // 将句子中的每个字符添加到Trie中
            for (char c: sentence.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
                node.sentences.put(sentence, node.sentences.getOrDefault(sentence, 0) + count);
            }
        }
    }

    /**
     * Your AutocompleteSystem object will be instantiated and called as such:
     * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
     * List<String> param_1 = obj.input(c);
     */
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 初始化句子和次数
        String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};

        // 创建自动完成系统对象
        AutocompleteSystem obj = new LeetCode_642_DesignSearchAutocompleteSystem().new AutocompleteSystem(sentences, times);

        // 测试输入
        System.out.println(obj.input('i'));  // 输出: ["i love you", "island", "i love leetcode"]
        System.out.println(obj.input(' '));  // 输出: ["i love you", "i love leetcode"]
        System.out.println(obj.input('a'));  // 输出: []
        System.out.println(obj.input('#'));  // 输出: []

        // 再次测试输入
        System.out.println(obj.input('i'));  // 输出: ["i love you", "island", "i love leetcode"]
        System.out.println(obj.input(' '));  // 输出: ["i love you", "i love leetcode"]
        System.out.println(obj.input('a'));  // 输出: []
        System.out.println(obj.input('#'));  // 输出: []
    }
}

/**
Design a search autocomplete system for a search engine. Users may input a 
sentence (at least one word and end with a special character '#'). 

 You are given a string array sentences and an integer array times both of 
length n where sentences[i] is a previously typed sentence and times[i] is the 
corresponding number of times the sentence was typed. For each input character except 
'#', return the top 3 historical hot sentences that have the same prefix as the 
part of the sentence already typed. 

 Here are the specific rules: 

 
 The hot degree for a sentence is defined as the number of times a user typed 
the exactly same sentence before. 
 The returned top 3 hot sentences should be sorted by hot degree (The first is 
the hottest one). If several sentences have the same hot degree, use ASCII-code 
order (smaller one appears first). 
 If less than 3 hot sentences exist, return as many as you can. 
 When the input is a special character, it means the sentence ends, and in this 
case, you need to return an empty list. 
 

 Implement the AutocompleteSystem class: 

 
 AutocompleteSystem(String[] sentences, int[] times) Initializes the object 
with the sentences and times arrays. 
 List<String> input(char c) This indicates that the user typed the character c. 

 
 Returns an empty array [] if c == '#' and stores the inputted sentence in the 
system. 
 Returns the top 3 historical hot sentences that have the same prefix as the 
part of the sentence already typed. If there are fewer than 3 matches, return them 
all. 
 
 

 
 Example 1: 

 
Input
["AutocompleteSystem", "input", "input", "input", "input"]
[[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [
" "], ["a"], ["#"]]
Output
[null, ["i love you", "island", "i love leetcode"], ["i love you", "i love 
leetcode"], [], []]

Explanation
AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", 
"iroman", "i love leetcode"], [5, 3, 2, 2]);
obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There 
are four sentences that have prefix "i". Among them, "ironman" and "i love 
leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, 
"i love leetcode" should be in front of "ironman". Also we only need to output 
top 3 hot sentences, so "ironman" will be ignored.
obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two 
sentences that have prefix "i ".
obj.input("a"); // return []. There are no sentences that have prefix "i a".
obj.input("#"); // return []. The user finished the input, the sentence "i a" 
should be saved as a historical sentence in system. And the following input will 
be counted as a new search.
 

 
 Constraints: 

 
 n == sentences.length 
 n == times.length 
 1 <= n <= 100 
 1 <= sentences[i].length <= 100 
 1 <= times[i] <= 50 
 c is a lowercase English letter, a hash '#', or space ' '. 
 Each tested sentence will be a sequence of characters c that end with the 
character '#'. 
 Each tested sentence will have a length in the range [1, 200]. 
 The words in each input sentence are separated by single spaces. 
 At most 5000 calls will be made to input. 
 

 Related Topics String Design Trie Sorting Heap (Priority Queue) Data Stream 👍 
2101 👎 170

*/
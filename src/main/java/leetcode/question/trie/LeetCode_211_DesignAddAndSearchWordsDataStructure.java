package leetcode.question.trie;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  211. Design Add and Search Words Data Structure
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 27.48%
 *@Time  Complexity: O(M)
 *@Space Complexity: O(M) for addWord, O(1) for search
 */

/**
 * ### 解题思路
 *
 * 这道题的目的是设计一个数据结构，可以添加单词并支持使用通配符 `.` 搜索单词。通配符 `.` 可以匹配任何单个字符。为此，我们可以使用 Trie（前缀树）来实现。
 *
 * #### Trie（前缀树）的基本概念
 *
 * Trie 是一种树形数据结构，特别适用于处理字符串相关的操作。每个节点代表一个字符，每条从根到叶的路径代表一个字符串。Trie 的主要优势是它可以高效地进行字符串的插入、删除和查找操作。
 *
 * #### 数据结构设计
 *
 * 1. **TrieNode 类**：
 *    - 每个节点有一个 `children` 字段，这是一个 Map，映射字符到相应的子节点。
 *    - 每个节点有一个 `word` 布尔值，用于标识该节点是否是某个单词的结尾。
 *
 * 2. **WordDictionary 类**：
 *    - 包含一个根节点 `trie`，即 Trie 的根节点。
 *    - `addWord` 方法用于向 Trie 中添加单词。
 *    - `search` 方法用于在 Trie 中搜索单词，可以包含通配符 `.`。
 *    - `searchInNode` 方法是一个辅助方法，用于递归搜索单词。
 *
 * #### 操作流程
 *
 * 1. **添加单词（addWord）**：
 *    - 从根节点开始，遍历单词的每个字符。
 *    - 对于每个字符，如果当前节点的子节点中不存在该字符，则创建一个新的子节点。
 *    - 移动到子节点，继续处理下一个字符。
 *    - 在处理完所有字符后，将最后一个节点标记为单词的结尾。
 *
 * 2. **搜索单词（search）**：
 *    - 从根节点开始，使用辅助方法 `searchInNode` 递归搜索单词。
 *    - 对于每个字符，如果是通配符 `.`，则检查所有可能的子节点，递归搜索剩余的单词。
 *    - 如果不是通配符，则检查当前字符是否在子节点中，如果不在则返回 false。
 *    - 如果当前字符在子节点中，继续在子节点中搜索下一个字符。
 *    - 在处理完所有字符后，检查当前节点是否标记为单词的结尾。
 *
 * ### 时间和空间复杂度
 *
 * #### 添加单词（addWord）
 *
 * - **时间复杂度**：O(M)
 *   - 其中 M 是要添加的单词的长度。
 *   - 因为每次添加操作都需要遍历单词的每个字符，并可能在每个字符位置创建一个新的节点。
 *
 * - **空间复杂度**：O(M)
 *   - 每次添加操作可能需要创建 M 个新的节点，最坏情况下，每个添加的字符都需要一个新的节点。
 *
 * #### 搜索单词（search）
 *
 * - **时间复杂度**：O(M)
 *   - 其中 M 是要搜索的单词的长度。
 *   - 因为每次搜索操作都需要遍历单词的每个字符。虽然通配符 `.` 可能增加了复杂度，但由于每个节点最多有 26 个子节点，且搜索的深度受限于单词的长度，因此总体时间复杂度仍为 O(M)。
 *
 * - **空间复杂度**：O(1)
 *   - 搜索操作不需要额外的空间，除了递归调用栈的开销，但这可以看作是常数级别的开销。
 */
public class LeetCode_211_DesignAddAndSearchWordsDataStructure{

    //leetcode submit region begin(Prohibit modification and deletion)
    class TrieNode {
        // 子节点的映射，字符到 TrieNode 的映射
        Map<Character, TrieNode> children = new HashMap<>();
        // 标记该节点是否是某个单词的结尾
        boolean word = false;

        // TrieNode 构造函数
        public TrieNode() {}
    }

    class WordDictionary {
        TrieNode trie;

        /** Initialize your data structure here. */
        // 初始化数据结构
        public WordDictionary() {
            trie = new TrieNode();
        }

        /** Adds a word into the data structure. */
        // 添加一个单词到数据结构中
        public void addWord(String word) {
            TrieNode node = trie;

            // 遍历单词中的每个字符
            for (char ch : word.toCharArray()) {
                // 如果子节点中没有该字符，新建一个节点
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }
                // 移动到子节点
                node = node.children.get(ch);
            }
            // 设置最后一个节点为单词的结尾
            node.word = true;
        }

        /** Returns if the word is in the node. */
        // 在节点中搜索单词
        public boolean searchInNode(String word, TrieNode node) {
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                // 如果当前字符不在子节点中
                if (!node.children.containsKey(ch)) {
                    // 如果当前字符是 '.'
                    if (ch == '.') {
                        // 检查所有可能的节点
                        for (char x : node.children.keySet()) {
                            TrieNode child = node.children.get(x);
                            // 递归搜索剩余的单词
                            if (searchInNode(word.substring(i + 1), child)) {
                                return true;
                            }
                        }
                    }
                    // 如果没有节点符合要求或者当前字符不是 '.'
                    return false;
                } else {
                    // 如果找到了字符，移动到子节点
                    node = node.children.get(ch);
                }
            }
            // 返回该节点是否是单词的结尾
            return node.word;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        // 判断数据结构中是否包含该单词，单词可以包含字符 '.' 来表示任何一个字母
        public boolean search(String word) {
            return searchInNode(word, trie);
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 创建 WordDictionary 对象
        WordDictionary wordDictionary = new LeetCode_211_DesignAddAndSearchWordsDataStructure().new WordDictionary();

        // 添加单词
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        // 搜索单词
        System.out.println(wordDictionary.search("pad")); // 返回 false
        System.out.println(wordDictionary.search("bad")); // 返回 true
        System.out.println(wordDictionary.search(".ad")); // 返回 true
        System.out.println(wordDictionary.search("b..")); // 返回 true
    }
}

/**
Design a data structure that supports adding new words and finding if a string 
matches any previously added string. 

 Implement the WordDictionary class: 

 
 WordDictionary() Initializes the object. 
 void addWord(word) Adds word to the data structure, it can be matched later. 
 bool search(word) Returns true if there is any string in the data structure 
that matches word or false otherwise. word may contain dots '.' where dots can be 
matched with any letter. 
 

 
 Example: 

 
Input
["WordDictionary","addWord","addWord","addWord","search","search","search",
"search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

 
 Constraints: 

 
 1 <= word.length <= 25 
 word in addWord consists of lowercase English letters. 
 word in search consist of '.' or lowercase English letters. 
 There will be at most 2 dots in word for search queries. 
 At most 10⁴ calls will be made to addWord and search. 
 

 Related Topics String Depth-First Search Design Trie 👍 7543 👎 448

*/
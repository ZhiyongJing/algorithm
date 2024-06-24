package leetcode.question.trie;
/**
 *@Question:  208. Implement Trie (Prefix Tree)
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 45.74%
 *@Time  Complexity: O(M)
 *@Space Complexity: O(M) for insert method, O(1) fro search method and startsWith method
 */

/**
 * 当然，以下是详细的解题思路以及时间和空间复杂度分析。
 *
 * ### 解题思路
 *
 * Trie（前缀树或字典树）是一种用于存储字符串的树形数据结构，特别适用于查找字符串的前缀。它具有以下特点：
 * 1. 每个节点表示一个字符串的某个前缀。
 * 2. 根节点为空，所有字符串都从根节点开始。
 * 3. 每个节点的子节点代表从根到当前节点的字符串扩展的一个字符。
 * 4. 一个标志位 `isEnd` 用于标识当前节点是否为一个字符串的结尾。
 *
 * #### Trie 节点结构
 *
 * 每个 Trie 节点包含：
 * - 一个长度为 26 的数组 `links`，用于存储子节点，代表每个小写字母。
 * - 一个布尔变量 `isEnd`，用于标识该节点是否是某个单词的结尾。
 *
 * #### Trie 操作
 *
 * 1. **Insert（插入）**：
 *    - 从根节点开始，对要插入的字符串的每个字符逐个检查。
 *    - 如果当前字符对应的子节点不存在，就创建一个新的节点。
 *    - 移动到子节点，继续处理下一个字符。
 *    - 在处理完字符串的所有字符后，将最后一个节点标记为单词结尾。
 *
 * 2. **Search（搜索）**：
 *    - 从根节点开始，对要搜索的字符串的每个字符逐个检查。
 *    - 如果当前字符对应的子节点不存在，说明 Trie 中没有这个单词，返回 false。
 *    - 如果处理完字符串的所有字符，并且最后一个节点标记为单词结尾，则返回 true。
 *    - 否则返回 false。
 *
 * 3. **StartsWith（前缀搜索）**：
 *    - 和 Search 类似，但不需要检查最后一个节点是否为单词结尾。
 *    - 只需确认 Trie 中存在该前缀即可。
 *
 * ### 时间和空间复杂度
 *
 * #### 插入操作（Insert）
 *
 * - **时间复杂度**：O(M)
 *   - 其中 M 是要插入的字符串的长度。
 *   - 因为每次插入操作都需要遍历字符串的每个字符，并可能在每个字符位置创建一个新的节点。
 *
 * - **空间复杂度**：O(M)
 *   - 每次插入操作可能需要创建 M 个新的节点，最坏情况下，每个插入的字符都需要一个新的节点。
 *
 * #### 搜索操作（Search）
 *
 * - **时间复杂度**：O(M)
 *   - 其中 M 是要搜索的字符串的长度。
 *   - 因为每次搜索操作都需要遍历字符串的每个字符。
 *
 * - **空间复杂度**：O(1)
 *   - 搜索操作不需要额外的空间。
 *
 * #### 前缀搜索操作（StartsWith）
 *
 * - **时间复杂度**：O(M)
 *   - 其中 M 是要搜索的前缀的长度。
 *   - 因为每次前缀搜索操作都需要遍历前缀的每个字符。
 *
 * - **空间复杂度**：O(1)
 *   - 前缀搜索操作不需要额外的空间。
 *
 * ### 总结
 *
 * Trie 树通过使用固定大小的数组来存储子节点，实现了高效的字符串插入、搜索和前缀搜索操作。
 * 其时间复杂度主要取决于操作字符串的长度，而空间复杂度则取决于插入的字符数量。Trie 非常适合用于需要快速前缀查询和单词查找的应用场景。
 */

public class LeetCode_208_ImplementTriePrefixTree{

    //leetcode submit region begin(Prohibit modification and deletion)
    class TrieNode {

        // R links to node children
        // 用于保存子节点的数组，长度为26，因为只考虑小写英文字母
        private TrieNode[] links;

        // 英文字母个数
        private final int R = 26;

        // 判断是否为某个单词的结尾
        private boolean isEnd;

        // TrieNode 构造函数，初始化子节点数组
        public TrieNode() {
            links = new TrieNode[R];
        }

        // 判断当前字符是否存在于子节点数组中
        public boolean containsKey(char ch) {
            return links[ch -'a'] != null;
        }

        // 获取当前字符对应的子节点
        public TrieNode get(char ch) {
            return links[ch -'a'];
        }

        // 将当前字符对应的子节点设置为新的节点
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
        }

        // 设置当前节点为单词的结尾
        public void setEnd() {
            isEnd = true;
        }

        // 判断当前节点是否为单词的结尾
        public boolean isEnd() {
            return isEnd;
        }
    }

    class Trie {

        // 根节点
        private TrieNode root;

        // Trie 构造函数，初始化根节点
        public Trie() {
            root = new TrieNode();
        }

        // 插入一个单词到 Trie 中
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                // 如果当前字符不在子节点数组中，则插入新的节点
                if (!node.containsKey(currentChar)) {
                    node.put(currentChar, new TrieNode());
                }
                // 移动到子节点
                node = node.get(currentChar);
            }
            // 设置最后一个节点为单词的结尾
            node.setEnd();
        }

        // 在 Trie 中搜索一个前缀或单词，并返回最后一个节点
        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (node.containsKey(curLetter)) {
                    node = node.get(curLetter);
                } else {
                    return null;
                }
            }
            return node;
        }

        // 判断 Trie 中是否包含某个单词
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        // 判断 Trie 中是否包含以某个前缀开头的单词
        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 创建 Trie 对象
        Trie trie = new LeetCode_208_ImplementTriePrefixTree().new Trie();

        // 插入单词
        trie.insert("apple");
        trie.insert("app");

        // 搜索单词
        System.out.println(trie.search("apple")); // 返回 true
        System.out.println(trie.search("app"));   // 返回 true
        System.out.println(trie.search("appl"));  // 返回 false

        // 搜索前缀
        System.out.println(trie.startsWith("app")); // 返回 true
        System.out.println(trie.startsWith("apl")); // 返回 false
    }
}

/**
A trie (pronounced as "try") or prefix tree is a tree data structure used to 
efficiently store and retrieve keys in a dataset of strings. There are various 
applications of this data structure, such as autocomplete and spellchecker. 

 Implement the Trie class: 

 
 Trie() Initializes the trie object. 
 void insert(String word) Inserts the string word into the trie. 
 boolean search(String word) Returns true if the string word is in the trie (i.
e., was inserted before), and false otherwise. 
 boolean startsWith(String prefix) Returns true if there is a previously 
inserted string word that has the prefix prefix, and false otherwise. 
 

 
 Example 1: 

 
Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
 

 
 Constraints: 

 
 1 <= word.length, prefix.length <= 2000 
 word and prefix consist only of lowercase English letters. 
 At most 3 * 10⁴ calls in total will be made to insert, search, and startsWith. 

 

 Related Topics Hash Table String Design Trie 👍 11473 👎 141

*/
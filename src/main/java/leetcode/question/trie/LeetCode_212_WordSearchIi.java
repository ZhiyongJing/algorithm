package leetcode.question.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *@Question:  212. Word Search II
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 60.28%
 *@Time  Complexity: O(M * (4 * 3^(L -1))) M is the number of cells in the board and L  is the maximum length of words.
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题的目的是在一个二维字符网格中找到所有给定的单词。我们可以通过构建 Trie（前缀树）并使用回溯（Backtracking）搜索来解决这个问题。
 *
 * #### 1. 构建 Trie（前缀树）
 *
 * Trie 是一种树形数据结构，适用于快速检索字符串。每个节点表示一个字符。我们首先将所有单词插入到 Trie 中，以便后续能够快速匹配和查找单词。
 *
 * - **插入单词**：遍历单词的每个字符，如果当前节点没有该字符的子节点，则创建一个新的子节点。遍历完所有字符后，在相应节点标记该节点存储的单词。
 *
 * #### 2. 回溯搜索
 *
 * 在构建好 Trie 之后，我们可以在网格中使用回溯搜索来查找单词。
 *
 * - **遍历网格**：从网格的每个单元格开始，检查该单元格的字符是否在 Trie 的根节点的子节点中。如果是，则开始回溯搜索。
 *
 * - **回溯搜索**：在每个单元格，递归探索其四周的单元格（上、右、下、左），同时更新 Trie 的当前节点。搜索过程中，如果匹配到单词，则将该单词添加到结果中并标记为已匹配。
 *
 * - **标记和恢复**：为避免重复访问单元格，在探索过程中将已访问的单元格标记为 `#`，探索完成后恢复原来的字符。
 *
 * #### 3. 优化
 *
 * 在回溯过程中，我们可以进行一些优化：
 *
 * - **去除叶节点**：在回溯过程中，如果发现当前节点的所有子节点都为空，则可以将该节点从 Trie 中移除，以减少后续搜索的开销。
 *
 * ### 时间和空间复杂度
 *
 * #### 时间复杂度
 *
 * - **构建 Trie**：O(L)，其中 L 是所有单词的字符总数。因为每个单词的每个字符都需要插入到 Trie 中。
 *
 * - **回溯搜索**：O(N * 3^L)，其中 N 是网格中的单元格数，L 是单词的最大长度。在每个单元格，我们最多会进行 4 个方向的探索，但由于不能回到原来的位置，实际的探索方向是 3 个。
 *
 *   - 遍历每个单元格的时间复杂度是 O(N)。
 *   - 在每个单元格进行回溯搜索的时间复杂度是 O(3^L)，因为每个单元格在最坏情况下会被访问 3^L 次。
 *
 * 因此，总的时间复杂度是 O(N * 3^L)。
 *
 * #### 空间复杂度
 *
 * - **Trie 的空间复杂度**：O(K)，其中 K 是 Trie 中存储单词的字符总数。Trie 的空间复杂度主要取决于存储的单词数量和长度。
 *
 * - **递归调用栈的空间复杂度**：最坏情况下，递归调用栈的深度是单词的最大长度 L，因此空间复杂度是 O(L)。
 *
 * 总的空间复杂度是 O(K + L)。
 */
public class LeetCode_212_WordSearchIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class TrieNode {
        // 子节点的映射，字符到 TrieNode 的映射
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        // 当前节点存储的单词，如果为空表示不是单词的结尾
        String word = null;

        // TrieNode 构造函数
        public TrieNode() {}
    }

    class Solution {
        // 存储字母板
        char[][] _board = null;
        // 存储结果
        ArrayList<String> _result = new ArrayList<String>();

        public List<String> findWords(char[][] board, String[] words) {
            // 步骤1：构建 Trie
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode node = root;

                for (Character letter : word.toCharArray()) {
                    if (node.children.containsKey(letter)) {
                        node = node.children.get(letter);
                    } else {
                        TrieNode newNode = new TrieNode();
                        node.children.put(letter, newNode);
                        node = newNode;
                    }
                }
                node.word = word; // 在 Trie 中存储单词
            }

            this._board = board;
            // 步骤2：从板上的每个单元格开始进行回溯
            for (int row = 0; row < board.length; ++row) {
                for (int col = 0; col < board[row].length; ++col) {
                    if (root.children.containsKey(board[row][col])) {
                        backtracking(row, col, root);
                    }
                }
            }

            return this._result;
        }

        private void backtracking(int row, int col, TrieNode parent) {
            // 获取当前字母
            Character letter = this._board[row][col];
            // 获取当前节点
            TrieNode currNode = parent.children.get(letter);

            // 检查是否有匹配
            if (currNode.word != null) {
                this._result.add(currNode.word);
                currNode.word = null; // 防止重复添加
            }

            // 在探索之前标记当前字母
            this._board[row][col] = '#';

            // 探索四个方向：上、右、下、左
            int[] rowOffset = { -1, 0, 1, 0 };
            int[] colOffset = { 0, 1, 0, -1 };
            for (int i = 0; i < 4; ++i) {
                int newRow = row + rowOffset[i];
                int newCol = col + colOffset[i];
                if (
                        newRow < 0 ||
                                newRow >= this._board.length ||
                                newCol < 0 ||
                                newCol >= this._board[0].length
                ) {
                    continue;
                }
                if (currNode.children.containsKey(this._board[newRow][newCol])) {
                    backtracking(newRow, newCol, currNode);
                }
            }

            // 结束探索，恢复原来的字母
            this._board[row][col] = letter;

            // 优化：逐步移除叶节点
            if (currNode.children.isEmpty()) {
                parent.children.remove(letter);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_212_WordSearchIi().new Solution();
        // 测试用例
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> result = solution.findWords(board, words);
        System.out.println(result); // 输出 ["oath", "eat"]
    }
}

/**
Given an m x n board of characters and a list of strings words, return all 
words on the board. 

 Each word must be constructed from letters of sequentially adjacent cells, 
where adjacent cells are horizontally or vertically neighboring. The same letter 
cell may not be used more than once in a word. 

 
 Example 1: 
 
 
Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f",
"l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
 

 Example 2: 
 
 
Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []
 

 
 Constraints: 

 
 m == board.length 
 n == board[i].length 
 1 <= m, n <= 12 
 board[i][j] is a lowercase English letter. 
 1 <= words.length <= 3 * 10⁴ 
 1 <= words[i].length <= 10 
 words[i] consists of lowercase English letters. 
 All the strings of words are unique. 
 

 Related Topics Array String Backtracking Trie Matrix 👍 9366 👎 456

*/
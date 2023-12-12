package leetcode.frequent.dfs;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *@Question:  212. Word Search II
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 63.61%
 *@Time  Complexity: O(M(4*3^(L-1))) M is the number of cells in the board and L is the maximum length of words.
 *@Space Complexity: O(n)
 */

/**
 * 算法思路：
 *
 * 使用Trie树存储所有待匹配的单词。
 * 遍历字符矩阵中的每个单元格，检查是否有以该字符开头的单词，如果有，进行回溯。
 * 回溯过程中，使用Trie树进行匹配，同时将匹配的单词存储在结果中。
 * 为了防止重复匹配，每次成功匹配一个单词后，将对应的Trie树节点的word字段置为空。
 * 时间复杂度：
 *
 * 假设字符矩阵中有 M 个单元格，Trie树中有 N 个节点，L 为最大单词长度：
 *
 * 构建Trie树的时间复杂度
 */

public class LeetCode_212_WordSearchIi{

    //leetcode submit region begin(Prohibit modification and deletion)
//    @ToString
    class TrieNode {
        // 一个HashMap，用于存储当前节点的子节点，key为字符，value为对应的节点

        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        // 如果该节点是一个单词的结束节点，将单词存储在这里
        String word = null;
        public TrieNode() {}

//        public String toString(){
//            return "\tTrieNode{\n" + "\tword" + "=" + word + ",\n"+ "\tchildren" + children.toString();
//        }
    }

    class Solution {
        // 用于存储字符矩阵
        char[][] _board = null;
        // 用于存储最终结果
        ArrayList<String> _result = new ArrayList<String>();

        public List<String> findWords(char[][] board, String[] words) {

            // Step 1). 构建Trie树
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode node = root;

                // 将单词中的每个字符插入Trie树
                for (Character letter : word.toCharArray()) {
                    if (node.children.containsKey(letter)) {
                        node = node.children.get(letter);
                    } else {
                        TrieNode newNode = new TrieNode();
                        node.children.put(letter, newNode);
                        node = newNode;
                    }
                }
                node.word = word;  // 将单词存储在Trie树中
            }
            System.out.println(root.toString());

            this._board = board;
            // Step 2). 从矩阵中的每个单元格开始进行回溯
            for (int row = 0; row < board.length; ++row) {
                for (int col = 0; col < board[row].length; ++col) {
                    // 如果当前字符在Trie树的根节点的子节点中存在，进行回溯
                    if (root.children.containsKey(board[row][col])) {
                        backtracking(row, col, root);
                    }
                }
            }

            return this._result;
        }

        // 回溯算法
        private void backtracking(int row, int col, TrieNode parent) {
            Character letter = this._board[row][col];
            TrieNode currNode = parent.children.get(letter);

            // 检查是否存在匹配的单词
            if (currNode.word != null) {
                this._result.add(currNode.word);
                currNode.word = null;
            }

            // 在"探索"之前，标记当前字符
            this._board[row][col] = '#';

            // 在上、右、下、左四个方向上探索相邻的单元格
            int[] rowOffset = {-1, 0, 1, 0};
            int[] colOffset = {0, 1, 0, -1};
            for (int i = 0; i < 4; ++i) {
                int newRow = row + rowOffset[i];
                int newCol = col + colOffset[i];
                if (newRow < 0 || newRow >= this._board.length || newCol < 0
                        || newCol >= this._board[0].length) {
                    continue;
                }
                // 如果Trie树中存在当前字符的子节点，进行递归回溯
                if (currNode.children.containsKey(this._board[newRow][newCol])) {
                    backtracking(newRow, newCol, currNode);
                }
            }

            // "探索"结束后，恢复矩阵中的原始字符
            this._board[row][col] = letter;

            // 优化：逐步删除叶子节点
            if (currNode.children.isEmpty()) {
                parent.children.remove(letter);
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_212_WordSearchIi.Solution solution = new LeetCode_212_WordSearchIi().new Solution();

        // 测试用例1
        char[][] board1 = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words1 = {"oath", "pea","peach", "eat", "rain"};
        List<String> result1 = solution.findWords(board1, words1);
        System.out.println("Test Case 1: " + result1);

        // 测试用例2
        char[][] board2 = {
                {'a', 'b'},
                {'c', 'd'}
        };
        String[] words2 = {"abcb"};
        List<String> result2 = solution.findWords(board2, words2);
        System.out.println("Test Case 2: " + result2);
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


 Related Topics Array String Backtracking Trie Matrix 👍 9058 👎 426

 */

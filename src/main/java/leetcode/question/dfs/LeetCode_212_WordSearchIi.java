package leetcode.question.dfs;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *@Question:  212. Word Search II
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 63.61%
 *@Time  Complexity: O(M(4*3^(L-1))) M is the number of cells in the board and L is the maximum length of words.
 * 从任意一个起点字符开始 DFS 时，你可以选择 4 个方向,从第二步开始，由于我们不能往回走（即不能返回上一个字符所在的位置），
 * 所以在 4 个方向中，有一个方向是不允许的（回头的方向），仅剩下 3 个方向可以扩展。
 *@Space Complexity: O(n)
 */

/**
 * 算法思路：
 *
 * 使用Trie树存储所有待匹配的单词。
 * 遍历字符矩阵中的每个单元格，检查是否有以该字符开头的单词，如果有，进行回溯。
 * 回溯过程中，使用Trie树进行匹配，同时将匹配的单词存储在结果中。
 * 为了防止重复匹配，每次成功匹配一个单词后，将对应的Trie树节点的word字段置为空。
 * 即使没共享前缀，Trie 仍能在 DFS 时快速判断：
 * 当前路径不是任何单词前缀 → 立刻停止回溯（剪掉无效路径）
 *
 * 好的—用中英混合把 **“Word Search II 中当 words 彼此没有公共前缀时会发生什么、为何 Trie+回溯仍有效、复杂度如何变化、实现要点”** 讲清楚👇
 *
 * ---
 *
 * ## 1) Scenario 场景设定
 *
 * **No common prefix（无公共前缀）**：
 *
 * ```text
 * words = ["dog", "cat", "sun", "tree"]
 * ```
 *
 * 每个单词从首字母开始就分道扬镳。
 * In the Trie, the root will have disjoint children:
 *
 * ```
 * root
 *  ├─ d → o → g
 *  ├─ c → a → t
 *  ├─ s → u → n
 *  └─ t → r → e → e
 * ```
 *
 * **Board 示例**（随意举个 4×4）：
 *
 * ```
 * d o x x
 * x a t x
 * x x u n
 * t r e e
 * ```
 *
 * ---
 *
 * ## 2) Why Trie still helps 即便无前缀，Trie 仍然有用
 *
 * ### (a) One traversal for all words 单次全局 DFS（不为每个词重复扫图）
 *
 * * **Naive per-word DFS**：对每个词都从所有格子起点试一次 → `O(K * M * 3^L)`
 *   (`K`=单词数, `M`=board cells, `L`=最长词长)
 * * **Trie + Backtracking**：从每个格子只启动一次 DFS，但**受 Trie 引导**
 *   → 只探索“是某个单词前缀”的路径，**不会为每个词重复扫同一路径**。
 *
 * **Intuition**：把“对每个词做 DFS”的重复工作，折叠为“对所有词共享的前缀空间做一次 DFS”。
 * 即便首字母不同，起码**每个起点只要 O(1) 检查是否存在对应的根孩子**（例如：`board[r][c] == 'd'` 才可能走到 `"dog"` 的分支）。
 *
 * ---
 *
 * ### (b) Early pruning 提前剪枝（Fail fast）
 *
 * * When a path does **not** match any prefix in Trie, **stop immediately**.
 *   不是前缀→立刻返回，**减少分支因子**。
 * * 这在 random board 或“字母噪声大”的情况下非常显著：越早判“不是前缀”，越少回溯。
 *
 * ---
 *
 * ### (c) De-dup & Lazy deletion 去重与懒删除
 *
 * * 找到单词：`node.word != null` → add to result, then set `node.word = null`
 *   防止重复加入（多条路径命中同一词）。
 * * 子树用尽：`if (curr.children.isEmpty()) parent.children.remove(letter);`
 *   **Lazy prune** 整个无用分支，后续 DFS 不再经过它，速度进一步提升。
 *   👉 这就解释了**为什么回溯代码里要带 `parent`**。
 *
 * ---
 *
 * ## 3) Time complexity 复杂度直觉与边界
 *
 * ### 官方上界（有无公共前缀都成立）
 *
 * `O(M * (4 * 3^(L-1)))`
 *
 * * `M` = cells in board
 * * `L` = max word length
 * * `4 * 3^(L-1)`：首步4个方向、之后每步最多3个（不可回头）
 *
 * > 这个上界与是否共享前缀**无关**，是由“图上 DFS 的形态 + 不走回头路”决定的。
 *
 * ### 那么“无公共前缀”会不会退化到乘以 `K`？
 *
 * * **不会直接变成 `K` 倍**，因为你**不是**“为每个词再扫一遍图”，而是**一次 DFS 由 Trie 决定能否深入**。
 * * 但**prefix-sharing 带来的路径复用减少了**：一旦**首字母都不同**，从某个起点字母能走的根分支就只有一个（甚至为零），**初始复用收益变小**。
 * * 实战里，性能更多取决于：
 *
 *   * board 上每个起点字母是否存在于根分支（快速 fail）
 *   * 单词的深度能走多远（前缀是否在 board 中可行）
 *   * 剪枝是否及时（`node.word=null` 与懒删除）
 *
 * **对比**：
 *
 * * **朴素 per-word**：`O(K * M * 3^L)`（确实乘以 `K`）
 * * **Trie+DFS（无公共前缀）**：仍按 `O(M * 4 * 3^(L-1))` 上界估，但常数项增大（少了前缀复用），**远优于 per-word** 的 `×K` 量级。
 *
 * ---
 *
 * ## 4) Micro walk-through 走查示例（直观对比）
 *
 * 以 `board[0][0] = 'd'` 为例：
 *
 * * **Trie**：先看 `root.children['d']` 是否存在。存在→才向 `'o'`、`'g'` 深入；否则**立即返回**。
 * * **Per-word**：你会为 "dog" 再跑一次起点是 `'d'` 的 DFS；为 "cat" 再跑一次（首字母不等，很快失败但也要检查）；为 "sun" 再跑一次；为 "tree" 再跑一次……
 *   👉 这就是 `×K` 的额外开销。
 *
 * 当你在 `(1,1) = 'a'` 处：
 *
 * * Trie 只会考虑 `'c' → 'a' → 't'` 的延展是否成立；
 * * Per-word 则会“对每个词都来试试”，哪怕首字母不对也要走一遍判断逻辑。
 *
 * ---
 *
 * ## 5) Engineering tips 工程落地要点
 *
 * * **Set `node.word = null` after found**：去重 & 少一次判断
 * * **Lazy prune empty child**：减少后续搜索树规模
 * * **In-place visited mark（board[r][c] = '#'）**：省 `visited[][]` 内存 & cache 友好
 * * **（可选）char frequency pre-check**：若某词需要的某字符在全局频次不足，直接不建这条 Trie 分支
 *
 * ---
 *
 * ## 6) TL;DR 总结
 *
 * * **Even without shared prefixes**, Trie+Backtracking 仍**统一了一次遍历**，让“是否深入”由 Trie 决定，避免为每个词重复全图 DFS。
 * * 上界仍是 `O(M * (4 * 3^(L-1)))`，只是**常数因子**可能比“有公共前缀”时略大（复用变少）。
 * * 对比朴素 per-word 的 `O(K * M * 3^L)`：**Trie 解法不会多出一个 `×K`**，因此整体**依旧显著更快**。
 *
 * 如果你想，我可以把这两种方案写成可运行的基准测试（JMH/简单计时），在相同 board/words 下对比耗时，直观看到“无前缀 vs 有前缀”的性能差异。
 * 时间复杂度：
 *
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

        public String toString(){
            return "\tTrieNode{\n" + "\tword" + "=" + word + ",\n"+ "\tchildren" + children.toString();
        }
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
            //假设 Trie 里有单词 "apple", "app", "apply"，当 "app" 匹配完成后：
            //我们把 currNode.word = null（防止重复加入结果）
            //然后继续 DFS，如果发现 currNode 没有 children（变成叶子节点），就从 parent.children 里删掉该字符分支
            //➜ 这就是为何需要传入 parent
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
//            Because在找到一个完整单词后，我们需要从 Trie 树中删除对应路径以减少后续搜索工作（避免重复匹配和无效遍历），
//            而删除一个 Trie 节点需要访问它的父节点，所以在 backtracking 过程中必须带上下一个 parent reference
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

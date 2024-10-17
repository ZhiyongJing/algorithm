package interview.company.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
// Trie 节点的定义
class Node {
    boolean isWord = false;
    double frequency;
    List<Node> children = Arrays.asList(new Node[26]);
};

class Trie {
    Node Root, curr;
    Trie() {
        Root = new Node();
    }
    // 初始化堆，按照出现频率从小到大排列
    Queue<Node> resultBuffer = new PriorityQueue<>(
            (n1, n2) -> count.get(n2.frequency) - count.get(n1.frequency));

    // 运行一个深度优先搜索（DFS）在 Trie 上，从给定前缀开始，并将所有单词添加到 resultBuffer 中，限制结果大小为 10
    void dfsWithPrefix(Node curr, String word) {
        if (resultBuffer.size() == 10)
            return;
        if (curr.isWord)
            resultBuffer.add(word);

        // 在所有可能的路径上运行 DFS。
        for (char c = 'a'; c <= 'z'; c++)
            if (curr.children.get(c - 'a') != null)
                dfsWithPrefix(curr.children.get(c - 'a'), word + c);
    }


    // 在 Trie 中插入字符串
// 思路：按照word的字符，从根节点开始，一直向下走：如果遇到null，就new出新节点；如果节点已经存在，cur顺着往下走就可以
    void insert(String s) {
        curr = Root;	// 将 curr 指针指向 Trie 的根节点。
        for (char c : s.toCharArray()) {
            if (curr.children.get(c - 'a') == null)
                curr.children.set(c - 'a', new Node());
            curr = curr.children.get(c - 'a');
        }
        curr.isWord = true;   // 将该节点标记为一个完成的单词。
    }
    //【判断一个单词word是否完整存在于字典树中】
    // 思路：cur从根节点开始，按照word的字符一直尝试向下走：
    //          如果走到了null，说明这个word不是前缀树的任何一条路径，返回false;
    //          如果按照word顺利的走完，就要判断此时cur是否为单词尾端：如果是，返回true；如果不是，说明word仅仅是一个前缀，并不完整，返回false
    public boolean search(String word) {
        cur = root;

        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (cur.children[c] == null) {
                return false;
            }
            cur = cur.children[c];
        }
        return cur.isWord;
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
}}
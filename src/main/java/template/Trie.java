package template;

public class Trie {

    private Node root; // 根节点

    // 定义前缀树的节点
    private class Node {
        private Node[] dict; // 使用数组存储子节点，每个节点代表一个字母
        private boolean isWord; // 标记当前节点是否为单词的结尾

        // 构造函数，初始化子节点数组和单词标记
        public Node() {
            dict = new Node[26]; // 数组长度为26，对应字母 a-z
            this.isWord = false;
        }
    }

    /**
     * 初始化 Trie（前缀树）。
     */
    public Trie() {
        root = new Node(); // 初始化根节点
    }

    /**
     * 插入一个单词到 Trie 中。
     */
    public void insert(String word) {
        int len = word.length(); // 单词长度
        Node curNode = root; // 从根节点开始
        for (int i = 0; i < len; i++) {
            char curChar = word.charAt(i); // 当前字符
            Node next = curNode.dict[curChar - 'a']; // 根据字符找到对应子节点
            if (next == null) {
                // 如果当前字符对应的子节点不存在，则创建新的节点
                curNode.dict[curChar - 'a'] = new Node();
            }
            curNode = curNode.dict[curChar - 'a']; // 进入下一个节点
        }
        if (!curNode.isWord) {
            // 如果当前节点之前不是单词结尾，标记为单词结尾
            curNode.isWord = true;
        }
    }

    /**
     * 判断单词是否存在于 Trie 中。
     */
    public boolean search(String word) {
        int len = word.length(); // 单词长度
        Node curNode = root; // 从根节点开始

        for (int i = 0; i < len; i++) {
            char curC = word.charAt(i); // 当前字符
            Node next = curNode.dict[curC - 'a']; // 根据字符找到对应子节点
            if (next == null) {
                // 如果子节点不存在，返回 false
                return false;
            } else {
                curNode = next; // 进入下一个节点
            }
        }
        return curNode.isWord; // 判断当前节点是否为单词结尾
    }

    /**
     * 判断是否存在以指定前缀开头的单词。
     */
    public boolean startsWith(String prefix) {
        int len = prefix.length(); // 前缀长度
        Node curNode = root; // 从根节点开始
        for (int i = 0; i < len; i++) {
            char curC = prefix.charAt(i); // 当前字符
            Node next = curNode.dict[curC - 'a']; // 根据字符找到对应子节点
            if (next == null) {
                // 如果子节点不存在，返回 false
                return false;
            } else {
                curNode = next; // 进入下一个节点
            }
        }
        return true; // 如果遍历完整个前缀，返回 true
    }

    /**
     * 判断是否存在匹配正则表达式 `.` 的单词。
     * '.' 表示可以匹配任何一个字符。
     */
    public boolean regExSearch(String word) {
        return match(word, root, 0); // 调用递归函数进行匹配
    }

    private boolean match(String word, Node node, int start) {
        if (start == word.length()) {
            return node.isWord; // 如果到达单词末尾，检查是否为单词结尾
        }
        char alpha = word.charAt(start);
        if (alpha == '.') {
            // 如果当前字符为 '.'，遍历所有可能的子节点
            for (int i = 0; i < 26; i++) {
                if (node.dict[i] != null && match(word, node.dict[i], start + 1)) {
                    return true;
                }
            }
            return false; // 如果所有子节点都不匹配，返回 false
        } else {
            // 普通字符处理
            if (node.dict[alpha - 'a'] == null) {
                return false; // 如果子节点不存在，返回 false
            }
            return match(word, node.dict[alpha - 'a'], start + 1); // 递归匹配下一个字符
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie(); // 创建 Trie 对象
        trie.insert("helloworld"); // 插入单词 "helloworld"
        trie.insert("bad");        // 插入单词 "bad"
        trie.insert("dag");        // 插入单词 "dag"
        trie.insert("mad");        // 插入单词 "mad"

        // 测试是否存在以 "hello" 开头的单词
        boolean startsWith = trie.startsWith("hello");
        System.out.println(startsWith); // 输出 true

        // 测试是否存在单词 "helloworld"
        boolean search1 = trie.search("helloworld");
        System.out.println(search1); // 输出 true

        // 测试是否存在单词 "hello"
        boolean search2 = trie.search("hello");
        System.out.println(search2); // 输出 false

        // 测试正则表达式匹配
        boolean search3 = trie.regExSearch(".ad");
        System.out.println(search3); // 输出 true (匹配 "bad", "mad")

        boolean search4 = trie.regExSearch("b..");
        System.out.println(search4); // 输出 true (匹配 "bad")

        boolean search5 = trie.regExSearch(".elloworl.");
        System.out.println(search5); // 输出 true (匹配 "helloworld")
    }
}

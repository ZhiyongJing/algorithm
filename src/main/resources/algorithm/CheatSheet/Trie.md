字典树 Trie

## 使用条件

- 需要查询包含某个前缀的单词 / 字符串是否存在
- 字符矩阵中找单词的问题

## 复杂度

- 时间复杂度：O(L)*O*(*L*) 增删查改
- 空间复杂度：O(N∗L)*O*(*N*∗*L*) N 是单词书、L 是单词长度

## 代码模板

java

```java
1class TrieNode {
2    // 儿子节点
3    public Map<Character, TrieNode> children;
4    // 根节点到该节点是否是一个单词
5    public boolean isWord;
6    // 根节点到该节点的单词是什么
7    public String word;
8
9    public TrieNode() {
10        children = new HashMap<Character, TrieNode>();
11        isWord = false;
12        word = null;
13    }
14
15}
16
17public class Trie {
18    private TrieNode root;
19
20    public Trie() {
21        root = new TrieNode();
22    }
23
24    public TrieNode getRoot() {
25        return root;
26    }
27
28    // 插入单词
29    public void insert(String word) {
30        TrieNode node = root;
31        for (int i = 0; i < word.length(); i++) {
32            char letter = word.charAt(i);
33            if (!node.children.containsKey(letter)) {
34                node.children.put(letter, new TrieNode());
35            }
36            node = node.children.get(letter);
37        }
38        node.isWord = true;
39        node.word = word;
40    }
41
42    // 判断单词 word 是不是在字典树中
43    public boolean hasWord(String word) {
44        int L = word.length();
45        TrieNode node = root;
46        for (int i = 0; i < L; i++) {
47            char letter = word.charAt(i);
48            if (!node.children.containsKey(letter)) {
49                return false;
50            }
51            node = node.children.get(letter);
52        }
53        return node.isWord;
54    }
55
56    // 判断前缀 prefix 是不是在字典树中
57    public boolean hasPrefix(String prefix) {
58        int L = prefix.length();
59        TrieNode node = root;
60        for (int i = 0; i < L; i++) {
61            char letter = prefix.charAt(i);
62            if (!node.children.containsKey(letter)) {
63                return false;
64            }
65            node = node.children.get(letter);
66        }
67        return true;
68    }
69
70}
```
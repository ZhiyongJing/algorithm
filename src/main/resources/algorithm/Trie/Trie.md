# 1. 算法思想

> - 在[计算机科学](https://link.juejin.cn/?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6)中，**trie**，又称**前缀树**或**字典树**，是一种有序[树](https://link.juejin.cn/?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E6%A0%91_(%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84))，用于保存[关联数组](https://link.juejin.cn/?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E5%85%B3%E8%81%94%E6%95%B0%E7%BB%84)，其中的键通常是[字符串](https://link.juejin.cn/?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E5%AD%97%E7%AC%A6%E4%B8%B2)。与[二叉查找树](https://link.juejin.cn/?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E4%BA%8C%E5%8F%89%E6%9F%A5%E6%89%BE%E6%A0%91)不同，键不是直接保存在节点中，而是由节点在树中的位置决定。一个节点的所有子孙都有相同的[前缀](https://link.juejin.cn/?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E5%89%8D%E7%BC%80)，也就是这个节点对应的字符串，而根节点对应[空字符串](https://link.juejin.cn/?target=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E7%A9%BA%E5%AD%97%E7%AC%A6%E4%B8%B2)。一般情况下，不是所有的节点都有对应的值，只有叶子节点和部分内部节点所对应的键才有相关的值。


# 2. 算法适用场景

> - 可以解决的问题
>
>   > - 需要查询包含某个前缀的单词 / 字符串是否存在
>   > - 字符矩阵中找单词的问题
>

# 3. 算法模版

## 3.1 实现

> ![image-20240613154851638](Trie.assets/image-20240613154851638.png)
>
> ```java
> // Trie 节点的定义
> class Node {
>    boolean isWord = false;
>  	 double frequency;
>    List<Node> children = Arrays.asList(new Node[26]);
> };
> 
> class Trie {
>   Node Root, curr;
> 	Trie() {
>    	Root = new Node();
> 	}
> // 初始化堆，按照出现频率从小到大排列
>    Queue<Node> resultBuffer = new PriorityQueue<>(
>            (n1, n2) -> count.get(n2.frequency) - count.get(n1.frequency));
> 
> // 运行一个深度优先搜索（DFS）在 Trie 上，从给定前缀开始，并将所有单词添加到 resultBuffer 中，限制结果大小为 10
> void dfsWithPrefix(Node curr, String word) {
>    if (resultBuffer.size() == 10)
>        return;
>    if (curr.isWord)
>        resultBuffer.add(word);
> 
>    // 在所有可能的路径上运行 DFS。
>    for (char c = 'a'; c <= 'z'; c++)
>        if (curr.children.get(c - 'a') != null)
>            dfsWithPrefix(curr.children.get(c - 'a'), word + c);
> }
> 
> 
> // 在 Trie 中插入字符串
> // 思路：按照word的字符，从根节点开始，一直向下走：如果遇到null，就new出新节点；如果节点已经存在，cur顺着往下走就可以
> void insert(String s) {
>    curr = Root;	// 将 curr 指针指向 Trie 的根节点。
>    for (char c : s.toCharArray()) {
>        if (curr.children.get(c - 'a') == null)
>            curr.children.set(c - 'a', new Node());
>        curr = curr.children.get(c - 'a');
>    }
>    curr.isWord = true;   // 将该节点标记为一个完成的单词。
> }
>       //【判断一个单词word是否完整存在于字典树中】
>     // 思路：cur从根节点开始，按照word的字符一直尝试向下走：
>     // 如果走到了null，说明这个word不是前缀树的任何一条路径，返回false;
>     // 如果按照word顺利的走完，就要判断此时cur是否为单词尾端：如果是，返回true；如果不是，说明word仅仅是一个前缀，并不完整，返回false
>     public boolean search(String word) {
>         cur = root;
> 
>         for (int i = 0; i < word.length(); i++) {
>             int c = word.charAt(i) - 'a';
>             if (cur.children[c] == null) {
>                 return false;
>             }
>             cur = cur.children[c];
>         }
>         return cur.isWord;
>     }
> 
>   
> 
> // 获取以指定前缀开头的单词
> List<String> getWordsStartingWith(String prefix) {
>    curr = Root;
>    resultBuffer = new ArrayList<String>();
>    // 将 curr 移动到其 Trie 表示中前缀的末尾。
>    for (char c : prefix.toCharArray()) {
>        if (curr.children.get(c - 'a') == null)
>            return resultBuffer;
>        curr = curr.children.get(c - 'a');
>    }
>    dfsWithPrefix(curr, prefix);
>    return resultBuffer;
> }
> };
> 
> List<List<String>> suggestedProducts(String[] products, String searchWord) {
>    Trie trie = new Trie();
>    List<List<String>> result = new ArrayList<>();
>    // 将所有单词添加到 Trie 中。
>    for (String w : products)
>        trie.insert(w);
>    String prefix = new String();
>    for (char c : searchWord.toCharArray()) {
>        prefix += c;
>        result.add(trie.getWordsStartingWith(prefix));
>    }
>    return result;
> }}
> ```
>
> 



# 4. 时间复杂度

> - 时间复杂度：
>   - 假设所有字符串长度之和为 `n`，构建字典树的时间复杂度为 `O(n)`
>   - 假设要查找的字符串长度为 `k`，查找的时间复杂度为 `O(k)`
> - 空间复杂度：O(N* L) N 是单词书、L 是单词长度
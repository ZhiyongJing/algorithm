package leetcode.question.trie;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  1166. Design File System
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 62.96000000000001%
 *@Time  Complexity: O(N) to add/find a path in the trie if it contains N components.
 *@Space Complexity: O(N) for createpath, O(1) for get
 */

/**
 * ### 解题思路
 *
 * #### Trie 数据结构
 *
 * Trie（前缀树）是一种树形数据结构，特别适合用于存储和搜索字符串集合。在本问题中，我们使用 Trie 来构建一个文件系统，支持两种操作：创建路径并设置值、获取路径对应的值。
 *
 * 1. **TrieNode 类**：
 *    - 每个 TrieNode 节点包含以下几个重要的属性：
 *      - `name`：节点的名称，表示路径的每一级目录或文件名。
 *      - `val`：节点存储的值，对于文件系统而言，这个值代表文件或目录的内容或属性。
 *      - `map`：子节点的映射，将子节点的名称映射到对应的 TrieNode 对象。
 *
 * 2. **FileSystem 类**：
 *    - 文件系统的根节点 `root` 是一个特殊的 TrieNode，其名称为空字符串。
 *    - **createPath 方法**：
 *      - 将给定的路径按照 `/` 分割成组件。
 *      - 从根节点开始遍历每个组件，依次检查当前节点的子节点映射。
 *      - 如果某一级目录不存在，则创建新的 TrieNode 添加到当前节点的子节点映射中。
 *      - 如果到达路径的最后一个组件，则将最后一个节点的值设置为给定的值。
 *      - 如果路径已经存在（即最后一个节点的值不为默认值 -1），则返回 false；否则返回 true。
 *
 *    - **get 方法**：
 *      - 将给定的路径按照 `/` 分割成组件。
 *      - 从根节点开始遍历每个组件，依次检查当前节点的子节点映射。
 *      - 如果某一级目录不存在，则返回 -1 表示路径不存在。
 *      - 如果所有组件都存在，则返回路径的最后一个节点的值。
 *
 * ### 时间复杂度
 *
 * - **创建路径 `createPath` 方法**：
 *   - 分割路径为组件的操作是 O(N)，其中 N 是路径中的组件数。
 *   - 插入或更新 TrieNode 的操作也是 O(N)，因为需要遍历每个组件并在 Trie 中定位或创建节点。
 *
 * - **获取值 `get` 方法**：
 *   - 分割路径为组件的操作是 O(N)，其中 N 是路径中的组件数。
 *   - 查询 TrieNode 的操作也是 O(N)，因为需要遍历每个组件并在 Trie 中定位节点。
 *
 * 综上所述，操作的时间复杂度取决于路径中的组件数，即 O(N)，其中 N 是路径中的组件数。
 *
 * ### 空间复杂度
 *
 * - Trie 的空间复杂度取决于存储的路径和值的数量。对于每个存储的路径，都会创建相应的 TrieNode 节点。
 * - 如果存在大量相似前缀的路径，Trie 的空间复杂度可能会相对较高。
 * - 空间复杂度主要受 TrieNode 节点数量的影响，因此是 O(N)，其中 N 是 TrieNode 的数量。
 */
public class LeetCode_1166_DesignFileSystem{

    //leetcode submit region begin(Prohibit modification and deletion)
    class FileSystem {

        // 文件系统的 TrieNode 数据结构
        class TrieNode {

            String name; // 节点名称
            int val = -1; // 存储的值，默认为 -1
            Map<String, TrieNode> map = new HashMap<>(); // 子节点映射

            TrieNode (String name) {
                this.name = name;
            }
        }

        TrieNode root; // 根节点

        // 文件系统的构造函数，根节点包含空字符串
        public FileSystem() {
            this.root = new TrieNode("");
        }

        // 创建路径并设置值
        public boolean createPath(String path, int value) {

            // 获取路径的所有组件
            String[] components = path.split("/");

            // 从根节点开始
            TrieNode cur = root;

            // 遍历所有路径组件
            for (int i = 1; i < components.length; i++) {

                String currentComponent = components[i];

                // 检查当前节点的子节点映射中是否包含当前组件
                if (!cur.map.containsKey(currentComponent)) {

                    // 如果不存在，并且当前组件是路径的最后一个组件，则将其添加到 Trie 中
                    if (i == components.length - 1) {
                        cur.map.put(currentComponent, new TrieNode(currentComponent));
                    } else {
                        return false;
                    }
                }

                cur = cur.map.get(currentComponent);
            }

            // 如果节点的值不为 -1，说明路径已经存在
            if (cur.val != -1) {
                return false;
            }

            // 设置节点的值为给定值
            cur.val = value;
            return true;
        }

        // 获取路径对应的值
        public int get(String path) {

            // 获取路径的所有组件
            String[] components = path.split("/");

            // 从根节点开始
            TrieNode cur = root;

            // 遍历所有路径组件
            for (int i = 1; i < components.length; i++) {

                String currentComponent = components[i];

                // 检查当前节点的子节点映射中是否包含当前组件
                if (!cur.map.containsKey(currentComponent)) {
                    return -1;
                }

                cur = cur.map.get(currentComponent);
            }

            // 返回路径对应的值
            return cur.val;
        }
    }

    /**
     * Your FileSystem object will be instantiated and called as such:
     * FileSystem obj = new FileSystem();
     * boolean param_1 = obj.createPath(path,value);
     * int param_2 = obj.get(path);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_1166_DesignFileSystem.FileSystem fs = new LeetCode_1166_DesignFileSystem().new FileSystem();

        // 测试样例
        System.out.println(fs.createPath("/a", 1)); // true
        System.out.println(fs.get("/a")); // 1

        System.out.println(fs.createPath("/a/b", 2)); // true
        System.out.println(fs.get("/a/b")); // 2

        System.out.println(fs.createPath("/c/d", 3)); // true
        System.out.println(fs.get("/c")); // -1

        System.out.println(fs.createPath("/a/b/c", 4)); // false，路径 "/a/b" 已经存在
        System.out.println(fs.get("/a/b/c")); // -1
    }
}

/**
You are asked to design a file system that allows you to create new paths and 
associate them with different values. 

 The format of a path is one or more concatenated strings of the form: / 
followed by one or more lowercase English letters. For example, "/leetcode" and "/
leetcode/problems" are valid paths while an empty string "" and "/" are not. 

 Implement the FileSystem class: 

 
 bool createPath(string path, int value) Creates a new path and associates a 
value to it if possible and returns true. Returns false if the path already exists 
or its parent path doesn't exist. 
 int get(string path) Returns the value associated with path or returns -1 if 
the path doesn't exist. 
 

 
 Example 1: 

 
Input: 
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output: 
[null,true,1]
Explanation: 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1
 

 Example 2: 

 
Input: 
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output: 
[null,true,true,2,false,-1]
Explanation: 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" 
doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.
 

 
 Constraints: 

 
 2 <= path.length <= 100 
 1 <= value <= 10⁹ 
 Each path is valid and consists of lowercase English letters and '/'. 
 At most 10⁴ calls in total will be made to createPath and get. 
 

 Related Topics Hash Table String Design Trie 👍 571 👎 68

*/
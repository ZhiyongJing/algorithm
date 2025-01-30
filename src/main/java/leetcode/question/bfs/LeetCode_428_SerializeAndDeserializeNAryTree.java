package leetcode.question.bfs;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
  *@Question:  428. Serialize and Deserialize N-ary Tree
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 76.48%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

/*
 * 题目描述：
 * LeetCode 428 - 序列化和反序列化 N 叉树（Serialize and Deserialize N-ary Tree）
 *
 * 设计一个算法，实现 N 叉树的序列化和反序列化。
 * 1. **序列化（serialize）**：将 N 叉树转换成字符串格式，以便存储或传输。
 * 2. **反序列化（deserialize）**：将字符串解析回 N 叉树的结构，使其能够重建原始的树。
 *
 * **输入：**
 * - `root`：N 叉树的根节点。
 * **输出：**
 * - `String`（序列化结果）或 `Node`（反序列化结果）。
 *
 * **示例：**
 * ```
 * 输入：
 *       1
 *     / | \
 *    3  2  4
 *   / \
 *  5   6
 *
 * 序列化后字符串: "1#3$2$4#5$6"
 * 反序列化后树的根节点值: 1
 * ```
 */

/*
 * 解题思路：
 * 该问题是树的序列化和反序列化，适用于 **层次遍历（BFS）** 方法。
 *
 * **方法 1：层次遍历（BFS）**
 * ------------------------------------------------------
 * 1️⃣ **序列化**
 *    - 使用 **队列（Queue）** 进行 **层次遍历（BFS）**。
 *    - 使用 `#` 作为 **层结束标记**，`$` 作为 **子节点分隔标记**。
 *    - 遍历整个树，将 `Node` 的 `val` 依次存入字符串。
 *
 * **示例**
 * ```
 * 输入：
 *       1
 *     / | \
 *    3  2  4
 *   / \
 *  5   6
 *
 * 序列化步骤：
 * - 1#  （根节点 1，层结束）
 * - 3$2$4#  （子节点 3、2、4，层结束）
 * - 5$6  （子节点 5、6）
 *
 * 输出: "1#3$2$4#5$6"
 * ```
 * **时间复杂度：O(n)**
 * **空间复杂度：O(n)**（存储结果）
 *
 * ------------------------------------------------------
 * **方法 2：反序列化**
 * ------------------------------------------------------
 * 1️⃣ **反序列化**
 *    - 逐字符解析字符串。
 *    - `#` 表示层结束，切换到新的一层。
 *    - `$` 表示子节点切换。
 *    - 使用 **队列（Queue）** 维护上一层的节点信息，恢复 `children` 关系。
 *
 * **示例**
 * ```
 * 输入："1#3$2$4#5$6"
 * 解析步骤：
 * - 解析 `1` -> 根节点
 * - 解析 `3,2,4` -> 根的子节点
 * - 解析 `5,6` -> `3` 的子节点
 *
 * 输出:
 *       1
 *     / | \
 *    3  2  4
 *   / \
 *  5   6
 * ```
 * **时间复杂度：O(n)**
 * **空间复杂度：O(n)**（存储节点）
 *
 * ------------------------------------------------------
 * **时间和空间复杂度分析**
 *
 * **序列化**
 * - **时间复杂度：O(n)**（遍历所有节点）
 * - **空间复杂度：O(n)**（存储字符串结果）
 *
 * **反序列化**
 * - **时间复杂度：O(n)**（遍历所有字符还原树）
 * - **空间复杂度：O(n)**（存储树节点）
 *
 * **推荐方法**
 * | 方法 | 时间复杂度 | 空间复杂度 | 适用场景 |
 * |------|----------|----------|--------|
 * | **层次遍历（BFS）** | `O(n)` | `O(n)` | **适用于大规模树结构** |
 */


public class LeetCode_428_SerializeAndDeserializeNAryTree{

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    */

    class Codec {

        // 序列化N叉树为字符串
        public String serialize(Node root) {
            if (root == null) {
                return ""; // 如果根节点为空，返回空字符串
            }
            StringBuilder sb = new StringBuilder(); // 创建StringBuilder用于拼接序列化字符串
            this._serializeHelper(root, sb); // 调用辅助方法进行序列化
            return sb.toString(); // 返回序列化后的字符串
        }

        // 递归辅助方法进行序列化
        private void _serializeHelper(Node root, StringBuilder sb) {
            Queue<Node> q = new LinkedList<>(); // 使用队列进行层次遍历

            // 特殊标记符
            Node endNode = new Node(); // 层结束标志
            Node childNode = new Node(); // 子节点之间的标志

            q.add(root); // 先加入根节点
            q.add(endNode); // 标记第一层结束

            while (!q.isEmpty()) {
                Node node = q.poll(); // 获取队列头部的节点

                if (node == endNode) {
                    sb.append('#'); // 记录层结束
                    if (!q.isEmpty()) {
                        q.add(endNode); // 继续标记下一层结束
                    }
                } else if (node == childNode) {
                    sb.append('$'); // 记录子节点分隔符
                } else {
                    sb.append((char) (node.val + '0')); // 存储当前节点的值
                    for (Node child : node.children) {
                        q.add(child); // 加入子节点
                    }
                    if (q.peek() != endNode) {
                        q.add(childNode); // 如果本层还未结束，标记子节点
                    }
                }
            }
            System.out.println("serialize: "+sb);
        }

        // 反序列化字符串为N叉树
        public Node deserialize(String data) {
            if (data.isEmpty()) {
                return null; // 如果输入字符串为空，返回空树
            }

            Node rootNode = new Node(data.charAt(0) - '0', new ArrayList<>()); // 根节点
            this._deserializeHelper(data, rootNode); // 调用辅助方法构造树
            return rootNode; // 返回根节点
        }

        // 递归辅助方法进行反序列化
        private void _deserializeHelper(String data, Node rootNode) {
            LinkedList<Node> currentLevel = new LinkedList<>(); // 当前层节点队列
            LinkedList<Node> prevLevel = new LinkedList<>(); // 前一层节点队列
            currentLevel.add(rootNode); // 初始化根节点
            Node parentNode = rootNode; // 初始父节点

            // 遍历序列化字符串
            for (int i = 1; i < data.length(); i++) {
                char d = data.charAt(i);
                if (d == '#') { // 层结束标志
                    prevLevel = currentLevel; // 交换当前层和前一层
                    currentLevel = new LinkedList<>();
                    parentNode = prevLevel.poll(); // 更新父节点
                } else {
                    if (d == '$') { // 兄弟节点分隔
                        parentNode = prevLevel.poll(); // 切换到下一个父节点
                    } else {
                        Node childNode = new Node(d - '0', new ArrayList<>()); // 创建子节点
                        currentLevel.add(childNode); // 添加到当前层队列
                        parentNode.children.add(childNode); // 添加子节点到父节点
                    }
                }
            }
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Codec codec = new LeetCode_428_SerializeAndDeserializeNAryTree().new Codec();

        // 创建测试用例：N 叉树
        Node root = new Node(1, new ArrayList<>());
        Node child1 = new Node(3, new ArrayList<>());
        Node child2 = new Node(2, new ArrayList<>());
        Node child3 = new Node(4, new ArrayList<>());
        Node grandchild1 = new Node(5, new ArrayList<>());
        Node grandchild2 = new Node(6, new ArrayList<>());

        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);
        child1.children.add(grandchild1);
        child1.children.add(grandchild2);

        // 测试序列化
        String serializedData = codec.serialize(root);
        System.out.println("序列化后的字符串: " + serializedData);

        // 测试反序列化
        Node deserializedRoot = codec.deserialize(serializedData);
        System.out.println("反序列化后的树根节点值: " + deserializedRoot.val);
    }
}

/**
Serialization is the process of converting a data structure or object into a 
sequence of bits so that it can be stored in a file or memory buffer, or 
transmitted across a network connection link to be reconstructed later in the same or 
another computer environment. 

 Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree 
is a rooted tree in which each node has no more than N children. There is no 
restriction on how your serialization/deserialization algorithm should work. You 
just need to ensure that an N-ary tree can be serialized to a string and this 
string can be deserialized to the original tree structure. 

 For example, you may serialize the following 3-ary tree 
 
 

 as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily 
need to follow this format. 

 Or you can follow LeetCode's level order traversal serialization format, where 
each group of children is separated by the null value. 
 
 

 For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,
null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]. 

 You do not necessarily need to follow the above-suggested formats, there are 
many more different formats that work so please be creative and come up with 
different approaches yourself. 

 
 Example 1: 

 
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,1
2,null,13,null,null,14]
Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
null,13,null,null,14]
 

 Example 2: 

 
Input: root = [1,null,3,2,4,null,5,6]
Output: [1,null,3,2,4,null,5,6]
 

 Example 3: 

 
Input: root = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 10⁴]. 
 0 <= Node.val <= 10⁴ 
 The height of the n-ary tree is less than or equal to 1000 
 Do not use class member/global/static variables to store states. Your encode 
and decode algorithms should be stateless. 
 

 Related Topics String Tree Depth-First Search Breadth-First Search 👍 1054 👎 5
7

*/
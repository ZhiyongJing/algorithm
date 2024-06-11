package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *@Question:  428. Serialize and Deserialize N-ary Tree
 *@Difficulty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 38.29%
 *@Time Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * 理解这道题的关键在于将 N 叉树序列化和反序列化为一个字符串。序列化是将树转换为一个字符串表示，而反序列化则是将这个字符串还原为原始树结构。
 *
 * **序列化思路**：
 * 1. 我们使用广度优先搜索（BFS）来遍历树，以确保我们按层级顺序访问树的每个节点。
 * 2. 对于每个节点，我们首先将其值记录到字符串中。然后，为了标记树的结构，我们需要使用特殊字符来表示当前层的结束以及节点之间的关系。在这里，我们可以选择使用 `#` 表示当前层的结束，用 `$` 表示切换到下一个父节点的孩子。
 * 3. 最终，我们得到的字符串就是树的序列化结果。
 *
 * **反序列化思路**：
 * 1. 对于反序列化过程，我们需要根据序列化字符串重新构建原始的树结构。
 * 2. 我们从字符串的第一个字符开始，逐个处理每个字符，根据字符的值来确定是什么节点，以及如何连接它们。
 * 3. 我们使用两个链表来分别存储当前层和前一层的节点，以便于构建孩子节点的连接关系。
 *
 * **时间复杂度**：
 * - 序列化和反序列化过程都需要访问树的每个节点，因此时间复杂度都是 **O(N)**，其中 **N** 是树的节点总数。
 *
 * **空间复杂度**：
 * - 在序列化过程中，我们需要使用额外的空间来存储字符串表示，因此空间复杂度为 **O(N)**，其中 **N** 是字符串的长度。
 * - 在反序列化过程中，我们使用两个链表来存储节点，空间复杂度也是 **O(N)**。
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

        // 序列化树为单个字符串
        public String serialize(Node root) {
            if (root == null) {
                return ""; // 如果根节点为空，返回空字符串
            }
            StringBuilder sb = new StringBuilder(); // 创建一个StringBuilder来构建序列化字符串
            this._serializeHelper(root, sb); // 调用辅助方法进行序列化
            return sb.toString(); // 返回序列化字符串
        }

        // 辅助方法进行序列化
        private void _serializeHelper(Node root, StringBuilder sb) {
            Queue<Node> q = new LinkedList<Node>(); // 队列用于层次遍历树

            // 两个哨兵节点，帮助形成序列化字符串
            Node endNode = new Node(); // 标记每层的结束
            Node childNode = new Node(); // 标记切换到下一个节点的孩子

            q.add(root);
            q.add(endNode);

            while (!q.isEmpty()) {
                Node node = q.poll(); // 取出队首节点

                if (node == endNode) {
                    sb.append('#'); // 添加层结束的哨兵值
                    if (!q.isEmpty()) {
                        q.add(endNode); // 如果队列不为空，继续标记下一层
                    }
                } else if (node == childNode) {
                    sb.append('$'); // 添加切换到下一个父节点的哨兵值
                } else {
                    sb.append((char) (node.val + '0')); // 添加当前节点的值
                    for (Node child : node.children) {
                        q.add(child); // 将孩子节点加入队列
                    }
                    if (q.peek() != endNode) {
                        q.add(childNode); // 如果当前层未结束，添加childNode标记
                    }
                }
            }
        }

        // 反序列化字符串为树
        public Node deserialize(String data) {
            if (data.isEmpty()) {
                return null; // 如果数据为空，返回空树
            }

            Node rootNode = new Node(data.charAt(0) - '0', new ArrayList<Node>()); // 创建根节点
            this._deserializeHelper(data, rootNode); // 调用辅助方法进行反序列化
            return rootNode; // 返回反序列化后的根节点
        }

        // 辅助方法进行反序列化
        private void _deserializeHelper(String data, Node rootNode) {
            LinkedList<Node> currentLevel = new LinkedList<Node>(); // 当前层的节点
            LinkedList<Node> prevLevel = new LinkedList<Node>(); // 前一层的节点
            currentLevel.add(rootNode); // 将根节点加入当前层
            Node parentNode = rootNode; // 初始父节点为根节点

            // 处理序列化字符串中的每个字符
            for (int i = 1; i < data.length(); i++) {
                char d = data.charAt(i);
                if (d == '#') {
                    prevLevel = currentLevel; // 交换当前层和前一层
                    currentLevel = new LinkedList<Node>();
                    parentNode = prevLevel.poll(); // 更新父节点
                } else {
                    if (d == '$') {
                        parentNode = prevLevel.poll(); // 切换到下一个父节点
                    } else {
                        Node childNode = new Node(d - '0', new ArrayList<Node>()); // 创建孩子节点
                        currentLevel.add(childNode); // 将孩子节点加入当前层
                        parentNode.children.add(childNode); // 将孩子节点加入父节点的孩子列表
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

        // 创建测试用例
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

        // 序列化
        String serializedData = codec.serialize(root);
        System.out.println("序列化后的字符串: " + serializedData);

        // 反序列化
        Node deserializedRoot = codec.deserialize(serializedData);
        System.out.println("反序列化后的树根节点值: " + deserializedRoot.val);
    }
}

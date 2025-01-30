package leetcode.question.bfs;

import leetcode.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *@Question:  297. 二叉树的序列化与反序列化 (Serialize and Deserialize Binary Tree)
 *@Difficulty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 68.17%
 *@Time Complexity: O(N) - 每个节点仅被访问一次
 *@Space Complexity: O(N) - 递归调用栈的最大深度取决于树的高度
 */
/**
 * 第一部分：题目描述
 *
 * 题目：297. 二叉树的序列化与反序列化（Serialize and Deserialize Binary Tree）
 *
 * 题目描述：
 * 1. 需要实现两个方法：
 *    - `serialize(TreeNode root)`: 将给定的二叉树转换成字符串
 *    - `deserialize(String data)`: 将序列化的字符串转换回原始二叉树
 * 2. 该方法需要支持 **所有二叉树结构**，包括完全二叉树、满二叉树、不完全二叉树、单侧树等。
 * 3. 不能使用全局或静态变量，必须在 `Codec` 类内实现。
 *
 * 示例：
 * 输入：
 *       1
 *      / \
 *     2   3
 *        / \
 *       4   5
 *
 * `serialize(root)` 输出："1,2,null,null,3,4,null,null,5,null,null,"
 * `deserialize("1,2,null,null,3,4,null,null,5,null,null,")` 返回原始二叉树
 *
 * 目标：
 * - **保证序列化后的数据能够唯一表示一棵二叉树**
 * - **保证反序列化后还原成与原树完全相同的结构**
 * - **优化时间和空间复杂度**
 */


/**
 * 第二部分：解题思路（基于代码的超级详细解析）
 *
 * 代码实现了两种方法：
 * 1. **深度优先搜索（DFS）**
 * 2. **广度优先搜索（BFS）**
 *
 * ------------
 * 方法1：使用 **DFS** 进行序列化和反序列化（前序遍历）
 * ------------
 *
 * **序列化思路（serialize1）**
 * - 采用 **前序遍历**（先访问根节点，再访问左子树，最后访问右子树）。
 * - 若当前节点为空，则存储 `"null,"`（表示该位置没有子节点）。
 * - 若当前节点不为空：
 *   1. 先存储当前节点的值
 *   2. 递归处理左子树
 *   3. 递归处理右子树
 * - **示例**
 *   - 输入二叉树：
 *           1
 *          / \
 *         2   3
 *            / \
 *           4   5
 *   - 前序遍历（DFS）过程：
 *     1. 访问 `1`，存储 `"1,"`
 *     2. 访问 `2`，存储 `"2,"`
 *     3. 左子树 `null`，存储 `"null,"`
 *     4. 右子树 `null`，存储 `"null,"`
 *     5. 访问 `3`，存储 `"3,"`
 *     6. 访问 `4`，存储 `"4,"`
 *     7. 左子树 `null`，存储 `"null,"`
 *     8. 右子树 `null`，存储 `"null,"`
 *     9. 访问 `5`，存储 `"5,"`
 *    10. 左子树 `null`，存储 `"null,"`
 *    11. 右子树 `null`，存储 `"null,"`
 *   - **最终序列化结果**: `"1,2,null,null,3,4,null,null,5,null,null,"`
 *
 * **反序列化思路（deserialize1）**
 * - 按照 **前序遍历** 规则恢复二叉树：
 *   1. 读取第一个值 `1`，创建 `TreeNode(1)`
 *   2. 读取 `2`，创建 `TreeNode(2)`
 *   3. 读取 `null`，左子树设为 `null`
 *   4. 读取 `null`，右子树设为 `null`
 *   5. 读取 `3`，创建 `TreeNode(3)`
 *   6. 读取 `4`，创建 `TreeNode(4)`
 *   7. 读取 `null`，左子树设为 `null`
 *   8. 读取 `null`，右子树设为 `null`
 *   9. 读取 `5`，创建 `TreeNode(5)`
 *  10. 读取 `null`，左子树设为 `null`
 *  11. 读取 `null`，右子树设为 `null`
 * - 这样就正确构造了原始二叉树
 *
 * ------------
 * 方法2：使用 **BFS** 进行序列化和反序列化（层序遍历）
 * ------------
 *
 * **序列化思路（serialize）**
 * - 使用 **队列（Queue）** 进行 **层序遍历**（BFS）。
 * - 依次存储节点的值，并用 `"n"` 表示 `null` 节点。
 * - 过程：
 *   1. 根节点 `1` 入队列，存储 `"1 "`
 *   2. `1` 的左子节点 `2` 入队列，存储 `"2 "`
 *   3. `1` 的右子节点 `3` 入队列，存储 `"3 "`
 *   4. `2` 没有子节点，存储 `"n n "`
 *   5. `3` 的左子节点 `4` 入队列，存储 `"4 "`
 *   6. `3` 的右子节点 `5` 入队列，存储 `"5 "`
 *   7. `4` 没有子节点，存储 `"n n "`
 *   8. `5` 没有子节点，存储 `"n n "`
 * - **最终序列化结果**: `"1 2 3 n n 4 5 n n n n "`
 *
 * **反序列化思路（deserialize）**
 * - 使用 **队列** 逐层构造二叉树：
 *   1. 读取 `1`，创建根节点 `TreeNode(1)`
 *   2. 读取 `2`，作为 `1` 的左子节点
 *   3. 读取 `3`，作为 `1` 的右子节点
 *   4. 读取 `n`，跳过 `2` 的左右子节点
 *   5. 读取 `4`，作为 `3` 的左子节点
 *   6. 读取 `5`，作为 `3` 的右子节点
 *   7. 读取 `n`，跳过 `4` 的左右子节点
 *   8. 读取 `n`，跳过 `5` 的左右子节点
 * - 构造出原始二叉树
 *
 */


/**
 * 第三部分：时间和空间复杂度分析
 *
 * **DFS 方法**
 * - **时间复杂度：O(N)**，每个节点只遍历一次
 * - **空间复杂度：O(N)**，递归调用栈最大深度为 `O(H)`，`H` 是树的高度，最坏情况下（链状树）等于 `O(N)`
 *
 * **BFS 方法**
 * - **时间复杂度：O(N)**，每个节点只入队和出队一次
 * - **空间复杂度：O(N)**，队列的最大大小等于二叉树最宽的一层的节点数，在满二叉树情况下是 `O(N)`
 */



public class LeetCode_297_SerializeAndDeserializeBinaryTree{

//leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Codec {
        //Solution1: 使用深度优先搜索（DFS）进行序列化与反序列化

        // 递归序列化二叉树，将其转换为字符串
        public String rserialize(TreeNode root, String str) {
            // 如果当前节点为空，则存储 "null," 代表空节点
            if (root == null) {
                str += "null,";
            } else {
                // 存储当前节点的值，并添加逗号作为分隔符
                str += String.valueOf(root.val) + ",";
                // 递归序列化左子树
                str = rserialize(root.left, str);
                // 递归序列化右子树
                str = rserialize(root.right, str);
            }
            return str;
        }

        // 对外提供的序列化接口，返回二叉树的字符串表示
        public String serialize1(TreeNode root) {
            return rserialize(root, "");
        }

        // 递归反序列化，将字符串转换回二叉树
        public TreeNode rdeserialize(List<String> l) {
            // 取出当前列表的第一个元素，判断是否为 "null"
            if (l.get(0).equals("null")) {
                l.remove(0); // 移除 "null" 并返回 null 代表的空节点
                return null;
            }

            // 否则将该值转换为整数，并创建一个新的树节点
            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            l.remove(0); // 移除已处理的元素
            // 递归构造左子树
            root.left = rdeserialize(l);
            // 递归构造右子树
            root.right = rdeserialize(l);

            return root;
        }

        // 对外提供的反序列化接口，将字符串转换为二叉树
        public TreeNode deserialize1(String data) {
            // 使用 "," 分割字符串，得到数据数组
            String[] data_array = data.split(",");
            // 将数据转换为 `LinkedList`，方便使用 `remove(0)`
            List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
            // 调用递归方法进行反序列化
            return rdeserialize(data_list);
        }

        //Solution2: 使用广度优先搜索（BFS）进行序列化与反序列化
        public String serialize(TreeNode root) {
            // 如果根节点为空，则返回空字符串
            if (root == null) return "";
            // 使用队列存储节点，进行层序遍历
            Queue<TreeNode> q = new LinkedList<>();
            // 使用 StringBuilder 存储序列化结果
            StringBuilder res = new StringBuilder();
            // 将根节点加入队列
            q.add(root);
            while (!q.isEmpty()) {
                // 取出队列头部元素
                TreeNode node = q.poll();
                // 如果节点为空，存储 "n " 表示空节点
                if (node == null) {
                    res.append("$ ");
                    continue;
                }
                // 存储当前节点的值
                res.append(node.val + " ");
                // 将左右子节点加入队列
                q.add(node.left);
                q.add(node.right);
                System.out.println(res);
            }
            return res.toString();
        }

        public TreeNode deserialize(String data) {
            // 如果输入为空字符串，则返回空树
            if (data.equals("")) return null;
            // 使用队列进行层序遍历
            Queue<TreeNode> q = new LinkedList<>();
            // 按空格分割字符串，得到所有节点值
            String[] values = data.split(" ");
            // 创建根节点
            TreeNode root = new TreeNode(Integer.parseInt(values[0]));
            q.add(root);
            for (int i = 1; i < values.length; i++) {
                // 取出队列头部节点作为父节点
                TreeNode parent = q.poll();
                // 如果当前值不是 "n"，说明存在左子节点
                if (!values[i].equals("$")) {
                    TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                    parent.left = left;
                    q.add(left);
                }
                // 处理右子节点，使用 `++i` 直接获取下一个值
                if (!values[++i].equals("$")) {
                    TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                    parent.right = right;
                    q.add(right);
                }
            }
            return root;
        }

    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Codec codec = new LeetCode_297_SerializeAndDeserializeBinaryTree().new Codec();

        // 构造测试用例的二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // 测试使用 DFS 序列化
        String serializedTreeDFS = codec.serialize1(root);
        System.out.println("DFS Serialized Tree: " + serializedTreeDFS);
        // 预期输出: "1,2,null,null,3,4,null,null,5,null,null,"

        // 测试使用 DFS 反序列化
        TreeNode deserializedTreeDFS = codec.deserialize1(serializedTreeDFS);
        System.out.println("DFS Re-Serialized Tree: " + codec.serialize1(deserializedTreeDFS));
        // 预期输出与 serializedTreeDFS 一致

        // 测试使用 BFS 序列化
        String serializedTreeBFS = codec.serialize(root);
        System.out.println("BFS Serialized Tree: " + serializedTreeBFS);
        // 预期输出: "1 2 3 n n 4 5 n n n n "

        // 测试使用 BFS 反序列化
        TreeNode deserializedTreeBFS = codec.deserialize(serializedTreeBFS);
        System.out.println("BFS Re-Serialized Tree: " + codec.serialize(deserializedTreeBFS));
        // 预期输出与 serializedTreeBFS 一致
    }
}


/**
 Serialization is the process of converting a data structure or object into a
 sequence of bits so that it can be stored in a file or memory buffer, or
 transmitted across a network connection link to be reconstructed later in the same or
 another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no 
 restriction on how your serialization/deserialization algorithm should work. You
 just need to ensure that a binary tree can be serialized to a string and this
 string can be deserialized to the original tree structure.

 Clarification: The input/output format is the same as how LeetCode serializes 
 a binary tree. You do not necessarily need to follow this format, so please be
 creative and come up with different approaches yourself.


 Example 1: 


 Input: root = [1,2,3,null,null,4,5]
 Output: [1,2,3,null,null,4,5]


 Example 2: 


 Input: root = []
 Output: []



 Constraints: 


 The number of nodes in the tree is in the range [0, 10⁴]. 
 -1000 <= Node.val <= 1000 


 Related Topics String Tree Depth-First Search Breadth-First Search Design 
 Binary Tree 👍 10424 👎 403

 */
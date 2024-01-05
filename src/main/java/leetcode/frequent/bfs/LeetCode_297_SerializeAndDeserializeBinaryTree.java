// 导入所需的类
package leetcode.frequent.bfs;

import leetcode.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *@Question:  297. Serialize and Deserialize Binary Tree
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 62.8%
 *@Time  Complexity: O(n)
 *@Space Complexity: O(n)
 */

/**
 * ### 算法思路
 *
 * 该问题涉及将二叉树进行序列化（将其表示成一个字符串）和反序列化（将字符串还原为二叉树）。
 * 为了实现这一目标，我们采用深度优先搜索（DFS）遍历二叉树。
 *
 * #### 序列化
 *
 * - 序列化过程采用深度优先搜索，递归地遍历整个二叉树。
 * - 对于每个节点，将其值转化为字符串，并将其添加到结果字符串中。
 * - 若节点为空，则将字符串 "null" 添加到结果中。
 * - 最终得到一个以逗号分隔的字符串，表示整个二叉树的结构。
 *
 * #### 反序列化
 *
 * - 反序列化过程同样采用深度优先搜索，根据逗号分隔的字符串逐步构建二叉树。
 * - 将字符串拆分成数组，根据数组的顺序逐个构建节点。
 * - 若数组中的元素为 "null"，则表示当前节点为空；否则，将数组元素转化为整数值，作为当前节点的值。
 * - 递归处理左右子树，按照前序遍历的顺序还原整个二叉树。
 *
 * ### 复杂度分析
 *
 * **时间复杂度：** 对于序列化和反序列化，都需要遍历整个二叉树，因此时间复杂度为 O(n)，其中 n 是二叉树中的节点数。
 *
 * **空间复杂度：** 在序列化时，递归调用会消耗栈空间，空间复杂度为 O(h)，
 * 其中 h 是二叉树的高度。在反序列化时，需要额外的数组存储节点值，空间复杂度也为 O(n)。
 */

// 主类
public class LeetCode_297_SerializeAndDeserializeBinaryTree {

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

// Codec 类，负责序列化和反序列化二叉树
    public class Codec {

        // Serialization

        // 递归序列化函数
        public String rserialize(TreeNode root, String str) {
            // 如果节点为空，序列化为字符串"null"
            if (root == null) {
                str += "null,";
            } else {
                // 否则，将节点值转为字符串并加入到序列化字符串中
                str += str.valueOf(root.val) + ",";
                // 递归处理左右子树
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        // 将二叉树序列化为字符串
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        // Deserialization

        // 递归反序列化函数
        public TreeNode rdeserialize(List<String> l) {
            // 如果列表的第一个元素为"null"，表示当前节点为空
            if (l.get(0).equals("null")) {
                // 移除该元素并返回空节点
                l.remove(0);
                return null;
            }

            // 创建根节点，值为列表的第一个元素的整数值
            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            // 移除第一个元素
            l.remove(0);
            // 递归处理左右子树
            root.left = rdeserialize(l);
            root.right = rdeserialize(l);

            return root;
        }

        // 将字符串反序列化为二叉树
        public TreeNode deserialize(String data) {
            // 将输入的字符串按逗号分隔成数组
            String[] data_array = data.split(",");
            // 将数组转为链表
            List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
            // 调用递归反序列化函数
            return rdeserialize(data_list);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    // 测试代码
    public static void main(String[] args) {
        // 创建一个示例二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // 创建 Codec 对象
        LeetCode_297_SerializeAndDeserializeBinaryTree.Codec ser = new LeetCode_297_SerializeAndDeserializeBinaryTree().new Codec();
        LeetCode_297_SerializeAndDeserializeBinaryTree.Codec deser = new LeetCode_297_SerializeAndDeserializeBinaryTree().new Codec();

        // 将二叉树序列化为字符串
        String serialized = ser.serialize(root);
        System.out.println("Serialized Tree: " + serialized);

        // 将字符串反序列化为二叉树
        TreeNode deserialized = deser.deserialize(serialized);

        // 比较原始树和反序列化后的树是否相同
        System.out.println("Original and Deserialized Trees are " + (isSameTree(root, deserialized) ? "equal." : "not equal."));
    }

    // 判断两棵二叉树是否相同的辅助函数
    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
Binary Tree 👍 9725 👎 357

*/

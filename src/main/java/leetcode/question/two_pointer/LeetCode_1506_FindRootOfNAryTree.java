// 这是包名，表示当前类位于 leetcode.question.two_pointer 包下
package leetcode.question.two_pointer;
// 导入 Java 中常用的工具类，如 List、ArrayList 等

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Question:  1506. Find Root of N-Ary Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/*
1. 题目描述
   在一棵 N 叉树中，每个节点包含一个整型值和若干子节点。给定这棵树的所有节点（以任意顺序存放于一个列表中），
   要求找出整棵 N 叉树的根节点。可以确定整棵树中只有且只有一个根节点，该根节点没有父节点。

2. 解题思路（基于代码超级详细说明）：
   (1) 问题转化：
       - 每个节点都有一个 val 值，以及子节点列表 children；
       - 我们需要从给定的所有节点列表中，找出唯一的“根节点”。
         根节点的特点：没有父节点，不会出现在其它节点的子节点列表中。

   (2) 核心思路：利用加减抵消来定位根节点
       - 定义一个整数变量 valueSum，初始为 0；
       - 遍历整个节点列表，对每个节点的 val 值执行加法；
         相当于假设所有节点都可能是根节点，先把它们“加”进去；
       - 同时，对每个节点的子节点的 val 值执行减法；
         因为子节点意味着它不是根节点，把它们“从可能根节点名单里减”出去；
       - 这样，在加减抵消之后，留在 valueSum 中的值就是根节点的 val；
         因为真正的根节点从没被当作某个节点的子节点，所以它的 val 不会被减去。

   (3) 找到具体节点：
       - 在上一步得到根节点的 val 值后，再次遍历一遍给定的节点列表；
         找到与 valueSum 相同 val 的节点，即为根节点；
       - 一旦找到，立即返回该节点。

   (4) 举例说明：
       设 N 叉树的节点以及关系如下：

         (1)
        / | \
       /  |  \
     (3) (2) (4)
      / \
     /   \
   (5)   (6)

       - 列表包含节点：1, 3, 2, 4, 5, 6；
         其中 1 的子节点是 3,2,4；3 的子节点是 5,6；2,4,5,6 没有子节点。
       - 遍历所有节点：
         累加：1 + 3 + 2 + 4 + 5 + 6 = 21
         减去所有子节点：
         (1) 的子节点：3,2,4  → 减去 3,2,4
         (3) 的子节点：5,6    → 减去 5,6
         (2) 的子节点：无
         (4) 的子节点：无
         (5) 的子节点：无
         (6) 的子节点：无
         因此共减去 (3 + 2 + 4 + 5 + 6) = 20
         累加结果：valueSum = 21 - 20 = 1
       - 得到 valueSum = 1，表示根节点的 val 值是 1；
         最后再遍历一遍节点列表，找到 val == 1 的节点，即可返回根节点。

3. 时间和空间复杂度
   - 时间复杂度：
     我们需要遍历节点列表两次：一次是加总所有节点值并减去子节点值，第二次是根据 valueSum 找到匹配的节点。
     如果树中共有 n 个节点，每个节点有若干子节点，遍历时总共需要 O(n + k) 次操作，k 为所有子节点的总数，但在 N 叉树中每条边只访问一次，
     因此整体可视为 O(n)。
   - 空间复杂度：
     仅使用了一个额外的变量 valueSum，以及最终返回的根节点所需常数空间。无论树有多少节点，辅助空间都为 O(1)。
*/


// 定义一个公共类 LeetCode_1506_FindRootOfNAryTree，用来包含题目中的解法
public class LeetCode_1506_FindRootOfNAryTree{
    // 定义一个内部类 Node，表示 N 叉树的节点结构
    class Node {
        // val 为节点的值
        public int val;
        // children 为节点的子节点列表
        public List<Node> children;

        // Node 的无参构造函数，将 children 初始化为空的 ArrayList
        public Node() {
            children = new ArrayList<Node>();
        }

        // Node 的构造函数，接收一个 int 类型的值 _val，并将 children 初始化为空的 ArrayList
        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        // Node 的构造函数，接收一个 int 值和一个节点列表，分别初始化 val 和 children
        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

    // 定义一个内部类 Solution，包含题目的解题方法
    class Solution {
        // findRoot 方法用于在给定的 N 叉树列表中找到根节点
        public Node findRoot(List<Node> tree) {

            // 定义一个整型变量 valueSum，用来累加所有节点值并扣除所有子节点值
            Integer valueSum = 0;

            // 遍历 tree 列表中的每一个节点
            for (Node node : tree) {
                // the value is added as a parent node
                // 将当前节点的值加入 valueSum
                valueSum += node.val;
                // 遍历当前节点的子节点
                for (Node child : node.children)
                    // the value is deducted as a child node.
                    // 将每一个子节点的值从 valueSum 中减去
                    valueSum -= child.val;
            }

            // 定义一个 Node 类型的 root，初始化为 null
            Node root = null;
            // the value of the root node is `valueSum`
            // 遍历 tree 列表，寻找与 valueSum 相等的节点，即为根节点
            for (Node node : tree) {
                if (node.val == valueSum) {
                    root = node;
                    break;
                }
            }
            // 返回找到的 root 节点
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    // main 方法，用于测试代码功能
    public static void main(String[] args) {
        // 创建 Solution 对象，以便调用解题方法
        Solution solution = new LeetCode_1506_FindRootOfNAryTree().new Solution();
        // TO TEST
        //solution.

        // 以下为测试样例：手动构造一个 N 叉树并将所有节点放入一个列表
        // 构造节点
        LeetCode_1506_FindRootOfNAryTree outer = new LeetCode_1506_FindRootOfNAryTree();
        Node n1 = outer.new Node(1);
        Node n2 = outer.new Node(2);
        Node n3 = outer.new Node(3);
        Node n4 = outer.new Node(4);
        Node n5 = outer.new Node(5);
        Node n6 = outer.new Node(6);

        // 构造树的层次结构
        // n1 作为根节点，它有子节点 n3, n2, n4
        n1.children = Arrays.asList(n3, n2, n4);
        // n3 有子节点 n5, n6
        n3.children = Arrays.asList(n5, n6);

        // 将所有节点放入列表中
        List<Node> tree = Arrays.asList(n1, n2, n3, n4, n5, n6);

        // 调用 findRoot 方法，查找根节点
        Node root = solution.findRoot(tree);

        // 输出结果，预期应为 1，因为 n1 是根节点
        System.out.println("根节点的值应为 1，实际找到的根节点值是：" + root.val);
    }
}

/**
You are given all the nodes of an N-ary tree as an array of Node objects, where 
each node has a unique value. 

 Return the root of the N-ary tree. 

 Custom testing: 

 An N-ary tree can be serialized as represented in its level order traversal 
where each group of children is separated by the null value (see examples). 

 

 For example, the above tree is serialized as [1,null,2,3,4,5,null,null,6,7,
null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]. 

 The testing will be done in the following way: 

 
 The input data should be provided as a serialization of the tree. 
 The driver code will construct the tree from the serialized input data and put 
each Node object into an array in an arbitrary order. 
 The driver code will pass the array to findRoot, and your function should find 
and return the root Node object in the array. 
 The driver code will take the returned Node object and serialize it. If the 
serialized value and the input data are the same, the test passes. 
 

 
 Example 1: 

 

 
Input: tree = [1,null,3,2,4,null,5,6]
Output: [1,null,3,2,4,null,5,6]
Explanation: The tree from the input data is shown above.
The driver code creates the tree and gives findRoot the Node objects in an 
arbitrary order.
For example, the passed array could be [Node(5),Node(4),Node(3),Node(6),Node(2),
Node(1)] or [Node(2),Node(6),Node(1),Node(3),Node(5),Node(4)].
The findRoot function should return the root Node(1), and the driver code will 
serialize it and compare with the input data.
The input data and serialized Node(1) are the same, so the test passes.
 

 Example 2: 

 

 
Input: tree = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,1
2,null,13,null,null,14]
Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
null,13,null,null,14]
 

 
 Constraints: 

 
 The total number of nodes is between [1, 5 * 10⁴]. 
 Each node has a unique value. 
 

 
 Follow up: 

 
 Could you solve this problem in constant space complexity with a linear time 
algorithm? 
 

 Related Topics Hash Table Bit Manipulation Tree Depth-First Search 👍 482 👎 20
4

*/
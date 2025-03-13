package leetcode.question.dfs;

import javafx.util.Pair;
import leetcode.util.TreeNode;

import java.util.Stack;

/**
 *@Question:  536. Construct Binary Tree from String
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 49.98%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(H)
 */
/*
 * 536. Construct Binary Tree from String
 *
 * 题目描述：
 * 给定一个**由整数和括号组成的字符串 s**，根据以下规则构造一棵二叉树：
 * 1. **整数表示一个节点的值**，例如 "4" 代表根节点的值是 4。
 * 2. **括号内的内容表示子树**：
 *    - **左子树总是先出现**，例如 "4(2)(6)" 代表 4 是根节点，2 是左子节点，6 是右子节点。
 *    - **嵌套括号代表更深层的子树**，例如 "4(2(3)(1))(6(5))"：
 *      - 4 是根
 *      - 2 是 4 的左子树，2 有两个子节点：3 和 1
 *      - 6 是 4 的右子树，6 有一个左子节点 5
 * 3. **负数也可以出现**，例如 "-1(2)(3)" 代表 -1 是根节点，2 是左子节点，3 是右子节点。
 *
 * 示例：
 * 输入："4(2(3)(1))(6(5))"
 * 输出：返回一棵二叉树，其先序遍历（Preorder Traversal）为 [4,2,3,1,6,5]。
 *
 * =====================
 * 超详细解题思路：
 * =====================
 * 解决方案1：DFS（递归构建）
 * -------------------------------------
 * 1️⃣ **解析字符串，构建二叉树**
 *    - 首先解析字符串 `s` 的根节点，即最前面的整数。
 *    - 如果后续存在 `(`，说明存在子树，递归解析左子树。
 *    - 如果左子树解析完毕后仍然有 `(`，则递归解析右子树。
 *    - 解析完毕后返回构造的 `TreeNode`。
 *
 * 2️⃣ **解析数字**
 *    - 使用 `getNumber1(String s, int index)`：
 *      - 解析当前索引的数字，支持负数。
 *      - 例如：
 *        - 输入 `s = "-23(1)"`，索引 0，返回 `(-23, 3)`。
 *        - 输入 `s = "4(2)(6)"`，索引 0，返回 `(4, 1)`。
 *
 * 3️⃣ **递归构建**
 *    - `str2treeInternal(String s, int index)` 逻辑：
 *      - 解析当前节点值，创建 `TreeNode`。
 *      - 若 `s[index] == '('`，递归解析左子树。
 *      - 若 `s[index] == '('` 且左子树已存在，递归解析右子树。
 *      - 若 `s[index] == ')'`，则递归结束，索引前进。
 *
 * 4️⃣ **示例解析**
 *    - `s = "4(2(3)(1))(6(5))"`
 *      - 解析 `4`，创建根节点 `TreeNode(4)`
 *      - 解析 `"2(3)(1)"`，创建 `TreeNode(2)`
 *        - 解析 `3`，创建 `TreeNode(3)`（作为 `2` 的左子树）
 *        - 解析 `1`，创建 `TreeNode(1)`（作为 `2` 的右子树）
 *      - 解析 `"6(5)"`，创建 `TreeNode(6)`
 *        - 解析 `5`，创建 `TreeNode(5)`（作为 `6` 的左子树）
 *
 * 5️⃣ **递归终止条件**
 *    - 当 `index == s.length()`，返回 `(null, index)`，表示解析完成。
 *
 * =====================
 * 解决方案2：Stack（迭代构建）
 * -------------------------------------
 * 1️⃣ **使用栈存储节点**
 *    - 创建 `Stack<TreeNode>` 来存储当前构造的树节点。
 *    - 解析 `s` 时，遇到整数时创建 `TreeNode`，并存入栈。
 *    - 遇到 `(` 说明有子树，将新节点加入栈。
 *    - 遇到 `)` 说明当前子树构造结束，出栈。
 *
 * 2️⃣ **迭代构建逻辑**
 *    - `str2tree(String s)` 逻辑：
 *      - 使用 `stack` 维护正在构建的树。
 *      - `s[index]` 是数字时，解析 `TreeNode` 并入栈。
 *      - `s[index] == '('` 时，表示要创建子树，入栈。
 *      - `s[index] == ')'` 时，子树结束，出栈。
 *
 * 3️⃣ **示例解析**
 *    - `s = "4(2(3)(1))(6(5))"`
 *      - 解析 `4`，创建 `TreeNode(4)`，压栈。
 *      - 解析 `2`，创建 `TreeNode(2)`，作为 `4` 的左子树，压栈。
 *      - 解析 `3`，创建 `TreeNode(3)`，作为 `2` 的左子树，压栈。
 *      - 解析 `1`，创建 `TreeNode(1)`，作为 `2` 的右子树，压栈。
 *      - 解析 `6`，创建 `TreeNode(6)`，作为 `4` 的右子树，压栈。
 *      - 解析 `5`，创建 `TreeNode(5)`，作为 `6` 的左子树，压栈。
 *
 * =====================
 * 时间 & 空间复杂度分析：
 * =====================
 * **方法1：递归DFS**
 * - 时间复杂度 O(N)：
 *   - 每个字符最多遍历一次，因此是 O(N)。
 *   - 递归最多进行 O(N) 次操作（每个字符最多入栈一次）。
 * - 空间复杂度 O(H)：
 *   - 递归深度由树的高度 H 决定，最坏情况 H=O(N)，最优情况 H=O(log N)。
 *
 * **方法2：Stack（迭代）**
 * - 时间复杂度 O(N)：
 *   - 解析字符串的时间是 O(N)。
 *   - 每个节点最多入栈、出栈一次，因此是 O(N)。
 * - 空间复杂度 O(H)：
 *   - 使用的 `Stack` 深度等于树的高度 H，最坏情况下 H=O(N)。
 *
 * =====================
 * 结论：
 * - **如果递归深度较小（平衡树），DFS 方法更直观。**
 * - **如果递归深度较深（链状树），Stack 方法可以避免栈溢出。**
 */



public class LeetCode_536_ConstructBinaryTreeFromString {

//leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
//        // 方案1：使用深度优先搜索（DFS）递归解析字符串并构建二叉树
//        public TreeNode str2tree1(String s) {
//            // 解析字符串并构建二叉树，调用内部递归函数
//            return this.str2treeInternal(s, 0).getKey();
//        }
//
//        // 解析字符串中的整数，并返回该整数及其结束索引
//        public Pair<Integer, Integer> getNumber1(String s, int index) {
//            boolean isNegative = false; // 标记是否为负数
//
//            // 如果当前字符是负号，标记为负数并移动索引
//            if (s.charAt(index) == '-') {
//                isNegative = true;
//                index++;
//            }
//
//            int number = 0;
//            // 解析连续的数字字符，转换为整数
//            while (index < s.length() && Character.isDigit(s.charAt(index))) {
//                number = number * 10 + (s.charAt(index) - '0');
//                index++;
//            }
//
//            // 返回解析出的整数及其结束索引
//            return new Pair<Integer, Integer>(isNegative ? -number : number, index);
//        }
//
//        // 递归解析字符串并构建二叉树
//        public Pair<TreeNode, Integer> str2treeInternal(String s, int index) {
//            // 如果索引已达到字符串末尾，返回空节点
//            if (index == s.length()) {
//                return new Pair<TreeNode, Integer>(null, index);
//            }
//
//            // 解析根节点的值
//            Pair<Integer, Integer> numberData = this.getNumber1(s, index);
//            int value = numberData.getKey();  // 获取根节点值
//            index = numberData.getValue();  // 更新索引位置
//
//            // 创建根节点
//            TreeNode node = new TreeNode(value);
//            Pair<TreeNode, Integer> data;
//
//            // 解析左子树
//            if (index < s.length() && s.charAt(index) == '(') {
//                data = this.str2treeInternal(s, index + 1);
//                node.left = data.getKey(); // 递归构建左子树
//                index = data.getValue(); // 更新索引位置
//            }
//
//            // 解析右子树
//            if (node.left != null && index < s.length() && s.charAt(index) == '(') {
//                data = this.str2treeInternal(s, index + 1);
//                node.right = data.getKey(); // 递归构建右子树
//                index = data.getValue(); // 更新索引位置
//            }
//
//            // 如果当前索引指向 ')'，则跳过它
//            return new Pair<TreeNode, Integer>(node, index < s.length() && s.charAt(index) == ')' ? index + 1 : index);
//        }

        // 方案2：,更直观，使用栈（Stack）进行迭代解析字符串
        public TreeNode str2tree(String s) {

            // 如果输入字符串为空，则返回 null
            if (s.isEmpty()) {
                return null;
            }

            // 创建根节点
            TreeNode root = new TreeNode();
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.add(root);

            // 遍历字符串
            for (int index = 0; index < s.length();) {

                // 取出当前栈顶的节点
                TreeNode node = stack.pop();

                // 处理数字
                if (Character.isDigit(s.charAt(index)) || s.charAt(index) == '-') {
                    Pair<Integer, Integer> numberData = this.getNumber(s, index);
                    int value = numberData.getKey();
                    index = numberData.getValue();

                    node.val = value;

                    // 处理左子树
                    if (index < s.length() && s.charAt(index) == '(') {
                        stack.add(node);
                        node.left = new TreeNode();
                        stack.add(node.left);
                    }
                } else if (s.charAt(index) == '(' && node.left != null) { // 处理右子树
                    stack.add(node);
                    node.right = new TreeNode();
                    stack.add(node.right);
                }
                for(TreeNode t: stack){
                    System.out.print(t.val + " ");

                }
                System.out.println(", size is = " + stack.size());

                // 移动索引
                ++index;
            }

            // 返回构造好的二叉树
            System.out.println("size is = " + stack.size());
            return stack.empty() ? root : stack.pop();
        }

        // 解析字符串中的整数，并返回该整数及其结束索引
        public Pair<Integer, Integer> getNumber(String s, int index) {
            boolean isNegative = false;

            // 处理负数
            if (s.charAt(index) == '-') {
                isNegative = true;
                index++;
            }

            int number = 0;
            // 解析数字字符
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                number = number * 10 + (s.charAt(index) - '0');
                index++;
            }

            // 返回解析的数值
            return new Pair<Integer, Integer>(isNegative ? -number : number, index);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_536_ConstructBinaryTreeFromString().new Solution();

        // 测试样例 1：标准二叉树字符串输入
        String input1 = "4(2(3)(1))(6(5))";
        TreeNode root1 = solution.str2tree(input1);
        System.out.println("测试样例 1 构建的二叉树（先序遍历）:");
        printPreOrder(root1); // 预期输出: 4 2 3 1 6 5

//        // 测试样例 2：只有根节点
//        String input2 = "10";
//        TreeNode root2 = solution.str2tree(input2);
//        System.out.println("测试样例 2 构建的二叉树（先序遍历）:");
//        printPreOrder(root2); // 预期输出: 10
//
//        // 测试样例 3：有负数的情况
//        String input3 = "-1(2)(3)";
//        TreeNode root3 = solution.str2tree(input3);
//        System.out.println("测试样例 3 构建的二叉树（先序遍历）:");
//        printPreOrder(root3); // 预期输出: -1 2 3
    }

    // 辅助方法：使用先序遍历打印二叉树
    public static void printPreOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }
}


/**
You need to construct a binary tree from a string consisting of parenthesis and 
integers. 

 The whole input represents a binary tree. It contains an integer followed by 
zero, one or two pairs of parenthesis. The integer represents the root's value 
and a pair of parenthesis contains a child binary tree with the same structure. 

 You always start to construct the left child node of the parent first if it 
exists. 

 
 Example 1: 
 
 
Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]
 

 Example 2: 

 
Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]
 

 Example 3: 

 
Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7]
 

 
 Constraints: 

 
 0 <= s.length <= 3 * 10⁴ 
 s consists of digits, '(', ')', and '-' only. 
 All numbers in the tree have value at most than 2³⁰. 
 

 Related Topics String Stack Tree Depth-First Search Binary Tree 👍 1104 👎 178

*/
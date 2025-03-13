package leetcode.question.bfs;

import javafx.util.Pair;
import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
  *@Question:  366. Find Leaves of Binary Tree     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 43.3%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

/*
 * 366. Find Leaves of Binary Tree（寻找二叉树的叶子节点）
 *
 * =====================
 * 题目描述：
 * =====================
 * 给定一棵二叉树 `root`，要求**按照层次顺序依次移除叶子节点**，直到整棵树为空。
 *
 * - **定义叶子节点**：**没有子节点**的节点，即 `left == null && right == null`。
 * - **每一轮移除的叶子节点应作为一个子列表**，最终返回所有的移除过程。
 *
 * **示例：**
 * ```
 * 输入：
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 *
 * 输出：
 * [
 *   [4, 5, 3],  // 第一轮移除叶子节点 4, 5, 3
 *   [2],        // 第二轮移除叶子节点 2
 *   [1]         // 第三轮移除叶子节点 1（根节点）
 * ]
 * ```
 *
 * =====================
 * 超详细解题思路：
 * =====================
 * 1️⃣ **计算每个节点的高度**
 *    - **定义 `getHeight(TreeNode root)`**：
 *      - 递归计算 `leftHeight = getHeight(root.left)`
 *      - 递归计算 `rightHeight = getHeight(root.right)`
 *      - 计算当前节点高度 `currHeight = max(leftHeight, rightHeight) + 1`
 *      - **将 `(currHeight, root.val)` 存入 `pairs` 列表**
 *      - **返回 `currHeight`**
 *    - **示例计算**
 *      ```
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 *      ```
 *      - `getHeight(4) = 0`
 *      - `getHeight(5) = 0`
 *      - `getHeight(2) = max(0, 0) + 1 = 1`
 *      - `getHeight(3) = 0`
 *      - `getHeight(1) = max(1, 0) + 1 = 2`
 *
 * 2️⃣ **按高度排序并分组**
 *    - `pairs = [(0, 4), (0, 5), (0, 3), (1, 2), (2, 1)]`
 *    - **按 `height` 排序**
 *      ```
 *      [
 *        [4, 5, 3],  // 高度 = 0
 *        [2],        // 高度 = 1
 *        [1]         // 高度 = 2
 *      ]
 *      ```
 *
 * 3️⃣ **构建 `findLeaves(TreeNode root)` 结果**
 *    - **初始化 `pairs = []`**
 *    - **调用 `getHeight(root)` 计算所有节点的 `(height, val)`**
 *    - **排序 `pairs`**
 *    - **分层填充 `solution[][]`**
 *
 * 4️⃣ **示例解析**
 *    **输入：**
 *    ```
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 *    ```
 *    **计算 `pairs`**
 *    ```
 *    [(0, 4), (0, 5), (0, 3), (1, 2), (2, 1)]
 *    ```
 *    **最终输出**
 *    ```
 *    [
 *      [4, 5, 3],
 *      [2],
 *      [1]
 *    ]
 *    ```
 *
 * =====================
 * 时间 & 空间复杂度分析：
 * =====================
 * **时间复杂度 O(N)：**
 * - 计算树高 `O(N)`
 * - 遍历 `pairs` 并分组 `O(N)`
 * - 复杂度最终为 `O(N)`
 *
 * **空间复杂度 O(N)：**
 * - 递归调用栈最大 `O(N)`
 * - 额外使用 `pairs` 列表 `O(N)`
 *
 * =====================
 * 结论：
 * - **二叉树层次结构清晰，可用于动态分析树的变化过程**
 * - **递归计算高度 + 按层分组，逻辑直观**
 * - **适用于树形数据的分层处理**
 */

public class LeetCode_366_FindLeavesOfBinaryTree {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private List<Pair<Integer, Integer>> pairs;

        /**
         * 计算每个节点的高度，并将 (height, val) 放入 pairs 列表中
         *
         * @param root 当前节点
         * @return 当前节点的高度
         */
        private int getHeight(TreeNode root) {
            // 对于空节点，返回 -1
            if (root == null) return -1;

            // 计算左右子节点的高度
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);

            // 根据左右子节点的高度计算当前节点的高度
            int currHeight = Math.max(leftHeight, rightHeight) + 1;

            // 将 (height, val) 放入 pairs 列表
            this.pairs.add(new Pair<>(currHeight, root.val));

            // 返回当前节点的高度
            return currHeight;
        }

        /**
         * 寻找二叉树的叶子节点
         *
         * @param root 二叉树的根节点
         * @return 每个层次上不同的叶子节点的值列表
         */
        public List<List<Integer>> findLeaves(TreeNode root) {
            this.pairs = new ArrayList<>();

            // 计算每个节点的高度
            getHeight(root);

            System.out.println(pairs);

            // 根据高度对 (height, val) 进行排序
            Collections.sort(this.pairs, Comparator.comparing(Pair::getKey));

            int n = this.pairs.size(), height = 0, i = 0;

            List<List<Integer>> solution = new ArrayList<>();

            // 根据高度构建结果列表
            while (i < n) {
                List<Integer> nums = new ArrayList<>();
                while (i < n && this.pairs.get(i).getKey() == height) {
                    nums.add(this.pairs.get(i).getValue());
                    i++;
                }
                solution.add(nums);
                height++;
            }
            return solution;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_366_FindLeavesOfBinaryTree().new Solution();

        // 测试用例
        // 构建测试二叉树
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // 调用函数获取结果
        List<List<Integer>> result = solution.findLeaves(root);

        // 打印结果
        for (List<Integer> level : result) {
            System.out.println(level);
        }
    }
}

/**
Given the root of a binary tree, collect a tree's nodes as if you were doing 
this: 

 
 Collect all the leaf nodes. 
 Remove all the leaf nodes. 
 Repeat until the tree is empty. 
 

 
 Example 1: 
 
 
Input: root = [1,2,3,4,5]
Output: [[4,5,3],[2],[1]]
Explanation:
[[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers 
since per each level it does not matter the order on which elements are returned.
 

 Example 2: 

 
Input: root = [1]
Output: [[1]]
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 100]. 
 -100 <= Node.val <= 100 
 

 Related Topics Tree Depth-First Search Binary Tree 👍 3166 👎 54

*/

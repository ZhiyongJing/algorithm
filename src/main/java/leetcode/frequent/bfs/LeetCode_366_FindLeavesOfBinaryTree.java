package leetcode.frequent.bfs;

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

/**
 * **算法思路解析：**
 *
 * 这个问题的主要目标是找到给定二叉树中每个层次上不同的叶子节点的值。我们可以按照以下步骤来实现：
 *
 * 1. **计算每个节点的高度：** 我们首先需要计算每个节点的高度，即根节点的高度为0，而每个子节点的高度是其父节点高度加1。
 * 我们可以通过递归遍历二叉树，计算每个节点的高度，并将 `(height, val)` 对放入一个列表中。
 *
 * 2. **根据高度排序：** 接下来，我们将得到的 `(height, val)` 列表按照高度进行排序。这样，我们就得到了按照高度顺序排列的节点信息。
 *
 * 3. **构建结果列表：** 最后，我们遍历排好序的节点信息列表，根据高度构建每个层次上的叶子节点的值的列表，并将这些列表放入结果列表中。
 *
 * **时间复杂度：** 对于二叉树中的每个节点，我们只需计算其高度一次，因此时间复杂度是 O(N)，其中 N 是二叉树中的节点数。
 *
 * **空间复杂度：** 我们使用了一个辅助列表来存储 `(height, val)` 对，因此空间复杂度是 O(N)，其中 N 是二叉树中的节点数。
 * 在递归调用中，我们使用的栈空间最大为树的高度，因此递归的空间复杂度也是 O(N)。
 *
 * 这个算法通过递归计算节点高度，然后排序节点信息，最后构建结果列表，实现了按照层次顺序找到不同叶子节点值的目标。
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

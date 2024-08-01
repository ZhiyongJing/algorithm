package leetcode.question.bfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *@Question:  270. Closest Binary Search Tree Value
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 27.98%
 *@Time  Complexity: O(N) for solution1, O(H) for solution2
 *@Space Complexity: O(N) for solution1, O(1) for solution2
 */

/**
 * ### 题目概述
 *
 * **LeetCode 270: Closest Binary Search Tree Value**
 *
 * 给定一个二叉搜索树（BST）和一个目标值 `target`，找到 BST 中最接近 `target` 的值。
 *
 * ### 解题思路
 *
 * 这道题要求我们找到 BST 中最接近目标值 `target` 的节点值。我们可以使用两种方法来解决这个问题：
 *
 * #### 解法1：中序遍历 + 线性搜索
 *
 * 1. **中序遍历**：首先，我们使用中序遍历遍历整个二叉搜索树，将所有节点的值按升序存储到一个列表中。
 * 2. **线性搜索**：然后，我们在该列表中线性搜索，找到与 `target` 值最接近的值。
 *
 * **具体步骤**：
 * - 我们从根节点开始，递归地访问左子树，根节点，和右子树。中序遍历的结果是一个有序数组。
 * - 然后，我们遍历这个有序数组，通过比较 `target` 和每个元素之间的绝对差值，找出最接近 `target` 的值。
 *
 * **时间复杂度**：O(N)，其中 N 是树中节点的数量。我们需要遍历整个树一次才能得到所有节点的值。
 *
 * **空间复杂度**：O(N)，用于存储所有节点的值。
 *
 * #### 解法2：二分查找法
 *
 * 1. **初始化**：将根节点值设为初始的最接近值 `closest`。
 * 2. **迭代遍历**：我们从根节点开始，通过比较 `target` 和当前节点的值 `val`，更新最接近值 `closest`。
 * 3. **二分选择**：根据 `target` 和当前节点值的关系决定遍历方向。如果 `target` 小于当前节点值，则向左子树移动；否则向右子树移动。
 *
 * **具体步骤**：
 * - 初始化 `closest` 为根节点的值。
 * - 迭代遍历 BST，对于每个节点：
 *   - 如果当前节点值更接近 `target`，更新 `closest`。
 *   - 根据 `target` 和当前节点值的大小关系决定向左或向右子树移动。
 * - 当遍历完成时，`closest` 即为最接近 `target` 的值。
 *
 * **时间复杂度**：O(H)，其中 H 是树的高度。最坏情况下我们可能需要遍历树的高度。
 *
 * **空间复杂度**：O(1)，因为我们只使用了常数空间来存储一些变量。
 *
 * ### 总结
 *
 * - **解法1** 适用于树结构较小或我们需要遍历所有节点的场景。
 * - **解法2** 更高效，适用于需要快速找到最接近值的情况，因为它利用了 BST 的有序性质。
 */

public class LeetCode_270_ClosestBinarySearchTreeValue{

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
        // 中序遍历二叉树，并将节点值添加到列表中
        public void inorder(TreeNode root, List<Integer> nums) {
            if (root == null) return; // 如果当前节点为空，直接返回
            inorder(root.left, nums); // 递归遍历左子树
            nums.add(root.val); // 将当前节点的值添加到列表中
            inorder(root.right, nums); // 递归遍历右子树
        }

        // 解法1：中序遍历 + 线性搜索，时间复杂度 O(N)
        public int closestValue1(TreeNode root, double target) {
            List<Integer> nums = new ArrayList<>();
            inorder(root, nums); // 获取中序遍历结果
            // 使用 Comparator 比较 nums 中每个值与 target 的差值的绝对值
            return Collections.min(nums, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
                }
            });
        }

        // 解法2：二分查找
        public int closestValue(TreeNode root, double target) {
            int val, closest = root.val; // 初始化 closest 为根节点的值
            while (root != null) { // 当节点不为空时，继续遍历
                val = root.val; // 获取当前节点的值
                // 更新 closest，比较当前值与 target 的差值
                closest = Math.abs(val - target) < Math.abs(closest - target)
                        || (Math.abs(val - target) == Math.abs(closest - target) && val < closest) ? val : closest;
                // 根据 target 和当前值的大小关系，决定遍历方向
                root = target < root.val ? root.left : root.right;
            }
            return closest; // 返回最接近 target 的值
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_270_ClosestBinarySearchTreeValue().new Solution();

        // 测试样例
        // 构建二叉搜索树
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // 调用 closestValue 方法，并打印结果
        double target = 3.714286;
        System.out.println(solution.closestValue(root, target)); // 输出应为 4

        // 测试解法1
        System.out.println(solution.closestValue1(root, target)); // 输出应为 4
    }
}

/**
Given the root of a binary search tree and a target value, return the value in 
the BST that is closest to the target. If there are multiple answers, print the 
smallest. 

 
 Example 1: 
 
 
Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
 

 Example 2: 

 
Input: root = [1], target = 4.428571
Output: 1
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁴]. 
 0 <= Node.val <= 10⁹ 
 -10⁹ <= target <= 10⁹ 
 

 Related Topics Binary Search Tree Depth-First Search Binary Search Tree Binary 
Tree 👍 1733 👎 115

*/

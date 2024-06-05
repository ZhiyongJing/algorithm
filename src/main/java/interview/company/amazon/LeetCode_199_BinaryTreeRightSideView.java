package interview.company.amazon;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 *@Question:  199. Binary Tree Right Side View
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 68.62%
 *@Time  Complexity: O(N) N is number of node in the  tree
 *@Space Complexity: O(D) D is a t ree diameter
 */

/**
 * 这个问题要求我们从右侧看二叉树，即返回每一层最右侧的节点的值构成的列表。下面是解题思路的详细解释以及时间和空间复杂度分析：
 *
 * ### 解题思路：
 *
 * 1. **层序遍历（BFS）**：
 *    - 我们可以使用广度优先搜索（BFS）来遍历二叉树的每一层，并记录每一层最右侧的节点的值。
 *
 * 2. **使用队列进行层序遍历**：
 *    - 我们使用队列来进行层序遍历，并通过在每一层结束时加入一个空节点来标记该层的结束。
 *    - 在遍历每一层时，我们将当前节点的左右子节点加入队列，直到遇到空节点为止。
 *
 * 3. **记录最右侧节点的值**：
 *    - 在遍历每一层时，我们始终保持一个指针指向当前层的最右侧节点，将其值记录到结果列表中。
 *
 * ### 时间复杂度分析：
 *
 * - **层序遍历**：我们需要遍历每个节点一次，因此时间复杂度为 O(N)，其中 N 是二叉树中的节点数量。
 *
 * ### 空间复杂度分析：
 *
 * - **队列空间**：我们使用了一个队列来存储节点，最坏情况下，队列中会包含每层的所有节点，因此空间复杂度为 O(N)，其中 N 是二叉树中的节点数量。
 * - **结果列表空间**：结果列表的空间复杂度也为 O(N)，因为最终结果列表中包含了每层的最右侧节点的值。
 *
 * 综上所述，该算法的时间复杂度为 O(N)，空间复杂度为 O(N)。
 */

public class LeetCode_199_BinaryTreeRightSideView{

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
        public List<Integer> rightSideView(TreeNode root) {
            // 若根节点为空，则返回空列表
            if (root == null) return new ArrayList<Integer>();

            // 创建一个队列，用于层序遍历二叉树
            Queue<TreeNode> queue = new LinkedList(){{ offer(root); offer(null); }};
            TreeNode prev, curr = root;
            List<Integer> rightside = new ArrayList();

            while (!queue.isEmpty()) {
                prev = curr;
                curr = queue.poll();

                // 在遍历一层时，将该层的右侧节点的值记录下来
                while (curr != null) {
                    // 将当前节点的左右子节点加入队列
                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }
                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }

                    prev = curr;
                    curr = queue.poll();
                }

                // 将该层的右侧节点的值添加到结果列表中
                rightside.add(prev.val);

                // 在队列中加入一个空节点，表示下一层的结束
                if (!queue.isEmpty())
                    queue.offer(null);
            }
            return rightside;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_199_BinaryTreeRightSideView().new Solution();
        // TO TEST
        //solution.
    }
}

/**
Given the root of a binary tree, imagine yourself standing on the right side of 
it, return the values of the nodes you can see ordered from top to bottom. 

 
 Example 1: 
 
 
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
 

 Example 2: 

 
Input: root = [1,null,3]
Output: [1,3]
 

 Example 3: 

 
Input: root = []
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 100]. 
 -100 <= Node.val <= 100 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 1179
3 👎 924

*/
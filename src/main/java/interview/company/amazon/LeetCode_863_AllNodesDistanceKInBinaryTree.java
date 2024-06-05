package interview.company.amazon;

import leetcode.util.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
  *@Question:  863. All Nodes Distance K in Binary Tree     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 59.18%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

/**
 * 这个问题可以通过两个步骤来解决：首先，我们需要找到目标节点，并记录每个节点到目标节点的距离；然后，我们进行深度优先搜索，找到距离目标节点为 K 的所有节点。
 *
 * ### 解题思路：
 *
 * 1. **找到目标节点并记录距离**：
 *    - 我们首先递归地在二叉树中查找目标节点。如果当前节点为目标节点，则记录其距离为0，并返回0。
 *    - 如果当前节点不是目标节点，则递归地在左右子树中查找目标节点。如果左子树中找到目标节点，则将当前节点到目标节点的距离记录在哈希表中，并返回左子树的距离加1。如果右子树中找到目标节点，则同样将当前节点到目标节点的距离记录在哈希表中，并返回右子树的距离加1。
 *    - 如果左右子树都没有找到目标节点，则返回-1表示未找到。
 *
 * 2. **深度优先搜索**：
 *    - 我们从根节点开始进行深度优先搜索。对于每个节点，我们首先检查它是否在哈希表中，如果在，则更新距离值为哈希表中记录的距离值。
 *    - 然后，如果当前节点与目标节点的距离等于 K，则将当前节点的值添加到结果列表中。
 *    - 接着，我们递归地搜索当前节点的左右子节点，并将距离值加1传递给它们。
 *    - 最后，我们返回结果列表。
 *
 * ### 时间复杂度分析：
 *
 * - **找到目标节点并记录距离**：这个过程需要对二叉树进行一次遍历，时间复杂度为 O(N)，其中 N 是二叉树中的节点数量。
 * - **深度优先搜索**：在最坏情况下，我们需要访问所有节点，时间复杂度为 O(N)。
 * - 因此，总体时间复杂度为 O(N)。
 *
 * ### 空间复杂度分析：
 *
 * - 我们使用了哈希表来存储节点到目标节点的距离，空间复杂度为 O(N)。
 * - 递归调用栈的深度取决于树的高度，对于一个平衡二叉树，空间复杂度是 O(logN)，对于一个不平衡的二叉树，空间复杂度是 O(N)。
 * - 因此，总体空间复杂度为 O(N)。
 */

public class LeetCode_863_AllNodesDistanceKInBinaryTree{

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
    class Solution {
        // 使用哈希表来存储每个节点到目标节点的距离
        Map<TreeNode, Integer> map = new HashMap<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            List<Integer> res = new LinkedList<>();
            // 首先找到目标节点并记录目标节点到根节点的距离
            find(root, target);
            // 进行深度优先搜索，找出距离目标节点为 K 的所有节点
            dfs(root, target, K, map.get(root), res);
            return res;
        }

        // 递归函数，用于找到目标节点，并记录目标节点到当前节点的距离
        private int find(TreeNode root, TreeNode target) {
            if (root == null) return -1;
            if (root == target) {
                map.put(root, 0); // 将目标节点到自身的距离设为0
                return 0;
            }
            int left = find(root.left, target); // 在左子树中找目标节点
            if (left >= 0) {
                map.put(root, left + 1); // 将当前节点到目标节点的距离记录在哈希表中
                return left + 1; // 返回目标节点到当前节点的距离
            }
            int right = find(root.right, target); // 在右子树中找目标节点
            if (right >= 0) {
                map.put(root, right + 1); // 将当前节点到目标节点的距离记录在哈希表中
                return right + 1; // 返回目标节点到当前节点的距离
            }
            return -1; // 如果左右子树都没有找到目标节点，则返回-1表示未找到
        }

        // 递归函数，用于深度优先搜索，找到距离目标节点为 K 的所有节点
        private void dfs(TreeNode root, TreeNode target, int K, int length, List<Integer> res) {
            if (root == null) return;
            if (map.containsKey(root)) length = map.get(root); // 如果当前节点在哈希表中，则更新距离值
            if (length == K) res.add(root.val); // 如果距离目标节点为 K，则将当前节点添加到结果列表中
            dfs(root.left, target, K, length + 1, res); // 递归搜索左子节点
            dfs(root.right, target, K, length + 1, res); // 递归搜索右子节点
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_863_AllNodesDistanceKInBinaryTree().new Solution();
        // 测试代码待添加
    }
}

/**
Given the root of a binary tree, the value of a target node target, and an 
integer k, return an array of the values of all nodes that have a distance k from 
the target node. 

 You can return the answer in any order. 

 
 Example 1: 
 
 
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5)
 have values 7, 4, and 1.
 

 Example 2: 

 
Input: root = [1], target = 1, k = 3
Output: []
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 500]. 
 0 <= Node.val <= 500 
 All the values Node.val are unique. 
 target is the value of one of the nodes in the tree. 
 0 <= k <= 1000 
 

 Related Topics Hash Table Tree Depth-First Search Breadth-First Search Binary 
Tree 👍 10820 👎 221

*/
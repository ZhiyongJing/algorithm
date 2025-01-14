package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *@Question:  652. Find Duplicate Subtrees
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 62.69%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 题目描述：
 * 给定一棵二叉树，找出所有重复的子树，并返回这些重复子树的根节点。对于每个重复子树，你需要返回该子树的根节点。
 * 两棵子树被认为是重复的，如果它们的结构和节点值完全相同。
 *
 * 示例：
 * 输入：
 *      1
 *     / \
 *    2   3
 *   /   / \
 *  4   2   4
 *     /
 *    4
 * 输出：
 *  [2,4]
 *  解释：
 *  这两棵子树在给定的二叉树中是重复的。
 *
 * 提示：
 * 1. 树中的节点数目范围是 [1, 1000]。
 * 2. 每个节点的值都是整数。
 */

/**
 * 解题思路：
 *
 * 1. 这道题的核心在于如何高效地找到二叉树中重复的子树。我们可以通过序列化每一棵子树，将其表示为一个字符串来进行匹配。
 * 2. 使用一个哈希表来存储每棵子树的序列化结果，并记录出现次数。通过遍历整棵树，序列化每一棵子树并检查它的出现次数。
 * 3. 通过深度优先搜索（DFS）递归遍历二叉树的每个节点，将每一棵子树表示为一个字符串，记录在哈希表中，并返回每棵子树的唯一ID。
 * 4. 通过这种方式，我们能迅速判断出哪些子树重复，并且返回这些子树的根节点。

 * 详细步骤：
 *
 * 步骤 1: 初始化哈希表 `tripletToID`，用于存储每棵子树的序列化结果及其唯一ID；初始化哈希表 `cnt`，用于记录每个子树ID出现的次数；
 * 初始化结果列表 `res`，用于存储重复子树的根节点。
 *
 * 步骤 2: 使用DFS遍历整棵树，对每个节点的子树进行序列化。序列化方式是：首先递归序列化左子树，再序列化当前节点值，最后递归序列化右子树，
 * 将这些信息连接起来，形成一个字符串（即子树的序列化表示）。
 *
 * 步骤 3: 如果某个子树的序列化表示在 `tripletToID` 中不存在，就将其加入并赋予一个新的ID。如果已经存在，获取该子树的ID，并更新它在 `cnt` 中的计数。
 *
 * 步骤 4: 如果某个子树的ID在 `cnt` 中的计数为2，说明这个子树重复出现过，应该将该节点加入结果列表 `res` 中。
 *
 * 步骤 5: 完成整个树的遍历后，返回 `res`，其中包含了所有重复子树的根节点。

 * 示例：
 * 对于树结构：
 *      1
 *     / \
 *    2   3
 *   /   / \
 *  4   2   4
 *     /
 *    4
 *
 * 经过DFS序列化后的过程：
 * - 根节点1的序列化结果为 "4,2,4,1,3,2,4"
 * - 2的子树序列化为 "4,2,4"
 * - 4的子树序列化为 "4"
 * 由于 "4" 和 "4,2,4" 都出现了两次，分别表示重复的子树，程序会返回节点值为2和4的子树。

 *
 * 时间复杂度分析：
 * - 对于每一个节点，我们都要进行一次递归调用来序列化它的子树，所以时间复杂度是O(N)，其中N是树中节点的总数。
 *
 * 空间复杂度分析：
 * - 哈希表用于存储每个子树的序列化字符串，空间复杂度为O(N)，因为每棵子树可能占用一个唯一的序列化表示。
 * - 递归调用栈的最大深度为树的高度，最坏情况下是O(N)。
 *
 * 因此，总的时间复杂度为O(N)，空间复杂度为O(N)。
 */


public class LeetCode_652_FindDuplicateSubtrees{

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
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            // 初始化结果列表，用于存储重复子树的根节点
            List<TreeNode> res = new LinkedList<>();
            // 调用辅助函数进行DFS遍历，同时使用两个哈希表存储序列化和计数信息
            traverse(root, new HashMap<>(), new HashMap<>(), res);
            return res;
        }

        /**
         * 递归遍历树并对子树进行序列化
         * @param node 当前节点
         * @param tripletToID 子树序列化字符串与唯一ID的映射
         * @param cnt 子树唯一ID出现的次数
         * @param res 存储结果的列表
         * @return 当前子树的唯一ID
         */
        public int traverse(TreeNode node, Map<String, Integer> tripletToID,
                            Map<Integer, Integer> cnt, List<TreeNode> res) {
            if (node == null) {
                // 空节点返回ID为0
                return 0;
            }
            // 对当前节点的子树进行序列化：左子树ID,当前节点值,右子树ID
            String triplet = traverse(node.left, tripletToID, cnt, res) + "," + node.val +
                    "," + traverse(node.right, tripletToID, cnt, res);
            // 如果该序列化字符串未出现过，则分配一个新的ID
            if (!tripletToID.containsKey(triplet)) {
                tripletToID.put(triplet, tripletToID.size() + 1);
            }
            // 获取当前子树的唯一ID
            int id = tripletToID.get(triplet);
            // 更新该ID的计数
            cnt.put(id, cnt.getOrDefault(id, 0) + 1);
            // 如果该子树刚好出现两次，将其根节点加入结果列表
            if (cnt.get(id) == 2) {
                res.add(node);
            }
            // 返回当前子树的ID
            return id;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_652_FindDuplicateSubtrees().new Solution();

        // 测试用例
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.right.right = new TreeNode(4);

        // 打印重复子树的根节点值
        List<TreeNode> duplicates = solution.findDuplicateSubtrees(root);
        System.out.println("重复子树的根节点值：");
        for (TreeNode node : duplicates) {
            System.out.println(node.val);
        }
    }
}

/**
Given the root of a binary tree, return all duplicate subtrees. 

 For each kind of duplicate subtrees, you only need to return the root node of 
any one of them. 

 Two trees are duplicate if they have the same structure with the same node 
values. 

 
 Example 1: 
 
 
Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]
 

 Example 2: 
 
 
Input: root = [2,1,1]
Output: [[1]]
 

 Example 3: 
 
 
Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]
 

 
 Constraints: 

 
 The number of the nodes in the tree will be in the range [1, 5000] 
 -200 <= Node.val <= 200 
 

 Related Topics Hash Table Tree Depth-First Search Binary Tree 👍 5918 👎 486

*/
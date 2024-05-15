package interview.company.amazon;

/**
  *@Question:  112. Path Sum     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 28.52%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N) if tree is not balanced, O(log(N)) for most case
 */

/**
 * 这个问题的解题思路有两种，一种是使用递归，另一种是使用迭代。我会分别解释这两种解法的思路和复杂度分析。
 *
 * ### 递归解法：
 *
 * 1. **递归函数设计**：我们定义一个递归函数 `hasPathSum(TreeNode root, int sum)`，它接收当前节点 `root` 和当前的目标和 `sum` 作为参数，返回布尔值表示是否存在根到叶子节点的路径，使得路径上所有节点值之和等于目标和 `sum`。
 *
 * 2. **递归终止条件**：如果当前节点 `root` 为空，说明到达了叶子节点的下一层，返回 `false`。如果当前节点是叶子节点，即左右子节点都为空，则判断当前的目标和 `sum` 是否等于当前节点的值，若相等返回 `true`，否则返回 `false`。
 *
 * 3. **递归过程**：递归地调用函数 `hasPathSum` 分别检查当前节点的左右子节点，并将目标和减去当前节点的值传递给它们。最终返回左右子树中是否存在路径和为 `sum - root.val` 的情况。
 *
 * 4. **时间复杂度**：每个节点都被访问一次，所以时间复杂度为 O(N)，其中 N 是树中节点的数量。
 *
 * 5. **空间复杂度**：递归调用栈的深度取决于树的高度，对于一个平衡二叉树，空间复杂度是 O(logN)，对于一个不平衡的二叉树，空间复杂度是 O(N)。
 *
 * ### 迭代解法：
 *
 * 1. **使用栈进行迭代**：我们可以使用一个栈来辅助迭代。栈中存储的是一对节点和剩余目标和的值。
 *
 * 2. **迭代过程**：从根节点开始，将根节点和目标和入栈。然后，不断地从栈中弹出节点，并对其左右子节点进行处理，更新剩余目标和。如果遇到叶子节点，则判断剩余目标和是否为0，若为0则说明找到了路径和等于目标和的路径，返回 `true`；若不为0，则继续遍历。直到栈为空，或者找到路径和等于目标和的路径。
 *
 * 3. **时间复杂度**：同样是因为每个节点都被访问一次，所以时间复杂度为 O(N)，其中 N 是树中节点的数量。
 *
 * 4. **空间复杂度**：使用了额外的栈来存储节点和剩余目标和，所以空间复杂度为 O(N)。
 *
 * 综上所述，两种方法的时间复杂度都是 O(N)，空间复杂度取决于树的结构，对于平衡二叉树是 O(logN)，对于不平衡的二叉树是 O(N)。
 */
public class LeetCode_112_PathSum{

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
        // Solution 1：递归解法
        public boolean hasPathSum(TreeNode root, int sum) {
            // 如果节点为空，返回 false
            if (root == null) return false;

            // 减去当前节点的值
            sum -= root.val;
            // 如果当前节点是叶子节点，并且sum为0，返回true
            if ((root.left == null) && (root.right == null)) return (sum == 0);
            // 递归检查左右子树
            return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
        }

        // Solution 2：迭代解法
        public boolean hasPathSum2(TreeNode root, int sum) {
            LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
            stack.add(new Pair(root, sum));
            while(!stack.isEmpty()){
                Pair<TreeNode, Integer> p = stack.poll();
                TreeNode node = p.getKey();
                int remain = p.getValue();
                if(node != null){
                    // 减去当前节点的值
                    remain -= node.val;
                    // 如果当前节点是叶子节点，并且remain为0，返回true
                    if(node.left == null && node.right == null && remain == 0) return true;
                    // 将左右子节点和对应的剩余值加入栈中
                    stack.add(new Pair(node.left, remain));
                    stack.add(new Pair(node.right, remain));
                }
            }
            // 如果迭代完都没找到，返回false
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_112_PathSum.Solution solution = new LeetCode_112_PathSum().new Solution();
        // 测试代码待添加
    }
}

/**
Given the root of a binary tree and an integer targetSum, return true if the 
tree has a root-to-leaf path such that adding up all the values along the path 
equals targetSum. 

 A leaf is a node with no children. 

 
 Example 1: 
 
 
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.
 

 Example 2: 
 
 
Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.
 

 Example 3: 

 
Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [0, 5000]. 
 -1000 <= Node.val <= 1000 
 -1000 <= targetSum <= 1000 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 9582
 👎 1090

*/
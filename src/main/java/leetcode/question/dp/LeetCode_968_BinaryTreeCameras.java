package leetcode.question.dp;

import leetcode.util.TreeNode;

/**
 *@Question:  968. Binary Tree Cameras
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.81999999999999%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(H)
 */
### 解题思路

#### 1. **问题描述**

我们需要在一棵二叉树中放置最少数量的摄像头，以确保每个节点都被监控到。每个摄像头可以监控其所在节点和其左右子节点。我们需要确定最少的摄像头数量，使得所有节点都被监控。

        #### 2. **状态定义**

我们使用动态规划来解决这个问题。定义每个节点的三种状态：
        - **状态 0**：当前节点不放摄像头，但其子树已被覆盖。
        - **状态 1**：当前节点及其子树被覆盖，但当前节点上没有摄像头。
        - **状态 2**：当前节点及其子树被覆盖，且当前节点上放置了摄像头。

        #### 3. **递归思路**

对于每个节点，我们可以通过递归地处理其左子树和右子树来确定其状态：
        - **状态 0**：当前节点不放摄像头的情况下，其左子树和右子树必须各自处于状态 1 或状态 2，因为当前节点的子树必须被监控到。如果节点不放摄像头，则其子树必须至少在状态 1（即子树的根节点被覆盖，且子树内部可能放有摄像头）。
        - **状态 1**：当前节点及其子树被覆盖，但当前节点上没有摄像头。这时，当前节点的子树至少有一个子节点需要放置摄像头，因此，我们需要选择左右子树中最少摄像头的情况来确定当前节点的状态。
        - **状态 2**：当前节点放置摄像头的情况下，当前节点及其左右子树都被覆盖。这意味着我们在当前节点上放置一个摄像头，同时考虑其左右子树的最小状态（即子树的根节点不需要再放置摄像头）。

        #### 4. **最小摄像头数计算**

在递归计算每个节点的状态后，我们需要选择根节点的最优解，即最少摄像头的数量。根节点的最优解是其状态 1 和状态 2 的最小值。状态 1 表示根节点不放摄像头但其子树被覆盖的情况，状态 2 表示根节点放置摄像头的情况。

        ### 时间复杂度

- **递归遍历**：每个节点被访问一次，并进行递归计算其左右子树。对于每个节点，递归的时间复杂度为 `O(1)`。因此，总体时间复杂度为 `O(N)`，其中 `N` 是树中的节点数。

        ### 空间复杂度

- **递归调用栈**：递归的深度为树的高度。在最坏情况下（例如链状树），递归深度为 `O(N)`。因此，空间复杂度为 `O(N)`。
        - **状态存储**：只需要存储每个节点的状态，额外的空间复杂度为 `O(N)`。总体上，空间复杂度主要由递归调用栈和状态存储组成，为 `O(N)`。

public class LeetCode_968_BinaryTreeCameras{

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

        // 计算最小的监控摄像头数量
        public int minCameraCover(TreeNode root) {
            int[] ans = solve(root);
            // 选择根节点的最优解
            return Math.min(ans[1], ans[2]);
        }

        // 返回一个长度为3的数组
        // 0: 根节点不放摄像头，但其子树已被覆盖
        // 1: 根节点及其子树被覆盖，但根节点上没有摄像头
        // 2: 根节点及其子树被覆盖，且根节点上放置了摄像头
        public int[] solve(TreeNode node) {
            if (node == null)
                // 空节点返回初始状态
                return new int[]{0, 0, 99999};

            // 递归计算左子树和右子树的结果
            int[] L = solve(node.left);
            int[] R = solve(node.right);

            // 左子树和右子树的最小值（不放摄像头但子树被覆盖，或放置摄像头）
            int mL12 = Math.min(L[1], L[2]);
            int mR12 = Math.min(R[1], R[2]);

            // 根节点不放摄像头的情况：左子树和右子树各自放置摄像头的最小值
            int d0 = L[1] + R[1];
            // 根节点放置摄像头的情况：左子树和右子树中最小值加上当前摄像头的值
            int d1 = Math.min(L[2] + mR12, R[2] + mL12);
            // 根节点放置摄像头的情况：左子树和右子树的最小值加上当前摄像头的值
            int d2 = 1 + Math.min(L[0], mL12) + Math.min(R[0], mR12);

            // 返回根节点不放摄像头、根节点覆盖子树不放摄像头、根节点放摄像头的最小值
            return new int[]{d0, d1, d2};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_968_BinaryTreeCameras().new Solution();

        // 创建测试用例
        TreeNode root1 = new TreeNode(0,
                new TreeNode(0, new TreeNode(0), new TreeNode(0)),
                new TreeNode(0)
        );
        // 预期输出: 1 (根节点放置一个摄像头即可覆盖所有节点)
        System.out.println("Minimum number of cameras needed: " + solution.minCameraCover(root1));

        TreeNode root2 = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6))
        );
        // 预期输出: 2 (需要两个摄像头)
        System.out.println("Minimum number of cameras needed: " + solution.minCameraCover(root2));

        TreeNode root3 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, new TreeNode(7), null),
                        new TreeNode(5, null, new TreeNode(8))
                ),
                new TreeNode(3,
                        new TreeNode(6),
                        null
                )
        );
        // 预期输出: 3 (需要三个摄像头)
        System.out.println("Minimum number of cameras needed: " + solution.minCameraCover(root3));
    }
}

/**
You are given the root of a binary tree. We install cameras on the tree nodes 
where each camera at a node can monitor its parent, itself, and its immediate 
children. 

 Return the minimum number of cameras needed to monitor all nodes of the tree. 

 
 Example 1: 
 
 
Input: root = [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
 

 Example 2: 
 
 
Input: root = [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. 
The above image shows one of the valid configurations of camera placement.
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 1000]. 
 Node.val == 0 
 

 Related Topics Dynamic Programming Tree Depth-First Search Binary Tree 👍 5331 
👎 77

*/
package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.*;
/**
 *@Question:  2385. Amount of Time for Binary Tree to Be Infected
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.35%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求计算从二叉树的某个节点开始，感染整个二叉树所需的时间。我们可以使用两种方法来解决这个问题：广度优先搜索 (BFS) 和深度优先搜索 (DFS)。
 *
 * #### 方法1：广度优先搜索 (BFS)
 * 1. **将二叉树转换为图**：
 *    - 使用邻接表表示图，每个节点与其相邻的节点相连。
 *    - 遍历二叉树，构建一个哈希表，键为节点值，值为与该节点相连的节点集合。
 *
 * 2. **从起始节点进行BFS遍历**：
 *    - 使用一个队列来执行BFS，从起始节点开始。
 *    - 每一轮遍历代表一次传播，每遍历完一层，计数器加一。
 *    - 记录访问过的节点，避免重复访问。
 *
 * 3. **计算最大传播时间**：
 *    - BFS遍历完所有节点，计数器的值减去1即为所需的时间，因为初始状态也被计入了一次传播。
 *
 * #### 方法2：深度优先搜索 (DFS)
 * 1. **使用递归遍历二叉树**：
 *    - 定义一个递归函数，返回从当前节点到叶子节点的最大深度。
 *    - 通过递归遍历，计算每个子树的最大深度。
 *
 * 2. **处理起始节点**：
 *    - 当遍历到起始节点时，记录从该节点到所有叶子节点的最大距离。
 *
 * 3. **计算最大距离**：
 *    - 通过递归计算每个节点到起始节点的距离，更新最大传播时间。
 *
 * ### 时间和空间复杂度
 *
 * #### 方法1：广度优先搜索 (BFS)
 * - **时间复杂度**：O(N)
 *   - 每个节点和每条边都被访问一次，因此时间复杂度是O(N)，其中N是二叉树的节点数。
 * - **空间复杂度**：O(N)
 *   - 最坏情况下，队列需要存储所有节点，即O(N)。
 *   - 用于存储邻接表的哈希表的空间复杂度也是O(N)。
 *
 * #### 方法2：深度优先搜索 (DFS)
 * - **时间复杂度**：O(N)
 *   - 每个节点都被访问一次，因此时间复杂度是O(N)。
 * - **空间复杂度**：O(H)
 *   - 递归调用的栈空间取决于二叉树的高度H，最坏情况下空间复杂度是O(N)（树为线性结构）。
 *
 * 两种方法在时间复杂度上都是线性的，但在空间复杂度上，BFS方法需要额外的空间来存储邻接表和队列，而DFS方法主要依赖于递归调用的栈空间。
 */

public class LeetCode_2385_AmountOfTimeForBinaryTreeToBeInfected{

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
        // Solution1: 将树转换为图，然后找到从起始点到其他顶点的最大距离
        public int amountOfTime(TreeNode root, int start) {
            // 使用 Map 存储图的邻接表
            Map<Integer, Set<Integer>> map = new HashMap<>();
            // 将二叉树转换为图
            convert(root, 0, map);
            // 使用队列进行广度优先搜索
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            int minute = 0;
            // 使用集合记录访问过的节点
            Set<Integer> visited = new HashSet<>();
            visited.add(start);

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                while (levelSize > 0) {
                    int current = queue.poll();
                    // 遍历当前节点的邻接点
                    for (int num : map.get(current)) {
                        if (!visited.contains(num)) {
                            visited.add(num);
                            queue.add(num);
                        }
                    }
                    levelSize--;
                }
                minute++;
            }
            return minute - 1;
        }

        // 将二叉树转换为图的邻接表表示
        public void convert(TreeNode current, int parent, Map<Integer, Set<Integer>> map) {
            if (current == null) {
                return;
            }
            if (!map.containsKey(current.val)) {
                map.put(current.val, new HashSet<>());
            }
            Set<Integer> adjacentList = map.get(current.val);
            if (parent != 0) {
                adjacentList.add(parent);
            }
            if (current.left != null) {
                adjacentList.add(current.left.val);
            }
            if (current.right != null) {
                adjacentList.add(current.right.val);
            }
            convert(current.left, current.val, map);
            convert(current.right, current.val, map);
        }

        // Solution2: 深度优先搜索
        private int maxDistance = 0;

        public int amountOfTime2(TreeNode root, int start) {
            traverse(root, start);
            return maxDistance;
        }

        // 递归遍历二叉树
        public int traverse(TreeNode root, int start) {
            int depth = 0;
            if (root == null) {
                return depth;
            }

            int leftDepth = traverse(root.left, start);
            int rightDepth = traverse(root.right, start);

            if (root.val == start) {
                maxDistance = Math.max(leftDepth, rightDepth);
                depth = -1;
            } else if (leftDepth >= 0 && rightDepth >= 0) {
                depth = Math.max(leftDepth, rightDepth) + 1;
            } else {
                int distance = Math.abs(leftDepth) + Math.abs(rightDepth);
                maxDistance = Math.max(maxDistance, distance);
                depth = Math.min(leftDepth, rightDepth) - 1;
            }

            return depth;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_2385_AmountOfTimeForBinaryTreeToBeInfected().new Solution();
        // 测试样例
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        int start = 3;
        System.out.println(solution.amountOfTime(root, start)); // 应输出2
    }
}

/**
You are given the root of a binary tree with unique values, and an integer 
start. At minute 0, an infection starts from the node with value start. 

 Each minute, a node becomes infected if: 

 
 The node is currently uninfected. 
 The node is adjacent to an infected node. 
 

 Return the number of minutes needed for the entire tree to be infected. 

 
 Example 1: 
 
 
Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.
 

 Example 2: 
 
 
Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 10⁵]. 
 1 <= Node.val <= 10⁵ 
 Each node has a unique value. 
 A node with a value of start exists in the tree. 
 

 Related Topics Hash Table Tree Depth-First Search Breadth-First Search Binary 
Tree 👍 2640 👎 63

*/
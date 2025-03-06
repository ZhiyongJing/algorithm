package leetcode.question.dfs;
/**
 *@Question:  427. Construct Quad Tree
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.24%
 *@Time Complexity: O(N^2)  (需要检查整个 `N x N` 网格的所有元素)
 *@Space Complexity: O(logN) (递归深度最多为 logN)
 */
/**
 * 题目描述：
 * LeetCode 427. Construct Quad Tree
 * 给定一个 `N x N` 的二进制矩阵 `grid`，要求构造 **四叉树 (QuadTree)** 结构：
 * - **四叉树** 是一种递归分割的数据结构，它将 `N x N` 的区域划分成 4 个子区域，直到所有值相同。
 * - 每个 `Node` 具有以下属性：
 *   - `val`: 该节点是否表示 `1`（仅在叶子节点有效）。
 *   - `isLeaf`: 是否为叶子节点（如果 `true`，则 `val` 有效）。
 *   - `topLeft`, `topRight`, `bottomLeft`, `bottomRight`: 代表四个子区域。
 *
 * 目标是构造四叉树，使得：
 * - **如果某个子区域的所有值都相同**（全 0 或全 1），则合并成一个**叶子节点**。
 * - **否则，该区域继续细分**，直到所有叶子节点都是全 0 或全 1。
 *
 * **示例 1：**
 * ```
 * 输入:
 * [[1,1,0,0],
 *  [1,1,0,0],
 *  [0,0,1,1],
 *  [0,0,1,1]]
 *
 * 输出:
 * 返回一个非叶子根节点，它的四个子节点分别是两个 `1` 叶子节点和两个 `0` 叶子节点。
 * ```
 *
 * **示例 2：**
 * ```
 * 输入:
 * [[1,1],
 *  [1,1]]
 *
 * 输出:
 * 该矩阵全为 `1`，直接返回一个 `val = true, isLeaf = true` 的叶子节点。
 * ```
 */

/**
 * 解题思路：
 * 由于矩阵 `grid` 需要**递归拆分成 4 个区域**，四叉树的构造过程适用于 **递归 + 分治**。
 *
 * 1. **递归拆分区域**
 *    - 设 `solve(grid, x1, y1, length)` 处理以 `(x1, y1)` 为左上角，边长为 `length` 的子区域。
 *    - **基准情况**：
 *      - 如果 `length == 1`，说明该区域是 `1x1`，直接创建叶子节点。
 *    - **递归划分**：
 *      - `topLeft` = `solve(grid, x1, y1, length/2)` （左上区域）
 *      - `topRight` = `solve(grid, x1, y1 + length/2, length/2)` （右上区域）
 *      - `bottomLeft` = `solve(grid, x1 + length/2, y1, length/2)` （左下区域）
 *      - `bottomRight` = `solve(grid, x1 + length/2, y1 + length/2, length/2)` （右下区域）
 *
 * 2. **检查是否能合并**
 *    - 如果 `topLeft`，`topRight`，`bottomLeft`，`bottomRight` 都是叶子节点，且 `val` 相同：
 *      - 说明该区域是**全 0 或全 1**，可合并为一个叶子节点。
 *      - 例如：
 *        ```
 *        [[1,1],
 *         [1,1]]
 *        ```
 *        递归到 `1x1` 之后，发现四个区域都是 `1`，则返回 `val = true, isLeaf = true` 叶子节点。
 *
 * 3. **否则，创建非叶子节点**
 *    - 例如：
 *      ```
 *      [[1,1,0,0],
 *       [1,1,0,0],
 *       [0,0,1,1],
 *       [0,0,1,1]]
 *      ```
 *      - 递归后，`topLeft = (1, true), topRight = (0, true), bottomLeft = (0, true), bottomRight = (1, true)`
 *      - 四个子节点值不全相同，因此创建一个 `isLeaf = false` 的非叶子节点，并连接 4 个子区域。
 *
 * 4. **返回根节点**
 *    - `construct(grid)` 直接调用 `solve(grid, 0, 0, grid.length)`，返回根节点。
 *
 * **举例分析**
 * 输入：
 * ```
 * grid = [[1,1,0,0],
 *         [1,1,0,0],
 *         [0,0,1,1],
 *         [0,0,1,1]]
 * ```
 * **递归过程：**
 * 1. 拆分 `grid`：
 *    ```
 *    topLeft  = [[1,1],   topRight  = [[0,0],
 *                 [1,1]]               [0,0]]
 *
 *    bottomLeft = [[0,0],  bottomRight = [[1,1],
 *                   [0,0]]                [1,1]]
 *    ```
 * 2. 由于四个子区域均为全 0 或全 1，可分别合并为叶子节点。
 * 3. 根节点 `isLeaf = false`，连接 `topLeft, topRight, bottomLeft, bottomRight` 作为四叉树。
 * 4. **最终返回该四叉树的根节点。**
 */

/**
 * 时间和空间复杂度分析：
 *
 * 1. **时间复杂度：O(N^2)**
 *    - 递归过程中，每个 `N x N` 矩阵最多被拆分成 `4` 个 `N/2 x N/2` 矩阵。
 *    - 计算 `O(N^2)` 个子矩阵，每个子矩阵最多需要 `O(1)` 时间合并或创建节点。
 *    - **总复杂度 O(N^2)**。
 *
 * 2. **空间复杂度：O(logN)**
 *    - 递归深度最多 `logN`（每次递归 `length` 减半）。
 *    - 递归调用栈消耗 `O(logN)` 额外空间。
 *    - **总空间复杂度 O(logN)**。
 */


public class LeetCode_427_ConstructQuadTree{
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a QuadTree node.
*/

    class Solution {
        private Node solve(int[][] grid, int x1, int y1, int length) {
            // 如果当前子网格的大小为 1x1，则直接返回一个叶子节点
            if (length == 1) {
                return new Node(grid[x1][y1] == 1, true);
            }

            // 递归处理四个子区域
            Node topLeft = solve(grid, x1, y1, length / 2);
            Node topRight = solve(grid, x1, y1 + length / 2, length / 2);
            Node bottomLeft = solve(grid, x1 + length / 2, y1, length / 2);
            Node bottomRight = solve(grid, x1 + length / 2, y1 + length / 2, length / 2);

            // 如果四个子区域都是叶子节点，且值相同，则可以合并为一个叶子节点
            if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                    && topLeft.val == topRight.val && topRight.val == bottomLeft.val
                    && bottomLeft.val == bottomRight.val) {
                return new Node(topLeft.val, true);
            }

            // 否则，创建一个非叶子节点，并连接四个子节点
            return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
        }

        public Node construct(int[][] grid) {
            return solve(grid, 0, 0, grid.length);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_427_ConstructQuadTree().new Solution();

        // 测试用例 1
        int[][] grid1 = {
                {1, 1},
                {1, 1}
        };
        Node root1 = solution.construct(grid1);
        System.out.println("Test Case 1: " + root1.isLeaf + ", " + root1.val);
        // 期望输出: true, 1 (整个网格相同，合并成一个叶子节点)

        // 测试用例 2
        int[][] grid2 = {
                {0, 1},
                {1, 0}
        };
        Node root2 = solution.construct(grid2);
        System.out.println("Test Case 2: " + root2.isLeaf);
        // 期望输出: false (网格有不同值，需要拆分)

        // 测试用例 3
        int[][] grid3 = {
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };
        Node root3 = solution.construct(grid3);
        System.out.println("Test Case 3: " + root3.isLeaf);
        // 期望输出: false (整体不同，需要拆分成四个子区域)
    }
}

/**
Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a 
Quad-Tree. 

 Return the root of the Quad-Tree representing grid. 

 A Quad-Tree is a tree data structure in which each internal node has exactly 
four children. Besides, each node has two attributes: 

 
 val: True if the node represents a grid of 1's or False if the node represents 
a grid of 0's. Notice that you can assign the val to True or False when isLeaf 
is False, and both are accepted in the answer. 
 isLeaf: True if the node is a leaf node on the tree or False if the node has 
four children. 
 

 
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
} 

 We can construct a Quad-Tree from a two-dimensional area using the following 
steps: 

 
 If the current grid has the same value (i.e all 1's or all 0's) set isLeaf 
True and set val to the value of the grid and set the four children to Null and 
stop. 
 If the current grid has different values, set isLeaf to False and set val to 
any value and divide the current grid into four sub-grids as shown in the photo. 
 Recurse for each of the children with the proper sub-grid. 
 
 
 If you want to know more about the Quad-Tree, you can refer to the wiki. 

 Quad-Tree format: 

 You don't need to read this section for solving the problem. This is only if 
you want to understand the output format here. The output represents the 
serialized format of a Quad-Tree using level order traversal, where null signifies a 
path terminator where no node exists below. 

 It is very similar to the serialization of the binary tree. The only 
difference is that the node is represented as a list [isLeaf, val]. 

 If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf,
 val] and if the value of isLeaf or val is False we represent it as 0. 

 
 Example 1: 
 
 
Input: grid = [[0,1],[1,0]]
Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
Explanation: The explanation of this example is shown below:
Notice that 0 represents False and 1 represents True in the photo representing 
the Quad-Tree.

 

 Example 2: 

 

 
Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,
1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,
1]]
Explanation: All values in the grid are not the same. We divide the grid into 
four sub-grids.
The topLeft, bottomLeft and bottomRight each has the same value.
The topRight have different values so we divide it into 4 sub-grids where each 
has the same value.
Explanation is shown in the photo below:

 

 
 Constraints: 

 
 n == grid.length == grid[i].length 
 n == 2ˣ where 0 <= x <= 6 
 

 Related Topics Array Divide and Conquer Tree Matrix 👍 1612 👎 1886

*/
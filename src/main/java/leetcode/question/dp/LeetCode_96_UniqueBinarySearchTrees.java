package leetcode.question.dp;
/**
 *@Question:  96. Unique Binary Search Trees
 *@Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 57.33%
 *@Time Complexity: O(N^2)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * **问题描述**：计算可以由 `n` 个节点组成的所有不同的二叉搜索树（BST）的数量。
 *
 * #### 解题思路
 *
 * 1. **动态规划**：
 *    - **目标**：计算包含 `n` 个节点的二叉搜索树的不同结构数。
 *    - **定义**：使用动态规划数组 `G`，其中 `G[i]` 表示包含 `i` 个节点的所有不同的二叉搜索树的数量。
 *    - **初始条件**：
 *      - `G[0] = 1`：空树只有一种结构。
 *      - `G[1] = 1`：只有一个节点的树也只有一种结构。
 *    - **状态转移**：
 *      - 对于每一个节点数 `i`，我们可以选择每一个节点 `j`（1 ≤ `j` ≤ `i`）作为根节点。
 *      - 选择 `j` 作为根节点时，左子树包含 `j-1` 个节点，右子树包含 `i-j` 个节点。
 *      - 计算不同的左子树和右子树组合数，更新 `G[i]`：
 *        - `G[i] += G[j - 1] * G[i - j]`。
 *      - `G[j - 1]` 表示左子树的可能结构数，`G[i - j]` 表示右子树的可能结构数。组合这两部分得到总的结构数。
 *
 * #### 时间复杂度
 *
 * - **时间复杂度**：O(N^2)。
 *   - 外层循环从 2 到 `n`，共进行 `n - 1` 次迭代。
 *   - 内层循环从 1 到当前的 `i`，最多迭代 `i` 次。
 *   - 因此，总的时间复杂度是 O(N^2)。
 *
 * #### 空间复杂度
 *
 * - **空间复杂度**：O(N)。
 *   - 使用一个长度为 `n + 1` 的数组 `G` 来存储从 0 到 `n` 的所有节点数的不同结构数。
 *   - 数组的长度是 `n + 1`，因此空间复杂度是 O(N)。
 */

public class LeetCode_96_UniqueBinarySearchTrees{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        public int numTrees(int n) {
            // G[i] 表示包含 i 个节点的二叉搜索树的不同结构数
            int[] G = new int[n + 1];  // 创建一个长度为 n+1 的数组来存储结果
            G[0] = 1;  // 空树的情况只有一种
            G[1] = 1;  // 单节点树的情况也只有一种

            // 遍历每个节点数 i，从 2 到 n
            for (int i = 2; i <= n; ++i) {
                // 对每一个节点 i，计算其可能的二叉搜索树的总数
                for (int j = 1; j <= i; ++j) {
                    // G[i] 由不同的根节点 j 的情况组合而成
                    G[i] += G[j - 1] * G[i - j];  // 左子树和右子树的组合数
                }
            }
            return G[n];  // 返回包含 n 个节点的二叉搜索树的不同结构数
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_96_UniqueBinarySearchTrees().new Solution();
        // 测试代码
        int n = 3;  // 测试用例：节点数量为 3
        int result = solution.numTrees(n);  // 计算包含 3 个节点的二叉搜索树的不同结构数
        System.out.println("Number of unique BSTs with " + n + " nodes: " + result);  // 输出结果
    }
}

/**
Given an integer n, return the number of structurally unique BST's (binary 
search trees) which has exactly n nodes of unique values from 1 to n. 

 
 Example 1: 
 
 
Input: n = 3
Output: 5
 

 Example 2: 

 
Input: n = 1
Output: 1
 

 
 Constraints: 

 
 1 <= n <= 19 
 

 Related Topics Math Dynamic Programming Tree Binary Search Tree Binary Tree 👍 
10355 👎 405

*/
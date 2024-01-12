package leetcode.question.bfs;

/**
 * @Question: 310. Minimum Height Trees
 * @Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 34.29%
 * @Time  Complexity: O(N)
 * @Space Complexity: O(N)
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ### 算法思路
 *
 * **问题背景：** 给定一个无向图，节点的编号从 0 到 n-1。要求找到一些节点，使得它们删除后，图的高度
 * （从任何一个剩余节点到任何其他剩余节点的最长简单路径的边数）最小。
 *
 * **算法步骤：**
 *
 * 1. **边缘情况处理：** 如果节点数 `n` 小于 2，直接返回所有节点，因为树的高度不会超过 1。
 *
 * 2. **建图：** 使用邻接表构建图，遍历边数组 `edges`，将每条边加入邻接表。
 *
 * 3. **初始化叶子节点：** 找到初始的叶子节点，即邻接表中度为 1 的节点。
 *
 * 4. **修剪树：** 不断修剪叶子节点，直到剩余节点数小于等于 2。每轮修剪都会移除叶子节点及其相邻的边，然后找到新的叶子节点。
 *
 * 5. **返回中心节点：** 最终剩余的节点即为树的中心节点，返回该节点列表。
 *
 * ### 时间复杂度
 *
 * - **建图：** O(E)，其中 E 为边的数量，遍历边数组 `edges`。
 * - **修剪树：** O(N)，其中 N 为节点数，最多需要修剪 N-2 次，每次修剪都需要遍历所有节点和相邻的边。
 *
 * 综合起来，总的时间复杂度为 O(E + N)。
 *
 * ### 空间复杂度
 *
 * - **邻接表：** O(E + N)，用于存储图的结构。
 * - **叶子节点列表：** O(N)，最多可能存储所有节点。
 *
 * 综合起来，总的空间复杂度为 O(E + N)。
 */

public class LeetCode_310_MinimumHeightTrees {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {

            // 边缘情况处理：如果节点数小于2，返回所有节点
            if (n < 2) {
                ArrayList<Integer> centroids = new ArrayList<>();
                for (int i = 0; i < n; i++)
                    centroids.add(i);
                return centroids;
            }

            // 使用邻接表构建图
            ArrayList<Set<Integer>> neighbors = new ArrayList<>();
            for (int i = 0; i < n; i++)
                neighbors.add(new HashSet<Integer>());
            System.out.println(neighbors);

            // 遍历边数组，建立邻接表
            for (int[] edge : edges) {
                Integer start = edge[0], end = edge[1];
                neighbors.get(start).add(end);
                neighbors.get(end).add(start);
            }
            System.out.println("neighbors is: "+ neighbors);

            // 初始化叶子节点列表
            ArrayList<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if (neighbors.get(i).size() == 1)
                    leaves.add(i);
            System.out.println("leaves is: " +leaves);
            // 不断修剪叶子节点直到找到树的中心节点
            int remainingNodes = n;
            while (remainingNodes > 2) {
                remainingNodes -= leaves.size();
                ArrayList<Integer> newLeaves = new ArrayList<>();

                // 移除当前叶子节点及其边
                for (Integer leaf : leaves) {
                    Integer neighbor = neighbors.get(leaf).iterator().next();
                    neighbors.get(neighbor).remove(leaf);
                    if (neighbors.get(neighbor).size() == 1)
                        newLeaves.add(neighbor);
                }

                // 准备下一轮修剪
                leaves = newLeaves;
            }

            // 剩余的节点即为图的中心节点
            return leaves;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_310_MinimumHeightTrees().new Solution();
        // TO TEST
        // 在这里添加测试代码
    }
}

/**
A tree is an undirected graph in which any two vertices are connected by 
exactly one path. In other words, any connected graph without simple cycles is a tree. 


 Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges 
where edges[i] = [ai, bi] indicates that there is an undirected edge between the 
two nodes ai and bi in the tree, you can choose any node of the tree as the 
root. When you select a node x as the root, the result tree has height h. Among all 
possible rooted trees, those with minimum height (i.e. min(h)) are called 
minimum height trees (MHTs). 

 Return a list of all MHTs' root labels. You can return the answer in any order.
 

 The height of a rooted tree is the number of edges on the longest downward 
path between the root and a leaf. 

 
 Example 1: 
 
 
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node 
with label 1 which is the only MHT.
 

 Example 2: 
 
 
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]
 

 
 Constraints: 

 
 1 <= n <= 2 * 10⁴ 
 edges.length == n - 1 
 0 <= ai, bi < n 
 ai != bi 
 All the pairs (ai, bi) are distinct. 
 The given input is guaranteed to be a tree and there will be no repeated edges.
 
 

 Related Topics Depth-First Search Breadth-First Search Graph Topological Sort ?
? 7305 👎 307

*/

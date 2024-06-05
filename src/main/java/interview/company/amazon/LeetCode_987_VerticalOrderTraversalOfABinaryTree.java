package interview.company.amazon;

import leetcode.util.TreeNode;

import java.util.*;
/**
  *@Question:  987. Vertical Order Traversal of a Binary Tree     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 66.03%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

/**
 *  * Yes I agree it looks similar but there's actually some differences worthing noticing between two problems. For #314, BFS is better than DFS since BFS doesn't care about rows (and the time and space usage has a significant difference in submission) But for this problem #987, BFS and DFS is pretty the same since we have to deal with rows based on the sorting requirements when nodes are in same column and row. And typically DFS code is easier to write so I would prefer DFS over BFS for this problem.
 * 这段代码实现了二叉树的垂序遍历，其中包含了两种不同的解法：BFS（广度优先搜索）和DFS（深度优先搜索）。
 *
 * ### 解题思路：
 *
 * 1. **BFS 解法**：
 *    - BFS 的主要思路是从根节点开始，逐层遍历二叉树，同时记录每个节点的位置信息（行、列）。
 *    - 使用队列进行层序遍历，每次出队一个节点时，将其左右子节点入队，并记录它们的位置信息。
 *    - 将节点的位置信息（列、行、值）存储到一个列表中。
 *    - 对列表进行排序，首先按照列排序，然后按照行排序，最后按照节点的值排序。
 *    - 最后，根据列的不同，将节点值按照列索引分组，得到垂序遍历的结果。
 *
 * 2. **DFS 解法**：
 *    - DFS 的主要思路是递归地遍历二叉树，同时记录每个节点的位置信息（行、列）。
 *    - 使用递归函数进行深度优先遍历，在遍历节点的同时，记录节点的位置信息，并将信息存储到一个列表中。
 *    - 同样地，对列表进行排序，首先按照列排序，然后按照行排序，最后按照节点的值排序。
 *    - 最后，根据列的不同，将节点值按照列索引分组，得到垂序遍历的结果。
 *
 * ### 时间复杂度：
 * - 在两种解法中，遍历整个二叉树的时间复杂度都是 O(N)，其中 N 是二叉树中的节点数。
 * - 排序的时间复杂度为 O(NlogN)，因为排序算法的复杂度为 NlogN。
 *
 * 综上所述，总的时间复杂度为 O(NlogN)。
 *
 * ### 空间复杂度：
 * - 在两种解法中，都使用了一个列表来存储节点的位置信息，因此空间复杂度都为 O(N)。
 * - 此外，在DFS解法中，递归调用栈的空间复杂度为树的高度，最坏情况下为 O(N)。
 * - 在BFS解法中，使用了一个队列来进行层序遍历，最坏情况下队列的大小也会达到 O(N)。
 *
 * 综上所述，总的空间复杂度为 O(N)。
 */
public class LeetCode_987_VerticalOrderTraversalOfABinaryTree {

//leetcode submit region begin(Prohibit modification and deletion)

    class Triplet<F, S, T> {
        public final F first;
        public final S second;
        public final T third;

        public Triplet(F first, S second, T third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    class Solution {
        List<Triplet<Integer, Integer, Integer>> nodeList = new ArrayList<>();

        //Solution 1: BFS
        // BFS 遍历二叉树
        private void BFS(TreeNode root) {
            Queue<Triplet<TreeNode, Integer, Integer>> queue = new ArrayDeque();
            int row = 0, column = 0;
            queue.offer(new Triplet(root, row, column));

            while (!queue.isEmpty()) {
                Triplet<TreeNode, Integer, Integer> triplet = queue.poll();
                root = triplet.first;
                row = triplet.second;
                column = triplet.third;

                if (root != null) {
                    this.nodeList.add(new Triplet(column, row, root.val));
                    queue.offer(new Triplet(root.left, row + 1, column - 1));
                    queue.offer(new Triplet(root.right, row + 1, column + 1));
                }
            }
        }

        public List<List<Integer>> verticalTraversal(TreeNode root) {

            List<List<Integer>> output = new ArrayList();
            if (root == null) {
                return output;
            }

            // 步骤1：BFS 遍历
            BFS(root);

            // 步骤2：将全局列表按照 <列, 行, 值> 进行排序
            Collections.sort(this.nodeList, new Comparator<Triplet<Integer, Integer, Integer>>() {
                @Override
                public int compare(Triplet<Integer, Integer, Integer> t1,
                                   Triplet<Integer, Integer, Integer> t2) {
                    if (t1.first.equals(t2.first))
                        if (t1.second.equals(t2.second))
                            return t1.third - t2.third;
                        else
                            return t1.second - t2.second;
                    else
                        return t1.first - t2.first;
                }
            });

            // 步骤3：提取值，根据列索引分组
            List<Integer> currColumn = new ArrayList();
            Integer currColumnIndex = this.nodeList.get(0).first;

            for (Triplet<Integer, Integer, Integer> triplet : this.nodeList) {
                Integer column = triplet.first, value = triplet.third;
                if (column == currColumnIndex) {
                    currColumn.add(value);
                } else {
                    output.add(currColumn);
                    currColumnIndex = column;
                    currColumn = new ArrayList();
                    currColumn.add(value);
                }
            }
            output.add(currColumn);

            return output;
        }

        //Solution2: DFS
//        List<Triplet<Integer, Integer, Integer>> nodeList = new ArrayList<>();

        //Solution 2: DFS
        // DFS 遍历二叉树
        private void DFS(TreeNode node, Integer row, Integer column) {
            if (node == null)
                return;
            nodeList.add(new Triplet(column, row, node.val));
            // preorder DFS traversal
            this.DFS(node.left, row + 1, column - 1);
            this.DFS(node.right, row + 1, column + 1);
        }

        public List<List<Integer>> verticalTraversal2(TreeNode root) {
            List<List<Integer>> output = new ArrayList();
            if (root == null) {
                return output;
            }

            // 步骤1：DFS 遍历
            DFS(root, 0, 0);

            // 步骤2：将列表按照 <列, 行, 值> 进行排序
            Collections.sort(this.nodeList, new Comparator<Triplet<Integer, Integer, Integer>>() {
                @Override
                public int compare(Triplet<Integer, Integer, Integer> t1,
                                   Triplet<Integer, Integer, Integer> t2) {
                    if (t1.first.equals(t2.first))
                        if (t1.second.equals(t2.second))
                            return t1.third - t2.third;
                        else
                            return t1.second - t2.second;
                    else
                        return t1.first - t2.first;
                }
            });

            // 步骤3：提取值，根据列索引分组
            List<Integer> currColumn = new ArrayList();
            Integer currColumnIndex = this.nodeList.get(0).first;

            for (Triplet<Integer, Integer, Integer> triplet : this.nodeList) {
                Integer column = triplet.first, value = triplet.third;
                if (column == currColumnIndex) {
                    currColumn.add(value);
                } else {
                    output.add(currColumn);
                    currColumnIndex = column;
                    currColumn = new ArrayList();
                    currColumn.add(value);
                }
            }
            output.add(currColumn);

            return output;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_987_VerticalOrderTraversalOfABinaryTree().new Solution();
        // TO TEST
        // 测试示例代码
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = solution.verticalTraversal(root);
        System.out.println("BFS 垂序遍历结果：");
        for (List<Integer> list : result) {
            System.out.println(list);
        }

        List<List<Integer>> result2 = solution.verticalTraversal2(root);
        System.out.println("DFS 垂序遍历结果：");
        for (List<Integer> list : result2) {
            System.out.println(list);
        }
    }
}
/**
Given the root of a binary tree, calculate the vertical order traversal of the 
binary tree. 

 For each node at position (row, col), its left and right children will be at 
positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the 
tree is at (0, 0). 

 The vertical order traversal of a binary tree is a list of top-to-bottom 
orderings for each column index starting from the leftmost column and ending on the 
rightmost column. There may be multiple nodes in the same row and same column. In 
such a case, sort these nodes by their values. 

 Return the vertical order traversal of the binary tree. 

 
 Example 1: 
 
 
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column. 

 Example 2: 
 
 
Input: root = [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their 
value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.
 

 Example 3: 
 
 
Input: root = [1,2,3,4,6,5,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location 
and should be ordered by their values.
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 1000]. 
 0 <= Node.val <= 1000 
 

 Related Topics Hash Table Tree Depth-First Search Breadth-First Search Sorting 
Binary Tree 👍 7423 👎 4310

*/
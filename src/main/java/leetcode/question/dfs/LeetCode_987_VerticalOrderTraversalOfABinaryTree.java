package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
  *@Question:  987. Vertical Order Traversal of a Binary Tree     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 64.8%      
  *@Time  Complexity: O(NlogN)
  *@Space Complexity: O(N)
 */

public class LeetCode_987_VerticalOrderTraversalOfABinaryTree{
    
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

        private void DFS(TreeNode node, Integer row, Integer column) {
            if (node == null)
                return;
//            nodeList.add(new Triplet(column, row, node.val));
//            // preorder DFS traversal
//            this.DFS(node.left, row + 1, column - 1);
//            this.DFS(node.right, row + 1, column + 1);

            // preorder DFS traversal
            this.DFS(node.left, row + 1, column - 1);
            this.DFS(node.right, row + 1, column + 1);
            nodeList.add(new Triplet(column, row, node.val));

        }

        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> output = new ArrayList();
            if (root == null) {
                return output;
            }

            // step 1). DFS traversal
            DFS(root, 0, 0);

            // step 2). sort the list by <column, row, value>
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

            // step 3). extract the values, grouped by the column index.
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
        //solution.
    }
}
/**
<p>Given the <code>root</code> of a binary tree, calculate the <strong>vertical order traversal</strong> of the binary tree.</p>

<p>For each node at position <code>(row, col)</code>, its left and right children will be at positions <code>(row + 1, col - 1)</code> and <code>(row + 1, col + 1)</code> respectively. The root of the tree is at <code>(0, 0)</code>.</p>

<p>The <strong>vertical order traversal</strong> of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.</p>

<p>Return <em>the <strong>vertical order traversal</strong> of the binary tree</em>.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/29/vtree1.jpg" style="width: 431px; height: 304px;" /> 
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> [[9],[3,15],[20],[7]]
<strong>Explanation:</strong>
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.</pre>

<p><strong class="example">Example 2:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/29/vtree2.jpg" style="width: 512px; height: 304px;" /> 
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6,7]
<strong>Output:</strong> [[4],[2],[1,5,6],[3],[7]]
<strong>Explanation:</strong>
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.
</pre>

<p><strong class="example">Example 3:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/29/vtree3.jpg" style="width: 512px; height: 304px;" /> 
<pre>
<strong>Input:</strong> root = [1,2,3,4,6,5,7]
<strong>Output:</strong> [[4],[2],[1,5,6],[3],[7]]
<strong>Explanation:</strong>
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li>The number of nodes in the tree is in the range <code>[1, 1000]</code>.</li> 
 <li><code>0 &lt;= Node.val &lt;= 1000</code></li> 
</ul>

<div><div>Related Topics</div><div><li>Hash Table</li><li>Tree</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Binary Tree</li></div></div><br><div><li>👍 7093</li><li>👎 4269</li></div>
*/

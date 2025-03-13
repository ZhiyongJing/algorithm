package leetcode.question.dfs;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Question:  655. Print Binary Tree
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 43.97%
 *@Time  Complexity:  O(h⋅2^h). We need to fill the res array of size h⋅2^h −1. Here, h refers to the height of the given tree.
 *@Space Complexity: O(h⋅2^h)
 */

public class LeetCode_655_PrintBinaryTree{

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
    public class Solution {
        public List<List<String>> printTree(TreeNode root) {
            // 获取树的高度
            int height = getHeight(root);

            // 创建一个二维字符串数组，行数为树高，列数为 2^h - 1，所有元素初始化为空字符串
            String[][] res = new String[height][(1 << height) - 1];
            for(String[] arr : res)
                Arrays.fill(arr, ""); // 初始化数组，防止 null 值

            // 创建一个 List<List<String>> 作为最终输出
            List<List<String>> ans = new ArrayList<>();

            // 递归填充 res 数组，将二叉树的值按照居中排列放入
            fill(res, root, 0, 0, res[0].length);

            // 将二维数组转换成 List<List<String>>
            for(String[] arr : res)
                ans.add(Arrays.asList(arr));

            return ans;
        }

        public void fill(String[][] res, TreeNode root, int i, int l, int r) {
            // 递归终止条件：当前节点为空
            if (root == null)
                return;

            // 计算当前节点应存放的位置，居中对齐
            res[i][(l + r) / 2] = "" + root.val;

            // 递归处理左子树，更新左右边界
            fill(res, root.left, i + 1, l, (l + r) / 2);

            // 递归处理右子树，更新左右边界
            fill(res, root.right, i + 1, (l + r + 1) / 2, r);
        }

        public int getHeight(TreeNode root) {
            // 计算树的高度：递归获取左子树和右子树的最大高度 +1
            if (root == null)
                return 0;
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_655_PrintBinaryTree().new Solution();

        // 构造测试二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);

        // 打印二叉树
        List<List<String>> result = solution.printTree(root);

        // 输出打印的结果
        for (List<String> row : result) {
            System.out.println(row);
        }
    }
}

/**
Given the root of a binary tree, construct a 0-indexed m x n string matrix res 
that represents a formatted layout of the tree. The formatted layout matrix 
should be constructed using the following rules: 

 
 The height of the tree is height and the number of rows m should be equal to 
height + 1. 
 The number of columns n should be equal to 2ʰᵉⁱᵍʰᵗ⁺¹ - 1. 
 Place the root node in the middle of the top row (more formally, at location 
res[0][(n-1)/2]). 
 For each node that has been placed in the matrix at position res[r][c], place 
its left child at res[r+1][c-2ʰᵉⁱᵍʰᵗ⁻ʳ⁻¹] and its right child at res[r+1][c+2ʰᵉⁱᵍ
ʰᵗ⁻ʳ⁻¹]. 
 Continue this process until all the nodes in the tree have been placed. 
 Any empty cells should contain the empty string "". 
 

 Return the constructed matrix res. 

 
 Example 1: 
 
 
Input: root = [1,2]
Output: 
[["","1",""],
 ["2","",""]]
 

 Example 2: 
 
 
Input: root = [1,2,3,null,4]
Output: 
[["","","","1","","",""],
 ["","2","","","","3",""],
 ["","","4","","","",""]]
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 2¹⁰]. 
 -99 <= Node.val <= 99 
 The depth of the tree will be in the range [1, 10]. 
 

 Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 524 
👎 462

*/
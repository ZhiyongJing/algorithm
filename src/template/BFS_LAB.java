package template;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Question:
 * @Difculty:
 * @Time Complexity: O
 * @Space Complexity: O
 * @UpdateTime: [12/15/23 4:08 PM]
 */
public class BFS_LAB {

    public List<List<Integer>> BFS(TreeNode node){
        List<List<Integer>> result = new ArrayList<>();
        if(node == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){
            TreeNode currentNode = queue.poll();
            result.add(new ArrayList<>());
            result.get(0).add(currentNode.val);
            if(currentNode.left != null){
                queue.add(currentNode.left);
            }
            if(currentNode.right != null){
                queue.add(currentNode.right);
            }
        }
        return result;
    }

    public List<List<Integer>> BFSByLevel(TreeNode node){
        List<List<Integer>> result = new ArrayList<>();
        if(node == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){

            int level = queue.size();
            result.add(new ArrayList<>());
            for(int i = 0; i < level; i++){
                TreeNode currentNode = queue.poll();
                result.get(result.size() - 1).add(currentNode.val);

                if(currentNode.left != null){
                    queue.add(currentNode.left);
                }
                if(currentNode.right != null){
                    queue.add(currentNode.right);
                }
            }
        }
        return result;
    }




    public static void main(String[] args) {
        // 创建一个更深的BST示例
        TreeNode root = new TreeNode(0).sampleTree();

        BFS_LAB bfs = new BFS_LAB();
        // 进行BST的测试
        System.out.println(bfs.BFS(root));
        System.out.println(bfs.BFSByLevel(root));

    }
}

/**
 *         15
 *        /  \
 *       8    20
 *      / \   / \
 *     5  12 18  25
 *    / \  /
 *   3   7 10
 *        \
 *        14
 */



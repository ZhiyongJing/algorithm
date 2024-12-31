package template;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 什么时候用BFS
 * 1. 图的遍历 Travel in Graph
 * 1.1 层级遍历 Level Order Traversal
 * 1.2 由点及面 Connected Component
 * 1.3 拓扑排序 Topological Sorting
 * 2. 最短路径 Shortest Path in Simple Graph
 * 2.1 仅限简单图求最短路径
 * 2.2 图中每条边长度都是1， 且没有方向
 */

public class BFS {

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

        BFS bfs = new BFS();
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



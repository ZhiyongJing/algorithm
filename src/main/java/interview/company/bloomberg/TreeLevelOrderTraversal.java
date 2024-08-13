package interview.company.bloomberg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode middle;
    TreeNode right;

    // Constructor
    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.middle = null;
        this.right = null;
    }
}

public class TreeLevelOrderTraversal {
    public List<List<TreeNode>> levelOrder(TreeNode root) {
        List<List<TreeNode>> levels = new ArrayList<>(); // To store the nodes by level
        if (root == null) {
            return levels; // If the tree is empty, return an empty list.
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of nodes at the current level
            List<TreeNode> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current);

                // Add the left, middle, and right children to the queue if they exist
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.middle != null) {
                    queue.add(current.middle);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            levels.add(currentLevel); // Add the current level to the list of levels
        }

        return levels;
    }

    public static void main(String[] args) {
        /* 初始化队列 */
        Queue<Integer> queue = new ArrayDeque<>();

        /* 元素入队 */
        queue.offer(1);
        queue.offer(3);
        queue.offer(2);
        queue.offer(5);
        queue.offer(4);
        System.out.println(queue.toString());

        /* 访问队首元素 */
        int peek = queue.peek();
        System.out.println(peek);

        /* 元素出队 */
        int pop = queue.poll();
        System.out.println(pop);

        /* 获取队列的长度 */
        int size = queue.size();

        /* 判断队列是否为空 */
        boolean isEmpty = queue.isEmpty();
        // Create a Deque using ArrayDeque
        Deque<String> deque = new ArrayDeque<>();

        // Add elements at the end of the deque
        deque.addLast("Alice");
        deque.addLast("Bob");
        deque.addLast("Charlie");

        // Add elements at the front of the deque
        deque.addFirst("Xander");
        deque.addFirst("Yara");

        // Print the deque
        System.out.println("Deque after adding elements: " + deque);

        // Remove elements from the front
        String first = deque.removeFirst();
        System.out.println("Removed from front: " + first);
        System.out.println("Deque after removing from front: " + deque);

        // Remove elements from the end
        String last = deque.removeLast();
        System.out.println("Removed from end: " + last);
        System.out.println("Deque after removing from end: " + deque);

        // Peek at the front element without removing it
        String peekFirst = deque.peekFirst();
        System.out.println("Peeked at front: " + peekFirst);

        // Peek at the last element without removing it
        String peekLast = deque.peekLast();
        System.out.println("Peeked at end: " + peekLast);

        // Iterate over the deque
        System.out.println("Iterating over deque:");
        for (String name : deque) {
            System.out.println(name);
        }
    }
}

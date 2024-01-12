package leetcode.question.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Question: 133. Clone Graph
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 50.4%
 * @Time Complexity: O(N+M) N is a number of nodes (vertices) and M is a number of edges.
 * @Space Complexity: O(N)
 */

/**
 * ### 算法思路：
 *
 * 这道题目是关于克隆图的问题，要求深度拷贝一个无向图。主要使用了深度优先搜索（DFS）和广度优先搜索（BFS）两种方法。
 *
 * #### 1. DFS 方法：
 *
 * - 创建一个哈希表 `visited`，用于存储原始节点到克隆节点的映射，防止重复访问。
 * - 从图的任意节点开始，递归地深度优先搜索图，同时进行克隆。
 * - 对于每个节点，首先检查它是否已被访问，如果是则返回其克隆节点。如果不是，则创建一个新的节点，将其加入哈希表，并递归地克隆邻居节点。
 * - 时间复杂度：O(N+M)，其中 N 是节点数量，M 是边的数量。每个节点至多被访问一次，每条边也至多被访问一次。
 * - 空间复杂度：O(N)，用于存储节点和边的信息。
 *
 * #### 2. BFS 方法：
 *
 * - 使用广度优先搜索，遍历图中的所有节点，并进行克隆。
 * - 使用队列进行BFS遍历，同时维护一个哈希表 `visited` 用于记录节点是否被访问过。
 * - 对于每个节点，首先检查它是否已被访问，如果是则返回其克隆节点。如果不是，则创建一个新的节点，将其加入哈希表，并将其邻居节点加入队列。
 * - 时间复杂度：O(N+M)，其中 N 是节点数量，M 是边的数量。每个节点至多被访问一次，每条边也至多被访问一次。
 * - 空间复杂度：O(N)，用于存储节点和边的信息。
 *
 * ### 总结：
 *
 * 这两种方法都通过遍历图的所有节点，并对每个节点及其邻居节点进行克隆，最终得到了整个图的深拷贝。
 * DFS 是通过递归实现，BFS 是通过队列实现，它们的时间复杂度和空间复杂度都是相同的。
 */

public class LeetCode_133_CloneGraph {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)


    class Solution {

        private HashMap<Node, Node> visited = new HashMap<>();

        /**
         * DFS方法克隆图
         *
         * @param node 图的节点
         * @return 克隆后的节点
         */
        public Node cloneGraph(Node node) {
            if (node == null) {
                return node;
            }

            // 如果节点已经被访问过，直接返回克隆的节点
            if (visited.containsKey(node)) {
                return visited.get(node);
            }

            // 创建当前节点的克隆节点
            Node cloneNode = new Node(node.val, new ArrayList());
            visited.put(node, cloneNode);

            // 遍历邻居节点，递归克隆邻居节点
            for (Node neighbor : node.neighbors) {
                cloneNode.neighbors.add(cloneGraph(neighbor));
            }
            System.out.println(cloneNode.val);
            return cloneNode;
        }

        /**
         * BFS方法克隆图
         *
         * @param node 图的节点
         * @return 克隆后的节点
         */
        public Node cloneGraph2(Node node) {
            if (node == null) {
                return node;
            }

            // 哈希表用于保存已访问的节点和对应的克隆节点
            HashMap<Node, Node> visited = new HashMap();

            // 将第一个节点加入队列
            LinkedList<Node> queue = new LinkedList<Node>();
            queue.add(node);
            // 克隆第一个节点并放入哈希表
            visited.put(node, new Node(node.val, new ArrayList()));

            // 开始BFS遍历
            while (!queue.isEmpty()) {
                Node n = queue.remove();
                // 遍历当前节点的邻居
                for (Node neighbor : n.neighbors) {
                    if (!visited.containsKey(neighbor)) {
                        // 如果邻居节点未被访问过，克隆邻居节点并放入队列
                        visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
                        queue.add(neighbor);
                    }
                    // 将克隆的邻居节点加入克隆节点的邻居列表中
                    visited.get(n).neighbors.add(visited.get(neighbor));
                }
            }

            // 返回克隆后的节点
            return visited.get(node);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_133_CloneGraph cloneGraph = new LeetCode_133_CloneGraph();
        Solution solution = cloneGraph.new Solution();

        // Test Case 1
        // 构建图结构：1 -> 2, 3; 2 -> 1, 3; 3 -> 1, 2
        Node node1 = cloneGraph.new Node(1, new ArrayList<>());
        Node node2 = cloneGraph.new Node(2, new ArrayList<>());
        Node node3 = cloneGraph.new Node(3, new ArrayList<>());
        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node1);
        node3.neighbors.add(node2);

        // 使用DFS克隆图
        Node clonedNode1 = solution.cloneGraph(node1);
        System.out.println("Test Case 1:");
        printGraph(clonedNode1);

        // 使用BFS克隆图
        Node clonedNode2 = solution.cloneGraph2(node1);
        System.out.println("Test Case 2:");
        printGraph(clonedNode2);
    }

    private static void printGraph(Node node) {
        // 打印图结构
        HashMap<Node, Integer> visited = new HashMap<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        visited.put(node, 1);

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            System.out.print("Node " + n.val + ": ");
            for (Node neighbor : n.neighbors) {
                System.out.print(neighbor.val + " ");
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, 1);
                    queue.add(neighbor);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

/**
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a value (int) and a list (List[Node]) of its
 * neighbors.
 * <p>
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * <p>
 * <p>
 * Test case format:
 * <p>
 * For simplicity, each node's value is the same as the node's index (1-indexed).
 * For example, the first node with val == 1, the second node with val == 2, and
 * so on. The graph is represented in the test case using an adjacency list.
 * <p>
 * An adjacency list is a collection of unordered lists used to represent a
 * finite graph. Each list describes the set of neighbors of a node in the graph.
 * <p>
 * The given node will always be the first node with val = 1. You must return the
 * copy of the given node as a reference to the cloned graph.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of
 * only one node with val = 1 and it does not have any neighbors.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given
 * node.
 * <p>
 * <p>
 * Related Topics Hash Table Depth-First Search Breadth-First Search Graph 👍 9017
 * 👎 3605
 */

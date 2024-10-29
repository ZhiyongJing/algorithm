package leetcode.question.bfs;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *@Question:  864. Shortest Path to Get All Keys
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 47.19%
 *@Time  Complexity: O(RC(2A+1)+ElogN), where R,C are the dimensions of the grid, and A is the maximum number of keys, N=(2A+1)*2^A is the number of nodes when we perform Dijkstra's, and E=N*(2A+1) is the maximum number of edges.
 *@Space Complexity: O(N)
 */

public class LeetCode_864_ShortestPathToGetAllKeys{

//leetcode submit region begin(Prohibit modification and deletion)
//import java.awt.Point;

    class Solution {
        int INF = Integer.MAX_VALUE;  // 用于表示无穷大的值
        String[] grid;  // 存储输入的二维网格
        int R, C;  // 网格的行数和列数
        Map<Character, Point> location;  // 存储关键位置的映射（如钥匙和门）
        int[] dr = new int[]{-1, 0, 1, 0};  // 行方向的偏移量（上、右、下、左）
        int[] dc = new int[]{0, -1, 0, 1};  // 列方向的偏移量（上、右、下、左）

        public int shortestPathAllKeys(String[] grid) {
            this.grid = grid;  // 初始化网格
            R = grid.length;  // 获取行数
            C = grid[0].length();  // 获取列数

            // location : 记录关键位置（钥匙和门）
            location = new HashMap<>();
            for (int r = 0; r < R; ++r)
                for (int c = 0; c < C; ++c) {
                    char v = grid[r].charAt(c);  // 获取当前网格的值
                    if (v != '.' && v != '#')  // 过滤掉空地和墙
                        location.put(v, new Point(r, c));  // 存储钥匙和门的位置
                }

            int targetState = (1 << (location.size() / 2)) - 1;  // 计算目标状态
            Map<Character, Map<Character, Integer>> dists = new HashMap<>();  // 存储从每个点到其他点的距离
            for (char place: location.keySet())
                dists.put(place, bfsFrom(place));  // 对每个关键位置进行 BFS 计算距离

            // Dijkstra 算法
            PriorityQueue<ANode> pq = new PriorityQueue<>((a, b) ->
                    Integer.compare(a.dist, b.dist));  // 优先队列，根据距离进行排序
            pq.offer(new ANode(new Node('@', 0), 0));  // 初始化起点
            Map<Node, Integer> finalDist = new HashMap<>();  // 存储最终距离
            finalDist.put(new Node('@', 0), 0);  // 起点距离为 0

            while (!pq.isEmpty()) {  // 当优先队列不为空时
                ANode anode = pq.poll();  // 取出当前距离最小的节点
                Node node = anode.node;  // 当前节点
                int d = anode.dist;  // 当前节点的距离
                if (finalDist.getOrDefault(node, INF) < d) continue;  // 如果已找到更短的路径，则跳过
                if (node.state == targetState) return d;  // 如果达到目标状态，返回当前距离

                // 遍历当前节点可以到达的目标
                for (char destination: dists.get(node.place).keySet()) {
                    int d2 = dists.get(node.place).get(destination);  // 获取当前到目标的距离
                    int state2 = node.state;  // 更新状态
                    if (Character.isLowerCase(destination)) // 如果是钥匙
                        state2 |= (1 << (destination - 'a'));  // 更新状态，标记钥匙已获取
                    if (Character.isUpperCase(destination)) // 如果是门
                        if ((node.state & (1 << (destination - 'A'))) == 0) // 如果没有钥匙
                            continue;  // 跳过该门

                    // 更新最短距离并加入优先队列
                    if (d + d2 < finalDist.getOrDefault(new Node(destination, state2), INF)) {
                        finalDist.put(new Node(destination, state2), d + d2);
                        pq.offer(new ANode(new Node(destination, state2), d + d2));  // 加入优先队列
                    }
                }
            }

            return -1;  // 如果无法获取所有钥匙，则返回 -1
        }

        // 从指定位置进行 BFS，计算到达其他点的距离
        public Map<Character, Integer> bfsFrom(char source) {
            int sr = location.get(source).x;  // 获取起始位置的行
            int sc = location.get(source).y;  // 获取起始位置的列
            boolean[][] seen = new boolean[R][C];  // 记录访问过的位置
            seen[sr][sc] = true;  // 标记起始位置为已访问
            int curDepth = 0;  // 当前深度
            Queue<Point> queue = new LinkedList<>();  // BFS 队列
            queue.offer(new Point(sr, sc));  // 将起始位置加入队列
            queue.offer(null);  // 终止当前层的标记
            Map<Character, Integer> dist = new HashMap<>();  // 存储到达每个点的距离

            while (!queue.isEmpty()) {  // 当队列不为空时
                Point p = queue.poll();  // 取出当前点
                if (p == null) {  // 如果是层结束标记
                    curDepth++;  // 深度加一
                    if (!queue.isEmpty())
                        queue.offer(null);  // 继续加入层结束标记
                    continue;
                }

                int r = p.x, c = p.y;  // 当前点的行列
                if (grid[r].charAt(c) != source && grid[r].charAt(c) != '.') {  // 如果当前点不是起始点且不是空地
                    dist.put(grid[r].charAt(c), curDepth);  // 记录到达该点的距离
                    continue; // 到达关键点，停止继续向下走
                }

                // 遍历四个方向
                for (int i = 0; i < 4; ++i) {
                    int cr = r + dr[i];  // 当前行的偏移
                    int cc = c + dc[i];  // 当前列的偏移
                    // 检查边界和是否已访问过
                    if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                        if (grid[cr].charAt(cc) != '#') {  // 如果不是墙
                            queue.offer(new Point(cr, cc));  // 加入队列
                            seen[cr][cc] = true;  // 标记为已访问
                        }
                    }
                }
            }

            return dist;  // 返回到达每个关键点的距离
        }
    }

    // ANode: Annotated Node
    class ANode {
        Node node;  // 当前节点
        int dist;  // 当前距离

        ANode(Node n, int d) {
            node = n;
            dist = d;  // 初始化
        }
    }

    class Node {
        char place;  // 当前地点
        int state;  // 当前状态（获取的钥匙状态）

        Node(char p, int s) {
            place = p;  // 初始化地点
            state = s;  // 初始化状态
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;  // 比较同一对象
            if (!(o instanceof Node)) return false;  // 类型检查
            Node other = (Node) o;
            return (place == other.place && state == other.state);  // 比较地点和状态
        }

        @Override
        public int hashCode() {
            return 256 * state + place;  // 自定义哈希码
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_864_ShortestPathToGetAllKeys().new Solution();

        // 测试用例
        String[] grid1 = {
                "@.a..",
                "###.#",
                "b.A.B"
        };
        System.out.println(solution.shortestPathAllKeys(grid1));  // Output: 8

        }}
/**
You are given an m x n grid grid where: 

 
 '.' is an empty cell. 
 '#' is a wall. 
 '@' is the starting point. 
 Lowercase letters represent keys. 
 Uppercase letters represent locks. 
 

 You start at the starting point and one move consists of walking one space in 
one of the four cardinal directions. You cannot walk outside the grid, or walk 
into a wall. 

 If you walk over a key, you can pick it up and you cannot walk over a lock 
unless you have its corresponding key. 

 For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter 
of the first k letters of the English alphabet in the grid. This means that 
there is exactly one key for each lock, and one lock for each key; and also that the 
letters used to represent the keys and locks were chosen in the same order as 
the English alphabet. 

 Return the lowest number of moves to acquire all keys. If it is impossible, 
return -1. 

 
 Example 1: 
 
 
Input: grid = ["@.a..","###.#","b.A.B"]
Output: 8
Explanation: Note that the goal is to obtain all the keys not to open all the 
locks.
 

 Example 2: 
 
 
Input: grid = ["@..aA","..B#.","....b"]
Output: 6
 

 Example 3: 
 
 
Input: grid = ["@Aa"]
Output: -1
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 30 
 grid[i][j] is either an English letter, '.', '#', or '@'. 
 There is exactly one '@' in the grid. 
 The number of keys in the grid is in the range [1, 6]. 
 Each key in the grid is unique. 
 Each key in the grid has a matching lock. 
 

 Related Topics Array Bit Manipulation Breadth-First Search Matrix 👍 2354 👎 10
3

*/
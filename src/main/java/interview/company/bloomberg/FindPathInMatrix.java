package interview.company.bloomberg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 2D grid (m x n), where:
 *
 * Each cell can have one of two values:
 * 0: A cell that can be passed through.
 * -1: A cell that represents a blockage and cannot be passed through.
 * You can move in 8 possible directions: up, down, left, right, and diagonally.
 * Each move has a cost of 1.
 *
 * Write a function to determine if it is possible to travel between two given points (start and end) within a given maximum cost (maxCost).
 * The function should return true if such a path exists and false otherwise.
 * arr = {
 *     {0, 0, 0, -1, 0},
 *     {-1, 0, 0, -1, -1},
 *     {0, 0, 0, -1, 0},
 *     {-1, 0, 0, 0, 0},
 *     {0, 0, -1, 0, 0}
 * };
 * start = [0, 0];
 * end = [4, 4];
 * maxCost = 6;
 */
public class FindPathInMatrix {
    //Solution1: DFS
    public  boolean isPathPossibleDFS(int[][] arr, int[] start, int[] end, int maxCost) {
        int m = arr.length; // 行数
        int n = arr[0].length; // 列数

        // 如果起点或终点被阻塞，直接返回 false
        if (arr[start[0]][start[1]] == -1 || arr[end[0]][end[1]] == -1) {
            return false;
        }

        // 记录访问状态
        boolean[][] visited = new boolean[m][n];

        // 通过 DFS 检查是否存在路径
        return dfs(arr, start[0], start[1], end[0], end[1], visited, maxCost, 0);
    }

    private  boolean dfs(int[][] arr, int x, int y, int destX, int destY, boolean[][] visited, int maxCost, int currentCost) {
        // 如果已经超过最大允许成本，返回 false
        if (currentCost > maxCost) {
            return false;
        }

        // 如果到达目标点，返回 true
        if (x == destX && y == destY) {
            return true;
        }

        // 标记当前点为已访问
        visited[x][y] = true;

        // 定义 8 个方向（包括对角线移动）
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        // 遍历 8 个方向
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            // 检查新点是否在边界内、未访问且可通过
            if (newX >= 0 && newY >= 0 && newX < arr.length && newY < arr[0].length &&
                    arr[newX][newY] == 0 && !visited[newX][newY]) {
                // 递归调用 DFS，如果找到路径则返回 true
                if (dfs(arr, newX, newY, destX, destY, visited, maxCost, currentCost + 1)) {
                    return true;
                }
            }
        }

        // 回溯时取消访问标记
        visited[x][y] = false;

        // 如果所有方向都无法到达目标点，返回 false
        return false;
    }

    //Solution2:BFS
    public static boolean isPathPossibleBFS(int[][] arr, int[] start, int[] end, int maxCost) {
        int m = arr.length;
        int n = arr[0].length;

        // 如果起点或终点被阻塞，直接返回 false
        if (arr[start[0]][start[1]] == -1 || arr[end[0]][end[1]] == -1) {
            return false;
        }

        // 队列存储当前点的坐标和累计的移动成本
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(new int[]{start[0], start[1], 0}); // x, y, cost
        visited[start[0]][start[1]] = true;

        // 定义 8 个方向（包括对角线移动）
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int cost = current[2];

            // 如果达到目标点且在成本限制内，返回 true
            if (x == end[0] && y == end[1]) {
                return true;
            }

            // 遍历 8 个方向
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // 检查新点是否在边界内、未访问且可通过
                if (newX >= 0 && newY >= 0 && newX < m && newY < n &&
                        arr[newX][newY] == 0 && !visited[newX][newY]) {
                    int newCost = cost + 1;

                    // 如果新路径的成本超过限制，不添加到队列
                    if (newCost > maxCost) {
                        continue;
                    }

                    // 将新的点加入队列
                    queue.offer(new int[]{newX, newY, newCost});
                    visited[newX][newY] = true;
                }
            }
        }

        // 如果无法到达目标点，返回 false
        return false;
    }



    public static void main(String[] args) {
        FindPathInMatrix s = new FindPathInMatrix();
        int[][] arr = {
                {0, 0, 0, -1, 0},
                {-1, 0, 0, -1, -1},
                {0, 0, 0, -1, 0},
                {-1, 0, 0, 0, 0},
                {0, 0, -1, 0, 0}
        };

        // 测试用例
        System.out.println(s.isPathPossibleDFS(arr, new int[]{0, 0}, new int[]{4, 4}, 6) ? "Yes" : "No"); // 输出: Yes
        System.out.println(s.isPathPossibleDFS(arr, new int[]{0, 0}, new int[]{4, 4}, 4) ? "Yes" : "No"); // 输出: No
    }

}

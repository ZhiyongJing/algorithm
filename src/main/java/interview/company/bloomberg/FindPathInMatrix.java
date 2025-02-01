package interview.company.bloomberg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 2D grid (m x n), where:
 * <p>
 * Each cell can have one of two values:
 * 0: A cell that can be passed through.
 * -1: A cell that represents a blockage and cannot be passed through.
 * You can move in 8 possible directions: up, down, left, right, and diagonally.
 * Each move has a cost of 1.
 * <p>
 * Write a function to determine if it is possible to travel between two given points (start and end) within a given maximum cost (maxCost).
 * The function should return true if such a path exists and false otherwise.
 * arr = {
 * {0, 0, 0, -1, 0},
 * {-1, 0, 0, -1, -1},
 * {0, 0, 0, -1, 0},
 * {-1, 0, 0, 0, 0},
 * {0, 0, -1, 0, 0}
 * };
 * start = [0, 0];
 * end = [4, 4];
 * maxCost = 6;
 */
public class FindPathInMatrix {
    //Solution1: DFS
    public static boolean canReachWithDFS(int[][] grid, int[] start, int[] end, int maxCost) {
        int m = grid.length;
        int n = grid[0].length;

        // 若起点或终点不可达，直接返回 false
        if (grid[start[0]][start[1]] == -1 || grid[end[0]][end[1]] == -1) {
            return false;
        }

        // 创建访问标记
        boolean[][] visited = new boolean[m][n];
        // 从起点开始 DFS，当前消耗初始为 0
        return dfs(grid, start[0], start[1], end, maxCost, 0, visited);
    }

    private static boolean dfs(int[][] grid, int row, int col, int[] end,
                               int maxCost, int currentCost, boolean[][] visited) {
        // 如果超出 maxCost，剪枝
        if (currentCost > maxCost) {
            return false;
        }
        // 如果到达终点，且当前 cost <= maxCost
        if (row == end[0] && col == end[1]) {
            return true;
        }

        visited[row][col] = true;

        // 尝试 8 个方向
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            // 边界判断、阻塞判断、访问判断
            if (isValid(grid, newRow, newCol) && !visited[newRow][newCol]) {
                // 递归调用，步数 +1
                if (dfs(grid, newRow, newCol, end, maxCost, currentCost + 1, visited)) {
                    return true;
                }
            }
        }

        // 回溯，标记为未访问，便于其他路径尝试
        visited[row][col] = false;
        return false;
    }

    private static boolean isValid(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != -1;
    }

    //Solution2:BFS
    // 8 个方向移动：上、下、左、右以及 4 个对角线
    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    public static boolean canReachWithBFS(int[][] grid, int[] start, int[] end, int maxCost) {
        // 行、列数
        int m = grid.length;
        int n = grid[0].length;

        // 边界条件判断
        if (grid[start[0]][start[1]] == -1 || grid[end[0]][end[1]] == -1) {
            return false;
        }

        // 创建一个队列，用于存储 (row, col, distance)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});

        // 创建一个 visited 数组，防止重复访问
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];
            int curDist = current[2];

            // 如果已经到达终点，并且距离 <= maxCost
            if (curRow == end[0] && curCol == end[1] && curDist <= maxCost) {
                return true;
            }

            // 如果距离已经超过 maxCost，就无需再继续
            if (curDist >= maxCost) {
                continue;
            }

            // 遍历 8 个方向
            for (int[] dir : DIRECTIONS) {
                int newRow = curRow + dir[0];
                int newCol = curCol + dir[1];

                // 判断新坐标是否在有效范围内，且不是 -1，且未访问
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                        && grid[newRow][newCol] != -1
                        && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, curDist + 1});
                }
            }
        }

        // BFS 结束后仍未找到符合要求的路径
        return false;
    }


    public static void main(String[] args) {
        int[][] arr = {
                {0, 0, 0, -1, 0},
                {-1, 0, 0, -1, -1},
                {0, 0, 0, -1, 0},
                {-1, 0, 0, 0, 0},
                {0, 0, -1, 0, 0}
        };
        int[] start = {0, 0};
        int[] end = {4, 4};
        int maxCost = 6;

        boolean canReach = canReachWithDFS(arr, start, end, maxCost);
        System.out.println("DFS: " + canReach);

        boolean canReach1 = canReachWithBFS(arr, start, end, maxCost);
        System.out.println("BFS: " + canReach1);
    }

}

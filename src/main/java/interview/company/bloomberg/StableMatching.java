package interview.company.bloomberg;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 定义公共类 StableMatching 用于实现稳定匹配算法
public class StableMatching {
    /**
     * Gale-Shapley算法, 就是从中得出一个稳定匹配的算法. 算法的思想通俗易懂, 一句话概括: 所有男生依次尝试想所有女生表白
     *
     * 算法的实现步骤如下:
     *
     * 找到一个还没有对象, 且未向所有女生表白的男生m
     * 找到一个m还没有表白过的女生n
     * 如果n还没有对象, 则进行匹配
     * 如果n有对象. 则判断n的喜欢列表. 若更喜欢当前对象, 则保持不变, 若更喜欢m则抛弃当前对象
     * 直到m找到对象或向所有女生都表白过. 则回到第一步, 直到找不到这样的男生.
     */

    /**
     * 新人入职培训，公司组选人，每一个新人都有一个自己心中想去的组的排名，每一个公司组都有一个自己心中想选的新人的排名，让设计一个函数，返回最好的Match
     * 设计一个函数实现稳定匹配（Stable Matching），即返回一个最优匹配结果
     * 输入：
     *   - employeePrefs：二维数组，表示每个新人对各个公司组的偏好列表，按优先级顺序排列，编号从 0 开始
     *   - groupPrefs：二维数组，表示每个公司组对各个新人的偏好列表，按优先级顺序排列，编号从 0 开始
     * 输出：
     *   - 返回一个数组 result，其中 result[i] 表示新人 i 被分配到的公司组编号
     */
    public static int[] stableMatching(int[][] employeePrefs, int[][] groupPrefs) {
        // 获取新人的总数量（假设新人和公司组数量相等）
        int n = employeePrefs.length;
        // 创建数组 matchEmployee，用于存储每个新人匹配到的公司组编号，初始值均为 -1 表示未匹配
        int[] matchEmployee = new int[n];
        Arrays.fill(matchEmployee, -1);
        // 创建数组 matchGroup，用于存储每个公司组匹配到的新人的编号，初始值均为 -1 表示未匹配
        int[] matchGroup = new int[n];
        Arrays.fill(matchGroup, -1);
        // 创建数组 nextProposalIndex，用于记录每个新人在其偏好列表中下一个待提议的公司组索引，初始值为 0
        int[] nextProposalIndex = new int[n];

        // 构造二维数组 rank 用于存储每个公司组对各个新人的排名，rank[j][i] 表示公司组 j 对新人 i 的排名（值越小表示越喜欢）
        int[][] rank = new int[n][n];
        // 遍历每个公司组 j
        for (int j = 0; j < n; j++) {
            // 遍历公司组 j 的偏好列表，rankIndex 表示偏好顺序中的位置
            for (int rankIndex = 0; rankIndex < n; rankIndex++) {
                // 获取公司组 j 偏好列表中位于 rankIndex 位置的新人的编号
                int employee = groupPrefs[j][rankIndex];
                // 将公司组 j 对新人 employee 的排名记录为 rankIndex
                rank[j][employee] = rankIndex;
            }
        }

        // 创建队列 freeEmployees 用于存储目前处于自由状态（未匹配）的新人的编号
        Queue<Integer> freeEmployees = new LinkedList<>();
        // 将所有新人（编号 0 到 n-1）初始化为自由状态，加入队列
        for (int i = 0; i < n; i++) {
            freeEmployees.offer(i);
        }

        // 开始 Gale-Shapley 算法的匹配过程，直到所有新人均匹配到公司组
        while (!freeEmployees.isEmpty()) { // 当队列不为空时
            // 从队列中取出一个自由新人 employee
            int employee = freeEmployees.poll();
            // 获取新人 employee 在其偏好列表中的下一个待提议的公司组的索引 proposalIndex
            int proposalIndex = nextProposalIndex[employee];
            // 如果该新人已经提议完所有公司组，则跳过（理论上不会发生，因为必有稳定匹配）
            if (proposalIndex >= n) continue;
            // 获取新人 employee 对应偏好列表中待提议的公司组编号 group
            int group = employeePrefs[employee][proposalIndex];
            // 更新新人 employee 的提议指针，指向下一个待提议的公司组
            nextProposalIndex[employee]++;
            // 判断公司组 group 是否未匹配（matchGroup[group] == -1 表示未匹配）
            if (matchGroup[group] == -1) {
                // 如果公司组 group 目前未匹配，则接受新人 employee 的提议
                // 将新人 employee 与公司组 group 建立匹配关系
                matchEmployee[employee] = group;
                matchGroup[group] = employee;
            } else { // 公司组 group 已经匹配了某个新人
                // 获取当前与公司组 group 匹配的新人的编号 currentEmployee
                int currentEmployee = matchGroup[group];
                // 比较公司组 group 对当前提议的新人 employee 与当前匹配的新人的偏好
                // 如果 rank[group][employee] 小于 rank[group][currentEmployee]，表示 group 更喜欢 employee
                if (rank[group][employee] < rank[group][currentEmployee]) {
                    // 公司组 group 接受新的提议，更新匹配关系，新人 employee 被匹配到 group
                    matchEmployee[employee] = group;
                    matchGroup[group] = employee;
                    // 原先匹配的新人 currentEmployee 解除匹配，重新回到自由状态
                    matchEmployee[currentEmployee] = -1;
                    freeEmployees.offer(currentEmployee);
                } else {
                    // 如果公司组 group 更喜欢当前匹配的新人，则拒绝新人 employee 的提议
                    // 将新人 employee 重新加入队列，等待下一次提议
                    freeEmployees.offer(employee);
                }
            }
        }
        // 返回最终的匹配结果数组，matchEmployee[i] 表示新人 i 被分配到的公司组编号
        return matchEmployee;
    }

    // 主函数用于测试稳定匹配算法
    public static void main(String[] args) {
        // 定义测试数据：假设有 3 个新人和 3 个公司组

        // 定义每个新人对各个公司组的偏好列表，按优先顺序排列（数字表示公司组的编号）
        int[][] employeePrefs = {
                {0, 1, 2}, // 新人 0 的偏好顺序：组 0 > 组 1 > 组 2
                {1, 0, 2}, // 新人 1 的偏好顺序：组 1 > 组 0 > 组 2
                {1, 2, 0}  // 新人 2 的偏好顺序：组 1 > 组 2 > 组 0
        };

        // 定义每个公司组对各个新人的偏好列表，按优先顺序排列（数字表示新人的编号）
        int[][] groupPrefs = {
                {1, 0, 2}, // 公司组 0 的偏好顺序：新人 1 > 新人 0 > 新人 2
                {0, 2, 1}, // 公司组 1 的偏好顺序：新人 0 > 新人 2 > 新人 1
                {0, 1, 2}  // 公司组 2 的偏好顺序：新人 0 > 新人 1 > 新人 2
        };

        // 调用 stableMatching 函数执行稳定匹配，并返回匹配结果数组
        int[] matchingResult = stableMatching(employeePrefs, groupPrefs);

        // 输出匹配结果：遍历匹配结果数组，打印每个新人被分配到的公司组编号
        System.out.println("匹配结果：");
        for (int i = 0; i < matchingResult.length; i++) {
            // 打印格式：新人 i 被分配到 公司组 matchingResult[i]
            System.out.println("新人 " + i + " 被分配到 公司组 " + matchingResult[i]);
        }
    }
}


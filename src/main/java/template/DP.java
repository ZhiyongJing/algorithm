package template;
import java.util.Arrays;

public class DP {
/**
 * ====================================================================================================================================================================
 * ============================================================================0-1 背包问题=============================================================================
 * ====================================================================================================================================================================
 *
 * 一个背包的总容量为c,现在有N类物品,第i类物品的重量为weight[i],价值为value[i]，那么往该背包里装东西,怎样装才能使得最终包内物品的总价值最大。每类物品最多只能装一次
 */
    //Solution1: Top-Down dp[i, c] = max(dp[i-1, c], dp[i-1, c - wgt[i-1]] + val[i-1])
    int knapsackDFSMem(int[] wgt, int[] val, int[][] mem, int i, int c) {
        // 若已选完所有物品或背包无剩余容量，则返回价值 0
        if (i == 0 || c == 0) {
            return 0;
        }
        // 若已有记录，则直接返回
        if (mem[i][c] != -1) {
            return mem[i][c];
        }
        // 若超过背包容量，则只能选择不放入背包
        if (wgt[i - 1] > c) {
            return knapsackDFSMem(wgt, val, mem, i - 1, c);
        }
        // 计算不放入和放入物品 i 的最大价值
        int no = knapsackDFSMem(wgt, val, mem, i - 1, c);
        int yes = knapsackDFSMem(wgt, val, mem, i - 1, c - wgt[i - 1]) + val[i - 1];
        // 记录并返回两种方案中价值更大的那一个
        mem[i][c] = Math.max(no, yes);
        return mem[i][c];
    }

    //Solution2: Bottom-Up。 dp[i, c] = max(dp[i-1, c], dp[i-1, c - wgt[i-1]] + val[i-1])
    int knapsackDP(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        // 初始化 dp 表
        int[][] dp = new int[n + 1][cap + 1];
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= cap; c++) {
                if (wgt[i - 1] > c) {
                    // 若超过背包容量，则不选物品 i
                    dp[i][c] = dp[i - 1][c];
                } else {
                    // 不选和选物品 i 这两种方案的较大值
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[n][cap];
    }

    //Solution3: 基于Solution2 空间优化。 dp[c] = max(dp【c], dp[c - wgt[i-1]] + val[i-1])
    int knapsackDPComp(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        // 初始化 dp 表
        int[] dp = new int[cap + 1];
        // 状态转移
        for (int i = 1; i <= n; i++) {
            // 倒序遍历
            for (int c = cap; c >= 1; c--) {
                if (wgt[i - 1] <= c) {
                    // 不选和选物品 i 这两种方案的较大值
                    dp[c] = Math.max(dp[c], dp[c - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[cap];
    }
/**
 * ====================================================================================================================================================================
 * ============================================================================多重背包问题==============================================================================
 * ====================================================================================================================================================================
 *
 * 一个背包的总容量为c,现在有N类物品,第i类物品的重量为weight[i],价值为value[i]，那么往该背包里装东西,怎样装才能使得最终包内物品的总价值最大。每类物品最多装num[i-1]次
 */

    int limitedKnapsackDP(int[] weight, int[] value, int[] num, int cap) {
        int n = weight.length;
        // 初始化 dp 表
        int[][] dp = new int[n + 1][cap + 1];
        // 状态转移
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= cap; c++) {
                if (weight[i - 1] > c) {
                    // 若超过背包容量，则不选物品 i
                    dp[i][c] = dp[i - 1][c];
                } else {
                    // 不选和选物品 i 这两种方案的较大值
                    int maxV = Math.min(num[i - 1], c / weight[i - 1]);
                    for (int k = 0; k < maxV + 1; k++) {
                        dp[i][c] = dp[i][c] > Math.max(dp[i - 1][c], dp[i - 1][c - k * weight[i - 1]] + k * value[i - 1]) ?
                                dp[i][c] : Math.max(dp[i - 1][c], dp[i - 1][c - k * weight[i - 1]] + k * value[i - 1]);
                    }
                }
            }
        }
        return dp[n][cap];
    }

/**
 * ====================================================================================================================================================================
 * ============================================================================完全背包问题==============================================================================
 * ====================================================================================================================================================================
 *
 * 一个背包的总容量为c,现在有N类物品,第i类物品的重量为weight[i],价值为value[i]，那么往该背包里装东西,怎样装才能使得最终包内物品的总价值最大。每类物品最多装无数次
 */

    //Soluiton1: Bottom-Up. dp[i][c] = dp[i - 1][c], dp[i][c - wgt[i - 1]] + val[i - 1] */
    int unboundedKnapsackDP(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        // 初始化 dp 表
        int[][] dp = new int[n + 1][cap + 1];
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= cap; c++) {
                if (wgt[i - 1] > c) {
                    // 若超过背包容量，则不选物品 i
                    dp[i][c] = dp[i - 1][c];
                } else {
                    // 不选和选物品 i 这两种方案的较大值
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i][c - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[n][cap];
    }

    //Solution2: 基于Solution1 空间优化后的动态规划。dp[c] = max(dp[c], dp[c - wgt[i - 1]] + val[i - 1])
    int unboundedKnapsackDPComp2(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        // 初始化 dp 表
        int[] dp = new int[cap + 1];
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= cap; c++) {
                if (wgt[i - 1] > c) {
                    // 若超过背包容量，则不选物品 i
                    dp[c] = dp[c];
                } else {
                    // 不选和选物品 i 这两种方案的较大值
                    dp[c] = Math.max(dp[c], dp[c - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[cap];
    }

/**
 * ====================================================================================================================================================================
 * ============================================================================换硬币问题：最少数量=======================================================================
 * ====================================================================================================================================================================
 *
 * 给定 n 种硬币，第  种硬币的面值为 coins[i - 1]  ，目标金额为 amt ，每种硬币可以重复选取，问凑出目标金额的最少硬币数量
 */
    //Solution1: Bottom-Up
    int coinChangeDP(int[] coins, int amt) {
        int n = coins.length;
        int MAX = amt + 1;
        // 初始化 dp 表
        int[][] dp = new int[n + 1][amt + 1];
        // 状态转移：首行首列
        for (int a = 1; a <= amt; a++) {
            dp[0][a] = MAX;
        }
        // 状态转移：其余行和列
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amt; a++) {
                if (coins[i - 1] > a) {
                    // 若超过目标金额，则不选硬币 i
                    dp[i][a] = dp[i - 1][a];
                } else {
                    // 不选和选硬币 i 这两种方案的较小值
                    dp[i][a] = Math.min(dp[i - 1][a], dp[i][a - coins[i - 1]] + 1);
                }
            }
        }
        return dp[n][amt] != MAX ? dp[n][amt] : -1;
    }
    //Solution2: 基于Soluiton1 空间优化后的动态规划
    int coinChangeDPComp(int[] coins, int amt) {
        int n = coins.length;
        int MAX = amt + 1;
        // 初始化 dp 表
        int[] dp = new int[amt + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amt; a++) {
                if (coins[i - 1] > a) {
                    // 若超过目标金额，则不选硬币 i
                    dp[a] = dp[a];
                } else {
                    // 不选和选硬币 i 这两种方案的较小值
                    dp[a] = Math.min(dp[a], dp[a - coins[i - 1]] + 1);
                }
            }
        }
        return dp[amt] != MAX ? dp[amt] : -1;
    }
/**
 * ====================================================================================================================================================================
 * ============================================================================换硬币问题：组合数量=======================================================================
 * ====================================================================================================================================================================
 *
 * 给定 n 种硬币，第  种硬币的面值为 coins[i - 1]  ，目标金额为 amt ，每种硬币可以重复选取，问凑出目标金额的组合数量
 */

    //Solution1: Bottom-Up.
    int coinChangeIIDP(int[] coins, int amt) {
        int n = coins.length;
        // 初始化 dp 表
        int[][] dp = new int[n + 1][amt + 1];
        // 初始化首列
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amt; a++) {
                if (coins[i - 1] > a) {
                    // 若超过目标金额，则不选硬币 i
                    dp[i][a] = dp[i - 1][a];
                } else {
                    // 不选和选硬币 i 这两种方案之和
                    dp[i][a] = dp[i - 1][a] + dp[i][a - coins[i - 1]];
                }
            }
        }
        return dp[n][amt];
    }
    //Solution2: 基于Solution1 的空间优化
    int coinChangeIIDPComp(int[] coins, int amt) {
        int n = coins.length;
        // 初始化 dp 表
        int[] dp = new int[amt + 1];
        dp[0] = 1;
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amt; a++) {
                if (coins[i - 1] > a) {
                    // 若超过目标金额，则不选硬币 i
                    dp[a] = dp[a];
                } else {
                    // 不选和选硬币 i 这两种方案之和
                    dp[a] = dp[a] + dp[a - coins[i - 1]];
                }
            }
        }
        return dp[amt];
    }


}

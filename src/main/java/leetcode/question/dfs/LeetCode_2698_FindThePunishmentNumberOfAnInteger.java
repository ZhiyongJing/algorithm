package leetcode.question.dfs;

import java.util.Arrays;

/**
 *@Question:  2698. Find the Punishment Number of an Integer
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 77.19%
 *@Time  Complexity: O(N * 2^(log10N))
 *@Space Complexity: O(N * log10N)
 */
/**
 * 第一部分：题目描述
 * ----------------------------------------------
 * 2698. Find the Punishment Number of an Integer
 * （计算整数的惩罚数）
 *
 * 题目要求：
 * 给定一个整数 n，计算范围 [1, n] 内所有满足特定条件的数的平方和。
 * 条件：
 * - 计算每个数的平方 `squareNum = num * num`
 * - 将 `squareNum` 拆分成多个子字符串，使得所有子字符串的数值之和等于 `num`
 * - 如果可以找到这样的一种划分，则 `squareNum` 计入最终的惩罚数
 * - 返回所有符合条件的 `squareNum` 之和
 *
 * 示例：
 * 输入：n = 10
 * 输出：182
 * 解释：
 * - 1² = 1, "1" → 满足条件
 * - 9² = 81, "8" + "1" = 9 → 满足条件
 * - 10² = 100, "10" + "0" = 10 → 满足条件
 * - 总和：1 + 81 + 100 = 182
 *
 * 输入：n = 5
 * 输出：45
 * 解释：
 * - 1² = 1, "1" → 满足条件
 * - 5² = 25, "2" + "5" = 5 → 满足条件
 * - 总和：1 + 25 = 45
 */


/**
 * 第二部分：详细解题思路（包含超级详细的步骤和举例）
 * ----------------------------------------------
 * 1. **遍历 1 到 n 的所有数字**
 *    - 计算每个数字 `currentNum` 的平方 `squareNum`
 *    - 例如：
 *        - `currentNum = 10`, `squareNum = 100`
 *        - `currentNum = 9`, `squareNum = 81`
 *
 * 2. **判断 `squareNum` 是否可以被拆分成多个子数字，其和等于 `currentNum`**
 *    - 使用递归 + 记忆化搜索 (`findPartitions` 方法)
 *    - 遍历 `squareNum` 的所有可能拆分方式：
 *        - 例如：
 *            - `squareNum = 100`, `currentNum = 10`
 *            - 可能的拆分：
 *                - "1" + "00" = 1 + 0 = 1（不满足）
 *                - "10" + "0" = 10 + 0 = 10（满足）
 *            - `squareNum = 81`, `currentNum = 9`
 *            - 可能的拆分：
 *                - "8" + "1" = 8 + 1 = 9（满足）
 *
 * 3. **递归检查每种拆分是否满足条件**
 *    - 递归地尝试所有可能的子字符串划分：
 *        - 例如：
 *            - `squareNum = 2025`, `currentNum = 45`
 *            - 可能的拆分：
 *                - "2" + "0" + "25" = 2 + 0 + 25 = 27（不满足）
 *                - "20" + "25" = 20 + 25 = 45（满足）
 *
 * 4. **使用记忆化搜索优化**
 *    - `memo[startIndex][sum]` 记录之前计算过的结果，避免重复计算。
 *    - 例如：
 *        - `squareNum = 2025`
 *        - `startIndex = 1, sum = 20` → 计算 `25` 的值
 *        - 记录 `memo[1][20] = 1`，下次遇到相同状态直接返回结果。
 *
 * 5. **累加所有符合条件的 `squareNum`**
 *    - 例如：
 *        - `1² = 1`（符合）→ 加入惩罚数
 *        - `9² = 81`（符合）→ 加入惩罚数
 *        - `10² = 100`（符合）→ 加入惩罚数
 *    - 最终返回 `1 + 81 + 100 = 182`
 */


/**
 * 第三部分：时间和空间复杂度分析
 * ----------------------------------------------
 * **时间复杂度：O(n * m * 2^m)**
 * - 遍历 `1` 到 `n`，有 `O(n)` 次循环。
 * - 每个 `squareNum` 长度最多 `m = log(n^2)`（即 `2 log(n)`）。
 * - 需要尝试所有拆分方案，每个字符有 `2^m` 种拆分方式。
 * - 递归调用 `findPartitions` 需要 `O(m * 2^m)`。
 * - 总体时间复杂度约为 `O(n * m * 2^m)`，其中 `m` 取决于 `n` 的大小。
 *
 * **空间复杂度：O(n * m)**
 * - 主要消耗在 `memo[][]` 数组：
 *    - 记忆化搜索的 `memo[][]` 大小为 `O(m * n)`
 * - 递归调用栈深度 `O(m)`。
 * - 总空间复杂度 `O(n * m)`。
 *
 * **总结：**
 * - 由于 `2^m` 增长较快，该算法对于较大的 `n` 可能会变慢。
 * - 但对于 `n ≤ 1000` 仍然可行。
 */

public class LeetCode_2698_FindThePunishmentNumberOfAnInteger{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean findPartitions(
                int startIndex,
                int sum,
                String stringNum,
                int target,
                int[][] memo
        ) {
            // 如果当前索引已经到达字符串末尾，则检查累加和是否等于目标值
            if (startIndex == stringNum.length()) {
                return sum == target;
            }

            // 如果当前累加和已经超过目标值，直接返回 false
            if (sum > target) return false;

            // 记忆化搜索：如果之前计算过当前状态，则直接返回结果
            if (memo[startIndex][sum] != -1) return memo[startIndex][sum] == 1;

            boolean partitionFound = false; // 记录是否找到有效的划分

            // 遍历从 startIndex 开始的所有可能的子字符串
            for (
                    int currentIndex = startIndex;
                    currentIndex < stringNum.length();
                    currentIndex++
            ) {
                // 截取当前子字符串
                String currentString = stringNum.substring(
                        startIndex,
                        currentIndex + 1
                );
                int addend = Integer.parseInt(currentString); // 将字符串转换为整数

                // 递归检查是否存在有效的划分
                partitionFound =
                        partitionFound ||
                                findPartitions(
                                        currentIndex + 1, // 继续往后查找
                                        sum + addend, // 累加当前数值
                                        stringNum, // 传递当前数字字符串
                                        target, // 目标值
                                        memo // 记忆化数组
                                );
                if (partitionFound) {
                    memo[startIndex][sum] = 1; // 记录结果
                    return true;
                }
            }

            // 记忆化存储当前状态的结果，并返回 false
            memo[startIndex][sum] = 0;
            return false;
        }

        public int punishmentNumber(int n) {
            int punishmentNum = 0; // 记录最终的惩罚数

            // 遍历范围 [1, n] 内的所有数
            for (int currentNum = 1; currentNum <= n; currentNum++) {
                int squareNum = currentNum * currentNum; // 计算当前数的平方
                String stringNum = Integer.toString(squareNum); // 转换为字符串，方便进行分割

                // 初始化记忆化数组
                int[][] memoArray = new int[stringNum.length()][currentNum + 1];
                for (int[] row : memoArray) {
                    java.util.Arrays.fill(row, -1); // 设定初始值 -1，表示未计算
                }

                // 如果可以找到有效的划分，使得各部分之和等于 currentNum，则累加其平方值
                if (findPartitions(0, 0, stringNum, currentNum, memoArray)) {
                    punishmentNum += squareNum;
                }
                for( int[] m: memoArray){
                    System.out.print(Arrays.toString(m) + ", ");
                }
            }
            return punishmentNum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_2698_FindThePunishmentNumberOfAnInteger().new Solution();

//        // 测试用例 1: n = 10
//        System.out.println(solution.punishmentNumber(10)); // 预期输出: 182
//
//        // 测试用例 2: n = 5
//        System.out.println(solution.punishmentNumber(5)); // 预期输出: 1

        // 测试用例 3: n = 15
        System.out.println(solution.punishmentNumber(37)); // 预期输出: 1478

//        // 测试用例 4: n = 20
//        System.out.println(solution.punishmentNumber(20)); // 预期输出: 627
//
//        // 测试用例 5: n = 1（最小边界）
//        System.out.println(solution.punishmentNumber(1)); // 预期输出: 1
    }
}

/**
 Given a positive integer n, return the punishment number of n.

 The punishment number of n is defined as the sum of the squares of all 
 integers i such that:


 1 <= i <= n 
 The decimal representation of i * i can be partitioned into contiguous 
 substrings such that the sum of the integer values of these substrings equals i.



 Example 1: 


 Input: n = 10
 Output: 182
 Explanation: There are exactly 3 integers i in the range [1, 10] that satisfy
 the conditions in the statement:
 - 1 since 1 * 1 = 1
 - 9 since 9 * 9 = 81 and 81 can be partitioned into 8 and 1 with a sum equal to
 8 + 1 == 9.
 - 10 since 10 * 10 = 100 and 100 can be partitioned into 10 and 0 with a sum
 equal to 10 + 0 == 10.
 Hence, the punishment number of 10 is 1 + 81 + 100 = 182


 Example 2: 


 Input: n = 37
 Output: 1478
 Explanation: There are exactly 4 integers i in the range [1, 37] that satisfy
 the conditions in the statement:
 - 1 since 1 * 1 = 1.
 - 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1.
 - 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0.
 - 36 since 36 * 36 = 1296 and 1296 can be partitioned into 1 + 29 + 6.
 Hence, the punishment number of 37 is 1 + 81 + 100 + 1296 = 1478



 Constraints: 


 1 <= n <= 1000 


 Related Topics Math Backtracking 👍 1138 👎 224

 */
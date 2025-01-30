package leetcode.question.dp;

import java.util.ArrayList;
import java.util.List;
/**
 *@Question:  119. Pascal's Triangle II
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 31.02%
 *@Time  Complexity: O(K ^2) K is the Kth row.
 *@Space Complexity: O(2K) for solution 1, O(K) for solution2
 */


/**
 * 第一部分：题目描述
 *
 * 题目：119. 杨辉三角 II（Pascal's Triangle II）
 *
 * 题目描述：
 * 1. 给定一个非负索引 rowIndex，返回帕斯卡三角形（杨辉三角）**的第 rowIndex 行**。
 * 2. 0 行是 `[1]`，1 行是 `[1,1]`，依次构建直到 `rowIndex`。
 * 3. 每一行的元素由 **前一行的元素相加** 生成：
 *    - `row[i][j] = row[i-1][j-1] + row[i-1][j]`
 * 4. 需要**高效**地生成结果，并返回 **列表**。
 *
 * **示例**
 * ```
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 *
 * 输入: rowIndex = 5
 * 输出: [1,5,10,10,5,1]
 * ```
 *
 * **要求**
 * - **时间复杂度尽可能低**
 * - **空间复杂度尽可能优化**
 */

/**
 * 第二部分：解题思路（基于代码的超级详细解析）
 *
 * 代码提供了 **两种解法**
 * 1. **动态规划（DP）**（存储前一行，生成当前行）
 * 2. **空间优化方案**（使用单个列表，从右向左更新）
 *
 * ---------------------------------------
 * **方法 1：使用动态规划（DP）**
 * ---------------------------------------
 * **思路**
 * 1. **初始化** `prev = [1]`，表示第一行
 * 2. **遍历从 1 到 rowIndex**：
 *    - 创建 `curr = [1]`
 *    - 计算 `curr[j] = prev[j-1] + prev[j]`（中间元素）
 *    - 末尾添加 `1`
 *    - `prev = curr`（更新为当前行）
 * 3. **返回 `prev`，即目标行**
 *
 * **示例解析（rowIndex = 5）**
 * ```
 * row 0: [1]
 * row 1: [1,1]
 * row 2: [1,2,1]
 * row 3: [1,3,3,1]
 * row 4: [1,4,6,4,1]
 * row 5: [1,5,10,10,5,1]
 * ```
 * **时间复杂度：O(rowIndex²)**
 * - 遍历每一行 `O(rowIndex)`，每行计算 `O(rowIndex)`
 *
 * **空间复杂度：O(rowIndex)**
 * - 仅存储 `prev` 和 `curr` 两行数据
 *
 * ---------------------------------------
 * **方法 2：使用单列表进行优化**
 * ---------------------------------------
 * **思路**
 * 1. **初始化** `row = [1]`
 * 2. **遍历从 0 到 rowIndex**：
 *    - **从右向左** 更新 `row[j] = row[j] + row[j-1]`
 *    - 在末尾 `add(1)`
 * 3. **返回 `row`**
 *
 * **示例解析（rowIndex = 5）**
 * ```
 * 初始 row: [1]
 * i=1: [1,1]
 * i=2: [1,2,1]
 * i=3: [1,3,3,1]
 * i=4: [1,4,6,4,1]
 * i=5: [1,5,10,10,5,1]
 * ```
 * **关键优化：**
 * - **从右向左计算**，避免覆盖旧值
 * - **空间复杂度优化到 O(rowIndex)**
 *
 * **时间复杂度：O(rowIndex²)**
 * - 遍历 `O(rowIndex)` 行，每行计算 `O(rowIndex)`
 *
 * **空间复杂度：O(rowIndex)**
 * - 只使用一个列表存储当前行
 */

/**
 * 第三部分：时间和空间复杂度分析
 *
 * **方法 1（动态规划）**
 * - **时间复杂度：O(rowIndex²)** - 计算每一行需要遍历所有元素
 * - **空间复杂度：O(rowIndex)** - 只存储两行数据
 *
 * **方法 2（单列表优化）**
 * - **时间复杂度：O(rowIndex²)** - 仍需遍历所有元素
 * - **空间复杂度：O(rowIndex)** - 仅存储一行
 *
 * **方法 2 更优**，因为减少了空间占用
 */

public class LeetCode_119_PascalsTriangleIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // solution1: 使用动态规划（DP）
        /**
         * 解决方案1：动态规划
         * 我们使用 **动态规划**（DP）来生成帕斯卡三角形的某一指定行。
         *
         * **思路：**
         * 1. **初始化第一行**：第 0 行是 `[1]`。
         * 2. **逐步构造下一行**：
         *    - 创建一个新的 `curr` 列表，并设置第一个元素为 `1`（因为每一行的开头都是 `1`）。
         *    - 遍历前一行 `prev`，计算新行的中间部分，公式：
         *      `curr[j] = prev[j - 1] + prev[j]`
         *    - 添加 `1` 作为新行的最后一个元素。
         * 3. **更新 `prev` 指向 `curr`，继续下一行**，直到达到 `rowIndex` 行。
         * 4. **返回 `prev`，即目标行**。
         *
         * **示例解析：**
         * `rowIndex = 5`
         * ```
         * row 0: [1]
         * row 1: [1, 1]
         * row 2: [1, 2, 1]
         * row 3: [1, 3, 3, 1]
         * row 4: [1, 4, 6, 4, 1]
         * row 5: [1, 5, 10, 10, 5, 1]
         * ```
         *
         * **时间复杂度：** O(rowIndex²) - 需要遍历所有元素
         * **空间复杂度：** O(rowIndex) - 仅存储两行数据
         */
        public List<Integer> getRow(int rowIndex) {
            // 初始化第一行
            List<Integer> curr, prev = new ArrayList<Integer>() {
                {
                    add(1); // 第0行是 [1]
                }
            };

            // 逐步构造到第 rowIndex 行
            for (int i = 1; i <= rowIndex; i++) {
                // 初始化当前行，首元素为 1
                curr = new ArrayList<Integer>(i + 1) {
                    {
                        add(1);
                    }
                };

                // 计算中间元素
                for (int j = 1; j < i; j++) {
                    curr.add(prev.get(j - 1) + prev.get(j));
                }

                // 添加最后一个 1
                curr.add(1);

                // 更新 prev 指向当前行
                prev = curr;
            }

            return prev;
        }

        // solution2: 使用单列表进行优化
        /**
         * 解决方案2：双重循环
         * 在之前的方法中，我们存储了 `prev` 列表，但实际上可以使用 **单个列表** 进行优化。
         *
         * **思路：**
         * 1. **初始化第0行**：列表 `row` 仅包含 `[1]`。
         * 2. **逐步计算到 rowIndex 行**：
         *    - 从 `rowIndex = 0` 开始，循环增加新的一行。
         *    - 每次计算时，从 **右向左** 更新 `row[j]`，避免影响未更新的值：
         *      `row[j] = row[j] + row[j - 1]`
         *    - 在每一行最后 `add(1)` 作为尾部。
         * 3. **返回最终计算出的 `row`**。
         *
         * **示例解析：**
         * `rowIndex = 5`
         * ```
         * row 0: [1]
         * row 1: [1, 1]
         * row 2: [1, 2, 1]
         * row 3: [1, 3, 3, 1]
         * row 4: [1, 4, 6, 4, 1]
         * row 5: [1, 5, 10, 10, 5, 1]
         * ```
         * **注意**：更新 `row[j]` 时，从 **右向左** 进行，确保计算不覆盖原始数据。
         *
         * **时间复杂度：** O(rowIndex²) - 需要遍历所有元素
         * **空间复杂度：** O(rowIndex) - 只使用单个列表
         */
        public List<Integer> getRow2(int rowIndex) {
            // 初始化第一行
            List<Integer> row = new ArrayList<Integer>(rowIndex + 1) {
                {
                    add(1);
                }
            };

            // 逐步计算到第 rowIndex 行
            for (int i = 0; i < rowIndex; i++) {
                // **从右向左** 计算新值，避免覆盖原数据
                for (int j = i; j > 0; j--) {
                    row.set(j, row.get(j) + row.get(j - 1));
                }
                // 添加最后的 1
                row.add(1);
            }

            return row;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_119_PascalsTriangleIi().new Solution();

        // 测试样例1：获取第5行
        System.out.println("测试样例1：获取第5行");
        List<Integer> result1 = solution.getRow(5);
        System.out.println(result1);
        // 预期输出: [1, 5, 10, 10, 5, 1]

        // 测试样例2：获取第8行
        System.out.println("测试样例2：获取第8行");
        List<Integer> result2 = solution.getRow(8);
        System.out.println(result2);
        // 预期输出: [1, 8, 28, 56, 70, 56, 28, 8, 1]

        // 测试样例3：使用方法2 获取第5行
        System.out.println("测试样例3：使用方法2 获取第5行");
        List<Integer> result3 = solution.getRow2(5);
        System.out.println(result3);
        // 预期输出: [1, 5, 10, 10, 5, 1]

        // 测试样例4：使用方法2 获取第8行
        System.out.println("测试样例4：使用方法2 获取第8行");
        List<Integer> result4 = solution.getRow2(8);
        System.out.println(result4);
        // 预期输出: [1, 8, 28, 56, 70, 56, 28, 8, 1]
    }
}


/**
Given an integer rowIndex, return the rowIndexᵗʰ (0-indexed) row of the 
Pascal's triangle. 

 In Pascal's triangle, each number is the sum of the two numbers directly above 
it as shown: 
 
 
 Example 1: 
 Input: rowIndex = 3
Output: [1,3,3,1]
 
 Example 2: 
 Input: rowIndex = 0
Output: [1]
 
 Example 3: 
 Input: rowIndex = 1
Output: [1,1]
 
 
 Constraints: 

 
 0 <= rowIndex <= 33 
 

 
 Follow up: Could you optimize your algorithm to use only O(rowIndex) extra 
space? 

 Related Topics Array Dynamic Programming 👍 4802 👎 338

*/
package leetcode.question.greedy;
/**
 *@Question:  670. Maximum Swap
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 84.62%
 *@Time Complexity: O(N) // 需要遍历两次数字的字符数组
 *@Space Complexity: O(N) // 需要额外存储最大索引数组
 */
/**
 * 题目描述：
 * LeetCode 670. Maximum Swap
 *
 * 给定一个非负整数 `num`，最多可以交换 **一次** 其中的两个不同数字的位置，使其变成 **最大的可能值**。
 *
 * **要求：** 返回交换后的最大整数（如果不需要交换，则直接返回 `num`）。
 *
 * **示例 1：**
 * ```
 * 输入: num = 2736
 * 输出: 7236
 * 解释:
 * - 交换 `2` 和 `7` 后，得到最大值 `7236`
 * ```
 *
 * **示例 2：**
 * ```
 * 输入: num = 9973
 * 输出: 9973
 * 解释:
 * - 已经是最大值，无需交换
 * ```
 *
 * ---
 *
 * **解题思路：**
 *
 * 1. **将数字转换为字符数组**
 *    - 便于后续 **交换** 和 **遍历**。
 *
 * 2. **记录每一位右侧的最大数字**
 *    - 创建 `maxRightIndex` 数组：
 *      - 其中 `maxRightIndex[i]` 记录 **从索引 `i` 到数组末尾的最大数字的索引**。
 *      - 从 **右向左遍历** `numArr`，逐步更新 `maxRightIndex`。
 *
 * 3. **从左向右查找可以交换的数**
 *    - 遍历 `numArr`：
 *      - 如果 `numArr[i]` 小于 `maxRightIndex[i]` 位置的数字：
 *        - 交换 `numArr[i]` 和 `numArr[maxRightIndex[i]]`，并返回交换后的整数。
 *
 * ---
 * **示例解析**
 *
 * **示例 1：`num = 2736`**
 *
 * **步骤 1：计算 `maxRightIndex`**
 * ```
 *  索引:  0  1  2  3
 *  数字:  2  7  3  6
 *  maxRightIndex: 1  1  3  3
 * ```
 * - `maxRightIndex[0] = 1`，因为 `7` 是 `2` 右侧的最大值。
 * - `maxRightIndex[2] = 3`，因为 `6` 是 `3` 右侧的最大值。
 *
 * **步骤 2：寻找交换点**
 * - `numArr[0] = 2` **小于** `numArr[1] = 7`，交换 `2` 和 `7`。
 * - **结果：`7236`**
 *
 * ---
 *
 * **示例 2：`num = 98368`**
 *
 * **步骤 1：计算 `maxRightIndex`**
 * ```
 *  索引:  0  1  2  3  4
 *  数字:  9  8  3  6  8
 *  maxRightIndex: 0  4  4  4  4
 * ```
 * - `maxRightIndex[2] = 4`，因为 `8` 是 `3` 右侧的最大值。
 *
 * **步骤 2：寻找交换点**
 * - `numArr[2] = 3` **小于** `numArr[4] = 8`，交换 `3` 和 `8`。
 * - **结果：`98863`**
 *
 * ---
 * **时间和空间复杂度分析**
 *
 * - **时间复杂度：O(N)**
 *   - 需要遍历 `numArr` **两次**：
 *     1. **计算 `maxRightIndex`** 需要 **O(N)**。
 *     2. **寻找交换点** 需要 **O(N)**。
 *   - **总时间复杂度：O(N)**。
 *
 * - **空间复杂度：O(N)**
 *   - 额外使用了 `maxRightIndex` 数组，存储 `N` 个索引。
 *   - **总空间复杂度：O(N)**。
 */


public class LeetCode_670_MaximumSwap{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int maximumSwap(int num) {
            // 将数字转换为字符数组，方便进行交换
            char[] numArr = Integer.toString(num).toCharArray();
            int n = numArr.length; // 获取数字的位数

            // 创建数组 maxRightIndex，存储从当前索引到最右侧的最大数字的索引
            int[] maxRightIndex = new int[n];

            // 初始化 maxRightIndex 的最后一位
            maxRightIndex[n - 1] = n - 1;

            // 从右向左遍历，记录每一位右侧的最大数字索引
            for (int i = n - 2; i >= 0; --i) {
                // 记录右侧最大值的索引
                maxRightIndex[i] = (numArr[i] > numArr[maxRightIndex[i + 1]])
                        ? i // 如果当前数字比右侧的最大数字更大，更新索引
                        : maxRightIndex[i + 1]; // 否则，保持右侧的最大索引
            }

            // 从左向右遍历，寻找第一个可以交换的数字
            for (int i = 0; i < n; ++i) {
                // 如果当前数字小于右侧的最大数字，则交换
                if (numArr[i] < numArr[maxRightIndex[i]]) {
                    // 交换当前数字和最大数字
                    char temp = numArr[i];
                    numArr[i] = numArr[maxRightIndex[i]];
                    numArr[maxRightIndex[i]] = temp;

                    // 交换后返回新的最大数
                    return Integer.parseInt(new String(numArr));
                }
            }

            // 如果未进行任何交换，则返回原数字
            return num;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_670_MaximumSwap().new Solution();

        // 测试用例 1
        int num1 = 2736;
        System.out.println("最大交换结果: " + solution.maximumSwap(num1));
        // 预期输出: 7236（交换 2 和 7）

        // 测试用例 2
        int num2 = 9973;
        System.out.println("最大交换结果: " + solution.maximumSwap(num2));
        // 预期输出: 9973（无需交换）

        // 测试用例 3
        int num3 = 98368;
        System.out.println("最大交换结果: " + solution.maximumSwap(num3));
        // 预期输出: 98863（交换 3 和 8）
    }
}

/**
You are given an integer num. You can swap two digits at most once to get the 
maximum valued number. 

 Return the maximum valued number you can get. 

 
 Example 1: 

 
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
 

 Example 2: 

 
Input: num = 9973
Output: 9973
Explanation: No swap.
 

 
 Constraints: 

 
 0 <= num <= 10⁸ 
 

 Related Topics Math Greedy 👍 4131 👎 262

*/
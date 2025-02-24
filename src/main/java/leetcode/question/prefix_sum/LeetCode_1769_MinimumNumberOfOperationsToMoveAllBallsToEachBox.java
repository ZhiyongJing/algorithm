package leetcode.question.prefix_sum;

import java.util.Arrays;

/**
 *@Question:  1769. Minimum Number of Operations to Move All Balls to Each Box
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 68.33%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * 题目描述：
 * 1769. Minimum Number of Operations to Move All Balls to Each Box
 *
 * 给定一个二进制字符串 `boxes`，其中 `boxes[i]` 为 `'0'` 或 `'1'`：
 * - `'1'` 代表该箱子中有一个球。
 * - `'0'` 代表该箱子为空。
 *
 * 计算一个数组 `answer`，其中 `answer[i]` 表示将 **所有球** 移动到 `boxes[i]` 需要的最少操作次数。
 * 每次操作可以将一个球从索引 `j` 移动到 `j-1` 或 `j+1`，操作次数等于 `|i - j|`。
 *
 * **示例 1：**
 * ```plaintext
 * 输入: boxes = "110"
 * 输出: [1, 1, 3]
 * 解释:
 * - 第 0 号箱子: 1 次移动 (1 个球从 index 1 -> index 0)
 * - 第 1 号箱子: 1 次移动 (1 个球从 index 0 -> index 1)
 * - 第 2 号箱子: 3 次移动 (1 个球从 index 0 -> index 2，1 个球从 index 1 -> index 2)
 * ```
 *
 * **示例 2：**
 * ```plaintext
 * 输入: boxes = "001011"
 * 输出: [11, 8, 5, 4, 3, 4]
 * ```
 *
 * ---
 *
 * **解题思路：**
 * 采用 **两次遍历（O(N) 计算）**，分别从左向右、从右向左遍历 `boxes`，高效计算每个箱子的最少移动次数：
 *
 * **步骤：**
 * - **第一次遍历（左向右）：计算左侧球的贡献**
 *   - `ballsToLeft` 记录当前遍历到的位置左侧的球的总数。
 *   - `movesToLeft` 记录所有左侧球到当前位置的总移动次数。
 *   - `movesToLeft` 累加到 `answer[i]`，作为部分答案。
 *   - 遇到 `'1'`，增加 `ballsToLeft` 计数，并更新 `movesToLeft`。
 *
 * - **第二次遍历（右向左）：计算右侧球的贡献**
 *   - `ballsToRight` 记录当前遍历到的位置右侧的球的总数。
 *   - `movesToRight` 记录所有右侧球到当前位置的总移动次数。
 *   - `movesToRight` 累加到 `answer[i]`，得到最终答案。
 *   - 遇到 `'1'`，增加 `ballsToRight` 计数，并更新 `movesToRight`。
 *
 * ---
 *
 * **举例计算**
 *
 * **输入:** `boxes = "110"`
 *
 * **第一遍从左向右**
 * ```plaintext
 * i = 0: answer[0] = 0, ballsToLeft = 1, movesToLeft = 1
 * i = 1: answer[1] = 1, ballsToLeft = 2, movesToLeft = 3
 * i = 2: answer[2] = 3, ballsToLeft = 2, movesToLeft = 5
 * ```
 *
 * **第二遍从右向左**
 * ```plaintext
 * i = 2: answer[2] = 3, ballsToRight = 0, movesToRight = 0
 * i = 1: answer[1] = 1, ballsToRight = 1, movesToRight = 1  ->  1 + 1 = 1
 * i = 0: answer[0] = 1, ballsToRight = 2, movesToRight = 3  ->  1 + 1 = 1
 * ```
 *
 * **最终结果:** `[1, 1, 3]`
 *
 * ---
 *
 * **时间复杂度分析**
 * - **遍历 `boxes` 两次**，每次 `O(N)`，总计 **O(N)**
 * - **空间复杂度:** 只使用 `O(1)` 额外变量，输出数组 `O(N)`
 * - **总时间复杂度: O(N)，空间复杂度: O(1)**（不考虑输出）
 */


public class LeetCode_1769_MinimumNumberOfOperationsToMoveAllBallsToEachBox{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int[] minOperations(String boxes) {
            int n = boxes.length(); // 获取字符串的长度
            int[] answer = new int[n]; // 创建结果数组，存储每个箱子的最小操作次数

            int ballsToLeft = 0, movesToLeft = 0; // 记录左侧的球数量和移动步数
            int ballsToRight = 0, movesToRight = 0; // 记录右侧的球数量和移动步数

            // 遍历 boxes 计算左侧和右侧的移动次数
            for (int i = 0; i < n; i++) {
                // 计算当前索引 i 处的移动次数
                answer[i] += movesToLeft; // 先累加左侧的移动次数
                ballsToLeft += Character.getNumericValue(boxes.charAt(i)); // 更新左侧球的数量
                movesToLeft += ballsToLeft; // 移动次数累加

                // 计算对称位置 j 处的移动次数
                int j = n - 1 - i; // 计算从右向左的索引
                answer[j] += movesToRight; // 先累加右侧的移动次数
                ballsToRight += Character.getNumericValue(boxes.charAt(j)); // 更新右侧球的数量
                movesToRight += ballsToRight; // 移动次数累加
                System.out.println("ballsToLeft: " + ballsToLeft + ", moveToLeft: " + movesToLeft);
                System.out.println("ballsToRight: " + ballsToRight + ", moveToRight: " + movesToRight);
            }

            return answer; // 返回计算得到的最小移动次数数组
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1769_MinimumNumberOfOperationsToMoveAllBallsToEachBox().new Solution();

        // 测试样例 1
        String boxes1 = "110";
        System.out.println(Arrays.toString(solution.minOperations(boxes1))); // 预期输出: [1, 1, 3]

        // 测试样例 2
        String boxes2 = "001011";
        System.out.println(Arrays.toString(solution.minOperations(boxes2))); // 预期输出: [11, 8, 5, 4, 3, 4]

        // 测试样例 3
        String boxes3 = "000";
        System.out.println(Arrays.toString(solution.minOperations(boxes3))); // 预期输出: [0, 0, 0]

        // 测试样例 4
        String boxes4 = "111";
        System.out.println(Arrays.toString(solution.minOperations(boxes4))); // 预期输出: [3, 2, 3]
    }
}


/**
You have n boxes. You are given a binary string boxes of length n, where boxes[
i] is '0' if the iᵗʰ box is empty, and '1' if it contains one ball. 

 In one operation, you can move one ball from a box to an adjacent box. Box i 
is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be 
more than one ball in some boxes. 

 Return an array answer of size n, where answer[i] is the minimum number of 
operations needed to move all the balls to the iᵗʰ box. 

 Each answer[i] is calculated considering the initial state of the boxes. 

 
 Example 1: 

 
Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first 
box in one operation.
2) Second box: you will have to move one ball from the first box to the second 
box in one operation.
3) Third box: you will have to move one ball from the first box to the third 
box in two operations, and move one ball from the second box to the third box in 
one operation.
 

 Example 2: 

 
Input: boxes = "001011"
Output: [11,8,5,4,3,4] 

 
 Constraints: 

 
 n == boxes.length 
 1 <= n <= 2000 
 boxes[i] is either '0' or '1'. 
 

 Related Topics Array String Prefix Sum 👍 2981 👎 128

*/
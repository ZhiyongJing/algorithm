package leetcode.question.string_list;
/**
 *@Question:  461. Hamming Distance
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 40.23%
 *@Time  Complexity: O(1)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目：461. Hamming Distance
 *
 * **题目描述**：
 * 给定两个整数 `x` 和 `y`，计算它们的汉明距离。汉明距离是指两个数字的二进制表示中不同位的数量。
 *
 * ### 解题思路
 *
 * 1. **计算异或**：
 *    - 汉明距离可以通过计算两个整数的异或值来获得。异或操作的特点是：如果两个对应的二进制位相同，则结果为 `0`；
 *    如果不同，则结果为 `1`。因此，通过对 `x` 和 `y` 进行异或操作，可以得到一个新的整数，
 *    其二进制表示中 `1` 的个数正是 `x` 和 `y` 的汉明距离。
 *
 * 2. **计算汉明距离**：
 *    - 在得到异或结果之后，我们需要计算其中 `1` 的个数。可以通过不断右移异或结果并检查最低位是否为 `1` 来实现。
 *    如果最低位为 `1`，则汉明距离增加 `1`；如果为 `0`，则继续右移直到所有位都处理完毕。
 *
 * ### 时间复杂度
 *
 * - **时间复杂度：O(1)**：
 *   - 计算异或和位移操作都是常数时间操作。无论输入的整数值如何，计算汉明距离的时间复杂度是常量级的。
 *
 * ### 空间复杂度
 *
 * - **空间复杂度：O(1)**：
 *   - 仅使用了常量级别的额外空间来存储中间变量，如异或结果和汉明距离计数器。因此，空间复杂度是常量级的。
 *
 * ### 总结
 *
 * - **时间复杂度**：O(1)
 * - **空间复杂度**：O(1)
 *
 * 通过上述思路和复杂度分析，我们可以高效地计算两个整数之间的汉明距离。
 */
public class LeetCode_461_HammingDistance{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int hammingDistance(int x, int y) {
            // 计算 x 和 y 的异或结果
            int xor = x ^ y;

            // 初始化汉明距离为 0
            int distance = 0;

            // 当异或结果不为 0 时，继续计算
            while (xor != 0) {
                // 如果最低位是 1，增加汉明距离
                if (xor % 2 == 1)
                    distance += 1;

                // 右移一位，检查下一位
                xor = xor >> 1;
            }

            // 返回最终的汉明距离
            return distance;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_461_HammingDistance().new Solution();

        // 测试用例 1
        int x1 = 1;
        int y1 = 4;
        System.out.println(solution.hammingDistance(x1, y1)); // 输出: 2

        // 测试用例 2
        int x2 = 2;
        int y2 = 14;
        System.out.println(solution.hammingDistance(x2, y2)); // 输出: 3

        // 测试用例 3
        int x3 = 3;
        int y3 = 8;
        System.out.println(solution.hammingDistance(x3, y3)); // 输出: 4
    }
}

/**
The Hamming distance between two integers is the number of positions at which 
the corresponding bits are different. 

 Given two integers x and y, return the Hamming distance between them. 

 
 Example 1: 

 
Input: x = 1, y = 4
Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
The above arrows point to positions where the corresponding bits are different.
 

 Example 2: 

 
Input: x = 3, y = 1
Output: 1
 

 
 Constraints: 

 
 0 <= x, y <= 2³¹ - 1 
 

 Related Topics Bit Manipulation 👍 3837 👎 218

*/
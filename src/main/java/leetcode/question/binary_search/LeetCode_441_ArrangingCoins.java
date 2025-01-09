package leetcode.question.binary_search;

/**
 *@Question:  441. Arranging Coins
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 52.25%
 *@Time  Complexity: O(logN)
 *@Space Complexity: O(1)
 */

/**
 * 题目描述:
 * 你总共有 `n` 个硬币。你需要将这些硬币排成几行，其中第 `i` 行包含 `i` 个硬币（从 1 开始）。你需要返回最多可以排列的完整行数。
 * 比如，给定 `n = 5`，最多可以排列 2 行，第一行 1 个硬币，第二行 2 个硬币，剩下的 2 个硬币不能形成完整的 3 行。
 *
 * 解题思路:
 *
 * 1. **数学建模**:
 *    - 每行需要的硬币数量是递增的，第一行需要 1 个硬币，第二行需要 2 个硬币，第三行需要 3 个硬币，依此类推。
 *    - 假设我们可以排成 `k` 行，那么前 `k` 行所需要的硬币数量是：
 *        - 1 + 2 + 3 + ... + k = k * (k + 1) / 2
 *    - 我们的目标是找到最大值 `k`，使得 `k * (k + 1) / 2 <= n`。

 * 2. **二分查找**:
 *    - 我们可以使用二分查找来高效地找到最大的 `k`。初始化 `left = 0` 和 `right = n`，然后进行二分查找。
 *    - 每次计算中间值 `k = (left + right) / 2`，计算前 `k` 行所需的硬币数量：
 *        - `curr = k * (k + 1) / 2`
 *    - 如果 `curr == n`，我们找到了恰好符合条件的 `k`，返回 `k`。
 *    - 如果 `curr < n`，则说明我们可以尝试增加行数，调整 `left = k + 1`。
 *    - 如果 `curr > n`，说明硬币不够，调整 `right = k - 1`。
 *    - 继续二分查找，直到 `left > right`。

 * 3. **返回结果**:
 *    - 最后返回 `right`，它代表了最大的完整行数。
 *
 * 时间复杂度和空间复杂度分析:
 *
 * - **时间复杂度**: O(log N)
 *   - 我们每次将搜索范围缩小一半，二分查找的时间复杂度是 O(log N)，其中 `N` 是硬币的数量。
 *
 * - **空间复杂度**: O(1)
 *   - 我们只使用了常数级别的额外空间，不需要额外的存储空间。

 */


public class LeetCode_441_ArrangingCoins{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 输入 n 表示有 n 个硬币，返回能够完全排列的最大完整行数
        public int arrangeCoins(int n) {
            // 使用二分查找法初始化左右边界，left = 0, right = n
            long left = 0, right = n;
            long k, curr;

            // 二分查找，找到最大完整的行数 k
            while (left <= right) {
                // 计算中间值 k
                k = left + (right - left) / 2;
                // 计算当前行数 k 的总硬币数
                curr = k * (k + 1) / 2;

                // 如果当前总硬币数等于 n，则 k 就是答案
                if (curr == n) return (int)k;

                // 如果当前总硬币数大于 n，减小 right 来缩小范围
                if (n < curr) {
                    right = k - 1;
                } else {
                    // 如果当前总硬币数小于 n，增大 left 来缩小范围
                    left = k + 1;
                }
            }
            // 返回 right，代表找到的最大行数
            return (int)right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_441_ArrangingCoins().new Solution();

        // 测试用例1: n = 5
        int n1 = 5;
        // 最多可以排列 2 行
        System.out.println("Test case 1: " + solution.arrangeCoins(n1)); // Expected output: 2

        // 测试用例2: n = 8
        int n2 = 8;
        // 最多可以排列 3 行
        System.out.println("Test case 2: " + solution.arrangeCoins(n2)); // Expected output: 3
    }
}

/**
You have n coins and you want to build a staircase with these coins. The 
staircase consists of k rows where the iᵗʰ row has exactly i coins. The last row of 
the staircase may be incomplete. 

 Given the integer n, return the number of complete rows of the staircase you 
will build. 

 
 Example 1: 
 
 
Input: n = 5
Output: 2
Explanation: Because the 3ʳᵈ row is incomplete, we return 2.
 

 Example 2: 
 
 
Input: n = 8
Output: 3
Explanation: Because the 4ᵗʰ row is incomplete, we return 3.
 

 
 Constraints: 

 
 1 <= n <= 2³¹ - 1 
 

 Related Topics Math Binary Search 👍 4003 👎 1348

*/
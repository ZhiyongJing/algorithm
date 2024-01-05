package leetcode.frequent.based_on_data_structure.queue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Question: 1492. The kth Factor of n
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 68.0%
 * @Time Complexity: O(sqrt(N) * log K), where N is the input number and K is the given factor index
 * @Space Complexity: O(min(K, sqrt(N))), where K is the given factor index
 */

/**
 * 这段代码的目标是找到给定数字 `n` 的第 `k` 个最大因子。以下是解题思路和复杂度分析：
 *
 * 1. 创建一个最大堆，用于存储因子。堆的大小限制为 `k`，确保最大的元素位于堆的顶部。
 *
 * 2. 遍历从 1 到 `sqrt(n)` 的所有因子 `x`，其中 `sqrt(n)` 是 `n` 的平方根。这是因为一个数字的因子是成对出现的，
 * 而平方根是这对因子的中点。在遍历过程中，将因子 `x` 和 `n/x` 推入最大堆中。
 *
 * 3. 如果堆的大小超过了 `k`，则删除堆顶元素，以保持堆的大小不超过 `k`。
 *
 * 4. 最终，如果堆的大小等于 `k`，则返回堆顶元素，否则返回 -1。
 *
 * 时间复杂度分析：
 * - 遍历因子的过程需要 O(sqrt(N)) 的时间，其中 `N` 是输入数字。
 * - 在最大堆中插入和删除元素的操作平均为 O(log K)，其中 `K` 是给定的因子索引。
 * - 因此，总体时间复杂度为 O(sqrt(N) * log K)。
 *
 * 空间复杂度分析：
 * - 使用了一个大小为 `k` 的最大堆，因此空间复杂度为 O(K)。
 */

public class LeetCode_1492_TheKthFactorOfN {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 使用最大堆，以保持最大的元素始终在堆的顶部
        Queue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        // 将元素推入堆，通过限制堆的大小为 k
        public void heappushK(int x, int k) {
            heap.add(x);
            if (heap.size() > k) {
                heap.remove();
            }
        }

        /**
         * 找到第 k 个因子
         *
         * @param n 给定的数字
         * @param k 第 k 个因子的索引
         * @return 第 k 个最大因子
         */
        public int kthFactor(int n, int k) {
            // 计算 n 的平方根，以确定循环的上限
            int sqrtN = (int) Math.sqrt(n);

            // 遍历从 1 到 sqrtN 的所有因子
            for (int x = 1; x < sqrtN + 1; ++x) {
                if (n % x == 0) {
                    // 将因子 x 推入堆中
                    heappushK(x, k);

                    // 如果 x 不等于 n/x，将 n/x 也推入堆中
                    if (x != n / x) {
                        heappushK(n / x, k);
                    }
                }
            }

            // 如果堆的大小等于 k，返回堆顶元素（第 k 个最大因子），否则返回 -1
            return k == heap.size() ? heap.poll() : -1;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试代码
        Solution solution = new LeetCode_1492_TheKthFactorOfN().new Solution();
        int n = 12;
        int k = 3;
        int result = solution.kthFactor(n, k);
        System.out.println("The " + k + "th factor of " + n + " is: " + result);
        // 输出：The 3th factor of 12 is: 3
    }
}

/**
You are given two positive integers n and k. A factor of an integer n is 
defined as an integer i where n % i == 0. 

 Consider a list of all factors of n sorted in ascending order, return the kᵗʰ 
factor in this list or return -1 if n has less than k factors. 

 
 Example 1: 

 
Input: n = 12, k = 3
Output: 3
Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3ʳᵈ factor is 3.
 

 Example 2: 

 
Input: n = 7, k = 2
Output: 7
Explanation: Factors list is [1, 7], the 2ⁿᵈ factor is 7.
 

 Example 3: 

 
Input: n = 4, k = 4
Output: -1
Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should 
return -1.
 

 
 Constraints: 

 
 1 <= k <= n <= 1000 
 

 
 Follow up: 

 Could you solve this problem in less than O(n) complexity? 

 Related Topics Math Number Theory 👍 1632 👎 286

*/
package leetcode.question.string_list;
/**
 *@Question:  204. Count Primes
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 65.55%
 *@Time  Complexity: O(N log log N)
 *@Space Complexity: O(N)
 */

/**
 * ### 题目描述
 *
 * **题目：** `204. Count Primes`
 *
 * **描述：** 给定一个整数 `n`，计算所有小于 `n` 的素数的数量。素数是指大于 1 的自然数，且只能被 1 和自身整除。
 *
 * ### 解题思路
 *
 * 1. **问题分析**：
 *    - 要计算小于 `n` 的素数的数量，可以通过检查每个数是否是素数来完成。直接检查每个数是否是素数的时间复杂度较高，因此需要更高效的算法。
 *
 * 2. **埃拉托斯特尼筛法（Sieve of Eratosthenes）**：
 *    - **基本思想**：埃拉托斯特尼筛法是一种高效的找出所有小于 `n` 的素数的算法。其核心思想是通过筛选去除非素数，从而得到素数。
 *    - **算法步骤**：
 *      - **初始化**：创建一个布尔数组 `numbers`，数组的每个索引对应一个整数，初始时所有值都设置为 `false`（表示这些数都是素数的候选者）。
 *      - **筛选**：
 *        - 从 2 开始遍历每个数 `p`。如果 `p` 在数组中标记为 `false`，则 `p` 是素数。
 *        - 然后将 `p` 的所有倍数标记为非素数，即将这些倍数的对应数组元素设置为 `true`。
 *        - 继续这个过程直到 `p` 的平方大于等于 `n`。
 *      - **计数**：
 *        - 遍历布尔数组 `numbers`，统计标记为 `false` 的元素个数，即为素数的数量。
 *
 * 3. **总结**：
 *    - **埃拉托斯特尼筛法** 通过一次遍历和标记操作，将问题的复杂度降低到比直接检查每个数的复杂度更低的水平。这种方法不仅高效，而且实现简单。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**：`O(N log log N)`。这是因为在埃拉托斯特尼筛法中，每个合数会被其对应的素数筛选多次，而每次筛选的复杂度是对数级别的，因此总的时间复杂度是 `O(N log log N)`。
 *
 * - **空间复杂度**：`O(N)`。需要一个大小为 `n` 的布尔数组来标记每个数是否是素数。这个数组占据了线性空间。
 */
public class LeetCode_204_CountPrimes{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countPrimes(int n) {
            // 如果 n 小于或等于 2，那么没有素数，直接返回 0
            if (n <= 2) {
                return 0;
            }

            // 创建一个布尔数组，用来标记每个数是否是素数
            boolean[] numbers = new boolean[n];
            // 遍历每一个数，从 2 开始
            for (int p = 2; p <= (int) Math.sqrt(n); ++p) {
                // 如果 numbers[p] 为 false，说明 p 是素数
                if (numbers[p] == false) {
                    // 将 p 的倍数标记为非素数（true）
                    for (int j = p * p; j < n; j += p) {
                        numbers[j] = true;
                    }
                }
            }

            int numberOfPrimes = 0;
            // 计算数组中标记为 false 的素数数量
            for (int i = 2; i < n; i++) {
                if (numbers[i] == false) {
                    ++numberOfPrimes;
                }
            }

            return numberOfPrimes;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_204_CountPrimes().new Solution();
        // 测试样例
        System.out.println(solution.countPrimes(10)); // 输出: 4，因为小于 10 的素数是 2, 3, 5, 7
        System.out.println(solution.countPrimes(20)); // 输出: 8，因为小于 20 的素数是 2, 3, 5, 7, 11, 13, 17, 19
    }
}

/**
Given an integer n, return the number of prime numbers that are strictly less 
than n. 

 
 Example 1: 

 
Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 

 Example 2: 

 
Input: n = 0
Output: 0
 

 Example 3: 

 
Input: n = 1
Output: 0
 

 
 Constraints: 

 
 0 <= n <= 5 * 10⁶ 
 

 Related Topics Array Math Enumeration Number Theory 👍 7969 👎 1455

*/
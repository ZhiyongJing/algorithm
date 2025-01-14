package leetcode.question.map_set;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *@Question:  1387. Sort Integers by The Power Value
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.49%
 *@Time Complexity: O((hi - lo) * log(hi))
 *@Space Complexity: O(hi - lo)
 */
/**
 * ==============================
 * 题目描述：LeetCode 1387 - Sort Integers by The Power Value
 * ==============================
 * 给定一个范围 `[lo, hi]` 内的所有整数，按照它们的 **幂值（Power Value）** 对这些整数进行排序。
 * 并返回排序后的第 `k` 小的数字。
 *
 * **幂值计算规则：**
 * - 对于任意正整数 `n`：
 *   - 如果 `n == 1`，幂值为 0。
 *   - 如果 `n` 是偶数，则下一个数字为 `n / 2`。
 *   - 如果 `n` 是奇数，则下一个数字为 `3 * n + 1`。
 *   - 重复上述步骤，直到数字变为 1，**步数**即为该数字的幂值。
 *
 * **要求：**
 * - 在给定范围内的整数中，根据幂值排序后，返回第 `k` 小的数字。
 *
 * **输入/输出示例：**
 * - 输入：`lo = 12`, `hi = 15`, `k = 2`
 * - 输出：`13`
 * - 解释：
 *   - 幂值为：`12 -> 9`, `13 -> 9`, `14 -> 17`, `15 -> 17`
 *   - 排序后：[12, 13, 14, 15]，第 2 小的数字是 `13`。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * **核心思想：**
 * - **计算每个数字的幂值**，并将数字和幂值作为一对数据存储起来。
 * - **使用最大堆（PriorityQueue）** 来动态维护前 `k` 个幂值最小的数字。
 * - 为了避免重复计算幂值，使用**缓存（HashMap）**来存储已经计算过的数字及其幂值。
 *
 * **解题步骤：**
 * ------------------------------
 * **步骤1：遍历给定范围 `[lo, hi]` 的每个数字**
 * - 对每个数字调用 `getPower()` 方法，计算它的幂值。
 * - 将数字和幂值封装成一个 `Item` 对象，添加到最大堆中。
 *
 * **步骤2：维护最大堆的大小为 `k`**
 * - 如果堆的大小超过 `k`，就移除堆中幂值最大的数字，确保堆中只保留前 `k` 个幂值最小的数字。
 * - 最终堆顶的数字就是我们需要的第 `k` 小的数字。
 *
 * ------------------------------
 * **步骤3：计算幂值的递归函数 `getPower()`**
 * - 如果数字 `n == 1`，返回 0（幂值为 0）。
 * - 如果数字已经存在于缓存中，直接返回缓存值，避免重复计算。
 * - 根据奇偶性递归计算幂值：
 *   - 如果 `n` 是偶数，调用 `getPower(n / 2)`。
 *   - 如果 `n` 是奇数，调用 `getPower(3 * n + 1)`。
 * - 将计算结果存入缓存。
 *
 * ------------------------------
 * **举例解释：**
 *
 * **示例1：**
 * - 输入：`lo = 12`, `hi = 15`, `k = 2`
 * - 计算每个数字的幂值：
 *   ```
 *   12 -> 9
 *   13 -> 9
 *   14 -> 17
 *   15 -> 17
 *   ```
 * - 使用最大堆维护前 2 个幂值最小的数字：
 *   ```
 *   堆中：[(13, 9), (12, 9)]
 *   ```
 * - 返回堆顶元素：`13`。
 *
 * **示例2：**
 * - 输入：`lo = 7`, `hi = 11`, `k = 4`
 * - 计算每个数字的幂值：
 *   ```
 *   7  -> 16
 *   8  -> 3
 *   9  -> 19
 *   10 -> 6
 *   11 -> 14
 *   ```
 * - 使用最大堆维护前 4 个幂值最小的数字：
 *   ```
 *   堆中：[(11, 14), (10, 6), (8, 3), (7, 16)]
 *   ```
 * - 返回堆顶元素：`7`。
 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：O((hi - lo) * log k)**
 * - 遍历范围内的所有数字，每个数字计算幂值的时间复杂度为 O(log n)。
 * - 使用最大堆的插入操作时间复杂度为 O(log k)。
 * - 因此总时间复杂度为 O((hi - lo) * log k)。
 *
 * **空间复杂度：O(hi - lo)**
 * - 使用了一个缓存 `cache` 存储范围内的每个数字的幂值。
 * - 最大堆的大小最多为 `k`。
 * - 因此空间复杂度为 O(hi - lo)。
 */


public class LeetCode_1387_SortIntegersByThePowerValue {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 定义一个缓存（哈希表），用于存储计算过的数字及其对应的幂值，减少重复计算
        HashMap<Integer, Integer> cache;

        /**
         * 获取第 k 小的数字，根据它们的幂值进行排序
         *
         * @param lo 起始范围
         * @param hi 结束范围
         * @param k  第 k 小的数字
         * @return 返回排序后第 k 小的数字
         */
        public int getKth(int lo, int hi, int k) {
            cache = new HashMap<>(); // 初始化缓存

            // 使用一个最大堆来存储前 k 个最小的数字，根据幂值排序
            // 如果两个数字的幂值相等，则按数字大小排序
            PriorityQueue<Item> maxHeap = new PriorityQueue<>(
                    (a, b) -> (a.power == b.power) ? (b.num - a.num) : (b.power - a.power)
            );

            // 遍历给定范围内的每个数字
            for (int num = lo; num <= hi; num++) {
                // 计算当前数字的幂值
                // 并将数字和幂值存入最大堆中
                maxHeap.add(new Item(num, getPower(num)));

                // 如果堆的大小超过 k，则移除堆中幂值最大的数字
                if (maxHeap.size() > k) {
                    maxHeap.remove();
                }
            }

            // 最后堆中剩下的第一个元素就是第 k 小的数字，返回其值
            return maxHeap.remove().num;
        }

        /**
         * 计算给定数字的幂值（Power Value）
         *
         * 幂值的计算规则：
         * - 如果 n == 1，则返回 0（因为到 1 的步数为 0）
         * - 如果 n 是偶数，则下一个数为 n / 2
         * - 如果 n 是奇数，则下一个数为 3 * n + 1
         *
         * @param n 要计算幂值的数字
         * @return 返回数字的幂值
         */
        private int getPower(int n) {
            // 如果 n 是 1，直接返回 0
            if (n == 1) return 0;

            // 如果缓存中已有 n 的幂值，直接返回缓存值，避免重复计算
            if (cache.containsKey(n)) return cache.get(n);

            // 计算当前数字的幂值，并根据 n 的奇偶性选择不同的计算方法
            int power = 1 + ((n % 2 == 0) ? getPower(n / 2) : getPower((3 * n) + 1));

            // 将计算结果存入缓存
            cache.put(n, power);

            // 返回幂值
            return power;
        }
    }

    // 定义一个类，用于存储数字及其对应的幂值
    class Item {
        int num;    // 数字
        int power;  // 该数字的幂值

        // 构造方法
        public Item(int num, int power) {
            this.num = num;
            this.power = power;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1387_SortIntegersByThePowerValue().new Solution();

        // 测试用例 1
        int lo1 = 12, hi1 = 15, k1 = 2;
        System.out.println("The " + k1 + "th number in range [" + lo1 + ", " + hi1 + "] is: " + solution.getKth(lo1, hi1, k1));
        // 预期输出：13

        // 测试用例 2
        int lo2 = 7, hi2 = 11, k2 = 4;
        System.out.println("The " + k2 + "th number in range [" + lo2 + ", " + hi2 + "] is: " + solution.getKth(lo2, hi2, k2));
        // 预期输出：7
    }
}

/**
The power of an integer x is defined as the number of steps needed to transform 
x into 1 using the following steps: 

 
 if x is even then x = x / 2 
 if x is odd then x = 3 * x + 1 
 

 For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 -->
 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1). 

 Given three integers lo, hi and k. The task is to sort all integers in the 
interval [lo, hi] by the power value in ascending order, if two or more integers 
have the same power value sort them by ascending order. 

 Return the kᵗʰ integer in the range [lo, hi] sorted by the power value. 

 Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will 
transform into 1 using these steps and that the power of x is will fit in a 32-bit 
signed integer. 

 
 Example 1: 

 
Input: lo = 12, hi = 15, k = 2
Output: 13
Explanation: The power of 12 is 9 (12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 
4 --> 2 --> 1)
The power of 13 is 9
The power of 14 is 17
The power of 15 is 17
The interval sorted by the power value [12,13,14,15]. For k = 2 answer is the 
second element which is 13.
Notice that 12 and 13 have the same power value and we sorted them in ascending 
order. Same for 14 and 15.
 

 Example 2: 

 
Input: lo = 7, hi = 11, k = 4
Output: 7
Explanation: The power array corresponding to the interval [7, 8, 9, 10, 11] is 
[16, 3, 19, 6, 14].
The interval sorted by power is [8, 10, 11, 7, 9].
The fourth number in the sorted array is 7.
 

 
 Constraints: 

 
 1 <= lo <= hi <= 1000 
 1 <= k <= hi - lo + 1 
 

 Related Topics Dynamic Programming Memoization Sorting 👍 1482 👎 118

*/
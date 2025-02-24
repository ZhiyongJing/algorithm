package leetcode.question.heap;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *@Question:  2342. Max Sum of a Pair With Equal Sum of Digits
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 58.5%
 *@Time  Complexity: O(NlogN) for solution1, O(N log M) for solution2, N is size of sum, and M is maximum number in nums
 *@Space Complexity: O(logN) for solution1, O(logM) for solution2
 */
/**
 * 题目描述：
 * 2342. Max Sum of a Pair With Equal Sum of Digits
 *
 * 给定一个整数数组 `nums`，我们需要找到两个不同的数字 `nums[i]` 和 `nums[j]`（i ≠ j），
 * 使得它们的 **数位和（digit sum）** 相同，并且它们的和 `nums[i] + nums[j]` 最大。
 * 如果不存在这样的两个数字，返回 `-1`。
 *
 * 示例 1：
 * 输入: nums = [18, 43, 36, 13, 7]
 * 输出: 54
 * 解释:
 * - 数字 18 和 36 的数位和相同 (1+8=9, 3+6=9)，它们的和是 18 + 36 = 54，这是最大的和。
 *
 * 示例 2：
 * 输入: nums = [10, 12, 19, 14]
 * 输出: -1
 * 解释:
 * - 没有两个不同的数字具有相同的数位和，因此返回 -1。
 *
 * 解题思路：
 *
 * **方法1：排序 + 遍历**
 * 1. **计算每个数的数位和**
 *    - 遍历 `nums` 数组，计算 `digitSum = num % 10 + num / 10` 直到 `num` 为 0。
 * 2. **存储 `Pair(digitSum, num)` 并按 `digitSum` 排序**
 *    - 使用 `Arrays.sort()`，首先按 `digitSum` 排序，如果相同，则按 `num` 排序。
 * 3. **遍历排序后的数组，找最大配对和**
 *    - 只需遍历一次，如果相邻两个 `digitSum` 相等，则计算 `pairSum = num[i] + num[i-1]`，更新 `maxPairSum`。
 *
 * **方法2：使用最小堆**
 * 1. **用数组存储最小堆**
 *    - 维护 `PriorityQueue<Integer>[] digitSumGroups = new PriorityQueue[82]`，每个索引存储相同 `digitSum` 的数值。
 * 2. **遍历 `nums` 并存入对应 `digitSum` 组**
 *    - 如果 `digitSumGroups[digitSum]` 超过 2 个值，则移除最小的，确保堆中始终保持 **最大的 2 个数**。
 * 3. **遍历 `digitSumGroups` 计算最大数对和**
 *    - 对每个 `digitSum`，取堆中的两个最大数 `first, second`，计算 `pairSum = first + second`，更新 `maxPairSum`。
 *
 * 时间复杂度分析：
 * - **方法1（排序 + 遍历）**
 *   - 计算数位和 `O(N log M)`
 *   - 排序 `O(N log N)`
 *   - 遍历 `O(N)`
 *   - **总时间复杂度 `O(N log N)`**
 * - **方法2（最小堆）**
 *   - 计算数位和 `O(N log M)`
 *   - 遍历 `O(N log 2) ≈ O(N)`
 *   - 遍历 `digitSumGroups` 计算 `O(82) ≈ O(1)`
 *   - **总时间复杂度 `O(N log M)`**
 *
 * 空间复杂度分析：
 * - `Pair[]` 需要存储 `O(N)` 的额外空间。
 * - `PriorityQueue[]` 需要 `O(N)` 存储数值，但最多 82 个键，近似 `O(1)`。
 * - **总空间复杂度 `O(N)`**
 */


public class LeetCode_2342_MaxSumOfAPairWithEqualSumOfDigits{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //Solution1
        public int maximumSum(int[] nums) {
            // 创建一个 Pair 数组，每个元素包含 (数位和, 原始数值)
            Pair<Integer, Integer>[] digitSumPairs = new Pair[nums.length];

            // 遍历 nums 数组，计算每个数的数位和，并存入 Pair 数组
            for (int i = 0; i < nums.length; i++) {
                int digitSum = calculateDigitSum(nums[i]);
                digitSumPairs[i] = new Pair<>(digitSum, nums[i]);
            }

            // 对 Pair 数组进行排序，优先按照数位和排序，若相同则按数值大小排序
            Arrays.sort(
                    digitSumPairs,
                    Comparator.comparing(Pair<Integer, Integer>::getKey)
                            .thenComparing(Pair<Integer, Integer>::getValue)
            );

            int maxPairSum = -1; // 记录最大数对和

            // 遍历排序后的数组，寻找相邻数位和相同的最大数对和
            for (int index = 1; index < digitSumPairs.length; index++) {
                int currentDigitSum = digitSumPairs[index].getKey();
                int previousDigitSum = digitSumPairs[index - 1].getKey();

                // 如果相邻两个数的数位和相同
                if (currentDigitSum == previousDigitSum) {
                    int currentSum =
                            digitSumPairs[index].getValue() +
                                    digitSumPairs[index - 1].getValue();
                    // 更新最大配对和
                    maxPairSum = Math.max(maxPairSum, currentSum);
                }
            }

            return maxPairSum;
        }

        // 计算一个数字的各位数字和
        private int calculateDigitSum(int num) {
            int digitSum = 0;
            while (num > 0) {
                // 取出最低位数字并加到 digitSum
                digitSum += num % 10;
                // 去掉最低位数字
                num /= 10;
            }
            return digitSum;
        }

        //solution2:
        public int maximumSum2(int[] nums) {
            // 创建一个数组，其中每个索引代表某个数位和，存储一个最小堆（最小堆最多存储两个元素）
            PriorityQueue<Integer>[] digitSumGroups = new PriorityQueue[82];
            for (int i = 0; i < 82; i++) {
                digitSumGroups[i] = new PriorityQueue<>();
            }

            int maxPairSum = -1; // 记录最大配对和的变量

            // 遍历数组，按照数位和将数字分组
            for (int number : nums) {
                // 计算当前数字的各位数字和
                int digitSum = calculateDigitSum(number);
                // 将当前数字加入对应数位和的最小堆
                digitSumGroups[digitSum].add(number);

                // 只保留最大两个数，维护一个大小为 2 的最小堆
                if (digitSumGroups[digitSum].size() > 2) {
                    digitSumGroups[digitSum].poll(); // 弹出最小的元素，保留两个最大元素
                }
            }

            // 遍历所有的数位和分组，计算最大配对和
            for (PriorityQueue<Integer> minHeap : digitSumGroups) {
                if (minHeap.size() == 2) {
                    // 弹出两个最大的元素
                    int first = minHeap.poll();
                    int second = minHeap.poll();
                    // 更新最大配对和
                    maxPairSum = Math.max(maxPairSum, first + second);
                }
            }

            return maxPairSum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_2342_MaxSumOfAPairWithEqualSumOfDigits().new Solution();

        // 测试样例
        int[] test1 = {18, 43, 36, 13, 7};
        System.out.println(solution.maximumSum(test1)); // 预期输出: 54 (18 + 36)

        int[] test2 = {10, 12, 19, 14};
        System.out.println(solution.maximumSum(test2)); // 预期输出: -1 (没有满足条件的数对)

        int[] test3 = {51, 71, 17, 42};
        System.out.println(solution.maximumSum(test3)); // 预期输出: 93 (51 + 42)

        int[] test4 = {8, 20, 41, 32, 23, 14};
        System.out.println(solution.maximumSum(test4)); // 预期输出: 63 (41 + 32)
    }
}


/**
You are given a 0-indexed array nums consisting of positive integers. You can 
choose two indices i and j, such that i != j, and the sum of digits of the number 
nums[i] is equal to that of nums[j]. 

 Return the maximum value of nums[i] + nums[j] that you can obtain over all 
possible indices i and j that satisfy the conditions. If no such pair of indices 
exists, return -1. 

 
 Example 1: 

 
Input: nums = [18,43,36,13,7]
Output: 54
Explanation: The pairs (i, j) that satisfy the conditions are:
- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36
 = 54.
- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 
= 50.
So the maximum sum that we can obtain is 54.
 

 Example 2: 

 
Input: nums = [10,12,19,14]
Output: -1
Explanation: There are no two numbers that satisfy the conditions, so we return 
-1.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 1 <= nums[i] <= 10⁹ 
 

 Related Topics Array Hash Table Sorting Heap (Priority Queue) 👍 1369 👎 44

*/
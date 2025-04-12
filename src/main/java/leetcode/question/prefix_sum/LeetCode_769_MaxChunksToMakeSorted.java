package leetcode.question.prefix_sum;

import java.util.Stack;

/**
 *@Question:  769. Max Chunks To Make Sorted
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 74.9%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1), O(N) for solution2
 */
/**
 * ===============================================
 * LeetCode 769. Max Chunks To Make Sorted
 * ===============================================
 *
 * 【一、题目描述】
 * 给定一个长度为 n 的整数数组 arr，数组包含 0 到 n-1 的所有整数且无重复。
 * 你可以将这个数组拆分为若干个“块”（chunk），每个块内的元素可以任意排序。
 * 最终将每个块排序后再拼接在一起，要求结果必须是升序排列的完整数组。
 *
 * 请返回你可以将数组分成的最多“块”的数量。
 *
 * 示例 1：
 * 输入：arr = [1, 0, 2, 3, 4]
 * 输出：4
 * 解释：可以分为 [1, 0], [2], [3], [4]，每块排序后合并为 [0,1,2,3,4]
 *
 * 示例 2：
 * 输入：arr = [4, 3, 2, 1, 0]
 * 输出：1
 * 解释：只能整体作为一个块
 *
 *
 * 【二、解题思路详解】
 *
 * 题目本质是找出“可以独立排序且不影响后续顺序”的分段边界。
 *
 * --------------------------------------------------
 * 解法一：前缀和对比（prefixSum）
 * --------------------------------------------------
 * 思路：
 * - 理论上，原数组和排序后数组中每一段前缀和应该一致；
 * - 如果某个位置 i 前的原数组前缀和 == 排序数组前缀和，说明 [0...i] 这段元素是一个合法块；
 * - 每次满足前缀和相等，块数 +1。
 *
 * 举例分析（arr = [1, 0, 2, 3, 4]）：
 * - i = 0: 原前缀和 = 1，排序前缀和 = 0 → 不相等 → 不成块
 * - i = 1: 原前缀和 = 1 → 排序前缀和 = 1 → 成块 ✔️
 * - i = 2: 原前缀和 = 3 → 排序前缀和 = 3 → 成块 ✔️
 * - i = 3: 原前缀和 = 6 → 排序前缀和 = 6 → 成块 ✔️
 * - i = 4: 原前缀和 = 10 → 排序前缀和 = 10 → 成块 ✔️
 * 最终结果为 4 块。
 *
 * --------------------------------------------------
 * 解法二：单调栈（monotonic stack）
 * --------------------------------------------------
 * 思路：
 * - 维持一个单调递减栈，栈中保存的是“当前所有块的最大值”；
 * - 当前值大于栈顶，说明可以成为新的块；
 * - 当前值小于栈顶，说明前面块与当前值有冲突 → 必须合并 → 弹出栈中所有大于当前值的块；
 * - 合并后的最大值重新压入栈；
 * - 最终栈的大小就是块的数量。
 *
 * 举例分析（arr = [2, 0, 1, 3, 4]）：
 * - i = 0: 栈空 → push(2)
 * - i = 1: 1 < 2 → 合并 → pop(2), push(2)
 * - i = 2: 1 < 2 → 合并 → push(2)
 * - i = 3: 3 > 2 → 新块 → push(3)
 * - i = 4: 4 > 3 → 新块 → push(4)
 * 栈大小为 3 → 即最多可分 3 块。
 *
 *
 * 【三、时间与空间复杂度】
 *
 * 解法一：前缀和
 * - 时间复杂度：O(N)，遍历一次数组，计算前缀和；
 * - 空间复杂度：O(1)，只用了常数级变量；
 *
 * 解法二：单调栈
 * - 时间复杂度：O(N)，每个元素最多入栈出栈一次；
 * - 空间复杂度：O(N)，最坏情况下每个元素都保留在栈中；
 */


public class LeetCode_769_MaxChunksToMakeSorted{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法1：基于前缀和判断能否分块
        public int maxChunksToSorted(int[] arr) {
            int n = arr.length;

            // 记录块数量、原数组前缀和、排序后前缀和
            int chunks = 0, prefixSum = 0, sortedPrefixSum = 0;

            // 遍历整个数组
            for (int i = 0; i < n; i++) {
                // 累加当前原数组的前缀和
                prefixSum += arr[i];

                // 累加当前排序数组的前缀和（排序后为 0 到 n-1）
                sortedPrefixSum += i;

                // 如果两者相等，说明当前前缀是一块可独立排序的块
                if (prefixSum == sortedPrefixSum) {
                    chunks++;
                }

                System.out.println(prefixSum + " = " + sortedPrefixSum);
            }

            // 返回可分成的最大块数
            return chunks;
        }

        // 解法2：使用单调栈维护每一块最大值
        public int maxChunksToSorted2(int[] arr) {
            int n = arr.length;

            // 单调栈用于存储每一块的最大值
            Stack<Integer> monotonicStack = new Stack<>();

            for (int i = 0; i < n; i++) {
                // 情况1：当前元素比栈顶大，可以直接入栈，作为新块的最大值
                if (monotonicStack.isEmpty() || arr[i] > monotonicStack.peek()) {
                    monotonicStack.push(arr[i]);
                } else {
                    // 情况2：当前元素比栈顶小，说明要合并块
                    int maxElement = monotonicStack.peek(); // 保留当前块最大值

                    // 弹出所有比当前元素大的块（合并）
                    while (!monotonicStack.isEmpty() && arr[i] < monotonicStack.peek()) {
                        monotonicStack.pop();
                    }

                    // 把合并后块的最大值压回栈
                    monotonicStack.push(maxElement);
                }
            }

            // 栈的大小就是可分块数
            return monotonicStack.size();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_769_MaxChunksToMakeSorted().new Solution();

        // 测试用例 1
        int[] arr1 = {1, 0, 2, 3, 4};
        System.out.println("测试用例1：期望输出 4，实际输出：" + solution.maxChunksToSorted(arr1));
        System.out.println("测试用例1（解法2）：期望输出 4，实际输出：" + solution.maxChunksToSorted2(arr1));

        // 测试用例 2
        int[] arr2 = {4, 3, 2, 1, 0};
        System.out.println("测试用例2：期望输出 1，实际输出：" + solution.maxChunksToSorted(arr2));
        System.out.println("测试用例2（解法2）：期望输出 1，实际输出：" + solution.maxChunksToSorted2(arr2));

        // 测试用例 3
        int[] arr3 = {2, 0, 1, 3, 4};
        System.out.println("测试用例3：期望输出 2，实际输出：" + solution.maxChunksToSorted(arr3));
        System.out.println("测试用例3（解法2）：期望输出 2，实际输出：" + solution.maxChunksToSorted2(arr3));
    }
}

/**
You are given an integer array arr of length n that represents a permutation of 
the integers in the range [0, n - 1]. 

 We split arr into some number of chunks (i.e., partitions), and individually 
sort each chunk. After concatenating them, the result should equal the sorted 
array. 

 Return the largest number of chunks we can make to sort the array. 

 
 Example 1: 

 
Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], 
which isn't sorted.
 

 Example 2: 

 
Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks 
possible.
 

 
 Constraints: 

 
 n == arr.length 
 1 <= n <= 10 
 0 <= arr[i] < n 
 All the elements of arr are unique. 
 

 Related Topics Array Stack Greedy Sorting Monotonic Stack 👍 3553 👎 350

*/
package leetcode.question.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 *@Question:  435. Non-overlapping Intervals
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 49.58%
 *@Time  Complexity: O(N log N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * 题目要求在给定的区间中移除最少数量的区间，使得剩下的区间互不重叠。我们可以使用贪心算法来解决这个问题。以下是详细的解题思路：
 *
 * #### 1. **排序**
 *
 * - **目的**：为了尽量保留更多的区间，我们需要优先选择结束时间早的区间。这样可以减少与后续区间的重叠，尽可能多地保留后面的区间。
 * - **实现**：将所有区间按照它们的结束时间升序排序。这样，排序后的区间集中的每个区间都能与之前的非重叠区间进行比较，从而决定是否需要移除当前区间。
 *
 * #### 2. **遍历和选择**
 *
 * - **初始化**：
 *   - `k`：记录上一个被选择的区间的结束时间。初始值为 `Integer.MIN_VALUE`，表示还没有选择任何区间。
 *   - `ans`：记录需要移除的区间数量。初始值为 `0`。
 * - **遍历过程**：
 *   - 遍历排序后的所有区间，对于每个区间，检查它的开始时间是否大于或等于 `k`：
 *     - 如果是：表示当前区间不与上一个选择的区间重叠，更新 `k` 为当前区间的结束时间，并不需要移除当前区间。
 *     - 如果否：表示当前区间与上一个选择的区间重叠，需要移除当前区间，计数器 `ans` 增加。
 *
 * #### 3. **返回结果**
 *
 * - **结果**：遍历结束后，`ans` 中的值即为需要移除的区间数量，使得剩下的区间互不重叠。返回 `ans` 作为结果。
 *
 * ### 时间和空间复杂度分析
 *
 * - **时间复杂度**：
 *   - **排序**：对所有区间进行排序的时间复杂度是 `O(N log N)`，其中 `N` 是区间的数量。
 *   - **遍历**：遍历所有区间的时间复杂度是 `O(N)`。
 *   - **总体**：因为排序的时间复杂度主导，总体时间复杂度为 `O(N log N)`。
 *
 * - **空间复杂度**：
 *   - **排序**：排序操作的空间复杂度为 `O(1)`（如果使用原地排序）。
 *   - **其他**：除了排序外，我们只使用了常数空间来保存变量 `k` 和 `ans`。
 *   - **总体**：空间复杂度为 `O(1)`。
 *
 * ### 总结
 *
 * 通过贪心算法，我们能够高效地解决区间不重叠的问题。关键在于首先将区间按结束时间排序，这样可以逐个处理区间并选择性地移除重叠区间。排序步骤是算法的主要开销，因此时间复杂度为 `O(N log N)`。空间复杂度非常低，为 `O(1)`，因为我们只使用了常数空间来存储状态。
 */

public class LeetCode_435_NonOverlappingIntervals{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            // 将所有区间按照结束时间升序排序
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
            int ans = 0; // 记录需要移除的区间数量
            int k = Integer.MIN_VALUE; // 记录上一个选择的区间的结束时间

            // 遍历所有区间
            for (int i = 0; i < intervals.length; i++) {
                int x = intervals[i][0]; // 当前区间的开始时间
                int y = intervals[i][1]; // 当前区间的结束时间

                // 如果当前区间的开始时间不小于上一个选择的区间的结束时间
                if (x >= k) {
                    // 更新上一个选择的区间的结束时间为当前区间的结束时间
                    k = y;
                } else {
                    // 当前区间与上一个选择的区间重叠，需要移除当前区间
                    ans++;
                }
            }

            return ans; // 返回需要移除的区间数量
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_435_NonOverlappingIntervals().new Solution();

        // 测试样例1
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(solution.eraseOverlapIntervals(intervals1)); // 输出：1

        // 测试样例2
        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(solution.eraseOverlapIntervals(intervals2)); // 输出：2

        // 测试样例3
        int[][] intervals3 = {{1, 2}, {2, 3}};
        System.out.println(solution.eraseOverlapIntervals(intervals3)); // 输出：0
    }
}

/**
Given an array of intervals intervals where intervals[i] = [starti, endi], 
return the minimum number of intervals you need to remove to make the rest of the 
intervals non-overlapping. 

 
 Example 1: 

 
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-
overlapping.
 

 Example 2: 

 
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-
overlapping.
 

 Example 3: 

 
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're 
already non-overlapping.
 

 
 Constraints: 

 
 1 <= intervals.length <= 10⁵ 
 intervals[i].length == 2 
 -5 * 10⁴ <= starti < endi <= 5 * 10⁴ 
 

 Related Topics Array Dynamic Programming Greedy Sorting 👍 8131 👎 219

*/
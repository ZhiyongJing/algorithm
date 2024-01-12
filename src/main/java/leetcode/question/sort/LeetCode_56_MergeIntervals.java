package leetcode.question.sort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *@Question:  56. Merge Intervals
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 94.46%
 *@Time  Complexity: O(NlogN)
 *@Space Complexity: O(N)
 */

/**
 * 解题思路：
 *
 * 1. **排序：** 首先，对输入的二维数组 `intervals` 进行排序，按照区间的起始位置从小到大排序。这是为了方便后续合并操作。
 *
 * 2. **合并：** 遍历排序后的区间数组，用一个链表 `merged` 来存储合并后的区间。对于每一个区间：
 *    - 如果 `merged` 列表为空，或者当前区间与 `merged` 列表中最后一个区间不重叠，直接将当前区间添加到 `merged` 列表的末尾。
 *    - 如果有重叠，更新 `merged` 列表中最后一个区间的结束位置，取当前区间的结束位置和 `merged` 列表中最后一个区间的结束位置的最大值。
 *
 * 3. **输出：** 最后，将链表 `merged` 转换为数组并返回。
 *
 * 时间复杂度：主要消耗在排序操作上，为 O(NlogN)，其中 N 是区间的个数。遍历排序后的区间数组是 O(N)。
 *
 * 空间复杂度：使用了一个链表来存储合并后的区间，最坏情况下会占用 O(N) 的额外空间。
 *
 * 代码中已经包含了详细的注释，可以更好地理解每一步的操作。如果有其他疑问，欢迎提出。
 */

public class LeetCode_56_MergeIntervals{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            // 按照区间的起始位置进行排序
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

            // 使用链表存储合并后的区间
            LinkedList<int[]> merged = new LinkedList<>();

            // 遍历排序后的区间数组
            for (int[] interval : intervals) {
                // 如果合并后的区间列表为空，或者当前区间与上一个区间不重叠，直接添加到列表末尾
                if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                    merged.add(interval);
                }
                // 如果有重叠，更新合并后的区间的结束位置
                else {
                    merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
                }
            }

            // 将链表转换为数组并返回
            return merged.toArray(new int[merged.size()][]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_56_MergeIntervals().new Solution();

        // 测试代码
        int[][] test1 = {{1,3},{2,6},{8,10},{15,18}};
        int[][] result1 = solution.merge(test1);
        System.out.println(Arrays.deepToString(result1)); // 预期输出: [[1,6],[8,10],[15,18]]

        int[][] test2 = {{1,4},{4,5}};
        int[][] result2 = solution.merge(test2);
        System.out.println(Arrays.deepToString(result2)); // 预期输出: [[1,5]]
    }
}
/**
 Given an array of intervals where intervals[i] = [starti, endi], merge all
 overlapping intervals, and return an array of the non-overlapping intervals that
 cover all the intervals in the input.


 Example 1:


 Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].


 Example 2:


 Input: intervals = [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.



 Constraints:


 1 <= intervals.length <= 10⁴
 intervals[i].length == 2
 0 <= starti <= endi <= 10⁴


 Related Topics Array Sorting 👍 21344 👎 730
*/

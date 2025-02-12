// package 语句，声明当前类所在的包
package leetcode.question.binary_search;
// 导入需要使用的包

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  57. Insert Interval
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 60.209999999999994%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/*
 * 一、题目描述
 *    给定一个区间数组 intervals，每个元素是 [start, end] 表示某个区间；
 *    还给定一个新区间 newInterval，需要将它插入到 intervals 中并保持区间有序，
 *    并在插入完成后合并所有重叠区间，返回合并后的结果。
 *    例如：intervals = [[1,3],[6,9]]，newInterval = [2,5]，
 *         输出应为 [[1,5],[6,9]]。
 *
 * 二、解题思路（超级详细说明）
 *    1. 利用二分查找找到合适的插入位置：
 *       - 对 newInterval 的起始值进行二分搜索，在 intervals 中找到使其保持有序的位置。
 *       - 假设二分搜索完得到插入位置 left，
 *         将 [0, left) 的区间放入 result 列表，随后加入 newInterval，
 *         再将 [left, n) 余下的区间依次放入 result。
 *
 *       举例解释：
 *         intervals = [[1,3],[6,9]]，newInterval = [2,5]。
 *         - target = 2，在 intervals 中二分查找插入位置。因为 [1,3] 的起始为 1 < 2，而 [6,9] 的起始为 6 > 2，
 *           所以插入下标在 1（即在 [1,3] 与 [6,9] 之间）。
 *         - 将 [1,3] 添加到 result，再添加 [2,5]，再将 [6,9] 加入 result。
 *
 *    2. 合并重叠区间：
 *       - 对 result 列表进行一次线性遍历，若当前区间与上一个合并后区间无重叠，则直接放入 merged。
 *         否则合并它们，更新合并后区间的结束值为二者的最大值。
 *
 *       举例解释：
 *         经过插入操作后，result = [[1,3],[2,5],[6,9]]。
 *         - 比较 [1,3] 与 [2,5]，发现它们重叠，所以合并为 [1,5]。
 *         - 再比较 [1,5] 与 [6,9]，无重叠，直接加入，得到 [[1,5],[6,9]]。
 *
 * 三、时间和空间复杂度
 *    1. 时间复杂度：
 *       - 二分查找的过程为 O(log N)，但随后的插入操作仍需 O(N) 来移动元素，
 *         以及合并过程也需要 O(N)。整体时间复杂度约为 O(N)。
 *
 *    2. 空间复杂度：
 *       - 需要额外的结果列表来存储插入和合并之后的区间，最坏情况下存储 O(N) 个区间，
 *         因此空间复杂度为 O(N)。
 */


// 定义公共类 LeetCode_57_InsertInterval
public class LeetCode_57_InsertInterval{

    // leetcode 提交区域开始（不可修改）
//leetcode submit region begin(Prohibit modification and deletion)
// 定义内部类 Solution
    class Solution {
        // 定义方法 insert，接受 intervals(二维数组) 和 newInterval(一维数组)
        public int[][] insert(int[][] intervals, int[] newInterval) {
            // 如果原 intervals 为空，则直接返回仅包含 newInterval 的二维数组
            // If the intervals vector is empty, return a vector containing the newInterval
            if (intervals.length == 0) {
                return new int[][] { newInterval };
            }

            // 记录 intervals 的长度
            int n = intervals.length;
            // 记录新插入区间的起始位置
            int target = newInterval[0];
            // 二分查找的左右指针
            int left = 0, right = n - 1;

            // 二分查找，确定将 newInterval 插入的合适位置
            // Binary search to find the position to insert newInterval
            while (left <= right) {
                // 计算中间位置
                int mid = (left + right) / 2;
                // 如果 intervals[mid] 的起始位置比 target 小，则向右搜索
                if (intervals[mid][0] < target) {
                    left = mid + 1;
                } else {
                    // 否则向左搜索
                    right = mid - 1;
                }
            }

            // 在找到的插入位置插入 newInterval
            // Insert newInterval at the found position
            List<int[]> result = new ArrayList<>();
            // 先把原 intervals 中的 [0, left) 区间加入到 result
            for (int i = 0; i < left; i++) {
                result.add(intervals[i]);
            }
            // 然后插入 newInterval
            result.add(newInterval);
            // 再把剩下的区间 [left, n) 加入 result
            for (int i = left; i < n; i++) {
                result.add(intervals[i]);
            }

            // 开始合并区间
            // Merge overlapping intervals
            List<int[]> merged = new ArrayList<>();
            for (int[] interval : result) {
                // 如果 merged 为空或者当前区间与 merged 最后一个区间无重叠，则直接加进去
                // If res is empty or there is no overlap, add the interval to the result
                if (
                        merged.isEmpty() ||
                                merged.get(merged.size() - 1)[1] < interval[0]
                ) {
                    merged.add(interval);
                    // 如果有重叠，则合并区间，更新 merged 中最后一个区间的结束值
                    // If there is an overlap, merge the intervals by updating the end of the last interval in res
                } else {
                    merged.get(merged.size() - 1)[1] = Math.max(
                            merged.get(merged.size() - 1)[1],
                            interval[1]
                    );
                }
            }

            // 将合并好的区间转换为二维数组并返回
            return merged.toArray(new int[0][]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    // main 方法，用于测试代码
    public static void main(String[] args) {
        // 创建 Solution 实例
        Solution solution = new LeetCode_57_InsertInterval().new Solution();
        // TO TEST: 在此处调用 solution.insert 进行测试

        // 测试用例 1
        int[][] intervals1 = {{1,3},{6,9}};
        int[] newInterval1 = {2,5};
        // 预期输出：[[1,5],[6,9]]
        int[][] result1 = solution.insert(intervals1, newInterval1);
        System.out.print("Test Case 1: ");
        for (int[] interval : result1) {
            System.out.print("[" + interval[0] + "," + interval[1] + "] ");
        }
        System.out.println();

        // 测试用例 2
        int[][] intervals2 = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval2 = {4,8};
        // 预期输出：[[1,2],[3,10],[12,16]]
        int[][] result2 = solution.insert(intervals2, newInterval2);
        System.out.print("Test Case 2: ");
        for (int[] interval : result2) {
            System.out.print("[" + interval[0] + "," + interval[1] + "] ");
        }
        System.out.println();

        // 你可以根据需要添加更多测试用例
    }
}

/**
You are given an array of non-overlapping intervals intervals where intervals[i]
 = [starti, endi] represent the start and the end of the iᵗʰ interval and 
intervals is sorted in ascending order by starti. You are also given an interval 
newInterval = [start, end] that represents the start and end of another interval. 

 Insert newInterval into intervals such that intervals is still sorted in 
ascending order by starti and intervals still does not have any overlapping intervals 
(merge overlapping intervals if necessary). 

 Return intervals after the insertion. 

 Note that you don't need to modify intervals in-place. You can make a new 
array and return it. 

 
 Example 1: 

 
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
 

 Example 2: 

 
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 

 
 Constraints: 

 
 0 <= intervals.length <= 10⁴ 
 intervals[i].length == 2 
 0 <= starti <= endi <= 10⁵ 
 intervals is sorted by starti in ascending order. 
 newInterval.length == 2 
 0 <= start <= end <= 10⁵ 
 

 Related Topics Array 👍 10807 👎 859

*/
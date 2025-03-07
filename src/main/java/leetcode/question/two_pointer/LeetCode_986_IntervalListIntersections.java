package leetcode.question.two_pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Question:  986. Interval List Intersections
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 86.77%
 *@Time  Complexity: O(m + n), m 和 n 分别是 A 和 B 的长度
 *@Space Complexity: O(m + n)
 */
/**
 * 题目描述：
 *
 * 给定两个按 **升序排列** 的 **不重叠区间列表** `A` 和 `B`，请你找出它们的交集部分。
 * 每个区间 `A[i]` 和 `B[j]` 由两个整数 `[start, end]` 表示，分别表示区间的起始值和结束值。
 *
 * 你需要返回一个新的区间列表 `C`，其中 `C[k]` 表示 `A[i]` 和 `B[j]` 的所有交集部分。
 *
 * **注意事项**：
 * - 交集区间需要按 **起始值升序排列**。
 * - 如果两个区间没有交集，则不加入结果。
 * - 结果区间的左右边界必须在 `A` 和 `B` 的区间边界之内。
 *
 * **示例 1**：
 * 输入：
 * A = [[0,2],[5,10],[13,23],[24,25]]
 * B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：
 * [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * 解释：
 * - [0,2] 和 [1,5] 的交集是 [1,2]
 * - [5,10] 和 [1,5] 的交集是 [5,5]
 * - [5,10] 和 [8,12] 的交集是 [8,10]
 * - [13,23] 和 [15,24] 的交集是 [15,23]
 * - [24,25] 和 [15,24] 的交集是 [24,24]
 * - [24,25] 和 [25,26] 的交集是 [25,25]
/**
 * 解题思路：
 *
 * **使用双指针遍历两个区间列表**
 * 1. **定义两个指针** `i` 和 `j`：
 *    - `i` 指向 `A` 的当前区间
 *    - `j` 指向 `B` 的当前区间
 *
 * 2. **比较当前两个区间 `A[i]` 和 `B[j]` 是否有交集**：
 *    - 交集的 **起始点**：`lo = max(A[i][0], B[j][0])`
 *    - 交集的 **结束点**：`hi = min(A[i][1], B[j][1])`
 *    - 如果 `lo <= hi`，说明有交集，加入 `ans` 结果列表。
 *
 * 3. **移动指针**：
 *    - 移除 **结束点较小** 的区间：
 *      - 如果 `A[i][1] < B[j][1]`，说明 `A[i]` 先结束，移动 `i++`
 *      - 否则，说明 `B[j]` 先结束，移动 `j++`
 *
 * 4. **重复步骤 2-3，直到遍历完任一数组**。
 *
 * **示例分析**
 *
 * **示例 1**
 * 输入：
 * A = [[0,2],[5,10],[13,23],[24,25]]
 * B = [[1,5],[8,12],[15,24],[25,26]]
 *
 * **初始化**：
 * - `i = 0, j = 0`
 * - `ans = []`
 *
 * **迭代过程**：
 * - `A[0] = [0,2]` 和 `B[0] = [1,5]`：
 *   - `lo = max(0,1) = 1`
 *   - `hi = min(2,5) = 2`
 *   - 交集 `[1,2]` 加入 `ans`
 *   - `A[0]` 结束，`i++`
 *
 * - `A[1] = [5,10]` 和 `B[0] = [1,5]`：
 *   - `lo = max(5,1) = 5`
 *   - `hi = min(10,5) = 5`
 *   - 交集 `[5,5]` 加入 `ans`
 *   - `B[0]` 结束，`j++`
 *
 * - `A[1] = [5,10]` 和 `B[1] = [8,12]`：
 *   - `lo = max(5,8) = 8`
 *   - `hi = min(10,12) = 10`
 *   - 交集 `[8,10]` 加入 `ans`
 *   - `A[1]` 结束，`i++`
 *
 * - 依次继续，最终 `ans = [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]`
 */

/**
 * 时间和空间复杂度：
 *
 * - **时间复杂度**：O(m + n)
 *   - `i` 和 `j` 每次只会向前移动一次，总共最多遍历 `m + n` 次，因此时间复杂度为 `O(m + n)`。
 *
 * - **空间复杂度**：O(m + n)
 *   - 最坏情况下，所有区间都有交集，需要存储 `O(m + n)` 个区间，因此额外空间复杂度是 `O(m + n)`。
 */

public class LeetCode_986_IntervalListIntersections {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算两个区间列表的交集
         * @param A 第一个区间列表，每个区间按非递减顺序排列
         * @param B 第二个区间列表，每个区间按非递减顺序排列
         * @return 两个区间列表的交集
         */
        public int[][] intervalIntersection(int[][] A, int[][] B) {
            // 结果列表，存储所有交集区间
            List<int[]> ans = new ArrayList();

            // i 遍历 A，j 遍历 B
            int i = 0, j = 0;

            // 遍历两个区间列表，查找重叠的区间
            while (i < A.length && j < B.length) {
                // 计算当前区间 A[i] 和 B[j] 的交集
                // lo 是交集的起始点，hi 是交集的结束点
                int lo = Math.max(A[i][0], B[j][0]); // 取两个区间起始点的较大值
                int hi = Math.min(A[i][1], B[j][1]); // 取两个区间结束点的较小值

                // 如果起始点 lo 小于等于结束点 hi，说明区间有交集
                if (lo <= hi) {
                    ans.add(new int[]{lo, hi});
                }

                // 移动指针，移除结束点较小的区间
                if (A[i][1] < B[j][1]) {
                    i++; // A[i] 区间已结束，移动到下一个区间
                } else {
                    j++; // B[j] 区间已结束，移动到下一个区间
                }
            }

            // 返回交集数组
            return ans.toArray(new int[ans.size()][]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_986_IntervalListIntersections().new Solution();

        // 测试样例 1
        int[][] A1 = {{0,2},{5,10},{13,23},{24,25}};
        int[][] B1 = {{1,5},{8,12},{15,24},{25,26}};
        System.out.println("测试样例 1 结果：" + Arrays.deepToString(solution.intervalIntersection(A1, B1)));
        // 预期输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

        // 测试样例 2（无交集）
        int[][] A2 = {{1,3},{5,9}};
        int[][] B2 = {{10,15},{20,25}};
        System.out.println("测试样例 2 结果：" + Arrays.deepToString(solution.intervalIntersection(A2, B2)));
        // 预期输出：[]

        // 测试样例 3（完全相同）
        int[][] A3 = {{1,5},{10,15}};
        int[][] B3 = {{1,5},{10,15}};
        System.out.println("测试样例 3 结果：" + Arrays.deepToString(solution.intervalIntersection(A3, B3)));
        // 预期输出：[[1,5],[10,15]]

        // 测试样例 4（一个区间包含另一个）
        int[][] A4 = {{3,10}};
        int[][] B4 = {{1,5},{7,12}};
        System.out.println("测试样例 4 结果：" + Arrays.deepToString(solution.intervalIntersection(A4, B4)));
        // 预期输出：[[3,5],[7,10]]
    }
}

/**
You are given two lists of closed intervals, firstList and secondList, where 
firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of 
intervals is pairwise disjoint and in sorted order. 

 Return the intersection of these two interval lists. 

 A closed interval [a, b] (with a <= b) denotes the set of real numbers x with 
a <= x <= b. 

 The intersection of two closed intervals is a set of real numbers that are 
either empty or represented as a closed interval. For example, the intersection of [
1, 3] and [2, 4] is [2, 3]. 

 
 Example 1: 
 
 
Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[1
5,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 

 Example 2: 

 
Input: firstList = [[1,3],[5,9]], secondList = []
Output: []
 

 
 Constraints: 

 
 0 <= firstList.length, secondList.length <= 1000 
 firstList.length + secondList.length >= 1 
 0 <= starti < endi <= 10⁹ 
 endi < starti+1 
 0 <= startj < endj <= 10⁹ 
 endj < startj+1 
 

 Related Topics Array Two Pointers Line Sweep 👍 5702 👎 121

*/
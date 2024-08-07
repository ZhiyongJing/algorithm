package leetcode.question.greedy;

import java.util.Arrays;

/**
 *@Question:  452. Minimum Number of Arrows to Burst Balloons
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 66.31%
 *@Time  Complexity: O(N log N)
 *@Space Complexity: O(logN)
 */

/**
 * ### 题目描述
 *
 * **题目编号：452. Minimum Number of Arrows to Burst Balloons**
 *
 * **问题描述：**
 *
 * 给定一个二维数组 `points`，每个元素是一个长度为 2 的数组 `[start, end]`，表示一个气球的起始和结束位置。你需要找到射爆所有气球所需的最少箭数。一支箭可以射穿多个气球，只要这些气球的起始和结束位置重叠或相接。
 *
 * ### 解题思路
 *
 * 1. **排序气球：**
 *
 *    - **按照右边界排序**：首先按照气球的右边界（`end`）进行排序。排序的原因是，尽可能先处理结束较早的气球，以便使用最少的箭。
 *
 * 2. **贪心算法：**
 *
 *    - **初始化箭数**：至少需要一支箭来开始。
 *    - **遍历气球**：从排序后的气球列表中依次检查每个气球。
 *      - 如果当前气球的起始位置（`start`）在已经用箭的气球的结束位置（`firstEnd`）之后，说明需要一支新的箭。
 *      - 如果当前气球的起始位置在之前的箭覆盖的范围内（即在 `firstEnd` 之内），则无需新的箭，继续使用现有的箭覆盖当前气球。
 *    - 更新箭的结束位置为当前气球的结束位置（`xEnd`）。
 *
 * 3. **计算箭数：**
 *
 *    - 每当发现一个新的气球需要用到新的箭时，箭数增加。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**：`O(N log N)`
 *   主要操作是对气球按右边界进行排序，这一过程的时间复杂度是 `O(N log N)`。遍历气球的时间复杂度是 `O(N)`，因此总的时间复杂度是 `O(N log N)`。
 *
 * - **空间复杂度**：`O(1)`
 *   只使用了常量空间来存储变量（例如箭数、当前气球的起始和结束位置），没有使用额外的存储空间，因此空间复杂度为 `O(1)`。
 */
public class LeetCode_452_MinimumNumberOfArrowsToBurstBalloons{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 计算射爆气球所需的最少箭数
        public int findMinArrowShots(int[][] points) {
            // 如果没有气球，返回 0
            if (points.length == 0) return 0;

            // 按照气球的右边界（end）进行排序
            Arrays.sort(points, (o1, o2) -> {
                // 处理边界相同的情况
                if (o1[1] == o2[1]) return 0;
                // 比较右边界
                if (o1[1] < o2[1]) return -1;
                return 1;
            });

            int arrows = 1;  // 至少需要一支箭
            int xStart, xEnd, firstEnd = points[0][1];  // 初始化第一个气球的右边界

            // 遍历所有气球
            for (int[] p: points) {
                xStart = p[0];  // 当前气球的左边界
                xEnd = p[1];    // 当前气球的右边界

                // 如果当前气球的左边界在上一个气球的右边界之后，则需要一支新箭
                if (firstEnd < xStart) {
                    arrows++;      // 增加箭数
                    firstEnd = xEnd;  // 更新箭的结束位置
                }
            }

            return arrows;  // 返回所需箭的最少数量
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_452_MinimumNumberOfArrowsToBurstBalloons().new Solution();

        // 测试样例 1
        int[][] points1 = {
                {10, 16},
                {2, 8},
                {1, 6},
                {7, 12}
        };
        System.out.println("测试样例 1：");
        // 期望输出：2，射两箭可以覆盖所有气球
        System.out.println("所需箭的最少数量: " + solution.findMinArrowShots(points1));

        // 测试样例 2
        int[][] points2 = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8}
        };
        System.out.println("测试样例 2：");
        // 期望输出：4，每个气球需要一支箭
        System.out.println("所需箭的最少数量: " + solution.findMinArrowShots(points2));

        // 测试样例 3
        int[][] points3 = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        System.out.println("测试样例 3：");
        // 期望输出：3，两支箭可以覆盖前三个气球，一支箭覆盖最后一个气球
        System.out.println("所需箭的最少数量: " + solution.findMinArrowShots(points3));
    }
}

/**
There are some spherical balloons taped onto a flat wall that represents the XY-
plane. The balloons are represented as a 2D integer array points where points[i]
 = [xstart, xend] denotes a balloon whose horizontal diameter stretches between 
xstart and xend. You do not know the exact y-coordinates of the balloons. 

 Arrows can be shot up directly vertically (in the positive y-direction) from 
different points along the x-axis. A balloon with xstart and xend is burst by an 
arrow shot at x if xstart <= x <= xend. There is no limit to the number of 
arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any 
balloons in its path. 

 Given the array points, return the minimum number of arrows that must be shot 
to burst all balloons. 

 
 Example 1: 

 
Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 

 Example 2: 

 
Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 
arrows.
 

 Example 3: 

 
Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 

 
 Constraints: 

 
 1 <= points.length <= 10⁵ 
 points[i].length == 2 
 -2³¹ <= xstart < xend <= 2³¹ - 1 
 

 Related Topics Array Greedy Sorting 👍 7466 👎 241

*/
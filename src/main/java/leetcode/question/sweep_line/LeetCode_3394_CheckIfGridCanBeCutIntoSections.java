package leetcode.question.sweep_line;

import java.util.Arrays;

/**
 *@Question:  3394. Check if Grid can be Cut into Sections
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 10.91%
 *@Time  Complexity: O(N * logN) n is number of rectangles
 *@Space Complexity: O(logN) java arrays.sort take o(logN) space
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 3394 - Check if Grid can be Cut into Sections
 *
 * 给定一组矩形，每个矩形用四个整数 [x1, y1, x2, y2] 表示其左下角和右上角的坐标。
 * 现在我们想要将这组矩形分成 3 个不相交的区域（section），要求：
 * - 每一部分必须完全被矩形覆盖
 * - 可以通过在 x 或 y 方向上进行“切割”来实现区域划分
 *
 * 问：是否存在一组切割方案，可以将这些矩形划分成 3 个完全独立的区域？
 * （例如横向切两刀、或纵向切两刀，把区域分成三段）
 *
 * -------------------------------------------------------------------
 *
 * 第二步：解题思路（代码逻辑逐步拆解 + 举例说明）
 * -------------------------------------------------------------------
 * ✅ 整体思路：
 * 我们需要找出能将矩形集合切成“3 段”的两个空隙（gap），即矩形之间不重叠的空间。
 *
 * 算法采用 **扫描线思路（sweep line）**，在两个维度上都尝试一次：
 * - 横向（y 轴）方向扫描 → 横切
 * - 纵向（x 轴）方向扫描 → 竖切
 *
 * 每次判断某个维度是否可以形成 **至少两个不重叠 gap**，即找到能将区域切成三部分的两条切线。
 *
 * ✅ 解题步骤：
 * 1. 对所有矩形在当前维度（x 或 y）上的起点进行排序；
 * 2. 使用变量 `furthestEnd` 记录到目前为止最远的终点；
 * 3. 遍历每个矩形：
 *    - 如果当前矩形的起点 > 前面的终点，说明存在空隙（gap）；
 *    - gap 数加一；
 *    - 更新 `furthestEnd` 为当前的最大终点；
 * 4. 如果找到的 gap 数量 ≥ 2，说明可以切成三段，返回 true；
 * 5. 横向与纵向都尝试一次，只要有一种方向满足条件即可。
 *
 * ✅ 举例说明：
 * 输入：
 *   rectangles = [
 *     [0, 0, 1, 5],   // 从 x=0 到 x=1
 *     [1, 0, 2, 5],   // 从 x=1 到 x=2
 *     [4, 0, 5, 5]    // 从 x=4 到 x=5（与前面有 gap）
 *   ]
 *
 * 处理：
 *   - 按照 x 排序后为：x=0→1, 1→2, 4→5
 *   - 第一个 gap 出现在 x=2 到 x=4
 *   - 因为有一个 gap，继续往后发现还有更多 gap（例如如果还有 x=6→7）
 *   - 如果 gap ≥ 2，就说明可以切成 3 段，返回 true
 *
 * -------------------------------------------------------------------
 *
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * 时间复杂度：O(N logN)
 * - 对 rectangles 按照 x 或 y 坐标排序，耗时 O(N logN)
 * - 遍历一次所有矩形 O(N)
 * - 总共尝试两个维度，因此仍是 O(N logN)
 *
 * 空间复杂度：O(logN)
 * - Java 的 Arrays.sort() 是 TimSort，使用递归空间 O(logN)
 * - 其余只用到常数空间
 */

public class LeetCode_3394_CheckIfGridCanBeCutIntoSections{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean checkValidCuts(int n, int[][] rectangles) {
            // 尝试两种方向的切割：dim=0 表示按 x 轴（竖切），dim=1 表示按 y 轴（横切）
            return checkCuts(rectangles, 0) || checkCuts(rectangles, 1);
        }

        // 检查是否可以在给定维度上做出有效切割（将平面分成3部分）
        private boolean checkCuts(int[][] rectangles, int dim) {
            int gapCount = 0; // 记录有多少个空隙（切割点）

            // 根据某一维度的起点进行排序（x 轴或 y 轴）
            Arrays.sort(rectangles, (a, b) -> Integer.compare(a[dim], b[dim]));

            // 初始化当前最远的右边界或上边界（根据 dim 决定）
            int furthestEnd = rectangles[0][dim + 2];

            // 从第二个矩形开始遍历
            for (int i = 1; i < rectangles.length; i++) {
                int[] rect = rectangles[i];

                // 如果当前矩形起点大于前面矩形的最远终点，说明中间有空隙，可切
                if (furthestEnd <= rect[dim]) {
                    gapCount++; // 发现一个切割点
                }

                // 更新目前为止最远的结束坐标
                furthestEnd = Math.max(furthestEnd, rect[dim + 2]);
            }

            // 至少需要两个空隙，才能把平面切成三段（3 sections 需要 2 个切割点）
            return gapCount >= 2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_3394_CheckIfGridCanBeCutIntoSections().new Solution();

        // ✅ 测试样例 1：可以纵向切出3段
        int[][] rectangles1 = {
                {0, 0, 1, 5},
                {1, 0, 2, 5},
                {4, 0, 5, 5}
        };
        System.out.println(solution.checkValidCuts(5, rectangles1)); // true

        // ✅ 测试样例 2：只能横切
        int[][] rectangles2 = {
                {0, 0, 5, 1},
                {0, 1, 5, 2},
                {0, 4, 5, 5}
        };
        System.out.println(solution.checkValidCuts(5, rectangles2)); // true

        // ❌ 测试样例 3：无空隙，无法切出3段
        int[][] rectangles3 = {
                {0, 0, 5, 2},
                {0, 2, 5, 4}
        };
        System.out.println(solution.checkValidCuts(5, rectangles3)); // false

        // ✅ 测试样例 4：两个明显 gap
        int[][] rectangles4 = {
                {0, 0, 2, 5},
                {3, 0, 4, 5},
                {5, 0, 6, 5}
        };
        System.out.println(solution.checkValidCuts(5, rectangles4)); // true
    }
}

/**
You are given an integer n representing the dimensions of an n x n
 grid, with the origin at the bottom-left corner of the grid. You are also 
given a 2D array of coordinates rectangles, where rectangles[i] is in the form [
startx, starty, endx, endy], representing a rectangle on the grid. Each rectangle is 
defined as follows: 

 
 (startx, starty): The bottom-left corner of the rectangle. 
 (endx, endy): The top-right corner of the rectangle. 
 

 Note that the rectangles do not overlap. Your task is to determine if it is 
possible to make either two horizontal or two vertical cuts on the grid such that: 


 
 Each of the three resulting sections formed by the cuts contains at least one 
rectangle. 
 Every rectangle belongs to exactly one section. 
 

 Return true if such cuts can be made; otherwise, return false. 

 
 Example 1: 

 
 Input: n = 5, rectangles = [[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]] 
 

 Output: true 

 Explanation: 

 

 The grid is shown in the diagram. We can make horizontal cuts at y = 2 and y = 
4. Hence, output is true. 

 Example 2: 

 
 Input: n = 4, rectangles = [[0,0,1,1],[2,0,3,4],[0,2,2,3],[3,0,4,3]] 
 

 Output: true 

 Explanation: 

 

 We can make vertical cuts at x = 2 and x = 3. Hence, output is true. 

 Example 3: 

 
 Input: n = 4, rectangles = [[0,2,2,4],[1,0,3,2],[2,2,3,4],[3,0,4,2],[3,2,4,4]] 

 

 Output: false 

 Explanation: 

 We cannot make two horizontal or two vertical cuts that satisfy the conditions.
 Hence, output is false. 

 
 Constraints: 

 
 3 <= n <= 10⁹ 
 3 <= rectangles.length <= 10⁵ 
 0 <= rectangles[i][0] < rectangles[i][2] <= n 
 0 <= rectangles[i][1] < rectangles[i][3] <= n 
 No two rectangles overlap. 
 

 Related Topics Array Sorting 👍 565 👎 34

*/
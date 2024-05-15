package interview.company.amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *@Question:  939. Minimum Area Rectangle
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 60.85%
 *@Time  Complexity: O(N^2)
 *@Space Complexity: O(N)
 */

/**
 * 这段代码的目标是在给定的点集合中找到最小面积的矩形，并返回其面积。下面是解题思路的详细解释：
 *
 * 1. **构建坐标映射：**
 *     - 首先，我们需要将给定的点集合映射到一个数据结构中，以便于我们能够有效地查找与某个点相对应的其他点。这里我们使用哈希映射来实现。
 *     - 我们创建一个 `Map<Integer, Set<Integer>>` 类型的 `map`，其中键是 x 坐标，值是一个集合，用于存储与该 x 坐标相关联的所有 y 坐标。
 *
 * 2. **遍历点对：**
 *     - 接下来，我们遍历点集合中的所有点对 `(p1, p2)`，其中 `p1` 和 `p2` 不在同一条水平线或竖直线上，即 `p1[0] != p2[0] && p1[1] != p2[1]`。这样可以确保我们不会在同一条边上找到矩形。
 *
 * 3. **寻找可能的矩形：**
 *     - 对于每对不同的点 `(p1, p2)`，我们检查是否存在另外两个点 `(p3, p4)`，使得 `p1, p2, p3, p4` 可以形成一个矩形。
 *     - 具体来说，我们检查是否存在 `p1` 和 `p2` 的横纵坐标分别与 `p3` 和 `p4` 的纵横坐标相等的点，这样就可以形成一个矩形。
 *
 * 4. **计算矩形面积：**
 *     - 如果找到了这样的点 `(p3, p4)`，我们计算以 `p1` 和 `p2` 为对角线的矩形的面积，并更新最小面积。
 *
 * 5. **返回结果：**
 *     - 最后，如果找到了符合条件的矩形，则返回最小面积，否则返回 0。
 *
 * 时间复杂度分析：
 * - 构建坐标映射需要遍历所有点并存储到哈希映射中，时间复杂度为 O(N)，其中 N 是点的数量。
 * - 然后，我们遍历所有可能的点对，并在哈希映射中查找与之相关联的其他两个点，这一步的时间复杂度为 O(N^2)。
 * - 因此，总体时间复杂度为 O(N^2)。
 *
 * 空间复杂度分析：
 * - 我们使用了一个哈希映射来存储点的坐标，以及一些辅助变量，因此空间复杂度为 O(N)。
 */
public class LeetCode_939_MinimumAreaRectangle{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 寻找最小面积矩形
         *
         * @param points 给定的点集合
         * @return 最小面积矩形的面积
         */
        public int minAreaRect(int[][] points) {
            // 创建一个哈希映射，用于存储每个 x 坐标对应的 y 坐标集合
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for (int[] p : points) {
                if (!map.containsKey(p[0])) {
                    map.put(p[0], new HashSet<>());
                }
                map.get(p[0]).add(p[1]);
            }

            int min = Integer.MAX_VALUE;
            // 遍历所有的点对，查找可能的矩形
            for (int[] p1 : points) {
                for (int[] p2 : points) {
                    if (p1[0] == p2[0] || p1[1] == p2[1]) { // 如果有相同的 x 或者 y，跳过
                        continue;
                    }
                    if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) { // 找到另外两个点
                        // 计算面积并更新最小值
                        min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                    }
                }
            }
            // 如果没有找到矩形，返回0；否则返回最小面积
            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_939_MinimumAreaRectangle().new Solution();
        // 测试
        int[][] points1 = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}};
        System.out.println(solution.minAreaRect(points1)); // 应返回 4

        int[][] points2 = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}};
        System.out.println(solution.minAreaRect(points2)); // 应返回 2
    }
}

/**
You are given an array of points in the X-Y plane points where points[i] = [xi, 
yi]. 

 Return the minimum area of a rectangle formed from these points, with sides 
parallel to the X and Y axes. If there is not any such rectangle, return 0. 

 
 Example 1: 
 
 
Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
 

 Example 2: 
 
 
Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2
 

 
 Constraints: 

 
 1 <= points.length <= 500 
 points[i].length == 2 
 0 <= xi, yi <= 4 * 10⁴ 
 All the given points are unique. 
 

 Related Topics Array Hash Table Math Geometry Sorting 👍 1934 👎 278

*/
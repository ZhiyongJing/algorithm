package leetcode.question.map_set;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  3160. Find the Number of Distinct Colors Among the Balls
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 58.98%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 题目描述：
 * 3160. Find the Number of Distinct Colors Among the Balls
 * 给定一个整数 limit（表示球的编号上限）和一个查询数组 queries，其中每个查询 queries[i] = [ball, color]
 * 表示给编号为 ball 的球涂上颜色 color。
 *
 * 在每次查询后，需要返回当前所有球上不同颜色的总数。
 *
 * 示例 1：
 * 输入: limit = 3, queries = [[1, 2], [2, 3], [1, 3], [3, 2]]
 * 输出: [1, 2, 2, 3]
 * 解释：
 * - 第 1 次查询后，球 1 被涂上颜色 2，当前有 1 种颜色: {2}
 * - 第 2 次查询后，球 2 被涂上颜色 3，当前有 2 种颜色: {2, 3}
 * - 第 3 次查询后，球 1 被重新涂上颜色 3，当前仍然是 2 种颜色: {3}
 * - 第 4 次查询后，球 3 被涂上颜色 2，当前有 3 种颜色: {2, 3}
 *
 * 示例 2：
 * 输入: limit = 3, queries = [[1, 1], [2, 2], [3, 3], [1, 2], [2, 3]]
 * 输出: [1, 2, 3, 3, 3]
 *
 * 解题思路：
 * 1. **使用哈希表记录球的颜色**
 *    - 使用 `ballMap` 记录每个球当前的颜色，键是球的编号，值是对应的颜色。
 *    - 例如：
 *      - queries = [[1, 2], [2, 3], [1, 3]]
 *      - ballMap 变化过程：
 *        - {1:2}
 *        - {1:2, 2:3}
 *        - {1:3, 2:3}（球 1 重新涂成 3）
 *
 * 2. **使用哈希表记录颜色的出现次数**
 *    - 使用 `colorMap` 记录当前所有颜色的球的数量，键是颜色，值是该颜色的球数。
 *    - 例如：
 *      - queries = [[1, 2], [2, 3], [1, 3]]
 *      - colorMap 变化过程：
 *        - {2:1}
 *        - {2:1, 3:1}
 *        - {2:0, 3:2}（颜色 2 没有球了，从 `colorMap` 中移除）
 *
 * 3. **处理每个查询**
 *    - 遍历 queries，对于每个 [ball, color]：
 *      - 如果球 `ball` 之前已经有颜色 `prevColor`，则减少 `colorMap[prevColor]` 计数。
 *      - 如果 `prevColor` 计数变为 0，则从 `colorMap` 中移除。
 *      - 更新 `ballMap[ball] = color`，并增加 `colorMap[color]` 计数。
 *      - 记录 `colorMap.size()` 作为当前查询的结果。
 *    - 例如：
 *      - 输入: queries = [[1, 2], [2, 3], [1, 3]]
 *      - 结果: [1, 2, 2]
 *
 * 时间复杂度分析：
 * - 每个查询都需要 O(1) 操作（哈希表插入、删除、查找）。
 * - 总体时间复杂度为 **O(N)**，其中 N 是 queries 的大小。
 *
 * 空间复杂度分析：
 * - `ballMap` 和 `colorMap` 在最坏情况下分别存储 O(N) 个键值对。
 * - 总体空间复杂度为 **O(N)**。
 */


public class LeetCode_3160_FindTheNumberOfDistinctColorsAmongTheBalls{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int[] queryResults(int limit, int[][] queries) {
            // 获取查询的数量
            int n = queries.length;
            // 结果数组，存储每个查询后的不同颜色数量
            int[] result = new int[n];

            // 记录每种颜色的球的数量
            Map<Integer, Integer> colorMap = new HashMap<>();
            // 记录每个球当前的颜色
            Map<Integer, Integer> ballMap = new HashMap<>();

            // 遍历所有查询
            for (int i = 0; i < n; i++) {
                // 获取当前查询的球编号和颜色
                int ball = queries[i][0];
                int color = queries[i][1];

                // 检查该球是否已经被涂色
                if (ballMap.containsKey(ball)) {
                    // 获取该球之前的颜色
                    int prevColor = ballMap.get(ball);
                    // 该颜色的球数量减少 1
                    colorMap.put(prevColor, colorMap.get(prevColor) - 1);

                    // 如果该颜色的球数量变为 0，则从 colorMap 中移除
                    if (colorMap.get(prevColor) == 0) {
                        colorMap.remove(prevColor);
                    }
                }

                // 更新该球的新颜色
                ballMap.put(ball, color);

                // 增加新颜色的球的数量
                colorMap.put(color, colorMap.getOrDefault(color, 0) + 1);

                // 记录当前查询后，不同颜色的数量
                result[i] = colorMap.size();
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_3160_FindTheNumberOfDistinctColorsAmongTheBalls().new Solution();

        // 测试样例
        int[][] test1 = {{1, 2}, {2, 3}, {1, 3}, {3, 2}};
        System.out.println(Arrays.toString(solution.queryResults(3, test1))); // 预期输出: [1, 2, 2, 3]

        int[][] test2 = {{1, 1}, {2, 2}, {3, 3}, {1, 2}, {2, 3}};
        System.out.println(Arrays.toString(solution.queryResults(3, test2))); // 预期输出: [1, 2, 3, 3, 3]

        int[][] test3 = {{1, 5}, {2, 5}, {3, 5}, {4, 6}, {1, 6}};
        System.out.println(Arrays.toString(solution.queryResults(6, test3))); // 预期输出: [1, 1, 1, 2, 2]
    }
}

/**
You are given an integer limit and a 2D array queries of size n x 2. 

 There are limit + 1 balls with distinct labels in the range [0, limit]. 
Initially, all balls are uncolored. For every query in queries that is of the form [x, 
y], you mark ball x with the color y. After each query, you need to find the 
number of colors among the balls. 

 Return an array result of length n, where result[i] denotes the number of 
colors after iᵗʰ query. 

 Note that when answering a query, lack of a color will not be considered as a 
color. 

 
 Example 1: 

 
 Input: limit = 4, queries = [[1,4],[2,5],[1,3],[3,4]] 
 

 Output: [1,2,2,3] 

 Explanation: 

 

 
 After query 0, ball 1 has color 4. 
 After query 1, ball 1 has color 4, and ball 2 has color 5. 
 After query 2, ball 1 has color 3, and ball 2 has color 5. 
 After query 3, ball 1 has color 3, ball 2 has color 5, and ball 3 has color 4. 

 

 Example 2: 

 
 Input: limit = 4, queries = [[0,1],[1,2],[2,2],[3,4],[4,5]] 
 

 Output: [1,2,2,3,4] 

 Explanation: 

 

 
 After query 0, ball 0 has color 1. 
 After query 1, ball 0 has color 1, and ball 1 has color 2. 
 After query 2, ball 0 has color 1, and balls 1 and 2 have color 2. 
 After query 3, ball 0 has color 1, balls 1 and 2 have color 2, and ball 3 has 
color 4. 
 After query 4, ball 0 has color 1, balls 1 and 2 have color 2, ball 3 has 
color 4, and ball 4 has color 5. 
 

 
 Constraints: 

 
 1 <= limit <= 10⁹ 
 1 <= n == queries.length <= 10⁵ 
 queries[i].length == 2 
 0 <= queries[i][0] <= limit 
 1 <= queries[i][1] <= 10⁹ 
 

 Related Topics Array Hash Table Simulation 👍 735 👎 88

*/
package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 *@Question:  475. Heaters
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 57.81%
 *@Time  Complexity: O(n log n + m log m)  // 排序花费 O(n log n)
 *@Space Complexity: O(lonN)  // 除了排序，没有额外空间
 */
/**
 * ===============================================
 * LeetCode 475. Heaters
 * ===============================================
 *
 * 【一、题目描述】
 * 给定两个整数数组：
 *   - houses：表示一维坐标轴上的房子位置
 *   - heaters：表示一维坐标轴上的加热器位置
 *
 * 每个加热器都可以加热一定范围内的房子。你需要找到**最小加热半径 r**，使得每个房子都在至少一个加热器的加热范围内。
 * 返回这个最小半径。
 *
 * 约束：
 * - 所有位置均为非负整数
 * - 数组长度最多为 3×10⁴
 *
 * 示例：
 * 输入：houses = [1,2,3], heaters = [2]
 * 输出：1
 * 解释：加热器放在位置 2，加热半径 1 则能覆盖 [1,2,3]
 *
 * 输入：houses = [1,2,3,4], heaters = [1,4]
 * 输出：1
 * 解释：heater 1 覆盖 [1,2]，heater 4 覆盖 [3,4]
 *
 * 【二、解题思路详解（双指针法）】
 *
 * ✅ 总体思路：
 *   - 将 `houses` 和 `heaters` 排序
 *   - 使用双指针：
 *     - `i` 遍历房子
 *     - `j` 遍历当前最接近 `houses[i]` 的加热器
 *
 * ✅ 步骤详解：
 * 1. 首先对 `houses` 和 `heaters` 进行排序；
 * 2. 初始化两个指针：
 *    - `i = 0` 表示当前处理的房子
 *    - `j = 0` 表示当前使用的加热器
 * 3. 对于每一个房子 houses[i]：
 *    - 计算当前加热器 j 到房子的距离 `dist1`
 *    - 计算下一个加热器 j+1 到房子的距离 `dist2`（若存在）
 *    - 如果 `dist1 < dist2`，说明当前加热器比下一个更近，此时记录这个房子的最短距离
 *    - 如果 `dist2 < dist1`，说明可能下一个加热器更优，则移动 j++
 * 4. 对所有房子维护一个最大的最小距离作为结果，即最小半径
 *
 * ✅ 举例说明：
 * 示例：houses = [1,2,3], heaters = [2]
 * - 排序后：houses = [1,2,3], heaters = [2]
 * - i = 0, j = 0：dist1 = |2-1| = 1，dist2 = 无，使用 heater 2 → res = 1
 * - i = 1, j = 0：dist1 = |2-2| = 0 → res = max(1,0) = 1
 * - i = 2, j = 0：dist1 = |2-3| = 1 → res = max(1,1) = 1
 * 最终答案为 1
 *
 * 【三、时间与空间复杂度分析】
 *
 * 时间复杂度：O(n log n + m log m)
 * - 排序花费 O(n log n + m log m)
 * - 遍历每个房子为 O(n)，总共最多移动一次加热器指针 O(m)
 *
 * 空间复杂度：O(1)
 * - 除了排序和结果变量，未使用额外空间
 */


public class LeetCode_475_Heaters{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findRadius(int[] houses, int[] heaters) {
            // 边界情况判断：房子或加热器数组为空，返回 0
            if(houses == null || houses.length == 0 || heaters == null || heaters.length == 0){
                return 0;
            }

            // 对房子和加热器位置进行排序（确保从左到右扫描）
            Arrays.sort(houses);
            Arrays.sort(heaters);

            // n 为房子的数量，m 为加热器的数量
            int n = houses.length;
            int m = heaters.length;

            // i 指向当前处理的房子，j 指向当前加热器
            int i = 0;
            int j = 0;

            // 结果变量，表示所需的最小加热半径
            int res = 0;

            // 使用双指针遍历所有房子
            while(i < n && j < m){
                // 计算当前加热器与当前房子的距离
                int dist1 = Math.abs(heaters[j] - houses[i]);

                // 如果有下一个加热器，计算下一个加热器与当前房子的距离
                int dist2 = Integer.MAX_VALUE;
                if(j + 1 < m){
                    dist2 = Math.abs(heaters[j + 1] - houses[i]);
                }

                // 如果当前加热器更近于当前房子，则将当前最小距离加入结果中，并处理下一个房子
                if(dist1 < dist2){
                    res = Math.max(res, dist1);
                    i++;
                } else {
                    // 否则移动加热器指针，尝试用更靠近的加热器去加热当前房子
                    j++;
                }
            }

            // 返回所需的最小加热半径
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_475_Heaters().new Solution();

        // 测试用例 1：房子 [1,2,3]，加热器 [2]，最小半径是 1
        int[] houses1 = {1, 2, 3};
        int[] heaters1 = {2};
        System.out.println("测试用例 1 输出：" + solution.findRadius(houses1, heaters1)); // 预期输出 1

        // 测试用例 2：房子 [1,2,3,4]，加热器 [1,4]，最小半径是 1
        int[] houses2 = {1, 2, 3, 4};
        int[] heaters2 = {1, 4};
        System.out.println("测试用例 2 输出：" + solution.findRadius(houses2, heaters2)); // 预期输出 1

        // 测试用例 3：房子 [1,5]，加热器 [10]，最小半径是 9
        int[] houses3 = {1, 5};
        int[] heaters3 = {10};
        System.out.println("测试用例 3 输出：" + solution.findRadius(houses3, heaters3)); // 预期输出 9
    }
}

/**
Winter is coming! During the contest, your first job is to design a standard 
heater with a fixed warm radius to warm all the houses. 

 Every house can be warmed, as long as the house is within the heater's warm 
radius range. 

 Given the positions of houses and heaters on a horizontal line, return the 
minimum radius standard of heaters so that those heaters could cover all houses. 

 Notice that all the heaters follow your radius standard, and the warm radius 
will the same. 

 
 Example 1: 

 
Input: houses = [1,2,3], heaters = [2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the 
radius 1 standard, then all the houses can be warmed.
 

 Example 2: 

 
Input: houses = [1,2,3,4], heaters = [1,4]
Output: 1
Explanation: The two heaters were placed at positions 1 and 4. We need to use a 
radius 1 standard, then all the houses can be warmed.
 

 Example 3: 

 
Input: houses = [1,5], heaters = [2]
Output: 3
 

 
 Constraints: 

 
 1 <= houses.length, heaters.length <= 3 * 10⁴ 
 1 <= houses[i], heaters[i] <= 10⁹ 
 

 Related Topics Array Two Pointers Binary Search Sorting 👍 2213 👎 1179

*/
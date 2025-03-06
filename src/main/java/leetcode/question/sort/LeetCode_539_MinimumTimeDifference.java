package leetcode.question.sort;

import java.util.Arrays;
import java.util.List;

/**
 *@Question:  539. Minimum Time Difference
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time Complexity: O(NlogN) for solution1, O(N) for solution2
 *@Space Complexity: O(N) for solution1, O(1) for solution2
 */
/**
 * 题目描述：
 * LeetCode 539. Minimum Time Difference
 * 给定一个 `timePoints` 列表，其中每个时间点的格式为 `"HH:MM"`（24 小时制）。
 * 计算**所有时间点中最小的时间差**（分钟）。
 *
 * **约束**
 * - `timePoints.length >= 2`
 * - 每个时间点格式保证是 `"HH:MM"`（00:00 - 23:59）。
 *
 * **示例 1：**
 * ```
 * 输入: timePoints = ["23:59", "00:00"]
 * 输出: 1
 * ```
 * 解释：
 * - "23:59" 和 "00:00" 之间的时间差为 `1` 分钟（跨午夜）。
 *
 * **示例 2：**
 * ```
 * 输入: timePoints = ["00:00", "04:30", "22:00"]
 * 输出: 150
 * ```
 * 解释：
 * - "04:30" 和 "22:00" 之间的时间差是 `150` 分钟。
 *
 * **示例 3：**
 * ```
 * 输入: timePoints = ["12:30", "12:30"]
 * 输出: 0
 * ```
 * 解释：
 * - 由于有重复时间点，最小时间差是 `0` 分钟。
 */

/**
 * 解题思路：
 * **方法 1：排序 + 遍历**
 * 1. **转换时间格式**
 *    - 将 `"HH:MM"` 转换为 **分钟数**（0 - 1439）。
 * 2. **排序**
 *    - 按分钟数排序。
 * 3. **计算相邻时间的最小差值**
 *    - 遍历相邻时间点，计算最小时间差。
 * 4. **考虑首尾时间的最小时间差**
 *    - 计算 `00:00` 到 `23:59` 之间的时间差（跨午夜）。
 *
 * **举例分析**
 * ```
 * 输入: ["23:59", "00:00"]
 * 1. 转换时间: [1439, 0]
 * 2. 排序: [0, 1439]
 * 3. 计算相邻差值: |1439 - 0| = 1439
 * 4. 计算跨午夜: 1440 - 1439 + 0 = 1
 * 输出: 1
 * ```
 *
 * ---
 *
 * **方法 2：桶排序（O(N) 复杂度）**
 * 1. **创建 `1440` 长度的 `boolean[]` 数组**
 *    - 由于时间点最多 `1440` 种可能（`24 * 60 = 1440`），可以用 `boolean[1440]` 直接存储是否出现过某时间点。
 * 2. **遍历 `timePoints` 并填充 `boolean[]`**
 *    - 如果时间点已存在，直接返回 `0`（表示最小时间差）。
 * 3. **遍历 `boolean[]` 找最小时间差**
 *    - 记录 `prevIndex`（上一个出现的时间点）。
 *    - 计算所有相邻时间点的时间差，取最小值。
 *    - 计算跨午夜的最小时间差 `1440 - lastIndex + firstIndex`。
 *
 * **举例分析**
 * ```
 * 输入: ["23:59", "00:00"]
 * 1. 创建 `boolean[1440]` 数组
 * 2. 填充: `boolean[0] = true, boolean[1439] = true`
 * 3. 遍历 `boolean[]`
 *    - prevIndex = 0
 *    - 1439 - 0 = 1439
 *    - 跨午夜: 1440 - 1439 + 0 = 1
 * 输出: 1
 * ```
 */

/**
 * 时间和空间复杂度分析：
 *
 * **方法 1：排序 + 遍历**
 * 1. **时间复杂度：O(NlogN)**
 *    - 转换分钟数 `O(N)`
 *    - 排序 `O(N log N)`
 *    - 遍历 `O(N)`
 *    - **总复杂度：O(N log N)**
 *
 * 2. **空间复杂度：O(N)**
 *    - 需要 `O(N)` 额外数组存储转换后的分钟数。
 *
 * ---
 *
 * **方法 2：桶排序**
 * 1. **时间复杂度：O(N)**
 *    - 遍历 `timePoints` 填充 `boolean[]` `O(N)`
 *    - 遍历 `boolean[]` `O(1440) ≈ O(1)`
 *    - **总复杂度：O(N)**
 *
 * 2. **空间复杂度：O(1)**
 *    - 额外使用 `boolean[1440]`（常数级空间）。
 */

public class LeetCode_539_MinimumTimeDifference{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 方法 1：排序 + 遍历
        public int findMinDifference1(List<String> timePoints) {
            // 将时间转换为分钟
            int[] minutes = new int[timePoints.size()];
            for (int i = 0; i < timePoints.size(); i++) {
                String time = timePoints.get(i);
                int h = Integer.parseInt(time.substring(0, 2));
                int m = Integer.parseInt(time.substring(3));
                minutes[i] = h * 60 + m;
            }

            // 将所有时间点按分钟排序
            Arrays.sort(minutes);

            // 计算相邻时间点的最小差值
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < minutes.length - 1; i++) {
                ans = Math.min(ans, minutes[i + 1] - minutes[i]);
            }

            // 计算首尾时间点的差值（跨午夜）
            return Math.min(ans, 24 * 60 - minutes[minutes.length - 1] + minutes[0]);
        }

        // 方法 2：桶排序（O(N) 复杂度）
        public int findMinDifference(List<String> timePoints) {
            // 创建一个布尔数组来标记时间是否出现
            boolean[] minutes = new boolean[24 * 60];
            for (String time : timePoints) {
                int min = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
                if (minutes[min]) return 0; // 说明有重复时间点，最小差值为 0
                minutes[min] = true;
            }

            int prevIndex = Integer.MAX_VALUE; // 记录上一个时间点的索引
            int firstIndex = Integer.MAX_VALUE; // 记录第一个出现的时间点
            int lastIndex = Integer.MAX_VALUE; // 记录最后一个出现的时间点
            int ans = Integer.MAX_VALUE;

            // 遍历 24 * 60 分钟，找到最小时间差
            for (int i = 0; i < 24 * 60; i++) {
                if (minutes[i]) {
                    if (prevIndex != Integer.MAX_VALUE) {
                        ans = Math.min(ans, i - prevIndex);
                    }
                    prevIndex = i;
                    if (firstIndex == Integer.MAX_VALUE) {
                        firstIndex = i;
                    }
                    lastIndex = i;
                }
            }

            // 计算首尾时间点的最小差值（跨午夜）
            return Math.min(ans, 24 * 60 - lastIndex + firstIndex);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_539_MinimumTimeDifference().new Solution();

        // 测试用例 1
        List<String> timePoints1 = Arrays.asList("23:59", "00:00");
        System.out.println(solution.findMinDifference(timePoints1));
        // 期望输出: 1 (跨午夜计算: 00:00 - 23:59 = 1 分钟)

        // 测试用例 2
        List<String> timePoints2 = Arrays.asList("00:00", "04:30", "22:00");
        System.out.println(solution.findMinDifference(timePoints2));
        // 期望输出: 150 (04:30 - 22:00 = 150 分钟)

        // 测试用例 3
        List<String> timePoints3 = Arrays.asList("12:30", "12:30");
        System.out.println(solution.findMinDifference(timePoints3));
        // 期望输出: 0 (重复时间点)

        // 测试用例 4
        List<String> timePoints4 = Arrays.asList("05:31", "22:08", "00:35");
        System.out.println(solution.findMinDifference(timePoints4));
        // 期望输出: 57 (00:35 - 23:59 = 57 分钟)
    }
}

/**
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum 
minutes difference between any two time-points in the list.

 
 Example 1: 
 Input: timePoints = ["23:59","00:00"]
Output: 1
 
 Example 2: 
 Input: timePoints = ["00:00","23:59","00:00"]
Output: 0
 
 
 Constraints: 

 
 2 <= timePoints.length <= 2 * 10⁴ 
 timePoints[i] is in the format "HH:MM". 
 

 Related Topics Array Math String Sorting 👍 2535 👎 315

*/
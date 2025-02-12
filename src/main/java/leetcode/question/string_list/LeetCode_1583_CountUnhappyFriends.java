// 定义包所在位置
package leetcode.question.string_list;
// 导入需要用到的 Java 工具包

import java.util.Arrays;

/**
 *@Question:  1583. Count Unhappy Friends
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 52.910000000000004%
 *@Time  Complexity: O()
 *@Space Complexity: O()
 */
/*
 * 一、题目描述
 *    在一场配对游戏中，共有 n 位朋友，每位朋友都有一个**偏好列表**，表示他们更愿意与哪些人配对。
 *    这些朋友被两两配对，形成了 n/2 组，每个朋友只能与其中一个朋友配对。
 *
 *    一位朋友 X 被认为是**不开心的**，当且仅当存在某个朋友 U，使得：
 *    1. X **更喜欢** U 胜过当前的配对对象 Y。
 *    2. U **也更喜欢** X 胜过 U 当前的配对对象 V。
 *
 *    目标是找出所有**不开心的朋友**的数量。
 *
 *    示例：
 *    输入：
 *    n = 4
 *    preferences = [
 *      [1,2,3],  // 0 号朋友的偏好顺序：1 > 2 > 3
 *      [3,2,0],  // 1 号朋友的偏好顺序：3 > 2 > 0
 *      [3,1,0],  // 2 号朋友的偏好顺序：3 > 1 > 0
 *      [1,2,0]   // 3 号朋友的偏好顺序：1 > 2 > 0
 *    ]
 *    pairs = [[0,1], [2,3]]  // 形成两组配对：0-1，2-3
 *
 *    输出：2
 *    解释：
 *    - 朋友 0 更喜欢 2（胜过 1），并且 2 也更喜欢 0（胜过 3），所以 0 不开心。
 *    - 朋友 2 更喜欢 0（胜过 3），并且 0 也更喜欢 2（胜过 1），所以 2 不开心。
 *    - 朋友 1 和 3 没有符合不开心的条件，所以最终有 2 位不开心的朋友。
 *
 * 二、解题思路（超级详细）
 *
 * 1. **构建排名映射表**：
 *    - 用一个 `rank[n][n]` 的二维数组来存储 **某个朋友 X 对其他所有朋友的偏好排名**。
 *    - 例如，`rank[0][1]` 表示 **朋友 0 对 朋友 1 的喜好程度**，数值越小表示越喜欢。
 *    - 这样，在后续判断时，可以快速查询 "X 是否更喜欢 U 胜过 Y"。
 *
 * 2. **存储配对关系**：
 *    - 用一个 `pairedWith[n]` 数组来存储当前每个朋友的配对对象：
 *      - `pairedWith[x] = y` 表示 朋友 x 与 朋友 y 是一对。
 *
 * 3. **遍历每位朋友 X，查找比当前配对对象更受 X 喜爱的朋友 U**：
 *    - 对于每个 X，找到它的配对对象 `Y = pairedWith[x]`。
 *    - 在 `preferences[X]` 里，找到所有 **排在 Y 之前的朋友 U**（即 X 更喜欢 U）。
 *    - 对于每个 U，找到它的配对对象 `V = pairedWith[U]`。
 *    - 判断 U 是否也更喜欢 X 胜过 V：
 *      - 如果 `rank[U][X] < rank[U][V]`，说明 **U 也更愿意与 X 配对**，那么 X 就是不开心的朋友。
 *      - 记录 X 并跳出循环（因为只要找到一个 U 满足条件，X 就已经是不开心的）。
 *
 * 4. **统计并返回不开心的朋友总数**。
 *
 * **举例分析**：
 *
 * 输入：
 * ```
 * n = 4
 * preferences = [
 *   [1,2,3],
 *   [3,2,0],
 *   [3,1,0],
 *   [1,2,0]
 * ]
 * pairs = [[0,1], [2,3]]
 * ```
 *
 * **步骤 1：构建 rank 矩阵**
 * ```
 * rank[0] = [_, 0, 1, 2]  // 0 号朋友的偏好顺序：1(0) > 2(1) > 3(2)
 * rank[1] = [2, _, 1, 0]  // 1 号朋友的偏好顺序：3(0) > 2(1) > 0(2)
 * rank[2] = [2, 1, _, 0]  // 2 号朋友的偏好顺序：3(0) > 1(1) > 0(2)
 * rank[3] = [2, 0, 1, _]  // 3 号朋友的偏好顺序：1(0) > 2(1) > 0(2)
 * ```
 *
 * **步骤 2：构建 pairedWith 数组**
 * ```
 * pairedWith[0] = 1
 * pairedWith[1] = 0
 * pairedWith[2] = 3
 * pairedWith[3] = 2
 * ```
 *
 * **步骤 3：遍历每个朋友 X**
 * - **朋友 0**:
 *   - 喜欢顺序是 `1 > 2 > 3`，当前配对是 `1`。
 *   - 0 **更喜欢 2**（比 1 排名更靠前）。
 *   - 查找朋友 2 的配对对象是 `3`，但 2 **更喜欢 0** 胜过 3。
 *   - 0 **不开心**，计数 +1。
 * - **朋友 1**:
 *   - 喜欢顺序是 `3 > 2 > 0`，当前配对是 `0`。
 *   - 没有符合不开心的条件。
 * - **朋友 2**:
 *   - 喜欢顺序是 `3 > 1 > 0`，当前配对是 `3`。
 *   - 2 **更喜欢 0**（比 3 排名更靠前）。
 *   - 查找朋友 0 的配对对象是 `1`，但 0 **更喜欢 2** 胜过 1。
 *   - 2 **不开心**，计数 +1。
 * - **朋友 3**:
 *   - 喜欢顺序是 `1 > 2 > 0`，当前配对是 `2`。
 *   - 没有符合不开心的条件。
 *
 * 最终结果：2 位不开心的朋友。
 *
 * 三、时间和空间复杂度分析
 *
 * 1. **时间复杂度**：
 *    - 构建 `rank` 数组需要 **O(n²)**（遍历所有朋友的偏好列表）。
 *    - 遍历 `pairs` 构建 `pairedWith` 数组需要 **O(n)**。
 *    - 检查每个朋友的不开心情况：
 *      - 每个朋友最多需要遍历 `O(n)` 个比当前配对对象更受喜爱的朋友。
 *      - 总体最坏情况是 **O(n²)**。
 *    - 因此，总体时间复杂度是 **O(n²)**。
 *
 * 2. **空间复杂度**：
 *    - `rank[n][n]` 需要 **O(n²)** 额外空间存储排名。
 *    - `pairedWith[n]` 需要 **O(n)** 额外空间存储配对关系。
 *    - 结果计数器 `unhappyCount` 仅占用 **O(1)** 空间。
 *    - 因此，总体空间复杂度为 **O(n²)**。
 */


// 定义公共类 LeetCode_1583_CountUnhappyFriends
public class LeetCode_1583_CountUnhappyFriends{

    // leetcode 提交区域开始（不可修改）
//leetcode submit region begin(Prohibit modification and deletion)
// 定义内部类 Solution
    class Solution {
        // 定义方法 unhappyFriends，用于计算不开心的朋友数目
        public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {

            // 定义一个二维数组 rank，用来存储每个用户对于其他用户在偏好列表中的排序
            int[][] rank = new int[n][n];
            // 遍历每个用户 i，对其 preferences[i] 列表中的每个人做映射
            for (int i = 0; i < n; i++) {
                // j 代表顺序位置，将 preferences[i][j] 映射到 rank[i][preferences[i][j]]
                for (int j = 0; j < preferences[i].length; j++) {
                    rank[i][preferences[i][j]] = j;
                }
            }
            System.out.println(Arrays.deepToString(rank));
            // 定义一个数组 pairedWith，用来记录每个用户 x 的配对对象
            int[] pairedWith = new int[n];
            // 遍历 pairs 数组，为每个用户记录其配对对象
            for (int[] pair : pairs) {
                pairedWith[pair[0]] = pair[1];
                pairedWith[pair[1]] = pair[0];
            }

            // unhappyCount 用来统计不开心的朋友数量
            int unhappyCount = 0;
            // 遍历每个用户 x
            for (int x = 0; x < n; x++) {
                // y 表示和 x 配对的那个人
                int y = pairedWith[x];
                // 在 rank[x][y] 之前的用户，都是 x 更倾向于的用户
                for (int i = 0; i < rank[x][y]; i++) {
                    // u 表示 x 更喜欢的这个用户
                    int u = preferences[x][i];
                    // v 表示 u 的配对对象
                    int v = pairedWith[u];
                    // 如果 u 也更喜欢 x 胜过 v，则 x 不开心
                    if (rank[u][x] < rank[u][v]) {
                        unhappyCount++;
                        // 跳出循环，不再计算 x
                        break;
                    }
                }
            }
            // 返回不开心的朋友数量
            return unhappyCount;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    // main 方法，用于测试
    public static void main(String[] args) {
        // 创建 Solution 实例
        Solution solution = new LeetCode_1583_CountUnhappyFriends().new Solution();
        // 添加测试用例 1
        int n1 = 4;
        int[][] preferences1 = {
                {1,2,3},  // 对于 0，优先级顺序是 1 > 2 > 3
                {3,2,0},  // 对于 1
                {3,1,0},  // 对于 2
                {1,2,0}   // 对于 3
        };
        int[][] pairs1 = {
                {0,1},
                {2,3}
        };
        // 预期输出：2
        System.out.println("Test Case 1: " + solution.unhappyFriends(n1, preferences1, pairs1));

//        // 添加测试用例 2
//        int n2 = 4;
//        int[][] preferences2 = {
//                {1,3,2},
//                {2,3,0},
//                {1,3,0},
//                {0,1,2}
//        };
//        int[][] pairs2 = {
//                {2,1},
//                {3,0}
//        };
//        // 预期输出：0
//        System.out.println("Test Case 2: " + solution.unhappyFriends(n2, preferences2, pairs2));
    }
}

/**
You are given a list of preferences for n friends, where n is always even. 

 For each person i, preferences[i] contains a list of friends sorted in the 
order of preference. In other words, a friend earlier in the list is more preferred 
than a friend later in the list. Friends in each list are denoted by integers 
from 0 to n-1. 

 All the friends are divided into pairs. The pairings are given in a list pairs,
 where pairs[i] = [xi, yi] denotes xi is paired with yi and yi is paired with 
xi. 

 However, this pairing may cause some of the friends to be unhappy. A friend x 
is unhappy if x is paired with y and there exists a friend u who is paired with 
v but: 

 
 x prefers u over y, and 
 u prefers x over v. 
 

 Return the number of unhappy friends. 

 
 Example 1: 

 
Input: n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs 
= [[0, 1], [2, 3]]
Output: 2
Explanation:
Friend 1 is unhappy because:
- 1 is paired with 0 but prefers 3 over 0, and
- 3 prefers 1 over 2.
Friend 3 is unhappy because:
- 3 is paired with 2 but prefers 1 over 2, and
- 1 prefers 3 over 0.
Friends 0 and 2 are happy.
 

 Example 2: 

 
Input: n = 2, preferences = [[1], [0]], pairs = [[1, 0]]
Output: 0
Explanation: Both friends 0 and 1 are happy.
 

 Example 3: 

 
Input: n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs 
= [[1, 3], [0, 2]]
Output: 4
 

 
 Constraints: 

 
 2 <= n <= 500 
 n is even. 
 preferences.length == n 
 preferences[i].length == n - 1 
 0 <= preferences[i][j] <= n - 1 
 preferences[i] does not contain i. 
 All values in preferences[i] are unique. 
 pairs.length == n/2 
 pairs[i].length == 2 
 xi != yi 
 0 <= xi, yi <= n - 1 
 Each person is contained in exactly one pair. 
 

 Related Topics Array Simulation 👍 288 👎 868

*/
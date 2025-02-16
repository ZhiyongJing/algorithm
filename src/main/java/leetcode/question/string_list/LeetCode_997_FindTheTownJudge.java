package leetcode.question.string_list;

/**
 * @Question: 997. Find the Town Judge（寻找小镇法官）
 * @Difficulty: 1 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 39.06%
 * @Time Complexity: O(E)  (N 是人数，E 是信任关系的个数)
 * @Space Complexity: O(N)
 */
/**
 * 题目描述：
 * --------------------------
 * LeetCode 997 - Find the Town Judge（寻找小镇法官）
 *
 * 在一个小镇上，有 `N` 个人，编号从 `1` 到 `N`。
 * 该镇有一个法官，法官具有以下两个特点：
 * 1. **法官不信任任何人**。
 * 2. **所有人（除了法官自己）都信任法官**。
 *
 * 给定一个 `trust` 数组，其中 `trust[i] = [a, b]` 代表 `a` 个人信任 `b` 个人。
 * 如果存在小镇法官，返回法官的编号；如果不存在，返回 `-1`。
 *
 * **示例 1**
 * ```
 * 输入: N = 3, trust = [[1,3], [2,3]]
 * 输出: 3
 * ```
 *
 * **示例 2**
 * ```
 * 输入: N = 3, trust = [[1,3], [2,3], [3,1]]
 * 输出: -1
 * ```
 *
 * **示例 3**
 * ```
 * 输入: N = 1, trust = []
 * 输出: 1
 * ```
 *
 *
 * 解题思路：
 * --------------------------
 * 该问题可以用 **计数法** 解决，利用一个 `trustScores` 数组记录每个人的信任情况：
 *
 * 1. **定义 `trustScores[N+1]` 数组**：
 *    - 如果 `a` 信任 `b`：
 *      - `trustScores[a]--`（`a` 不可能是法官）
 *      - `trustScores[b]++`（`b` 可能是法官）
 *
 * 2. **遍历 `trustScores[]` 数组，寻找信任分数等于 `N-1` 的人**：
 *    - **信任分数 = `N-1`**：说明这个人被所有 `N-1` 个人信任，且没有信任他人，符合法官条件。
 *    - 如果找不到满足条件的人，则返回 `-1`。
 *
 * **举例分析**
 * --------------------------
 * **示例 1**
 * ```
 * N = 3, trust = [[1,3], [2,3]]
 * ```
 * |  人  | 被信任情况 | 信任情况 |  信任分数 |
 * |-----|---------|---------|---------|
 * | 1   | 0 次    | 1 次    | -1      |
 * | 2   | 0 次    | 1 次    | -1      |
 * | 3   | 2 次    | 0 次    | **2** ✅ |
 *
 * 结论：法官是 `3`。
 *
 * **示例 2**
 * ```
 * N = 3, trust = [[1,3], [2,3], [3,1]]
 * ```
 * |  人  | 被信任情况 | 信任情况 |  信任分数 |
 * |-----|---------|---------|---------|
 * | 1   | 1 次    | 1 次    | 0       |
 * | 2   | 0 次    | 1 次    | -1      |
 * | 3   | 1 次    | 1 次    | 0       |
 *
 * 结论：`3` 也信任了 `1`，不符合法官条件，返回 `-1`。
 *
 * **示例 3**
 * ```
 * N = 1, trust = []
 * ```
 * - 只有 1 个人，没有信任关系，默认他就是法官，返回 `1`。
 *
 *
 * 时间和空间复杂度分析：
 * ---------------------------------
 * **时间复杂度**
 * - 遍历 `trust` 数组计算信任分数：O(E)
 * - 遍历 `trustScores` 找到可能的法官：O(N)
 * - **总时间复杂度：O(N + E)**
 *
 * **空间复杂度**
 * - `trustScores[]` 数组需要 O(N) 额外空间。
 * - **总空间复杂度：O(N)**。
 */

public class LeetCode_997_FindTheTownJudge {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        public int findJudge(int N, int[][] trust) {

            // 如果信任关系数少于 N-1，说明不可能有法官（因为法官被所有人信任）
            if (trust.length < N - 1) {
                return -1;
            }

            // 定义一个数组 trustScores 记录每个人的信任分数
            int[] trustScores = new int[N + 1]; // 1-based index

            // 遍历信任关系 trust 数组
            for (int[] relation : trust) {
                // relation[0] 是信任他人的人，他不可能是法官，减少他的信任分数
                trustScores[relation[0]]--;

                // relation[1] 是被信任的人，他可能是法官，增加他的信任分数
                trustScores[relation[1]]++;
            }

            // 找到信任分数为 N-1 的人，即所有人信任他但他不信任任何人
            for (int i = 1; i <= N; i++) {
                if (trustScores[i] == N - 1) {
                    return i;
                }
            }

            // 如果没有找到满足条件的人，返回 -1
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_997_FindTheTownJudge.Solution solution = new LeetCode_997_FindTheTownJudge().new Solution();

        // 测试用例 1：有法官
        int N1 = 3;
        int[][] trust1 = {{1, 3}, {2, 3}};
        System.out.println(solution.findJudge(N1, trust1)); // 输出 3

        // 测试用例 2：没有法官（因为法官应该被所有人信任）
        int N2 = 3;
        int[][] trust2 = {{1, 3}, {2, 3}, {3, 1}};
        System.out.println(solution.findJudge(N2, trust2)); // 输出 -1

        // 测试用例 3：只有一个人，且没有信任关系，该人就是法官
        int N3 = 1;
        int[][] trust3 = {};
        System.out.println(solution.findJudge(N3, trust3)); // 输出 1

        // 测试用例 4：法官存在
        int N4 = 4;
        int[][] trust4 = {{1, 4}, {2, 4}, {3, 4}};
        System.out.println(solution.findJudge(N4, trust4)); // 输出 4
    }
}
/**
In a town, there are n people labeled from 1 to n. There is a rumor that one of 
these people is secretly the town judge. 

 If the town judge exists, then: 

 
 The town judge trusts nobody. 
 Everybody (except for the town judge) trusts the town judge. 
 There is exactly one person that satisfies properties 1 and 2. 
 

 You are given an array trust where trust[i] = [ai, bi] representing that the 
person labeled ai trusts the person labeled bi. If a trust relationship does not 
exist in trust array, then such a trust relationship does not exist. 

 Return the label of the town judge if the town judge exists and can be 
identified, or return -1 otherwise. 

 
 Example 1: 

 
Input: n = 2, trust = [[1,2]]
Output: 2
 

 Example 2: 

 
Input: n = 3, trust = [[1,3],[2,3]]
Output: 3
 

 Example 3: 

 
Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1
 

 
 Constraints: 

 
 1 <= n <= 1000 
 0 <= trust.length <= 10⁴ 
 trust[i].length == 2 
 All the pairs of trust are unique. 
 ai != bi 
 1 <= ai, bi <= n 
 

 Related Topics Array Hash Table Graph 👍 6725 👎 610

*/
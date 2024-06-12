package leetcode.question.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 *@Question:  1125. Smallest Sufficient Team
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 23.61%
 *@Time  Complexity: O(N*2^M) let n be the number of people and m be the number of required skills.
 *@Space Complexity: O(2^M)
 */

/**
 * 这道题目是要求找出组成指定技能的最小团队，每个团队成员具有一定的技能。题目提供了两种解决方案：一种是自底向上的动态规划，另一种是自顶向下的动态规划。
 *
 * 解题思路：
 *
 * 1. **自底向上的动态规划解决方案**：
 *    - 首先，将技能与索引建立映射，方便后续的处理。
 *    - 然后，对于每个人，计算其掌握的技能掩码，即用一个整数表示这个人具有哪些技能。
 *    - 创建一个动态规划数组 dp，dp[i] 表示达到技能掩码 i 所需的最小团队成员的掩码。
 *    - 初始化 dp 数组为最大值。
 *    - 从技能掩码为 0 开始，逐步计算
 *    达到所有技能的最小团队成员掩码。
 *    - 对于每个技能掩码，遍历所有人员，如果某人的技能可以满足当前技能掩码的要求，则更新 dp 数组。
 *    - 最终得到达到所有技能所需的最小团队成员掩码，然后根据这个掩码找出团队成员的索引。
 *
 * 2. **自顶向下的动态规划解决方案**：
 *    - 首先，同样将技能与索引建立映射。
 *    - 对于每个人，计算其掌握的技能掩码。
 *    - 创建一个动态规划数组 dp，dp[i] 表示达到技能掩码 i 所需的最小团队成员的掩码。
 *    - 使用递归函数 f 来计算每个技能掩码所需的最小团队成员掩码。
 *    - 递归函数 f 的逻辑是对于当前技能掩码，遍历所有人员，如果某人的技能可以满足当前技能掩码的要求，则继续递归计算剩余技能掩码所需的最小团队成员掩码。
 *    - 最终得到达到所有技能所需的最小团队成员掩码，然后根据这个掩码找出团队成员的索引。
 *
 * 时间复杂度分析：
 * - 自底向上的动态规划解决方案：
 *   - 需要遍历每个人，计算其技能掩码，时间复杂度为 O(N * M)，其中 N 是人数，M 是技能数。
 *   - 动态规划的状态转移需要遍历每个技能掩码，时间复杂度为 O(2^M * N)，其中 M 是技能数。
 *   - 总体时间复杂度为 O(N * M + 2^M * N)。
 * - 自顶向下的动态规划解决方案：
 *   - 递归计算每个技能掩码所需的最小团队成员掩码，时间复杂度为 O(2^M * N)，其中 M 是技能数。
 *   - 总体时间复杂度为 O(2^M * N)。
 *
 * 空间复杂度分析：
 * - 自底向上的动态规划解决方案：
 *   - 需要额外空间来存储技能与索引的映射，以及每个人的技能掩码，空间复杂度为 O(N + M)。
 * - 自顶向下的动态规划解决方案：
 *   - 需要额外空间来存储技能与索引的映射，以及每个人的技能掩码，以及动态规划数组 dp，空间复杂度为 O(N + M + 2^M)。
 */



public class LeetCode_1125_SmallestSufficientTeam{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解决方案1: 自底向上的动态规划（DP Bottom up）
        public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
            int n = people.size(), m = req_skills.length;
            // 将技能与索引建立映射
            HashMap<String, Integer> skillId = new HashMap<String, Integer>();
            for (int i = 0; i < m; i++) {
                skillId.put(req_skills[i], i);
            }
            // 记录每个人掌握的技能掩码
            int skillsMaskOfPerson[] = new int[n];
            for (int i = 0; i < n; i++) {
                for (String skill : people.get(i)) {
                    skillsMaskOfPerson[i] |= 1 << skillId.get(skill);
                }
            }
            // 动态规划数组，表示达到当前技能掩码所需的最小团队成员
            long dp[] = new long[1 << m];
            // 初始化 dp 数组，初始值为最大值
            Arrays.fill(dp, (1L << n) - 1);
            dp[0] = 0;
            // 状态转移，逐步更新 dp 数组
            for (int skillsMask = 1; skillsMask < (1 << m); skillsMask++) {
                for (int i = 0; i < n; i++) {
                    int smallerSkillsMask = skillsMask & ~skillsMaskOfPerson[i];
                    if (smallerSkillsMask != skillsMask) {
                        long peopleMask = dp[smallerSkillsMask] | (1L << i);
                        if (Long.bitCount(peopleMask) < Long.bitCount(dp[skillsMask])) {
                            dp[skillsMask] = peopleMask;
                        }
                    }
                }
            }
            // 获取最小团队成员的技能掩码
            long answerMask = dp[(1 << m) - 1];
            int ans[] = new int[Long.bitCount(answerMask)];
            int ptr = 0;
            // 根据技能掩码获取团队成员的索引
            for (int i = 0; i < n; i++) {
                if (((answerMask >> i) & 1) == 1) {
                    ans[ptr++] = i;
                }
            }
            return ans;
        }

        // 解决方案2: 自顶向下的动态规划（Top down）
        int n;
        int skillsMaskOfPerson[];
        long dp[];

        private Long f(int skillsMask) {
            if (skillsMask == 0) {
                return 0L;
            }
            if (dp[skillsMask] != -1) {
                return dp[skillsMask];
            }
            for (int i = 0; i < n; i++) {
                int smallerSkillsMask = skillsMask & ~skillsMaskOfPerson[i];
                if (smallerSkillsMask != skillsMask) {
                    long peopleMask = f(smallerSkillsMask) | (1L << i);
                    if (dp[skillsMask] == -1 || Long.bitCount(peopleMask) <
                            Long.bitCount(dp[skillsMask])) {
                        dp[skillsMask] = peopleMask;
                    }
                }
            }
            return dp[skillsMask];
        }

        public int[] smallestSufficientTeam2(String[] req_skills, List<List<String>> people) {
            n = people.size();
            int m = req_skills.length;
            // 将技能与索引建立映射
            HashMap<String, Integer> skillId = new HashMap<String, Integer>();
            for (int i = 0; i < m; i++) {
                skillId.put(req_skills[i], i);
            }
            // 记录每个人掌握的技能掩码
            skillsMaskOfPerson = new int[n];
            for (int i = 0; i < n; i++) {
                for (String skill : people.get(i)) {
                    skillsMaskOfPerson[i] |= 1 << skillId.get(skill);
                }
            }
            // 动态规划数组，表示达到当前技能掩码所需的最小团队成员
            dp = new long[1 << m];
            // 初始化 dp 数组，初始值为 -1
            Arrays.fill(dp, -1);
            // 获取最小团队成员的技能掩码
            long answerMask = f((1 << m) - 1);
            int ans[] = new int[Long.bitCount(answerMask)];
            int ptr = 0;
            // 根据技能掩码获取团队成员的索引
            for (int i = 0; i < n; i++) {
                if (((answerMask >> i) & 1) == 1) {
                    ans[ptr++] = i;
                }
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new Solution();
        // 测试用例
        String[] req_skills = {"java", "python", "javascript"};
        List<List<String>> people = new ArrayList<>();
        people.add(Arrays.asList("java", "python"));
        people.add(Arrays.asList("java", "javascript"));
        people.add(Arrays.asList("java"));
        int[] result = solution.smallestSufficientTeam(req_skills, people);
        // 打印结果
        System.out.println("Smallest Sufficient Team: ");
        for (int person : result) {
            System.out.println(person);
        }
    }
}

/**
 In a project, you have a list of required skills req_skills, and a list of
 people. The iᵗʰ person people[i] contains a list of skills that the person has.

 Consider a sufficient team: a set of people such that for every required skill
 in req_skills, there is at least one person in the team who has that skill. We
 can represent these teams by the index of each person.


 For example, team = [0, 1, 3] represents the people with skills people[0],
 people[1], and people[3].


 Return any sufficient team of the smallest possible size, represented by the
 index of each person. You may return the answer in any order.

 It is guaranteed an answer exists.


 Example 1:
 Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],
 ["nodejs","reactjs"]]
 Output: [0,2]

 Example 2:
 Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"],
 people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java",
 "csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 Output: [1,2]


 Constraints:


 1 <= req_skills.length <= 16
 1 <= req_skills[i].length <= 16
 req_skills[i] consists of lowercase English letters.
 All the strings of req_skills are unique.
 1 <= people.length <= 60
 0 <= people[i].length <= 16
 1 <= people[i][j].length <= 16
 people[i][j] consists of lowercase English letters.
 All the strings of people[i] are unique.
 Every skill in people[i] is a skill in req_skills.
 It is guaranteed a sufficient team exists.


 Related Topics Array Dynamic Programming Bit Manipulation Bitmask 👍 2156 👎 55


 */
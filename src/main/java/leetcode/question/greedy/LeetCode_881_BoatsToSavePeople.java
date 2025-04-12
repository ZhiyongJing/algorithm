package leetcode.question.greedy;

import java.util.Arrays;

/**
 *@Question:  881. Boats to Save People
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 60.84%
 *@Time  Complexity: O(N * logN) n is length of people
 *@Space Complexity: O(logN) java sort
 */
/**
 * ===============================================
 * LeetCode 881. Boats to Save People
 * ===============================================
 *
 * 【一、题目描述】
 * 给定一个整数数组 people，其中 people[i] 表示第 i 个人的体重，
 * 以及一个整数 limit，表示每艘救生艇的最大载重。
 *
 * 每艘船最多只能载两人，且两人的体重总和不能超过 limit。
 * 返回将所有人救上船所需的最小船只数量。
 *
 * 示例：
 * 输入：people = [1, 2], limit = 3
 * 输出：1 （两人可共乘一艘船）
 *
 * 输入：people = [3, 2, 2, 1], limit = 3
 * 输出：3 （(1+2), (2), (3)）
 *
 * 输入：people = [3, 5, 3, 4], limit = 5
 * 输出：4 （每人单独乘船）
 *
 *
 * 【二、解题思路（贪心 + 双指针策略）】
 * 本题核心策略是贪心法：
 * - 目标是**用尽可能少的船救最多的人**；
 * - 尝试将最轻的人和最重的人配对搭船；
 * - 如果配对成功（两人和 ≤ limit），就两人一起坐；
 * - 如果配对失败（两人和 > limit），最重的那个人必须单独坐船。
 *
 * 详细步骤：
 * 1）先对数组 people 进行升序排序；
 * 2）设置两个指针：i 指向最轻的人（头部），j 指向最重的人（尾部）；
 * 3）初始化船数 ans = 0；
 * 4）循环直到 i > j：
 *    - 每次安排一个人坐船（ans++）；
 *    - 如果 people[i] + people[j] ≤ limit，说明可以两人一艘，i++，j--；
 *    - 否则只能 j--（最重者独自乘船）；
 * 5）最终返回船的总数。
 *
 * 示例解释：
 * 输入：people = [3, 2, 2, 1], limit = 3
 * 排序后：people = [1, 2, 2, 3]
 * i = 0, j = 3:
 *   - people[0] + people[3] = 1 + 3 > 3 → 3 单独坐 → j--
 * i = 0, j = 2:
 *   - people[0] + people[2] = 1 + 2 = 3 → 搭配成功 → i++, j--
 * i = 1, j = 1:
 *   - people[1] 单独坐 → j--
 * 总共用了 3 艘船
 *
 *
 * 【三、时间与空间复杂度】
 *
 * 时间复杂度：O(N * logN)
 * - 主要花在排序上，排序复杂度为 O(N logN)
 * - 指针移动是线性 O(N)
 *
 * 空间复杂度：O(logN)
 * - Java 排序所用的额外栈空间为 O(logN)
 * - 使用常数变量（i, j, ans），不需要额外数组
 */

public class LeetCode_881_BoatsToSavePeople{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            // 对所有人的体重进行排序（升序）
            Arrays.sort(people);

            // i 指向最轻的人，j 指向最重的人
            int i = 0, j = people.length - 1;

            // 记录所需船只数量
            int ans = 0;

            // 当还有人没有上船时继续执行
            while (i <= j) {
                // 每次至少使用一条船
                ans++;

                // 如果当前最轻和最重的人可以共用一条船
                if (people[i] + people[j] <= limit)
                    i++; // 最轻的人也被带走

                // 最重的人无论如何都会上船
                j--;
            }

            // 返回最少需要的船数
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_881_BoatsToSavePeople().new Solution();

        // 测试用例 1：基础测试
        int[] people1 = {1, 2};
        int limit1 = 3;
        // 预期输出：1 （两人可共乘一艘船）
        System.out.println("测试用例1: " + solution.numRescueBoats(people1, limit1));

        // 测试用例 2：多人不同重量
        int[] people2 = {3, 2, 2, 1};
        int limit2 = 3;
        // 预期输出：3 （1+2，2单独，3单独）
        System.out.println("测试用例2: " + solution.numRescueBoats(people2, limit2));

        // 测试用例 3：每人都必须单独一艘船
        int[] people3 = {3, 5, 3, 4};
        int limit3 = 5;
        // 预期输出：4
        System.out.println("测试用例3: " + solution.numRescueBoats(people3, limit3));

        // 测试用例 4：所有人刚好配对
        int[] people4 = {2, 2, 2, 2};
        int limit4 = 4;
        // 预期输出：2
        System.out.println("测试用例4: " + solution.numRescueBoats(people4, limit4));

        // 测试用例 5：极端情况
        int[] people5 = {5, 1, 4, 2};
        int limit5 = 6;
        // 预期输出：2 （1+5，2+4）
        System.out.println("测试用例5: " + solution.numRescueBoats(people5, limit5));
    }
}

/**
You are given an array people where people[i] is the weight of the iᵗʰ person, 
and an infinite number of boats where each boat can carry a maximum weight of 
limit. Each boat carries at most two people at the same time, provided the sum of 
the weight of those people is at most limit. 

 Return the minimum number of boats to carry every given person. 

 
 Example 1: 

 
Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
 

 Example 2: 

 
Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)
 

 Example 3: 

 
Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)
 

 
 Constraints: 

 
 1 <= people.length <= 5 * 10⁴ 
 1 <= people[i] <= limit <= 3 * 10⁴ 
 

 Related Topics Array Two Pointers Greedy Sorting 👍 6590 👎 165

*/
package leetcode.question.greedy;

import java.util.Arrays;

/**
 *@Question:  455. Assign Cookies
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 45.4%
 *@Time  Complexity: O(n * logN + m * logm)
 *@Space Complexity: O(logn + logm) for java sorting
 */
/**
 * ===============================================
 * LeetCode 455. Assign Cookies
 * ===============================================
 *
 * 【一、题目描述】
 * 每个孩子都有一个胃口值 g[i]，每块饼干都有一个大小值 s[j]。
 * 如果 s[j] >= g[i]，我们就可以将这块饼干分配给这个孩子。
 *
 * 每个孩子最多只能获得一块饼干，每块饼干也只能被分配一次。
 * 目标是尽可能让更多的孩子满足，返回最多能满足多少个孩子。
 *
 * 示例：
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1（只有第一个孩子能满足）
 *
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2（两个孩子都能满足）
 *
 *
 * 【二、解题思路（贪心 + 排序，含详细举例）】
 * 思路核心：**优先用最小的饼干满足胃口最小的孩子**，这样可以为大胃口孩子保留更大饼干。
 *
 * 步骤如下：
 * 1）将 g（孩子胃口数组）升序排序；
 * 2）将 s（饼干大小数组）升序排序；
 * 3）使用两个指针分别遍历孩子和饼干：
 *     - 如果当前饼干可以满足当前孩子（s[j] >= g[i]），则：
 *         - 孩子 i 得到饼干 j，两个指针都后移；
 *     - 否则：
 *         - 当前饼干太小，尝试下一块更大的饼干（j++）；
 * 4）当任一数组走完时，结束循环；
 * 5）记录满足的孩子数并返回。
 *
 * 示例解释：
 * 示例 1：
 * g = [1,2,3], s = [1,1]
 * → 排序后 g = [1,2,3], s = [1,1]
 * → 第一块饼干 1 满足第一个孩子（胃口为1），contentChildren = 1
 * → 第二块饼干 1 无法满足第二个孩子（胃口为2），跳过
 * → 结果为 1
 *
 * 示例 2：
 * g = [1,2], s = [1,2,3]
 * → 排序后 g = [1,2], s = [1,2,3]
 * → 第一块饼干 1 满足第一个孩子，contentChildren = 1
 * → 第二块饼干 2 满足第二个孩子，contentChildren = 2
 * → 结果为 2
 *
 *
 * 【三、时间和空间复杂度】
 * 时间复杂度：O(n log n + m log m)
 * - 主要耗时在排序阶段，n = g.length，m = s.length
 * - 遍历两个数组最多 O(n + m)
 *
 * 空间复杂度：O(log n + log m)
 * - 排序使用 Java 内置排序算法所需的栈空间
 * - 若使用原地排序和双指针，额外空间为常数级别
 */


public class LeetCode_455_AssignCookies{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findContentChildren(int[] g, int[] s) {
            // 对孩子的胃口数组 g 进行升序排序
            Arrays.sort(g);
            // 对饼干大小数组 s 进行升序排序
            Arrays.sort(s);

            // contentChildren 表示当前能满足的孩子数量
            int contentChildren = 0;
            // cookieIndex 表示当前尝试分配的饼干下标
            int cookieIndex = 0;

            // 当还有饼干并且还有未满足的孩子时继续循环
            while (cookieIndex < s.length && contentChildren < g.length) {
                // 如果当前饼干大小 >= 当前孩子的胃口，说明可以满足
                if (s[cookieIndex] >= g[contentChildren]) {
                    // 满足一个孩子
                    contentChildren++;
                }
                // 尝试下一个饼干
                cookieIndex++;
            }

            // 返回最多能满足多少个孩子
            return contentChildren;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_455_AssignCookies().new Solution();

        // 测试用例 1：标准情况
        int[] g1 = {1, 2, 3};
        int[] s1 = {1, 1};
        // 预期输出：1（只能满足第一个孩子）
        System.out.println("满足的孩子数量: " + solution.findContentChildren(g1, s1));

        // 测试用例 2：每个孩子都能分到一块饼干
        int[] g2 = {1, 2};
        int[] s2 = {1, 2, 3};
        // 预期输出：2
        System.out.println("满足的孩子数量: " + solution.findContentChildren(g2, s2));

        // 测试用例 3：没有孩子
        int[] g3 = {};
        int[] s3 = {1, 2};
        // 预期输出：0
        System.out.println("满足的孩子数量: " + solution.findContentChildren(g3, s3));

        // 测试用例 4：没有饼干
        int[] g4 = {1, 2, 3};
        int[] s4 = {};
        // 预期输出：0
        System.out.println("满足的孩子数量: " + solution.findContentChildren(g4, s4));

        // 测试用例 5：全部正好匹配
        int[] g5 = {1, 2, 3};
        int[] s5 = {1, 2, 3};
        // 预期输出：3
        System.out.println("满足的孩子数量: " + solution.findContentChildren(g5, s5));
    }
}

/**
Assume you are an awesome parent and want to give your children some cookies. 
But, you should give each child at most one cookie. 

 Each child i has a greed factor g[i], which is the minimum size of a cookie 
that the child will be content with; and each cookie j has a size s[j]. If s[j] >= 
g[i], we can assign the cookie j to the child i, and the child i will be 
content. Your goal is to maximize the number of your content children and output the 
maximum number. 

 
 Example 1: 

 
Input: g = [1,2,3], s = [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children 
are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only 
make the child whose greed factor is 1 content.
You need to output 1.
 

 Example 2: 

 
Input: g = [1,2], s = [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children 
are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the 
children, 
You need to output 2.
 

 
 Constraints: 

 
 1 <= g.length <= 3 * 10⁴ 
 0 <= s.length <= 3 * 10⁴ 
 1 <= g[i], s[j] <= 2³¹ - 1 
 

 
 Note: This question is the same as 2410: Maximum Matching of Players With 
Trainers. 

 Related Topics Array Two Pointers Greedy Sorting 👍 4350 👎 405

*/
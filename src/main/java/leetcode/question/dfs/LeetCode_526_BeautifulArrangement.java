package leetcode.question.dfs;

/**
 *@Question:  526. Beautiful Arrangement
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.92000000000001%
 *@Time  Complexity: O(2^n)  // 由于存在指数级递归调用，时间复杂度为指数级
 *@Space Complexity: O(n)    // 递归调用栈的深度最差情况下为n
 */

public class LeetCode_526_BeautifulArrangement{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        int count = 0;

        // 计算美丽的排列数
        public int countArrangement(int N) {
            boolean[] visited = new boolean[N + 1];
            calculate(N, 1, visited);
            return count;
        }

        // 递归计算排列
        public void calculate(int N, int pos, boolean[] visited) {
            if (pos > N)
                count++;

            for (int i = 1; i <= N; i++) {
                // 如果 i 未被访问过，且满足美丽的排列条件
                if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                    visited[i] = true;  // 将 i 标记为已访问
                    calculate(N, pos + 1, visited);  // 递归计算下一个位置
                    visited[i] = false;  // 回溯，将 i 标记为未访问
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_526_BeautifulArrangement.Solution solution = new LeetCode_526_BeautifulArrangement().new Solution();

        // 简单测试
        System.out.println(solution.countArrangement(2));  // 输出：2
        System.out.println(solution.countArrangement(1));  // 输出：1
    }
}
/**
 Suppose you have n integers labeled 1 through n. A permutation of those n
 integers perm (1-indexed) is considered a beautiful arrangement if for every i (1 <=
 i <= n), either of the following is true:


 perm[i] is divisible by i.
 i is divisible by perm[i].


 Given an integer n, return the number of the beautiful arrangements that you
 can construct.


 Example 1:


 Input: n = 2
 Output: 2
 Explanation:
 The first beautiful arrangement is [1,2]:
 - perm[1] = 1 is divisible by i = 1
 - perm[2] = 2 is divisible by i = 2
 The second beautiful arrangement is [2,1]:
 - perm[1] = 2 is divisible by i = 1
 - i = 2 is divisible by perm[2] = 1


 Example 2:


 Input: n = 1
 Output: 1



 Constraints:


 1 <= n <= 15


 Related Topics Array Dynamic Programming Backtracking Bit Manipulation Bitmask
 👍 3114 👎 354

*/
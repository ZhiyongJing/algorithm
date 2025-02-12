// package 语句，声明当前类所在的包
package leetcode.question.dfs;
// 导入 Java 中用到的工具类

/**
 *@Question:  1823. Find the Winner of the Circular Game
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 66.99%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/*
 * 一、题目描述
 *    给定 n 名玩家围成一圈，每次从玩家 1 开始数 k 个数，被数到的玩家会被淘汰。
 *    然后从下一个玩家继续数 k 个数，并重复此过程，直至仅剩一名玩家。
 *    需要返回最后存活玩家的编号（1-based）。
 *
 * 二、基于给出的代码，超级详细解题思路
 *    1. 通过一个递归函数来模拟约瑟夫环：
 *       - winnerHelper(n, k) 返回在 0-based 编号下的获胜者位置。
 *       - 当只剩下 1 人 (n == 1) 时，获胜者必为 0。
 *       - 当有 n 人时，可以将问题转化为 n-1 人的获胜位置，
 *         然后加上 k 再对 n 取模，得到新的获胜者位置。
 *    2. 在主函数 findTheWinner 中调用 winnerHelper(n, k)，并将结果 +1，
 *       以得到最终 1-based 编号的胜利者。
 *    3. 举例说明：
 *       - 若 n=5, k=2，则先求 4 人局面的胜利者，然后加上 k (2) 对 5 取模。
 *         递归一直进行到 n=1 时，返回 0，逆向推导可得最终结果为 2 (0-based)，
 *         再 +1 等于 3 (1-based)。
 *
 * 三、时间和空间复杂度
 *    1. 时间复杂度：O(N)
 *       - 递归会调用 n 次，每次计算过程为 O(1)。
 *    2. 空间复杂度：O(N)
 *       - 由于采用递归形式，调用栈深度可达 n。
 */


// 定义一个公共类，名称为 LeetCode_1823_FindTheWinnerOfTheCircularGame
public class LeetCode_1823_FindTheWinnerOfTheCircularGame{

    // leetcode 提交区域开始（不可修改）
//leetcode submit region begin(Prohibit modification and deletion)
// 定义一个内部类 Solution
    class Solution {

        // 定义方法 findTheWinner，参数 n 表示玩家数，k 表示数到 k 被淘汰
        public int findTheWinner(int n, int k) {
            // 调用递归函数 winnerHelper，结果再加 1
            return winnerHelper(n, k) + 1;
        }

        // 定义私有方法 winnerHelper，返回最终获胜者的 0-based 索引
        private int winnerHelper(int n, int k) {
            // 如果只剩 1 人，则返回 0（表示在 0-based 中的索引）
            if (n == 1) {
                return 0;
            }
            // 递归调用：在 n-1 个玩家的情况下求解，再加上 k，并对 n 取模
            return (winnerHelper(n - 1, k) + k) % n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    // main 方法，用于手动测试上述代码
    public static void main(String[] args) {
        // 创建 Solution 实例
        Solution solution = new LeetCode_1823_FindTheWinnerOfTheCircularGame().new Solution();
        // TO TEST: 可以在此处调用 solution.findTheWinner 进行测试
        // 添加测试用例 1
        int n1 = 5;
        int k1 = 2;
        // 预期输出：3，表示第 3 号玩家获胜
        System.out.println("Test Case 1: " + solution.findTheWinner(n1, k1));

        // 添加测试用例 2
        int n2 = 6;
        int k2 = 5;
        // 预期输出：1，表示第 1 号玩家获胜
        System.out.println("Test Case 2: " + solution.findTheWinner(n2, k2));

        // 添加测试用例 3
        int n3 = 1;
        int k3 = 1;
        // 预期输出：1，因为只有一个玩家
        System.out.println("Test Case 3: " + solution.findTheWinner(n3, k3));
    }
}

/**
There are n friends that are playing a game. The friends are sitting in a 
circle and are numbered from 1 to n in clockwise order. More formally, moving 
clockwise from the iᵗʰ friend brings you to the (i+1)ᵗʰ friend for 1 <= i < n, and 
moving clockwise from the nᵗʰ friend brings you to the 1ˢᵗ friend. 

 The rules of the game are as follows: 

 
 Start at the 1ˢᵗ friend. 
 Count the next k friends in the clockwise direction including the friend you 
started at. The counting wraps around the circle and may count some friends more 
than once. 
 The last friend you counted leaves the circle and loses the game. 
 If there is still more than one friend in the circle, go back to step 2 
starting from the friend immediately clockwise of the friend who just lost and repeat. 

 Else, the last friend in the circle wins the game. 
 

 Given the number of friends, n, and an integer k, return the winner of the 
game. 

 
 Example 1: 
 
 
Input: n = 5, k = 2
Output: 3
Explanation: Here are the steps of the game:
1) Start at friend 1.
2) Count 2 friends clockwise, which are friends 1 and 2.
3) Friend 2 leaves the circle. Next start is friend 3.
4) Count 2 friends clockwise, which are friends 3 and 4.
5) Friend 4 leaves the circle. Next start is friend 5.
6) Count 2 friends clockwise, which are friends 5 and 1.
7) Friend 1 leaves the circle. Next start is friend 3.
8) Count 2 friends clockwise, which are friends 3 and 5.
9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner. 

 Example 2: 

 
Input: n = 6, k = 5
Output: 1
Explanation: The friends leave in this order: 5, 4, 6, 2, 3. The winner is 
friend 1.
 

 
 Constraints: 

 
 1 <= k <= n <= 500 
 

 
 Follow up: 

 Could you solve this problem in linear time with constant space? 

 Related Topics Array Math Recursion Queue Simulation 👍 3884 👎 114

*/
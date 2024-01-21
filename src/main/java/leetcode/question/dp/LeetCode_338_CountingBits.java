package leetcode.question.dp;

/**
  *@Question:  338. Counting Bits     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 50.96000000000001%      
  *@Time  Complexity: O(NlogN) for solution1, O(N) for solution 2
  *@Space Complexity: O(1)
 */

/**
 * 这是一个关于计算比特位的问题，题目要求计算从0到n范围内每个数字的二进制表示中包含的1的个数。给出了两种解法，下面分别详细讲解：
 *
 * ### Solution 1:
 *
 * 1. **popCount函数：** 该函数用于计算一个数的二进制表示中包含的1的个数。通过不断将最右边的非零位变为零，统计循环的次数即可。
 * 2. **countBits1函数：** 从0到n遍历每个数字，调用popCount函数计算每个数字的二进制表示中包含的1的个数，
 * 将结果存储在数组中返回。
 *
 * ### Solution 2:
 *
 * 1. **countBits函数：** 使用动态规划的思想，计算从0到n范围内每个数字的二进制表示中包含的1的个数。
 * 2. 初始化一个数组`ans`，其中`ans[i]`表示数字`i`的二进制表示中包含的1的个数。
 * 3. 使用两个变量`x`和`b`，`x`用于迭代从0到`b`的范围，`b`用于表示当前区间的结束点，初始时`b=1`。
 * 4. 通过迭代，不断生成新的区间`[b, 2b)`或者`[b, n)`，并从旧区间`[0, b)`计算得到新区间的结果。每个新区间的计算都是在旧区间的基础上增加一个1，因此`ans[x + b] = ans[x] + 1`。
 * 5. 当生成的新区间超过`n`时，结束循环。
 * 6. 时间复杂度为O(N)，其中N为输入的整数n。
 *
 * ### 时间复杂度：
 *
 * - Solution 1: O(NlogN)，其中N为输入的整数n。遍历每个数字，popCount函数的时间复杂度为O(logN)。
 * - Solution 2: O(N)，其中N为输入的整数n。动态规划的过程只需遍历每个数字一次。
 *
 * ### 空间复杂度：
 *
 * - Solution 1: O(1)，常数级别的额外空间，只需要存储一个计数变量。
 * - Solution 2: O(N)，动态规划过程中使用了数组`ans`，大小为n+1。
 */


public class LeetCode_338_CountingBits {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        //Solution 1:
        /**
         * 计算一个数的二进制表示中包含的1的个数
         * @param x 输入的整数
         * @return 二进制表示中包含的1的个数
         */
        private int popCount(int x) {
            int count;
            for (count = 0; x != 0; ++count) {
                //这个操作常用于计算一个数的二进制表示中包含的 1 的个数，
                // 因为每执行一次 x &= x - 1，就会消去 x 的最右边的一个 1。通过不断执行这个操作，可以统计 1 的个数。
                x &= x - 1; // 将最右边的非零位变为零
                System.out.println(x);
            }
            return count;
        }

        /**
         * 计算从0到n范围内每个数字的二进制表示中包含的1的个数
         * @param n 输入的整数
         * @return 包含1的个数数组
         */
        public int[] countBits(int n) {
            int[] ans = new int[n + 1];
            for (int x = 0; x <= n; ++x) {
                ans[x] = popCount(x);
            }
            return ans;
        }

        //Solution 2: DP
        /**
         * 计算从0到n范围内每个数字的二进制表示中包含的1的个数
         * @param n 输入的整数
         * @return 包含1的个数数组
         */
        public int[] countBits2(int n) {
            int[] ans = new int[n + 1];
            int x = 0;
            int b = 1;

            // [0, b) is calculated
            while (b <= n) {
                // generate [b, 2b) or [b, n) from [0, b)
                while (x < b && x + b <= n) {
                    ans[x + b] = ans[x] + 1;
                    ++x;
                }
                x = 0; // 重置 x
                b <<= 1; // b = 2b
            }

            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_338_CountingBits lcr = new LeetCode_338_CountingBits();
        Solution solution = lcr.new Solution();

        // 测试代码
        // 示例1
        int n1 = 2;
        int[] result1 = solution.countBits(n1);
        // 预期输出: [0, 1, 1]
        printArray(result1);

        // 示例2
        int n2 = 5;
        int[] result2 = solution.countBits(n2);
        // 预期输出: [0, 1, 1, 2, 1, 2]
        printArray(result2);
    }

    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

/**
Given an integer n, return an array ans of length n + 1 such that for each i (0 
<= i <= n), ans[i] is the number of 1's in the binary representation of i. 

 
 Example 1: 

 
Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
 

 Example 2: 

 
Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
 

 
 Constraints: 

 
 0 <= n <= 10⁵ 
 

 
 Follow up: 

 
 It is very easy to come up with a solution with a runtime of O(n log n). Can 
you do it in linear time O(n) and possibly in a single pass? 
 Can you do it without using any built-in function (i.e., like __builtin_
popcount in C++)? 
 

 Related Topics Dynamic Programming Bit Manipulation 👍 10784 👎 501

*/
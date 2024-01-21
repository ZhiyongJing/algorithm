package leetcode.question.dp;

import java.util.Arrays;
import java.util.Comparator;
/**
 *@Question:  354. Russian Doll Envelopes
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 45.43%
 *@Time  Complexity: O(NlogN)
 *@Space Complexity: O(N)
 */
/**
 *
 */

public class LeetCode_354_RussianDollEnvelopes {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 定义求最长递增子序列的方法
        public int lengthOfLIS(int[] nums) {
            // dp 数组用于存储当前位置的最长递增子序列的长度
            int[] dp = new int[nums.length];
            int len = 0; // 记录最长递增子序列的长度

            // 遍历数组中的每个元素
            for (int num : nums) {
                // 使用二分查找找到当前元素在 dp 数组中的位置
                int i = Arrays.binarySearch(dp, 0, len, num);

                // 如果二分查找返回负值，取反得到插入位置
                if (i < 0) {
                    i = -(i + 1);
                }

                // 更新 dp 数组
                dp[i] = num;

                // 如果插入位置等于当前长度 len，说明当前元素加入后使得最长递增子序列长度增加
                if (i == len) {
                    len++;
                }
            }

            // 返回最长递增子序列的长度
            return len;
        }

        // 主方法，求解俄罗斯套娃信封问题
        public int maxEnvelopes(int[][] envelopes) {
            // 对信封数组进行排序，按照第一维度升序，第二维度降序
            Arrays.sort(envelopes, new Comparator<int[]>() {
                public int compare(int[] arr1, int[] arr2) {
                    if (arr1[0] == arr2[0]) {
                        return arr2[1] - arr1[1];
                    } else {
                        return arr1[0] - arr2[0];
                    }
                }
            });

            // 提取第二维度的数组，并运行最长递增子序列算法
            int[] secondDim = new int[envelopes.length];
            for (int i = 0; i < envelopes.length; ++i) secondDim[i] = envelopes[i][1];
            return lengthOfLIS(secondDim);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_354_RussianDollEnvelopes().new Solution();

        // 测试代码
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        int result = solution.maxEnvelopes(envelopes);
        System.out.println("最大套娃信封数量：" + result);
    }
}

/**
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] 
represents the width and the height of an envelope. 

 One envelope can fit into another if and only if both the width and height of 
one envelope are greater than the other envelope's width and height. 

 Return the maximum number of envelopes you can Russian doll (i.e., put one 
inside the other). 

 Note: You cannot rotate an envelope. 

 
 Example 1: 

 
Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] =>
 [5,4] => [6,7]).
 

 Example 2: 

 
Input: envelopes = [[1,1],[1,1],[1,1]]
Output: 1
 

 
 Constraints: 

 
 1 <= envelopes.length <= 10⁵ 
 envelopes[i].length == 2 
 1 <= wi, hi <= 10⁵ 
 

 Related Topics Array Binary Search Dynamic Programming Sorting 👍 5793 👎 140

*/
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
 * ### 题目概述
 *
 * 这道题是俄罗斯套娃信封问题，要求找出能够套娃的最大数量。信封有两个维度，分别为宽度和高度，一个信封 `(w1, h1)`
 * 可以套在另一个 `(w2, h2)` 里面当且仅当 `w1 < w2` 且 `h1 < h2`。
 * 本质上是一个最长递增子序列的变种。我们可以通过对信封数组进行排序，并在排序后的信封高度上寻找最长递增子序列来解决
 *
 * ### 解题思路
 *
 * 1. **排序：** 首先，对信封数组进行排序。排序的规则是按照信封的宽度升序排序，如果宽度相同则按照高度降序排序。
 * 这是为了确保在宽度相同的情况下，高度较大的信封排在前面，以便寻找最长递增子序列时能够正确选择信封。
 *
 * 2. **求解最长递增子序列：** 排序后，我们可以将问题转化为求解高度数组的最长递增子序列。为什么呢？因为在排序后的数组中，
 * 宽度已经满足递增的条件，我们只需要考虑高度即可。
 *
 * 3. **动态规划求解最长递增子序列：** 定义一个 `dp` 数组，`dp[i]` 表示以第 `i` 个信封结尾的最长递增子序列的长度。
 * 然后，使用动态规划的思想，遍历每个信封，更新 `dp` 数组。对于第 `i` 个信封，遍历其前面的每个信封 `j`，
 * 如果当前信封可以放入前面的信封中（即 `h[i] > h[j]`），则更新 `dp[i]` 为 `dp[j] + 1`。
 *
 * 4. **返回结果：** 最终，`dp` 数组中的最大值即为所求的最长递增子序列的长度，也就是能够套娃的最大数量。
 *
 * ### 时间复杂度
 *
 * - 排序的时间复杂度为 O(NlogN)，其中 N 是信封的数量。
 * - 求解最长递增子序列的时间复杂度为 O(NlogN)，其中 N 是信封的数量。
 *
 * 总体时间复杂度为 O(NlogN)。
 *
 * ### 空间复杂度
 *
 * - 动态规划数组 `dp` 的空间复杂度为 O(N)，其中 N 是信封的数量。
 *
 * 总体空间复杂度为 O(N)。
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
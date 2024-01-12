package leetcode.question.dfs;

import java.util.Arrays;
import java.util.HashMap;

/**
  *@Question:  698. Partition to K Equal Sum Subsets     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 56.35%      
  *@Time  Complexity: O(2^n * n)  // n 为数组长度
  *@Space Complexity: O(2^n * n) // 递归调用栈深度和记忆化存储的字符串个数
 */

/**
 * 这个问题是一个经典的NP问题，可以使用回溯法来解决。算法的基本思路是尝试将数组分成k个相等的子集，通过递归的方式在不同的选择中寻找一种满足条件的划分。
 * 具体步骤如下：
 *
 * 1. **判断是否能够分成k个相等的子集：**首先，算法计算数组的总和`totalArraySum`，如果`totalArraySum`不能被k整除，
 * 那么数组无法被划分为k个相等的子集，直接返回false。
 *
 * 2. **降序排序：**为了提高回溯的效率，将数组按照降序进行排序，这样可以更早地发现无法满足条件的情况。
 *
 * 3. **回溯法构建子集：**使用回溯法的思想，递归地尝试构建k个相等的子集。在回溯的过程中，每次选择一个元素加入当前的子集，
 * 并检查是否满足子集和为`targetSum`。如果满足，递归地构建下一个子集；如果不满足，回溯并尝试其他选择。递归的结束条件是已经构建了k-1个相等的子集。
 *
 * 4. **记忆化存储：**为了避免重复计算，使用一个HashMap（memo）来记录已经计算过的组合，提高算法效率。
 *
 * 下面是算法的时间和空间复杂度分析：
 *
 * - **时间复杂度：**由于算法使用了回溯法，其时间复杂度为指数级别的O(2^n)，其中n为数组的长度。
 * 回溯法的时间复杂度主要取决于问题的解空间树的大小，每个元素在选择和不选择两种情况下都需要递归调用，因此是指数级别的。
 *
 * - **空间复杂度：**算法使用了递归调用栈和HashMap（memo）来进行记忆化存储。递归调用栈的深度最差情况下为n，
 * HashMap用于存储2^n个子问题的解，因此空间复杂度也是O(2^n)。需要注意的是，实际使用的空间可能会比O(2^n)小，
 * 因为HashMap中不会存储所有可能的子问题解。
 *
 * 这个算法在实际应用中可能会面临指数级别的计算量，因此对于大规模输入可能不太适用。有时候需要考虑其他优化方法，例如动态规划或者使用启发式算法。
 */

public class LeetCode_698_PartitionToKEqualSumSubsets {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 回溯法，用于判断是否能将数组划分为 k 个相等的子集
         *
         * @param arr       输入的整数数组
         * @param index     当前处理的元素索引
         * @param count     已经构造的子集个数
         * @param currSum   当前子集的和
         * @param k         需要构造的子集个数
         * @param targetSum 目标子集的和
         * @param taken     记录数组中的元素是否已经被选中
         * @param memo      记忆化存储，记录已经计算过的情况
         * @return 是否能够划分为 k 个相等的子集
         */
        private boolean backtrack(int[] arr, int index, int count, int currSum, int k,
                                  int targetSum, char[] taken, HashMap<String, Boolean> memo) {

            int n = arr.length;

            // 已经构造了 k - 1 个和为 targetSum 的子集，剩下的子集也会和为 targetSum。
            if (count == k - 1) {
                return true;
            }

            // 当前子集的和超过了目标和，无法继续构造子集。
            if (currSum > targetSum) {
                return false;
            }

            String takenStr = new String(taken);

            // 如果已经计算过当前组合，直接返回结果。
            if (memo.containsKey(takenStr)) {
                return memo.get(takenStr);
            }

            // 当前子集和达到目标和，递归调用构造下一个子集。
            if (currSum == targetSum) {
                boolean ans = backtrack(arr, 0, count + 1, 0, k, targetSum, taken, memo);
                memo.put(takenStr, ans);
                return ans;
            }

            // 尝试不选取当前元素，构造其他可能的子集。
            for (int j = index; j < n; ++j) {
                if (taken[j] == '0') {
                    // 将当前元素加入当前子集。
                    taken[j] = '1';

                    // 如果当前选择的元素能够构造所有子集，则返回 true。
                    if (backtrack(arr, j + 1, count, currSum + arr[j], k, targetSum, taken, memo)) {
                        return true;
                    }

                    // 回溯步骤，撤销选择。
                    taken[j] = '0';
                }
            }

            // 无法构造出有效的组合，返回 false。
            memo.put(takenStr, false);
            return false;
        }

        /**
         * 将数组反转
         *
         * @param arr 输入的整数数组
         */
        void reverse(int[] arr) {
            for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        /**
         * 判断是否能将数组划分为 k 个相等的子集
         *
         * @param arr 输入的整数数组
         * @param k   需要划分的子集个数
         * @return 是否能够划分为 k 个相等的子集
         */
        public boolean canPartitionKSubsets(int[] arr, int k) {
            int totalArraySum = 0;
            int n = arr.length;

            for (int i = 0; i < n; ++i) {
                totalArraySum += arr[i];
            }

            // 如果数组总和不是 k 的倍数，无法划分。
            if (totalArraySum % k != 0) {
                return false;
            }

            // 将数组按照降序排序。
            Arrays.sort(arr);
            reverse(arr);

            int targetSum = totalArraySum / k;

            char[] taken = new char[n];
            Arrays.fill(taken, '0');

            // 记忆化存储，记录已经计算过的情况。
            HashMap<String, Boolean> memo = new HashMap<>();

            return backtrack(arr, 0, 0, 0, k, targetSum, taken, memo);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_698_PartitionToKEqualSumSubsets().new Solution();

        // 测试代码
        int[] nums1 = {4, 3, 2, 3, 5, 2, 1};
        int k1 = 4;
        System.out.println("Example 1: " + solution.canPartitionKSubsets(nums1, k1)); // 预期输出：true

        int[] nums2 = {1, 2, 3, 4};
        int k2 = 3;
        System.out.println("Example 2: " + solution.canPartitionKSubsets(nums2, k2)); // 预期输出：false
    }
}

/**
Given an integer array nums and an integer k, return true if it is possible to 
divide this array into k non-empty subsets whose sums are all equal. 

 
 Example 1: 

 
Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3
) with equal sums.
 

 Example 2: 

 
Input: nums = [1,2,3,4], k = 3
Output: false
 

 
 Constraints: 

 
 1 <= k <= nums.length <= 16 
 1 <= nums[i] <= 10⁴ 
 The frequency of each element is in the range [1, 4]. 
 

 Related Topics Array Dynamic Programming Backtracking Bit Manipulation 
Memoization Bitmask 👍 6926 👎 489

*/

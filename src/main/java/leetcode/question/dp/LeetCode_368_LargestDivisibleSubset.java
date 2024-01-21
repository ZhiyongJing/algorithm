package leetcode.question.dp;

import java.util.*;

/**
  *@Question:  368. Largest Divisible Subset     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 20.97%      
 *@Time  Complexity: O(N^2) for solution1, O(N^2) for solution2, O(N^2) for solution3
 *@Space Complexity: O(N^2) for solution1, O(N^2) for solution2, O(N) for solution3
 */

/**
 * 这道题的解题思路主要是通过动态规划来求解最大整除子集。
 *
 * ### 解题思路
 *
 * #### Solution1: 自顶向下的动态规划
 *
 * 1. 使用HashMap `_eds` 缓存每个位置为结束点的最大整除子集，以避免重复计算。
 * 2. 定义函数 `EDS(i)`，递归计算以第 `i` 个数为结束点的最大整除子集。
 * 3. 在 `EDS(i)` 中，遍历前面的元素，找到能整除的元素，然后递归计算前面元素的最大整除子集。
 * 4. 将当前元素加入得到的子集，得到新的子集，并更新缓存 `_eds`。
 * 5. 对每个位置调用 `EDS(i)`，找到最大的整除子集。
 *
 * #### Solution2: 自底向上的动态规划
 *
 * 1. 使用二维数组 `EDS` 存储每个位置为结束点的最大整除子集。
 * 2. 对原数组进行排序。
 * 3. 对每个位置 `i`，遍历前面的元素，找到能整除的元素，然后将其子集扩展，得到新的子集。
 * 4. 在 `EDS` 中记录以当前元素为结束点的最大整除子集。
 * 5. 找到 `EDS` 中最大的子集。
 *
 * #### Solution3: 基于 Solution2 的空间优化
 *
 * 1. 使用一维数组 `dp` 记录以每个元素为结束点的最大整除子集的大小。
 * 2. 对每个位置 `i`，遍历前面的元素，找到能整除的元素，然后将其子集大小加上当前元素。
 * 3. 记录最大子集的大小和结束点。
 * 4. 通过最大子集的大小和结束点，重建最大整除子集。
 *
 * ### 时间复杂度
 *
 * - Solution1: 自顶向下的动态规划，时间复杂度为 O(N^2)，其中 N 为数组的长度。
 * - Solution2: 自底向上的动态规划，时间复杂度为 O(N^2)。
 * - Solution3: 基于 Solution2 的空间优化，时间复杂度为 O(N^2)。
 *
 * ### 空间复杂度
 *
 * - Solution1: 自顶向下的动态规划，空间复杂度为 O(N^2)。
 * - Solution2: 自底向上的动态规划，空间复杂度为 O(N^2)。
 * - Solution3: 基于 Solution2 的空间优化，空间复杂度为 O(N)。
 */

public class LeetCode_368_LargestDivisibleSubset{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //Solution1: 自顶向下的动态规划
        HashMap<Integer, List<Integer>> _eds = new HashMap<Integer, List<Integer>>();
        int[] _nums = {};

        private List<Integer> EDS(Integer i) {
            // memoization
            if (this._eds.containsKey(i)) return this._eds.get(i);

            List<Integer> maxSubset = new ArrayList();
            // 寻找前面元素的最大整除子集
            for (int k = 0; k < i; ++k) {
                if (this._nums[i] % this._nums[k] == 0) {
                    List<Integer> subset = EDS(k);
                    if (maxSubset.size() < subset.size()) maxSubset = subset;
                }
            }
            // 将找到的子集加上当前元素
            List<Integer> newEntry = new ArrayList();
            newEntry.addAll(maxSubset);
            newEntry.add(this._nums[i]);

            // 更新缓存的值
            this._eds.put(i, newEntry);
            return newEntry;
        }

        public List<Integer> largestDivisibleSubset1(int[] nums) {
            // 空集的情况
            int n = nums.length;
            if (n == 0) return new ArrayList();

            // 存储以每个元素为结尾的最大整除子集
            this._eds = new HashMap<Integer, List<Integer>>();

            // 对原数组进行升序排序
            Arrays.sort(nums);
            this._nums = nums;

            List<Integer> maxSubset = new ArrayList();
            // 计算所有 EDS(X_i) 的值，同时保持最大值作为结果值
            for (int i = 0; i < n; ++i) {
                List<Integer> subset = EDS(i);
                if (maxSubset.size() < subset.size()) maxSubset = subset;
            }

            // 返回最大的子集
            return maxSubset;
        }

        //Solution2: 自底向上的动态规划
        public List<Integer> largestDivisibleSubset2(int[] nums) {
            // 空集的情况
            int n = nums.length;
            if (n == 0) return new ArrayList();

            // 存储以每个元素为结尾的最大整除子集
            List<ArrayList> EDS = new ArrayList();
            for (int num : nums) EDS.add(new ArrayList());

            // 对原数组进行升序排序
            Arrays.sort(nums);

            // 计算所有 EDS(X_i) 的值
            for (int i = 0; i < n; ++i) {
                List<Integer> maxSubset = new ArrayList();

                // 寻找前面元素的最大整除子集
                for (int k = 0; k < i; ++k)
                    if (nums[i] % nums[k] == 0 && maxSubset.size() < EDS.get(k).size())
                        maxSubset = EDS.get(k);

                // 将找到的子集加上当前元素
                EDS.get(i).addAll(maxSubset);
                EDS.get(i).add(nums[i]);
            }
            System.out.println(EDS);
            /* 找到所有 EDS 值中最大的一个 */
            List<Integer> ret = new ArrayList();
            for (int i = 0; i < n; ++i)
                if (ret.size() < EDS.get(i).size()) ret = EDS.get(i);
            return ret;
        }

        //Solution3: 基于Solution2的空间优化
        public List<Integer> largestDivisibleSubset(int[] nums) {
            // 空集的情况
            int n = nums.length;
            if (n == 0) return new ArrayList();

            // 存储以每个元素为结尾的最大整除子集的大小
            Integer[] dp = new Integer[n];

            // 对原数组进行升序排序
            Arrays.sort(nums);

            Integer maxSubsetSize = -1, maxSubsetIndex = -1;

            /* 计算剩余的 EDS(X_i) 的值 */
            for (int i = 0; i < n; ++i) {
                Integer subsetSize = 0;

                // 寻找前面元素的最大整除子集的大小
                for (int k = 0; k < i; ++k)
                    if (nums[i] % nums[k] == 0 && subsetSize < dp[k])
                        subsetSize = dp[k];

                // 将子集大小加上当前元素
                dp[i] = subsetSize + 1;

                // 重用循环以获取最大子集大小，为子集重建做准备
                if (maxSubsetSize < dp[i]) {
                    maxSubsetSize = dp[i];
                    maxSubsetIndex = i;
                }
            }
            System.out.println(Arrays.toString(dp));
            System.out.println(maxSubsetIndex);
            System.out.println(maxSubsetSize);

            /* 重建最大整除子集  */
            LinkedList<Integer> subset = new LinkedList();
            Integer currSize = maxSubsetSize;
            Integer currTail = nums[maxSubsetIndex];
            for (int i = maxSubsetIndex; i >= 0; --i) {
                if (currSize == 0) break;

                if (currTail % nums[i] == 0 && currSize == dp[i]) {
                    subset.addFirst(nums[i]);
                    currTail = nums[i];
                    currSize -= 1;
                }
            }

            return subset;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_368_LargestDivisibleSubset().new Solution();
        // 测试代码
        int[] nums = {1, 2, 3, 4, 8, 12};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        System.out.println("Solution 1: " + result);

        result = solution.largestDivisibleSubset(nums);
        System.out.println("Solution 2: " + result);

        result = solution.largestDivisibleSubset(nums);
        System.out.println("Solution 3: " + result);
    }
}

/**
Given a set of distinct positive integers nums, return the largest subset 
answer such that every pair (answer[i], answer[j]) of elements in this subset 
satisfies: 

 
 answer[i] % answer[j] == 0, or 
 answer[j] % answer[i] == 0 
 

 If there are multiple solutions, return any of them. 

 
 Example 1: 

 
Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
 

 Example 2: 

 
Input: nums = [1,2,4,8]
Output: [1,2,4,8]
 

 
 Constraints: 

 
 1 <= nums.length <= 1000 
 1 <= nums[i] <= 2 * 10⁹ 
 All the integers in nums are unique. 
 

 Related Topics Array Math Dynamic Programming Sorting 👍 5049 👎 196

*/
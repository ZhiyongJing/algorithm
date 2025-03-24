package leetcode.question.binary_search;

/**
 *@Question:  3356. Zero Array Transformation II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 61.9%
 *@Time  Complexity: O(logM * (N + M)) for solution1, N is size of nums, M is size of queries, O(N + M) for solution2
 *@Space Complexity: O(N) for both
 */
/**
 * 第一步：题目描述
 * -------------------------------------------------------------------
 * LeetCode 3356 - Zero Array Transformation II
 *
 * 给定一个整数数组 `nums`，初始不全为 0，目标是通过一系列操作使得该数组中所有元素都变为 0。
 * 操作由一个二维数组 `queries` 给出，每条 query 表示对区间 `[start, end]` 内的每个元素加上一个值 `val`。
 * 注意：每次 query 是“加值”操作，你无法减少某个数的值。
 *
 * 你的任务是找出 **最少需要前多少个 query（按顺序）** 才能把 nums 转变成 zero array（所有值都小于等于 0）。
 * 若使用所有 query 仍无法完成任务，返回 -1。
 *
 * 示例：
 * 输入：
 * nums = [1, 2, 3]
 * queries = [[0, 1, 1], [1, 2, 2], [0, 2, 1]]
 * 输出：3
 * 解释：
 * - 第 1 个 query 后：nums = [2, 3, 3]
 * - 第 2 个 query 后：nums = [2, 5, 5]
 * - 第 3 个 query 后：nums = [3, 6, 6]
 * 此时可以通过每个元素被加的总和满足 >= 原始值，实现 zero array。

 * -------------------------------------------------------------------
 *
 * 第二步：解题思路详解（Solution 1 和 Solution 2）
 * -------------------------------------------------------------------
 * ✅ Solution 1：二分查找 + 差分数组（Binary Search + Prefix Sum）
 *
 * 思路核心：
 * - 使用二分查找来找到“最早可以变成 zero array”的 query 个数。
 * - 判断前 k 个 query 是否能使所有 nums[i] <= 所加值之和，用差分数组模拟区间加法。
 *
 * 步骤说明：
 * 1. 定义 binary search 区间为 [0, queries.length]，表示我们在尝试使用前多少个 query。
 * 2. 对于每次二分猜测的中间值 middle，执行 currentIndexZero()：
 *    - 初始化差分数组 differenceArray[]，模拟前 middle 个 query 对 nums 的影响。
 *    - 遍历每个元素，累计差分数组得到实际加和值 prefix sum。
 *    - 如果某个 nums[i] > prefixSum[i]，说明当前 query 不够。
 * 3. 若 middle 个 query 成功，继续向左搜索；否则向右搜索。
 * 4. 返回最小满足条件的 query 个数。
 *
 * 举例解释：
 * nums = [1, 2, 3]，queries = [[0, 1, 1], [1, 2, 2], [0, 2, 1]]
 * - 使用前 1 个 query，nums 变为 [2, 3, 3]（不足）
 * - 使用前 2 个 query，变为 [2, 5, 5]（仍不足）
 * - 使用前 3 个 query，变为 [3, 6, 6]（每个元素所加之和 >= 原始值，成功）
 *
 * ✅ Solution 2：贪心模拟 + 差分数组（Line Sweep / Greedy）
 *
 * 思路核心：
 * - 遍历每个位置 index，动态检查它当前被加的总和是否足以覆盖原始 nums[index]。
 * - 如果不足，就不断添加下一个可用的 query 来增加值（直到满足或耗尽所有 query）。
 *
 * 步骤说明：
 * 1. 对每个 index，从前往后依次模拟。
 * 2. 使用差分数组 differenceArray[] 和 prefix sum sum 来累计之前的总加值。
 * 3. 如果当前 sum + difference[index] < nums[index]，说明不够。
 *    - 继续从 queries[k] 开始，应用对当前 index 有效的 query。
 *    - 重复直到满足或 query 耗尽。
 * 4. 最终返回使用的 query 个数。
 *
 * 举例解释：
 * nums = [2, 1, 1]
 * queries = [[0, 2, 1], [1, 2, 1]]
 * - index = 0，需要至少加到 >= 2，使用 query[0] → difference[0] += 1
 * - index = 1，当前差分值不足 1，再用 query[1] → difference[1] += 1
 * - 最终所有 index 满足条件，返回 k = 2
 *
 * -------------------------------------------------------------------
 *
 * 第三步：时间和空间复杂度分析
 * -------------------------------------------------------------------
 * ✅ Solution 1（二分查找解法）
 * 时间复杂度：O(log M * (N + M))
 * - log M 次二分，每次判断需要遍历 N 个元素和 K（最多 M）个 query 来构造差分数组。
 * 空间复杂度：O(N)
 * - 差分数组 differenceArray 占用 O(N+1) 空间。

 * ✅ Solution 2（线性贪心解法）
 * 时间复杂度：O(N + M)
 * - 每个 index 最多访问一次，每个 query 也最多处理一次。
 * 空间复杂度：O(N)
 * - 差分数组占 O(N+1) 空间。
 */

public class LeetCode_3356_ZeroArrayTransformationIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //solution1: binary search（二分查找版本）
        public int minZeroArray1(int[] nums, int[][] queries) {
            int n = nums.length, left = 0, right = queries.length;

            // 如果所有 query 都执行完仍不能变成 zero array，直接返回 -1
            if (!currentIndexZero(nums, queries, right)) return -1;

            // 开始二分查找，查找最小满足条件的 query 数量
            while (left <= right) {
                int middle = left + (right - left) / 2;
                // 如果前 middle 个 query 可以构造 zero array，缩小范围继续找更小的
                if (currentIndexZero(nums, queries, middle)) {
                    right = middle - 1;
                } else {
                    // 否则扩大查找范围
                    left = middle + 1;
                }
            }

            // 返回最小满足条件的 query 数量
            return left;
        }

        // 判断前 k 个 query 是否可以将 nums 变为 zero array
        private boolean currentIndexZero(int[] nums, int[][] queries, int k) {
            int n = nums.length, sum = 0;
            int[] differenceArray = new int[n + 1];

            // 构建差分数组，模拟加法操作
            for (int queryIndex = 0; queryIndex < k; queryIndex++) {
                int left = queries[queryIndex][0], right =
                        queries[queryIndex][1], val = queries[queryIndex][2];

                // 差分更新：区间 [left, right] 上每个元素加 val
                differenceArray[left] += val;
                differenceArray[right + 1] -= val;
            }

            // 前缀和模拟真实数组并进行比较
            for (int numIndex = 0; numIndex < n; numIndex++) {
                sum += differenceArray[numIndex];
                // 如果当前元素加完后仍小于原始值，说明失败
                if (sum < nums[numIndex]) return false;
            }
            return true;
        }

        //solution2: line sweep（贪心 + 前缀和）
        public int minZeroArray(int[] nums, int[][] queries) {
            int n = nums.length, sum = 0, k = 0;
            int[] differenceArray = new int[n + 1];

            // 遍历原始数组每个位置
            for (int index = 0; index < n; index++) {
                // 当前前缀和不够消除 nums[index]，不断取 query 增加值
                while (sum + differenceArray[index] < nums[index]) {
                    k++;

                    // 如果 query 全部用完仍无法满足，返回 -1
                    if (k > queries.length) {
                        return -1;
                    }
                    int left = queries[k - 1][0], right = queries[k - 1][1], val =
                            queries[k - 1][2];

                    // 只处理会影响当前 index 的 query
                    if (right >= index) {
                        differenceArray[Math.max(left, index)] += val;
                        differenceArray[right + 1] -= val;
                    }
                }
                // 更新前缀和
                sum += differenceArray[index];
            }
            // 返回所用 query 数量
            return k;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_3356_ZeroArrayTransformationIi().new Solution();

        // ✅ 测试用例 1：基本示例
        int[] nums1 = {1, 2, 3};
        int[][] queries1 = {{0, 1, 1}, {1, 2, 2}, {0, 2, 1}};
        System.out.println(solution.minZeroArray(nums1, queries1));  // 输出: 3

        // ✅ 测试用例 2：提前满足
        int[] nums2 = {1, 1};
        int[][] queries2 = {{0, 1, 1}};
        System.out.println(solution.minZeroArray(nums2, queries2));  // 输出: 1

        // ✅ 测试用例 3：无法满足
        int[] nums3 = {5, 5, 5};
        int[][] queries3 = {{0, 0, 1}, {1, 1, 1}};
        System.out.println(solution.minZeroArray(nums3, queries3));  // 输出: -1

        // ✅ 测试用例 4：空数组
        int[] nums4 = {};
        int[][] queries4 = {};
        System.out.println(solution.minZeroArray(nums4, queries4));  // 输出: 0

        // ✅ 测试用例 5：全部为 0，无需操作
        int[] nums5 = {0, 0, 0};
        int[][] queries5 = {{0, 2, 1}};
        System.out.println(solution.minZeroArray(nums5, queries5));  // 输出: 0
    }
}

/**
You are given an integer array nums of length n and a 2D array queries where 
queries[i] = [li, ri, vali]. 

 Each queries[i] represents the following action on nums: 

 
 Decrement the value at each index in the range [li, ri] in nums by at most 
vali. 
 The amount by which each value is decremented
 can be chosen independently for each index. 
 

 A Zero Array is an array with all its elements equal to 0. 

 Return the minimum possible non-negative value of k, such that after 
processing the first k queries in sequence, nums becomes a Zero Array. If no such k 
exists, return -1. 

 
 Example 1: 

 
 Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]] 
 

 Output: 2 

 Explanation: 

 
 For i = 0 (l = 0, r = 2, val = 1): 
 

 
 Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively. 
 The array will become [1, 0, 1]. 
 
 
 For i = 1 (l = 0, r = 2, val = 1):
 
 Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively. 
 The array will become [0, 0, 0], which is a Zero Array. Therefore, the minimum 
value of k is 2. 
 
 


 Example 2: 

 
 Input: nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]] 
 

 Output: -1 

 Explanation: 

 
 For i = 0 (l = 1, r = 3, val = 2): 
 

 
 Decrement values at indices [1, 2, 3] by [2, 2, 1] respectively. 
 The array will become [4, 1, 0, 0]. 
 
 
 For i = 1 (l = 0, r = 2, val = 1):
 
 Decrement values at indices [0, 1, 2] by [1, 1, 0] respectively. 
 The array will become [3, 0, 0, 0], which is not a Zero Array. 
 
 


 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 0 <= nums[i] <= 5 * 10⁵ 
 1 <= queries.length <= 10⁵ 
 queries[i].length == 3 
 0 <= li <= ri < nums.length 
 1 <= vali <= 5 
 

 Related Topics Array Binary Search Prefix Sum 👍 886 👎 80

*/
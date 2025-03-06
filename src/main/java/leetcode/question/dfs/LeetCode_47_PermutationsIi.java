package leetcode.question.dfs;
import java.util.*;

/**
 *@Question:  47. Permutations II
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 52.99%
 *@Time Complexity: O(N * N!)  (N! 种排列，每种排列需要 O(N) 时间复制到结果列表)
 *@Space Complexity: O(N!)  (结果列表存储所有唯一排列)
 */
/**
 * 题目描述：
 * LeetCode 47. Permutations II
 * 给定一个可能包含重复元素的整数数组 `nums`，返回所有**不重复**的全排列。
 * 每个数字都必须使用一次，并且排列结果不能包含重复项。
 *
 * 示例 1：
 * 输入: nums = [1,1,2]
 * 输出:
 * [[1,1,2], [1,2,1], [2,1,1]]
 *
 * 示例 2：
 * 输入: nums = [1,2,3]
 * 输出:
 * [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
 *
 * 题目要求返回所有唯一的全排列，即 **不能出现重复排列**。
 */

/**
 * 解题思路：
 * 该问题可以使用 **回溯 + 哈希表去重** 解决。
 * 主要思路如下：
 *
 * 1. **使用哈希表 `counter` 统计每个数字出现的次数**
 *    - 由于 `nums` 可能包含重复元素，因此我们不能直接用 `visited[]` 数组来标记是否使用过某个数，
 *      否则会导致相同的数字被认为是不同的数字，从而产生重复排列。
 *    - 例如：`nums = [1,1,2]`，如果简单地使用 `visited[]`，就会多次生成 `[1,1,2]`。
 *    - 解决方案是：使用 `HashMap<Integer, Integer>` 记录每个数字的出现次数，并且**只有在次数大于 0 时**才选择该数字。
 *
 * 2. **回溯生成所有可能的排列**
 *    - 维护一个 `comb` 列表（当前构建的排列）。
 *    - 递归时，遍历 `counter` 的所有键值对 `(num, count)`：
 *      - 如果 `count > 0`，说明当前数字 `num` 仍可使用：
 *        1. 选择 `num`，加入 `comb`
 *        2. 递归继续填充下一个位置
 *        3. 撤销选择 `num`（回溯），恢复 `counter` 的状态。
 *
 * 3. **递归终止条件**
 *    - 如果 `comb.size() == nums.length`，说明已经生成了一个完整排列，将其存入 `results`。
 *    - 由于我们是基于 `counter` 进行选择，因此生成的排列天然**不会包含重复项**。
 *
 * 4. **举例分析**
 * 以 `nums = [1,1,2]` 为例：
 * - 初始 `counter = {1:2, 2:1}`
 * - **第一层递归**
 *   - 选择 `1` → `comb = [1]`, `counter = {1:1, 2:1}`
 * - **第二层递归**
 *   - 选择 `1` → `comb = [1,1]`, `counter = {1:0, 2:1}`
 * - **第三层递归**
 *   - 选择 `2` → `comb = [1,1,2]`, `counter = {1:0, 2:0}`（已满）
 *   - 加入结果集
 * - **回溯**
 *   - 撤销 `2` → `comb = [1,1]`, `counter = {1:0, 2:1}`
 * - **第二层递归继续**
 *   - 选择 `2` → `comb = [1,2]`, `counter = {1:1, 2:0}`
 * - **第三层递归**
 *   - 选择 `1` → `comb = [1,2,1]`, `counter = {1:0, 2:0}`（已满）
 *   - 加入结果集
 * - **回溯**
 *   - 撤销 `1` → `comb = [1,2]`, `counter = {1:1, 2:0}`
 * - 继续回溯，最终生成所有唯一排列：
 *   `[[1,1,2], [1,2,1], [2,1,1]]`
 *
 * **最终返回所有唯一排列**
 */

/**
 * 时间和空间复杂度分析：
 *
 * 1. **时间复杂度：O(N * N!)**
 *    - `N!` 是所有可能的全排列个数（去重后仍接近 `N!`）。
 *    - 由于每次递归需要 `O(N)` 遍历 `counter`，所以总复杂度为 `O(N * N!)`。
 *
 * 2. **空间复杂度：O(N!)**
 *    - `results` 存储所有唯一排列，最多 `O(N!)` 个。
 *    - 递归调用的深度为 `O(N)`（`comb` 递归栈）。
 *    - `counter` 额外占用 `O(N)` 空间。
 *    - 综上，**整体空间复杂度为 `O(N!)`**。
 *
 * 综上，该解法利用 **回溯 + 计数去重** 高效求解该问题。
 */


public class LeetCode_47_PermutationsIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            // 用于存储最终的唯一排列结果
            List<List<Integer>> results = new ArrayList<>();

            // 统计每个数字出现的次数，避免重复使用
            HashMap<Integer, Integer> counter = new HashMap<>();
            for (int num : nums) {
                if (!counter.containsKey(num)) counter.put(num, 0);
                counter.put(num, counter.get(num) + 1);
            }

            // 递归回溯，生成所有唯一的排列
            LinkedList<Integer> comb = new LinkedList<>();
            this.backtrack(comb, nums.length, counter, results);
            return results;
        }

        protected void backtrack(
                LinkedList<Integer> comb,  // 当前正在构建的排列
                Integer N,  // 目标排列长度，即 nums.length
                HashMap<Integer, Integer> counter,  // 记录每个数字的剩余使用次数
                List<List<Integer>> results  // 存储所有唯一的排列
        ) {
            if (comb.size() == N) {
                // 当排列长度达到 N，添加当前排列的副本到结果集
                results.add(new ArrayList<Integer>(comb));
                return;
            }

            // 遍历哈希表中的所有数字
            for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
                Integer num = entry.getKey();  // 当前选取的数字
                Integer count = entry.getValue();  // 当前数字剩余的使用次数
                if (count == 0) continue;  // 如果当前数字已经用完，跳过

                // 选择当前数字
                comb.addLast(num);
                counter.put(num, count - 1);

                // 继续递归寻找下一个位置的数字
                backtrack(comb, N, counter, results);

                // 撤销选择，回溯到上一状态
                comb.removeLast();
                counter.put(num, count);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_47_PermutationsIi().new Solution();

        // 测试用例 1
        int[] nums1 = {1, 1, 2};
        System.out.println(solution.permuteUnique(nums1));
        // 期望输出: [[1,1,2], [1,2,1], [2,1,1]]

        // 测试用例 2
        int[] nums2 = {1, 2, 3};
        System.out.println(solution.permuteUnique(nums2));
        // 期望输出: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

        // 测试用例 3
        int[] nums3 = {2, 2, 1, 1};
        System.out.println(solution.permuteUnique(nums3));
        // 期望输出: [[1,1,2,2], [1,2,1,2], [1,2,2,1], [2,1,1,2], [2,1,2,1], [2,2,1,1]]
    }
}

/**
Given a collection of numbers, nums, that might contain duplicates, return all 
possible unique permutations in any order. 

 
 Example 1: 

 
Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
 

 Example 2: 

 
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 

 
 Constraints: 

 
 1 <= nums.length <= 8 
 -10 <= nums[i] <= 10 
 

 Related Topics Array Backtracking Sorting 👍 8722 👎 151

*/
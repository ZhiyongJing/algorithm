package leetcode.question.map_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *@Question:  398. Random Pick Index
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 63.24%
 *@Time Complexity: O(1) for pick // 查询操作 pick(target) 仅需 O(1)
 *@Space Complexity: O(N) // 需要存储所有元素的索引
 */
/**
 * 题目描述：
 * LeetCode 398. Random Pick Index
 *
 * 给定一个整数数组 `nums`，其中可能包含重复元素。
 * 设计一个 `pick(target)` 方法，该方法应当 **均匀随机** 地返回 `nums` 中 **值等于 `target`** 的一个索引。
 *
 * **示例 1：**
 * ```
 * 输入:
 * nums = [1,2,3,3,3]
 * solution.pick(3)  // 可能返回索引 2, 3 或 4，要求随机选择
 * solution.pick(1)  // 只能返回索引 0
 * ```
 *
 * ---
 *
 * **解题思路：**
 *
 * 由于 `nums` 可能包含多个 `target`，我们需要 **随机均匀地选择一个索引**。
 *
 * **方法 1：预处理 + 哈希表**
 * 1. **使用 `HashMap<Integer, List<Integer>>` 记录每个数出现的索引**
 *    - 遍历 `nums`，将每个数的索引存入 `indices` 哈希表：
 *      ```
 *      nums = [1,2,3,3,3]
 *      indices = {
 *          1 -> [0],
 *          2 -> [1],
 *          3 -> [2,3,4]
 *      }
 *      ```
 *
 * 2. **查询 `pick(target)`**
 *    - 从 `indices.get(target)` 获取目标值的所有索引列表。
 *    - 使用 `rand.nextInt(size)` **随机选择一个索引** 并返回：
 *      - 例如 `pick(3)`，从 `[2,3,4]` 选择。
 *
 * ---
 * **示例解析**
 *
 * **输入**
 * ```
 * nums = [1,2,3,3,3]
 * ```
 * **预处理**
 * ```
 * indices = {
 *     1 -> [0],
 *     2 -> [1],
 *     3 -> [2,3,4]
 * }
 * ```
 * **查询 `pick(3)`**
 * - `indices.get(3) = [2,3,4]`
 * - 随机选择 `2, 3, 4` 之一，**概率均等**
 *
 * ---
 * **时间和空间复杂度分析**
 *
 * - **构造 (`Solution`)**
 *   - 预处理 `nums` 需要 **O(N)** 遍历数组并存储索引。
 *   - 空间复杂度 **O(N)**，存储 `nums` 中所有索引。
 *
 * - **查询 (`pick`)**
 *   - 获取 `indices.get(target)` 需要 **O(1)**。
 *   - 使用 `rand.nextInt()` 选择索引 **O(1)**。
 *   - **总查询时间复杂度：O(1)**。
 */


public class LeetCode_398_RandomPickIndex{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private HashMap<Integer, List<Integer>> indices; // 用于存储每个元素出现的所有索引
        private Random rand; // 随机数生成器

        public Solution(int[] nums) {
            this.rand = new Random(); // 初始化随机数生成器
            this.indices = new HashMap<>(); // 初始化哈希表存储索引列表

            int l = nums.length; // 数组长度
            for (int i = 0; i < l; ++i) {
                // 如果该数字的索引列表不存在，则创建
                if (!this.indices.containsKey(nums[i])) {
                    this.indices.put(nums[i], new ArrayList<>());
                }
                // 将当前索引添加到对应数字的索引列表
                this.indices.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            // 获取 target 对应的所有索引列表
            int l = indices.get(target).size();
            // 在列表中随机选择一个索引并返回
            int randomIndex = indices.get(target).get(rand.nextInt(l));
            return randomIndex;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int param_1 = obj.pick(target);
     */
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3}; // 初始化数组
        Solution solution = new LeetCode_398_RandomPickIndex().new Solution(nums);

        // 测试 pick 方法，调用多次以观察随机性
        System.out.println("随机选择 3 的索引: " + solution.pick(3)); // 可能返回 2, 3, 4
        System.out.println("随机选择 3 的索引: " + solution.pick(3)); // 可能返回 2, 3, 4
        System.out.println("随机选择 3 的索引: " + solution.pick(3)); // 可能返回 2, 3, 4
        System.out.println("随机选择 1 的索引: " + solution.pick(1)); // 只能返回 0
        System.out.println("随机选择 2 的索引: " + solution.pick(2)); // 只能返回 1
    }
}

/**
Given an integer array nums with possible duplicates, randomly output the index 
of a given target number. You can assume that the given target number must 
exist in the array. 

 Implement the Solution class: 

 
 Solution(int[] nums) Initializes the object with the array nums. 
 int pick(int target) Picks a random index i from nums where nums[i] == target. 
If there are multiple valid i's, then each index should have an equal 
probability of returning. 
 

 
 Example 1: 

 
Input
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
Output
[null, 4, 0, 2]

Explanation
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each 
index should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is 
equal to 1.
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each 
index should have equal probability of returning.
 

 
 Constraints: 

 
 1 <= nums.length <= 2 * 10⁴ 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 target is an integer from nums. 
 At most 10⁴ calls will be made to pick. 
 

 Related Topics Hash Table Math Reservoir Sampling Randomized 👍 1345 👎 1299

*/
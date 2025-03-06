package leetcode.question.bit_manipulation;

import java.util.Arrays;

/**
 *@Question:  260. Single Number III
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 46.42%
 *@Time Complexity: O(N)  (遍历数组两次，每次 O(N))
 *@Space Complexity: O(1)  (仅使用常数级额外变量)
 */
/**
 * 题目描述：
 * LeetCode 260. Single Number III
 * 给定一个整数数组 `nums`，其中恰好有两个元素只出现 **一次**，其余的元素都出现 **两次**。
 * 你需要找到这两个 **唯一** 出现的元素，并以 **数组形式** 返回结果。
 *
 * **要求：**
 * - 你的算法应该具有 **线性时间复杂度 O(N)**。
 * - **不使用额外空间**（即 **O(1) 额外空间**）。
 *
 * 示例 1：
 * ```
 * 输入: nums = [1,2,1,3,2,5]
 * 输出: [3,5] 或 [5,3]
 * ```
 *
 * 示例 2：
 * ```
 * 输入: nums = [-1,0]
 * 输出: [-1,0] 或 [0,-1]
 * ```
 *
 * 示例 3：
 * ```
 * 输入: nums = [0,1]
 * 输出: [0,1] 或 [1,0]
 * ```
 */

/**
 * 解题思路：
 * 题目要求 **O(N) 时间复杂度** 和 **O(1) 额外空间**，这意味着我们不能使用排序或哈希表。
 * **位运算（Bit Manipulation）+ 分组异或** 是解决本题的最佳方法。
 * 主要思路如下：
 *
 * 1. **使用异或找出两个唯一数的 XOR 结果**
 *    - 因为相同的数字异或后结果为 `0`，所以 `nums` 中所有元素的 **异或结果 bitmask** 就是两个唯一数 `x` 和 `y` 的异或结果：
 *      ```
 *      bitmask = x ^ y
 *      ```
 *    - 例如：
 *      ```
 *      nums = [1,2,1,3,2,5]
 *      bitmask = 3 ^ 5 = 011 ^ 101 = 110
 *      ```
 *    - 这意味着 `bitmask` 中至少有一位是 `1`，说明 `x` 和 `y` 在某一位上是不同的。
 *
 * 2. **找到 `bitmask` 的最低位 `1`**
 *    - `bitmask & (-bitmask)` 取 `bitmask` 的最低位 `1`：
 *      ```
 *      diff = bitmask & (-bitmask)
 *      ```
 *    - 例如：
 *      ```
 *      bitmask = 110
 *      -bitmask = 010
 *      diff = 110 & 010 = 010
 *      ```
 *    - `diff` 的作用是找到 `x` 和 `y` **最低位不同的那一位**，用来分组。
 *
 * 3. **利用 `diff` 进行分组异或**
 *    - 遍历 `nums`，将所有数字按照 `diff` 的那一位是否是 `1` 分成 **两组**：
 *      - 组 1：所有 `num & diff == 0` 的数。
 *      - 组 2：所有 `num & diff != 0` 的数。
 *    - 由于相同的数字会落入 **相同的组**，且每组里只有一个唯一数，因此对每组所有数字进行 **异或**，就能分别得到 `x` 和 `y`。
 *    - 例如：
 *      ```
 *      nums = [1,2,1,3,2,5], bitmask = 110, diff = 010
 *      组 1 (bit 位 1 处是 0)：[3, 5] → 3 ^ 5 = 6 (x)
 *      组 2 (bit 位 1 处是 1)：[1, 2, 1, 2] → 1 ^ 2 ^ 1 ^ 2 = 5 (y)
 *      ```
 *    - 最终返回 `[x, y]`，即 `[3, 5]`。
 *
 * **举例分析**
 * **示例 1**
 * ```
 * 输入: nums = [1,2,1,3,2,5]
 * 计算：
 *   1 ^ 2 ^ 1 ^ 3 ^ 2 ^ 5 = 3 ^ 5 = 110
 *   diff = 110 & (-110) = 010
 *   组 1：所有 `num & 010 == 0` → [3, 5]，异或结果 `3 ^ 5 = 6`
 *   组 2：所有 `num & 010 != 0` → [1, 2, 1, 2]，异或结果 `1 ^ 2 ^ 1 ^ 2 = 5`
 * 输出: [3,5] 或 [5,3]
 * ```
 *
 * **示例 2**
 * ```
 * 输入: nums = [-1,0]
 * 计算：
 *   -1 ^ 0 = 11111111 ^ 00000000 = 11111111
 *   diff = 11111111 & (-11111111) = 00000001
 *   组 1：所有 `num & 1 == 0` → [0]，异或结果 `0`
 *   组 2：所有 `num & 1 != 0` → [-1]，异或结果 `-1`
 * 输出: [-1,0] 或 [0,-1]
 * ```
 */

/**
 * 时间和空间复杂度分析：
 *
 * 1. **时间复杂度：O(N)**
 *    - **第一次遍历**：计算 `bitmask`，需要 `O(N)`。
 *    - **第二次遍历**：计算 `x`（使用 `diff` 进行分组），需要 `O(N)`。
 *    - **最终计算 `y`**：只需 `O(1)`。
 *    - **总时间复杂度：O(N)**。
 *
 * 2. **空间复杂度：O(1)**
 *    - 只使用了 `bitmask`、`diff`、`x` 这几个额外变量，均为 **常数级**。
 *    - **总空间复杂度：O(1)**。
 */
/**
 * 题目描述：
 * LeetCode 260. Single Number III
 * 给定一个整数数组 `nums`，其中恰好有两个元素只出现 **一次**，其余的元素都出现 **两次**。
 * 你需要找到这两个 **唯一** 出现的元素，并以 **数组形式** 返回结果。
 *
 * **要求：**
 * - 你的算法应该具有 **线性时间复杂度 O(N)**。
 * - **不使用额外空间**（即 **O(1) 额外空间**）。
 *
 * 示例 1：
 * ```
 * 输入: nums = [1,2,1,3,2,5]
 * 输出: [3,5] 或 [5,3]
 * ```
 *
 * 示例 2：
 * ```
 * 输入: nums = [-1,0]
 * 输出: [-1,0] 或 [0,-1]
 * ```
 *
 * 示例 3：
 * ```
 * 输入: nums = [0,1]
 * 输出: [0,1] 或 [1,0]
 * ```
 */

/**
 * 解题思路：
 * 题目要求 **O(N) 时间复杂度** 和 **O(1) 额外空间**，这意味着我们不能使用排序或哈希表。
 * **位运算（Bit Manipulation）+ 分组异或** 是解决本题的最佳方法。
 * 主要思路如下：
 *
 * 1. **使用异或找出两个唯一数的 XOR 结果**
 *    - 因为相同的数字异或后结果为 `0`，所以 `nums` 中所有元素的 **异或结果 bitmask** 就是两个唯一数 `x` 和 `y` 的异或结果：
 *      ```
 *      bitmask = x ^ y
 *      ```
 *    - 例如：
 *      ```
 *      nums = [1,2,1,3,2,5]
 *      bitmask = 3 ^ 5 = 011 ^ 101 = 110
 *      ```
 *    - 这意味着 `bitmask` 中至少有一位是 `1`，说明 `x` 和 `y` 在某一位上是不同的。
 *
 * 2. **找到 `bitmask` 的最低位 `1`**
 *    - `bitmask & (-bitmask)` 取 `bitmask` 的最低位 `1`：
 *      ```
 *      diff = bitmask & (-bitmask)
 *      ```
 *    - 例如：
 *      ```
 *      bitmask = 110
 *      -bitmask = 010
 *      diff = 110 & 010 = 010
 *      ```
 *    - `diff` 的作用是找到 `x` 和 `y` **最低位不同的那一位**，用来分组。
 *
 * 3. **利用 `diff` 进行分组异或**
 *    - 遍历 `nums`，将所有数字按照 `diff` 的那一位是否是 `1` 分成 **两组**：
 *      - 组 1：所有 `num & diff == 0` 的数。
 *      - 组 2：所有 `num & diff != 0` 的数。
 *    - 由于相同的数字会落入 **相同的组**，且每组里只有一个唯一数，因此对每组所有数字进行 **异或**，就能分别得到 `x` 和 `y`。
 *    - 例如：
 *      ```
 *      nums = [1,2,1,3,2,5], bitmask = 110, diff = 010
 *      组 1 (bit 位 1 处是 0)：[3, 5] → 3 ^ 5 = 6 (x)
 *      组 2 (bit 位 1 处是 1)：[1, 2, 1, 2] → 1 ^ 2 ^ 1 ^ 2 = 5 (y)
 *      ```
 *    - 最终返回 `[x, y]`，即 `[3, 5]`。
 *
 * **举例分析**
 * **示例 1**
 * ```
 * 输入: nums = [1,2,1,3,2,5]
 * 计算：
 *   1 ^ 2 ^ 1 ^ 3 ^ 2 ^ 5 = 3 ^ 5 = 110
 *   diff = 110 & (-110) = 010
 *   组 1：所有 `num & 010 == 0` → [3, 5]，异或结果 `3 ^ 5 = 6`
 *   组 2：所有 `num & 010 != 0` → [1, 2, 1, 2]，异或结果 `1 ^ 2 ^ 1 ^ 2 = 5`
 * 输出: [3,5] 或 [5,3]
 * ```
 *
 * **示例 2**
 * ```
 * 输入: nums = [-1,0]
 * 计算：
 *   -1 ^ 0 = 11111111 ^ 00000000 = 11111111
 *   diff = 11111111 & (-11111111) = 00000001
 *   组 1：所有 `num & 1 == 0` → [0]，异或结果 `0`
 *   组 2：所有 `num & 1 != 0` → [-1]，异或结果 `-1`
 * 输出: [-1,0] 或 [0,-1]
 * ```
 */

/**
 * 时间和空间复杂度分析：
 *
 * 1. **时间复杂度：O(N)**
 *    - **第一次遍历**：计算 `bitmask`，需要 `O(N)`。
 *    - **第二次遍历**：计算 `x`（使用 `diff` 进行分组），需要 `O(N)`。
 *    - **最终计算 `y`**：只需 `O(1)`。
 *    - **总时间复杂度：O(N)**。
 *
 * 2. **空间复杂度：O(1)**
 *    - 只使用了 `bitmask`、`diff`、`x` 这几个额外变量，均为 **常数级**。
 *    - **总空间复杂度：O(1)**。
 */



public class LeetCode_260_SingleNumberIii{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] singleNumber(int[] nums) {
            // 用 bitmask 记录所有数字的异或结果
            int bitmask = 0;
            for (int num : nums) bitmask ^= num;

            // 找到两个不同数 x 和 y 的最低位不同的 bit
            int diff = bitmask & (-bitmask);

            int x = 0;
            // 通过 diff 分组异或，找到其中一个唯一数 x
            for (int num : nums) if ((num & diff) != 0) x ^= num;

            // 另一个唯一数 y = bitmask ^ x
            return new int[]{x, bitmask^x};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_260_SingleNumberIii().new Solution();

        // 测试用例 1
        int[] nums1 = {1,2,1,3,2,5};
        System.out.println(Arrays.toString(solution.singleNumber(nums1)));
        // 期望输出: [3,5] 或 [5,3]

        // 测试用例 2
        int[] nums2 = {-1, 0};
        System.out.println(Arrays.toString(solution.singleNumber(nums2)));
        // 期望输出: [-1,0] 或 [0,-1]

        // 测试用例 3
        int[] nums3 = {0,1};
        System.out.println(Arrays.toString(solution.singleNumber(nums3)));
        // 期望输出: [0,1] 或 [1,0]

        // 测试用例 4
        int[] nums4 = {4, 2, 4, 6};
        System.out.println(Arrays.toString(solution.singleNumber(nums4)));
        // 期望输出: [2,6] 或 [6,2]
    }
    }

/**
Given an integer array nums, in which exactly two elements appear only once and 
all the other elements appear exactly twice. Find the two elements that appear 
only once. You can return the answer in any order. 

 You must write an algorithm that runs in linear runtime complexity and uses 
only constant extra space. 

 
 Example 1: 

 
Input: nums = [1,2,1,3,2,5]
Output: [3,5]
Explanation:  [5, 3] is also a valid answer.
 

 Example 2: 

 
Input: nums = [-1,0]
Output: [-1,0]
 

 Example 3: 

 
Input: nums = [0,1]
Output: [1,0]
 

 
 Constraints: 

 
 2 <= nums.length <= 3 * 10⁴ 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 Each integer in nums will appear twice, only two integers will appear once. 
 

 Related Topics Array Bit Manipulation 👍 6504 👎 268

*/
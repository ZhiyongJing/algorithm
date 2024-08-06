package leetcode.question.string_list;
/**
 *@Question:  477. Total Hamming Distance
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 42.46%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * **题目：477. Total Hamming Distance**
 *
 * **问题描述：**
 * 给定一个整数数组 `nums`，计算所有数对之间的汉明距离的总和。汉明距离是指两个整数的二进制表示中，对应位不同的位置的数量。
 *
 * **解题思路：**
 *
 * 为了高效地计算汉明距离的总和，我们采用以下方法：
 *
 * 1. **统计每个位上的1的数量：**
 *    - 对于每个整数，检查它的每个位（共31位，因为我们考虑的是非负整数）。
 *    - 使用位运算 `(num >> i) & 1` 来判断第 `i` 位是否为1。
 *    - 用一个大小为31的数组 `cnt` 来记录每个位上1的数量。
 *
 * 2. **计算汉明距离：**
 *    - 对于每个位，第 `k` 位上有 `cnt[k]` 个数字为1，其余的 `nums.length - cnt[k]` 个数字为0。
 *    - 第 `k` 位对汉明距离的贡献为 `cnt[k] * (nums.length - cnt[k])`，即1的数量乘以0的数量。
 *    - 累加所有位的贡献，得到最终的汉明距离总和。
 *
 * **时间复杂度分析：**
 * - 统计每个位上1的数量的时间复杂度为 `O(n * 31)`，其中 `n` 是数组 `nums` 的长度，31是因为我们需要检查每个整数的每一位。
 * - 计算汉明距离总和的时间复杂度为 `O(31)`，因为我们只需遍历31个位置。
 * - 因此，整体时间复杂度为 `O(n)`。
 *
 * **空间复杂度分析：**
 * - 主要的空间消耗是用于存储每个位上1的数量的数组 `cnt`，大小为31。
 * - 因此，空间复杂度为 `O(1)`，因为 `cnt` 的大小是常数，不随输入数组的大小变化。
 */

public class LeetCode_477_TotalHammingDistance{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int totalHammingDistance(int[] nums) {
            // 计数第 i 位的 1 的数量
            int[] cnt = new int[31]; // 初始化一个数组来存储每个位上1的数量，数组大小为31，
            // 因为int类型有32位，这里使用31是因为负数的补码表示需要考虑符号位
            for (int num : nums) {
                for (int i = 0; i < 31; i++) {
                    cnt[i] += (num >> i) & 1; // 计算每个数字在每个位上的1的数量
                }
            }
            // 计算汉明距离的总和
            int ans = 0; // 存储最终的汉明距离总和
            for (int k = 0; k < 31; k++) {
                ans += cnt[k] * (nums.length - cnt[k]); // 计算每一位的贡献并累加到总和
            }
            return ans; // 返回最终的汉明距离总和
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_477_TotalHammingDistance().new Solution();
        // 测试样例
        int[] nums1 = {4, 14, 2};
        int result1 = solution.totalHammingDistance(nums1);
        System.out.println("Total Hamming Distance (Example 1): " + result1); // 应输出 6

        int[] nums2 = {1, 2, 3};
        int result2 = solution.totalHammingDistance(nums2);
        System.out.println("Total Hamming Distance (Example 2): " + result2); // 应输出 2
    }
}

/**
The Hamming distance between two integers is the number of positions at which 
the corresponding bits are different. 

 Given an integer array nums, return the sum of Hamming distances between all 
the pairs of the integers in nums. 

 
 Example 1: 

 
Input: nums = [4,14,2]
Output: 6
Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 
(just
showing the four bits relevant in this case).
The answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 
+ 2 = 6.
 

 Example 2: 

 
Input: nums = [4,14,4]
Output: 4
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 0 <= nums[i] <= 10⁹ 
 The answer for the given input will fit in a 32-bit integer. 
 

 Related Topics Array Math Bit Manipulation 👍 2213 👎 90

*/
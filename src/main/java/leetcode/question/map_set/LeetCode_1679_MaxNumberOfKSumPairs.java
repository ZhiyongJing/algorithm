// package 语句，声明当前类所在的包
package leetcode.question.map_set;
// 导入 Java 需要的工具包

import java.util.HashMap;

/**
 *@Question:  1679. Max Number of K-Sum Pairs
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.43%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/*
 * 一、题目描述
 *    给定一个整数数组 nums 和一个整数 k，要求找出 nums 中的两个数，使其和等于 k。
 *    每对数只能使用一次，返回最多可以形成多少对符合要求的数对。
 *    示例：
 *      输入：nums = [1,2,3,4], k = 5
 *      输出：2  （因为 1+4 和 2+3 都可以组成 k）
 *
 * 二、解题思路（超级详细）
 *    1. 使用 HashMap 记录数组中每个数字的出现次数，以便快速查找补数 (complement = k - current)。
 *    2. 遍历数组：
 *       - 对于当前数 current，计算其补数 complement = k - current。
 *       - 在 HashMap 中检查 complement 是否已经存在：
 *         - 若存在（且次数 > 0），说明找到一对符合要求的数，计数 +1，并减少补数的次数。
 *         - 若不存在，将 current 记录到 HashMap，并增加其计数。
 *    3. 继续遍历直到所有可能的对都被找到。
 *
 *    例子解析：
 *      nums = [1, 2, 3, 4], k = 5
 *      遍历 1: 需要 4，map 中无 4 -> 存 1
 *      遍历 2: 需要 3，map 中无 3 -> 存 2
 *      遍历 3: 需要 2，map 中有 2 -> 计数 +1，移除 2
 *      遍历 4: 需要 1，map 中有 1 -> 计数 +1，移除 1
 *      最终结果为 2 组（1+4, 2+3）。
 *
 * 三、时间和空间复杂度分析
 *    1. 时间复杂度：O(N)
 *       - 需要遍历整个数组一次，每次查找和更新 HashMap 需要 O(1) 的时间，总体 O(N)。
 *    2. 空间复杂度：O(N)
 *       - 额外使用了 HashMap 存储每个元素的出现次数，最坏情况下存储整个数组 O(N)。
 */


// 定义公共类 LeetCode_1679_MaxNumberOfKSumPairs
public class LeetCode_1679_MaxNumberOfKSumPairs{

    // leetcode 提交区域开始（不可修改）
    //leetcode submit region begin(Prohibit modification and deletion)
    // 定义 Solution 内部类
    class Solution {
        // 定义方法 maxOperations，输入数组 nums 和目标和 k，返回最大对数
        public int maxOperations(int[] nums, int k) {
            // 使用 HashMap 记录每个数的出现次数
            HashMap<Integer, Integer> map = new HashMap<>();
            // 记录可以组成 k 的对数
            int count = 0;
            // 遍历数组
            for (int i = 0; i < nums.length; i++) {
                int current = nums[i]; // 当前元素
                int complement = k - current; // 计算需要的补数
                // 如果补数存在于 map 中，并且次数大于 0，则找到一对
                if (map.getOrDefault(complement, 0) > 0) {
                    // 减少补数的计数
                    map.put(complement, map.get(complement) - 1);
                    count++; // 增加对数
                } else {
                    // 否则，将当前元素存入 map，增加计数
                    map.put(current, map.getOrDefault(current, 0) + 1);
                }
            }
            // 返回最大对数
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    // main 方法，用于测试代码
    public static void main(String[] args) {
        // 创建 Solution 实例
        Solution solution = new LeetCode_1679_MaxNumberOfKSumPairs().new Solution();

        // 测试用例 1
        int[] nums1 = {1, 2, 3, 4};
        int k1 = 5;
        // 预期输出: 2  (1+4, 2+3)
        System.out.println("Test Case 1: " + solution.maxOperations(nums1, k1));

        // 测试用例 2
        int[] nums2 = {3, 1, 3, 4, 3};
        int k2 = 6;
        // 预期输出: 1  (3+3 只找到一对)
        System.out.println("Test Case 2: " + solution.maxOperations(nums2, k2));
    }
}

/**
You are given an integer array nums and an integer k. 

 In one operation, you can pick two numbers from the array whose sum equals k 
and remove them from the array. 

 Return the maximum number of operations you can perform on the array. 

 
 Example 1: 

 
Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations. 

 Example 2: 

 
Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation. 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 1 <= nums[i] <= 10⁹ 
 1 <= k <= 10⁹ 
 

 Related Topics Array Hash Table Two Pointers Sorting 👍 3302 👎 101

*/
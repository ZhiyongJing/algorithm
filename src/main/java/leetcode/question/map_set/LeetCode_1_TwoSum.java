package leetcode.question.map_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Question:  1. Two Sum
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 100.0%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 题目：1. Two Sum
 *
 * **问题描述：**
 * 给定一个整数数组 `nums` 和一个目标值 `target`，在数组中找出两个数使它们的和等于目标值。你可以假设每个输入只对应一个答案，并且你不能重复使用相同的元素。返回这两个数的索引。
 *
 * ### 解题思路：
 *
 * 1. **哈希表**：
 *    - 使用哈希表（`HashMap`）来记录数组中每个元素的值及其对应的索引。哈希表提供了快速的查找和插入操作，使得我们能够在 O(1) 的时间内检查补数是否存在。
 *
 * 2. **遍历数组**：
 *    - 遍历数组的每个元素，计算当前元素的补数（`target - nums[i]`），然后检查这个补数是否在哈希表中已经存在。
 *    - 如果补数存在，则说明找到了两个数，其和等于目标值。返回这两个数的索引即可。
 *    - 如果补数不存在，则将当前元素及其索引存入哈希表中，以便后续可以用来查找其他元素的补数。
 *
 * 3. **时间复杂度和空间复杂度**：
 *    - **时间复杂度**：O(N)，其中 N 是数组的长度。我们需要遍历数组一次，每次对哈希表的查找和插入操作都为 O(1)，因此整体时间复杂度为 O(N)。
 *    - **空间复杂度**：O(N)，在最坏情况下，哈希表中会存储所有 N 个元素的值和索引，因此空间复杂度为 O(N)。
 *
 * 这种方法高效地解决了问题，通过利用哈希表的快速查找特性，避免了暴力破解方法中常见的 O(N^2) 时间复杂度。
 */
public class LeetCode_1_TwoSum{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 方法: 查找两个数的和等于目标值
        public int[] twoSum(int[] nums, int target) {
            // 创建一个哈希表来存储数组值及其索引
            Map<Integer, Integer> map = new HashMap<>();

            // 遍历数组
            for (int i = 0; i < nums.length; i++) {
                // 计算当前元素的补数
                int rest = target - nums[i];

                // 如果补数在哈希表中存在，返回补数的索引和当前元素的索引
                if (map.containsKey(rest)) {
                    return new int[] { map.get(rest), i };
                }

                // 将当前元素及其索引存入哈希表
                map.put(nums[i], i);
            }
            // 如果没有找到满足条件的两个数，则返回 null
            return null;
        }
        /**
         * if input contians dup element,
         */
        public List<int[]> twoSum2(int[] nums, int target) {
            // 创建一个哈希表来存储数组值及其索引
            Map<Integer, List<Integer>> map = new HashMap<>();
            List<int []> result = new ArrayList<>();

            // 遍历数组
            for (int i = 0; i < nums.length; i++) {
                // 计算当前元素的补数
                int rest = target - nums[i];

                // 如果补数在哈希表中存在，返回补数的索引和当前元素的索引
                if (map.containsKey(rest)) {
                    for(int index: map.get(rest)){
                        result.add(new int[]{index, i});
                    }
                }
                if(!map.containsKey(nums[i])){
                    map.put(nums[i], new ArrayList<>());
                }


                // 将当前元素及其索引存入哈希表
                map.get(nums[i]).add(i);
            }
            // 如果没有找到满足条件的两个数，则返回 null
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1_TwoSum().new Solution();

        // 测试代码
        int[] nums = {2, 7, 11, 15}; // 数组
        int target = 9; // 目标值

        // 调用 twoSum 方法并打印结果
        int[] result = solution.twoSum(nums, target);

        // 输出结果
        if (result != null) {
            System.out.println("索引: " + result[0] + ", " + result[1]);
        } else {
            System.out.println("没有找到满足条件的两个数。");

        }
        int[] nums2 = {3, 3, 4, 2, 5, 1, 6};
        List<int[]> res = new ArrayList<>();
        res= solution.twoSum2(nums2, 7);
        for( int[] pair: res){
            System.out.println(pair[0] +" "+ pair[1]);
        }
    }
}

/**
Given an array of integers nums and an integer target, return indices of the 
two numbers such that they add up to target. 

 You may assume that each input would have exactly one solution, and you may 
not use the same element twice. 

 You can return the answer in any order. 

 
 Example 1: 

 
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 

 Example 2: 

 
Input: nums = [3,2,4], target = 6
Output: [1,2]
 

 Example 3: 

 
Input: nums = [3,3], target = 6
Output: [0,1]
 

 
 Constraints: 

 
 2 <= nums.length <= 10⁴ 
 -10⁹ <= nums[i] <= 10⁹ 
 -10⁹ <= target <= 10⁹ 
 Only one valid answer exists. 
 

 
Follow-up: Can you come up with an algorithm that is less than 
O(n²)
 time complexity?

 Related Topics Array Hash Table 👍 54032 👎 1799

*/
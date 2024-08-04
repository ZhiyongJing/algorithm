package leetcode.question.map_set;
/**
 *@Question:  287. Find the Duplicate Number
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 72.22%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * #### 方法 1：使用数组模拟哈希表
 *
 * 1. **初始条件**：给定一个长度为 n + 1 的数组，其中元素范围是 [1, n]，且存在一个重复的数字。
 * 2. **思路**：利用数组的索引和元素值之间的关系，我们可以将每个数字放到其对应的索引位置上，最终会在 `nums[0]` 处发现重复的数字。
 * 3. **过程**：
 *    - 当 `nums[0]` 不等于 `nums[nums[0]]` 时，交换 `nums[0]` 和 `nums[nums[0]]` 的值。这个操作将数字移动到其“正确”的位置，即 `nums[i]` 应该为 `i`。
 *    - 一旦发现 `nums[0]` 等于 `nums[nums[0]]`，说明找到了重复的数字。
 *
 * ##### 时间复杂度
 * 该方法的时间复杂度为 O(n)，因为在最坏情况下，每个元素最多只会被交换一次。
 *
 * ##### 空间复杂度
 * 该方法的空间复杂度为 O(1)，因为只使用了常数额外空间。
 *
 * #### 方法 2：负标记法
 *
 * 1. **初始条件**：同样的数组条件。
 * 2. **思路**：利用数组的正负号来标记访问状态。遍历数组，将每个数字对应索引处的值置为负数。如果遇到一个已经是负数的值，说明我们已经访问过这个索引位置，当前索引所指向的数就是重复数。
 * 3. **过程**：
 *    - 遍历数组，对于每个数值 `cur`，取其绝对值作为索引。
 *    - 如果 `nums[cur]` 为负，说明之前已经访问过，这个数 `cur` 就是重复的。
 *    - 如果 `nums[cur]` 为正，将其置为负数，表示已访问。
 *    - 最后再遍历一遍数组，将所有数恢复为正数。
 *
 * ##### 时间复杂度
 * 该方法的时间复杂度为 O(n)，因为我们需要遍历数组两次，一次是查找重复数，另一次是恢复原数组。
 *
 * ##### 空间复杂度
 * 该方法的空间复杂度为 O(1)，因为我们只使用了常数额外空间来存储重复数的值。
 */

public class LeetCode_287_FindTheDuplicateNumber{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //Solution1: 使用数组模拟哈希表
        public int findDuplicate(int[] nums) {
            // 当 nums[0] 不等于 nums[nums[0]] 时，进行交换
            while (nums[0] != nums[nums[0]]) {
                // 记录下一个位置的值
                int nxt = nums[nums[0]];
                // 将当前值移动到正确的位置
                nums[nums[0]] = nums[0];
                // 更新 nums[0] 为下一个位置的值
                nums[0] = nxt;
            }
            // 返回找到的重复数字
            return nums[0];
        }

        //Solution2: 负标记法
        public int findDuplicate2(int[] nums) {
            int duplicate = -1;
            // 遍历数组
            for (int i = 0; i < nums.length; i++) {
                // 取当前值的绝对值
                int cur = Math.abs(nums[i]);
                // 如果当前位置的值为负数，说明已经访问过，即找到了重复数
                if (nums[cur] < 0) {
                    duplicate = cur;
                    break;
                }
                // 否则，将当前位置的值设为负数
                nums[cur] *= -1;
            }

            // 恢复数组中的数值为正数
            for (int i = 0; i < nums.length; i++)
                nums[i] = Math.abs(nums[i]);

            // 返回找到的重复数字
            return duplicate;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 创建 Solution 对象
        Solution solution = new LeetCode_287_FindTheDuplicateNumber().new Solution();

        // 测试样例
        int[] nums1 = {1, 3, 4, 2, 2};
        int[] nums2 = {3, 1, 3, 4, 2};
        int[] nums3 = {1, 1};

        // 调用方法并输出结果
        System.out.println(solution.findDuplicate(nums1)); // 输出 2
        System.out.println(solution.findDuplicate(nums2)); // 输出 3
        System.out.println(solution.findDuplicate(nums3)); // 输出 1
    }
}

/**
Given an array of integers nums containing n + 1 integers where each integer is 
in the range [1, n] inclusive. 

 There is only one repeated number in nums, return this repeated number. 

 You must solve the problem without modifying the array nums and uses only 
constant extra space. 

 
 Example 1: 

 
Input: nums = [1,3,4,2,2]
Output: 2
 

 Example 2: 

 
Input: nums = [3,1,3,4,2]
Output: 3
 

 Example 3: 

 
Input: nums = [3,3,3,3,3]
Output: 3 

 
 Constraints: 

 
 1 <= n <= 10⁵ 
 nums.length == n + 1 
 1 <= nums[i] <= n 
 All the integers in nums appear only once except for precisely one integer 
which appears two or more times. 
 

 
 Follow up: 

 
 How can we prove that at least one duplicate number must exist in nums? 
 Can you solve the problem in linear runtime complexity? 
 

 Related Topics Array Two Pointers Binary Search Bit Manipulation 👍 23314 👎 46
49

*/
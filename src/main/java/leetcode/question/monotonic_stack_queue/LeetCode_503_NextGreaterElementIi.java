package leetcode.question.monotonic_stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 *@Question:  503. Next Greater Element II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 59.45%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路详细讲解
 *
 * #### 问题理解
 *
 * 给定一个循环数组 `nums`，对于数组中的每个元素，找到它在下一个位置上比它大的第一个元素。如果不存在这样的元素，则返回 -1。循环数组意味着当到达数组的末尾时，下一个元素是数组的第一个元素。
 *
 * #### 解题思路
 *
 * 为了解决这个问题，我们可以使用单调栈来帮助我们找到每个元素的下一个更大元素。单调栈是一种在栈中维护一个单调性的数据结构，通常用于解决类似的问题。具体来说，我们可以使用一个单调递减栈，即栈中的元素从栈底到栈顶是递减的。
 *
 * 步骤如下：
 *
 * 1. **初始化**：
 *    - 创建一个结果数组 `res`，用于存储每个元素的下一个更大元素。初始时，所有元素的下一个更大元素都设置为 -1。
 *    - 创建一个栈 `stack`，用于存储数组元素的索引。
 *
 * 2. **遍历数组**：
 *    - 为了模拟循环数组的特性，我们遍历数组两遍。
 *    - 从数组的最后一个元素开始，向前遍历到第一个元素，再从最后一个元素遍历到第一个元素。
 *
 * 3. **维护单调栈**：
 *    - 对于当前遍历到的元素 `nums[i % n]`：
 *      - 如果栈不为空且栈顶元素小于等于当前元素，则弹出栈顶元素。
 *      - 如果栈为空，说明当前元素右侧没有比它大的元素，结果为 -1。
 *      - 否则，栈顶元素即为当前元素的下一个更大元素，将其赋值给结果数组对应的位置。
 *      - 将当前元素的索引压入栈中。
 *
 * 4. **返回结果**：
 *    - 遍历完成后，结果数组 `res` 即为每个元素的下一个更大元素。
 *
 * ### 复杂度分析
 *
 * #### 时间复杂度
 * - 遍历数组两遍，每个元素最多被压入和弹出栈一次，因此时间复杂度为 O(N)。
 *
 * #### 空间复杂度
 * - 额外使用了一个栈来存储数组元素的索引，栈的空间复杂度为 O(N)。
 * - 结果数组 `res` 也需要 O(N) 的空间。因此，总的空间复杂度为 O(N)。
 *
 * 通过上述解题思路和复杂度分析，我们可以高效地计算出循环数组中每个元素的下一个更大元素。
 */

public class LeetCode_503_NextGreaterElementIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // 定义 nextGreaterElements 方法，接受一个整型数组并返回一个整型数组
        public int[] nextGreaterElements(int[] nums) {
            int[] res = new int[nums.length]; // 初始化结果数组
            Stack<Integer> stack = new Stack<>(); // 初始化栈，用于存储元素索引
            // 遍历数组两遍，以处理循环数组的特性
            for (int i = 2 * nums.length - 1; i >= 0; --i) {
                // 栈不为空且当前元素大于等于栈顶元素，弹出栈顶元素
                while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                    stack.pop();
                }
                // 如果栈为空，则结果为 -1；否则，结果为栈顶元素对应的值
                res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
                // 将当前元素的索引压入栈中
                stack.push(i % nums.length);
            }
            return res; // 返回结果数组
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_503_NextGreaterElementIi().new Solution();

        // 测试样例
        int[] nums = {1, 2, 1};
        int[] result = solution.nextGreaterElements(nums);
        System.out.println(Arrays.toString(result)); // 应该输出 [2, -1, 2]

        int[] nums2 = {3, 8, 4, 1, 2};
        int[] result2 = solution.nextGreaterElements(nums2);
        System.out.println(Arrays.toString(result2)); // 应该输出 [8, -1, 8, 2, 3]
    }
}

/**
Given a circular integer array nums (i.e., the next element of nums[nums.length 
- 1] is nums[0]), return the next greater number for every element in nums. 

 The next greater number of a number x is the first greater number to its 
traversing-order next in the array, which means you could search circularly to find 
its next greater number. If it doesn't exist, return -1 for this number. 

 
 Example 1: 

 
Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.
 

 Example 2: 

 
Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -10⁹ <= nums[i] <= 10⁹ 
 

 Related Topics Array Stack Monotonic Stack 👍 7898 👎 196

*/
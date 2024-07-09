package leetcode.question.monotonic_stack_queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 *@Question:  496. Next Greater Element I
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.71%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路详细讲解：
 *
 * 1. **问题理解**：
 *    - 给定两个数组 `nums1` 和 `nums2`，其中 `nums1` 是 `nums2` 的子集。
 *    - 对于 `nums1` 中的每个元素，找到它在 `nums2` 中的下一个更大元素。如果不存在下一个更大元素，则返回 -1。
 *
 * 2. **单调栈的应用**：
 *    - 为了解决这个问题，我们使用单调栈的思想。单调栈是一种特殊的栈结构，通常用于解决 "下一个更大元素" 或 "上一个更大元素" 的问题。
 *    - 在本题中，我们使用单调递减栈，即栈内元素从栈底到栈顶是递减的。
 *
 * 3. **算法步骤**：
 *    - 创建一个栈 `stack` 和一个哈希表 `map`。
 *    - 遍历数组 `nums2`：
 *      - 对于每个元素 `nums2[i]`，如果栈不为空且当前元素 `nums2[i]` 大于栈顶元素 `stack.peek()`，则栈顶元素找到了下一个更大元素：
 *        - 弹出栈顶元素，将其与当前元素 `nums2[i]` 之间的关系记录在哈希表 `map` 中，即 `map.put(stack.pop(), nums2[i])`。
 *      - 将当前元素 `nums2[i]` 压入栈中。
 *    - 遍历完 `nums2` 后，栈中剩余的元素没有找到下一个更大元素，它们的值应为 -1：
 *      - 将这些元素依次弹出，记录在哈希表 `map` 中，即 `map.put(stack.pop(), -1)`。
 *    - 最后，遍历数组 `nums1`，根据哈希表 `map` 中的记录填充结果数组 `res`，即 `res[i] = map.get(nums1[i])`。
 *
 * 4. **时间复杂度分析**：
 *    - 遍历数组 `nums2` 是 O(N)，其中 N 是数组 `nums2` 的长度。每个元素最多被压入和弹出栈一次，因此时间复杂度为 O(N)。
 *    - 遍历数组 `nums1` 是 O(M)，其中 M 是数组 `nums1` 的长度。
 *    - 因此，总的时间复杂度为 O(N + M)。
 *
 * 5. **空间复杂度分析**：
 *    - 使用了栈和哈希表，最坏情况下存储所有元素，因此空间复杂度为 O(N)。
 *    - 结果数组 `res` 需要额外的 O(M) 空间。
 *    - 因此，总的空间复杂度为 O(N + M)。
 *
 * ### 总结：
 *
 * 通过使用单调栈和哈希表，我们能够高效地解决这个问题。在遍历 `nums2` 的过程中，我们动态地更新每个元素的下一个更大元素信息，确保时间和空间复杂度都保持在线性级别。
 * 最后，通过哈希表的快速查找特性，我们能够在常数时间内完成对 `nums1` 中每个元素的查询。
 */
public class LeetCode_496_NextGreaterElementI{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // 查找 nums1 中每个元素在 nums2 中的下一个更大元素
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Stack<Integer> stack = new Stack<>(); // 创建一个栈，用于存储 nums2 中的元素
            HashMap<Integer, Integer> map = new HashMap<>(); // 创建一个哈希表，用于存储每个元素的下一个更大元素

            // 遍历 nums2 数组
            for (int i = 0; i < nums2.length; i++) {
                // 当栈不为空且当前元素大于栈顶元素时，更新哈希表
                while (!stack.empty() && nums2[i] > stack.peek()) {
                    map.put(stack.pop(), nums2[i]); // 将栈顶元素弹出，并将其下一个更大元素记录到哈希表中
                }
                stack.push(nums2[i]); // 将当前元素压入栈中
            }

            // 对于栈中剩余的元素，没有下一个更大元素，用 -1 表示
            while (!stack.empty()) {
                map.put(stack.pop(), -1); // 将栈中元素弹出，并记录其下一个更大元素为 -1
            }

            int[] res = new int[nums1.length]; // 创建结果数组
            // 遍历 nums1 数组，根据哈希表中的记录填充结果数组
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.get(nums1[i]); // 获取 nums1 中每个元素在哈希表中对应的下一个更大元素
            }

            return res; // 返回结果数组
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_496_NextGreaterElementI().new Solution();

        // 测试样例 1
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] result1 = solution.nextGreaterElement(nums1, nums2);
        System.out.println("测试样例 1 的结果: " + Arrays.toString(result1)); // 应该输出 [-1, 3, -1]

        // 测试样例 2
        int[] nums1 = {2, 4};
        int[] nums2 = {1, 2, 3, 4};
        int[] result2 = solution.nextGreaterElement(nums1, nums2);
        System.out.println("测试样例 2 的结果: " + Arrays.toString(result2)); // 应该输出 [3, -1]
    }
}

/**
 The next greater element of some element x in an array is the first greater
 element that is to the right of x in the same array.

 You are given two distinct 0-indexed integer arrays nums1 and nums2, where 
 nums1 is a subset of nums2.

 For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
 and determine the next greater element of nums2[j] in nums2. If there is no 
 next greater element, then the answer for this query is -1.

 Return an array ans of length nums1.length such that ans[i] is the next 
 greater element as described above.


 Example 1: 


 Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 Output: [-1,3,-1]
 Explanation: The next greater element for each value of nums1 is as follows:
 - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so
 the answer is -1.
 - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so
 the answer is -1.


 Example 2: 


 Input: nums1 = [2,4], nums2 = [1,2,3,4]
 Output: [3,-1]
 Explanation: The next greater element for each value of nums1 is as follows:
 - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so
 the answer is -1.



 Constraints: 


 1 <= nums1.length <= nums2.length <= 1000 
 0 <= nums1[i], nums2[i] <= 10⁴ 
 All integers in nums1 and nums2 are unique. 
 All the integers of nums1 also appear in nums2. 



 Follow up: Could you find an
 O(nums1.length + nums2.length) solution?

 Related Topics Array Hash Table Stack Monotonic Stack 👍 7901 👎 730

 */
package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 *@Question:  189. Rotate Array
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 77.13%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * **题目：**
 *
 * 给定一个数组 `nums` 和一个整数 `k`，要求将数组向右旋转 `k` 个位置。旋转操作是将数组中的元素向右移动 `k` 位，数组的最后 `k` 个元素会移到数组的开头。我们需要实现一个函数来完成这个操作，并且尽量使时间复杂度和空间复杂度最优。
 *
 * **解题思路：**
 *
 * 1. **解决方案1: 环状替换（Cycle Replacement）**
 *    - **步骤：**
 *      1. 计算实际需要移动的步数：`k = k % nums.length`，以防 `k` 大于数组长度。
 *      2. 使用环状替换算法从每个位置开始尝试旋转：
 *         - 对于每个起始位置，跟踪当前位置和之前的位置。
 *         - 交换元素直到回到起始位置。
 *      3. 通过这种方式处理所有元素，以确保所有元素都被移动到正确的位置。
 *    - **时间复杂度：** O(N)
 *      - 每个元素仅会被访问和交换一次，整体复杂度为 O(N)。
 *    - **空间复杂度：** O(1)
 *      - 只使用了常数级别的额外空间，主要是用于存储临时变量。
 *
 * 2. **解决方案2: 使用翻转（Reverse）**
 *    - **步骤：**
 *      1. 计算实际需要移动的步数：`k = k % nums.length`。
 *      2. 将整个数组翻转。
 *      3. 将前 `k` 个元素翻转回原来的顺序。
 *      4. 将剩余的 `n-k` 个元素翻转回原来的顺序。
 *    - **时间复杂度：** O(N)
 *      - 翻转操作需要 O(N) 时间，且需要进行三次翻转，因此整体时间复杂度为 O(N)。
 *    - **空间复杂度：** O(1)
 *      - 只使用了常数级别的额外空间，主要是用于翻转操作的临时变量。
 *
 * ### 详细说明：
 *
 * - **环状替换方法**：
 *   - **优点**：实现简单，适用于需要逐个替换数组元素的情况。
 *   - **缺点**：需要处理每个元素，可能会有多次交换，复杂度在理论上也是 O(N)。
 *
 * - **翻转方法**：
 *   - **优点**：非常高效，空间复杂度最低，利用了翻转操作来实现数组的旋转。
 *   - **缺点**：实现略复杂，需要进行三次翻转操作，但整体复杂度仍然是 O(N)。
 *
 * 总体来说，两种方法的时间复杂度和空间复杂度都很优，但翻转方法通常被认为更为高效和直观。
 */

public class LeetCode_189_RotateArray{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解决方案1: 使用环状替换
        public void rotate1(int[] nums, int k) {
            // 计算实际需要移动的步数，因为移动长度超过数组长度的部分会回到起点
            k = k % nums.length;
            // 记录交换次数
            int count = 0;
            // 从数组的每个位置开始尝试旋转
            for (int start = 0; count < nums.length; start++) {
                // 当前开始的位置
                int current = start;
                // 记录当前的值以便交换
                int prev = nums[start];
                do {
                    // 计算下一个位置的索引
                    int next = (current + k) % nums.length;
                    // 暂存下一个位置的值
                    int temp = nums[next];
                    // 将上一个位置的值放到下一个位置
                    nums[next] = prev;
                    // 更新上一个位置的值
                    prev = temp;
                    // 更新当前的索引位置
                    current = next;
                    // 更新已交换的元素个数
                    count++;
                    // 当循环回到起点时结束内循环
                    System.out.println("current nums is: " + Arrays.toString(nums));

                } while (start != current);
            }
        }

        // 解决方案2: 使用翻转，容易理解
        public void rotate(int[] nums, int k) {
            // 计算实际需要移动的步数
            k %= nums.length;
            System.out.println("Initinal array: "+ Arrays.toString(nums));

            // 翻转整个数组
            reverse(nums, 0, nums.length - 1);
            System.out.println("First reverse: "+ Arrays.toString(nums));
            // 翻转前 k 个元素
            reverse(nums, 0, k - 1);
            System.out.println("Second reverse: "+ Arrays.toString(nums));

            // 翻转剩余的部分
            reverse(nums, k, nums.length - 1);
            System.out.println("Third reverse: "+ Arrays.toString(nums));

        }

        // 翻转数组的指定区间
        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                // 交换 start 和 end 位置的元素
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                // 移动指针
                start++;
                end--;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_189_RotateArray().new Solution();
        // 测试样例
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        solution.rotate(nums, k);
        // 输出: [5, 6, 7, 1, 2, 3, 4]
        System.out.println(Arrays.toString(nums));
    }
}


/**
Given an integer array nums, rotate the array to the right by k steps, where k 
is non-negative. 

 
 Example 1: 

 
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
 

 Example 2: 

 
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 0 <= k <= 10⁵ 
 

 
 Follow up: 

 
 Try to come up with as many solutions as you can. There are at least three 
different ways to solve this problem. 
 Could you do it in-place with O(1) extra space? 
 

 Related Topics Array Math Two Pointers 👍 18048 👎 1980

*/
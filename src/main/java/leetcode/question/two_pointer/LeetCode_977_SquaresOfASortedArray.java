package leetcode.question.two_pointer;

import java.util.Arrays;

/**
 *@Question:  977. Squares of a Sorted Array
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 62.4%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 1. 题目描述：
 *
 * 给定一个升序排序的整数数组 `nums`，其中包含 `n` 个元素。你需要返回一个新数组，
 * 其中包含 `nums` 中每个元素的平方，并且返回的结果数组也必须是升序排列的。
 *
 * 输入：一个整数数组 `nums`，其中的元素可以是负数、零或者正数，且数组已按升序排序。
 * 输出：一个包含 `nums` 中所有元素的平方的新数组，并且该数组按升序排列。
 *
 * 示例：
 * 输入：`[-4, -1, 0, 3, 10]`
 * 输出：`[0, 1, 9, 16, 100]`
 *
 * 输入：`[-7, -3, 2, 3, 11]`
 * 输出：`[4, 9, 9, 49, 121]`
 *
 * 2. 解题思路：
 *
 * - 由于输入数组已经是升序排列的，数组中的元素可以是负数、零或正数，因此平方后较大的值可能出现在数组的两端。
 * - 我们可以使用双指针法来解决此问题。设置两个指针，左指针 `left` 指向数组的第一个元素，右指针 `right` 指向数组的最后一个元素。
 *
 * 步骤：
 * 1. 初始化两个指针 `left` 和 `right`：
 *    - `left` 指针指向数组的第一个元素（索引为 0），`right` 指针指向数组的最后一个元素（索引为 `n - 1`）。
 *    - 例如，对于数组 `[-4, -1, 0, 3, 10]`，`left` 指向 `-4`，`right` 指向 `10`。
 *
 * 2. 创建一个新的结果数组 `result` 用于存储平方后的元素：
 *    - 结果数组的大小与输入数组相同，初始化时为空。
 *
 * 3. 双指针遍历数组：
 *    - 比较 `left` 和 `right` 指向的两个元素的绝对值，选择较大的绝对值并计算其平方，插入到 `result` 数组的末尾。
 *    - 每次比较后，相应地调整指针：
 *      - 如果左边的元素绝对值较大，则将 `left` 向右移动（`left++`）。
 *      - 如果右边的元素绝对值较大，则将 `right` 向左移动（`right--`）。
 *    - 重复此过程直到 `left` 超过 `right`，此时结果数组中包含所有元素的平方。
 *    - 例如，给定 `nums = [-4, -1, 0, 3, 10]`：
 *      - 第一步：比较 `|-4|` 和 `|10|`，选择 `10`，并计算其平方 `100`，将其放入结果数组 `[100]`。
 *      - 第二步：比较 `|-4|` 和 `|3|`，选择 `3`，并计算其平方 `9`，将其放入结果数组 `[100, 9]`。
 *      - 第三步：继续此过程，最终得到 `[0, 1, 9, 16, 100]`。
 *
 * 4. 返回结果数组 `result`，即为所求的平方后的升序数组。

 * 3. 时间和空间复杂度：
 *
 * - 时间复杂度：O(N)，我们只需要遍历一次输入数组，比较和计算平方操作的时间复杂度为 O(1)，因此总时间复杂度为 O(N)，其中 N 是数组的长度。
 * - 空间复杂度：O(N)，我们需要一个与输入数组长度相同的结果数组来存储平方后的元素，因此空间复杂度为 O(N)。
 */


public class LeetCode_977_SquaresOfASortedArray{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 该方法用于返回一个新的数组，数组的每个元素是输入数组对应元素的平方，并且该数组是升序排序的
        public int[] sortedSquares(int[] nums) {
            int n = nums.length; // 获取输入数组的长度
            int[] result = new int[n]; // 创建一个与输入数组相同长度的结果数组
            int left = 0; // 初始化左指针
            int right = n - 1; // 初始化右指针

            // 从数组的末尾开始遍历结果数组，确保排序是升序的
            for (int i = n - 1; i >= 0; i--) {
                int square; // 用于存储当前处理元素的平方

                // 比较左右两边元素的绝对值，选择较大的元素计算平方
                if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                    square = nums[right]; // 如果右边元素的绝对值更大，选择右边元素
                    right--; // 右指针向左移动
                } else {
                    square = nums[left]; // 否则选择左边元素
                    left++; // 左指针向右移动
                }

                result[i] = square * square; // 将当前选择元素的平方放入结果数组的相应位置
            }
            return result; // 返回结果数组
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_977_SquaresOfASortedArray().new Solution();

        // 测试样例1
        int[] nums1 = {-4, -1, 0, 3, 10};
        int[] result1 = solution.sortedSquares(nums1);
        System.out.println("Test 1: " + Arrays.toString(result1)); // 输出：[0, 1, 9, 16, 100]

        // 测试样例2
        int[] nums2 = {-7, -3, 2, 3, 11};
        int[] result2 = solution.sortedSquares(nums2);
        System.out.println("Test 2: " + Arrays.toString(result2)); // 输出：[4, 9, 9, 49, 121]
    }
}

/**
Given an integer array nums sorted in non-decreasing order, return an array of 
the squares of each number sorted in non-decreasing order. 

 
 Example 1: 

 
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
 

 Example 2: 

 
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -10⁴ <= nums[i] <= 10⁴ 
 nums is sorted in non-decreasing order. 
 

 
Follow up: Squaring each element and sorting the new array is very trivial, 
could you find an 
O(n) solution using a different approach?

 Related Topics Array Two Pointers Sorting 👍 9511 👎 247

*/
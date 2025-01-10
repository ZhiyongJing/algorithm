package leetcode.question.two_pointer;

/**
 *@Question: 1574. Shortest Subarray to be Removed to Make Array Sorted
 *@Difficulty: 2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 72.89%
 *@Time Complexity: O(N), where N is the size of the array
 *@Space Complexity: O(1)
 */
/**
 *@Question: 1574. Shortest Subarray to be Removed to Make Array Sorted
 *
 *@Description:
 * 给定一个整数数组 `arr`，需要通过删除一个连续的子数组使得剩余的数组是非递减顺序。
 * 返回可以删除的最短子数组的长度。
 *
 * - 示例 1:
 *   输入: arr = [1, 2, 3, 10, 4, 2, 3, 5]
 *   输出: 3
 *   解释: 删除 [10, 4, 2] 后，剩余数组 [1, 2, 3, 3, 5] 是非递减的。
 *
 * - 示例 2:
 *   输入: arr = [5, 4, 3, 2, 1]
 *   输出: 4
 *   解释: 删除 [5, 4, 3, 2] 或 [4, 3, 2, 1]。
 *
 * - 示例 3:
 *   输入: arr = [1, 2, 3]
 *   输出: 0
 *   解释: 数组本身已经是非递减的。
 *
 * - 示例 4:
 *   输入: arr = [1]
 *   输出: 0
 *   解释: 数组本身已经是非递减的。
 *
 *
 *@Solution:
 * 1. 从右向左扫描数组，找到第一个不满足非递减序列的位置 `right`，即 `arr[right] < arr[right - 1]`。
 *    如果 `right == 0`，说明数组本身已经是非递减的，直接返回 0。
 *
 * 2. 初始化需要删除的最短子数组长度 `ans = right`，这是删除从起点到 `right` 的子数组所需的长度。
 *
 * 3. 从左向右扫描数组，找到可能保留的左侧子数组的结束位置 `left`：
 *    - 在扫描过程中，确保左侧子数组是非递减的，即满足 `arr[left - 1] <= arr[left]`。
 *    - 对于每个 `left`，尝试将其与右侧的递增子数组连接：
 *      - 使用双指针从 `right` 开始，找到第一个满足 `arr[left] <= arr[right]` 的位置。
 *      - 计算删除子数组的长度 `right - left - 1` 并更新 `ans`。
 *
 * 4. 返回最终的 `ans`。
 *
 *@Time Complexity: O(N)
 * - 找到初始的 `right` 需要 O(N)。
 * - 双指针扫描左侧和右侧也需要 O(N)。
 * - 总时间复杂度为 O(N)。
 *
 *@Space Complexity: O(1)
 * - 使用了常数空间存储索引和变量，因此空间复杂度为 O(1)。
 */

public class LeetCode_1574_ShortestSubarrayToBeRemovedToMakeArraySorted {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int findLengthOfShortestSubarray(int[] arr) {
            // 从右往左找到第一个不满足递增的索引位置
            int right = arr.length - 1;
            while (right > 0 && arr[right] >= arr[right - 1]) {
                right--;
            }

            // 如果整个数组本来就是递增的，直接返回0
            if (right == 0) return 0;

            // 记录当前需要删除的子数组长度
            int ans = right;

            // 从左往右检查可能保留的左侧子数组
            int left = 0;
            while (left < right && (left == 0 || arr[left - 1] <= arr[left])) {
                // 找到当前 `arr[left]` 在右侧符合条件的位置
                while (right < arr.length && arr[left] > arr[right]) {
                    right++;
                }
                // 更新需要删除的子数组长度
                ans = Math.min(ans, right - left - 1);
                left++;
            }
            return ans; // 返回最终需要删除的子数组长度
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1574_ShortestSubarrayToBeRemovedToMakeArraySorted().new Solution();
        // 测试样例
        int[] arr1 = {1, 2, 3, 10, 4, 2, 3, 5};
        System.out.println(solution.findLengthOfShortestSubarray(arr1)); // 输出: 3

        int[] arr2 = {5, 4, 3, 2, 1};
        System.out.println(solution.findLengthOfShortestSubarray(arr2)); // 输出: 4

        int[] arr3 = {1, 2, 3};
        System.out.println(solution.findLengthOfShortestSubarray(arr3)); // 输出: 0

        int[] arr4 = {1};
        System.out.println(solution.findLengthOfShortestSubarray(arr4)); // 输出: 0
    }
}

/**
Given an integer array arr, remove a subarray (can be empty) from arr such that 
the remaining elements in arr are non-decreasing. 

 Return the length of the shortest subarray to remove. 

 A subarray is a contiguous subsequence of the array. 

 
 Example 1: 

 
Input: arr = [1,2,3,10,4,2,3,5]
Output: 3
Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The 
remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].
 

 Example 2: 

 
Input: arr = [5,4,3,2,1]
Output: 4
Explanation: Since the array is strictly decreasing, we can only keep a single 
element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or 
[4,3,2,1].
 

 Example 3: 

 
Input: arr = [1,2,3]
Output: 0
Explanation: The array is already non-decreasing. We do not need to remove any 
elements.
 

 
 Constraints: 

 
 1 <= arr.length <= 10⁵ 
 0 <= arr[i] <= 10⁹ 
 

 Related Topics Array Two Pointers Binary Search Stack Monotonic Stack 👍 2342 ?
? 143

*/
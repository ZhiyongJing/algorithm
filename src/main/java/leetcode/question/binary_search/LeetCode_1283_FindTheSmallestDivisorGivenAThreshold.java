package leetcode.question.binary_search;

/**
 *@Question:  1283. Find the Smallest Divisor Given a Threshold
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 55.53%
 *@Time  Complexity: O(N * log(M)), N is array length, and M is the maximum element of the array
 *@Space Complexity: O(1)
 */
/**
 * 题目：1283. Given a threshold, find the smallest divisor.
 *
 * 题目描述：
 * 给定一个数组 `nums` 和一个整数 `threshold`，要求找出一个除数 `divisor`，使得将 `nums` 中的每个元素除以该除数后的和不超过 `threshold`。
 * 并且我们需要返回最小的除数 `divisor`。
 * 每个元素除以除数时，我们使用向上取整的方式（即 `ceil`）来确保每个结果为整数。
 *
 * 解题思路：
 * 本题可以使用二分查找的方式来找到最小的除数，下面是具体的思路：
 *
 * 1. **确定二分查找的边界**：
 *    - 二分查找的搜索范围是 [1, max(nums)]，其中 `max(nums)` 是数组中的最大元素。
 *    - 之所以从 1 开始，是因为除数不能为 0。
 *
 * 2. **二分查找**：
 *    - 我们希望找到最小的除数，使得每个元素除以该除数后的和不超过 `threshold`。
 *    - 对于当前的除数 `mid`，我们计算所有元素除以 `mid` 后的和，并判断它是否小于或等于 `threshold`。
 *    - 如果当前的除数 `mid` 满足条件，则可能还存在更小的除数，因此我们继续缩小搜索范围，将上边界缩小。
 *    - 如果当前的除数 `mid` 不满足条件，则说明除数太小，需要增大除数，所以我们将下边界增大。
 *
 * 3. **计算当前除数下的和**：
 *    - 每次二分查找时，我们需要计算数组中所有元素除以当前除数 `mid` 后的和，这个操作是一个 O(N) 的复杂度。
 *    - 对每个元素 `num`，我们计算 `(num + mid - 1) / mid`，这样可以模拟向上取整的过程。即 `ceil(num / mid)`。
 *
 * 4. **返回最小的除数**：
 *    - 最终，我们通过二分查找得出一个最小的除数 `mid`，使得所有元素除以 `mid` 后的和不超过 `threshold`。

 * 时间复杂度：
 * - 我们的算法是通过二分查找来确定最小除数，因此在每次二分查找中，我们需要计算 `findDivisionSum`，其复杂度是 O(N)。
 * - 二分查找的搜索范围是 [1, max(nums)]，所以它的复杂度为 O(log(max(nums)))。
 * - 因此，总的时间复杂度是 O(N * log(max(nums)))，其中 N 是数组的长度，max(nums) 是数组中的最大元素值。

 * 空间复杂度：
 * - 该算法只使用了常数级别的额外空间，所以空间复杂度是 O(1)。
 */


public class LeetCode_1283_FindTheSmallestDivisorGivenAThreshold{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 该函数返回给定除数下所有元素除以该除数后的和
        private int findDivisionSum(int[] nums, int divisor) {
            int result = 0;
            // 遍历数组中的每个数
            for (int num : nums) {
                // 计算每个数除以 divisor 后的结果并累加
                result += Math.ceil((1.0 * num) / divisor);  // Math.ceil() 返回大于或等于给定数字的最小整数
            }
            return result;
        }

        public int smallestDivisor(int[] nums, int threshold) {
            int ans = -1;

            // 设置二分查找的范围，low 为 1，因为除数不能为 0
            int low = 1;
            // high 设置为数组中的最大值
            int high = nums[0];
            for (int num : nums) {
                high = Math.max(high, num);  // 找到数组中的最大值
            }

            // 使用二分查找，查找满足条件的最小除数
            while (low <= high) {
                // 中间值
                int mid = (low + high) / 2;
                // 计算当前除数下的总和
                int result = findDivisionSum(nums, mid);
                // 如果当前除数的结果不超过阈值，则可以是答案，但也要继续尝试更小的除数
                // 所以我们将搜索范围缩小到左半部分
                if (result <= threshold) {
                    ans = mid;  // 更新答案
                    high = mid - 1;  // 继续在左半部分查找更小的除数
                }
                // 否则，我们需要更大的除数来减小结果的和
                // 所以将搜索范围缩小到右半部分
                else {
                    low = mid + 1;  // 继续在右半部分查找更大的除数
                }
            }

            return ans;  // 返回找到的最小除数
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1283_FindTheSmallestDivisorGivenAThreshold().new Solution();

        // 测试样例
        int[] nums = {1, 2, 5, 9};
        int threshold = 6;

        // 调用方法并输出结果
        int result = solution.smallestDivisor(nums, threshold);
        System.out.println("Smallest Divisor: " + result);  // 结果应该是 5
    }
}

/**
Given an array of integers nums and an integer threshold, we will choose a 
positive integer divisor, divide all the array by it, and sum the division's result. 
Find the smallest divisor such that the result mentioned above is less than or 
equal to threshold. 

 Each result of the division is rounded to the nearest integer greater than or 
equal to that element. (For example: 7/3 = 3 and 10/2 = 5). 

 The test cases are generated so that there will be an answer. 

 
 Example 1: 

 
Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the 
sum will be 5 (1+1+1+2). 
 

 Example 2: 

 
Input: nums = [44,22,33,11,1], threshold = 5
Output: 44
 

 
 Constraints: 

 
 1 <= nums.length <= 5 * 10⁴ 
 1 <= nums[i] <= 10⁶ 
 nums.length <= threshold <= 10⁶ 
 

 Related Topics Array Binary Search 👍 3084 👎 211

*/